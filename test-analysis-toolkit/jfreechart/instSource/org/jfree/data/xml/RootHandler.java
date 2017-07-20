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
 * RootHandler.java
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

import java.util.Stack;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAX handler that delegates work to sub-handlers.
 */
public class RootHandler extends DefaultHandler implements DatasetTags {
  static {
    CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.ping();
  }


    /** The sub-handlers. */
    private Stack subHandlers;

    /**
     * Creates a new handler.
     */
    public RootHandler() {
        this.subHandlers = new Stack();
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[1]++;
    }

    /**
     * Returns the stack of sub handlers.
     *
     * @return The sub-handler stack.
     */
    public Stack getSubHandlers() {
        return this.subHandlers;
    }

    /**
     * Receives some (or all) of the text in the current element.
     *
     * @param ch  character buffer.
     * @param start  the start index.
     * @param length  the length of the valid character data.
     *
     * @throws SAXException for errors.
     */
    public void characters(char[] ch, int start, int length) 
        throws SAXException {
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[2]++;
        DefaultHandler handler = getCurrentHandler();
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((handler != this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[1]++;
            handler.characters(ch, start, length);
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[4]++;

        } else {
  CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[2]++;}
    }

    /**
     * Returns the handler at the top of the stack.
     *
     * @return The handler.
     */
    public DefaultHandler getCurrentHandler() {
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[5]++;
        DefaultHandler result = this;
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.subHandlers != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[3]++;
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.subHandlers.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[5]++;
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[8]++;
                Object top = this.subHandlers.peek();
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((top != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[7]++;
                    result = (DefaultHandler) top;
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[10]++;

                } else {
  CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[8]++;}

            } else {
  CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.branches[4]++;}
        return result;
    }

    /**
     * Pushes a sub-handler onto the stack.
     *
     * @param subhandler  the sub-handler.
     */
    public void pushSubHandler(DefaultHandler subhandler) {
        this.subHandlers.push(subhandler);
CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5.statements[11]++;
    }

    /**
     * Pops a sub-handler from the stack.
     *
     * @return The sub-handler.
     */
    public DefaultHandler popSubHandler() {
        return (DefaultHandler) this.subHandlers.pop();
    }

}

class CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5 ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xml.RootHandler.java";
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

  public CodeCoverCoverageCounter$4voyhrbiih1clxeovs559h9b5 () {
    super("org.jfree.data.xml.RootHandler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
    log.startNamedSection("org.jfree.data.xml.RootHandler.java");
      for (int i = 1; i <= 11; i++) {
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

