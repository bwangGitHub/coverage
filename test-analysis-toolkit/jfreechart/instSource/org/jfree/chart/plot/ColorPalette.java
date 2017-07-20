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
 * ColorPalette.java
 * -----------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 26-Nov-2002 : Version 1 contributed by David M. O'Donnell (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 14-Aug-2003 : Implemented Cloneable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.Color;
import java.awt.Paint;
import java.io.Serializable;
import java.util.Arrays;

import org.jfree.chart.axis.ValueTick;
import org.jfree.chart.renderer.xy.XYBlockRenderer;

/**
 * Defines palette used by {@link ContourPlot}.
 * 
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public abstract class ColorPalette implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -9029901853079622051L;
  static {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[1]++;
  }
    
    /** The min z-axis value. */
    protected double minZ = -1;
  {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[2]++;
  }

    /** The max z-axis value. */
    protected double maxZ = -1;
  {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[3]++;
  }

    /** Red components. */
    protected int[] r;

    /** Green components. */
    protected int[] g;

    /** Blue components. */
    protected int[] b;

    /** Tick values are stored for use with stepped palette. */
    protected double[] tickValues = null;
  {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[4]++;
  }

    /** Logscale? */
    protected boolean logscale = false;
  {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[5]++;
  }

    /** Inverse palette (ie, min and max colors are reversed). */
    protected boolean inverse = false;
  {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[6]++;
  }

    /** The palette name. */
    protected String paletteName = null;
  {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[7]++;
  }

    /** Controls whether palette colors are stepped (not continuous). */
    protected boolean stepped = false;
  {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[8]++;
  }

    /** Constant for converting loge to log10. */
    protected static final double log10 = Math.log(10);
  static {
    CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[9]++;
  }
    
    /**
     * Default contructor.
     */
    public ColorPalette() {
        super();
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[10]++;
    }

    /**
     * Returns the color associated with a value.
     *
     * @param value  the value.
     *
     * @return The color.
     */
    public Paint getColor(double value) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[11]++;
        int izV = (int) (253 * (value - this.minZ) 
                    / (this.maxZ - this.minZ)) + 2;
        return new Color(this.r[izV], this.g[izV], this.b[izV]);
    }

    /**
     * Returns a color.
     *
     * @param izV  the index into the palette (zero based).
     *
     * @return The color.
     */
    public Color getColor(int izV) {
        return new Color(this.r[izV], this.g[izV], this.b[izV]);
    }

    /**
     * Returns Color by mapping a given value to a linear palette.
     *
     * @param value  the value.
     *
     * @return The color.
     */
    public Color getColorLinear(double value) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[12]++;
        int izV = 0;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.stepped) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[1]++;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[14]++;
            int index = Arrays.binarySearch(this.tickValues, value);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[3]++;
                index = -1 * index - 2;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[16]++;

            } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[4]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[5]++; // For the case were the first tick is greater 
                             // than minZ
                value = this.minZ;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[18]++;

            }
            else {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[6]++;
                value = this.tickValues[index];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[19]++;
            }

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[2]++;}
        izV = (int) (253 * (value - this.minZ) / (this.maxZ - this.minZ)) + 2;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[20]++;
        izV = Math.min(izV, 255);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[21]++;
        izV = Math.max(izV, 2);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[22]++;
        return getColor(izV);
    }

    /**
     * Returns Color by mapping a given value to a common log palette.
     *
     * @param value  the value.
     *
     * @return The color.
     */
    public Color getColorLog(double value) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[23]++;
        int izV = 0;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[24]++;
        double minZtmp = this.minZ;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[25]++;
        double maxZtmp = this.maxZ;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.minZ <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[7]++;
