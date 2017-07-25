/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.XMLObject;

class XML extends XMLObjectImpl {
  static {
    CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.ping();
  }

    static final long serialVersionUID = -630969919086449092L;
  static {
    CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[1]++;
  }

    private XmlNode node;

    XML(XMLLibImpl lib, Scriptable scope, XMLObject prototype, XmlNode node) {
      super(lib, scope, prototype);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[2]++;
      initialize(node);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[3]++;
    }

    void initialize(XmlNode node) {
        this.node = node;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[4]++;
        this.node.setXml(this);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[5]++;
    }

    @Override
    final XML getXML() {
        return this;
    }

    void replaceWith(XML value) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        //    We use the underlying document structure if the node is not
        //    "standalone," but we need to just replace the XmlNode instance
        //    otherwise
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.node.parent() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
 || false)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[1]++;
            this.node.replaceWith(value.node);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[7]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[2]++;
            this.initialize(value.node);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[8]++;
        }
    }

    /* TODO: needs encapsulation. */
    XML makeXmlFromString(XMLName name, String value) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[9]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return newTextElementXML(this.node, name.toQname(), value);
        } catch(Exception e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[4]++;
            throw ScriptRuntime.typeError(e.getMessage());
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[3]++;
}
  }
    }

    /* TODO: Rename this, at the very least.  But it's not clear it's even necessary */
    XmlNode getAnnotation() {
        return node;
    }

    //
    //  Methods from ScriptableObject
    //

    //    TODO Either cross-reference this next comment with the specification or delete it and change the behavior
    //    The comment: XML[0] should return this, all other indexes are Undefined
    @Override
    public Object get(int index, Scriptable start) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[5]++;
            return this;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[6]++;
            return Scriptable.NOT_FOUND;
        }
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return (index == 0);
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        //    TODO    Clarify the following comment and add a reference to the spec
        //    The comment: Spec says assignment to indexed XML object should return type error
        throw ScriptRuntime.typeError("Assignment to indexed XML is not allowed");
    }

    @Override
    public Object[] getIds() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[7]++;
            return new Object[0];

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[8]++;
            return new Object[] { Integer.valueOf(0) };
        }
    }

    //    TODO    This is how I found it but I am not sure it makes sense
    @Override
    public void delete(int index) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[9]++;
            this.remove();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[13]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[10]++;}
    }

    //
    //    Methods from XMLObjectImpl
    //

    @Override
    boolean hasXMLProperty(XMLName xmlName) {
        return (getPropertyList(xmlName).length() > 0);
    }

    @Override
    Object getXMLProperty(XMLName xmlName) {
        return getPropertyList(xmlName);
    }

    //
    //
    //    Methods that merit further review
    //
    //

    XmlNode.QName getNodeQname() {
        return this.node.getQname();
    }

    XML[] getChildren() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isElement()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[11]++; return null;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[12]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[15]++;
        XmlNode[] children = this.node.getMatchingChildren(XmlNode.Filter.TRUE);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[16]++;
        XML[] rv = new XML[children.length];
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[17]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i<rv.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[1]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[2]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[3]++;
}
            rv[i] = toXML(children[i]);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[18]++;
        }
        return rv;
    }

    XML[] getAttributes() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[19]++;
        XmlNode[] attributes = this.node.getAttributes();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[20]++;
        XML[] rv = new XML[attributes.length];
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i<rv.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[4]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[5]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[6]++;
}
            rv[i] = toXML(attributes[i]);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[22]++;
        }
        return rv;
    }

    //    Used only by XML, XMLList
    XMLList getPropertyList(XMLName name) {
        return name.getMyValueOn(this);
    }

    @Override
    void deleteXMLProperty(XMLName name) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[23]++;
        XMLList list = getPropertyList(name);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[24]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i<list.length()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[7]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[8]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[9]++;
}
            list.item(i).node.deleteMe();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[25]++;
        }
    }

    @Override
    void putXMLProperty(XMLName xmlName, Object value) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[13]++;

            //    TODO    Is this really a no-op?  Check the spec to be sure
        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[14]++;
            xmlName.setMyValueOn(this, value);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[27]++;
        }
    }

    @Override
    boolean hasOwnProperty(XMLName xmlName) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[28]++;
        boolean hasProperty = false;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[15]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[30]++;
            String property = xmlName.localName();
            hasProperty = (0 != findPrototypeId(property));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[31]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[16]++;
            hasProperty = (getPropertyList(xmlName).length() > 0);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[32]++;
        }

        return hasProperty;
    }

    @Override
    protected Object jsConstructor(Context cx, boolean inNewExpr, Object[] args) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((args[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((args[0] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[17]++;
            args = new Object[] { "" };
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[34]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[18]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[35]++;
        //    ECMA 13.4.2 does not appear to specify what to do if multiple arguments are sent.
        XML toXml = ecmaToXml(args[0]);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[36]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[19]++;
            return toXml.copy();

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[20]++;
            return toXml;
        }
    }

    //    See ECMA 357, 11_2_2_1, Semantics, 3_f.
    @Override
    public Scriptable getExtraMethodSource(Context cx) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[21]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[38]++;
            String src = toString();
            return ScriptRuntime.toObjectOrNull(cx, src);

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[22]++;}
        return null;
    }

    //
    //    TODO    Miscellaneous methods not yet grouped
    //

    void removeChild(int index) {
        this.node.removeChild(index);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[39]++;
    }

    @Override
    void normalize() {
        this.node.normalize();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[40]++;
    }

    private XML toXML(XmlNode node) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[41]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((node.getXml() == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[23]++;
            node.setXml(newXML(node));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[42]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[24]++;}
        return node.getXml();
    }

    void setAttribute(XMLName xmlName, Object value) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[43]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isElement()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[25]++; throw new IllegalStateException("Can only set attributes on elements.");
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[26]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[44]++;
int CodeCoverConditionCoverageHelper_C16;
        //    TODO    Is this legal, but just not "supported"?  If so, support it.
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((xmlName.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((xmlName.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[27]++;
            throw ScriptRuntime.typeError("@* assignment not supported.");

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[28]++;}
        this.node.setAttribute(xmlName.toQname(), ScriptRuntime.toString(value));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[45]++;
    }

    void remove() {
        this.node.deleteMe();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[46]++;
    }

    @Override
    void addMatches(XMLList rv, XMLName name) {
        name.addMatches(rv, this);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[47]++;
    }

    @Override
    XMLList elements(XMLName name) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[48]++;
        XMLList rv = newXMLList();
        rv.setTargets(this, name.toQname());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[49]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[50]++;
        //    TODO    Should have an XMLNode.Filter implementation based on XMLName
        XmlNode[] elements = this.node.getMatchingChildren(XmlNode.Filter.ELEMENT);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[51]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[10]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i<elements.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[10]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[11]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[12]++;
}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[52]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((name.matches( toXML(elements[i]) )) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[29]++;
                rv.addToList( toXML(elements[i]) );
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[53]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[30]++;}
        }
        return rv;
    }

    @Override
    XMLList child(XMLName xmlName) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[54]++;
        //    TODO    Right now I think this method would allow child( "@xxx" ) to return the xxx attribute, which is wrong

        XMLList rv = newXMLList();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[55]++;

        //    TODO    Should this also match processing instructions?  If so, we have to change the filter and also the XMLName
        //            class to add an acceptsProcessingInstruction() method

        XmlNode[] elements = this.node.getMatchingChildren(XmlNode.Filter.ELEMENT);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[56]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i<elements.length) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[13]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[14]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[15]++;
}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[57]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((xmlName.matchesElement(elements[i].getQname())) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[31]++;
                rv.addToList( toXML(elements[i]) );
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[58]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[32]++;}
        }
        rv.setTargets(this, xmlName.toQname());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[59]++;
        return rv;
    }

    XML replace(XMLName xmlName, Object xml) {
        putXMLProperty(xmlName, xml);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[60]++;
        return this;
    }

    @Override
    XMLList children() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[61]++;
        XMLList rv = newXMLList();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[62]++;
        XMLName all = XMLName.formStar();
        rv.setTargets(this, all.toQname());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[63]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[64]++;
        XmlNode[] children = this.node.getMatchingChildren(XmlNode.Filter.TRUE);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[65]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[16]++;


