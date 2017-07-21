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
 * ----------------
 * ItemHandler.java
 * ----------------
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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A handler for reading key-value items.
 */
public class ItemHandler extends DefaultHandler implements DatasetTags {
  static {
    CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.ping();
  }


    /** The root handler. */
    private RootHandler root;

    /** The parent handler (can be the same as root, but not always). */
    private DefaultHandler parent;

    /** The key. */
    private Comparable key;

    /** The value. */
    private Number value;

    /**
     * Creates a new item handler.
     *
     * @param root  the root handler.
     * @param parent  the parent handler.
     */
    public ItemHandler(RootHandler root, DefaultHandler parent) {
        this.root = root;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[1]++;
        this.parent = parent;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[2]++;
        this.key = null;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[3]++;
        this.value = null;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[4]++;
    }

    /**
     * Returns the key that has been read by the handler, or <code>null</code>.
     *
     * @return The key.
     */
    public Comparable getKey() {
        return this.key;
    }

    /**
     * Sets the key.
     *
     * @param key  the key.
     */
    public void setKey(Comparable key) {
        this.key = key;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[5]++;
    }

    /**
     * Returns the key that has been read by the handler, or <code>null</code>.
     *
     * @return The value.
     */
    public Number getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     *
     * @param value  the value.
     */
    public void setValue(Number value) {
        this.value = value;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[6]++;
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
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((qName.equals(ITEM_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[1]++;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[8]++;
            KeyHandler subhandler = new KeyHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[9]++;

        }
        else {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[2]++;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[10]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((qName.equals(VALUE_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[3]++;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[11]++;
            ValueHandler subhandler = new ValueHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[12]++;

        }
        else {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[4]++;
            throw new SAXException(
                "Expected <Item> or <Value>...found " + qName
            );
        }
}

    }

    /**
     * The end of an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     */
    public void endElement(String namespaceURI,
                           String localName,
                           String qName) {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.parent instanceof PieDatasetHandler) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[5]++;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[14]++;
            PieDatasetHandler handler = (PieDatasetHandler) this.parent;
            handler.addItem(this.key, this.value);
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[15]++;
            this.root.popSubHandler();
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[16]++;

        }
        else {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[6]++;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[17]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.parent instanceof CategorySeriesHandler) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[7]++;
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[18]++;
            CategorySeriesHandler handler = (CategorySeriesHandler) this.parent;
            handler.addItem(this.key, this.value);
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[19]++;
            this.root.popSubHandler();
CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.statements[20]++;

        } else {
  CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt.branches[8]++;}
}

    }

}

class CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xml.ItemHandler.java";
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

  public CodeCoverCoverageCounter$4cjwootr51vr8hl3mmypmb7tt () {
    super("org.jfree.data.xml.ItemHandler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
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
    log.startNamedSection("org.jfree.data.xml.ItemHandler.java");
      for (int i = 1; i <= 20; i++) {
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

