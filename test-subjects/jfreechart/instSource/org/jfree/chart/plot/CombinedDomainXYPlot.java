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
 * -------------------------
 * CombinedDomainXYPlot.java
 * -------------------------
 * (C) Copyright 2001-2007, by Bill Kelemen and Contributors.
 *
 * Original Author:  Bill Kelemen;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Anthony Boulestreau;
 *                   David Basten;
 *                   Kevin Frechette (for ISTI);
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
 * 26-Mar-2002 : Added an empty zoom method (this method needs to be written so
 *               that combined plots will support zooming (DG);
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
 * 16-May-2003 : Renamed CombinedXYPlot --> CombinedDomainXYPlot (DG);
 * 04-Aug-2003 : Removed leftover code that was causing domain axis drawing 
 *               problem (DG);
 * 08-Aug-2003 : Adjusted totalWeight in remove() method (DG);
 * 21-Aug-2003 : Implemented Cloneable (DG);
 * 11-Sep-2003 : Fix cloning support (subplots) (NB);
 * 15-Sep-2003 : Fixed error in cloning (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 17-Sep-2003 : Updated handling of 'clicks' (DG);
 * 12-Nov-2004 : Implemented the new Zoomable interface (DG);
 * 25-Nov-2004 : Small update to clone() implementation (DG);
 * 21-Feb-2005 : The getLegendItems() method now returns the fixed legend
 *               items if set (DG);
 * 05-May-2005 : Removed unused draw() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 23-Aug-2006 : Override setFixedRangeAxisSpace() to update subplots (DG);
 * 06-Feb-2007 : Fixed bug 1606205, draw shared axis after subplots (DG);
 * 23-Mar-2007 : Reverted previous patch (bug fix 1606205) (DG);
 * 17-Apr-2007 : Added null argument checks to findSubplot() (DG);
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
 * common domain axis.
 */
public class CombinedDomainXYPlot extends XYPlot 
                                  implements Cloneable, PublicCloneable, 
                                             Serializable,
                                             PlotChangeListener {
  static {
    CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7765545541261907383L;
  static {
    CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[1]++;
  }
    
    /** Storage for the subplot references. */
    private List subplots;

    /** Total weight of all charts. */
    private int totalWeight = 0;
  {
    CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[2]++;
  }

    /** The gap between subplots. */
    private double gap = 5.0;
  {
    CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[3]++;
  }

    /** Temporary storage for the subplot areas. */
    private transient Rectangle2D[] subplotAreas;
    // TODO:  the subplot areas needs to be moved out of the plot into the plot
    //        state
    
    /**
     * Default constructor.
     */
    public CombinedDomainXYPlot() {
        this(new NumberAxis());
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[4]++;      
    }
    
    /**
     * Creates a new combined plot that shares a domain axis among multiple 
     * subplots.
     *
     * @param domainAxis  the shared axis.
     */
    public CombinedDomainXYPlot(ValueAxis domainAxis) {

        super(
            null,        // no data in the parent plot
            domainAxis,
            null,        // no range axis
            null         // no rendereer
        );
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[5]++;  

        this.subplots = new java.util.ArrayList();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[6]++;

    }

    /**
     * Returns a string describing the type of plot.
     *
     * @return The type of plot.
     */
    public String getPlotType() {
        return "Combined_Domain_XYPlot";
    }

    /**
     * Sets the orientation for the plot (also changes the orientation for all 
     * the subplots to match).
     * 
     * @param orientation  the orientation (<code>null</code> not allowed).
     */
    public void setOrientation(PlotOrientation orientation) {

        super.setOrientation(orientation);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[7]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[8]++;
        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[1]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[2]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[3]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[10]++;
            XYPlot plot = (XYPlot) iterator.next();
            plot.setOrientation(orientation);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[11]++;
        }

    }

    /**
     * Returns the range for the specified axis.  This is the combined range 
     * of all the subplots.
     *
     * @param axis  the axis.
     *
     * @return The range (possibly <code>null</code>).
     */
    public Range getDataRange(ValueAxis axis) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[12]++;

        Range result = null;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[1]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[14]++;
            Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
            while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[4]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[5]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[6]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[16]++;
                XYPlot subplot = (XYPlot) iterator.next();
                result = Range.combine(result, subplot.getDataRange(axis));
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[17]++;
            }

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[2]++;}
        return result;

    }

    /**
     * Returns the gap between subplots, measured in Java2D units.
     *
     * @return The gap (in Java2D units).
     */
    public double getGap() {
        return this.gap;
    }

    /**
     * Sets the amount of space between subplots and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param gap  the gap between subplots (in Java2D units).
     */
    public void setGap(double gap) {
        this.gap = gap;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[18]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[19]++;
    }

    /**
     * Adds a subplot (with a default 'weight' of 1) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * <P>
     * The domain axis for the subplot will be set to <code>null</code>.  You
     * must ensure that the subplot has a non-null range axis.
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     */
    public void add(XYPlot subplot) {
        // defer argument checking
        add(subplot, 1);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[20]++;
    }

    /**
     * Adds a subplot with the specified weight and sends a 
     * {@link PlotChangeEvent} to all registered listeners.  The weight 
     * determines how much space is allocated to the subplot relative to all 
     * the other subplots.
     * <P>
     * The domain axis for the subplot will be set to <code>null</code>.  You
     * must ensure that the subplot has a non-null range axis.
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     * @param weight  the weight (must be >= 1).
     */
    public void add(XYPlot subplot, int weight) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((subplot == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[3]++;
            throw new IllegalArgumentException("Null 'subplot' argument.");

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[4]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((weight <= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[5]++;
            throw new IllegalArgumentException("Require weight >= 1.");

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[6]++;}

        // store the plot and its weight
        subplot.setParent(this);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[23]++;
        subplot.setWeight(weight);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[24]++;
        subplot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0), false);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[25]++;
        subplot.setDomainAxis(null);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[26]++;
        subplot.addChangeListener(this);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[27]++;
        this.subplots.add(subplot);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[28]++;

        // keep track of total weights
        this.totalWeight += weight;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[29]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[30]++;

        ValueAxis axis = getDomainAxis();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[7]++;
            axis.configure();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[32]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[8]++;}
        
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[33]++;

    }

    /**
     * Removes a subplot from the combined chart and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     */
    public void remove(XYPlot subplot) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((subplot == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[9]++;
            throw new IllegalArgumentException(" Null 'subplot' argument.");
   
        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[10]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[35]++;
        int position = -1;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[36]++;
        int size = this.subplots.size();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[37]++;
        int i = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((position == -1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[7]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[8]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[9]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.subplots.get(i) == subplot) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[11]++;
                position = i;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[40]++;

            } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[12]++;}
            i++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[41]++;
        }
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((position != -1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[13]++;
            this.subplots.remove(position);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[43]++;
            subplot.setParent(null);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[44]++;
            subplot.removeChangeListener(this);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[45]++;
            this.totalWeight -= subplot.getWeight();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[46]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[47]++;

            ValueAxis domain = getDomainAxis();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((domain != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[15]++;
                domain.configure();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[49]++;

            } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[16]++;}
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[50]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[14]++;}
    }

    /**
     * Returns the list of subplots.
     *
     * @return An unmodifiable list of subplots.
     */
    public List getSubplots() {
        return Collections.unmodifiableList(this.subplots);
    }

    /**
     * Calculates the axis space required.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * 
     * @return The space.
     */
    protected AxisSpace calculateAxisSpace(Graphics2D g2, 
                                           Rectangle2D plotArea) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[51]++;
        
        AxisSpace space = new AxisSpace();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[52]++;
        PlotOrientation orientation = getOrientation();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[53]++;
        
        // work out the space required by the domain axis...
        AxisSpace fixed = getFixedDomainAxisSpace();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((fixed != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[17]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[19]++;
                space.setLeft(fixed.getLeft());
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[56]++;
                space.setRight(fixed.getRight());
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[57]++;

            }
            else {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[20]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[58]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[21]++;
                space.setTop(fixed.getTop());
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[59]++;
                space.setBottom(fixed.getBottom());
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[60]++;
                
            } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[22]++;}
}

        }
        else {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[18]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[61]++;
            ValueAxis xAxis = getDomainAxis();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[62]++;
            RectangleEdge xEdge = Plot.resolveDomainAxisLocation(
                    getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((xAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[23]++;
                space = xAxis.reserveSpace(g2, this, plotArea, xEdge, space);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[64]++;

            } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[24]++;}
        }
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[65]++;
        
        Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[66]++;
        
        // work out the maximum height or width of the non-shared axes...
        int n = this.subplots.size();
        this.subplotAreas = new Rectangle2D[n];
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[67]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[68]++;
        double x = adjustedPlotArea.getX();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[69]++;
        double y = adjustedPlotArea.getY();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[70]++;
        double usableSize = 0.0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[71]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[25]++;
            usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[72]++;

        }
        else {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[26]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[73]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[27]++;
            usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[74]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[28]++;}
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[75]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[10]++;


