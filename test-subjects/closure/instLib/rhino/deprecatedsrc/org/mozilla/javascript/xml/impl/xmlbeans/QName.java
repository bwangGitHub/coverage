/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import org.mozilla.javascript.*;

/**
 * Class QName
 *
 */
final class QName extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.ping();
  }

    static final long serialVersionUID = 416745167693026750L;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[1]++;
  }

    private static final Object QNAME_TAG = "QName";
  static {
    CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[2]++;
  }

    XMLLibImpl lib;
    private String prefix;
    private String localName;
    private String uri;

    QName(XMLLibImpl lib, String uri, String localName, String prefix)
    {
        super(lib.globalScope(), lib.qnamePrototype);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[3]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((localName == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[1]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[2]++;}
        this.lib = lib;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[5]++;
        this.uri = uri;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[6]++;
        this.prefix = prefix;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[7]++;
        this.localName = localName;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[8]++;
    }

    void exportAsJSClass(boolean sealed)
    {
        exportAsJSClass(MAX_PROTOTYPE_ID, lib.globalScope(), sealed);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[9]++;
    }

    /**
     *
     * @return
     */
    public String toString()
    {
        String result;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))
        {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[3]++;
            result = "*::".concat(localName);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[11]++;

        }
        else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[4]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[12]++;
int CodeCoverConditionCoverageHelper_C3; if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false))
        {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[5]++;
            result = localName;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[13]++;

        }
        else
        {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[6]++;
            result = uri + "::" + localName;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[14]++;
        }
}

        return result;
    }

    public String localName()
    {
        return localName;
    }

    String prefix()
    {
        return (prefix == null) ? prefix : "";
    }

    String uri()
    {
        return uri;
    }

    public boolean equals(Object obj)
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[7]++; return false;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[8]++;}
        return equals((QName)obj);
    }

    public int hashCode()
    {
        return localName.hashCode() ^ (uri == null ? 0 : uri.hashCode());
    }

    protected Object equivalentValues(Object value)
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[9]++; return Scriptable.NOT_FOUND;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[10]++;}
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[17]++;
        boolean result = equals((QName)value);
        return result ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean equals(QName q)
    {
        boolean result;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[11]++;
            result = q.uri == null && localName.equals(q.localName);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[19]++;

        } else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[12]++;
            result = uri.equals(q.uri) && localName.equals(q.localName);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[20]++;
        }

        return result;
    }

    /**
     *
     * @return
     */
    public String getClassName ()
    {
        return "QName";
    }

    /**
     *
     * @param hint
     * @return
     */
    public Object getDefaultValue (Class hint)
    {
        return toString();
    }

// #string_id_map#
    private static final int
        Id_localName            = 1,
        Id_uri                  = 2,
        MAX_INSTANCE_ID         = 2;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[21]++;
  }

    protected int getMaxInstanceId()
    {
        return super.getMaxInstanceId() + MAX_INSTANCE_ID;
    }

    protected int findInstanceIdInfo(String s)
    {
        int id;
// #generated# Last update: 2004-07-18 12:32:51 CEST
        L0: { id = 0;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[22]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[23]++; String X = null;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[24]++;
            int s_length = s.length();
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((s_length==3) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[13]++; X="uri";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[26]++;id=Id_uri;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[27]++;
 }
            else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[14]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[28]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s_length==9) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[15]++; X="localName";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[29]++;id=Id_localName;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[30]++;
 } else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[16]++;}
}
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[17]++; id = 0;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[32]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[18]++;}
        }
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[19]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[20]++;}

        int attr;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[34]++;
        switch (id) {
          case Id_localName:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[21]++;
          case Id_uri:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[22]++;
            attr = PERMANENT | READONLY;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[35]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[36]++;
            break;
          default:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[23]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }
// #/string_id_map#

    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[37]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_localName:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[24]++; return "localName";
          case Id_uri:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[25]++; return "uri"; default : CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[26]++;
        }
        return super.getInstanceIdName(id);
    }

    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[38]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_localName:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[27]++; return localName;
          case Id_uri:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[28]++; return uri; default : CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[29]++;
        }
        return super.getInstanceIdValue(id);
    }

