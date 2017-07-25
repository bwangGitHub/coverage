/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.*;

class XMLName extends Ref {
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.ping();
  }

    static final long serialVersionUID = 3832176310755686977L;
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[1]++;
  }

    private static boolean isNCNameStartChar(int c) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (((c & ~0x7F) == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[1]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
            // Optimize for ASCII and use A..Z < _ < a..z
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((c >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[3]++;
                return c <= 'z';

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[4]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[4]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[5]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[5]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[7]++;
                    return true;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[8]++;}
                return c == '_';

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[6]++;}
}

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[2]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[6]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 (((c & ~0x1FFF) == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[9]++;
            return (0xC0 <= c && c <= 0xD6)
            || (0xD8 <= c && c <= 0xF6)
            || (0xF8 <= c && c <= 0x2FF)
            || (0x370 <= c && c <= 0x37D)
            || 0x37F <= c;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[10]++;}
}
        return (0x200C <= c && c <= 0x200D)
        || (0x2070 <= c && c <= 0x218F)
        || (0x2C00 <= c && c <= 0x2FEF)
        || (0x3001 <= c && c <= 0xD7FF)
        || (0xF900 <= c && c <= 0xFDCF)
        || (0xFDF0 <= c && c <= 0xFFFD)
        || (0x10000 <= c && c <= 0xEFFFF);
    }

    private static boolean isNCNameChar(int c) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[7]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (((c & ~0x7F) == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[11]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[8]++;
int CodeCoverConditionCoverageHelper_C7;
            // Optimize for ASCII and use - < . < 0..9 < A..Z < _ < a..z
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[13]++;
                return c <= 'z';

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[14]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[9]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((c >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[15]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[10]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[17]++;
                    return true;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[18]++;}
                return c == '_';

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[16]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[11]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[19]++;
                return c <= '9';

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[20]++;
                return c == '-' || c == '.';
            }
}
}

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[12]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[12]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 (((c & ~0x1FFF) == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[21]++;
            return isNCNameStartChar(c) || c == 0xB7
                || (0x300 <= c && c <= 0x36F);

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[22]++;}
}
        return isNCNameStartChar(c) || (0x203F <= c && c <= 0x2040);
    }

    //    This means "accept" in the parsing sense
    //    See ECMA357 13.1.2.1
    static boolean accept(Object nameObj) {
        String name;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            name = ScriptRuntime.toString(nameObj);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[14]++;
        } catch (EcmaError ee) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[24]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[15]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 (("TypeError".equals(ee.getName())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[25]++;
                return false;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[26]++;}
            throw ee;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[23]++;
}
  }
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[16]++;

        // See http://w3.org/TR/xml-names11/#NT-NCName
        int length = name.length();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[17]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((length != 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[27]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[18]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((isNCNameStartChar(name.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[29]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[1]++;


int CodeCoverConditionCoverageHelper_C15;
                for (int i = 1;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i != length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[3]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[20]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isNCNameChar(name.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[31]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[32]++;}
                }
                return true;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[30]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[28]++;}

        return false;
    }

    private XmlNode.QName qname;
    private boolean isAttributeName;
    private boolean isDescendants;
    private XMLObjectImpl xmlObject;

    private XMLName() {
    }

    static XMLName formStar() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[21]++;
        XMLName rv = new XMLName();
        rv.qname = XmlNode.QName.create(null, null);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[22]++;
        return rv;
    }

    /** @deprecated */
    static XMLName formProperty(XmlNode.Namespace namespace, String localName) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[23]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((localName != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((localName.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[33]++; localName = null;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[24]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[34]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[25]++;
        XMLName rv = new XMLName();
        rv.qname = XmlNode.QName.create(namespace, localName);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[26]++;
        return rv;
    }

    /** TODO: marked deprecated by original author */
    static XMLName formProperty(String uri, String localName) {
        return formProperty(XmlNode.Namespace.create(uri), localName);
    }

    /** TODO: marked deprecated by original implementor */
    static XMLName create(String defaultNamespaceUri, String name) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[27]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[35]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[36]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[28]++;

        int l = name.length();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[29]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((l != 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[37]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[30]++;
            char firstChar = name.charAt(0);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[31]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((firstChar == '*') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[39]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[32]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((l == 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[41]++;
                    return XMLName.formStar();

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[42]++;}

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[40]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[33]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((firstChar == '@') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[43]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[34]++;
                XMLName xmlName = XMLName.formProperty("", name.substring(1));
                xmlName.setAttributeName();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[35]++;
                return xmlName;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[44]++;}
}

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[38]++;}

        return XMLName.formProperty(defaultNamespaceUri, name);
    }

    static XMLName create(XmlNode.QName qname, boolean attribute, boolean descendants) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[36]++;
        XMLName rv = new XMLName();
        rv.qname = qname;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[37]++;
        rv.isAttributeName = attribute;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[38]++;
        rv.isDescendants = descendants;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[39]++;
        return rv;
    }

    /** @deprecated */
    static XMLName create(XmlNode.QName qname) {
        return create(qname, false, false);
    }

    void initXMLObject(XMLObjectImpl xmlObject) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[40]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[45]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[46]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[41]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.xmlObject != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[47]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[48]++;}
        this.xmlObject = xmlObject;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[42]++;
    }

    String uri() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[43]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((qname.getNamespace() == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[49]++; return null;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[50]++;}
        return qname.getNamespace().getUri();
    }

    String localName() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[44]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((qname.getLocalName() == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[51]++; return "*";
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[52]++;}
        return qname.getLocalName();
    }

    private void addDescendantChildren(XMLList list, XML target) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[45]++;
        XMLName xmlName = this;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[46]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((target.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[53]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[47]++;
            XML[] children = target.getChildren();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[48]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[4]++;


int CodeCoverConditionCoverageHelper_C28;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i<children.length) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[6]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[49]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((xmlName.matches( children[i] )) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[55]++;
                    list.addToList( children[i] );
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[50]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[56]++;}
                addDescendantChildren(list, children[i]);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[51]++;
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[54]++;}
    }

    void addMatchingAttributes(XMLList list, XML target) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[52]++;
        XMLName name = this;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[53]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((target.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[57]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[54]++;
            XML[] attributes = target.getAttributes();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[55]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[7]++;


int CodeCoverConditionCoverageHelper_C31;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((i<attributes.length) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[9]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[56]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((name.matches( attributes[i])) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false) ) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[59]++;
                    list.addToList( attributes[i] );
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[57]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[60]++;}
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[58]++;}
    }

    private void addDescendantAttributes(XMLList list, XML target) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[58]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((target.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[61]++;
            addMatchingAttributes(list, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[59]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[60]++;
            XML[] children = target.getChildren();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[61]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[10]++;


int CodeCoverConditionCoverageHelper_C34;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i<children.length) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[10]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[11]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[12]++;
}
                addDescendantAttributes(list, children[i]);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[62]++;
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[62]++;}
    }

    XMLList matchDescendantAttributes(XMLList rv, XML target) {
        rv.setTargets(target, null);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[63]++;
        addDescendantAttributes(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[64]++;
        return rv;
    }

    XMLList matchDescendantChildren(XMLList rv, XML target) {
        rv.setTargets(target, null);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[65]++;
        addDescendantChildren(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[66]++;
        return rv;
    }

    void addDescendants(XMLList rv, XML target) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[67]++;
        XMLName xmlName = this;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[68]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[63]++;
            matchDescendantAttributes(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[69]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[64]++;
            matchDescendantChildren(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[70]++;
        }
    }

    private void addAttributes(XMLList rv, XML target) {
        addMatchingAttributes(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[71]++;
    }

    void addMatches(XMLList rv, XML target) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[72]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isDescendants()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[65]++;
            addDescendants(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[73]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[66]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[74]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[67]++;
            addAttributes(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[75]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[68]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[76]++;
            XML[] children = target.getChildren();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[77]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((children != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[69]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[78]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[13]++;


int CodeCoverConditionCoverageHelper_C39;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((i<children.length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[13]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[14]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[15]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[79]++;
int CodeCoverConditionCoverageHelper_C40;
                    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.matches(children[i])) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[71]++;
                        rv.addToList( children[i] );
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[80]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[72]++;}
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[70]++;}
            rv.setTargets(target, this.toQname());
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[81]++;
        }
}
    }

    XMLList getMyValueOn(XML target) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[82]++;
        XMLList rv = target.newXMLList();
        addMatches(rv, target);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[83]++;
        return rv;
    }

    void setMyValueOn(XML target, Object value) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[84]++;
int CodeCoverConditionCoverageHelper_C41;
        // Special-case checks for undefined and null
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[73]++;
            value = "null";
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[85]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[74]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[86]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((value instanceof Undefined) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[75]++;
            value = "undefined";
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[87]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[76]++;}
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[88]++;

        XMLName xmlName = this;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[89]++;
int CodeCoverConditionCoverageHelper_C43;
        // Get the named property
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[77]++;
            target.setAttribute(xmlName, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[90]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[78]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[91]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((xmlName.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((xmlName.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[79]++;
            target.setChildren(value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[92]++;

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[80]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[93]++;
            // Convert text into XML if needed.
            XMLObjectImpl xmlValue = null;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[94]++;
int CodeCoverConditionCoverageHelper_C45;

            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((value instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[81]++;
                xmlValue = (XMLObjectImpl)value;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[95]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[96]++;
int CodeCoverConditionCoverageHelper_C46;

                // Check for attribute type and convert to textNode
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[83]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[97]++;
int CodeCoverConditionCoverageHelper_C47;
                    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((((XML)xmlValue).isAttribute()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[85]++;
                        xmlValue = target.makeXmlFromString(xmlName,
                                xmlValue.toString());
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[98]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[86]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[84]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[99]++;
int CodeCoverConditionCoverageHelper_C48;

                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((xmlValue instanceof XMLList) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[87]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[100]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[16]++;


int CodeCoverConditionCoverageHelper_C49;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((i < xmlValue.length()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[16]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[17]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[18]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[101]++;
                        XML xml = ((XMLList) xmlValue).item(i);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[102]++;
int CodeCoverConditionCoverageHelper_C50;

                        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((xml.isAttribute()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[89]++;
                            ((XMLList)xmlValue).replace(i, target.makeXmlFromString(xmlName, xml.toString()));
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[103]++;

                        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[90]++;}
                    }

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[88]++;}

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[82]++;
                xmlValue = target.makeXmlFromString(xmlName, ScriptRuntime.toString(value));
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[104]++;
            }
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[105]++;

            XMLList matches = target.getPropertyList(xmlName);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[106]++;
int CodeCoverConditionCoverageHelper_C51;

            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((matches.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[91]++;
                target.appendChild(xmlValue);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[107]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[92]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[108]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[19]++;


int CodeCoverConditionCoverageHelper_C52;
                // Remove all other matches
                for (int i = 1;(((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((i < matches.length()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[19]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[20]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.loops[21]++;
}
                    target.removeChild(matches.item(i).childIndex());
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[109]++;
                }
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[110]++;

                // Replace first match with new value.
                XML firstMatch = matches.item(0);
                target.replace(firstMatch.childIndex(), xmlValue);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[111]++;
            }
        }
}
    }

    @Override
    public boolean has(Context cx) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[112]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[93]++;
            return false;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[94]++;}
        return xmlObject.hasXMLProperty(this);
    }

    @Override
    public Object get(Context cx) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[113]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[95]++;
            throw ScriptRuntime.undefReadError(Undefined.instance,
                toString());

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[96]++;}
        return xmlObject.getXMLProperty(this);
    }

    @Override
    public Object set(Context cx, Object value) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[114]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[97]++;
            throw ScriptRuntime.undefWriteError(Undefined.instance,
                toString(),
                value);

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[98]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[115]++;
int CodeCoverConditionCoverageHelper_C56;
        // Assignment to descendants causes parse error on bad reference
        // and this should not be called
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((isDescendants) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[99]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[100]++;}
        xmlObject.putXMLProperty(this, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[116]++;
        return value;
    }

    @Override
    public boolean delete(Context cx) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[117]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((xmlObject == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[101]++;
            return true;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[102]++;}
        xmlObject.deleteXMLProperty(this);
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[118]++;
        return !xmlObject.hasXMLProperty(this);
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[119]++;
        //return qname.localName();
        StringBuffer buff = new StringBuffer();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[120]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((isDescendants) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[103]++; buff.append("..");
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[121]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[104]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[122]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((isAttributeName) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[105]++; buff.append('@');
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[123]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[106]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[124]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[107]++;
            buff.append('*');
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[125]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[126]++;
int CodeCoverConditionCoverageHelper_C61;
            if((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[109]++;
                return buff.toString();

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[110]++;}

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[108]++;
            buff.append('"').append(uri()).append('"');
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[127]++;
        }
        buff.append(':').append(localName());
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[128]++;
        return buff.toString();
    }

    final XmlNode.QName toQname() {
        return this.qname;
    }

    final boolean matchesLocalName(String localName) {
        return localName().equals("*") || localName().equals(localName);
    }

    final boolean matchesElement(XmlNode.QName qname) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[129]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((this.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((this.uri().equals(qname.getNamespace().getUri())) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[111]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[130]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((this.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.localName().equals(qname.getLocalName())) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[113]++;
                return true;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[114]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[112]++;}
        return false;
    }

    final boolean matches(XML node) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[131]++;
        XmlNode.QName qname = node.getNodeQname();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[132]++;
        String nodeUri = null;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[133]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((qname.getNamespace() != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[115]++;
            nodeUri = qname.getNamespace().getUri();
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[134]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[116]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[135]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((isAttributeName) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[117]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[136]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((node.isAttribute()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[119]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[137]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((this.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.uri().equals(nodeUri)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[121]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[138]++;
int CodeCoverConditionCoverageHelper_C68;
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((this.localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.localName().equals(qname.getLocalName())) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[123]++;
                        return true;

                    } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[124]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[122]++;}
                return false;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[120]++;
                //    TODO    Could throw exception maybe, should not call this method on attribute name with arbitrary node type
                //            unless we traverse all attributes and children habitually
                return false;
            }

        } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[118]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[139]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((this.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 || ((
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((node.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.uri().equals(nodeUri)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) && false) ) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[125]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[140]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((localName().equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[127]++; return true;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[128]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[141]++;
int CodeCoverConditionCoverageHelper_C71;
                if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((node.isElement()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[129]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[142]++;
int CodeCoverConditionCoverageHelper_C72;
                    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((localName().equals(qname.getLocalName())) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[131]++; return true;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[132]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[130]++;}

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.branches[126]++;}
            return false;
        }
    }

    /* TODO: marked deprecated by original author */
    boolean isAttributeName() {
        return isAttributeName;
    }

    // TODO Fix whether this is an attribute XMLName at construction?
    // Marked deprecated by original author
    void setAttributeName() {
//        if (isAttributeName) throw new IllegalStateException();
        isAttributeName = true;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[143]++;
    }

    /* TODO: was marked deprecated by original author */
    boolean isDescendants() {
        return isDescendants;
    }

    //    TODO    Fix whether this is an descendant XMLName at construction?
    /** @deprecated */
    void setIsDescendants() {
//        if (isDescendants) throw new IllegalStateException();
        isDescendants = true;
CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5.statements[144]++;
    }
}

class CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5 ());
  }
    public static long[] statements = new long[145];
    public static long[] branches = new long[133];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[73];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XMLName.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,2,2,3,1,1,1};
    for (int i = 1; i <= 72; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$di175yxae5opd1zjygst0e5jpk175mc9r5 () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLName.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 144; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 132; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLName.java");
      for (int i = 1; i <= 144; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 132; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

