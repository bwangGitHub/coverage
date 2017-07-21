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
 * DisplayChart.java
 * -----------------
 * (C) Copyright 2002-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Aug-2002 : Version 1;
 * 09-Mar-2005 : Added facility to serve up "one time" charts - see 
 *               ServletUtilities.java (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet used for streaming charts to the client browser from the temporary
 * directory.  You need to add this servlet and mapping to your deployment 
 * descriptor (web.xml) in order to get it to work.  The syntax is as follows:
 * <xmp>
 * <servlet>
 *    <servlet-name>DisplayChart</servlet-name>
 *    <servlet-class>org.jfree.chart.servlet.DisplayChart</servlet-class>
 * </servlet>
 * <servlet-mapping>
 *     <servlet-name>DisplayChart</servlet-name>
 *     <url-pattern>/servlet/DisplayChart</url-pattern>
 * </servlet-mapping>
 * </xmp>
 */
public class DisplayChart extends HttpServlet {
  static {
    CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.ping();
  }


    /**
     * Default constructor.
     */
    public DisplayChart() {
        super();
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[1]++;
    }

    /**
     * Init method.
     *
     * @throws ServletException never.
     */
    public void init() throws ServletException {
        return;
    }

    /**
     * Service method.
     *
     * @param request  the request.
     * @param response  the response.
     *
     * @throws ServletException ??.
     * @throws IOException ??.
     */
    public void service(HttpServletRequest request, 
                        HttpServletResponse response)
            throws ServletException, IOException {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[2]++;

        HttpSession session = request.getSession();
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[3]++;
        String filename = request.getParameter("filename");
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[1]++;
            throw new ServletException("Parameter 'filename' must be supplied");

        } else {
  CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[2]++;}

        //  Replace ".." with ""
        //  This is to prevent access to the rest of the file system
        filename = ServletUtilities.searchReplace(filename, "..", "");
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[5]++;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[6]++;

        //  Check the file exists
        File file = new File(System.getProperty("java.io.tmpdir"), filename);
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((file.exists()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[3]++;
            throw new ServletException("File '" + file.getAbsolutePath() 
                    + "' does not exist");

        } else {
  CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[4]++;}
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[8]++;

        //  Check that the graph being served was created by the current user
        //  or that it begins with "public"
        boolean isChartInUserList = false;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[9]++;
        ChartDeleter chartDeleter = (ChartDeleter) session.getAttribute(
                "JFreeChart_Deleter");
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chartDeleter != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[5]++;
            isChartInUserList = chartDeleter.isChartAvailable(filename);
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[11]++;

        } else {
  CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[6]++;}
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[12]++;

        boolean isChartPublic = false;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((filename.length() >= 6) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[7]++;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((filename.substring(0, 6).equals("public")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[9]++;
                isChartPublic = true;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[15]++;

            } else {
  CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[8]++;}
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[16]++;
        
        boolean isOneTimeChart = false;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((filename.startsWith(ServletUtilities.getTempOneTimeFilePrefix())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[11]++;
            isOneTimeChart = true;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[18]++;
   
        } else {
  CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[12]++;}
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((isChartInUserList) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((isChartPublic) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isOneTimeChart) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[13]++;
            //  Serve it up
            ServletUtilities.sendTempFile(file, response);
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[20]++;
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isOneTimeChart) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[15]++;
                file.delete();
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.statements[22]++;
   
            } else {
  CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[16]++;}

        }
        else {
CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35.branches[14]++;
            throw new ServletException("Chart image not found");
        }
        return;
    }

}

class CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35 ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.chart.servlet.DisplayChart.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,3,1};
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

  public CodeCoverCoverageCounter$ssu6xjct9seds3vj40tx7sfj35 () {
    super("org.jfree.chart.servlet.DisplayChart.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
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
    log.startNamedSection("org.jfree.chart.servlet.DisplayChart.java");
      for (int i = 1; i <= 22; i++) {
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

