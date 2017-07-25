/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.*;

/**
 * Class Namespace
 *
 */
class Namespace extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.ping();
  }

    static final long serialVersionUID = -5765755238131301744L;
  static {
    CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[1]++;
  }

    private static final Object NAMESPACE_TAG = "Namespace";
  static {
    CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[2]++;
  }

    private Namespace prototype;
    private XmlNode.Namespace ns;

    private Namespace() {
    }

    static Namespace create(Scriptable scope, Namespace prototype, XmlNode.Namespace namespace) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[3]++;
        Namespace rv = new Namespace();
        rv.setParentScope(scope);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[4]++;
        rv.prototype = prototype;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[5]++;
        rv.setPrototype(prototype);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[6]++;
        rv.ns = namespace;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[7]++;
        return rv;
    }

    final XmlNode.Namespace getDelegate() {
        return ns;
    }

    public void exportAsJSClass(boolean sealed) {
        exportAsJSClass(MAX_PROTOTYPE_ID, this.getParentScope(), sealed);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[8]++;
    }

    public String uri() {
        return ns.getUri();
    }

    public String prefix() {
        return ns.getPrefix();
    }

    @Override
    public String toString() {
        return uri();
    }

    public String toLocaleString() {
        return toString();
    }

    private boolean equals(Namespace n) {
        return uri().equals(n.uri());
    }

    @Override
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[1]++; return false;
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[2]++;}
        return equals((Namespace)obj);
    }

    @Override
    public int hashCode() {
        return uri().hashCode();
    }

    @Override
    protected Object equivalentValues(Object value) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[3]++; return Scriptable.NOT_FOUND;
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[4]++;}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[11]++;
        boolean result = equals((Namespace)value);
        return result ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public String getClassName() {
        return "Namespace";
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        return uri();
    }

// #string_id_map#
    private static final int
        Id_prefix               = 1,
        Id_uri                  = 2,
        MAX_INSTANCE_ID         = 2;
  static {
    CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[12]++;
  }

    @Override
    protected int getMaxInstanceId()
    {
        return super.getMaxInstanceId() + MAX_INSTANCE_ID;
    }

    @Override
    protected int findInstanceIdInfo(String s)
    {
        int id;
// #generated# Last update: 2007-08-20 08:23:22 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[13]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[14]++; String X = null;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[15]++;
            int s_length = s.length();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((s_length==3) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[5]++; X="uri";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[17]++;id=Id_uri;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[18]++;
 }
            else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[6]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[19]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((s_length==6) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[7]++; X="prefix";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[20]++;id=Id_prefix;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[21]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[8]++;}
}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[9]++; id = 0;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[23]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[10]++;}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[24]++;
            break L0;
        }
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[11]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[12]++;}

        int attr;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[26]++;
        switch (id) {
          case Id_prefix:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[13]++;
          case Id_uri:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[14]++;
            attr = PERMANENT | READONLY;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[27]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[28]++;
            break;
          default:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[15]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }
// #/string_id_map#

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[29]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_prefix:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[16]++; return "prefix";
          case Id_uri:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[17]++; return "uri"; default : CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[18]++;
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[30]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_prefix:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[19]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ns.getPrefix() == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[20]++; return Undefined.instance;
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[21]++;}
            return ns.getPrefix();
          case Id_uri:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[22]++;
            return ns.getUri(); default : CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[23]++;
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
    CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[32]++;
  }

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-08-20 08:23:22 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[33]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[34]++; String X = null; int c;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[35]++;
            int s_length = s.length();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[24]++;
                c=s.charAt(3);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[37]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[26]++; X="toSource";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[39]++;id=Id_toSource;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[40]++;
 }
                else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[27]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[41]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[28]++; X="toString";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[42]++;id=Id_toString;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[43]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[29]++;}
}

            }
            else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[25]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[44]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[30]++; X="constructor";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[45]++;id=Id_constructor;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[46]++;
 } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[31]++;}
}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[32]++; id = 0;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[48]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[33]++;}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[49]++;
            break L0;
        }
// #/generated#
        return id;
    }
