/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Undefined;

class XMLName extends Ref
{
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.ping();
  }

    static final long serialVersionUID = 3832176310755686977L;
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[1]++;
  }

    private String uri;
    private String localName;
    private boolean isAttributeName;
    private boolean isDescendants;
    private XMLObjectImpl xmlObject;

    private XMLName(String uri, String localName)
    {
        this.uri = uri;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[2]++;
        this.localName = localName;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[3]++;
    }

    static XMLName formStar()
    {
        return new XMLName(null, "*");
    }

    static XMLName formProperty(String uri, String localName)
    {
        return new XMLName(uri, localName);
    }

    void initXMLObject(XMLObjectImpl xmlObject)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[1]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[2]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.xmlObject != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[3]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[4]++;}
        this.xmlObject = xmlObject;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[6]++;
    }

    String uri()
    {
        return uri;
    }

    String localName()
    {
        return localName;
    }

    boolean isAttributeName()
    {
        return isAttributeName;
    }

    void setAttributeName()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isAttributeName) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[5]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[6]++;}
        isAttributeName = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[8]++;
    }

    boolean isDescendants()
    {
        return isDescendants;
    }

    void setIsDescendants()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isDescendants) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[7]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[8]++;}
        isDescendants = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[10]++;
    }

    public boolean has(Context cx)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[10]++;}
        return xmlObject.hasXMLProperty(this);
    }

    public Object get(Context cx)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[12]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[11]++;
            throw ScriptRuntime.undefReadError(Undefined.instance,
                                               toString());

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[12]++;}
        return xmlObject.getXMLProperty(this);
    }

    public Object set(Context cx, Object value)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[13]++;
            throw ScriptRuntime.undefWriteError(Undefined.instance,
                                                toString(),
                                                value);

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[14]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[14]++;
int CodeCoverConditionCoverageHelper_C8;
        // Assignment to descendants causes parse error on bad reference
        // and this should not be called
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isDescendants) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[15]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[16]++;}
        xmlObject.putXMLProperty(this, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[15]++;
        return value;
    }

    public boolean delete(Context cx)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[16]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[17]++;
            return true;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[18]++;}
        xmlObject.deleteXMLProperty(this);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[17]++;
        return !xmlObject.hasXMLProperty(this);
    }

    public String toString()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[18]++;
        //return qname.localName();
        StringBuffer buff = new StringBuffer();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[19]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isDescendants) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[19]++; buff.append("..");
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[20]++;
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[20]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[21]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isAttributeName) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[21]++; buff.append('@');
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[22]++;
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[22]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[23]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[23]++;
            buff.append('*');
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[24]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[25]++;
int CodeCoverConditionCoverageHelper_C13;
            if((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[25]++;
                return buff.toString();

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[26]++;}

        } else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.branches[24]++;
            buff.append('"').append(uri()).append('"');
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[26]++;
        }
        buff.append(':').append(localName());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh.statements[27]++;
        return buff.toString();
    }

}

class CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLName.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$di175yxae4i0fxztrydxgtpaqwazsk1roh () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLName.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLName.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

