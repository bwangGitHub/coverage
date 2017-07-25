/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.regexp;

import org.mozilla.javascript.*;

/**
 *
 */
public class RegExpImpl implements RegExpProxy {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.ping();
  }


    public boolean isRegExp(Scriptable obj) {
        return obj instanceof NativeRegExp;
    }

    public Object compileRegExp(Context cx, String source, String flags)
    {
        return NativeRegExp.compileRE(cx, source, flags, false);
    }

    public Scriptable wrapRegExp(Context cx, Scriptable scope,
                                 Object compiled)
    {
        return new NativeRegExp(scope, (RECompiled) compiled);
    }

    public Object action(Context cx, Scriptable scope,
                         Scriptable thisObj, Object[] args,
                         int actionType)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[1]++;
        GlobData data = new GlobData();
        data.mode = actionType;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[2]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[3]++;

        switch (actionType) {
          case RA_MATCH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[1]++;
            {
                Object rval;
                data.optarg = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[4]++;
                rval = matchOrReplace(cx, scope, thisObj, args,
                                      this, data, false);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[5]++;
                return data.arrayobj == null ? rval : data.arrayobj;
            }

          case RA_SEARCH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[2]++;
            data.optarg = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[6]++;
            return matchOrReplace(cx, scope, thisObj, args,
                                  this, data, false);

          case RA_REPLACE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[3]++;
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[7]++;
                Object arg1 = args.length < 2 ? Undefined.instance : args[1];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[8]++;
                String repstr = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[9]++;
                Function lambda = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
                if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((arg1 instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[4]++;
                    lambda = (Function) arg1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[11]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[5]++;
                    repstr = ScriptRuntime.toString(arg1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[12]++;
                }

                data.optarg = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[13]++;
                data.lambda = lambda;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[14]++;
                data.repstr = repstr;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[15]++;
                data.dollar = repstr == null ? -1 : repstr.indexOf('$');
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[16]++;
                data.charBuf = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[17]++;
                data.leftIndex = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[18]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[19]++;
                Object val = matchOrReplace(cx, scope, thisObj, args,
                                            this, data, true);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;

                if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((data.charBuf == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[6]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
                    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((data.global) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((val.equals(Boolean.TRUE)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false))
                    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[8]++;
                        /* Didn't match even once. */
                        return data.str;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[9]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[22]++;
                    SubString lc = this.leftContext;
                    replace_glob(data, cx, scope, this, lc.index, lc.length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[23]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[7]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[24]++;
                SubString rc = this.rightContext;
                data.charBuf.append(rc.str, rc.index, rc.index + rc.length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[25]++;
                return data.charBuf.toString();
            }

          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[10]++;
            throw Kit.codeBug();
        }
    }

    /**
     * Analog of C match_or_replace.
     */
    private static Object matchOrReplace(Context cx, Scriptable scope,
                                         Scriptable thisObj, Object[] args,
                                         RegExpImpl reImpl,
                                         GlobData data, boolean forceFlat)
    {
        NativeRegExp re;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[26]++;

        String str = ScriptRuntime.toString(thisObj);
        data.str = str;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[27]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[28]++;
        Scriptable topScope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[29]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[11]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[30]++;
            RECompiled compiled = NativeRegExp.compileRE(cx, "", "", false);
            re = new NativeRegExp(topScope, compiled);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[31]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[12]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[32]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((args[0] instanceof NativeRegExp) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[13]++;
            re = (NativeRegExp) args[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[33]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[14]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[34]++;
            String src = ScriptRuntime.toString(args[0]);
            String opt;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[35]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((data.optarg < args.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[15]++;
                args[0] = src;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[36]++;
                opt = ScriptRuntime.toString(args[data.optarg]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[37]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[16]++;
                opt = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[38]++;
            }
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[39]++;
            RECompiled compiled = NativeRegExp.compileRE(cx, src, opt, forceFlat);
            re = new NativeRegExp(topScope, compiled);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[40]++;
        }
}

        data.global = (re.getFlags() & NativeRegExp.JSREG_GLOB) != 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[41]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[42]++;
        int[] indexp = { 0 };
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[43]++;
        Object result = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((data.mode == RA_SEARCH) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[17]++;
            result = re.executeRegExp(cx, scope, reImpl,
                                      str, indexp, NativeRegExp.TEST);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[45]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((result.equals(Boolean.TRUE)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[19]++;
                result = Integer.valueOf(reImpl.leftContext.length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[47]++;
}
            else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[20]++;
                result = Integer.valueOf(-1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[48]++;
}

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[18]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[49]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((data.global) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[21]++;
            re.lastIndex = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[50]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[51]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
            for (int count = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((indexp[0] <= str.length()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); count++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[3]++;
}
                result = re.executeRegExp(cx, scope, reImpl,
                                          str, indexp, NativeRegExp.TEST);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[52]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((result.equals(Boolean.TRUE)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[23]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[54]++;
                    break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[24]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[55]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((data.mode == RA_MATCH) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[25]++;
                    match_glob(data, cx, scope, count, reImpl);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[56]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[26]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((data.mode != RA_REPLACE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[27]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[58]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[28]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[59]++;
                    SubString lastMatch = reImpl.lastMatch;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[60]++;
                    int leftIndex = data.leftIndex;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[61]++;
                    int leftlen = lastMatch.index - leftIndex;
                    data.leftIndex = lastMatch.index + lastMatch.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[62]++;
                    replace_glob(data, cx, scope, reImpl, leftIndex, leftlen);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[63]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((reImpl.lastMatch.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[29]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[65]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((indexp[0] == str.length()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[31]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[66]++;
                        break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[32]++;}
                    indexp[0]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[67]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[30]++;}
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[22]++;
            result = re.executeRegExp(cx, scope, reImpl, str, indexp,
                                      ((data.mode == RA_REPLACE)
                                       ? NativeRegExp.TEST
                                       : NativeRegExp.MATCH));
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[68]++;
        }
}

        return result;
    }



    public int find_split(Context cx, Scriptable scope, String target,
                          String separator, Scriptable reObj,
                          int[] ip, int[] matchlen,
                          boolean[] matched, String[][] parensp)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[69]++;
        int i = ip[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[70]++;
        int length = target.length();
        int result;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[71]++;

        int version = cx.getLanguageVersion();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[72]++;
        NativeRegExp re = (NativeRegExp) reObj;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[73]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[4]++;


        again:
        while (true) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[74]++;  // imitating C label
            /* JS1.2 deviated from Perl by never matching at end of string. */
            int ipsave = ip[0]; // reuse ip to save object creation
            ip[0] = i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[75]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[76]++;
            Object ret = re.executeRegExp(cx, scope, this, target, ip,
                                          NativeRegExp.TEST);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[77]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ret != Boolean.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[33]++;
                // Mismatch: ensure our caller advances i past end of string.
                ip[0] = ipsave;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[78]++;
                matchlen[0] = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[79]++;
                matched[0] = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[80]++;
                return length;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[34]++;}
            i = ip[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[81]++;
            ip[0] = ipsave;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[82]++;
            matched[0] = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[83]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[84]++;

            SubString sep = this.lastMatch;
            matchlen[0] = sep.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[85]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[86]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((matchlen[0] == 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[35]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[87]++;
int CodeCoverConditionCoverageHelper_C19;
                /*
                 * Empty string match: never split on an empty
                 * match at the start of a find_split cycle.  Same
                 * rule as for an empty global match in
                 * match_or_replace.
                 */
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i == ip[0]) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[37]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[88]++;
int CodeCoverConditionCoverageHelper_C20;
                    /*
                     * "Bump-along" to avoid sticking at an empty
                     * match, but don't bump past end of string --
                     * our caller must do that by adding
                     * sep->length to our return value.
                     */
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i == length) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[39]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[89]++;
int CodeCoverConditionCoverageHelper_C21;
                        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((version == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[41]++;
                            matchlen[0] = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[90]++;
                            result = i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[91]++;

                        }
                        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[42]++;
                            result = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[92]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[93]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[40]++;}
                    i++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[94]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[95]++;
                    continue again;
 // imitating C goto
                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[36]++;}
            // PR_ASSERT((size_t)i >= sep->length);
            result = i - matchlen[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[96]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[97]++;
            break;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[98]++;
        int size = (parens == null) ? 0 : parens.length;
        parensp[0] = new String[size];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[99]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[100]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int num = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((num < size) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); num++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[9]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[101]++;
            SubString parsub = getParenSubString(num);
            parensp[0][num] = parsub.toString();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[102]++;
        }
        return result;
    }

    /**
     * Analog of REGEXP_PAREN_SUBSTRING in C jsregexp.h.
     * Assumes zero-based; i.e., for $3, i==2
     */
    SubString getParenSubString(int i)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[103]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((parens != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < parens.length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[43]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[104]++;
            SubString parsub = parens[i];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[105]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((parsub != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[45]++;
                return parsub;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[46]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[44]++;}
        return SubString.emptySubString;
    }

    /*
     * Analog of match_glob() in jsstr.c
     */
    private static void match_glob(GlobData mdata, Context cx,
                                   Scriptable scope, int count,
                                   RegExpImpl reImpl)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[106]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((mdata.arrayobj == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[47]++;
            mdata.arrayobj = cx.newArray(scope, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[107]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[48]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[108]++;
        SubString matchsub = reImpl.lastMatch;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[109]++;
        String matchstr = matchsub.toString();
        mdata.arrayobj.put(count, mdata.arrayobj, matchstr);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[110]++;
    }

    /*
     * Analog of replace_glob() in jsstr.c
     */
    private static void replace_glob(GlobData rdata, Context cx,
                                     Scriptable scope, RegExpImpl reImpl,
                                     int leftIndex, int leftlen)
    {
        int replen;
        String lambdaStr;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[111]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((rdata.lambda != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[49]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[112]++;
            // invoke lambda function with args lastMatch, $1, $2, ... $n,
            // leftContext.length, whole string.
            SubString[] parens = reImpl.parens;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[113]++;
            int parenCount = (parens == null) ? 0 : parens.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[114]++;
            Object[] args = new Object[parenCount + 3];
            args[0] = reImpl.lastMatch.toString();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[115]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[116]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[10]++;


int CodeCoverConditionCoverageHelper_C27;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < parenCount) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[10]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[11]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[12]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[117]++;
                SubString sub = parens[i];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[118]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((sub != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[51]++;
                    args[i+1] = sub.toString();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[119]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[52]++;
                    args[i+1] = Undefined.instance;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[120]++;
                }
            }
            args[parenCount+1] = Integer.valueOf(reImpl.leftContext.length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[121]++;
            args[parenCount+2] = rdata.str;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[122]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[123]++;
int CodeCoverConditionCoverageHelper_C29;
            // This is a hack to prevent expose of reImpl data to
            // JS function which can run new regexps modifing
            // regexp that are used later by the engine.
            // TODO: redesign is necessary
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((reImpl != ScriptRuntime.getRegExpProxy(cx)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[53]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[124]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[54]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[125]++;
            RegExpImpl re2 = new RegExpImpl();
            re2.multiline = reImpl.multiline;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[126]++;
            re2.input = reImpl.input;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[127]++;
            ScriptRuntime.setRegExpProxy(cx, re2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[128]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[129]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[130]++;
                Scriptable parent = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[131]++;
                Object result = rdata.lambda.call(cx, parent, parent, args);
                lambdaStr = ScriptRuntime.toString(result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[132]++;
            } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[55]++;
}
                ScriptRuntime.setRegExpProxy(cx, reImpl);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[133]++;
            }
            replen = lambdaStr.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[134]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[50]++;
            lambdaStr = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[135]++;
            replen = rdata.repstr.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[136]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[137]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((rdata.dollar >= 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[56]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[138]++;
                int[] skip = new int[1];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[139]++;
                int dp = rdata.dollar;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[140]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[13]++;


int CodeCoverConditionCoverageHelper_C31;
                do {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[13]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[14]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[15]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[141]++;
                    SubString sub = interpretDollar(cx, reImpl, rdata.repstr,
                                                    dp, skip);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[142]++;
int CodeCoverConditionCoverageHelper_C32;
                    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((sub != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[58]++;
                        replen += sub.length - skip[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[143]++;
                        dp += skip[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[144]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[59]++;
                        ++dp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[145]++;
                    }
                    dp = rdata.repstr.indexOf('$', dp);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[146]++;
                } while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((dp >= 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false));

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[57]++;}
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[147]++;

        int growth = leftlen + replen + reImpl.rightContext.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[148]++;
        StringBuilder charBuf = rdata.charBuf;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[149]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((charBuf == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[60]++;
            charBuf = new StringBuilder(growth);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[150]++;
            rdata.charBuf = charBuf;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[151]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[61]++;
            charBuf.ensureCapacity(rdata.charBuf.length() + growth);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[152]++;
        }

        charBuf.append(reImpl.leftContext.str, leftIndex, leftIndex + leftlen);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[153]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[154]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((rdata.lambda != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[62]++;
            charBuf.append(lambdaStr);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[155]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[63]++;
            do_replace(rdata, cx, reImpl);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[156]++;
        }
    }

    private static SubString interpretDollar(Context cx, RegExpImpl res,
                                             String da, int dp, int[] skip)
    {
        char dc;
        int num, tmp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[157]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((da.charAt(dp) != '$') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[64]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[158]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[65]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[159]++;

        /* Allow a real backslash (literal "\\") to escape "$1" etc. */
        int version = cx.getLanguageVersion();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[160]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((version != Context.VERSION_DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((version <= Context.VERSION_1_4) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false))
        {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[66]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[161]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((dp > 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((da.charAt(dp - 1) == '\\') && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[68]++;
                return null;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[69]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[67]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[162]++;
        int daL = da.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[163]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((dp + 1 >= daL) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[70]++;
            return null;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[71]++;}
        /* Interpret all Perl match-induced dollar variables. */
        dc = da.charAt(dp + 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[164]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[165]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((NativeRegExp.isDigit(dc)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[72]++;
            int cp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[166]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((version != Context.VERSION_DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((version <= Context.VERSION_1_4) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false))
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[74]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[167]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((dc == '0') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[76]++;
                    return null;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[77]++;}
                /* Check for overflow to avoid gobbling arbitrary decimal digits. */
                num = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[168]++;
                cp = dp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[169]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[170]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[16]++;


                while (++cp < daL && NativeRegExp.isDigit(dc = da.charAt(cp)))
                {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[16]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[17]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[18]++;
}
                    tmp = 10 * num + (dc - '0');
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[171]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[172]++;
int CodeCoverConditionCoverageHelper_C43;
                    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((tmp < num) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[78]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[173]++;
                        break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[79]++;}
                    num = tmp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[174]++;
                }

            }
            else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[75]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[175]++;  /* ECMA 3, 1-9 or 01-99 */
                int parenCount = (res.parens == null) ? 0 : res.parens.length;
                num = dc - '0';
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[176]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[177]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((num > parenCount) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[80]++;
                    return null;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[81]++;}
                cp = dp + 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[178]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[179]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 (((dp + 2) < daL) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[82]++;
                    dc = da.charAt(dp + 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[180]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[181]++;
int CodeCoverConditionCoverageHelper_C46;
                    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((NativeRegExp.isDigit(dc)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[84]++;
                        tmp = 10 * num + (dc - '0');
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[182]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[183]++;
int CodeCoverConditionCoverageHelper_C47;
                        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((tmp <= parenCount) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[86]++;
                            cp++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[184]++;
                            num = tmp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[185]++;

                        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[87]++;}

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[85]++;}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[83]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[186]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((num == 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[88]++; return null;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[89]++;}  /* $0 or $00 is not valid */
            }
            /* Adjust num from 1 $n-origin to 0 array-index-origin. */
            num--;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[187]++;
            skip[0] = cp - dp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[188]++;
            return res.getParenSubString(num);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[73]++;}

        skip[0] = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[189]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[190]++;
        switch (dc) {
          case '$':
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[90]++;
            return new SubString("$");
          case '&':
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[91]++;
            return res.lastMatch;
          case '+':
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[92]++;
            return res.lastParen;
          case '`':
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[93]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[191]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((version == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[94]++;
                /*
                 * JS1.2 imitated the Perl4 bug where left context at each step
                 * in an iterative use of a global regexp started from last match,
                 * not from the start of the target string.  But Perl4 does start
                 * $` at the beginning of the target string when it is used in a
                 * substitution, so we emulate that special case here.
                 */
                res.leftContext.index = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[192]++;
                res.leftContext.length = res.lastMatch.index;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[193]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[95]++;}
            return res.leftContext;
          case '\'':
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[96]++;
            return res.rightContext; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[97]++;
        }
        return null;
    }

    /**
     * Analog of do_replace in jsstr.c
     */
    private static void do_replace(GlobData rdata, Context cx,
                                   RegExpImpl regExpImpl)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[194]++;
        StringBuilder charBuf = rdata.charBuf;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[195]++;
        int cp = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[196]++;
        String da = rdata.repstr;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[197]++;
        int dp = rdata.dollar;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[198]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((dp != -1) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[98]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[199]++;
            int[] skip = new int[1];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[200]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[19]++;


int CodeCoverConditionCoverageHelper_C51;
            do {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[19]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[20]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[21]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[201]++;
                int len = dp - cp;
                charBuf.append(da.substring(cp, dp));
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[202]++;
                cp = dp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[203]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[204]++;
                SubString sub = interpretDollar(cx, regExpImpl, da,
                                                dp, skip);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[205]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((sub != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[100]++;
                    len = sub.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[206]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[207]++;
int CodeCoverConditionCoverageHelper_C53;
                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((len > 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[102]++;
                        charBuf.append(sub.str, sub.index, sub.index + len);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[208]++;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[103]++;}
                    cp += skip[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[209]++;
                    dp += skip[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[210]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[101]++;
                    ++dp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[211]++;
                }
                dp = da.indexOf('$', dp);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[212]++;
            } while ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((dp >= 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false));

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[99]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[213]++;
        int daL = da.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[214]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((daL > cp) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[104]++;
            charBuf.append(da.substring(cp, daL));
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[215]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[105]++;}
    }

    /*
     * See ECMA 15.5.4.8.  Modified to match JS 1.2 - optionally takes
     * a limit argument and accepts a regular expression as the split
     * argument.
     */
    public Object js_split(Context cx, Scriptable scope,
                                   String target, Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[216]++;
        // create an empty Array to return;
        Scriptable result = cx.newArray(scope, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[217]++;
int CodeCoverConditionCoverageHelper_C55;

        // return an array consisting of the target if no separator given
        // don't check against undefined, because we want
        // 'fooundefinedbar'.split(void 0) to split to ['foo', 'bar']
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[106]++;
            result.put(0, result, target);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[218]++;
            return result;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[107]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[219]++;

        // Use the second argument as the split limit, if given.
        boolean limited = (args.length > 1) && (args[1] != Undefined.instance);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[220]++;
        long limit = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[221]++;
int CodeCoverConditionCoverageHelper_C56;  // Initialize to avoid warning.
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((limited) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[108]++;
            /* Clamp limit between 0 and 1 + string length. */
            limit = ScriptRuntime.toUint32(args[1]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[222]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[223]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((limit > target.length()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[110]++;
                limit = 1 + target.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[224]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[111]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[109]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[225]++;

        String separator = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[226]++;
        int[] matchlen = new int[1];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[227]++;
        Scriptable re = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[228]++;
        RegExpProxy reProxy = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[229]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((args[0] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[112]++;
            reProxy = ScriptRuntime.getRegExpProxy(cx);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[230]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[231]++;
int CodeCoverConditionCoverageHelper_C59;
            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((reProxy != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[114]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[232]++;
                Scriptable test = (Scriptable)args[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[233]++;
int CodeCoverConditionCoverageHelper_C60;
                if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((reProxy.isRegExp(test)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[116]++;
                    re = test;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[234]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[117]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[115]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[113]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[235]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((re == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[118]++;
            separator = ScriptRuntime.toString(args[0]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[236]++;
            matchlen[0] = separator.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[237]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[119]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[238]++;

        // split target with separator or re
        int[] ip = { 0 };
        int match;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[239]++;
        int len = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[240]++;
        boolean[] matched = { false };
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[241]++;
        String[][] parens = { null };
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[242]++;
        int version = cx.getLanguageVersion();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[243]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[22]++;


        while ((match = find_split(cx, scope, target, separator, version,
                                   reProxy, re, ip, matchlen, matched, parens))
               >= 0)
        {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[22]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[23]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[24]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[244]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C63 |= (32)) == 0 || true) &&
 ((limited) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((len >= limit) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((match > target.length()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[120]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[245]++;
                break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[121]++;}

            String substr;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[246]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((target.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[122]++;
                substr = target;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[247]++;
}
            else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[123]++;
                substr = target.substring(ip[0], match);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[248]++;
}

            result.put(len, result, substr);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[249]++;
            len++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[250]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[251]++;
int CodeCoverConditionCoverageHelper_C65;
        /*
         * Imitate perl's feature of including parenthesized substrings
         * that matched part of the delimiter in the new array, after the
         * split substring that was delimited.
         */
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((re != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((matched[0] == true) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[124]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[252]++;
                int size = parens[0].length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[253]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[25]++;


int CodeCoverConditionCoverageHelper_C66;
                for (int num = 0;(((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((num < size) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false); num++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[25]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[26]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[27]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[254]++;
int CodeCoverConditionCoverageHelper_C67;
                    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((limited) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((len >= limit) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[126]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[255]++;
                        break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[127]++;}
                    result.put(len, result, parens[0][num]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[256]++;
                    len++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[257]++;
                }
                matched[0] = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[258]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[125]++;}
            ip[0] = match + matchlen[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[259]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[260]++;
int CodeCoverConditionCoverageHelper_C68;

            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((version < Context.VERSION_1_3) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((version != Context.VERSION_DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false))
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[128]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[261]++;
int CodeCoverConditionCoverageHelper_C69;
        /*
         * Deviate from ECMA to imitate Perl, which omits a final
         * split unless a limit argument is given and big enough.
         */
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((limited) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((ip[0] == target.length()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[130]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[262]++;
                    break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[131]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[129]++;}
        }
        return result;
    }

    /*
     * Used by js_split to find the next split point in target,
     * starting at offset ip and looking either for the given
     * separator substring, or for the next re match.  ip and
     * matchlen must be reference variables (assumed to be arrays of
     * length 1) so they can be updated in the leading whitespace or
     * re case.
     *
     * Return -1 on end of string, >= 0 for a valid index of the next
     * separator occurrence if found, or the string length if no
     * separator is found.
     */
    private static int find_split(Context cx, Scriptable scope, String target,
                                  String separator, int version,
                                  RegExpProxy reProxy, Scriptable re,
                                  int[] ip, int[] matchlen, boolean[] matched,
                                  String[][] parensp)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[263]++;
        int i = ip[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[264]++;
        int length = target.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[265]++;
int CodeCoverConditionCoverageHelper_C70;

        /*
         * Perl4 special case for str.split(' '), only if the user has selected
         * JavaScript1.2 explicitly.  Split on whitespace, and skip leading w/s.
         * Strange but true, apparently modeled after awk.
         */
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (128)) == 0 || true) &&
 ((version == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (32)) == 0 || true) &&
 ((re == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((separator.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((separator.charAt(0) == ' ') && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 4) && false))
        {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[132]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[266]++;
int CodeCoverConditionCoverageHelper_C71;
            /* Skip leading whitespace if at front of str. */
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[134]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[267]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[28]++;


int CodeCoverConditionCoverageHelper_C72;
                while ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((Character.isWhitespace(target.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) { 
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[28]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[29]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[30]++;
}
                    i++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[268]++;
  }
                ip[0] = i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[269]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[135]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[270]++;
int CodeCoverConditionCoverageHelper_C73;

            /* Don't delimit whitespace at end of string. */
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((i == length) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[136]++;
                return -1;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[137]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[271]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[31]++;


int CodeCoverConditionCoverageHelper_C74;

            /* Skip over the non-whitespace chars. */
            while ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((Character.isWhitespace(target.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) { 
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[31]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[32]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[33]++;
}
                i++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[272]++;
  }
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[273]++;

            /* Now skip the next run of whitespace. */
            int j = i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[274]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[34]++;


int CodeCoverConditionCoverageHelper_C75;
            while ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((j < length) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((Character.isWhitespace(target.charAt(j))) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) { 
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[34]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[35]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.loops[36]++;
}
                j++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[275]++;
  }

            /* Update matchlen to count delimiter chars. */
            matchlen[0] = j - i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[276]++;
            return i;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[133]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[277]++;
int CodeCoverConditionCoverageHelper_C76;

        /*
         * Stop if past end of string.  If at end of string, we will
         * return target length, so that
         *
         *  "ab,".split(',') => new Array("ab", "")
         *
         * and the resulting array converts back to the string "ab,"
         * for symmetry.  NB: This differs from perl, which drops the
         * trailing empty substring if the LIMIT argument is omitted.
         */
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((i > length) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[138]++;
            return -1;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[139]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[278]++;
int CodeCoverConditionCoverageHelper_C77;

        /*
         * Match a regular expression against the separator at or
         * above index i.  Return -1 at end of string instead of
         * trying for a match, so we don't get stuck in a loop.
         */
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((re != null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[140]++;
            return reProxy.find_split(cx, scope, target, separator, re,
                                      ip, matchlen, matched, parensp);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[141]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[279]++;
int CodeCoverConditionCoverageHelper_C78;

        /*
         * Deviate from ECMA by never splitting an empty string by any separator
         * string into a non-empty array (an array of length 1 that contains the
         * empty string).
         */
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (32)) == 0 || true) &&
 ((version != Context.VERSION_DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((version < Context.VERSION_1_3) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[142]++;
            return -1;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[143]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[280]++;
int CodeCoverConditionCoverageHelper_C79;

        /*
         * Special case: if sep is the empty string, split str into
         * one character substrings.  Let our caller worry about
         * whether to split once at end of string into an empty
         * substring.
         *
         * For 1.2 compatibility, at the end of the string, we return the length as
         * the result, and set the separator length to 1 -- this allows the caller
         * to include an additional null string at the end of the substring list.
         */
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((separator.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[144]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[281]++;
int CodeCoverConditionCoverageHelper_C80;
            if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((version == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[146]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[282]++;
int CodeCoverConditionCoverageHelper_C81;
                if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((i == length) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[148]++;
                    matchlen[0] = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[283]++;
                    return i;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[149]++;}
                return i + 1;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[147]++;}
            return (i == length) ? -1 : i + 1;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[145]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[284]++;
int CodeCoverConditionCoverageHelper_C82;

        /* Punt to j.l.s.indexOf; return target length if separator is
         * not found.
         */
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((ip[0] >= length) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[150]++;
            return length;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.branches[151]++;}

        i = target.indexOf(separator, ip[0]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[285]++;

        return (i != -1) ? i : length;
    }

    protected String          input;         /* input string to match (perl $_, GC root) */
    protected boolean         multiline;     /* whether input contains newlines (perl $*) */
    protected SubString[]     parens;        /* Vector of SubString; last set of parens
                                      matched (perl $1, $2) */
    protected SubString       lastMatch;     /* last string matched (perl $&) */
    protected SubString       lastParen;     /* last paren matched (perl $+) */
    protected SubString       leftContext;   /* input to left of last match (perl $`) */
    protected SubString       rightContext;  /* input to right of last match (perl $') */
}


final class GlobData
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.ping();
  }

    int      mode;      /* input: return index, match object, or void */
    int      optarg;    /* input: index of optional flags argument */
    boolean  global;    /* output: whether regexp was global */
    String   str;       /* output: 'this' parameter object as string */

    // match-specific data

    Scriptable arrayobj;

    // replace-specific data

    Function      lambda;        /* replacement function object or null */
    String        repstr;        /* replacement string */
    int           dollar = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5.statements[286]++;
  }   /* -1 or index of first $ in repstr */
    StringBuilder charBuf;       /* result characters, null initially */
    int           leftIndex;     /* leftContext index, always 0 for JS1.2 */
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5 ());
  }
    public static long[] statements = new long[287];
    public static long[] branches = new long[152];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[83];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.regexp.RHINO-SRC-RegExpImpl.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,1,1,1,1,2,1,1,2,1,1,1,1,0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,2,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3,1,2,1,2,2,2,3,1,2,1,2,2,1,1,3,1,1,1,1};
    for (int i = 1; i <= 82; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw9a7kd5d0mu12tjg7yrzo9r5 () {
    super("org.mozilla.javascript.regexp.RHINO-SRC-RegExpImpl.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 286; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 151; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 82; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.regexp.RHINO-SRC-RegExpImpl.java");
      for (int i = 1; i <= 286; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 151; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 82; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 12; i++) {
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

