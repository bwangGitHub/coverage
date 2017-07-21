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
 * DefaultAxisEditor.java
 * ----------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Andrzej Porebski;
 *                   Arnaud Lelievre;
 *
 * Changes
 * -------
 * 24-Nov-2005 : Version 1, based on AxisPropertyEditPanel.java (DG); 
 *
 */

package org.jfree.chart.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import org.jfree.ui.FontChooserPanel;
import org.jfree.ui.FontDisplayField;
import org.jfree.ui.PaintSample;
import org.jfree.ui.RectangleInsets;

/**
 * A panel for editing the properties of an axis.
 */
class DefaultAxisEditor extends JPanel implements ActionListener {
  static {
    CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.ping();
  }


    /** The axis label. */
    private JTextField label;

    /** The label font. */
    private Font labelFont;

    /** The label paint. */
    private PaintSample labelPaintSample;

    /** A field showing a description of the label font. */
    private JTextField labelFontField;

    /** The font for displaying tick labels on the axis. */
    private Font tickLabelFont;

    /** 
     * A field containing a description of the font for displaying tick labels 
     * on the axis. 
     */
    private JTextField tickLabelFontField;

    /** The paint (color) for the tick labels. */
    private PaintSample tickLabelPaintSample;

    /**
     * An empty sub-panel for extending the user interface to handle more 
     * complex axes. 
     */
    private JPanel slot1;

    /** 
     * An empty sub-panel for extending the user interface to handle more 
     * complex axes. 
     */
    private JPanel slot2;

    /** A flag that indicates whether or not the tick labels are visible. */
    private JCheckBox showTickLabelsCheckBox;

    /** A flag that indicates whether or not the tick marks are visible. */
    private JCheckBox showTickMarksCheckBox;

//    /** Insets text field. */
//    private InsetsTextField tickLabelInsetsTextField;
//
//    /** Label insets text field. */
//    private InsetsTextField labelInsetsTextField;

    /** The tick label insets. */
    private RectangleInsets tickLabelInsets;

    /** The label insets. */
    private RectangleInsets labelInsets;

    /** A tabbed pane for... */
    private JTabbedPane otherTabs;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources = 
        ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[1]++;
  }

    /**
     * A static method that returns a panel that is appropriate for the axis
     * type.
     *
     * @param axis  the axis whose properties are to be displayed/edited in 
     *              the panel.
     *
     * @return A panel or <code>null</code< if axis is <code>null</code>.
     */
    public static DefaultAxisEditor getInstance(Axis axis) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[1]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
            // figure out what type of axis we have and instantiate the
            // appropriate panel
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((axis instanceof NumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[3]++;
                return new DefaultNumberAxisEditor((NumberAxis) axis);

            }
            else {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[4]++;
                return new DefaultAxisEditor(axis);
            }

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[2]++;
            return null;
        }

    }

    /**
     * Standard constructor: builds a panel for displaying/editing the
     * properties of the specified axis.
     *
     * @param axis  the axis whose properties are to be displayed/edited in 
     *              the panel.
     */
    public DefaultAxisEditor(Axis axis) {

        this.labelFont = axis.getLabelFont();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[4]++;
        this.labelPaintSample = new PaintSample(axis.getLabelPaint());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[5]++;
        this.tickLabelFont = axis.getTickLabelFont();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[6]++;
        this.tickLabelPaintSample = new PaintSample(axis.getTickLabelPaint());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[7]++;

        // Insets values
        this.tickLabelInsets = axis.getTickLabelInsets();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[8]++;
        this.labelInsets = axis.getLabelInsets();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[9]++;

        setLayout(new BorderLayout());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[10]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[11]++;

        JPanel general = new JPanel(new BorderLayout());
        general.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                localizationResources.getString("General")
            )
        );
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[12]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[13]++;

        JPanel interior = new JPanel(new LCBLayout(5));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[14]++;
        interior.add(new JLabel(localizationResources.getString("Label")));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[15]++;
        this.label = new JTextField(axis.getLabel());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[16]++;
        interior.add(this.label);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[17]++;
        interior.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[18]++;

        interior.add(new JLabel(localizationResources.getString("Font")));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[19]++;
        this.labelFontField = new FontDisplayField(this.labelFont);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[20]++;
        interior.add(this.labelFontField);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[21]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[22]++;
        JButton b = new JButton(localizationResources.getString("Select..."));
        b.setActionCommand("SelectLabelFont");
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[23]++;
        b.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[24]++;
        interior.add(b);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[25]++;

        interior.add(new JLabel(localizationResources.getString("Paint")));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[26]++;
        interior.add(this.labelPaintSample);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[27]++;
        b = new JButton(localizationResources.getString("Select..."));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[28]++;
        b.setActionCommand("SelectLabelPaint");
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[29]++;
        b.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[30]++;
        interior.add(b);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[31]++;

