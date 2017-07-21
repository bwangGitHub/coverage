/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * -----------------
 * WaferMapPlot.java
 * -----------------
 *
 * (C) Copyright 2003, 2004, by Robert Redburn and Contributors.
 *
 * Original Author:  Robert Redburn;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 25-Nov-2003 : Version 1 contributed by Robert Redburn (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 10-Jun-2005 : Changed private --> protected for drawChipGrid(), 
 *               drawWaferEdge() and getWafterEdge() (DG);
 * 16-Jun-2005 : Added default constructor and setDataset() method (DG);
 *
 */
 
package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ResourceBundle;

import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.renderer.WaferMapRenderer;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.WaferMapDataset;
import org.jfree.ui.RectangleInsets;

/**
 * A wafer map plot.
 */
public class WaferMapPlot extends Plot implements RendererChangeListener,
                                                  Cloneable,
                                                  Serializable {
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 4668320403707308155L;
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[1]++;
  }
    
    /** The default grid line stroke. */
    public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5f,
        BasicStroke.CAP_BUTT,
        BasicStroke.JOIN_BEVEL,
        0.0f,
        new float[] {2.0f, 2.0f},
        0.0f);
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[2]++;
  }

    /** The default grid line paint. */
    public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[3]++;
  }

    /** The default crosshair visibility. */
    public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[4]++;
  }

    /** The default crosshair stroke. */
    public static final Stroke DEFAULT_CROSSHAIR_STROKE 
        = DEFAULT_GRIDLINE_STROKE;
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[5]++;
  }

    /** The default crosshair paint. */
    public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[6]++;
  }

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources = 
        ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[7]++;
  }

    /** The plot orientation. 
     *  vertical = notch down
     *  horizontal = notch right
     */
    private PlotOrientation orientation;

    /** The dataset. */
    private WaferMapDataset dataset;

    /** 
     * Object responsible for drawing the visual representation of each point 
     * on the plot. 
     */
    private WaferMapRenderer renderer;

    /**
     * Creates a new plot with no dataset.
     */
    public WaferMapPlot() {
        this(null);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[8]++;   
    }
    
    /**
     * Creates a new plot.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public WaferMapPlot(WaferMapDataset dataset) {
        this(dataset, null);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[9]++;
    }

    /**
     * Creates a new plot.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param renderer  the renderer (<code>null</code> permitted).
     */
    public WaferMapPlot(WaferMapDataset dataset, WaferMapRenderer renderer) {

        super();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[10]++;

        this.orientation = PlotOrientation.VERTICAL;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[11]++;
        
        this.dataset = dataset;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[12]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[1]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[14]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[2]++;}

        this.renderer = renderer;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[15]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[3]++;
            renderer.setPlot(this);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[17]++;
            renderer.addChangeListener(this);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[18]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[4]++;}

    }

    /**
     * Returns the plot type as a string.
     *
     * @return A short string describing the type of plot.
     */
    public String getPlotType() {
        return ("WMAP_Plot");
    }

    /**
     * Returns the dataset
     * 
     * @return The dataset (possibly <code>null</code>).
     */
    public WaferMapDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset used by the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public void setDataset(WaferMapDataset dataset) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        // if there is an existing dataset, remove the plot from the list of 
        // change listeners...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[5]++;
            this.dataset.removeChangeListener(this);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[20]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[6]++;}

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[21]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[7]++;
            setDatasetGroup(dataset.getGroup());
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[23]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[24]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[8]++;}

        // send a dataset change event to self to trigger plot change event
        datasetChanged(new DatasetChangeEvent(this, dataset));
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[25]++;
    }
    
    /**
     * Sets the item renderer, and notifies all listeners of a change to the 
     * plot.  If the renderer is set to <code>null</code>, no chart will be 
     * drawn.
     *
     * @param renderer  the new renderer (<code>null</code> permitted).
     */
    public void setRenderer(WaferMapRenderer renderer) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[9]++;
            this.renderer.removeChangeListener(this);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[27]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[10]++;}

        this.renderer = renderer;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[28]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[11]++;
            renderer.setPlot(this);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[30]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[12]++;}

        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[31]++;

    }
    
    /**
     * Draws the wafermap view.
     * 
     * @param g2  the graphics device.
     * @param area  the plot area.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param state  the plot state.
     * @param info  the plot rendering info.
     */
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
                     PlotState state, 
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[32]++;

        // if the plot area is too small, just return...
        boolean b1 = (area.getWidth() <= MINIMUM_WIDTH_TO_DRAW);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[33]++;
        boolean b2 = (area.getHeight() <= MINIMUM_HEIGHT_TO_DRAW);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((b1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((b2) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[13]++;
            return;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[14]++;}
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;

        // record the plot area...
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[15]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[36]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[16]++;}
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[37]++;

        // adjust the drawing area for the plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[38]++;

        drawChipGrid(g2, area);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[39]++;       
        drawWaferEdge(g2, area);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[40]++;
        
    }

    /**
     * Calculates and draws the chip locations on the wafer.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     */
    protected void drawChipGrid(Graphics2D g2, Rectangle2D plotArea) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[41]++;
        
        Shape savedClip = g2.getClip();
        g2.setClip(getWaferEdge(plotArea));
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[42]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[43]++;
        Rectangle2D chip = new Rectangle2D.Double();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[44]++;
        int xchips = 35;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[45]++;
        int ychips = 20;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[46]++;
        double space = 1d;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[17]++;
            xchips = this.dataset.getMaxChipX() + 2;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[48]++;
            ychips = this.dataset.getMaxChipY() + 2;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[49]++;
            space = this.dataset.getChipSpace();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[50]++;

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[18]++;}
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[51]++;
        double startX = plotArea.getX();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[52]++;
        double startY = plotArea.getY();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[53]++;
        double chipWidth = 1d;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[54]++;
        double chipHeight = 1d;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((plotArea.getWidth() != plotArea.getHeight()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[19]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[56]++;
            double major = 0d;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[57]++;
            double minor = 0d;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[58]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((plotArea.getWidth() > plotArea.getHeight()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[21]++;
                major = plotArea.getWidth();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[59]++;
                minor = plotArea.getHeight();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[60]++;

            } 
            else {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[22]++;
                major = plotArea.getHeight();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[61]++;
                minor = plotArea.getWidth();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[62]++;
            }
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[63]++;
int CodeCoverConditionCoverageHelper_C12; 
            //set upperLeft point
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((plotArea.getWidth() == minor) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[23]++; // x is minor
                startY += (major - minor) / 2;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[64]++;
                chipWidth = (plotArea.getWidth() - (space * xchips - 1)) 
                    / xchips;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[65]++;
                chipHeight = (plotArea.getWidth() - (space * ychips - 1)) 
                    / ychips;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[66]++;

            }
            else {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[24]++; // y is minor
                startX += (major - minor) / 2;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[67]++;
                chipWidth = (plotArea.getHeight() - (space * xchips - 1)) 
                    / xchips;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[68]++;
                chipHeight = (plotArea.getHeight() - (space * ychips - 1)) 
                    / ychips;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[69]++;
            }

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[20]++;}
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[70]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        
        for (int x = 1;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((x <= xchips) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); x++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[1]--;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[2]--;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[3]++;
}
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[71]++;
            double upperLeftX = (startX - chipWidth) + (chipWidth * x) 
                + (space * (x - 1));
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[72]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;
            for (int y = 1;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((y <= ychips) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); y++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[4]--;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[5]--;
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.loops[6]++;
}
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[73]++;
                double upperLeftY = (startY - chipHeight) + (chipHeight * y) 
                    + (space * (y - 1));
                chip.setFrame(upperLeftX, upperLeftY, chipWidth, chipHeight);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[74]++;
                g2.setColor(Color.white);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[75]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[76]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.dataset.getChipValue(x - 1, ychips - y - 1) != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[25]++;
                    g2.setPaint(
                        this.renderer.getChipColor(
                            this.dataset.getChipValue(x - 1, ychips - y - 1)
                        )
                    );
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[77]++;

                } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[26]++;} 
                g2.fill(chip);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[78]++;
                g2.setColor(Color.lightGray);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[79]++;
                g2.draw(chip);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[80]++;
            }
        }
        g2.setClip(savedClip);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[81]++;
    }

    /**
     * Calculates the location of the waferedge.
     * 
     * @param plotArea  the plot area.
     * 
     * @return The wafer edge.
     */
    protected Ellipse2D getWaferEdge(Rectangle2D plotArea) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[82]++;
        Ellipse2D edge = new Ellipse2D.Double();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[83]++;
        double diameter = plotArea.getWidth();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[84]++;
        double upperLeftX = plotArea.getX();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[85]++;
        double upperLeftY = plotArea.getY();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[86]++;
