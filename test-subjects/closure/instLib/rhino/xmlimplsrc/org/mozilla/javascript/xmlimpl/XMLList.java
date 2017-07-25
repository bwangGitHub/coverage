/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.*;
import java.util.ArrayList;

class XMLList extends XMLObjectImpl implements Function {
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.ping();
  }

    static final long serialVersionUID = -4543618751670781135L;
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[1]++;
  }

    private XmlNode.InternalList _annos;
    private XMLObjectImpl targetObject = null;
  {
    CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[2]++;
  }
    private XmlNode.QName targetProperty = null;
  {
    CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[3]++;
  }

    XMLList(XMLLibImpl lib, Scriptable scope, XMLObject prototype) {
        super(lib, scope, prototype);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[4]++;
        _annos = new XmlNode.InternalList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[5]++;
    }

    /* TODO Will probably end up unnecessary as we move things around */
    XmlNode.InternalList getNodeList() {
        return _annos;
    }

    //    TODO    Should be XMLObjectImpl, XMLName?
    void setTargets(XMLObjectImpl object, XmlNode.QName property) {
        targetObject = object;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[6]++;
        targetProperty = property;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[7]++;
    }

    /* TODO: original author marked this as deprecated */
    private XML getXmlFromAnnotation(int index) {
        return getXML(_annos, index);
    }

    @Override
    XML getXML() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[1]++; return getXmlFromAnnotation(0);
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[2]++;}
        return null;
    }

    private void internalRemoveFromList(int index) {
        _annos.remove(index);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[9]++;
    }

    void replace(int index, XML xml) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[3]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[11]++;
            XmlNode.InternalList newAnnoList = new XmlNode.InternalList();
            newAnnoList.add(_annos, 0, index);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[12]++;
            newAnnoList.add(xml);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[13]++;
            newAnnoList.add(_annos, index+1, length());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[14]++;
            _annos = newAnnoList;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[15]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[4]++;}
    }

    private void insert(int index, XML xml) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[5]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[17]++;
            XmlNode.InternalList newAnnoList = new XmlNode.InternalList();
            newAnnoList.add(_annos, 0, index);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[18]++;
            newAnnoList.add(xml);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[19]++;
            newAnnoList.add(_annos, index, length());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[20]++;
            _annos = newAnnoList;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[21]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[6]++;}
    }

    //
    //
    //  methods overriding ScriptableObject
    //
    //

    @Override
    public String getClassName() {
        return "XMLList";
    }

    //
    //
    //  methods overriding IdScriptableObject
    //
    //

    @Override
    public Object get(int index, Scriptable start) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        //Log("get index: " + index);

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[7]++;
            return getXmlFromAnnotation(index);

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[8]++;
            return Scriptable.NOT_FOUND;
        }
    }

    @Override
    boolean hasXMLProperty(XMLName xmlName) {
        // Has should return true if the property would have results > 0
        return getPropertyList(xmlName).length() > 0;
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return 0 <= index && index < length();
    }

    @Override
    void putXMLProperty(XMLName xmlName, Object value) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        //Log("put property: " + name);

        // Special-case checks for undefined and null
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[9]++;
            value = "null";
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[24]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[10]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[25]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[11]++;
            value = "undefined";
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[26]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[12]++;}
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((length() > 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[13]++;
            throw ScriptRuntime.typeError(
               "Assignment to lists with more than one item is not supported");

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[14]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[28]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[15]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
            // Secret sauce for super-expandos.
            // We set an element here, and then add ourselves to our target.
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 ((targetObject != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((targetProperty != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((targetProperty.getLocalName() != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((targetProperty.getLocalName().length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) && false))
            {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[17]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[30]++;
                // Add an empty element with our targetProperty name and
                // then set it.
                XML xmlValue = newTextElementXML(null, targetProperty, null);
                addToList(xmlValue);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[31]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;

                if((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[19]++;
                    setAttribute(xmlName, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[33]++;

                } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[20]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[34]++;
                    XML xml = item(0);
                    xml.putXMLProperty(xmlName, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[35]++;

                    // Update the list with the new item at location 0.
                    replace(0, item(0));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[36]++;
                }
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[37]++;

                // Now add us to our parent
                XMLName name2 = XMLName.formProperty(
                        targetProperty.getNamespace().getUri(),
                        targetProperty.getLocalName());
                targetObject.putXMLProperty(name2, this);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[38]++;
                replace(0, targetObject.getXML().getLastXmlChild());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[39]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[18]++;
                throw ScriptRuntime.typeError(
                  "Assignment to empty XMLList without targets not supported");
            }

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[16]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[40]++;
int CodeCoverConditionCoverageHelper_C11; if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[21]++;
            setAttribute(xmlName, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[41]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[22]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[42]++;
            XML xml = item(0);
            xml.putXMLProperty(xmlName, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[43]++;

            // Update the list with the new item at location 0.
            replace(0, item(0));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[44]++;
        }
}
}
    }

    @Override
    Object getXMLProperty(XMLName name) {
        return getPropertyList(name);
    }

    private void replaceNode(XML xml, XML with) {
        xml.replaceWith(with);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[45]++;
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[46]++;
        Object parent = Undefined.instance;
        // Convert text into XML if needed.
        XMLObject xmlValue;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;

        // Special-case checks for undefined and null
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[23]++;
            value = "null";
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[48]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[24]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[49]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[25]++;
            value = "undefined";
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[50]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[26]++;}
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[27]++;
            xmlValue = (XMLObject) value;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[52]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[28]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((targetProperty == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[29]++;
                xmlValue = newXMLFromJs(value.toString());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[54]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[30]++;
                //    Note that later in the code, we will use this as an argument to replace(int,value)
                //    So we will be "replacing" this element with itself
                //    There may well be a better way to do this
                //    TODO    Find a way to refactor this whole method and simplify it
                xmlValue = item(index);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[55]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((xmlValue == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[31]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[57]++;
                    XML x = item(0);
                    xmlValue = x == null
                        ? newTextElementXML(null,targetProperty,null)
                        : x.copy();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[58]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[32]++;}
                ((XML)xmlValue).setChildren(value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[59]++;
            }
        }
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[60]++;
int CodeCoverConditionCoverageHelper_C17;

        // Find the parent
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[33]++;
          parent = item(index).parent();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[61]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[34]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[62]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[35]++;
          parent = targetObject != null ? targetObject.getXML() : parent();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[63]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[36]++;
          // Appending
          parent = parent();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[64]++;
        }
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[65]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((parent instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[37]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[66]++;
            // found parent, alter doc
            XML xmlParent = (XML) parent;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[67]++;
int CodeCoverConditionCoverageHelper_C20;

            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[39]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[68]++;
                // We're replacing the the node.
                XML xmlNode = getXmlFromAnnotation(index);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[69]++;
int CodeCoverConditionCoverageHelper_C21;

                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[41]++;
                    replaceNode(xmlNode, (XML) xmlValue);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[70]++;
                    replace(index, xmlNode);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[71]++;

                } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[42]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[72]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[43]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[73]++;
                    // Replace the first one, and add the rest on the list.
                    XMLList list = (XMLList) xmlValue;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[74]++;
int CodeCoverConditionCoverageHelper_C23;

                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((list.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[45]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[75]++;
                        int lastIndexAdded = xmlNode.childIndex();
                        replaceNode(xmlNode, list.item(0));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[76]++;
                        replace(index, list.item(0));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[77]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[78]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[1]++;


int CodeCoverConditionCoverageHelper_C24;

                        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < list.length()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[3]++;
}
                            xmlParent.insertChildAfter(xmlParent.getXmlChild(lastIndexAdded), list.item(i));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[79]++;
                            lastIndexAdded++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[80]++;
                            insert(index + i, list.item(i));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[81]++;
                        }

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[46]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[44]++;}
}

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[40]++;
                // Appending
                xmlParent.appendChild(xmlValue);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[82]++;
                addToList(xmlParent.getLastXmlChild());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[83]++;
            }

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[38]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[84]++;
int CodeCoverConditionCoverageHelper_C25;
            // Don't all have same parent, no underlying doc to alter
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[47]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[85]++;
                XML xmlNode = getXML(_annos, index);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[86]++;
int CodeCoverConditionCoverageHelper_C26;

                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[49]++;
                    replaceNode(xmlNode, (XML) xmlValue);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[87]++;
                    replace(index, xmlNode);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[88]++;

                } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[50]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[89]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[51]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[90]++;
                    // Replace the first one, and add the rest on the list.
                    XMLList list = (XMLList) xmlValue;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[91]++;
int CodeCoverConditionCoverageHelper_C28;

                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((list.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[53]++;
                        replaceNode(xmlNode, list.item(0));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[92]++;
                        replace(index, list.item(0));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[93]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[94]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[4]++;


int CodeCoverConditionCoverageHelper_C29;

                        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i < list.length()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[6]++;
}
                            insert(index + i, list.item(i));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[95]++;
                        }

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[54]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[52]++;}
}

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[48]++;
                addToList(xmlValue);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[96]++;
            }
        }
    }

    private XML getXML(XmlNode.InternalList _annos, int index) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[97]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[55]++;
            return xmlFromNode(_annos.item(index));

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[56]++;
            return null;
        }
    }

    @Override
    void deleteXMLProperty(XMLName name) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[98]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[7]++;


