/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.*;

/**
 * Class QName
 *
 */
final class QName extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.ping();
  }

    static final long serialVersionUID = 416745167693026750L;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[1]++;
  }

    private static final Object QNAME_TAG = "QName";
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[2]++;
  }

    private XMLLibImpl lib;

    private QName prototype;

    private XmlNode.QName delegate;

    private QName() {
    }

    static QName create(XMLLibImpl lib, Scriptable scope, QName prototype, XmlNode.QName delegate) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[3]++;
        QName rv = new QName();
        rv.lib = lib;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[4]++;
        rv.setParentScope(scope);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[5]++;
        rv.prototype = prototype;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[6]++;
        rv.setPrototype(prototype);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[7]++;
        rv.delegate = delegate;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[8]++;
        return rv;
    }

//    /** @deprecated */
//    static QName create(XMLLibImpl lib, XmlNode.QName nodeQname) {
//        return create(lib, lib.globalScope(), lib.qnamePrototype(), nodeQname);
//    }

    void exportAsJSClass(boolean sealed) {
        exportAsJSClass(MAX_PROTOTYPE_ID, getParentScope(), sealed);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[9]++;
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        //    ECMA357 13.3.4.2
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((delegate.getNamespace() == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[1]++;
            return "*::" + localName();

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[2]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[11]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((delegate.getNamespace().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[3]++;
            //    leave as empty
            return localName();

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[4]++;
            return uri() + "::" + localName();
        }
}
    }

    public String localName() {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((delegate.getLocalName() == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[5]++; return "*";
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[6]++;}
        return delegate.getLocalName();
    }

    /*
     * TODO This property is supposed to be invisible and I think we can
     *  make it private at some point, though Namespace might need it
     */
    String prefix() {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((delegate.getNamespace() == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[7]++; return null;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[8]++;}
        return delegate.getNamespace().getPrefix();
    }

    String uri() {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((delegate.getNamespace() == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[9]++; return null;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[10]++;}
        return delegate.getNamespace().getUri();
    }

    /** @deprecated */
    final XmlNode.QName toNodeQname() {
        return delegate;
    }

    final XmlNode.QName getDelegate() {
        return delegate;
    }

    @Override
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
        if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[11]++; return false;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[12]++;}
        return equals((QName)obj);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    protected Object equivalentValues(Object value)
    {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[16]++;
int CodeCoverConditionCoverageHelper_C7;
        if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[13]++; return Scriptable.NOT_FOUND;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[14]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[17]++;
        boolean result = equals((QName)value);
        return result ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean equals(QName q) {
        return this.delegate.equals(q.delegate);
    }

    @Override
    public String getClassName() {
        return "QName";
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        return toString();
    }

// #string_id_map#
    private static final int
        Id_localName            = 1,
        Id_uri                  = 2,
        MAX_INSTANCE_ID         = 2;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[18]++;
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
// #generated# Last update: 2007-08-20 08:21:41 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[19]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[20]++; String X = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[21]++;
            int s_length = s.length();
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s_length==3) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[15]++; X="uri";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[23]++;id=Id_uri;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[24]++;
 }
            else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[16]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[25]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s_length==9) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[17]++; X="localName";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[26]++;id=Id_localName;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[27]++;
 } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[18]++;}
}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[28]++;
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
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[19]++; id = 0;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[29]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[20]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[30]++;
            break L0;
        }
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[21]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[22]++;}

        int attr;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[32]++;
        switch (id) {
          case Id_localName:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[23]++;
          case Id_uri:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[24]++;
            attr = PERMANENT | READONLY;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[33]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[34]++;
            break;
          default:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[25]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }
