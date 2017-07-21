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
 * -------------------------------
 * CombinedDomainCategoryPlot.java
 * -------------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *
 * Changes:
 * --------
 * 16-May-2003 : Version 1 (DG);
 * 08-Aug-2003 : Adjusted totalWeight in remove() method (DG);
 * 19-Aug-2003 : Added equals() method, implemented Cloneable and 
 *               Serializable (DG);
 * 11-Sep-2003 : Fix cloning support (subplots) (NB);
 * 15-Sep-2003 : Implemented PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 17-Sep-2003 : Updated handling of 'clicks' (DG);
 * 04-May-2004 : Added getter/setter methods for 'gap' attribute (DG);
 * 12-Nov-2004 : Implemented the Zoomable interface (DG);
 * 25-Nov-2004 : Small update to clone() implementation (DG);
 * 21-Feb-2005 : The getLegendItems() method now returns the fixed legend
 *               items if set (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Sep-2006 : Updated API docs (DG);
 * 30-Oct-2006 : Added new getCategoriesForAxis() override (DG);
 * 17-Apr-2007 : Added null argument checks to findSubplot() (DG);
 * 14-Nov-2007 : Updated setFixedRangeAxisSpaceForSubplots() method (DG);
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A combined category plot where the domain axis is shared.
 */
public class CombinedDomainCategoryPlot extends CategoryPlot
                                        implements Zoomable,
                                                   Cloneable, PublicCloneable, 
                                                   Serializable,
                                                   PlotChangeListener {
  static {
    CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8207194522653701572L;
  static {
    CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[1]++;
  }
    
    /** Storage for the subplot references. */
    private List subplots;

    /** Total weight of all charts. */
    private int totalWeight;

    /** The gap between subplots. */
    private double gap;

    /** Temporary storage for the subplot areas. */
    private transient Rectangle2D[] subplotAreas;
    // TODO:  move the above to the plot state
    
    /**
     * Default constructor.
     */
    public CombinedDomainCategoryPlot() {
        this(new CategoryAxis());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[2]++;
    }
    
    /**
     * Creates a new plot.
     *
     * @param domainAxis  the shared domain axis (<code>null</code> not 
     *                    permitted).
     */
    public CombinedDomainCategoryPlot(CategoryAxis domainAxis) {
        super(null, domainAxis, null, null);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[3]++;
        this.subplots = new java.util.ArrayList();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[4]++;
        this.totalWeight = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[5]++;
        this.gap = 5.0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[6]++;
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
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[7]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[8]++;
    }

    /**
     * Adds a subplot to the combined chart and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * <br><br>
     * The domain axis for the subplot will be set to <code>null</code>.  You
     * must ensure that the subplot has a non-null range axis.
     * 
     * @param subplot  the subplot (<code>null</code> not permitted).
     */
    public void add(CategoryPlot subplot) {
        add(subplot, 1);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[9]++;    
    }
    
    /**
     * Adds a subplot to the combined chart and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * <br><br>
     * The domain axis for the subplot will be set to <code>null</code>.  You
     * must ensure that the subplot has a non-null range axis.
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     * @param weight  the weight (must be >= 1).
     */
    public void add(CategoryPlot subplot, int weight) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((subplot == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[1]++;
            throw new IllegalArgumentException("Null 'subplot' argument.");

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[2]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((weight < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[3]++;
            throw new IllegalArgumentException("Require weight >= 1.");

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[4]++;}
        subplot.setParent(this);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[12]++;
        subplot.setWeight(weight);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[13]++;
        subplot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[14]++;
        subplot.setDomainAxis(null);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[15]++;
        subplot.setOrientation(getOrientation());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[16]++;
        subplot.addChangeListener(this);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[17]++;
        this.subplots.add(subplot);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[18]++;
        this.totalWeight += weight;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[19]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[20]++;
        CategoryAxis axis = getDomainAxis();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[5]++;
            axis.configure();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[22]++;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[6]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[23]++;
    }

    /**
     * Removes a subplot from the combined chart.  Potentially, this removes 
     * some unique categories from the overall union of the datasets...so the 
     * domain axis is reconfigured, then a {@link PlotChangeEvent} is sent to 
     * all registered listeners.
     *
     * @param subplot  the subplot (<code>null</code> not permitted).
     */
    public void remove(CategoryPlot subplot) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((subplot == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[7]++;
            throw new IllegalArgumentException("Null 'subplot' argument.");

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[8]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[25]++;
        int position = -1;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[26]++;
        int size = this.subplots.size();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[27]++;
        int i = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((position == -1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[1]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[2]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[3]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.subplots.get(i) == subplot) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[9]++;
                position = i;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[30]++;

            } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[10]++;}
            i++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[31]++;
        }
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((position != -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[11]++;
            this.subplots.remove(position);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[33]++;
            subplot.setParent(null);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[34]++;
            subplot.removeChangeListener(this);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[35]++;
            this.totalWeight -= subplot.getWeight();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[36]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[37]++;

            CategoryAxis domain = getDomainAxis();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((domain != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[13]++;
                domain.configure();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[39]++;

            } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[14]++;}
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[40]++;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[12]++;}
    }

    /**
     * Returns the list of subplots.
     *
     * @return An unmodifiable list of subplots .
     */
    public List getSubplots() {
        return Collections.unmodifiableList(this.subplots);
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
    public CategoryPlot findSubplot(PlotRenderingInfo info, Point2D source) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[41]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[15]++;
            throw new IllegalArgumentException("Null 'info' argument.");

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[16]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[17]++;
            throw new IllegalArgumentException("Null 'source' argument.");

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[18]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[43]++;
        CategoryPlot result = null;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[44]++;
        int subplotIndex = info.getSubplotIndex(source);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((subplotIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[19]++;
            result =  (CategoryPlot) this.subplots.get(subplotIndex);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[46]++;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[20]++;}
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
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[47]++;
        // delegate 'info' and 'source' argument checks...
        CategoryPlot subplot = findSubplot(info, source);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((subplot != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[21]++;
            subplot.zoomRangeAxes(factor, info, source);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[49]++;

        }
        else {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[22]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[50]++;
            // if the source point doesn't fall within a subplot, we do the
            // zoom on all subplots...
            Iterator iterator = getSubplots().iterator();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[51]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
            while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[4]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[5]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[6]++;
}
                subplot = (CategoryPlot) iterator.next();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[52]++;
                subplot.zoomRangeAxes(factor, info, source);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[53]++;
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
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[54]++;
        // delegate 'info' and 'source' argument checks...
        CategoryPlot subplot = findSubplot(info, source);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[55]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((subplot != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[23]++;
            subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[56]++;

        }
        else {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[24]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[57]++;
            // if the source point doesn't fall within a subplot, we do the
            // zoom on all subplots...
            Iterator iterator = getSubplots().iterator();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[58]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[7]++;


int CodeCoverConditionCoverageHelper_C15;
            while ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[7]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[8]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[9]++;
}
                subplot = (CategoryPlot) iterator.next();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[59]++;
                subplot.zoomRangeAxes(lowerPercent, upperPercent, info, source);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[60]++;
            }
        }
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
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[61]++;
        
        AxisSpace space = new AxisSpace();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[62]++;
        PlotOrientation orientation = getOrientation();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[63]++;
        
        // work out the space required by the domain axis...
        AxisSpace fixed = getFixedDomainAxisSpace();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((fixed != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[25]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[65]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[27]++;
                space.setLeft(fixed.getLeft());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[66]++;
                space.setRight(fixed.getRight());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[67]++;

            }
            else {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[28]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[68]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[29]++;
                space.setTop(fixed.getTop());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[69]++;
                space.setBottom(fixed.getBottom());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[70]++;
                
            } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[30]++;}
}

        }
        else {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[26]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[71]++;
            CategoryAxis categoryAxis = getDomainAxis();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[72]++;
            RectangleEdge categoryEdge = Plot.resolveDomainAxisLocation(
                    getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[73]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((categoryAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[31]++;
                space = categoryAxis.reserveSpace(g2, this, plotArea, 
                        categoryEdge, space);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[74]++;

            }
            else {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[32]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[75]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getDrawSharedDomainAxis()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[33]++;
                    space = getDomainAxis().reserveSpace(g2, this, plotArea, 
                            categoryEdge, space);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[76]++;

                } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[34]++;}
            }
        }
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[77]++;
        
        Rectangle2D adjustedPlotArea = space.shrink(plotArea, null);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[78]++;
        
        // work out the maximum height or width of the non-shared axes...
        int n = this.subplots.size();
        this.subplotAreas = new Rectangle2D[n];
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[79]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[80]++;
        double x = adjustedPlotArea.getX();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[81]++;
        double y = adjustedPlotArea.getY();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[82]++;
        double usableSize = 0.0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[83]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[35]++;
            usableSize = adjustedPlotArea.getWidth() - this.gap * (n - 1);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[84]++;

        }
        else {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[36]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[85]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[37]++;
            usableSize = adjustedPlotArea.getHeight() - this.gap * (n - 1);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[86]++;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[38]++;}
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[87]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[10]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[11]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[12]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[88]++;
            CategoryPlot plot = (CategoryPlot) this.subplots.get(i);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[89]++;
int CodeCoverConditionCoverageHelper_C24;

            // calculate sub-plot area
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[39]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[90]++;
                double w = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, w, 
                        adjustedPlotArea.getHeight());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[91]++;
                x = x + w + this.gap;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[92]++;

            }
            else {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[40]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[93]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[41]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[94]++;
                double h = usableSize * plot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, 
                        adjustedPlotArea.getWidth(), h);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[95]++;
                y = y + h + this.gap;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[96]++;

            } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[42]++;}
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[97]++;

            AxisSpace subSpace = plot.calculateRangeAxisSpace(g2, 
                    this.subplotAreas[i], null);
            space.ensureAtLeast(subSpace);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[98]++;

        }

        return space;
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).  Will perform all the placement calculations for each of the
     * sub-plots and then tell these to draw themselves.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot (including axis labels) 
     *              should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the state from the parent plot, if there is one.
     * @param info  collects information about the drawing (<code>null</code> 
     *              permitted).
     */
    public void draw(Graphics2D g2, 
                     Rectangle2D area, 
                     Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[99]++;
int CodeCoverConditionCoverageHelper_C26;
        
        // set up info collection...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[43]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[100]++;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[44]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[101]++;

        // adjust the drawing area for plot insets (if any)...
        RectangleInsets insets = getInsets();
        area.setRect(area.getX() + insets.getLeft(),
                area.getY() + insets.getTop(),
                area.getWidth() - insets.getLeft() - insets.getRight(),
                area.getHeight() - insets.getTop() - insets.getBottom());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[102]++;


        // calculate the data area...
        setFixedRangeAxisSpaceForSubplots(null);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[103]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[104]++;
        AxisSpace space = calculateAxisSpace(g2, area);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[105]++;
        Rectangle2D dataArea = space.shrink(area, null);

        // set the width and height of non-shared axis of all sub-plots
        setFixedRangeAxisSpaceForSubplots(space);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[106]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[107]++;

        // draw the shared axis
        CategoryAxis axis = getDomainAxis();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[108]++;
        RectangleEdge domainEdge = getDomainAxisEdge();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[109]++;
        double cursor = RectangleEdge.coordinate(dataArea, domainEdge);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[110]++;
        AxisState axisState = axis.draw(g2, cursor, area, dataArea, 
                domainEdge, info);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[111]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((parentState == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[45]++;
            parentState = new PlotState();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[112]++;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[46]++;}
        parentState.getSharedAxisStates().put(axis, axisState);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[113]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[114]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[13]++;


int CodeCoverConditionCoverageHelper_C28;
        
        // draw all the subplots
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[13]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[14]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[15]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[115]++;
            CategoryPlot plot = (CategoryPlot) this.subplots.get(i);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[116]++;
            PlotRenderingInfo subplotInfo = null;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[117]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[47]++;
                subplotInfo = new PlotRenderingInfo(info.getOwner());
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[118]++;
                info.addSubplotInfo(subplotInfo);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[119]++;

            } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[48]++;}
            plot.draw(g2, this.subplotAreas[i], null, parentState, subplotInfo);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[120]++;
        }
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[121]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[49]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[122]++;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[50]++;}

    }

    /**
     * Sets the size (width or height, depending on the orientation of the 
     * plot) for the range axis of each subplot.
     *
     * @param space  the space (<code>null</code> permitted).
     */
    protected void setFixedRangeAxisSpaceForSubplots(AxisSpace space) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[123]++;
        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[124]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[16]++;


