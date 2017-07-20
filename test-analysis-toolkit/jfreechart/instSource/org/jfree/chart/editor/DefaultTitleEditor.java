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
 * DefaultTitleEditor.java
 * -----------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Arnaud Lelievre;
 *                   Daniel Gredler;
 *
 * Changes
 * -------
 * 24-Nov-2005 : Version 1, based on TitlePropertyEditPanel.java (DG);
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
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.layout.LCBLayout;
import org.jfree.ui.FontChooserPanel;
import org.jfree.ui.FontDisplayField;
import org.jfree.ui.PaintSample;

/**
 * A panel for editing the properties of a chart title.
 */
class DefaultTitleEditor extends JPanel implements ActionListener {
  static {
    CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.ping();
  }


    /** Whether or not to display the title on the chart. */
    private boolean showTitle;

    /** The checkbox to indicate whether or not to display the title. */
    private JCheckBox showTitleCheckBox;

    /** A field for displaying/editing the title text. */
    private JTextField titleField;

    /** The font used to draw the title. */
    private Font titleFont;

    /** A field for displaying a description of the title font. */
    private JTextField fontfield;

    /** The button to use to select a new title font. */
    private JButton selectFontButton;

    /** The paint (color) used to draw the title. */
    private PaintSample titlePaint;