int CodeCoverConditionCoverageHelper_C16;
        //get major dimension
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((plotArea.getWidth() != plotArea.getHeight()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[27]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[87]++;
            double major = 0d;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[88]++;
            double minor = 0d;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[89]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((plotArea.getWidth() > plotArea.getHeight()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[29]++;
                major = plotArea.getWidth();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[90]++;
                minor = plotArea.getHeight();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[91]++;

            } 
            else {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[30]++;
                major = plotArea.getHeight();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[92]++;
                minor = plotArea.getWidth();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[93]++;
            } 
            //ellipse diameter is the minor dimension
            diameter = minor;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[94]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[95]++;
int CodeCoverConditionCoverageHelper_C18;
            //set upperLeft point
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((plotArea.getWidth() == minor) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[31]++; // x is minor
                upperLeftY = plotArea.getY() + (major - minor) / 2;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[96]++;

            }
            else {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[32]++; // y is minor
                upperLeftX = plotArea.getX() + (major - minor) / 2;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[97]++;
            }

        } else {
  CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[28]++;}
        edge.setFrame(upperLeftX, upperLeftY, diameter, diameter);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[98]++; 
        return edge;        
    }

    /**
     * Draws the waferedge, including the notch.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     */
    protected void drawWaferEdge(Graphics2D g2, Rectangle2D plotArea) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[99]++;
        // draw the wafer
        Ellipse2D waferEdge = getWaferEdge(plotArea);
        g2.setColor(Color.black);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[100]++;
        g2.draw(waferEdge);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[101]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[102]++;
        // calculate and draw the notch
        // horizontal orientation is considered notch right
        // vertical orientation is considered notch down
        Arc2D notch = null;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[103]++;
        Rectangle2D waferFrame = waferEdge.getFrame();
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[104]++;
        double notchDiameter = waferFrame.getWidth() * 0.04;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[105]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[33]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[106]++;
            Rectangle2D notchFrame = 
                new Rectangle2D.Double(
                    waferFrame.getX() + waferFrame.getWidth() 
                    - (notchDiameter / 2), waferFrame.getY()
                    + (waferFrame.getHeight() / 2) - (notchDiameter / 2),
                    notchDiameter, notchDiameter
                );
            notch = new Arc2D.Double(notchFrame, 90d, 180d, Arc2D.OPEN);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[107]++;

        }
        else {
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.branches[34]++;
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[108]++;
            Rectangle2D notchFrame = 
                new Rectangle2D.Double(
                    waferFrame.getX() + (waferFrame.getWidth() / 2) 
                    - (notchDiameter / 2), waferFrame.getY() 
                    + waferFrame.getHeight() - (notchDiameter / 2),
                    notchDiameter, notchDiameter
                );
            notch = new Arc2D.Double(notchFrame, 0d, 180d, Arc2D.OPEN);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[109]++;        
        }
        g2.setColor(Color.white);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[110]++;
        g2.fill(notch);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[111]++;
        g2.setColor(Color.black);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[112]++;
        g2.draw(notch);
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[113]++;
        
    }

    /**
     * Return the legend items from the renderer.
     * 
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
        return this.renderer.getLegendCollection();
    }

    /**
     * Notifies all registered listeners of a renderer change.
     *
     * @param event  the event.
     */
    public void rendererChanged(RendererChangeEvent event) {
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd.statements[114]++;
    }

}

class CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd ());
  }
    public static long[] statements = new long[115];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.WaferMapPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$10sbm42jnlo4ts23ofucm7xcfhd () {
    super("org.jfree.chart.plot.WaferMapPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 114; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.WaferMapPlot.java");
      for (int i = 1; i <= 114; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

