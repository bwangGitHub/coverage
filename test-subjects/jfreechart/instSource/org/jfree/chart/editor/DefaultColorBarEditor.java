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
 * --------------------------
 * DefaultColorBarEditor.java
 * --------------------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Arnaud Lelievre;
 *
 * Changes
 * -------
 * 26-Nov-2002 : Version 1 contributed by David M. O'Donnell (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL); 
 * 24-Nov-2005 : Moved and renamed: org.jfree.chart.ui.ColorBarPropertyEditPanel
 *               --> DefaultColorBarEditor (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.editor;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.axis.ColorBar;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.GreyPalette;
import org.jfree.chart.plot.RainbowPalette;
import org.jfree.layout.LCBLayout;

/**
 * A DefaultColorBarEditor.  Extends DefaultNumberAxisEditor to allow 
 * change general axis type parameters.
 */
class DefaultColorBarEditor extends DefaultNumberAxisEditor {
  static {
    CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.ping();
  }


    /** 
     * A checkbox that indicates whether or not the color indices should run 
     * high to low. 
     */
    private JCheckBox invertPaletteCheckBox;

    /** Flag set by invertPaletteCheckBox. */
    private boolean invertPalette = false;
  {
    CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[1]++;
  }

    /** A checkbox that indicates whether the palette is stepped. */
    private JCheckBox stepPaletteCheckBox;

    /** Flag set by stepPaletteCheckBox. */
    private boolean stepPalette = false;
  {
    CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[2]++;
  }

    /** The Palette Sample displaying the current Palette. */
    private PaletteSample currentPalette;

    /** An array of availiable sample palettes. */
    private PaletteSample[] availablePaletteSamples;

    /** The resourceBundle for the localization. */
   protected  static ResourceBundle localizationResources = 
       ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[3]++;
  }

    /**
     * Creates a new edit panel for a color bar.
     * 
     * @param colorBar  the color bar.
     */
    public DefaultColorBarEditor(ColorBar colorBar) {
        super((NumberAxis) colorBar.getAxis());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[4]++;
        this.invertPalette = colorBar.getColorPalette().isInverse();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[5]++; 
        this.stepPalette = colorBar.getColorPalette().isStepped();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[6]++; 
        this.currentPalette = new PaletteSample(colorBar.getColorPalette());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[7]++;
        this.availablePaletteSamples = new PaletteSample[2];
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[8]++;
        this.availablePaletteSamples[0] 
            = new PaletteSample(new RainbowPalette());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[9]++;
        this.availablePaletteSamples[1] 
            = new PaletteSample(new GreyPalette());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[10]++;
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[11]++;

        JTabbedPane other = getOtherTabs();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[12]++;

        JPanel palettePanel = new JPanel(new LCBLayout(4));
        palettePanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[13]++;

        palettePanel.add(new JPanel());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[14]++;
        this.invertPaletteCheckBox = new JCheckBox(
            localizationResources.getString("Invert_Palette"), 
            this.invertPalette
        );
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[15]++;
        this.invertPaletteCheckBox.setActionCommand("invertPalette");
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[16]++;
        this.invertPaletteCheckBox.addActionListener(this);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[17]++;
        palettePanel.add(this.invertPaletteCheckBox);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[18]++;
        palettePanel.add(new JPanel());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[19]++;

        palettePanel.add(new JPanel());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[20]++;
        this.stepPaletteCheckBox = new JCheckBox(
            localizationResources.getString("Step_Palette"),
            this.stepPalette
        );
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[21]++;
        this.stepPaletteCheckBox.setActionCommand("stepPalette");
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[22]++;
        this.stepPaletteCheckBox.addActionListener(this);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[23]++;
        palettePanel.add(this.stepPaletteCheckBox);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[24]++;
        palettePanel.add(new JPanel());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[25]++;

        palettePanel.add(
            new JLabel(localizationResources.getString("Palette"))
        );
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[26]++;
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[27]++;
        JButton button 
            = new JButton(localizationResources.getString("Set_palette..."));
        button.setActionCommand("PaletteChoice");
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[28]++;
        button.addActionListener(this);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[29]++;
        palettePanel.add(this.currentPalette);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[30]++;
        palettePanel.add(button);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[31]++;

        other.add(localizationResources.getString("Palette"), palettePanel);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[32]++;

    }

    /**
     * Handles actions from within the property panel.
     *
     * @param event  the event.
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[33]++;
        String command = event.getActionCommand();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[34]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((command.equals("PaletteChoice")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[1]++;
            attemptPaletteSelection();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[35]++;

        }
        else {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[2]++;
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[36]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((command.equals("invertPalette")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[3]++;
            this.invertPalette = this.invertPaletteCheckBox.isSelected();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[37]++;

        }
        else {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[4]++;
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[38]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((command.equals("stepPalette")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[5]++;
            this.stepPalette = this.stepPaletteCheckBox.isSelected();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[39]++;

        }
        else {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[6]++;
            super.actionPerformed(event);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[40]++;  // pass to super-class for handling
        }
}
}
    }

    /**
     * Handle a palette selection.
     */
    private void attemptPaletteSelection() {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[41]++;
        PaletteChooserPanel panel 
            = new PaletteChooserPanel(null, this.availablePaletteSamples);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[42]++;
        int result = JOptionPane.showConfirmDialog(
            this, panel, localizationResources.getString("Palette_Selection"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[43]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((result == JOptionPane.OK_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[7]++;
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[44]++;
            double zmin = this.currentPalette.getPalette().getMinZ();
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[45]++;
            double zmax = this.currentPalette.getPalette().getMaxZ();
            this.currentPalette.setPalette(panel.getSelectedPalette());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[46]++;
            this.currentPalette.getPalette().setMinZ(zmin);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[47]++;
            this.currentPalette.getPalette().setMaxZ(zmax);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[48]++;

        } else {
  CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[8]++;}
    }

    /**
     * Sets the properties of the specified axis to match the properties 
     * defined on this panel.
     * 
     * @param colorBar  the color bar.
     */
    public void setAxisProperties(ColorBar colorBar) {
        super.setAxisProperties(colorBar.getAxis());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[49]++;
        colorBar.setColorPalette(this.currentPalette.getPalette());
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[50]++;
        colorBar.getColorPalette().setInverse(this.invertPalette);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[51]++; //dmo added
        colorBar.getColorPalette().setStepped(this.stepPalette);
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[52]++; //dmo added
    }

    /**
     * A static method that returns a panel that is appropriate for the axis
     * type.
     *
     * @param colorBar  the color bar.
     *
     * @return A panel or <code>null</code< if axis is <code>null</code>.
     */
    public static DefaultColorBarEditor getInstance(ColorBar colorBar) {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.statements[53]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((colorBar != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[9]++;
            return new DefaultColorBarEditor(colorBar);

        }
        else {
CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh.branches[10]++;
            return null;
        }

    }

}

class CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh ());
  }
    public static long[] statements = new long[54];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.DefaultColorBarEditor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$m553c7l0laov7pzlw7z2qv0dyyos7qppc27k0dwh () {
    super("org.jfree.chart.editor.DefaultColorBarEditor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 53; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.editor.DefaultColorBarEditor.java");
      for (int i = 1; i <= 53; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

