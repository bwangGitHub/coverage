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
 * -----------------------
 * DefaultChartEditor.java
 * -----------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Arnaud Lelievre;
 *                   Daniel Gredler;
 *
 * Changes
 * -------
 * 24-Nov-2005 : New class, based on ChartPropertyEditPanel.java (DG);
 *
 */

package org.jfree.chart.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.Title;
import org.jfree.layout.LCBLayout;
import org.jfree.ui.PaintSample;

/**
 * A panel for editing chart properties (includes subpanels for the title,
 * legend and plot).
 */
class DefaultChartEditor extends JPanel implements ActionListener, ChartEditor {
  static {
    CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.ping();
  }


    /** A panel for displaying/editing the properties of the title. */
    private DefaultTitleEditor titleEditor;

    /** A panel for displaying/editing the properties of the plot. */
    private DefaultPlotEditor plotEditor;

    /** 
     * A checkbox indicating whether or not the chart is drawn with
     * anti-aliasing.
     */
    private JCheckBox antialias;

    /** The chart background color. */
    private PaintSample background;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
        = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[1]++;
  }

    /**
     * Standard constructor - the property panel is made up of a number of
     * sub-panels that are displayed in the tabbed pane.
     *
     * @param chart  the chart, whichs properties should be changed.
     */
    public DefaultChartEditor(JFreeChart chart) {
        setLayout(new BorderLayout());
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[2]++;
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[3]++;

        JPanel other = new JPanel(new BorderLayout());
        other.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[4]++;
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[5]++;

        JPanel general = new JPanel(new BorderLayout());
        general.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            localizationResources.getString("General")));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[6]++;
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[7]++;

        JPanel interior = new JPanel(new LCBLayout(6));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[8]++;

        this.antialias = new JCheckBox(localizationResources.getString(
                "Draw_anti-aliased"));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[9]++;
        this.antialias.setSelected(chart.getAntiAlias());
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[10]++;
        interior.add(this.antialias);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[11]++;
        interior.add(new JLabel(""));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[12]++;
        interior.add(new JLabel(""));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[13]++;
        interior.add(new JLabel(localizationResources.getString(
                "Background_paint")));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[14]++;
        this.background = new PaintSample(chart.getBackgroundPaint());
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[15]++;
        interior.add(this.background);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[16]++;
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[17]++;
        JButton button = new JButton(localizationResources.getString(
                "Select..."));
        button.setActionCommand("BackgroundPaint");
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[18]++;
        button.addActionListener(this);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[19]++;
        interior.add(button);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[20]++;

        interior.add(new JLabel(localizationResources.getString(
                "Series_Paint")));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[21]++;
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[22]++;
        JTextField info = new JTextField(localizationResources.getString(
                "No_editor_implemented"));
        info.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[23]++;
        interior.add(info);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[24]++;
        button = new JButton(localizationResources.getString("Edit..."));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[25]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[26]++;
        interior.add(button);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[27]++;

        interior.add(new JLabel(localizationResources.getString(
                "Series_Stroke")));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[28]++;
        info = new JTextField(localizationResources.getString(
                "No_editor_implemented"));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[29]++;
        info.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[30]++;
        interior.add(info);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[31]++;
        button = new JButton(localizationResources.getString("Edit..."));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[32]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[33]++;
        interior.add(button);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[34]++;

        interior.add(new JLabel(localizationResources.getString(
                "Series_Outline_Paint")));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[35]++;
        info = new JTextField(localizationResources.getString(
                "No_editor_implemented"));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[36]++;
        info.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[37]++;
        interior.add(info);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[38]++;
        button = new JButton(localizationResources.getString("Edit..."));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[39]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[40]++;
        interior.add(button);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[41]++;

        interior.add(new JLabel(localizationResources.getString(
                "Series_Outline_Stroke")));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[42]++;
        info = new JTextField(localizationResources.getString(
                "No_editor_implemented"));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[43]++;
        info.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[44]++;
        interior.add(info);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[45]++;
        button = new JButton(localizationResources.getString("Edit..."));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[46]++;
        button.setEnabled(false);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[47]++;
        interior.add(button);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[48]++;

        general.add(interior, BorderLayout.NORTH);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[49]++;
        other.add(general, BorderLayout.NORTH);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[50]++;
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[51]++;

        JPanel parts = new JPanel(new BorderLayout());
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[52]++;

        Title title = chart.getTitle();
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[53]++;
        Plot plot = chart.getPlot();
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[54]++;

        JTabbedPane tabs = new JTabbedPane();

        this.titleEditor = new DefaultTitleEditor(title);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[55]++;
        this.titleEditor.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[56]++;
        tabs.addTab(localizationResources.getString("Title"), this.titleEditor);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[57]++;

        this.plotEditor = new DefaultPlotEditor(plot);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[58]++;
        this.plotEditor.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[59]++;
        tabs.addTab(localizationResources.getString("Plot"), this.plotEditor);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[60]++;

        tabs.add(localizationResources.getString("Other"), other);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[61]++;
        parts.add(tabs, BorderLayout.NORTH);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[62]++;
        add(parts);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[63]++;
    }

    /**
     * Returns a reference to the title editor.
     *
     * @return A panel for editing the title.
     */
    public DefaultTitleEditor getTitleEditor() {
      return this.titleEditor;
    }

    /**
     * Returns a reference to the plot property sub-panel.
     *
     * @return A panel for editing the plot properties.
     */
    public DefaultPlotEditor getPlotEditor() {
        return this.plotEditor;
    }

    /**
     * Returns the current setting of the anti-alias flag.
     *
     * @return <code>true</code> if anti-aliasing is enabled.
     */
    public boolean getAntiAlias() {
        return this.antialias.isSelected();
    }

    /**
     * Returns the current background paint.
     *
     * @return The current background paint.
     */
    public Paint getBackgroundPaint() {
        return this.background.getPaint();
    }

    /**
     * Handles user interactions with the panel.
     *
     * @param event  a BackgroundPaint action.
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[64]++;
        String command = event.getActionCommand();
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[65]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((command.equals("BackgroundPaint")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.branches[1]++;
            attemptModifyBackgroundPaint();
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[66]++;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.branches[2]++;}
    }

    /**
     * Allows the user the opportunity to select a new background paint.  Uses
     * JColorChooser, so we are only allowing a subset of all Paint objects to
     * be selected (fix later).
     */
    private void attemptModifyBackgroundPaint() {
        Color c;
        c = JColorChooser.showDialog(this, localizationResources.getString(
                "Background_Color"), Color.blue);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[67]++;
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[68]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.branches[3]++;
            this.background.setPaint(c);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[69]++;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.branches[4]++;}
    }

    /**
     * Updates the properties of a chart to match the properties defined on the
     * panel.
     *
     * @param chart  the chart.
     */
    public void updateChart(JFreeChart chart) {

        this.titleEditor.setTitleProperties(chart);
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[70]++;
        this.plotEditor.updatePlotProperties(chart.getPlot());
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[71]++;

        chart.setAntiAlias(getAntiAlias());
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[72]++;
        chart.setBackgroundPaint(getBackgroundPaint());
CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx.statements[73]++;
    }

}

class CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx ());
  }
    public static long[] statements = new long[74];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.DefaultChartEditor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$27sy6oy8nww0pffmj1ke7r0lgdsxvfequ4wx () {
    super("org.jfree.chart.editor.DefaultChartEditor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 73; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.editor.DefaultChartEditor.java");
      for (int i = 1; i <= 73; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

