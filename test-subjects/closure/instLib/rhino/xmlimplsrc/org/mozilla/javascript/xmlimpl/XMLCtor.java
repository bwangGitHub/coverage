/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.*;

class XMLCtor extends IdFunctionObject
{
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.ping();
  }

    static final long serialVersionUID = -8708195078359817341L;
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[1]++;
  }

    private static final Object XMLCTOR_TAG = "XMLCtor";
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[2]++;
  }

    private XmlProcessor options;
//    private XMLLibImpl lib;

    XMLCtor(XML xml, Object tag, int id, int arity)
    {
        super(xml, tag, id, arity);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[3]++;
//        this.lib = xml.lib;
        this.options = xml.getProcessor();
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[4]++;
        activatePrototypeMap(MAX_FUNCTION_ID);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[5]++;
    }

    private void writeSetting(Scriptable target)
    {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i <= MAX_INSTANCE_ID) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[3]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[7]++;
            int id = super.getMaxInstanceId() + i;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[8]++;
            String name = getInstanceIdName(id);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[9]++;
            Object value = getInstanceIdValue(id);
            ScriptableObject.putProperty(target, name, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[10]++;
        }
    }

    private void readSettings(Scriptable source)
    {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[11]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i <= MAX_INSTANCE_ID) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.loops[6]++;
}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[12]++;
            int id = super.getMaxInstanceId() + i;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[13]++;
            String name = getInstanceIdName(id);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[14]++;
            Object value = ScriptableObject.getProperty(source, name);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[1]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[16]++;
                continue;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[2]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[17]++;
            switch (i) {
              case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[3]++;
              case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[4]++;
              case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[5]++;
              case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[6]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[7]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[19]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[8]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[20]++;
                break;
              case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[9]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[10]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[22]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[11]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[23]++;
                break;
              default:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[12]++;
                throw new IllegalStateException();
            }
            setInstanceIdValue(id, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[24]++;
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
    CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[25]++;
  }

    @Override
    protected int getMaxInstanceId()
    {
        return super.getMaxInstanceId() + MAX_INSTANCE_ID;
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        int id;
// #generated# Last update: 2007-08-20 09:01:10 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[26]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[27]++; String X = null; int c;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[28]++;
            L: switch (s.length()) {
            case 12:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[13]++; X="prettyIndent";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[29]++;id=Id_prettyIndent;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[30]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[31]++; break L;
            case 14:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[14]++; c=s.charAt(0);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[32]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[15]++; X="ignoreComments";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[34]++;id=Id_ignoreComments;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[35]++;
 }
                else {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[16]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[36]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[17]++; X="prettyPrinting";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[37]++;id=Id_prettyPrinting;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[38]++;
 } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[18]++;}
}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[39]++;
                break L;
            case 16:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[19]++; X="ignoreWhitespace";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[40]++;id=Id_ignoreWhitespace;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[41]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[42]++; break L;
            case 28:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[20]++; X="ignoreProcessingInstructions";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[43]++;id=Id_ignoreProcessingInstructions;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[44]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[45]++; break L; default : CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[21]++;
            }
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[46]++;
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
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[22]++; id = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[47]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[23]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[48]++;
            break L0;
        }
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[24]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[25]++;}

        int attr;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[50]++;
        switch (id) {
          case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[26]++;
          case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[27]++;
          case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[28]++;
          case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[29]++;
          case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[30]++;
            attr = PERMANENT | DONTENUM;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[51]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[52]++;
            break;
          default:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[31]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }

// #/string_id_map#

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[53]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[32]++;               return "ignoreComments";
          case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[33]++; return "ignoreProcessingInstructions";
          case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[34]++;             return "ignoreWhitespace";
          case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[35]++;                 return "prettyIndent";
          case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[36]++;               return "prettyPrinting"; default : CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[37]++;
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[54]++;
        switch (id - super.getMaxInstanceId()) {
          case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[38]++;
            return ScriptRuntime.wrapBoolean(options.isIgnoreComments());
          case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[39]++;
            return ScriptRuntime.wrapBoolean(options.isIgnoreProcessingInstructions());
          case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[40]++;
            return ScriptRuntime.wrapBoolean(options.isIgnoreWhitespace());
          case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[41]++;
            return ScriptRuntime.wrapInt(options.getPrettyIndent());
          case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[42]++;
            return ScriptRuntime.wrapBoolean(options.isPrettyPrinting()); default : CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[43]++;
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[55]++;
        switch (id - super.getMaxInstanceId()) {
            case Id_ignoreComments:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[44]++;
                options.setIgnoreComments(ScriptRuntime.toBoolean(value));
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[56]++;
                return;
            case Id_ignoreProcessingInstructions:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[45]++;
                options.setIgnoreProcessingInstructions(ScriptRuntime.toBoolean(value));
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[57]++;
                return;
            case Id_ignoreWhitespace:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[46]++;
                options.setIgnoreWhitespace(ScriptRuntime.toBoolean(value));
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[58]++;
                return;
            case Id_prettyIndent:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[47]++;
                options.setPrettyIndent(ScriptRuntime.toInt32(value));
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[59]++;
                return;
            case Id_prettyPrinting:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[48]++;
                options.setPrettyPrinting(ScriptRuntime.toBoolean(value));
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[60]++;
                return; default : CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[49]++;
        }
        super.setInstanceIdValue(id, value);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[61]++;
    }

// #string_id_map#
    private static final int
        Id_defaultSettings              = 1,
        Id_settings                     = 2,
        Id_setSettings                  = 3,
        MAX_FUNCTION_ID                 = 3;
  static {
    CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[62]++;
  }

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-08-20 09:01:10 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[63]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[64]++; String X = null;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[65]++;
            int s_length = s.length();
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[66]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[50]++; X="settings";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[67]++;id=Id_settings;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[68]++;
 }
            else {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[51]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[69]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[52]++; X="setSettings";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[70]++;id=Id_setSettings;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[71]++;
 }
            else {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[53]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[72]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s_length==15) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[54]++; X="defaultSettings";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[73]++;id=Id_defaultSettings;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[74]++;
 } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[55]++;}
}
}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[75]++;
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
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[56]++; id = 0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[76]++;
} else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[57]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[77]++;
            break L0;
        }
// #/generated#
        return id;
    }
// #/string_id_map#

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[78]++;
        switch (id) {
          case Id_defaultSettings:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[58]++;  arity=0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[79]++; s="defaultSettings";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[80]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[81]++;  break;
          case Id_settings:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[59]++;         arity=0;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[82]++; s="settings";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[83]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[84]++;         break;
          case Id_setSettings:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[60]++;      arity=1;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[85]++; s="setSettings";
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[86]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[87]++;      break;
          default:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[61]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(XMLCTOR_TAG, id, s, arity);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[88]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[89]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((f.hasTag(XMLCTOR_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[62]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[63]++;}
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[90]++;
        int id = f.methodId();
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[91]++;
        switch (id) {
          case Id_defaultSettings:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[64]++; {
            options.setDefault();
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[92]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[93]++;
            Scriptable obj = cx.newObject(scope);
            writeSetting(obj);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[94]++;
            return obj;
          }
          case Id_settings:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[65]++; {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[95]++;
            Scriptable obj = cx.newObject(scope);
            writeSetting(obj);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[96]++;
            return obj;
          }
          case Id_setSettings:
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[66]++; {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[97]++;
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
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false))
            {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[67]++;
                options.setDefault();
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[98]++;

            } else {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[68]++;
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[99]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((args[0] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[69]++;
                readSettings((Scriptable)args[0]);
CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.statements[100]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[70]++;}
}
            return Undefined.instance;
          } default : CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up.branches[71]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    /**
        hasInstance for XML objects works differently than other objects; see ECMA357 13.4.3.10.
     */
    @Override
    public boolean hasInstance(Scriptable instance) {
        return (instance instanceof XML || instance instanceof XMLList);
    }
}

class CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up ());
  }
    public static long[] statements = new long[101];
    public static long[] branches = new long[72];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XMLCtor.java";
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

  public CodeCoverCoverageCounter$di175yxae5opd1zjygssz7mb1kqtuqc1up () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLCtor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 100; i++) {
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
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLCtor.java");
      for (int i = 1; i <= 100; i++) {
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

