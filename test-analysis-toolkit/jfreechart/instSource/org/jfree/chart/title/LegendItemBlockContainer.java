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
 * -----------------------------
 * LegendItemBlockContainer.java
 * -----------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Jul-2006 : Version 1 (DG);
 * 06-Oct-2006 : Added tooltip and URL text fields (DG);
 * 18-May-2007 : Added seriesKey and dataset fields (DG);
 * 
 */

package org.jfree.chart.title;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.block.Arrangement;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BlockResult;
import org.jfree.chart.block.EntityBlockParams;
import org.jfree.chart.block.EntityBlockResult;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.Dataset;

/**
 * A container that holds all the pieces of a single legend item.
 *
 * @since 1.0.2
 */
public class LegendItemBlockContainer extends BlockContainer {
  static {
    CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.ping();
  }


    /** 
     * The dataset. 
     * 
     * @since 1.0.6
     */
    private Dataset dataset;
    
    /**
     * The series key.
     * 
     * @since 1.0.6
     */
    private Comparable seriesKey;
    
    /** The dataset index. */
    private int datasetIndex;
    
    /** The series index. */
    private int series;
    
    /** The tool tip text (can be <code>null</code>). */
    private String toolTipText;
    
    /** The URL text (can be <code>null</code>). */
    private String urlText;
    
    /**
     * Creates a new legend item block.
     * 
     * @param arrangement  the arrangement.
     * @param datasetIndex  the dataset index.
     * @param series  the series index.
     * 
     * @deprecated As of 1.0.6, use the other constructor.
     */
    public LegendItemBlockContainer(Arrangement arrangement, int datasetIndex,
            int series) {
        super(arrangement);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[1]++;
        this.datasetIndex = datasetIndex;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[2]++;
        this.series = series;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[3]++;
    }
    
    /**
     * Creates a new legend item block.
     * 
     * @param arrangement  the arrangement.
     * @param dataset  the dataset.
     * @param seriesKey  the series key.
     * 
     * @since 1.0.6
     */
    public LegendItemBlockContainer(Arrangement arrangement, Dataset dataset,
            Comparable seriesKey) {
        super(arrangement);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[4]++;
        this.dataset = dataset;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[5]++;
        this.seriesKey = seriesKey;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[6]++;
    }
    
    /**
     * Returns a reference to the dataset for the associated legend item.
     * 
     * @return A dataset reference.
     * 
     * @since 1.0.6
     */
    public Dataset getDataset() {
        return this.dataset;
    }
    
    /**
     * Returns the series key.
     * 
     * @return The series key.
     * 
     * @since 1.0.6
     */
    public Comparable getSeriesKey() {
        return this.seriesKey;
    }
    
    /**
     * Returns the dataset index.
     * 
     * @return The dataset index.
     * 
     * @deprecated As of 1.0.6, use the {@link #getDataset()} method.
     */
    public int getDatasetIndex() {
        return this.datasetIndex;
    }
   
    /**
     * Returns the series index.
     * 
     * @return The series index.
     */
    public int getSeriesIndex() {
        return this.series;
    }
    
    /**
     * Returns the tool tip text.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     * 
     * @since 1.0.3
     */
    public String getToolTipText() {
        return this.toolTipText;
    }
    
    /**
     * Sets the tool tip text.
     * 
     * @param text  the text (<code>null</code> permitted).
     * 
     * @since 1.0.3
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[7]++;   
    }
    
    /**
     * Returns the URL text.
     * 
     * @return The URL text (possibly <code>null</code>).
     * 
     * @since 1.0.3
     */
    public String getURLText() {
        return this.urlText;
    }
    
    /**
     * Sets the URL text.
     * 
     * @param text  the text (<code>null</code> permitted).
     * 
     * @since 1.0.3
     */
    public void setURLText(String text) {
        this.urlText = text;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[8]++;   
    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  passed on to blocks within the container 
     *                (<code>null</code> permitted).
     * 
     * @return An instance of {@link EntityBlockResult}, or <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
        // draw the block without collecting entities
        super.draw(g2, area, null);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[9]++;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[10]++;
        EntityBlockParams ebp = null;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[11]++;
        BlockResult r = new BlockResult();
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((params instanceof EntityBlockParams) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.branches[1]++;
            ebp = (EntityBlockParams) params;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[13]++;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ebp.getGenerateEntities()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.branches[3]++;
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[15]++;
                EntityCollection ec = new StandardEntityCollection();
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[16]++;
                LegendItemEntity entity = new LegendItemEntity(
                        (Shape) area.clone());
                entity.setSeriesIndex(this.series);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[17]++;
                entity.setSeriesKey(this.seriesKey);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[18]++;
                entity.setDataset(this.dataset);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[19]++;
                entity.setToolTipText(getToolTipText());
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[20]++;
                entity.setURLText(getURLText());
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[21]++;
                ec.add(entity);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[22]++;
                r.setEntityCollection(ec);
CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.statements[23]++;

            } else {
  CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5.branches[2]++;}
        return r;
    }
}

class CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5 ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.LegendItemBlockContainer.java";
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

  public CodeCoverCoverageCounter$6v1r8gatn8fa7o53tnsfegato7kn2fhgk2bce09e7rfj5 () {
    super("org.jfree.chart.title.LegendItemBlockContainer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
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
    log.startNamedSection("org.jfree.chart.title.LegendItemBlockContainer.java");
      for (int i = 1; i <= 23; i++) {
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

