/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import java.util.Vector;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.*;

import org.apache.xmlbeans.XmlCursor;

class XMLList extends XMLObjectImpl implements Function
{
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.ping();
  }

    static final long serialVersionUID = -4543618751670781135L;
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[1]++;
  }

    static class AnnotationList
    {
        private Vector v;


        AnnotationList ()
        {
            v = new Vector();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[2]++;
        }


        void add (XML.XScriptAnnotation n)
        {
            v.add(n);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[3]++;
        }


        XML.XScriptAnnotation item(int index)
        {
            return (XML.XScriptAnnotation)(v.get(index));
        }


        void remove (int index)
        {
            v.remove(index);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[4]++;
        }


        int length()
        {
            return v.size();
        }
    };


    // Fields
    private AnnotationList    _annos;

    private XMLObjectImpl targetObject = null;
  {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[5]++;
  }
    private javax.xml.namespace.QName targetProperty = null;
  {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[6]++;
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Constructors
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    XMLList(XMLLibImpl lib)
    {
        super(lib, lib.xmlListPrototype);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[7]++;
        _annos = new AnnotationList();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[8]++;
    }

    /**
     *
     * @param inputObject
     */
    XMLList(XMLLibImpl lib, Object inputObject)
    {
        super(lib, lib.xmlListPrototype);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[9]++;
        String frag;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((inputObject == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((inputObject instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[1]++;
            frag = "";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[11]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[2]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[12]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((inputObject instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[3]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[13]++;
            XML xml = (XML) inputObject;

            _annos = new AnnotationList();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[14]++;
            _annos.add(xml.getAnnotation());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[15]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[4]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[16]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((inputObject instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[5]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[17]++;
            XMLList xmll = (XMLList) inputObject;

            _annos = new AnnotationList();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[18]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < xmll._annos.length()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[1]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[2]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[3]++;
}
                _annos.add(xmll._annos.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[20]++;
            }

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[6]++;
            frag = ScriptRuntime.toString(inputObject).trim();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[21]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;

            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((frag.startsWith("<>")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[7]++;
                frag = "<>" + frag + "</>";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[23]++;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[8]++;}

            frag = "<fragment>" + frag.substring(2);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[24]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((frag.endsWith("</>")) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[9]++;
                throw ScriptRuntime.typeError("XML with anonymous tag missing end anonymous tag");

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[10]++;}

            frag = frag.substring(0, frag.length() - 3) + "</fragment>";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[26]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[27]++;

            XML orgXML = XML.createFromJS(lib, frag);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[28]++;

            // Now orphan the children and add them to our XMLList.
            XMLList children = orgXML.children();

            _annos = new AnnotationList();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[29]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[30]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < children._annos.length()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[4]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[5]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[6]++;
}
                // Copy here is so that they'll be orphaned (parent() will be undefined)
                _annos.add(((XML) children.item(i).copy()).getAnnotation());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[31]++;
            }
        }
}
}
    }

    //
    //
    // TargetObject/Property accessors
    //
    //

    /**
     *
     * @param object
     * @param property
     */
    void setTargets(XMLObjectImpl object, javax.xml.namespace.QName property)
    {
        targetObject = object;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[32]++;
        targetProperty = property;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[33]++;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Private functions
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param index
     * @return
     */
    XML getXmlFromAnnotation(int index)
    {
        XML retVal;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[11]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[35]++;
            XML.XScriptAnnotation anno = _annos.item(index);
            retVal = XML.getFromAnnotation(lib, anno);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[36]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[12]++;
            retVal = null;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[37]++;
        }

        return retVal;
    }

    /**
     *
     * @param index
     */
    private void internalRemoveFromList (int index)
    {
        _annos.remove(index);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[38]++;
    }

    /**
     *
     * @param index
     * @param xml
     */
    void replace(int index, XML xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[13]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[40]++;
            AnnotationList newAnnoList = new AnnotationList();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[41]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;

            // Copy upto item to replace.
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < index) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[7]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[8]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[9]++;
}
                newAnnoList.add(_annos.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[42]++;
            }

            newAnnoList.add(xml.getAnnotation());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[43]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[44]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[10]++;


int CodeCoverConditionCoverageHelper_C11;

            // Skip over old item we're going to replace we've already add new item on above line.
            for (int i = index + 1;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[10]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[11]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[12]++;
}
                newAnnoList.add(_annos.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[45]++;
            }

            _annos = newAnnoList;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[46]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[14]++;}
    }

    /**
     *
     * @param index
     * @param xml
     */
    private void insert(int index, XML xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[15]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[48]++;
            AnnotationList newAnnoList = new AnnotationList();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[49]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[13]++;


int CodeCoverConditionCoverageHelper_C13;

            // Copy upto item to insert.
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < index) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[13]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[14]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[15]++;
}
                newAnnoList.add(_annos.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[50]++;
            }

            newAnnoList.add(xml.getAnnotation());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[51]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[52]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[16]++;


