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
 * XYLineAndShapeRenderer.java
 * ---------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 27-Jan-2004 : Version 1 (DG);
 * 10-Feb-2004 : Minor change to drawItem() method to make cut-and-paste 
 *               overriding easier (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 25-Aug-2004 : Added support for chart entities (required for tooltips) (DG);
 * 24-Sep-2004 : Added flag to allow whole series to be drawn as a path 
 *               (necessary when using a dashed stroke with many data 
 *               items) (DG);
 * 04-Oct-2004 : Renamed BooleanUtils --> BooleanUtilities (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 27-Jan-2005 : The getLegendItem() method now omits hidden series (DG);
 * 28-Jan-2005 : Added new constructor (DG);
 * 09-Mar-2005 : Added fillPaint settings (DG);
 * 20-Apr-2005 : Use generators for legend tooltips and URLs (DG);
 * 22-Jul-2005 : Renamed defaultLinesVisible --> baseLinesVisible, 
 *               defaultShapesVisible --> baseShapesVisible and
 *               defaultShapesFilled --> baseShapesFilled (DG);
 * 29-Jul-2005 : Added code to draw item labels (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Set dataset and series indices in LegendItem (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 21-Feb-2007 : Fixed bugs in clone() and equals() (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 08-Jun-2007 : Fix for bug 1731912 where entities are created even for data
 *               items that are not displayed (DG);
 * 26-Oct-2007 : Deprecated override attributes (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
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

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.BooleanList;
import org.jfree.util.BooleanUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that connects data points with lines and/or draws shapes at each
 * data point.  This renderer is designed for use with the {@link XYPlot} 
 * class.
 */
public class XYLineAndShapeRenderer extends AbstractXYItemRenderer 
                                    implements XYItemRenderer, 
                                               Cloneable,
                                               PublicCloneable,
                                               Serializable {
  static {
    CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7435246895986425885L;
  static {
    CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[1]++;
  }
    
    /** 
     * A flag that controls whether or not lines are visible for ALL series. 
     * 
     * @deprecated As of 1.0.7.
     */
    private Boolean linesVisible;

    /** 
     * A table of flags that control (per series) whether or not lines are 
     * visible. 
     */
    private BooleanList seriesLinesVisible;

    /** The default value returned by the getLinesVisible() method. */
    private boolean baseLinesVisible;

    /** The shape that is used to represent a line in the legend. */
    private transient Shape legendLine;
    
    /** 
     * A flag that controls whether or not shapes are visible for ALL series.
     * 
     * @deprecated As of 1.0.7.
     */
    private Boolean shapesVisible;

    /** 
     * A table of flags that control (per series) whether or not shapes are 
     * visible. 
     */
    private BooleanList seriesShapesVisible;

    /** The default value returned by the getShapeVisible() method. */
    private boolean baseShapesVisible;

    /** 
     * A flag that controls whether or not shapes are filled for ALL series. 
     * 
     * @deprecated As of 1.0.7.
     */
    private Boolean shapesFilled;

    /** 
     * A table of flags that control (per series) whether or not shapes are 
     * filled. 
     */
    private BooleanList seriesShapesFilled;

    /** The default value returned by the getShapeFilled() method. */
    private boolean baseShapesFilled;
    
    /** A flag that controls whether outlines are drawn for shapes. */
    private boolean drawOutlines;
    
    /** 
     * A flag that controls whether the fill paint is used for filling 
     * shapes. 
     */
    private boolean useFillPaint;
    
    /** 
     * A flag that controls whether the outline paint is used for drawing shape 
     * outlines. 
     */
    private boolean useOutlinePaint;
    
    /** 
     * A flag that controls whether or not each series is drawn as a single 
     * path. 
     */
    private boolean drawSeriesLineAsPath;

    /**
     * Creates a new renderer with both lines and shapes visible.
     */
    public XYLineAndShapeRenderer() {
        this(true, true);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[2]++;
    }
    
    /**
     * Creates a new renderer.
     * 
     * @param lines  lines visible?
     * @param shapes  shapes visible?
     */
    public XYLineAndShapeRenderer(boolean lines, boolean shapes) {
        this.linesVisible = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[3]++;
        this.seriesLinesVisible = new BooleanList();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[4]++;
        this.baseLinesVisible = lines;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[5]++;
        this.legendLine = new Line2D.Double(-7.0, 0.0, 7.0, 0.0);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[6]++;
        
        this.shapesVisible = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[7]++;
        this.seriesShapesVisible = new BooleanList();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[8]++;
        this.baseShapesVisible = shapes;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[9]++;
        
        this.shapesFilled = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[10]++;
        this.useFillPaint = false;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[11]++;     // use item paint for fills by default
        this.seriesShapesFilled = new BooleanList();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[12]++;
        this.baseShapesFilled = true;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[13]++;

        this.drawOutlines = true;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[14]++;     
        this.useOutlinePaint = false;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[15]++;  // use item paint for outlines by 
                                       // default, not outline paint
        
        this.drawSeriesLineAsPath = false;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[16]++;
    }
    
    /**
     * Returns a flag that controls whether or not each series is drawn as a 
     * single path.
     * 
     * @return A boolean.
     * 
     * @see #setDrawSeriesLineAsPath(boolean)
     */
    public boolean getDrawSeriesLineAsPath() {
        return this.drawSeriesLineAsPath;
    }
    
    /**
     * Sets the flag that controls whether or not each series is drawn as a 
     * single path and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #getDrawSeriesLineAsPath()
     */
    public void setDrawSeriesLineAsPath(boolean flag) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.drawSeriesLineAsPath != flag) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[1]++;
            this.drawSeriesLineAsPath = flag;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[18]++;
            fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[19]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[2]++;}
    }
    
    /**
     * Returns the number of passes through the data that the renderer requires 
     * in order to draw the chart.  Most charts will require a single pass, but 
     * some require two passes.
     * 
     * @return The pass count.
     */
    public int getPassCount() {
        return 2;
    }
    
    // LINES VISIBLE

    /**
     * Returns the flag used to control whether or not the shape for an item is 
     * visible.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return A boolean.
     */
    public boolean getItemLineVisible(int series, int item) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[20]++;
        Boolean flag = this.linesVisible;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((flag == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[3]++;
            flag = getSeriesLinesVisible(series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[22]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[4]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[5]++;
            return flag.booleanValue();

        }
        else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[6]++;
            return this.baseLinesVisible;   
        }
    }

    /**
     * Returns a flag that controls whether or not lines are drawn for ALL 
     * series.  If this flag is <code>null</code>, then the "per series" 
     * settings will apply.
     * 
     * @return A flag (possibly <code>null</code>).
     * 
     * @see #setLinesVisible(Boolean)
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public Boolean getLinesVisible() {
        return this.linesVisible;   
    }
    
    /**
     * Sets a flag that controls whether or not lines are drawn between the 
     * items in ALL series, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.  You need to set this to <code>null</code> if you 
     * want the "per series" settings to apply.
     *
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getLinesVisible()
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public void setLinesVisible(Boolean visible) {
        this.linesVisible = visible;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[24]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[25]++;
    }

    /**
     * Sets a flag that controls whether or not lines are drawn between the 
     * items in ALL series, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     *
     * @param visible  the flag.
     * 
     * @see #getLinesVisible()
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public void setLinesVisible(boolean visible) {
        // we use BooleanUtilities here to preserve JRE 1.3.1 compatibility
        setLinesVisible(BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[26]++;
    }

    /**
     * Returns the flag used to control whether or not the lines for a series 
     * are visible.
     *
     * @param series  the series index (zero-based).
     *
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesLinesVisible(int, Boolean)
     */
    public Boolean getSeriesLinesVisible(int series) {
        return this.seriesLinesVisible.getBoolean(series);
    }

    /**
     * Sets the 'lines visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param flag  the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesLinesVisible(int)
     */
    public void setSeriesLinesVisible(int series, Boolean flag) {
        this.seriesLinesVisible.setBoolean(series, flag);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[27]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[28]++;
    }

    /**
     * Sets the 'lines visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param visible  the flag.
     * 
     * @see #getSeriesLinesVisible(int)
     */
    public void setSeriesLinesVisible(int series, boolean visible) {
        setSeriesLinesVisible(series, BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[29]++;
    }
    
    /**
     * Returns the base 'lines visible' attribute.
     *
     * @return The base flag.
     * 
     * @see #setBaseLinesVisible(boolean)
     */
    public boolean getBaseLinesVisible() {
        return this.baseLinesVisible;
    }

    /**
     * Sets the base 'lines visible' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseLinesVisible()
     */
    public void setBaseLinesVisible(boolean flag) {
        this.baseLinesVisible = flag;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[30]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[31]++;
    }

    /**
     * Returns the shape used to represent a line in the legend.
     * 
     * @return The legend line (never <code>null</code>).
     * 
     * @see #setLegendLine(Shape)
     */
    public Shape getLegendLine() {
        return this.legendLine;   
    }
    
    /**
     * Sets the shape used as a line in each legend item and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param line  the line (<code>null</code> not permitted).
     * 
     * @see #getLegendLine()
     */
    public void setLegendLine(Shape line) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((line == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[7]++;
            throw new IllegalArgumentException("Null 'line' argument.");
   
        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[8]++;}
        this.legendLine = line;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[33]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[34]++;
    }

    // SHAPES VISIBLE

    /**
     * Returns the flag used to control whether or not the shape for an item is
     * visible.
     * <p>
     * The default implementation passes control to the 
     * <code>getSeriesShapesVisible</code> method. You can override this method
     * if you require different behaviour.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return A boolean.
     */
    public boolean getItemShapeVisible(int series, int item) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[35]++;
        Boolean flag = this.shapesVisible;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[36]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((flag == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[9]++;
            flag = getSeriesShapesVisible(series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[37]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[10]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[11]++;
            return flag.booleanValue();
   
        }
        else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[12]++;
            return this.baseShapesVisible;
        }
    }

    /**
     * Returns the flag that controls whether the shapes are visible for the 
     * items in ALL series.
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setShapesVisible(Boolean)
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public Boolean getShapesVisible() {
        return this.shapesVisible;    
    }
    
    /**
     * Sets the 'shapes visible' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getShapesVisible()
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public void setShapesVisible(Boolean visible) {
        this.shapesVisible = visible;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[39]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[40]++;
    }

    /**
     * Sets the 'shapes visible' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #getShapesVisible()
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public void setShapesVisible(boolean visible) {
        setShapesVisible(BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[41]++;
    }

    /**
     * Returns the flag used to control whether or not the shapes for a series
     * are visible.
     *
     * @param series  the series index (zero-based).
     *
     * @return A boolean.
     * 
     * @see #setSeriesShapesVisible(int, Boolean)
     */
    public Boolean getSeriesShapesVisible(int series) {
        return this.seriesShapesVisible.getBoolean(series);
    }

    /**
     * Sets the 'shapes visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param visible  the flag.
     * 
     * @see #getSeriesShapesVisible(int)
     */
    public void setSeriesShapesVisible(int series, boolean visible) {
        setSeriesShapesVisible(series, BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[42]++;
    }
    
    /**
     * Sets the 'shapes visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param flag  the flag.
     * 
     * @see #getSeriesShapesVisible(int)
     */
    public void setSeriesShapesVisible(int series, Boolean flag) {
        this.seriesShapesVisible.setBoolean(series, flag);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[43]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[44]++;
    }

    /**
     * Returns the base 'shape visible' attribute.
     *
     * @return The base flag.
     * 
     * @see #setBaseShapesVisible(boolean)
     */
    public boolean getBaseShapesVisible() {
        return this.baseShapesVisible;
    }

    /**
     * Sets the base 'shapes visible' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseShapesVisible()
     */
    public void setBaseShapesVisible(boolean flag) {
        this.baseShapesVisible = flag;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[45]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[46]++;
    }

    // SHAPES FILLED

    /**
     * Returns the flag used to control whether or not the shape for an item 
     * is filled.
     * <p>
     * The default implementation passes control to the 
     * <code>getSeriesShapesFilled</code> method. You can override this method
     * if you require different behaviour.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return A boolean.
     */
    public boolean getItemShapeFilled(int series, int item) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[47]++;
        Boolean flag = this.shapesFilled;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[48]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((flag == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[13]++;
            flag = getSeriesShapesFilled(series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[49]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[14]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[50]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[15]++;
            return flag.booleanValue();
   
        }
        else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[16]++;
            return this.baseShapesFilled;   
        }
    }
    
    /**
     * Sets the 'shapes filled' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param filled  the flag.
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public void setShapesFilled(boolean filled) {
        setShapesFilled(BooleanUtilities.valueOf(filled));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[51]++;
    }

    /**
     * Sets the 'shapes filled' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param filled  the flag (<code>null</code> permitted).
     * 
     * @deprecated As of 1.0.7, use the per-series and base level settings.
     */
    public void setShapesFilled(Boolean filled) {
        this.shapesFilled = filled;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[52]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[53]++;
    }
    
    /**
     * Returns the flag used to control whether or not the shapes for a series
     * are filled.
     *
     * @param series  the series index (zero-based).
     *
     * @return A boolean.
     * 
     * @see #setSeriesShapesFilled(int, Boolean)
     */
    public Boolean getSeriesShapesFilled(int series) {
        return this.seriesShapesFilled.getBoolean(series);
    }

    /**
     * Sets the 'shapes filled' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param flag  the flag.
     * 
     * @see #getSeriesShapesFilled(int)
     */
    public void setSeriesShapesFilled(int series, boolean flag) {
        setSeriesShapesFilled(series, BooleanUtilities.valueOf(flag));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[54]++;
    }

    /**
     * Sets the 'shapes filled' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param flag  the flag.
     * 
     * @see #getSeriesShapesFilled(int)
     */
    public void setSeriesShapesFilled(int series, Boolean flag) {
        this.seriesShapesFilled.setBoolean(series, flag);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[55]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[56]++;
    }

    /**
     * Returns the base 'shape filled' attribute.
     *
     * @return The base flag.
     * 
     * @see #setBaseShapesFilled(boolean)
     */
    public boolean getBaseShapesFilled() {
        return this.baseShapesFilled;
    }

    /**
     * Sets the base 'shapes filled' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseShapesFilled()
     */
    public void setBaseShapesFilled(boolean flag) {
        this.baseShapesFilled = flag;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[57]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[58]++;
    }

    /**
     * Returns <code>true</code> if outlines should be drawn for shapes, and 
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #setDrawOutlines(boolean)
     */
    public boolean getDrawOutlines() {
        return this.drawOutlines;
    }
    
    /**
     * Sets the flag that controls whether outlines are drawn for 
     * shapes, and sends a {@link RendererChangeEvent} to all registered 
     * listeners. 
     * <P>
     * In some cases, shapes look better if they do NOT have an outline, but 
     * this flag allows you to set your own preference.
     * 
     * @param flag  the flag.
     * 
     * @see #getDrawOutlines()
     */
    public void setDrawOutlines(boolean flag) {
        this.drawOutlines = flag;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[59]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[60]++;
    }
    
    /**
     * Returns <code>true</code> if the renderer should use the fill paint 
     * setting to fill shapes, and <code>false</code> if it should just
     * use the regular paint.
     * <p>
     * Refer to <code>XYLineAndShapeRendererDemo2.java</code> to see the
     * effect of this flag.
     * 
     * @return A boolean.
     * 
     * @see #setUseFillPaint(boolean)
     * @see #getUseOutlinePaint()
     */
    public boolean getUseFillPaint() {
        return this.useFillPaint;
    }
    
    /**
     * Sets the flag that controls whether the fill paint is used to fill 
     * shapes, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #getUseFillPaint()
     */
    public void setUseFillPaint(boolean flag) {
        this.useFillPaint = flag;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[61]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[62]++;
    }
    
    /**
     * Returns <code>true</code> if the renderer should use the outline paint 
     * setting to draw shape outlines, and <code>false</code> if it should just
     * use the regular paint.
     * 
     * @return A boolean.
     * 
     * @see #setUseOutlinePaint(boolean)
     * @see #getUseFillPaint()
     */
    public boolean getUseOutlinePaint() {
        return this.useOutlinePaint;
    }
    
    /**
     * Sets the flag that controls whether the outline paint is used to draw 
     * shape outlines, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * <p>
     * Refer to <code>XYLineAndShapeRendererDemo2.java</code> to see the
     * effect of this flag.
     * 
     * @param flag  the flag.
     * 
     * @see #getUseOutlinePaint()
     */
    public void setUseOutlinePaint(boolean flag) {
        this.useOutlinePaint = flag;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[63]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[64]++;
    }
    
    /**
     * Records the state for the renderer.  This is used to preserve state 
     * information between calls to the drawItem() method for a single chart 
     * drawing.
     */
    public static class State extends XYItemRendererState {
        
        /** The path for the current series. */
        public GeneralPath seriesPath;
        
        /** 
         * A flag that indicates if the last (x, y) point was 'good' 
         * (non-null). 
         */
        private boolean lastPointGood;
        
        /**
         * Creates a new state instance.
         * 
         * @param info  the plot rendering info.
         */
        public State(PlotRenderingInfo info) {
            super(info);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[65]++;
        }
        
        /**
         * Returns a flag that indicates if the last point drawn (in the 
         * current series) was 'good' (non-null).
         * 
         * @return A boolean.
         */
        public boolean isLastPointGood() {
            return this.lastPointGood;
        }
        
        /**
         * Sets a flag that indicates if the last point drawn (in the current 
         * series) was 'good' (non-null).
         * 
         * @param good  the flag.
         */
        public void setLastPointGood(boolean good) {
            this.lastPointGood = good;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[66]++;
        }
    }
    
    /**
     * Initialises the renderer.
     * <P>
     * This method will be called before the first item is rendered, giving the
     * renderer an opportunity to initialise any state information it wants to 
     * maintain.  The renderer can do nothing if it chooses.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return The renderer state.
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset data,
                                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[67]++;

        State state = new State(info);
        state.seriesPath = new GeneralPath();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[68]++;
        return state;

    }
    
    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         XYItemRendererState state,
                         Rectangle2D dataArea,
                         PlotRenderingInfo info,
                         XYPlot plot,
                         ValueAxis domainAxis,
                         ValueAxis rangeAxis,
                         XYDataset dataset,
                         int series,
                         int item,
                         CrosshairState crosshairState,
                         int pass) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[69]++;
int CodeCoverConditionCoverageHelper_C9;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[17]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[18]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[70]++;
int CodeCoverConditionCoverageHelper_C10;

        // first pass draws the background (lines, for instance)
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isLinePass(pass)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[19]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[71]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((item == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[21]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[72]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.drawSeriesLineAsPath) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[23]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[73]++;
                    State s = (State) state;
                    s.seriesPath.reset();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[74]++;
                    s.lastPointGood = false;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[75]++;
     
                } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[24]++;}

            } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[22]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[76]++;
int CodeCoverConditionCoverageHelper_C13;

            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((getItemLineVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[25]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[77]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.drawSeriesLineAsPath) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[27]++;
                    drawPrimaryLineAsPath(state, g2, plot, dataset, pass, 
                            series, item, domainAxis, rangeAxis, dataArea);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[78]++;

                }
                else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[28]++;
                    drawPrimaryLine(state, g2, plot, dataset, pass, series, 
                            item, domainAxis, rangeAxis, dataArea);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[79]++;
                }

            } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[26]++;}

        }
        // second pass adds shapes where the items are ..
        else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[20]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[80]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isItemPass(pass)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[29]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[81]++;

            // setup for collecting optional entity info...
            EntityCollection entities = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[82]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[31]++;
                entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[83]++;

            } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[32]++;}

            drawSecondaryPass(g2, plot, dataset, pass, series, item, 
                    domainAxis, dataArea, rangeAxis, crosshairState, entities);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[84]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[30]++;}
}
    }

    /**
     * Returns <code>true</code> if the specified pass is the one for drawing 
     * lines.
     * 
     * @param pass  the pass.
     * 
     * @return A boolean.
     */
    protected boolean isLinePass(int pass) {
        return pass == 0;
    }

    /**
     * Returns <code>true</code> if the specified pass is the one for drawing 
     * items.
     * 
     * @param pass  the pass.
     * 
     * @return A boolean.
     */
    protected boolean isItemPass(int pass) {
        return pass == 1;
    }

    /**
     * Draws the item (first pass). This method draws the lines
     * connecting the items.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param pass  the pass.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     */
    protected void drawPrimaryLine(XYItemRendererState state,
                                   Graphics2D g2,
                                   XYPlot plot,
                                   XYDataset dataset,
                                   int pass,
                                   int series,
                                   int item,
                                   ValueAxis domainAxis,
                                   ValueAxis rangeAxis,
                                   Rectangle2D dataArea) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[85]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((item == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[33]++;
            return;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[34]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[86]++;

        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[87]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[88]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((Double.isNaN(x1)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[35]++;
            return;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[36]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[89]++;

        double x0 = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[90]++;
        double y0 = dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[91]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((Double.isNaN(x0)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[37]++;
            return;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[38]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[92]++;

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[93]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[94]++;

        double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[95]++;
        double transY0 = rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[96]++;

        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[97]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;

        // only draw if we have good values
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (128)) == 0 || true) &&
 ((Double.isNaN(transX0)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((Double.isNaN(transY0)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((Double.isNaN(transX1)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((Double.isNaN(transY1)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[39]++;
            return;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[40]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[99]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[100]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[41]++;
            state.workingLine.setLine(transY0, transX0, transY1, transX1);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[101]++;

        }
        else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[42]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[102]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[43]++;
            state.workingLine.setLine(transX0, transY0, transX1, transY1);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[103]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[44]++;}
}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[104]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((state.workingLine.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[45]++;
            drawFirstPassShape(g2, pass, series, item, state.workingLine);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[105]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[46]++;}
    }

    /**
     * Draws the first pass shape.
     * 
     * @param g2  the graphics device.
     * @param pass  the pass.
     * @param series  the series index.
     * @param item  the item index.
     * @param shape  the shape.
     */
    protected void drawFirstPassShape(Graphics2D g2, int pass, int series,
                                      int item, Shape shape) {
        g2.setStroke(getItemStroke(series, item));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[106]++;
        g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[107]++;
        g2.draw(shape);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[108]++;
    }


    /**
     * Draws the item (first pass). This method draws the lines
     * connecting the items. Instead of drawing separate lines,
     * a GeneralPath is constructed and drawn at the end of
     * the series painting.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param dataset  the dataset.
     * @param pass  the pass.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataArea  the area within which the data is being drawn.
     */
    protected void drawPrimaryLineAsPath(XYItemRendererState state,
                                         Graphics2D g2, XYPlot plot,
                                         XYDataset dataset,
                                         int pass,
                                         int series,
                                         int item,
                                         ValueAxis domainAxis,
                                         ValueAxis rangeAxis,
                                         Rectangle2D dataArea) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[109]++;


        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[110]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[111]++;

        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[112]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[113]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[114]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[115]++;

        State s = (State) state;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[116]++;
int CodeCoverConditionCoverageHelper_C24;
        // update path to reflect latest point
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((Double.isNaN(transX1)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((Double.isNaN(transY1)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[47]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[117]++;
            float x = (float) transX1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[118]++;
            float y = (float) transY1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[119]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[120]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[49]++;
                x = (float) transY1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[121]++;
                y = (float) transX1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[122]++;

            } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[50]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[123]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((s.isLastPointGood()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[51]++;
                s.seriesPath.lineTo(x, y);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[124]++;

            }
            else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[52]++;
                s.seriesPath.moveTo(x, y);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[125]++;
            }
            s.setLastPointGood(true);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[126]++;

        }
        else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[48]++;
            s.setLastPointGood(false);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[127]++;
        }
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[128]++;
int CodeCoverConditionCoverageHelper_C27;
        // if this is the last item, draw the path ...
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((item == dataset.getItemCount(series) - 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[53]++;
            // draw path
            drawFirstPassShape(g2, pass, series, item, s.seriesPath);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[129]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[54]++;}
    }

    /**
     * Draws the item shapes and adds chart entities (second pass). This method 
     * draws the shapes which mark the item positions. If <code>entities</code> 
     * is not <code>null</code> it will be populated with entity information
     * for points that fall within the data area.
     *
     * @param g2  the graphics device.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param dataArea  the area within which the data is being drawn.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param pass  the pass.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  the crosshair state.
     * @param entities the entity collection.
     */
    protected void drawSecondaryPass(Graphics2D g2, XYPlot plot, 
                                     XYDataset dataset,
                                     int pass, int series, int item,
                                     ValueAxis domainAxis, 
                                     Rectangle2D dataArea,
                                     ValueAxis rangeAxis, 
                                     CrosshairState crosshairState,
                                     EntityCollection entities) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[130]++;

        Shape entityArea = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[131]++;
        
        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[132]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[133]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((Double.isNaN(x1)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[55]++;
            return;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[56]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[134]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[135]++;
        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[136]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[137]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[138]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[139]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((getItemShapeVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[57]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[140]++;
            Shape shape = getItemShape(series, item);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[141]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[59]++;
                shape = ShapeUtilities.createTranslatedShape(shape, transY1, 
                        transX1);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[142]++;

            }
            else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[60]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[143]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[61]++;
                shape = ShapeUtilities.createTranslatedShape(shape, transX1, 
                        transY1);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[144]++;

            } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[62]++;}
}
            entityArea = shape;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[145]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[146]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((shape.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[63]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[147]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((getItemShapeFilled(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[65]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[148]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.useFillPaint) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[67]++;
                        g2.setPaint(getItemFillPaint(series, item));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[149]++;

                    }
                    else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[68]++;
                        g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[150]++;
                    }
                    g2.fill(shape);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[151]++;

                } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[66]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[152]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[69]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[153]++;
int CodeCoverConditionCoverageHelper_C36;
                    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((getUseOutlinePaint()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[71]++;
                        g2.setPaint(getItemOutlinePaint(series, item));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[154]++;

                    }
                    else {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[72]++;
                        g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[155]++;
                    }
                    g2.setStroke(getItemOutlineStroke(series, item));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[156]++;
                    g2.draw(shape);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[157]++;

                } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[70]++;}

            } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[64]++;}

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[58]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[158]++;

        double xx = transX1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[159]++;
        double yy = transY1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[160]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[73]++;
            xx = transY1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[161]++;
            yy = transX1;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[162]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[74]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[163]++;
int CodeCoverConditionCoverageHelper_C38;          

        // draw the item label if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[75]++;
            drawItemLabel(g2, orientation, dataset, series, item, xx, yy, 
                    (y1 < 0.0));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[164]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[76]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[165]++;

        int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[166]++;
        int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
        updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, 
                rangeAxisIndex, transX1, transY1, plot.getOrientation());
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[167]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[168]++;
int CodeCoverConditionCoverageHelper_C39;

        // add an entity for the item, but only if it falls within the data
        // area...
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((dataArea.contains(xx, yy)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[77]++;
            addEntity(entities, entityArea, dataset, series, item, xx, yy);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[169]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[78]++;}
    }


    /**
     * Returns a legend item for the specified series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return A legend item for the series.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[170]++;

        XYPlot plot = getPlot();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[171]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[79]++;
            return null;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[80]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[172]++;

        LegendItem result = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[173]++;
        XYDataset dataset = plot.getDataset(datasetIndex);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[174]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[81]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[175]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((getItemVisible(series, 0)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[83]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[176]++;
                String label = getLegendItemLabelGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[177]++;
                String description = label;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[178]++;
                String toolTipText = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[179]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[85]++;
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[180]++;

                } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[86]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[181]++;
                String urlText = null;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[182]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[87]++;
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[183]++;

                } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[88]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[184]++;
                boolean shapeIsVisible = getItemShapeVisible(series, 0);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[185]++;
                Shape shape = lookupSeriesShape(series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[186]++;
                boolean shapeIsFilled = getItemShapeFilled(series, 0);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[187]++;
                Paint fillPaint = (this.useFillPaint 
                    ? lookupSeriesFillPaint(series) 
                    : lookupSeriesPaint(series));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[188]++;
                boolean shapeOutlineVisible = this.drawOutlines;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[189]++;  
                Paint outlinePaint = (this.useOutlinePaint 
                    ? lookupSeriesOutlinePaint(series) 
                    : lookupSeriesPaint(series));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[190]++;
                Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[191]++;
                boolean lineVisible = getItemLineVisible(series, 0);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[192]++;
                Stroke lineStroke = lookupSeriesStroke(series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[193]++;
                Paint linePaint = lookupSeriesPaint(series);
                result = new LegendItem(label, description, toolTipText, 
                        urlText, shapeIsVisible, shape, shapeIsFilled, 
                        fillPaint, shapeOutlineVisible, outlinePaint, 
                        outlineStroke, lineVisible, this.legendLine, 
                        lineStroke, linePaint);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[194]++;
                result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[195]++;
                result.setSeriesIndex(series);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[196]++;
                result.setDataset(dataset);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[197]++;
                result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[198]++;

            } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[84]++;}

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[82]++;}

        return result;

    }
    
    /**
     * Returns a clone of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the clone cannot be created.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[199]++;
        XYLineAndShapeRenderer clone = (XYLineAndShapeRenderer) super.clone();
        clone.seriesLinesVisible 
                = (BooleanList) this.seriesLinesVisible.clone();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[200]++;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[201]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.legendLine != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[89]++;
            clone.legendLine = ShapeUtilities.clone(this.legendLine);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[202]++;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[90]++;}
        clone.seriesShapesVisible 
                = (BooleanList) this.seriesShapesVisible.clone();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[203]++;
        clone.seriesShapesFilled 
                = (BooleanList) this.seriesShapesFilled.clone();
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[204]++;
        return clone;
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[205]++;
int CodeCoverConditionCoverageHelper_C46;

        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[91]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[92]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[206]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((obj instanceof XYLineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[93]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[94]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[207]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[95]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[96]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[208]++;
        XYLineAndShapeRenderer that = (XYLineAndShapeRenderer) obj;
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[209]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.linesVisible, that.linesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[97]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[98]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[210]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.seriesLinesVisible, that.seriesLinesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)
        ) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[99]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[100]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[211]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.baseLinesVisible != that.baseLinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[101]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[102]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[212]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendLine, that.legendLine)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[103]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[104]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[213]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shapesVisible, that.shapesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[105]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[106]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[214]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.seriesShapesVisible, that.seriesShapesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)
        ) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[107]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[108]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[215]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.baseShapesVisible != that.baseShapesVisible) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[109]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[110]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[216]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shapesFilled, that.shapesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[111]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[112]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[217]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.seriesShapesFilled, that.seriesShapesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)
        ) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[114]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[218]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this.baseShapesFilled != that.baseShapesFilled) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[115]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[116]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[219]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((this.drawOutlines != that.drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[117]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[118]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[220]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint != that.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[119]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[120]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[221]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this.useFillPaint != that.useFillPaint) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[121]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[122]++;}
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[222]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((this.drawSeriesLineAsPath != that.drawSeriesLineAsPath) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[123]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.branches[124]++;}
        return true;

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
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[223]++;
        this.legendLine = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[224]++;
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
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[225]++;
        SerialUtilities.writeShape(this.legendLine, stream);
CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9.statements[226]++;
    }
  
}

class CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9 ());
  }
    public static long[] statements = new long[227];
    public static long[] branches = new long[125];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[63];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYLineAndShapeRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,3,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 62; i++) {
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

  public CodeCoverCoverageCounter$5ne7nhg76gq6uv9pj3zlv1mbgwdw240o4b20e3bdy9 () {
    super("org.jfree.chart.renderer.xy.XYLineAndShapeRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 226; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 124; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 62; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYLineAndShapeRenderer.java");
      for (int i = 1; i <= 226; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 124; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 62; i++) {
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

