/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.regexp;

import org.mozilla.javascript.*;

/**
 * This class implements the RegExp constructor native object.
 *
 * Revision History:
 * Implementation in C by Brendan Eich
 * Initial port to Java by Norris Boyd from jsregexp.c version 1.36
 * Merged up to version 1.38, which included Unicode support.
 * Merged bug fixes in version 1.39.
 * Merged JSFUN13_BRANCH changes up to 1.32.2.11
 *
 */
class NativeRegExpCtor extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.ping();
  }

    static final long serialVersionUID = -5733330028285400526L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[1]++;
  }

    NativeRegExpCtor()
    {
    }

    @Override
    public String getFunctionName()
    {
        return "RegExp";
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((args[0] instanceof NativeRegExp) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((args[1] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false))
        {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[1]++;
            return args[0];

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[2]++;}
        return construct(cx, scope, args);
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[3]++;
        NativeRegExp re = new NativeRegExp();
        re.compile(cx, scope, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[4]++;
        ScriptRuntime.setBuiltinProtoAndParent(re, scope, TopLevel.Builtins.RegExp);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[5]++;
        return re;
    }

    private static RegExpImpl getImpl()
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[6]++;
        Context cx = Context.getCurrentContext();
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(cx);
    }

// #string_id_map#

    private static final int
        Id_multiline     = 1,
        Id_STAR          = 2,  // #string=$*#

        Id_input         = 3,
        Id_UNDERSCORE    = 4,  // #string=$_#

        Id_lastMatch     = 5,
        Id_AMPERSAND     = 6,  // #string=$&#

        Id_lastParen     = 7,
        Id_PLUS          = 8,  // #string=$+#

        Id_leftContext   = 9,
        Id_BACK_QUOTE    = 10, // #string=$`#

        Id_rightContext  = 11,
        Id_QUOTE         = 12, // #string=$'#

        DOLLAR_ID_BASE   = 12;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[7]++;
  }

    private static final int
        Id_DOLLAR_1 = DOLLAR_ID_BASE + 1, // #string=$1#
        Id_DOLLAR_2 = DOLLAR_ID_BASE + 2, // #string=$2#
        Id_DOLLAR_3 = DOLLAR_ID_BASE + 3, // #string=$3#
        Id_DOLLAR_4 = DOLLAR_ID_BASE + 4, // #string=$4#
        Id_DOLLAR_5 = DOLLAR_ID_BASE + 5, // #string=$5#
        Id_DOLLAR_6 = DOLLAR_ID_BASE + 6, // #string=$6#
        Id_DOLLAR_7 = DOLLAR_ID_BASE + 7, // #string=$7#
        Id_DOLLAR_8 = DOLLAR_ID_BASE + 8, // #string=$8#
        Id_DOLLAR_9 = DOLLAR_ID_BASE + 9, // #string=$9#

        MAX_INSTANCE_ID = DOLLAR_ID_BASE + 9;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[8]++;
  }

    @Override
    protected int getMaxInstanceId()
    {
        return super.getMaxInstanceId() + MAX_INSTANCE_ID;
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        int id;
// #generated# Last update: 2001-05-24 16:09:31 GMT+02:00
        L0: { id = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[9]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[10]++; String X = null; int c;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[11]++;
            L: switch (s.length()) {
            case 2:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[3]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[12]++; switch (s.charAt(1)) {
                case '&':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[4]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[13]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[5]++;id=Id_AMPERSAND;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[14]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[15]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[6]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[16]++; break L;
                case '\'':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[7]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[17]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[8]++;id=Id_QUOTE;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[18]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[19]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[9]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[20]++; break L;
                case '*':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[10]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[21]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[11]++;id=Id_STAR;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[22]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[23]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[12]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[24]++; break L;
                case '+':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[13]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[25]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[14]++;id=Id_PLUS;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[26]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[27]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[15]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[28]++; break L;
                case '1':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[16]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[29]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[17]++;id=Id_DOLLAR_1;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[30]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[31]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[18]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[32]++; break L;
                case '2':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[19]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[33]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[20]++;id=Id_DOLLAR_2;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[34]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[35]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[21]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[36]++; break L;
                case '3':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[22]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[37]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[23]++;id=Id_DOLLAR_3;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[38]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[39]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[24]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[40]++; break L;
                case '4':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[25]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[41]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[26]++;id=Id_DOLLAR_4;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[42]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[43]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[27]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[44]++; break L;
                case '5':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[28]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[45]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[29]++;id=Id_DOLLAR_5;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[46]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[47]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[30]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[48]++; break L;
                case '6':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[31]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[49]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[32]++;id=Id_DOLLAR_6;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[50]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[51]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[33]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[52]++; break L;
                case '7':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[34]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[53]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[35]++;id=Id_DOLLAR_7;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[54]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[55]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[36]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[56]++; break L;
                case '8':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[37]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[57]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[38]++;id=Id_DOLLAR_8;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[58]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[59]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[39]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[60]++; break L;
                case '9':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[40]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[61]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[41]++;id=Id_DOLLAR_9;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[62]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[63]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[42]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[64]++; break L;
                case '_':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[43]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[65]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[44]++;id=Id_UNDERSCORE;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[66]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[67]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[45]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[68]++; break L;
                case '`':
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[46]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[69]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='$') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[47]++;id=Id_BACK_QUOTE;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[70]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[71]++; break L0;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[48]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[72]++; break L; default : CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[49]++;
                }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[73]++; break L;
            case 5:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[50]++; X="input";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[74]++;id=Id_input;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[75]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[76]++; break L;
            case 9:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[51]++; c=s.charAt(4);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[77]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[78]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((c=='M') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[52]++; X="lastMatch";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[79]++;id=Id_lastMatch;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[80]++;
 }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[53]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[81]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c=='P') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[54]++; X="lastParen";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[82]++;id=Id_lastParen;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[83]++;
 }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[55]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[84]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[56]++; X="multiline";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[85]++;id=Id_multiline;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[86]++;
 } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[57]++;}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[87]++;
                break L;
            case 11:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[58]++; X="leftContext";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[88]++;id=Id_leftContext;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[89]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[90]++; break L;
            case 12:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[59]++; X="rightContext";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[91]++;id=Id_rightContext;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[92]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[93]++; break L; default : CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[60]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[94]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[61]++; id = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[95]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[62]++;}
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[96]++;
int CodeCoverConditionCoverageHelper_C21;
// #/generated#

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[63]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[64]++;}

        int attr;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[97]++;
        switch (id) {
          case Id_multiline:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[65]++;
          case Id_STAR:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[66]++;
          case Id_input:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[67]++;
          case Id_UNDERSCORE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[68]++;
            attr = PERMANENT;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[98]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[99]++;
            break;
          default:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[69]++;
            attr = PERMANENT | READONLY;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[100]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[101]++;
            break;
        }

        return instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }

