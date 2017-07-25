/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import java.io.Serializable;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.*;
import org.xml.sax.SAXException;

public final class XMLLibImpl extends XMLLib implements Serializable {
  static {
    CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[1]++;
  }

    //
    //    EXPERIMENTAL Java interface
    //

    /**
        This experimental interface is undocumented.
     */
    public static org.w3c.dom.Node toDomNode(Object xmlObject) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        //    Could return DocumentFragment for XMLList
        //    Probably a single node for XMLList with one element
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((xmlObject instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[1]++;
            return ((XML)xmlObject).toDomNode();

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[2]++;
            throw new IllegalArgumentException(
                    "xmlObject is not an XML object in JavaScript.");
        }
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[3]++;
        XMLLibImpl lib = new XMLLibImpl(scope);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[4]++;
        XMLLib bound = lib.bindToScope(scope);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((bound == lib) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[3]++;
            lib.exportToScope(sealed);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[6]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[4]++;}
    }

    public void setIgnoreComments(boolean b) {
        options.setIgnoreComments(b);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[7]++;
    }

    public void setIgnoreWhitespace(boolean b) {
        options.setIgnoreWhitespace(b);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[8]++;
    }

    public void setIgnoreProcessingInstructions(boolean b) {
        options.setIgnoreProcessingInstructions(b);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[9]++;
    }

    public void setPrettyPrinting(boolean b) {
        options.setPrettyPrinting(b);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[10]++;
    }

    public void setPrettyIndent(int i) {
        options.setPrettyIndent(i);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[11]++;
    }

    public boolean isIgnoreComments() {
        return options.isIgnoreComments();
    }

    public boolean isIgnoreProcessingInstructions() {
        return options.isIgnoreProcessingInstructions();
    }

    public boolean isIgnoreWhitespace() {
        return options.isIgnoreWhitespace();
    }

    public  boolean isPrettyPrinting() {
        return options.isPrettyPrinting();
    }

    public int getPrettyIndent() {
        return options.getPrettyIndent();
    }


    private Scriptable globalScope;

    private XML xmlPrototype;
    private XMLList xmlListPrototype;
    private Namespace namespacePrototype;
    private QName qnamePrototype;

    private XmlProcessor options = new XmlProcessor();
  {
    CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[12]++;
  }

    private XMLLibImpl(Scriptable globalScope) {
        this.globalScope = globalScope;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[13]++;
    }

    /** @deprecated */
    QName qnamePrototype() {
        return qnamePrototype;
    }

    /** @deprecated */
    Scriptable globalScope() {
        return globalScope;
    }

    XmlProcessor getProcessor() {
        return options;
    }

    private void exportToScope(boolean sealed) {
        xmlPrototype = newXML(XmlNode.createText(options, ""));
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[14]++;
        xmlListPrototype = newXMLList();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[15]++;
        namespacePrototype = Namespace.create(this.globalScope, null,
                XmlNode.Namespace.GLOBAL);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[16]++;
        qnamePrototype = QName.create(this, this.globalScope, null,
                XmlNode.QName.create(XmlNode.Namespace.create(""), ""));
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[17]++;

        xmlPrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[18]++;
        xmlListPrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[19]++;
        namespacePrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[20]++;
        qnamePrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[21]++;
    }

