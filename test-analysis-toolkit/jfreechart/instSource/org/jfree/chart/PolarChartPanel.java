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
 * PolarChartPanel.java
 * --------------------
 * (C) Copyright 2004, 2007, by Solution Engineering, Inc. and Contributors.
 *
 * Original Author:  Daniel Bridenbecker, Solution Engineering, Inc.;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Jan-2004 : Version 1, contributed by DB with minor changes by DG (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PolarPlot;

/**
 * <code>PolarChartPanel</code> is the top level object for using the 
 * {@link PolarPlot}. Since this class has a <code>JPanel</code> in the 
 * inheritance hierarchy, one uses this class to integrate the Polar plot into 
 * their application.
 * <p>
 * The main modification to <code>ChartPanel</code> is the popup menu.  It 
 * removes <code>ChartPanel</code>'s versions of:
 * <ul>
 *    <li><code>Zoom In</code></li>
 *    <li><code>Zoom Out</code></li>
 *    <li><code>Auto Range</code></li>
 * </ul>
 * and replaces them with versions more appropriate for {@link PolarPlot}.
 */
public class PolarChartPanel extends ChartPanel {
  static {
    CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.ping();
  }


    // -----------------
    // --- Constants ---
    // -----------------   
    
    /** Zoom in command string. */
    private static final String POLAR_ZOOM_IN_ACTION_COMMAND = "Polar Zoom In";
  static {
    CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[1]++;
  }
    
    /** Zoom out command string. */
    private static final String POLAR_ZOOM_OUT_ACTION_COMMAND 
        = "Polar Zoom Out";
  static {
    CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[2]++;
  }
    
    /** Auto range command string. */
    private static final String POLAR_AUTO_RANGE_ACTION_COMMAND 
        = "Polar Auto Range";
  static {
    CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[3]++;
  }
   
    // ------------------------
    // --- Member Variables ---
    // ------------------------
   
    // --------------------
    // --- Constructors ---
    // --------------------
    /**
     * Constructs a JFreeChart panel.
     *
     * @param chart  the chart.
     */
    public PolarChartPanel(JFreeChart chart) {
        this(chart, true);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[4]++;
    }

    /**
     * Creates a new panel.
     * 
     * @param chart  the chart.
     * @param useBuffer  buffered?
     */
    public PolarChartPanel(JFreeChart chart, boolean useBuffer) {
        super(chart, useBuffer);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[5]++;
        checkChart(chart);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[6]++;
        setMinimumDrawWidth(200);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[7]++;
        setMinimumDrawHeight(200);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[8]++;
        setMaximumDrawWidth(2000);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[9]++;
        setMaximumDrawHeight(2000);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[10]++;
    }
   
    // --------------------------
    // --- ChartPanel Methods ---
    // --------------------------
    /**
     * Sets the chart that is displayed in the panel.
     *
     * @param chart  The chart.
     */
    public void setChart(JFreeChart chart) {
        checkChart(chart);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[11]++;
        super.setChart(chart);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[12]++;
    }
   
    /**
     * Creates a popup menu for the panel.
     *
     * @param properties  include a menu item for the chart property editor.
     * @param save  include a menu item for saving the chart.
     * @param print  include a menu item for printing the chart.
     * @param zoom  include menu items for zooming.
     *
     * @return The popup menu.
     */
    protected JPopupMenu createPopupMenu(boolean properties,
                                         boolean save, 
                                         boolean print, 
                                         boolean zoom) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[13]++;
        
       JPopupMenu result = super.createPopupMenu(properties, save, print, zoom);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[14]++;
       int zoomInIndex  = getPopupMenuItem(result, "Zoom In");
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[15]++;
       int zoomOutIndex = getPopupMenuItem(result, "Zoom Out");
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[16]++;
       int autoIndex     = getPopupMenuItem(result, "Auto Range");
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
       if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zoom) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[1]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[18]++;
           JMenuItem zoomIn = new JMenuItem("Zoom In");
           zoomIn.setActionCommand(POLAR_ZOOM_IN_ACTION_COMMAND);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[19]++;
           zoomIn.addActionListener(this);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[20]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[21]++;
          
           JMenuItem zoomOut = new JMenuItem("Zoom Out");
           zoomOut.setActionCommand(POLAR_ZOOM_OUT_ACTION_COMMAND);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[22]++;
           zoomOut.addActionListener(this);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[23]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[24]++;
          
           JMenuItem auto = new JMenuItem("Auto Range");
           auto.setActionCommand(POLAR_AUTO_RANGE_ACTION_COMMAND);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[25]++;
           auto.addActionListener(this);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[26]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[27]++;
int CodeCoverConditionCoverageHelper_C2;
          
           if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((zoomInIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[3]++;
               result.remove(zoomInIndex);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[28]++;

           }
           else {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[4]++;
               zoomInIndex = result.getComponentCount() - 1;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[29]++;
           }
           result.add(zoomIn, zoomInIndex);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[30]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[31]++;
int CodeCoverConditionCoverageHelper_C3;
           if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zoomOutIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[5]++;
               result.remove(zoomOutIndex);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[32]++;

           }
           else {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[6]++;
               zoomOutIndex = zoomInIndex + 1;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[33]++;
           }
           result.add(zoomOut, zoomOutIndex);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[34]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[35]++;
int CodeCoverConditionCoverageHelper_C4;
           if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((autoIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[7]++;
               result.remove(autoIndex);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[36]++;

           }
           else {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[8]++;
               autoIndex = zoomOutIndex + 1;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[37]++;
           }
           result.add(auto, autoIndex);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[38]++;

       } else {
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[2]++;}
       return result;
    }
   
    /**
     * Handles action events generated by the popup menu.
     *
     * @param event  the event.
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[39]++;
       String command = event.getActionCommand();
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[40]++;
int CodeCoverConditionCoverageHelper_C5;
       
       if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((command.equals(POLAR_ZOOM_IN_ACTION_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[9]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[41]++;
           PolarPlot plot = (PolarPlot) getChart().getPlot();
           plot.zoom(0.5);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[42]++;

       }
       else {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[10]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[43]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((command.equals(POLAR_ZOOM_OUT_ACTION_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[11]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[44]++;
           PolarPlot plot = (PolarPlot) getChart().getPlot();
           plot.zoom(2.0);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[45]++;

       }
       else {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[12]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[46]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((command.equals(POLAR_AUTO_RANGE_ACTION_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[13]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[47]++;
           PolarPlot plot = (PolarPlot) getChart().getPlot();
           plot.getAxis().setAutoRange(true);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[48]++;

       }
       else {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[14]++;
           super.actionPerformed(event);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[49]++;
       }
}
}
    }

    // ----------------------
    // --- Public Methods ---
    // ----------------------

    // -----------------------
    // --- Private Methods ---
    // -----------------------
   
    /**
     * Test that the chart is using an xy plot with time as the domain axis.
     * 
     * @param chart  the chart.
     */
    private void checkChart(JFreeChart chart) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[50]++;
        Plot plot = chart.getPlot();
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[51]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((plot instanceof PolarPlot) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[15]++;
            throw new IllegalArgumentException("plot is not a PolarPlot");

       } else {
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[16]++;}
    }
   
    /**
     * Returns the index of an item in a popup menu.
     * 
     * @param menu  the menu.
     * @param text  the label.
     * 
     * @return The item index.
     */
    private int getPopupMenuItem(JPopupMenu menu, String text) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[52]++;
        int index = -1;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[53]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < menu.getComponentCount()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.loops[1]--;
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.loops[2]--;
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.loops[3]++;
}
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[54]++;
            Component comp = menu.getComponent(i);
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((comp instanceof JMenuItem) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[17]++;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[56]++;
                JMenuItem item = (JMenuItem) comp;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((text.equals(item.getText())) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[19]++;
                    index = i;
CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.statements[58]++;

                } else {
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[20]++;}

            } else {
  CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl.branches[18]++;}
       }
       return index;
    }

}

class CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl ());
  }
    public static long[] statements = new long[59];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.PolarChartPanel.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$9e8vdfam6lm736y9rt2ga89fpv9dizl () {
    super("org.jfree.chart.PolarChartPanel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 58; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.PolarChartPanel.java");
      for (int i = 1; i <= 58; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