int CodeCoverConditionCoverageHelper_C31;
        while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[16]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[17]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[18]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[125]++;
            CategoryPlot plot = (CategoryPlot) iterator.next();
            plot.setFixedRangeAxisSpace(space, false);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[126]++;
        }
    }

    /**
     * Sets the orientation of the plot (and all subplots).
     * 
     * @param orientation  the orientation (<code>null</code> not permitted).
     */
    public void setOrientation(PlotOrientation orientation) {

        super.setOrientation(orientation);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[127]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[128]++;

        Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[129]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[19]++;


int CodeCoverConditionCoverageHelper_C32;
        while ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[19]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[20]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[21]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[130]++;
            CategoryPlot plot = (CategoryPlot) iterator.next();
            plot.setOrientation(orientation);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[131]++;
        }

    }
    
    /**
     * Returns a collection of legend items for the plot.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[132]++;
        LegendItemCollection result = getFixedLegendItems();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[133]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[51]++;
            result = new LegendItemCollection();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[134]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[135]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[53]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[136]++;
                Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[137]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[22]++;


int CodeCoverConditionCoverageHelper_C35;
                while ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[22]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[23]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[24]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[138]++;
                    CategoryPlot plot = (CategoryPlot) iterator.next();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[139]++;
                    LegendItemCollection more = plot.getLegendItems();
                    result.addAll(more);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[140]++;
                }

            } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[54]++;}

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[52]++;}
        return result;
    }
    
    /**
     * Returns an unmodifiable list of the categories contained in all the 
     * subplots.
     * 
     * @return The list.
     */
    public List getCategories() {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[141]++;
        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[142]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.subplots != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[55]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[143]++;
            Iterator iterator = this.subplots.iterator();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[144]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[25]++;


int CodeCoverConditionCoverageHelper_C37;
            while ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[25]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[26]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[27]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[145]++;
                CategoryPlot plot = (CategoryPlot) iterator.next();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[146]++;
                List more = plot.getCategories();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[147]++;
                Iterator moreIterator = more.iterator();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[148]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[28]++;


int CodeCoverConditionCoverageHelper_C38;
                while ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((moreIterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[28]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[29]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[30]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[149]++;
                    Comparable category = (Comparable) moreIterator.next();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[150]++;
int CodeCoverConditionCoverageHelper_C39;
                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((result.contains(category)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[57]++;
                        result.add(category);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[151]++;

                    } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[58]++;}
                }
            }

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[56]++;}
        return Collections.unmodifiableList(result);
    }
    
    /**
     * Overridden to return the categories in the subplots.
     * 
     * @param axis  ignored.
     * 
     * @return A list of the categories in the subplots.
     * 
     * @since 1.0.3
     */
    public List getCategoriesForAxis(CategoryAxis axis) {
        // FIXME:  this code means that it is not possible to use more than
        // one domain axis for the combined plots...
        return getCategories();    
    }
    
    /**
     * Handles a 'click' on the plot.
     *
     * @param x  x-coordinate of the click.
     * @param y  y-coordinate of the click.
     * @param info  information about the plot's dimensions.
     *
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[152]++;

        Rectangle2D dataArea = info.getDataArea();
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[153]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((dataArea.contains(x, y)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[59]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[154]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[31]++;


int CodeCoverConditionCoverageHelper_C41;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i < this.subplots.size()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[31]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[32]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[33]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[155]++;
                CategoryPlot subplot = (CategoryPlot) this.subplots.get(i);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[156]++;
                PlotRenderingInfo subplotInfo = info.getSubplotInfo(i);
                subplot.handleClick(x, y, subplotInfo);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[157]++;
            }

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[60]++;}

    }
    
    /**
     * Receives a {@link PlotChangeEvent} and responds by notifying all 
     * listeners.
     * 
     * @param event  the event.
     */
    public void plotChanged(PlotChangeEvent event) {
        notifyListeners(event);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[158]++;
    }

    /** 
     * Tests the plot for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[159]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[61]++;
            return true;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[62]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[160]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((obj instanceof CombinedDomainCategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[64]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[161]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[66]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[162]++;
        CombinedDomainCategoryPlot plot = (CombinedDomainCategoryPlot) obj;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[163]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.subplots, plot.subplots)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[68]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[164]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.totalWeight != plot.totalWeight) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[70]++;}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[165]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.gap != plot.gap) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[71]++; 
            return false;

        } else {
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.branches[72]++;}
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
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[166]++;
        
        CombinedDomainCategoryPlot result 
            = (CombinedDomainCategoryPlot) super.clone(); 
        result.subplots = (List) ObjectUtilities.deepClone(this.subplots);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[167]++;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[168]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[34]++;


int CodeCoverConditionCoverageHelper_C48;
        for (Iterator it = result.subplots.iterator();(((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false);) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[34]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[35]--;
  CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.loops[36]++;
}
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[169]++;
            Plot child = (Plot) it.next();
            child.setParent(result);
CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5.statements[170]++;
        }
        return result;
        
    }
    
}

class CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5 ());
  }
    public static long[] statements = new long[171];
    public static long[] branches = new long[73];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[49];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CombinedDomainCategoryPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$8ibepyefyerrxe7lsgbpga0yfbm8vvyvzi9q2ra7bzneffj5 () {
    super("org.jfree.chart.plot.CombinedDomainCategoryPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 170; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.CombinedDomainCategoryPlot.java");
      for (int i = 1; i <= 170; i++) {
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

