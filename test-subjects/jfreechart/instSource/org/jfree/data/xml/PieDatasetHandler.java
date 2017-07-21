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
 * PieDatasetHandler.java
 * ----------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1 (DG);
 *
 */

package org.jfree.data.xml;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAX handler for reading a {@link PieDataset} from an XML file.
 */
public class PieDatasetHandler extends RootHandler implements DatasetTags {
  static {
    CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.ping();
  }


    /** The pie dataset under construction. */
    private DefaultPieDataset dataset;

    /**
     * Default constructor.
     */
    public PieDatasetHandler() {
        this.dataset = null;
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[1]++;
    }

    /**
     * Returns the dataset.
     *
     * @return The dataset.
     */
    public PieDataset getDataset() {
        return this.dataset;
    }

    /**
     * Adds an item to the dataset under construction.
     *
     * @param key  the key.
     * @param value  the value.
     */
    public void addItem(Comparable key, Number value) {
        this.dataset.setValue(key, value);
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[2]++;
    }

    /**
     * Starts an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     * @param atts  the element attributes.
     *
     * @throws SAXException for errors.
     */
    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts) throws SAXException {
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[3]++;

        DefaultHandler current = getCurrentHandler();
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((current != this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[1]++;
            current.startElement(namespaceURI, localName, qName, atts);
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[5]++;

        }
        else {
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[2]++;
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[6]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((qName.equals(PIEDATASET_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[3]++;
            this.dataset = new DefaultPieDataset();
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[7]++;

        }
        else {
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[4]++;
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[8]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((qName.equals(ITEM_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[5]++;
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[9]++;
            ItemHandler subhandler = new ItemHandler(this, this);
            getSubHandlers().push(subhandler);
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[10]++;
            subhandler.startElement(namespaceURI, localName, qName, atts);
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[11]++;

        } else {
  CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[6]++;}
}
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
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[12]++;

        DefaultHandler current = getCurrentHandler();
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((current != this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[7]++;
            current.endElement(namespaceURI, localName, qName);
CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.statements[14]++;

        } else {
  CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh.branches[8]++;}

    }

}

class CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xml.PieDatasetHandler.java";
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

  public CodeCoverCoverageCounter$d6zekwx3dx8iol3xpouhvxr9wi8a0zunoh () {
    super("org.jfree.data.xml.PieDatasetHandler.java");
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
    log.startNamedSection("org.jfree.data.xml.PieDatasetHandler.java");
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