//          negatives = true;
            this.maxZ = maxZtmp - minZtmp + 1;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[27]++;
            this.minZ = 1;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[28]++;
            value = value - minZtmp + 1;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[29]++;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[8]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[30]++;
        double minZlog = Math.log(this.minZ) / log10;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[31]++;
        double maxZlog = Math.log(this.maxZ) / log10;
        value = Math.log(value) / log10;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[32]++;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
        //  value = Math.pow(10,value);
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.stepped) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[9]++;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[34]++;
            int numSteps = this.tickValues.length;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[35]++;
            int steps = 256 / (numSteps - 1);
            izV = steps * (int) (numSteps * (value - minZlog) 
                    / (maxZlog - minZlog)) + 2;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[36]++;

            //  izV = steps*numSteps*(int)((value/minZ)/(maxZlog-minZlog)) + 2;
        }
        else {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[10]++;
            izV = (int) (253 * (value - minZlog) / (maxZlog - minZlog)) + 2;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[37]++;
        }
        izV = Math.min(izV, 255);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[38]++;
        izV = Math.max(izV, 2);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[39]++;

        this.minZ = minZtmp;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[40]++;
        this.maxZ = maxZtmp;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[41]++;

        return getColor(izV);
    }

    /**
     * Returns the maximum Z value.
     *
     * @return The value.
     */
    public double getMaxZ() {
        return this.maxZ;
    }

    /**
     * Returns the minimum Z value.
     *
     * @return The value.
     */
    public double getMinZ() {
        return this.minZ;
    }

    /**
     * Returns Paint by mapping a given value to a either a linear or common 
     * log palette as controlled by the value logscale.
     *
     * @param value  the value.
     *
     * @return The paint.
     */
    public Paint getPaint(double value) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isLogscale()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[11]++;
            return getColorLog(value);

        }
        else {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[12]++;
            return getColorLinear(value);
        }
    }

    /**
     * Returns the palette name.
     *
     * @return The palette name.
     */
    public String getPaletteName () {
        return this.paletteName;
    }

    /**
     * Returns the tick values.
     *
     * @return The tick values.
     */
    public double[] getTickValues() {
        return this.tickValues;
    }

    /**
     * Called to initialize the palette's color indexes
     */
    public abstract void initialize();

    /**
     * Inverts Palette
     */
    public void invertPalette() {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[43]++;

        int[] red = new int[256];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[44]++;
        int[] green = new int[256];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[45]++;
        int[] blue = new int[256];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[46]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < 256) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[1]--;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[2]--;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[3]++;
}
            red[i] = this.r[i];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[47]++;
            green[i] = this.g[i];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[48]++;
            blue[i] = this.b[i];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[49]++;
        }
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[50]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;

        for (int i = 2;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < 256) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[4]--;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[5]--;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[6]++;
}
            this.r[i] = red[257 - i];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[51]++;
            this.g[i] = green[257 - i];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[52]++;
            this.b[i] = blue[257 - i];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[53]++;
        }
    }

    /**
     * Returns the inverse flag.
     *
     * @return The flag.
     */
    public boolean isInverse () {
        return this.inverse;
    }

    /**
     * Returns the log-scale flag.
     *
     * @return The flag.
     */
    public boolean isLogscale() {
        return this.logscale;
    }

    /**
     * Returns the 'is-stepped' flag.
     *
     * @return The flag.
     */
    public boolean isStepped () {
        return this.stepped;
    }

    /**
     * Sets the inverse flag.
     *
     * @param inverse  the new value.
     */
    public void setInverse (boolean inverse) {
        this.inverse = inverse;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[54]++;
        initialize();
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[55]++;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[56]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((inverse) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[13]++;
            invertPalette();
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[57]++;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[14]++;}
        return;
    }

    /**
     * Sets the 'log-scale' flag.
     *
     * @param logscale  the new value.
     */
    public void setLogscale(boolean logscale) {
        this.logscale = logscale;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[58]++;
    }

    /**
     * Sets the maximum Z value.
     *
     * @param newMaxZ  the new value.
     */
    public void setMaxZ(double newMaxZ) {
        this.maxZ = newMaxZ;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[59]++;
    }

    /**
     * Sets the minimum Z value.
     *
     * @param newMinZ  the new value.
     */
    public void setMinZ(double newMinZ) {
        this.minZ = newMinZ;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[60]++;
    }

    /**
     * Sets the palette name.
     *
     * @param paletteName  the name.
     */
    public void setPaletteName (String paletteName) {
        //String oldValue = this.paletteName;
        this.paletteName = paletteName;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[61]++;
        return;
    }

    /**
     * Sets the stepped flag.
     *
     * @param stepped  the flag.
     */
    public void setStepped (boolean stepped) {
        this.stepped = stepped;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[62]++;
        return;
    }

    /**
     * Sets the tick values.
     *
     * @param newTickValues  the tick values.
     */
    public void setTickValues(double[] newTickValues) {
        this.tickValues = newTickValues;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[63]++;
    }

    /**
     * Store ticks. Required when doing stepped axis
     *
     * @param ticks  the ticks.
     */
    public void setTickValues(java.util.List ticks) {
        this.tickValues = new double[ticks.size()];
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[64]++;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[65]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < this.tickValues.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[7]--;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[8]--;
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.loops[9]++;
}
            this.tickValues[i] = ((ValueTick) ticks.get(i)).getValue();
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[66]++;
        }
    }

    /**
     * Tests an object for equality with this instance.
     * 
     * @param o  the object to test.
     * 
     * @return A boolean.
     */    
    public boolean equals(Object o) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[67]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this == o) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[16]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((o instanceof ColorPalette) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[18]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[69]++;

        ColorPalette colorPalette = (ColorPalette) o;
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[70]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.inverse != colorPalette.inverse) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[20]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[71]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.logscale != colorPalette.logscale) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[22]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[72]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.maxZ != colorPalette.maxZ) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[24]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[73]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.minZ != colorPalette.minZ) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[26]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[74]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.stepped != colorPalette.stepped) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[28]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[75]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.b, colorPalette.b)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[30]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[76]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.g, colorPalette.g)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[32]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[77]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((this.paletteName != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 ? !
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((this.paletteName.equals(colorPalette.paletteName)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 : 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((colorPalette.paletteName != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[34]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[78]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.r, colorPalette.r)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[36]++;}
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[79]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.tickValues, colorPalette.tickValues)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.branches[38]++;}

        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.minZ);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[80]++;
        result = (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[81]++;
        temp = Double.doubleToLongBits(this.maxZ);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[82]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[83]++;
        result = 29 * result + (this.logscale ? 1 : 0);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[84]++;
        result = 29 * result + (this.inverse ? 1 : 0);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[85]++;
        result = 29 * result 
                 + (this.paletteName != null ? this.paletteName.hashCode() : 0);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[86]++;
        result = 29 * result + (this.stepped ? 1 : 0);
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[87]++;
        return result;
    }

    /**
     * Returns a clone of the palette.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException never.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch.statements[88]++;
        
        ColorPalette clone = (ColorPalette) super.clone();
        return clone;
        
    }

}

class CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch ());
  }
    public static long[] statements = new long[89];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.ColorPalette.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1};
    for (int i = 1; i <= 22; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$se1b8xtlk3tlwibytqjchb1mch () {
    super("org.jfree.chart.plot.ColorPalette.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 88; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.ColorPalette.java");
      for (int i = 1; i <= 88; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