int CodeCoverConditionCoverageHelper_C14;

            for (int i = index;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[16]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[17]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[18]++;
}
                newAnnoList.add(_annos.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[53]++;
            }

            _annos = newAnnoList;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[54]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[16]++;}
    }

    //
    //
    //  methods overriding ScriptableObject
    //
    //

    public String getClassName ()
    {
        return "XMLList";
    }

    //
    //
    //  methods overriding IdScriptableObject
    //
    //

    /**
     *
     * @param index
     * @param start
     * @return
     */
    public Object get(int index, Scriptable start)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[55]++;
int CodeCoverConditionCoverageHelper_C15;
        //Log("get index: " + index);

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[17]++;
            return getXmlFromAnnotation(index);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[18]++;
            return Scriptable.NOT_FOUND;
        }
    }

    /**
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
        return 0 <= index && index < length();
    }

    /**
     *
     * @param name
     * @param value
     */
    void putXMLProperty(XMLName xmlName, Object value)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
        //Log("put property: " + name);

        // Special-case checks for undefined and null
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[19]++;
            value = "null";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[57]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[20]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[58]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[21]++;
            value = "undefined";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[59]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[22]++;}
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[60]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((length() > 1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[23]++;
            throw ScriptRuntime.typeError("Assignment to lists with more that one item is not supported");

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[24]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[61]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[25]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[62]++;
int CodeCoverConditionCoverageHelper_C20;
            // Secret sauce for super-expandos.
            // We set an element here, and then add ourselves to our target.
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((targetObject != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((targetProperty != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((targetProperty.getLocalPart().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[27]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[63]++;
                // Add an empty element with our targetProperty name and then set it.
                XML xmlValue = XML.createTextElement(lib, targetProperty, "");
                addToList(xmlValue);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[64]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[65]++;
int CodeCoverConditionCoverageHelper_C21;

                if((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[29]++;
                    setAttribute(xmlName, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[66]++;

                }
                else
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[30]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[67]++;
                    XML xml = item(0);
                    xml.putXMLProperty(xmlName, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[68]++;

                    // Update the list with the new item at location 0.
                    replace(0, item(0));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[69]++;
                }
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[70]++;

                // Now add us to our parent
                XMLName name2 = XMLName.formProperty(targetProperty.getNamespaceURI(), targetProperty.getLocalPart());
                targetObject.putXMLProperty(name2, this);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[71]++;

            }
            else
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[28]++;
                throw ScriptRuntime.typeError("Assignment to empty XMLList without targets not supported");
            }

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[26]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[72]++;
int CodeCoverConditionCoverageHelper_C22; if((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[31]++;
            setAttribute(xmlName, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[73]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[32]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[74]++;
            XML xml = item(0);
            xml.putXMLProperty(xmlName, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[75]++;

            // Update the list with the new item at location 0.
            replace(0, item(0));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[76]++;
        }
}
}
    }

    /**
     *
     * @param name
     * @return
     */
    Object getXMLProperty(XMLName name)
    {
        return getPropertyList(name);
    }

    /**
     *
     * @param index
     * @param value
     */
    public void put(int index, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[77]++;
        Object parent = Undefined.instance;
        // Convert text into XML if needed.
        XMLObject xmlValue;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[78]++;
int CodeCoverConditionCoverageHelper_C23;

        // Special-case checks for undefined and null
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[33]++;
            value = "null";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[79]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[34]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[80]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((value instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[35]++;
            value = "undefined";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[81]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[36]++;}
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[82]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((value instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[37]++;
            xmlValue = (XMLObject) value;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[83]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[38]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[84]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((targetProperty == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[39]++;
                xmlValue = XML.createFromJS(lib, value.toString());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[85]++;

            }
            else
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[40]++;
                xmlValue = XML.createTextElement(lib, targetProperty, value.toString());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[86]++;
            }
        }
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[87]++;
int CodeCoverConditionCoverageHelper_C27;

        // Find the parent
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[41]++;
            parent = item(index).parent();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[88]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[42]++;
            // Appending
            parent = parent();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[89]++;
        }
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[90]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((parent instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[43]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[91]++;
            // found parent, alter doc
            XML xmlParent = (XML) parent;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[92]++;
int CodeCoverConditionCoverageHelper_C29;

            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[45]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[93]++;
                // We're replacing the the node.
                XML xmlNode = getXmlFromAnnotation(index);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[94]++;
int CodeCoverConditionCoverageHelper_C30;

                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[47]++;
                    xmlNode.replaceAll((XML) xmlValue);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[95]++;
                    replace(index, xmlNode);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[96]++;

                }
                else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[48]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[97]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[49]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[98]++;
                    // Replace the first one, and add the rest on the list.
                    XMLList list = (XMLList) xmlValue;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[99]++;
int CodeCoverConditionCoverageHelper_C32;

                    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((list.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false))
                    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[51]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[100]++;
                        int lastIndexAdded = xmlNode.childIndex();
                        xmlNode.replaceAll(list.item(0));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[101]++;
                        replace(index, list.item(0));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[102]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[103]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[19]++;


int CodeCoverConditionCoverageHelper_C33;

                        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((i < list.length()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false); i++)
                        {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[19]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[20]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[21]++;
}
                            xmlParent.insertChildAfter(xmlParent.getXmlChild(lastIndexAdded), list.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[104]++;
                            lastIndexAdded++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[105]++;
                            insert(index + i, list.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[106]++;
                        }

                    } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[52]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[50]++;}
}

            }
            else
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[46]++;
                // Appending
                xmlParent.appendChild(xmlValue);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[107]++;
                addToList(xmlParent.getXmlChild(index));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[108]++;
            }

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[44]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[109]++;
int CodeCoverConditionCoverageHelper_C34;
            // Don't all have same parent, no underlying doc to alter
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[53]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[110]++;
                XML xmlNode = XML.getFromAnnotation(lib, _annos.item(index));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[111]++;
int CodeCoverConditionCoverageHelper_C35;

                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[55]++;
                    xmlNode.replaceAll((XML) xmlValue);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[112]++;
                    replace(index, xmlNode);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[113]++;

                }
                else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[56]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[114]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[57]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[115]++;
                    // Replace the first one, and add the rest on the list.
                    XMLList list = (XMLList) xmlValue;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[116]++;
int CodeCoverConditionCoverageHelper_C37;

                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((list.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false))
                    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[59]++;
                        xmlNode.replaceAll(list.item(0));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[117]++;
                        replace(index, list.item(0));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[118]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[119]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[22]++;


int CodeCoverConditionCoverageHelper_C38;

                        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((i < list.length()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); i++)
                        {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[22]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[23]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[24]++;
}
                            insert(index + i, list.item(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[120]++;
                        }

                    } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[60]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[58]++;}
}

            }
            else
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[54]++;
                addToList(xmlValue);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[121]++;
            }
        }
    }


    /**
     *
     * @param name
     */
    void deleteXMLProperty(XMLName name)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[122]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[25]++;