// #/string_id_map#

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[50]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[34]++; arity=2;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[51]++; s="constructor";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[52]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[53]++; break;
          case Id_toString:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[35]++;    arity=0;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[54]++; s="toString";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[55]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[56]++;    break;
          case Id_toSource:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[36]++;    arity=0;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[57]++; s="toSource";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[58]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[59]++;    break;
          default:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[37]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(NAMESPACE_TAG, id, s, arity);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[60]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f,
                             Context cx,
                             Scriptable scope,
                             Scriptable thisObj,
                             Object[] args)
    {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((f.hasTag(NAMESPACE_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[38]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[39]++;}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[62]++;
        int id = f.methodId();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[63]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[40]++;
            return jsConstructor(cx, (thisObj == null), args);
          case Id_toString:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[41]++;
            return realThis(thisObj, f).toString();
          case Id_toSource:
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[42]++;
            return realThis(thisObj, f).js_toSource(); default : CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[43]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private Namespace realThis(Scriptable thisObj, IdFunctionObject f) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
        if((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((thisObj instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[44]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[45]++;}
        return (Namespace)thisObj;
    }

    Namespace newNamespace(String uri) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[65]++;
        Namespace prototype = (this.prototype == null) ? this : this.prototype;
        return create( this.getParentScope(), prototype, XmlNode.Namespace.create(uri) );
    }

    Namespace newNamespace(String prefix, String uri) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[46]++; return newNamespace(uri);
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[47]++;}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[67]++;
        Namespace prototype = (this.prototype == null) ? this : this.prototype;
        return create( this.getParentScope(), prototype, XmlNode.Namespace.create(prefix, uri) );
    }

    Namespace constructNamespace(Object uriValue) {
        String prefix;
        String uri;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[68]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((uriValue instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[48]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[69]++;
            Namespace ns = (Namespace)uriValue;
            prefix = ns.prefix();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[70]++;
            uri = ns.uri();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[71]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[49]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[72]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((uriValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[50]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[73]++;
            QName qname = (QName)uriValue;
            uri = qname.uri();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[74]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[75]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((uri != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[52]++;
                //    TODO    Is there a way to push this back into QName so that we can make prefix() private?
                prefix = qname.prefix();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[76]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[53]++;
                uri = qname.toString();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[77]++;
                prefix = null;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[78]++;
            }

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[51]++;
            uri = ScriptRuntime.toString(uriValue);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[79]++;
            prefix = (uri.length() == 0) ? "" : null;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[80]++;
        }
}

        return newNamespace(prefix, uri);
    }

    Namespace castToNamespace(Object namespaceObj) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((namespaceObj instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[54]++;
            return (Namespace)namespaceObj;

        } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[55]++;}
        return constructNamespace(namespaceObj);
    }

    private Namespace constructNamespace(Object prefixValue, Object uriValue) {
        String prefix;
        String uri;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[82]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((uriValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[56]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[83]++;
            QName qname = (QName)uriValue;
            uri = qname.uri();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[84]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[85]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[58]++;
                uri = qname.toString();
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[86]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[59]++;}

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[57]++;
            uri = ScriptRuntime.toString(uriValue);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[87]++;
        }
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[88]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[60]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[89]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((prefixValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[62]++;
                prefix = "";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[90]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[63]++;
                prefix = ScriptRuntime.toString(prefixValue);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[91]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[92]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((prefix.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[64]++;
                    throw ScriptRuntime.typeError(
                        "Illegal prefix '"+prefix+"' for 'no namespace'.");

                } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[65]++;}
            }

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[61]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[93]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((prefixValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[66]++;
            prefix = "";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[94]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[67]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[95]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((XMLName.accept(prefixValue)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[68]++;
            prefix = "";
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[96]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[69]++;
            prefix = ScriptRuntime.toString(prefixValue);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[97]++;
        }
}
}

        return newNamespace(prefix, uri);
    }

    private Namespace constructNamespace() {
        return newNamespace("", "");
    }

    private Object jsConstructor(Context cx, boolean inNewExpr, Object[] args)
    {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[98]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[70]++;
            return castToNamespace(args[0]);

        } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[71]++;}
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[99]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[72]++;
            return constructNamespace();

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[73]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[100]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[74]++;
            return constructNamespace(args[0]);

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[75]++;
            return constructNamespace(args[0], args[1]);
        }
}
    }

    private String js_toSource()
    {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[101]++;
        StringBuffer sb = new StringBuffer();
        sb.append('(');
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[102]++;
        toSourceImpl(ns.getPrefix(), ns.getUri(), sb);
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[103]++;
        sb.append(')');
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[104]++;
        return sb.toString();
    }

    static void toSourceImpl(String prefix, String uri, StringBuffer sb)
    {
        sb.append("new Namespace(");
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[105]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[106]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[76]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[107]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 (("".equals(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[78]++; throw new IllegalArgumentException(prefix);
} else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[79]++;}

        } else {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[77]++;
            sb.append('\'');
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[108]++;
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[109]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((prefix != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[80]++;
                sb.append(ScriptRuntime.escapeString(prefix, '\''));
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[110]++;
                sb.append("', '");
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[111]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.branches[81]++;}
            sb.append(ScriptRuntime.escapeString(uri, '\''));
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[112]++;
            sb.append('\'');
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[113]++;
        }
        sb.append(')');
CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl.statements[114]++;
    }
}

class CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl ());
  }
    public static long[] statements = new long[115];
    public static long[] branches = new long[82];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[33];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-Namespace.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,3,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 32; i++) {
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

  public CodeCoverCoverageCounter$iypomt0ag8drh7a9xpf5oshrc8aazxdimcorl () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-Namespace.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 114; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 81; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-Namespace.java");
      for (int i = 1; i <= 114; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 81; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 32; i++) {
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

