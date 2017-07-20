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
 * ----------------------
 * PlotRenderingInfo.java
 * ----------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 16-Sep-2003 : Version 1 (DG);
 * 23-Sep-2003 : Added Javadocs (DG);
 * 12-Nov-2004 : Added getSubplotCount() and findSubplot() methods (DG);
 * 01-Nov-2005 : Made 'owner' non-transient to fix bug 1344048 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Dec-2006 : Implemented clone() method properly (DG);
 * 17-Apr-2007 : Fixed bug 1698965 (NPE in CombinedDomainXYPlot) (DG);
 * 
 */
 
package org.jfree.chart.plot;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.io.SerialUtilities;
import org.jfree.util.ObjectUtilities;

/**
 * Stores information about the dimensions of a plot and its subplots.
 */
public class PlotRenderingInfo implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8446720134379617220L;
  static {
    CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[1]++;
  }
    
    /** The owner of this info. */
    private ChartRenderingInfo owner;
    
    /** The plot area. */
    private transient Rectangle2D plotArea;
    
    /** The data area. */
    private transient Rectangle2D dataArea;
    
    /**
     * Storage for the plot rendering info objects belonging to the subplots. 
     */
    private List subplotInfo;
      
    /**
     * Creates a new instance.
     * 
     * @param owner  the owner (<code>null</code> permitted).
     */
    public PlotRenderingInfo(ChartRenderingInfo owner) {
        this.owner = owner;
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[2]++;        
        this.dataArea = new Rectangle2D.Double();
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[3]++;
        this.subplotInfo = new java.util.ArrayList();
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[4]++;    
    }
    
    /**
     * Returns the owner (as specified in the constructor).
     * 
     * @return The owner (possibly <code>null</code>).
     */
    public ChartRenderingInfo getOwner() {
        return this.owner;
    }
    
    /**
     * Returns the plot area (in Java2D space).
     * 
     * @return The plot area (possibly <code>null</code>).
     *
     * @see #setPlotArea(Rectangle2D)
     */
    public Rectangle2D getPlotArea() {
        return this.plotArea;
    }
    
    /**
     * Sets the plot area.
     * 
     * @param area  the plot area (in Java2D space, <code>null</code> 
     *     permitted but discouraged)
     * 
     * @see #getPlotArea()
     */
    public void setPlotArea(Rectangle2D area) {
        this.plotArea = area;
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[5]++;
    }
    
    /**
     * Returns the plot's data area (in Java2D space).
     * 
     * @return The data area (possibly <code>null</code>).
     * 
     * @see #setDataArea(Rectangle2D)
     */
    public Rectangle2D getDataArea() {
        return this.dataArea;
    }
    
    /**
     * Sets the data area.
     * 
     * @param area  the data area (in Java2D space, <code>null</code> permitted
     *     but discouraged).
     * 
     * @see #getDataArea()
     */
    public void setDataArea(Rectangle2D area) {
        this.dataArea = area;
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[6]++;
    }
    
    /**
     * Returns the number of subplots (possibly zero).
     * 
     * @return The subplot count.
     */
    public int getSubplotCount() {
        return this.subplotInfo.size();
    }
    
    /**
     * Adds the info for a subplot.
     * 
     * @param info  the subplot info.
     * 
     * @see #getSubplotInfo(int)
     */
    public void addSubplotInfo(PlotRenderingInfo info) {
        this.subplotInfo.add(info);
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[7]++;    
    }
    
    /**
     * Returns the info for a subplot.
     * 
     * @param index  the subplot index.
     * 
     * @return The info.
     * 
     * @see #addSubplotInfo(PlotRenderingInfo)
     */
    public PlotRenderingInfo getSubplotInfo(int index) {
        return (PlotRenderingInfo) this.subplotInfo.get(index);
    }
    
    /**
     * Returns the index of the subplot that contains the specified
     * (x, y) point (the "source" point).  The source point will usually
     * come from a mouse click on a {@link org.jfree.chart.ChartPanel},
     * and this method is then used to determine the subplot that 
     * contains the source point.
     * 
     * @param source  the source point (in Java2D space, <code>null</code> not
     * permitted).
     * 
     * @return The subplot index (or -1 if no subplot contains 
     *         <code>source</code>).
     */
    public int getSubplotIndex(Point2D source) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[1]++;
            throw new IllegalArgumentException("Null 'source' argument.");

        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[2]++;}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[9]++;
        int subplotCount = getSubplotCount();
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < subplotCount) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[1]--;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[2]--;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[3]++;
}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[11]++;
            PlotRenderingInfo info = getSubplotInfo(i);
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[12]++;
            Rectangle2D area = info.getDataArea();
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((area.contains(source)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[3]++;
                return i;

            } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[4]++;}
        }
        return -1;
    }
    
    /**
     * Tests this instance for equality against an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[5]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[6]++;}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof PlotRenderingInfo) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[8]++;}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[16]++;
        PlotRenderingInfo that = (PlotRenderingInfo) obj;
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.dataArea, that.dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[9]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[10]++;}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.plotArea, that.plotArea)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[11]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[12]++;}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.subplotInfo, that.subplotInfo)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[13]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[14]++;}
        return true;   
    }
    
    /**
     * Returns a clone of this object.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[20]++;
        PlotRenderingInfo clone = (PlotRenderingInfo) super.clone();
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.plotArea != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[15]++;
            clone.plotArea = (Rectangle2D) this.plotArea.clone();
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[22]++;

        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[16]++;}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.dataArea != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[17]++;
            clone.dataArea = (Rectangle2D) this.dataArea.clone();
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[24]++;

        } else {
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.branches[18]++;}
        clone.subplotInfo = new java.util.ArrayList(this.subplotInfo.size());
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[25]++;
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < this.subplotInfo.size()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[4]--;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[5]--;
  CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.loops[6]++;
}
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[27]++;
            PlotRenderingInfo info 
                    = (PlotRenderingInfo) this.subplotInfo.get(i);
            clone.subplotInfo.add(info.clone());
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[28]++;
        }
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
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[29]++;
        SerialUtilities.writeShape(this.dataArea, stream);
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[30]++;
        SerialUtilities.writeShape(this.plotArea, stream);
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[31]++;
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
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[32]++;
        this.dataArea = (Rectangle2D) SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[33]++;
        this.plotArea = (Rectangle2D) SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735.statements[34]++;
    }

}

class CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735 ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.PlotRenderingInfo.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$d71xhcjzse5s11tql4p17nxhq8bdj6i735 () {
    super("org.jfree.chart.plot.PlotRenderingInfo.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.PlotRenderingInfo.java");
      for (int i = 1; i <= 34; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