int CodeCoverConditionCoverageHelper_C39;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[25]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[26]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[27]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[123]++;
            XML xml = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[124]++;
int CodeCoverConditionCoverageHelper_C40;

            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((xml.tokenType() == XmlCursor.TokenType.START) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[61]++;
                xml.deleteXMLProperty(name);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[125]++;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[62]++;}
        }
    }

    /**
     *
     * @param index
     */
    public void delete(int index)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[126]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((index < length()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[63]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[127]++;
            XML xml = getXmlFromAnnotation(index);

            xml.remove();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[128]++;

            internalRemoveFromList(index);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[129]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[64]++;}
    }


    /**
     *
     * @return
     */
    public Object[] getIds()
    {
        Object enumObjs[];
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[130]++;
int CodeCoverConditionCoverageHelper_C42;

        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((prototypeFlag) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[65]++;
            enumObjs = new Object[0];
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[131]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[66]++;
            enumObjs = new Object[length()];
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[132]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[133]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[28]++;


int CodeCoverConditionCoverageHelper_C43;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((i < enumObjs.length) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[28]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[29]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[30]++;
}
                enumObjs[i] = new Integer(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[134]++;
            }
        }

        return enumObjs;
    }

    /**
     *
     * @return
     */
    public Object[] getIdsForDebug()
    {
        return getIds();
    }


    // XMLList will remove will delete all items in the list (a set delete) this differs from the XMLList delete operator.
    void remove ()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[135]++;
        int nLen = length();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[136]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[31]++;