int CodeCoverConditionCoverageHelper_C31;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[9]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[99]++;
            XML xml = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[100]++;
int CodeCoverConditionCoverageHelper_C32;

            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((xml.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[57]++;
                xml.deleteXMLProperty(name);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[101]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[58]++;}
        }
    }

    @Override
    public void delete(int index) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[102]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[59]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[103]++;
            XML xml = getXmlFromAnnotation(index);

            xml.remove();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[104]++;

            internalRemoveFromList(index);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[105]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[60]++;}
    }

    @Override
    public Object[] getIds() {
        Object enumObjs[];
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[106]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[61]++;
            enumObjs = new Object[0];
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[107]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[62]++;
            enumObjs = new Object[length()];
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[108]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[109]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[10]++;


int CodeCoverConditionCoverageHelper_C35;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i < enumObjs.length) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[10]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[11]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[12]++;
}
                enumObjs[i] = Integer.valueOf(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[110]++;
            }
        }

        return enumObjs;
    }

    public Object[] getIdsForDebug() {
        return getIds();
    }


    // XMLList will remove will delete all items in the list (a set delete) this differs from the XMLList delete operator.
    void remove() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[111]++;
        int nLen = length();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[112]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[13]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = nLen - 1;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[13]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[14]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[15]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[113]++;
            XML xml = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[114]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((xml != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[63]++;
                xml.remove();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[115]++;
                internalRemoveFromList(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[116]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[64]++;}
        }
    }

    XML item(int index) {
        return _annos != null
            ? getXmlFromAnnotation(index) : createEmptyXML();
    }

    private void setAttribute(XMLName xmlName, Object value) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[117]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[16]++;


