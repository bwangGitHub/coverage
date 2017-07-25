/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import java.io.Serializable;
import java.util.*;

import org.mozilla.javascript.*;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlCursor.XmlBookmark;
import org.apache.xmlbeans.XmlCursor.TokenType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

class XML extends XMLObjectImpl
{
  static {
    CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.ping();
  }

    static final long serialVersionUID = -630969919086449092L;
  static {
    CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[1]++;
  }

    final static class XScriptAnnotation extends XmlBookmark implements Serializable
    {
        private static final long serialVersionUID = 1L;

        javax.xml.namespace.QName _name;
        XML _xScriptXML;


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //
        //  Constructurs
        //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        XScriptAnnotation (XmlCursor curs)
        {
            _name = curs.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[2]++;
        }

    }

    /**
     *
     */
    final static class NamespaceDeclarations
    {
        private int             _prefixIdx;
        private StringBuffer    _namespaceDecls;
        private String          _defaultNSURI;


        NamespaceDeclarations (XmlCursor curs)
        {
            _prefixIdx = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[3]++;
            _namespaceDecls = new StringBuffer();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[4]++;

            skipNonElements(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[5]++;
            _defaultNSURI = curs.namespaceForPrefix("");
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[6]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isAnyDefaultNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[1]++;
                addDecl("", _defaultNSURI);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[8]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[2]++;}
        }


        private void addDecl (String prefix, String ns)
        {
            _namespaceDecls.append((prefix.length() > 0 ?
                                        "declare namespace " + prefix :
                                        "default element namespace") +
                                    " = \"" + ns + "\"" + "\n");
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[9]++;
        }


        String getNextPrefix (String ns)
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[10]++;
            String prefix = "NS" + _prefixIdx++;

            _namespaceDecls.append("declare namespace " + prefix + " = " + "\"" + ns + "\"" + "\n");
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[11]++;

            return prefix;
        }


        boolean isAnyDefaultNamespace ()
        {
            return _defaultNSURI != null ?_defaultNSURI.length() > 0 : false;
        }


