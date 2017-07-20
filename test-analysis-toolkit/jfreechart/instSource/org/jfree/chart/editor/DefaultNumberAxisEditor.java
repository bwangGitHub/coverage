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
 * ----------------------------
 * DefaultNumberAxisEditor.java
 * ----------------------------
 * (C) Copyright 2005, 2007, Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Arnaud Lelievre;
 *
 * Changes:
 * --------
 * 24-Nov-2005 : Version 1, based on NumberAxisPropertyEditor (DG);
 * 
 */

package org.jfree.chart.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.layout.LCBLayout;
import org.jfree.ui.PaintSample;
import org.jfree.ui.StrokeChooserPanel;
import org.jfree.ui.StrokeSample;

/**
 * A panel for editing the properties of a value axis.
 */
class DefaultNumberAxisEditor extends DefaultAxisEditor 
                              implements FocusListener {
  static {
    CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.ping();
  }


    /** A flag that indicates whether or not the axis range is determined
     *  automatically.
     */
    private boolean autoRange;

    /** The lowest value in the axis range. */
    private double minimumValue;

    /** The highest value in the axis range. */
    private double maximumValue;

    /** A checkbox that indicates whether or not the axis range is determined
     *  automatically.
     */
    private JCheckBox autoRangeCheckBox;

    /** A text field for entering the minimum value in the axis range. */
    private JTextField minimumRangeValue;

    /** A text field for entering the maximum value in the axis range. */
    private JTextField maximumRangeValue;

    /** The paint selected for drawing the gridlines. */
    private PaintSample gridPaintSample;

    /** The stroke selected for drawing the gridlines. */
    private StrokeSample gridStrokeSample;

    /** An array of stroke samples to choose from (since I haven't written a
     *  decent StrokeChooser component yet).
     */
    private StrokeSample[] availableStrokeSamples;
    
    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources = 
        ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[1]++;
  }

    /**
     * Standard constructor: builds a property panel for the specified axis.
     *
     * @param axis  the axis, which should be changed.
     */
    public DefaultNumberAxisEditor(NumberAxis axis) {

        super(axis);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[2]++;

        this.autoRange = axis.isAutoRange();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[3]++;
        this.minimumValue = axis.getLowerBound();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[4]++;
        this.maximumValue = axis.getUpperBound();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[5]++;

        this.gridPaintSample = new PaintSample(Color.blue);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[6]++;
        this.gridStrokeSample = new StrokeSample(new BasicStroke(1.0f));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[7]++;

        this.availableStrokeSamples = new StrokeSample[3];
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[8]++;
        this.availableStrokeSamples[0] 
            = new StrokeSample(new BasicStroke(1.0f));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[9]++;
        this.availableStrokeSamples[1] 
            = new StrokeSample(new BasicStroke(2.0f));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[10]++;
        this.availableStrokeSamples[2] 
            = new StrokeSample(new BasicStroke(3.0f));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[11]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[12]++;

        JTabbedPane other = getOtherTabs();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[13]++;

        JPanel range = new JPanel(new LCBLayout(3));
        range.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[14]++;

        range.add(new JPanel());
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[15]++;
        this.autoRangeCheckBox = new JCheckBox(
            localizationResources.getString("Auto-adjust_range"), this.autoRange
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[16]++;
        this.autoRangeCheckBox.setActionCommand("AutoRangeOnOff");
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[17]++;
        this.autoRangeCheckBox.addActionListener(this);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[18]++;
        range.add(this.autoRangeCheckBox);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[19]++;
        range.add(new JPanel());
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[20]++;

        range.add(
            new JLabel(localizationResources.getString("Minimum_range_value"))
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[21]++;
        this.minimumRangeValue = new JTextField(
            Double.toString(this.minimumValue)
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[22]++;
        this.minimumRangeValue.setEnabled(!this.autoRange);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[23]++;
        this.minimumRangeValue.setActionCommand("MinimumRange");
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[24]++;
        this.minimumRangeValue.addActionListener(this);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[25]++;
        this.minimumRangeValue.addFocusListener(this);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[26]++;
        range.add(this.minimumRangeValue);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[27]++;
        range.add(new JPanel());
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[28]++;

        range.add(
            new JLabel(localizationResources.getString("Maximum_range_value"))
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[29]++;
        this.maximumRangeValue = new JTextField(
            Double.toString(this.maximumValue)
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[30]++;
        this.maximumRangeValue.setEnabled(!this.autoRange);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[31]++;
        this.maximumRangeValue.setActionCommand("MaximumRange");
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[32]++;
        this.maximumRangeValue.addActionListener(this);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[33]++;
        this.maximumRangeValue.addFocusListener(this);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[34]++;
        range.add(this.maximumRangeValue);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[35]++;
        range.add(new JPanel());
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[36]++;

        other.add(localizationResources.getString("Range"), range);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[37]++;

    }

    /**
     * Returns the current setting of the auto-range property.
     *
     * @return <code>true</code> if auto range is enabled.
     */
    public boolean isAutoRange() {
        return this.autoRange;
    }

    /**
     * Returns the current setting of the minimum value in the axis range.
     *
     * @return The current setting of the minimum value in the axis range.
     */
    public double getMinimumValue() {
        return this.minimumValue;
    }

    /**
     * Returns the current setting of the maximum value in the axis range.
     *
     * @return The current setting of the maximum value in the axis range.
     */
    public double getMaximumValue() {
        return this.maximumValue;
    }

    /**
     * Handles actions from within the property panel.
     * @param event an event.
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[38]++;
        String command = event.getActionCommand();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[39]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((command.equals("GridStroke")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[1]++;
            attemptGridStrokeSelection();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[40]++;

        }
        else {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[2]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[41]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((command.equals("GridPaint")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[3]++;
            attemptGridPaintSelection();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[42]++;

        }
        else {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[4]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[43]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((command.equals("AutoRangeOnOff")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[5]++;
            toggleAutoRange();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[44]++;

        }
        else {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[6]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[45]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((command.equals("MinimumRange")) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[7]++;
            validateMinimum();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[46]++;

        }
        else {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[8]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[47]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((command.equals("MaximumRange")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[9]++;
            validateMaximum();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[48]++;

        }
        else {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[10]++;
            // pass to the super-class for handling
            super.actionPerformed(event);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[49]++;
        }
}
}
}
}
    }

    /**
     * Handle a grid stroke selection.
     */
    private void attemptGridStrokeSelection() {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[50]++;
        StrokeChooserPanel panel = new StrokeChooserPanel(
            null, this.availableStrokeSamples
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[51]++;
        int result = JOptionPane.showConfirmDialog(
            this, panel, localizationResources.getString("Stroke_Selection"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[52]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((result == JOptionPane.OK_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[11]++;
            this.gridStrokeSample.setStroke(panel.getSelectedStroke());
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[53]++;

        } else {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[12]++;}
    }

    /**
     * Handle a grid paint selection.
     */
    private void attemptGridPaintSelection() {
        Color c;
        c = JColorChooser.showDialog(
            this, localizationResources.getString("Grid_Color"), Color.blue
        );
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[54]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[55]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[13]++;
            this.gridPaintSample.setPaint(c);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[56]++;

        } else {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[14]++;}
    }

    /**
     * Does nothing.
     *
     * @param event  the event.
     */
    public void focusGained(FocusEvent event) {
        // don't need to do anything
    }

    /**
     *  Revalidates minimum/maximum range.
     *
     *  @param event  the event.
     */
    public void focusLost(FocusEvent event) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[57]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((event.getSource() == this.minimumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[15]++;
            validateMinimum();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[58]++;

        }
        else {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[16]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[59]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((event.getSource() == this.maximumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[17]++;
            validateMaximum();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[60]++;

        } else {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[18]++;}
}
    }

    /**
     *  Toggle the auto range setting.
     */
    public void toggleAutoRange() {
        this.autoRange = this.autoRangeCheckBox.isSelected();
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[61]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[62]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.autoRange) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[19]++;
            this.minimumRangeValue.setText(Double.toString(this.minimumValue));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[63]++;
            this.minimumRangeValue.setEnabled(false);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[64]++;
            this.maximumRangeValue.setText(Double.toString(this.maximumValue));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[65]++;
            this.maximumRangeValue.setEnabled(false);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[66]++;

        }
        else {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[20]++;
            this.minimumRangeValue.setEnabled(true);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[67]++;
            this.maximumRangeValue.setEnabled(true);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[68]++;
        }
    }

    /**
     * Revalidate the range minimum.
     */
    public void validateMinimum() {
        double newMin;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[69]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            newMin = Double.parseDouble(this.minimumRangeValue.getText());
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[70]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[71]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((newMin >= this.maximumValue) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[22]++;
                newMin = this.minimumValue;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[72]++;

            } else {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[23]++;}
        }
        catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[24]++;
            newMin = this.minimumValue;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[73]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[21]++;
}
  }

        this.minimumValue = newMin;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[74]++;
        this.minimumRangeValue.setText(Double.toString(this.minimumValue));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[75]++;
    }

    /**
     * Revalidate the range maximum.
     */
    public void validateMaximum() {
        double newMax;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[76]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            newMax = Double.parseDouble(this.maximumRangeValue.getText());
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[77]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[78]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((newMax <= this.minimumValue) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[26]++;
                newMax = this.maximumValue;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[79]++;

            } else {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[27]++;}
        }
        catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[28]++;
            newMax = this.maximumValue;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[80]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[25]++;
}
  }

        this.maximumValue = newMax;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[81]++;
        this.maximumRangeValue.setText(Double.toString(this.maximumValue));
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[82]++;
    }

    /**
     * Sets the properties of the specified axis to match the properties
     * defined on this panel.
     *
     * @param axis  the axis.
     */
    public void setAxisProperties(Axis axis) {
        super.setAxisProperties(axis);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[83]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[84]++;
        NumberAxis numberAxis = (NumberAxis) axis;
        numberAxis.setAutoRange(this.autoRange);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[85]++;
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[86]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.autoRange) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[29]++;
            numberAxis.setRange(this.minimumValue, this.maximumValue);
CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.statements[87]++;

        } else {
  CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5.branches[30]++;}
    }

}

class CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5 ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.DefaultNumberAxisEditor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$v3prr2stv8y72rcq4zu52un9osisyjd0f89boi6yzj5 () {
    super("org.jfree.chart.editor.DefaultNumberAxisEditor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.editor.DefaultNumberAxisEditor.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

