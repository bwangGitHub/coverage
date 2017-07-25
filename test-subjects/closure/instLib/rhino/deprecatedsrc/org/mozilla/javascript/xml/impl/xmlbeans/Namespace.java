/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import org.mozilla.javascript.*;

/**
 * Class Namespace
 *
 */
class Namespace extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.ping();
  }

    static final long serialVersionUID = -5765755238131301744L;
  static {
    CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[1]++;
  }

    private static final Object NAMESPACE_TAG = "Namespace";
  static {
    CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[2]++;
  }

    private XMLLibImpl lib;
    private String prefix;
    private String uri;

    public Namespace(XMLLibImpl lib, String uri)
    {
        super(lib.globalScope(), lib.namespacePrototype);
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[3]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[1]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[2]++;}

        this.lib = lib;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[5]++;
        this.prefix = (uri.length() == 0) ? "" : null;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[6]++;
        this.uri = uri;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[7]++;
    }


    public Namespace(XMLLibImpl lib, String prefix, String uri)
    {
        super(lib.globalScope(), lib.namespacePrototype);
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[8]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[3]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[4]++;}
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[5]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
            // prefix should be "" for empty uri
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[7]++;
                throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[8]++;}
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((prefix.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[9]++;
                throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[6]++;}

        this.lib = lib;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[13]++;
        this.prefix = prefix;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[14]++;
        this.uri = uri;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[15]++;
    }

    public void exportAsJSClass(boolean sealed)
    {
        exportAsJSClass(MAX_PROTOTYPE_ID, lib.globalScope(), sealed);
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[16]++;
    }

    /**
     *
     * @return
     */
    public String uri()
    {
        return uri;
    }

    /**
     *
     * @return
     */
    public String prefix()
    {
        return prefix;
    }

    /**
     *
     * @return
     */
    public String toString ()
    {
        return uri();
    }

    /**
     *
     * @return
     */
    public String toLocaleString ()
    {
        return toString();
    }

    public boolean equals(Object obj)
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[11]++; return false;
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[12]++;}
        return equals((Namespace)obj);
    }

    public int hashCode()
    {
        return uri().hashCode();
    }

    protected Object equivalentValues(Object value)
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[13]++; return Scriptable.NOT_FOUND;
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[14]++;}
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[19]++;
        boolean result = equals((Namespace)value);
        return result ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean equals(Namespace n)
    {
        return uri().equals(n.uri());
    }

    /**
     *
     * @return
     */
    public String getClassName ()
    {
        return "Namespace";
    }

    /**
     *
     * @param hint
     * @return
     */
    public Object getDefaultValue (Class hint)
    {
        return uri();
    }

// #string_id_map#
    private static final int
        Id_prefix               = 1,
        Id_uri                  = 2,
        MAX_INSTANCE_ID         = 2;
  static {
    CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[20]++;
  }

    protected int getMaxInstanceId()
    {
        return super.getMaxInstanceId() + MAX_INSTANCE_ID;
    }

    protected int findInstanceIdInfo(String s)
    {
        int id;
// #generated# Last update: 2004-07-20 19:50:50 CEST
        L0: { id = 0;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[21]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[22]++; String X = null;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[23]++;
            int s_length = s.length();
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s_length==3) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[15]++; X="uri";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[25]++;id=Id_uri;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[26]++;
 }
            else {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[16]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[27]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s_length==6) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[17]++; X="prefix";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[28]++;id=Id_prefix;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[29]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[18]++;}
}
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[19]++; id = 0;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[31]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[20]++;}
        }
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[21]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[22]++;}

        int attr;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[33]++;
        switch (id) {
          case Id_prefix:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[23]++;
          case Id_uri:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[24]++;
            attr = PERMANENT | READONLY;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[34]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[35]++;
            break;
          default:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[25]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }
