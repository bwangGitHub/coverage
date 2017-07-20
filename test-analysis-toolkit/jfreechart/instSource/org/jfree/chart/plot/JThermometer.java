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
 * -----------------
 * JThermometer.java
 * -----------------
 * A plot that displays a single value in a thermometer type display.
 *
 * (C) Copyright 2000-2007, Australian Antarctic Division and Contributors.
 *
 * Original Author:  Bryan Scott.
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Irv Thomae;
 *
 * Changes (from 17-Sep-2002)
 * --------------------------
 * 17-Sep-2002 : Reviewed with Checkstyle utility (DG);
 * 18-Sep-2003 : Integrated new methods contributed by Irv Thomae (DG);
 * 08-Jan-2004 : Renamed AbstractTitle --> Title and moved to new package (DG);
 * 31-May-2005 : Fixed typo in method name (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.Serializable;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleInsets;

/**
 * An initial quick and dirty.  The concept behind this class would be to
 * generate a gui bean that could be used within JBuilder, Netbeans etc...
 */
public class JThermometer extends JPanel implements Serializable {
  static {
    CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1079905665515589820L;
  static {
    CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[1]++;
  }
    
    /** The dataset. */
    private DefaultValueDataset data;

    /** The chart. */
    private JFreeChart chart;

    /** The chart panel. */
    private ChartPanel panel;

    /** The thermometer plot. */
    private ThermometerPlot plot = new ThermometerPlot();
  {
    CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[2]++;
  }

    /**
     * Default constructor.
     */
    public JThermometer() {
        super(new CardLayout());
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[3]++;
        this.plot.setInsets(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[4]++;
        this.data = new DefaultValueDataset();
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[5]++;
        this.plot.setDataset(this.data);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[6]++;
        this.chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, 
                this.plot, false);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[7]++;
        this.panel = new ChartPanel(this.chart);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[8]++;
        add(this.panel, "Panel");
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[9]++;
        setBackground(getBackground());
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[10]++;
    }

    /**
     * Adds a subtitle to the chart.
     *
     * @param subtitle  the subtitle.
     */
    public void addSubtitle(Title subtitle) {
        this.chart.addSubtitle(subtitle);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[11]++;
    }

    /**
     * Adds a subtitle to the chart.
     *
     * @param subtitle  the subtitle.
     */
    public void addSubtitle(String subtitle) {
        this.chart.addSubtitle(new TextTitle(subtitle));
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[12]++;
    }

    /**
     * Adds a subtitle to the chart.
     *
     * @param subtitle  the subtitle.
     * @param font  the subtitle font.
     */
    public void addSubtitle(String subtitle, Font font) {
        this.chart.addSubtitle(new TextTitle(subtitle, font));
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[13]++;
    }

    /**
     * Sets the value format for the thermometer.
     *
     * @param df  the formatter.
     */
    public void setValueFormat(DecimalFormat df) {
        this.plot.setValueFormat(df);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[14]++;
    }

    /**
     * Sets the lower and upper bounds for the thermometer.
     *
     * @param lower  the lower bound.
     * @param upper  the upper bound.
     */
    public void setRange(double lower, double upper) {
        this.plot.setRange(lower, upper);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[15]++;
    }

    /**
     * Sets the range.
     *
     * @param range  the range type.
     * @param displayLow  the low value.
     * @param displayHigh  the high value.
     */
    public void setSubrangeInfo(int range, double displayLow, 
                                double displayHigh) {
        this.plot.setSubrangeInfo(range, displayLow, displayHigh);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[16]++;
    }

    /**
     * Sets the range.
     *
     * @param range  the range type.
     * @param rangeLow  the low value for the range.
     * @param rangeHigh  the high value for the range.
     * @param displayLow  the low value for display.
     * @param displayHigh  the high value for display.
     */
    public void setSubrangeInfo(int range,
                             double rangeLow, double rangeHigh,
                             double displayLow, double displayHigh) {

        this.plot.setSubrangeInfo(range, rangeLow, rangeHigh, displayLow, 
                displayHigh);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[17]++;

    }

    /**
     * Sets the location at which the temperature value is displayed.
     *
     * @param loc  the location.
     */
    public void setValueLocation(int loc) {
        this.plot.setValueLocation(loc);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[18]++;
        this.panel.repaint();
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[19]++;
    }

    /**
     * Sets the value paint.
     *
     * @param paint  the paint.
     */
    public void setValuePaint(Paint paint) {
        this.plot.setValuePaint(paint);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[20]++;
    }

    /**
     * Returns the value of the thermometer.
     *
     * @return The value.
     */
    public Number getValue() {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[21]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.data != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[1]++;
            return this.data.getValue();

        }
        else {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[2]++;
            return null;
        }
    }

    /**
     * Sets the value of the thermometer.
     *
     * @param value  the value.
     */
    public void setValue(double value) {
        setValue(new Double(value));
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[22]++;
    }

    /**
     * Sets the value of the thermometer.
     *
     * @param value  the value.
     */
    public void setValue(Number value) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.data != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[3]++;
            this.data.setValue(value);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[24]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[4]++;}
    }

    /**
     * Sets the unit type.
     *
     * @param i  the unit type.
     */
    public void setUnits(int i) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.plot != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[5]++;
            this.plot.setUnits(i);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[26]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[6]++;}
    }

    /**
     * Sets the outline paint.
     *
     * @param p  the paint.
     */
    public void setOutlinePaint(Paint p) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.plot != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[7]++;
            this.plot.setOutlinePaint(p);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[28]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[8]++;}
    }

    /**
     * Sets the foreground color.
     *
     * @param fg  the foreground color.
     */
    public void setForeground(Color fg) {
        super.setForeground(fg);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[29]++;
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.plot != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[9]++;
            this.plot.setThermometerPaint(fg);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[31]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[10]++;}
    }

    /**
     * Sets the background color.
     *
     * @param bg  the background color.
     */
    public void setBackground(Color bg) {
        super.setBackground(bg);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[32]++;
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.plot != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[11]++;
            this.plot.setBackgroundPaint(bg);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[34]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[12]++;}
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.chart != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[13]++;
            this.chart.setBackgroundPaint(bg);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[36]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[14]++;}
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.panel != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[15]++;
            this.panel.setBackground(bg);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[38]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[16]++;}
    }

    /**
     * Sets the value font.
     *
     * @param f  the font.
     */
    public void setValueFont(Font f) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.plot != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[17]++;
            this.plot.setValueFont(f);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[40]++;

        } else {
  CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.branches[18]++;}
    }

    /**
     * Returns the tick label font.
     *
     * @return The tick label font.
     */
    public Font getTickLabelFont() {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[41]++;
        ValueAxis axis = this.plot.getRangeAxis();
        return axis.getTickLabelFont();
    }

    /**
     * Sets the tick label font.
     *
     * @param font  the font.
     */
    public void setTickLabelFont(Font font) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[42]++;
        ValueAxis axis = this.plot.getRangeAxis();
        axis.setTickLabelFont(font);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[43]++;
    }

    /**
     * Increases or decreases the tick font size.
     *
     * @param delta  the change in size.
     */
    public void changeTickFontSize(int delta) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[44]++;
        Font f = getTickLabelFont();
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[45]++;
        String fName = f.getFontName();
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[46]++;
        Font newFont = new Font(fName, f.getStyle(), (f.getSize() + delta));
        setTickLabelFont(newFont);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[47]++;
    }

    /**
     * Sets the tick font style.
     *
     * @param style  the style.
     */
    public void setTickFontStyle(int style) {
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[48]++;
        Font f = getTickLabelFont();
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[49]++;
        String fName = f.getFontName();
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[50]++;
        Font newFont = new Font(fName, style, f.getSize());
        setTickLabelFont(newFont);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[51]++;
    }

    /**
     * Sets the flag that controls whether or not the display range follows the
     * data value.
     *
     * @param flag  the new value of the flag.
     */
    public void setFollowDataInSubranges(boolean flag) {
        this.plot.setFollowDataInSubranges(flag);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[52]++;
    }

    /**
     * Sets the flag that controls whether or not value lines are displayed.
     *
     * @param b  the new flag value.
     */
    public void setShowValueLines(boolean b) {
        this.plot.setShowValueLines(b);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[53]++;
    }

    /**
     * Sets the location for the axis.
     * 
     * @param location  the location.
     */
    public void setShowAxisLocation(int location) {
        this.plot.setAxisLocation(location);
CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp.statements[54]++;
    }

    /**
     * Returns the location for the axis.
     * 
     * @return The location.
     */
    public int getShowAxisLocation() {
      return this.plot.getAxisLocation();
    }

}

class CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp ());
  }
    public static long[] statements = new long[55];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.JThermometer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$vaj00scy505unph73ioecbnuqp () {
    super("org.jfree.chart.plot.JThermometer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 54; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.JThermometer.java");
      for (int i = 1; i <= 54; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

