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
 * SymbolAxis.java
 * ---------------
 * (C) Copyright 2002-2007, by Anthony Boulestreau and Contributors.
 *
 * Original Author:  Anthony Boulestreau;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 *
 * Changes
 * -------
 * 29-Mar-2002 : First version (AB);
 * 19-Apr-2002 : Updated formatting and import statements (DG);
 * 21-Jun-2002 : Make change to use the class TickUnit - remove valueToString() 
 *               method and add SymbolicTickUnit (AB);
 * 25-Jun-2002 : Removed redundant code (DG);
 * 25-Jul-2002 : Changed order of parameters in ValueAxis constructor (DG);
 * 05-Sep-2002 : Updated constructor to reflect changes in the Axis class (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 14-Feb-2003 : Added back missing constructor code (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 14-May-2003 : Renamed HorizontalSymbolicAxis --> SymbolicAxis and merged in
 *               VerticalSymbolicAxis (DG);
 * 12-Aug-2003 : Fixed bug where refreshTicks() method has different signature 
 *               to super class (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 02-Nov-2003 : Added code to avoid overlapping labels (MR);
 * 07-Nov-2003 : Modified to use new tick classes (DG);
 * 18-Nov-2003 : Fixed bug where symbols are not being displayed on the 
 *               axis (DG);
 * 24-Nov-2003 : Added fix for gridlines on zooming (bug id 834643) (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 11-Mar-2004 : Modified the way the background grid color is being drawn, see
 *               this thread:
 *               http://www.jfree.org/phpBB2/viewtopic.php?p=22973 (DG);
 * 16-Mar-2004 : Added plotState to draw() method (DG);
 * 07-Apr-2004 : Modified string bounds calculation (DG);
 * 28-Mar-2005 : Renamed autoRangeIncludesZero() --> getAutoRangeIncludesZero()
 *               and autoRangeStickyZero() --> getAutoRangeStickyZero() (DG);
 * 05-Jul-2005 : Fixed signature on refreshTicks() method - see bug report
 *               1232264 (DG);
 * 06-Jul-2005 : Renamed SymbolicAxis --> SymbolAxis, added equals() method, 
 *               renamed getSymbolicValue() --> getSymbols(), renamed 
 *               symbolicGridPaint --> gridBandPaint, fixed serialization of 
 *               gridBandPaint, renamed symbolicGridLinesVisible --> 
 *               gridBandsVisible, eliminated symbolicGridLineList (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 28-Feb-2007 : Fixed bug 1669302 (tick label overlap) (DG);
 * 25-Jul-2007 : Added new field for alternate grid band paint (DG);
 * 
 */

package org.jfree.chart.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.PaintUtilities;

/**
 * A standard linear value axis that replaces integer values with symbols.
 */
public class SymbolAxis extends NumberAxis implements Serializable {
  static {
    CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7216330468770619716L;
  static {
    CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[1]++;
  }
    
    /** The default grid band paint. */
    public static final Paint DEFAULT_GRID_BAND_PAINT 
            = new Color(232, 234, 232, 128);
  static {
    CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[2]++;
  }

    /**
     * The default paint for alternate grid bands.
     * 
     * @since 1.0.7
     */
    public static final Paint DEFAULT_GRID_BAND_ALTERNATE_PAINT
            = new Color(0, 0, 0, 0);
  static {
    CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[3]++;
  }  // transparent
    
    /** The list of symbols to display instead of the numeric values. */
    private List symbols;

    /** Flag that indicates whether or not grid bands are visible. */
    private boolean gridBandsVisible;

    /** The paint used to color the grid bands (if the bands are visible). */
    private transient Paint gridBandPaint;
    
    /** 
     * The paint used to fill the alternate grid bands.
     * 
     * @since 1.0.7
     */
    private transient Paint gridBandAlternatePaint;

    /**
     * Constructs a symbol axis, using default attribute values where 
     * necessary.
     *
     * @param label  the axis label (<code>null</code> permitted).
     * @param sv  the list of symbols to display instead of the numeric
     *            values.
     */
    public SymbolAxis(String label, String[] sv) {
        super(label);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[4]++;
        this.symbols = Arrays.asList(sv);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[5]++;
        this.gridBandsVisible = true;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[6]++;
        this.gridBandPaint = DEFAULT_GRID_BAND_PAINT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[7]++;
        this.gridBandAlternatePaint = DEFAULT_GRID_BAND_ALTERNATE_PAINT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[8]++;
        setAutoTickUnitSelection(false, false);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[9]++;
        setAutoRangeStickyZero(false);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[10]++;

    }

    /**
     * Returns an array of the symbols for the axis.
     *
     * @return The symbols.
     */
    public String[] getSymbols() {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[11]++;
        String[] result = new String[this.symbols.size()];
        result = (String[]) this.symbols.toArray(result);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[12]++;
        return result;
    }

    /**
     * Returns <code>true</code> if the grid bands are showing, and
     * <code>false</code> otherwise.
     *
     * @return <code>true</code> if the grid bands are showing, and 
     *         <code>false</code> otherwise.
     *         
     * @see #setGridBandsVisible(boolean)
     */
    public boolean isGridBandsVisible() {
        return this.gridBandsVisible;
    }

    /**
     * Sets the visibility of the grid bands and notifies registered
     * listeners that the axis has been modified.
     *
     * @param flag  the new setting.
     * 
     * @see #isGridBandsVisible()
     */
    public void setGridBandsVisible(boolean flag) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.gridBandsVisible != flag) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[1]++;
            this.gridBandsVisible = flag;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[14]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[15]++;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[2]++;}
    }

    /**
     * Returns the paint used to color the grid bands.
     *
     * @return The grid band paint (never <code>null</code>).
     * 
     * @see #setGridBandPaint(Paint)
     * @see #isGridBandsVisible()
     */
    public Paint getGridBandPaint() {
        return this.gridBandPaint;
    }

    /**
     * Sets the grid band paint and sends an {@link AxisChangeEvent} to 
     * all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getGridBandPaint()
     */
    public void setGridBandPaint(Paint paint) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[4]++;}
        this.gridBandPaint = paint;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[17]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[18]++;
    }

    /**
     * Returns the paint used for alternate grid bands.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setGridBandAlternatePaint(Paint)
     * @see #getGridBandPaint()
     * 
     * @since 1.0.7
     */
    public Paint getGridBandAlternatePaint() {
        return this.gridBandAlternatePaint;
    }
    
    /**
     * Sets the paint used for alternate grid bands and sends a 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getGridBandAlternatePaint()
     * @see #setGridBandPaint(Paint)
     * 
     * @since 1.0.7
     */
    public void setGridBandAlternatePaint(Paint paint) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[5]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[6]++;}
        this.gridBandAlternatePaint = paint;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[20]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[21]++;
    }
    
    /**
     * This operation is not supported by this axis.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot and axes should be drawn.
     * @param edge  the edge along which the axis is drawn.
     */
    protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, 
                                      RectangleEdge edge) {
        throw new UnsupportedOperationException();
    }

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the plot and axes should be drawn
     *                  (<code>null</code> not permitted).
     * @param dataArea  the area within which the data should be drawn 
     *                  (<code>null</code> not permitted).
     * @param edge  the axis location (<code>null</code> not permitted).
     * @param plotState  collects information about the plot 
     *                   (<code>null</code> permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Graphics2D g2, 
                          double cursor,
                          Rectangle2D plotArea, 
                          Rectangle2D dataArea, 
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[22]++;

        AxisState info = new AxisState(cursor);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[7]++;
            info = super.draw(g2, cursor, plotArea, dataArea, edge, plotState);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[24]++;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[8]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.gridBandsVisible) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[9]++;
            drawGridBands(g2, plotArea, dataArea, edge, info.getTicks());
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[26]++;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[10]++;}
        return info;

    }

    /**
     * Draws the grid bands.  Alternate bands are colored using 
     * <CODE>gridBandPaint<CODE> (<CODE>DEFAULT_GRID_BAND_PAINT</CODE> by 
     * default).
     *
     * @param g2  the graphics device.
     * @param plotArea  the area within which the chart should be drawn.
     * @param dataArea  the area within which the plot should be drawn (a 
     *                  subset of the drawArea).
     * @param edge  the axis location.
     * @param ticks  the ticks.
     */
    protected void drawGridBands(Graphics2D g2,
                                 Rectangle2D plotArea, 
                                 Rectangle2D dataArea,
                                 RectangleEdge edge, 
                                 List ticks) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[27]++;

        Shape savedClip = g2.getClip();
        g2.clip(dataArea);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[28]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[11]++;
            drawGridBandsHorizontal(g2, plotArea, dataArea, true, ticks);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[30]++;

        }
        else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[12]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[31]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[13]++;
            drawGridBandsVertical(g2, plotArea, dataArea, true, ticks);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[32]++;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[14]++;}
}
        g2.setClip(savedClip);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[33]++;

    }

    /**
     * Draws the grid bands for the axis when it is at the top or bottom of 
     * the plot.
     *
     * @param g2  the graphics device.
     * @param plotArea  the area within which the chart should be drawn.
     * @param dataArea  the area within which the plot should be drawn
     *                  (a subset of the drawArea).
     * @param firstGridBandIsDark  True: the first grid band takes the
     *                             color of <CODE>gridBandPaint<CODE>.
     *                             False: the second grid band takes the 
     *                             color of <CODE>gridBandPaint<CODE>.
     * @param ticks  the ticks.
     */
    protected void drawGridBandsHorizontal(Graphics2D g2,
                                           Rectangle2D plotArea, 
                                           Rectangle2D dataArea,
                                           boolean firstGridBandIsDark, 
                                           List ticks) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[34]++;

        boolean currentGridBandIsDark = firstGridBandIsDark;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[35]++;
        double yy = dataArea.getY();
        double xx1, xx2;

        //gets the outline stroke width of the plot
        double outlineStrokeWidth;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getPlot().getOutlineStroke() !=  null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[15]++;
            outlineStrokeWidth 
                = ((BasicStroke) getPlot().getOutlineStroke()).getLineWidth();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[37]++;

        }
        else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[16]++;
            outlineStrokeWidth = 1d;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[38]++;
        }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[39]++;

        Iterator iterator = ticks.iterator();
        ValueTick tick;
        Rectangle2D band;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[40]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
        while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[1]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[2]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[3]++;
}
            tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[41]++;
            xx1 = valueToJava2D(tick.getValue() - 0.5d, dataArea, 
                    RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[42]++;
            xx2 = valueToJava2D(tick.getValue() + 0.5d, dataArea, 
                    RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[43]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((currentGridBandIsDark) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[17]++;
                g2.setPaint(this.gridBandPaint);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[45]++;

            }
            else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[18]++;
                g2.setPaint(Color.white);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[46]++;
            }
            band = new Rectangle2D.Double(xx1, yy + outlineStrokeWidth, 
                xx2 - xx1, dataArea.getMaxY() - yy - outlineStrokeWidth);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[47]++;
            g2.fill(band);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[48]++;
            currentGridBandIsDark = !currentGridBandIsDark;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[49]++;
        }
        g2.setPaintMode();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[50]++;
    }

    /**
     * Draws the grid bands for the axis when it is at the top or bottom of 
     * the plot.
     *
     * @param g2  the graphics device.
     * @param drawArea  the area within which the chart should be drawn.
     * @param plotArea  the area within which the plot should be drawn (a
     *                  subset of the drawArea).
     * @param firstGridBandIsDark  True: the first grid band takes the
     *                             color of <CODE>gridBandPaint<CODE>.
     *                             False: the second grid band takes the 
     *                             color of <CODE>gridBandPaint<CODE>.
     * @param ticks  a list of ticks.
     */
    protected void drawGridBandsVertical(Graphics2D g2, 
                                         Rectangle2D drawArea,
                                         Rectangle2D plotArea, 
                                         boolean firstGridBandIsDark,
                                         List ticks) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[51]++;

        boolean currentGridBandIsDark = firstGridBandIsDark;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[52]++;
        double xx = plotArea.getX();
        double yy1, yy2;

        //gets the outline stroke width of the plot
        double outlineStrokeWidth;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[53]++;
        Stroke outlineStroke = getPlot().getOutlineStroke();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[54]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((outlineStroke instanceof BasicStroke) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[19]++;
            outlineStrokeWidth = ((BasicStroke) outlineStroke).getLineWidth();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[55]++;

        }
        else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[20]++;
            outlineStrokeWidth = 1d;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[56]++;
        }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[57]++;

        Iterator iterator = ticks.iterator();
        ValueTick tick;
        Rectangle2D band;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[58]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;
        while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[4]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[5]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[6]++;
}
            tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[59]++;
            yy1 = valueToJava2D(tick.getValue() + 0.5d, plotArea, 
                    RectangleEdge.LEFT);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[60]++;
            yy2 = valueToJava2D(tick.getValue() - 0.5d, plotArea, 
                    RectangleEdge.LEFT);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[61]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[62]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((currentGridBandIsDark) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[21]++;
                g2.setPaint(this.gridBandPaint);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[63]++;

            }
            else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[22]++;
                g2.setPaint(Color.white);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[64]++;
            }
            band = new Rectangle2D.Double(xx + outlineStrokeWidth, yy1, 
                    plotArea.getMaxX() - xx - outlineStrokeWidth, yy2 - yy1);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[65]++;
            g2.fill(band);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[66]++;
            currentGridBandIsDark = !currentGridBandIsDark;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[67]++;
        }
        g2.setPaintMode();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[68]++;
    }

    /**
     * Rescales the axis to ensure that all data is visible.
     */
    protected void autoAdjustRange() {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[69]++;

        Plot plot = getPlot();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[70]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[23]++;
            return;
  // no plot, no data
        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[24]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[71]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((plot instanceof ValueAxisPlot) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[25]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[72]++;

            // ensure that all the symbols are displayed
            double upper = this.symbols.size() - 1;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[73]++;
            double lower = 0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[74]++;
            double range = upper - lower;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[75]++;

            // ensure the autorange is at least <minRange> in size...
            double minRange = getAutoRangeMinimumSize();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((range < minRange) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[27]++;
                upper = (upper + lower + minRange) / 2;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[77]++;
                lower = (upper + lower - minRange) / 2;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[78]++;

            } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[28]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[79]++;

            // this ensure that the grid bands will be displayed correctly.
            double upperMargin = 0.5;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[80]++;
            double lowerMargin = 0.5;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;

            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((getAutoRangeIncludesZero()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[29]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[82]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((getAutoRangeStickyZero()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[31]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[83]++;
int CodeCoverConditionCoverageHelper_C19;
                    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((upper <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[33]++;
                        upper = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[84]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[34]++;
                        upper = upper + upperMargin;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[85]++;
                    }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[86]++;
int CodeCoverConditionCoverageHelper_C20;
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((lower >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[35]++;
                        lower = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[87]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[36]++;
                        lower = lower - lowerMargin;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[88]++;
                    }

                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[32]++;
                    upper = Math.max(0.0, upper + upperMargin);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[89]++;
                    lower = Math.min(0.0, lower - lowerMargin);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[90]++;
                }

            }
            else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[30]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[91]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((getAutoRangeStickyZero()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[37]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[92]++;
int CodeCoverConditionCoverageHelper_C22;
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((upper <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[39]++;
                        upper = Math.min(0.0, upper + upperMargin);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[93]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[40]++;
                        upper = upper + upperMargin * range;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[94]++;
                    }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[95]++;
int CodeCoverConditionCoverageHelper_C23;
                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((lower >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[41]++;
                        lower = Math.max(0.0, lower - lowerMargin);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[96]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[42]++;
                        lower = lower - lowerMargin;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[97]++;
                    }

                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[38]++;
                    upper = upper + upperMargin;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[98]++;
                    lower = lower - lowerMargin;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[99]++;
                }
            }

            setRange(new Range(lower, upper), false, false);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[100]++;


        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[26]++;}

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the 
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the area in which the data should be drawn.
     * @param edge  the location of the axis.
     * 
     * @return A list of ticks.
     */
    public List refreshTicks(Graphics2D g2, 
                             AxisState state,
                             Rectangle2D dataArea,
                             RectangleEdge edge) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[101]++;
        List ticks = null;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[102]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[43]++;
            ticks = refreshTicksHorizontal(g2, dataArea, edge);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[103]++;

        }
        else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[44]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[104]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[45]++;
            ticks = refreshTicksVertical(g2, dataArea, edge);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[105]++;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[46]++;}
}
        return ticks;
    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the 
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data should be drawn.
     * @param edge  the location of the axis.
     * 
     * @return The ticks.
     */
    protected List refreshTicksHorizontal(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          RectangleEdge edge) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[106]++;

        List ticks = new java.util.ArrayList();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[107]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[108]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[109]++;

        double size = getTickUnit().getSize();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[110]++;
        int count = calculateVisibleTickCount();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[111]++;
        double lowestTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[112]++;

        double previousDrawnTickLabelPos = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[113]++;         
        double previousDrawnTickLabelLength = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[114]++;
int CodeCoverConditionCoverageHelper_C26;              

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((count <= ValueAxis.MAXIMUM_TICK_COUNT) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[47]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[115]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[7]++;


int CodeCoverConditionCoverageHelper_C27;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[7]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[8]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[9]++;
}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[116]++;
                double currentTickValue = lowestTickValue + (i * size);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[117]++;
                double xx = valueToJava2D(currentTickValue, dataArea, edge);
                String tickLabel;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[118]++;
                NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[119]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[49]++;
                    tickLabel = formatter.format(currentTickValue);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[120]++;

                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[50]++;
                    tickLabel = valueToString(currentTickValue);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[121]++;
                }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[122]++;
                
                // avoid to draw overlapping tick labels
                Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2, 
                        g2.getFontMetrics());
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[123]++;
                double tickLabelLength = isVerticalTickLabels() 
                        ? bounds.getHeight() : bounds.getWidth();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[124]++;
                boolean tickLabelsOverlapping = false;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[125]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[51]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[126]++;
                    double avgTickLabelLength = (previousDrawnTickLabelLength 
                            + tickLabelLength) / 2.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[127]++;
int CodeCoverConditionCoverageHelper_C30;
                    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((Math.abs(xx - previousDrawnTickLabelPos) 
                            < avgTickLabelLength) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[53]++;
                        tickLabelsOverlapping = true;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[128]++;

                    } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[54]++;}

                } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[52]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[129]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((tickLabelsOverlapping) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[55]++;
                    tickLabel = "";
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[130]++;
 // don't draw this tick label
                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[56]++;
                    // remember these values for next comparison
                    previousDrawnTickLabelPos = xx;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[131]++;
                    previousDrawnTickLabelLength = tickLabelLength;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[132]++;         
                }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[133]++; 
                
                TextAnchor anchor = null;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[134]++;
                TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[135]++;
                double angle = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[136]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[57]++;
                    anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[137]++;
                    rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[138]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[139]++;
