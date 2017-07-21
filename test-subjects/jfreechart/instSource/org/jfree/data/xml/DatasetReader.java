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
 * ------------------
 * DatasetReader.java
 * ------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Nov-2002 : Version 1 (DG);
 *
 */

package org.jfree.data.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.xml.sax.SAXException;

/**
 * A utility class for reading datasets from XML.
 */
public class DatasetReader {
  static {
    CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.ping();
  }


    /**
     * Reads a {@link PieDataset} from an XML file.
     *
     * @param file  the file.
     *
     * @return A dataset.
     *
     * @throws IOException if there is a problem reading the file.
     */
    public static PieDataset readPieDatasetFromXML(File file) 
        throws IOException {
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[1]++;
        InputStream in = new FileInputStream(file);
        return readPieDatasetFromXML(in);
    }

    /**
     * Reads a {@link PieDataset} from a stream.
     *
     * @param in  the input stream.
     *
     * @return A dataset.
     *
     * @throws IOException if there is an I/O error.
     */
    public static PieDataset readPieDatasetFromXML(InputStream in) 
        throws IOException {
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[2]++;

        PieDataset result = null;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[3]++;
        SAXParserFactory factory = SAXParserFactory.newInstance();
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[4]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[5]++;
            SAXParser parser = factory.newSAXParser();
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[6]++;
            PieDatasetHandler handler = new PieDatasetHandler();
            parser.parse(in, handler);
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[7]++;
            result = handler.getDataset();
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[8]++;
        }
        catch (SAXException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.branches[2]++;
            System.out.println(e.getMessage());
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[9]++;
        }
        catch (ParserConfigurationException e2) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.branches[3]++;
            System.out.println(e2.getMessage());
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[10]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.branches[1]++;
}
  }
        return result;

    }

    /**
     * Reads a {@link CategoryDataset} from a file.
     *
     * @param file  the file.
     *
     * @return A dataset.
     *
     * @throws IOException if there is a problem reading the file.
     */
    public static CategoryDataset readCategoryDatasetFromXML(File file) 
        throws IOException {
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[11]++;
        InputStream in = new FileInputStream(file);
        return readCategoryDatasetFromXML(in);
    }

    /**
     * Reads a {@link CategoryDataset} from a stream.
     *
     * @param in  the stream.
     *
     * @return A dataset.
     *
     * @throws IOException if there is a problem reading the file.
     */
    public static CategoryDataset readCategoryDatasetFromXML(InputStream in) 
        throws IOException {
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[12]++;

        CategoryDataset result = null;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[13]++;

        SAXParserFactory factory = SAXParserFactory.newInstance();
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[14]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[15]++;
            SAXParser parser = factory.newSAXParser();
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[16]++;
            CategoryDatasetHandler handler = new CategoryDatasetHandler();
            parser.parse(in, handler);
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[17]++;
            result = handler.getDataset();
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[18]++;
        }
        catch (SAXException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.branches[5]++;
            System.out.println(e.getMessage());
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[19]++;
        }
        catch (ParserConfigurationException e2) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.branches[6]++;
            System.out.println(e2.getMessage());
CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.statements[20]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt.branches[4]++;
}
  }
        return result;

    }

}

class CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[7];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5oppif6w8pfmwgu8kn0a4b60z0pt () {
    super("org.jfree.data.xml.DatasetReader.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xml.DatasetReader.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