int CodeCoverConditionCoverageHelper_C38;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[16]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[17]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[18]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[118]++;
            XML xml = getXmlFromAnnotation(i);
            xml.setAttribute(xmlName, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[119]++;
        }
    }

    void addToList(Object toAdd) {
        _annos.addToList(toAdd);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[120]++;
    }

    //
    //
    // Methods from section 12.4.4 in the spec
    //
    //

    @Override
    XMLList child(int index) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[121]++;
        XMLList result = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[122]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[19]++;


int CodeCoverConditionCoverageHelper_C39;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[19]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[20]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[21]++;
}
            result.addToList(getXmlFromAnnotation(i).child(index));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[123]++;
        }

        return result;
    }

    @Override
    XMLList child(XMLName xmlName) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[124]++;
        XMLList result = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[125]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[22]++;


int CodeCoverConditionCoverageHelper_C40;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[22]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[23]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[24]++;
}
            result.addToList(getXmlFromAnnotation(i).child(xmlName));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[126]++;
        }

        return result;
    }

    @Override
    void addMatches(XMLList rv, XMLName name) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[127]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[25]++;


int CodeCoverConditionCoverageHelper_C41;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i<length()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[25]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[26]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[27]++;
}
            getXmlFromAnnotation(i).addMatches(rv, name);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[128]++;
        }
    }

    @Override
    XMLList children() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[129]++;
        ArrayList<XML> list = new ArrayList<XML>();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[130]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[28]++;


int CodeCoverConditionCoverageHelper_C42;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[28]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[29]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[30]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[131]++;
            XML xml = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[132]++;
int CodeCoverConditionCoverageHelper_C43;

            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((xml != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[65]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[133]++;
                XMLList childList = xml.children();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[134]++;

                int cChildren = childList.length();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[135]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[31]++;


int CodeCoverConditionCoverageHelper_C44;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((j < cChildren) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[31]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[32]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[33]++;
}
                    list.add(childList.item(j));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[136]++;
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[66]++;}
        }
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[137]++;

        XMLList allChildren = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[138]++;
        int sz = list.size();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[139]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[34]++;