    /** @deprecated */
    XMLName toAttributeName(Context cx, Object nameValue) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nameValue instanceof XMLName) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[5]++;
            //    TODO    Will this always be an XMLName of type attribute name?
            return (XMLName)nameValue;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[6]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[23]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[7]++;
            return XMLName.create( ((QName)nameValue).getDelegate(), true, false );

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[8]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[24]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (128)) == 0 || true) &&
 ((nameValue instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((nameValue instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((nameValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((nameValue == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[9]++;
            throw badXMLName(nameValue);

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[10]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[25]++;
            //    TODO    Not 100% sure that putting these in global namespace is the right thing to do
            String localName = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((nameValue instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[11]++;
                localName = (String)nameValue;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[27]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[12]++;
                localName = ScriptRuntime.toString(nameValue);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[28]++;
            }
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((localName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((localName.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[13]++; localName = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[30]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[14]++;}
            return XMLName.create(XmlNode.QName.create(
                    XmlNode.Namespace.create(""), localName), true, false);
        }
}
}
    }

    private static RuntimeException badXMLName(Object value)
    {
        String msg;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[15]++;
            msg = "Can not construct XML name from number: ";
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[32]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[16]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[33]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[17]++;
            msg = "Can not construct XML name from boolean: ";
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[34]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[18]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[35]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[19]++;
            msg = "Can not construct XML name from ";
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[36]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[20]++;
            throw new IllegalArgumentException(value.toString());
        }
}
}
        return ScriptRuntime.typeError(msg+ScriptRuntime.toString(value));
    }

    XMLName toXMLNameFromString(Context cx, String name) {
        return XMLName.create(getDefaultNamespaceURI(cx), name);
    }

    /* TODO: Marked deprecated by original author */
    XMLName toXMLName(Context cx, Object nameValue) {
        XMLName result;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((nameValue instanceof XMLName) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[21]++;
            result = (XMLName)nameValue;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[38]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[22]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[39]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[23]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[40]++;
            QName qname = (QName)nameValue;
            result = XMLName.formProperty(qname.uri(), qname.localName());
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[41]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[24]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[42]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((nameValue instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[25]++;
            result = toXMLNameFromString(cx, (String)nameValue);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[43]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[26]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[44]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (128)) == 0 || true) &&
 ((nameValue instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((nameValue instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((nameValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((nameValue == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[27]++;
            throw badXMLName(nameValue);

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[28]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[45]++;
            String name = ScriptRuntime.toString(nameValue);
            result = toXMLNameFromString(cx, name);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[46]++;
        }
}
}
}

        return result;
    }

    /**
     * If value represents Uint32 index, make it available through
     * ScriptRuntime.lastUint32Result(cx) and return null.
     * Otherwise return the same value as toXMLName(cx, value).
     */
    XMLName toXMLNameOrIndex(Context cx, Object value)
    {
        XMLName result;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[47]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((value instanceof XMLName) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[29]++;
            result = (XMLName)value;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[48]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[30]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[49]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[31]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[50]++;
            String str = (String)value;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[51]++;
            long test = ScriptRuntime.testUint32String(str);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[52]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((test >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[33]++;
                ScriptRuntime.storeUint32Result(cx, test);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[53]++;
                result = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[54]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[34]++;
                result = toXMLNameFromString(cx, str);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[55]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[32]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[56]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[35]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[57]++;
            double d = ((Number)value).doubleValue();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[58]++;
            long l = (long)d;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[59]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((l == d) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((0 <= l) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((l <= 0xFFFFFFFFL) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[37]++;
                ScriptRuntime.storeUint32Result(cx, l);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[60]++;
                result = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[61]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[38]++;
                throw badXMLName(value);
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[36]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[62]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((value instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[39]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[63]++;
            QName qname = (QName)value;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[64]++;
            String uri = qname.uri();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[65]++;
            boolean number = false;
            result = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[66]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[67]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((uri != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[41]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[68]++;
                // Only in this case qname.toString() can resemble uint32
                long test = ScriptRuntime.testUint32String(uri);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[69]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((test >= 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[43]++;
                    ScriptRuntime.storeUint32Result(cx, test);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[70]++;
                    number = true;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[71]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[44]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[42]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[72]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((number) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[45]++;
                result = XMLName.formProperty(uri, qname.localName());
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[73]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[46]++;}

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[40]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[74]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) && false))
        {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[47]++;
            throw badXMLName(value);

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[48]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[75]++;
            String str = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[76]++;
            long test = ScriptRuntime.testUint32String(str);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[77]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((test >= 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[49]++;
                ScriptRuntime.storeUint32Result(cx, test);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[78]++;
                result = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[79]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[50]++;
                result = toXMLNameFromString(cx, str);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[80]++;
            }
        }
}
}
}
}

        return result;
    }

    Object addXMLObjects(Context cx, XMLObject obj1, XMLObject obj2)
    {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[81]++;
        XMLList listToAdd = newXMLList();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[82]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj1 instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[51]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[83]++;
            XMLList list1 = (XMLList)obj1;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[84]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((list1.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[53]++;
                listToAdd.addToList(list1.item(0));
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[85]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[54]++;
                // Might be xmlFragment + xmlFragment + xmlFragment + ...;
                // then the result will be an XMLList which we want to be an
                // rValue and allow it to be assigned to an lvalue.
                listToAdd = newXMLListFrom(obj1);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[86]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[52]++;
            listToAdd.addToList(obj1);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[87]++;
        }
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[88]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((obj2 instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[55]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[89]++;
            XMLList list2 = (XMLList)obj2;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[90]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[1]++;


int CodeCoverConditionCoverageHelper_C29;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i < list2.length()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[3]++;
}
                listToAdd.addToList(list2.item(i));
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[91]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[56]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[92]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((obj2 instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[57]++;
            listToAdd.addToList(obj2);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[93]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[58]++;}
}

        return listToAdd;
    }

    private Ref xmlPrimaryReference(Context cx, XMLName xmlName, Scriptable scope) {
        XMLObjectImpl xmlObj;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[94]++;
        XMLObjectImpl firstXml = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[95]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[4]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[96]++;
int CodeCoverConditionCoverageHelper_C32;
            // XML object can only present on scope chain as a wrapper
            // of XMLWithScope
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((scope instanceof XMLWithScope) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[59]++;
                xmlObj = (XMLObjectImpl)scope.getPrototype();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[97]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[98]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((xmlObj.hasXMLProperty(xmlName)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[61]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[99]++;
                    break;

                } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[62]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[100]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((firstXml == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[63]++;
                    firstXml = xmlObj;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[101]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[64]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[60]++;}
            scope = scope.getParentScope();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[102]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[103]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[65]++;
                xmlObj = firstXml;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[104]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[105]++;
                break;

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[66]++;}
        }
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[106]++;
int CodeCoverConditionCoverageHelper_C36;

        // xmlObj == null corresponds to undefined as the target of
        // the reference
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((xmlObj != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[67]++;
            xmlName.initXMLObject(xmlObj);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[107]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[68]++;}
        return xmlName;
    }

    Namespace castToNamespace(Context cx, Object namespaceObj) {
        return this.namespacePrototype.castToNamespace(namespaceObj);
    }

    private String getDefaultNamespaceURI(Context cx) {
        return getDefaultNamespace(cx).uri();
    }

    Namespace newNamespace(String uri) {
        return this.namespacePrototype.newNamespace(uri);
    }

    Namespace getDefaultNamespace(Context cx) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[108]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[69]++;
            cx = Context.getCurrentContext();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[109]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[110]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[71]++;
                return namespacePrototype;

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[72]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[70]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[111]++;

        Object ns = ScriptRuntime.searchDefaultNamespace(cx);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[112]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ns == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[73]++;
            return namespacePrototype;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[74]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[113]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((ns instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[75]++;
                return (Namespace)ns;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[76]++;
                //    TODO    Clarify or remove the following comment
                // Should not happen but for now it could
                // due to bad searchDefaultNamespace implementation.
                return namespacePrototype;
            }
        }
    }

    Namespace[] createNamespaces(XmlNode.Namespace[] declarations) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[114]++;
        Namespace[] rv = new Namespace[declarations.length];
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[115]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[7]++;


int CodeCoverConditionCoverageHelper_C41;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i<declarations.length) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[9]++;
}
            rv[i] = this.namespacePrototype.newNamespace(
                    declarations[i].getPrefix(), declarations[i].getUri());
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[116]++;
        }
        return rv;
    }

    //    See ECMA357 13.3.2
    QName constructQName(Context cx, Object namespace, Object name) {
        return this.qnamePrototype.constructQName(this, cx, namespace, name);
    }

    QName newQName(String uri, String localName, String prefix) {
        return this.qnamePrototype.newQName(this, uri, localName, prefix);
    }

    QName constructQName(Context cx, Object nameValue) {
//        return constructQName(cx, Undefined.instance, nameValue);
        return this.qnamePrototype.constructQName(this, cx, nameValue);
    }

    QName castToQName(Context cx, Object qnameValue) {
        return this.qnamePrototype.castToQName(this, cx, qnameValue);
    }

    QName newQName(XmlNode.QName qname) {
        return QName.create(this, this.globalScope, this.qnamePrototype, qname);
    }

    XML newXML(XmlNode node) {
        return new XML(this, this.globalScope, this.xmlPrototype, node);
    }

    /* TODO: Can this can be replaced by ecmaToXml below?
     */
    final XML newXMLFromJs(Object inputObject) {
        String frag;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[117]++;
int CodeCoverConditionCoverageHelper_C42;

        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((inputObject == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((inputObject == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[77]++;
            frag = "";
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[118]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[78]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[119]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((inputObject instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[79]++;
            // todo: faster way for XMLObjects?
            frag = ((XMLObjectImpl) inputObject).toXMLString();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[120]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[80]++;
            frag = ScriptRuntime.toString(inputObject);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[121]++;
        }
}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[122]++;
int CodeCoverConditionCoverageHelper_C44;

        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((frag.trim().startsWith("<>")) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[81]++;
            throw ScriptRuntime.typeError("Invalid use of XML object anonymous tags <></>.");

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[82]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[123]++;
int CodeCoverConditionCoverageHelper_C45;

        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((frag.indexOf("<") == -1) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[83]++;
            //    Solo text node
            return newXML(XmlNode.createText(options, frag));

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[84]++;}
        return parse(frag);
    }

    private XML parse(String frag) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[124]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return newXML(XmlNode.createElement(options,
                    getDefaultNamespaceURI(Context.getCurrentContext()), frag));
        } catch (SAXException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[86]++;
            throw ScriptRuntime.typeError("Cannot parse XML: " + e.getMessage());
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[85]++;
}
  }
    }

    final XML ecmaToXml(Object object) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[125]++;
int CodeCoverConditionCoverageHelper_C46;
        //    See ECMA357 10.3
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((object == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[87]++;
            throw ScriptRuntime.typeError("Cannot convert " + object + " to XML");

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[88]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[126]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((object instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[89]++; return (XML)object;
} else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[90]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[127]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((object instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[91]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[128]++;
            XMLList list = (XMLList)object;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[129]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((list.getXML() != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[93]++;
                return list.getXML();

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[94]++;
                throw ScriptRuntime.typeError("Cannot convert list of >1 element to XML");
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[92]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[130]++;
int CodeCoverConditionCoverageHelper_C50;
        //    TODO    Technically we should fail on anything except a String, Number or Boolean
        //            See ECMA357 10.3
        // Extension: if object is a DOM node, use that to construct the XML
        // object.
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((object instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[95]++;
            object = ((Wrapper) object).unwrap();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[131]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[96]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[132]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((object instanceof org.w3c.dom.Node) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[97]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[133]++;
            org.w3c.dom.Node node = (org.w3c.dom.Node) object;
            return newXML(XmlNode.createElementFromNode(node));

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[98]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[134]++;
        //    Instead we just blindly cast to a String and let them convert anything.
        String s = ScriptRuntime.toString(object);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[135]++;
int CodeCoverConditionCoverageHelper_C52;
        //    TODO    Could this get any uglier?
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((s.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((s.charAt(0) == '<') && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[99]++;
            return parse(s);

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[100]++;
            return newXML(XmlNode.createText(options, s));
        }
    }

    final XML newTextElementXML(XmlNode reference, XmlNode.QName qname, String value) {
        return newXML(XmlNode.newElementWithText(options, reference, qname, value));
    }

    XMLList newXMLList() {
        return new XMLList(this, this.globalScope, this.xmlListPrototype);
    }

    final XMLList newXMLListFrom(Object inputObject) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[136]++;
        XMLList rv = newXMLList();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[137]++;
int CodeCoverConditionCoverageHelper_C53;

        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((inputObject == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((inputObject instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[101]++;
            return rv;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[102]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[138]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((inputObject instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[103]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[139]++;
            XML xml = (XML) inputObject;
            rv.getNodeList().add(xml);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[140]++;
            return rv;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[104]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[141]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((inputObject instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[105]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[142]++;
            XMLList xmll = (XMLList) inputObject;
            rv.getNodeList().add(xmll.getNodeList());
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[143]++;
            return rv;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[106]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[144]++;
            String frag = ScriptRuntime.toString(inputObject).trim();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[145]++;
int CodeCoverConditionCoverageHelper_C56;

            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((frag.startsWith("<>")) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[107]++;
                frag = "<>" + frag + "</>";
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[146]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[108]++;}

            frag = "<fragment>" + frag.substring(2);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[147]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[148]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((frag.endsWith("</>")) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[109]++;
                throw ScriptRuntime.typeError("XML with anonymous tag missing end anonymous tag");

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[110]++;}

            frag = frag.substring(0, frag.length() - 3) + "</fragment>";
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[149]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[150]++;

            XML orgXML = newXMLFromJs(frag);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[151]++;

            // Now orphan the children and add them to our XMLList.
            XMLList children = orgXML.children();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[152]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[10]++;


int CodeCoverConditionCoverageHelper_C58;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((i < children.getNodeList().length()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[10]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[11]--;
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.loops[12]++;
}
                // Copy here is so that they'll be orphaned (parent() will be undefined)
                rv.getNodeList().add(((XML) children.item(i).copy()));
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[153]++;
            }
            return rv;
        }
}
}
    }

    XmlNode.QName toNodeQName(Context cx, Object namespaceValue, Object nameValue) {
        // This is duplication of constructQName(cx, namespaceValue, nameValue)
        // but for XMLName

        String localName;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[154]++;
int CodeCoverConditionCoverageHelper_C59;

        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[111]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[155]++;
            QName qname = (QName)nameValue;
            localName = qname.localName();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[156]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[112]++;
            localName = ScriptRuntime.toString(nameValue);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[157]++;
        }

        XmlNode.Namespace ns;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[158]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((namespaceValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[113]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[159]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 (("*".equals(localName)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[115]++;
                ns = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[160]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[116]++;
                ns = getDefaultNamespace(cx).getDelegate();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[161]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[114]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[162]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((namespaceValue == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[117]++;
            ns = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[163]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[118]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[164]++;
int CodeCoverConditionCoverageHelper_C63; if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((namespaceValue instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[119]++;
            ns = ((Namespace)namespaceValue).getDelegate();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[165]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[120]++;
            ns = this.namespacePrototype.constructNamespace(namespaceValue).getDelegate();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[166]++;
        }
}
}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[167]++;
int CodeCoverConditionCoverageHelper_C64;

        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((localName != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((localName.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[121]++; localName = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[168]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[122]++;}
        return XmlNode.QName.create(ns, localName);
    }

    XmlNode.QName toNodeQName(Context cx, String name, boolean attribute) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[169]++;
        XmlNode.Namespace defaultNamespace = getDefaultNamespace(cx).getDelegate();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[170]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((name.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[123]++;
            return XmlNode.QName.create(null, null);

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[124]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[171]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((attribute) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[125]++;
                return XmlNode.QName.create(XmlNode.Namespace.GLOBAL, name);

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[126]++;
                return XmlNode.QName.create(defaultNamespace, name);
            }
        }
    }

    /*
        TODO: Too general; this should be split into overloaded methods.
        Is that possible?
     */
    XmlNode.QName toNodeQName(Context cx, Object nameValue, boolean attribute) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[172]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((nameValue instanceof XMLName) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[127]++;
            return ((XMLName)nameValue).toQname();

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[128]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[173]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[129]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[174]++;
            QName qname = (QName)nameValue;
            return qname.getDelegate();

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[130]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[175]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (128)) == 0 || true) &&
 ((nameValue instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((nameValue instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((nameValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((nameValue == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 4) && false)
        ) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[131]++;
            throw badXMLName(nameValue);

        } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[132]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[176]++;
            String local = null;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[177]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((nameValue instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[133]++;
                local = (String)nameValue;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[178]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[134]++;
                local = ScriptRuntime.toString(nameValue);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[179]++;
            }
            return toNodeQName(cx, local, attribute);
        }
}
}
    }

    //
    //    Override methods from XMLLib
    //

    @Override
    public boolean isXMLName(Context _cx, Object nameObj) {
        return XMLName.accept(nameObj);
    }

    @Override
    public Object toDefaultXmlNamespace(Context cx, Object uriValue) {
        return this.namespacePrototype.constructNamespace(uriValue);
    }

    @Override
    public String escapeTextValue(Object o) {
        return options.escapeTextValue(o);
    }

    @Override
    public String escapeAttributeValue(Object o) {
        return options.escapeAttributeValue(o);
    }

    @Override
    public Ref nameRef(Context cx, Object name, Scriptable scope, int memberTypeFlags) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[180]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.ATTRIBUTE_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[135]++;
            // should only be called for cases like @name or @[expr]
            throw Kit.codeBug();

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[136]++;}
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[181]++;
        XMLName xmlName = toAttributeName(cx, name);
        return xmlPrimaryReference(cx, xmlName, scope);
    }

    @Override
    public Ref nameRef(Context cx, Object namespace, Object name, Scriptable scope, int memberTypeFlags) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[182]++;
        XMLName xmlName = XMLName.create(toNodeQName(cx, namespace, name), false, false);
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[183]++;
int CodeCoverConditionCoverageHelper_C72;

        //    No idea what is coming in from the parser in this case; is it detecting the "@"?
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.ATTRIBUTE_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[137]++;
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[184]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[139]++;
                xmlName.setAttributeName();
CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.statements[185]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[140]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd.branches[138]++;}

        return xmlPrimaryReference(cx, xmlName, scope);
    }
}

class CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd ());
  }
    public static long[] statements = new long[186];
    public static long[] branches = new long[141];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[74];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XMLLibImpl.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,3,1,2,1,1,2,1,1,1,3,1,1,1,1,3,1,2,1,1,3,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,3,1,1,1,1};
    for (int i = 1; i <= 73; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$3quun66a2bflvebt5mqi0x6oyl3l05x9am4tgtd () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLLibImpl.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 185; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 140; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 73; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLLibImpl.java");
      for (int i = 1; i <= 185; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 140; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 73; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

