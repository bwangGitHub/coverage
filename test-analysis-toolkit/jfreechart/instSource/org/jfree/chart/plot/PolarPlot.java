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
 * --------------
 * PolarPlot.java
 * --------------
 * (C) Copyright 2004-2007, by Solution Engineering, Inc. and Contributors.
 *
 * Original Author:  Daniel Bridenbecker, Solution Engineering, Inc.;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Jan-2004 : Version 1, contributed by DB with minor changes by DG (DG);
 * 07-Apr-2004 : Changed text bounds calculation (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 09-Jun-2005 : Fixed getDataRange() and equals() methods (DG);
 * 25-Oct-2005 : Implemented Zoomable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 07-Feb-2007 : Fixed bug 1599761, data value less than axis minimum (DG);
 * 21-Mar-2007 : Fixed serialization bug (DG);
 * 24-Sep-2007 : Implemented new zooming methods (DG);
 *
 */

package org.jfree.chart.plot;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.renderer.PolarItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;


/**
 * Plots data that is in (theta, radius) pairs where
 * theta equal to zero is due north and increases clockwise.
 */
public class PolarPlot extends Plot implements ValueAxisPlot, Zoomable,
        RendererChangeListener, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.ping();
  }

   
    /** For serialization. */
    private static final long serialVersionUID = 3794383185924179525L;
  static {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[1]++;
  }
    
    /** The default margin. */
    private static final int MARGIN = 20;
  static {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[2]++;
  }
   
    /** The annotation margin. */
    private static final double ANNOTATION_MARGIN = 7.0;
  static {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[3]++;
  }
   
    /** The default grid line stroke. */
    public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(
            0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 
            0.0f, new float[]{2.0f, 2.0f}, 0.0f);
  static {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[4]++;
  }
   
    /** The default grid line paint. */
    public static final Paint DEFAULT_GRIDLINE_PAINT = Color.gray;
  static {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[5]++;
  }
   
    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
        = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[6]++;
  }
   
    /** The angles that are marked with gridlines. */
    private List angleTicks;
    
    /** The axis (used for the y-values). */
    private ValueAxis axis;
    
    /** The dataset. */
    private XYDataset dataset;
   
    /** 
     * Object responsible for drawing the visual representation of each point 
     * on the plot. 
     */
    private PolarItemRenderer renderer;
   
    /** A flag that controls whether or not the angle labels are visible. */
    private boolean angleLabelsVisible = true;
  {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[7]++;
  }
    
    /** The font used to display the angle labels - never null. */
    private Font angleLabelFont = new Font("SansSerif", Font.PLAIN, 12);
  {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[8]++;
  }
    
    /** The paint used to display the angle labels. */
    private transient Paint angleLabelPaint = Color.black;
  {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[9]++;
  }
    
    /** A flag that controls whether the angular grid-lines are visible. */
    private boolean angleGridlinesVisible;
   
    /** The stroke used to draw the angular grid-lines. */
    private transient Stroke angleGridlineStroke;
   
    /** The paint used to draw the angular grid-lines. */
    private transient Paint angleGridlinePaint;
   
    /** A flag that controls whether the radius grid-lines are visible. */
    private boolean radiusGridlinesVisible;
   
    /** The stroke used to draw the radius grid-lines. */
    private transient Stroke radiusGridlineStroke;
   
    /** The paint used to draw the radius grid-lines. */
    private transient Paint radiusGridlinePaint;
   
    /** The annotations for the plot. */
    private List cornerTextItems = new ArrayList();
  {
    CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[10]++;
  }
   
    /**
     * Default constructor.
     */
    public PolarPlot() {
        this(null, null, null);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[11]++;
    }
   
   /**
     * Creates a new plot.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param radiusAxis  the radius axis (<code>null</code> permitted).
     * @param renderer  the renderer (<code>null</code> permitted).
     */
    public PolarPlot(XYDataset dataset, 
                     ValueAxis radiusAxis,
                     PolarItemRenderer renderer) {
      
        super();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[12]++;
            
        this.dataset = dataset;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[13]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[1]++;
            this.dataset.addChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[15]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[2]++;}
      
        this.angleTicks = new java.util.ArrayList();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[16]++;
        this.angleTicks.add(new NumberTick(new Double(0.0), "0", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[17]++;
        this.angleTicks.add(new NumberTick(new Double(45.0), "45", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[18]++;
        this.angleTicks.add(new NumberTick(new Double(90.0), "90", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[19]++;
        this.angleTicks.add(new NumberTick(new Double(135.0), "135", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[20]++;
        this.angleTicks.add(new NumberTick(new Double(180.0), "180", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[21]++;
        this.angleTicks.add(new NumberTick(new Double(225.0), "225", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[22]++;
        this.angleTicks.add(new NumberTick(new Double(270.0), "270", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[23]++;
        this.angleTicks.add(new NumberTick(new Double(315.0), "315", 
                TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[24]++;
        
        this.axis = radiusAxis;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[25]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.axis != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[3]++;
            this.axis.setPlot(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[27]++;
            this.axis.addChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[28]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[4]++;}
      
        this.renderer = renderer;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[29]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[5]++;
            this.renderer.setPlot(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[31]++;
            this.renderer.addChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[32]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[6]++;}
      
        this.angleGridlinesVisible = true;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[33]++;
        this.angleGridlineStroke = DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[34]++;
        this.angleGridlinePaint = DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[35]++;
      
        this.radiusGridlinesVisible = true;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[36]++;
        this.radiusGridlineStroke = DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[37]++;
        this.radiusGridlinePaint = DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[38]++;      
    }
   
    /**
     * Add text to be displayed in the lower right hand corner and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param text  the text to display (<code>null</code> not permitted).
     * 
     * @see #removeCornerTextItem(String)
     */
    public void addCornerTextItem(String text) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[39]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[7]++;
            throw new IllegalArgumentException("Null 'text' argument.");

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[8]++;}
        this.cornerTextItems.add(text);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[40]++;
        this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[41]++;
    }
   
    /**
     * Remove the given text from the list of corner text items and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param text  the text to remove (<code>null</code> ignored).
     * 
     * @see #addCornerTextItem(String)
     */
    public void removeCornerTextItem(String text) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[42]++;
        boolean removed = this.cornerTextItems.remove(text);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[43]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[9]++;
            this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[44]++;
        
        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[10]++;}
    }
   
    /**
     * Clear the list of corner text items and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @see #addCornerTextItem(String)
     * @see #removeCornerTextItem(String)
     */
    public void clearCornerTextItems() {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.cornerTextItems.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[11]++;
            this.cornerTextItems.clear();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[46]++;
            this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[47]++;
        
        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[12]++;}
    }
   
    /**
     * Returns the plot type as a string.
     *
     * @return A short string describing the type of plot.
     */
    public String getPlotType() {
       return PolarPlot.localizationResources.getString("Polar_Plot");
    }
    
    /**
     * Returns the axis for the plot.
     *
     * @return The radius axis (possibly <code>null</code>).
     * 
     * @see #setAxis(ValueAxis)
     */
    public ValueAxis getAxis() {
        return this.axis;
    }
   
    /**
     * Sets the axis for the plot and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param axis  the new axis (<code>null</code> permitted).
     */
    public void setAxis(ValueAxis axis) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[48]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[13]++;
            axis.setPlot(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[49]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[14]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[50]++;
int CodeCoverConditionCoverageHelper_C8;
       
        // plot is likely registered as a listener with the existing axis...
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.axis != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[15]++;
            this.axis.removeChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[51]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[16]++;}
       
        this.axis = axis;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[52]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[53]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.axis != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[17]++;
            this.axis.configure();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[54]++;
            this.axis.addChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[55]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[18]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[56]++;
    }
   
    /**
     * Returns the primary dataset for the plot.
     *
     * @return The primary dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(XYDataset)
     */
    public XYDataset getDataset() {
        return this.dataset;
    }
    
    /**
     * Sets the dataset for the plot, replacing the existing dataset if there 
     * is one.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(XYDataset dataset) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[57]++;
        // if there is an existing dataset, remove the plot from the list of 
        // change listeners...
        XYDataset existing = this.dataset;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[58]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[19]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[59]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[20]++;}
       
        // set the new m_Dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[60]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[61]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[21]++;
            setDatasetGroup(this.dataset.getGroup());
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[62]++;
            this.dataset.addChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[63]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[22]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[64]++;
       
        // send a m_Dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, this.dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[65]++;
    }
   
    /**
     * Returns the item renderer.
     *
     * @return The renderer (possibly <code>null</code>).
     * 
     * @see #setRenderer(PolarItemRenderer)
     */
    public PolarItemRenderer getRenderer() {
        return this.renderer;
    }
   
    /**
     * Sets the item renderer, and notifies all listeners of a change to the 
     * plot.
     * <P>
     * If the renderer is set to <code>null</code>, no chart will be drawn.
     *
     * @param renderer  the new renderer (<code>null</code> permitted).
     * 
     * @see #getRenderer()
     */
    public void setRenderer(PolarItemRenderer renderer) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[66]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[23]++;
            this.renderer.removeChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[67]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[24]++;}
       
        this.renderer = renderer;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[68]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[69]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[25]++;
            this.renderer.setPlot(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[70]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[26]++;}
       
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[71]++;
    }
   
    /**
     * Returns a flag that controls whether or not the angle labels are visible.
     * 
     * @return A boolean.
     * 
     * @see #setAngleLabelsVisible(boolean)
     */
    public boolean isAngleLabelsVisible() {
        return this.angleLabelsVisible;
    }
    
    /**
     * Sets the flag that controls whether or not the angle labels are visible,
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #isAngleLabelsVisible()
     */
    public void setAngleLabelsVisible(boolean visible) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[72]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.angleLabelsVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[27]++;
            this.angleLabelsVisible = visible;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[73]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[74]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[28]++;}
    }
    
    /**
     * Returns the font used to display the angle labels.
     * 
     * @return A font (never <code>null</code>).
     * 
     * @see #setAngleLabelFont(Font)
     */
    public Font getAngleLabelFont() {
        return this.angleLabelFont;
    }
    
    /**
     * Sets the font used to display the angle labels and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getAngleLabelFont()
     */
    public void setAngleLabelFont(Font font) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[75]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[29]++;
            throw new IllegalArgumentException("Null 'font' argument.");
   
        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[30]++;}
        this.angleLabelFont = font;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[76]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[77]++;
    }
    
    /**
     * Returns the paint used to display the angle labels.
     * 
     * @return A paint (never <code>null</code>).
     * 
     * @see #setAngleLabelPaint(Paint)
     */
    public Paint getAngleLabelPaint() {
        return this.angleLabelPaint;
    }
    
    /**
     * Sets the paint used to display the angle labels and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setAngleLabelPaint(Paint paint) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[78]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[31]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[32]++;}
        this.angleLabelPaint = paint;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[79]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[80]++;
    }
    
    /**
     * Returns <code>true</code> if the angular gridlines are visible, and 
     * <code>false<code> otherwise.
     *
     * @return <code>true</code> or <code>false</code>.
     * 
     * @see #setAngleGridlinesVisible(boolean)
     */
    public boolean isAngleGridlinesVisible() {
        return this.angleGridlinesVisible;
    }
    
    /**
     * Sets the flag that controls whether or not the angular grid-lines are 
     * visible.
     * <p>
     * If the flag value is changed, a {@link PlotChangeEvent} is sent to all 
     * registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #isAngleGridlinesVisible()
     */
    public void setAngleGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.angleGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[33]++;
            this.angleGridlinesVisible = visible;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[82]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[83]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[34]++;}
    }
   
    /**
     * Returns the stroke for the grid-lines (if any) plotted against the 
     * angular axis.
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setAngleGridlineStroke(Stroke)
     */
    public Stroke getAngleGridlineStroke() {
        return this.angleGridlineStroke;
    }
    
    /**
     * Sets the stroke for the grid lines plotted against the angular axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     * <p>
     * If you set this to <code>null</code>, no grid lines will be drawn.
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getAngleGridlineStroke()
     */
    public void setAngleGridlineStroke(Stroke stroke) {
        this.angleGridlineStroke = stroke;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[84]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[85]++;
    }
    
    /**
     * Returns the paint for the grid lines (if any) plotted against the 
     * angular axis.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setAngleGridlinePaint(Paint)
     */
    public Paint getAngleGridlinePaint() {
        return this.angleGridlinePaint;
    }
   
    /**
     * Sets the paint for the grid lines plotted against the angular axis.
     * <p>
     * If you set this to <code>null</code>, no grid lines will be drawn.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getAngleGridlinePaint()
     */
    public void setAngleGridlinePaint(Paint paint) {
        this.angleGridlinePaint = paint;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[86]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[87]++;
    }
    
    /**
     * Returns <code>true</code> if the radius axis grid is visible, and 
     * <code>false<code> otherwise.
     *
     * @return <code>true</code> or <code>false</code>.
     * 
     * @see #setRadiusGridlinesVisible(boolean)
     */
    public boolean isRadiusGridlinesVisible() {
        return this.radiusGridlinesVisible;
    }
    
    /**
     * Sets the flag that controls whether or not the radius axis grid lines 
     * are visible.
     * <p>
     * If the flag value is changed, a {@link PlotChangeEvent} is sent to all 
     * registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #isRadiusGridlinesVisible()
     */
    public void setRadiusGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[88]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.radiusGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[35]++;
            this.radiusGridlinesVisible = visible;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[89]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[90]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[36]++;}
    }
   
    /**
     * Returns the stroke for the grid lines (if any) plotted against the 
     * radius axis.
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setRadiusGridlineStroke(Stroke)
     */
    public Stroke getRadiusGridlineStroke() {
        return this.radiusGridlineStroke;
    }
    
    /**
     * Sets the stroke for the grid lines plotted against the radius axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     * <p>
     * If you set this to <code>null</code>, no grid lines will be drawn.
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getRadiusGridlineStroke()
     */
    public void setRadiusGridlineStroke(Stroke stroke) {
        this.radiusGridlineStroke = stroke;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[91]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[92]++;
    }
    
    /**
     * Returns the paint for the grid lines (if any) plotted against the radius
     * axis.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setRadiusGridlinePaint(Paint)
     */
    public Paint getRadiusGridlinePaint() {
        return this.radiusGridlinePaint;
    }
    
    /**
     * Sets the paint for the grid lines plotted against the radius axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     * <p>
     * If you set this to <code>null</code>, no grid lines will be drawn.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getRadiusGridlinePaint()
     */
    public void setRadiusGridlinePaint(Paint paint) {
        this.radiusGridlinePaint = paint;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[93]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[94]++;
    }
    
    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).
     * <P>
     * This plot relies on a {@link PolarItemRenderer} to draw each 
     * item in the plot.  This allows the visual representation of the data to 
     * be changed easily.
     * <P>
     * The optional info argument collects information about the rendering of
     * the plot (dimensions, tooltip information etc).  Just pass in 
     * <code>null</code> if you do not need this information.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot (including axes and 
     *              labels) should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  ignored.
     * @param info  collects chart drawing information (<code>null</code> 
     *              permitted).
     */
    public void draw(Graphics2D g2, 
                     Rectangle2D area, 
                     Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[95]++;
       
        // if the plot area is too small, just return...
        boolean b1 = (area.getWidth() <= MINIMUM_WIDTH_TO_DRAW);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[96]++;
        boolean b2 = (area.getHeight() <= MINIMUM_HEIGHT_TO_DRAW);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[97]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((b1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((b2) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[37]++;
            return;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[38]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;
       
        // record the plot area...
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[39]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[99]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[40]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[100]++;
       
        // adjust the drawing area for the plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[101]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[102]++;
      
        Rectangle2D dataArea = area;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[103]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[41]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[104]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[42]++;}
       
        // draw the plot background and axes...
        drawBackground(g2, dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[105]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[106]++;
        double h = Math.min(dataArea.getWidth() / 2.0, 
                dataArea.getHeight() / 2.0) - MARGIN;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[107]++;
        Rectangle2D quadrant = new Rectangle2D.Double(dataArea.getCenterX(), 
                dataArea.getCenterY(), h, h);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[108]++;
        AxisState state = drawAxis(g2, area, quadrant);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[109]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[43]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[110]++;
            Shape originalClip = g2.getClip();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[111]++;
            Composite originalComposite = g2.getComposite();
          
            g2.clip(dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[112]++;
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, getForegroundAlpha()));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[113]++;
          
            drawGridlines(g2, dataArea, this.angleTicks, state.getTicks());
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[114]++;
          
            // draw...
            render(g2, dataArea, info);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[115]++;
          
            g2.setClip(originalClip);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[116]++;
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[117]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[44]++;}
        drawOutline(g2, dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[118]++;
        drawCornerTextItems(g2, dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[119]++;
    }
   
    /**
     * Draws the corner text items.
     * 
     * @param g2  the drawing surface.
     * @param area  the area.
     */
    protected void drawCornerTextItems(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[120]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.cornerTextItems.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[45]++;
            return;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[46]++;}
       
        g2.setColor(Color.black);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[121]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[122]++;
        double width = 0.0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[123]++;
        double height = 0.0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[124]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[1]++;


int CodeCoverConditionCoverageHelper_C24;
        for (Iterator it = this.cornerTextItems.iterator();(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[1]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[2]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[3]++;
}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[125]++;
            String msg = (String) it.next();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[126]++;
            FontMetrics fm = g2.getFontMetrics();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[127]++;
            Rectangle2D bounds = TextUtilities.getTextBounds(msg, g2, fm);
            width = Math.max(width, bounds.getWidth());
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[128]++;
            height += bounds.getHeight();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[129]++;
        }
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[130]++;
        
        double xadj = ANNOTATION_MARGIN * 2.0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[131]++;
        double yadj = ANNOTATION_MARGIN;
        width += xadj;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[132]++;
        height += yadj;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[133]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[134]++;
       
        double x = area.getMaxX() - width;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[135]++;
        double y = area.getMaxY() - height;
        g2.drawRect((int) x, (int) y, (int) width, (int) height);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[136]++;
        x += ANNOTATION_MARGIN;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[137]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[138]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[4]++;


int CodeCoverConditionCoverageHelper_C25;
        for (Iterator it = this.cornerTextItems.iterator();(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[4]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[5]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[6]++;
}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[139]++;
            String msg = (String) it.next();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[140]++;
            Rectangle2D bounds = TextUtilities.getTextBounds(msg, g2, 
                    g2.getFontMetrics());
            y += bounds.getHeight();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[141]++;
            g2.drawString(msg, (int) x, (int) y);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[142]++;
        }
    }
   
    /**
     * A utility method for drawing the axes.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param dataArea  the data area.
     * 
     * @return A map containing the axis states.
     */
    protected AxisState drawAxis(Graphics2D g2, Rectangle2D plotArea, 
                                 Rectangle2D dataArea) {
        return this.axis.draw(g2, dataArea.getMinY(), plotArea, dataArea, 
                RectangleEdge.TOP, null);
    }
   
    /**
     * Draws a representation of the data within the dataArea region, using the
     * current m_Renderer.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param info  an optional object for collection dimension 
     *              information (<code>null</code> permitted).
     */
    protected void render(Graphics2D g2,
                       Rectangle2D dataArea,
                       PlotRenderingInfo info) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[143]++;
int CodeCoverConditionCoverageHelper_C26;
      
        // now get the data and plot it (the visual representation will depend
        // on the m_Renderer that has been set)...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((DatasetUtilities.isEmptyOrNull(this.dataset)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[47]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[144]++;
            int seriesCount = this.dataset.getSeriesCount();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[145]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[7]++;


int CodeCoverConditionCoverageHelper_C27;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[7]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[8]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[9]++;
}
                this.renderer.drawSeries(g2, dataArea, info, this, 
                        this.dataset, series);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[146]++;
            }

        }
        else {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[48]++;
            drawNoDataMessage(g2, dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[147]++;
        }
    }
   
    /**
     * Draws the gridlines for the plot, if they are visible.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param angularTicks  the ticks for the angular axis.
     * @param radialTicks  the ticks for the radial axis.
     */
    protected void drawGridlines(Graphics2D g2, Rectangle2D dataArea, 
                                 List angularTicks, List radialTicks) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[148]++;