//        interior.add(
//            new JLabel(localizationResources.getString("Label_Insets"))
//        );
//        b = new JButton(localizationResources.getString("Edit..."));
//        b.setActionCommand("LabelInsets");
//        b.addActionListener(this);
//        this.labelInsetsTextField = new InsetsTextField(this.labelInsets);
//        interior.add(this.labelInsetsTextField);
//        interior.add(b);
//
//        interior.add(
//            new JLabel(localizationResources.getString("Tick_Label_Insets"))
//        );
//        b = new JButton(localizationResources.getString("Edit..."));
//        b.setActionCommand("TickLabelInsets");
//        b.addActionListener(this);
//        this.tickLabelInsetsTextField 
//            = new InsetsTextField(this.tickLabelInsets);
//        interior.add(this.tickLabelInsetsTextField);
//        interior.add(b);

        general.add(interior);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[32]++;

        add(general, BorderLayout.NORTH);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[33]++;

        this.slot1 = new JPanel(new BorderLayout());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[34]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[35]++;

        JPanel other = new JPanel(new BorderLayout());
        other.setBorder(BorderFactory.createTitledBorder(
                             BorderFactory.createEtchedBorder(), 
                             localizationResources.getString("Other")));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[36]++;

        this.otherTabs = new JTabbedPane();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[37]++;
        this.otherTabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[38]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[39]++;

        JPanel ticks = new JPanel(new LCBLayout(3));
        ticks.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[40]++;

        this.showTickLabelsCheckBox = new JCheckBox(
            localizationResources.getString("Show_tick_labels"), 
            axis.isTickLabelsVisible()
        );
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[41]++;
        ticks.add(this.showTickLabelsCheckBox);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[42]++;
        ticks.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[43]++;
        ticks.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[44]++;

        ticks.add(
            new JLabel(localizationResources.getString("Tick_label_font"))
        );
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[45]++;
        this.tickLabelFontField = new FontDisplayField(this.tickLabelFont);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[46]++;
        ticks.add(this.tickLabelFontField);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[47]++;
        b = new JButton(localizationResources.getString("Select..."));
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[48]++;
        b.setActionCommand("SelectTickLabelFont");
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[49]++;
        b.addActionListener(this);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[50]++;
        ticks.add(b);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[51]++;

        this.showTickMarksCheckBox = new JCheckBox(
            localizationResources.getString("Show_tick_marks"), 
            axis.isTickMarksVisible()
        );
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[52]++;
        ticks.add(this.showTickMarksCheckBox);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[53]++;
        ticks.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[54]++;
        ticks.add(new JPanel());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[55]++;

        this.otherTabs.add(localizationResources.getString("Ticks"), ticks);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[56]++;

        other.add(this.otherTabs);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[57]++;

        this.slot1.add(other);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[58]++;

        this.slot2 = new JPanel(new BorderLayout());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[59]++;
        this.slot2.add(this.slot1, BorderLayout.NORTH);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[60]++;
        add(this.slot2);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[61]++;

    }

    /**
     * Returns the current axis label.
     *
     * @return The current axis label.
     */
    public String getLabel() {
        return this.label.getText();
    }

    /**
     * Returns the current label font.
     *
     * @return The current label font.
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Returns the current label paint.
     *
     * @return The current label paint.
     */
    public Paint getLabelPaint() {
        return this.labelPaintSample.getPaint();
    }

    /**
     * Returns a flag that indicates whether or not the tick labels are visible.
     *
     * @return <code>true</code> if ick mark labels are visible.
     */
    public boolean isTickLabelsVisible() {
        return this.showTickLabelsCheckBox.isSelected();
    }

    /**
     * Returns the font used to draw the tick labels (if they are showing).
     *
     * @return The font used to draw the tick labels.
     */
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }

    /**
     * Returns the current tick label paint.
     *
     * @return The current tick label paint.
     */
    public Paint getTickLabelPaint() {
        return this.tickLabelPaintSample.getPaint();
    }

    /**
     * Returns the current value of the flag that determines whether or not
     * tick marks are visible.
     *
     * @return <code>true</code> if tick marks are visible.
     */
    public boolean isTickMarksVisible() {
        return this.showTickMarksCheckBox.isSelected();
    }

    /**
     * Returns the current tick label insets value
     *
     * @return The current tick label insets value.
     */
    public RectangleInsets getTickLabelInsets() {
        return (this.tickLabelInsets == null)
            ? new RectangleInsets(0, 0, 0, 0)
            : this.tickLabelInsets;
    }

    /**
     * Returns the current label insets value
     *
     * @return The current label insets value.
     */
    public RectangleInsets getLabelInsets() {
        return (this.labelInsets == null) 
            ? new RectangleInsets(0, 0, 0, 0) : this.labelInsets;
    }

    /**
     * Returns a reference to the tabbed pane.
     *
     * @return A reference to the tabbed pane.
     */
    public JTabbedPane getOtherTabs() {
        return this.otherTabs;
    }

    /**
     * Handles user interaction with the property panel.
     * 
     * @param event  information about the event that triggered the call to
     *      this method.
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[62]++;
        String command = event.getActionCommand();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[63]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((command.equals("SelectLabelFont")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[5]++;
            attemptLabelFontSelection();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[64]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[6]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[65]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((command.equals("SelectLabelPaint")) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[7]++;
            attemptModifyLabelPaint();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[66]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[8]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[67]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((command.equals("SelectTickLabelFont")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[9]++;
            attemptTickLabelFontSelection();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[68]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[10]++;}
}
}
//        else if (command.equals("LabelInsets")) {
//            editLabelInsets();
//        }
//        else if (command.equals("TickLabelInsets")) {
//            editTickLabelInsets();
//        }
    }

    /**
     * Presents a font selection dialog to the user.
     */
    private void attemptLabelFontSelection() {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[69]++;

        FontChooserPanel panel = new FontChooserPanel(this.labelFont);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[70]++;
        int result = JOptionPane.showConfirmDialog(this, panel,
            localizationResources.getString("Font_Selection"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[71]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((result == JOptionPane.OK_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[11]++;
            this.labelFont = panel.getSelectedFont();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[72]++;
            this.labelFontField.setText(
                this.labelFont.getFontName() + " " + this.labelFont.getSize()
            );
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[73]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[12]++;}

    }

    /**
     * Allows the user the opportunity to change the outline paint.
     */
    private void attemptModifyLabelPaint() {
        Color c;
        c = JColorChooser.showDialog(
            this, localizationResources.getString("Label_Color"), Color.blue
        );
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[74]++;
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[75]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[13]++;
            this.labelPaintSample.setPaint(c);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[76]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[14]++;}
    }

    /**
     * Presents a tick label font selection dialog to the user.
     */
    public void attemptTickLabelFontSelection() {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[77]++;

        FontChooserPanel panel = new FontChooserPanel(this.tickLabelFont);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[78]++;
        int result = JOptionPane.showConfirmDialog(this, panel,
            localizationResources.getString("Font_Selection"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[79]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((result == JOptionPane.OK_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[15]++;
            this.tickLabelFont = panel.getSelectedFont();
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[80]++;
            this.tickLabelFontField.setText(
                this.tickLabelFont.getFontName() + " "
                + this.tickLabelFont.getSize()
            );
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[81]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.branches[16]++;}

    }

//    /**
//     * Presents insets chooser panel allowing user to modify tick label's
//     * individual insets values. Updates the current insets text field if 
//     * edit is accepted.
//     */
//    private void editTickLabelInsets() {
//        InsetsChooserPanel panel = new InsetsChooserPanel(
//            this.tickLabelInsets);
//        int result =  JOptionPane.showConfirmDialog(
//            this, panel, localizationResources.getString("Edit_Insets"),
//            JOptionPane.PLAIN_MESSAGE
//        );
//
//        if (result == JOptionPane.OK_OPTION) {
//            this.tickLabelInsets = panel.getInsets();
//            this.tickLabelInsetsTextField.setInsets(this.tickLabelInsets);
//        }
//    }
//
//    /**
//     * Presents insets chooser panel allowing user to modify label's
//     * individual insets values. Updates the current insets text field if edit
//     * is accepted.
//     */
//    private void editLabelInsets() {
//        InsetsChooserPanel panel = new InsetsChooserPanel(this.labelInsets);
//        int result = JOptionPane.showConfirmDialog(
//            this, panel, localizationResources.getString("Edit_Insets"),
//            JOptionPane.PLAIN_MESSAGE
//        );
//
//        if (result == JOptionPane.OK_OPTION) {
//            this.labelInsets = panel.getInsets();
//            this.labelInsetsTextField.setInsets(this.labelInsets);
//        }
//    }

    /**
     * Sets the properties of the specified axis to match the properties
     * defined on this panel.
     *
     * @param axis  the axis.
     */
    public void setAxisProperties(Axis axis) {
        axis.setLabel(getLabel());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[82]++;
        axis.setLabelFont(getLabelFont());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[83]++;
        axis.setLabelPaint(getLabelPaint());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[84]++;
        axis.setTickMarksVisible(isTickMarksVisible());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[85]++;
        // axis.setTickMarkStroke(getTickMarkStroke());
        axis.setTickLabelsVisible(isTickLabelsVisible());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[86]++;
        axis.setTickLabelFont(getTickLabelFont());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[87]++;
        axis.setTickLabelPaint(getTickLabelPaint());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[88]++;
        axis.setTickLabelInsets(getTickLabelInsets());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[89]++;
        axis.setLabelInsets(getLabelInsets());
CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069.statements[90]++;
    }

}

class CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069 ());
  }
    public static long[] statements = new long[91];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.DefaultAxisEditor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$b80b2dtcd292zturvrfh8arq0vv13gm069 () {
    super("org.jfree.chart.editor.DefaultAxisEditor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 90; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.editor.DefaultAxisEditor.java");
      for (int i = 1; i <= 90; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

