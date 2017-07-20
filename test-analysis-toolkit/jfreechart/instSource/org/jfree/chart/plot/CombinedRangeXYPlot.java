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
 * CombinedRangeXYPlot.java
 * ------------------------
 * (C) Copyright 2001-2007, by Bill Kelemen and Contributors.
 *
 * Original Author:  Bill Kelemen;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Anthony Boulestreau;
 *                   David Basten;
 *                   Kevin Frechette (for ISTI);
 *                   Arnaud Lelievre;
 *                   Nicolas Brodu;
 *                   Petr Kubanek (bug 1606205);
 *
 * Changes:
 * --------
 * 06-Dec-2001 : Version 1 (BK);
 * 12-Dec-2001 : Removed unnecessary 'throws' clause from constructor (DG);
 * 18-Dec-2001 : Added plotArea attribute and get/set methods (BK);
 * 22-Dec-2001 : Fixed bug in chartChanged with multiple combinations of 
 *               CombinedPlots (BK);
 * 08-Jan-2002 : Moved to new package com.jrefinery.chart.combination (DG);
 * 25-Feb-2002 : Updated import statements (DG);
 * 28-Feb-2002 : Readded "this.plotArea = plotArea" that was deleted from 
 *               draw() method (BK);
 * 26-Mar-2002 : Added an empty zoom method (this method needs to be written 
 *               so that combined plots will support zooming (DG);
 * 29-Mar-2002 : Changed the method createCombinedAxis adding the creation of 
 *               OverlaidSymbolicAxis and CombinedSymbolicAxis(AB);
 * 23-Apr-2002 : Renamed CombinedPlot-->MultiXYPlot, and simplified the 
 *               structure (DG);
 * 23-May-2002 : Renamed (again) MultiXYPlot-->CombinedXYPlot (DG);
 * 19-Jun-2002 : Added get/setGap() methods suggested by David Basten (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 16-Jul-2002 : Draws shared axis after subplots (to fix missing gridlines),
 *               added overrides of 'setSeriesPaint()' and 'setXYItemRenderer()'
 *               that pass changes down to subplots (KF);
 * 09-Oct-2002 : Added add(XYPlot) method (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 16-May-2003 : Renamed CombinedXYPlot --> CombinedRangeXYPlot (DG);
 * 26-Jun-2003 : Fixed bug 755547 (DG);
 * 16-Jul-2003 : Removed getSubPlots() method (duplicate of getSubplots()) (DG);
 * 08-Aug-2003 : Adjusted totalWeight in remove() method (DG);
 * 21-Aug-2003 : Implemented Cloneable (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL); 
 * 11-Sep-2003 : Fix cloning support (subplots) (NB);
 * 15-Sep-2003 : Fixed error in cloning (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 17-Sep-2003 : Updated handling of 'clicks' (DG);
 * 12-Nov-2004 : Implements the new Zoomable interface (DG);
 * 25-Nov-2004 : Small update to clone() implementation (DG);
 * 21-Feb-2005 : The getLegendItems() method now returns the fixed legend
 *               items if set (DG);
 * 05-May-2005 : Removed unused draw() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Sep-2006 : Updated API docs (DG);
 * 06-Feb-2007 : Fixed bug 1606205, draw shared axis after subplots (DG);
 * 23-Mar-2007 : Reverted previous patch (DG);
 * 17-Apr-2007 : Added null argument checks to findSubplot() (DG);
 * 18-Jul-2007 : Fixed bug in removeSubplot (DG);
 * 
 */

package org.jfree.chart.plot;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * An extension of {@link XYPlot} that contains multiple subplots that share a 
 * common range axis.
 */
public class CombinedRangeXYPlot extends XYPlot 
                                 implements Zoomable,
                                            Cloneable, PublicCloneable, 
                                            Serializable,
                                            PlotChangeListener {
  static {
    CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5177814085082031168L;
  static {
    CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[1]++;
  }
    
    /** Storage for the subplot references. */
    private List subplots;

    /** Total weight of all charts. */
    private int totalWeight = 0;
  {
    CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[2]++;
  }

    /** The gap between subplots. */
    private double gap = 5.0;
  {
    CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[3]++;
  }

    /** Temporary storage for the subplot areas. */
    private transient Rectangle2D[] subplotAreas;

    /**
     * Default constructor.
     */
    public CombinedRangeXYPlot() {
        this(new NumberAxis());
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[4]++;
    }
    
    /**
     * Creates a new plot.
     *
     * @param rangeAxis  the shared axis.
     */
    public CombinedRangeXYPlot(ValueAxis rangeAxis) {

        super(null, // no data in the parent plot
              null,
              rangeAxis,
              null);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[5]++;

        this.subplots = new java.util.ArrayList();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[6]++;

    }

    /**
     * Returns a string describing the type of plot.
     *
     * @return The type of plot.
     */
    public String getPlotType() {
        return localizationResources.getString("Combined_Range_XYPlot");
    }

    /**
     * Returns the space between subplots.
     *
     * @return The gap
     */
    public double getGap() {
        return this.gap;
    }

    /**
     * Sets the amount of space between subplots.
     *
     * @param gap  the gap between subplots
     */
    public void setGap(double gap) {
        this.gap = gap;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[7]++;
    }

    /**
     * Adds a subplot, with a default 'weight' of 1.
     * <br><br>
     * You must ensure that the subplot has a non-null domain axis.  The range
     * axis for the subplot will be set to <code>null</code>.  
     *
     * @param subplot  the subplot.
     */
    public void add(XYPlot subplot) {
        add(subplot, 1);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[8]++;
    }

    /**
     * Adds a subplot with a particular weight (greater than or equal to one).  
     * The weight determines how much space is allocated to the subplot 
     * relative to all the other subplots.
     * <br><br>
     * You must ensure that the subplot has a non-null domain axis.  The range
     * axis for the subplot will be set to <code>null</code>.  
     *
     * @param subplot  the subplot.
     * @param weight  the weight (must be 1 or greater).
     */
    public void add(XYPlot subplot, int weight) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;

        // verify valid weight
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((weight <= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[1]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[10]++;
            String msg = "The 'weight' must be positive.";
            throw new IllegalArgumentException(msg);

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[2]++;}

        // store the plot and its weight
        subplot.setParent(this);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[11]++;
        subplot.setWeight(weight);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[12]++;
        subplot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[13]++;
        subplot.setRangeAxis(null);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[14]++;
        subplot.addChangeListener(this);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[15]++;
        this.subplots.add(subplot);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[16]++;

        // keep track of total weights
        this.totalWeight += weight;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[17]++;
        configureRangeAxes();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[18]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[19]++;

    }

    /**
     * Removes a subplot from the combined chart.
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     */
    public void remove(XYPlot subplot) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((subplot == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[3]++;
            throw new IllegalArgumentException(" Null 'subplot' argument.");
   
        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[4]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[21]++;
        int position = -1;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[22]++;
        int size = this.subplots.size();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[23]++;
        int i = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((position == -1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[1]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[2]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[3]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.subplots.get(i) == subplot) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[5]++;
                position = i;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[26]++;

            } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[6]++;}
            i++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[27]++;
        }
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((position != -1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[7]++;
            this.subplots.remove(position);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[29]++;
            subplot.setParent(null);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[30]++;
            subplot.removeChangeListener(this);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[31]++;
            this.totalWeight -= subplot.getWeight();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[32]++;
            configureRangeAxes();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[33]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[34]++;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[8]++;}
    }

    /**
     * Returns a list of the subplots.
     *
     * @return The list (unmodifiable).
     */
    public List getSubplots() {
        return Collections.unmodifiableList(this.subplots);
    }

    /**
     * Calculates the space required for the axes.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * 
     * @return The space required for the axes.
     */
    protected AxisSpace calculateAxisSpace(Graphics2D g2, 
                                           Rectangle2D plotArea) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[35]++;
        
        AxisSpace space = new AxisSpace();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[36]++;
        PlotOrientation orientation = getOrientation();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[37]++;
        
        // work out the space required by the domain axis...
        AxisSpace fixed = getFixedRangeAxisSpace();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((fixed != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[9]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[11]++;
                space.setLeft(fixed.getLeft());
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[40]++;
                space.setRight(fixed.getRight());
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[41]++;

            }
            else {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[12]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[42]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[13]++;
                space.setTop(fixed.getTop());
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[43]++;
                space.setBottom(fixed.getBottom());
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[44]++;
                
            } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[14]++;}
}

        }
        else {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[10]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[45]++;
            ValueAxis valueAxis = getRangeAxis();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[46]++;
            RectangleEdge valueEdge = Plot.resolveRangeAxisLocation(
                getRangeAxisLocation(), orientation
            );
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((valueAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[15]++;
                space = valueAxis.reserveSpace(g2, this, plotArea, valueEdge, 
                        space);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[48]++;

            } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[16]++;}
        }
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[49]++;
        
        Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[50]++;
        // work out the maximum height or width of the non-shared axes...
        int n = this.subplots.size();

        // calculate plotAreas of all sub-plots, maximum vertical/horizontal 
        // axis width/height
        this.subplotAreas = new Rectangle2D[n];
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[51]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[52]++;
        double x = adjustedPlotArea.getX();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[53]++;
        double y = adjustedPlotArea.getY();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[54]++;
        double usableSize = 0.0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[17]++;
            usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[56]++;

        }
        else {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[18]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[57]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[19]++;
            usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[58]++;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[20]++;}
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[59]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[4]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[5]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[6]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[60]++;
            XYPlot plot = (XYPlot) this.subplots.get(i);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;

            // calculate sub-plot area
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[21]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[62]++;
                double w = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, 
                        adjustedPlotArea.getHeight());
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[63]++;
                x = x + w + this.gap;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[64]++;

            }
            else {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[22]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[65]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[23]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[66]++;
                double h = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, 
                        adjustedPlotArea.getWidth(), h);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[67]++;
                y = y + h + this.gap;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[68]++;

            } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[24]++;}
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[69]++;

            AxisSpace subSpace = plot.calculateDomainAxisSpace(g2, 
                    this.subplotAreas[i], null);
            space.ensureAtLeast(subSpace);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[70]++;

        }

        return space;
    }
    
    /**
     * Draws the plot within the specified area on a graphics device.
     * 
     * @param g2  the graphics device.
     * @param area  the plot area (in Java2D space).
     * @param anchor  an anchor point in Java2D space (<code>null</code> 
     *                permitted).
     * @param parentState  the state from the parent plot, if there is one 
     *                     (<code>null</code> permitted).
     * @param info  collects chart drawing information (<code>null</code> 
     *              permitted).
     */
    public void draw(Graphics2D g2,
                     Rectangle2D area,
                     Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[71]++;
int CodeCoverConditionCoverageHelper_C15;
        
        // set up info collection...
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[25]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[72]++;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[26]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[73]++;

        // adjust the drawing area for plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[74]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[75]++;

        AxisSpace space = calculateAxisSpace(g2, area);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[76]++;
        Rectangle2D dataArea = space.shrink(area, null);
        //this.axisOffset.trim(dataArea);

        // set the width and height of non-shared axis of all sub-plots
        setFixedDomainAxisSpaceForSubplots(space);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[77]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[78]++;

        // draw the shared axis
        ValueAxis axis = getRangeAxis();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[79]++;
        RectangleEdge edge = getRangeAxisEdge();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[80]++;
        double cursor = RectangleEdge.coordinate(dataArea, edge);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[81]++;
        AxisState axisState = axis.draw(g2, cursor, area, dataArea, edge, info);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[82]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((parentState == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[27]++;
            parentState = new PlotState();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[83]++;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[28]++;}
        parentState.getSharedAxisStates().put(axis, axisState);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[84]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[85]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[7]++;


int CodeCoverConditionCoverageHelper_C17;
        
        // draw all the charts
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[7]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[8]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[9]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[86]++;
            XYPlot plot = (XYPlot) this.subplots.get(i);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[87]++;
            PlotRenderingInfo subplotInfo = null;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[88]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[29]++;
                subplotInfo = new PlotRenderingInfo(info.getOwner());
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[89]++;
                info.addSubplotInfo(subplotInfo);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[90]++;

            } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[30]++;}
            plot.draw(g2, this.subplotAreas[i], anchor, parentState, 
                    subplotInfo);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[91]++;
        }
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[92]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[31]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[93]++;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[32]++;}

    }

    /**
     * Returns a collection of legend items for the plot.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[94]++;
        LegendItemCollection result = getFixedLegendItems();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[95]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[33]++;
            result = new LegendItemCollection();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[96]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[97]++;
int CodeCoverConditionCoverageHelper_C21;
        
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[35]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[98]++;
                Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[99]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[10]++;


int CodeCoverConditionCoverageHelper_C22;
                while ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[10]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[11]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[12]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[100]++;
                    XYPlot plot = (XYPlot) iterator.next();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[101]++;
                    LegendItemCollection more = plot.getLegendItems();
                    result.addAll(more);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[102]++;
                }

            } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[34]++;}
        return result;
    }

    /**
     * Multiplies the range on the domain axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info (<code>null</code> not permitted).
     * @param source  the source point (<code>null</code> not permitted).
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo info, 
                               Point2D source) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[103]++;
        // delegate 'info' and 'source' argument checks...
        XYPlot subplot = findSubplot(info, source);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[104]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((subplot != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[37]++;
            subplot.zoomDomainAxes(factor, info, source);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[105]++;

        }
        else {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[38]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[106]++;
            // if the source point doesn't fall within a subplot, we do the
            // zoom on all subplots...
            Iterator iterator = getSubplots().iterator();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[107]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[13]++;


int CodeCoverConditionCoverageHelper_C24;
            while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[13]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[14]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[15]++;
}
                subplot = (XYPlot) iterator.next();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[108]++;
                subplot.zoomDomainAxes(factor, info, source);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[109]++;
            }
        }
    }

    /**
     * Zooms in on the domain axes.
     *
     * @param lowerPercent  the lower bound.
     * @param upperPercent  the upper bound.
     * @param info  the plot rendering info (<code>null</code> not permitted).
     * @param source  the source point (<code>null</code> not permitted).
     */
    public void zoomDomainAxes(double lowerPercent, double upperPercent, 
                               PlotRenderingInfo info, Point2D source) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[110]++;
        // delegate 'info' and 'source' argument checks...
        XYPlot subplot = findSubplot(info, source);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[111]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((subplot != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[39]++;
            subplot.zoomDomainAxes(lowerPercent, upperPercent, info, source);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[112]++;

        }
        else {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[40]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[113]++;
            // if the source point doesn't fall within a subplot, we do the
            // zoom on all subplots...
            Iterator iterator = getSubplots().iterator();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[114]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[16]++;


int CodeCoverConditionCoverageHelper_C26;
            while ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[16]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[17]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[18]++;
}
                subplot = (XYPlot) iterator.next();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[115]++;
                subplot.zoomDomainAxes(lowerPercent, upperPercent, info, 
                        source);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[116]++;
            }
        }
    }

    /**
     * Returns the subplot (if any) that contains the (x, y) point (specified 
     * in Java2D space).
     * 
     * @param info  the chart rendering info (<code>null</code> not permitted).
     * @param source  the source point (<code>null</code> not permitted).
     * 
     * @return A subplot (possibly <code>null</code>).
     */
    public XYPlot findSubplot(PlotRenderingInfo info, Point2D source) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[117]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[41]++;
            throw new IllegalArgumentException("Null 'info' argument.");

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[42]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[118]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[43]++;
            throw new IllegalArgumentException("Null 'source' argument.");

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[44]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[119]++;
        XYPlot result = null;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[120]++;
        int subplotIndex = info.getSubplotIndex(source);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[121]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((subplotIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[45]++;
            result =  (XYPlot) this.subplots.get(subplotIndex);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[122]++;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[46]++;}
        return result;
    }

    /**
     * Sets the item renderer FOR ALL SUBPLOTS.  Registered listeners are 
     * notified that the plot has been modified.
     * <P>
     * Note: usually you will want to set the renderer independently for each 
     * subplot, which is NOT what this method does.
     *
     * @param renderer the new renderer.
     */
    public void setRenderer(XYItemRenderer renderer) {

        super.setRenderer(renderer);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[123]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[124]++;  // not strictly necessary, since the 
                                      // renderer set for the
                                      // parent plot is not used

        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[125]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[19]++;


int CodeCoverConditionCoverageHelper_C30;
        while ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[19]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[20]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[21]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[126]++;
            XYPlot plot = (XYPlot) iterator.next();
            plot.setRenderer(renderer);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[127]++;
        }

    }

    /**
     * Sets the orientation for the plot (and all its subplots).
     * 
     * @param orientation  the orientation.
     */
    public void setOrientation(PlotOrientation orientation) {

        super.setOrientation(orientation);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[128]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[129]++;

        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[130]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[22]++;


int CodeCoverConditionCoverageHelper_C31;
        while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[22]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[23]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[24]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[131]++;
            XYPlot plot = (XYPlot) iterator.next();
            plot.setOrientation(orientation);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[132]++;
        }

    }

    /**
     * Returns the range for the axis.  This is the combined range of all the 
     * subplots.
     *
     * @param axis  the axis.
     *
     * @return The range.
     */
    public Range getDataRange(ValueAxis axis) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[133]++;

        Range result = null;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[134]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[47]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[135]++;
            Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[136]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[25]++;


