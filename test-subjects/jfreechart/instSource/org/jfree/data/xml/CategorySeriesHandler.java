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
 * --------------------------
 * CategorySeriesHandler.java
 * --------------------------
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

import java.util.Iterator;

import org.jfree.data.DefaultKeyedValues;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A handler for reading a series for a category dataset.
 */
public class CategorySeriesHandler extends DefaultHandler 
                                   implements DatasetTags {
  static {
    CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.ping();
  }


    /** The root handler. */
    private RootHandler root;

    /** The series key. */
    private Comparable seriesKey;

    /** The values. */
    private DefaultKeyedValues values;

    /**
     * Creates a new item handler.
     *
     * @param root  the root handler.
     */
    public CategorySeriesHandler(RootHandler root) {
        this.root = root;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[1]++;
        this.values = new DefaultKeyedValues();
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[2]++;
    }

    /**
     * Sets the series key.
     *
     * @param key  the key.
     */
    public void setSeriesKey(Comparable key) {
        this.seriesKey = key;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[3]++;
    }

    /**
     * Adds an item to the temporary storage for the series.
     *
     * @param key  the key.
     * @param value  the value.
     */
    public void addItem(Comparable key, final Number value) {
        this.values.addValue(key, value);
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[4]++;
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
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((qName.equals(SERIES_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.branches[1]++;
            setSeriesKey(atts.getValue("name"));
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[6]++;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[7]++;
            ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[8]++;

        }
        else {
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.branches[2]++;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[9]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((qName.equals(ITEM_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.branches[3]++;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[10]++;
            ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[11]++;
            subhandler.startElement(namespaceURI, localName, qName, atts);
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[12]++;

        }

        else {
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.branches[4]++;
            throw new SAXException(
                "Expecting <Series> or <Item> tag...found " + qName
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
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.root instanceof CategoryDatasetHandler) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.branches[5]++;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[14]++;
            CategoryDatasetHandler handler = (CategoryDatasetHandler) this.root;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[15]++;

            Iterator iterator = this.values.getKeys().iterator();
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
            while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.loops[1]--;
  CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.loops[2]--;
  CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.loops[3]++;
}
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[17]++;
                Comparable key = (Comparable) iterator.next();
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[18]++;
                Number value = this.values.getValue(key);
                handler.addItem(this.seriesKey, key, value);
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[19]++;
            }

            this.root.popSubHandler();
CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.statements[20]++;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech.branches[6]++;}

    }

}

class CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xml.CategorySeriesHandler.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$ltb1ypvvjuamuoilzde0exbnkhhwm8asobllbech () {
    super("org.jfree.data.xml.CategorySeriesHandler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xml.CategorySeriesHandler.java");
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
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

