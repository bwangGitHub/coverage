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
 * PiePlot3D.java
 * --------------
 * (C) Copyright 2000-2007, by Object Refinery and Contributors.
 *
 * Original Author:  Tomer Peretz;
 * Contributor(s):   Richard Atkinson;
 *                   David Gilbert (for Object Refinery Limited);
 *                   Xun Kang;
 *                   Christian W. Zuckschwerdt;
 *                   Arnaud Lelievre;
 *                   Dave Crane;
 *
 * Changes
 * -------
 * 21-Jun-2002 : Version 1;
 * 31-Jul-2002 : Modified to use startAngle and direction, drawing modified so 
 *               that charts render with foreground alpha < 1.0 (DG);
 * 05-Aug-2002 : Small modification to draw method to support URLs for HTML 
 *               image maps (RA);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 18-Oct-2002 : Added drawing bug fix sent in by Xun Kang, and made a couple 
 *               of other related fixes (DG);
 * 30-Oct-2002 : Changed the PieDataset interface. Fixed another drawing 
 *               bug (DG);
 * 12-Nov-2002 : Fixed null pointer exception for zero or negative values (DG);
 * 07-Mar-2003 : Modified to pass pieIndex on to PieSectionEntity (DG);
 * 21-Mar-2003 : Added workaround for bug id 620031 (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 29-Aug-2003 : Small changes for API updates in PiePlot class (DG);
 * 02-Sep-2003 : Fixed bug where the 'no data' message is not displayed (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL); 
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 20-Nov-2003 : Fixed bug 845289 (sides not showing) (DG);
 * 25-Nov-2003 : Added patch (845095) to fix outline paint issues (DG);
 * 10-Mar-2004 : Numerous changes to enhance labelling (DG);
 * 31-Mar-2004 : Adjusted plot area when label generator is null (DG);
 * 08-Apr-2004 : Added flag to PiePlot class to control the treatment of null 
 *               values (DG);
 *               Added pieIndex to PieSectionEntity (DG);
 * 15-Nov-2004 : Removed creation of default tool tip generator (DG);
 * 16-Jun-2005 : Added default constructor (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 27-Sep-2006 : Updated draw() method for new lookup methods (DG);
 * 22-Mar-2007 : Added equals() override (DG);
 * 18-Jun-2007 : Added handling for simple label option (DG);
 * 04-Oct-2007 : Added option to darken sides of plot - thanks to Alex Moots 
 *               (see patch 1805262) (DG);
 * 21-Nov-2007 : Changed default depth factor, fixed labelling bugs and added
 *               debug code - see debug flags in PiePlot class (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;

/**
 * A plot that displays data in the form of a 3D pie chart, using data from
 * any class that implements the {@link PieDataset} interface.
 * <P>
 * Although this class extends {@link PiePlot}, it does not currently support
 * exploded sections.
 */
public class PiePlot3D extends PiePlot implements Serializable {
  static {
    CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3408984188945161432L;
  static {
    CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[1]++;
  }
    
    /** The factor of the depth of the pie from the plot height */
    private double depthFactor = 0.12;
  {
    CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[2]++;
  }

    /** 
     * A flag that controls whether or not the sides of the pie chart
     * are rendered using a darker colour.
     * 
     *  @since 1.0.7.
     */
    private boolean darkerSides = false;
  {
    CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[3]++;
  }  // default preserves previous 
                                          // behaviour
    
    /**
     * Creates a new instance with no dataset.
     */
    public PiePlot3D() {
        this(null);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[4]++;   
    }
    
    /**
     * Creates a pie chart with a three dimensional effect using the specified 
     * dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public PiePlot3D(PieDataset dataset) {
        super(dataset);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[5]++;
        setCircular(false, false);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[6]++;
    }

    /**
     * Returns the depth factor for the chart.
     *
     * @return The depth factor.
     * 
     * @see #setDepthFactor(double)
     */
    public double getDepthFactor() {
        return this.depthFactor;
    }

    /**
     * Sets the pie depth as a percentage of the height of the plot area, and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param factor  the depth factor (for example, 0.20 is twenty percent).
     * 
     * @see #getDepthFactor()
     */
    public void setDepthFactor(double factor) {
        this.depthFactor = factor;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[7]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[8]++;
    }

    /**
     * Returns a flag that controls whether or not the sides of the pie chart
     * are rendered using a darker colour.  This is only applied if the
     * section colour is an instance of {@link java.awt.Color}.
     *
     * @return A boolean.
     * 
     * @see #setDarkerSides(boolean)
     * 
     * @since 1.0.7
     */
    public boolean getDarkerSides() {
        return this.darkerSides;
    }

    /**
     * Sets a flag that controls whether or not the sides of the pie chart
     * are rendered using a darker colour, and sends a {@link PlotChangeEvent} 
     * to all registered listeners.  This is only applied if the
     * section colour is an instance of {@link java.awt.Color}.
     *
     * @param darker true to darken the sides, false to use the default 
     *         behaviour.
     * 
     * @see #getDarkerSides()
     * 
     * @since 1.0.7.
     */
    public void setDarkerSides(boolean darker) {
        this.darkerSides = darker;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[9]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[10]++;
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).  This method is called by the 
     * {@link org.jfree.chart.JFreeChart} class, you don't normally need 
     * to call it yourself.
     *
     * @param g2  the graphics device.
     * @param plotArea  the area within which the plot should be drawn.
     * @param anchor  the anchor point.
     * @param parentState  the state from the parent plot, if there is one.
     * @param info  collects info about the drawing 
     *              (<code>null</code> permitted).
     */
    public void draw(Graphics2D g2, Rectangle2D plotArea, Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[11]++;

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(plotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[12]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[13]++;

        Rectangle2D originalPlotArea = (Rectangle2D) plotArea.clone();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[1]++;
            info.setPlotArea(plotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[15]++;
            info.setDataArea(plotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[16]++;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[2]++;}

        drawBackground(g2, plotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[17]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[18]++;

        Shape savedClip = g2.getClip();
        g2.clip(plotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[19]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[20]++;

        // adjust the plot area by the interior spacing value
        double gapPercent = getInteriorGap();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[21]++;
        double labelPercent = 0.0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getLabelGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[3]++;
            labelPercent = getLabelGap() + getMaximumLabelWidth();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[23]++;
   
        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[4]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[24]++;
        double gapHorizontal = plotArea.getWidth() * (gapPercent 
                + labelPercent) * 2.0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[25]++;
        double gapVertical = plotArea.getHeight() * gapPercent * 2.0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((DEBUG_DRAW_INTERIOR) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[5]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[27]++;
            double hGap = plotArea.getWidth() * getInteriorGap();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[28]++;
            double vGap = plotArea.getHeight() * getInteriorGap();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[29]++;
            double igx1 = plotArea.getX() + hGap;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[30]++;
            double igx2 = plotArea.getMaxX() - hGap;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[31]++;
            double igy1 = plotArea.getY() + vGap;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[32]++;
            double igy2 = plotArea.getMaxY() - vGap;
            g2.setPaint(Color.lightGray);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[33]++;
            g2.draw(new Rectangle2D.Double(igx1, igy1, igx2 - igx1, 
                    igy2 - igy1));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[34]++;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[6]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[35]++;

        double linkX = plotArea.getX() + gapHorizontal / 2;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[36]++;
        double linkY = plotArea.getY() + gapVertical / 2;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[37]++;
        double linkW = plotArea.getWidth() - gapHorizontal;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[38]++;
        double linkH = plotArea.getHeight() - gapVertical;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[39]++;
int CodeCoverConditionCoverageHelper_C4;
        
        // make the link area a square if the pie chart is to be circular...
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isCircular()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[7]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[40]++; // is circular?
            double min = Math.min(linkW, linkH) / 2;
            linkX = (linkX + linkX + linkW) / 2 - min;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[41]++;
            linkY = (linkY + linkY + linkH) / 2 - min;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[42]++;
            linkW = 2 * min;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[43]++;
            linkH = 2 * min;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[44]++;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[8]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[45]++;
        
        PiePlotState state = initialise(g2, plotArea, this, null, info);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[46]++;

        // the link area defines the dog leg points for the linking lines to 
        // the labels
        Rectangle2D linkAreaXX = new Rectangle2D.Double(linkX, linkY, linkW, 
                linkH * (1 - this.depthFactor));
        state.setLinkArea(linkAreaXX);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[47]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[48]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((DEBUG_DRAW_LINK_AREA) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[9]++;
            g2.setPaint(Color.blue);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[49]++;
            g2.draw(linkAreaXX);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[50]++;
            g2.setPaint(Color.yellow);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[51]++;
            g2.draw(new Ellipse2D.Double(linkAreaXX.getX(), linkAreaXX.getY(), 
                    linkAreaXX.getWidth(), linkAreaXX.getHeight()));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[52]++;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[10]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[53]++;
        
        // the explode area defines the max circle/ellipse for the exploded pie 
        // sections.
        // it is defined by shrinking the linkArea by the linkMargin factor.
        double hh = linkW * getLabelLinkMargin();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[54]++;
        double vv = linkH * getLabelLinkMargin();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[55]++;
        Rectangle2D explodeArea = new Rectangle2D.Double(linkX + hh / 2.0, 
                linkY + vv / 2.0, linkW - hh, linkH - vv);
       
        state.setExplodedPieArea(explodeArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[56]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[57]++;
        
        // the pie area defines the circle/ellipse for regular pie sections.
        // it is defined by shrinking the explodeArea by the explodeMargin 
        // factor. 
        double maximumExplodePercent = getMaximumExplodePercent();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[58]++;
        double percent = maximumExplodePercent / (1.0 + maximumExplodePercent);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[59]++;
        
        double h1 = explodeArea.getWidth() * percent;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[60]++;
        double v1 = explodeArea.getHeight() * percent;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[61]++;
        Rectangle2D pieArea = new Rectangle2D.Double(explodeArea.getX() 
                + h1 / 2.0, explodeArea.getY() + v1 / 2.0,
                explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[62]++;

        // the link area defines the dog-leg point for the linking lines to 
        // the labels
        int depth = (int) (pieArea.getHeight() * this.depthFactor);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[63]++;
        Rectangle2D linkArea = new Rectangle2D.Double(linkX, linkY, linkW, 
                linkH - depth);
        state.setLinkArea(linkArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[64]++;   

        state.setPieArea(pieArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[65]++;
        state.setPieCenterX(pieArea.getCenterX());
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[66]++;
        state.setPieCenterY(pieArea.getCenterY() - depth / 2.0);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[67]++;
        state.setPieWRadius(pieArea.getWidth() / 2.0);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[68]++;
        state.setPieHRadius((pieArea.getHeight() - depth) / 2.0);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[69]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[70]++;

        // get the data source - return if null;
        PieDataset dataset = getDataset();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[71]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((DatasetUtilities.isEmptyOrNull(getDataset())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[11]++;
            drawNoDataMessage(g2, plotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[72]++;
            g2.setClip(savedClip);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[73]++;
            drawOutline(g2, plotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[74]++;
            return;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[12]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[75]++;
int CodeCoverConditionCoverageHelper_C7;

        // if too any elements
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dataset.getKeys().size() > plotArea.getWidth()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[13]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[76]++;
            String text = "Too many elements";
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[77]++;
            Font sfont = new Font("dialog", Font.BOLD, 10);
            g2.setFont(sfont);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[78]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[79]++;
            FontMetrics fm = g2.getFontMetrics(sfont);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[80]++;
            int stringWidth = fm.stringWidth(text);

            g2.drawString(text, (int) (plotArea.getX() + (plotArea.getWidth() 
                    - stringWidth) / 2), (int) (plotArea.getY() 
                    + (plotArea.getHeight() / 2)));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[81]++;
            return;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[14]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[82]++;
int CodeCoverConditionCoverageHelper_C8;
        // if we are drawing a perfect circle, we need to readjust the top left
        // coordinates of the drawing area for the arcs to arrive at this
        // effect.
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isCircular()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[15]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[83]++;
            double min = Math.min(plotArea.getWidth(), 
                    plotArea.getHeight()) / 2;
            plotArea = new Rectangle2D.Double(plotArea.getCenterX() - min, 
                    plotArea.getCenterY() - min, 2 * min, 2 * min);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[84]++;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[16]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[85]++;
        // get a list of keys...
        List sectionKeys = dataset.getKeys();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[86]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sectionKeys.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[17]++;
            return;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[18]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[87]++;

        // establish the coordinates of the top left corner of the drawing area
        double arcX = pieArea.getX();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[88]++;
        double arcY = pieArea.getY();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[89]++;

        //g2.clip(clipArea);
        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                getForegroundAlpha()));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[90]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[91]++;

        double totalValue = DatasetUtilities.calculatePieDatasetTotal(dataset);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[92]++;
        double runningTotal = 0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[93]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((depth < 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[19]++;
            return;
  // if depth is negative don't draw anything
        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[20]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[94]++;

        ArrayList arcList = new ArrayList();
        Arc2D.Double arc;
        Paint paint;
        Paint outlinePaint;
        Stroke outlineStroke;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[95]++;

        Iterator iterator = sectionKeys.iterator();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[96]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
        while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[1]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[2]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[3]++;
}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[97]++;

            Comparable currentKey = (Comparable) iterator.next();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[98]++;
            Number dataValue = dataset.getValue(currentKey);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[99]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[21]++;
                arcList.add(null);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[100]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[101]++;
                continue;

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[22]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[102]++;
            double value = dataValue.doubleValue();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[103]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value <= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[23]++;
                arcList.add(null);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[104]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[105]++;
                continue;

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[24]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[106]++;
            double startAngle = getStartAngle();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[107]++;
            double direction = getDirection().getFactor();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[108]++;
            double angle1 = startAngle + (direction * (runningTotal * 360)) 
                    / totalValue;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[109]++;
            double angle2 = startAngle + (direction * (runningTotal + value) 
                    * 360) / totalValue;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[110]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Math.abs(angle2 - angle1) > getMinimumArcAngleToDraw()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[25]++;
                arcList.add(new Arc2D.Double(arcX, arcY + depth, 
                        pieArea.getWidth(), pieArea.getHeight() - depth,
                        angle1, angle2 - angle1, Arc2D.PIE));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[111]++;

            }
            else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[26]++;
                arcList.add(null);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[112]++;
            }
            runningTotal += value;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[113]++;
        }
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[114]++;

        Shape oldClip = g2.getClip();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[115]++;

        Ellipse2D top = new Ellipse2D.Double(pieArea.getX(), pieArea.getY(), 
                pieArea.getWidth(), pieArea.getHeight() - depth);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[116]++;

        Ellipse2D bottom = new Ellipse2D.Double(pieArea.getX(), pieArea.getY() 
                + depth, pieArea.getWidth(), pieArea.getHeight() - depth);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[117]++;

        Rectangle2D lower = new Rectangle2D.Double(top.getX(), 
                top.getCenterY(), pieArea.getWidth(), bottom.getMaxY() 
                - top.getCenterY());
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[118]++;

        Rectangle2D upper = new Rectangle2D.Double(pieArea.getX(), top.getY(), 
                pieArea.getWidth(), bottom.getCenterY() - top.getY());
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[119]++;

        Area a = new Area(top);
        a.add(new Area(lower));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[120]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[121]++;
        Area b = new Area(bottom);
        b.add(new Area(upper));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[122]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[123]++;
        Area pie = new Area(a);
        pie.intersect(b);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[124]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[125]++;

        Area front = new Area(pie);
        front.subtract(new Area(top));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[126]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[127]++;

        Area back = new Area(pie);
        back.subtract(new Area(bottom));
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[128]++;

        // draw the bottom circle
        int[] xs;
        int[] ys;
        arc = new Arc2D.Double(arcX, arcY + depth, pieArea.getWidth(), 
                pieArea.getHeight() - depth, 0, 360, Arc2D.PIE);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[129]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[130]++;

        int categoryCount = arcList.size();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[131]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[4]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int categoryIndex = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((categoryIndex < categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); 
                 categoryIndex++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[4]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[5]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[6]++;
}
            arc = (Arc2D.Double) arcList.get(categoryIndex);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[132]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[133]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((arc == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[27]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[134]++;
                continue;

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[28]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[135]++;
            Comparable key = getSectionKey(categoryIndex);
            paint = lookupSectionPaint(key, true);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[136]++;
            outlinePaint = lookupSectionOutlinePaint(key);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[137]++;
            outlineStroke = lookupSectionOutlineStroke(key);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[138]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[139]++;
            g2.fill(arc);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[140]++;
            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[141]++;
            g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[142]++;
            g2.draw(arc);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[143]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[144]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[145]++;

            Point2D p1 = arc.getStartPoint();

            // draw the height
            xs = new int[] {(int) arc.getCenterX(), (int) arc.getCenterX(),
                    (int) p1.getX(), (int) p1.getX()};
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[146]++;
            ys = new int[] {(int) arc.getCenterY(), (int) arc.getCenterY() 
                    - depth, (int) p1.getY() - depth, (int) p1.getY()};
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[147]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[148]++;
            Polygon polygon = new Polygon(xs, ys, 4);
            g2.setPaint(java.awt.Color.lightGray);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[149]++;
            g2.fill(polygon);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[150]++;
            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[151]++;
            g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[152]++;
            g2.draw(polygon);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[153]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[154]++;

        }

        g2.setPaint(Color.gray);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[155]++;
        g2.fill(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[156]++;
        g2.fill(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[157]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[158]++;

        // cycle through once drawing only the sides at the back...
        int cat = 0;
        iterator = arcList.iterator();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[159]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[160]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[7]++;


int CodeCoverConditionCoverageHelper_C17;
        while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[7]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[8]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[9]++;
}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[161]++;
            Arc2D segment = (Arc2D) iterator.next();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[162]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((segment != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[29]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[163]++;
                Comparable key = getSectionKey(cat);
                paint = lookupSectionPaint(key, true);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[164]++;
                outlinePaint = lookupSectionOutlinePaint(key);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[165]++;
                outlineStroke = lookupSectionOutlineStroke(key);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[166]++;
                drawSide(g2, pieArea, segment, front, back, paint, 
                        outlinePaint, outlineStroke, false, true);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[167]++;

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[30]++;}
            cat++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[168]++;
        }

        // cycle through again drawing only the sides at the front...
        cat = 0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[169]++;
        iterator = arcList.iterator();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[170]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[171]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
        while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[10]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[11]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[12]++;
}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[172]++;
            Arc2D segment = (Arc2D) iterator.next();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[173]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((segment != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[31]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[174]++;
                Comparable key = getSectionKey(cat);
                paint = lookupSectionPaint(key);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[175]++;
                outlinePaint = lookupSectionOutlinePaint(key);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[176]++;
                outlineStroke = lookupSectionOutlineStroke(key);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[177]++;
                drawSide(g2, pieArea, segment, front, back, paint, 
                        outlinePaint, outlineStroke, true, false);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[178]++;

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[32]++;}
            cat++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[179]++;
        }

        g2.setClip(oldClip);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[180]++;

        // draw the sections at the top of the pie (and set up tooltips)...
        Arc2D upperArc;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[181]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[13]++;


int CodeCoverConditionCoverageHelper_C21;
        for (int sectionIndex = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((sectionIndex < categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); 
                 sectionIndex++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[13]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[14]--;
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.loops[15]++;
}
            arc = (Arc2D.Double) arcList.get(sectionIndex);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[182]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[183]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((arc == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[33]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[184]++;
                continue;

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[34]++;}
            upperArc = new Arc2D.Double(arcX, arcY, pieArea.getWidth(),
                    pieArea.getHeight() - depth, arc.getAngleStart(), 
                    arc.getAngleExtent(), Arc2D.PIE);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[185]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[186]++;
            
            Comparable currentKey = (Comparable) sectionKeys.get(sectionIndex);
            paint = lookupSectionPaint(currentKey, true);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[187]++;
            outlinePaint = lookupSectionOutlinePaint(currentKey);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[188]++;
            outlineStroke = lookupSectionOutlineStroke(currentKey);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[189]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[190]++;
            g2.fill(upperArc);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[191]++;
            g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[192]++;
            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[193]++;
            g2.draw(upperArc);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[194]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[195]++;
int CodeCoverConditionCoverageHelper_C23;

           // add a tooltip for the section...
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[35]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[196]++;
                EntityCollection entities 
                        = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[197]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[37]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[198]++;
                    String tip = null;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[199]++;
                    PieToolTipGenerator tipster = getToolTipGenerator();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[200]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[39]++;
                        // @mgs: using the method's return value was missing 
                        tip = tipster.generateToolTip(dataset, currentKey);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[201]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[40]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[202]++;
                    String url = null;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[203]++;
int CodeCoverConditionCoverageHelper_C26;
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[41]++;
                        url = getURLGenerator().generateURL(dataset, currentKey,
                                getPieIndex());
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[204]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[42]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[205]++;
                    PieSectionEntity entity = new PieSectionEntity(
                            upperArc, dataset, getPieIndex(), sectionIndex, 
                            currentKey, tip, url);
                    entities.add(entity);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[206]++;

                } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[36]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[207]++;
            List keys = dataset.getKeys();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[208]++;
            Rectangle2D adjustedPlotArea = new Rectangle2D.Double(
                    originalPlotArea.getX(), originalPlotArea.getY(), 
                    originalPlotArea.getWidth(), originalPlotArea.getHeight() 
                    - depth);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[209]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((getSimpleLabels()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[43]++;
                drawSimpleLabels(g2, keys, totalValue, adjustedPlotArea, 
                        linkArea, state);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[210]++;

            }
            else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[44]++;
                drawLabels(g2, keys, totalValue, adjustedPlotArea, linkArea, 
                        state);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[211]++;
            }
        }

        g2.setClip(savedClip);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[212]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[213]++;
        drawOutline(g2, originalPlotArea);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[214]++;

    }

    /**
     * Draws the side of a pie section.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param arc  the arc.
     * @param front  the front of the pie.
     * @param back  the back of the pie.
     * @param paint  the color.
     * @param outlinePaint  the outline paint.
     * @param outlineStroke  the outline stroke.
     * @param drawFront  draw the front?
     * @param drawBack  draw the back?
     */
    protected void drawSide(Graphics2D g2,
                            Rectangle2D plotArea, 
                            Arc2D arc, 
                            Area front, 
                            Area back,
                            Paint paint, 
                            Paint outlinePaint,
                            Stroke outlineStroke,
                            boolean drawFront, 
                            boolean drawBack) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[215]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((getDarkerSides()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[45]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[216]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((paint instanceof Color) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[47]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[217]++;
                Color c = (Color) paint;
                c = c.darker();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[218]++;
                paint = c;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[219]++;

            } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[48]++;}

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[46]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[220]++;

        double start = arc.getAngleStart();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[221]++;
        double extent = arc.getAngleExtent();
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[222]++;
        double end = start + extent;

        g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[223]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[224]++;
int CodeCoverConditionCoverageHelper_C30;
        
        // for CLOCKWISE charts, the extent will be negative...
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((extent < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[49]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[225]++;
int CodeCoverConditionCoverageHelper_C31;

            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isAngleAtFront(start)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[51]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[226]++;
int CodeCoverConditionCoverageHelper_C32;  // start at front

                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isAngleAtBack(end)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[53]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[227]++;
int CodeCoverConditionCoverageHelper_C33;

                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((extent > -180.0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[55]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[228]++;
int CodeCoverConditionCoverageHelper_C34;  // the segment is entirely at the 
                                            // front of the chart
                        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[57]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[229]++;
                            Area side = new Area(new Rectangle2D.Double(
                                    arc.getEndPoint().getX(), plotArea.getY(), 
                                    arc.getStartPoint().getX() 
                                    - arc.getEndPoint().getX(),
                                    plotArea.getHeight()));
                            side.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[230]++;
                            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[231]++;
                            g2.fill(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[232]++;
                            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[233]++;
                            g2.draw(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[234]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[58]++;}

                    }
                    else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[56]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[235]++;  // the segment starts at the front, and wraps all 
                            // the way around
                            // the back and finishes at the front again
                        Area side1 = new Area(new Rectangle2D.Double(
                                plotArea.getX(), plotArea.getY(),
                                arc.getStartPoint().getX() - plotArea.getX(), 
                                plotArea.getHeight()));
                        side1.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[236]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[237]++;

                        Area side2 = new Area(new Rectangle2D.Double(
                                arc.getEndPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getEndPoint().getX(),
                                plotArea.getHeight()));

                        side2.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[238]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[239]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[240]++;
int CodeCoverConditionCoverageHelper_C35;
                        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[59]++;
                            g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[241]++;
                            g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[242]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[60]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[243]++;
int CodeCoverConditionCoverageHelper_C36;

                        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[61]++;
                            g2.fill(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[244]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[62]++;}

                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[245]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[246]++;
int CodeCoverConditionCoverageHelper_C37;
                        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[63]++;
                            g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[247]++;
                            g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[248]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[64]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[249]++;
int CodeCoverConditionCoverageHelper_C38;

                        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[65]++;
                            g2.draw(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[250]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[66]++;}

                    }

                }
                else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[54]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[251]++;
int CodeCoverConditionCoverageHelper_C39;  // starts at the front, finishes at the back (going 
                        // around the left side)

                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[67]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[252]++;
                        Area side2 = new Area(new Rectangle2D.Double(
                                plotArea.getX(), plotArea.getY(),
                                arc.getEndPoint().getX() - plotArea.getX(), 
                                plotArea.getHeight()));
                        side2.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[253]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[254]++;
                        g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[255]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[256]++;
                        g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[257]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[68]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[258]++;
int CodeCoverConditionCoverageHelper_C40;

                    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[69]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[259]++;
                        Area side1 = new Area(new Rectangle2D.Double(
                                plotArea.getX(), plotArea.getY(),
                                arc.getStartPoint().getX() - plotArea.getX(),
                                plotArea.getHeight()));
                        side1.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[260]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[261]++;
                        g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[262]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[263]++;
                        g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[264]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[70]++;}
                }

            }
            else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[52]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[265]++;
int CodeCoverConditionCoverageHelper_C41;  // the segment starts at the back (still extending 
                    // CLOCKWISE)

                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isAngleAtFront(end)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[71]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[266]++;
int CodeCoverConditionCoverageHelper_C42;
                    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((extent > -180.0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[73]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[267]++;
int CodeCoverConditionCoverageHelper_C43;  // whole segment stays at the back
                        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[75]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[268]++;
                            Area side = new Area(new Rectangle2D.Double(
                                    arc.getStartPoint().getX(), plotArea.getY(),
                                    arc.getEndPoint().getX() 
                                    - arc.getStartPoint().getX(),
                                    plotArea.getHeight()));
                            side.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[269]++;
                            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[270]++;
                            g2.fill(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[271]++;
                            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[272]++;
                            g2.draw(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[273]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[76]++;}

                    }
                    else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[74]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[274]++;  // starts at the back, wraps around front, and 
                            // finishes at back again
                        Area side1 = new Area(new Rectangle2D.Double(
                                arc.getStartPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getStartPoint().getX(),
                                plotArea.getHeight()));
                        side1.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[275]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[276]++;

                        Area side2 = new Area(new Rectangle2D.Double(
                                plotArea.getX(), plotArea.getY(),
                                arc.getEndPoint().getX() - plotArea.getX(),
                                plotArea.getHeight()));

                        side2.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[277]++;

                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[278]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[279]++;
int CodeCoverConditionCoverageHelper_C44;
                        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[77]++;
                            g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[280]++;
                            g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[281]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[78]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[282]++;
int CodeCoverConditionCoverageHelper_C45;

                        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[79]++;
                            g2.fill(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[283]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[80]++;}

                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[284]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[285]++;
int CodeCoverConditionCoverageHelper_C46;
                        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[81]++;
                            g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[286]++;
                            g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[287]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[82]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[288]++;
int CodeCoverConditionCoverageHelper_C47;

                        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[83]++;
                            g2.draw(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[289]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[84]++;}

                    }

                }
                else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[72]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[290]++;
int CodeCoverConditionCoverageHelper_C48;  // starts at back, finishes at front (CLOCKWISE)

                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[85]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[291]++;
                        Area side1 = new Area(new Rectangle2D.Double(
                                arc.getStartPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getStartPoint().getX(),
                                plotArea.getHeight()));
                        side1.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[292]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[293]++;
                        g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[294]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[295]++;
                        g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[296]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[86]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[297]++;
int CodeCoverConditionCoverageHelper_C49;

                    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[87]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[298]++;
                        Area side2 = new Area(new Rectangle2D.Double(
                                arc.getEndPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getEndPoint().getX(),
                                plotArea.getHeight()));
                        side2.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[299]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[300]++;
                        g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[301]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[302]++;
                        g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[303]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[88]++;}

                }
            }

        }
        else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[50]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[304]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((extent > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[89]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[305]++;
int CodeCoverConditionCoverageHelper_C51;  // the pie sections are arranged ANTICLOCKWISE

            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((isAngleAtFront(start)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[91]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[306]++;
int CodeCoverConditionCoverageHelper_C52;  // segment starts at the front

                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((isAngleAtBack(end)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[93]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[307]++;
int CodeCoverConditionCoverageHelper_C53;  // and finishes at the front

                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((extent < 180.0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[95]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[308]++;
int CodeCoverConditionCoverageHelper_C54;  // segment only occupies the front
                        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[97]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[309]++;
                            Area side = new Area(new Rectangle2D.Double(
                                    arc.getStartPoint().getX(), plotArea.getY(),
                                    arc.getEndPoint().getX() 
                                    - arc.getStartPoint().getX(),
                                    plotArea.getHeight()));
                            side.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[310]++;
                            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[311]++;
                            g2.fill(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[312]++;
                            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[313]++;
                            g2.draw(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[314]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[98]++;}

                    }
                    else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[96]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[315]++;  // segments wraps right around the back...
                        Area side1 = new Area(new Rectangle2D.Double(
                                arc.getStartPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getStartPoint().getX(),
                                plotArea.getHeight()));
                        side1.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[316]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[317]++;

                        Area side2 = new Area(new Rectangle2D.Double(
                                plotArea.getX(), plotArea.getY(),
                                arc.getEndPoint().getX() - plotArea.getX(),
                                plotArea.getHeight()));
                        side2.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[318]++;

                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[319]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[320]++;
int CodeCoverConditionCoverageHelper_C55;
                        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[99]++;
                            g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[321]++;
                            g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[322]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[100]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[323]++;
int CodeCoverConditionCoverageHelper_C56;

                        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[101]++;
                            g2.fill(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[324]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[102]++;}

                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[325]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[326]++;
int CodeCoverConditionCoverageHelper_C57;
                        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[103]++;
                            g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[327]++;
                            g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[328]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[104]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[329]++;
int CodeCoverConditionCoverageHelper_C58;

                        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[105]++;
                            g2.draw(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[330]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[106]++;}

                    }

                }
                else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[94]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[331]++;
int CodeCoverConditionCoverageHelper_C59;  // segments starts at front and finishes at back...
                    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[107]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[332]++;
                        Area side2 = new Area(new Rectangle2D.Double(
                                arc.getEndPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getEndPoint().getX(),
                                plotArea.getHeight()));
                        side2.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[333]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[334]++;
                        g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[335]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[336]++;
                        g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[337]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[108]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[338]++;
int CodeCoverConditionCoverageHelper_C60;

                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[109]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[339]++;
                        Area side1 = new Area(new Rectangle2D.Double(
                                arc.getStartPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getStartPoint().getX(),
                                plotArea.getHeight()));
                        side1.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[340]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[341]++;
                        g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[342]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[343]++;
                        g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[344]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[110]++;}
                }

            }
            else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[92]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[345]++;
int CodeCoverConditionCoverageHelper_C61;  // segment starts at back

                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((isAngleAtFront(end)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[111]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[346]++;
int CodeCoverConditionCoverageHelper_C62;
                    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((extent < 180.0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[113]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[347]++;
int CodeCoverConditionCoverageHelper_C63;  // and finishes at back
                        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[115]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[348]++;
                            Area side = new Area(new Rectangle2D.Double(
                                    arc.getEndPoint().getX(), plotArea.getY(),
                                    arc.getStartPoint().getX() 
                                    - arc.getEndPoint().getX(),
                                    plotArea.getHeight()));
                            side.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[349]++;
                            g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[350]++;
                            g2.fill(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[351]++;
                            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[352]++;
                            g2.draw(side);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[353]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[116]++;}

                    }
                    else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[114]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[354]++;  // starts at back and wraps right around to the 
                            // back again
                        Area side1 = new Area(new Rectangle2D.Double(
                                arc.getStartPoint().getX(), plotArea.getY(),
                                plotArea.getX() - arc.getStartPoint().getX(),
                                plotArea.getHeight()));
                        side1.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[355]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[356]++;

                        Area side2 = new Area(new Rectangle2D.Double(
                                arc.getEndPoint().getX(), plotArea.getY(),
                                plotArea.getMaxX() - arc.getEndPoint().getX(),
                                plotArea.getHeight()));
                        side2.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[357]++;

                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[358]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[359]++;
int CodeCoverConditionCoverageHelper_C64;
                        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[117]++;
                            g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[360]++;
                            g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[361]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[118]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[362]++;
int CodeCoverConditionCoverageHelper_C65;

                        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[119]++;
                            g2.fill(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[363]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[120]++;}

                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[364]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[365]++;
int CodeCoverConditionCoverageHelper_C66;
                        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[121]++;
                            g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[366]++;
                            g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[367]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[122]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[368]++;
int CodeCoverConditionCoverageHelper_C67;

                        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[123]++;
                            g2.draw(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[369]++;

                        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[124]++;}

                    }

                }
                else {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[112]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[370]++;
int CodeCoverConditionCoverageHelper_C68;  // starts at the back and finishes at the front 
                        // (wrapping the left side)
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((drawBack) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[125]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[371]++;
                        Area side1 = new Area(new Rectangle2D.Double(
                                plotArea.getX(), plotArea.getY(),
                                arc.getStartPoint().getX() - plotArea.getX(),
                                plotArea.getHeight()));
                        side1.intersect(back);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[372]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[373]++;
                        g2.fill(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[374]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[375]++;
                        g2.draw(side1);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[376]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[126]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[377]++;
int CodeCoverConditionCoverageHelper_C69;

                    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((drawFront) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[127]++;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[378]++;
                        Area side2 = new Area(new Rectangle2D.Double(
                                plotArea.getX(), plotArea.getY(),
                                arc.getEndPoint().getX() - plotArea.getX(),
                                plotArea.getHeight()));
                        side2.intersect(front);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[379]++;
                        g2.setPaint(paint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[380]++;
                        g2.fill(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[381]++;
                        g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[382]++;
                        g2.draw(side2);
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[383]++;

                    } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[128]++;}
                }
            }


        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[90]++;}
}

    }

    /**
     * Returns a short string describing the type of plot.
     *
     * @return <i>Pie 3D Plot</i>.
     */
    public String getPlotType() {
        return localizationResources.getString("Pie_3D_Plot");
    }

    /**
     * A utility method that returns true if the angle represents a point at 
     * the front of the 3D pie chart.  0 - 180 degrees is the back, 180 - 360 
     * is the front.
     *
     * @param angle  the angle.
     *
     * @return A boolean.
     */
    private boolean isAngleAtFront(double angle) {
        return (Math.sin(Math.toRadians(angle)) < 0.0);
    }

    /**
     * A utility method that returns true if the angle represents a point at 
     * the back of the 3D pie chart.  0 - 180 degrees is the back, 180 - 360 
     * is the front.
     *
     * @param angle  the angle.
     *
     * @return <code>true</code> if the angle is at the back of the pie.
     */
    private boolean isAngleAtBack(double angle) {
        return (Math.sin(Math.toRadians(angle)) > 0.0);
    }
    
    /**
     * Tests this plot for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[384]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[129]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[130]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[385]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((obj instanceof PiePlot3D) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[131]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[132]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[386]++;
        PiePlot3D that = (PiePlot3D) obj;
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[387]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((this.depthFactor != that.depthFactor) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[133]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[134]++;}
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.statements[388]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((this.darkerSides != that.darkerSides) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[135]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x.branches[136]++;}
        return super.equals(obj);
    }

}

class CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x ());
  }
    public static long[] statements = new long[389];
    public static long[] branches = new long[137];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[74];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.PiePlot3D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 73; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$3e0ajjykj1dnauov9wkk0x () {
    super("org.jfree.chart.plot.PiePlot3D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 388; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 136; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 73; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.PiePlot3D.java");
      for (int i = 1; i <= 388; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 136; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 73; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

