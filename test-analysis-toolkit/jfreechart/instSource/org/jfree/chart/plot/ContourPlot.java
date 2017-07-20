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
 * ----------------
 * ContourPlot.java
 * ----------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Arnaud Lelievre;
 *                   Nicolas Brodu;
 *
 * Changes
 * -------
 * 26-Nov-2002 : Version 1 contributed by David M. O'Donnell (DG);
 * 14-Jan-2003 : Added crosshair attributes (DG);
 * 23-Jan-2003 : Removed two constructors (DG);
 * 21-Mar-2003 : Bug fix 701744 (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 09-Jul-2003 : Changed ColorBar from extending axis classes to enclosing 
 *               them (DG);
 * 05-Aug-2003 : Applied changes in bug report 780298 (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL);
 * 11-Sep-2003 : Cloning support (NB); 
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 17-Jan-2004 : Removed references to DefaultContourDataset class, replaced 
 *               with ContourDataset interface (with changes to the interface). 
 *               See bug 741048 (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 06-Oct-2004 : Updated for changes in DatasetUtilities class (DG);
 * 11-Nov-2004 : Renamed zoom methods to match ValueAxisPlot interface (DG);
 * 25-Nov-2004 : Small update to clone() implementation (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 16-Jun-2005 : Added default constructor (DG);
 * 01-Sep-2005 : Moved dataAreaRatio from Plot to here (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 * 
 */

package org.jfree.chart.plot;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.jfree.chart.ClipPath;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.ColorBar;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ContourEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.labels.ContourToolTipGenerator;
import org.jfree.chart.labels.StandardContourToolTipGenerator;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.contour.ContourDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;

/**
 * A class for creating shaded contours.
 * 
 * @deprecated This plot is no longer supported, please use {@link XYPlot} with
 *     an {@link XYBlockRenderer}.
 */
public class ContourPlot extends Plot implements ContourValuePlot,
                                                 ValueAxisPlot,
                                                 PropertyChangeListener,
                                                 Serializable,
                                                 Cloneable {
  static {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7861072556590502247L;
  static {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[1]++;
  }
    
    /** The default insets. */
    protected static final RectangleInsets DEFAULT_INSETS 
        = new RectangleInsets(2.0, 2.0, 100.0, 10.0);
  static {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[2]++;
  }

    /** The domain axis (used for the x-values). */
    private ValueAxis domainAxis;

    /** The range axis (used for the y-values). */
    private ValueAxis rangeAxis;

    /** The dataset. */
    private ContourDataset dataset;
    
    /** The colorbar axis (used for the z-values). */
    private ColorBar colorBar = null;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[3]++;
  }

    /** The color bar location. */
    private RectangleEdge colorBarLocation;
    
    /** A flag that controls whether or not a domain crosshair is drawn..*/
    private boolean domainCrosshairVisible;

    /** The domain crosshair value. */
    private double domainCrosshairValue;

    /** The pen/brush used to draw the crosshair (if any). */
    private transient Stroke domainCrosshairStroke;

    /** The color used to draw the crosshair (if any). */
    private transient Paint domainCrosshairPaint;

    /** 
     * A flag that controls whether or not the crosshair locks onto actual data
     * points. 
     */
    private boolean domainCrosshairLockedOnData = true;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[4]++;
  }

    /** A flag that controls whether or not a range crosshair is drawn..*/
    private boolean rangeCrosshairVisible;

    /** The range crosshair value. */
    private double rangeCrosshairValue;

    /** The pen/brush used to draw the crosshair (if any). */
    private transient Stroke rangeCrosshairStroke;

    /** The color used to draw the crosshair (if any). */
    private transient Paint rangeCrosshairPaint;

    /** 
     * A flag that controls whether or not the crosshair locks onto actual data
     * points. 
     */
    private boolean rangeCrosshairLockedOnData = true;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[5]++;
  }

    /** 
     * Defines dataArea rectangle as the ratio formed from dividing height by 
     * width (of the dataArea).  Modifies plot area calculations.
     * ratio>0 will attempt to layout the plot so that the
     * dataArea.height/dataArea.width = ratio.
     * ratio<0 will attempt to layout the plot so that the
     * dataArea.height/dataArea.width in plot units (not java2D units as when 
     * ratio>0) = -1.*ratio.
     */         //dmo
    private double dataAreaRatio = 0.0;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[6]++;
  }  //zero when the parameter is not set

    /** A list of markers (optional) for the domain axis. */
    private List domainMarkers;

    /** A list of markers (optional) for the range axis. */
    private List rangeMarkers;

    /** A list of annotations (optional) for the plot. */
    private List annotations;

    /** The tool tip generator. */
    private ContourToolTipGenerator toolTipGenerator;

    /** The URL text generator. */
    private XYURLGenerator urlGenerator;

    /** 
     * Controls whether data are render as filled rectangles or rendered as 
     * points 
     */
    private boolean renderAsPoints = false;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[7]++;
  }

    /** 
     * Size of points rendered when renderAsPoints = true.  Size is relative to
     * dataArea 
     */
    private double ptSizePct = 0.05;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[8]++;
  }

    /** Contains the a ClipPath to "trim" the contours. */
    private transient ClipPath clipPath = null;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[9]++;
  }

    /** Set to Paint to represent missing values. */
    private transient Paint missingPaint = null;
  {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[10]++;
  }

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources = 
        ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[11]++;
  }

    /**
     * Creates a new plot with no dataset or axes.
     */
    public ContourPlot() {
        this(null, null, null, null);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[12]++;
    }
    
    /**
     * Constructs a contour plot with the specified axes (other attributes take
     * default values).
     *
     * @param dataset  The dataset.
     * @param domainAxis  The domain axis.
     * @param rangeAxis  The range axis.
     * @param colorBar  The z-axis axis.
    */
    public ContourPlot(ContourDataset dataset,
                       ValueAxis domainAxis, ValueAxis rangeAxis, 
                       ColorBar colorBar) {

        super();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[13]++;

        this.dataset = dataset;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[14]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[1]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[16]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[2]++;}
        
        this.domainAxis = domainAxis;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[17]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[3]++;
            domainAxis.setPlot(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[19]++;
            domainAxis.addChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[20]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[4]++;}

        this.rangeAxis = rangeAxis;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[21]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[5]++;
            rangeAxis.setPlot(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[23]++;
            rangeAxis.addChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[24]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[6]++;}

        this.colorBar = colorBar;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[25]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((colorBar != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[7]++;
            colorBar.getAxis().setPlot(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[27]++;
            colorBar.getAxis().addChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[28]++;
            colorBar.configure(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[29]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[8]++;}
        this.colorBarLocation = RectangleEdge.LEFT;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[30]++;

        this.toolTipGenerator = new StandardContourToolTipGenerator();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[31]++;

    }

    /**
     * Returns the color bar location.
     * 
     * @return The color bar location.
     */
    public RectangleEdge getColorBarLocation() {
        return this.colorBarLocation;
    }
    
    /**
     * Sets the color bar location and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param edge  the location.
     */
    public void setColorBarLocation(RectangleEdge edge) {
        this.colorBarLocation = edge;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[32]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[33]++;    
    }
    
    /**
     * Returns the primary dataset for the plot.
     * 
     * @return The primary dataset (possibly <code>null</code>).
     */
    public ContourDataset getDataset() {
        return this.dataset;
    }
    
    /**
     * Sets the dataset for the plot, replacing the existing dataset if there
     * is one.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public void setDataset(ContourDataset dataset) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[34]++;
        
        // if there is an existing dataset, remove the plot from the list of 
        // change listeners...
        ContourDataset existing = this.dataset;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[9]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[36]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[10]++;}

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[37]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[11]++;
            setDatasetGroup(dataset.getGroup());
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[39]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[40]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[12]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[41]++;

        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[42]++;
        
    }

    /**
     * Returns the domain axis for the plot.
     *
     * @return The domain axis.
     */
    public ValueAxis getDomainAxis() {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[43]++;

        ValueAxis result = this.domainAxis;

        return result;

    }

    /**
     * Sets the domain axis for the plot (this must be compatible with the plot
     * type or an exception is thrown).
     *
     * @param axis The new axis.
     */
    public void setDomainAxis(ValueAxis axis) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isCompatibleDomainAxis(axis)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[13]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;

            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[15]++;
                axis.setPlot(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[46]++;
                axis.addChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[47]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[16]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;

            // plot is likely registered as a listener with the existing axis...
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[17]++;
                this.domainAxis.removeChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[49]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[18]++;}

            this.domainAxis = axis;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[50]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[51]++;


        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[14]++;}

    }

    /**
     * Returns the range axis for the plot.
     *
     * @return The range axis.
     */
    public ValueAxis getRangeAxis() {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[52]++;

        ValueAxis result = this.rangeAxis;

        return result;

    }

    /**
     * Sets the range axis for the plot.
     * <P>
     * An exception is thrown if the new axis and the plot are not mutually
     * compatible.
     *
     * @param axis The new axis (null permitted).
     */
    public void setRangeAxis(ValueAxis axis) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[53]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[19]++;
            axis.setPlot(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[54]++;
            axis.addChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[55]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[20]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;

        // plot is likely registered as a listener with the existing axis...
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[21]++;
            this.rangeAxis.removeChangeListener(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[57]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[22]++;}

        this.rangeAxis = axis;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[58]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[59]++;

    }

    /**
     * Sets the colorbar for the plot.
     *
     * @param axis The new axis (null permitted).
     */
    public void setColorBarAxis(ColorBar axis) {

        this.colorBar = axis;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[60]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[61]++;

    }

    /**
     * Returns the data area ratio.
     *
     * @return The ratio.
     */
    public double getDataAreaRatio() {
        return this.dataAreaRatio;
    }

    /**
     * Sets the data area ratio.
     *
     * @param ratio  the ratio.
     */
    public void setDataAreaRatio(double ratio) {
        this.dataAreaRatio = ratio;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[62]++;
    }

    /**
     * Adds a marker for the domain axis.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker the marker.
     */
    public void addDomainMarker(Marker marker) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[63]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.domainMarkers == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[23]++;
            this.domainMarkers = new java.util.ArrayList();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[64]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[24]++;}
        this.domainMarkers.add(marker);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[65]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[66]++;

    }

    /**
     * Clears all the domain markers.
     */
    public void clearDomainMarkers() {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[67]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.domainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[25]++;
            this.domainMarkers.clear();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[68]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[69]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[26]++;}
    }

    /**
     * Adds a marker for the range axis.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker The marker.
     */
    public void addRangeMarker(Marker marker) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[70]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.rangeMarkers == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[27]++;
            this.rangeMarkers = new java.util.ArrayList();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[71]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[28]++;}
        this.rangeMarkers.add(marker);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[72]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[73]++;

    }

    /**
     * Clears all the range markers.
     */
    public void clearRangeMarkers() {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[74]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.rangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[29]++;
            this.rangeMarkers.clear();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[75]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[76]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[30]++;}
    }

    /**
     * Adds an annotation to the plot.
     *
     * @param annotation  the annotation.
     */
    public void addAnnotation(XYAnnotation annotation) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.annotations == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[31]++;
            this.annotations = new java.util.ArrayList();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[78]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[32]++;}
        this.annotations.add(annotation);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[79]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[80]++;

    }

    /**
     * Clears all the annotations.
     */
    public void clearAnnotations() {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.annotations != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[33]++;
            this.annotations.clear();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[82]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[83]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[34]++;}
    }

    /**
     * Checks the compatibility of a domain axis, returning true if the axis is
     * compatible with the plot, and false otherwise.
     *
     * @param axis The proposed axis.
     *
     * @return <code>true</code> if the axis is compatible with the plot.
     */
    public boolean isCompatibleDomainAxis(ValueAxis axis) {

        return true;

    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).
     * <P>
     * The optional <code>info</code> argument collects information about the 
     * rendering of the plot (dimensions, tooltip information etc).  Just pass
     * in <code>null</code> if you do not need this information.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot (including axis labels)
     *              should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the state from the parent plot, if there is one.
     * @param info  collects chart drawing information (<code>null</code> 
     *              permitted).
     */
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[84]++;

        // if the plot area is too small, just return...
        boolean b1 = (area.getWidth() <= MINIMUM_WIDTH_TO_DRAW);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[85]++;
        boolean b2 = (area.getHeight() <= MINIMUM_HEIGHT_TO_DRAW);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[86]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((b1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((b2) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[35]++;
            return;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[36]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[87]++;
int CodeCoverConditionCoverageHelper_C19;

        // record the plot area...
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[37]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[88]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[38]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[89]++;

        // adjust the drawing area for plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[90]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[91]++;

        AxisSpace space = new AxisSpace();
        
        space = this.domainAxis.reserveSpace(g2, this, area, 
                RectangleEdge.BOTTOM, space);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[92]++;
        space = this.rangeAxis.reserveSpace(g2, this, area, 
                RectangleEdge.LEFT, space);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[93]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[94]++;

        Rectangle2D estimatedDataArea = space.shrink(area, null);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[95]++;
        
        AxisSpace space2 = new AxisSpace();
        space2 = this.colorBar.reserveSpace(g2, this, area, estimatedDataArea, 
                this.colorBarLocation, space2);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[96]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[97]++;
        Rectangle2D adjustedPlotArea = space2.shrink(area, null);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[98]++;
        
        Rectangle2D dataArea = space.shrink(adjustedPlotArea, null);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[99]++;

        Rectangle2D colorBarArea = space2.reserved(area, this.colorBarLocation);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[100]++;
int CodeCoverConditionCoverageHelper_C20;

        // additional dataArea modifications
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getDataAreaRatio() != 0.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[39]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[101]++; //check whether modification is
            double ratio = getDataAreaRatio();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[102]++;
            Rectangle2D tmpDataArea = (Rectangle2D) dataArea.clone();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[103]++;
            double h = tmpDataArea.getHeight();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[104]++;
            double w = tmpDataArea.getWidth();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[105]++;
int CodeCoverConditionCoverageHelper_C21;

            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ratio > 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[41]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[106]++;
int CodeCoverConditionCoverageHelper_C22; // ratio represents pixels
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((w * ratio <= h) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[43]++;
                    h = ratio * w;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[107]++;

                }
                else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[44]++;
                    w = h / ratio;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[108]++;
                }

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[42]++;  // ratio represents axis units
                ratio *= -1.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[109]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[110]++;
                double xLength = getDomainAxis().getRange().getLength();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[111]++;
                double yLength = getRangeAxis().getRange().getLength();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[112]++;
                double unitRatio = yLength / xLength;

                ratio = unitRatio * ratio;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[113]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[114]++;
int CodeCoverConditionCoverageHelper_C23;

                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((w * ratio <= h) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[45]++;
                    h = ratio * w;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[115]++;

                }
                else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[46]++;
                    w = h / ratio;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[116]++;
                }
            }

            dataArea.setRect(tmpDataArea.getX() + tmpDataArea.getWidth() / 2 
                    - w / 2, tmpDataArea.getY(), w, h);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[117]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[40]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[118]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[47]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[119]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[48]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[120]++;

        CrosshairState crosshairState = new CrosshairState();
        crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[121]++;

        // draw the plot background...
        drawBackground(g2, dataArea);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[122]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[123]++;

        double cursor = dataArea.getMaxY();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[124]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[49]++;
            this.domainAxis.draw(g2, cursor, adjustedPlotArea, dataArea, 
                    RectangleEdge.BOTTOM, info);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[125]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[50]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[126]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[51]++;
            cursor = dataArea.getMinX();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[127]++;
            this.rangeAxis.draw(g2, cursor, adjustedPlotArea, dataArea, 
                    RectangleEdge.LEFT, info);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[128]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[52]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[129]++;
