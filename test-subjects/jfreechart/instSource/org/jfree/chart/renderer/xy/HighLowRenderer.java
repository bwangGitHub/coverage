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
 * --------------------
 * HighLowRenderer.java
 * --------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1 (DG);
 * 23-Jan-2002 : Added DrawInfo parameter to drawItem() method (DG);
 * 28-Mar-2002 : Added a property change listener mechanism so that renderers 
 *               no longer need to be immutable (DG);
 * 09-Apr-2002 : Removed translatedRangeZero from the drawItem() method, and 
 *               changed the return type of the drawItem method to void, 
 *               reflecting a change in the XYItemRenderer interface.  Added 
 *               tooltip code to drawItem() method (DG);
 * 05-Aug-2002 : Small modification to drawItem method to support URLs for 
 *               HTML image maps (RA);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 31-Jul-2003 : Deprecated constructor (DG);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 29-Jan-2004 : Fixed bug (882392) when rendering with 
 *               PlotOrientation.HORIZONTAL (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed 
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 01-Nov-2005 : Added optional openTickPaint and closeTickPaint settings (DG);
 * ------------- JFREECHART 1.0.0 ---------------------------------------------
 * 06-Jul-2006 : Replace dataset methods getX() --> getXValue() (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that draws high/low/open/close markers on an {@link XYPlot} 
 * (requires a {@link OHLCDataset}).  This renderer does not include code to 
 * calculate the crosshair point for the plot.
 */
public class HighLowRenderer extends AbstractXYItemRenderer
                             implements XYItemRenderer,
                                        Cloneable,
                                        PublicCloneable,
                                        Serializable {
  static {
    CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -8135673815876552516L;
  static {
    CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[1]++;
  }
    
    /** A flag that controls whether the open ticks are drawn. */
    private boolean drawOpenTicks;

    /** A flag that controls whether the close ticks are drawn. */
    private boolean drawCloseTicks;
    
    /** 
     * The paint used for the open ticks (if <code>null</code>, the series
     * paint is used instead).
     */
    private transient Paint openTickPaint;
    
    /** 
     * The paint used for the close ticks (if <code>null</code>, the series
     * paint is used instead).
     */
    private transient Paint closeTickPaint;

    /**
     * The default constructor.
     */
    public HighLowRenderer() {
        super();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[2]++;
        this.drawOpenTicks = true;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[3]++;
        this.drawCloseTicks = true;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[4]++;
    }

    /**
     * Returns the flag that controls whether open ticks are drawn.
     * 
     * @return A boolean.
     */
    public boolean getDrawOpenTicks() {
        return this.drawOpenTicks;
    }
    
    /**
     * Sets the flag that controls whether open ticks are drawn, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param draw  the flag.
     */
    public void setDrawOpenTicks(boolean draw) {
        this.drawOpenTicks = draw;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[5]++;
        fireChangeEvent();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[6]++;
    }
    
    /**
     * Returns the flag that controls whether close ticks are drawn.
     * 
     * @return A boolean.
     */
    public boolean getDrawCloseTicks() {
        return this.drawCloseTicks;
    }
    
    /**
     * Sets the flag that controls whether close ticks are drawn, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param draw  the flag.
     */
    public void setDrawCloseTicks(boolean draw) {
        this.drawCloseTicks = draw;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[7]++;
        fireChangeEvent();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[8]++;
    }
    
    /**
     * Returns the paint used to draw the ticks for the open values.
     * 
     * @return The paint used to draw the ticks for the open values (possibly 
     *         <code>null</code>).
     */
    public Paint getOpenTickPaint() {
        return this.openTickPaint;
    }
    
    /**
     * Sets the paint used to draw the ticks for the open values and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  If you set
     * this to <code>null</code> (the default), the series paint is used 
     * instead.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     */
    public void setOpenTickPaint(Paint paint) {
        this.openTickPaint = paint;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[9]++;
        fireChangeEvent();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[10]++;
    }
    
    /**
     * Returns the paint used to draw the ticks for the close values.
     * 
     * @return The paint used to draw the ticks for the close values (possibly 
     *         <code>null</code>).
     */
    public Paint getCloseTickPaint() {
        return this.closeTickPaint;
    }
    
    /**
     * Sets the paint used to draw the ticks for the close values and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  If you set
     * this to <code>null</code> (the default), the series paint is used 
     * instead.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     */
    public void setCloseTickPaint(Paint paint) {
        this.closeTickPaint = paint;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[11]++;
        fireChangeEvent();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[12]++;
    }
    
    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         XYItemRendererState state,
                         Rectangle2D dataArea,
                         PlotRenderingInfo info,
                         XYPlot plot,
                         ValueAxis domainAxis,
                         ValueAxis rangeAxis,
                         XYDataset dataset,
                         int series,
                         int item,
                         CrosshairState crosshairState,
                         int pass) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[13]++;

        double x = dataset.getXValue(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((domainAxis.getRange().contains(x)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[1]++;
            return;
    // the x value is not within the axis range
        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[2]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[15]++;
        double xx = domainAxis.valueToJava2D(x, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[16]++;
        
        // setup for collecting optional entity info...
        Shape entityArea = null;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[17]++;
        EntityCollection entities = null;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[3]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[19]++;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[4]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[20]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[21]++;
        RectangleEdge location = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[22]++;

        Paint itemPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[23]++;
        Stroke itemStroke = getItemStroke(series, item);
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[24]++;
        g2.setStroke(itemStroke);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[25]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;
        
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof OHLCDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[5]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[27]++;
            OHLCDataset hld = (OHLCDataset) dataset;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[28]++;
            
            double yHigh = hld.getHighValue(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[29]++;
            double yLow = hld.getLowValue(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((Double.isNaN(yHigh)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(yLow)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[7]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[31]++;
                double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, 
                        location);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[32]++;
                double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, 
                        location);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[9]++;
                    g2.draw(new Line2D.Double(yyLow, xx, yyHigh, xx));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[34]++;
                    entityArea = new Rectangle2D.Double(Math.min(yyLow, yyHigh),
                            xx - 1.0, Math.abs(yyHigh - yyLow), 2.0);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[35]++;

                }
                else {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[10]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[36]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[11]++;
                    g2.draw(new Line2D.Double(xx, yyLow, xx, yyHigh));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[37]++;   
                    entityArea = new Rectangle2D.Double(xx - 1.0, 
                            Math.min(yyLow, yyHigh), 2.0,  
                            Math.abs(yyHigh - yyLow));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[38]++;

                } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[12]++;}
}

            } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[8]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[39]++;
            
            double delta = 2.0;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[40]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((domainAxis.isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[13]++;
                delta = -delta;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[41]++;

            } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[14]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getDrawOpenTicks()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[15]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[43]++;
                double yOpen = hld.getOpenValue(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Double.isNaN(yOpen)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[17]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[45]++;
                    double yyOpen = rangeAxis.valueToJava2D(yOpen, dataArea, 
                            location);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.openTickPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[19]++;
                        g2.setPaint(this.openTickPaint);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[47]++;

                    }
                    else {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[20]++;
                        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[48]++;
                    }
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
                    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[21]++;
                        g2.draw(new Line2D.Double(yyOpen, xx + delta, yyOpen, 
                                xx));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[50]++;
   
                    }
                    else {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[22]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[51]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[23]++;
                        g2.draw(new Line2D.Double(xx - delta, yyOpen, xx, 
                                yyOpen));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[52]++;
   
                    } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[24]++;}
}

                } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[18]++;}

            } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[16]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[53]++;
