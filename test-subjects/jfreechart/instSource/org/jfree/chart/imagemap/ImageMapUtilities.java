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
 * ImageMapUtilities.java
 * ----------------------
 * (C) Copyright 2004-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 02-Aug-2004 : Initial version (RA);
 * 13-Jan-2005 : Renamed ImageMapUtilities (DG);
 * 19-Jan-2005 : Reversed order of tags for chart entities to get correct
 *               layering (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Feb-2006 : API doc updates (DG);
 * 
 */

package org.jfree.chart.imagemap;

import java.io.IOException;
import java.io.PrintWriter;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.util.StringUtils;

/**
 * Collection of utility methods related to producing image maps.  
 * Functionality was originally in {@link org.jfree.chart.ChartUtilities}.
 */
public class ImageMapUtilities {
  static {
    CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.ping();
  }


    /**
     * Writes an image map to an output stream.
     *
     * @param writer  the writer (<code>null</code> not permitted).
     * @param name  the map name (<code>null</code> not permitted).
     * @param info  the chart rendering info (<code>null</code> not permitted).
     *
     * @throws java.io.IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer, String name, 
                                     ChartRenderingInfo info)
        throws IOException {

        // defer argument checking...
        ImageMapUtilities.writeImageMap(writer, name, info,
                new StandardToolTipTagFragmentGenerator(),
                new StandardURLTagFragmentGenerator());
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[1]++;

    }

    /**
     * Writes an image map to an output stream.
     *
     * @param writer  the writer (<code>null</code> not permitted).
     * @param name  the map name (<code>null</code> not permitted).
     * @param info  the chart rendering info (<code>null</code> not permitted).
     * @param useOverLibForToolTips  whether to use OverLIB for tooltips
     *                               (http://www.bosrup.com/web/overlib/).
     *
     * @throws java.io.IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer,
                                     String name,
                                     ChartRenderingInfo info,
                                     boolean useOverLibForToolTips) 
        throws IOException {
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[2]++;

        ToolTipTagFragmentGenerator toolTipTagFragmentGenerator = null;
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((useOverLibForToolTips) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[1]++;
            toolTipTagFragmentGenerator 
                    = new OverLIBToolTipTagFragmentGenerator();
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[4]++;

        }
        else {
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[2]++;
            toolTipTagFragmentGenerator 
                    = new StandardToolTipTagFragmentGenerator();
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[5]++;
        }
        ImageMapUtilities.writeImageMap(writer, name, info, 
                toolTipTagFragmentGenerator, 
                new StandardURLTagFragmentGenerator());
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[6]++;

    }

    /**
     * Writes an image map to an output stream.
     *
     * @param writer  the writer (<code>null</code> not permitted).
     * @param name  the map name (<code>null</code> not permitted).
     * @param info  the chart rendering info (<code>null</code> not permitted).
     * @param toolTipTagFragmentGenerator  a generator for the HTML fragment
     *     that will contain the tooltip text (<code>null</code> not permitted 
     *     if <code>info</code> contains tooltip information).
     * @param urlTagFragmentGenerator  a generator for the HTML fragment that
     *     will contain the URL reference (<code>null</code> not permitted if 
     *     <code>info</code> contains URLs).
     *
     * @throws java.io.IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer, String name, 
            ChartRenderingInfo info,
            ToolTipTagFragmentGenerator toolTipTagFragmentGenerator,
            URLTagFragmentGenerator urlTagFragmentGenerator) 
        throws IOException {

        writer.println(ImageMapUtilities.getImageMap(name, info, 
                toolTipTagFragmentGenerator, urlTagFragmentGenerator));
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[7]++;
    }

    /**
     * Creates an image map element that complies with the XHTML 1.0
     * specification.
     *
     * @param name  the map name (<code>null</code> not permitted).
     * @param info  the chart rendering info (<code>null</code> not permitted).
     *
     * @return The map element.
     */
    public static String getImageMap(String name, ChartRenderingInfo info) {
        return ImageMapUtilities.getImageMap(name, info,
                new StandardToolTipTagFragmentGenerator(),
                new StandardURLTagFragmentGenerator());
    }

    /**
     * Creates an image map element that complies with the XHTML 1.0
     * specification.
     *
     * @param name  the map name (<code>null</code> not permitted).
     * @param info  the chart rendering info (<code>null</code> not permitted).
     * @param toolTipTagFragmentGenerator  a generator for the HTML fragment
     *     that will contain the tooltip text (<code>null</code> not permitted 
     *     if <code>info</code> contains tooltip information).
     * @param urlTagFragmentGenerator  a generator for the HTML fragment that
     *     will contain the URL reference (<code>null</code> not permitted if 
     *     <code>info</code> contains URLs).
     *
     * @return The map tag.
     */
    public static String getImageMap(String name, ChartRenderingInfo info,
            ToolTipTagFragmentGenerator toolTipTagFragmentGenerator,
            URLTagFragmentGenerator urlTagFragmentGenerator) {
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[8]++;

        StringBuffer sb = new StringBuffer();
        sb.append("<map id=\"" + name + "\" name=\"" + name + "\">");
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[9]++;
        sb.append(StringUtils.getLineSeparator());
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[10]++;
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[11]++;
        EntityCollection entities = info.getEntityCollection();
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[3]++;
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[13]++;
            int count = entities.getEntityCount();
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
            for (int i = count - 1;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.loops[1]--;
  CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.loops[2]--;
  CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.loops[3]++;
}
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[15]++;
                ChartEntity entity = entities.getEntity(i);
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((entity.getToolTipText() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((entity.getURLText() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[5]++;
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[17]++;
                    String area = entity.getImageMapAreaTag(
                            toolTipTagFragmentGenerator, 
                            urlTagFragmentGenerator);
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((area.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[7]++;
                        sb.append(area);
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[19]++;
                        sb.append(StringUtils.getLineSeparator());
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[20]++;

                    } else {
  CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[8]++;}

                } else {
  CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[6]++;}
            }

        } else {
  CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.branches[4]++;}
        sb.append("</map>");
CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5.statements[21]++;
        return sb.toString();
        
    }

}

class CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5 ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.imagemap.ImageMapUtilities.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$c1q6e5sqk5u1k474zro4jo2oye1s7uh0f5 () {
    super("org.jfree.chart.imagemap.ImageMapUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.imagemap.ImageMapUtilities.java");
      for (int i = 1; i <= 21; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

