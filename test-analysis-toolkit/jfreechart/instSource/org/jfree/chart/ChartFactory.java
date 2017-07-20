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
 * ChartFactory.java
 * -----------------
 * (C) Copyright 2001-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Serge V. Grachov;
 *                   Joao Guilherme Del Valle;
 *                   Bill Kelemen;
 *                   Jon Iles;
 *                   Jelai Wang;
 *                   Richard Atkinson;
 *                   David Browning (for Australian Institute of Marine 
 *                       Science);
 *                   Benoit Xhenseval;
 *
 * Changes
 * -------
 * 19-Oct-2001 : Version 1, most methods transferred from JFreeChart.java (DG);
 * 22-Oct-2001 : Added methods to create stacked bar charts (DG);
 *               Renamed DataSource.java --> Dataset.java etc. (DG);
 * 31-Oct-2001 : Added 3D-effect vertical bar and stacked-bar charts, 
 *               contributed by Serge V. Grachov (DG);
 * 07-Nov-2001 : Added a flag to control whether or not a legend is added to 
 *               the chart (DG);
 * 17-Nov-2001 : For pie chart, changed dataset from CategoryDataset to 
 *               PieDataset (DG);
 * 30-Nov-2001 : Removed try/catch handlers from chart creation, as the 
 *               exception are now RuntimeExceptions, as suggested by Joao 
 *               Guilherme Del Valle (DG);
 * 06-Dec-2001 : Added createCombinableXXXXXCharts methods (BK);
 * 12-Dec-2001 : Added createCandlestickChart() method (DG);
 * 13-Dec-2001 : Updated methods for charts with new renderers (DG);
 * 08-Jan-2002 : Added import for 
 *               com.jrefinery.chart.combination.CombinedChart (DG);
 * 31-Jan-2002 : Changed the createCombinableVerticalXYBarChart() method to use
 *               renderer (DG);
 * 06-Feb-2002 : Added new method createWindPlot() (DG);
 * 23-Apr-2002 : Updates to the chart and plot constructor API (DG);
 * 21-May-2002 : Added new method createAreaChart() (JI);
 * 06-Jun-2002 : Added new method createGanttChart() (DG);
 * 11-Jun-2002 : Renamed createHorizontalStackedBarChart() 
 *               --> createStackedHorizontalBarChart() for consistency (DG);
 * 06-Aug-2002 : Updated Javadoc comments (DG);
 * 21-Aug-2002 : Added createPieChart(CategoryDataset) method (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 09-Oct-2002 : Added methods including tooltips and URL flags (DG);
 * 06-Nov-2002 : Moved renderers into a separate package (DG);
 * 18-Nov-2002 : Changed CategoryDataset to TableDataset (DG);
 * 21-Mar-2003 : Incorporated HorizontalCategoryAxis3D, see bug id 685501 (DG);
 * 13-May-2003 : Merged some horizontal and vertical methods (DG);
 * 24-May-2003 : Added support for timeline in createHighLowChart (BK);
 * 07-Jul-2003 : Added createHistogram() method contributed by Jelai Wang (DG);
 * 27-Jul-2003 : Added createStackedAreaXYChart() method (RA);
 * 05-Aug-2003 : added new method createBoxAndWhiskerChart (DB);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 07-Oct-2003 : Added stepped area XY chart contributed by Matthias Rose (DG);
 * 06-Nov-2003 : Added createWaterfallChart() method (DG);
 * 20-Nov-2003 : Set rendering order for 3D bar charts to fix overlapping 
 *               problems (DG);
 * 25-Nov-2003 : Added createWaferMapChart() method (DG);
 * 23-Dec-2003 : Renamed createPie3DChart() --> createPieChart3D for 
 *               consistency (DG);
 * 20-Jan-2004 : Added createPolarChart() method (DG);
 * 28-Jan-2004 : Fixed bug (882890) with axis range in 
 *               createStackedXYAreaChart() method (DG);
 * 25-Feb-2004 : Renamed XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 11-Mar-2004 : Updated for pie chart changes (DG);
 * 27-Apr-2004 : Added new createPieChart() method contributed by Benoit 
 *               Xhenseval (see RFE 942195) (DG);
 * 11-May-2004 : Split StandardCategoryItemLabelGenerator 
 *               --> StandardCategoryToolTipGenerator and
 *               StandardCategoryLabelGenerator (DG);
 * 06-Jan-2005 : Removed deprecated methods (DG);
 * 27-Jan-2005 : Added new constructor to LineAndShapeRenderer (DG);
 * 28-Feb-2005 : Added docs to createBubbleChart() method (DG);
 * 17-Mar-2005 : Added createRingPlot() method (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * 29-Nov-2005 : Removed signal chart (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 26-Jan-2006 : Corrected API docs for createScatterPlot() (DG);
 * 23-Aug-2006 : Modified createStackedXYAreaChart() to use 
 *               StackedXYAreaRenderer2, because StackedXYAreaRenderer doesn't
 *               handle negative values (DG);
 * 27-Sep-2006 : Update createPieChart() method for deprecated code (DG);
 * 29-Nov-2006 : Update createXYBarChart() to use a time based tool tip 
 *               generator is a DateAxis is requested (DG);
 * 17-Jan-2007 : Added createBoxAndWhiskerChart() method from patch 1603937
 *               submitted by Darren Jung (DG);
 * 10-Jul-2007 : Added new methods to create pie charts with locale for
 *               section label and tool tip formatting (DG);
 *
 */

package org.jfree.chart;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.Timeline;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.labels.HighLowItemLabelGenerator;
import org.jfree.chart.labels.IntervalCategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.StandardXYZToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.WaferMapRenderer;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.LineRenderer3D;
import org.jfree.chart.renderer.category.StackedAreaRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.renderer.category.WaterfallBarRenderer;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer2;
import org.jfree.chart.renderer.xy.WindItemRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepAreaRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.urls.PieURLGenerator;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.StandardXYZURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.WaferMapDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.SortOrder;
import org.jfree.util.TableOrder;

/**
 * A collection of utility methods for creating some standard charts with 
 * JFreeChart.
 */
public abstract class ChartFactory {
  static {
    CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.ping();
  }


    /**
     * Creates a pie chart with default settings.
     * <P>
     * The chart object returned by this method uses a {@link PiePlot} instance 
     * as the plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param locale  the locale (<code>null</code> not permitted).
     *
     * @return A pie chart.
     * 
     * @since 1.0.7
     */
    public static JFreeChart createPieChart(String title, PieDataset dataset,
            boolean legend, boolean tooltips, Locale locale) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[1]++;

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[2]++;
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[3]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[1]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[5]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[2]++;}
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);

    }

    /**
     * Creates a pie chart with default settings.
     * <P>
     * The chart object returned by this method uses a {@link PiePlot} instance 
     * as the plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A pie chart.
     */
    public static JFreeChart createPieChart(String title,
                                            PieDataset dataset,
                                            boolean legend,
                                            boolean tooltips,
                                            boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[6]++;

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[7]++;
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[8]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[3]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[10]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[4]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[5]++;
            plot.setURLGenerator(new StandardPieURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[12]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[6]++;}
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);

    }

    /**
     * Creates a pie chart with default settings that compares 2 datasets.  
     * The colour of each section will be determined by the move from the value
     * for the same key in <code>previousDataset</code>. ie if value1 > value2 
     * then the section will be in green (unless <code>greenForIncrease</code> 
     * is <code>false</code>, in which case it would be <code>red</code>).  
     * Each section can have a shade of red or green as the difference can be 
     * tailored between 0% (black) and percentDiffForMaxScale% (bright 
     * red/green).
     * <p>
     * For instance if <code>percentDiffForMaxScale</code> is 10 (10%), a 
     * difference of 5% will have a half shade of red/green, a difference of 
     * 10% or more will have a maximum shade/brightness of red/green.
     * <P>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     * <p>
     * Written by <a href="mailto:opensource@objectlab.co.uk">Benoit 
     * Xhenseval</a>.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param previousDataset  the dataset for the last run, this will be used 
     *                         to compare each key in the dataset
     * @param percentDiffForMaxScale scale goes from bright red/green to black,
     *                               percentDiffForMaxScale indicate the change 
     *                               required to reach top scale.
     * @param greenForIncrease  an increase since previousDataset will be 
     *                          displayed in green (decrease red) if true.
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param locale  the locale (<code>null</code> not permitted).
     * @param subTitle displays a subtitle with colour scheme if true
     * @param showDifference  create a new dataset that will show the % 
     *                        difference between the two datasets. 
     *
     * @return A pie chart.
     * 
     * @since 1.0.7
     */
    public static JFreeChart createPieChart(String title, PieDataset dataset,
            PieDataset previousDataset, int percentDiffForMaxScale,
            boolean greenForIncrease, boolean legend, boolean tooltips,
            Locale locale, boolean subTitle, boolean showDifference) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[13]++;

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[14]++;
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[15]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[7]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[17]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[8]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[18]++;

        List keys = dataset.getKeys();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[19]++;
        DefaultPieDataset series = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[9]++;
            series = new DefaultPieDataset();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[21]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[10]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[22]++;

        double colorPerPercent = 255.0 / percentDiffForMaxScale;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        for (Iterator it = keys.iterator();(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[1]--;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[2]--;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[3]++;
}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[24]++;
            Comparable key = (Comparable) it.next();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[25]++;
            Number newValue = dataset.getValue(key);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[26]++;
            Number oldValue = previousDataset.getValue(key);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;

            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((oldValue == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[11]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((greenForIncrease) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[13]++;
                    plot.setSectionPaint(key, Color.green);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[29]++;

                } 
                else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[14]++;
                    plot.setSectionPaint(key, Color.red);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[30]++;
                }
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[15]++;
                    series.setValue(key + " (+100%)", newValue);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[32]++;

                } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[16]++;}

            }
            else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[12]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[33]++;
                double percentChange = (newValue.doubleValue() 
                        / oldValue.doubleValue() - 1.0) * 100.0;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[34]++;
                double shade
                    = (Math.abs(percentChange) >= percentDiffForMaxScale ? 255
                    : Math.abs(percentChange) * colorPerPercent);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (128)) == 0 || true) &&
 ((greenForIncrease) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((newValue.doubleValue() > oldValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((greenForIncrease) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((newValue.doubleValue() 
                        < oldValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[17]++;
                    plot.setSectionPaint(key, new Color(0, (int) shade, 0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[36]++;

                }
                else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[18]++;
                    plot.setSectionPaint(key, new Color((int) shade, 0, 0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[37]++;
                }
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[38]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[19]++;
                    series.setValue(key + " (" + (percentChange >= 0 ? "+" : "")
                            + NumberFormat.getPercentInstance().format(
                            percentChange / 100.0) + ")", newValue);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[39]++;

                } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[20]++;}
            }
        }
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[40]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[21]++;
            plot.setDataset(series);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[41]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[22]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[42]++;

        JFreeChart chart =  new JFreeChart(title, 
                JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((subTitle) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[23]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[44]++;
            TextTitle subtitle = null;
            subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" 
                    : "green") + "=change >=-" + percentDiffForMaxScale 
                    + "%, Bright " + (!greenForIncrease ? "red" : "green") 
                    + "=change >=+" + percentDiffForMaxScale + "%", 
                    new Font("SansSerif", Font.PLAIN, 10));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[45]++;
            chart.addSubtitle(subtitle);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[46]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[24]++;}

        return chart;
    }

    /**
     * Creates a pie chart with default settings that compares 2 datasets.  
     * The colour of each section will be determined by the move from the value
     * for the same key in <code>previousDataset</code>. ie if value1 > value2 
     * then the section will be in green (unless <code>greenForIncrease</code> 
     * is <code>false</code>, in which case it would be <code>red</code>).  
     * Each section can have a shade of red or green as the difference can be 
     * tailored between 0% (black) and percentDiffForMaxScale% (bright 
     * red/green).
     * <p>
     * For instance if <code>percentDiffForMaxScale</code> is 10 (10%), a 
     * difference of 5% will have a half shade of red/green, a difference of 
     * 10% or more will have a maximum shade/brightness of red/green.
     * <P>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     * <p>
     * Written by <a href="mailto:opensource@objectlab.co.uk">Benoit 
     * Xhenseval</a>.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param previousDataset  the dataset for the last run, this will be used 
     *                         to compare each key in the dataset
     * @param percentDiffForMaxScale scale goes from bright red/green to black,
     *                               percentDiffForMaxScale indicate the change 
     *                               required to reach top scale.
     * @param greenForIncrease  an increase since previousDataset will be 
     *                          displayed in green (decrease red) if true.
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     * @param subTitle displays a subtitle with colour scheme if true
     * @param showDifference  create a new dataset that will show the % 
     *                        difference between the two datasets. 
     *
     * @return A pie chart.
     */
    public static JFreeChart createPieChart(String title,
                                            PieDataset dataset,
                                            PieDataset previousDataset,
                                            int percentDiffForMaxScale,
                                            boolean greenForIncrease,
                                            boolean legend,
                                            boolean tooltips,
                                            boolean urls,
                                            boolean subTitle,
                                            boolean showDifference) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[47]++;

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[48]++;
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[49]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[50]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[25]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[51]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[26]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[27]++;
            plot.setURLGenerator(new StandardPieURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[53]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[28]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[54]++;

        List keys = dataset.getKeys();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[55]++;
        DefaultPieDataset series = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[29]++;
            series = new DefaultPieDataset();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[57]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[30]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[58]++;

        double colorPerPercent = 255.0 / percentDiffForMaxScale;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[59]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;
        for (Iterator it = keys.iterator();(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[4]--;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[5]--;
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.loops[6]++;
}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[60]++;
            Comparable key = (Comparable) it.next();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[61]++;
            Number newValue = dataset.getValue(key);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[62]++;
            Number oldValue = previousDataset.getValue(key);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[63]++;
int CodeCoverConditionCoverageHelper_C18;

            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((oldValue == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[31]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[64]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((greenForIncrease) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[33]++;
                    plot.setSectionPaint(key, Color.green);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[65]++;

                } 
                else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[34]++;
                    plot.setSectionPaint(key, Color.red);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[66]++;
                }
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[67]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[35]++;
                    series.setValue(key + " (+100%)", newValue);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[68]++;

                } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[36]++;}

            }
            else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[32]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[69]++;
                double percentChange = (newValue.doubleValue() 
                        / oldValue.doubleValue() - 1.0) * 100.0;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[70]++;
                double shade
                    = (Math.abs(percentChange) >= percentDiffForMaxScale ? 255
                    : Math.abs(percentChange) * colorPerPercent);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[71]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (128)) == 0 || true) &&
 ((greenForIncrease) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((newValue.doubleValue() > oldValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((greenForIncrease) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((newValue.doubleValue() 
                        < oldValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 4) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 4) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[37]++;
                    plot.setSectionPaint(key, new Color(0, (int) shade, 0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[72]++;

                }
                else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[38]++;
                    plot.setSectionPaint(key, new Color((int) shade, 0, 0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[73]++;
                }
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[74]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[39]++;
                    series.setValue(key + " (" + (percentChange >= 0 ? "+" : "")
                            + NumberFormat.getPercentInstance().format(
                            percentChange / 100.0) + ")", newValue);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[75]++;

                } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[40]++;}
            }
        }
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[76]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((showDifference) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[41]++;
            plot.setDataset(series);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[77]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[42]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[78]++;

        JFreeChart chart =  new JFreeChart(title, 
                JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[79]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((subTitle) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[43]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[80]++;
            TextTitle subtitle = null;
            subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" 
                    : "green") + "=change >=-" + percentDiffForMaxScale 
                    + "%, Bright " + (!greenForIncrease ? "red" : "green") 
                    + "=change >=+" + percentDiffForMaxScale + "%", 
                    new Font("SansSerif", Font.PLAIN, 10));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[81]++;
            chart.addSubtitle(subtitle);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[82]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[44]++;}

        return chart;
    }

    /**
     * Creates a ring chart with default settings.
     * <P>
     * The chart object returned by this method uses a {@link RingPlot} 
     * instance as the plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param locale  the locale (<code>null</code> not permitted).
     *
     * @return A ring chart.
     * 
     * @since 1.0.7
     */
    public static JFreeChart createRingChart(String title, PieDataset dataset,
            boolean legend, boolean tooltips, Locale locale) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[83]++;

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[84]++;
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[85]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[86]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[45]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[87]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[46]++;}
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);

    }

    /**
     * Creates a ring chart with default settings.
     * <P>
     * The chart object returned by this method uses a {@link RingPlot} 
     * instance as the plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A ring chart.
     */
    public static JFreeChart createRingChart(String title,
                                             PieDataset dataset,
                                             boolean legend,
                                             boolean tooltips,
                                             boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[88]++;

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[89]++;
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[90]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[91]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[47]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[92]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[48]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[93]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[49]++;
            plot.setURLGenerator(new StandardPieURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[94]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[50]++;}
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);

    }

    /**
     * Creates a chart that displays multiple pie plots.  The chart object 
     * returned by this method uses a {@link MultiplePiePlot} instance as the
     * plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param order  the order that the data is extracted (by row or by column)
     *               (<code>null</code> not permitted).
     * @param legend  include a legend?
     * @param tooltips  generate tooltips?
     * @param urls  generate URLs?
     *
     * @return A chart.
     */
    public static JFreeChart createMultiplePieChart(String title,
                                                    CategoryDataset dataset,
                                                    TableOrder order,
                                                    boolean legend,
                                                    boolean tooltips,
                                                    boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[95]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[51]++;
            throw new IllegalArgumentException("Null 'order' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[52]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[96]++;
        MultiplePiePlot plot = new MultiplePiePlot(dataset);
        plot.setDataExtractOrder(order);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[97]++;
        plot.setBackgroundPaint(null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[98]++;
        plot.setOutlineStroke(null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[99]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[100]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[53]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[101]++;
            PieToolTipGenerator tooltipGenerator 
                = new StandardPieToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[102]++;
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setToolTipGenerator(tooltipGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[103]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[54]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[104]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[55]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[105]++;
            PieURLGenerator urlGenerator = new StandardPieURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[106]++;
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[107]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[56]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[108]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a 3D pie chart using the specified dataset.  The chart object 
     * returned by this method uses a {@link PiePlot3D} instance as the
     * plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param locale  the locale (<code>null</code> not permitted).
     *
     * @return A pie chart.
     * 
     * @since 1.0.7
     */
    public static JFreeChart createPieChart3D(String title, PieDataset dataset,
            boolean legend, boolean tooltips, Locale locale) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[109]++;

        PiePlot3D plot = new PiePlot3D(dataset);
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[110]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[111]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[57]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[112]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[58]++;}
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);

    }

    /**
     * Creates a 3D pie chart using the specified dataset.  The chart object 
     * returned by this method uses a {@link PiePlot3D} instance as the
     * plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A pie chart.
     */
    public static JFreeChart createPieChart3D(String title,
                                              PieDataset dataset,
                                              boolean legend,
                                              boolean tooltips,
                                              boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[113]++;

        PiePlot3D plot = new PiePlot3D(dataset);
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[114]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[115]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[59]++;
            plot.setToolTipGenerator(new StandardPieToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[116]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[60]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[117]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[61]++;
            plot.setURLGenerator(new StandardPieURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[118]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[62]++;}
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);

    }

    /**
     * Creates a chart that displays multiple pie plots.  The chart object 
     * returned by this method uses a {@link MultiplePiePlot} instance as the
     * plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param order  the order that the data is extracted (by row or by column) 
     *               (<code>null</code> not permitted).
     * @param legend  include a legend?
     * @param tooltips  generate tooltips?
     * @param urls  generate URLs?
     *
     * @return A chart.
     */
    public static JFreeChart createMultiplePieChart3D(String title,
                                                      CategoryDataset dataset,
                                                      TableOrder order,
                                                      boolean legend,
                                                      boolean tooltips,
                                                      boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[119]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[63]++;
            throw new IllegalArgumentException("Null 'order' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[64]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[120]++;
        MultiplePiePlot plot = new MultiplePiePlot(dataset);
        plot.setDataExtractOrder(order);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[121]++;
        plot.setBackgroundPaint(null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[122]++;
        plot.setOutlineStroke(null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[123]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[124]++;

        JFreeChart pieChart = new JFreeChart(new PiePlot3D(null));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[125]++;
        TextTitle seriesTitle = new TextTitle("Series Title", 
                new Font("SansSerif", Font.BOLD, 12));
        seriesTitle.setPosition(RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[126]++;
        pieChart.setTitle(seriesTitle);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[127]++;
        pieChart.removeLegend();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[128]++;
        pieChart.setBackgroundPaint(null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[129]++;
        plot.setPieChart(pieChart);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[130]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[131]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[65]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[132]++;
            PieToolTipGenerator tooltipGenerator 
                = new StandardPieToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[133]++;
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setToolTipGenerator(tooltipGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[134]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[66]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[135]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[67]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[136]++;
            PieURLGenerator urlGenerator = new StandardPieURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[137]++;
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[138]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[68]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[139]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a bar chart.  The chart object returned by this method uses a 
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis} 
     * for the domain axis, a {@link NumberAxis} as the range axis, and a 
     * {@link BarRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis 
     *                        (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A bar chart.
     */
    public static JFreeChart createBarChart(String title,
                                            String categoryAxisLabel,
                                            String valueAxisLabel,
                                            CategoryDataset dataset,
                                            PlotOrientation orientation,
                                            boolean legend,
                                            boolean tooltips,
                                            boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[140]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[69]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[70]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[141]++;
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[142]++;
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[143]++;

        BarRenderer renderer = new BarRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[144]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[71]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[145]++;
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer.setBasePositiveItemLabelPosition(position1);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[146]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[147]++;
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer.setBaseNegativeItemLabelPosition(position2);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[148]++;

         }
        else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[72]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[149]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[73]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[150]++;
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer.setBasePositiveItemLabelPosition(position1);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[151]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[152]++;
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer.setBaseNegativeItemLabelPosition(position2);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[153]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[74]++;}
}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[154]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[75]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[155]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[76]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[156]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[77]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[157]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[78]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[158]++;

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[159]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[160]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object 
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a 
     * {@link NumberAxis} as the range axis, and a {@link StackedBarRenderer} 
     * as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param domainAxisLabel  the label for the category axis 
     *                         (<code>null</code> permitted).
     * @param rangeAxisLabel  the label for the value axis 
     *                        (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the orientation of the chart (horizontal or 
     *                     vertical) (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A stacked bar chart.
     */
    public static JFreeChart createStackedBarChart(String title,
                                                   String domainAxisLabel,
                                                   String rangeAxisLabel,
                                                   CategoryDataset dataset,
                                                   PlotOrientation orientation,
                                                   boolean legend,
                                                   boolean tooltips,
                                                   boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[161]++;
int CodeCoverConditionCoverageHelper_C42;

        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[79]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[80]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[162]++;

        CategoryAxis categoryAxis = new CategoryAxis(domainAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[163]++;
        ValueAxis valueAxis = new NumberAxis(rangeAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[164]++;

        StackedBarRenderer renderer = new StackedBarRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[165]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[81]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[166]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[82]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[167]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[83]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[168]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[84]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[169]++;

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[170]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[171]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a bar chart with a 3D effect. The chart object returned by this 
     * method uses a {@link CategoryPlot} instance as the plot, with a 
     * {@link CategoryAxis3D} for the domain axis, a {@link NumberAxis3D} as 
     * the range axis, and a {@link BarRenderer3D} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A bar chart with a 3D effect.
     */
    public static JFreeChart createBarChart3D(String title,
                                              String categoryAxisLabel,
                                              String valueAxisLabel,
                                              CategoryDataset dataset,
                                              PlotOrientation orientation,
                                              boolean legend,
                                              boolean tooltips,
                                              boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[172]++;
int CodeCoverConditionCoverageHelper_C45;

        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[85]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[86]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[173]++;
        CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[174]++;
        ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[175]++;

        BarRenderer3D renderer = new BarRenderer3D();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[176]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[87]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[177]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[88]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[178]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[89]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[179]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[90]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[180]++;

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[181]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[182]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[91]++;
            // change rendering order to ensure that bar overlapping is the 
            // right way around
            plot.setRowRenderingOrder(SortOrder.DESCENDING);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[183]++;
            plot.setColumnRenderingOrder(SortOrder.DESCENDING);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[184]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[92]++;}
        plot.setForegroundAlpha(0.75f);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[185]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[186]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a stacked bar chart with a 3D effect and default settings. The 
     * chart object returned by this method uses a {@link CategoryPlot} 
     * instance as the plot, with a {@link CategoryAxis3D} for the domain axis, 
     * a {@link NumberAxis3D} as the range axis, and a 
     * {@link StackedBarRenderer3D} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the orientation (horizontal or vertical) 
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A stacked bar chart with a 3D effect.
     */
    public static JFreeChart createStackedBarChart3D(String title,
                                                    String categoryAxisLabel,
                                                    String valueAxisLabel,
                                                    CategoryDataset dataset,
                                                    PlotOrientation orientation,
                                                    boolean legend,
                                                    boolean tooltips,
                                                    boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[187]++;
int CodeCoverConditionCoverageHelper_C49;

        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[93]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[94]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[188]++;
        CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[189]++;
        ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[190]++;

        // create the renderer...
        CategoryItemRenderer renderer = new StackedBarRenderer3D();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[191]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[95]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[192]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[96]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[193]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[97]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[194]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[98]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[195]++;

        // create the plot...
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[196]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[197]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[99]++;
            // change rendering order to ensure that bar overlapping is the 
            // right way around
            plot.setColumnRenderingOrder(SortOrder.DESCENDING);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[198]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[100]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[199]++;

        // create the chart...
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, 
                plot, legend);

        return chart;

    }

    /**
     * Creates an area chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a 
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and an {@link AreaRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (<code>null</code> not 
     *                     permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return An area chart.
     */
    public static JFreeChart createAreaChart(String title,
                                             String categoryAxisLabel,
                                             String valueAxisLabel,
                                             CategoryDataset dataset,
                                             PlotOrientation orientation,
                                             boolean legend,
                                             boolean tooltips,
                                             boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[200]++;
int CodeCoverConditionCoverageHelper_C53;

        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[101]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[102]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[201]++;
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[202]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[203]++;

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[204]++;

        AreaRenderer renderer = new AreaRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[205]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[103]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[206]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[104]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[207]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[105]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[208]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[106]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[209]++;

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[210]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[211]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a stacked area chart with default settings.  The chart object 
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a 
     * {@link NumberAxis} as the range axis, and a {@link StackedAreaRenderer} 
     * as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A stacked area chart.
     */
    public static JFreeChart createStackedAreaChart(String title,
                                                    String categoryAxisLabel,
                                                    String valueAxisLabel,
                                                    CategoryDataset dataset,
                                                    PlotOrientation orientation,
                                                    boolean legend,
                                                    boolean tooltips,
                                                    boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[212]++;
int CodeCoverConditionCoverageHelper_C56;

        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[107]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[108]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[213]++;
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[214]++;
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[215]++;

        StackedAreaRenderer renderer = new StackedAreaRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[216]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[109]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[217]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[110]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[218]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[111]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[219]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[112]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[220]++;

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[221]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[222]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, 
                plot, legend);

        return chart;

    }

    /**
     * Creates a line chart with default settings.  The chart object returned 
     * by this method uses a {@link CategoryPlot} instance as the plot, with a 
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the 
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the chart orientation (horizontal or vertical) 
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A line chart.
     */
    public static JFreeChart createLineChart(String title,
                                             String categoryAxisLabel,
                                             String valueAxisLabel,
                                             CategoryDataset dataset,
                                             PlotOrientation orientation,
                                             boolean legend,
                                             boolean tooltips,
                                             boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[223]++;
int CodeCoverConditionCoverageHelper_C59;

        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[113]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[114]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[224]++;
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[225]++;
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[226]++;

        LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[227]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[115]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[228]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[116]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[229]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[117]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[230]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[118]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[231]++;
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[232]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[233]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a line chart with default settings. The chart object returned by 
     * this method uses a {@link CategoryPlot} instance as the plot, with a 
     * {@link CategoryAxis3D} for the domain axis, a {@link NumberAxis3D} as 
     * the range axis, and a {@link LineRenderer3D} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the chart orientation (horizontal or vertical) 
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A line chart.
     */
    public static JFreeChart createLineChart3D(String title,
                                               String categoryAxisLabel,
                                               String valueAxisLabel,
                                               CategoryDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[234]++;
int CodeCoverConditionCoverageHelper_C62;

        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[119]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[120]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[235]++;
        CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[236]++;
        ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[237]++;

        LineRenderer3D renderer = new LineRenderer3D();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[238]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[121]++;
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[239]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[122]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[240]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[123]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[241]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[124]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[242]++;
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[243]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[244]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values 
     * where required.  The chart object returned by this method uses a 
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis} 
     * for the domain axis, a {@link DateAxis} as the range axis, and a 
     * {@link GanttRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param dateAxisLabel  the label for the date axis 
     *                       (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A Gantt chart.
     */
    public static JFreeChart createGanttChart(String title,
                                              String categoryAxisLabel,
                                              String dateAxisLabel,
                                              IntervalCategoryDataset dataset,
                                              boolean legend,
                                              boolean tooltips,
                                              boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[245]++;

        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[246]++;
        DateAxis dateAxis = new DateAxis(dateAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[247]++;

        CategoryItemRenderer renderer = new GanttRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[248]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[125]++;
            renderer.setBaseToolTipGenerator(
                    new IntervalCategoryToolTipGenerator(
                    "{3} - {4}", DateFormat.getDateInstance()));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[249]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[126]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[250]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[127]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[251]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[128]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[252]++;

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, dateAxis, 
                renderer);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[253]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[254]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a waterfall chart.  The chart object returned by this method 
     * uses a {@link CategoryPlot} instance as the plot, with a 
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link WaterfallBarRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis 
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A waterfall chart.
     */
    public static JFreeChart createWaterfallChart(String title,
                                                  String categoryAxisLabel,
                                                  String valueAxisLabel,
                                                  CategoryDataset dataset,
                                                  PlotOrientation orientation,
                                                  boolean legend,
                                                  boolean tooltips,
                                                  boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[255]++;
int CodeCoverConditionCoverageHelper_C67;

        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[129]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[130]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[256]++;
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[257]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[258]++;

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[259]++;

        WaterfallBarRenderer renderer = new WaterfallBarRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[260]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[131]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[261]++;
            ItemLabelPosition position = new ItemLabelPosition(
                    ItemLabelAnchor.CENTER, TextAnchor.CENTER, 
                    TextAnchor.CENTER, Math.PI / 2.0);
            renderer.setBasePositiveItemLabelPosition(position);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[262]++;
            renderer.setBaseNegativeItemLabelPosition(position);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[263]++;

         }
        else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[132]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[264]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[133]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[265]++;
            ItemLabelPosition position = new ItemLabelPosition(
                    ItemLabelAnchor.CENTER, TextAnchor.CENTER, 
                    TextAnchor.CENTER, 0.0);
            renderer.setBasePositiveItemLabelPosition(position);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[266]++;
            renderer.setBaseNegativeItemLabelPosition(position);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[267]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[134]++;}
}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[268]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[135]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[269]++;
            StandardCategoryToolTipGenerator generator 
                = new StandardCategoryToolTipGenerator();
            renderer.setBaseToolTipGenerator(generator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[270]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[136]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[271]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[137]++;
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[272]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[138]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[273]++;

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        plot.clearRangeMarkers();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[274]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[275]++;
        Marker baseline = new ValueMarker(0.0);
        baseline.setPaint(Color.black);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[276]++;
        plot.addRangeMarker(baseline, Layer.FOREGROUND);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[277]++;
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[278]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[279]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, 
                plot, legend);

        return chart;

    }

    /**
     * Creates a polar plot for the specified dataset (x-values interpreted as 
     * angles in degrees).  The chart object returned by this method uses a 
     * {@link PolarPlot} instance as the plot, with a {@link NumberAxis} for 
     * the radial axis.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param legend  legend required?
     * @param tooltips  tooltips required?
     * @param urls  URLs required?
     *
     * @return A chart.
     */
    public static JFreeChart createPolarChart(String title,
                                              XYDataset dataset,
                                              boolean legend,
                                              boolean tooltips,
                                              boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[280]++;

        PolarPlot plot = new PolarPlot();
        plot.setDataset(dataset);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[281]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[282]++;
        NumberAxis rangeAxis = new NumberAxis();
        rangeAxis.setAxisLineVisible(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[283]++;
        rangeAxis.setTickMarksVisible(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[284]++;
        rangeAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[285]++;
        plot.setAxis(rangeAxis);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[286]++;
        plot.setRenderer(new DefaultPolarItemRenderer());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[287]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[288]++;
        JFreeChart chart = new JFreeChart(
                title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;

    }

    /**
     * Creates a scatter plot with default settings.  The chart object 
     * returned by this method uses an {@link XYPlot} instance as the plot, 
     * with a {@link NumberAxis} for the domain axis, a  {@link NumberAxis} 
     * as the range axis, and an {@link XYLineAndShapeRenderer} as the 
     * renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A scatter plot.
     */
    public static JFreeChart createScatterPlot(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[289]++;
int CodeCoverConditionCoverageHelper_C72;

        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[139]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[140]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[290]++;
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[291]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[292]++;
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[293]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[294]++;

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[295]++;

        XYToolTipGenerator toolTipGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[296]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[141]++;
            toolTipGenerator = new StandardXYToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[297]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[142]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[298]++;

        XYURLGenerator urlGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[299]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[143]++;
            urlGenerator = new StandardXYURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[300]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[144]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[301]++;
        XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setBaseToolTipGenerator(toolTipGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[302]++;
        renderer.setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[303]++;
        plot.setRenderer(renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[304]++;
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[305]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[306]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <P>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a 
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the 
     * renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param dateAxis  make the domain axis display dates?
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return An XY bar chart.
     */
    public static JFreeChart createXYBarChart(String title,
                                              String xAxisLabel,
                                              boolean dateAxis,
                                              String yAxisLabel,
                                              IntervalXYDataset dataset,
                                              PlotOrientation orientation,
                                              boolean legend,
                                              boolean tooltips,
                                              boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[307]++;
int CodeCoverConditionCoverageHelper_C75;

        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[145]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[146]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[308]++;
        ValueAxis domainAxis = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[309]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((dateAxis) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[147]++;
            domainAxis = new DateAxis(xAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[310]++;

        }
        else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[148]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[311]++;
            NumberAxis axis = new NumberAxis(xAxisLabel);
            axis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[312]++;
            domainAxis = axis;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[313]++;
        }
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[314]++;
        ValueAxis valueAxis = new NumberAxis(yAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[315]++;

        XYBarRenderer renderer = new XYBarRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[316]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[149]++;
            XYToolTipGenerator tt;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[317]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((dateAxis) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[151]++;
                tt = StandardXYToolTipGenerator.getTimeSeriesInstance();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[318]++;

            }
            else {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[152]++;
                tt = new StandardXYToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[319]++;
            }
            renderer.setBaseToolTipGenerator(tt);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[320]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[150]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[321]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[153]++;
            renderer.setURLGenerator(new StandardXYURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[322]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[154]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[323]++;

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[324]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[325]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates an area chart using an {@link XYDataset}.
     * <P>
     * The chart object returned by this method uses an {@link XYPlot} instance 
     * as the plot, with a {@link NumberAxis} for the domain axis, a 
     * {@link NumberAxis} as the range axis, and a {@link XYAreaRenderer} as 
     * the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return An XY area chart.
     */
    public static JFreeChart createXYAreaChart(String title,
                                               String xAxisLabel,
                                               String yAxisLabel,
                                               XYDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[326]++;
int CodeCoverConditionCoverageHelper_C80;

        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[155]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[156]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[327]++;
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[328]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[329]++;
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[330]++;
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[331]++;
        plot.setForegroundAlpha(0.5f);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[332]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[333]++;

        XYToolTipGenerator tipGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[334]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[157]++;
            tipGenerator = new StandardXYToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[335]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[158]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[336]++;

        XYURLGenerator urlGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[337]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[159]++;
            urlGenerator = new StandardXYURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[338]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[160]++;}

        plot.setRenderer(
            new XYAreaRenderer(XYAreaRenderer.AREA, tipGenerator, urlGenerator)
        );
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[339]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[340]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this 
     * method uses an {@link XYPlot} instance as the plot, with a 
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A stacked XY area chart.
     */
    public static JFreeChart createStackedXYAreaChart(String title,
                                                    String xAxisLabel,
                                                    String yAxisLabel,
                                                    TableXYDataset dataset,
                                                    PlotOrientation orientation,
                                                    boolean legend,
                                                    boolean tooltips,
                                                    boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[341]++;
int CodeCoverConditionCoverageHelper_C83;

        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[161]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[162]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[342]++;
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[343]++;
        xAxis.setLowerMargin(0.0);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[344]++;
        xAxis.setUpperMargin(0.0);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[345]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[346]++;
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[347]++;
        XYToolTipGenerator toolTipGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[348]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[163]++;
            toolTipGenerator = new StandardXYToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[349]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[164]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[350]++;

        XYURLGenerator urlGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[351]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[165]++;
            urlGenerator = new StandardXYURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[352]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[166]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[353]++;
        StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2(
                toolTipGenerator, urlGenerator);
        renderer.setOutline(true);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[354]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[355]++;
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[356]++;

        plot.setRangeAxis(yAxis);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[357]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[358]++;  // forces recalculation of the axis range

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default 
     * settings.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return The chart.
     */
    public static JFreeChart createXYLineChart(String title,
                                               String xAxisLabel,
                                               String yAxisLabel,
                                               XYDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[359]++;
int CodeCoverConditionCoverageHelper_C86;

        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[167]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[168]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[360]++;
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[361]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[362]++;
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[363]++;
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[364]++;
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[365]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[366]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[169]++;
            renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[367]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[170]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[368]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[171]++;
            renderer.setURLGenerator(new StandardXYURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[369]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[172]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[370]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a stepped XY plot with default settings.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A chart.
     */
    public static JFreeChart createXYStepChart(String title,
                                               String xAxisLabel,
                                               String yAxisLabel,
                                               XYDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[371]++;
int CodeCoverConditionCoverageHelper_C89;

        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[173]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[174]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[372]++;
        DateAxis xAxis = new DateAxis(xAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[373]++;
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[374]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[375]++;

        XYToolTipGenerator toolTipGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[376]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[175]++;
            toolTipGenerator = new StandardXYToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[377]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[176]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[378]++;

        XYURLGenerator urlGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[379]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[177]++;
            urlGenerator = new StandardXYURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[380]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[178]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[381]++;
        XYItemRenderer renderer 
            = new XYStepRenderer(toolTipGenerator, urlGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[382]++;

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[383]++;
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[384]++;
        plot.setDomainCrosshairVisible(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[385]++;
        plot.setRangeCrosshairVisible(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[386]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[387]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates a filled stepped XY plot with default settings.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A chart.
     */
    public static JFreeChart createXYStepAreaChart(String title,
                                                   String xAxisLabel,
                                                   String yAxisLabel,
                                                   XYDataset dataset,
                                                   PlotOrientation orientation,
                                                   boolean legend,
                                                   boolean tooltips,
                                                   boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[388]++;
int CodeCoverConditionCoverageHelper_C92;

        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[179]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[180]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[389]++;
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[390]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[391]++;
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[392]++;

        XYToolTipGenerator toolTipGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[393]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[181]++;
            toolTipGenerator = new StandardXYToolTipGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[394]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[182]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[395]++;

        XYURLGenerator urlGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[396]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[183]++;
            urlGenerator = new StandardXYURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[397]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[184]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[398]++;
        XYItemRenderer renderer = new XYStepAreaRenderer(
                XYStepAreaRenderer.AREA_AND_SHAPES, toolTipGenerator, 
                urlGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[399]++;

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[400]++;
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[401]++;
        plot.setDomainCrosshairVisible(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[402]++;
        plot.setRangeCrosshairVisible(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[403]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[404]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;
    }

    /**
     * Creates and returns a time series chart.  A time series chart is an 
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a 
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <P>
     * A convenient dataset to use with this chart is a 
     * {@link org.jfree.data.time.TimeSeriesCollection}.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code> 
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A time series chart.
     */
    public static JFreeChart createTimeSeriesChart(String title,
                                                   String timeAxisLabel,
                                                   String valueAxisLabel,
                                                   XYDataset dataset,
                                                   boolean legend,
                                                   boolean tooltips,
                                                   boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[405]++;

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        timeAxis.setLowerMargin(0.02);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[406]++;  // reduce the default margins 
        timeAxis.setUpperMargin(0.02);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[407]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[408]++;
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[409]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[410]++;  // override default
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[411]++;

        XYToolTipGenerator toolTipGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[412]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[185]++;
            toolTipGenerator 
                = StandardXYToolTipGenerator.getTimeSeriesInstance();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[413]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[186]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[414]++;

        XYURLGenerator urlGenerator = null;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[415]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[187]++;
            urlGenerator = new StandardXYURLGenerator();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[416]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[188]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[417]++;

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, 
                false);
        renderer.setBaseToolTipGenerator(toolTipGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[418]++;
        renderer.setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[419]++;
        plot.setRenderer(renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[420]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[421]++;
        
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates and returns a default instance of a candlesticks chart.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code> 
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     *
     * @return A candlestick chart.
     */
    public static JFreeChart createCandlestickChart(String title,
                                                    String timeAxisLabel,
                                                    String valueAxisLabel,
                                                    OHLCDataset dataset,
                                                    boolean legend) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[422]++;

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[423]++;
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[424]++;
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setRenderer(new CandlestickRenderer());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[425]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[426]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates and returns a default instance of a high-low-open-close chart.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code> 
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     *
     * @return A high-low-open-close chart.
     */
    public static JFreeChart createHighLowChart(String title,
                                                String timeAxisLabel,
                                                String valueAxisLabel,
                                                OHLCDataset dataset,
                                                boolean legend) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[427]++;

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[428]++;
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[429]++;
        HighLowRenderer renderer = new HighLowRenderer();
        renderer.setBaseToolTipGenerator(new HighLowItemLabelGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[430]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[431]++;
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[432]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates and returns a default instance of a high-low-open-close chart 
     * with a special timeline. This timeline can be a 
     * {@link org.jfree.chart.axis.SegmentedTimeline} such as the Monday 
     * through Friday timeline that will remove Saturdays and Sundays from
     * the axis.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code> 
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param timeline  the timeline.
     * @param legend  a flag specifying whether or not a legend is required.
     *
     * @return A high-low-open-close chart.
     */
    public static JFreeChart createHighLowChart(String title,
                                                String timeAxisLabel,
                                                String valueAxisLabel,
                                                OHLCDataset dataset,
                                                Timeline timeline,
                                                boolean legend) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[433]++;

        DateAxis timeAxis = new DateAxis(timeAxisLabel);
        timeAxis.setTimeline(timeline);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[434]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[435]++;
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[436]++;
        HighLowRenderer renderer = new HighLowRenderer();
        renderer.setBaseToolTipGenerator(new HighLowItemLabelGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[437]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[438]++;
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[439]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates a bubble chart with default settings.  The chart is composed of
     * an {@link XYPlot}, with a {@link NumberAxis} for the domain axis,
     * a {@link NumberAxis} for the range axis, and an {@link XYBubbleRenderer}
     * to draw the data items.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A bubble chart.
     */
    public static JFreeChart createBubbleChart(String title,
                                               String xAxisLabel,
                                               String yAxisLabel,
                                               XYZDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[440]++;
int CodeCoverConditionCoverageHelper_C97;

        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[189]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[190]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[441]++;
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[442]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[443]++;
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[444]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[445]++;

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[446]++;

        XYItemRenderer renderer = new XYBubbleRenderer(
                XYBubbleRenderer.SCALE_ON_RANGE_AXIS);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[447]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[191]++;
            renderer.setBaseToolTipGenerator(new StandardXYZToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[448]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[192]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[449]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[193]++;
            renderer.setURLGenerator(new StandardXYZURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[450]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[194]++;}
        plot.setRenderer(renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[451]++;
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[452]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[453]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a histogram chart.  This chart is constructed with an 
     * {@link XYPlot} using an {@link XYBarRenderer}.  The domain and range 
     * axes are {@link NumberAxis} instances.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  the x axis label (<code>null</code> permitted).
     * @param yAxisLabel  the y axis label (<code>null</code> permitted).
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param orientation  the orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted).
     * @param legend  create a legend?
     * @param tooltips  display tooltips?
     * @param urls  generate URLs?
     *
     * @return The chart.
     */
    public static JFreeChart createHistogram(String title,
                                             String xAxisLabel,
                                             String yAxisLabel,
                                             IntervalXYDataset dataset,
                                             PlotOrientation orientation,
                                             boolean legend,
                                             boolean tooltips,
                                             boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[454]++;
int CodeCoverConditionCoverageHelper_C100;

        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[195]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[196]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[455]++;
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[456]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[457]++;
        ValueAxis yAxis = new NumberAxis(yAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[458]++;

        XYItemRenderer renderer = new XYBarRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[459]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[197]++;
            renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[460]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[198]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[461]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[199]++;
            renderer.setURLGenerator(new StandardXYURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[462]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[200]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[463]++;

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[464]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[465]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        return chart;

    }

    /**
     * Creates and returns a default instance of a box and whisker chart
     * based on data from a {@link BoxAndWhiskerCategoryDataset}.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  a label for the category axis 
     *     (<code>null</code> permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code> 
     *     permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     *
     * @return A box and whisker chart.
     * 
     * @since 1.0.4
     */
    public static JFreeChart createBoxAndWhiskerChart(String title,
            String categoryAxisLabel, String valueAxisLabel,
            BoxAndWhiskerCategoryDataset dataset, boolean legend) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[466]++;
        
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[467]++;
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[468]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[469]++;
        
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setBaseToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[470]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[471]++;
           
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
                renderer);
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);
    } 

    /**
     * Creates and returns a default instance of a box and whisker chart.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code> 
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code> 
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     *
     * @return A box and whisker chart.
     */
    public static JFreeChart createBoxAndWhiskerChart(String title,
                                                 String timeAxisLabel,
                                                 String valueAxisLabel,
                                                 BoxAndWhiskerXYDataset dataset,
                                                 boolean legend) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[472]++;

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[473]++;
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[474]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[475]++;
        XYBoxAndWhiskerRenderer renderer = new XYBoxAndWhiskerRenderer(10.0);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[476]++;
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, 
                legend);

    }

    /**
     * Creates a wind plot with default settings.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the x-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag that controls whether or not a legend is created.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A wind plot.
     *
     */
    public static JFreeChart createWindPlot(String title,
                                            String xAxisLabel,
                                            String yAxisLabel,
                                            WindDataset dataset,
                                            boolean legend,
                                            boolean tooltips,
                                            boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[477]++;

        ValueAxis xAxis = new DateAxis(xAxisLabel);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[478]++;
        ValueAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setRange(-12.0, 12.0);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[479]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[480]++;

        WindItemRenderer renderer = new WindItemRenderer();
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[481]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((tooltips) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[201]++;
            renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[482]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[202]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[483]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((urls) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[203]++;
            renderer.setURLGenerator(new StandardXYURLGenerator());
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[484]++;

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[204]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[485]++;
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[486]++;
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }

    /**
     * Creates a wafer map chart.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical) 
     *                     (<code>null</code> NOT permitted.
     * @param legend  display a legend?
     * @param tooltips  generate tooltips?
     * @param urls  generate URLs?
     *
     * @return A wafer map chart.
     */
    public static JFreeChart createWaferMapChart(String title,
                                                 WaferMapDataset dataset,
                                                 PlotOrientation orientation,
                                                 boolean legend,
                                                 boolean tooltips,
                                                 boolean urls) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[487]++;
int CodeCoverConditionCoverageHelper_C105;

        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[205]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.branches[206]++;}
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[488]++;
        WaferMapPlot plot = new WaferMapPlot(dataset);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[489]++;
        WaferMapRenderer renderer = new WaferMapRenderer();
        plot.setRenderer(renderer);
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[490]++;
CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01.statements[491]++;

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;
    }

}

class CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01 ());
  }
    public static long[] statements = new long[492];
    public static long[] branches = new long[207];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[106];
  static {
    final String SECTION_NAME = "org.jfree.chart.ChartFactory.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 105; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$sdmavcd19fpr9mzxglw5kxvq01 () {
    super("org.jfree.chart.ChartFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 491; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 206; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 105; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.ChartFactory.java");
      for (int i = 1; i <= 491; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 206; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 105; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

