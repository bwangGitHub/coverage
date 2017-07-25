/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mozilla.javascript.Undefined;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.UserDataHandler;

class XmlNode implements Serializable {
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.ping();
  }

    private static final String XML_NAMESPACES_NAMESPACE_URI = "http://www.w3.org/2000/xmlns/";
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[1]++;
  }

    private static final String USER_DATA_XMLNODE_KEY = XmlNode.class.getName();
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[2]++;
  }

    private static final boolean DOM_LEVEL_3 = true;
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[3]++;
  }

    private static XmlNode getUserData(Node node) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((DOM_LEVEL_3) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[1]++;
            return (XmlNode)node.getUserData(USER_DATA_XMLNODE_KEY);

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[2]++;}
        return null;
    }

    private static void setUserData(Node node, XmlNode wrap) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((DOM_LEVEL_3) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[3]++;
            node.setUserData(USER_DATA_XMLNODE_KEY, wrap, wrap.events);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[6]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[4]++;}
    }

    private static XmlNode createImpl(Node node) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((node instanceof Document) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[5]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[6]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[8]++;
        XmlNode rv = null;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getUserData(node) == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[7]++;
            rv = new XmlNode();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[10]++;
            rv.dom = node;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[11]++;
            setUserData(node, rv);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[12]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[8]++;
            rv = getUserData(node);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[13]++;
        }
        return rv;
    }

    static XmlNode newElementWithText(XmlProcessor processor, XmlNode reference, XmlNode.QName qname, String value) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((reference instanceof org.w3c.dom.Document) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[9]++; throw new IllegalArgumentException("Cannot use Document node as reference");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[10]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[15]++;
        Document document = null;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((reference != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[11]++;
            document = reference.dom.getOwnerDocument();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[17]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[12]++;
            document = processor.newDocument();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[18]++;
        }
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[19]++;
        Node referenceDom = (reference != null) ? reference.dom : null;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[20]++;
        Namespace ns = qname.getNamespace();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[21]++;
        Element e = (ns == null || ns.getUri().length() == 0)
            ? document.createElementNS(null, qname.getLocalName())
            : document.createElementNS(ns.getUri(),
                                       qname.qualify(referenceDom));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[13]++;
            e.appendChild(document.createTextNode(value));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[23]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[14]++;}
        return XmlNode.createImpl(e);
    }

    static XmlNode createText(XmlProcessor processor, String value) {
        return createImpl( processor.newDocument().createTextNode(value) );
    }

    static XmlNode createElementFromNode(Node node) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node instanceof Document) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[15]++;
            node = ((Document) node).getDocumentElement();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[25]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[16]++;}
        return createImpl(node);
    }

    static XmlNode createElement(XmlProcessor processor, String namespaceUri, String xml) throws org.xml.sax.SAXException {
        return createImpl( processor.toXml(namespaceUri, xml) );
    }

    static XmlNode createEmpty(XmlProcessor processor) {
        return createText(processor, "");
    }

    private static XmlNode copy(XmlNode other) {
        return createImpl( other.dom.cloneNode(true) );
    }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[26]++;
  }

    private UserDataHandler events = new XmlNodeUserDataHandler();
  {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[27]++;
  }

    private Node dom;

    private XML xml;

    private XmlNode() {
    }

    String debug() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[28]++;
        XmlProcessor raw = new XmlProcessor();
        raw.setIgnoreComments(false);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[29]++;
        raw.setIgnoreProcessingInstructions(false);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[30]++;
        raw.setIgnoreWhitespace(false);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[31]++;
        raw.setPrettyPrinting(false);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[32]++;
        return raw.ecmaToXmlString(this.dom);
    }

    @Override
    public String toString() {
        return "XmlNode: type=" + dom.getNodeType() + " dom=" + dom.toString();
    }

    XML getXml() {
        return xml;
    }

    void setXml(XML xml) {
        this.xml = xml;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[33]++;
    }

    int getChildCount() {
        return this.dom.getChildNodes().getLength();
    }

    XmlNode parent() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[34]++;
        Node domParent = dom.getParentNode();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((domParent instanceof Document) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[17]++; return null;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[18]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((domParent == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[19]++; return null;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[20]++;}
        return createImpl(domParent);
    }

    int getChildIndex() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.isAttributeType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[21]++; return -1;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[22]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((parent() == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[23]++; return -1;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[24]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[39]++;
        org.w3c.dom.NodeList siblings = this.dom.getParentNode().getChildNodes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[40]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i<siblings.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[3]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[41]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((siblings.item(i) == dom) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[25]++;
                return i;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[26]++;}
        }
        //    Either the parent is -1 or one of the this node's parent's children is this node.
        throw new RuntimeException("Unreachable.");
    }

    void removeChild(int index) {
        this.dom.removeChild( this.dom.getChildNodes().item(index) );
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[42]++;
    }

    String toXmlString(XmlProcessor processor) {
        return processor.ecmaToXmlString(this.dom);
    }

    String ecmaValue() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[43]++;
int CodeCoverConditionCoverageHelper_C15;
        //    TODO    See ECMA 357 Section 9.1
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isTextType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[27]++;
            return ((org.w3c.dom.Text)dom).getData();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[28]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[44]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isAttributeType()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[29]++;
            return ((org.w3c.dom.Attr)dom).getValue();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[30]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[45]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isProcessingInstructionType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[31]++;
            return ((org.w3c.dom.ProcessingInstruction)dom).getData();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[32]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[46]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isCommentType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[33]++;
            return ((org.w3c.dom.Comment)dom).getNodeValue();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[34]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[47]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isElementType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[35]++;
            throw new RuntimeException("Unimplemented ecmaValue() for elements.");

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[36]++;
            throw new RuntimeException("Unimplemented for node " + dom);
        }
}
}
}
}
    }

    void deleteMe() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[48]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((dom instanceof Attr) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[37]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[49]++;
            Attr attr = (Attr)this.dom;
            attr.getOwnerElement().getAttributes().removeNamedItemNS(attr.getNamespaceURI(), attr.getLocalName());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[50]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[38]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[51]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.dom.getParentNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[39]++;
                this.dom.getParentNode().removeChild(this.dom);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[52]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[40]++;
                //    This case can be exercised at least when executing the regression
                //    tests under https://bugzilla.mozilla.org/show_bug.cgi?id=354145
            }
        }
    }

    void normalize() {
        this.dom.normalize();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[53]++;
    }

    void insertChildAt(int index, XmlNode node) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[54]++;
        Node parent = this.dom;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[55]++;
        Node child = parent.getOwnerDocument().importNode( node.dom, true );
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[56]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((parent.getChildNodes().getLength() < index) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[41]++;
            //    TODO    Check ECMA for what happens here
            throw new IllegalArgumentException("index=" + index + " length=" + parent.getChildNodes().getLength());

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[42]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[57]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((parent.getChildNodes().getLength() == index) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[43]++;
            parent.appendChild(child);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[58]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[44]++;
            parent.insertBefore(child, parent.getChildNodes().item(index));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[59]++;
        }
    }

    void insertChildrenAt(int index, XmlNode[] nodes) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[60]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[4]++;