// #/string_id_map#

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[102]++;
        int shifted = id - super.getMaxInstanceId();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[103]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((1 <= shifted) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((shifted <= MAX_INSTANCE_ID) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[70]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[104]++;
            switch (shifted) {
                case Id_multiline:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[72]++;    return "multiline";
                case Id_STAR:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[73]++;         return "$*";

                case Id_input:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[74]++;        return "input";
                case Id_UNDERSCORE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[75]++;   return "$_";

                case Id_lastMatch:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[76]++;    return "lastMatch";
                case Id_AMPERSAND:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[77]++;    return "$&";

                case Id_lastParen:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[78]++;    return "lastParen";
                case Id_PLUS:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[79]++;         return "$+";

                case Id_leftContext:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[80]++;  return "leftContext";
                case Id_BACK_QUOTE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[81]++;   return "$`";

                case Id_rightContext:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[82]++; return "rightContext";
                case Id_QUOTE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[83]++;        return "$'"; default : CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[84]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[105]++;
            // Must be one of $1..$9, convert to 0..8
            int substring_number = shifted - DOLLAR_ID_BASE - 1;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[106]++;
            char[] buf = { '$', (char)('1' + substring_number) };
            return new String(buf);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[71]++;}
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[107]++;
        int shifted = id - super.getMaxInstanceId();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[108]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((1 <= shifted) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((shifted <= MAX_INSTANCE_ID) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[85]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[109]++;
            RegExpImpl impl = getImpl();
            Object stringResult;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[110]++;
            switch (shifted) {
              case Id_multiline:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[87]++;
              case Id_STAR:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[88]++;
                return ScriptRuntime.wrapBoolean(impl.multiline);

              case Id_input:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[89]++;
              case Id_UNDERSCORE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[90]++;
                stringResult = impl.input;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[111]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[112]++;
                break;

              case Id_lastMatch:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[91]++;
              case Id_AMPERSAND:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[92]++;
                stringResult = impl.lastMatch;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[113]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[114]++;
                break;

              case Id_lastParen:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[93]++;
              case Id_PLUS:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[94]++;
                stringResult = impl.lastParen;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[115]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[116]++;
                break;

              case Id_leftContext:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[95]++;
              case Id_BACK_QUOTE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[96]++;
                stringResult = impl.leftContext;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[117]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[118]++;
                break;

              case Id_rightContext:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[97]++;
              case Id_QUOTE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[98]++;
                stringResult = impl.rightContext;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[119]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[120]++;
                break;

              default:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[99]++;
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[121]++;
                    // Must be one of $1..$9, convert to 0..8
                    int substring_number = shifted - DOLLAR_ID_BASE - 1;
                    stringResult = impl.getParenSubString(substring_number);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[122]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[123]++;
                    break;
                }
            }
            return (stringResult == null) ? "" : stringResult.toString();

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[86]++;}
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[124]++;
        int shifted = id - super.getMaxInstanceId();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[125]++;
        switch (shifted) {
            case Id_multiline:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[100]++;
            case Id_STAR:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[101]++;
                getImpl().multiline = ScriptRuntime.toBoolean(value);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[126]++;
                return;

            case Id_input:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[102]++;
            case Id_UNDERSCORE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[103]++;
                getImpl().input = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[127]++;
                return;

            case Id_lastMatch:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[104]++;
            case Id_AMPERSAND:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[105]++;
            case Id_lastParen:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[106]++;
            case Id_PLUS:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[107]++;
            case Id_leftContext:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[108]++;
            case Id_BACK_QUOTE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[109]++;
            case Id_rightContext:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[110]++;
            case Id_QUOTE:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[111]++;
                return;
            default:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[112]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[128]++;
                int substring_number = shifted - DOLLAR_ID_BASE - 1;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[129]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((0 <= substring_number) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((substring_number <= 8) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[113]++;
                  return;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.branches[114]++;}
        }
        super.setInstanceIdValue(id, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5.statements[130]++;
    }

}

class CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5 ());
  }
    public static long[] statements = new long[131];
    public static long[] branches = new long[115];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.regexp.RHINO-SRC-NativeRegExpCtor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,2,2,2};
    for (int i = 1; i <= 24; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7av8umlnax7dnacfuqkx9p5r5 () {
    super("org.mozilla.javascript.regexp.RHINO-SRC-NativeRegExpCtor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 130; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 114; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.regexp.RHINO-SRC-NativeRegExpCtor.java");
      for (int i = 1; i <= 130; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 114; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

