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
 * -------------
 * RingPlot.java
 * -------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limtied);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 08-Nov-2004 : Version 1 (DG);
 * 22-Feb-2005 : Renamed DonutPlot --> RingPlot (DG);
 * 06-Jun-2005 : Added default constructor and fixed equals() method to handle
 *               GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Dec-2005 : Fixed problem with entity shape (bug 1386328) (DG);
 * 27-Sep-2006 : Updated drawItem() method for new lookup methods (DG);
 * 12-Oct-2006 : Added configurable section depth (DG);
 * 14-Feb-2007 : Added notification in setSectionDepth() method (DG);
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
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.urls.PieURLGenerator;
import org.jfree.data.general.PieDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.Rotation;
import org.jfree.util.ShapeUtilities;
import org.jfree.util.UnitType;

/**
 * A customised pie plot that leaves a hole in the middle.
 */
public class RingPlot extends PiePlot implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 1556064784129676620L;
  static {
    CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[1]++;
  }
    
    /** 
     * A flag that controls whether or not separators are drawn between the
     * sections of the chart.
     */
    private boolean separatorsVisible;
    
    /** The stroke used to draw separators. */
    private transient Stroke separatorStroke;
    
    /** The paint used to draw separators. */
    private transient Paint separatorPaint;
    
    /** 
     * The length of the inner separator extension (as a percentage of the
     * depth of the sections). 
     */
    private double innerSeparatorExtension;
    
    /** 
     * The length of the outer separator extension (as a percentage of the
     * depth of the sections). 
     */
    private double outerSeparatorExtension;

    /** 
     * The depth of the section as a percentage of the diameter.  
     */
    private double sectionDepth;

    /**
     * Creates a new plot with a <code>null</code> dataset.
     */
    public RingPlot() {
        this(null);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[2]++;   
    }
    
    /**
     * Creates a new plot for the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public RingPlot(PieDataset dataset) {
        super(dataset);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[3]++;
        this.separatorsVisible = true;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[4]++;
        this.separatorStroke = new BasicStroke(0.5f);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[5]++;
        this.separatorPaint = Color.gray;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[6]++;
        this.innerSeparatorExtension = 0.20;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[7]++;  // twenty percent
        this.outerSeparatorExtension = 0.20;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[8]++;  // twenty percent
        this.sectionDepth = 0.20;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[9]++; // 20%
    }
    
    /**
     * Returns a flag that indicates whether or not separators are drawn between
     * the sections in the chart.
     * 
     * @return A boolean.
     *
     * @see #setSeparatorsVisible(boolean)
     */
    public boolean getSeparatorsVisible() {
        return this.separatorsVisible;
    }
    
    /**
     * Sets the flag that controls whether or not separators are drawn between 
     * the sections in the chart, and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #getSeparatorsVisible()
     */
    public void setSeparatorsVisible(boolean visible) {
        this.separatorsVisible = visible;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[10]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[11]++;
    }
    
    /**
     * Returns the separator stroke.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setSeparatorStroke(Stroke)
     */
    public Stroke getSeparatorStroke() {
        return this.separatorStroke;
    }
    
    /**
     * Sets the stroke used to draw the separator between sections and sends 
     * a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getSeparatorStroke()
     */
    public void setSeparatorStroke(Stroke stroke) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[1]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[2]++;}
        this.separatorStroke = stroke;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[13]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[14]++;
    }
    
    /**
     * Returns the separator paint.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setSeparatorPaint(Paint)
     */
    public Paint getSeparatorPaint() {
        return this.separatorPaint;
    }
    
    /**
     * Sets the paint used to draw the separator between sections and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getSeparatorPaint()
     */
    public void setSeparatorPaint(Paint paint) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[4]++;}
        this.separatorPaint = paint;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[16]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[17]++;
    }
    
    /**
     * Returns the length of the inner extension of the separator line that
     * is drawn between sections, expressed as a percentage of the depth of
     * the section.
     * 
     * @return The inner separator extension (as a percentage).
     * 
     * @see #setInnerSeparatorExtension(double)
     */
    public double getInnerSeparatorExtension() {
        return this.innerSeparatorExtension;
    }
    
    /**
     * Sets the length of the inner extension of the separator line that is
     * drawn between sections, as a percentage of the depth of the 
     * sections, and sends a {@link PlotChangeEvent} to all registered 
     * listeners.
     * 
     * @param percent  the percentage.
     * 
     * @see #getInnerSeparatorExtension()
     * @see #setOuterSeparatorExtension(double)
     */
    public void setInnerSeparatorExtension(double percent) {
        this.innerSeparatorExtension = percent;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[18]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[19]++;
    }
    
    /**
     * Returns the length of the outer extension of the separator line that
     * is drawn between sections, expressed as a percentage of the depth of
     * the section.
     * 
     * @return The outer separator extension (as a percentage).
     * 
     * @see #setOuterSeparatorExtension(double)
     */
    public double getOuterSeparatorExtension() {
        return this.outerSeparatorExtension;
    }
    
    /**
     * Sets the length of the outer extension of the separator line that is
     * drawn between sections, as a percentage of the depth of the 
     * sections, and sends a {@link PlotChangeEvent} to all registered 
     * listeners.
     * 
     * @param percent  the percentage.
     * 
     * @see #getOuterSeparatorExtension()
     */
    public void setOuterSeparatorExtension(double percent) {
        this.outerSeparatorExtension = percent;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[20]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[21]++;
    }
    
    /**
     * Returns the depth of each section, expressed as a percentage of the
     * plot radius.
     * 
     * @return The depth of each section.
     * 
     * @see #setSectionDepth(double)
     * @since 1.0.3
     */
    public double getSectionDepth() {
        return this.sectionDepth;
    }
    
    /**
     * The section depth is given as percentage of the plot radius.
     * Specifying 1.0 results in a straightforward pie chart.
     * 
     * @param sectionDepth  the section depth.
     *
     * @see #getSectionDepth()
     * @since 1.0.3
     */
    public void setSectionDepth(double sectionDepth) {
        this.sectionDepth = sectionDepth;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[22]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[23]++;
    }

    /**
     * Initialises the plot state (which will store the total of all dataset
     * values, among other things).  This method is called once at the 
     * beginning of each drawing.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area (<code>null</code> not permitted).
     * @param plot  the plot.
     * @param index  the secondary index (<code>null</code> for primary 
     *               renderer).
     * @param info  collects chart rendering information for return to caller.
     * 
     * @return A state object (maintains state information relevant to one 
     *         chart drawing).
     */
    public PiePlotState initialise(Graphics2D g2, Rectangle2D plotArea,
            PiePlot plot, Integer index, PlotRenderingInfo info) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[24]++;

        PiePlotState state = super.initialise(g2, plotArea, plot, index, info);
        state.setPassesRequired(3);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[25]++;
        return state;   

    }

    /**
     * Draws a single data item.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param section  the section index.
     * @param dataArea  the data plot area.
     * @param state  state information for one chart.
     * @param currentPass  the current pass index.
     */
    protected void drawItem(Graphics2D g2,
                            int section,
                            Rectangle2D dataArea,
                            PiePlotState state,
                            int currentPass) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[26]++;
    
        PieDataset dataset = getDataset();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[27]++;
        Number n = dataset.getValue(section);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[5]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[6]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[29]++;
        double value = n.doubleValue();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[30]++;
        double angle1 = 0.0;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[31]++;
        double angle2 = 0.0;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[32]++;
        
        Rotation direction = getDirection();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((direction == Rotation.CLOCKWISE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[7]++;
            angle1 = state.getLatestAngle();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[34]++;
            angle2 = angle1 - value / state.getTotal() * 360.0;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[35]++;

        }
        else {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[8]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[36]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((direction == Rotation.ANTICLOCKWISE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[9]++;
            angle1 = state.getLatestAngle();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[37]++;
            angle2 = angle1 + value / state.getTotal() * 360.0;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[38]++;
         
        }
        else {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[10]++;
            throw new IllegalStateException("Rotation type not recognised.");   
        }
}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[39]++;
        
        double angle = (angle2 - angle1);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[40]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((Math.abs(angle) > getMinimumArcAngleToDraw()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[11]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[41]++;
            Comparable key = getSectionKey(section);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[42]++;
            double ep = 0.0;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[43]++;
            double mep = getMaximumExplodePercent();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((mep > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[13]++;
                ep = getExplodePercent(key) / mep;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[45]++;
                
            } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[14]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[46]++;
            Rectangle2D arcBounds = getArcBounds(state.getPieArea(), 
                    state.getExplodedPieArea(), angle1, angle, ep);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[47]++;            
            Arc2D.Double arc = new Arc2D.Double(arcBounds, angle1, angle, 
                    Arc2D.OPEN);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[48]++;

            // create the bounds for the inner arc
            double depth = this.sectionDepth / 2.0;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[49]++;
            RectangleInsets s = new RectangleInsets(UnitType.RELATIVE, 
                depth, depth, depth, depth);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[50]++;
            Rectangle2D innerArcBounds = new Rectangle2D.Double();
            innerArcBounds.setRect(arcBounds);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[51]++;
            s.trim(innerArcBounds);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[52]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[53]++;
            // calculate inner arc in reverse direction, for later 
            // GeneralPath construction
            Arc2D.Double arc2 = new Arc2D.Double(innerArcBounds, angle1 
                    + angle, -angle, Arc2D.OPEN);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[54]++;
            GeneralPath path = new GeneralPath();
            path.moveTo((float) arc.getStartPoint().getX(), 
                    (float) arc.getStartPoint().getY());
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[55]++;
            path.append(arc.getPathIterator(null), false);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[56]++;
            path.append(arc2.getPathIterator(null), true);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[57]++;
            path.closePath();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[58]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[59]++;
            
            Line2D separator = new Line2D.Double(arc2.getEndPoint(), 
                    arc.getStartPoint());
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[60]++;
int CodeCoverConditionCoverageHelper_C8;
            
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((currentPass == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[15]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[61]++;
                Paint shadowPaint = getShadowPaint();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[62]++;
                double shadowXOffset = getShadowXOffset();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[63]++;
                double shadowYOffset = getShadowYOffset();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[64]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((shadowPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[17]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[65]++;
                    Shape shadowArc = ShapeUtilities.createTranslatedShape(
                            path, (float) shadowXOffset, (float) shadowYOffset);
                    g2.setPaint(shadowPaint);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[66]++;
                    g2.fill(shadowArc);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[67]++;

                } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[18]++;}

            }
            else {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[16]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[68]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((currentPass == 1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[19]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[69]++;
                Paint paint = lookupSectionPaint(key, true);
                g2.setPaint(paint);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[70]++;
                g2.fill(path);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[71]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[72]++;
                Paint outlinePaint = lookupSectionOutlinePaint(key);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[73]++;
                Stroke outlineStroke = lookupSectionOutlineStroke(key);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[74]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[21]++;
                    g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[75]++;
                    g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[76]++;
                    g2.draw(path);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[77]++;

                } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[22]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[78]++;
int CodeCoverConditionCoverageHelper_C12;
                
                // add an entity for the pie section
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[23]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[79]++;
                    EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[80]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[25]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[81]++;
                        String tip = null;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[82]++;
                        PieToolTipGenerator toolTipGenerator 
                                = getToolTipGenerator();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[83]++;
int CodeCoverConditionCoverageHelper_C14;
                        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((toolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[27]++;
                            tip = toolTipGenerator.generateToolTip(dataset, 
                                    key);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[84]++;

                        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[28]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[85]++;
                        String url = null;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[86]++;
                        PieURLGenerator urlGenerator = getURLGenerator();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[87]++;
int CodeCoverConditionCoverageHelper_C15;
                        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((urlGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[29]++;
                            url = urlGenerator.generateURL(dataset, key, 
                                    getPieIndex());
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[88]++;

                        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[30]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[89]++;
                        PieSectionEntity entity = new PieSectionEntity(path, 
                                dataset, getPieIndex(), section, key, tip, 
                                url);
                        entities.add(entity);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[90]++;

                    } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[26]++;}

                } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[24]++;}

            }
            else {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[20]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[91]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((currentPass == 2) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[31]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[92]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.separatorsVisible) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[33]++;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[93]++;
                    Line2D extendedSeparator = extendLine(separator,
                        this.innerSeparatorExtension, 
                        this.outerSeparatorExtension);
                    g2.setStroke(this.separatorStroke);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[94]++;
                    g2.setPaint(this.separatorPaint);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[95]++;
                    g2.draw(extendedSeparator);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[96]++;

                } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[34]++;}

            } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[32]++;}
}
}

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[12]++;}    
        state.setLatestAngle(angle2);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[97]++;
    }

    /**
     * Tests this plot for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[98]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[35]++;
            return true;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[36]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[99]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj instanceof RingPlot) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[38]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[100]++;
        RingPlot that = (RingPlot) obj;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[101]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.separatorsVisible != that.separatorsVisible) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[40]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[102]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.separatorStroke, 
                that.separatorStroke)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[42]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[103]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.separatorPaint, that.separatorPaint)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[44]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[104]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.innerSeparatorExtension != that.innerSeparatorExtension) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[46]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[105]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.outerSeparatorExtension != that.outerSeparatorExtension) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[48]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[106]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.sectionDepth != that.sectionDepth) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[50]++;}
        return super.equals(obj);
    }
    
    /**
     * Creates a new line by extending an existing line.
     * 
     * @param line  the line (<code>null</code> not permitted).
     * @param startPercent  the amount to extend the line at the start point 
     *                      end.
     * @param endPercent  the amount to extend the line at the end point end.
     * 
     * @return A new line.
     */
    private Line2D extendLine(Line2D line, double startPercent, 
                              double endPercent) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[107]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((line == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[51]++;
            throw new IllegalArgumentException("Null 'line' argument.");

        } else {
  CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.branches[52]++;}
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[108]++;
        double x1 = line.getX1();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[109]++;
        double x2 = line.getX2();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[110]++;
        double deltaX = x2 - x1;
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[111]++;
        double y1 = line.getY1();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[112]++;
        double y2 = line.getY2();
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[113]++;
        double deltaY = y2 - y1;
        x1 = x1 - (startPercent * deltaX);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[114]++;
        y1 = y1 - (startPercent * deltaY);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[115]++;
        x2 = x2 + (endPercent * deltaX);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[116]++;
        y2 = y2 + (endPercent * deltaY);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[117]++;
        return new Line2D.Double(x1, y1, x2, y2);
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
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[118]++;
        SerialUtilities.writeStroke(this.separatorStroke, stream);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[119]++;
        SerialUtilities.writePaint(this.separatorPaint, stream);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[120]++;
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
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[121]++;
        this.separatorStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[122]++;
        this.separatorPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$hl12oel6sp73sx337uqp.statements[123]++;
    }
    
}

class CodeCoverCoverageCounter$hl12oel6sp73sx337uqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$hl12oel6sp73sx337uqp ());
  }
    public static long[] statements = new long[124];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.RingPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 26; i++) {
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

  public CodeCoverCoverageCounter$hl12oel6sp73sx337uqp () {
    super("org.jfree.chart.plot.RingPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 123; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.RingPlot.java");
      for (int i = 1; i <= 123; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
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

