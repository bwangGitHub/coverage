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
 * ValueHandler.java
 * -----------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Luke Quinane;
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1 (DG);
 * 25-Nov-2003 : Patch to handle 'NaN' values (DG);
 *
 */

package org.jfree.data.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A handler for reading a 'Value' element.
 */
public class ValueHandler extends DefaultHandler implements DatasetTags {
  static {
    CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.ping();
  }


    /** The root handler. */
    private RootHandler rootHandler;

    /** The item handler. */
    private ItemHandler itemHandler;

    /** Storage for the current CDATA */
    private StringBuffer currentText;

    /**
     * Creates a new value handler.
     *
     * @param rootHandler  the root handler.
     * @param itemHandler  the item handler.
     */
    public ValueHandler(RootHandler rootHandler, ItemHandler itemHandler) {
        this.rootHandler = rootHandler;
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[1]++;
        this.itemHandler = itemHandler;
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[2]++;
        this.currentText = new StringBuffer();
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[3]++;
    }

    /**
     * The start of an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     * @param atts  the attributes.
     *
     * @throws SAXException for errors.
     */
    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts) throws SAXException {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((qName.equals(VALUE_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[1]++;
            // no attributes to read
            clearCurrentText();
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[5]++;

        }
        else {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[2]++;
            throw new SAXException("Expecting <Value> but found " + qName);
        }

    }

    /**
     * The end of an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     *
     * @throws SAXException for errors.
     */
    public void endElement(String namespaceURI,
                           String localName,
                           String qName) throws SAXException {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((qName.equals(VALUE_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[3]++;
            Number value;
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[7]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                value = Double.valueOf(this.currentText.toString());
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[8]++;
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((((Double) value).isNaN()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[6]++;
                    value = null;
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[10]++;

                } else {
  CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[7]++;}
            } 
            catch (NumberFormatException e1) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[8]++;
                value = null;
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[11]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[5]++;
}
  }
            this.itemHandler.setValue(value);
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[12]++;
            this.rootHandler.popSubHandler();
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[13]++;

        }
        else {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[4]++;
            throw new SAXException("Expecting </Value> but found " + qName);
        }

    }

    /**
     * Receives some (or all) of the text in the current element.
     *
     * @param ch  character buffer.
     * @param start  the start index.
     * @param length  the length of the valid character data.
     */
    public void characters(char[] ch, int start, int length) {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.currentText != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[9]++;
            this.currentText.append(String.copyValueOf(ch, start, length));
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[15]++;

        } else {
  CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.branches[10]++;}
    }

    /**
     * Returns the current text of the textbuffer.
     *
     * @return The current text.
     */
    protected String getCurrentText() {
        return this.currentText.toString();
    }

    /**
     * Removes all text from the textbuffer at the end of a CDATA section.
     */
    protected void clearCurrentText() {
        this.currentText.delete(0, this.currentText.length());
CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81.statements[16]++;
    }

}

class CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81 ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xml.ValueHandler.java";
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

  public CodeCoverCoverageCounter$10d61zk936ftuq2tdmm46ltzo81 () {
    super("org.jfree.data.xml.ValueHandler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.jfree.data.xml.ValueHandler.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
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
