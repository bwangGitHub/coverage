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
 * ------------------------
 * XYPolygonAnnotation.java
 * ------------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 09-Feb-2005 : Version 1 (DG);
 * 
 */
 
package org.jfree.chart.annotations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import org.jfree.chart.HashUtilities;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A polygon annotation that can be placed on an {@link XYPlot}.  The 
 * polygon coordinates are specified in data space.
 */
public class XYPolygonAnnotation extends AbstractXYAnnotation
                                 implements Cloneable, 
                                            PublicCloneable, 
                                            Serializable {
  static {
    CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -6984203651995900036L;
  static {
    CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[1]++;
  }
    
    /** The polygon. */
    private double[] polygon;

    /** The stroke used to draw the box outline. */
    private transient Stroke stroke;

    /** The paint used to draw the box outline. */
    private transient Paint outlinePaint;
    
    /** The paint used to fill the box. */
    private transient Paint fillPaint;

    /**
     * Creates a new annotation (where, by default, the polygon is drawn 
     * with a black outline).  The array of polygon coordinates must contain
     * an even number of coordinates (each pair is an (x, y) location on the
     * plot) and the last point is automatically joined back to the first point.
     * 
     * @param polygon  the coordinates of the polygon's vertices 
     *     (<code>null</code> not permitted).
     */
    public XYPolygonAnnotation(double[] polygon) {
        this(polygon, new BasicStroke(1.0f), Color.black);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[2]++;
    }
    
    /**
     * Creates a new annotation where the box is drawn as an outline using
     * the specified <code>stroke</code> and <code>outlinePaint</code>.  
     * The array of polygon coordinates must contain an even number of 
     * coordinates (each pair is an (x, y) location on the plot) and the last 
     * point is automatically joined back to the first point.
     *
     * @param polygon  the coordinates of the polygon's vertices 
     *     (<code>null</code> not permitted).
     * @param stroke  the shape stroke (<code>null</code> permitted).
     * @param outlinePaint  the shape color (<code>null</code> permitted).
     */
    public XYPolygonAnnotation(double[] polygon, 
                               Stroke stroke, Paint outlinePaint) {
        this(polygon, stroke, outlinePaint, null);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[3]++;
    }

    /**
     * Creates a new annotation.  The array of polygon coordinates must 
     * contain an even number of coordinates (each pair is an (x, y) location 
     * on the plot) and the last point is automatically joined back to the 
     * first point.
     *
     * @param polygon  the coordinates of the polygon's vertices 
     *     (<code>null</code> not permitted).
     * @param stroke  the shape stroke (<code>null</code> permitted).
     * @param outlinePaint  the shape color (<code>null</code> permitted).
     * @param fillPaint  the paint used to fill the shape (<code>null</code> 
     *                   permitted).
     */
    public XYPolygonAnnotation(double[] polygon, 
                               Stroke stroke, 
                               Paint outlinePaint, Paint fillPaint) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((polygon == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[1]++;
            throw new IllegalArgumentException("Null 'polygon' argument.");

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[2]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((polygon.length % 2 != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[3]++;
            throw new IllegalArgumentException("The 'polygon' array must " 
                    + "contain an even number of items.");

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[4]++;}
        this.polygon = (double[]) polygon.clone();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[6]++;
        this.stroke = stroke;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[7]++;
        this.outlinePaint = outlinePaint;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[8]++;
        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[9]++;
    }
    
    /**
     * Returns the coordinates of the polygon's vertices.  The returned array
     * is a copy, so it is safe to modify without altering the annotation's 
     * state.
     * 
     * @return The coordinates of the polygon's vertices.
     * 
     * @since 1.0.2
     */
    public double[] getPolygonCoordinates() {
        return (double[]) this.polygon.clone();
    }
    
    /**
     * Returns the fill paint.
     * 
     * @return The fill paint (possibly <code>null</code>).
     * 
     * @since 1.0.2
     */
    public Paint getFillPaint() {
        return this.fillPaint;
    }
    
    /**
     * Returns the outline stroke.
     * 
     * @return The outline stroke (possibly <code>null</code>).
     * 
     * @since 1.0.2
     */
    public Stroke getOutlineStroke() {
        return this.stroke;
    }
    
    /**
     * Returns the outline paint.
     * 
     * @return The outline paint (possibly <code>null</code>).
     * 
     * @since 1.0.2
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }

    /**
     * Draws the annotation.  This method is usually called by the 
     * {@link XYPlot} class, you shouldn't need to call it directly.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  the plot rendering info.
     */
    public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis, 
                     int rendererIndex, PlotRenderingInfo info) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;

        // if we don't have at least 2 (x, y) coordinates, just return
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.polygon.length < 4) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[5]++;
            return;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[6]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[11]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[12]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[13]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[14]++;

        GeneralPath area = new GeneralPath();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[15]++;
        double x = domainAxis.valueToJava2D(this.polygon[0], dataArea, 
                domainEdge);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[16]++;
        double y = rangeAxis.valueToJava2D(this.polygon[1], dataArea, 
                rangeEdge);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[7]++;
            area.moveTo((float) y, (float) x);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[18]++;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
            for (int i = 2;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < this.polygon.length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i += 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[1]--;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[2]--;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[3]++;
}
                x = domainAxis.valueToJava2D(this.polygon[i], dataArea, 
                        domainEdge);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[20]++;
                y = rangeAxis.valueToJava2D(this.polygon[i + 1], dataArea, 
                        rangeEdge);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[21]++;
                area.lineTo((float) y, (float) x);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[22]++;
            }
            area.closePath();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[23]++;

        }
        else {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[8]++;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[24]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[9]++;
            area.moveTo((float) x, (float) y);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[25]++;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;            
            for (int i = 2;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < this.polygon.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i += 2) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[4]--;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[5]--;
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.loops[6]++;
}
                x = domainAxis.valueToJava2D(this.polygon[i], dataArea, 
                        domainEdge);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[27]++;
                y = rangeAxis.valueToJava2D(this.polygon[i + 1], dataArea, 
                        rangeEdge);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[28]++;
                area.lineTo((float) x, (float) y);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[29]++;
            }
            area.closePath();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[30]++;

       } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[10]++;}
}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.fillPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[11]++;
            g2.setPaint(this.fillPaint);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[32]++;
            g2.fill(area);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[33]++;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[12]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
        
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((this.stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[13]++;
            g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[35]++;
            g2.setStroke(this.stroke);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[36]++;
            g2.draw(area);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[37]++;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[14]++;}
        addEntity(info, area, rendererIndex, getToolTipText(), getURL());
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[38]++;
        
    }
        
    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[16]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        // now try to reject equality
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[18]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj instanceof XYPolygonAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[20]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[42]++;
        XYPolygonAnnotation that = (XYPolygonAnnotation) obj;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.polygon, that.polygon)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[21]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[22]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[24]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[45]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[26]++;}
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[46]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[28]++;}
        // seem to be the same
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[47]++;
        int result = 193;
        result = 37 * result + HashUtilities.hashCodeForDoubleArray(
                this.polygon);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[48]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.fillPaint);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[49]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.outlinePaint);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[50]++;
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[51]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[29]++;
            result = 37 * result + this.stroke.hashCode();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[52]++;

        } else {
  CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.branches[30]++;}
        return result;
    }
    
    /**
     * Returns a clone.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not thrown by this class, but may be
     *                                    by subclasses.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream (<code>null</code> not permitted).
     *
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[53]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[54]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[55]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[56]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream (<code>null</code> not permitted).
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[57]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[58]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[59]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl.statements[60]++;
    }

}

class CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl ());
  }
    public static long[] statements = new long[61];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYPolygonAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$kd1qxbt0hwx51fpcvtd74rl93ffa687yajlnl () {
    super("org.jfree.chart.annotations.XYPolygonAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 60; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.annotations.XYPolygonAnnotation.java");
      for (int i = 1; i <= 60; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