int CodeCoverConditionCoverageHelper_C13;
            
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((getDrawCloseTicks()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[25]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[54]++;
                double yClose = hld.getCloseValue(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[55]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Double.isNaN(yClose)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[27]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[56]++;
                    double yyClose = rangeAxis.valueToJava2D(
                        yClose, dataArea, location);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.closeTickPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[29]++;
                        g2.setPaint(this.closeTickPaint);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[58]++;

                    }
                    else {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[30]++;
                        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[59]++;
                    }
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[31]++;
                        g2.draw(new Line2D.Double(yyClose, xx, yyClose, 
                                xx - delta));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[61]++;
   
                    }
                    else {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[32]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[62]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[33]++;
                        g2.draw(new Line2D.Double(xx, yyClose, xx + delta, 
                                yyClose));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[63]++;
   
                    } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[34]++;}
}

                } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[28]++;}

            } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[26]++;}

  
        }
        else {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[6]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[64]++;
int CodeCoverConditionCoverageHelper_C18;
            // not a HighLowDataset, so just draw a line connecting this point 
            // with the previous point...
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[35]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[65]++;
                double x0 = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[66]++;
                double y0 = dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[67]++;
                double y = dataset.getYValue(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((Double.isNaN(x0)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((Double.isNaN(y)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[37]++;
                    return;

                } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[38]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[69]++;
                double xx0 = domainAxis.valueToJava2D(x0, dataArea, 
                        plot.getDomainAxisEdge());
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[70]++;
                double yy0 = rangeAxis.valueToJava2D(y0, dataArea, location);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[71]++;
                double yy = rangeAxis.valueToJava2D(y, dataArea, location);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[72]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[39]++;
                    g2.draw(new Line2D.Double(yy0, xx0, yy, xx));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[73]++;

                }
                else {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[40]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[74]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[41]++;
                    g2.draw(new Line2D.Double(xx0, yy0, xx, yy));
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[75]++;

                } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[42]++;}
}

            } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[36]++;}
        }
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[76]++;
int CodeCoverConditionCoverageHelper_C22;
        
        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[43]++;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[77]++;
            String tip = null;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[78]++;
            XYToolTipGenerator generator = getToolTipGenerator(series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[79]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[45]++;
                tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[80]++;

            } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[46]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[81]++;
            String url = null;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[82]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[47]++;
                url = getURLGenerator().generateURL(dataset, series, item);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[83]++;

            } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[48]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[84]++;
            XYItemEntity entity = new XYItemEntity(entityArea, dataset, 
                    series, item, tip, url);
            entities.add(entity);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[85]++;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[44]++;}

    }
    
    /**
     * Returns a clone of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[86]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[49]++;
            return true;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[50]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[87]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj instanceof HighLowRenderer) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[52]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[88]++;
        HighLowRenderer that = (HighLowRenderer) obj;
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[89]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.drawOpenTicks != that.drawOpenTicks) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[54]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[90]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.drawCloseTicks != that.drawCloseTicks) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[56]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[91]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.openTickPaint, that.openTickPaint)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[58]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[92]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.closeTickPaint, that.closeTickPaint)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[60]++;}
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[93]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.branches[62]++;}
        return true;
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
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[94]++;
        this.openTickPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[95]++;
        this.closeTickPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[96]++;
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
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[97]++;
        SerialUtilities.writePaint(this.openTickPaint, stream);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[98]++;
        SerialUtilities.writePaint(this.closeTickPaint, stream);
CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl.statements[99]++;
    }

}

class CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl ());
  }
    public static long[] statements = new long[100];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.HighLowRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
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

  public CodeCoverCoverageCounter$8gi7p2ilcimw9anzi6ohx2rw0geu2jl () {
    super("org.jfree.chart.renderer.xy.HighLowRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 99; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.HighLowRenderer.java");
      for (int i = 1; i <= 99; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
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