int CodeCoverConditionCoverageHelper_C21;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i<children.length) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[16]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[17]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[18]++;
}
            rv.addToList( toXML(children[i]) );
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[66]++;
        }
        return rv;
    }

    @Override
    XMLList child(int index) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[67]++;
        //    ECMA357 13.4.4.6 (numeric case)
        XMLList result = newXMLList();
        result.setTargets(this, null);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[68]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[69]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((index < this.node.getChildCount()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[33]++;
            result.addToList(getXmlChild(index));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[70]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[34]++;}
        return result;
    }

    XML getXmlChild(int index) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[71]++;
        XmlNode child = this.node.getChild(index);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[72]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((child.getXml() == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[35]++;
            child.setXml(newXML(child));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[73]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[36]++;}
        return child.getXml();
    }

    /* Return the last added element */
    XML getLastXmlChild() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[74]++;
        int pos = this.node.getChildCount() - 1;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[75]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((pos < 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[37]++; return null;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[38]++;}
        return getXmlChild(pos);
    }

    int childIndex() {
        return this.node.getChildIndex();
    }

    @Override
    boolean contains(Object xml) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[76]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((xml instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[39]++;
            return equivalentXml(xml);

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[40]++;
            return false;
        }
    }

    //    Method overriding XMLObjectImpl
    @Override
    boolean equivalentXml(Object target) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[77]++;
        boolean result = false;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[78]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((target instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[41]++;
            //    TODO    This is a horrifyingly inefficient way to do this so we should make it better.  It may also not work.
            return this.node.toXmlString(getProcessor()).equals( ((XML)target).node.toXmlString(getProcessor()) );

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[42]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[79]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((target instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[43]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[80]++;
            //    TODO    Is this right?  Check the spec ...
            XMLList otherList = (XMLList) target;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[81]++;
int CodeCoverConditionCoverageHelper_C28;

            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((otherList.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[45]++;
                result = equivalentXml(otherList.getXML());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[82]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[46]++;}

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[44]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[83]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[47]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[84]++;
            String otherStr = ScriptRuntime.toString(target);

            result = toString().equals(otherStr);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[85]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[48]++;}
}
}

        return result;
    }

    @Override
    XMLObjectImpl copy() {
        return newXML( this.node.copy() );
    }

    @Override
    boolean hasSimpleContent() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[86]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((isComment()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isProcessingInstruction()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[49]++; return false;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[50]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[87]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((isText()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.node.isAttributeType()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[51]++; return true;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[52]++;}
        return !this.node.hasChildElement();
    }

    @Override
    boolean hasComplexContent() {
        return !hasSimpleContent();
    }

    //    TODO Cross-reference comment below with spec
    //    Comment is: Length of an XML object is always 1, it's a list of XML objects of size 1.
    @Override
    int length() {
        return 1;
    }

    //    TODO    it is not clear what this method was for ...
    boolean is(XML other) {
        return this.node.isSameNode(other.node);
    }

    Object nodeKind() {
        return ecmaClass();
    }

    @Override
    Object parent() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[88]++;
        XmlNode parent = this.node.parent();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[89]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[53]++; return null;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[54]++;}
        return newXML(this.node.parent());
    }

    @Override
    boolean propertyIsEnumerable(Object name)
    {
        boolean result;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[90]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((name instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[55]++;
            result = (((Integer)name).intValue() == 0);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[91]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[56]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[92]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((name instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[57]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[93]++;
            double x = ((Number)name).doubleValue();
            // Check that number is positive 0
            result = (x == 0.0 && 1.0 / x > 0);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[94]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[58]++;
            result = ScriptRuntime.toString(name).equals("0");
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[95]++;
        }
}
        return result;
    }

    @Override
    Object valueOf() {
        return this;
    }

    //
    //    Selection of children
    //

    @Override
    XMLList comments() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[96]++;
        XMLList rv = newXMLList();
        this.node.addMatchingChildren(rv, XmlNode.Filter.COMMENT);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[97]++;
        return rv;
    }

    @Override
    XMLList text() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[98]++;
        XMLList rv = newXMLList();
        this.node.addMatchingChildren(rv, XmlNode.Filter.TEXT);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[99]++;
        return rv;
    }

    @Override
    XMLList processingInstructions(XMLName xmlName) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[100]++;
        XMLList rv = newXMLList();
        this.node.addMatchingChildren(rv, XmlNode.Filter.PROCESSING_INSTRUCTION(xmlName));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[101]++;
        return rv;
    }

    //
    //    Methods relating to modification of child nodes
    //

    //    We create all the nodes we are inserting before doing the insert to
    //    avoid nasty cycles caused by mutability of these objects.  For example,
    //    what if the toString() method of value modifies the XML object we were
    //    going to insert into?  insertAfter might get confused about where to
    //    insert.  This actually came up with SpiderMonkey, leading to a (very)
    //    long discussion.  See bug #354145.
    private XmlNode[] getNodesForInsert(Object value) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[102]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((value instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[59]++;
            return new XmlNode[] { ((XML)value).node };

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[60]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[103]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((value instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[61]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[104]++;
            XMLList list = (XMLList)value;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[105]++;
            XmlNode[] rv = new XmlNode[list.length()];
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[106]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[19]++;


int CodeCoverConditionCoverageHelper_C37;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((i<list.length()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[19]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[20]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[21]++;
}
                rv[i] = list.item(i).node;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[107]++;
            }
            return rv;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[62]++;
            return new XmlNode[] {
                XmlNode.createText(getProcessor(), ScriptRuntime.toString(value))
            };
        }
}
    }

    XML replace(int index, Object xml) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[108]++;
        XMLList xlChildToReplace = child(index);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[109]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((xlChildToReplace.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[63]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[110]++;
            // One exists an that index
            XML childToReplace = xlChildToReplace.item(0);
            insertChildAfter(childToReplace, xml);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[111]++;
            removeChild(index);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[112]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[64]++;}
        return this;
    }

    XML prependChild(Object xml) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[113]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.node.isParentType()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[65]++;
            this.node.insertChildrenAt(0, getNodesForInsert(xml));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[114]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[66]++;}
        return this;
    }

    XML appendChild(Object xml) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[115]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.node.isParentType()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[67]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[116]++;
            XmlNode[] nodes = getNodesForInsert(xml);
            this.node.insertChildrenAt(this.node.getChildCount(), nodes);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[117]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[68]++;}
        return this;
    }

    private int getChildIndexOf(XML child) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[118]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[22]++;