int CodeCoverConditionCoverageHelper_C45;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((i < sz) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[34]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[35]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[36]++;
}
            allChildren.addToList(list.get(i));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[140]++;
        }

        return allChildren;
    }

    @Override
    XMLList comments() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[141]++;
        XMLList result = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[142]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[37]++;


int CodeCoverConditionCoverageHelper_C46;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[37]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[38]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[39]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[143]++;
            XML xml = getXmlFromAnnotation(i);
            result.addToList(xml.comments());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[144]++;
        }

        return result;
    }

    @Override
    XMLList elements(XMLName name) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[145]++;
        XMLList rv = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[146]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[40]++;


int CodeCoverConditionCoverageHelper_C47;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((i<length()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[40]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[41]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[42]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[147]++;
            XML xml = getXmlFromAnnotation(i);
            rv.addToList(xml.elements(name));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[148]++;
        }
        return rv;
    }

    @Override
    boolean contains(Object xml) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[149]++;
        boolean result = false;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[150]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[43]++;


int CodeCoverConditionCoverageHelper_C48;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[43]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[44]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[45]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[151]++;
            XML member = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[152]++;
int CodeCoverConditionCoverageHelper_C49;

            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((member.equivalentXml(xml)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[67]++;
                result = true;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[153]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[154]++;
                break;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[68]++;}
        }

        return result;
    }

    @Override
    XMLObjectImpl copy() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[155]++;
        XMLList result = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[156]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[46]++;


int CodeCoverConditionCoverageHelper_C50;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[46]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[47]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[48]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[157]++;
            XML xml = getXmlFromAnnotation(i);
            result.addToList(xml.copy());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[158]++;
        }

        return result;
    }

    @Override
    boolean hasOwnProperty(XMLName xmlName) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[159]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((isPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[69]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[160]++;
            String property = xmlName.localName();
            return (findPrototypeId(property) != 0);

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[70]++;
            return (getPropertyList(xmlName).length() > 0);
        }
    }

    @Override
    boolean hasComplexContent() {
        boolean complexContent;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[161]++;
        int length = length();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[162]++;
int CodeCoverConditionCoverageHelper_C52;

        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[71]++;
            complexContent = false;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[163]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[72]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[164]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((length == 1) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[73]++;
            complexContent = getXmlFromAnnotation(0).hasComplexContent();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[165]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[74]++;
            complexContent = false;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[166]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[167]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[49]++;


int CodeCoverConditionCoverageHelper_C54;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[49]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[50]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[51]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[168]++;
                XML nextElement = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[169]++;
int CodeCoverConditionCoverageHelper_C55;
                if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((nextElement.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[75]++;
                    complexContent = true;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[170]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[171]++;
                    break;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[76]++;}
            }
        }
}

        return complexContent;
    }

    @Override
    boolean hasSimpleContent() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[172]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[77]++;
            return true;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[78]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[173]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[79]++;
            return getXmlFromAnnotation(0).hasSimpleContent();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[80]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[174]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[52]++;


int CodeCoverConditionCoverageHelper_C58;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((i<length()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[52]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[53]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[54]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[175]++;
                XML nextElement = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[176]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((nextElement.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[81]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[82]++;}
            }
            return true;
        }
}
    }

    @Override
    int length() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[177]++;
        int result = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[178]++;
int CodeCoverConditionCoverageHelper_C60;

        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((_annos != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[83]++;
            result = _annos.length();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[179]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[84]++;}

        return result;
    }

    @Override
    void normalize() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[180]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[55]++;


int CodeCoverConditionCoverageHelper_C61;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[55]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[56]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[57]++;
}
            getXmlFromAnnotation(i).normalize();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[181]++;
        }
    }

    /**
     * If list is empty, return undefined, if elements have different parents return undefined,
     * If they all have the same parent, return that parent
     */
    @Override
    Object parent() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[182]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[85]++; return Undefined.instance;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[86]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[183]++;

        XML candidateParent = null;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[184]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[58]++;


int CodeCoverConditionCoverageHelper_C63;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[58]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[59]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[60]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[185]++;
            Object currParent = getXmlFromAnnotation(i).parent();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[186]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((currParent instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[87]++; return Undefined.instance;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[88]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[187]++;
            XML xml = (XML)currParent;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[188]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[89]++;
                // Set the first for the rest to compare to.
                candidateParent = xml;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[189]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[90]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[190]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((candidateParent.is(xml)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[91]++;

                    //    keep looking
                } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[92]++;
                    return Undefined.instance;
                }
            }
        }
        return candidateParent;
    }

    @Override
    XMLList processingInstructions(XMLName xmlName) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[191]++;
        XMLList result = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[192]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[61]++;