// #string_id_map#
    private static final int
        Id_constructor          = 1,
        Id_toString             = 2,
        Id_toSource             = 3,
        MAX_PROTOTYPE_ID        = 3;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[39]++;
  }

    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2004-08-21 12:45:13 CEST
        L0: { id = 0;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[40]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[41]++; String X = null; int c;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[42]++;
            int s_length = s.length();
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[43]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[30]++;
                c=s.charAt(3);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[44]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[32]++; X="toSource";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[46]++;id=Id_toSource;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[47]++;
 }
                else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[33]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[48]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[34]++; X="toString";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[49]++;id=Id_toString;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[50]++;
 } else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[35]++;}
}

            }
            else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[31]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[51]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[36]++; X="constructor";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[52]++;id=Id_constructor;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[53]++;
 } else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[37]++;}
}
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[38]++; id = 0;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[55]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[39]++;}
        }
// #/generated#
        return id;
    }
// #/string_id_map#

    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[56]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[40]++; arity=2;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[57]++; s="constructor";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[58]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[59]++; break;
          case Id_toString:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[41]++;    arity=0;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[60]++; s="toString";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[61]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[62]++;    break;
          case Id_toSource:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[42]++;    arity=0;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[63]++; s="toSource";
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[64]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[65]++;    break;
          default:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[43]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(QNAME_TAG, id, s, arity);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[66]++;
    }

    public Object execIdCall(IdFunctionObject f,
                             Context cx,
                             Scriptable scope,
                             Scriptable thisObj,
                             Object[] args)
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((f.hasTag(QNAME_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[44]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[45]++;}
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[68]++;
        int id = f.methodId();
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[69]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[46]++;
            return jsConstructor(cx, (thisObj == null), args);
          case Id_toString:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[47]++;
            return realThis(thisObj, f).toString();
          case Id_toSource:
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[48]++;
            return realThis(thisObj, f).js_toSource(); default : CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[49]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private QName realThis(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
        if((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((thisObj instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[50]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[51]++;}
        return (QName)thisObj;
    }

    private Object jsConstructor(Context cx, boolean inNewExpr, Object[] args)
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[71]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[52]++;
            return lib.castToQName(cx, args[0]);

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[53]++;}
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[72]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[54]++;
            return lib.constructQName(cx, Undefined.instance);

        } else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[55]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[73]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[56]++;
            return lib.constructQName(cx, args[0]);

        } else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[57]++;
            return lib.constructQName(cx, args[0], args[1]);
        }
}
    }

    private String js_toSource()
    {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[74]++;
        StringBuffer sb = new StringBuffer();
        sb.append('(');
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[75]++;
        toSourceImpl(uri, localName, prefix, sb);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[76]++;
        sb.append(')');
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[77]++;
        return sb.toString();
    }

    private static void toSourceImpl(String uri, String localName,
                                     String prefix, StringBuffer sb)
    {
        sb.append("new QName(");
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[78]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[58]++;
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[80]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 (("*".equals(localName)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[60]++;
                sb.append("null, ");
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[81]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[61]++;}

        } else {
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.branches[59]++;
            Namespace.toSourceImpl(prefix, uri, sb);
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[82]++;
            sb.append(", ");
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[83]++;
        }
        sb.append('\'');
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[84]++;
        sb.append(ScriptRuntime.escapeString(localName, '\''));
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[85]++;
        sb.append("')");
CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541.statements[86]++;
    }

}

class CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541 ());
  }
    public static long[] statements = new long[87];
    public static long[] branches = new long[62];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-QName.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,3,1,1,1,1,1,3,1,1,2,1,1,2,1};
    for (int i = 1; i <= 22; i++) {
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

  public CodeCoverCoverageCounter$9m0i2o1yzuz6kmrd4zt15a2iynk2541 () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-QName.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 86; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 61; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-QName.java");
      for (int i = 1; i <= 86; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 61; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
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