int CodeCoverConditionCoverageHelper_C44;
        for (int i = nLen - 1;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); i--)
        {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[31]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[32]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[33]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[137]++;
            XML xml = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[138]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((xml != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[67]++;
                xml.remove();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[139]++;
                internalRemoveFromList(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[140]++;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[68]++;}
        }
    }

    /**
     *
     * @param index
     * @return
     */
    XML item (int index)
    {
        return _annos != null
            ? getXmlFromAnnotation(index) : XML.createEmptyXML(lib);
    }


    /**
     *
     * @param name
     * @param value
     */
    private void setAttribute (XMLName xmlName, Object value)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[141]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[34]++;


int CodeCoverConditionCoverageHelper_C46;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[34]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[35]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[36]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[142]++;
            XML xml = getXmlFromAnnotation(i);
            xml.setAttribute(xmlName, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[143]++;
        }
    }


    /**
     *
     * @param toAdd
     */
    void addToList(Object toAdd)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[144]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((toAdd instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[69]++;
            // Missing argument do nothing...
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[70]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[145]++;
int CodeCoverConditionCoverageHelper_C48;

        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((toAdd instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[71]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[146]++;
            XMLList xmlSrc = (XMLList)toAdd;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[147]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[37]++;


int CodeCoverConditionCoverageHelper_C49;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((i < xmlSrc.length()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[37]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[38]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[39]++;
}
                _annos.add((xmlSrc.item(i)).getAnnotation());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[148]++;
            }

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[72]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[149]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((toAdd instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[73]++;
            _annos.add(((XML)(toAdd)).getAnnotation());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[150]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[74]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[151]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((toAdd instanceof XML.XScriptAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[75]++;
            _annos.add((XML.XScriptAnnotation)toAdd);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[152]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[76]++;}
}
}
    }

    //
    //
    // Methods from section 12.4.4 in the spec
    //
    //

    /**
     *
     * @param toAdd
     */
    XML addNamespace(Namespace ns)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[153]++;
int CodeCoverConditionCoverageHelper_C52;
        if((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[77]++;
            return getXmlFromAnnotation(0).addNamespace(ns);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[78]++;
            throw ScriptRuntime.typeError("The addNamespace method works only on lists containing one item");
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    XML appendChild(Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[154]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[79]++;
            return getXmlFromAnnotation(0).appendChild(xml);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[80]++;
            throw ScriptRuntime.typeError("The appendChild method works only on lists containing one item");
        }
    }

    /**
     *
     * @param attr
     * @return
     */
    XMLList attribute(XMLName xmlName)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[155]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[156]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[40]++;


int CodeCoverConditionCoverageHelper_C54;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[40]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[41]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[42]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[157]++;
            XML xml = getXmlFromAnnotation(i);
            result.addToList(xml.attribute(xmlName));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[158]++;
        }

        return result;
    }

    /**
     *
     * @return
     */
    XMLList attributes()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[159]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[160]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[43]++;


int CodeCoverConditionCoverageHelper_C55;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[43]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[44]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[45]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[161]++;
            XML xml = getXmlFromAnnotation(i);
            result.addToList(xml.attributes());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[162]++;
        }

        return result;
    }

    XMLList child(long index)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[163]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[164]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[46]++;


int CodeCoverConditionCoverageHelper_C56;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[46]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[47]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[48]++;
}
            result.addToList(getXmlFromAnnotation(i).child(index));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[165]++;
        }

        return result;
    }

    XMLList child(XMLName xmlName)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[166]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[167]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[49]++;


int CodeCoverConditionCoverageHelper_C57;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[49]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[50]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[51]++;
}
            result.addToList(getXmlFromAnnotation(i).child(xmlName));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[168]++;
        }

        return result;
    }

    /**
     *
     * @return
     */
    int childIndex()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[169]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[81]++;
            return getXmlFromAnnotation(0).childIndex();

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[82]++;
            throw ScriptRuntime.typeError("The childIndex method works only on lists containing one item");
        }
    }

    /**
     *
     * @return
     */
    XMLList children()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[170]++;
        Vector v = new Vector();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[171]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[52]++;


int CodeCoverConditionCoverageHelper_C59;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[52]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[53]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[54]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[172]++;
            XML xml = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[173]++;
int CodeCoverConditionCoverageHelper_C60;

            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((xml != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[83]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[174]++;
                Object o = xml.children();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[175]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((o instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[85]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[176]++;
                    XMLList childList = (XMLList)o;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[177]++;

                    int cChildren = childList.length();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[178]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[55]++;


int CodeCoverConditionCoverageHelper_C62;
                    for (int j = 0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((j < cChildren) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); j++)
                    {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[55]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[56]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[57]++;
}
                        v.addElement(childList.item(j));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[179]++;
                    }

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[86]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[84]++;}
        }
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[180]++;

        XMLList allChildren = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[181]++;
        int sz = v.size();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[182]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[58]++;


int CodeCoverConditionCoverageHelper_C63;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((i < sz) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[58]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[59]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[60]++;
}
            allChildren.addToList(v.get(i));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[183]++;
        }

        return allChildren;
    }

    /**
     *
     * @return
     */
    XMLList comments()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[184]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[185]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[61]++;


