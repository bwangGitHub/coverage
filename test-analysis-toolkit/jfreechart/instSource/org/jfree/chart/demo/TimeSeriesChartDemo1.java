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
 * TimeSeriesChartDemo1.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 09-Mar-2005 : Version 1, copied from the demo collection that ships with
 *               the JFreeChart Developer Guide (DG);
 *
 */

package org.jfree.chart.demo;

import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of a time series chart.  For the most part, default settings are 
 * used, except that the renderer is modified to show filled shapes (as well as 
 * lines) at each data point.
 */
public class TimeSeriesChartDemo1 extends ApplicationFrame {
  static {
    CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.ping();
  }


    /**
     * A demonstration application showing how to create a simple time series 
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public TimeSeriesChartDemo1(String title) {
        super(title);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[1]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[2]++;
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[3]++;
        chartPanel.setMouseZoomable(true, false);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[4]++;
        setContentPane(chartPanel);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[5]++;
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[6]++;

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Legal & General Unit Trust Prices",  // title
            "Date",             // x-axis label
            "Price Per Unit",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[7]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[8]++;

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[9]++;
        plot.setDomainGridlinePaint(Color.white);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[10]++;
        plot.setRangeGridlinePaint(Color.white);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[11]++;
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[12]++;
        plot.setDomainCrosshairVisible(true);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[13]++;
        plot.setRangeCrosshairVisible(true);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[14]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[15]++;
        
        XYItemRenderer r = plot.getRenderer();
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((r instanceof XYLineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.branches[1]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[17]++;
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[18]++;
            renderer.setBaseShapesFilled(true);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[19]++;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.branches[2]++;}
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[20]++;
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[21]++;
        
        return chart;

    }
    
    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset() {
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[22]++;

        TimeSeries s1 = new TimeSeries("L&G European Index Trust", Month.class);
        s1.add(new Month(2, 2001), 181.8);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[23]++;
        s1.add(new Month(3, 2001), 167.3);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[24]++;
        s1.add(new Month(4, 2001), 153.8);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[25]++;
        s1.add(new Month(5, 2001), 167.6);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[26]++;
        s1.add(new Month(6, 2001), 158.8);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[27]++;
        s1.add(new Month(7, 2001), 148.3);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[28]++;
        s1.add(new Month(8, 2001), 153.9);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[29]++;
        s1.add(new Month(9, 2001), 142.7);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[30]++;
        s1.add(new Month(10, 2001), 123.2);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[31]++;
        s1.add(new Month(11, 2001), 131.8);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[32]++;
        s1.add(new Month(12, 2001), 139.6);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[33]++;
        s1.add(new Month(1, 2002), 142.9);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[34]++;
        s1.add(new Month(2, 2002), 138.7);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[35]++;
        s1.add(new Month(3, 2002), 137.3);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[36]++;
        s1.add(new Month(4, 2002), 143.9);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[37]++;
        s1.add(new Month(5, 2002), 139.8);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[38]++;
        s1.add(new Month(6, 2002), 137.0);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[39]++;
        s1.add(new Month(7, 2002), 132.8);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[40]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[41]++;

        TimeSeries s2 = new TimeSeries("L&G UK Index Trust", Month.class);
        s2.add(new Month(2, 2001), 129.6);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[42]++;
        s2.add(new Month(3, 2001), 123.2);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[43]++;
        s2.add(new Month(4, 2001), 117.2);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[44]++;
        s2.add(new Month(5, 2001), 124.1);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[45]++;
        s2.add(new Month(6, 2001), 122.6);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[46]++;
        s2.add(new Month(7, 2001), 119.2);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[47]++;
        s2.add(new Month(8, 2001), 116.5);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[48]++;
        s2.add(new Month(9, 2001), 112.7);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[49]++;
        s2.add(new Month(10, 2001), 101.5);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[50]++;
        s2.add(new Month(11, 2001), 106.1);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[51]++;
        s2.add(new Month(12, 2001), 110.3);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[52]++;
        s2.add(new Month(1, 2002), 111.7);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[53]++;
        s2.add(new Month(2, 2002), 111.0);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[54]++;
        s2.add(new Month(3, 2002), 109.6);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[55]++;
        s2.add(new Month(4, 2002), 113.2);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[56]++;
        s2.add(new Month(5, 2002), 111.6);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[57]++;
        s2.add(new Month(6, 2002), 108.8);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[58]++;
        s2.add(new Month(7, 2002), 101.6);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[59]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[60]++;

        // ******************************************************************
        //  More than 150 demo applications are included with the JFreeChart
        //  Developer Guide...for more information, see:
        //
        //  >   http://www.object-refinery.com/jfreechart/guide.html
        //
        // ******************************************************************
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[61]++;
        dataset.addSeries(s2);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[62]++;
        
        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[63]++;
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[64]++;

        TimeSeriesChartDemo1 demo = new TimeSeriesChartDemo1(
                "Time Series Chart Demo 1");
        demo.pack();
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[65]++;
        RefineryUtilities.centerFrameOnScreen(demo);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[66]++;
        demo.setVisible(true);
CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl.statements[67]++;

    }

}

class CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.demo.TimeSeriesChartDemo1.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$3uchsdzsboshx4joxw9qu55vbspgjqngl0lb5nl () {
    super("org.jfree.chart.demo.TimeSeriesChartDemo1.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.demo.TimeSeriesChartDemo1.java");
      for (int i = 1; i <= 67; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

