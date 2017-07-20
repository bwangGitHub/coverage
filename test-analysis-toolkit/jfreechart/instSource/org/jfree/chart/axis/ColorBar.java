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
 * -------------
 * ColorBar.java
 * -------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 26-Nov-2002 : Version 1 contributed by David M. O'Donnell (DG);
 * 14-Jan-2003 : Changed autoRangeMinimumSize from Number --> double (DG);
 * 17-Jan-2003 : Moved plot classes to separate package (DG);
 * 20-Jan-2003 : Removed unnecessary constructors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 09-Jul-2003 : Changed ColorBar from extending axis classes to enclosing 
 *               them (DG);
 * 05-Aug-2003 : Applied changes in bug report 780298 (DG);
 * 14-Aug-2003 : Implemented Cloneable (DG);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.plot.ColorPalette;
import org.jfree.chart.plot.ContourPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.RainbowPalette;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.ui.RectangleEdge;

/**
 * A color bar.
 *
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class ColorBar implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2101776212647268103L;
  static {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[1]++;
  }
    
    /** The default color bar thickness. */
    public static final int DEFAULT_COLORBAR_THICKNESS = 0;
  static {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[2]++;
  }

    /** The default color bar thickness percentage. */
    public static final double DEFAULT_COLORBAR_THICKNESS_PERCENT = 0.10;
  static {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[3]++;
  }

    /** The default outer gap. */
    public static final int DEFAULT_OUTERGAP = 2;
  static {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[4]++;
  }

    /** The axis. */
    private ValueAxis axis;
    
    /** The color bar thickness. */
    private int colorBarThickness = DEFAULT_COLORBAR_THICKNESS;
  {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[5]++;
  }

    /** 
     * The color bar thickness as a percentage of the height of the data area. 
     */
    private double colorBarThicknessPercent 
            = DEFAULT_COLORBAR_THICKNESS_PERCENT;
  {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[6]++;
  }

    /** The color palette. */
    private ColorPalette colorPalette = null;
  {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[7]++;
  }

    /** The color bar length. */
    private int colorBarLength = 0;
  {
    CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[8]++;
  } // default make height of plotArea

    /** The amount of blank space around the colorbar. */
    private int outerGap;

    /**
     * Constructs a horizontal colorbar axis, using default values where 
     * necessary.
     *
     * @param label  the axis label.
     */
    public ColorBar(String label) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[9]++;
   
        NumberAxis a = new NumberAxis(label);
        a.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[10]++;
        this.axis = a;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[11]++;
        this.axis.setLowerMargin(0.0);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[12]++;
        this.axis.setUpperMargin(0.0);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[13]++;

        this.colorPalette = new RainbowPalette();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[14]++;
        this.colorBarThickness = DEFAULT_COLORBAR_THICKNESS;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[15]++;
        this.colorBarThicknessPercent = DEFAULT_COLORBAR_THICKNESS_PERCENT;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[16]++;
        this.outerGap = DEFAULT_OUTERGAP;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[17]++;
        this.colorPalette.setMinZ(this.axis.getRange().getLowerBound());
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[18]++;
        this.colorPalette.setMaxZ(this.axis.getRange().getUpperBound());
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[19]++;

    }

    /**
     * Configures the color bar.
     * 
     * @param plot  the plot.
     */
    public void configure(ContourPlot plot) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[20]++;
        double minZ = plot.getDataset().getMinZValue();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[21]++;
        double maxZ = plot.getDataset().getMaxZValue();
        setMinimumValue(minZ);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[22]++;
        setMaximumValue(maxZ);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[23]++;
    }
    
    /**
     * Returns the axis.
     * 
     * @return The axis.
     */
    public ValueAxis getAxis() {
        return this.axis;
    }
    
    /**
     * Sets the axis.
     * 
     * @param axis  the axis.
     */
    public void setAxis(ValueAxis axis) {
        this.axis = axis;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[24]++;
    }
    
    /**
     * Rescales the axis to ensure that all data are visible.
     */
    public void autoAdjustRange() {
        this.axis.autoAdjustRange();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[25]++;
        this.colorPalette.setMinZ(this.axis.getLowerBound());
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[26]++;
        this.colorPalette.setMaxZ(this.axis.getUpperBound());
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[27]++;
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param cursor  the cursor.
     * @param plotArea  the area within which the chart should be drawn.
     * @param dataArea  the area within which the plot should be drawn (a
     *                  subset of the drawArea).
     * @param reservedArea  the reserved area.
     * @param edge  the color bar location.
     * 
     * @return The new cursor location.
     */
    public double draw(Graphics2D g2, double cursor,
                       Rectangle2D plotArea, Rectangle2D dataArea, 
                       Rectangle2D reservedArea, RectangleEdge edge) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[28]++;


        Rectangle2D colorBarArea = null;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[29]++;
        
        double thickness = calculateBarThickness(dataArea, edge);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[30]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.colorBarThickness > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[1]++;
            thickness = this.colorBarThickness;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[31]++;
  // allow fixed thickness
        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[2]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[32]++;

        double length = 0.0;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[33]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[3]++;
            length = dataArea.getHeight();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[34]++;

        }
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[4]++;
            length = dataArea.getWidth();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[35]++;
        }
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[36]++;
int CodeCoverConditionCoverageHelper_C3;
        
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.colorBarLength > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[5]++;
            length = this.colorBarLength;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[37]++;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[6]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[38]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[7]++;
            colorBarArea = new Rectangle2D.Double(dataArea.getX(), 
                    plotArea.getMaxY() + this.outerGap, length, thickness);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[39]++;

        }
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[8]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[40]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[9]++;
            colorBarArea = new Rectangle2D.Double(dataArea.getX(), 
                    reservedArea.getMinY() + this.outerGap, length, thickness);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[41]++;

        }
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[10]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[42]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[11]++;
            colorBarArea = new Rectangle2D.Double(plotArea.getX() - thickness 
                    - this.outerGap, dataArea.getMinY(), thickness, length);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[43]++;

        }
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[12]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[44]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[13]++;
            colorBarArea = new Rectangle2D.Double(plotArea.getMaxX() 
                    + this.outerGap, dataArea.getMinY(), thickness, length);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[45]++;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[14]++;}
}
}
}
        
        // update, but dont draw tick marks (needed for stepped colors)
        this.axis.refreshTicks(g2, new AxisState(), colorBarArea, edge);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[46]++;

        drawColorBar(g2, colorBarArea, edge);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[47]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[48]++;

        AxisState state = null;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[49]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[15]++;
            cursor = colorBarArea.getMinY();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[50]++;
            state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, 
                    RectangleEdge.TOP, null);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[51]++;

        } 
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[16]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[52]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[17]++;
            cursor = colorBarArea.getMaxY();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[53]++;
            state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, 
                    RectangleEdge.BOTTOM, null);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[54]++;

        } 
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[18]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[55]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[19]++;
            cursor = colorBarArea.getMinX();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[56]++;
            state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, 
                    RectangleEdge.LEFT, null);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[57]++;

        } 
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[20]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[58]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[21]++;
            cursor = colorBarArea.getMaxX();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[59]++;
            state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, 
                    RectangleEdge.RIGHT, null);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[60]++;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[22]++;}
}
}
}
        return state.getCursor();
        
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param colorBarArea  the area within which the axis should be drawn.
     * @param edge  the location.
     */
    public void drawColorBar(Graphics2D g2, Rectangle2D colorBarArea, 
                             RectangleEdge edge) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[61]++;

        Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_OFF);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[62]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[63]++;

        // setTickValues was missing from ColorPalette v. 0.96
        //colorPalette.setTickValues(this.axis.getTicks());

        Stroke strokeSaved = g2.getStroke();
        g2.setStroke(new BasicStroke(1.0f));
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[64]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[65]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[23]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[66]++;
            double y1 = colorBarArea.getY();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[67]++;
            double y2 = colorBarArea.getMaxY();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[68]++;
            double xx = colorBarArea.getX();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[69]++;
            Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[70]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
            while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((xx <= colorBarArea.getMaxX()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[1]--;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[2]--;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[3]++;
}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[71]++;
                double value = this.axis.java2DToValue(xx, colorBarArea, edge);
                line.setLine(xx, y1, xx, y2);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[72]++;
                g2.setPaint(getPaint(value));
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[73]++;
                g2.draw(line);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[74]++;
                xx += 1;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[75]++;
            }

        }
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[24]++;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[76]++;
            double y1 = colorBarArea.getX();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[77]++;
            double y2 = colorBarArea.getMaxX();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[78]++;
            double xx = colorBarArea.getY();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[79]++;
            Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[80]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;
            while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((xx <= colorBarArea.getMaxY()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[4]--;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[5]--;
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.loops[6]++;
}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[81]++;
                double value = this.axis.java2DToValue(xx, colorBarArea, edge);
                line.setLine(y1, xx, y2, xx);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[82]++;
                g2.setPaint(getPaint(value));
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[83]++;
                g2.draw(line);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[84]++;
                xx += 1;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[85]++;
            }            
        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[86]++;
        g2.setStroke(strokeSaved);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[87]++;

    }

    /**
     * Returns the color palette.
     *
     * @return The color palette.
     */
    public ColorPalette getColorPalette() {
        return this.colorPalette;
    }

    /**
     * Returns the Paint associated with a value.
     *
     * @param value  the value.
     *
     * @return The paint.
     */
    public Paint getPaint(double value) {
        return this.colorPalette.getPaint(value);
    }

    /**
     * Sets the color palette.
     *
     * @param palette  the new palette.
     */
    public void setColorPalette(ColorPalette palette) {
        this.colorPalette = palette;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[88]++;
    }

    /**
     * Sets the maximum value.
     *
     * @param value  the maximum value.
     */
    public void setMaximumValue(double value) {
        this.colorPalette.setMaxZ(value);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[89]++;
        this.axis.setUpperBound(value);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[90]++;
    }

    /**
     * Sets the minimum value.
     *
     * @param value  the minimum value.
     */
    public void setMinimumValue(double value) {
        this.colorPalette.setMinZ(value);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[91]++;
        this.axis.setLowerBound(value);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[92]++;
    }

    /**
     * Reserves the space required to draw the color bar.
     *
     * @param g2  the graphics device.
     * @param plot  the plot that the axis belongs to.
     * @param plotArea  the area within which the plot should be drawn.
     * @param dataArea  the data area.
     * @param edge  the axis location.
     * @param space  the space already reserved.
     *
     * @return The space required to draw the axis in the specified plot area.
     */
    public AxisSpace reserveSpace(Graphics2D g2, Plot plot, 
                                  Rectangle2D plotArea,
                                  Rectangle2D dataArea, RectangleEdge edge, 
                                  AxisSpace space) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[93]++;

        AxisSpace result = this.axis.reserveSpace(g2, plot, plotArea, edge, 
                space);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[94]++;
        double thickness = calculateBarThickness(dataArea, edge);
        result.add(thickness + 2 * this.outerGap, edge);
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[95]++;
        return result;

    }
    
    /**
     * Calculates the bar thickness.
     * 
     * @param plotArea  the plot area.
     * @param edge  the location.
     * 
     * @return The thickness.
     */
    private double calculateBarThickness(Rectangle2D plotArea, 
                                         RectangleEdge edge) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[96]++;
        double result = 0.0;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[97]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[25]++;
            result = plotArea.getWidth() * this.colorBarThicknessPercent;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[98]++;

        }
        else {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[26]++;
            result = plotArea.getHeight() * this.colorBarThicknessPercent;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[99]++;
        }
        return result;  
    }

    /**
     * Returns a clone of the object.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some component of the color bar 
     *         does not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[100]++;
    
        ColorBar clone = (ColorBar) super.clone();
        clone.axis = (ValueAxis) this.axis.clone();
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[101]++;
        return clone;
            
    }
    
    /**
     * Tests this object for equality with another.
     * 
     * @param obj  the object to test against.
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[102]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[27]++;
            return true;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[28]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[103]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj instanceof ColorBar) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[29]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[30]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[104]++;
        ColorBar that = (ColorBar) obj;
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[105]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.axis.equals(that.axis)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[32]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[106]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.colorBarThickness != that.colorBarThickness) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[34]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[107]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.colorBarThicknessPercent != that.colorBarThicknessPercent) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[36]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[108]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.colorPalette.equals(that.colorPalette)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[38]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[109]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.colorBarLength != that.colorBarLength) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[40]++;}
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.statements[110]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.outerGap != that.outerGap) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$edzna3fgymnld2hiw4td.branches[42]++;}
        return true;
        
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return this.axis.hashCode();
    }
    
}

class CodeCoverCoverageCounter$edzna3fgymnld2hiw4td extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$edzna3fgymnld2hiw4td ());
  }
    public static long[] statements = new long[111];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.ColorBar.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$edzna3fgymnld2hiw4td () {
    super("org.jfree.chart.axis.ColorBar.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 110; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.ColorBar.java");
      for (int i = 1; i <= 110; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
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