int CodeCoverConditionCoverageHelper_C27;

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.colorBar != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[53]++;
            cursor = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[130]++;
            cursor = this.colorBar.draw(g2, cursor, adjustedPlotArea, dataArea,
                    colorBarArea, this.colorBarLocation);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[131]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[54]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[132]++;
        Shape originalClip = g2.getClip();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[133]++;
        Composite originalComposite = g2.getComposite();

        g2.clip(dataArea);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[134]++;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                getForegroundAlpha()));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[135]++;
        render(g2, dataArea, info, crosshairState);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[136]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[137]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.domainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[55]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[138]++;
            Iterator iterator = this.domainMarkers.iterator();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[139]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[1]++;


int CodeCoverConditionCoverageHelper_C29;
            while ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[1]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[2]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[3]++;
}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[140]++;
                Marker marker = (Marker) iterator.next();
                drawDomainMarker(g2, this, getDomainAxis(), marker, dataArea);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[141]++;
            }

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[56]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[142]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.rangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[57]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[143]++;
            Iterator iterator = this.rangeMarkers.iterator();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[144]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[4]++;


int CodeCoverConditionCoverageHelper_C31;
            while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[4]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[5]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[6]++;
}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[145]++;
                Marker marker = (Marker) iterator.next();
                drawRangeMarker(g2, this, getRangeAxis(), marker, dataArea);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[146]++;
            }

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[58]++;}

