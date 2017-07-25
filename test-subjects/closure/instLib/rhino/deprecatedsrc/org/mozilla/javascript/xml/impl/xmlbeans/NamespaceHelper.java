/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import java.util.*;
import org.apache.xmlbeans.XmlCursor;

import org.mozilla.javascript.*;

class NamespaceHelper
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.ping();
  }

    private XMLLibImpl lib;
    private final Map prefixToURI = new HashMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[1]++;
  }
    private final Map uriToPrefix = new HashMap();
  {
    CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[2]++;
  }
    // A set of URIs that are used without explicit namespace declaration in scope.
    private final Set undeclared = new HashSet();
  {
    CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[3]++;
  }

    private NamespaceHelper(XMLLibImpl lib)
    {
        this.lib = lib;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[4]++;
        // Insert the default namespace
        prefixToURI.put("", "");
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[5]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[6]++;
        Set prefixes = new HashSet();
        prefixes.add("");
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[7]++;
        uriToPrefix.put("", prefixes);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[8]++;
    }

    /**
     * Declared a new namespace
     *
     * @param prefix
     * @param uri
     * @param declarations
     */
    private void declareNamespace(String prefix, String uri, ObjArray declarations)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[9]++;
        Set prefixes = (Set)uriToPrefix.get(uri);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((prefixes == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[1]++;
            prefixes = new HashSet();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[11]++;
            uriToPrefix.put(uri, prefixes);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[12]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[2]++;}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;

        if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((prefixes.contains(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[3]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[14]++;
            String oldURI = (String)prefixToURI.get(prefix);

            // Add the new mapping
            prefixes.add(prefix);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[15]++;
            prefixToURI.put(prefix, uri);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[16]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
            if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((declarations != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[5]++;
                declarations.add(new Namespace(lib, prefix, uri));
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[18]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[6]++;}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;

            if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((oldURI != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[7]++;
                // Update the existing mapping
                prefixes = (Set)uriToPrefix.get(oldURI);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[20]++;
                prefixes.remove(prefix);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[21]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[4]++;}
    }

    /**
     * Updates the internal state of this NamespaceHelper to reflect the
     * existance of the XML token pointed to by the cursor.
     */
    private void processName(XmlCursor cursor, ObjArray declarations)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[22]++;
        javax.xml.namespace.QName qname = cursor.getName();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[23]++;
        String uri = qname.getNamespaceURI();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[24]++;
        Set prefixes = (Set)uriToPrefix.get(uri);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((prefixes == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((prefixes.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[9]++;
            undeclared.add(uri);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[26]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
            if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((declarations != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[11]++;
                declarations.add(new Namespace(lib, uri));
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[28]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[10]++;}
    }

    /**
     * Updates the internal state of this NamespaceHelper with the
     * namespace information of the element pointed to by the cursor.
     */
    private void update(XmlCursor cursor, ObjArray declarations)
    {
        // Process the Namespace declarations
        cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[29]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[30]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
        while((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((cursor.toNextToken().isAnyAttr()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false))
        {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[3]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
            if((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((cursor.isNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[13]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[32]++;
                javax.xml.namespace.QName name = cursor.getName();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[33]++;
                String prefix = name.getLocalPart();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[34]++;
                String uri = name.getNamespaceURI();

                declareNamespace(prefix, uri, declarations);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[35]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[14]++;}
        }
        cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[36]++;

        // Process the element
        processName(cursor, declarations);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[37]++;

        // Process the attributes
        cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[38]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[39]++;
        boolean hasNext = cursor.toFirstAttribute();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[40]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;
        while((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((hasNext) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false))
        {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[4]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[5]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[6]++;
}
            processName(cursor, declarations);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[41]++;
            hasNext = cursor.toNextAttribute();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[42]++;
        }
        cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[43]++;
    }

    /**
     * @return Object[] array of Namespace objects in scope at the cursor.
     */
    public static Object[] inScopeNamespaces(XMLLibImpl lib, XmlCursor cursor)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[44]++;
        ObjArray namespaces = new ObjArray();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[45]++;
        NamespaceHelper helper = new NamespaceHelper(lib);

        cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[46]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[47]++;

        int depth = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[48]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
        while((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cursor.hasPrevToken()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false))
        {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[7]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[8]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[9]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
            if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((cursor.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[15]++;
                cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[50]++;
                depth++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[51]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[16]++;}

            cursor.toParent();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[52]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[53]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[10]++;


int CodeCoverConditionCoverageHelper_C12;

        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < depth) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[10]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[11]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[12]++;
}
            cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[54]++;
            helper.update(cursor, null);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[55]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[56]++;

        Iterator i = helper.prefixToURI.entrySet().iterator();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[57]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[13]++;


int CodeCoverConditionCoverageHelper_C13;
        while((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false))
        {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[13]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[14]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[15]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[58]++;
            Map.Entry entry = (Map.Entry)i.next();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[59]++;
            Namespace ns = new Namespace(lib, (String)entry.getKey(),
                                            (String)entry.getValue());
            namespaces.add(ns);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[60]++;
        }

        i = helper.undeclared.iterator();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[61]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[62]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[16]++;


int CodeCoverConditionCoverageHelper_C14;
        while((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false))
        {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[16]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[17]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[18]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[63]++;
            Namespace ns = new Namespace(lib, (String)i.next());
            namespaces.add(ns);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[64]++;
        }

        cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[65]++;

        return namespaces.toArray();
    }

    static Namespace getNamespace(XMLLibImpl lib, XmlCursor cursor,
                                  Object[] inScopeNamespaces)
    {
        String uri;
        String prefix;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((cursor.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[17]++;
            uri = "";
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[67]++;
            prefix = "";
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[68]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[18]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[69]++;
            javax.xml.namespace.QName qname = cursor.getName();
            uri = qname.getNamespaceURI();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[70]++;
            prefix = qname.getPrefix();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[71]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[72]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((inScopeNamespaces == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[19]++;
            return new Namespace(lib, prefix, uri);
} else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[20]++;}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[73]++;

        Namespace result = null;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[74]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[19]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i != inScopeNamespaces.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[19]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[20]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[21]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[75]++;
            Namespace ns = (Namespace)inScopeNamespaces[i];
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[76]++;
int CodeCoverConditionCoverageHelper_C18;
            if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ns == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[21]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[77]++; continue;
} else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[22]++;}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[78]++;

            String nsURI = ns.uri();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[79]++;
int CodeCoverConditionCoverageHelper_C19;
            if((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((nsURI.equals(uri)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[23]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[80]++;
int CodeCoverConditionCoverageHelper_C20;
                if((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((prefix.equals(ns.prefix())) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[25]++;
                    result = ns;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[81]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[82]++;
                    break;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[26]++;}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[83]++;
int CodeCoverConditionCoverageHelper_C21;

                if((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((result.prefix() == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ns.prefix() != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[27]++;
                    result = ns;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[84]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[28]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[24]++;}
        }
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[85]++;
int CodeCoverConditionCoverageHelper_C22;

        if((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[29]++;
            result = new Namespace(lib, prefix, uri);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[86]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[30]++;}

        return result;
    }

    /**
     * @return List of Namespace objects that are declared in the container pointed to by the cursor.
     */
    public static Object[] namespaceDeclarations(XMLLibImpl lib, XmlCursor cursor)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[87]++;
        ObjArray declarations = new ObjArray();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[88]++;
        NamespaceHelper helper = new NamespaceHelper(lib);

        cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[89]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[90]++;

        int depth = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[91]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[22]++;


int CodeCoverConditionCoverageHelper_C23;
        while((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((cursor.hasPrevToken()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false))
        {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[22]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[23]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[24]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[92]++;
int CodeCoverConditionCoverageHelper_C24;
            if((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((cursor.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[31]++;
                cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[93]++;
                depth++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[94]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[32]++;}

            cursor.toParent();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[95]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[96]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[25]++;


int CodeCoverConditionCoverageHelper_C25;

        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i < depth - 1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[25]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[26]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[27]++;
}
            cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[97]++;
            helper.update(cursor, null);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[98]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[99]++;
int CodeCoverConditionCoverageHelper_C26;

        if((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((depth > 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[33]++;
            cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[100]++;
            helper.update(cursor, declarations);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[101]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[34]++;}

        cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[102]++;

        return declarations.toArray();
    }

    /**
     * @return Prefix to URI map of all namespaces in scope at the cursor.
     */
    public static Map getAllNamespaces(XMLLibImpl lib, XmlCursor cursor)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[103]++;
        NamespaceHelper helper = new NamespaceHelper(lib);

        cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[104]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[105]++;

        int depth = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[106]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[28]++;


int CodeCoverConditionCoverageHelper_C27;
        while((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((cursor.hasPrevToken()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false))
        {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[28]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[29]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[30]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[107]++;
int CodeCoverConditionCoverageHelper_C28;
            if((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((cursor.isContainer()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[35]++;
                cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[108]++;
                depth++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[109]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[36]++;}

            cursor.toParent();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[110]++;
        }
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[111]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[31]++;


int CodeCoverConditionCoverageHelper_C29;

        for(int i = 0;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i < depth) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); i++)
        {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[31]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[32]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[33]++;
}
            cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[112]++;
            helper.update(cursor, null);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[113]++;
        }

        cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[114]++;

        return helper.prefixToURI;
    }

    public static void getNamespaces(XmlCursor cursor, Map prefixToURI)
    {
        cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[115]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[116]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[34]++;


int CodeCoverConditionCoverageHelper_C30;
        while((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((cursor.toNextToken().isAnyAttr()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false))
        {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[34]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[35]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[36]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[117]++;
int CodeCoverConditionCoverageHelper_C31;
            if((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((cursor.isNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[37]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[118]++;
                javax.xml.namespace.QName name = cursor.getName();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[119]++;
                String prefix = name.getLocalPart();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[120]++;
                String uri = name.getNamespaceURI();

                prefixToURI.put(prefix, uri);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[121]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[38]++;}
        }
        cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[122]++;
    }

    public static void removeNamespace(XmlCursor cursor, String prefix)
    {
        cursor.push();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[123]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[124]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[37]++;


int CodeCoverConditionCoverageHelper_C32;
        while((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((cursor.toNextToken().isAnyAttr()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false))
        {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[37]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[38]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.loops[39]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[125]++;
int CodeCoverConditionCoverageHelper_C33;
            if((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cursor.isNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[39]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[126]++;
                javax.xml.namespace.QName name = cursor.getName();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[127]++;
int CodeCoverConditionCoverageHelper_C34;
                if((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((name.getLocalPart().equals(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[41]++;
                    cursor.removeXml();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[128]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[129]++;
                    break;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[42]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.branches[40]++;}
        }
        cursor.pop();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5.statements[130]++;
    }
}

class CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5 ());
  }
    public static long[] statements = new long[131];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[35];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-NamespaceHelper.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 34; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$1gk5ffks5uqdq4dcqazrysjc3eb926cr1vvp2649u31axr5 () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-NamespaceHelper.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 130; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-NamespaceHelper.java");
      for (int i = 1; i <= 130; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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
