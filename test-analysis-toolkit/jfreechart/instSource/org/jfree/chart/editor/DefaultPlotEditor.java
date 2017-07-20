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
 * DefaultPlotEditor.java
 * ----------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski;
 *                   Arnaud Lelievre;
 *                   Daniel Gredler;
 *
 * Changes:
 * --------
 * 24-Nov-2005 : Version 1, based on PlotPropertyEditPanel.java (DG);
 *
 */

package org.jfree.chart.editor;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.ColorBar;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.ContourPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.layout.LCBLayout;
import org.jfree.ui.PaintSample;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.StrokeChooserPanel;
import org.jfree.ui.StrokeSample;
import org.jfree.util.BooleanUtilities;

/**
 * A panel for editing the properties of a {@link Plot}.
 */
class DefaultPlotEditor extends JPanel implements ActionListener {
  static {
    CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.ping();
  }


    /** Orientation constants. */
    private final static String[] orientationNames = {"Vertical", "Horizontal"};
  static {
    CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[1]++;
  }
    private final static int ORIENTATION_VERTICAL = 0;
  static {
    CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[2]++;
  }
    private final static int ORIENTATION_HORIZONTAL = 1;
  static {
    CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[3]++;
  }

    /** The paint (color) used to fill the background of the plot. */
    private PaintSample backgroundPaintSample;

    /** The stroke (pen) used to draw the outline of the plot. */
    private StrokeSample outlineStrokeSample;

    /** The paint (color) used to draw the outline of the plot. */
    private PaintSample outlinePaintSample;

    /** 
     * A panel used to display/edit the properties of the domain axis (if any). 
     */
    private DefaultAxisEditor domainAxisPropertyPanel;

    /** 
     * A panel used to display/edit the properties of the range axis (if any).
     */
    private DefaultAxisEditor rangeAxisPropertyPanel;

    /** 
     * A panel used to display/edit the properties of the colorbar axis (if 
     * any).
     */
    private DefaultColorBarEditor colorBarAxisPropertyPanel;

    /** An array of stroke samples to choose from. */
    private StrokeSample[] availableStrokeSamples;

    /** The insets for the plot. */
    private RectangleInsets plotInsets;

    /** 
     * The orientation for the plot (for <tt>CategoryPlot</tt>s and 
     * <tt>XYPlot</tt>s). 
     */
    private PlotOrientation plotOrientation;

    /** 
     * The orientation combo box (for <tt>CategoryPlot</tt>s and 
     * <tt>XYPlot</tt>s). 
     */
    private JComboBox orientationCombo;

    /** Whether or not to draw lines between each data point (for
     * <tt>LineAndShapeRenderer</tt>s and <tt>StandardXYItemRenderer</tt>s).
     */
    private Boolean drawLines;

    /**
     * The checkbox for whether or not to draw lines between each data point.
     */
    private JCheckBox drawLinesCheckBox;

    /** Whether or not to draw shapes at each data point (for
     * <tt>LineAndShapeRenderer</tt>s and <tt>StandardXYItemRenderer</tt>s).
     */
    private Boolean drawShapes;

