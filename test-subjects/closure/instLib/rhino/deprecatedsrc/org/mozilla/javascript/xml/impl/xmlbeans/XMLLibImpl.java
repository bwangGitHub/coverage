/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import java.io.Serializable;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.*;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

public final class XMLLibImpl extends XMLLib implements Serializable
{
  static {
    CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[1]++;
  }

    private Scriptable globalScope;

    XML xmlPrototype;
    XMLList xmlListPrototype;
    Namespace namespacePrototype;
    QName qnamePrototype;


    // Environment settings...
    boolean ignoreComments;
    boolean ignoreProcessingInstructions;
    boolean ignoreWhitespace;
    boolean prettyPrinting;
    int prettyIndent;

    Scriptable globalScope()
    {
        return globalScope;
    }

    private XMLLibImpl(Scriptable globalScope)
    {
        this.globalScope = globalScope;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[2]++;
        defaultSettings();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[3]++;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed)
    {
        // To force LinkageError if XmlObject is not available
        XmlObject.class.getName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[4]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[5]++;

        XMLLibImpl lib = new XMLLibImpl(scope);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[6]++;
        XMLLib bound = lib.bindToScope(scope);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((bound == lib) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[1]++;
            lib.exportToScope(sealed);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[8]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[2]++;}
    }

    private void exportToScope(boolean sealed)
    {
        xmlPrototype = XML.createEmptyXML(this);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[9]++;
        xmlListPrototype = new XMLList(this);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[10]++;
        namespacePrototype = new Namespace(this, "", "");
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[11]++;
        qnamePrototype = new QName(this, "", "", "");
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[12]++;

        xmlPrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[13]++;
        xmlListPrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[14]++;
        namespacePrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[15]++;
        qnamePrototype.exportAsJSClass(sealed);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[16]++;
    }

    void defaultSettings()
    {
        ignoreComments = true;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[17]++;
        ignoreProcessingInstructions = true;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[18]++;
        ignoreWhitespace = true;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[19]++;
        prettyPrinting = true;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[20]++;
        prettyIndent = 2;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[21]++;
    }