// TO DO:  these annotations only work with XYPlot, see if it is possible to 
// make ContourPlot a subclass of XYPlot (DG);

//        // draw the annotations...
//        if (this.annotations != null) {
//            Iterator iterator = this.annotations.iterator();
//            while (iterator.hasNext()) {
//                Annotation annotation = (Annotation) iterator.next();
//                if (annotation instanceof XYAnnotation) {
//                    XYAnnotation xya = (XYAnnotation) annotation;
//                    // get the annotation to draw itself...
//                    xya.draw(g2, this, dataArea, getDomainAxis(), 
//                             getRangeAxis());
//                }
//            }
//        }

        g2.setClip(originalClip);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[147]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[148]++;
        drawOutline(g2, dataArea);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[149]++;

    }

    /**
     * Draws a representation of the data within the dataArea region, using the
     * current renderer.
     * <P>
     * The <code>info</code> and <code>crosshairState</code> arguments may be 
     * <code>null</code>.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param info  an optional object for collection dimension information.
     * @param crosshairState  an optional object for collecting crosshair info.
     */
    public void render(Graphics2D g2, Rectangle2D dataArea,
                       PlotRenderingInfo info, CrosshairState crosshairState) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[150]++;

        // now get the data and plot it (the visual representation will depend
        // on the renderer that has been set)...
        ContourDataset data = getDataset();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[151]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[59]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[152]++;

            ColorBar zAxis = getColorBar();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[153]++;
int CodeCoverConditionCoverageHelper_C33;

            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((this.clipPath != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[61]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[154]++;
                GeneralPath clipper = getClipPath().draw(g2, dataArea, 
                        this.domainAxis, this.rangeAxis);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[155]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.clipPath.isClip()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[63]++;
                    g2.clip(clipper);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[156]++;

                } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[64]++;}

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[62]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[157]++;
int CodeCoverConditionCoverageHelper_C35;

            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.renderAsPoints) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[65]++;
                pointRenderer(g2, dataArea, info, this, this.domainAxis, 
                        this.rangeAxis, zAxis, data, crosshairState);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[158]++;

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[66]++;
                contourRenderer(g2, dataArea, info, this, this.domainAxis, 
                        this.rangeAxis, zAxis, data, crosshairState);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[159]++;
            }

            // draw vertical crosshair if required...
            setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[160]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[161]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isDomainCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[67]++;
                drawVerticalLine(g2, dataArea,
                                 getDomainCrosshairValue(),
                                 getDomainCrosshairStroke(),
                                 getDomainCrosshairPaint());
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[162]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[68]++;}

            // draw horizontal crosshair if required...
            setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[163]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[164]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((isRangeCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[69]++;
                drawHorizontalLine(g2, dataArea,
                                   getRangeCrosshairValue(),
                                   getRangeCrosshairStroke(),
                                   getRangeCrosshairPaint());
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[165]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[70]++;}


        }
        else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[60]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[166]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.clipPath != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[71]++;
            getClipPath().draw(g2, dataArea, this.domainAxis, this.rangeAxis);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[167]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[72]++;}
}

    }

    /**
     * Fills the plot.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param horizontalAxis  the domain (horizontal) axis.
     * @param verticalAxis  the range (vertical) axis.
     * @param colorBar  the color bar axis.
     * @param data  the dataset.
     * @param crosshairState  information about crosshairs on a plot.
     */
    public void contourRenderer(Graphics2D g2,
                                Rectangle2D dataArea,
                                PlotRenderingInfo info,
                                ContourPlot plot,
                                ValueAxis horizontalAxis,
                                ValueAxis verticalAxis,
                                ColorBar colorBar,
                                ContourDataset data,
                                CrosshairState crosshairState) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[168]++;

        // setup for collecting optional entity info...
        Rectangle2D.Double entityArea = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[169]++;
        EntityCollection entities = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[170]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[73]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[171]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[74]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[172]++;

        Rectangle2D.Double rect = null;
        rect = new Rectangle2D.Double();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[173]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[174]++;

        //turn off anti-aliasing when filling rectangles
        Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[175]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[176]++;

        // get the data points
        Number[] xNumber = data.getXValues();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[177]++;
        Number[] yNumber = data.getYValues();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[178]++;
        Number[] zNumber = data.getZValues();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[179]++;

        double[] x = new double[xNumber.length];
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[180]++;
        double[] y = new double[yNumber.length];
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[181]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[7]++;