// #/string_id_map#

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[35]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_localName:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[26]++; return "localName";
          case Id_uri:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[27]++; return "uri"; default : CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[28]++;
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[36]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_localName:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[29]++; return localName();
          case Id_uri:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[30]++; return uri(); default : CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[31]++;
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
    CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[37]++;
  }

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-08-20 08:21:41 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[38]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[39]++; String X = null; int c;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[40]++;
            int s_length = s.length();
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[32]++;
                c=s.charAt(3);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[42]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[34]++; X="toSource";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[44]++;id=Id_toSource;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[45]++;
 }
                else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[35]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[46]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[36]++; X="toString";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[47]++;id=Id_toString;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[48]++;
 } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[37]++;}
}

            }
            else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[33]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[49]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[38]++; X="constructor";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[50]++;id=Id_constructor;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[51]++;
 } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[39]++;}
}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[40]++; id = 0;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[53]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[41]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[54]++;
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
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[55]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[42]++; arity=2;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[56]++; s="constructor";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[57]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[58]++; break;
          case Id_toString:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[43]++;    arity=0;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[59]++; s="toString";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[60]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[61]++;    break;
          case Id_toSource:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[44]++;    arity=0;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[62]++; s="toSource";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[63]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[64]++;    break;
          default:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[45]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(QNAME_TAG, id, s, arity);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[65]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f,
                             Context cx,
                             Scriptable scope,
                             Scriptable thisObj,
                             Object[] args)
    {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((f.hasTag(QNAME_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[46]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[47]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[67]++;
        int id = f.methodId();
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[68]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[48]++;
            return jsConstructor(cx, (thisObj == null), args);
          case Id_toString:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[49]++;
            return realThis(thisObj, f).toString();
          case Id_toSource:
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[50]++;
            return realThis(thisObj, f).js_toSource(); default : CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[51]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private QName realThis(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;
        if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((thisObj instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[52]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[53]++;}
        return (QName)thisObj;
    }

    QName newQName(XMLLibImpl lib, String q_uri, String q_localName, String q_prefix) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[70]++;
        QName prototype = this.prototype;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((prototype == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[54]++;
            prototype = this;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[72]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[55]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[73]++;
        XmlNode.Namespace ns = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[74]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((q_prefix != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[56]++;
            ns = XmlNode.Namespace.create(q_prefix, q_uri);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[75]++;

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[57]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[76]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((q_uri != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[58]++;
            ns = XmlNode.Namespace.create(q_uri);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[77]++;

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[59]++;
            ns = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[78]++;
        }
}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[79]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((q_localName != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((q_localName.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[60]++; q_localName = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[80]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[61]++;}
        return create(lib, this.getParentScope(), prototype, XmlNode.QName.create(ns, q_localName));
    }

    //    See ECMA357 13.3.2
    QName constructQName(XMLLibImpl lib, Context cx, Object namespace, Object name) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[81]++;
        String nameString = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[82]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((name instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[62]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[83]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((namespace == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[64]++;
                return (QName)name;

            } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[65]++;
                nameString = ((QName)name).localName();
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[84]++;
            }

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[63]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[85]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((name == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[66]++;
            nameString = "";
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[86]++;

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[67]++;
            nameString = ScriptRuntime.toString(name);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[87]++;
        }
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[88]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((namespace == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[68]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[89]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 (("*".equals(nameString)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[70]++;
                namespace = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[90]++;

            } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[71]++;
                namespace = lib.getDefaultNamespace(cx);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[91]++;
            }

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[69]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[92]++;
        Namespace namespaceNamespace = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[93]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[72]++;

            //    leave as null
        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[73]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[94]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((namespace instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[74]++;
            namespaceNamespace = (Namespace)namespace;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[95]++;

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[75]++;
            namespaceNamespace = lib.newNamespace(ScriptRuntime.toString(namespace));
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[96]++;
        }
}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[97]++;
        String q_localName = nameString;
        String q_uri;
        String q_prefix;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[98]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[76]++;
            q_uri = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[99]++;
            q_prefix = null;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[100]++;
    //    corresponds to undefined; see QName class
        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[77]++;
            q_uri = namespaceNamespace.uri();
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[101]++;
            q_prefix = namespaceNamespace.prefix();
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[102]++;
        }
        return newQName(lib, q_uri, q_localName, q_prefix);
    }

    QName constructQName(XMLLibImpl lib, Context cx, Object nameValue) {
        return constructQName(lib, cx, Undefined.instance, nameValue);
    }

    QName castToQName(XMLLibImpl lib, Context cx, Object qnameValue) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[103]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((qnameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[78]++;
            return (QName)qnameValue;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[79]++;}
        return constructQName(lib, cx, qnameValue);
    }

    private Object jsConstructor(Context cx, boolean inNewExpr, Object[] args) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[104]++;
int CodeCoverConditionCoverageHelper_C32;
        //    See ECMA357 13.3.2
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[80]++;
            return castToQName(lib, cx, args[0]);

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[81]++;}
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[105]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[82]++;
            return constructQName(lib, cx, Undefined.instance);

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[83]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[106]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[84]++;
            return constructQName(lib, cx, args[0]);

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[85]++;
            return constructQName(lib, cx, args[0], args[1]);
        }
}
    }

    private String js_toSource() {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[107]++;
        StringBuffer sb = new StringBuffer();
        sb.append('(');
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[108]++;
        toSourceImpl(uri(), localName(), prefix(), sb);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[109]++;
        sb.append(')');
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[110]++;
        return sb.toString();
    }

    private static void toSourceImpl(String uri, String localName, String prefix, StringBuffer sb) {
        sb.append("new QName(");
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[111]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[112]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[86]++;
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[113]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 (("*".equals(localName)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[88]++;
                sb.append("null, ");
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[114]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[89]++;}

        } else {
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.branches[87]++;
            Namespace.toSourceImpl(prefix, uri, sb);
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[115]++;
            sb.append(", ");
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[116]++;
        }
        sb.append('\'');
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[117]++;
        sb.append(ScriptRuntime.escapeString(localName, '\''));
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[118]++;
        sb.append("')");
CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep.statements[119]++;
    }
}

class CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep ());
  }
    public static long[] statements = new long[120];
    public static long[] branches = new long[90];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[37];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-QName.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,2,1};
    for (int i = 1; i <= 36; i++) {
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

  public CodeCoverCoverageCounter$9m0i2o1yzvtkq7ctrua5tata5lyglep () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-QName.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 119; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 89; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 36; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-QName.java");
      for (int i = 1; i <= 119; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 89; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 36; i++) {
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

