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
 * -------------------
 * ChartUtilities.java
 * -------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Wolfgang Irler;
 *                   Richard Atkinson;
 *                   Xavier Poinsard;
 *
 * Changes
 * -------
 * 11-Dec-2001 : Version 1.  The JPEG method comes from Wolfgang Irler's 
 *               JFreeChartServletDemo class (DG);
 * 23-Jan-2002 : Changed saveChartAsXXX() methods to pass IOExceptions back to 
 *               caller (DG);
 * 26-Jun-2002 : Added image map methods (DG);
 * 05-Aug-2002 : Added writeBufferedImage methods
 *               Modified writeImageMap method to support flexible image 
 *               maps (RA);
 * 26-Aug-2002 : Added saveChartAsJPEG and writeChartAsJPEG methods with info 
 *               objects (RA);
 * 05-Sep-2002 : Added writeImageMap() method to support OverLIB
 *               - http://www.bosrup.com/web/overlib (RA);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 17-Oct-2002 : Exposed JPEG quality setting and PNG compression level as 
 *               parameters (DG);
 * 25-Oct-2002 : Fixed writeChartAsJPEG() empty method bug (DG);
 * 13-Mar-2003 : Updated writeImageMap method as suggested by Xavier Poinsard 
 *               (see Feature Request 688079) (DG);
 * 12-Aug-2003 : Added support for custom image maps using 
 *               ToolTipTagFragmentGenerator and URLTagFragmentGenerator (RA);
 * 02-Sep-2003 : Separated PNG encoding from writing chart to an 
 *               OutputStream (RA);
 * 04-Dec-2003 : Chart draw() method modified to include anchor point (DG);
 * 20-Feb-2004 : Edited Javadocs and added argument checking (DG);
 * 05-Apr-2004 : Fixed problem with buffered image type (DG);
 * 01-Aug-2004 : Modified to use EncoderUtil for all image encoding (RA);
 * 02-Aug-2004 : Delegated image map related functionality to ImageMapUtil (RA);
 * 13-Jan-2005 : Renamed ImageMapUtil --> ImageMapUtilities, removed method
 *               writeImageMap(PrintWriter, String, ChartRenderingInfo) which 
 *               exists in ImageMapUtilities (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Feb-2006 : API doc update (DG);
 * 19-Mar-2007 : Use try-finally to close output stream in saveChartAsXXX() 
 *               methods (DG);
 *
 */

package org.jfree.chart;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.imagemap.ImageMapUtilities;
import org.jfree.chart.imagemap.OverLIBToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.URLTagFragmentGenerator;

/**
 * A collection of utility methods for JFreeChart.  Includes methods for 
 * converting charts to image formats (PNG and JPEG) plus creating simple HTML 
 * image maps.
 * 
 * @see ImageMapUtilities
 */
public abstract class ChartUtilities {
  static {
    CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.ping();
  }


    /**
     * Writes a chart to an output stream in PNG format.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsPNG(OutputStream out, JFreeChart chart,
            int width, int height) throws IOException {

        // defer argument checking...
        writeChartAsPNG(out, chart, width, height, null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[1]++;

    }

    /**
     * Writes a chart to an output stream in PNG format.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param encodeAlpha  encode alpha?
     * @param compression  the compression level (0-9).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsPNG(OutputStream out, JFreeChart chart,
            int width, int height, boolean encodeAlpha, int compression) 
            throws IOException {

        // defer argument checking...
        ChartUtilities.writeChartAsPNG(out, chart, width, height, null, 
                encodeAlpha, compression);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[2]++;

    }

    /**
     * Writes a chart to an output stream in PNG format.  This method allows 
     * you to pass in a {@link ChartRenderingInfo} object, to collect 
     * information about the chart dimensions/entities.  You will need this 
     * info if you want to create an HTML image map.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  the chart rendering info (<code>null</code> permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsPNG(OutputStream out, JFreeChart chart,
            int width, int height,  ChartRenderingInfo info) 
            throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[1]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[2]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[4]++;
        BufferedImage bufferedImage 
                = chart.createBufferedImage(width, height, info);
        EncoderUtil.writeBufferedImage(bufferedImage, ImageFormat.PNG, out);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[5]++;
    }

    /**
     * Writes a chart to an output stream in PNG format.  This method allows 
     * you to pass in a {@link ChartRenderingInfo} object, to collect 
     * information about the chart dimensions/entities.  You will need this 
     * info if you want to create an HTML image map.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  carries back chart rendering info (<code>null</code> 
     *              permitted).
     * @param encodeAlpha  encode alpha?
     * @param compression  the PNG compression level (0-9).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsPNG(OutputStream out, JFreeChart chart,
            int width, int height, ChartRenderingInfo info,
            boolean encodeAlpha, int compression) throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((out == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[3]++;
            throw new IllegalArgumentException("Null 'out' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[4]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[5]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[6]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[8]++;
        BufferedImage chartImage = chart.createBufferedImage(width, height, 
                BufferedImage.TYPE_INT_ARGB, info);
        ChartUtilities.writeBufferedImageAsPNG(out, chartImage, encodeAlpha, 
                compression);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[9]++;

    }

    /**
     * Writes a scaled version of a chart to an output stream in PNG format.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the unscaled chart width.
     * @param height  the unscaled chart height.
     * @param widthScaleFactor  the horizontal scale factor.
     * @param heightScaleFactor  the vertical scale factor.
     *
     * @throws IOException if there are any I/O problems.
     */
    public static void writeScaledChartAsPNG(OutputStream out,
            JFreeChart chart, int width, int height, int widthScaleFactor,
            int heightScaleFactor) throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((out == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[7]++;
            throw new IllegalArgumentException("Null 'out' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[8]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[9]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[10]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[12]++;

        double desiredWidth = width * widthScaleFactor;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[13]++;
        double desiredHeight = height * heightScaleFactor;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[14]++;
        double defaultWidth = width;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[15]++;
        double defaultHeight = height;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[16]++;
        boolean scale = false;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;

        // get desired width and height from somewhere then...
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((widthScaleFactor != 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((heightScaleFactor != 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[11]++;
            scale = true;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[18]++;

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[12]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[19]++;

        double scaleX = desiredWidth / defaultWidth;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[20]++;
        double scaleY = desiredHeight / defaultHeight;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[21]++;

        BufferedImage image = new BufferedImage((int) desiredWidth, 
                (int) desiredHeight, BufferedImage.TYPE_INT_ARGB);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[22]++;
        Graphics2D g2 = image.createGraphics();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((scale) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[13]++;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[24]++;
            AffineTransform saved = g2.getTransform();
            g2.transform(AffineTransform.getScaleInstance(scaleX, scaleY));
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[25]++;
            chart.draw(g2, new Rectangle2D.Double(0, 0, defaultWidth, 
                    defaultHeight), null, null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[26]++;
            g2.setTransform(saved);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[27]++;
            g2.dispose();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[28]++;

        }
        else {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[14]++;
            chart.draw(g2, new Rectangle2D.Double(0, 0, defaultWidth, 
                    defaultHeight), null, null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[29]++;
        }
        out.write(encodeAsPNG(image));
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[30]++;

    }

    /**
     * Saves a chart to the specified file in PNG format.
     *
     * @param file  the file name (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void saveChartAsPNG(File file, JFreeChart chart,
            int width, int height) throws IOException {

        // defer argument checking...
        saveChartAsPNG(file, chart, width, height, null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[31]++;

    }

    /**
     * Saves a chart to a file in PNG format.  This method allows you to pass 
     * in a {@link ChartRenderingInfo} object, to collect information about the 
     * chart dimensions/entities.  You will need this info if you want to 
     * create an HTML image map.
     *
     * @param file  the file (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  the chart rendering info (<code>null</code> permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void saveChartAsPNG(File file, JFreeChart chart,
            int width, int height, ChartRenderingInfo info) 
        throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((file == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[15]++;
            throw new IllegalArgumentException("Null 'file' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[16]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[33]++;
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[34]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            ChartUtilities.writeChartAsPNG(out, chart, width, height, info);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[35]++;
        }
        finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[17]++;
}
            out.close();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[36]++;
        }
    }

    /**
     * Saves a chart to a file in PNG format.  This method allows you to pass 
     * in a {@link ChartRenderingInfo} object, to collect information about the 
     * chart dimensions/entities.  You will need this info if you want to 
     * create an HTML image map.
     *
     * @param file  the file (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  the chart rendering info (<code>null</code> permitted).
     * @param encodeAlpha  encode alpha?
     * @param compression  the PNG compression level (0-9).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void saveChartAsPNG(File file, JFreeChart chart,
           int width, int height, ChartRenderingInfo info, boolean encodeAlpha,
           int compression) throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((file == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[18]++;
            throw new IllegalArgumentException("Null 'file' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[19]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[20]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[21]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[39]++;

        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[40]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            writeChartAsPNG(out, chart, width, height, info, encodeAlpha, 
                    compression);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[41]++;
        }
        finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[22]++;
}
            out.close();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[42]++;
        }

    }

    /**
     * Writes a chart to an output stream in JPEG format.  Please note that
     * JPEG is a poor format for chart images, use PNG if possible.
     * 
     * @param out  the output stream (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsJPEG(OutputStream out,
            JFreeChart chart, int width, int height) throws IOException {

        // defer argument checking...
        writeChartAsJPEG(out, chart, width, height, null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[43]++;

    }

    /**
     * Writes a chart to an output stream in JPEG format.  Please note that
     * JPEG is a poor format for chart images, use PNG if possible.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param quality  the quality setting.
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsJPEG(OutputStream out, float quality,
            JFreeChart chart, int width, int height) throws IOException {

        // defer argument checking...
        ChartUtilities.writeChartAsJPEG(out, quality, chart, width, height, 
                null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[44]++;

    }

    /**
     * Writes a chart to an output stream in JPEG format. This method allows 
     * you to pass in a {@link ChartRenderingInfo} object, to collect 
     * information about the chart dimensions/entities.  You will need this 
     * info if you want to create an HTML image map.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  the chart rendering info (<code>null</code> permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsJPEG(OutputStream out, JFreeChart chart,
            int width, int height, ChartRenderingInfo info) 
            throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[23]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[24]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[46]++;
        BufferedImage image = chart.createBufferedImage(width, height, info);
        EncoderUtil.writeBufferedImage(image, ImageFormat.JPEG, out);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[47]++;

    }

    /**
     * Writes a chart to an output stream in JPEG format.  This method allows 
     * you to pass in a {@link ChartRenderingInfo} object, to collect 
     * information about the chart dimensions/entities.  You will need this 
     * info if you want to create an HTML image map.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param quality  the output quality (0.0f to 1.0f).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  the chart rendering info (<code>null</code> permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeChartAsJPEG(OutputStream out, float quality,
            JFreeChart chart, int width, int height, ChartRenderingInfo info) 
            throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[25]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[26]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[49]++;
        BufferedImage image = chart.createBufferedImage(width, height, info);
        EncoderUtil.writeBufferedImage(image, ImageFormat.JPEG, out, quality);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[50]++;

    }

    /**
     * Saves a chart to a file in JPEG format.
     *
     * @param file  the file (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void saveChartAsJPEG(File file, JFreeChart chart,
            int width, int height) throws IOException {

        // defer argument checking...
        saveChartAsJPEG(file, chart, width, height, null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[51]++;

    }

    /**
     * Saves a chart to a file in JPEG format.
     *
     * @param file  the file (<code>null</code> not permitted).
     * @param quality  the JPEG quality setting.
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void saveChartAsJPEG(File file, float quality,
            JFreeChart chart, int width, int height) throws IOException {

        // defer argument checking...
        saveChartAsJPEG(file, quality, chart, width, height, null);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[52]++;

    }

    /**
     * Saves a chart to a file in JPEG format.  This method allows you to pass 
     * in a {@link ChartRenderingInfo} object, to collect information about the 
     * chart dimensions/entities.  You will need this info if you want to 
     * create an HTML image map.
     *
     * @param file  the file name (<code>null</code> not permitted).
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  the chart rendering info (<code>null</code> permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void saveChartAsJPEG(File file, JFreeChart chart,
            int width, int height, ChartRenderingInfo info) throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[53]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((file == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[27]++;
            throw new IllegalArgumentException("Null 'file' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[28]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[29]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[30]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[55]++;
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[56]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            writeChartAsJPEG(out, chart, width, height, info);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[57]++;
        }
        finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[31]++;
}
            out.close();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[58]++;
        }

    }

    /**
     * Saves a chart to a file in JPEG format.  This method allows you to pass 
     * in a {@link ChartRenderingInfo} object, to collect information about the 
     * chart dimensions/entities.  You will need this info if you want to 
     * create an HTML image map.
     *
     * @param file  the file name (<code>null</code> not permitted).
     * @param quality  the quality setting.
     * @param chart  the chart (<code>null</code> not permitted).
     * @param width  the image width.
     * @param height  the image height.
     * @param info  the chart rendering info (<code>null</code> permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void saveChartAsJPEG(File file, float quality,
            JFreeChart chart, int width, int height,
            ChartRenderingInfo info) throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((file == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[32]++;
            throw new IllegalArgumentException("Null 'file' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[33]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((chart == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[34]++;
            throw new IllegalArgumentException("Null 'chart' argument.");

        } else {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[35]++;}
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[61]++;
        
        OutputStream out = new BufferedOutputStream(new FileOutputStream(
                file));
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[62]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
            writeChartAsJPEG(out, quality, chart, width, height, info);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[63]++;
        }
        finally {
if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[36]++;
}
            out.close();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[64]++;
        }

    }

    /**
     * Writes a {@link BufferedImage} to an output stream in JPEG format.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param image  the image (<code>null</code> not permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeBufferedImageAsJPEG(OutputStream out, 
            BufferedImage image) throws IOException {

        // defer argument checking...
        writeBufferedImageAsJPEG(out, 0.75f, image);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[65]++;

    }

    /**
     * Writes a {@link BufferedImage} to an output stream in JPEG format.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param quality  the image quality (0.0f to 1.0f).
     * @param image  the image (<code>null</code> not permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeBufferedImageAsJPEG(OutputStream out, float quality,
            BufferedImage image) throws IOException {

        EncoderUtil.writeBufferedImage(image, ImageFormat.JPEG, out, quality);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[66]++;

    }

    /**
     * Writes a {@link BufferedImage} to an output stream in PNG format.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param image  the image (<code>null</code> not permitted).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeBufferedImageAsPNG(OutputStream out, 
            BufferedImage image) throws IOException {

        EncoderUtil.writeBufferedImage(image, ImageFormat.PNG, out);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[67]++;

    }

    /**
     * Writes a {@link BufferedImage} to an output stream in PNG format.
     *
     * @param out  the output stream (<code>null</code> not permitted).
     * @param image  the image (<code>null</code> not permitted).
     * @param encodeAlpha  encode alpha?
     * @param compression  the compression level (0-9).
     *
     * @throws IOException if there are any I/O errors.
     */
    public static void writeBufferedImageAsPNG(OutputStream out,
            BufferedImage image, boolean encodeAlpha, int compression) 
            throws IOException {

        EncoderUtil.writeBufferedImage(image, ImageFormat.PNG, out, 
                compression, encodeAlpha);
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[68]++;
    }

    /**
     * Encodes a {@link BufferedImage} to PNG format.
     *
     * @param image  the image (<code>null</code> not permitted).
     *
     * @return A byte array in PNG format.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public static byte[] encodeAsPNG(BufferedImage image) throws IOException {
        return EncoderUtil.encode(image, ImageFormat.PNG);
    }

    /**
     * Encodes a {@link BufferedImage} to PNG format.
     *
     * @param image  the image (<code>null</code> not permitted).
     * @param encodeAlpha  encode alpha?
     * @param compression  the PNG compression level (0-9).
     *
     * @return The byte array in PNG format.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public static byte[] encodeAsPNG(BufferedImage image, boolean encodeAlpha, 
                                     int compression) 
            throws IOException {
        return EncoderUtil.encode(image, ImageFormat.PNG, compression, 
                encodeAlpha);
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
     * @throws IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer,
                                     String name,
                                     ChartRenderingInfo info,
                                     boolean useOverLibForToolTips) 
        throws IOException {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[69]++;

        ToolTipTagFragmentGenerator toolTipTagFragmentGenerator = null;
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((useOverLibForToolTips) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[37]++;
            toolTipTagFragmentGenerator 
                    = new OverLIBToolTipTagFragmentGenerator();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[71]++;

        }
        else {
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.branches[38]++;
            toolTipTagFragmentGenerator 
                    = new StandardToolTipTagFragmentGenerator();
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[72]++;
        }
        ImageMapUtilities.writeImageMap(writer, name, info, 
                toolTipTagFragmentGenerator, 
                new StandardURLTagFragmentGenerator());
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[73]++;

    }

    /**
     * Writes an image map to the specified writer.
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
     * @throws IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer, String name, 
            ChartRenderingInfo info, 
            ToolTipTagFragmentGenerator toolTipTagFragmentGenerator,
            URLTagFragmentGenerator urlTagFragmentGenerator) 
            throws IOException {

        writer.println(ImageMapUtilities.getImageMap(name, info, 
                toolTipTagFragmentGenerator, urlTagFragmentGenerator));
CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip.statements[74]++;
    }

    /**
     * Creates an HTML image map.  This method maps to 
     * {@link ImageMapUtilities#getImageMap(String, ChartRenderingInfo, 
     * ToolTipTagFragmentGenerator, URLTagFragmentGenerator)}, using default 
     * generators.
     *
     * @param name  the map name (<code>null</code> not permitted).
     * @param info  the chart rendering info (<code>null</code> not permitted).
     *
     * @return The map tag.
     */
    public static String getImageMap(String name, ChartRenderingInfo info) {
        return ImageMapUtilities.getImageMap(name, info,
                new StandardToolTipTagFragmentGenerator(),
                new StandardURLTagFragmentGenerator());
    }

    /**
     * Creates an HTML image map.  This method maps directly to
     * {@link ImageMapUtilities#getImageMap(String, ChartRenderingInfo, 
     * ToolTipTagFragmentGenerator, URLTagFragmentGenerator)}.
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

        return ImageMapUtilities.getImageMap(name, info, 
                toolTipTagFragmentGenerator, urlTagFragmentGenerator);
        
    }

}

class CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip ());
  }
    public static long[] statements = new long[75];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.chart.ChartUtilities.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$13v15jhex4h105c3ptwnujrmwh30ip () {
    super("org.jfree.chart.ChartUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 74; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.ChartUtilities.java");
      for (int i = 1; i <= 74; i++) {
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
    for (int i = 1; i <= 17; i++) {
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