int CodeCoverConditionCoverageHelper_C33;
                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[59]++;
                        angle = Math.PI / 2.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[140]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[60]++;
                        angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[141]++;
                    }

                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[58]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[142]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[61]++;
                        anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[143]++;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[144]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[62]++;
                        anchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[145]++;
                        rotationAnchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[146]++;
                    }
                }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[147]++;
                Tick tick = new NumberTick(new Double(currentTickValue), 
                        tickLabel, anchor, rotationAnchor, angle);
                ticks.add(tick);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[148]++;
            }

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[48]++;}
        return ticks;

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the 
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     * 
     * @return The ticks.
     */
    protected List refreshTicksVertical(Graphics2D g2,
                                        Rectangle2D dataArea,
                                        RectangleEdge edge) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[149]++;

        List ticks = new java.util.ArrayList();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[150]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[151]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[152]++;

        double size = getTickUnit().getSize();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[153]++;
        int count = calculateVisibleTickCount();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[154]++;
        double lowestTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[155]++;

        double previousDrawnTickLabelPos = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[156]++;         
        double previousDrawnTickLabelLength = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[157]++;
int CodeCoverConditionCoverageHelper_C35;              

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((count <= ValueAxis.MAXIMUM_TICK_COUNT) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[63]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[158]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[10]++;


int CodeCoverConditionCoverageHelper_C36;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[10]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[11]--;
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.loops[12]++;
}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[159]++;
                double currentTickValue = lowestTickValue + (i * size);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[160]++;
                double yy = valueToJava2D(currentTickValue, dataArea, edge);
                String tickLabel;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[161]++;
                NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[162]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[65]++;
                    tickLabel = formatter.format(currentTickValue);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[163]++;

                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[66]++;
                    tickLabel = valueToString(currentTickValue);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[164]++;
                }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[165]++;

                // avoid to draw overlapping tick labels
                Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2,
                        g2.getFontMetrics());
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[166]++;
                double tickLabelLength = isVerticalTickLabels() 
                    ? bounds.getWidth() : bounds.getHeight();
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[167]++;
                boolean tickLabelsOverlapping = false;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[168]++;
