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
 * ------------------------------
 * CombinedRangeCategoryPlot.java
 * ------------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *
 * Changes:
 * --------
 * 16-May-2003 : Version 1 (DG);
 * 08-Aug-2003 : Adjusted totalWeight in remove() method (DG);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 11-Sep-2003 : Fix cloning support (subplots) (NB);
 * 15-Sep-2003 : Implemented PublicCloneable.  Fixed errors in cloning and 
 *               serialization (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 17-Sep-2003 : Updated handling of 'clicks' (DG);
 * 04-May-2004 : Added getter/setter methods for 'gap' attributes (DG);
 * 12-Nov-2004 : Implements the new Zoomable interface (DG);
 * 25-Nov-2004 : Small update to clone() implementation (DG);
 * 21-Feb-2005 : Fixed bug in remove() method (id = 1121172) (DG);
 * 21-Feb-2005 : The getLegendItems() method now returns the fixed legend
 *               items if set (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 14-Nov-2007 : Updated setFixedDomainAxisSpaceForSubplots() method (DG);
 */
 
package org.jfree.chart.plot;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import org.jfree.data.Range;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A combined category plot where the range axis is shared.
 */
public class CombinedRangeCategoryPlot extends CategoryPlot 
                                       implements Zoomable,
                                                  Cloneable, PublicCloneable, 
                                                  Serializable,
                                                  PlotChangeListener {
  static {
    CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7260210007554504515L;
  static {
    CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[1]++;
  }
    
    /** Storage for the subplot references. */
    private List subplots;

    /** Total weight of all charts. */
    private int totalWeight;

    /** The gap between subplots. */
    private double gap;

    /** Temporary storage for the subplot areas. */
    private transient Rectangle2D[] subplotArea;  // TODO: move to plot state

    /**
     * Default constructor.
     */
    public CombinedRangeCategoryPlot() {
        this(new NumberAxis());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[2]++;
    }
    
    /**
     * Creates a new plot.
     *
     * @param rangeAxis  the shared range axis.
     */
    public CombinedRangeCategoryPlot(ValueAxis rangeAxis) {
        super(null, null, rangeAxis, null);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[3]++;
        this.subplots = new java.util.ArrayList();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[4]++;
        this.totalWeight = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[5]++;
        this.gap = 5.0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[6]++;
    }

    /**
     * Returns the space between subplots.
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
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[7]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[8]++;
    }

    /**
     * Adds a subplot (with a default 'weight' of 1) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * <br><br>
     * You must ensure that the subplot has a non-null domain axis.  The range
     * axis for the subplot will be set to <code>null</code>.  
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     */
    public void add(CategoryPlot subplot) {
        // defer argument checking
        add(subplot, 1);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[9]++;
    }

    /**
     * Adds a subplot and sends a {@link PlotChangeEvent} to all registered 
     * listeners.
     * <br><br>
     * You must ensure that the subplot has a non-null domain axis.  The range
     * axis for the subplot will be set to <code>null</code>.  
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     * @param weight  the weight (must be >= 1).
     */
    public void add(CategoryPlot subplot, int weight) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((subplot == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[1]++;
            throw new IllegalArgumentException("Null 'subplot' argument.");

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[2]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((weight <= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[3]++;
            throw new IllegalArgumentException("Require weight >= 1.");

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[4]++;}
        // store the plot and its weight
        subplot.setParent(this);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[12]++;
        subplot.setWeight(weight);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[13]++;
        subplot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[14]++;
        subplot.setRangeAxis(null);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[15]++;
        subplot.setOrientation(getOrientation());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[16]++;
        subplot.addChangeListener(this);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[17]++;
        this.subplots.add(subplot);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[18]++;
        this.totalWeight += weight;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[19]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[20]++;
        
        // configure the range axis...
        ValueAxis axis = getRangeAxis();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[5]++;
            axis.configure();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[22]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[6]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[23]++;
    }

    /**
     * Removes a subplot from the combined chart.
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     */
    public void remove(CategoryPlot subplot) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((subplot == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[7]++;
            throw new IllegalArgumentException(" Null 'subplot' argument.");
   
        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[8]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[25]++;
        int position = -1;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[26]++;
        int size = this.subplots.size();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[27]++;
        int i = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((position == -1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[1]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[2]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[3]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.subplots.get(i) == subplot) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[9]++;
                position = i;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[30]++;

            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[10]++;}
            i++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[31]++;
        }
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((position != -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[11]++;
            this.subplots.remove(position);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[33]++;
            subplot.setParent(null);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[34]++;
            subplot.removeChangeListener(this);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[35]++;
            this.totalWeight -= subplot.getWeight();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[36]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[37]++;
        
            ValueAxis range = getRangeAxis();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((range != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[13]++;
                range.configure();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[39]++;

            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[14]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[40]++;

            ValueAxis range2 = getRangeAxis(1);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[41]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((range2 != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[15]++;
                range2.configure();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[42]++;

            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[16]++;}
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[43]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[12]++;}
    }

    /**
     * Returns the list of subplots.
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
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[44]++;
        
        AxisSpace space = new AxisSpace();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[45]++;  
        PlotOrientation orientation = getOrientation();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[46]++;
        
        // work out the space required by the domain axis...
        AxisSpace fixed = getFixedRangeAxisSpace();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((fixed != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[17]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[19]++;
                space.setLeft(fixed.getLeft());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[49]++;
                space.setRight(fixed.getRight());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[50]++;

            }
            else {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[20]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[51]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[21]++;
                space.setTop(fixed.getTop());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[52]++;
                space.setBottom(fixed.getBottom());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[53]++;
                
            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[22]++;}
}

        }
        else {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[18]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[54]++;
            ValueAxis valueAxis = getRangeAxis();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[55]++;
            RectangleEdge valueEdge = Plot.resolveRangeAxisLocation(
                    getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[56]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((valueAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[23]++;
                space = valueAxis.reserveSpace(g2, this, plotArea, valueEdge, 
                        space);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[57]++;

            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[24]++;}
        }
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[58]++;
        
        Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[59]++;
        // work out the maximum height or width of the non-shared axes...
        int n = this.subplots.size();

        // calculate plotAreas of all sub-plots, maximum vertical/horizontal 
        // axis width/height
        this.subplotArea = new Rectangle2D[n];
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[60]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[61]++;
        double x = adjustedPlotArea.getX();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[62]++;
        double y = adjustedPlotArea.getY();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[63]++;
        double usableSize = 0.0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[25]++;
            usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[65]++;

        }
        else {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[26]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[66]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[27]++;
            usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[67]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[28]++;}
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[68]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[4]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[5]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[6]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[69]++;
            CategoryPlot plot = (CategoryPlot) this.subplots.get(i);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;

            // calculate sub-plot area
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[29]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[71]++;
                double w = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotArea[i] = new Rectangle2D.Double(x, y, w, 
                        adjustedPlotArea.getHeight());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[72]++;
                x = x + w + this.gap;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[73]++;

            }
            else {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[30]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[74]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[31]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[75]++;
                double h = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotArea[i] = new Rectangle2D.Double(x, y, 
                        adjustedPlotArea.getWidth(), h);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[76]++;
                y = y + h + this.gap;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[77]++;

            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[32]++;}
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[78]++;

            AxisSpace subSpace = plot.calculateDomainAxisSpace(g2, 
                    this.subplotArea[i], null);
            space.ensureAtLeast(subSpace);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[79]++;

        }

        return space;
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).  Will perform all the placement calculations for each 
     * sub-plots and then tell these to draw themselves.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot (including axis labels)
     *              should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the parent state.
     * @param info  collects information about the drawing (<code>null</code> 
     *              permitted).
     */
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[80]++;
int CodeCoverConditionCoverageHelper_C19;

        // set up info collection...
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[33]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[81]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[34]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[82]++;

        // adjust the drawing area for plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[83]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[84]++;

        // calculate the data area...
        AxisSpace space = calculateAxisSpace(g2, area);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[85]++;
        Rectangle2D dataArea = space.shrink(area, null);

        // set the width and height of non-shared axis of all sub-plots
        setFixedDomainAxisSpaceForSubplots(space);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[86]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[87]++;

        // draw the shared axis
        ValueAxis axis = getRangeAxis();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[88]++;
        RectangleEdge rangeEdge = getRangeAxisEdge();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[89]++;
        double cursor = RectangleEdge.coordinate(dataArea, rangeEdge);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[90]++;
        AxisState state = axis.draw(g2, cursor, area, dataArea, rangeEdge, 
                info);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[91]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((parentState == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[35]++;
            parentState = new PlotState();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[92]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[36]++;}
        parentState.getSharedAxisStates().put(axis, state);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[93]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[94]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[7]++;


int CodeCoverConditionCoverageHelper_C21;
        
        // draw all the charts
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[7]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[8]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[9]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[95]++;
            CategoryPlot plot = (CategoryPlot) this.subplots.get(i);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[96]++;
            PlotRenderingInfo subplotInfo = null;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[97]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[37]++;
                subplotInfo = new PlotRenderingInfo(info.getOwner());
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[98]++;
                info.addSubplotInfo(subplotInfo);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[99]++;

            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[38]++;}
            plot.draw(g2, this.subplotArea[i], null, parentState, subplotInfo);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[100]++;
        }
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[101]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[39]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[102]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[40]++;}

    }

    /**
     * Sets the orientation for the plot (and all the subplots).
     * 
     * @param orientation  the orientation.
     */
    public void setOrientation(PlotOrientation orientation) {

        super.setOrientation(orientation);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[103]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[104]++;

        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[105]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[10]++;


int CodeCoverConditionCoverageHelper_C24;
        while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[10]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[11]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[12]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[106]++;
            CategoryPlot plot = (CategoryPlot) iterator.next();
            plot.setOrientation(orientation);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[107]++;
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
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[108]++;

         Range result = null;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[109]++;
int CodeCoverConditionCoverageHelper_C25;
         if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[41]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[110]++;
             Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[111]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[13]++;


int CodeCoverConditionCoverageHelper_C26;
             while ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[13]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[14]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[15]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[112]++;
                 CategoryPlot subplot = (CategoryPlot) iterator.next();
                 result = Range.combine(result, subplot.getDataRange(axis));
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[113]++;
             }

         } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[42]++;}
         return result;

     }

    /**
     * Returns a collection of legend items for the plot.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[114]++;
        LegendItemCollection result = getFixedLegendItems();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[115]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[43]++;
            result = new LegendItemCollection();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[116]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[117]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[45]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[118]++;
                Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[119]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[16]++;


int CodeCoverConditionCoverageHelper_C29;
                while ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[16]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[17]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[18]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[120]++;
                    CategoryPlot plot = (CategoryPlot) iterator.next();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[121]++;
                    LegendItemCollection more = plot.getLegendItems();
                    result.addAll(more);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[122]++;
                }

            } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[46]++;}

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[44]++;}
        return result;
    }
    
    /**
     * Sets the size (width or height, depending on the orientation of the 
     * plot) for the domain axis of each subplot.
     *
     * @param space  the space.
     */
    protected void setFixedDomainAxisSpaceForSubplots(AxisSpace space) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[123]++;
        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[124]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[19]++;


int CodeCoverConditionCoverageHelper_C30;
        while ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[19]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[20]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[21]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[125]++;
            CategoryPlot plot = (CategoryPlot) iterator.next();
            plot.setFixedDomainAxisSpace(space, false);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[126]++;
        }
    }

    /**
     * Handles a 'click' on the plot by updating the anchor value.
     *
     * @param x  x-coordinate of the click.
     * @param y  y-coordinate of the click.
     * @param info  information about the plot's dimensions.
     *
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[127]++;

        Rectangle2D dataArea = info.getDataArea();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[128]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((dataArea.contains(x, y)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[47]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[129]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[22]++;


int CodeCoverConditionCoverageHelper_C32;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[22]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[23]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[24]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[130]++;
                CategoryPlot subplot = (CategoryPlot) this.subplots.get(i);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[131]++;
                PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
                subplot.handleClick(x, y, subplotInfo);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[132]++;
            }

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[48]++;}

    }

    /**
     * Receives a {@link PlotChangeEvent} and responds by notifying all 
     * listeners.
     * 
     * @param event  the event.
     */
    public void plotChanged(PlotChangeEvent event) {
        notifyListeners(event);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[133]++;
    }

    /** 
     * Tests the plot for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[134]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[49]++;
            return true;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[50]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[135]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((obj instanceof CombinedRangeCategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[52]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[136]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[54]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[137]++;
        CombinedRangeCategoryPlot that = (CombinedRangeCategoryPlot) obj;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[138]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.subplots, that.subplots)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[56]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[139]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.totalWeight != that.totalWeight) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[58]++;}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[140]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.gap != that.gap) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[60]++;}
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
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[141]++;
        CombinedRangeCategoryPlot result 
            = (CombinedRangeCategoryPlot) super.clone(); 
        result.subplots = (List) ObjectUtilities.deepClone(this.subplots);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[142]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[143]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[25]++;


int CodeCoverConditionCoverageHelper_C39;
        for (Iterator it = result.subplots.iterator();(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false);) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[25]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[26]--;
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.loops[27]++;
}
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[144]++;
            Plot child = (Plot) it.next();
            child.setParent(result);
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[145]++;
        }
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[146]++;
        
        // after setting up all the subplots, the shared range axis may need 
        // reconfiguring
        ValueAxis rangeAxis = result.getRangeAxis();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[147]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[61]++;
            rangeAxis.configure();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[148]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[62]++;}
        
        return result;
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
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[149]++;
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[150]++;
        
        // the range axis is deserialized before the subplots, so its value 
        // range is likely to be incorrect...
        ValueAxis rangeAxis = getRangeAxis();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[151]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[63]++;
            rangeAxis.configure();
CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.statements[152]++;

        } else {
  CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl.branches[64]++;}
        
    }

}

class CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl ());
  }
    public static long[] statements = new long[153];
    public static long[] branches = new long[65];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[42];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CombinedRangeCategoryPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 41; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$172q99wem9ya473kg8favt1s2dgvlcqf67xve8qisko7gbl () {
    super("org.jfree.chart.plot.CombinedRangeCategoryPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 152; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 64; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 41; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.CombinedRangeCategoryPlot.java");
      for (int i = 1; i <= 152; i++) {
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
    for (int i = 1; i <= 41; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

