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
 * ---------------------------
 * XYTitleAnnotationTests.java
 * ---------------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Apr-2007 : Version 1 (DG);
 *
 */

package org.jfree.experimental.chart.annotations.junit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.title.TextTitle;
import org.jfree.experimental.chart.annotations.XYTitleAnnotation;

/**
 * Tests for the {@link XYTitleAnnotation} class.
 */
public class XYTitleAnnotationTests extends TestCase {
  static {
    CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.ping();
  }


    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(XYTitleAnnotationTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public XYTitleAnnotationTests(String name) {
        super(name);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[1]++;
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    public void testEquals() {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[2]++;
        TextTitle t = new TextTitle("Title");
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[3]++;
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[4]++;
        XYTitleAnnotation a2 = new XYTitleAnnotation(1.0, 2.0, t);
        assertTrue(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[5]++;
        
        a1 = new XYTitleAnnotation(1.1, 2.0, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[6]++;
        assertFalse(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[7]++;
        a2 = new XYTitleAnnotation(1.1, 2.0, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[8]++;
        assertTrue(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[9]++;

        a1 = new XYTitleAnnotation(1.1, 2.2, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[10]++;
        assertFalse(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[11]++;
        a2 = new XYTitleAnnotation(1.1, 2.2, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[12]++;
        assertTrue(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[13]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[14]++;
        
        TextTitle t2 = new TextTitle("Title 2");
        a1 = new XYTitleAnnotation(1.1, 2.2, t2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[15]++;
        assertFalse(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[16]++;
        a2 = new XYTitleAnnotation(1.1, 2.2, t2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[17]++;
        assertTrue(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[18]++;
    }

    /**
     * Two objects that are equal are required to return the same hashCode. 
     */
    public void testHashCode() {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[19]++;
        TextTitle t = new TextTitle("Title");
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[20]++;
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[21]++;
        XYTitleAnnotation a2 = new XYTitleAnnotation(1.0, 2.0, t);
        assertTrue(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[22]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[23]++;
        int h1 = a1.hashCode();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[24]++;
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[25]++;
    }
    
    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[26]++;
        TextTitle t = new TextTitle("Title");
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[27]++;
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[28]++;
        XYTitleAnnotation a2 = null;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[29]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            a2 = (XYTitleAnnotation) a1.clone();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[30]++;
        }
        catch (CloneNotSupportedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.branches[2]++;
            e.printStackTrace();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[31]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.branches[1]++;
}
  }
        assertTrue(a1 != a2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[32]++;
        assertTrue(a1.getClass() == a2.getClass());
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[33]++;
        assertTrue(a1.equals(a2));
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[34]++;
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[35]++;
        TextTitle t = new TextTitle("Title");
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[36]++;
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[37]++;
        XYTitleAnnotation a2 = null;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[38]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[39]++;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[40]++;
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(a1);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[41]++;
            out.close();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[42]++;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[43]++;

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
                    buffer.toByteArray()));
            a2 = (XYTitleAnnotation) in.readObject();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[44]++;
            in.close();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[45]++;
        }
        catch (Exception e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.branches[4]++;
            e.printStackTrace();
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[46]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.branches[3]++;
}
  }
        assertEquals(a1, a2);
CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41.statements[47]++;
    }

}

class CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41 ());
  }
    public static long[] statements = new long[48];
    public static long[] branches = new long[5];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5ne80lh2c7218zplln8n7vw60741g1h1hui7lf7x41 () {
    super("org.jfree.experimental.chart.annotations.junit.XYTitleAnnotationTests.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 47; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.experimental.chart.annotations.junit.XYTitleAnnotationTests.java");
      for (int i = 1; i <= 47; i++) {
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