int CodeCoverConditionCoverageHelper_C38;
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[67]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[169]++;
                    double avgTickLabelLength = (previousDrawnTickLabelLength 
                            + tickLabelLength) / 2.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[170]++;
int CodeCoverConditionCoverageHelper_C39;
                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((Math.abs(yy - previousDrawnTickLabelPos) 
                            < avgTickLabelLength) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[69]++;
                        tickLabelsOverlapping = true;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[171]++;
    
                    } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[70]++;}

                } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[68]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[172]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((tickLabelsOverlapping) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[71]++;
                    tickLabel = "";
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[173]++;
 // don't draw this tick label
                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[72]++;
                    // remember these values for next comparison
                    previousDrawnTickLabelPos = yy;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[174]++;
                    previousDrawnTickLabelLength = tickLabelLength;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[175]++;         
                }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[176]++;
                
                TextAnchor anchor = null;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[177]++;
                TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[178]++;
                double angle = 0.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[179]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[73]++;
                    anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[180]++;
                    rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[181]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[182]++;
int CodeCoverConditionCoverageHelper_C42;
                    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[75]++;
                        angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[183]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[76]++;
                        angle = Math.PI / 2.0;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[184]++;
                    }
                    
                }
                else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[74]++;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[185]++;
int CodeCoverConditionCoverageHelper_C43;
                    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[77]++;
                        anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[186]++;
                        rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[187]++;

                    }
                    else {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[78]++;
                        anchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[188]++;
                        rotationAnchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[189]++;
                    }
                }
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[190]++;
                Tick tick = new NumberTick(new Double(currentTickValue), 
                        tickLabel, anchor, rotationAnchor, angle);
                ticks.add(tick);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[191]++;
            }

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[64]++;}
        return ticks;
        
    }

    /**
     * Converts a value to a string, using the list of symbols.
     *
     * @param value  value to convert.
     *
     * @return The symbol.
     */
    public String valueToString(double value) {
        String strToReturn;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[192]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            strToReturn = (String) this.symbols.get((int) value);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[193]++;
        }
        catch (IndexOutOfBoundsException  ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[80]++;
            strToReturn = "";
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[194]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[79]++;
}
  }
        return strToReturn;
    }

    /**
     * Tests this axis for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[195]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[81]++;
            return true;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[82]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[196]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((obj instanceof SymbolAxis) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[84]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[197]++;
        SymbolAxis that = (SymbolAxis) obj;
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[198]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.symbols.equals(that.symbols)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[86]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[199]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.gridBandsVisible != that.gridBandsVisible) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[87]++;
            return false;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[88]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[200]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.gridBandPaint, that.gridBandPaint)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[90]++;}
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[201]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.gridBandAlternatePaint, 
                that.gridBandAlternatePaint)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.branches[92]++;}
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
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[202]++;
        SerialUtilities.writePaint(this.gridBandPaint, stream);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[203]++;
        SerialUtilities.writePaint(this.gridBandAlternatePaint, stream);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[204]++;
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
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[205]++;
        this.gridBandPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[206]++;
        this.gridBandAlternatePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh.statements[207]++;
    }

}

class CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh ());
  }
    public static long[] statements = new long[208];
    public static long[] branches = new long[93];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[50];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.SymbolAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 49; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$p0nos4dmpbilxg6aw5h2wkh () {
    super("org.jfree.chart.axis.SymbolAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 207; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 92; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 49; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.SymbolAxis.java");
      for (int i = 1; i <= 207; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 92; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 49; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

