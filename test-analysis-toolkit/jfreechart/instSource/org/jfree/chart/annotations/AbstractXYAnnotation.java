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
 * -------------------------
 * AbstractXYAnnotation.java
 * -------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 29-Sep-2004 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Mar-2007 : Implemented hashCode() (DG);
 * 
 */

package org.jfree.chart.annotations;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYAnnotationEntity;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.util.ObjectUtilities;

/**
 * The interface that must be supported by annotations that are to be added to 
 * an {@link XYPlot}.
 */
public abstract class AbstractXYAnnotation implements XYAnnotation {
  static {
    CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.ping();
  }


    /** The tool tip text. */
    private String toolTipText;
    
    /** The URL. */
    private String url;
    
    /**
     * Creates a new instance that has no tool tip or URL specified.
     */
    protected AbstractXYAnnotation() {
        this.toolTipText = null;
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[1]++;    
        this.url = null;
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[2]++;
    }
    
    /**
     * Returns the tool tip text for the annotation.  This will be displayed in
     * a {@link org.jfree.chart.ChartPanel} when the mouse pointer hovers over 
     * the annotation.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     * 
     * @see #setToolTipText(String)
     */
    public String getToolTipText() {
        return this.toolTipText;
    }
    
    /**
     * Sets the tool tip text for the annotation.
     * 
     * @param text  the tool tip text (<code>null</code> permitted).
     * 
     * @see #getToolTipText()
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[3]++;
    }
    
    /**
     * Returns the URL for the annotation.  This URL will be used to provide
     * hyperlinks when an HTML image map is created for the chart.
     * 
     * @return The URL (possibly <code>null</code>).
     * 
     * @see #setURL(String)
     */
    public String getURL() {
        return this.url;
    }
    
    /**
     * Sets the URL for the annotation.
     * 
     * @param url  the URL (<code>null</code> permitted).
     * 
     * @see #getURL()
     */
    public void setURL(String url) {
        this.url = url;
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[4]++;
    }
    
    /**
     * Draws the annotation.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  if supplied, this info object will be populated with
     *              entity information.
     */
    public abstract void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
                              ValueAxis domainAxis, ValueAxis rangeAxis, 
                              int rendererIndex,
                              PlotRenderingInfo info);

    /**
     * A utility method for adding an {@link XYAnnotationEntity} to 
     * a {@link PlotRenderingInfo} instance.
     * 
     * @param info  the plot rendering info (<code>null</code> permitted).
     * @param hotspot  the hotspot area.
     * @param rendererIndex  the renderer index.
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text.
     */
    protected void addEntity(PlotRenderingInfo info, 
                             Shape hotspot, int rendererIndex, 
                             String toolTipText, String urlText) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[1]++;
            return;
  
        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[2]++;}
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[6]++;
        EntityCollection entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((entities == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[3]++;
            return;

        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[4]++;}
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[8]++;
        XYAnnotationEntity entity = new XYAnnotationEntity(hotspot, 
                rendererIndex, toolTipText, urlText);
        entities.add(entity);
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[9]++;
    }

    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[6]++;}
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractXYAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[8]++;}
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[12]++;
        AbstractXYAnnotation that = (AbstractXYAnnotation) obj;
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipText, that.toolTipText)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[10]++;}
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.url, that.url)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[12]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[15]++;
        int result = 193;
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[16]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.toolTipText != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[13]++;
            result = 37 * result + this.toolTipText.hashCode();
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[17]++;

        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[14]++;}
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[18]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.url != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[15]++;
            result = 37 * result + this.url.hashCode();
CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.statements[19]++;
            
        } else {
  CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd.branches[16]++;}
        return result;
    }
    
}

class CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.AbstractXYAnnotation.java";
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

  public CodeCoverCoverageCounter$2z5ub9ipn43e3y2ya6rj1dgk0hx3l7bfyf0tgtd () {
    super("org.jfree.chart.annotations.AbstractXYAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
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
    log.startNamedSection("org.jfree.chart.annotations.AbstractXYAnnotation.java");
      for (int i = 1; i <= 19; i++) {
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