int CodeCoverConditionCoverageHelper_C28;

        // no renderer, no gridlines...
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.renderer == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[49]++;
            return;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[50]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[149]++;
int CodeCoverConditionCoverageHelper_C29;
       
        // draw the domain grid lines, if any...
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isAngleGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[51]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[150]++;
            Stroke gridStroke = getAngleGridlineStroke();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[151]++;
            Paint gridPaint = getAngleGridlinePaint();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[152]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((gridStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((gridPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[53]++;
                this.renderer.drawAngularGridLines(g2, this, angularTicks, 
                        dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[153]++;

            } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[54]++;}

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[52]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[154]++;
int CodeCoverConditionCoverageHelper_C31;
       
        // draw the radius grid lines, if any...
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isRadiusGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[55]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[155]++;
            Stroke gridStroke = getRadiusGridlineStroke();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[156]++;
            Paint gridPaint = getRadiusGridlinePaint();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[157]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((gridStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((gridPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[57]++;
                this.renderer.drawRadialGridLines(g2, this, this.axis, 
                        radialTicks, dataArea);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[158]++;

            } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[58]++;}

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[56]++;}      
    }
   
    /**
     * Zooms the axis ranges by the specified percentage about the anchor point.
     *
     * @param percent  the amount of the zoom.
     */
    public void zoom(double percent) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[159]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((percent > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[59]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[160]++;
            double radius = getMaxRadius();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[161]++;
            double scaledRadius = radius * percent;
            this.axis.setUpperBound(scaledRadius);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[162]++;
            getAxis().setAutoRange(false);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[163]++;

        } 
        else {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[60]++;
            getAxis().setAutoRange(true);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[164]++;
        }
    }
   
    /**
     * Returns the range for the specified axis.
     *
     * @param axis  the axis.
     *
     * @return The range.
     */
    public Range getDataRange(ValueAxis axis) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[165]++;
        Range result = null;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[166]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[61]++;
            result = Range.combine(result, 
                    DatasetUtilities.findRangeBounds(this.dataset));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[167]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[62]++;}
        return result;
    }
   
    /**
     * Receives notification of a change to the plot's m_Dataset.
     * <P>
     * The axis ranges are updated if necessary.
     *
     * @param event  information about the event (not used here).
     */
    public void datasetChanged(DatasetChangeEvent event) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[168]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.axis != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[63]++;
            this.axis.configure();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[169]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[64]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[170]++;
int CodeCoverConditionCoverageHelper_C36;
       
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[65]++;
            getParent().datasetChanged(event);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[171]++;

        }
        else {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[66]++;
            super.datasetChanged(event);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[172]++;
        }
    }
   
    /**
     * Notifies all registered listeners of a property change.
     * <P>
     * One source of property change events is the plot's m_Renderer.
     *
     * @param event  information about the property change.
     */
    public void rendererChanged(RendererChangeEvent event) {
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[173]++;
    }
   
    /**
     * Returns the number of series in the dataset for this plot.  If the 
     * dataset is <code>null</code>, the method returns 0.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[174]++;
        int result = 0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[175]++;
int CodeCoverConditionCoverageHelper_C37;
       
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[67]++;
            result = this.dataset.getSeriesCount();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[176]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[68]++;}
        return result;
    }
   
    /**
     * Returns the legend items for the plot.  Each legend item is generated by
     * the plot's m_Renderer, since the m_Renderer is responsible for the visual
     * representation of the data.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[177]++;
        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[178]++;
int CodeCoverConditionCoverageHelper_C38;
       
        // get the legend items for the main m_Dataset...
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[69]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[179]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[71]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[180]++;
                int seriesCount = this.dataset.getSeriesCount();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[181]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[10]++;


int CodeCoverConditionCoverageHelper_C40;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[10]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[11]--;
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.loops[12]++;
}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[182]++;
                    LegendItem item = this.renderer.getLegendItem(i);
                    result.add(item);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[183]++;
                }

            } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[72]++;}

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[70]++;}      
        return result;
    }
   
    /**
     * Tests this plot for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[184]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[73]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[74]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[185]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((obj instanceof PolarPlot) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[76]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[186]++;
        PolarPlot that = (PolarPlot) obj;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[187]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.axis, that.axis)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[78]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[188]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.renderer, that.renderer)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[80]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[189]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.angleGridlinesVisible != that.angleGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[82]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[190]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.angleLabelsVisible != that.angleLabelsVisible) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[83]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[84]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[191]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.angleLabelFont.equals(that.angleLabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[85]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[86]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[192]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.angleLabelPaint, that.angleLabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[87]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[88]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[193]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.angleGridlineStroke, 
                that.angleGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[90]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[194]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(
            this.angleGridlinePaint, that.angleGridlinePaint
        )) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[92]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[195]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.radiusGridlinesVisible != that.radiusGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[93]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[94]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[196]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.radiusGridlineStroke, 
                that.radiusGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[95]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[96]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[197]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.radiusGridlinePaint, 
                that.radiusGridlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[97]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[98]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[198]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.cornerTextItems.equals(that.cornerTextItems)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[99]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[100]++;}
        return super.equals(obj);
    }
   
    /**
     * Returns a clone of the plot.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  this can occur if some component of 
     *         the plot cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[199]++;
      
        PolarPlot clone = (PolarPlot) super.clone();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[200]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.axis != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[101]++;
            clone.axis = (ValueAxis) ObjectUtilities.clone(this.axis);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[201]++;
            clone.axis.setPlot(clone);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[202]++;
            clone.axis.addChangeListener(clone);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[203]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[102]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[204]++;
int CodeCoverConditionCoverageHelper_C56;
      
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((clone.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[103]++;
            clone.dataset.addChangeListener(clone);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[205]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[104]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[206]++;
int CodeCoverConditionCoverageHelper_C57;
      
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[105]++;
            clone.renderer 
                = (PolarItemRenderer) ObjectUtilities.clone(this.renderer);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[207]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[106]++;}
        
        clone.cornerTextItems = new ArrayList(this.cornerTextItems);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[208]++;
       
        return clone;
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
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[209]++;
        SerialUtilities.writeStroke(this.angleGridlineStroke, stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[210]++;
        SerialUtilities.writePaint(this.angleGridlinePaint, stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[211]++;
        SerialUtilities.writeStroke(this.radiusGridlineStroke, stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[212]++;
        SerialUtilities.writePaint(this.radiusGridlinePaint, stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[213]++;
        SerialUtilities.writePaint(this.angleLabelPaint, stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[214]++;
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
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[215]++;
        this.angleGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[216]++;
        this.angleGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[217]++;
        this.radiusGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[218]++;
        this.radiusGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[219]++;
        this.angleLabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[220]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[221]++;
int CodeCoverConditionCoverageHelper_C58;
      
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this.axis != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[107]++;
            this.axis.setPlot(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[222]++;
            this.axis.addChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[223]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[108]++;}
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[224]++;
int CodeCoverConditionCoverageHelper_C59;
      
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[109]++;
            this.dataset.addChangeListener(this);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[225]++;

        } else {
  CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[110]++;}
    }
   
    /**
     * This method is required by the {@link Zoomable} interface, but since
     * the plot does not have any domain axes, it does nothing.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point (in Java2D coordinates).
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo state, 
                               Point2D source) {
        // do nothing
    }
   
    /**
     * This method is required by the {@link Zoomable} interface, but since
     * the plot does not have any domain axes, it does nothing.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point (in Java2D coordinates).
     * @param useAnchor  use source point as zoom anchor?
     * 
     * @since 1.0.7
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo state, 
                               Point2D source, boolean useAnchor) {
        // do nothing
    }
   
    /**
     * This method is required by the {@link Zoomable} interface, but since
     * the plot does not have any domain axes, it does nothing.
     * 
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     * @param state  the plot state.
     * @param source  the source point (in Java2D coordinates).
     */
    public void zoomDomainAxes(double lowerPercent, double upperPercent, 
                               PlotRenderingInfo state, Point2D source) {
        // do nothing
    }

    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point (in Java2D coordinates).
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo state, 
                              Point2D source) {
        zoom(factor);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[226]++;
    }
   
    /**
     * Multiplies the range on the range axis by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point (in Java2D space).
     * @param useAnchor  use source point as zoom anchor?
     * 
     * @see #zoomDomainAxes(double, PlotRenderingInfo, Point2D, boolean)
     * 
     * @since 1.0.7
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo info,
                              Point2D source, boolean useAnchor) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[227]++;
int CodeCoverConditionCoverageHelper_C60;
                
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((useAnchor) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[111]++;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[228]++;
            // get the source coordinate - this plot has always a VERTICAL
            // orientation
            double sourceX = source.getX();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[229]++;
            double anchorX = this.axis.java2DToValue(sourceX, 
                    info.getDataArea(), RectangleEdge.BOTTOM);
            this.axis.resizeRange(factor, anchorX);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[230]++;

        }
        else {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.branches[112]++;
            this.axis.resizeRange(factor);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[231]++;
        }
        
    }
    
    /**
     * Zooms in on the range axes.
     * 
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     * @param state  the plot state.
     * @param source  the source point (in Java2D coordinates).
     */
    public void zoomRangeAxes(double lowerPercent, double upperPercent, 
                              PlotRenderingInfo state, Point2D source) {
        zoom((upperPercent + lowerPercent) / 2.0);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[232]++;
    }   

    /**
     * Returns <code>false</code> always.
     * 
     * @return <code>false</code> always.
     */
    public boolean isDomainZoomable() {
        return false;
    }
    
    /**
     * Returns <code>true</code> to indicate that the range axis is zoomable.
     * 
     * @return <code>true</code>.
     */
    public boolean isRangeZoomable() {
        return true;
    }
    
    /**
     * Returns the orientation of the plot.
     * 
     * @return The orientation.
     */
    public PlotOrientation getOrientation() {
        return PlotOrientation.HORIZONTAL;
    }

    /**
     * Returns the upper bound of the radius axis.
     * 
     * @return The upper bound.
     */
    public double getMaxRadius() {
        return this.axis.getUpperBound();
    }

    /**
     * Translates a (theta, radius) pair into Java2D coordinates.  If 
     * <code>radius</code> is less than the lower bound of the axis, then
     * this method returns the centre point.
     * 
     * @param angleDegrees  the angle in degrees.
     * @param radius  the radius.
     * @param dataArea  the data area.
     * 
     * @return A point in Java2D space.
     */   
    public Point translateValueThetaRadiusToJava2D(double angleDegrees, 
                                                   double radius,
                                                   Rectangle2D dataArea) {
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[233]++;
       
        double radians = Math.toRadians(angleDegrees - 90.0);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[234]++;
      
        double minx = dataArea.getMinX() + MARGIN;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[235]++;
        double maxx = dataArea.getMaxX() - MARGIN;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[236]++;
        double miny = dataArea.getMinY() + MARGIN;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[237]++;
        double maxy = dataArea.getMaxY() - MARGIN;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[238]++;
      
        double lengthX = maxx - minx;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[239]++;
        double lengthY = maxy - miny;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[240]++;
        double length = Math.min(lengthX, lengthY);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[241]++;
      
        double midX = minx + lengthX / 2.0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[242]++;
        double midY = miny + lengthY / 2.0;
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[243]++;
      
        double axisMin = this.axis.getLowerBound();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[244]++;
        double axisMax =  getMaxRadius();
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[245]++;
        double adjustedRadius = Math.max(radius, axisMin);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[246]++;

        double xv = length / 2.0 * Math.cos(radians);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[247]++;
        double yv = length / 2.0 * Math.sin(radians);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[248]++;

        float x = (float) (midX + (xv * (adjustedRadius - axisMin) 
                / (axisMax - axisMin)));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[249]++;
        float y = (float) (midY + (yv * (adjustedRadius - axisMin) 
                / (axisMax - axisMin)));
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[250]++;
      
        int ix = Math.round(x);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[251]++;
        int iy = Math.round(y);
CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl.statements[252]++;
      
        Point p = new Point(ix, iy);
        return p;
        
    }
    
}

class CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl ());
  }
    public static long[] statements = new long[253];
    public static long[] branches = new long[113];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[61];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.PolarPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 60; i++) {
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

  public CodeCoverCoverageCounter$3e1kuciowxwhzwyrvsqezl () {
    super("org.jfree.chart.plot.PolarPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 252; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 112; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 60; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.PolarPlot.java");
      for (int i = 1; i <= 252; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 112; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 60; i++) {
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

