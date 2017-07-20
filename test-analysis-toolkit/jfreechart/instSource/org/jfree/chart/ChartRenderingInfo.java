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
 * ChartRenderingInfo.java
 * -----------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Jan-2002 : Version 1 (DG);
 * 05-Feb-2002 : Added a new constructor, completed Javadoc comments (DG);
 * 05-Mar-2002 : Added a clear() method (DG);
 * 23-May-2002 : Renamed DrawInfo --> ChartRenderingInfo (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 17-Sep-2003 : Added PlotRenderingInfo (DG);
 * 01-Nov-2005 : Updated equals() method (DG);
 * 30-Nov-2005 : Removed get/setPlotArea() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Dec-2006 : Fixed equals() and clone() (DG);
 *
 */

package org.jfree.chart;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.io.SerialUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A structure for storing rendering information from one call to the
 * JFreeChart.draw() method.
 * <P>
 * An instance of the {@link JFreeChart} class can draw itself within an 
 * arbitrary rectangle on any <code>Graphics2D</code>.  It is assumed that 
 * client code will sometimes render the same chart in more than one view, so 
 * the {@link JFreeChart} instance does not retain any information about its 
 * rendered dimensions.  This information can be useful sometimes, so you have 
 * the option to collect the information at each call to 
 * <code>JFreeChart.draw()</code>, by passing an instance of this
 * <code>ChartRenderingInfo</code> class.
 */
public class ChartRenderingInfo implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2751952018173406822L;
  static {
    CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[1]++;
  }
    
    /** The area in which the chart is drawn. */
    private transient Rectangle2D chartArea;

    /** Rendering info for the chart's plot (and subplots, if any). */
    private PlotRenderingInfo plotInfo;

    /** 
     * Storage for the chart entities.  Since retaining entity information for 
     * charts with a large number of data points consumes a lot of memory, it 
     * is intended that you can set this to <code>null</code> to prevent the 
     * information being collected.
     */
    private EntityCollection entities;

    /**
     * Constructs a new ChartRenderingInfo structure that can be used to 
     * collect information about the dimensions of a rendered chart.
     */
    public ChartRenderingInfo() {
        this(new StandardEntityCollection());
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[2]++;
    }

    /**
     * Constructs a new instance. If an entity collection is supplied, it will 
     * be populated with information about the entities in a chart.  If it is 
     * <code>null</code>, no entity information (including tool tips) will
     * be collected.
     *
     * @param entities  an entity collection (<code>null</code> permitted).
     */
    public ChartRenderingInfo(EntityCollection entities) {
        this.chartArea = new Rectangle2D.Double();
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[3]++;
        this.plotInfo = new PlotRenderingInfo(this);
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[4]++;
        this.entities = entities;
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[5]++;
    }

    /**
     * Returns the area in which the chart was drawn.
     *
     * @return The area in which the chart was drawn.
     * 
     * @see #setChartArea(Rectangle2D)
     */
    public Rectangle2D getChartArea() {
        return this.chartArea;
    }

    /**
     * Sets the area in which the chart was drawn.
     *
     * @param area  the chart area.
     * 
     * @see #getChartArea()
     */
    public void setChartArea(Rectangle2D area) {
        this.chartArea.setRect(area);
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[6]++;
    }

    /**
     * Returns the collection of entities maintained by this instance.
     *
     * @return The entity collection (possibly <code>null</code>).
     * 
     * @see #setEntityCollection(EntityCollection)
     */
    public EntityCollection getEntityCollection() {
        return this.entities;
    }

    /**
     * Sets the entity collection.
     *
     * @param entities  the entity collection (<code>null</code> permitted).
     * 
     * @see #getEntityCollection()
     */
    public void setEntityCollection(EntityCollection entities) {
        this.entities = entities;
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[7]++;
    }

    /**
     * Clears the information recorded by this object.
     */
    public void clear() {
        this.chartArea.setRect(0.0, 0.0, 0.0, 0.0);
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[8]++;
        this.plotInfo = new PlotRenderingInfo(this);
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[9]++;
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.entities != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[1]++;
            this.entities.clear();
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[11]++;

        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[2]++;}
    }
  
    /**
     * Returns the rendering info for the chart's plot.
     * 
     * @return The rendering info for the plot.
     */  
    public PlotRenderingInfo getPlotInfo() {
        return this.plotInfo;
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[3]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[4]++;}
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof ChartRenderingInfo) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[6]++;}
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[14]++;
        ChartRenderingInfo that = (ChartRenderingInfo) obj;
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.chartArea, that.chartArea)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[8]++;}
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.plotInfo, that.plotInfo)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[10]++;}
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.entities, that.entities)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[12]++;}
        return true;
    }
    
    /**
     * Returns a clone of this object.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the object cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[18]++;
        ChartRenderingInfo clone = (ChartRenderingInfo) super.clone();
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.chartArea != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[13]++;
            clone.chartArea = (Rectangle2D) this.chartArea.clone();
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[20]++;

        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[14]++;}
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.entities instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[15]++;
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[22]++;
            PublicCloneable pc = (PublicCloneable) this.entities;
            clone.entities = (EntityCollection) pc.clone();
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[23]++;

        } else {
  CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.branches[16]++;}
        return clone;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[24]++;
        SerialUtilities.writeShape(this.chartArea, stream);
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[25]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[26]++;
        this.chartArea = (Rectangle2D) SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap.statements[27]++;
    }
        
}

class CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.chart.ChartRenderingInfo.java";
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

  public CodeCoverCoverageCounter$26nfmlg4sha9ljnet6yex37rw3velwkusuap () {
    super("org.jfree.chart.ChartRenderingInfo.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
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
    log.startNamedSection("org.jfree.chart.ChartRenderingInfo.java");
      for (int i = 1; i <= 27; i++) {
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