// #/string_id_map#

    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[36]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_prefix:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[26]++; return "prefix";
          case Id_uri:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[27]++; return "uri"; default : CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[28]++;
        }
        return super.getInstanceIdName(id);
    }

    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[37]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_prefix:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[29]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[30]++; return Undefined.instance;
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[31]++;}
            return prefix;
          case Id_uri:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[32]++;
            return uri; default : CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[33]++;
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
    CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[39]++;
  }

    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2004-08-21 12:07:01 CEST
        L0: { id = 0;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[40]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[41]++; String X = null; int c;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[42]++;
            int s_length = s.length();
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[34]++;
                c=s.charAt(3);
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[44]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[45]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[36]++; X="toSource";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[46]++;id=Id_toSource;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[47]++;
 }
                else {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[37]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[48]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[38]++; X="toString";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[49]++;id=Id_toString;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[50]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[39]++;}
}

            }
            else {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[35]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[51]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[40]++; X="constructor";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[52]++;id=Id_constructor;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[53]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[41]++;}
}
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[54]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[42]++; id = 0;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[55]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[43]++;}
        }
// #/generated#
        return id;
    }
// #/string_id_map#

    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[56]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[44]++; arity=2;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[57]++; s="constructor";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[58]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[59]++; break;
          case Id_toString:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[45]++;    arity=0;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[60]++; s="toString";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[61]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[62]++;    break;
          case Id_toSource:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[46]++;    arity=0;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[63]++; s="toSource";
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[64]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[65]++;    break;
          default:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[47]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(NAMESPACE_TAG, id, s, arity);
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[66]++;
    }

    public Object execIdCall(IdFunctionObject f,
                             Context cx,
                             Scriptable scope,
                             Scriptable thisObj,
                             Object[] args)
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((f.hasTag(NAMESPACE_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[48]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[49]++;}
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[68]++;
        int id = f.methodId();
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[69]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[50]++;
            return jsConstructor(cx, (thisObj == null), args);
          case Id_toString:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[51]++;
            return realThis(thisObj, f).toString();
          case Id_toSource:
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[52]++;
            return realThis(thisObj, f).js_toSource(); default : CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[53]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private Namespace realThis(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[70]++;
int CodeCoverConditionCoverageHelper_C19;
        if((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((thisObj instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[54]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[55]++;}
        return (Namespace)thisObj;
    }

    private Object jsConstructor(Context cx, boolean inNewExpr, Object[] args)
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[71]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[56]++;
            return lib.castToNamespace(cx, args[0]);

        } else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[57]++;}
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[58]++;
            return lib.constructNamespace(cx);

        } else {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[59]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[73]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[60]++;
            return lib.constructNamespace(cx, args[0]);

        } else {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[61]++;
            return lib.constructNamespace(cx, args[0], args[1]);
        }
}
    }

    private String js_toSource()
    {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[74]++;
        StringBuffer sb = new StringBuffer();
        sb.append('(');
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[75]++;
        toSourceImpl(prefix, uri, sb);
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[76]++;
        sb.append(')');
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[77]++;
        return sb.toString();
    }

    static void toSourceImpl(String prefix, String uri, StringBuffer sb)
    {
        sb.append("new Namespace(");
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[78]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[79]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[62]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[80]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 (("".equals(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[64]++; throw new IllegalArgumentException(prefix);
} else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[65]++;}

        } else {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[63]++;
            sb.append('\'');
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[81]++;
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[82]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((prefix != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[66]++;
                sb.append(ScriptRuntime.escapeString(prefix, '\''));
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[83]++;
                sb.append("', '");
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[84]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.branches[67]++;}
            sb.append(ScriptRuntime.escapeString(uri, '\''));
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[85]++;
            sb.append('\'');
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[86]++;
        }
        sb.append(')');
CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x.statements[87]++;
    }
}

class CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[68];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-Namespace.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,3,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$iypomt0ag6psme9py8ldfh74rw3bo1nunxu8x () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-Namespace.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 67; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-Namespace.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 67; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