        String getDeclarations()
        {
            return _namespaceDecls.toString();
        }
    }

    // Fields
    //static final XML prototype = new XML();
    private XScriptAnnotation _anno;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Constructors
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param anno
     */
    private XML(XMLLibImpl lib, XScriptAnnotation anno)
    {
        super(lib, lib.xmlPrototype);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[12]++;
        _anno = anno;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[13]++;
        _anno._xScriptXML = this;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[14]++;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Public factories for creating a XScript XML object given an XBean cursor.
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    static XML createEmptyXML(XMLLibImpl lib)
    {
        XScriptAnnotation anno;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[15]++;

        XmlObject xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[16]++;
        XmlCursor curs = xo.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[17]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            anno = new XScriptAnnotation(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[18]++;
            curs.setBookmark(anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[19]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[3]++;
}
            curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[20]++;
        }

        return new XML(lib, anno);
    }

    private static XML createXML (XMLLibImpl lib, XmlCursor curs)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[4]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[22]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[5]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[23]++;

        XScriptAnnotation anno = findAnnotation(curs);

        return new XML(lib, anno);
    }

    /**
     * Special constructor for making an attribute
     *
     */
    private static XML createAttributeXML(XMLLibImpl lib, XmlCursor cursor)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((cursor.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[6]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[7]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[25]++;

        XScriptAnnotation anno = new XScriptAnnotation(cursor);
        cursor.setBookmark(anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[26]++;

        return new XML(lib, anno);
    }


    /**
     *
     * @param qname
     * @param value
     * @return
     */
    static XML createTextElement(XMLLibImpl lib, javax.xml.namespace.QName qname, String value)
    {
        XScriptAnnotation anno;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[27]++;

        XmlObject xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[28]++;
        XmlCursor cursor = xo.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[29]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            cursor.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[30]++;

            cursor.beginElement(qname.getLocalPart(), qname.getNamespaceURI());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[31]++;
            //if(namespace.length() > 0)
            //    cursor.insertNamespace("", namespace);
            cursor.insertChars(value);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[32]++;

            cursor.toStartDoc();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[33]++;
            cursor.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[34]++;
            anno = new XScriptAnnotation(cursor);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[35]++;
            cursor.setBookmark(anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[36]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[8]++;
}
            cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[37]++;
        }

        return new XML(lib, anno);
    }

    static XML createFromXmlObject(XMLLibImpl lib, XmlObject xo)
    {
        XScriptAnnotation anno;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[38]++;
        XmlCursor curs = xo.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[39]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[9]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[40]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[10]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[41]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            anno = new XScriptAnnotation(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[42]++;
            curs.setBookmark(anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[43]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[11]++;
}
            curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[44]++;
        }
        return new XML(lib, anno);
    }

    static XML createFromJS(XMLLibImpl lib, Object inputObject)
    {
        XmlObject xo;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[45]++;
        boolean isText = false;
        String frag;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[46]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((inputObject == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((inputObject == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[12]++;
            frag = "";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[47]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[13]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[48]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((inputObject instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[14]++;
            // todo: faster way for XMLObjects?
            frag = ((XMLObjectImpl) inputObject).toXMLString(0);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[49]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[15]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[50]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((inputObject instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[16]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[51]++;
                Object wrapped = ((Wrapper)inputObject).unwrap();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[52]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((wrapped instanceof XmlObject) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[18]++;
                    return createFromXmlObject(lib, (XmlObject)wrapped);

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[19]++;}

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[17]++;}
            frag = ScriptRuntime.toString(inputObject);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[53]++;
        }
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((frag.trim().startsWith("<>")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[20]++;
            throw ScriptRuntime.typeError("Invalid use of XML object anonymous tags <></>.");

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[21]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((frag.indexOf("<") == -1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[22]++;
            // Must be solo text node, wrap in XML fragment
            isText = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[56]++;
            frag = "<textFragment>" + frag + "</textFragment>";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[57]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[23]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[58]++;

        XmlOptions options = new XmlOptions();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[59]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((lib.ignoreComments) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[24]++;
            options.put(XmlOptions.LOAD_STRIP_COMMENTS);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[60]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[25]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[61]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lib.ignoreProcessingInstructions) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[26]++;
            options.put(XmlOptions.LOAD_STRIP_PROCINSTS);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[62]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[27]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[63]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((lib.ignoreWhitespace) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[28]++;
            options.put(XmlOptions.LOAD_STRIP_WHITESPACE);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[64]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[29]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[65]++;
boolean CodeCoverTryBranchHelper_Try4 = false;

        try
        {
CodeCoverTryBranchHelper_Try4 = true;
            xo = XmlObject.Factory.parse(frag, options);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[66]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[67]++;

            // Apply the default namespace
            Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[68]++;
            String defaultURI = lib.getDefaultNamespaceURI(cx);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[69]++;
int CodeCoverConditionCoverageHelper_C14;

            if((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((defaultURI.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[31]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[70]++;
                XmlCursor cursor = xo.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[71]++;
                boolean isRoot = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[72]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[1]++;


int CodeCoverConditionCoverageHelper_C15;
                while((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((cursor.toNextToken().isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false))
                {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[1]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[2]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[3]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[73]++;
int CodeCoverConditionCoverageHelper_C16;
                    if((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((cursor.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[33]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[74]++; continue;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[34]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[75]++;

                    // Check if this element explicitly sets the
                    // default namespace
                    boolean defaultNSDeclared = false;
                    cursor.push();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[76]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[77]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;
                    while((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((cursor.toNextToken().isAnyAttr()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false))
                    {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[4]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[5]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[6]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[78]++;
int CodeCoverConditionCoverageHelper_C18;
                        if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((cursor.isNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[35]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[79]++;
int CodeCoverConditionCoverageHelper_C19;
                            if((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((cursor.getName().getLocalPart().length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false))
                            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[37]++;
                                defaultNSDeclared = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[80]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[81]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[38]++;}

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[36]++;}
                    }
                    cursor.pop();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[82]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[83]++;
int CodeCoverConditionCoverageHelper_C20;
                    if((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((defaultNSDeclared) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[39]++;
                        cursor.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[84]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[85]++;
                        continue;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[40]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[86]++;

                    // Check if this element's name is in no namespace
                    javax.xml.namespace.QName qname = cursor.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;
                    if((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((qname.getNamespaceURI().length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[41]++;
                        // Change the namespace
                        qname = new javax.xml.namespace.QName(defaultURI,
                                                              qname.getLocalPart());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[88]++;
                        cursor.setName(qname);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[89]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[42]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[90]++;
int CodeCoverConditionCoverageHelper_C22;

                    if((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isRoot) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[43]++;
                        // Declare the default namespace
                        cursor.push();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[91]++;
                        cursor.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[92]++;
                        cursor.insertNamespace("", defaultURI);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[93]++;
                        cursor.pop();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[94]++;

                        isRoot = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[95]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[44]++;}
                }
                cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[96]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[32]++;}
        }
        catch (XmlException xe)
        {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[45]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[97]++;
/*
todo need to handle namespace prefix not found in XML look for namespace type in the scope change.

            String errorMsg = "Use of undefined namespace prefix: ";
            String msg = xe.getError().getMessage();
            if (msg.startsWith(errorMsg))
            {
                String prefix = msg.substring(errorMsg.length());
            }
*/
            String errMsg = xe.getMessage();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[98]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((errMsg.equals("error: Unexpected end of file after null")) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[46]++;
                // Create an empty document.
                xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[99]++;

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[47]++;
                throw ScriptRuntime.typeError(xe.getMessage());
            }
        }
        catch (Throwable e)
        {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[48]++;
            // todo: TLL Catch specific exceptions during parse.
            throw ScriptRuntime.typeError("Not Parsable as XML");
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[30]++;
}
  }
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[100]++;

        XmlCursor curs = xo.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[101]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[49]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[102]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[50]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[103]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isText) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[51]++;
            // Move it to point to the text node
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[104]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[52]++;}

        XScriptAnnotation anno;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[105]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
        try
        {
CodeCoverTryBranchHelper_Try5 = true;
            anno = new XScriptAnnotation(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[106]++;
            curs.setBookmark(anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[107]++;
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[53]++;
}
            curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[108]++;
        }

        return new XML(lib, anno);
    }

    static XML getFromAnnotation(XMLLibImpl lib, XScriptAnnotation anno)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[109]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((anno._xScriptXML == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[54]++;
            anno._xScriptXML = new XML(lib, anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[110]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[55]++;}

        return anno._xScriptXML;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Private functions:
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param curs
     * @return
     */
    private static TokenType skipNonElements (XmlCursor curs)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[111]++;
        TokenType tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[112]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[7]++;


int CodeCoverConditionCoverageHelper_C27;
        while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((tt.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((tt.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false))
        {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[7]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[8]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[9]++;
}
            tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[113]++;
        }

        return tt;
    }

    /**
     *
     * @param curs
     * @return
     */
    protected static XScriptAnnotation findAnnotation(XmlCursor curs)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[114]++;
        XmlBookmark anno = curs.getBookmark(XScriptAnnotation.class);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[115]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((anno == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[56]++;
            anno = new XScriptAnnotation(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[116]++;
            curs.setBookmark(anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[117]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[57]++;}

        return (XScriptAnnotation)anno;
    }

    /**
     *
     * @return
     */
    private XmlOptions getOptions()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[118]++;
        XmlOptions options = new XmlOptions();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[119]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((lib.ignoreComments) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[58]++;
            options.put(XmlOptions.LOAD_STRIP_COMMENTS);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[120]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[59]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[121]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((lib.ignoreProcessingInstructions) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[60]++;
            options.put(XmlOptions.LOAD_STRIP_PROCINSTS);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[122]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[61]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[123]++;
int CodeCoverConditionCoverageHelper_C31;

        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((lib.ignoreWhitespace) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[62]++;
            options.put(XmlOptions.LOAD_STRIP_WHITESPACE);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[124]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[63]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[125]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((lib.prettyPrinting) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[64]++;
            options.put(XmlOptions.SAVE_PRETTY_PRINT, null);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[126]++;
            options.put(XmlOptions.SAVE_PRETTY_PRINT_INDENT, new Integer(lib.prettyIndent));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[127]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[65]++;}

        return options;
    }


    /**
     *
     * @param cursor
     * @param opts
     * @return
     */
    private static String dumpNode(XmlCursor cursor, XmlOptions opts)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[128]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cursor.isText()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[66]++;
            return cursor.getChars();
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[67]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[129]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((cursor.isFinish()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[68]++;
            return "";
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[69]++;}

        cursor.push();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[130]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[131]++;
        boolean wanRawText = cursor.isStartdoc() && !cursor.toFirstChild();
        cursor.pop();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[132]++;

        return wanRawText ? cursor.getTextValue() : cursor.xmlText( opts );
    }

    /**
     *
     * @return
     */
    private XmlCursor newCursor ()
    {
        XmlCursor curs;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[133]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((_anno != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[70]++;
            curs = _anno.createCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[134]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[135]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((curs == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[72]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[136]++;
                // Orphaned case.
                XmlObject doc = XmlObject.Factory.newInstance();
                curs = doc.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[137]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[138]++;
int CodeCoverConditionCoverageHelper_C37;

                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((_anno._name != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[74]++;
                    curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[139]++;
                    curs.insertElement(_anno._name);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[140]++;
                    curs.toPrevSibling();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[141]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[75]++;}

                curs.setBookmark(_anno);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[142]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[73]++;}

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[71]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[143]++;
            XmlObject doc = XmlObject.Factory.newInstance();
            curs = doc.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[144]++;
        }

        return curs;
    }

    /*
     * fUseStartDoc used by child(int index) the index is at startDoc is the element at the top-level
     *              otherwise we always want to drill in.
     */
    private boolean moveToChild(XmlCursor curs, long index, boolean fFirstChild, boolean fUseStartDoc)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[145]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[76]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[77]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[146]++;

        long idxChild = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[147]++;
int CodeCoverConditionCoverageHelper_C39;

        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((fUseStartDoc) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[78]++;
            // We always move to the children of the top node.
            // todo:  This assumes that we want have multiple top-level nodes.  Which we should be able tohave.
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[148]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[79]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[149]++;

        TokenType tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[150]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((tt.isNone()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[80]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[151]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[10]++;


            while (true)
            {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[10]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[11]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[12]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[152]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((index == idxChild) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[82]++;
                    return true;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[83]++;}

                tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[153]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[154]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((tt.isText()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[84]++;
                    curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[155]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[85]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[156]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[86]++;
                    // Need to do this we want to be pointing at the text if that after the end token.
                    curs.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[157]++;
                    curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[158]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[87]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[159]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((tt.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((tt.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[88]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[160]++;
                    continue;

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[89]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[161]++;
                    break;
                }
}
}

                idxChild++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[162]++;
            }

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[81]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[163]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((fFirstChild) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[90]++;
            // Drill into where first child would be.
//            curs.toFirstContentToken();
            return true;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[91]++;}
}

        return false;
    }

    /**
     *
     * @return
     */
    XmlCursor.TokenType tokenType()
    {
        XmlCursor.TokenType result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[164]++;

        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[165]++;
int CodeCoverConditionCoverageHelper_C47;

        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[92]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[166]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[93]++;}

        result = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[167]++;

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[168]++;

        return result;
    }
    /**
     *
     * @param srcCurs
     * @param destCurs
     * @param fDontMoveIfSame
     * @return
     */
    private boolean moveSrcToDest (XmlCursor srcCurs, XmlCursor destCurs, boolean fDontMoveIfSame)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[169]++;
        boolean fMovedSomething = true;
        TokenType tt;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[170]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[13]++;


int CodeCoverConditionCoverageHelper_C48;
        do
        {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[13]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[14]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[15]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[171]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (32)) == 0 || true) &&
 ((fDontMoveIfSame) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((srcCurs.isInSameDocument(destCurs)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((srcCurs.comparePosition(destCurs) == 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[94]++;
                // If the source and destination are pointing at the same place then there's nothing to move.
                fMovedSomething = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[172]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[173]++;
                break;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[95]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[174]++;
int CodeCoverConditionCoverageHelper_C50;

            // todo ***TLL*** Use replaceContents (when added) and eliminate children removes (see above todo).
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((destCurs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[96]++;
                destCurs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[175]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[97]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[176]++;

            // todo ***TLL*** Can Eric support notion of copy instead of me copying then moving???
            XmlCursor copyCurs = copy(srcCurs);

            copyCurs.moveXml(destCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[177]++;

            copyCurs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[178]++;

            tt = srcCurs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[179]++;
        } while ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (32)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((tt.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 3) && false));

        return fMovedSomething;
    }

    /**
     *
     * @param cursToCopy
     * @return
     */
    private XmlCursor copy (XmlCursor cursToCopy)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[180]++;
        XmlObject xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[181]++;

        XmlCursor copyCurs = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[182]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((cursToCopy.currentTokenType().isText()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[98]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[183]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
            try
            {
CodeCoverTryBranchHelper_Try6 = true;
                // Try just as a textnode, to do that we need to wrap the text in a special fragment tag
                // that is not visible from the XmlCursor.
                copyCurs = XmlObject.Factory.parse("<x:fragment xmlns:x=\"http://www.openuri.org/fragment\">" +
                                           cursToCopy.getChars() +
                                           "</x:fragment>").newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[184]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[185]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((cursToCopy.toNextSibling()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[101]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[186]++;
int CodeCoverConditionCoverageHelper_C53;
                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((cursToCopy.currentTokenType().isText()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[103]++;
                        cursToCopy.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[187]++;
   // It's not an element it's text so skip it.
                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[104]++;}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[102]++;}
            }
            catch (Exception ex)
            {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[105]++;
                throw ScriptRuntime.typeError(ex.getMessage());
            } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[100]++;
}
  }

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[99]++;
            copyCurs = xo.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[188]++;
            copyCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[189]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[190]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((cursToCopy.currentTokenType() == XmlCursor.TokenType.STARTDOC) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[106]++;
                cursToCopy.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[191]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[107]++;}

            cursToCopy.copyXml(copyCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[192]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[193]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((cursToCopy.toNextSibling()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false))        // If element skip element.
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[108]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[194]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((cursToCopy.currentTokenType().isText()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[110]++;
                    cursToCopy.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[195]++;
       // It's not an element it's text so skip it.
                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[111]++;}

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[109]++;}

        }

        copyCurs.toStartDoc();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[196]++;
        copyCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[197]++;

        return copyCurs;
    }

    private static final int APPEND_CHILD = 1;
  static {
    CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[198]++;
  }
    private static final int PREPEND_CHILD = 2;
  static {
    CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[199]++;
  }

    /**
     *
     * @param curs
     * @param xmlToInsert
     */
    private void insertChild(XmlCursor curs, Object xmlToInsert)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[200]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((xmlToInsert == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((xmlToInsert instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[112]++;

            // Do nothing
        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[113]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[201]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((xmlToInsert instanceof XmlCursor) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[114]++;
            moveSrcToDest((XmlCursor)xmlToInsert, curs, true);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[202]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[115]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[203]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((xmlToInsert instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[116]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[204]++;
            XML xmlValue = (XML) xmlToInsert;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[205]++;
int CodeCoverConditionCoverageHelper_C60;

            // If it's an attribute, then change to text node
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((xmlValue.tokenType() == XmlCursor.TokenType.ATTR) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[118]++;
                insertChild(curs, xmlValue.toString());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[206]++;

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[119]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[207]++;
                XmlCursor cursToInsert = ((XML) xmlToInsert).newCursor();

                moveSrcToDest(cursToInsert, curs, true);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[208]++;

                cursToInsert.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[209]++;
            }

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[117]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[210]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((xmlToInsert instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[120]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[211]++;
            XMLList list = (XMLList) xmlToInsert;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[212]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[16]++;


int CodeCoverConditionCoverageHelper_C62;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((i < list.length()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[16]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[17]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[18]++;
}
                insertChild(curs, list.item(i));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[213]++;
            }

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[121]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[214]++;
            // Convert to string and make XML out of it
            String  xmlStr = ScriptRuntime.toString(xmlToInsert);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[215]++;
            XmlObject xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[216]++;         // Create an empty document.

            XmlCursor sourceCurs = xo.newCursor();
            sourceCurs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[217]++;

            // To hold the text.
            sourceCurs.insertChars(xmlStr);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[218]++;

            sourceCurs.toPrevToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[219]++;

            // Call us again with the cursor.
            moveSrcToDest(sourceCurs, curs, true);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[220]++;
        }
}
}
}
    }

    /**
     *
     * @param childToMatch
     * @param xmlToInsert
     * @param addToType
     */
    private void insertChild(XML childToMatch, Object xmlToInsert, int addToType)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[221]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[222]++;
        TokenType tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[223]++;
        XmlCursor xmlChildCursor = childToMatch.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[224]++;
int CodeCoverConditionCoverageHelper_C63;

        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((tt.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[122]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[225]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[123]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[226]++;
int CodeCoverConditionCoverageHelper_C64;

        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((tt.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[124]++;
            tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[227]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[228]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[19]++;


int CodeCoverConditionCoverageHelper_C65;

            while ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false))
            {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[19]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[20]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[21]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[229]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[126]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[230]++;
int CodeCoverConditionCoverageHelper_C67;
                    // See if this child is the same as the one thep passed in
                    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((curs.comparePosition(xmlChildCursor) == 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[128]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[231]++;
int CodeCoverConditionCoverageHelper_C68;
                        // Found it
                        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((addToType == APPEND_CHILD) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[130]++;
                            // Move the cursor to just past the end of this element
                            curs.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[232]++;
                            curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[233]++;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[131]++;}

                        insertChild(curs, xmlToInsert);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[234]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[235]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[129]++;}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[127]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[236]++;
int CodeCoverConditionCoverageHelper_C69;

                // Skip over child elements
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[132]++;
                    tt = curs.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[237]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[133]++;}

                tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[238]++;
            }


        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[125]++;}

        xmlChildCursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[239]++;
        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[240]++;
    }

    /**
     *
     * @param curs
     */
    protected void removeToken (XmlCursor curs)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[241]++;
        XmlObject xo = XmlObject.Factory.newInstance();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[242]++;

        // Don't delete anything move to another document so it gets orphaned nicely.
        XmlCursor tmpCurs = xo.newCursor();
        tmpCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[243]++;


        curs.moveXml(tmpCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[244]++;

        tmpCurs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[245]++;
    }

    /**
     *
     * @param index
     */
    protected void removeChild(long index)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[246]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[247]++;
int CodeCoverConditionCoverageHelper_C70;

        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((moveToChild(curs, index, false, false)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[134]++;
            removeToken(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[248]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[135]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[249]++;
    }

    /**
     *
     * @param name
     * @return
     */
    protected static javax.xml.namespace.QName computeQName (Object name)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[250]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((name instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[136]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[251]++;
            String ns = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[252]++;
            String localName = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[253]++;

            String fullName = (String)name;
            localName = fullName;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[254]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[255]++;
int CodeCoverConditionCoverageHelper_C72;
            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((fullName.startsWith("\"")) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[138]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[256]++;
                int idx = fullName.indexOf(":");
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[257]++;
int CodeCoverConditionCoverageHelper_C73;
                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((idx != -1) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[140]++;
                    ns = fullName.substring(1, idx - 1);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[258]++;    // Don't include the "" around the namespace
                    localName = fullName.substring(idx + 1);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[259]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[141]++;}

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[139]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[260]++;
int CodeCoverConditionCoverageHelper_C74;

            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((ns == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[142]++;
                return new javax.xml.namespace.QName(localName);

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[143]++;
                return new javax.xml.namespace.QName(ns, localName);
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[137]++;}

        return null;
    }

    /**
     *
     * @param destCurs
     * @param newValue
     */
    private void replace(XmlCursor destCurs, XML newValue)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[261]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((destCurs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[144]++;
            // Can't overwrite a whole document (user really wants to overwrite the contents of).
            destCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[262]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[145]++;}

        // Orphan the token -- don't delete it outright on the XmlCursor.
        removeToken(destCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[263]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[264]++;

        XmlCursor srcCurs = newValue.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[265]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((srcCurs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[146]++;
            // Cann't append a whole document (user really wants to append the contents of).
            srcCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[266]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[147]++;}

        moveSrcToDest(srcCurs, destCurs, false);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[267]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[268]++;
int CodeCoverConditionCoverageHelper_C77;

        // Re-link a new annotation to this cursor -- we just deleted the previous annotation on entrance to replace.
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((destCurs.toPrevSibling()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[148]++;
            destCurs.toPrevToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[269]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[149]++;}
        destCurs.setBookmark(new XScriptAnnotation(destCurs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[270]++;

        // todo would be nice if destCurs.toNextSibling went to where the next token if the cursor was pointing at the last token in the stream.
        destCurs.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[271]++;
        destCurs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[272]++;

        srcCurs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[273]++;
    }

    /**
     *
     * @param currXMLNode
     * @param xmlValue
     * @return
     */
    private boolean doPut(XMLName name, XML currXMLNode, XMLObjectImpl xmlValue)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[274]++;
        boolean result = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[275]++;
        XmlCursor curs = currXMLNode.newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[276]++;
boolean CodeCoverTryBranchHelper_Try7 = false;

        try
        {
CodeCoverTryBranchHelper_Try7 = true;
            // Replace the node with this new xml value.
            XML xml;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[277]++;

            int toAssignLen = xmlValue.length();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[278]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[22]++;


int CodeCoverConditionCoverageHelper_C78;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((i < toAssignLen) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[22]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[23]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[24]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[279]++;
int CodeCoverConditionCoverageHelper_C79;
                if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[151]++;
                    xml = ((XMLList) xmlValue).item(i);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[280]++;

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[152]++;
                    xml = (XML) xmlValue;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[281]++;
                }
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[282]++;

                // If it's an attribute or text node, make text node.
                XmlCursor.TokenType tt = xml.tokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[283]++;
int CodeCoverConditionCoverageHelper_C80;
                if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.ATTR) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.TEXT) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[153]++;
                    xml = makeXmlFromString(lib, name, xml.toString());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[284]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[154]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[285]++;
int CodeCoverConditionCoverageHelper_C81;

                if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[155]++;
                    // 1st assignment is replaceChild all others are appendChild
                    replace(curs, xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[286]++;

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[156]++;
                    insertChild(curs, xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[287]++;
                }
            }

            // We're done we've blown away the node because the rvalue was XML...
            result = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[288]++;
        }
        catch (Exception ex)
        {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[157]++;
            ex.printStackTrace();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[289]++;
            throw ScriptRuntime.typeError(ex.getMessage());
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[150]++;
}
            curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[290]++;
        }

        return result;
    }

    /**
     * Make a text node element with this element name and text value.
     *
     * @param name
     * @param value
     * @return
     */
    private XML makeXmlFromString(XMLLibImpl lib, XMLName name,
                                      String value)
    {
        XML result;

        javax.xml.namespace.QName qname;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[291]++;
boolean CodeCoverTryBranchHelper_Try8 = false;

        try
        {
CodeCoverTryBranchHelper_Try8 = true;
            qname = new javax.xml.namespace.QName(name.uri(), name.localName());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[292]++;
        }
        catch(Exception e)
        {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[159]++;
            throw ScriptRuntime.typeError(e.getMessage());
        } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[158]++;
}
  }

        result = createTextElement(lib, qname, value);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[293]++;

        return result;
    }

    /**
     *
     * @param name
     * @return
     */
    private XMLList matchAttributes(XMLName xmlName)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[294]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[295]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[296]++;
int CodeCoverConditionCoverageHelper_C82;

        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[160]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[297]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[161]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[298]++;
int CodeCoverConditionCoverageHelper_C83;

        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((curs.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[162]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[299]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((curs.toFirstAttribute()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[164]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[300]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[25]++;


int CodeCoverConditionCoverageHelper_C85;
                do
                {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[25]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[26]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[27]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[301]++;
int CodeCoverConditionCoverageHelper_C86;
                    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((qnameMatches(xmlName, curs.getName())) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[166]++;
                        result.addToList(createAttributeObject(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[302]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[167]++;}
                } while ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((curs.toNextAttribute()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false));

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[165]++;}

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[163]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[303]++;

        return result;
    }

    /**
     *
     * @param attrCurs
     * @return
     */
    private XML createAttributeObject (XmlCursor attrCurs)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[304]++;
        XML result = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[305]++;
int CodeCoverConditionCoverageHelper_C87;

        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((attrCurs.currentTokenType().isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[168]++;
            result = createAttributeXML(lib, attrCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[306]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[169]++;}

        return result;
    }

    //
    //
    //  methods overriding ScriptableObject
    //
    //

    public String getClassName ()
    {
        return "XML";
    }

    //
    //
    //  methods overriding IdScriptableObject
    //
    //

    /**
     * XML[0] should return this, all other indexes are Undefined
     *
     * @param index
     * @param start
     * @return
     */
    public Object get(int index, Scriptable start)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[307]++;
int CodeCoverConditionCoverageHelper_C88;
        //Log("get index: " + index);

        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[170]++;
            return this;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[171]++;
            return Scriptable.NOT_FOUND;
        }
    }

    /**
     * Does the named property exist
     *
     * @param xmlName
     * @return
     */
    boolean hasXMLProperty(XMLName xmlName)
    {
        // Has now should return true if the property would have results > 0
        return (getPropertyList(xmlName).length() > 0);
    }


    /**
     *
     * @param index
     * @param start
     * @return
     */
    public boolean has(int index, Scriptable start)
    {
        return (index == 0);
    }

    /**
     *
     * @return
     */
    public Object[] getIds()
    {
        Object[] enumObjs;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[308]++;
int CodeCoverConditionCoverageHelper_C89;

        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((prototypeFlag) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[172]++;
            enumObjs = new Object[0];
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[309]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[173]++;
            enumObjs = new Object[1];
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[310]++;

            enumObjs[0] = new Integer(0);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[311]++;
        }

        return enumObjs;
    }


    /**
     *
     * @return
     */
    public Object [] getIdsForDebug()
    {
        return getIds();
    }

    /**
     *
     * @param xmlName
     * @return
     */
    Object getXMLProperty(XMLName xmlName)
    {
        return getPropertyList(xmlName);
    }

    /**
     *
     * @param xmlName
     * @param value
     */
    void putXMLProperty(XMLName xmlName, Object value)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[312]++;
int CodeCoverConditionCoverageHelper_C90;
        //Log("put property: " + name + " value: " + value.getClass());

        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((prototypeFlag) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[174]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[175]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[313]++;
int CodeCoverConditionCoverageHelper_C91;
            // Special-case checks for undefined and null
            if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[176]++;
                value = "null";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[314]++;

            }
            else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[177]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[315]++;
int CodeCoverConditionCoverageHelper_C92; if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((value instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[178]++;
                value = "undefined";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[316]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[179]++;}
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[317]++;
int CodeCoverConditionCoverageHelper_C93;

            // Get the named property
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[180]++;
                setAttribute(xmlName, value);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[318]++;

            }
            else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[181]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[319]++;
int CodeCoverConditionCoverageHelper_C94; if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((xmlName.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((xmlName.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[182]++;
                setChildren(value);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[320]++;

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[183]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[321]++;
                // Convert text into XML if needed.
                XMLObjectImpl xmlValue = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[322]++;
int CodeCoverConditionCoverageHelper_C95;

                if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((value instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[184]++;
                    xmlValue = (XMLObjectImpl) value;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[323]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[324]++;
int CodeCoverConditionCoverageHelper_C96;

                    // Check for attribute type and convert to textNode
                    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[186]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[325]++;
int CodeCoverConditionCoverageHelper_C97;
                        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((((XML) xmlValue).tokenType() == XmlCursor.TokenType.ATTR) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[188]++;
                            xmlValue = makeXmlFromString(lib, xmlName, xmlValue.toString());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[326]++;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[189]++;}

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[187]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[327]++;
int CodeCoverConditionCoverageHelper_C98;

                    if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[190]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[328]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[28]++;


int CodeCoverConditionCoverageHelper_C99;
                        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((i < xmlValue.length()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false); i++)
                        {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[28]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[29]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[30]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[329]++;
                            XML xml = ((XMLList) xmlValue).item(i);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[330]++;
int CodeCoverConditionCoverageHelper_C100;

                            if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((xml.tokenType() == XmlCursor.TokenType.ATTR) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false))
                            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[192]++;
                                ((XMLList) xmlValue).replace(i, makeXmlFromString(lib, xmlName, xml.toString()));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[331]++;

                            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[193]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[191]++;}

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[185]++;
                    xmlValue = makeXmlFromString(lib, xmlName, ScriptRuntime.toString(value));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[332]++;
                }
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[333]++;

                XMLList matches = getPropertyList(xmlName);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[334]++;
int CodeCoverConditionCoverageHelper_C101;

                if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((matches.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[194]++;
                    appendChild(xmlValue);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[335]++;

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[195]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[336]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[31]++;


int CodeCoverConditionCoverageHelper_C102;
                    // Remove all other matches
                    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((i < matches.length()) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false); i++)
                    {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[31]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[32]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[33]++;
}
                        removeChild(matches.item(i).childIndex());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[337]++;
                    }

                    // Replace first match with new value.
                    doPut(xmlName, matches.item(0), xmlValue);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[338]++;
                }
            }
}
        }
    }


    /**
     *
     * @param index
     * @param start
     * @param value
     */
    public void put(int index, Scriptable start, Object value)
    {
        // Spec says assignment to indexed XML object should return type error
        throw ScriptRuntime.typeError("Assignment to indexed XML is not allowed");
    }


    /**
     *
     * @param name
     */
    void deleteXMLProperty(XMLName name)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[339]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C103 |= (8)) == 0 || true) &&
 ((name.isDescendants()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((name.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[196]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[340]++;
            XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[341]++;
int CodeCoverConditionCoverageHelper_C104;

            // TODO: Cover the case *::name
            if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((name.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[198]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[342]++;
int CodeCoverConditionCoverageHelper_C105;
                // Delete all attributes.
                if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((curs.toFirstAttribute()) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[200]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[343]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[34]++;


int CodeCoverConditionCoverageHelper_C106;
                    while ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false))
                    {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[34]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[35]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[36]++;
}
                        curs.removeXml();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[344]++;
                    }

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[201]++;}

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[199]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[345]++;
                // Delete an attribute.
                javax.xml.namespace.QName qname = new javax.xml.namespace.QName(
                    name.uri(), name.localName());
                curs.removeAttribute(qname);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[346]++;
            }

            curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[347]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[197]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[348]++;
            XMLList matches = getPropertyList(name);

            matches.remove();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[349]++;
        }
    }


    /**
     *
     * @param index
     */
    public void delete(int index)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[350]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[202]++;
            remove();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[351]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[203]++;}
    }

    //
    //
    //  package utility functions:
    //
    //

    protected XScriptAnnotation getAnnotation ()
    { return _anno; }


    protected void changeNS (String oldURI, String newURI)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[352]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[353]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[37]++;


int CodeCoverConditionCoverageHelper_C108;
        while ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((curs.toParent()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[37]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[38]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[39]++;
}
          /* Goto the top of the document */
        }
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[354]++;

        TokenType tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[355]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((tt.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[204]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[356]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[205]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[357]++;
int CodeCoverConditionCoverageHelper_C110;

        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[206]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[358]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[40]++;


int CodeCoverConditionCoverageHelper_C111;
            do
            {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[40]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[41]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[42]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[359]++;
int CodeCoverConditionCoverageHelper_C112;
                if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (32)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C112 |= (8)) == 0 || true) &&
 ((tt.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((tt.isNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 3) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[208]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[360]++;
                    javax.xml.namespace.QName currQName = curs.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[361]++;
int CodeCoverConditionCoverageHelper_C113;
                    if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((oldURI.equals(currQName.getNamespaceURI())) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[210]++;
                        curs.setName(new javax.xml.namespace.QName(newURI, currQName.getLocalPart()));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[362]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[211]++;}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[209]++;}

                tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[363]++;
            } while ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C111 |= (8)) == 0 || true) &&
 ((tt.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((tt.isNone()) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 2) && false));

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[207]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[364]++;
    }


    /**
     *
     */
    void remove ()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[365]++;
        XmlCursor childCurs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[366]++;
int CodeCoverConditionCoverageHelper_C114;

        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((childCurs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[212]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[367]++;
            // Remove on the document removes all children.
            TokenType tt = childCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[368]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[43]++;


int CodeCoverConditionCoverageHelper_C115;
            while ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C115 |= (8)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((tt.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) && false))
            {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[43]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[44]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[45]++;
}
                removeToken(childCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[369]++;
                tt = childCurs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[370]++;      // Now see where we're pointing after the delete -- next token.
            }

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[213]++;
                removeToken(childCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[371]++;
        }

        childCurs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[372]++;
    }


    /**
     *
     * @param value
     */
    void replaceAll(XML value)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[373]++;
        XmlCursor curs = newCursor();

        replace(curs, value);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[374]++;
        _anno = value._anno;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[375]++;

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[376]++;
    }


    /**
     *
     * @param attrName
     * @param value
     */
    void setAttribute(XMLName xmlName, Object value)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[377]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (8)) == 0 || true) &&
 ((xmlName.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((xmlName.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[214]++;
            throw ScriptRuntime.typeError("@* assignment not supported.");

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[215]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[378]++;

        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[379]++;

        String strValue = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[380]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[216]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[381]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[217]++;}

        javax.xml.namespace.QName qName;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[382]++;
boolean CodeCoverTryBranchHelper_Try9 = false;

        try
        {
CodeCoverTryBranchHelper_Try9 = true;
            qName = new javax.xml.namespace.QName(xmlName.uri(), xmlName.localName());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[383]++;
        }
        catch(Exception e)
        {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[219]++;
            throw ScriptRuntime.typeError(e.getMessage());
        } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[218]++;
}
  }
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[384]++;
int CodeCoverConditionCoverageHelper_C118;

        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((curs.setAttributeText(qName, strValue)) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[220]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[385]++;
int CodeCoverConditionCoverageHelper_C119;
            if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((curs.currentTokenType().isStart()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[222]++;
                // Can only add attributes inside of a start.
                curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[386]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[223]++;}
            curs.insertAttributeWithValue(qName, strValue);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[387]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[221]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[388]++;
    }

    /**
     *
     * @param namespace
     * @return
     */
    private XMLList allChildNodes(String namespace)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[389]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[390]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[391]++;
        TokenType tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[392]++;
        javax.xml.namespace.QName targetProperty = new javax.xml.namespace.QName(namespace, "*");
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[393]++;
int CodeCoverConditionCoverageHelper_C120;

        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((tt.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[224]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[394]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[225]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[395]++;
int CodeCoverConditionCoverageHelper_C121;

        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((tt.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[226]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[396]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[397]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[46]++;


int CodeCoverConditionCoverageHelper_C122;

            while ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false))
            {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[46]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[47]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[48]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[398]++;
int CodeCoverConditionCoverageHelper_C123;
                if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[228]++;
                    // Not an element
                    result.addToList(findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[399]++;

                    // Reset target property to null in this case
                    targetProperty = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[400]++;

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[229]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[401]++;
int CodeCoverConditionCoverageHelper_C124;
                    // Match namespace as well if specified
                    if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (128)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C124 |= (32)) == 0 || true) &&
 ((namespace.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C124 |= (8)) == 0 || true) &&
 ((namespace.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((curs.getName().getNamespaceURI().equals(namespace)) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 4) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 4) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[230]++;
                        // Add it to the list
                        result.addToList(findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[402]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[403]++;
int CodeCoverConditionCoverageHelper_C125;

                        // Set target property if target name is "*",
                        // Otherwise if target property does not match current, then
                        // set to null
                        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((targetProperty != null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[232]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[404]++;
int CodeCoverConditionCoverageHelper_C126;
                            if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((targetProperty.getLocalPart().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false))
                            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[234]++;
                                targetProperty = curs.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[405]++;

                            }
                            else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[235]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[406]++;
int CodeCoverConditionCoverageHelper_C127; if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((targetProperty.getLocalPart().equals(curs.getName().getLocalPart())) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false))
                            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[236]++;
                                // Not a match, unset target property
                                targetProperty = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[407]++;

                            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[237]++;}
}

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[233]++;}

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[231]++;}
                }
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[408]++;
int CodeCoverConditionCoverageHelper_C128;

                // Skip over child elements
                if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[238]++;
                    tt = curs.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[409]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[239]++;}

                tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[410]++;
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[227]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[411]++;

        // Set the targets for this XMLList.
        result.setTargets(this, targetProperty);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[412]++;

        return result;
    }

    /**
     *
     * @return
     */
    private XMLList matchDescendantAttributes(XMLName xmlName)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[413]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[414]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[415]++;
        TokenType tt = curs.currentTokenType();

        // Set the targets for this XMLList.
        result.setTargets(this, null);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[416]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[417]++;
int CodeCoverConditionCoverageHelper_C129;

        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((tt.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[240]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[418]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[241]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[419]++;
int CodeCoverConditionCoverageHelper_C130;

        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((tt.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[242]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[420]++;
            int nestLevel = 1;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[421]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[49]++;


int CodeCoverConditionCoverageHelper_C131;

            while ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((nestLevel > 0) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false))
            {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[49]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[50]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[51]++;
}
                tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[422]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[423]++;
int CodeCoverConditionCoverageHelper_C132;

                // Only try to match names for attributes
                if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((tt.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[244]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[424]++;
int CodeCoverConditionCoverageHelper_C133;
                    if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((qnameMatches(xmlName, curs.getName())) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[246]++;
                        result.addToList(findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[425]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[247]++;}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[245]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[426]++;
int CodeCoverConditionCoverageHelper_C134;

                if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[248]++;
                    nestLevel++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[427]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[249]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[428]++;
int CodeCoverConditionCoverageHelper_C135; if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[250]++;
                    nestLevel--;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[429]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[251]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[430]++;
int CodeCoverConditionCoverageHelper_C136; if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((tt.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[252]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[431]++;
                    // Shouldn't get here, but just in case.
                    break;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[253]++;}
}
}
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[243]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[432]++;

        return result;
    }

    /**
     *
     * @return
     */
    private XMLList matchDescendantChildren(XMLName xmlName)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[433]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[434]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[435]++;
        TokenType tt = curs.currentTokenType();

        // Set the targets for this XMLList.
        result.setTargets(this, null);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[436]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[437]++;
int CodeCoverConditionCoverageHelper_C137;

        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((tt.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[254]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[438]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[255]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[439]++;
int CodeCoverConditionCoverageHelper_C138;

        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((tt.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[256]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[440]++;
            int nestLevel = 1;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[441]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[52]++;


int CodeCoverConditionCoverageHelper_C139;

            while ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((nestLevel > 0) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false))
            {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[52]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[53]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[54]++;
}
                tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[442]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[443]++;
int CodeCoverConditionCoverageHelper_C140;

                if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C140 |= (32)) == 0 || true) &&
 ((tt.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C140 |= (8)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((tt.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 3) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[258]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[444]++;
int CodeCoverConditionCoverageHelper_C141;
                    // Only try to match names for elements or processing instructions.
                    if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((tt.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[260]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[445]++;
int CodeCoverConditionCoverageHelper_C142;
                        // Not an element or procinst, only add if qname is all
                        if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((xmlName.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[262]++;
                            result.addToList(findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[446]++;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[263]++;}

                    }
                    else
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[261]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[447]++;
int CodeCoverConditionCoverageHelper_C143;
                        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((qnameMatches(xmlName, curs.getName())) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[264]++;
                            result.addToList(findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[448]++;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[265]++;}
                    }

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[259]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[449]++;
int CodeCoverConditionCoverageHelper_C144;

                if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[266]++;
                    nestLevel++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[450]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[267]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[451]++;
int CodeCoverConditionCoverageHelper_C145; if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[268]++;
                    nestLevel--;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[452]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[269]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[453]++;
int CodeCoverConditionCoverageHelper_C146; if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((tt.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[270]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[454]++;
                    // Shouldn't get here, but just in case.
                    break;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[271]++;}
}
}
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[257]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[455]++;

        return result;
    }

    /**
     *
     * @param tokenType
     * @return
     */
    private XMLList matchChildren(XmlCursor.TokenType tokenType)
    {
        return matchChildren(tokenType, XMLName.formStar());
    }

    /**
     *
     * @return
     */
    private XMLList matchChildren(XmlCursor.TokenType tokenType, XMLName name)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[456]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[457]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[458]++;
        TokenType tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[459]++;
        javax.xml.namespace.QName qname = new javax.xml.namespace.QName(name.uri(), name.localName());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[460]++;
        javax.xml.namespace.QName targetProperty = qname;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[461]++;
int CodeCoverConditionCoverageHelper_C147;

        if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((tt.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[272]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[462]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[273]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[463]++;
int CodeCoverConditionCoverageHelper_C148;

        if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((tt.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[274]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[464]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[465]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[55]++;


int CodeCoverConditionCoverageHelper_C149;

            while ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false))
            {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[55]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[56]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[57]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[466]++;
int CodeCoverConditionCoverageHelper_C150;
                if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((tt == tokenType) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[276]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[467]++;
int CodeCoverConditionCoverageHelper_C151;
                    // Only try to match names for elements or processing instructions.
                    if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C151 |= (8)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((tt.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 2) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[278]++;
                        // Not an element or no name specified.
                        result.addToList(findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[468]++;

                        // Reset target property to null in this case
                        targetProperty = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[469]++;

                    }
                    else
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[279]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[470]++;
int CodeCoverConditionCoverageHelper_C152;
                        // Match names as well
                        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((qnameMatches(name, curs.getName())) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[280]++;
                            // Add it to the list
                            result.addToList(findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[471]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[472]++;
int CodeCoverConditionCoverageHelper_C153;

                            // Set target property if target name is "*",
                            // Otherwise if target property does not match current, then
                            // set to null
                            if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((targetProperty != null) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false))
                            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[282]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[473]++;
int CodeCoverConditionCoverageHelper_C154;
                                if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((targetProperty.getLocalPart().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false))
                                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[284]++;
                                    targetProperty = curs.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[474]++;

                                }
                                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[285]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[475]++;
int CodeCoverConditionCoverageHelper_C155; if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((targetProperty.getLocalPart().equals(curs.getName().getLocalPart())) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false))
                                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[286]++;
                                    // Not a match, unset target property
                                    targetProperty = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[476]++;

                                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[287]++;}
}

                            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[283]++;}

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[281]++;}
                    }

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[277]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[477]++;
int CodeCoverConditionCoverageHelper_C156;

                // Skip over child elements
                if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[288]++;
                    tt = curs.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[478]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[289]++;}

                tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[479]++;
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[275]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[480]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[481]++;
int CodeCoverConditionCoverageHelper_C157;

        if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((tokenType == XmlCursor.TokenType.START) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[290]++;
            // Set the targets for this XMLList.
            result.setTargets(this, targetProperty);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[482]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[291]++;}

        return result;

    }

    /**
     *
     * @param template
     * @param match
     * @return
     */
    private boolean qnameMatches(XMLName template, javax.xml.namespace.QName match)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[483]++;
        boolean matches = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[484]++;
int CodeCoverConditionCoverageHelper_C158;

        if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (8)) == 0 || true) &&
 ((template.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((template.uri().equals(match.getNamespaceURI())) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[292]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[485]++;
int CodeCoverConditionCoverageHelper_C159;
            // URI OK, test name
            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (8)) == 0 || true) &&
 ((template.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((template.localName().equals(match.getLocalPart())) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[294]++;
                matches = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[486]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[295]++;}

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[293]++;}

        return matches;
    }

    //
    //
    // Methods from section 12.4.4 in the spec
    //
    //

    /**
     * The addNamespace method adds a namespace declaration to the in scope
     * namespaces for this XML object and returns this XML object.
     *
     * @param toAdd
     */
    XML addNamespace(Namespace ns)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[487]++;
        // When a namespace is used it will be added automatically
        // to the inScopeNamespaces set. There is no need to add
        // Namespaces with undefined prefixes.
        String nsPrefix = ns.prefix();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[488]++;
int CodeCoverConditionCoverageHelper_C160;
        if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((nsPrefix == null) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[296]++; return this;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[297]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[489]++;

        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[490]++;
boolean CodeCoverTryBranchHelper_Try10 = false;

        try
        {
CodeCoverTryBranchHelper_Try10 = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[491]++;
int CodeCoverConditionCoverageHelper_C161;
            if((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((cursor.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[299]++; return this;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[300]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[492]++;

            javax.xml.namespace.QName qname = cursor.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[493]++;
int CodeCoverConditionCoverageHelper_C162;
            // Don't add a default namespace declarations to containers
            // with QNames in no namespace.
            if((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (8)) == 0 || true) &&
 ((qname.getNamespaceURI().equals("")) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((nsPrefix.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[301]++; return this;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[302]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[494]++;

            // Get all declared namespaces that are in scope
            Map prefixToURI = NamespaceHelper.getAllNamespaces(lib, cursor);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[495]++;

            String uri = (String)prefixToURI.get(nsPrefix);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[496]++;
int CodeCoverConditionCoverageHelper_C163;
            if((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((uri != null) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[303]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[497]++;
int CodeCoverConditionCoverageHelper_C164;
                // Check if the Namespace is not already in scope
                if((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((uri.equals(ns.uri())) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[305]++; return this;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[306]++;}

                cursor.push();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[498]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[499]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[58]++;


int CodeCoverConditionCoverageHelper_C165;

                // Let's see if we have to delete a namespace declaration
                while((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((cursor.toNextToken().isAnyAttr()) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false))
                {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[58]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[59]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[60]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[500]++;
int CodeCoverConditionCoverageHelper_C166;
                    if((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((cursor.isNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[307]++;
                        qname = cursor.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[501]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[502]++;
                        String prefix = qname.getLocalPart();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[503]++;
int CodeCoverConditionCoverageHelper_C167;
                        if((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((prefix.equals(nsPrefix)) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[309]++;
                            // Delete the current Namespace declaration
                            cursor.removeXml();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[504]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[505]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[310]++;}

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[308]++;}
                }

                cursor.pop();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[506]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[304]++;}

            cursor.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[507]++;
            cursor.insertNamespace(nsPrefix, ns.uri());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[508]++;
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[298]++;
}
            cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[509]++;
        }

        return this;
    }

    /**
     *
     * @param xml
     * @return
     */
    XML appendChild(Object xml)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[510]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[511]++;
int CodeCoverConditionCoverageHelper_C168;

        if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[311]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[512]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[312]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[513]++;
int CodeCoverConditionCoverageHelper_C169;

        // Move the cursor to the end of this element
        if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((curs.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[313]++;
            curs.toEndToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[514]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[314]++;}

        insertChild(curs, xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[515]++;

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[516]++;

        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    XMLList attribute(XMLName xmlName)
    {
        return matchAttributes(xmlName);
    }

    /**
     *
     * @return
     */
    XMLList attributes()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[517]++;
        XMLName xmlName = XMLName.formStar();
        return matchAttributes(xmlName);
    }

    XMLList child(long index)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[518]++;
        XMLList result = new XMLList(lib);
        result.setTargets(this, null);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[519]++;
        result.addToList(getXmlChild(index));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[520]++;
        return result;
    }

    XMLList child(XMLName xmlName)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[521]++;
int CodeCoverConditionCoverageHelper_C170;
        if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[315]++;
            return new XMLList(lib);
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[316]++;}

        XMLList result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[522]++;
int CodeCoverConditionCoverageHelper_C171;
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((xmlName.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[317]++;
            result = allChildNodes(xmlName.uri());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[523]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[318]++;
            result = matchChildren(XmlCursor.TokenType.START, xmlName);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[524]++;
        }

        return result;
    }

    /**
     *
     * @param index
     * @return
     */
    XML getXmlChild(long index)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[525]++;
        XML result = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[526]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[527]++;
int CodeCoverConditionCoverageHelper_C172;

        if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((moveToChild(curs, index, false, true)) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[319]++;
            result = createXML(lib, curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[528]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[320]++;}

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[529]++;

        return result;
    }

    /**
     *
     * @return
     */
    int childIndex()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[530]++;
        int index = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[531]++;

        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[532]++;

        TokenType tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[533]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[61]++;


        while (true)
        {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[61]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[62]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[63]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[534]++;
int CodeCoverConditionCoverageHelper_C174;
            if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((tt.isText()) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[321]++;
                index++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[535]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[536]++;
int CodeCoverConditionCoverageHelper_C175;
                if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((curs.toPrevSibling()) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[323]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[537]++;
                    break;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[324]++;}

            }
            else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[322]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[538]++;
int CodeCoverConditionCoverageHelper_C176; if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[325]++;
                tt = curs.toPrevToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[539]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[540]++;
int CodeCoverConditionCoverageHelper_C177;
                if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[327]++;
                    curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[541]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[542]++;
int CodeCoverConditionCoverageHelper_C178;
                    if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((curs.toPrevSibling()) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[329]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[543]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[330]++;}

                    index++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[544]++;

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[328]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[545]++;
                    // Hit the parent start tag so get out we're down counting children.
                    break;
                }

            }
            else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[326]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[546]++;
int CodeCoverConditionCoverageHelper_C179; if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (8)) == 0 || true) &&
 ((tt.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((tt.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[331]++;
                curs.toPrevToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[547]++;

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[332]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[548]++;
                break;
            }
}
}

            tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[549]++;
        }

        index = curs.currentTokenType().isStartdoc() ? -1 : index;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[550]++;

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[551]++;

        return index;
    }

    /**
     *
     * @return
     */
    XMLList children()
    {
        return allChildNodes(null);
    }

    /**
     *
     * @return
     */
    XMLList comments()
    {
        return matchChildren(XmlCursor.TokenType.COMMENT);
    }

    /**
     *
     * @param xml
     * @return
     */
    boolean contains(Object xml)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[552]++;
        boolean result = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[553]++;
int CodeCoverConditionCoverageHelper_C180;

        if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((xml instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[333]++;
            result = equivalentXml(xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[554]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[334]++;}

        return result;
    }

    /**
     *
     * @return
     */
    Object copy()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[555]++;
        XmlCursor srcCurs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[556]++;
int CodeCoverConditionCoverageHelper_C181;

        if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((srcCurs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[335]++;
            srcCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[557]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[336]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[558]++;

        XML xml = createEmptyXML(lib);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[559]++;

        XmlCursor destCurs = xml.newCursor();
        destCurs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[560]++;

        srcCurs.copyXml(destCurs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[561]++;

        destCurs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[562]++;
        srcCurs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[563]++;

        return xml;
    }

    /**
     *
     * @param name
     * @return
     */
    XMLList descendants(XMLName xmlName)
    {
        XMLList result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[564]++;
int CodeCoverConditionCoverageHelper_C182;
        if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[337]++;
            result = matchDescendantAttributes(xmlName);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[565]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[338]++;
            result = matchDescendantChildren(xmlName);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[566]++;
        }

        return result;
    }

    /**
     * The inScopeNamespaces method returns an Array of Namespace objects
     * representing the namespaces in scope for this XML object in the
     * context of its parent.
     *
     * @return Array of all Namespaces in scope for this XML Object.
     */
    Object[] inScopeNamespaces()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[567]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[568]++;
        Object[] namespaces = NamespaceHelper.inScopeNamespaces(lib, cursor);
        cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[569]++;
        return namespaces;
    }

    /**
     *
     * @param child
     * @param xml
     */
    XML insertChildAfter(Object child, Object xml)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[570]++;
int CodeCoverConditionCoverageHelper_C183;
        if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((child == null) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[339]++;
            // Spec says inserting after nothing is the same as prepending
            prependChild(xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[571]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[340]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[572]++;
int CodeCoverConditionCoverageHelper_C184; if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((child instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[341]++;
            insertChild((XML) child, xml, APPEND_CHILD);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[573]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[342]++;}
}

        return this;
    }

    /**
     *
     * @param child
     * @param xml
     */
    XML insertChildBefore(Object child, Object xml)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[574]++;
int CodeCoverConditionCoverageHelper_C185;
        if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((child == null) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[343]++;
            // Spec says inserting before nothing is the same as appending
            appendChild(xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[575]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[344]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[576]++;
int CodeCoverConditionCoverageHelper_C186; if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((child instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[345]++;
            insertChild((XML) child, xml, PREPEND_CHILD);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[577]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[346]++;}
}

        return this;
    }

    /**
     *
     * @return
     */
    boolean hasOwnProperty(XMLName xmlName)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[578]++;
        boolean hasProperty = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[579]++;
int CodeCoverConditionCoverageHelper_C187;

        if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((prototypeFlag) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[347]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[580]++;
            String property = xmlName.localName();
            hasProperty = (0 != findPrototypeId(property));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[581]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[348]++;
            hasProperty = (getPropertyList(xmlName).length() > 0);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[582]++;
        }

        return hasProperty;
    }

    /**
     *
     * @return
     */
    boolean hasComplexContent()
    {
        return !hasSimpleContent();
    }

    /**
     *
     * @return
     */
    boolean hasSimpleContent()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[583]++;
        boolean simpleContent = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[584]++;

        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[585]++;
int CodeCoverConditionCoverageHelper_C188;

        if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (8)) == 0 || true) &&
 ((curs.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((curs.isText()) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[349]++;
            return true;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[350]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[586]++;
int CodeCoverConditionCoverageHelper_C189;

        if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[351]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[587]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[352]++;}

        simpleContent = !(curs.toFirstChild());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[588]++;

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[589]++;

        return simpleContent;
    }

    /**
     * Length of an XML object is always 1, it's a list of XML objects of size 1.
     *
     * @return
     */
    int length()
    {
        return 1;
    }

    /**
     *
     * @return
     */
    String localName()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[590]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[591]++;
int CodeCoverConditionCoverageHelper_C190;
        if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((cursor.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[353]++;
            cursor.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[592]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[354]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[593]++;

        String name = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[594]++;
int CodeCoverConditionCoverageHelper_C191;

        if((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (32)) == 0 || true) &&
 ((cursor.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C191 |= (8)) == 0 || true) &&
 ((cursor.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((cursor.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 3) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[355]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[595]++;
            javax.xml.namespace.QName qname = cursor.getName();
            name = qname.getLocalPart();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[596]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[356]++;}
        cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[597]++;

        return name;
    }

    /**
     * The name method returns the qualified name associated with this XML object.
     *
     * @return The qualified name associated with this XML object.
     */
    QName name()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[598]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[599]++;
int CodeCoverConditionCoverageHelper_C192;
        if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((cursor.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[357]++;
            cursor.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[600]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[358]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[601]++;

        QName name = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[602]++;
int CodeCoverConditionCoverageHelper_C193;

        if((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (32)) == 0 || true) &&
 ((cursor.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C193 |= (8)) == 0 || true) &&
 ((cursor.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((cursor.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 3) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[359]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[603]++;
            javax.xml.namespace.QName qname = cursor.getName();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[604]++;
int CodeCoverConditionCoverageHelper_C194;
            if((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((cursor.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[361]++;
                name = new QName(lib, "", qname.getLocalPart(), "");
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[605]++;

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[362]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[606]++;
                String uri = qname.getNamespaceURI();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[607]++;
                String prefix = qname.getPrefix();
                name = new QName(lib, uri, qname.getLocalPart(), prefix);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[608]++;
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[360]++;}

        cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[609]++;

        return name;
    }

    /**
     *
     * @param prefix
     * @return
     */
    Object namespace(String prefix)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[610]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[611]++;
int CodeCoverConditionCoverageHelper_C195;
        if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((cursor.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[363]++;
            cursor.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[612]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[364]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[613]++;

        Object result = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[614]++;
int CodeCoverConditionCoverageHelper_C196;

        if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[365]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[615]++;
int CodeCoverConditionCoverageHelper_C197;
            if((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (8)) == 0 || true) &&
 ((cursor.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((cursor.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[367]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[616]++;
                Object[] inScopeNS = NamespaceHelper.inScopeNamespaces(lib, cursor);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[617]++;
                // XXX Is it reaaly necessary to create the second cursor?
                XmlCursor cursor2 = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[618]++;
int CodeCoverConditionCoverageHelper_C198;
                if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((cursor2.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[369]++;
                    cursor2.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[619]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[370]++;}

                result = NamespaceHelper.getNamespace(lib, cursor2, inScopeNS);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[620]++;

                cursor2.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[621]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[368]++;}

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[366]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[622]++;
            Map prefixToURI = NamespaceHelper.getAllNamespaces(lib, cursor);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[623]++;
            String uri = (String)prefixToURI.get(prefix);
            result = (uri == null) ? Undefined.instance : new Namespace(lib, prefix, uri);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[624]++;
        }

        cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[625]++;

        return result;
    }

    /**
     *
     * @return
     */
    Object[] namespaceDeclarations()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[626]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[627]++;
        Object[] namespaces = NamespaceHelper.namespaceDeclarations(lib, cursor);
        cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[628]++;
        return namespaces;
    }

    /**
     *
     * @return
     */
    Object nodeKind()
    {
        String result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[629]++;
        XmlCursor.TokenType tt = tokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[630]++;
int CodeCoverConditionCoverageHelper_C199;

        if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.ATTR) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[371]++;
            result = "attribute";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[631]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[372]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[632]++;
int CodeCoverConditionCoverageHelper_C200; if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.TEXT) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[373]++;
            result = "text";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[633]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[374]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[634]++;
int CodeCoverConditionCoverageHelper_C201; if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.COMMENT) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[375]++;
            result = "comment";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[635]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[376]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[636]++;
int CodeCoverConditionCoverageHelper_C202; if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.PROCINST) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[377]++;
            result = "processing-instruction";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[637]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[378]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[638]++;
int CodeCoverConditionCoverageHelper_C203; if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.START) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[379]++;
            result = "element";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[639]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[380]++;
            // A non-existant node has the nodeKind() of text
            result = "text";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[640]++;
        }
}
}
}
}

        return result;
    }

    /**
     *
     */
    void normalize()
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[641]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[642]++;
        TokenType tt = curs.currentTokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[643]++;
int CodeCoverConditionCoverageHelper_C204;

        // Walk through the tokens removing empty text nodes and merging adjacent text nodes.
        if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((tt.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[381]++;
            tt = curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[644]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[382]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[645]++;
int CodeCoverConditionCoverageHelper_C205;

        if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((tt.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[383]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[646]++;
            int nestLevel = 1;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[647]++;
            String previousText = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[648]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[64]++;


int CodeCoverConditionCoverageHelper_C206;

            while ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((nestLevel > 0) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false))
            {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[64]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[65]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[66]++;
}
                tt = curs.toNextToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[649]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[650]++;
int CodeCoverConditionCoverageHelper_C207;

                if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((tt == XmlCursor.TokenType.TEXT) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[385]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[651]++;
                    String currentText = curs.getChars().trim();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[652]++;
int CodeCoverConditionCoverageHelper_C208;

                    if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((currentText.trim().length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[387]++;
                        // Empty text node, remove.
                        removeToken(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[653]++;
                        curs.toPrevToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[654]++;

                    }
                    else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[388]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[655]++;
int CodeCoverConditionCoverageHelper_C209; if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((previousText == null) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[389]++;
                        // No previous text node, reset to trimmed version
                        previousText = currentText;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[656]++;

                    }
                    else
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[390]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[657]++;
                        // It appears that this case never happens with XBeans.
                        // Previous text node exists, concatenate
                        String newText = previousText + currentText;

                        curs.toPrevToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[658]++;
                        removeToken(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[659]++;
                        removeToken(curs);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[660]++;
                        curs.insertChars(newText);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[661]++;
                    }
}

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[386]++;
                    previousText = null;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[662]++;
                }
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[663]++;
int CodeCoverConditionCoverageHelper_C210;

                if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((tt.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[391]++;
                    nestLevel++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[664]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[392]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[665]++;
int CodeCoverConditionCoverageHelper_C211; if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((tt.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[393]++;
                    nestLevel--;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[666]++;

                }
                else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[394]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[667]++;
int CodeCoverConditionCoverageHelper_C212; if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((tt.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[395]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[668]++;
                    // Shouldn't get here, but just in case.
                    break;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[396]++;}
}
}
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[384]++;}


        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[669]++;
    }

    /**
     *
     * @return
     */
    Object parent()
    {
        Object parent;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[670]++;

        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[671]++;
int CodeCoverConditionCoverageHelper_C213;

        if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[397]++;
            // At doc level - no parent
            parent = Undefined.instance;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[672]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[398]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[673]++;
int CodeCoverConditionCoverageHelper_C214;
            if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((curs.toParent()) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[399]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[674]++;
int CodeCoverConditionCoverageHelper_C215;
                if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[401]++;
                    // Was top-level - no parent
                    parent = Undefined.instance;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[675]++;

                }
                else
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[402]++;
                    parent = getFromAnnotation(lib, findAnnotation(curs));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[676]++;
                }

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[400]++;
                // No parent
                parent = Undefined.instance;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[677]++;
            }
        }

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[678]++;

        return parent;
    }

    /**
     *
     * @param xml
     * @return
     */
    XML prependChild (Object xml)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[679]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[680]++;
int CodeCoverConditionCoverageHelper_C216;

        if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[403]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[681]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[404]++;}

        // Move the cursor to the first content token
        curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[682]++;

        insertChild(curs, xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[683]++;

        curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[684]++;

        return this;
    }

    /**
     *
     * @return
     */
    Object processingInstructions(XMLName xmlName)
    {
        return matchChildren(XmlCursor.TokenType.PROCINST, xmlName);
    }

    /**
     *
     * @param name
     * @return
     */
    boolean propertyIsEnumerable(Object name)
    {
        boolean result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[685]++;
int CodeCoverConditionCoverageHelper_C217;
        if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((name instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[405]++;
            result = (((Integer)name).intValue() == 0);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[686]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[406]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[687]++;
int CodeCoverConditionCoverageHelper_C218; if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((name instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[407]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[688]++;
            double x = ((Number)name).doubleValue();
            // Check that number is posotive 0
            result = (x == 0.0 && 1.0 / x > 0);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[689]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[408]++;
            result = ScriptRuntime.toString(name).equals("0");
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[690]++;
        }
}
        return result;
    }

    /**
     *
     * @param namespace
     */
    XML removeNamespace(Namespace ns)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[691]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[692]++;
boolean CodeCoverTryBranchHelper_Try11 = false;

        try
        {
CodeCoverTryBranchHelper_Try11 = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[693]++;
int CodeCoverConditionCoverageHelper_C219;
            if((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((cursor.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[410]++;
                cursor.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[694]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[411]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[695]++;
int CodeCoverConditionCoverageHelper_C220;
            if((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((cursor.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[412]++; return this;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[413]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[696]++;

            String nsPrefix = ns.prefix();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[697]++;
            String nsURI = ns.uri();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[698]++;
            Map prefixToURI = new HashMap();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[699]++;
            int depth = 1;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[700]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[67]++;


int CodeCoverConditionCoverageHelper_C221;

            while((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C221 |= (8)) == 0 || true) &&
 ((cursor.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((depth == 0) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 2) && false))
            {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[67]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[68]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[69]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[701]++;
int CodeCoverConditionCoverageHelper_C222;
                if((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((cursor.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[414]++;
                    // Get the namespaces declared in this element.
                    // The ones with undefined prefixes are not candidates
                    // for removal because they are used.
                    prefixToURI.clear();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[702]++;
                    NamespaceHelper.getNamespaces(cursor, prefixToURI);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[703]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[704]++;
                    ObjArray inScopeNSBag = new ObjArray();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[705]++;
                    Iterator i = prefixToURI.entrySet().iterator();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[706]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[70]++;


int CodeCoverConditionCoverageHelper_C223;
                    while((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false))
                    {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[70]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[71]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[72]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[707]++;
                        Map.Entry entry = (Map.Entry)i.next();
                        ns = new Namespace(lib, (String)entry.getKey(), (String)entry.getValue());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[708]++;
                        inScopeNSBag.add(ns);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[709]++;
                    }

                    // Add the URI we are looking for to avoid matching
                    // non-existing Namespaces.
                    ns = new Namespace(lib, nsURI);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[710]++;
                    inScopeNSBag.add(ns);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[711]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[712]++;

                    Object[] inScopeNS = inScopeNSBag.toArray();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[713]++;

                    // Check the element name
                    Namespace n = NamespaceHelper.getNamespace(lib, cursor,
                                                               inScopeNS);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[714]++;
int CodeCoverConditionCoverageHelper_C224;
                    if((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (32)) == 0 || true) &&
 ((nsURI.equals(n.uri())) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C224 |= (8)) == 0 || true) &&
 ((nsPrefix == null) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((nsPrefix.equals(n.prefix())) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 3) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[416]++;
                        // This namespace is used
                        return this;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[417]++;}

                    // Check the attributes
                    cursor.push();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[715]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[716]++;
                    boolean hasNext = cursor.toFirstAttribute();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[717]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[73]++;


int CodeCoverConditionCoverageHelper_C225;
                    while((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((hasNext) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false))
                    {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[73]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[74]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[75]++;
}
                        n = NamespaceHelper.getNamespace(lib, cursor, inScopeNS);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[718]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[719]++;
int CodeCoverConditionCoverageHelper_C226;
                        if((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (32)) == 0 || true) &&
 ((nsURI.equals(n.uri())) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C226 |= (8)) == 0 || true) &&
 ((nsPrefix == null) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((nsPrefix.equals(n.prefix())) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 3) && false))
                        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[418]++;
                            // This namespace is used
                            return this;

                        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[419]++;}

                        hasNext = cursor.toNextAttribute();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[720]++;
                    }
                    cursor.pop();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[721]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[722]++;
int CodeCoverConditionCoverageHelper_C227;

                    if((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((nsPrefix == null) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[420]++;
                        // Remove all namespaces declarations that match nsURI
                        i = prefixToURI.entrySet().iterator();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[723]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[724]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[76]++;


int CodeCoverConditionCoverageHelper_C228;
                        while((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false))
                        {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[76]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[77]--;
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.loops[78]++;
}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[725]++;
                            Map.Entry entry = (Map.Entry)i.next();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[726]++;
int CodeCoverConditionCoverageHelper_C229;
                            if((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((entry.getValue().equals(nsURI)) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[422]++;
                                NamespaceHelper.removeNamespace(cursor, (String)entry.getKey());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[727]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[423]++;}
                        }

                    }
                    else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[421]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[728]++;
int CodeCoverConditionCoverageHelper_C230; if((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((nsURI.equals(prefixToURI.get(nsPrefix))) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false))
                    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[424]++;
                        // Remove the namespace declaration that matches nsPrefix
                        NamespaceHelper.removeNamespace(cursor, String.valueOf(nsPrefix));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[729]++;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[425]++;}
}

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[415]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[730]++;

                switch(cursor.toNextToken().intValue())
                {
                case XmlCursor.TokenType.INT_START:
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[426]++;
                    depth++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[731]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[732]++;
                    break;
                case XmlCursor.TokenType.INT_END:
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[427]++;
                    depth--;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[733]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[734]++;
                    break; default : CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[428]++;
                }
            }
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[409]++;
}
            cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[735]++;
        }

        return this;
    }

    XML replace(long index, Object xml)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[736]++;
        XMLList xlChildToReplace = child(index);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[737]++;
int CodeCoverConditionCoverageHelper_C231;
        if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((xlChildToReplace.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[429]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[738]++;
            // One exists an that index
            XML childToReplace = xlChildToReplace.item(0);
            insertChildAfter(childToReplace, xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[739]++;
            removeChild(index);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[740]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[430]++;}
        return this;
    }

    /**
     *
     * @param propertyName
     * @param xml
     * @return
     */
    XML replace(XMLName xmlName, Object xml)
    {
        putXMLProperty(xmlName, xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[741]++;
        return this;
    }

    /**
     *
     * @param xml
     */
    XML setChildren(Object xml)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[742]++;
        // remove all children
        XMLName xmlName = XMLName.formStar();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[743]++;
        XMLList matches = getPropertyList(xmlName);
        matches.remove();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[744]++;

        // append new children
        appendChild(xml);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[745]++;

        return this;
    }

    /**
     *
     * @param name
     */
    void setLocalName(String localName)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[746]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[747]++;
boolean CodeCoverTryBranchHelper_Try12 = false;

        try
        {
CodeCoverTryBranchHelper_Try12 = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[748]++;
int CodeCoverConditionCoverageHelper_C232;
            if((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((cursor.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[432]++;
                cursor.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[749]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[433]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[750]++;
int CodeCoverConditionCoverageHelper_C233;

            if((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (8)) == 0 || true) &&
 ((cursor.isText()) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((cursor.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[434]++; return;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[435]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[751]++;


            javax.xml.namespace.QName qname = cursor.getName();
            cursor.setName(new javax.xml.namespace.QName(
                qname.getNamespaceURI(), localName, qname.getPrefix()));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[752]++;
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[431]++;
}
            cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[753]++;
        }
    }

    /**
     *
     * @param name
     */
    void setName(QName qname)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[754]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[755]++;
boolean CodeCoverTryBranchHelper_Try13 = false;

        try
        {
CodeCoverTryBranchHelper_Try13 = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[756]++;
int CodeCoverConditionCoverageHelper_C234;
            if((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((cursor.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[437]++;
                cursor.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[757]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[438]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[758]++;
int CodeCoverConditionCoverageHelper_C235;

            if((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (8)) == 0 || true) &&
 ((cursor.isText()) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((cursor.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[439]++; return;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[440]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[759]++;
int CodeCoverConditionCoverageHelper_C236;

            if((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((cursor.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[441]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[760]++;
                String localName = qname.localName();
                cursor.setName(new javax.xml.namespace.QName(localName));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[761]++;

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[442]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[762]++;
                String prefix = qname.prefix();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[763]++;
int CodeCoverConditionCoverageHelper_C237;
                if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[443]++; prefix = "";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[764]++;
 } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[444]++;}
                cursor.setName(new javax.xml.namespace.QName(
                    qname.uri(), qname.localName(), prefix));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[765]++;
            }
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try13 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[436]++;
}
            cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[766]++;
        }
    }

    /**
     *
     * @param ns
     */
    void setNamespace(Namespace ns)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[767]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[768]++;
boolean CodeCoverTryBranchHelper_Try14 = false;

        try
        {
CodeCoverTryBranchHelper_Try14 = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[769]++;
int CodeCoverConditionCoverageHelper_C238;
            if((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((cursor.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[446]++;
                cursor.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[770]++;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[447]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[771]++;
int CodeCoverConditionCoverageHelper_C239;

            if((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (32)) == 0 || true) &&
 ((cursor.isText()) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C239 |= (8)) == 0 || true) &&
 ((cursor.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((cursor.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 3) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 3) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[448]++; return;
} else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[449]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[772]++;

            String prefix = ns.prefix();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[773]++;
int CodeCoverConditionCoverageHelper_C240;
            if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[450]++;
                prefix = "";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[774]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[451]++;}
            cursor.setName(new javax.xml.namespace.QName(
                ns.uri(), localName(), prefix));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[775]++;
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try14 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[445]++;
}
            cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[776]++;
        }
    }

    /**
     *
     * @return
     */
    XMLList text()
    {
        return matchChildren(XmlCursor.TokenType.TEXT);
    }

    /**
     *
     * @return
     */
    public String toString()
    {
        String result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[777]++;
        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[778]++;
int CodeCoverConditionCoverageHelper_C241;

        if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[452]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[779]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[453]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[780]++;
int CodeCoverConditionCoverageHelper_C242;

        if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((curs.isText()) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[454]++;
             result = curs.getChars();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[781]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[455]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[782]++;
int CodeCoverConditionCoverageHelper_C243; if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (8)) == 0 || true) &&
 ((curs.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[456]++;
            result = curs.getTextValue();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[783]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[457]++;
            result = toXMLString(0);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[784]++;
        }
}

        return result;
    }

    String toSource(int indent)
    {
        // XXX Does toXMLString always return valid XML literal?
        return toXMLString(indent);
    }

    /**
     *
     * @return
     */
    String toXMLString(int indent)
    {
        // XXX indent is ignored

        String result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[785]++;

        XmlCursor curs = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[786]++;
int CodeCoverConditionCoverageHelper_C244;

        if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((curs.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[458]++;
            curs.toFirstContentToken();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[787]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[459]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[788]++;
boolean CodeCoverTryBranchHelper_Try15 = false;

        try
        {
CodeCoverTryBranchHelper_Try15 = true;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[789]++;
int CodeCoverConditionCoverageHelper_C245;
            if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((curs.isText()) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[461]++;
                result = curs.getChars();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[790]++;

            }
            else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[462]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[791]++;
int CodeCoverConditionCoverageHelper_C246; if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((curs.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[463]++;
                result = curs.getTextValue();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[792]++;

            }
            else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[464]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[793]++;
int CodeCoverConditionCoverageHelper_C247; if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (8)) == 0 || true) &&
 ((curs.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((curs.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 2) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[465]++;
                result = XML.dumpNode(curs, getOptions());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[794]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[795]++;

                // todo: XBeans-dependent hack here
                // If it's a comment or PI, take off the xml-frament stuff
                String start = "<xml-fragment>";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[796]++;
                String end = "</xml-fragment>";
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[797]++;
int CodeCoverConditionCoverageHelper_C248;

                if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((result.startsWith(start)) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[467]++;
                    result = result.substring(start.length());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[798]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[468]++;}
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[799]++;
int CodeCoverConditionCoverageHelper_C249;

                if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((result.endsWith(end)) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false))
                {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[469]++;
                    result = result.substring(0, result.length() - end.length());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[800]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[470]++;}

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[466]++;
                result = XML.dumpNode(curs, getOptions());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[801]++;
            }
}
}
        }
        finally
        {
if ( CodeCoverTryBranchHelper_Try15 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[460]++;
}
            curs.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[802]++;
        }

        return result;
    }

    /**
     *
     * @return
     */
    Object valueOf()
    {
        return this;
    }

    //
    // Other public Functions from XMLObject
    //

    /**
     *
     * @param target
     * @return
     */
    boolean equivalentXml(Object target)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[803]++;
        boolean result = false;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[804]++;
int CodeCoverConditionCoverageHelper_C250;

        if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((target instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[471]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[805]++;
            XML otherXml = (XML) target;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[806]++;

            // Compare with toString() if either side is text node or attribute
            // otherwise compare as XML
            XmlCursor.TokenType thisTT = tokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[807]++;
            XmlCursor.TokenType otherTT = otherXml.tokenType();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[808]++;
int CodeCoverConditionCoverageHelper_C251;
            if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (128)) == 0 || true) &&
 ((thisTT == XmlCursor.TokenType.ATTR) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C251 |= (32)) == 0 || true) &&
 ((otherTT == XmlCursor.TokenType.ATTR) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C251 |= (8)) == 0 || true) &&
 ((thisTT == XmlCursor.TokenType.TEXT) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((otherTT == XmlCursor.TokenType.TEXT) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 4) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 4) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[473]++;
                result = toString().equals(otherXml.toString());
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[809]++;

            }
            else
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[474]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[810]++;
                XmlCursor cursOne = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[811]++;
                XmlCursor cursTwo = otherXml.newCursor();

                result = LogicalEquality.nodesEqual(cursOne, cursTwo);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[812]++;

                cursOne.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[813]++;
                cursTwo.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[814]++;

// Old way of comparing by string.
//                boolean orgPrettyPrinting = prototype.prettyPrinting;
//                prototype.prettyPrinting = true;
//                result = toXMLString(0).equals(otherXml.toXMLString(0));
//                prototype.prettyPrinting = orgPrettyPrinting;
            }

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[472]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[815]++;
int CodeCoverConditionCoverageHelper_C252; if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((target instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[475]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[816]++;
            XMLList otherList = (XMLList) target;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[817]++;
int CodeCoverConditionCoverageHelper_C253;

            if ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((otherList.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false))
            {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[477]++;
                result = equivalentXml(otherList.getXmlFromAnnotation(0));
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[818]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[478]++;}

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[476]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[819]++;
int CodeCoverConditionCoverageHelper_C254; if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[479]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[820]++;
            String otherStr = ScriptRuntime.toString(target);

            result = toString().equals(otherStr);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[821]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[480]++;}
}
}

        return result;
    }

    /**
     *
     * @param name
     * @param start
     * @return
     */
    XMLList getPropertyList(XMLName name)
    {
        XMLList result;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[822]++;
int CodeCoverConditionCoverageHelper_C255;

        // Get the named property
        if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((name.isDescendants()) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[481]++;
            result = descendants(name);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[823]++;

        }
        else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[482]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[824]++;
int CodeCoverConditionCoverageHelper_C256; if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((name.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) && false))
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[483]++;
            result = attribute(name);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[825]++;

        }
        else
        {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[484]++;
            result = child(name);
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[826]++;
        }
}

        return result;
    }

    protected Object jsConstructor(Context cx, boolean inNewExpr,
                                   Object[] args)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[827]++;
int CodeCoverConditionCoverageHelper_C257;
        if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[485]++;
            return createFromJS(lib, "");

        } else {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[486]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[828]++;
            Object arg0 = args[0];
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[829]++;
int CodeCoverConditionCoverageHelper_C258;
            if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C258 |= (8)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((arg0 instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 2) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 2) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[487]++;
                // XML(XML) returns the same object.
                return arg0;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[488]++;}
            return createFromJS(lib, arg0);
        }
    }

    /**
     * See ECMA 357, 11_2_2_1, Semantics, 3_f.
     */
    public Scriptable getExtraMethodSource(Context cx)
    {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[830]++;
int CodeCoverConditionCoverageHelper_C259;
        if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[489]++;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[831]++;
            String src = toString();
            return ScriptRuntime.toObjectOrNull(cx, src);

        } else {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[490]++;}
        return null;
    }

    XmlObject getXmlObject()
    {
        XmlObject xo;
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[832]++;
        XmlCursor cursor = newCursor();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[833]++;
boolean CodeCoverTryBranchHelper_Try16 = false;
        try {
CodeCoverTryBranchHelper_Try16 = true;
            xo = cursor.getObject();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[834]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try16 ) {
  CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.branches[491]++;
}
            cursor.dispose();
CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap.statements[835]++;
        }
        return xo;
    }
}

class CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap ());
  }
    public static long[] statements = new long[836];
    public static long[] branches = new long[492];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[260];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XML.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,2,0,1,1,1,2,2,1,3,3,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,3,1,1,2,2,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,2,1,1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,3,1,3,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,3,1,3,1,1,1,1,1,1,2,1,2,1,1,1,3,1,1,1,2,1,1,1,2,1,1,1,3,1,1,1,1,1,1,2,1};
    for (int i = 1; i <= 259; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[79];

  public CodeCoverCoverageCounter$6ubyiuewjjwplib9a8ecx24s1aap () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XML.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 835; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 491; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 259; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 78; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XML.java");
      for (int i = 1; i <= 835; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 491; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 259; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 26; i++) {
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

