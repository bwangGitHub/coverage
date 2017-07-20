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
 * --------------------------------
 * StandardPieToolTipGenerator.java
 * --------------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Andreas Schroeder;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1 (DG);
 * 16-Jan-2002 : Completed Javadocs (DG);
 * 29-Aug-2002 : Changed to format numbers using default locale (RA);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 30-Oct-2002 : Changed PieToolTipGenerator interface (DG);
 * 21-Mar-2003 : Implemented Serializable (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 19-Aug-2003 : Renamed StandardPieToolTipGenerator --> 
 *               StandardPieItemLabelGenerator (DG);
 * 10-Mar-2004 : Modified to use MessageFormat class (DG);
 * 31-Mar-2004 : Added javadocs for the MessageFormat usage (AS);
 * 15-Apr-2004 : Split PieItemLabelGenerator interface into 
 *               PieSectionLabelGenerator and PieToolTipGenerator (DG);
 * 25-Nov-2004 : Moved some code into abstract super class (DG);
 * 29-Jul-2005 : Removed implementation of PieSectionLabelGenerator 
 *               interface (DG);
 * 10-Jul-2007 : Added constructors with locale argument (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import org.jfree.data.general.PieDataset;
import org.jfree.util.PublicCloneable;

/**
 * A standard item label generator for plots that use data from a 
 * {@link PieDataset}.
 * <p>
 * For the label format, use {0} where the pie section key should be inserted,
 * {1} for the absolute section value and {2} for the percent amount of the pie
 * section, e.g. <code>"{0} = {1} ({2})"</code> will display as  
 * <code>apple = 120 (5%)</code>.
 */
public class StandardPieToolTipGenerator extends AbstractPieItemLabelGenerator
                                           implements PieToolTipGenerator,
                                                      Cloneable, 
                                                      PublicCloneable, 
                                                      Serializable {
  static {
    CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2995304200445733779L;
  static {
    CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[1]++;
  }
    
    /** The default tooltip format. */
    public static final String DEFAULT_TOOLTIP_FORMAT = "{0}: ({1}, {2})";
  static {
    CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[2]++;
  }

    /** 
     * The default section label format. 
     * 
     * @deprecated As of 1.0.7, use {@link #DEFAULT_TOOLTIP_FORMAT} instead.
     */
    public static final String DEFAULT_SECTION_LABEL_FORMAT = "{0} = {1}";
  static {
    CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[3]++;
  }

    /**
     * Creates an item label generator using default number formatters.
     */
    public StandardPieToolTipGenerator() {
        this(DEFAULT_TOOLTIP_FORMAT);
CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[4]++;
    }

    /**
     * Creates a pie tool tip generator for the specified locale, using the
     * default format string.
     * 
     * @param locale  the locale (<code>null</code> not permitted).
     * 
     * @since 1.0.7
     */
    public StandardPieToolTipGenerator(Locale locale) {
        this(DEFAULT_TOOLTIP_FORMAT, locale);
CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[5]++;
    }
    
    /**
     * Creates a pie tool tip generator for the default locale.
     * 
     * @param labelFormat  the label format (<code>null</code> not permitted).
     */
    public StandardPieToolTipGenerator(String labelFormat) {
        this(labelFormat, Locale.getDefault());
CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[6]++;
    }
    
    /**
     * Creates a pie tool tip generator for the specified locale.
     * 
     * @param labelFormat  the label format (<code>null</code> not permitted).
     * @param locale  the locale (<code>null</code> not permitted).
     * 
     * @since 1.0.7
     */
    public StandardPieToolTipGenerator(String labelFormat, Locale locale) {
        this(labelFormat, NumberFormat.getNumberInstance(locale), 
                NumberFormat.getPercentInstance(locale));
CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[7]++;
    }
    
    /**
     * Creates an item label generator using the specified number formatters.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param numberFormat  the format object for the values (<code>null</code>
     *                      not permitted).
     * @param percentFormat  the format object for the percentages 
     *                       (<code>null</code> not permitted).
     */
    public StandardPieToolTipGenerator(String labelFormat, 
            NumberFormat numberFormat, NumberFormat percentFormat) {
        super(labelFormat, numberFormat, percentFormat);
CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d.statements[8]++;
    }

    /**
     * Generates a tool tip text item for one section in a pie chart.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param key  the section key (<code>null</code> not permitted).
     *
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String generateToolTip(PieDataset dataset, Comparable key) {
        return generateSectionLabel(dataset, key);
    }

    /**
     * Returns an independent copy of the generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  should not happen.
     */
    public Object clone() throws CloneNotSupportedException {      
        return super.clone();
    }

}

class CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$22vp8kmedfvmi91hysrjivf84jwn5mr0b30r9j3tnhot93i99d () {
    super("org.jfree.chart.labels.StandardPieToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.StandardPieToolTipGenerator.java");
      for (int i = 1; i <= 8; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
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