int CodeCoverConditionCoverageHelper_C64;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[61]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[62]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[63]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[186]++;
            XML xml = getXmlFromAnnotation(i);

            result.addToList(xml.comments());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[187]++;
        }

        return result;
    }

    /**
     *
     * @param xml
     * @return
     */
    boolean contains(Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[188]++;
        boolean result = false;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[189]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[64]++;


int CodeCoverConditionCoverageHelper_C65;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[64]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[65]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[66]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[190]++;
            XML member = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[191]++;
int CodeCoverConditionCoverageHelper_C66;

            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((member.equivalentXml(xml)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[87]++;
                result = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[192]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[193]++;
                break;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[88]++;}
        }

        return result;
    }

    /**
     *
     * @return
     */
    Object copy()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[194]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[195]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[67]++;


int CodeCoverConditionCoverageHelper_C67;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[67]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[68]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[69]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[196]++;
            XML xml = getXmlFromAnnotation(i);
            result.addToList(xml.copy());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[197]++;
        }

        return result;
    }

    /**
     *
     * @return
     */
    XMLList descendants(XMLName xmlName)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[198]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[199]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[70]++;


int CodeCoverConditionCoverageHelper_C68;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[70]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[71]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[72]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[200]++;
            XML xml = getXmlFromAnnotation(i);
            result.addToList(xml.descendants(xmlName));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[201]++;
        }

        return result;
    }

    /**
     *
     * @return
     */
    Object[] inScopeNamespaces()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[202]++;
int CodeCoverConditionCoverageHelper_C69;
        if((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[89]++;
            return getXmlFromAnnotation(0).inScopeNamespaces();

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[90]++;
            throw ScriptRuntime.typeError("The inScopeNamespaces method works only on lists containing one item");
        }
    }

    /**
     *
     * @param child
     * @param xml
     */
    XML insertChildAfter(Object child, Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[203]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[91]++;
            return getXmlFromAnnotation(0).insertChildAfter(child, xml);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[92]++;
            throw ScriptRuntime.typeError("The insertChildAfter method works only on lists containing one item");
        }
    }

    /**
     *
     * @param child
     * @param xml
     */
    XML insertChildBefore(Object child, Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[204]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[93]++;
            return getXmlFromAnnotation(0).insertChildAfter(child, xml);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[94]++;
            throw ScriptRuntime.typeError("The insertChildBefore method works only on lists containing one item");
        }
    }

    /**
     *
     * @return
     */
    boolean hasOwnProperty(XMLName xmlName)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[205]++;
        boolean hasProperty = false;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[206]++;
int CodeCoverConditionCoverageHelper_C72;

        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((prototypeFlag) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[95]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[207]++;
            String property = xmlName.localName();
            hasProperty = (0 != findPrototypeId(property));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[208]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[96]++;
            hasProperty = (getPropertyList(xmlName).length() > 0);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[209]++;
        }

        return hasProperty;
    }

    /**
     *
     * @return
     */
    boolean hasComplexContent()
    {
        boolean complexContent;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[210]++;
        int length = length();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[211]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[97]++;
            complexContent = false;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[212]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[98]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[213]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((length == 1) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[99]++;
            complexContent = getXmlFromAnnotation(0).hasComplexContent();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[214]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[100]++;
            complexContent = false;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[215]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[216]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[73]++;


int CodeCoverConditionCoverageHelper_C75;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[73]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[74]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[75]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[217]++;
                XML nextElement = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[218]++;
int CodeCoverConditionCoverageHelper_C76;
                if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((nextElement.tokenType() == XmlCursor.TokenType.START) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[101]++;
                    complexContent = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[219]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[220]++;
                    break;

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[102]++;}
            }
        }
}

        return complexContent;
    }

    /**
     *
     * @return
     */
    boolean hasSimpleContent()
    {
        boolean simpleContent;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[221]++;
        int length = length();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[222]++;
int CodeCoverConditionCoverageHelper_C77;

        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[103]++;
            simpleContent = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[223]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[104]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[224]++;
int CodeCoverConditionCoverageHelper_C78; if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((length == 1) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[105]++;
            simpleContent = getXmlFromAnnotation(0).hasSimpleContent();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[225]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[106]++;
            simpleContent = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[226]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[227]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[76]++;


int CodeCoverConditionCoverageHelper_C79;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[76]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[77]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[78]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[228]++;
                XML nextElement = getXmlFromAnnotation(i);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[229]++;
int CodeCoverConditionCoverageHelper_C80;
                if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((nextElement.tokenType() == XmlCursor.TokenType.START) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[107]++;
                    simpleContent = false;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[230]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[231]++;
                    break;

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[108]++;}
            }
        }
}

        return simpleContent;
    }

    /**
     *
     * @return
     */
    int length()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[232]++;
        int result = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[233]++;