int CodeCoverConditionCoverageHelper_C33;
            while ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[25]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[26]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[27]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[137]++;
                XYPlot subplot = (XYPlot) iterator.next();
                result = Range.combine(result, subplot.getDataRange(axis));
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[138]++;
            }

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[48]++;}
        return result;

    }

    /**
     * Sets the space (width or height, depending on the orientation of the 
     * plot) for the domain axis of each subplot.
     *
     * @param space  the space.
     */
    protected void setFixedDomainAxisSpaceForSubplots(AxisSpace space) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[139]++;

        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[140]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[28]++;


int CodeCoverConditionCoverageHelper_C34;
        while ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[28]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[29]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[30]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[141]++;
            XYPlot plot = (XYPlot) iterator.next();
            plot.setFixedDomainAxisSpace(space);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[142]++;
        }

    }

    /**
     * Handles a 'click' on the plot by updating the anchor values...
     *
     * @param x  x-coordinate, where the click occured.
     * @param y  y-coordinate, where the click occured.
     * @param info  object containing information about the plot dimensions.
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[143]++;

        Rectangle2D dataArea = info.getDataArea();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[144]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((dataArea.contains(x, y)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[49]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[145]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[31]++;


int CodeCoverConditionCoverageHelper_C36;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[31]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[32]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[33]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[146]++;
                XYPlot subplot = (XYPlot) this.subplots.get(i);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[147]++;
                PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
                subplot.handleClick(x, y, subplotInfo);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[148]++;
            }

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[50]++;}

    }

    /**
     * Receives a {@link PlotChangeEvent} and responds by notifying all 
     * listeners.
     * 
     * @param event  the event.
     */
    public void plotChanged(PlotChangeEvent event) {
        notifyListeners(event);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[149]++;
    }

    /**
     * Tests this plot for equality with another object.
     *
     * @param obj  the other object.
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[150]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[51]++;
            return true;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[52]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[151]++;
int CodeCoverConditionCoverageHelper_C38;

        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((obj instanceof CombinedRangeXYPlot) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[54]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[152]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[56]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[153]++;
        CombinedRangeXYPlot that = (CombinedRangeXYPlot) obj;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[154]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.subplots, that.subplots)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[58]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[155]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.totalWeight != that.totalWeight) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[60]++;}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[156]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.gap != that.gap) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[62]++;}
        return true;
    }
    
    /**
     * Returns a clone of the plot.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[157]++;
        
        CombinedRangeXYPlot result = (CombinedRangeXYPlot) super.clone(); 
        result.subplots = (List) ObjectUtilities.deepClone(this.subplots);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[158]++;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[159]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[34]++;


int CodeCoverConditionCoverageHelper_C43;
        for (Iterator it = result.subplots.iterator();(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false);) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[34]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[35]--;
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.loops[36]++;
}
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[160]++;
            Plot child = (Plot) it.next();
            child.setParent(result);
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[161]++;
        }
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[162]++;
        
        // after setting up all the subplots, the shared range axis may need 
        // reconfiguring
        ValueAxis rangeAxis = result.getRangeAxis();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[163]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[63]++;
            rangeAxis.configure();
CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.statements[164]++;

        } else {
  CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p.branches[64]++;}
        
        return result;
    }

}

class CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p ());
  }
    public static long[] statements = new long[165];
    public static long[] branches = new long[65];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[45];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CombinedRangeXYPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 44; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$fjiv4ckv722g7q8mv0l8volbxb5kcd0ygbr6p () {
    super("org.jfree.chart.plot.CombinedRangeXYPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 164; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 64; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.CombinedRangeXYPlot.java");
      for (int i = 1; i <= 164; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 64; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 12; i++) {
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