int CodeCoverConditionCoverageHelper_C24;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i<nodes.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[6]++;
}
            insertChildAt(index+i, nodes[i]);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[61]++;
        }
    }

    XmlNode getChild(int index) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[62]++;
        Node child = dom.getChildNodes().item(index);
        return createImpl(child);
    }

    //    Helper method for XML.hasSimpleContent()
    boolean hasChildElement() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[63]++;
        org.w3c.dom.NodeList nodes = this.dom.getChildNodes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[64]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[7]++;


int CodeCoverConditionCoverageHelper_C25;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i<nodes.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[9]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[65]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((nodes.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[45]++; return true;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[46]++;}
        }
        return false;
    }

    boolean isSameNode(XmlNode other) {
        //    TODO    May need to be changed if we allow XmlNode to refer to several Node objects
        return this.dom == other.dom;
    }

    private String toUri(String ns) {
        return (ns == null) ? "" : ns;
    }

    private void addNamespaces(Namespaces rv, Element element) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[66]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((element == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[47]++; throw new RuntimeException("element must not be null");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[48]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[67]++;
        String myDefaultNamespace = toUri(element.lookupNamespaceURI(null));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[68]++;
        String parentDefaultNamespace = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[69]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((element.getParentNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[49]++;
            parentDefaultNamespace = toUri(element.getParentNode().lookupNamespaceURI(null));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[70]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[50]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[71]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((myDefaultNamespace.equals(parentDefaultNamespace)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((element.getParentNode() instanceof Element) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false) ) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[51]++;
            rv.declare(Namespace.create("", myDefaultNamespace));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[72]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[52]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[73]++;
        NamedNodeMap attributes = element.getAttributes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[74]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[10]++;


int CodeCoverConditionCoverageHelper_C30;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i<attributes.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[10]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[11]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[12]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[75]++;
            Attr attr = (Attr)attributes.item(i);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[76]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((attr.getPrefix() != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((attr.getPrefix().equals("xmlns")) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[53]++;
                rv.declare(Namespace.create(attr.getLocalName(), attr.getValue()));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[77]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[54]++;}
        }
    }

    private Namespaces getAllNamespaces() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[78]++;
        Namespaces rv = new Namespaces();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[79]++;

        Node target = this.dom;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[80]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((target instanceof Attr) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[55]++;
            target = ((Attr)target).getOwnerElement();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[81]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[56]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[82]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[13]++;


int CodeCoverConditionCoverageHelper_C33;
        while((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[13]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[14]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[15]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[83]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((target instanceof Element) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[57]++;
                addNamespaces(rv, (Element)target);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[84]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[58]++;}
            target = target.getParentNode();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[85]++;
        }
        //    Fallback in case no namespace was declared
        rv.declare(Namespace.create("", ""));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[86]++;
        return rv;
    }

    Namespace[] getInScopeNamespaces() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[87]++;
        Namespaces rv = getAllNamespaces();
        return rv.getNamespaces();
    }

    Namespace[] getNamespaceDeclarations() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[88]++;
int CodeCoverConditionCoverageHelper_C35;
        //    ECMA357 13.4.4.24
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.dom instanceof Element) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[59]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[89]++;
            Namespaces rv = new Namespaces();
            addNamespaces( rv, (Element)this.dom );
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[90]++;
            return rv.getNamespaces();

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[60]++;
            return new Namespace[0];
        }
    }

    Namespace getNamespaceDeclaration(String prefix) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[91]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((prefix.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((dom instanceof Attr) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[61]++;
            //    Default namespaces do not apply to attributes; see XML Namespaces section 5.2
            return Namespace.create("", "");

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[62]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[92]++;
        Namespaces rv = getAllNamespaces();
        return rv.getNamespace(prefix);
    }

    Namespace getNamespaceDeclaration() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[93]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((dom.getPrefix() == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[63]++; return getNamespaceDeclaration("");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[64]++;}
        return getNamespaceDeclaration(dom.getPrefix());
    }

    static class XmlNodeUserDataHandler implements UserDataHandler, Serializable {
        private static final long serialVersionUID = 4666895518900769588L;

        public void handle(short operation, String key, Object data, Node src, Node dest) {
        }
    }

    private static class Namespaces {
        private Map<String,String> map = new HashMap<String,String>();
  {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[94]++;
  }
        private Map<String,String> uriToPrefix = new HashMap<String,String>();
  {
    CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[95]++;
  }

        Namespaces() {
        }

        void declare(Namespace n) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[96]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((map.get(n.prefix) == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[65]++;
                map.put(n.prefix, n.uri);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[97]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[66]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[98]++;
int CodeCoverConditionCoverageHelper_C39;
            //    TODO    I think this is analogous to the other way, but have not really thought it through ... should local scope
            //            matter more than outer scope?
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((uriToPrefix.get(n.uri) == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[67]++;
                uriToPrefix.put(n.uri, n.prefix);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[99]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[68]++;}
        }

        Namespace getNamespaceByUri(String uri) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[100]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((uriToPrefix.get(uri) == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[69]++; return null;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[70]++;}
            return Namespace.create(uri, uriToPrefix.get(uri));
        }

        Namespace getNamespace(String prefix) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[101]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((map.get(prefix) == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[71]++; return null;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[72]++;}
            return Namespace.create(prefix, map.get(prefix));
        }

        Namespace[] getNamespaces() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[102]++;
            ArrayList<Namespace> rv = new ArrayList<Namespace>();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[103]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[16]++;


            for (String prefix: map.keySet()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[16]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[17]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[18]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[104]++;
                String uri = map.get(prefix);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[105]++;
                Namespace n = Namespace.create(prefix, uri);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[106]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((n.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[73]++;
                    rv.add(n);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[107]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[74]++;}
            }
            return rv.toArray(new Namespace[rv.size()]);
        }
    }

    final XmlNode copy() {
        return copy( this );
    }

    //    Returns whether this node is capable of being a parent
    final boolean isParentType() {
        return isElementType();
    }

    final boolean isTextType() {
        return dom.getNodeType() == Node.TEXT_NODE || dom.getNodeType() == Node.CDATA_SECTION_NODE;
    }

    final boolean isAttributeType() {
        return dom.getNodeType() == Node.ATTRIBUTE_NODE;
    }

    final boolean isProcessingInstructionType() {
        return dom.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE;
    }

    final boolean isCommentType() {
        return dom.getNodeType() == Node.COMMENT_NODE;
    }

    final boolean isElementType() {
        return dom.getNodeType() == Node.ELEMENT_NODE;
    }

    final void renameNode(QName qname) {
        this.dom = dom.getOwnerDocument().renameNode(dom, qname.getNamespace().getUri(), qname.qualify(dom));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[108]++;
    }

    void invalidateNamespacePrefix() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[109]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((dom instanceof Element) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[75]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[76]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[110]++;
        String prefix = this.dom.getPrefix();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[111]++;
        QName after = QName.create(this.dom.getNamespaceURI(), this.dom.getLocalName(), null);
        renameNode(after);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[112]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[113]++;
        NamedNodeMap attrs = this.dom.getAttributes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[114]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[19]++;


int CodeCoverConditionCoverageHelper_C44;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i<attrs.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[19]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[20]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[21]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[115]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((attrs.item(i).getPrefix().equals(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[77]++;
                createImpl( attrs.item(i) ).renameNode( QName.create(attrs.item(i).getNamespaceURI(), attrs.item(i).getLocalName(), null) );
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[116]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[78]++;}
        }
    }

    private void declareNamespace(Element e, String prefix, String uri) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[117]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((prefix.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[79]++;
            e.setAttributeNS(XML_NAMESPACES_NAMESPACE_URI, "xmlns:" + prefix, uri);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[118]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[80]++;
            e.setAttribute("xmlns", uri);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[119]++;
        }
    }

    void declareNamespace(String prefix, String uri) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[120]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((dom instanceof Element) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[81]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[82]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[121]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((dom.lookupNamespaceURI(uri) != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((dom.lookupNamespaceURI(uri).equals(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[83]++;

            //    do nothing
        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[84]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[122]++;
            Element e = (Element)dom;
            declareNamespace(e, prefix, uri);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[123]++;
        }
    }

    private Namespace getDefaultNamespace() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[124]++;
        String prefix = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[125]++;
        String uri = (dom.lookupNamespaceURI(null) == null) ? "" : dom.lookupNamespaceURI(null);
        return Namespace.create(prefix, uri);
    }

    private String getExistingPrefixFor(Namespace namespace) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[126]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((getDefaultNamespace().getUri().equals(namespace.getUri())) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[85]++;
            return "";

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[86]++;}
        return dom.lookupPrefix(namespace.getUri());
    }

    private Namespace getNodeNamespace() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[127]++;
        String uri = dom.getNamespaceURI();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[128]++;
        String prefix = dom.getPrefix();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[129]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[87]++; uri = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[130]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[88]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[131]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[89]++; prefix = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[132]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[90]++;}
        return Namespace.create(prefix, uri);
    }

    Namespace getNamespace() {
        return getNodeNamespace();
    }

    void removeNamespace(Namespace namespace) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[133]++;
        Namespace current = getNodeNamespace();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[134]++;
int CodeCoverConditionCoverageHelper_C52;

        //    Do not remove in-use namespace
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((namespace.is(current)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[91]++; return;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[92]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[135]++;
        NamedNodeMap attrs = this.dom.getAttributes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[136]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[22]++;


int CodeCoverConditionCoverageHelper_C53;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((i<attrs.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[22]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[23]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[24]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[137]++;
            XmlNode attr = XmlNode.createImpl(attrs.item(i));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[138]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((namespace.is(attr.getNodeNamespace())) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[93]++; return;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[94]++;}
        }
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[139]++;

        //    TODO    I must confess I am not sure I understand the spec fully.  See ECMA357 13.4.4.31
        String existingPrefix = getExistingPrefixFor(namespace);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[140]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((existingPrefix != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[95]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[141]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((namespace.isUnspecifiedPrefix()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[97]++;
                //    we should remove any namespace with this URI from scope; we do this by declaring a namespace with the same
                //    prefix as the existing prefix and setting its URI to the default namespace
                declareNamespace(existingPrefix, getDefaultNamespace().getUri());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[142]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[98]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[143]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((existingPrefix.equals(namespace.getPrefix())) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[99]++;
                    declareNamespace(existingPrefix, getDefaultNamespace().getUri());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[144]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[100]++;}
            }

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[96]++;
            //    the argument namespace is not declared in this scope, so do nothing.
        }
    }

    private void setProcessingInstructionName(String localName) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[145]++;
        org.w3c.dom.ProcessingInstruction pi = (ProcessingInstruction)this.dom;
        //    We cannot set the node name; Document.renameNode() only supports elements and attributes.  So we replace it
        pi.getParentNode().replaceChild(
            pi,
            pi.getOwnerDocument().createProcessingInstruction(localName, pi.getData())
        );
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[146]++;
    }

    final void setLocalName(String localName) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[147]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((dom instanceof ProcessingInstruction) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[101]++;
            setProcessingInstructionName(localName);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[148]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[102]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[149]++;
            String prefix = dom.getPrefix();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[150]++;
int CodeCoverConditionCoverageHelper_C59;
            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[103]++; prefix = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[151]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[104]++;}
            this.dom = dom.getOwnerDocument().renameNode(dom, dom.getNamespaceURI(), QName.qualify(prefix, localName));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[152]++;
        }
    }

    final QName getQname() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[153]++;
        String uri = (dom.getNamespaceURI()) == null ? "" : dom.getNamespaceURI();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[154]++;
        String prefix = (dom.getPrefix() == null) ? "" : dom.getPrefix();
        return QName.create( uri, dom.getLocalName(), prefix );
    }

    void addMatchingChildren(XMLList result, XmlNode.Filter filter) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[155]++;
        Node node = this.dom;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[156]++;
        NodeList children = node.getChildNodes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[157]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[25]++;


int CodeCoverConditionCoverageHelper_C60;
        for(int i=0;(((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((i<children.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[25]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[26]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[27]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[158]++;
            Node childnode = children.item(i);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[159]++;
            XmlNode child = XmlNode.createImpl(childnode);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[160]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((filter.accept(childnode)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[105]++;
                result.addToList(child);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[161]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[106]++;}
        }
    }

    XmlNode[] getMatchingChildren(Filter filter) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[162]++;
        ArrayList<XmlNode> rv = new ArrayList<XmlNode>();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[163]++;
        NodeList nodes = this.dom.getChildNodes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[164]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[28]++;


int CodeCoverConditionCoverageHelper_C62;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((i<nodes.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[28]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[29]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[30]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[165]++;
            Node node = nodes.item(i);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[166]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((filter.accept(node)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[107]++;
                rv.add(createImpl(node));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[167]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[108]++;}
        }
        return rv.toArray(new XmlNode[rv.size()]);
    }

    XmlNode[] getAttributes() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[168]++;
        NamedNodeMap attrs = this.dom.getAttributes();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[169]++;
int CodeCoverConditionCoverageHelper_C64;
        //    TODO    Or could make callers handle null?
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((attrs == null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[109]++; throw new IllegalStateException("Must be element.");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[110]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[170]++;
        XmlNode[] rv = new XmlNode[attrs.getLength()];
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[171]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[31]++;


int CodeCoverConditionCoverageHelper_C65;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((i<attrs.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[31]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[32]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[33]++;
}
            rv[i] = createImpl( attrs.item(i) );
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[172]++;
        }
        return rv;
    }

    String getAttributeValue() {
        return ((Attr)dom).getValue();
    }

    void setAttribute(QName name, String value) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[173]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((dom instanceof Element) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[111]++; throw new IllegalStateException("Can only set attribute on elements.");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[112]++;}
        name.setAttribute( (Element)dom, value );
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[174]++;
    }

    void replaceWith(XmlNode other) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[175]++;
        Node replacement = other.dom;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[176]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((replacement.getOwnerDocument() != this.dom.getOwnerDocument()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[113]++;
            replacement = this.dom.getOwnerDocument().importNode(replacement, true);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[177]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[114]++;}
        this.dom.getParentNode().replaceChild(replacement, this.dom);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[178]++;
    }

    String ecmaToXMLString(XmlProcessor processor) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[179]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.isElementType()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[115]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[180]++;
            Element copy = (Element)this.dom.cloneNode(true);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[181]++;
            Namespace[] inScope = this.getInScopeNamespaces();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[182]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[34]++;


int CodeCoverConditionCoverageHelper_C69;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((i<inScope.length) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[34]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[35]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[36]++;
}
                declareNamespace(copy, inScope[i].getPrefix(), inScope[i].getUri());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[183]++;
            }
            return processor.ecmaToXmlString(copy);

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[116]++;
            return processor.ecmaToXmlString(dom);
        }
    }

    static class Namespace implements Serializable {

        /**
         * Serial version id for Namespace with fields prefix and uri
         */
        private static final long serialVersionUID = 4073904386884677090L;

        static Namespace create(String prefix, String uri) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[184]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[117]++;
                throw new IllegalArgumentException(
                        "Empty string represents default namespace prefix");

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[118]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[185]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[119]++;
                throw new IllegalArgumentException(
                        "Namespace may not lack a URI");

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[120]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[186]++;
            Namespace rv = new Namespace();
            rv.prefix = prefix;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[187]++;
            rv.uri = uri;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[188]++;
            return rv;
        }

        static Namespace create(String uri) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[189]++;
            Namespace rv = new Namespace();
            rv.uri = uri;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[190]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[191]++;
int CodeCoverConditionCoverageHelper_C72;

            // Avoid null prefix for "" namespace
            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((uri == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((uri.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[121]++;
                rv.prefix = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[192]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[122]++;}

            return rv;
        }

        static final Namespace GLOBAL = create("", "");

        private String prefix;
        private String uri;

        private Namespace() {
        }

        @Override
        public String toString() {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[193]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[123]++; return "XmlNode.Namespace [" + uri + "]";
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[124]++;}
            return "XmlNode.Namespace [" + prefix + "{" + uri + "}]";
        }

        boolean isUnspecifiedPrefix() {
            return prefix == null;
        }

        boolean is(Namespace other) {
            return this.prefix != null && other.prefix != null && this.prefix.equals(other.prefix) && this.uri.equals(other.uri);
        }

        boolean isEmpty() {
            return prefix != null && prefix.equals("") && uri.equals("");
        }

        boolean isDefault() {
            return prefix != null && prefix.equals("");
        }

        boolean isGlobal() {
            return uri != null && uri.equals("");
        }

        //    Called by QName
        //    TODO    Move functionality from QName lookupPrefix to here
        private void setPrefix(String prefix) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[194]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[125]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[126]++;}
            this.prefix = prefix;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[195]++;
        }

        String getPrefix() {
            return prefix;
        }

        String getUri() {
            return uri;
        }
    }

    //    TODO    Where is this class used?  No longer using it in QName implementation
    static class QName implements Serializable {
        private static final long serialVersionUID = -6587069811691451077L;

        static QName create(Namespace namespace, String localName) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[196]++;
int CodeCoverConditionCoverageHelper_C75;
            //    A null namespace indicates a wild-card match for any namespace
            //    A null localName indicates "*" from the point of view of ECMA357
            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((localName != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((localName.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[127]++; throw new RuntimeException("* is not valid localName");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[128]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[197]++;
            QName rv = new QName();
            rv.namespace = namespace;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[198]++;
            rv.localName = localName;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[199]++;
            return rv;
        }

        /** @deprecated */
        static QName create(String uri, String localName, String prefix) {
            return create(Namespace.create(prefix, uri), localName);
        }

        static String qualify(String prefix, String localName) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[200]++;
int CodeCoverConditionCoverageHelper_C76;
            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[129]++; throw new IllegalArgumentException("prefix must not be null");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[130]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[201]++;
int CodeCoverConditionCoverageHelper_C77;
            if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((prefix.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[131]++; return prefix + ":" + localName;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[132]++;}
            return localName;
        }

        private Namespace namespace;
        private String localName;

        private QName() {
        }

        @Override
        public String toString() {
            return "XmlNode.QName [" + localName + "," + namespace + "]";
        }

        private boolean equals(String one, String two) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[202]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((one == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((two == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[133]++; return true;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[134]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[203]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((one == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((two == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[135]++; return false;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[136]++;}
            return one.equals(two);
        }

        private boolean namespacesEqual(Namespace one, Namespace two) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[204]++;
int CodeCoverConditionCoverageHelper_C80;
            if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((one == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((two == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[137]++; return true;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[138]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[205]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((one == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((two == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[139]++; return false;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[140]++;}
            return equals(one.getUri(), two.getUri());
        }

        final boolean equals(QName other) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[206]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((namespacesEqual(this.namespace, other.namespace)) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[141]++; return false;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[142]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[207]++;
int CodeCoverConditionCoverageHelper_C83;
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((equals(this.localName, other.localName)) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[143]++; return false;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[144]++;}
            return true;
        }

        @Override
        public boolean equals(Object obj) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[208]++;
int CodeCoverConditionCoverageHelper_C84;
            if((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((obj instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[145]++;
                return false;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[146]++;}
            return equals((QName)obj);
        }

        @Override
        public int hashCode() {
            return localName == null ? 0 : localName.hashCode();
        }

        void lookupPrefix(org.w3c.dom.Node node) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[209]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[147]++; throw new IllegalArgumentException("node must not be null");
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[148]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[210]++;
            String prefix = node.lookupPrefix(namespace.getUri());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[211]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[149]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[212]++;
                //    check to see if we match the default namespace
                String defaultNamespace = node.lookupNamespaceURI(null);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[213]++;
int CodeCoverConditionCoverageHelper_C87;
                if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((defaultNamespace == null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[151]++; defaultNamespace = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[214]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[152]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[215]++;
                String nodeNamespace = namespace.getUri();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[216]++;
int CodeCoverConditionCoverageHelper_C88;
                if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((nodeNamespace.equals(defaultNamespace)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[153]++;
                    prefix = "";
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[217]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[154]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[150]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[218]++;
            int i = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[219]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[37]++;


int CodeCoverConditionCoverageHelper_C89;
            while((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[37]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[38]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[39]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[220]++;
                String generatedPrefix = "e4x_" + i++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[221]++;
                String generatedUri = node.lookupNamespaceURI(generatedPrefix);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[222]++;
int CodeCoverConditionCoverageHelper_C90;
                if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((generatedUri == null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[155]++;
                    prefix = generatedPrefix;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[223]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[224]++;
                    org.w3c.dom.Node top = node;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[225]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[40]++;


int CodeCoverConditionCoverageHelper_C91;
                    while((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 ((top.getParentNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((top.getParentNode() instanceof org.w3c.dom.Element) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[40]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[41]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[42]++;
}
                        top = top.getParentNode();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[226]++;
                    }
                    ((org.w3c.dom.Element)top).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, namespace.getUri());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[227]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[156]++;}
            }
            namespace.setPrefix(prefix);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[228]++;
        }

        String qualify(org.w3c.dom.Node node) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[229]++;
int CodeCoverConditionCoverageHelper_C92;
            if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((namespace.getPrefix() == null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[157]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[230]++;
int CodeCoverConditionCoverageHelper_C93;
                if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[159]++;
                    lookupPrefix(node);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[231]++;

                } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[160]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[232]++;
int CodeCoverConditionCoverageHelper_C94;
                    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((namespace.getUri().equals("")) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[161]++;
                        namespace.setPrefix("");
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[233]++;

                    } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[162]++;
                        //    TODO    I am not sure this is right, but if we are creating a standalone node, I think we can set the
                        //            default namespace on the node itself and not worry about setting a prefix for that namespace.
                        namespace.setPrefix("");
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[234]++;
                    }
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[158]++;}
            return qualify(namespace.getPrefix(), localName);
        }

        void setAttribute(org.w3c.dom.Element element, String value) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[235]++;
int CodeCoverConditionCoverageHelper_C95;
            if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((namespace.getPrefix() == null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[163]++; lookupPrefix(element);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[236]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[164]++;}
            element.setAttributeNS(namespace.getUri(), qualify(namespace.getPrefix(), localName), value);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[237]++;
        }

        Namespace getNamespace() {
            return namespace;
        }

        String getLocalName() {
            return localName;
        }
    }

    static class InternalList implements Serializable {
        private static final long serialVersionUID = -3633151157292048978L;
        private List<XmlNode> list;

        InternalList() {
            list = new ArrayList<XmlNode>();
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[238]++;
        }

        private void _add(XmlNode n) {
            list.add(n);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[239]++;
        }

        XmlNode item(int index) {
            return list.get(index);
        }

        void remove(int index) {
            list.remove(index);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[240]++;
        }

        void add(InternalList other) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[241]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[43]++;


int CodeCoverConditionCoverageHelper_C96;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((i<other.length()) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[43]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[44]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[45]++;
}
                _add(other.item(i));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[242]++;
            }
        }

        void add(InternalList from, int startInclusive, int endExclusive) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[243]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[46]++;


int CodeCoverConditionCoverageHelper_C97;
            for (int i=startInclusive;(((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((i<endExclusive) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[46]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[47]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[48]++;
}
                _add(from.item(i));
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[244]++;
            }
        }

        void add(XmlNode node) {
            _add(node);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[245]++;
        }

        /* TODO: was marked deprecated by original author */
        void add(XML xml) {
            _add(xml.getAnnotation());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[246]++;
        }

        /* TODO: was marked deprecated by original author */
        void addToList(Object toAdd) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[247]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((toAdd instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[165]++;
                // Missing argument do nothing...
                return;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[166]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[248]++;
int CodeCoverConditionCoverageHelper_C99;

            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((toAdd instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[167]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[249]++;
                XMLList xmlSrc = (XMLList)toAdd;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[250]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[49]++;


int CodeCoverConditionCoverageHelper_C100;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((i < xmlSrc.length()) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[49]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[50]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.loops[51]++;
}
                    this._add((xmlSrc.item(i)).getAnnotation());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[251]++;
                }

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[168]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[252]++;
int CodeCoverConditionCoverageHelper_C101; if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((toAdd instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[169]++;
                this._add(((XML)(toAdd)).getAnnotation());
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[253]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[170]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[254]++;
int CodeCoverConditionCoverageHelper_C102; if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((toAdd instanceof XmlNode) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[171]++;
                this._add((XmlNode)toAdd);
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[255]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[172]++;}
}
}
        }

        int length() {
            return list.size();
        }
    }

    static abstract class Filter {
        static final Filter COMMENT = new Filter() {
            @Override
            boolean accept(Node node) {
                return node.getNodeType() == Node.COMMENT_NODE;
            }
        };
        static final Filter TEXT = new Filter() {
            @Override
            boolean accept(Node node) {
                return node.getNodeType() == Node.TEXT_NODE;
            }
        };
        static Filter PROCESSING_INSTRUCTION(final XMLName name) {
            return new Filter() {
                @Override
                boolean accept(Node node) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[256]++;
int CodeCoverConditionCoverageHelper_C103;
                    if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((node.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[173]++;
CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.statements[257]++;
                        ProcessingInstruction pi = (ProcessingInstruction)node;
                        return name.matchesLocalName(pi.getTarget());

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5.branches[174]++;}
                    return false;
                }
            };
        }
        static Filter ELEMENT = new Filter() {
            @Override
            boolean accept(Node node) {
                return node.getNodeType() == Node.ELEMENT_NODE;
            }
        };
        static Filter TRUE = new Filter() {
            @Override
            boolean accept(Node node) {
                return true;
            }
        };
        abstract boolean accept(Node node);
    }

    //    Support experimental Java interface
    org.w3c.dom.Node toDomNode() {
        return this.dom;
    }
}

class CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5 ());
  }
    public static long[] statements = new long[258];
    public static long[] branches = new long[175];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[104];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XmlNode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 103; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[52];

  public CodeCoverCoverageCounter$di175yxae5opd1zjyloh8qrg5xkpxfwrj5 () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XmlNode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 257; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 174; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 103; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 51; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XmlNode.java");
      for (int i = 1; i <= 257; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 174; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 103; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 17; i++) {
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