int CodeCoverConditionCoverageHelper_C81;

        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((_annos != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[109]++;
            result = _annos.length();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[234]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[110]++;}

        return result;
    }

    /**
     *
     * @return
     */
    String localName()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[235]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[111]++;
            return name().localName();

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[112]++;
            throw ScriptRuntime.typeError("The localName method works only on lists containing one item");
        }
    }

    /**
     *
     * @return
     */
    QName name()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[236]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[113]++;
            return getXmlFromAnnotation(0).name();

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[114]++;
            throw ScriptRuntime.typeError("The name method works only on lists containing one item");
        }
    }

    /**
     *
     * @param prefix
     * @return
     */
    Object namespace(String prefix)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[237]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[115]++;
            return getXmlFromAnnotation(0).namespace(prefix);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[116]++;
            throw ScriptRuntime.typeError("The namespace method works only on lists containing one item");
        }
    }

    /**
     *
     * @return
     */
    Object[] namespaceDeclarations()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[238]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[117]++;
            return getXmlFromAnnotation(0).namespaceDeclarations();

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[118]++;
            throw ScriptRuntime.typeError("The namespaceDeclarations method works only on lists containing one item");
        }
    }

    /**
     *
     * @return
     */
    Object nodeKind()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[239]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[119]++;
            return getXmlFromAnnotation(0).nodeKind();

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[120]++;
            throw ScriptRuntime.typeError("The nodeKind method works only on lists containing one item");
        }
    }

    /**
     *
     */
    void normalize()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[240]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[79]++;


int CodeCoverConditionCoverageHelper_C87;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[79]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[80]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[81]++;
}
            getXmlFromAnnotation(i).normalize();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[241]++;
        }
    }

    /**
     * If list is empty, return undefined, if elements have different parents return undefined,
     * If they all have the same parent, return that parent.
     *
     * @return
     */
    Object parent()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[242]++;
        Object sameParent = Undefined.instance;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[243]++;
int CodeCoverConditionCoverageHelper_C88;

        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C88 |= (32)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((targetObject != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((targetObject instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 3) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 3) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[121]++;
            sameParent = targetObject;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[244]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[122]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[245]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[82]++;


int CodeCoverConditionCoverageHelper_C89;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[82]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[83]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[84]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[246]++;
                Object currParent = getXmlFromAnnotation(i).parent();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[247]++;
int CodeCoverConditionCoverageHelper_C90;

                if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[123]++;
                    // Set the first for the rest to compare to.
                    sameParent = currParent;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[248]++;

                }
                else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[124]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[249]++;
int CodeCoverConditionCoverageHelper_C91; if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((sameParent != currParent) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false))
                {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[125]++;
                    sameParent = Undefined.instance;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[250]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[251]++;
                    break;

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[126]++;}
}
            }
        }

        // If everything in the list is the sameParent then return that as the parent.
        return sameParent;
    }

    /**
     *
     * @param xml
     * @return
     */
    XML prependChild(Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[252]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[127]++;
            return getXmlFromAnnotation(0).prependChild(xml);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[128]++;
            throw ScriptRuntime.typeError("The prependChild method works only on lists containing one item");
        }
    }

    /**
     *
     * @return
     */
    Object processingInstructions(XMLName xmlName)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[253]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[254]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[85]++;


