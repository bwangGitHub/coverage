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
 * ---------------------------
 * XYTitleAnnotationDemo1.java
 * ---------------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 02-Feb-2007 : Version 1 (DG);
 *
 */

package org.jfree.experimental.chart.demo;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.experimental.chart.annotations.XYTitleAnnotation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of a time series chart.  For the most part, default settings are 
 * used, except that the renderer is modified to show filled shapes (as well as 
 * lines) at each data point.
 */
public class XYTitleAnnotationDemo1 extends ApplicationFrame {
  static {
    CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.ping();
  }


    /**
     * A demonstration application showing how to create a simple time series 
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public XYTitleAnnotationDemo1(String title) {
        super(title);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[1]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[2]++;
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[3]++;
        chartPanel.setMouseZoomable(true, false);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[4]++;
        setContentPane(chartPanel);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[5]++;
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[6]++;

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Legal & General Unit Trust Prices",  // title
            "Date",             // x-axis label
            "Price Per Unit",   // y-axis label
            dataset,            // data
            false,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[7]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[8]++;

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[9]++;
        plot.setDomainGridlinePaint(Color.white);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[10]++;
        plot.setRangeGridlinePaint(Color.white);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[11]++;
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[12]++;
        plot.setDomainCrosshairVisible(true);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[13]++;
        plot.setRangeCrosshairVisible(true);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[14]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[15]++;

        LegendTitle lt = new LegendTitle(plot);
        lt.setItemFont(new Font("Dialog", Font.PLAIN, 9));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[16]++;
        lt.setBackgroundPaint(new Color(200, 200, 255, 100));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[17]++;
        lt.setFrame(new BlockBorder(Color.white));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[18]++;
        lt.setPosition(RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[19]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[20]++;
        XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, lt, 
                RectangleAnchor.BOTTOM_RIGHT);
        
        ta.setMaxWidth(0.48);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[21]++;
        plot.addAnnotation(ta);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[22]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[23]++;

        XYItemRenderer r = plot.getRenderer();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[24]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((r instanceof XYLineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.branches[1]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[25]++;
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[26]++;
            renderer.setBaseShapesFilled(true);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[27]++;

        } else {
  CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.branches[2]++;}
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[28]++;
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[29]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[30]++;
        
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setLowerMargin(0.35);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[31]++;
        return chart;

    }
    
    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset() {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[32]++;

        TimeSeries s1 = new TimeSeries("L&G European Index Trust", Month.class);
        s1.add(new Month(2, 2001), 181.8);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[33]++;
        s1.add(new Month(3, 2001), 167.3);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[34]++;
        s1.add(new Month(4, 2001), 153.8);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[35]++;
        s1.add(new Month(5, 2001), 167.6);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[36]++;
        s1.add(new Month(6, 2001), 158.8);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[37]++;
        s1.add(new Month(7, 2001), 148.3);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[38]++;
        s1.add(new Month(8, 2001), 153.9);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[39]++;
        s1.add(new Month(9, 2001), 142.7);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[40]++;
        s1.add(new Month(10, 2001), 123.2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[41]++;
        s1.add(new Month(11, 2001), 131.8);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[42]++;
        s1.add(new Month(12, 2001), 139.6);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[43]++;
        s1.add(new Month(1, 2002), 142.9);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[44]++;
        s1.add(new Month(2, 2002), 138.7);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[45]++;
        s1.add(new Month(3, 2002), 137.3);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[46]++;
        s1.add(new Month(4, 2002), 143.9);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[47]++;
        s1.add(new Month(5, 2002), 139.8);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[48]++;
        s1.add(new Month(6, 2002), 137.0);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[49]++;
        s1.add(new Month(7, 2002), 132.8);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[50]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[51]++;

        TimeSeries s2 = new TimeSeries("L&G UK Index Trust", Month.class);
        s2.add(new Month(2, 2001), 129.6);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[52]++;
        s2.add(new Month(3, 2001), 123.2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[53]++;
        s2.add(new Month(4, 2001), 117.2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[54]++;
        s2.add(new Month(5, 2001), 124.1);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[55]++;
        s2.add(new Month(6, 2001), 122.6);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[56]++;
        s2.add(new Month(7, 2001), 119.2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[57]++;
        s2.add(new Month(8, 2001), 116.5);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[58]++;
        s2.add(new Month(9, 2001), 112.7);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[59]++;
        s2.add(new Month(10, 2001), 101.5);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[60]++;
        s2.add(new Month(11, 2001), 106.1);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[61]++;
        s2.add(new Month(12, 2001), 110.3);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[62]++;
        s2.add(new Month(1, 2002), 111.7);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[63]++;
        s2.add(new Month(2, 2002), 111.0);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[64]++;
        s2.add(new Month(3, 2002), 109.6);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[65]++;
        s2.add(new Month(4, 2002), 113.2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[66]++;
        s2.add(new Month(5, 2002), 111.6);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[67]++;
        s2.add(new Month(6, 2002), 108.8);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[68]++;
        s2.add(new Month(7, 2002), 101.6);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[69]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[70]++;
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[71]++;
        dataset.addSeries(s2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[72]++;
        
        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[73]++;
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[74]++;

        XYTitleAnnotationDemo1 demo = new XYTitleAnnotationDemo1(
                "XYTitleAnnotationDemo1");
        demo.pack();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[75]++;
        RefineryUtilities.centerFrameOnScreen(demo);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[76]++;
        demo.setVisible(true);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap.statements[77]++;

    }

}

class CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap ());
  }
    public static long[] statements = new long[78];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.experimental.chart.demo.XYTitleAnnotationDemo1.java";
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

  public CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw6073p53gip3k78zjiap () {
    super("org.jfree.experimental.chart.demo.XYTitleAnnotationDemo1.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 77; i++) {
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
    log.startNamedSection("org.jfree.experimental.chart.demo.XYTitleAnnotationDemo1.java");
      for (int i = 1; i <= 77; i++) {
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

