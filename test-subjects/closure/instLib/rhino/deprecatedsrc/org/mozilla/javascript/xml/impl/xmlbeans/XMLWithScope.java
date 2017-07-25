/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.*;

final class XMLWithScope extends NativeWith
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.ping();
  }

    private static final long serialVersionUID = -696429282095170887L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[1]++;
  }

    private XMLLibImpl lib;
    private int         _currIndex;
    private XMLList     _xmlList;
    private XMLObject   _dqPrototype;

    XMLWithScope(XMLLibImpl lib, Scriptable parent, XMLObject prototype)
    {
        super(parent, prototype);
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[2]++;
        this.lib = lib;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[3]++;
    }

    void initAsDotQuery()
    {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[4]++;
        XMLObject prototype = (XMLObject)getPrototype();
        // XMLWithScope also handles the .(xxx) DotQuery for XML
        // basically DotQuery is a for/in/with statement and in
        // the following 3 statements we setup to signal it's
        // DotQuery,
        // the index and the object being looped over.  The
        // xws.setPrototype is the scope of the object which is
        // is a element of the lhs (XMLList).
        _currIndex = 0;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[5]++;
        _dqPrototype = prototype;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[6]++;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((prototype instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[1]++;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[8]++;
            XMLList xl = (XMLList)prototype;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xl.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[3]++;
                setPrototype((Scriptable)(xl.get(0, null)));
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[10]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[2]++;}
        // Always return the outer-most type of XML lValue of
        // XML to left of dotQuery.
        _xmlList = new XMLList(lib);
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[11]++;
    }

    protected Object updateDotQuery(boolean value)
    {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[12]++;
        // Return null to continue looping

        XMLObject seed = _dqPrototype;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[13]++;
        XMLList xmlL = _xmlList;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seed instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[5]++;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[15]++;
            // We're a list so keep testing each element of the list if the
            // result on the top of stack is true then that element is added
            // to our result list.  If false, we try the next element.
            XMLList orgXmlL = (XMLList)seed;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[16]++;

            int idx = _currIndex;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;

            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[7]++;
                xmlL.addToList(orgXmlL.get(idx, null));
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[18]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[8]++;}
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;

            // More elements to test?
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((++idx < orgXmlL.length()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[9]++;
                // Yes, set our new index, get the next element and
                // reset the expression to run with this object as
                // the WITH selector.
                _currIndex = idx;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[20]++;
                setPrototype((Scriptable)(orgXmlL.get(idx, null)));
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[21]++;

                // continue looping
                return null;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[10]++;}

        } else {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[6]++;
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
            // If we're not a XMLList then there's no looping
            // just return DQPrototype if the result is true.
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[11]++;
              xmlL.addToList(seed);
CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.statements[23]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1.branches[12]++;}
        }

        return xmlL;
    }
}

class CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1 ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLWithScope.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$59ffmx2g7l6f4hkrq7jsop3kb3dri90tx4ba1wwrc1 () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLWithScope.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLWithScope.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

