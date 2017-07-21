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
 * ------------------
 * BarChartDemo1.java
 * ------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 09-Mar-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 */
public class BarChartDemo1 extends ApplicationFrame {
  static {
    CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.ping();
  }


    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public BarChartDemo1(String title) {
        super(title);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[1]++;
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[2]++;
        CategoryDataset dataset = createDataset();
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[3]++;
        JFreeChart chart = createChart(dataset);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[4]++;
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 270));
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[5]++;
        setContentPane(chartPanel);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[6]++;
    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[7]++;
        
        // row keys...
        String series1 = "First";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[8]++;
        String series2 = "Second";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[9]++;
        String series3 = "Third";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[10]++;

        // column keys...
        String category1 = "Category 1";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[11]++;
        String category2 = "Category 2";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[12]++;
        String category3 = "Category 3";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[13]++;
        String category4 = "Category 4";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[14]++;
        String category5 = "Category 5";
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[15]++;

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[16]++;
        dataset.addValue(4.0, series1, category2);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[17]++;
        dataset.addValue(3.0, series1, category3);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[18]++;
        dataset.addValue(5.0, series1, category4);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[19]++;
        dataset.addValue(5.0, series1, category5);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[20]++;

        dataset.addValue(5.0, series2, category1);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[21]++;
        dataset.addValue(7.0, series2, category2);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[22]++;
        dataset.addValue(6.0, series2, category3);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[23]++;
        dataset.addValue(8.0, series2, category4);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[24]++;
        dataset.addValue(4.0, series2, category5);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[25]++;

        dataset.addValue(4.0, series3, category1);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[26]++;
        dataset.addValue(3.0, series3, category2);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[27]++;
        dataset.addValue(2.0, series3, category3);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[28]++;
        dataset.addValue(3.0, series3, category4);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[29]++;
        dataset.addValue(6.0, series3, category5);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[30]++;
        
        return dataset;
        
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[31]++;
        
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart Demo 1",       // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[32]++;
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[33]++;

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[34]++;
        plot.setDomainGridlinePaint(Color.white);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[35]++;
        plot.setDomainGridlinesVisible(true);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[36]++;
        plot.setRangeGridlinePaint(Color.white);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[37]++;
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[38]++;

        // ******************************************************************
        //  More than 150 demo applications are included with the JFreeChart
        //  Developer Guide...for more information, see:
        //
        //  >   http://www.object-refinery.com/jfreechart/guide.html
        //
        // ******************************************************************
        
        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[39]++;
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[40]++;

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[41]++;
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[42]++;
        
        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 
                0.0f, 0.0f, new Color(0, 0, 64));
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[43]++;
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 
                0.0f, 0.0f, new Color(0, 64, 0));
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[44]++;
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[45]++;
        renderer.setSeriesPaint(1, gp1);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[46]++;
        renderer.setSeriesPaint(2, gp2);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[47]++;
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[48]++;

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0));
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[49]++;
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[50]++;
        BarChartDemo1 demo = new BarChartDemo1("Bar Chart Demo 1");
        demo.pack();
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[51]++;
        RefineryUtilities.centerFrameOnScreen(demo);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[52]++;
        demo.setVisible(true);
CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl.statements[53]++;
    }

}

class CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl ());
  }
    public static long[] statements = new long[54];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5iq5lucbm629m4ey2c49ermqunvl () {
    super("org.jfree.chart.demo.BarChartDemo1.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 53; i++) {
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
    log.startNamedSection("org.jfree.chart.demo.BarChartDemo1.java");
      for (int i = 1; i <= 53; i++) {
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

