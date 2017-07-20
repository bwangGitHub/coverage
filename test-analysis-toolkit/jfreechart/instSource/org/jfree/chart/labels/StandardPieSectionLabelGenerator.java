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
 * -------------------------------------
 * StandardPieSectionLabelGenerator.java
 * -------------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 09-Nov-2004 : Version 1, derived from StandardPieItemLabelGenerator (DG);
 * 29-Jul-2005 : Removed unused generateToolTip() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-May-2006 : Modified DEFAULT_SECTION_LABEL_FORMAT (DG);
 * 10-Jan-2007 : Include attributedLabels in equals() test (DG);
 * 10-Jul-2007 : Added constructors with locale parameter (DG);
 *
 */

package org.jfree.chart.labels;

import java.awt.Font;
import java.awt.Paint;
import java.awt.font.TextAttribute;
import java.io.Serializable;
import java.text.AttributedString;
import java.text.NumberFormat;
import java.util.Locale;

import org.jfree.data.general.PieDataset;
import org.jfree.util.ObjectList;

/**
 * A standard item label generator for plots that use data from a 
 * {@link PieDataset}.
 * <p>
 * For the label format, use {0} where the pie section key should be inserted,
 * {1} for the absolute section value and {2} for the percent amount of the pie
 * section, e.g. <code>"{0} = {1} ({2})"</code> will display as  
 * <code>apple = 120 (5%)</code>.
 */
public class StandardPieSectionLabelGenerator 
        extends AbstractPieItemLabelGenerator
        implements PieSectionLabelGenerator, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3064190563760203668L;
  static {
    CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[1]++;
  }
    
    /** The default section label format. */
    public static final String DEFAULT_SECTION_LABEL_FORMAT = "{0}";
  static {
    CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[2]++;
  }

    /** 
     * An optional list of attributed labels (instances of AttributedString). 
     */
    private ObjectList attributedLabels;

    /**
     * Creates a new section label generator using 
     * {@link #DEFAULT_SECTION_LABEL_FORMAT} as the label format string, and 
     * platform default number and percentage formatters.
     */
    public StandardPieSectionLabelGenerator() {
        this(DEFAULT_SECTION_LABEL_FORMAT, NumberFormat.getNumberInstance(), 
                NumberFormat.getPercentInstance());
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[3]++;
    }

    /**
     * Creates a new instance for the specified locale.
     * 
     * @param locale  the local (<code>null</code> not permitted).
     * 
     * @since 1.0.7
     */
    public StandardPieSectionLabelGenerator(Locale locale) {
        this(DEFAULT_SECTION_LABEL_FORMAT, locale);
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[4]++;
    }
    
    /**
     * Creates a new section label generator using the specified label format
     * string, and platform default number and percentage formatters.
     * 
     * @param labelFormat  the label format (<code>null</code> not permitted).
     */
    public StandardPieSectionLabelGenerator(String labelFormat) {
        this(labelFormat, NumberFormat.getNumberInstance(), 
                NumberFormat.getPercentInstance());
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[5]++;   
    }
    
    /**
     * Creates a new instance for the specified locale.
     * 
     * @param labelFormat  the label format (<code>null</code> not permitted).
     * @param locale  the local (<code>null</code> not permitted).
     * 
     * @since 1.0.7
     */
    public StandardPieSectionLabelGenerator(String labelFormat, Locale locale) {
        this(labelFormat, NumberFormat.getNumberInstance(locale),
                NumberFormat.getPercentInstance(locale));
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[6]++;
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
    public StandardPieSectionLabelGenerator(String labelFormat,
            NumberFormat numberFormat, NumberFormat percentFormat) {
        super(labelFormat, numberFormat, percentFormat);
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[7]++;
        this.attributedLabels = new ObjectList();
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[8]++;
    }

    /**
     * Returns the attributed label for a section, or <code>null</code> if none
     * is defined.
     * 
     * @param section  the section index.
     * 
     * @return The attributed label.
     */
    public AttributedString getAttributedLabel(int section) {
        return (AttributedString) this.attributedLabels.get(section);    
    }
    
    /**
     * Sets the attributed label for a section.
     * 
     * @param section  the section index.
     * @param label  the label (<code>null</code> permitted).
     */
    public void setAttributedLabel(int section, AttributedString label) {
        this.attributedLabels.set(section, label);
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[9]++;
    }
    
    /**
     * Generates a label for a pie section.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param key  the section key (<code>null</code> not permitted).
     * 
     * @return The label (possibly <code>null</code>).
     */
    public String generateSectionLabel(PieDataset dataset, Comparable key) {
        return super.generateSectionLabel(dataset, key);
    }

    /**
     * Generates an attributed label for the specified series, or 
     * <code>null</code> if no attributed label is available (in which case,
     * the string returned by 
     * {@link #generateSectionLabel(PieDataset, Comparable)} will 
     * provide the fallback).  Only certain attributes are recognised by the 
     * code that ultimately displays the labels: 
     * <ul>
     * <li>{@link TextAttribute#FONT}: will set the font;</li>
     * <li>{@link TextAttribute#POSTURE}: a value of 
     *     {@link TextAttribute#POSTURE_OBLIQUE} will add {@link Font#ITALIC} to
     *     the current font;</li>
     * <li>{@link TextAttribute#WEIGHT}: a value of 
     *     {@link TextAttribute#WEIGHT_BOLD} will add {@link Font#BOLD} to the 
     *     current font;</li>
     * <li>{@link TextAttribute#FOREGROUND}: this will set the {@link Paint} 
     *     for the current</li>
     * <li>{@link TextAttribute#SUPERSCRIPT}: the values 
     *     {@link TextAttribute#SUPERSCRIPT_SUB} and 
     *     {@link TextAttribute#SUPERSCRIPT_SUPER} are recognised.</li> 
     * </ul>
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param key  the key.
     * 
     * @return An attributed label (possibly <code>null</code>).
     */
    public AttributedString generateAttributedSectionLabel(PieDataset dataset, 
                                                           Comparable key) {
        return getAttributedLabel(dataset.getIndex(key));
    }

    /**
     * Tests the generator for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[2]++;}
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof StandardPieSectionLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[4]++;}
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[12]++;
        StandardPieSectionLabelGenerator that 
                = (StandardPieSectionLabelGenerator) obj;
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.attributedLabels.equals(that.attributedLabels)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[6]++;}
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch.branches[8]++;}
        return true;
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

class CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.StandardPieSectionLabelGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$t6mtz1cb2zt265i762pq822ib5omwcc7x03w20a7yx3833va3jrbcbuch () {
    super("org.jfree.chart.labels.StandardPieSectionLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.StandardPieSectionLabelGenerator.java");
      for (int i = 1; i <= 14; i++) {
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
    for (int i = 1; i <= 4; i++) {
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

