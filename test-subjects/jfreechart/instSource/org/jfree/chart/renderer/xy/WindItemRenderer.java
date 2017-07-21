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
 * ---------------------
 * WindItemRenderer.java
 * ---------------------
 * (C) Copyright 2001-2007, by Achilleus Mantzios and Contributors.
 *
 * Original Author:  Achilleus Mantzios;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 06-Feb-2002 : Version 1, based on code contributed by Achilleus 
 *               Mantzios (DG);
 * 28-Mar-2002 : Added a property change listener mechanism so that renderers 
 *               no longer need to be immutable.  Changed StrictMath --> Math 
 *               to retain JDK1.2 compatibility (DG);
 * 09-Apr-2002 : Changed return type of the drawItem method to void, reflecting 
 *               the change in the XYItemRenderer method (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 21-Jan-2003 : Added new constructor (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A specialised renderer for displaying wind intensity/direction data.
 */
public class WindItemRenderer extends AbstractXYItemRenderer 
                              implements XYItemRenderer, 
                                         Cloneable,
                                         PublicCloneable,
                                         Serializable {
  static {
    CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8078914101916976844L;
  static {
    CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[1]++;
  }
    
    /**
     * Creates a new renderer.
     */
    public WindItemRenderer() {
        super();
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[2]++;
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param plotArea  the area within which the plot is being drawn.
     * @param info  optional information collection.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the horizontal axis.
     * @param rangeAxis  the vertical axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         XYItemRendererState state,
                         Rectangle2D plotArea,
                         PlotRenderingInfo info,
                         XYPlot plot,
                         ValueAxis domainAxis,
                         ValueAxis rangeAxis,
                         XYDataset dataset,
                         int series,
                         int item,
                         CrosshairState crosshairState,
                         int pass) {
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[3]++;

        WindDataset windData = (WindDataset) dataset;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[4]++;

        Paint seriesPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[5]++;
        Stroke seriesStroke = getItemStroke(series, item);
        g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[6]++;
        g2.setStroke(seriesStroke);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[7]++;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[8]++;

        // get the data point...

        Number x = windData.getX(series, item);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[9]++;
        Number windDir = windData.getWindDirection(series, item);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[10]++;
        Number wforce = windData.getWindForce(series, item);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[11]++;
        double windForce = wforce.doubleValue();
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[12]++;

        double wdirt = Math.toRadians(windDir.doubleValue() * (-30.0) - 90.0);

        double ax1, ax2, ay1, ay2, rax2, ray2;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[13]++;

        RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[14]++;
        RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
        ax1 = domainAxis.valueToJava2D(x.doubleValue(), plotArea, 
                domainAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[15]++;
        ay1 = rangeAxis.valueToJava2D(0.0, plotArea, rangeAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[16]++;

        rax2 = x.doubleValue() + (windForce * Math.cos(wdirt) * 8000000.0);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[17]++;
        ray2 = windForce * Math.sin(wdirt);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[18]++;

        ax2 = domainAxis.valueToJava2D(rax2, plotArea, domainAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[19]++;
        ay2 = rangeAxis.valueToJava2D(ray2, plotArea, rangeAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[20]++;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[21]++;

        int diri = windDir.intValue();
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[22]++;
        int forcei = wforce.intValue();
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[23]++;
        String dirforce = diri + "-" + forcei;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[24]++;
        Line2D line = new Line2D.Double(ax1, ay1, ax2, ay2);

        g2.draw(line);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[25]++;
        g2.setPaint(Color.blue);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[26]++;
        g2.setFont(new Font("foo", 1, 9));
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[27]++;

        g2.drawString(dirforce, (float) ax1, (float) ay1);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[28]++;

        g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[29]++;
        g2.setStroke(seriesStroke);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[30]++;

        double alx2, aly2, arx2, ary2;
        double ralx2, raly2, rarx2, rary2;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[31]++;

        double aldir = Math.toRadians(windDir.doubleValue() 
                * (-30.0) - 90.0 - 5.0);
        ralx2 = wforce.doubleValue() * Math.cos(aldir) * 8000000 * 0.8 
        + x.doubleValue();
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[32]++;
        raly2 = wforce.doubleValue() * Math.sin(aldir) * 0.8;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[33]++;

        alx2 = domainAxis.valueToJava2D(ralx2, plotArea, domainAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[34]++;
        aly2 = rangeAxis.valueToJava2D(raly2, plotArea, rangeAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[35]++;

        line = new Line2D.Double(alx2, aly2, ax2, ay2);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[36]++;
        g2.draw(line);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[37]++;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[38]++;

        double ardir = Math.toRadians(windDir.doubleValue() 
                * (-30.0) - 90.0 + 5.0);
        rarx2 = wforce.doubleValue() * Math.cos(ardir) * 8000000 * 0.8 
                + x.doubleValue();
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[39]++;
        rary2 = wforce.doubleValue() * Math.sin(ardir) * 0.8;
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[40]++;

        arx2 = domainAxis.valueToJava2D(rarx2, plotArea, domainAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[41]++;
        ary2 = rangeAxis.valueToJava2D(rary2, plotArea, rangeAxisLocation);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[42]++;

        line = new Line2D.Double(arx2, ary2, ax2, ay2);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[43]++;
        g2.draw(line);
CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl.statements[44]++;

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

class CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$20lxlxcbtbiq4rgil7mt2nxiq5b0stdnl () {
    super("org.jfree.chart.renderer.xy.WindItemRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.WindItemRenderer.java");
      for (int i = 1; i <= 44; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

