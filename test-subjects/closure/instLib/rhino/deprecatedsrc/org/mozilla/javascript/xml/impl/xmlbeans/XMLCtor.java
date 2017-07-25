/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import org.mozilla.javascript.*;

class XMLCtor extends IdFunctionObject
{
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.ping();
  }

    static final long serialVersionUID = -8708195078359817341L;
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[1]++;
  }

    private static final Object XMLCTOR_TAG = "XMLCtor";
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[2]++;
  }

    private XMLLibImpl lib;

    XMLCtor(XML xml, Object tag, int id, int arity)
    {
        super(xml, tag, id, arity);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[3]++;
        this.lib = xml.lib;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[4]++;
        activatePrototypeMap(MAX_FUNCTION_ID);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[5]++;
    }

    private void writeSetting(Scriptable target)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i <= MAX_INSTANCE_ID) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[1]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[2]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[3]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[7]++;
            int id = super.getMaxInstanceId() + i;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[8]++;
            String name = getInstanceIdName(id);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[9]++;
            Object value = getInstanceIdValue(id);
            ScriptableObject.putProperty(target, name, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[10]++;
        }
    }

    private void readSettings(Scriptable source)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[11]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i <= MAX_INSTANCE_ID) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[4]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[5]--;
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.loops[6]++;
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[12]++;
            int id = super.getMaxInstanceId() + i;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[13]++;
            String name = getInstanceIdName(id);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[14]++;
            Object value = ScriptableObject.getProperty(source, name);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[1]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[16]++;
                continue;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[2]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[17]++;
            switch (i) {
              case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[3]++;
              case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[4]++;
              case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[5]++;
              case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[6]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[7]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[19]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[8]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[20]++;
                break;
              case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[9]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[10]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[22]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[11]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[23]++;
                break;
              default:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[12]++;
                throw new IllegalStateException();
            }
            setInstanceIdValue(id, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[24]++;
        }
    }

// #string_id_map#

    private static final int
        Id_ignoreComments               = 1,
        Id_ignoreProcessingInstructions = 2,
        Id_ignoreWhitespace             = 3,
        Id_prettyIndent                 = 4,
        Id_prettyPrinting               = 5,

        MAX_INSTANCE_ID                 = 5;
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[25]++;
  }

    protected int getMaxInstanceId()
    {
        return super.getMaxInstanceId() + MAX_INSTANCE_ID;
    }

    protected int findInstanceIdInfo(String s) {
        int id;
// #generated# Last update: 2004-07-19 13:03:52 CEST
        L0: { id = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[26]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[27]++; String X = null; int c;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[28]++;
            L: switch (s.length()) {
            case 12:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[13]++; X="prettyIndent";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[29]++;id=Id_prettyIndent;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[30]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[31]++; break L;
            case 14:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[14]++; c=s.charAt(0);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[32]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[15]++; X="ignoreComments";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[34]++;id=Id_ignoreComments;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[35]++;
 }
                else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[16]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[36]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[17]++; X="prettyPrinting";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[37]++;id=Id_prettyPrinting;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[38]++;
 } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[18]++;}
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[39]++;
                break L;
            case 16:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[19]++; X="ignoreWhitespace";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[40]++;id=Id_ignoreWhitespace;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[41]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[42]++; break L;
            case 28:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[20]++; X="ignoreProcessingInstructions";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[43]++;id=Id_ignoreProcessingInstructions;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[44]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[45]++; break L; default : CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[21]++;
            }
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[22]++; id = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[47]++;
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[23]++;}
        }
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[24]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[25]++;}

        int attr;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[49]++;
        switch (id) {
          case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[26]++;
          case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[27]++;
          case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[28]++;
          case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[29]++;
          case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[30]++;
            attr = PERMANENT | DONTENUM;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[50]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[51]++;
            break;
          default:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[31]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }

// #/string_id_map#

    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[52]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[32]++;               return "ignoreComments";
          case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[33]++; return "ignoreProcessingInstructions";
          case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[34]++;             return "ignoreWhitespace";
          case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[35]++;                 return "prettyIndent";
          case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[36]++;               return "prettyPrinting"; default : CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[37]++;
        }
        return super.getInstanceIdName(id);
    }

    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[53]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[38]++;
            return ScriptRuntime.wrapBoolean(lib.ignoreComments);
          case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[39]++;
            return ScriptRuntime.wrapBoolean(lib.ignoreProcessingInstructions);
          case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[40]++;
            return ScriptRuntime.wrapBoolean(lib.ignoreWhitespace);
          case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[41]++;
            return ScriptRuntime.wrapInt(lib.prettyIndent);
          case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[42]++;
            return ScriptRuntime.wrapBoolean(lib.prettyPrinting); default : CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[43]++;
        }
        return super.getInstanceIdValue(id);
    }

    protected void setInstanceIdValue(int id, Object value)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[54]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[44]++;
            lib.ignoreComments = ScriptRuntime.toBoolean(value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[55]++;
            return;
          case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[45]++;
            lib.ignoreProcessingInstructions = ScriptRuntime.toBoolean(value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[56]++;
            return;
          case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[46]++;
            lib.ignoreWhitespace = ScriptRuntime.toBoolean(value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[57]++;
            return;
          case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[47]++;
            lib.prettyIndent = ScriptRuntime.toInt32(value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[58]++;
            return;
          case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[48]++;
            lib.prettyPrinting = ScriptRuntime.toBoolean(value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[59]++;
            return; default : CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[49]++;
        }
        super.setInstanceIdValue(id, value);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[60]++;
    }

// #string_id_map#
    private static final int
        Id_defaultSettings              = 1,
        Id_settings                     = 2,
        Id_setSettings                  = 3,
        MAX_FUNCTION_ID                 = 3;
  static {
    CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[61]++;
  }

    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2004-07-19 13:03:52 CEST
        L0: { id = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[62]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[63]++; String X = null;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[64]++;
            int s_length = s.length();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[65]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[50]++; X="settings";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[66]++;id=Id_settings;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[67]++;
 }
            else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[51]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[68]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[52]++; X="setSettings";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[69]++;id=Id_setSettings;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[70]++;
 }
            else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[53]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[71]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s_length==15) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[54]++; X="defaultSettings";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[72]++;id=Id_defaultSettings;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[73]++;
 } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[55]++;}
}
}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[74]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[56]++; id = 0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[75]++;
} else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[57]++;}
        }
// #/generated#
        return id;
    }
// #/string_id_map#

    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[76]++;
        switch (id) {
          case Id_defaultSettings:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[58]++;  arity=0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[77]++; s="defaultSettings";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[78]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[79]++;  break;
          case Id_settings:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[59]++;         arity=0;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[80]++; s="settings";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[81]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[82]++;         break;
          case Id_setSettings:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[60]++;      arity=1;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[83]++; s="setSettings";
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[84]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[85]++;      break;
          default:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[61]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(XMLCTOR_TAG, id, s, arity);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[86]++;
    }

    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[87]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((f.hasTag(XMLCTOR_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[62]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[63]++;}
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[88]++;
        int id = f.methodId();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[89]++;
        switch (id) {
          case Id_defaultSettings:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[64]++; {
            lib.defaultSettings();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[90]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[91]++;
            Scriptable obj = cx.newObject(scope);
            writeSetting(obj);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[92]++;
            return obj;
          }
          case Id_settings:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[65]++; {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[93]++;
            Scriptable obj = cx.newObject(scope);
            writeSetting(obj);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[94]++;
            return obj;
          }
          case Id_setSettings:
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[66]++; {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[95]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((args[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((args[0] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false))
            {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[67]++;
                lib.defaultSettings();
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[96]++;

            } else {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[68]++;
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[97]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((args[0] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[69]++;
                readSettings((Scriptable)args[0]);
CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.statements[98]++;

            } else {
  CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[70]++;}
}
            return Undefined.instance;
          } default : CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1.branches[71]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }
}

class CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1 ());
  }
    public static long[] statements = new long[99];
    public static long[] branches = new long[72];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLCtor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,3,1,1,1,1,3,1,3,1};
    for (int i = 1; i <= 16; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$di175yxae4i0fxztrydxfn622x0mho1js1 () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLCtor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 98; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 71; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLCtor.java");
      for (int i = 1; i <= 98; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 71; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