int CodeCoverConditionCoverageHelper_C41;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i<this.node.getChildCount()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[22]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[23]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[24]++;
}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[119]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.node.getChild(i).isSameNode(child.node)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[69]++;
                return i;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[70]++;}
        }
        return -1;
    }

    XML insertChildBefore(XML child, Object xml) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[120]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((child == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[71]++;
            // Spec says inserting before nothing is the same as appending
            appendChild(xml);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[121]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[72]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[122]++;
            XmlNode[] toInsert = getNodesForInsert(xml);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[123]++;
            int index = getChildIndexOf(child);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[124]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((index != -1) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[73]++;
                this.node.insertChildrenAt(index, toInsert);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[125]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[74]++;}
        }

        return this;
    }

    XML insertChildAfter(XML child, Object xml) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[126]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((child == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[75]++;
            // Spec says inserting after nothing is the same as prepending
            prependChild(xml);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[127]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[76]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[128]++;
            XmlNode[] toInsert = getNodesForInsert(xml);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[129]++;
            int index = getChildIndexOf(child);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[130]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((index != -1) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[77]++;
                this.node.insertChildrenAt(index+1, toInsert);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[131]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[78]++;}
        }

        return this;
    }

    XML setChildren(Object xml) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[132]++;
int CodeCoverConditionCoverageHelper_C47;
        //    TODO    Have not carefully considered the spec but it seems to call for this
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((isElement()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[79]++; return this;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[80]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[133]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[25]++;


int CodeCoverConditionCoverageHelper_C48;

        while((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((this.node.getChildCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[25]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[26]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[27]++;
}
            this.node.removeChild(0);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[134]++;
        }
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[135]++;
        XmlNode[] toInsert = getNodesForInsert(xml);
        // append new children
        this.node.insertChildrenAt(0, toInsert);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[136]++;

        return this;
    }

    //
    //    Name and namespace-related methods
    //

    private void addInScopeNamespace(Namespace ns) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[137]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((isElement()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[81]++;
            return;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[82]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[138]++;
int CodeCoverConditionCoverageHelper_C50;
        //    See ECMA357 9.1.1.13
        //    in this implementation null prefix means ECMA undefined
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ns.prefix() != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[83]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[139]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((ns.prefix().length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((ns.uri().length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[85]++;
                return;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[86]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[140]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((node.getQname().getNamespace().getPrefix().equals(ns.prefix())) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[87]++;
                node.invalidateNamespacePrefix();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[141]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[88]++;}
            node.declareNamespace(ns.prefix(), ns.uri());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[142]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[84]++;
            return;
        }
    }

    Namespace[] inScopeNamespaces() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[143]++;
        XmlNode.Namespace[] inScope = this.node.getInScopeNamespaces();
        return createNamespaces(inScope);
    }

    private XmlNode.Namespace adapt(Namespace ns) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[144]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((ns.prefix() == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[89]++;
            return XmlNode.Namespace.create(ns.uri());

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[90]++;
            return XmlNode.Namespace.create(ns.prefix(), ns.uri());
        }
    }

    XML removeNamespace(Namespace ns) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[145]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((isElement()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[91]++; return this;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[92]++;}
        this.node.removeNamespace(adapt(ns));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[146]++;
        return this;
    }

    XML addNamespace(Namespace ns) {
        addInScopeNamespace(ns);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[147]++;
        return this;
    }

    QName name() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[148]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((isText()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((isComment()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[93]++; return null;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[94]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[149]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((isProcessingInstruction()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[95]++; return newQName("", this.node.getQname().getLocalName(), null);
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[96]++;}
        return newQName(node.getQname());
    }

    Namespace[] namespaceDeclarations() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[150]++;
        XmlNode.Namespace[] declarations = node.getNamespaceDeclarations();
        return createNamespaces(declarations);
    }

    Namespace namespace(String prefix) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[151]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[97]++;
            return createNamespace( this.node.getNamespaceDeclaration() );

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[98]++;
            return createNamespace( this.node.getNamespaceDeclaration(prefix) );
        }
    }

    String localName() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[152]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((name() == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[99]++; return null;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[100]++;}
        return name().localName();
    }

    void setLocalName(String localName) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[153]++;
int CodeCoverConditionCoverageHelper_C59;
        //    ECMA357 13.4.4.34
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((isText()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((isComment()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[101]++; return;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[102]++;}
        this.node.setLocalName(localName);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[154]++;
    }

    void setName(QName name) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[155]++;
int CodeCoverConditionCoverageHelper_C60;
        //    See ECMA357 13.4.4.35
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((isText()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((isComment()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[103]++; return;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[104]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[156]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((isProcessingInstruction()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[105]++;
            //    Spec says set the name URI to empty string and then set the [[Name]] property, but I understand this to do the same
            //    thing, unless we allow colons in processing instruction targets, which I think we do not.
            this.node.setLocalName(name.localName());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[157]++;
            return;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[106]++;}
        node.renameNode(name.getDelegate());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[158]++;
    }

    void setNamespace(Namespace ns) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[159]++;
int CodeCoverConditionCoverageHelper_C62;
        //    See ECMA357 13.4.4.36
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (32)) == 0 || true) &&
 ((isText()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((isComment()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((isProcessingInstruction()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[107]++; return;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[108]++;}
        setName(newQName(ns.uri(), localName(), ns.prefix()));
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[160]++;
    }

    final String ecmaClass() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[161]++;
int CodeCoverConditionCoverageHelper_C63;
        //    See ECMA357 9.1

        //    TODO    See ECMA357 9.1.1 last paragraph for what defaults should be

        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((node.isTextType()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[109]++;
            return "text";

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[110]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[162]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((node.isAttributeType()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[111]++;
            return "attribute";

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[112]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[163]++;
int CodeCoverConditionCoverageHelper_C65; if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((node.isCommentType()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[113]++;
            return "comment";

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[114]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[164]++;
int CodeCoverConditionCoverageHelper_C66; if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((node.isProcessingInstructionType()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[115]++;
            return "processing-instruction";

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[116]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[165]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((node.isElementType()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[117]++;
            return "element";

        } else {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[118]++;
            throw new RuntimeException("Unrecognized type: " + node);
        }
}
}
}
}
    }

    @Override
    public String getClassName() {
        //    TODO:    This appears to confuse the interpreter if we use the "real" class property from ECMA.  Otherwise this code
        //    would be:
        //    return ecmaClass();
        return "XML";
    }

    private String ecmaValue() {
        return node.ecmaValue();
    }

    private String ecmaToString() {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[166]++;
int CodeCoverConditionCoverageHelper_C68;
        //    See ECMA357 10.1.1
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((isAttribute()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((isText()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[119]++;
            return ecmaValue();

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[120]++;}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[167]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[121]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[168]++;
            StringBuffer rv = new StringBuffer();
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[169]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[28]++;


int CodeCoverConditionCoverageHelper_C70;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((i < this.node.getChildCount()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[28]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[29]--;
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.loops[30]++;
}
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[170]++;
                XmlNode child = this.node.getChild(i);
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[171]++;
int CodeCoverConditionCoverageHelper_C71;
                if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((child.isProcessingInstructionType()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((child.isCommentType()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[123]++;
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[172]++;
                    // TODO: Probably inefficient; taking clean non-optimized
                    // solution for now
                    XML x = new XML(getLib(), getParentScope(),
                                    (XMLObject)getPrototype(), child);
                    rv.append(x.toString());
CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.statements[173]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[124]++;}
            }
            return rv.toString();

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd.branches[122]++;}
        return toXMLString();
    }

    @Override
    public String toString() {
        return ecmaToString();
    }

    @Override
    String toSource(int indent) {
        return toXMLString();
    }

    @Override
    String toXMLString() {
        return this.node.ecmaToXMLString(getProcessor());
    }

    final boolean isAttribute() {
        return node.isAttributeType();
    }

    final boolean isComment() {
        return node.isCommentType();
    }

    final boolean isText() {
        return node.isTextType();
    }

    final boolean isElement() {
        return node.isElementType();
    }

    final boolean isProcessingInstruction() {
        return node.isProcessingInstructionType();
    }

    //    Support experimental Java interface
    org.w3c.dom.Node toDomNode() {
        return node.toDomNode();
    }
}

class CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd ());
  }
    public static long[] statements = new long[174];
    public static long[] branches = new long[125];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[72];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XML.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,2,2,1,3,1,1,1,1,1,2,1,1,2};
    for (int i = 1; i <= 71; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$6ubyiuewjkicjhyoyihrixvp8vhd () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XML.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 173; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 124; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 71; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XML.java");
      for (int i = 1; i <= 173; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 124; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 71; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

