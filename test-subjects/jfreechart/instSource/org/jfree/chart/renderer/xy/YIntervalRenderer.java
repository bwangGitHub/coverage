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
 * YIntervalRenderer.java
 * ----------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Nov-2002 : Version 1 (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 27-Sep-2004 : Access double values from dataset (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that draws a line connecting the start and end Y values for an 
 * {@link XYPlot}.
 */
public class YIntervalRenderer extends AbstractXYItemRenderer 
                               implements XYItemRenderer, 
                                          Cloneable,
                                          PublicCloneable,
                                          Serializable {
  static {
    CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2951586537224143260L;
  static {
    CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[1]++;
  }
    
    /**
     * The default constructor.
     */
    public YIntervalRenderer() {
        super();
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[2]++;
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
     * @param pass  the pass index (ignored here).
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
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[3]++;

        // setup for collecting optional entity info...
        Shape entityArea = null;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[4]++;
        EntityCollection entities = null;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[1]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[6]++;

        } else {
  CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[2]++;}
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[7]++;

        IntervalXYDataset intervalDataset = (IntervalXYDataset) dataset;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[8]++;

        double x = intervalDataset.getXValue(series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[9]++;
        double yLow   = intervalDataset.getStartYValue(series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[10]++;
        double yHigh  = intervalDataset.getEndYValue(series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[11]++;

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[12]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[13]++;
        
        double xx = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[14]++;
        double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, yAxisLocation);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[15]++;
        double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, yAxisLocation);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[16]++;

        Paint p = getItemPaint(series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[17]++;
        Stroke s = getItemStroke(series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[18]++;
        
        Line2D line = null;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[19]++;
        Shape shape = getItemShape(series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[20]++;
        Shape top = null;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[21]++;
        Shape bottom = null;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[22]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[3]++;
            line = new Line2D.Double(yyLow, xx, yyHigh, xx);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[24]++;
            top = ShapeUtilities.createTranslatedShape(shape, yyHigh, xx);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[25]++;
            bottom = ShapeUtilities.createTranslatedShape(shape, yyLow, xx);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[26]++;

        }
        else {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[4]++;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[27]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[5]++;
            line = new Line2D.Double(xx, yyLow, xx, yyHigh);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[28]++;
            top = ShapeUtilities.createTranslatedShape(shape, xx, yyHigh);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[29]++;
            bottom = ShapeUtilities.createTranslatedShape(shape, xx, yyLow);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[30]++;

        } else {
  CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[6]++;}
}
        g2.setPaint(p);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[31]++;
        g2.setStroke(s);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[32]++;
        g2.draw(line);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[33]++;

        g2.fill(top);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[34]++;
        g2.fill(bottom);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[35]++;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;

        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[7]++;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((entityArea == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[9]++;
                entityArea = line.getBounds();
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[38]++;

            } else {
  CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[10]++;}
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[39]++;
            String tip = null;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[40]++;
            XYToolTipGenerator generator = getToolTipGenerator(series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[11]++;
                tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[42]++;

            } else {
  CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[12]++;}
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[43]++;
            String url = null;
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[13]++;
                url = getURLGenerator().generateURL(dataset, series, item);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[45]++;

            } else {
  CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[14]++;}
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[46]++;
            XYItemEntity entity = new XYItemEntity(entityArea, dataset, series,
                    item, tip, url);
            entities.add(entity);
CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.statements[47]++;

        } else {
  CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich.branches[8]++;}

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

}

class CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich ());
  }
    public static long[] statements = new long[48];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.YIntervalRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$eneorcqr7osgsrkyo53wzfvvb5tixnaich () {
    super("org.jfree.chart.renderer.xy.YIntervalRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 47; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.YIntervalRenderer.java");
      for (int i = 1; i <= 47; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

