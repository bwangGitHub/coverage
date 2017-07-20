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
 * URLUtilities.java
 * -----------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributors:     -;
 *
 * Changes:
 * --------
 * 17-Apr-2007 : Version 1 (DG);
 * 
 */

package org.jfree.chart.urls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**
 * General utility methods for URLs.
 * 
 * @since 1.0.6
 */
public class URLUtilities {
  static {
    CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.ping();
  }


    /** Constant used by {@link #encode(String, String)}. */
    private static final Class[] STRING_ARGS_2 = new Class[] {String.class, 
            String.class};
  static {
    CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[1]++;
  }
    
    /**
     * Calls <code>java.net.URLEncoder.encode(String, String)</code> via
     * reflection, if we are running on JRE 1.4 or later, otherwise reverts to
     * the deprecated <code>URLEncoder.encode(String)</code> method.
     * 
     * @param s  the string to encode.
     * @param encoding  the encoding.
     * 
     * @return The encoded string.
     * 
     * @since 1.0.6
     */
    public static String encode(String s, String encoding) {
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[2]++;
        Class c = URLEncoder.class;
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[3]++;
        String result = null;
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[4]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[5]++;
            Method m = c.getDeclaredMethod("encode", STRING_ARGS_2);
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[6]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                result = (String) m.invoke(null, new Object[] {s, encoding});
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[7]++;
            }
            catch (InvocationTargetException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.branches[3]++;
                e.printStackTrace();
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[8]++;
            }
            catch (IllegalAccessException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.branches[4]++;
                e.printStackTrace();
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[9]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.branches[2]++;
}
  }
        }
        catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.branches[5]++;
            // we're running on JRE 1.3.1 so this is the best we have...
            result = URLEncoder.encode(s);
CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.statements[10]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd.branches[1]++;
}
  }
        return result;
    }
    
}

class CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[6];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$zx47it2zhwg8jxo9z6vylnynhd () {
    super("org.jfree.chart.urls.URLUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 5; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.urls.URLUtilities.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 5; i++) {
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