int CodeCoverConditionCoverageHelper_C93;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[85]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[86]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[87]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[255]++;
            XML xml = getXmlFromAnnotation(i);

            result.addToList(xml.processingInstructions(xmlName));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[256]++;
        }

        return result;
    }

    /**
     *
     * @param name
     * @return
     */
    boolean propertyIsEnumerable(Object name)
    {
        long index;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[257]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((name instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[129]++;
            index = ((Integer)name).intValue();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[258]++;

        } else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[130]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[259]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((name instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[131]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[260]++;
            double x = ((Number)name).doubleValue();
            index = (long)x;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[261]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[262]++;
int CodeCoverConditionCoverageHelper_C96;
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((index != x) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[133]++;
                return false;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[134]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[263]++;
int CodeCoverConditionCoverageHelper_C97;
            if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (8)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((1.0 / x < 0) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[135]++;
                // Negative 0
                return false;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[136]++;}

        } else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[132]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[264]++;
            String s = ScriptRuntime.toString(name);
            index = ScriptRuntime.testUint32String(s);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[265]++;
        }
}
        return (0 <= index && index < length());
    }

    /**
     *
     * @param ns
     */
    XML removeNamespace(Namespace ns)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[266]++;
int CodeCoverConditionCoverageHelper_C98;
        if((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[137]++;
            return getXmlFromAnnotation(0).removeNamespace(ns);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[138]++;
            throw ScriptRuntime.typeError("The removeNamespace method works only on lists containing one item");
        }
    }

    XML replace(long index, Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[267]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[139]++;
            return getXmlFromAnnotation(0).replace(index, xml);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[140]++;
            throw ScriptRuntime.typeError("The replace method works only on lists containing one item");
        }
    }

    /**
     *
     * @param propertyName
     * @param xml
     * @return
     */
    XML replace(XMLName xmlName, Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[268]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[141]++;
            return getXmlFromAnnotation(0).replace(xmlName, xml);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[142]++;
            throw ScriptRuntime.typeError("The replace method works only on lists containing one item");
        }
    }

    /**
     *
     * @param xml
     */
    XML setChildren(Object xml)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[269]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[143]++;
            return getXmlFromAnnotation(0).setChildren(xml);

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[144]++;
            throw ScriptRuntime.typeError("The setChildren method works only on lists containing one item");
        }
    }

    /**
     *
     * @param name
     */
    void setLocalName(String localName)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[270]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[145]++;
            getXmlFromAnnotation(0).setLocalName(localName);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[271]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[146]++;
            throw ScriptRuntime.typeError("The setLocalName method works only on lists containing one item");
        }
    }

    /**
     *
     * @param name
     */
    void setName(QName qname)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[272]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[147]++;
            getXmlFromAnnotation(0).setName(qname);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[273]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[148]++;
            throw ScriptRuntime.typeError("The setName method works only on lists containing one item");
        }
    }

    /**
     *
     * @param ns
     */
    void setNamespace(Namespace ns)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[274]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[149]++;
            getXmlFromAnnotation(0).setNamespace(ns);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[275]++;

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[150]++;
            throw ScriptRuntime.typeError("The setNamespace method works only on lists containing one item");
        }
    }

    /**
     *
     * * @return
     */
    XMLList text()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[276]++;
        XMLList result = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[277]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[88]++;


int CodeCoverConditionCoverageHelper_C105;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[88]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[89]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[90]++;
}
            result.addToList(getXmlFromAnnotation(i).text());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[278]++;
        }

        return result;
    }

    /**
     *
     * @return
     */
    public String toString()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[279]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((hasSimpleContent()) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[151]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[280]++;
            StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[281]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[91]++;


int CodeCoverConditionCoverageHelper_C107;

            for(int i = 0;(((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false); i++)
            {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[91]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[92]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[93]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[282]++;
                XML next = getXmlFromAnnotation(i);
                sb.append(next.toString());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[283]++;
            }

            return sb.toString();

        }
        else
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[152]++;
            return toXMLString(0);
        }
    }

    String toSource(int indent)
    {
        // XXX indent is ignored
        return "<>"+toXMLString(0)+"</>";
    }

    /**
     *
     * @return
     */
    String toXMLString(int indent)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[284]++;
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[285]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[94]++;


int CodeCoverConditionCoverageHelper_C108;

        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[94]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[95]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[96]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[286]++;
int CodeCoverConditionCoverageHelper_C109;
            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[153]++;
                sb.append('\n');
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[287]++;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[154]++;}

            sb.append(getXmlFromAnnotation(i).toXMLString(indent));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[288]++;
        }

        return sb.toString();
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
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[289]++;
        boolean result = false;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[290]++;
int CodeCoverConditionCoverageHelper_C110;

        // Zero length list should equate to undefined
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 ((target instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[155]++;
            result = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[291]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[156]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[292]++;
int CodeCoverConditionCoverageHelper_C111; if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[157]++;
            result = getXmlFromAnnotation(0).equivalentXml(target);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[293]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[158]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[294]++;
int CodeCoverConditionCoverageHelper_C112; if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((target instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[159]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[295]++;
            XMLList otherList = (XMLList) target;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[296]++;
int CodeCoverConditionCoverageHelper_C113;

            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((otherList.length() == length()) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[161]++;
                result = true;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[297]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[298]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[97]++;


int CodeCoverConditionCoverageHelper_C114;

                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false); i++)
                {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[97]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[98]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[99]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[299]++;
int CodeCoverConditionCoverageHelper_C115;
                    if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((getXmlFromAnnotation(i).equivalentXml(otherList.getXmlFromAnnotation(i))) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false))
                    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[163]++;
                        result = false;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[300]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[301]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[164]++;}
                }

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[162]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[160]++;}
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
    private XMLList getPropertyList(XMLName name)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[302]++;
        XMLList propertyList = new XMLList(lib);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[303]++;
        javax.xml.namespace.QName qname = null;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[304]++;