int CodeCoverConditionCoverageHelper_C18;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[10]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[11]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[12]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[76]++;
            XYPlot plot = (XYPlot) this.subplots.get(i);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[77]++;
int CodeCoverConditionCoverageHelper_C19;

            // calculate sub-plot area
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[29]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[78]++;
                double w = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, 
                        adjustedPlotArea.getHeight());
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[79]++;
                x = x + w + this.gap;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[80]++;

            }
            else {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[30]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[81]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[31]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[82]++;
                double h = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, 
                        adjustedPlotArea.getWidth(), h);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[83]++;
                y = y + h + this.gap;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[84]++;

            } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[32]++;}
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[85]++;

            AxisSpace subSpace = plot.calculateRangeAxisSpace(g2, 
                    this.subplotAreas[i], null);
            space.ensureAtLeast(subSpace);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[86]++;

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
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;
        
        // set up info collection...
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[33]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[88]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[34]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[89]++;

        // adjust the drawing area for plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[90]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[91]++;

        AxisSpace space = calculateAxisSpace(g2, area);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[92]++;
        Rectangle2D dataArea = space.shrink(area, null);

        // set the width and height of non-shared axis of all sub-plots
        setFixedRangeAxisSpaceForSubplots(space);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[93]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[94]++;

        // draw the shared axis
        ValueAxis axis = getDomainAxis();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[95]++;
        RectangleEdge edge = getDomainAxisEdge();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[96]++;
        double cursor = RectangleEdge.coordinate(dataArea, edge);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[97]++;
        AxisState axisState = axis.draw(g2, cursor, area, dataArea, edge, info);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[98]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((parentState == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[35]++;
            parentState = new PlotState();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[99]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[36]++;}
        parentState.getSharedAxisStates().put(axis, axisState);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[100]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[101]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[13]++;


int CodeCoverConditionCoverageHelper_C23;
        
        // draw all the subplots
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[13]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[14]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[15]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[102]++;
            XYPlot plot = (XYPlot) this.subplots.get(i);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[103]++;
            PlotRenderingInfo subplotInfo = null;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[104]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[37]++;
                subplotInfo = new PlotRenderingInfo(info.getOwner());
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[105]++;
                info.addSubplotInfo(subplotInfo);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[106]++;

            } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[38]++;}
            plot.draw(g2, this.subplotAreas[i], anchor, parentState, 
                    subplotInfo);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[107]++;
        }
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[108]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[39]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[109]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[40]++;}
        
    }

    /**
     * Returns a collection of legend items for the plot.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[110]++;        
        LegendItemCollection result = getFixedLegendItems();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[111]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[41]++;
            result = new LegendItemCollection();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[112]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[113]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[43]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[114]++;
                Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[115]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[16]++;


int CodeCoverConditionCoverageHelper_C28;
                while ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[16]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[17]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[18]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[116]++;
                    XYPlot plot = (XYPlot) iterator.next();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[117]++;
                    LegendItemCollection more = plot.getLegendItems();
                    result.addAll(more);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[118]++;
                }

            } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[42]++;}
        return result;
    }

    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info (<code>null</code> not permitted).
     * @param source  the source point (<code>null</code> not permitted).
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo info, 
                              Point2D source) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[119]++;
        // delegate 'info' and 'source' argument checks...
        XYPlot subplot = findSubplot(info, source);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[120]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((subplot != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[45]++;
            subplot.zoomRangeAxes(factor, info, source);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[121]++;

        }
        else {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[46]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[122]++;
            // if the source point doesn't fall within a subplot, we do the
            // zoom on all subplots...
            Iterator iterator = getSubplots().iterator();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[123]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[19]++;


int CodeCoverConditionCoverageHelper_C30;
            while ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[19]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[20]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[21]++;
}
                subplot = (XYPlot) iterator.next();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[124]++;
                subplot.zoomRangeAxes(factor, info, source);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[125]++;
            }
        }
    }

    /**
     * Zooms in on the range axes.
     *
     * @param lowerPercent  the lower bound.
     * @param upperPercent  the upper bound.
     * @param info  the plot rendering info (<code>null</code> not permitted).
     * @param source  the source point (<code>null</code> not permitted).
     */
    public void zoomRangeAxes(double lowerPercent, double upperPercent, 
                              PlotRenderingInfo info, Point2D source) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[126]++;
        // delegate 'info' and 'source' argument checks...
        XYPlot subplot = findSubplot(info, source);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[127]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((subplot != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[47]++;
            subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[128]++;

        }
        else {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[48]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[129]++;
            // if the source point doesn't fall within a subplot, we do the
            // zoom on all subplots...
            Iterator iterator = getSubplots().iterator();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[130]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[22]++;


int CodeCoverConditionCoverageHelper_C32;
            while ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[22]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[23]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[24]++;
}
                subplot = (XYPlot) iterator.next();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[131]++;
                subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[132]++;
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
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[133]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[49]++;
            throw new IllegalArgumentException("Null 'info' argument.");

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[50]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[134]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[51]++;
            throw new IllegalArgumentException("Null 'source' argument.");

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[52]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[135]++;
        XYPlot result = null;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[136]++;
        int subplotIndex = info.getSubplotIndex(source);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[137]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((subplotIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[53]++;
            result =  (XYPlot) this.subplots.get(subplotIndex);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[138]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[54]++;}
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
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[139]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[140]++;  // not strictly necessary, since the 
                                      // renderer set for the
                                      // parent plot is not used

        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[141]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[25]++;


int CodeCoverConditionCoverageHelper_C36;
        while ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[25]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[26]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[27]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[142]++;
            XYPlot plot = (XYPlot) iterator.next();
            plot.setRenderer(renderer);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[143]++;
        }

    }

    /**
     * Sets the fixed range axis space.
     *
     * @param space  the space (<code>null</code> permitted).
     */
    public void setFixedRangeAxisSpace(AxisSpace space) {
        super.setFixedRangeAxisSpace(space);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[144]++;
        setFixedRangeAxisSpaceForSubplots(space);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[145]++;
        this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[146]++;
    }
    
    /**
     * Sets the size (width or height, depending on the orientation of the 
     * plot) for the domain axis of each subplot.
     *
     * @param space  the space.
     */
    protected void setFixedRangeAxisSpaceForSubplots(AxisSpace space) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[147]++;

        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[148]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[28]++;