    /**
     * The checkbox for whether or not to draw shapes at each data point.
     */
    private JCheckBox drawShapesCheckBox;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
        = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[4]++;
  }

    /**
     * Standard constructor - constructs a panel for editing the properties of
     * the specified plot.
     * <P>
     * In designing the panel, we need to be aware that subclasses of Plot will
     * need to implement subclasses of PlotPropertyEditPanel - so we need to
     * leave one or two 'slots' where the subclasses can extend the user
     * interface.
     *
     * @param plot  the plot, which should be changed.
     */
    public DefaultPlotEditor(Plot plot) {

        this.plotInsets = plot.getInsets();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[5]++;
        this.backgroundPaintSample = new PaintSample(plot.getBackgroundPaint());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[6]++;
        this.outlineStrokeSample = new StrokeSample(plot.getOutlineStroke());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[7]++;
        this.outlinePaintSample = new PaintSample(plot.getOutlinePaint());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[8]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[1]++;
            this.plotOrientation = ((CategoryPlot) plot).getOrientation();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[10]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[2]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[11]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[3]++;
            this.plotOrientation = ((XYPlot) plot).getOrientation();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[12]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[4]++;}
}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[5]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[14]++;
            CategoryItemRenderer renderer = ((CategoryPlot) plot).getRenderer();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((renderer instanceof LineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[7]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[16]++;
                LineAndShapeRenderer r = (LineAndShapeRenderer) renderer;
                this.drawLines 
                    = BooleanUtilities.valueOf(r.getBaseLinesVisible());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[17]++;
                this.drawShapes 
                    = BooleanUtilities.valueOf(r.getBaseShapesVisible());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[18]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[8]++;}

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[6]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[19]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[9]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[20]++;
            XYItemRenderer renderer = ((XYPlot) plot).getRenderer();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((renderer instanceof StandardXYItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[11]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[22]++;
                StandardXYItemRenderer r = (StandardXYItemRenderer) renderer;
                this.drawLines = BooleanUtilities.valueOf(r.getPlotLines());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[23]++;
                this.drawShapes = BooleanUtilities.valueOf(
                        r.getBaseShapesVisible());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[24]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[10]++;}
}

        setLayout(new BorderLayout());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[25]++;

        this.availableStrokeSamples = new StrokeSample[3];
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[26]++;
        this.availableStrokeSamples[0] 
            = new StrokeSample(new BasicStroke(1.0f));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[27]++;
        this.availableStrokeSamples[1] 
            = new StrokeSample(new BasicStroke(2.0f));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[28]++;
        this.availableStrokeSamples[2] 
            = new StrokeSample(new BasicStroke(3.0f));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[29]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[30]++;

        // create a panel for the settings...
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                plot.getPlotType() + localizationResources.getString(":")
            )
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[31]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[32]++;

        JPanel general = new JPanel(new BorderLayout());
        general.setBorder(
            BorderFactory.createTitledBorder(
                localizationResources.getString("General")
            )
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[33]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[34]++;

        JPanel interior = new JPanel(new LCBLayout(7));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[35]++;

//        interior.add(new JLabel(localizationResources.getString("Insets")));
//        JButton button = new JButton(
//            localizationResources.getString("Edit...")
//        );
//        button.setActionCommand("Insets");
//        button.addActionListener(this);
//
//        this.insetsTextField = new InsetsTextField(this.plotInsets);
//        this.insetsTextField.setEnabled(false);
//        interior.add(this.insetsTextField);
//        interior.add(button);

        interior.add(
            new JLabel(localizationResources.getString("Outline_stroke"))
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[36]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[37]++;
        JButton button = new JButton(localizationResources.getString(
                "Select..."));
        button.setActionCommand("OutlineStroke");
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[38]++;
        button.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[39]++;
        interior.add(this.outlineStrokeSample);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[40]++;
        interior.add(button);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[41]++;

        interior.add(
            new JLabel(localizationResources.getString("Outline_Paint"))
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[42]++;
        button = new JButton(localizationResources.getString("Select..."));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[43]++;
        button.setActionCommand("OutlinePaint");
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[44]++;
        button.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[45]++;
        interior.add(this.outlinePaintSample);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[46]++;
        interior.add(button);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[47]++;

        interior.add(
            new JLabel(localizationResources.getString("Background_paint"))
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[48]++;
        button = new JButton(localizationResources.getString("Select..."));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[49]++;
        button.setActionCommand("BackgroundPaint");
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[50]++;
        button.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[51]++;
        interior.add(this.backgroundPaintSample);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[52]++;
        interior.add(button);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[53]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[54]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.plotOrientation != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[13]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[55]++;
            boolean isVertical 
                = this.plotOrientation.equals(PlotOrientation.VERTICAL);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[56]++;
            int index 
                = isVertical ? ORIENTATION_VERTICAL : ORIENTATION_HORIZONTAL;
            interior.add(
                new JLabel(localizationResources.getString("Orientation"))
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[57]++;
            this.orientationCombo = new JComboBox(orientationNames);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[58]++;
            this.orientationCombo.setSelectedIndex(index);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[59]++;
            this.orientationCombo.setActionCommand("Orientation");
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[60]++;
            this.orientationCombo.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[61]++;
            interior.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[62]++;
            interior.add(this.orientationCombo);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[63]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[14]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[64]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.drawLines != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[15]++;
            interior.add(
                new JLabel(localizationResources.getString("Draw_lines"))
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[65]++;
            this.drawLinesCheckBox = new JCheckBox();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[66]++;
            this.drawLinesCheckBox.setSelected(this.drawLines.booleanValue());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[67]++;
            this.drawLinesCheckBox.setActionCommand("DrawLines");
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[68]++;
            this.drawLinesCheckBox.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[69]++;
            interior.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[70]++;
            interior.add(this.drawLinesCheckBox);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[71]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[16]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[72]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.drawShapes != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[17]++;
            interior.add(
                new JLabel(localizationResources.getString("Draw_shapes"))
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[73]++;
            this.drawShapesCheckBox = new JCheckBox();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[74]++;
            this.drawShapesCheckBox.setSelected(this.drawShapes.booleanValue());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[75]++;
            this.drawShapesCheckBox.setActionCommand("DrawShapes");
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[76]++;
            this.drawShapesCheckBox.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[77]++;
            interior.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[78]++;
            interior.add(this.drawShapesCheckBox);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[79]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[18]++;}

        general.add(interior, BorderLayout.NORTH);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[80]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[81]++;

        JPanel appearance = new JPanel(new BorderLayout());
        appearance.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[82]++;
        appearance.add(general, BorderLayout.NORTH);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[83]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[84]++;

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[85]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[86]++;

        Axis domainAxis = null;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[87]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[19]++;
            domainAxis = ((CategoryPlot) plot).getDomainAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[88]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[20]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[89]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[21]++;
            domainAxis = ((XYPlot) plot).getDomainAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[90]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[22]++;}
}
        this.domainAxisPropertyPanel 
            = DefaultAxisEditor.getInstance(domainAxis);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[91]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[92]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.domainAxisPropertyPanel != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[23]++;
            this.domainAxisPropertyPanel.setBorder(
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[93]++;
            tabs.add(
                localizationResources.getString("Domain_Axis"), 
                this.domainAxisPropertyPanel
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[94]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[24]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[95]++;

        Axis rangeAxis = null;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[96]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[25]++;
            rangeAxis = ((CategoryPlot) plot).getRangeAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[97]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[26]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[98]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[27]++;
            rangeAxis = ((XYPlot) plot).getRangeAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[99]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[28]++;}
}

        this.rangeAxisPropertyPanel 
            = DefaultAxisEditor.getInstance(rangeAxis);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[100]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[101]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.rangeAxisPropertyPanel != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[29]++;
            this.rangeAxisPropertyPanel.setBorder(
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[102]++;
            tabs.add(
                localizationResources.getString("Range_Axis"), 
                this.rangeAxisPropertyPanel
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[103]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[30]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[104]++;

//dmo: added this panel for colorbar control. (start dmo additions)
        ColorBar colorBar = null;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[105]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((plot instanceof ContourPlot) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[31]++;
            colorBar = ((ContourPlot) plot).getColorBar();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[106]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[32]++;}

        this.colorBarAxisPropertyPanel 
            = DefaultColorBarEditor.getInstance(colorBar);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[107]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[108]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.colorBarAxisPropertyPanel != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[33]++;
            this.colorBarAxisPropertyPanel.setBorder(
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[109]++;
            tabs.add(
                localizationResources.getString("Color_Bar"), 
                this.colorBarAxisPropertyPanel
            );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[110]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[34]++;}
//dmo: (end dmo additions)

        tabs.add(localizationResources.getString("Appearance"), appearance);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[111]++;
        panel.add(tabs);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[112]++;

        add(panel);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[113]++;
    }

    /**
     * Returns the current plot insets.
     * 
     * @return The current plot insets.
     */
    public RectangleInsets getPlotInsets() {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[114]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.plotInsets == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[35]++;
            this.plotInsets = new RectangleInsets(0.0, 0.0, 0.0, 0.0);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[115]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[36]++;}
        return this.plotInsets;
    }

    /**
     * Returns the current background paint.
     * 
     * @return The current background paint.
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaintSample.getPaint();
    }

    /**
     * Returns the current outline stroke.
     * 
     * @return The current outline stroke.
     */
    public Stroke getOutlineStroke() {
        return this.outlineStrokeSample.getStroke();
    }

    /**
     * Returns the current outline paint.
     * 
     * @return The current outline paint.
     */
    public Paint getOutlinePaint() {
        return this.outlinePaintSample.getPaint();
    }

    /**
     * Returns a reference to the panel for editing the properties of the
     * domain axis.
     *
     * @return A reference to a panel.
     */
    public DefaultAxisEditor getDomainAxisPropertyEditPanel() {
        return this.domainAxisPropertyPanel;
    }

    /**
     * Returns a reference to the panel for editing the properties of the
     * range axis.
     *
     * @return A reference to a panel.
     */
    public DefaultAxisEditor getRangeAxisPropertyEditPanel() {
        return this.rangeAxisPropertyPanel;
    }

    /**
     * Handles user actions generated within the panel.
     * @param event     the event
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[116]++;
        String command = event.getActionCommand();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[117]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((command.equals("BackgroundPaint")) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[37]++;
            attemptBackgroundPaintSelection();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[118]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[38]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[119]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((command.equals("OutlineStroke")) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[39]++;
            attemptOutlineStrokeSelection();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[120]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[40]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[121]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((command.equals("OutlinePaint")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[41]++;
            attemptOutlinePaintSelection();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[122]++;

        }
//        else if (command.equals("Insets")) {
//            editInsets();
//        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[42]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[123]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((command.equals("Orientation")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[43]++;
            attemptOrientationSelection();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[124]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[44]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[125]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((command.equals("DrawLines")) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[45]++;
            attemptDrawLinesSelection();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[126]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[46]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[127]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((command.equals("DrawShapes")) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[47]++;
            attemptDrawShapesSelection();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[128]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[48]++;}
}
}
}
}
}
    }

    /**
     * Allow the user to change the background paint.
     */
    private void attemptBackgroundPaintSelection() {
        Color c;
        c = JColorChooser.showDialog(
            this, localizationResources.getString("Background_Color"),
            Color.blue
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[129]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[130]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[49]++;
            this.backgroundPaintSample.setPaint(c);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[131]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[50]++;}
    }

    /**
     * Allow the user to change the outline stroke.
     */
    private void attemptOutlineStrokeSelection() {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[132]++;
        StrokeChooserPanel panel 
            = new StrokeChooserPanel(null, this.availableStrokeSamples);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[133]++;
        int result = JOptionPane.showConfirmDialog(this, panel,
            localizationResources.getString("Stroke_Selection"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[134]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((result == JOptionPane.OK_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[51]++;
            this.outlineStrokeSample.setStroke(panel.getSelectedStroke());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[135]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[52]++;}
    }

    /**
     * Allow the user to change the outline paint.  We use JColorChooser, so
     * the user can only choose colors (a subset of all possible paints).
     */
    private void attemptOutlinePaintSelection() {
        Color c;
        c = JColorChooser.showDialog(
            this, localizationResources.getString("Outline_Color"), Color.blue
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[136]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[137]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[53]++;
            this.outlinePaintSample.setPaint(c);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[138]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[54]++;}
    }

//    /**
//     * Allow the user to edit the individual insets' values.
//     */
//    private void editInsets() {
//        InsetsChooserPanel panel = new InsetsChooserPanel(this.plotInsets);
//        int result = JOptionPane.showConfirmDialog(
//            this, panel, localizationResources.getString("Edit_Insets"),
//            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
//        );
//
//        if (result == JOptionPane.OK_OPTION) {
//            this.plotInsets = panel.getInsets();
//            this.insetsTextField.setInsets(this.plotInsets);
//        }
//
//    }
//
    /**
     * Allow the user to modify the plot orientation if this is an editor for a
     * <tt>CategoryPlot</tt> or a <tt>XYPlot</tt>.
     */
    private void attemptOrientationSelection() {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[139]++;

        int index = this.orientationCombo.getSelectedIndex();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[140]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((index == ORIENTATION_VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[55]++;
            this.plotOrientation = PlotOrientation.VERTICAL;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[141]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[56]++;
            this.plotOrientation = PlotOrientation.HORIZONTAL;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[142]++;
        }
    }

    /**
     * Allow the user to modify whether or not lines are drawn between data 
     * points by <tt>LineAndShapeRenderer</tt>s and 
     * <tt>StandardXYItemRenderer</tt>s.
     */
    private void attemptDrawLinesSelection() {
        this.drawLines = BooleanUtilities.valueOf(
            this.drawLinesCheckBox.isSelected()
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[143]++;
    }

    /**
     * Allow the user to modify whether or not shapes are drawn at data points
     * by <tt>LineAndShapeRenderer</tt>s and <tt>StandardXYItemRenderer</tt>s.
     */
    private void attemptDrawShapesSelection() {
        this.drawShapes = BooleanUtilities.valueOf(
            this.drawShapesCheckBox.isSelected()
        );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[144]++;
    }

    /**
     * Updates the plot properties to match the properties defined on the panel.
     *
     * @param plot  The plot.
     */
    public void updatePlotProperties(Plot plot) {

        // set the plot properties...
        plot.setOutlinePaint(getOutlinePaint());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[145]++;
        plot.setOutlineStroke(getOutlineStroke());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[146]++;
        plot.setBackgroundPaint(getBackgroundPaint());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[147]++;
        plot.setInsets(getPlotInsets());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[148]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[149]++;
int CodeCoverConditionCoverageHelper_C29;

        // then the axis properties...
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.domainAxisPropertyPanel != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[57]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[150]++;
            Axis domainAxis = null;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[151]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[59]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[152]++;
                CategoryPlot p = (CategoryPlot) plot;
                domainAxis = p.getDomainAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[153]++;

            }
            else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[60]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[154]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[61]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[155]++;
                XYPlot p = (XYPlot) plot;
                domainAxis = p.getDomainAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[156]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[62]++;}
}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[157]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[63]++;
                this.domainAxisPropertyPanel.setAxisProperties(domainAxis);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[158]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[64]++;}

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[58]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[159]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((this.rangeAxisPropertyPanel != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[65]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[160]++;
            Axis rangeAxis = null;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[161]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[67]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[162]++;
                CategoryPlot p = (CategoryPlot) plot;
                rangeAxis = p.getRangeAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[163]++;

            }
            else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[68]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[164]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[69]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[165]++;
                XYPlot p = (XYPlot) plot;
                rangeAxis = p.getRangeAxis();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[166]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[70]++;}
}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[167]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[71]++;
                this.rangeAxisPropertyPanel.setAxisProperties(rangeAxis);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[168]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[72]++;}

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[66]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[169]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.plotOrientation != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[73]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[170]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[75]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[171]++;
                CategoryPlot p = (CategoryPlot) plot;
                p.setOrientation(this.plotOrientation);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[172]++;

            }
            else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[76]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[173]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[77]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[174]++;
                XYPlot p = (XYPlot) plot;
                p.setOrientation(this.plotOrientation);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[175]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[78]++;}
}

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[74]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[176]++;
int CodeCoverConditionCoverageHelper_C40;

        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.drawLines != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[79]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[177]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[81]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[178]++;
                CategoryPlot p = (CategoryPlot) plot;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[179]++;
                CategoryItemRenderer r = p.getRenderer();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[180]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((r instanceof LineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[83]++;
                    ((LineAndShapeRenderer) r).setLinesVisible(
                        this.drawLines.booleanValue()
                    );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[181]++;

                } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[84]++;}

            }
            else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[82]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[182]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[85]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[183]++;
                XYPlot p = (XYPlot) plot;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[184]++;
                XYItemRenderer r = p.getRenderer();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[185]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((r instanceof StandardXYItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[87]++;
                    ((StandardXYItemRenderer) r).setPlotLines(
                        this.drawLines.booleanValue()
                    );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[186]++;

                } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[88]++;}

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[86]++;}
}

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[80]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[187]++;
int CodeCoverConditionCoverageHelper_C45;

        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.drawShapes != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[89]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[188]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[91]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[189]++;
                CategoryPlot p = (CategoryPlot) plot;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[190]++;
                CategoryItemRenderer r = p.getRenderer();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[191]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((r instanceof LineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[93]++;
                    ((LineAndShapeRenderer) r).setShapesVisible(
                        this.drawShapes.booleanValue()
                    );
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[192]++;

                } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[94]++;}

            }
            else {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[92]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[193]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((plot instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[95]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[194]++;
                XYPlot p = (XYPlot) plot;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[195]++;
                XYItemRenderer r = p.getRenderer();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[196]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((r instanceof StandardXYItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[97]++;
                    ((StandardXYItemRenderer) r).setBaseShapesVisible(
                        this.drawShapes.booleanValue());
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[197]++;

                } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[98]++;}

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[96]++;}
}

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[90]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[198]++;
int CodeCoverConditionCoverageHelper_C50;

//dmo: added this panel for colorbar control. (start dmo additions)
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.colorBarAxisPropertyPanel != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[99]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[199]++;
            ColorBar colorBar = null;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[200]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((plot instanceof  ContourPlot) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[101]++;
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[201]++;
                ContourPlot p = (ContourPlot) plot;
                colorBar = p.getColorBar();
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[202]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[102]++;}
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[203]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((colorBar != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[103]++;
                this.colorBarAxisPropertyPanel.setAxisProperties(colorBar);
CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.statements[204]++;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[104]++;}

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup.branches[100]++;}
//dmo: (end dmo additions)

    }

}

class CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup ());
  }
    public static long[] statements = new long[205];
    public static long[] branches = new long[105];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[53];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.DefaultPlotEditor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 52; i++) {
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

  public CodeCoverCoverageCounter$b80b2dtcd297h637h0hxwujntr1mf93lup () {
    super("org.jfree.chart.editor.DefaultPlotEditor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 204; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 104; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 52; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.editor.DefaultPlotEditor.java");
      for (int i = 1; i <= 204; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 104; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 52; i++) {
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