int CodeCoverConditionCoverageHelper_C40;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i < x.length) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[7]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[8]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[9]++;
}
            x[i] = xNumber[i].doubleValue();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[182]++;
            y[i] = yNumber[i].doubleValue();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[183]++;
        }
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[184]++;

        int[] xIndex = data.indexX();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[185]++;
        int[] indexX = data.getXIndices();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[186]++;
        boolean vertInverted = ((NumberAxis) verticalAxis).isInverted();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[187]++;
        boolean horizInverted = false;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[188]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((horizontalAxis instanceof NumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[75]++;
            horizInverted = ((NumberAxis) horizontalAxis).isInverted();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[189]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[76]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[190]++;
        double transX = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[191]++;
        double transXm1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[192]++;
        double transXp1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[193]++;
        double transDXm1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[194]++;
        double transDXp1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[195]++;
        double transDX = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[196]++;
        double transY = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[197]++;
        double transYm1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[198]++;
        double transYp1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[199]++;
        double transDYm1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[200]++;
        double transDYp1 = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[201]++;
        double transDY = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[202]++;
        int iMax = xIndex[xIndex.length - 1];
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[203]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[10]++;


int CodeCoverConditionCoverageHelper_C42;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((k < x.length) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[10]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[11]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[12]++;
}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[204]++;
            int i = xIndex[k];
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[205]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((indexX[i] == k) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[77]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[206]++;
int CodeCoverConditionCoverageHelper_C44; // this is a new column
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[79]++;
                    transX = horizontalAxis.valueToJava2D(x[k], dataArea, 
                            RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[207]++;
                    transXm1 = transX;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[208]++;
                    transXp1 = horizontalAxis.valueToJava2D(
                            x[indexX[i + 1]], dataArea, RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[209]++;
                    transDXm1 = Math.abs(0.5 * (transX - transXm1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[210]++;
                    transDXp1 = Math.abs(0.5 * (transX - transXp1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[211]++;

                }
                else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[80]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[212]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((i == iMax) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[81]++;
                    transX = horizontalAxis.valueToJava2D(x[k], dataArea, 
                            RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[213]++;
                    transXm1 = horizontalAxis.valueToJava2D(x[indexX[i - 1]], 
                            dataArea, RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[214]++;
                    transXp1 = transX;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[215]++;
                    transDXm1 = Math.abs(0.5 * (transX - transXm1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[216]++;
                    transDXp1 = Math.abs(0.5 * (transX - transXp1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[217]++;

                }
                else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[82]++;
                    transX = horizontalAxis.valueToJava2D(x[k], dataArea, 
                            RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[218]++;
                    transXp1 = horizontalAxis.valueToJava2D(x[indexX[i + 1]], 
                            dataArea, RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[219]++;
                    transDXm1 = transDXp1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[220]++;
                    transDXp1 = Math.abs(0.5 * (transX - transXp1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[221]++;
                }
}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[222]++;
int CodeCoverConditionCoverageHelper_C46;

                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((horizInverted) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[83]++;
                    transX -= transDXp1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[223]++;

                }
                else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[84]++;
                    transX -= transDXm1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[224]++;
                }

                transDX = transDXm1 + transDXp1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[225]++;

                transY = verticalAxis.valueToJava2D(y[k], dataArea, 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[226]++;
                transYm1 = transY;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[227]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[228]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((k + 1 == y.length) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[85]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[229]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[86]++;}
                transYp1 = verticalAxis.valueToJava2D(y[k + 1], dataArea, 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[230]++;
                transDYm1 = Math.abs(0.5 * (transY - transYm1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[231]++;
                transDYp1 = Math.abs(0.5 * (transY - transYp1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[232]++;

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[78]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[233]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C48 |= (32)) == 0 || true) &&
 ((i < indexX.length - 1) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((indexX[i + 1] - 1 == k) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((k == x.length - 1) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[87]++;
                // end of column
                transY = verticalAxis.valueToJava2D(y[k], dataArea, 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[234]++;
                transYm1 = verticalAxis.valueToJava2D(y[k - 1], dataArea, 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[235]++;
                transYp1 = transY;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[236]++;
                transDYm1 = Math.abs(0.5 * (transY - transYm1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[237]++;
                transDYp1 = Math.abs(0.5 * (transY - transYp1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[238]++;

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[88]++;
                transY = verticalAxis.valueToJava2D(y[k], dataArea, 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[239]++;
                transYp1 = verticalAxis.valueToJava2D(y[k + 1], dataArea, 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[240]++;
                transDYm1 = transDYp1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[241]++;
                transDYp1 = Math.abs(0.5 * (transY - transYp1));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[242]++;
            }
}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[243]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((vertInverted) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[89]++;
                transY -= transDYm1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[244]++;

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[90]++;
                transY -= transDYp1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[245]++;
            }

            transDY = transDYm1 + transDYp1;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[246]++;

            rect.setRect(transX, transY, transDX, transDY);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[247]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[248]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((zNumber[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[91]++;
                g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[249]++;
                g2.fill(rect);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[250]++;

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[92]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[251]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.missingPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[93]++;
                g2.setPaint(this.missingPaint);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[252]++;
                g2.fill(rect);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[253]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[94]++;}
}

            entityArea = rect;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[254]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[255]++;
int CodeCoverConditionCoverageHelper_C52;

            // add an entity for the item...
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[95]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[256]++;
                String tip = "";
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[257]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((getToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[97]++;
                    tip = this.toolTipGenerator.generateToolTip(data, k);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[258]++;

                } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[98]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[259]++;
//              Shape s = g2.getClip();
//              if (s.contains(rect) || s.intersects(rect)) {
                String url = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[260]++;
                // if (getURLGenerator() != null) {    //dmo: look at this later
                //      url = getURLGenerator().generateURL(data, series, item);
                // }
                // Unlike XYItemRenderer, we need to clone entityArea since it 
                // reused.
                ContourEntity entity = new ContourEntity(
                        (Rectangle2D.Double) entityArea.clone(), tip, url);
                entity.setIndex(k);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[261]++;
                entities.add(entity);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[262]++;

//              }
            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[96]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[263]++;
int CodeCoverConditionCoverageHelper_C54;

            // do we need to update the crosshair values?
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((plot.isDomainCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[99]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[264]++;
int CodeCoverConditionCoverageHelper_C55;
                if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((plot.isRangeCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[101]++;
                    // both axes
                    crosshairState.updateCrosshairPoint(x[k], y[k], transX, 
                            transY, PlotOrientation.VERTICAL);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[265]++;

                }
                else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[102]++;
                    // just the horizontal axis...
                    crosshairState.updateCrosshairX(transX);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[266]++;
                }

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[100]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[267]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((plot.isRangeCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[103]++;
                    // just the vertical axis...
                    crosshairState.updateCrosshairY(transY);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[268]++;

                } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[104]++;}
            }
        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[269]++;

        return;

    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain (horizontal) axis.
     * @param rangeAxis  the range (vertical) axis.
     * @param colorBar  the color bar axis.
     * @param data  the dataset.
     * @param crosshairState  information about crosshairs on a plot.
     */
    public void pointRenderer(Graphics2D g2,
                              Rectangle2D dataArea,
                              PlotRenderingInfo info,
                              ContourPlot plot,
                              ValueAxis domainAxis,
                              ValueAxis rangeAxis,
                              ColorBar colorBar,
                              ContourDataset data,
                              CrosshairState crosshairState) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[270]++;

        // setup for collecting optional entity info...
        RectangularShape entityArea = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[271]++;
        EntityCollection entities = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[272]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[105]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[273]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[106]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[274]++;

//      Rectangle2D.Double rect = null;
//      rect = new Rectangle2D.Double();
        RectangularShape rect = new Ellipse2D.Double();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[275]++;


        //turn off anti-aliasing when filling rectangles
        Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_OFF);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[276]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[277]++;

        // if (tooltips!=null) tooltips.clearToolTips(); // reset collection
        // get the data points
        Number[] xNumber = data.getXValues();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[278]++;
        Number[] yNumber = data.getYValues();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[279]++;
        Number[] zNumber = data.getZValues();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[280]++;

        double[] x = new double[xNumber.length];
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[281]++;
        double[] y = new double[yNumber.length];
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[282]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[13]++;


int CodeCoverConditionCoverageHelper_C58;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((i < x.length) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[13]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[14]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[15]++;
}
            x[i] = xNumber[i].doubleValue();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[283]++;
            y[i] = yNumber[i].doubleValue();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[284]++;
        }
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[285]++;

        double transX = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[286]++;
        double transDX = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[287]++;
        double transY = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[288]++;
        double transDY = 0.0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[289]++;
        double size = dataArea.getWidth() * this.ptSizePct;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[290]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[16]++;


int CodeCoverConditionCoverageHelper_C59;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((k < x.length) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[16]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[17]--;
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.loops[18]++;
}

            transX = domainAxis.valueToJava2D(x[k], dataArea, 
                    RectangleEdge.BOTTOM) - 0.5 * size;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[291]++;
            transY = rangeAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT)
                     - 0.5 * size;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[292]++;
            transDX = size;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[293]++;
            transDY = size;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[294]++;

            rect.setFrame(transX, transY, transDX, transDY);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[295]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[296]++;
int CodeCoverConditionCoverageHelper_C60;

            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((zNumber[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[107]++;
                g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[297]++;
                g2.fill(rect);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[298]++;

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[108]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[299]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this.missingPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[109]++;
                g2.setPaint(this.missingPaint);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[300]++;
                g2.fill(rect);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[301]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[110]++;}
}


            entityArea = rect;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[302]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[303]++;
int CodeCoverConditionCoverageHelper_C62;

            // add an entity for the item...
            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[111]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[304]++;
                String tip = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[305]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((getToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[113]++;
                    tip = this.toolTipGenerator.generateToolTip(data, k);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[306]++;

                } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[114]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[307]++;
                String url = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[308]++;
                // if (getURLGenerator() != null) {   //dmo: look at this later
                //   url = getURLGenerator().generateURL(data, series, item);
                // }
                // Unlike XYItemRenderer, we need to clone entityArea since it 
                // reused.
                ContourEntity entity = new ContourEntity(
                        (RectangularShape) entityArea.clone(), tip, url);
                entity.setIndex(k);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[309]++;
                entities.add(entity);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[310]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[112]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[311]++;
int CodeCoverConditionCoverageHelper_C64;

            // do we need to update the crosshair values?
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((plot.isDomainCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[115]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[312]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((plot.isRangeCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[117]++;
                    // both axes
                    crosshairState.updateCrosshairPoint(x[k], y[k], transX, 
                            transY, PlotOrientation.VERTICAL);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[313]++;

                }
                else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[118]++;
                    // just the horizontal axis...
                    crosshairState.updateCrosshairX(transX);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[314]++;
                }

            }
            else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[116]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[315]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((plot.isRangeCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[119]++;
                    // just the vertical axis...
                    crosshairState.updateCrosshairY(transY);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[316]++;

                } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[120]++;}
            }
        }


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[317]++;

        return;

    }

    /**
     * Utility method for drawing a crosshair on the chart (if required).
     *
     * @param g2  The graphics device.
     * @param dataArea  The data area.
     * @param value  The coordinate, where to draw the line.
     * @param stroke  The stroke to use.
     * @param paint  The paint to use.
     */
    protected void drawVerticalLine(Graphics2D g2, Rectangle2D dataArea,
                                    double value, Stroke stroke, Paint paint) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[318]++;

        double xx = getDomainAxis().valueToJava2D(value, dataArea, 
                RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[319]++;
        Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, 
                dataArea.getMaxY());
        g2.setStroke(stroke);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[320]++;
        g2.setPaint(paint);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[321]++;
        g2.draw(line);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[322]++;

    }

    /**
     * Utility method for drawing a crosshair on the chart (if required).
     *
     * @param g2  The graphics device.
     * @param dataArea  The data area.
     * @param value  The coordinate, where to draw the line.
     * @param stroke  The stroke to use.
     * @param paint  The paint to use.
     */
    protected void drawHorizontalLine(Graphics2D g2, Rectangle2D dataArea,
                                      double value, Stroke stroke, 
                                      Paint paint) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[323]++;

        double yy = getRangeAxis().valueToJava2D(value, dataArea, 
                RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[324]++;
        Line2D line = new Line2D.Double(dataArea.getMinX(), yy, 
                dataArea.getMaxX(), yy);
        g2.setStroke(stroke);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[325]++;
        g2.setPaint(paint);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[326]++;
        g2.draw(line);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[327]++;

    }

    /**
     * Handles a 'click' on the plot by updating the anchor values...
     *
     * @param x  x-coordinate, where the click occured.
     * @param y  y-coordinate, where the click occured.
     * @param info  An object for collection dimension information.
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {

/*        // set the anchor value for the horizontal axis...
        ValueAxis hva = getDomainAxis();
        if (hva != null) {
            double hvalue = hva.translateJava2DtoValue(
                (float) x, info.getDataArea()
            );

            hva.setAnchorValue(hvalue);
            setDomainCrosshairValue(hvalue);
        }

        // set the anchor value for the vertical axis...
        ValueAxis vva = getRangeAxis();
        if (vva != null) {
            double vvalue = vva.translateJava2DtoValue(
                (float) y, info.getDataArea()
            );
            vva.setAnchorValue(vvalue);
            setRangeCrosshairValue(vvalue);
        }
*/
    }

    /**
     * Zooms the axis ranges by the specified percentage about the anchor point.
     *
     * @param percent  The amount of the zoom.
     */
    public void zoom(double percent) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[328]++;
int CodeCoverConditionCoverageHelper_C67;

        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((percent > 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[121]++;

          //  double range = this.domainAxis.getRange().getLength();
          //  double scaledRange = range * percent;
          //  domainAxis.setAnchoredRange(scaledRange);

          //  range = this.rangeAxis.getRange().getLength();
         //  scaledRange = range * percent;
         //   rangeAxis.setAnchoredRange(scaledRange);
        }
        else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[122]++;
            getRangeAxis().setAutoRange(true);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[329]++;
            getDomainAxis().setAutoRange(true);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[330]++;
        }

    }

    /**
     * Returns the plot type as a string.
     *
     * @return A short string describing the type of plot.
     */
    public String getPlotType() {
        return localizationResources.getString("Contour_Plot");
    }

    /**
     * Returns the range for an axis.
     *
     * @param axis  the axis.
     *
     * @return The range for an axis.
     */
    public Range getDataRange(ValueAxis axis) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[331]++;
int CodeCoverConditionCoverageHelper_C68;

        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[123]++;
            return null;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[124]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[332]++;

        Range result = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[333]++;
int CodeCoverConditionCoverageHelper_C69;

        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((axis == getDomainAxis()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[125]++;
            result = DatasetUtilities.findDomainBounds(this.dataset);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[334]++;

        }
        else {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[126]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[335]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((axis == getRangeAxis()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[127]++;
            result = DatasetUtilities.findRangeBounds(this.dataset);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[336]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[128]++;}
}

        return result;

    }

    /**
     * Returns the range for the Contours.
     *
     * @return The range for the Contours (z-axis).
     */
    public Range getContourDataRange() {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[337]++;

        Range result = null;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[338]++;

        ContourDataset data = getDataset();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[339]++;
int CodeCoverConditionCoverageHelper_C71;

        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[129]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[340]++;
            Range h = getDomainAxis().getRange();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[341]++;
            Range v = getRangeAxis().getRange();
            result = this.visibleRange(data, h, v);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[342]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[130]++;}

        return result;
    }

    /**
     * Notifies all registered listeners of a property change.
     * <P>
     * One source of property change events is the plot's renderer.
     *
     * @param event  Information about the property change.
     */
    public void propertyChange(PropertyChangeEvent event) {
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[343]++;
    }

    /**
     * Receives notification of a change to the plot's dataset.
     * <P>
     * The chart reacts by passing on a chart change event to all registered
     * listeners.
     *
     * @param event  Information about the event (not used here).
     */
    public void datasetChanged(DatasetChangeEvent event) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[344]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((this.domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[131]++;
            this.domainAxis.configure();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[345]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[132]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[346]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[133]++;
            this.rangeAxis.configure();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[347]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[134]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[348]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((this.colorBar != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[135]++;
            this.colorBar.configure(this);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[349]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[136]++;}
        super.datasetChanged(event);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[350]++;
    }

    /**
     * Returns the colorbar.
     *
     * @return The colorbar.
     */
    public ColorBar getColorBar() {
        return this.colorBar;
    }

    /**
     * Returns a flag indicating whether or not the domain crosshair is visible.
     *
     * @return The flag.
     */
    public boolean isDomainCrosshairVisible() {
        return this.domainCrosshairVisible;
    }

    /**
     * Sets the flag indicating whether or not the domain crosshair is visible.
     *
     * @param flag  the new value of the flag.
     */
    public void setDomainCrosshairVisible(boolean flag) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[351]++;
int CodeCoverConditionCoverageHelper_C75;

        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.domainCrosshairVisible != flag) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[137]++;
            this.domainCrosshairVisible = flag;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[352]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[353]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[138]++;}

    }

    /**
     * Returns a flag indicating whether or not the crosshair should "lock-on"
     * to actual data values.
     *
     * @return The flag.
     */
    public boolean isDomainCrosshairLockedOnData() {
        return this.domainCrosshairLockedOnData;
    }

    /**
     * Sets the flag indicating whether or not the domain crosshair should 
     * "lock-on" to actual data values.
     *
     * @param flag  the flag.
     */
    public void setDomainCrosshairLockedOnData(boolean flag) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[354]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((this.domainCrosshairLockedOnData != flag) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[139]++;
            this.domainCrosshairLockedOnData = flag;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[355]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[356]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[140]++;}
    }

    /**
     * Returns the domain crosshair value.
     *
     * @return The value.
     */
    public double getDomainCrosshairValue() {
        return this.domainCrosshairValue;
    }

    /**
     * Sets the domain crosshair value.
     * <P>
     * Registered listeners are notified that the plot has been modified, but
     * only if the crosshair is visible.
     *
     * @param value  the new value.
     */
    public void setDomainCrosshairValue(double value) {
        setDomainCrosshairValue(value, true);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[357]++;
    }

    /**
     * Sets the domain crosshair value.
     * <P>
     * Registered listeners are notified that the axis has been modified, but
     * only if the crosshair is visible.
     *
     * @param value  the new value.
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     */
    public void setDomainCrosshairValue(double value, boolean notify) {
        this.domainCrosshairValue = value;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[358]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[359]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((isDomainCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[141]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[360]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[142]++;}
    }

    /**
     * Returns the Stroke used to draw the crosshair (if visible).
     *
     * @return The crosshair stroke.
     */
    public Stroke getDomainCrosshairStroke() {
        return this.domainCrosshairStroke;
    }

    /**
     * Sets the Stroke used to draw the crosshairs (if visible) and notifies
     * registered listeners that the axis has been modified.
     *
     * @param stroke  the new crosshair stroke.
     */
    public void setDomainCrosshairStroke(Stroke stroke) {
        this.domainCrosshairStroke = stroke;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[361]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[362]++;
    }

    /**
     * Returns the domain crosshair color.
     *
     * @return The crosshair color.
     */
    public Paint getDomainCrosshairPaint() {
        return this.domainCrosshairPaint;
    }

    /**
     * Sets the Paint used to color the crosshairs (if visible) and notifies
     * registered listeners that the axis has been modified.
     *
     * @param paint the new crosshair paint.
     */
    public void setDomainCrosshairPaint(Paint paint) {
        this.domainCrosshairPaint = paint;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[363]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[364]++;
    }

    /**
     * Returns a flag indicating whether or not the range crosshair is visible.
     *
     * @return The flag.
     */
    public boolean isRangeCrosshairVisible() {
        return this.rangeCrosshairVisible;
    }

    /**
     * Sets the flag indicating whether or not the range crosshair is visible.
     *
     * @param flag  the new value of the flag.
     */
    public void setRangeCrosshairVisible(boolean flag) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[365]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairVisible != flag) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[143]++;
            this.rangeCrosshairVisible = flag;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[366]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[367]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[144]++;}
    }

    /**
     * Returns a flag indicating whether or not the crosshair should "lock-on"
     * to actual data values.
     *
     * @return The flag.
     */
    public boolean isRangeCrosshairLockedOnData() {
        return this.rangeCrosshairLockedOnData;
    }

    /**
     * Sets the flag indicating whether or not the range crosshair should 
     * "lock-on" to actual data values.
     *
     * @param flag  the flag.
     */
    public void setRangeCrosshairLockedOnData(boolean flag) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[368]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairLockedOnData != flag) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[145]++;
            this.rangeCrosshairLockedOnData = flag;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[369]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[370]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[146]++;}
    }

    /**
     * Returns the range crosshair value.
     *
     * @return The value.
     */
    public double getRangeCrosshairValue() {
        return this.rangeCrosshairValue;
    }

    /**
     * Sets the domain crosshair value.
     * <P>
     * Registered listeners are notified that the plot has been modified, but
     * only if the crosshair is visible.
     *
     * @param value  the new value.
     */
    public void setRangeCrosshairValue(double value) {
        setRangeCrosshairValue(value, true);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[371]++;
    }

    /**
     * Sets the range crosshair value.
     * <P>
     * Registered listeners are notified that the axis has been modified, but
     * only if the crosshair is visible.
     *
     * @param value  the new value.
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     */
    public void setRangeCrosshairValue(double value, boolean notify) {
        this.rangeCrosshairValue = value;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[372]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[373]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((isRangeCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[147]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[374]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[148]++;}
    }

    /**
     * Returns the Stroke used to draw the crosshair (if visible).
     *
     * @return The crosshair stroke.
     */
    public Stroke getRangeCrosshairStroke() {
        return this.rangeCrosshairStroke;
    }

    /**
     * Sets the Stroke used to draw the crosshairs (if visible) and notifies
     * registered listeners that the axis has been modified.
     *
     * @param stroke  the new crosshair stroke.
     */
    public void setRangeCrosshairStroke(Stroke stroke) {
        this.rangeCrosshairStroke = stroke;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[375]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[376]++;
    }

    /**
     * Returns the range crosshair color.
     *
     * @return The crosshair color.
     */
    public Paint getRangeCrosshairPaint() {
        return this.rangeCrosshairPaint;
    }

    /**
     * Sets the Paint used to color the crosshairs (if visible) and notifies
     * registered listeners that the axis has been modified.
     *
     * @param paint the new crosshair paint.
     */
    public void setRangeCrosshairPaint(Paint paint) {
        this.rangeCrosshairPaint = paint;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[377]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[378]++;
    }

    /**
     * Returns the tool tip generator.
     *
     * @return The tool tip generator (possibly null).
     */
    public ContourToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }

    /**
     * Sets the tool tip generator.
     *
     * @param generator  the tool tip generator (null permitted).
     */
    public void setToolTipGenerator(ContourToolTipGenerator generator) {
        //Object oldValue = this.toolTipGenerator;
        this.toolTipGenerator = generator;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[379]++;
    }

    /**
     * Returns the URL generator for HTML image maps.
     *
     * @return The URL generator (possibly null).
     */
    public XYURLGenerator getURLGenerator() {
        return this.urlGenerator;
    }

    /**
     * Sets the URL generator for HTML image maps.
     *
     * @param urlGenerator  the URL generator (null permitted).
     */
    public void setURLGenerator(XYURLGenerator urlGenerator) {
        //Object oldValue = this.urlGenerator;
        this.urlGenerator = urlGenerator;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[380]++;
    }

    /**
     * Draws a vertical line on the chart to represent a 'range marker'.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param marker  the marker line.
     * @param dataArea  the axis data area.
     */
    public void drawDomainMarker(Graphics2D g2,
                                 ContourPlot plot,
                                 ValueAxis domainAxis,
                                 Marker marker,
                                 Rectangle2D dataArea) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[381]++;
int CodeCoverConditionCoverageHelper_C81;

        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((marker instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[149]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[382]++;
            ValueMarker vm = (ValueMarker) marker;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[383]++;
            double value = vm.getValue();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[384]++;
            Range range = domainAxis.getRange();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[385]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[151]++;
                return;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[152]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[386]++;
  
            double x = domainAxis.valueToJava2D(value, dataArea, 
                    RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[387]++;
            Line2D line = new Line2D.Double(x, dataArea.getMinY(), x, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[388]++;
            Paint paint = marker.getOutlinePaint();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[389]++;
            Stroke stroke = marker.getOutlineStroke();
            g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[390]++;
            g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[391]++;
            g2.draw(line);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[392]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[150]++;}

    }

    /**
     * Draws a horizontal line across the chart to represent a 'range marker'.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param rangeAxis  the range axis.
     * @param marker  the marker line.
     * @param dataArea  the axis data area.
     */
    public void drawRangeMarker(Graphics2D g2,
                                ContourPlot plot,
                                ValueAxis rangeAxis,
                                Marker marker,
                                Rectangle2D dataArea) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[393]++;
int CodeCoverConditionCoverageHelper_C83;

        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((marker instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[153]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[394]++;
            ValueMarker vm = (ValueMarker) marker;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[395]++;
            double value = vm.getValue();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[396]++;
            Range range = rangeAxis.getRange();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[397]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[155]++;
                return;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[156]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[398]++;

            double y = rangeAxis.valueToJava2D(value, dataArea, 
                    RectangleEdge.LEFT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[399]++;
            Line2D line = new Line2D.Double(dataArea.getMinX(), y, 
                    dataArea.getMaxX(), y);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[400]++;
            Paint paint = marker.getOutlinePaint();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[401]++;
            Stroke stroke = marker.getOutlineStroke();
            g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[402]++;
            g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[403]++;
            g2.draw(line);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[404]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[154]++;}

    }

    /**
     * Returns the clipPath.
     * @return ClipPath
     */
    public ClipPath getClipPath() {
        return this.clipPath;
    }

    /**
     * Sets the clipPath.
     * @param clipPath The clipPath to set
     */
    public void setClipPath(ClipPath clipPath) {
        this.clipPath = clipPath;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[405]++;
    }

    /**
     * Returns the ptSizePct.
     * @return double
     */
    public double getPtSizePct() {
        return this.ptSizePct;
    }

    /**
     * Returns the renderAsPoints.
     * @return boolean
     */
    public boolean isRenderAsPoints() {
        return this.renderAsPoints;
    }

    /**
     * Sets the ptSizePct.
     * @param ptSizePct The ptSizePct to set
     */
    public void setPtSizePct(double ptSizePct) {
        this.ptSizePct = ptSizePct;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[406]++;
    }

    /**
     * Sets the renderAsPoints.
     * @param renderAsPoints The renderAsPoints to set
     */
    public void setRenderAsPoints(boolean renderAsPoints) {
        this.renderAsPoints = renderAsPoints;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[407]++;
    }

    /**
     * Receives notification of a change to one of the plot's axes.
     *
     * @param event  information about the event.
     */
    public void axisChanged(AxisChangeEvent event) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[408]++;
        Object source = event.getSource();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[409]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((source.equals(this.rangeAxis)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((source.equals(this.domainAxis)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[157]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[410]++;
            ColorBar cba = this.colorBar;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[411]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((this.colorBar.getAxis().isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[159]++;
                cba.getAxis().configure();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[412]++;

            } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[160]++;}


        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[158]++;}
        super.axisChanged(event);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[413]++;
    }

    /**
     * Returns the visible z-range.
     *
     * @param data  the dataset.
     * @param x  the x range.
     * @param y  the y range.
     *
     * @return The range.
     */
    public Range visibleRange(ContourDataset data, Range x, Range y) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[414]++;
        Range range = null;
        range = data.getZValueRange(x, y);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[415]++;
        return range;
    }

    /**
     * Returns the missingPaint.
     * @return Paint
     */
    public Paint getMissingPaint() {
        return this.missingPaint;
    }

    /**
     * Sets the missingPaint.
     * 
     * @param paint  the missingPaint to set.
     */
    public void setMissingPaint(Paint paint) {
        this.missingPaint = paint;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[416]++;
    }
    
    /**
     * Multiplies the range on the domain axis/axes by the specified factor 
     * (to be implemented).
     * 
     * @param x  the x-coordinate (in Java2D space).
     * @param y  the y-coordinate (in Java2D space).
     * @param factor  the zoom factor.
     */
    public void zoomDomainAxes(double x, double y, double factor) {
        // TODO: to be implemented
    }
    
    /**
     * Zooms the domain axes (not yet implemented).
     * 
     * @param x  the x-coordinate (in Java2D space).
     * @param y  the y-coordinate (in Java2D space).
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     */
    public void zoomDomainAxes(double x, double y, double lowerPercent, 
                               double upperPercent) {
        // TODO: to be implemented
    }
    
    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     * 
     * @param x  the x-coordinate (in Java2D space).
     * @param y  the y-coordinate (in Java2D space).
     * @param factor  the zoom factor.
     */
    public void zoomRangeAxes(double x, double y, double factor) {
        // TODO: to be implemented
    }

    /**
     * Zooms the range axes (not yet implemented).
     * 
     * @param x  the x-coordinate (in Java2D space).
     * @param y  the y-coordinate (in Java2D space).
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     */
    public void zoomRangeAxes(double x, double y, double lowerPercent, 
                              double upperPercent) {
        // TODO: to be implemented
    }

    /**
     * Returns <code>false</code>.
     * 
     * @return A boolean.
     */
    public boolean isDomainZoomable() {
        return false;
    }
    
    /**
     * Returns <code>false</code>.
     * 
     * @return A boolean.
     */
    public boolean isRangeZoomable() {
        return false;
    }

    /** 
     * Extends plot cloning to this plot type
     * @see org.jfree.chart.plot.Plot#clone()
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[417]++;
        ContourPlot clone = (ContourPlot) super.clone();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[418]++;
int CodeCoverConditionCoverageHelper_C87;
        
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((this.domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[161]++;
            clone.domainAxis = (ValueAxis) this.domainAxis.clone();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[419]++;
            clone.domainAxis.setPlot(clone);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[420]++;
            clone.domainAxis.addChangeListener(clone);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[421]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[162]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[422]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[163]++;
            clone.rangeAxis = (ValueAxis) this.rangeAxis.clone();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[423]++;
            clone.rangeAxis.setPlot(clone);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[424]++;
            clone.rangeAxis.addChangeListener(clone);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[425]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[164]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[426]++;
int CodeCoverConditionCoverageHelper_C89;

        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((clone.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[165]++;
            clone.dataset.addChangeListener(clone);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[427]++;
 
        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[166]++;}
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[428]++;
int CodeCoverConditionCoverageHelper_C90;
    
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((this.colorBar != null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[167]++;
            clone.colorBar = (ColorBar) this.colorBar.clone();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[429]++;

        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[168]++;}

        clone.domainMarkers = (List) ObjectUtilities.deepClone(
                this.domainMarkers);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[430]++;
        clone.rangeMarkers = (List) ObjectUtilities.deepClone(
                this.rangeMarkers);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[431]++;
        clone.annotations = (List) ObjectUtilities.deepClone(this.annotations);
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[432]++;
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[433]++;
int CodeCoverConditionCoverageHelper_C91;

        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((this.clipPath != null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[169]++;
            clone.clipPath = (ClipPath) this.clipPath.clone();
CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.statements[434]++;
 
        } else {
  CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t.branches[170]++;}

        return clone;
    }

}

class CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t ());
  }
    public static long[] statements = new long[435];
    public static long[] branches = new long[171];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[92];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.ContourPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 91; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$3zq289bdbaqrjnx6nk1zgb49t () {
    super("org.jfree.chart.plot.ContourPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 434; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 170; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 91; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.ContourPlot.java");
      for (int i = 1; i <= 434; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 170; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 91; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