    XMLName toAttributeName(Context cx, Object nameValue)
    {
        String uri;
        String localName;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((nameValue instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[3]++;
            uri = "";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[23]++;
            localName = (String)nameValue;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[24]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[4]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[25]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nameValue instanceof XMLName) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[5]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[26]++;
            XMLName xmlName = (XMLName)nameValue;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[7]++;
                xmlName.setAttributeName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[28]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[8]++;}
            return xmlName;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[6]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[29]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[9]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[30]++;
            QName qname = (QName)nameValue;
            uri = qname.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[31]++;
            localName = qname.localName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[32]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[10]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[33]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (128)) == 0 || true) &&
 ((nameValue instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((nameValue instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((nameValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((nameValue == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 4) && false))
        {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[11]++;
            throw badXMLName(nameValue);

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[12]++;
            uri = "";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[34]++;
            localName = ScriptRuntime.toString(nameValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[35]++;
        }
}
}
}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[36]++;
        XMLName xmlName = XMLName.formProperty(uri, localName);
        xmlName.setAttributeName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[37]++;
        return xmlName;
    }

    private static RuntimeException badXMLName(Object value)
    {
        String msg;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[13]++;
            msg = "Can not construct XML name from number: ";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[39]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[14]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[40]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[15]++;
            msg = "Can not construct XML name from boolean: ";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[41]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[16]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[42]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[17]++;
            msg = "Can not construct XML name from ";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[43]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[18]++;
            throw new IllegalArgumentException(value.toString());
        }
}
}
        return ScriptRuntime.typeError(msg+ScriptRuntime.toString(value));
    }

    XMLName toXMLName(Context cx, Object nameValue)
    {
        XMLName result;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((nameValue instanceof XMLName) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[19]++;
            result = (XMLName)nameValue;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[45]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[20]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[46]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[21]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[47]++;
            QName qname = (QName)nameValue;
            result = XMLName.formProperty(qname.uri(), qname.localName());
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[48]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[22]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[49]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((nameValue instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[23]++;
            result = toXMLNameFromString(cx, (String)nameValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[50]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[24]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[51]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (128)) == 0 || true) &&
 ((nameValue instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((nameValue instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((nameValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((nameValue == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 4) && false))
        {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[25]++;
            throw badXMLName(nameValue);

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[26]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[52]++;
            String name = ScriptRuntime.toString(nameValue);
            result = toXMLNameFromString(cx, name);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[53]++;
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
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value instanceof XMLName) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[27]++;
            result = (XMLName)value;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[55]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[28]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[56]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[29]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[57]++;
            String str = (String)value;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[58]++;
            long test = ScriptRuntime.testUint32String(str);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[59]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((test >= 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[31]++;
                ScriptRuntime.storeUint32Result(cx, test);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[60]++;
                result = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[61]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[32]++;
                result = toXMLNameFromString(cx, str);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[62]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[30]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[63]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[33]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[64]++;
            double d = ((Number)value).doubleValue();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[65]++;
            long l = (long)d;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[66]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((l == d) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((0 <= l) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((l <= 0xFFFFFFFFL) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[35]++;
                ScriptRuntime.storeUint32Result(cx, l);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[67]++;
                result = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[68]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[36]++;
                throw badXMLName(value);
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[34]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[69]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((value instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[37]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[70]++;
            QName qname = (QName)value;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[71]++;
            String uri = qname.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[72]++;
            boolean number = false;
            result = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[73]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[74]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((uri != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[39]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[75]++;
                // Only in this case qname.toString() can resemble uint32
                long test = ScriptRuntime.testUint32String(uri);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[76]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((test >= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[41]++;
                    ScriptRuntime.storeUint32Result(cx, test);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[77]++;
                    number = true;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[78]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[42]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[40]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[79]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((number) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[43]++;
                result = XMLName.formProperty(uri, qname.localName());
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[80]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[44]++;}

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[38]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[81]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) && false))
        {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[45]++;
            throw badXMLName(value);

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[46]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[82]++;
            String str = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[83]++;
            long test = ScriptRuntime.testUint32String(str);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[84]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((test >= 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[47]++;
                ScriptRuntime.storeUint32Result(cx, test);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[85]++;
                result = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[86]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[48]++;
                result = toXMLNameFromString(cx, str);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[87]++;
            }
        }
}
}
}
}

        return result;
    }

    XMLName toXMLNameFromString(Context cx, String name)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[88]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[49]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[50]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[89]++;

        int l = name.length();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[90]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((l != 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[51]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[91]++;
            char firstChar = name.charAt(0);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[92]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((firstChar == '*') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[53]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[93]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((l == 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[55]++;
                    return XMLName.formStar();

                } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[56]++;}

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[54]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[94]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((firstChar == '@') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[57]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[95]++;
                XMLName xmlName = XMLName.formProperty("", name.substring(1));
                xmlName.setAttributeName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[96]++;
                return xmlName;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[58]++;}
}

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[52]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[97]++;

        String uri = getDefaultNamespaceURI(cx);

        return XMLName.formProperty(uri, name);
    }

    Namespace constructNamespace(Context cx, Object uriValue)
    {
        String prefix;
        String uri;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[98]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((uriValue instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[59]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[99]++;
            Namespace ns = (Namespace)uriValue;
            prefix = ns.prefix();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[100]++;
            uri = ns.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[101]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[60]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[102]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((uriValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[61]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[103]++;
            QName qname = (QName)uriValue;
            uri = qname.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[104]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[105]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((uri != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[63]++;
                prefix = qname.prefix();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[106]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[64]++;
                uri = qname.toString();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[107]++;
                prefix = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[108]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[62]++;
            uri = ScriptRuntime.toString(uriValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[109]++;
            prefix = (uri.length() == 0) ? "" : null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[110]++;
        }
}

        return new Namespace(this, prefix, uri);
    }

    Namespace castToNamespace(Context cx, Object namescapeObj)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[111]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((namescapeObj instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[65]++;
            return (Namespace)namescapeObj;

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[66]++;}
        return constructNamespace(cx, namescapeObj);
    }

    Namespace constructNamespace(Context cx)
    {
        return new Namespace(this, "", "");
    }

    public Namespace constructNamespace(Context cx, Object prefixValue,
                                        Object uriValue)
    {
        String prefix;
        String uri;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[112]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((uriValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[67]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[113]++;
            QName qname = (QName)uriValue;
            uri = qname.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[114]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[115]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[69]++;
                uri = qname.toString();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[116]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[70]++;}

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[68]++;
            uri = ScriptRuntime.toString(uriValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[117]++;
        }
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[118]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[71]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[119]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((prefixValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[73]++;
                prefix = "";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[120]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[74]++;
                prefix = ScriptRuntime.toString(prefixValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[121]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[122]++;
int CodeCoverConditionCoverageHelper_C38;
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((prefix.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[75]++;
                    throw ScriptRuntime.typeError(
                        "Illegal prefix '"+prefix+"' for 'no namespace'.");

                } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[76]++;}
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[72]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[123]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((prefixValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[77]++;
            prefix = "";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[124]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[78]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[125]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((isXMLName(cx, prefixValue)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[79]++;
            prefix = "";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[126]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[80]++;
            prefix = ScriptRuntime.toString(prefixValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[127]++;
        }
}
}

        return new Namespace(this, prefix, uri);
    }

    String getDefaultNamespaceURI(Context cx)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[128]++;
        String uri = "";
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[129]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[81]++;
            cx = Context.getCurrentContext();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[130]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[82]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[131]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[83]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[132]++;
            Object ns = ScriptRuntime.searchDefaultNamespace(cx);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[133]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[85]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[134]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((ns instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[87]++;
                    uri = ((Namespace)ns).uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[135]++;

                } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[88]++;
                    // Should not happen but for now it could
                    // due to bad searchDefaultNamespace implementation.
                }

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[86]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[84]++;}
        return uri;
    }

    Namespace getDefaultNamespace(Context cx)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[136]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[89]++;
            cx = Context.getCurrentContext();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[137]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[138]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[91]++;
                return namespacePrototype;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[92]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[90]++;}

        Namespace result;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[139]++;
        Object ns = ScriptRuntime.searchDefaultNamespace(cx);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[140]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((ns == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[93]++;
            result = namespacePrototype;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[141]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[94]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[142]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((ns instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[95]++;
                result = (Namespace)ns;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[143]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[96]++;
                // Should not happen but for now it could
                // due to bad searchDefaultNamespace implementation.
                result = namespacePrototype;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[144]++;
            }
        }
        return result;
    }

    QName castToQName(Context cx, Object qnameValue)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[145]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((qnameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[97]++;
            return (QName)qnameValue;

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[98]++;}
        return constructQName(cx, qnameValue);
    }

    QName constructQName(Context cx, Object nameValue)
    {
        QName result;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[146]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[99]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[147]++;
            QName qname = (QName)nameValue;
            result = new QName(this, qname.uri(), qname.localName(),
                               qname.prefix());
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[148]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[100]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[149]++;
            String localName = ScriptRuntime.toString(nameValue);
            result = constructQNameFromString(cx, localName);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[150]++;
        }

        return result;
    }

    /**
     * Optimized version of constructQName for String type
     */
    QName constructQNameFromString(Context cx, String localName)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[151]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((localName == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[101]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[102]++;}

        String uri;
        String prefix;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[152]++;
int CodeCoverConditionCoverageHelper_C52;

        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 (("*".equals(localName)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[103]++;
            uri = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[153]++;
            prefix = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[154]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[104]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[155]++;
            Namespace ns = getDefaultNamespace(cx);
            uri = ns.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[156]++;
            prefix = ns.prefix();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[157]++;
        }

        return new QName(this, uri, localName, prefix);
    }

    QName constructQName(Context cx, Object namespaceValue, Object nameValue)
    {
        String uri;
        String localName;
        String prefix;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[158]++;
int CodeCoverConditionCoverageHelper_C53;

        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[105]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[159]++;
            QName qname = (QName)nameValue;
            localName = qname.localName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[160]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[106]++;
            localName = ScriptRuntime.toString(nameValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[161]++;
        }

        Namespace ns;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[162]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((namespaceValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[107]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[163]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 (("*".equals(localName)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[109]++;
                ns = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[164]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[110]++;
                ns = getDefaultNamespace(cx);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[165]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[108]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[166]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((namespaceValue == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[111]++;
            ns = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[167]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[112]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[168]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((namespaceValue instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[113]++;
            ns = (Namespace)namespaceValue;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[169]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[114]++;
            ns = constructNamespace(cx, namespaceValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[170]++;
        }
}
}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[171]++;
int CodeCoverConditionCoverageHelper_C58;

        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((ns == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[115]++;
            uri = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[172]++;
            prefix = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[173]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[116]++;
            uri = ns.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[174]++;
            prefix = ns.prefix();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[175]++;
        }

        return new QName(this, uri, localName, prefix);
    }

    Object addXMLObjects(Context cx, XMLObject obj1, XMLObject obj2)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[176]++;
        XMLList listToAdd = new XMLList(this);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[177]++;
int CodeCoverConditionCoverageHelper_C59;

        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((obj1 instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[117]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[178]++;
            XMLList list1 = (XMLList)obj1;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[179]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((list1.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[119]++;
                listToAdd.addToList(list1.item(0));
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[180]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[120]++;
                // Might be xmlFragment + xmlFragment + xmlFragment + ...;
                // then the result will be an XMLList which we want to be an
                // rValue and allow it to be assigned to an lvalue.
                listToAdd = new XMLList(this, obj1);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[181]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[118]++;
            listToAdd.addToList(obj1);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[182]++;
        }
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[183]++;
int CodeCoverConditionCoverageHelper_C61;

        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((obj2 instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[121]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[184]++;
            XMLList list2 = (XMLList)obj2;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[185]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[1]++;


int CodeCoverConditionCoverageHelper_C62;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((i < list2.length()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[3]++;
}
                listToAdd.addToList(list2.item(i));
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[186]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[122]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[187]++;
int CodeCoverConditionCoverageHelper_C63; if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((obj2 instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[123]++;
            listToAdd.addToList(obj2);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[188]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[124]++;}
}

        return listToAdd;
    }

    //
    //
    // Overriding XMLLib methods
    //
    //

    /**
     * See E4X 13.1.2.1.
     */
    public boolean isXMLName(Context cx, Object nameObj)
    {
        String name;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[189]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            name = ScriptRuntime.toString(nameObj);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[190]++;
        } catch (EcmaError ee) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[126]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[191]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 (("TypeError".equals(ee.getName())) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[127]++;
                return false;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[128]++;}
            throw ee;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[125]++;
}
  }
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[192]++;

        // See http://w3.org/TR/xml-names11/#NT-NCName
        int length = name.length();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[193]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((length != 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[129]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[194]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((isNCNameStartChar(name.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[131]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[195]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[4]++;


int CodeCoverConditionCoverageHelper_C67;
                for (int i = 1;(((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((i != length) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[196]++;
int CodeCoverConditionCoverageHelper_C68;
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((isNCNameChar(name.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[133]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[134]++;}
                }
                return true;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[132]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[130]++;}

        return false;
    }

    private static boolean isNCNameStartChar(int c)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[197]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 (((c & ~0x7F) == 0) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[135]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[198]++;
int CodeCoverConditionCoverageHelper_C70;
            // Optimize for ASCII and use A..Z < _ < a..z
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((c >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[137]++;
                return c <= 'z';

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[138]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[199]++;
int CodeCoverConditionCoverageHelper_C71; if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((c >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[139]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[200]++;
int CodeCoverConditionCoverageHelper_C72;
                if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[141]++;
                    return true;

                } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[142]++;}
                return c == '_';

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[140]++;}
}

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[136]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[201]++;
int CodeCoverConditionCoverageHelper_C73; if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 (((c & ~0x1FFF) == 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[143]++;
            return (0xC0 <= c && c <= 0xD6)
                   || (0xD8 <= c && c <= 0xF6)
                   || (0xF8 <= c && c <= 0x2FF)
                   || (0x370 <= c && c <= 0x37D)
                   || 0x37F <= c;

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[144]++;}
}
        return (0x200C <= c && c <= 0x200D)
               || (0x2070 <= c && c <= 0x218F)
               || (0x2C00 <= c && c <= 0x2FEF)
               || (0x3001 <= c && c <= 0xD7FF)
               || (0xF900 <= c && c <= 0xFDCF)
               || (0xFDF0 <= c && c <= 0xFFFD)
               || (0x10000 <= c && c <= 0xEFFFF);
    }

    private static boolean isNCNameChar(int c)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[202]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 (((c & ~0x7F) == 0) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[145]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[203]++;
int CodeCoverConditionCoverageHelper_C75;
            // Optimize for ASCII and use - < . < 0..9 < A..Z < _ < a..z
            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((c >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[147]++;
                return c <= 'z';

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[148]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[204]++;
int CodeCoverConditionCoverageHelper_C76; if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((c >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[149]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[205]++;
int CodeCoverConditionCoverageHelper_C77;
                if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[151]++;
                    return true;

                } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[152]++;}
                return c == '_';

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[150]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[206]++;
int CodeCoverConditionCoverageHelper_C78; if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[153]++;
                return c <= '9';

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[154]++;
                return c == '-' || c == '.';
            }
}
}

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[146]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[207]++;
int CodeCoverConditionCoverageHelper_C79; if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 (((c & ~0x1FFF) == 0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[155]++;
            return isNCNameStartChar(c) || c == 0xB7
                   || (0x300 <= c && c <= 0x36F);

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[156]++;}
}
        return isNCNameStartChar(c) || (0x203F <= c && c <= 0x2040);
    }

    XMLName toQualifiedName(Context cx, Object namespaceValue,
                            Object nameValue)
    {
        // This is duplication of constructQName(cx, namespaceValue, nameValue)
        // but for XMLName

        String uri;
        String localName;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[208]++;
int CodeCoverConditionCoverageHelper_C80;

        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((nameValue instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[157]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[209]++;
            QName qname = (QName)nameValue;
            localName = qname.localName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[210]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[158]++;
            localName = ScriptRuntime.toString(nameValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[211]++;
        }

        Namespace ns;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[212]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((namespaceValue == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[159]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[213]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 (("*".equals(localName)) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[161]++;
                ns = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[214]++;

            } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[162]++;
                ns = getDefaultNamespace(cx);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[215]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[160]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[216]++;
int CodeCoverConditionCoverageHelper_C83; if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((namespaceValue == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[163]++;
            ns = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[217]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[164]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[218]++;
int CodeCoverConditionCoverageHelper_C84; if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((namespaceValue instanceof Namespace) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[165]++;
            ns = (Namespace)namespaceValue;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[219]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[166]++;
            ns = constructNamespace(cx, namespaceValue);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[220]++;
        }
}
}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[221]++;
int CodeCoverConditionCoverageHelper_C85;

        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((ns == null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[167]++;
            uri = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[222]++;

        } else {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[168]++;
            uri = ns.uri();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[223]++;
        }

        return XMLName.formProperty(uri, localName);
    }

    public Ref nameRef(Context cx, Object name,
                       Scriptable scope, int memberTypeFlags)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[224]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.ATTRIBUTE_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[169]++;
            // should only be called foir cases like @name or @[expr]
            throw Kit.codeBug();

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[170]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[225]++;
        XMLName xmlName = toAttributeName(cx, name);
        return xmlPrimaryReference(cx, xmlName, scope);
    }

    public Ref nameRef(Context cx, Object namespace, Object name,
                       Scriptable scope, int memberTypeFlags)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[226]++;
        XMLName xmlName = toQualifiedName(cx, namespace, name);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[227]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.ATTRIBUTE_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[171]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[228]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[173]++;
                xmlName.setAttributeName();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[229]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[174]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[172]++;}
        return xmlPrimaryReference(cx, xmlName, scope);
    }

    private Ref xmlPrimaryReference(Context cx, XMLName xmlName,
                                    Scriptable scope)
    {
        XMLObjectImpl xmlObj;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[230]++;
        XMLObjectImpl firstXmlObject = null;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[231]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[7]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.loops[9]++;
}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[232]++;
int CodeCoverConditionCoverageHelper_C90;
            // XML object can only present on scope chain as a wrapper
            // of XMLWithScope
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((scope instanceof XMLWithScope) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[175]++;
                xmlObj = (XMLObjectImpl)scope.getPrototype();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[233]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[234]++;
int CodeCoverConditionCoverageHelper_C91;
                if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((xmlObj.hasXMLProperty(xmlName)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[177]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[235]++;
                    break;

                } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[178]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[236]++;
int CodeCoverConditionCoverageHelper_C92;
                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((firstXmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[179]++;
                    firstXmlObject = xmlObj;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[237]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[180]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[176]++;}
            scope = scope.getParentScope();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[238]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[239]++;
int CodeCoverConditionCoverageHelper_C93;
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[181]++;
                xmlObj = firstXmlObject;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[240]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[241]++;
                break;

            } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[182]++;}
        }
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[242]++;
int CodeCoverConditionCoverageHelper_C94;

        // xmlObj == null corresponds to undefined as the target of
        // the reference
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((xmlObj != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[183]++;
            xmlName.initXMLObject(xmlObj);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[243]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[184]++;}
        return xmlName;
    }

    /**
     * Escapes the reserved characters in a value of an attribute
     *
     * @param value Unescaped text
     * @return The escaped text
     */
    public String escapeAttributeValue(Object value)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[244]++;
        String text = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[245]++;
int CodeCoverConditionCoverageHelper_C95;

        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((text.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[185]++; return "";
} else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[186]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[246]++;

        XmlObject xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[247]++;

        XmlCursor cursor = xo.newCursor();
        cursor.toNextToken();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[248]++;
        cursor.beginElement("a");
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[249]++;
        cursor.insertAttributeWithValue("a", text);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[250]++;
        cursor.dispose();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[251]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[252]++;

        String elementText = xo.toString();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[253]++;
        int begin = elementText.indexOf('"');
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[254]++;
        int end = elementText.lastIndexOf('"');
        return elementText.substring(begin + 1, end);
    }

    /**
     * Escapes the reserved characters in a value of a text node
     *
     * @param value Unescaped text
     * @return The escaped text
     */
    public String escapeTextValue(Object value)
    {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[255]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((value instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[187]++;
            return ((XMLObjectImpl)value).toXMLString(0);

        } else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[188]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[256]++;

        String text = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[257]++;
int CodeCoverConditionCoverageHelper_C97;

        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((text.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[189]++; return text;
} else {
  CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.branches[190]++;}
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[258]++;

        XmlObject xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[259]++;

        XmlCursor cursor = xo.newCursor();
        cursor.toNextToken();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[260]++;
        cursor.beginElement("a");
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[261]++;
        cursor.insertChars(text);
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[262]++;
        cursor.dispose();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[263]++;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[264]++;

        String elementText = xo.toString();
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[265]++;
        int begin = elementText.indexOf('>') + 1;
CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p.statements[266]++;
        int end = elementText.lastIndexOf('<');
        return (begin < end) ? elementText.substring(begin, end) : "";
    }

    public Object toDefaultXmlNamespace(Context cx, Object uriValue)
    {
        return constructNamespace(cx, uriValue);
    }
}

class CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p ());
  }
    public static long[] statements = new long[267];
    public static long[] branches = new long[191];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[98];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLLibImpl.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,3,1,1,2,1,1,1,3,1,1,1,1,3,1,2,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 97; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$3quun66a2b3rfj9h7iiu7yyde4gvcxfqs9f8g2p () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLLibImpl.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 266; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 190; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 97; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLLibImpl.java");
      for (int i = 1; i <= 266; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 190; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 97; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