int CodeCoverConditionCoverageHelper_C67;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[61]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[62]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[63]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[193]++;
            XML xml = getXmlFromAnnotation(i);

            result.addToList(xml.processingInstructions(xmlName));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[194]++;
        }

        return result;
    }

    @Override
    boolean propertyIsEnumerable(Object name) {
        long index;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[195]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((name instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[93]++;
            index = ((Integer)name).intValue();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[196]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[94]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[197]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((name instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[95]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[198]++;
            double x = ((Number)name).doubleValue();
            index = (long)x;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[199]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[200]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((index != x) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[97]++;
                return false;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[98]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[201]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((1.0 / x < 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[99]++;
                // Negative 0
                return false;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[100]++;}

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[96]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[202]++;
            String s = ScriptRuntime.toString(name);
            index = ScriptRuntime.testUint32String(s);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[203]++;
        }
}
        return (0 <= index && index < length());
    }

    @Override
    XMLList text() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[204]++;
        XMLList result = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[205]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[64]++;


int CodeCoverConditionCoverageHelper_C72;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[64]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[65]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[66]++;
}
            result.addToList(getXmlFromAnnotation(i).text());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[206]++;
        }

        return result;
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[207]++;
int CodeCoverConditionCoverageHelper_C73;
        //    ECMA357 10.1.2
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[101]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[208]++;
            StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[209]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[67]++;


int CodeCoverConditionCoverageHelper_C74;

            for(int i = 0;(((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[67]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[68]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[69]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[210]++;
                XML next = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[211]++;
int CodeCoverConditionCoverageHelper_C75;
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((next.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((next.isProcessingInstruction()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[103]++;

                    //    do nothing
                } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[104]++;
                    sb.append(next.toString());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[212]++;
                }
            }

            return sb.toString();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[102]++;
            return toXMLString();
        }
    }

    @Override
    String toSource(int indent) {
        return toXMLString();
    }

    @Override
    String toXMLString() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[213]++;
        //    See ECMA 10.2.1
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[214]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[70]++;


int CodeCoverConditionCoverageHelper_C76;

        for (int i=0;(((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((i<length()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[70]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[71]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[72]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[215]++;
int CodeCoverConditionCoverageHelper_C77;
            if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((getProcessor().isPrettyPrinting()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[105]++;
                sb.append('\n');
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[216]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[106]++;}
            sb.append(getXmlFromAnnotation(i).toXMLString());
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[217]++;
        }
        return sb.toString();
    }

    @Override
    Object valueOf() {
        return this;
    }

    //
    // Other public Functions from XMLObject
    //

    @Override
    boolean equivalentXml(Object target) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[218]++;
        boolean result = false;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[219]++;
int CodeCoverConditionCoverageHelper_C78;

        // Zero length list should equate to undefined
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((target instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[107]++;
            result = true;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[220]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[108]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[221]++;
int CodeCoverConditionCoverageHelper_C79; if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[109]++;
            result = getXmlFromAnnotation(0).equivalentXml(target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[222]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[110]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[223]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((target instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[111]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[224]++;
            XMLList otherList = (XMLList) target;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[225]++;
int CodeCoverConditionCoverageHelper_C81;

            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((otherList.length() == length()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[113]++;
                result = true;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[226]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[227]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[73]++;


int CodeCoverConditionCoverageHelper_C82;

                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[73]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[74]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[75]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[228]++;
int CodeCoverConditionCoverageHelper_C83;
                    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((getXmlFromAnnotation(i).equivalentXml(otherList.getXmlFromAnnotation(i))) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[115]++;
                        result = false;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[229]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[230]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[116]++;}
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[114]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[112]++;}
}
}

        return result;
    }

    private XMLList getPropertyList(XMLName name) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[231]++;
        XMLList propertyList = newXMLList();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[232]++;
        XmlNode.QName qname = null;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[233]++;
int CodeCoverConditionCoverageHelper_C84;

        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((name.isDescendants()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((name.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[117]++;
            // Only set the targetProperty if this is a regular child get
            // and not a descendant or attribute get
            qname = name.toQname();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[234]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[118]++;}

        propertyList.setTargets(this, qname);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[235]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[236]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[76]++;


int CodeCoverConditionCoverageHelper_C85;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[76]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[77]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[78]++;
}
            propertyList.addToList(
                getXmlFromAnnotation(i).getPropertyList(name));
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[237]++;
        }

        return propertyList;
    }

    private Object applyOrCall(boolean isApply,
        Context cx, Scriptable scope,
        Scriptable thisObj, Object[] args) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[238]++;
        String methodName = isApply ? "apply" : "call";
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[239]++;
int CodeCoverConditionCoverageHelper_C86;
        if((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((thisObj instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((((XMLList)thisObj).targetProperty == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[119]++;
            throw ScriptRuntime.typeError1("msg.isnt.function",
                methodName);
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[120]++;}

        return ScriptRuntime.applyOrCall(isApply, cx, scope, thisObj, args);
    }

    @Override
    protected Object jsConstructor(Context cx, boolean inNewExpr,
                                   Object[] args)
    {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[240]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[121]++;
            return newXMLList();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[122]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[241]++;
            Object arg0 = args[0];
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[242]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((arg0 instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[123]++;
                // XMLList(XMLList) returns the same object.
                return arg0;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[124]++;}
            return newXMLListFrom(arg0);
        }
    }

    /**
     * See ECMA 357, 11_2_2_1, Semantics, 3_e.
     */
    @Override
    public Scriptable getExtraMethodSource(Context cx) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[243]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[125]++;
            return getXmlFromAnnotation(0);

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[126]++;}
        return null;
    }

    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
        Object[] args) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[244]++;
int CodeCoverConditionCoverageHelper_C90;
        // This XMLList is being called as a Function.
        // Let's find the real Function object.
        if((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((targetProperty == null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[127]++;
            throw ScriptRuntime.notFunctionError(this);
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[128]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[245]++;

        String methodName = targetProperty.getLocalName();
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[246]++;

        boolean isApply = methodName.equals("apply");
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[247]++;
int CodeCoverConditionCoverageHelper_C91;
        if((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 ((isApply) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((methodName.equals("call")) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[129]++;
            return applyOrCall(isApply, cx, scope, thisObj, args);
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[130]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[248]++;
int CodeCoverConditionCoverageHelper_C92;

        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((thisObj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[131]++;
            throw ScriptRuntime.typeError1("msg.incompat.call", methodName);

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[132]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[249]++;
        Object func = null;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[250]++;
        Scriptable sobj = thisObj;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[251]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[79]++;


int CodeCoverConditionCoverageHelper_C93;

        while ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((sobj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[79]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[80]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.loops[81]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[252]++;
            XMLObject xmlObject = (XMLObject) sobj;
            func = xmlObject.getFunctionProperty(cx, methodName);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[253]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[254]++;
int CodeCoverConditionCoverageHelper_C94;
            if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((func != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[133]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[255]++;
                break;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[134]++;}
            sobj = xmlObject.getExtraMethodSource(cx);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[256]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[257]++;
int CodeCoverConditionCoverageHelper_C95;
            if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((sobj != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[135]++;
                thisObj = sobj;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[258]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[259]++;
int CodeCoverConditionCoverageHelper_C96;
                if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((sobj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[137]++;
                    func = ScriptableObject.getProperty(sobj, methodName);
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[260]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[138]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[136]++;}
        }
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.statements[261]++;
int CodeCoverConditionCoverageHelper_C97;

        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((func instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[139]++;
            throw ScriptRuntime.notFunctionError(thisObj, func, methodName);

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch.branches[140]++;}
        return ((Callable)func).call(cx, scope, thisObj, args);
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        throw ScriptRuntime.typeError1("msg.not.ctor", "XMLList");
    }
}

class CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch ());
  }
    public static long[] statements = new long[262];
    public static long[] branches = new long[141];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[98];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XMLList.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,2,2,1,1,1,1,1,2,1,2,1,2,1,1,2,1,1,1,1,1,1};
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
    public static long[] loops = new long[82];

  public CodeCoverCoverageCounter$di175yxae5opd1zjygst06hn2cr01x62ch () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLList.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 261; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 140; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 97; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 81; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLList.java");
      for (int i = 1; i <= 261; i++) {
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
    for (int i = 1; i <= 97; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 27; i++) {
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