    /** The button to use to select a new paint (color) to draw the title. */
    private JButton selectPaintButton;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
        = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[1]++;
  }

    /**
     * Standard constructor: builds a panel for displaying/editing the
     * properties of the specified title.
     *
     * @param title  the title, which should be changed.
     */
    public DefaultTitleEditor(Title title) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[2]++;

        TextTitle t = (title != null ? (TextTitle) title 
                : new TextTitle(localizationResources.getString("Title")));
        this.showTitle = (title != null);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[3]++;
        this.titleFont = t.getFont();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[4]++;
        this.titleField = new JTextField(t.getText());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[5]++;
        this.titlePaint = new PaintSample(t.getPaint());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[6]++;

        setLayout(new BorderLayout());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[7]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[8]++;

        JPanel general = new JPanel(new BorderLayout());
        general.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                localizationResources.getString("General")
            )
        );
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[9]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[10]++;

        JPanel interior = new JPanel(new LCBLayout(4));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[11]++;

        interior.add(new JLabel(localizationResources.getString("Show_Title")));
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[12]++;
        this.showTitleCheckBox = new JCheckBox();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[13]++;
        this.showTitleCheckBox.setSelected(this.showTitle);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[14]++;
        this.showTitleCheckBox.setActionCommand("ShowTitle");
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[15]++;
        this.showTitleCheckBox.addActionListener(this);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[16]++;
        interior.add(new JPanel());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[17]++;
        interior.add(this.showTitleCheckBox);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[18]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[19]++;

        JLabel titleLabel = new JLabel(localizationResources.getString("Text"));
        interior.add(titleLabel);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[20]++;
        interior.add(this.titleField);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[21]++;
        interior.add(new JPanel());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[22]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[23]++;

        JLabel fontLabel = new JLabel(localizationResources.getString("Font"));
        this.fontfield = new FontDisplayField(this.titleFont);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[24]++;
        this.selectFontButton = new JButton(
            localizationResources.getString("Select...")
        );
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[25]++;
        this.selectFontButton.setActionCommand("SelectFont");
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[26]++;
        this.selectFontButton.addActionListener(this);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[27]++;
        interior.add(fontLabel);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[28]++;
        interior.add(this.fontfield);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[29]++;
        interior.add(this.selectFontButton);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[30]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[31]++;

        JLabel colorLabel = new JLabel(
            localizationResources.getString("Color")
        );
        this.selectPaintButton = new JButton(
            localizationResources.getString("Select...")
        );
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[32]++;
        this.selectPaintButton.setActionCommand("SelectPaint");
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[33]++;
        this.selectPaintButton.addActionListener(this);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[34]++;
        interior.add(colorLabel);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[35]++;
        interior.add(this.titlePaint);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[36]++;
        interior.add(this.selectPaintButton);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[37]++;

        this.enableOrDisableControls();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[38]++;

        general.add(interior);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[39]++;
        add(general, BorderLayout.NORTH);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[40]++;
    }

    /**
     * Returns the title text entered in the panel.
     *
     * @return The title text entered in the panel.
     */
    public String getTitleText() {
        return this.titleField.getText();
    }

    /**
     * Returns the font selected in the panel.
     *
     * @return The font selected in the panel.
     */
    public Font getTitleFont() {
        return this.titleFont;
    }

    /**
     * Returns the paint selected in the panel.
     *
     * @return The paint selected in the panel.
     */
    public Paint getTitlePaint() {
        return this.titlePaint.getPaint();
    }

    /**
     * Handles button clicks by passing control to an appropriate handler 
     * method.
     *
     * @param event  the event
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[41]++;

        String command = event.getActionCommand();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[42]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((command.equals("SelectFont")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[1]++;
            attemptFontSelection();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[43]++;

        }
        else {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[2]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[44]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((command.equals("SelectPaint")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[3]++;
            attemptPaintSelection();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[45]++;

        }
        else {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[4]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[46]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((command.equals("ShowTitle")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[5]++;
            attemptModifyShowTitle();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[47]++;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[6]++;}
}
}
    }

    /**
     * Presents a font selection dialog to the user.
     */
    public void attemptFontSelection() {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[48]++;

        FontChooserPanel panel = new FontChooserPanel(this.titleFont);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[49]++;
        int result = 
            JOptionPane.showConfirmDialog(
                this, panel, localizationResources.getString("Font_Selection"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[50]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((result == JOptionPane.OK_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[7]++;
            this.titleFont = panel.getSelectedFont();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[51]++;
            this.fontfield.setText(
                this.titleFont.getFontName() + " " + this.titleFont.getSize()
            );
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[52]++;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[8]++;}
    }

    /**
     * Allow the user the opportunity to select a Paint object.  For now, we
     * just use the standard color chooser - all colors are Paint objects, but
     * not all Paint objects are colors (later we can implement a more general
     * Paint chooser).
     */
    public void attemptPaintSelection() {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[53]++;
        Paint p = this.titlePaint.getPaint();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[54]++;
        Color defaultColor = (p instanceof Color ? (Color) p : Color.blue);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[55]++;
        Color c = JColorChooser.showDialog(
            this, localizationResources.getString("Title_Color"), defaultColor
        );
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[56]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[9]++;
            this.titlePaint.setPaint(c);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[57]++;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[10]++;}
    }

    /**
     * Allow the user the opportunity to change whether the title is
     * displayed on the chart or not.
     */
    private void attemptModifyShowTitle() {
        this.showTitle = this.showTitleCheckBox.isSelected();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[58]++;
        this.enableOrDisableControls();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[59]++;
    }

    /**
     * If we are supposed to show the title, the controls are enabled.
     * If we are not supposed to show the title, the controls are disabled.
     */
    private void enableOrDisableControls() {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[60]++;
        boolean enabled = (this.showTitle == true);
        this.titleField.setEnabled(enabled);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[61]++;
        this.selectFontButton.setEnabled(enabled);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[62]++;
        this.selectPaintButton.setEnabled(enabled);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[63]++;
    }

    /**
     * Sets the properties of the specified title to match the properties
     * defined on this panel.
     *
     * @param chart  the chart whose title is to be modified.
     */
    public void setTitleProperties(JFreeChart chart) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[64]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.showTitle) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[11]++;
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[65]++;
            TextTitle title = chart.getTitle();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[66]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((title == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[13]++;
                title = new TextTitle();
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[67]++;
                chart.setTitle(title);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[68]++;

            } else {
  CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[14]++;}
            title.setText(getTitleText());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[69]++;
            title.setFont(getTitleFont());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[70]++;
            title.setPaint(getTitlePaint());
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[71]++;

        }
        else {
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.branches[12]++;
            chart.setTitle((TextTitle) null);
CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd.statements[72]++;
        }
    }

}

class CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd ());
  }
    public static long[] statements = new long[73];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.DefaultTitleEditor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$27sy6oy8nww1po3qeag0rrz6b9cdhnzu3hpd () {
    super("org.jfree.chart.editor.DefaultTitleEditor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 72; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.editor.DefaultTitleEditor.java");
      for (int i = 1; i <= 72; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