int CodeCoverConditionCoverageHelper_C37;
        while ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[28]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[29]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[30]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[149]++;
            XYPlot plot = (XYPlot) iterator.next();
            plot.setFixedRangeAxisSpace(space);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[150]++;
        }

    }

    /**
     * Handles a 'click' on the plot by updating the anchor values.
     *
     * @param x  x-coordinate, where the click occured.
     * @param y  y-coordinate, where the click occured.
     * @param info  object containing information about the plot dimensions.
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[151]++;
        Rectangle2D dataArea = info.getDataArea();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[152]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((dataArea.contains(x, y)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[55]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[153]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[31]++;


int CodeCoverConditionCoverageHelper_C39;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[31]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[32]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[33]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[154]++;
                XYPlot subplot = (XYPlot) this.subplots.get(i);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[155]++;
                PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
                subplot.handleClick(x, y, subplotInfo);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[156]++;
            }

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[56]++;}
    }
    
    /**
     * Receives a {@link PlotChangeEvent} and responds by notifying all 
     * listeners.
     * 
     * @param event  the event.
     */
    public void plotChanged(PlotChangeEvent event) {
        notifyListeners(event);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[157]++;
    }

    /**
     * Tests this plot for equality with another object.
     *
     * @param obj  the other object.
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[158]++;
int CodeCoverConditionCoverageHelper_C40;

        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[58]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[159]++;
int CodeCoverConditionCoverageHelper_C41;

        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[59]++;
            return true;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[60]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[160]++;
int CodeCoverConditionCoverageHelper_C42;

        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((obj instanceof CombinedDomainXYPlot) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[62]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[161]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[64]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[162]++;

        CombinedDomainXYPlot p = (CombinedDomainXYPlot) obj;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[163]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.totalWeight != p.totalWeight) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[66]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[164]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.gap != p.gap) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[68]++;}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[165]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.subplots, p.subplots)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[70]++;}

        return true;
    }
    
    /**
     * Returns a clone of the annotation.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[166]++;
        
        CombinedDomainXYPlot result = (CombinedDomainXYPlot) super.clone(); 
        result.subplots = (List) ObjectUtilities.deepClone(this.subplots);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[167]++;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[168]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[34]++;


int CodeCoverConditionCoverageHelper_C47;
        for (Iterator it = result.subplots.iterator();(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false);) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[34]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[35]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.loops[36]++;
}
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[169]++;
            Plot child = (Plot) it.next();
            child.setParent(result);
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[170]++;
        }
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[171]++;
        
        // after setting up all the subplots, the shared domain axis may need 
        // reconfiguring
        ValueAxis domainAxis = result.getDomainAxis();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[172]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[71]++;
            domainAxis.configure();
CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.statements[173]++;

        } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9.branches[72]++;}
        
        return result;
        
    }
    
}

class CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9 ());
  }
    public static long[] statements = new long[174];
    public static long[] branches = new long[73];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[49];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CombinedDomainXYPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 48; i++) {
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

  public CodeCoverCoverageCounter$32iu5axgdu6pf538p8qkb7c67fichedrnx52sm9 () {
    super("org.jfree.chart.plot.CombinedDomainXYPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 173; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 72; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 48; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.CombinedDomainXYPlot.java");
      for (int i = 1; i <= 173; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 72; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 48; i++) {
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