int CodeCoverConditionCoverageHelper_C116;

        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C116 |= (8)) == 0 || true) &&
 ((name.isDescendants()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((name.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) && false))
        {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[165]++;
            // Only set the targetProperty if this is a regular child get
            // and not a descendant or attribute get
            qname = new javax.xml.namespace.QName(name.uri(), name.localName());
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[305]++;

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[166]++;}

        propertyList.setTargets(this, qname);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[306]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[307]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[100]++;


int CodeCoverConditionCoverageHelper_C117;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((i < length()) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[100]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[101]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[102]++;
}
            propertyList.addToList(
                getXmlFromAnnotation(i).getPropertyList(name));
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[308]++;
        }

        return propertyList;
    }

    private Object applyOrCall(boolean isApply,
                               Context cx, Scriptable scope,
                               Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[309]++;
        String methodName = isApply ? "apply" : "call";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[310]++;
int CodeCoverConditionCoverageHelper_C118;
        if((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((thisObj instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((((XMLList)thisObj).targetProperty == null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[167]++;
            throw ScriptRuntime.typeError1("msg.isnt.function",
                                           methodName);
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[168]++;}

        return ScriptRuntime.applyOrCall(isApply, cx, scope, thisObj, args);
    }

    protected Object jsConstructor(Context cx, boolean inNewExpr,
                                   Object[] args)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[311]++;
int CodeCoverConditionCoverageHelper_C119;
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[169]++;
            return new XMLList(lib);

        } else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[170]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[312]++;
            Object arg0 = args[0];
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[313]++;
int CodeCoverConditionCoverageHelper_C120;
            if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C120 |= (8)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((arg0 instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 2) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[171]++;
                // XMLList(XMLList) returns the same object.
                return arg0;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[172]++;}
            return new XMLList(lib, arg0);
        }
    }

    org.apache.xmlbeans.XmlObject getXmlObject()
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[314]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[173]++;
            return getXmlFromAnnotation(0).getXmlObject();

        } else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[174]++;
            throw ScriptRuntime.typeError("getXmlObject method works only on lists containing one item");
        }
    }

    /**
     * See ECMA 357, 11_2_2_1, Semantics, 3_e.
     */
    public Scriptable getExtraMethodSource(Context cx)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[315]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[175]++;
            return getXmlFromAnnotation(0);

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[176]++;}
        return null;
    }

    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[316]++;
int CodeCoverConditionCoverageHelper_C123;
        // This XMLList is being called as a Function.
        // Let's find the real Function object.
        if((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((targetProperty == null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[177]++;
            throw ScriptRuntime.notFunctionError(this);
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[178]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[317]++;

        String methodName = targetProperty.getLocalPart();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[318]++;

        boolean isApply = methodName.equals("apply");
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[319]++;
int CodeCoverConditionCoverageHelper_C124;
        if((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (8)) == 0 || true) &&
 ((isApply) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((methodName.equals("call")) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[179]++;
            return applyOrCall(isApply, cx, scope, thisObj, args);
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[180]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[320]++;
int CodeCoverConditionCoverageHelper_C125;

        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((thisObj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[181]++;
            throw ScriptRuntime.typeError1("msg.incompat.call", methodName);

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[182]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[321]++;
        Object func = null;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[322]++;
        Scriptable sobj = thisObj;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[323]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[103]++;


int CodeCoverConditionCoverageHelper_C126;

        while ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((sobj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[103]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[104]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.loops[105]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[324]++;
            XMLObject xmlObject = (XMLObject) sobj;
            func = xmlObject.getFunctionProperty(cx, methodName);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[325]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[326]++;
int CodeCoverConditionCoverageHelper_C127;
            if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((func != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[183]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[327]++;
                break;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[184]++;}
            sobj = xmlObject.getExtraMethodSource(cx);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[328]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[329]++;
int CodeCoverConditionCoverageHelper_C128;
            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((sobj != null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[185]++;
                thisObj = sobj;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[330]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[331]++;
int CodeCoverConditionCoverageHelper_C129;
                if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((sobj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[187]++;
                    func = ScriptableObject.getProperty(sobj, methodName);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[332]++;

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[188]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[186]++;}
        }
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.statements[333]++;
int CodeCoverConditionCoverageHelper_C130;

        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((func instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[189]++;
            throw ScriptRuntime.notFunctionError(thisObj, func, methodName);

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t.branches[190]++;}
        return ((Callable)func).call(cx, scope, thisObj, args);
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
        throw ScriptRuntime.typeError1("msg.not.ctor", "XMLList");
    }
}

class CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t ());
  }
    public static long[] statements = new long[334];
    public static long[] branches = new long[191];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[131];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLList.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,2,1,2,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 130; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[106];

  public CodeCoverCoverageCounter$di175yxae4i0fxztrydxgm1e3p0souvk9t () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLList.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 333; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 190; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 130; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 105; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLList.java");
      for (int i = 1; i <= 333; i++) {
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
    for (int i = 1; i <= 130; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 35; i++) {
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



