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
 * PieChartDemo1.java
 * ------------------
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

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a pie chart using 
 * data from a {@link DefaultPieDataset}.
 */
public class PieChartDemo1 extends ApplicationFrame {
  static {
    CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.ping();
  }


    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public PieChartDemo1(String title) {
        super(title);
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[1]++;
        setContentPane(createDemoPanel());
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[2]++;
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[3]++;
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[4]++;
        dataset.setValue("Two", new Double(10.0));
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[5]++;
        dataset.setValue("Three", new Double(27.5));
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[6]++;
        dataset.setValue("Four", new Double(17.5));
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[7]++;
        dataset.setValue("Five", new Double(11.0));
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[8]++;
        dataset.setValue("Six", new Double(19.4));
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[9]++;
        return dataset;        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[10]++;
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 1",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[11]++;

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[12]++;
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[13]++;
        plot.setNoDataMessage("No data available");
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[14]++;
        plot.setSimpleLabels(true);
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[15]++;
        return chart;
        
    }
    
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[16]++;
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[17]++;

        // ******************************************************************
        //  More than 150 demo applications are included with the JFreeChart
        //  Developer Guide...for more information, see:
        //
        //  >   http://www.object-refinery.com/jfreechart/guide.html
        //
        // ******************************************************************
        
        PieChartDemo1 demo = new PieChartDemo1("Pie Chart Demo 1");
        demo.pack();
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[18]++;
        RefineryUtilities.centerFrameOnScreen(demo);
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[19]++;
        demo.setVisible(true);
CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l.statements[20]++;
    }

}

class CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$6oqcjh6lscefr1v5271j1j8fyd7l () {
    super("org.jfree.chart.demo.PieChartDemo1.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
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
    log.startNamedSection("org.jfree.chart.demo.PieChartDemo1.java");
      for (int i = 1; i <= 20; i++) {
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

