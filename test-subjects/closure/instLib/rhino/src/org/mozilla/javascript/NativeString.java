/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.text.Collator;

/**
 * This class implements the String native object.
 *
 * See ECMA 15.5.
 *
 * String methods for dealing with regular expressions are
 * ported directly from C. Latest port is from version 1.40.12.19
 * in the JSFUN13_BRANCH.
 *
 */
final class NativeString extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.ping();
  }

    static final long serialVersionUID = 920268368584188687L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[1]++;
  }

    private static final Object STRING_TAG = "String";
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[2]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[3]++;
        NativeString obj = new NativeString("");
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[4]++;
    }

    NativeString(CharSequence s) {
        string = s;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[5]++;
    }

    @Override
    public String getClassName() {
        return "String";
    }

    private static final int
        Id_length                    =  1,
        MAX_INSTANCE_ID              =  1;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[6]++;
  }

    @Override
    protected int getMaxInstanceId()
    {
        return MAX_INSTANCE_ID;
    }

    @Override
    protected int findInstanceIdInfo(String s)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((s.equals("length")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[1]++;
            return instanceIdInfo(DONTENUM | READONLY | PERMANENT, Id_length);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[2]++;}
        return super.findInstanceIdInfo(s);
    }

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((id == Id_length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[3]++; return "length";
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[4]++;}
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((id == Id_length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[5]++;
            return ScriptRuntime.wrapInt(string.length());

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[6]++;}
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor)
    {
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_fromCharCode,
                "fromCharCode", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[10]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_charAt, "charAt", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[11]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_charCodeAt, "charCodeAt", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[12]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_indexOf, "indexOf", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[13]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_lastIndexOf, "lastIndexOf", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[14]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_split, "split", 3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[15]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_substring, "substring", 3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[16]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_toLowerCase, "toLowerCase", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[17]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_toUpperCase, "toUpperCase", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[18]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_substr, "substr", 3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[19]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_concat, "concat", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[20]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_slice, "slice", 3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[21]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_equalsIgnoreCase, "equalsIgnoreCase", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[22]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_match, "match", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[23]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_search, "search", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[24]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_replace, "replace", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[25]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_localeCompare, "localeCompare", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[26]++;
        addIdFunctionProperty(ctor, STRING_TAG,
                ConstructorId_toLocaleLowerCase, "toLocaleLowerCase", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[27]++;
        super.fillConstructorProperties(ctor);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[28]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[29]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[7]++;       arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[30]++; s="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[31]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[32]++;       break;
          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[8]++;          arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[33]++; s="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[34]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[35]++;          break;
          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[9]++;          arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[36]++; s="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[37]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[38]++;          break;
          case Id_valueOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[10]++;           arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[39]++; s="valueOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[40]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[41]++;           break;
          case Id_charAt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[11]++;            arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[42]++; s="charAt";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[43]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[44]++;            break;
          case Id_charCodeAt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[12]++;        arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[45]++; s="charCodeAt";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[46]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[47]++;        break;
          case Id_indexOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[13]++;           arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[48]++; s="indexOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[49]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[50]++;           break;
          case Id_lastIndexOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[14]++;       arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[51]++; s="lastIndexOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[52]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[53]++;       break;
          case Id_split:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[15]++;             arity=2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[54]++; s="split";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[55]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[56]++;             break;
          case Id_substring:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[16]++;         arity=2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[57]++; s="substring";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[58]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[59]++;         break;
          case Id_toLowerCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[17]++;       arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[60]++; s="toLowerCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[61]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[62]++;       break;
          case Id_toUpperCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[18]++;       arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[63]++; s="toUpperCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[64]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[65]++;       break;
          case Id_substr:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[19]++;            arity=2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[66]++; s="substr";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[67]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[68]++;            break;
          case Id_concat:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[20]++;            arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[69]++; s="concat";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[70]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[71]++;            break;
          case Id_slice:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[21]++;             arity=2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[72]++; s="slice";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[73]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[74]++;             break;
          case Id_bold:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[22]++;              arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[75]++; s="bold";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[76]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[77]++;              break;
          case Id_italics:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[23]++;           arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[78]++; s="italics";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[79]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[80]++;           break;
          case Id_fixed:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[24]++;             arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[81]++; s="fixed";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[82]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[83]++;             break;
          case Id_strike:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[25]++;            arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[84]++; s="strike";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[85]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[86]++;            break;
          case Id_small:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[26]++;             arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[87]++; s="small";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[88]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[89]++;             break;
          case Id_big:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[27]++;               arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[90]++; s="big";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[91]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[92]++;               break;
          case Id_blink:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[28]++;             arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[93]++; s="blink";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[94]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[95]++;             break;
          case Id_sup:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[29]++;               arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[96]++; s="sup";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[97]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[98]++;               break;
          case Id_sub:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[30]++;               arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[99]++; s="sub";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[100]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[101]++;               break;
          case Id_fontsize:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[31]++;          arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[102]++; s="fontsize";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[103]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[104]++;          break;
          case Id_fontcolor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[32]++;         arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[105]++; s="fontcolor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[106]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[107]++;         break;
          case Id_link:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[33]++;              arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[108]++; s="link";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[109]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[110]++;              break;
          case Id_anchor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[34]++;            arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[111]++; s="anchor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[112]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[113]++;            break;
          case Id_equals:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[35]++;            arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[114]++; s="equals";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[115]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[116]++;            break;
          case Id_equalsIgnoreCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[36]++;  arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[117]++; s="equalsIgnoreCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[118]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[119]++;  break;
          case Id_match:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[37]++;             arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[120]++; s="match";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[121]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[122]++;             break;
          case Id_search:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[38]++;            arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[123]++; s="search";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[124]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[125]++;            break;
          case Id_replace:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[39]++;           arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[126]++; s="replace";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[127]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[128]++;           break;
          case Id_localeCompare:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[40]++;     arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[129]++; s="localeCompare";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[130]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[131]++;     break;
          case Id_toLocaleLowerCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[41]++; arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[132]++; s="toLocaleLowerCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[133]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[134]++; break;
          case Id_toLocaleUpperCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[42]++; arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[135]++; s="toLocaleUpperCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[136]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[137]++; break;
          case Id_trim:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[43]++;              arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[138]++; s="trim";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[139]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[140]++;              break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[44]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(STRING_TAG, id, s, arity);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[141]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[142]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((f.hasTag(STRING_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[45]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[46]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[143]++;
        int id = f.methodId();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[144]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[1]++;


      again:
        for(;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[3]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[145]++;
            switch (id) {
              case ConstructorId_charAt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[47]++;
              case ConstructorId_charCodeAt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[48]++;
              case ConstructorId_indexOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[49]++;
              case ConstructorId_lastIndexOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[50]++;
              case ConstructorId_split:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[51]++;
              case ConstructorId_substring:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[52]++;
              case ConstructorId_toLowerCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[53]++;
              case ConstructorId_toUpperCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[54]++;
              case ConstructorId_substr:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[55]++;
              case ConstructorId_concat:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[56]++;
              case ConstructorId_slice:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[57]++;
              case ConstructorId_equalsIgnoreCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[58]++;
              case ConstructorId_match:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[59]++;
              case ConstructorId_search:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[60]++;
              case ConstructorId_replace:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[61]++;
              case ConstructorId_localeCompare:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[62]++;
              case ConstructorId_toLocaleLowerCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[63]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[146]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[64]++;
                    thisObj = ScriptRuntime.toObject(scope,
                            ScriptRuntime.toCharSequence(args[0]));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[147]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[148]++;
                    Object[] newArgs = new Object[args.length-1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[149]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
                    for (int i=0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < newArgs.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[6]++;
}
                        newArgs[i] = args[i+1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[150]++;
  }
                    args = newArgs;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[151]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[65]++;
                    thisObj = ScriptRuntime.toObject(scope,
                            ScriptRuntime.toCharSequence(thisObj));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[152]++;
                }
                id = -id;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[153]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[154]++;
                continue again;
              }

              case ConstructorId_fromCharCode:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[66]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[155]++;
                int N = args.length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[156]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((N < 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[67]++;
                    return "";
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[68]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[157]++;
                StringBuffer sb = new StringBuffer(N);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[158]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[7]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[8]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[9]++;
}
                    sb.append(ScriptRuntime.toUint16(args[i]));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[159]++;
                }
                return sb.toString();
              }

              case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[69]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[160]++;
                CharSequence s = (args.length >= 1)
                    ? ScriptRuntime.toCharSequence(args[0]) : "";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[161]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((thisObj == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[70]++;
                    // new String(val) creates a new String object.
                    return new NativeString(s);

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[71]++;}
                // String(val) converts val to a string value.
                return s instanceof String ? s : s.toString();
              }

              case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[72]++;
              case Id_valueOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[73]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[162]++;
                // ECMA 15.5.4.2: 'the toString function is not generic.
                CharSequence cs = realThis(thisObj, f).string;
                return cs instanceof String ? cs : cs.toString();

              case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[74]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[163]++;
                CharSequence s = realThis(thisObj, f).string;
                return "(new String(\""+ScriptRuntime.escapeString(s.toString())+"\"))";
              }

              case Id_charAt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[75]++;
              case Id_charCodeAt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[76]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[164]++;
                 // See ECMA 15.5.4.[4,5]
                CharSequence target = ScriptRuntime.toCharSequence(thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[165]++;
                double pos = ScriptRuntime.toInteger(args, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[166]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((pos < 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((pos >= target.length()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[77]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[167]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((id == Id_charAt) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[79]++; return "";
}
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[80]++; return ScriptRuntime.NaNobj;
}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[78]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[168]++;
                char c = target.charAt((int)pos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[169]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((id == Id_charAt) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[81]++; return String.valueOf(c);
}
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[82]++; return ScriptRuntime.wrapInt(c);
}
              }

              case Id_indexOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[83]++;
                return ScriptRuntime.wrapInt(js_indexOf(
                    ScriptRuntime.toString(thisObj), args));

              case Id_lastIndexOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[84]++;
                return ScriptRuntime.wrapInt(js_lastIndexOf(
                    ScriptRuntime.toString(thisObj), args));

              case Id_split:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[85]++;
                return ScriptRuntime.checkRegExpProxy(cx).
                  js_split(cx, scope, ScriptRuntime.toString(thisObj),
                        args);

              case Id_substring:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[86]++;
                return js_substring(cx, ScriptRuntime.toCharSequence(thisObj), args);

              case Id_toLowerCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[87]++;
                // See ECMA 15.5.4.11
                return ScriptRuntime.toString(thisObj).toLowerCase(
                         ScriptRuntime.ROOT_LOCALE);

              case Id_toUpperCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[88]++;
                // See ECMA 15.5.4.12
                return ScriptRuntime.toString(thisObj).toUpperCase(
                         ScriptRuntime.ROOT_LOCALE);

              case Id_substr:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[89]++;
                return js_substr(ScriptRuntime.toCharSequence(thisObj), args);

              case Id_concat:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[90]++;
                return js_concat(ScriptRuntime.toString(thisObj), args);

              case Id_slice:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[91]++;
                return js_slice(ScriptRuntime.toCharSequence(thisObj), args);

              case Id_bold:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[92]++;
                return tagify(thisObj, "b", null, null);

              case Id_italics:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[93]++;
                return tagify(thisObj, "i", null, null);

              case Id_fixed:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[94]++;
                return tagify(thisObj, "tt", null, null);

              case Id_strike:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[95]++;
                return tagify(thisObj, "strike", null, null);

              case Id_small:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[96]++;
                return tagify(thisObj, "small", null, null);

              case Id_big:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[97]++;
                return tagify(thisObj, "big", null, null);

              case Id_blink:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[98]++;
                return tagify(thisObj, "blink", null, null);

              case Id_sup:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[99]++;
                return tagify(thisObj, "sup", null, null);

              case Id_sub:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[100]++;
                return tagify(thisObj, "sub", null, null);

              case Id_fontsize:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[101]++;
                return tagify(thisObj, "font", "size", args);

              case Id_fontcolor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[102]++;
                return tagify(thisObj, "font", "color", args);

              case Id_link:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[103]++;
                return tagify(thisObj, "a", "href", args);

              case Id_anchor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[104]++;
                return tagify(thisObj, "a", "name", args);

              case Id_equals:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[105]++;
              case Id_equalsIgnoreCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[106]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[170]++;
                String s1 = ScriptRuntime.toString(thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[171]++;
                String s2 = ScriptRuntime.toString(args, 0);
                return ScriptRuntime.wrapBoolean(
                    (id == Id_equals) ? s1.equals(s2)
                                      : s1.equalsIgnoreCase(s2));
              }

              case Id_match:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[107]++;
              case Id_search:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[108]++;
              case Id_replace:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[109]++;
                {
                    int actionType;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[172]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((id == Id_match) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[110]++;
                        actionType = RegExpProxy.RA_MATCH;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[173]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[111]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[174]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((id == Id_search) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[112]++;
                        actionType = RegExpProxy.RA_SEARCH;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[175]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[113]++;
                        actionType = RegExpProxy.RA_REPLACE;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[176]++;
                    }
}
                    return ScriptRuntime.checkRegExpProxy(cx).
                        action(cx, scope, thisObj, args, actionType);
                }
                // ECMA-262 1 5.5.4.9
              case Id_localeCompare:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[114]++;
                {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[177]++;
                    // For now, create and configure a collator instance. I can't
                    // actually imagine that this'd be slower than caching them
                    // a la ClassCache, so we aren't trying to outsmart ourselves
                    // with a caching mechanism for now.
                    Collator collator = Collator.getInstance(cx.getLocale());
                    collator.setStrength(Collator.IDENTICAL);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[178]++;
                    collator.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[179]++;
                    return ScriptRuntime.wrapNumber(collator.compare(
                            ScriptRuntime.toString(thisObj),
                            ScriptRuntime.toString(args, 0)));
                }
              case Id_toLocaleLowerCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[115]++;
                {
                    return ScriptRuntime.toString(thisObj)
                            .toLowerCase(cx.getLocale());
                }
              case Id_toLocaleUpperCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[116]++;
                {
                    return ScriptRuntime.toString(thisObj)
                            .toUpperCase(cx.getLocale());
                }
              case Id_trim:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[117]++;
                {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[180]++;
                    String str = ScriptRuntime.toString(thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[181]++;
                    char[] chars = str.toCharArray();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[182]++;

                    int start = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[183]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[10]++;


int CodeCoverConditionCoverageHelper_C16;
                    while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((start < chars.length) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSWhitespaceOrLineTerminator(chars[start])) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[10]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[11]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[12]++;
}
                      start++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[184]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[185]++;
                    int end = chars.length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[186]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[13]++;


int CodeCoverConditionCoverageHelper_C17;
                    while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((end > start) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSWhitespaceOrLineTerminator(chars[end-1])) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[13]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[14]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[15]++;
}
                      end--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[187]++;
                    }

                    return str.substring(start, end);
                } default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[118]++;
            }
            throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    private static NativeString realThis(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[188]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeString) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[119]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[120]++;}
        return (NativeString)thisObj;
    }

    /*
     * HTML composition aids.
     */
    private static String tagify(Object thisObj, String tag,
                                 String attribute, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[189]++;
        String str = ScriptRuntime.toString(thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[190]++;
        StringBuffer result = new StringBuffer();
        result.append('<');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[191]++;
        result.append(tag);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[192]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[193]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((attribute != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[121]++;
            result.append(' ');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[194]++;
            result.append(attribute);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[195]++;
            result.append("=\"");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[196]++;
            result.append(ScriptRuntime.toString(args, 0));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[197]++;
            result.append('"');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[198]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[122]++;}
        result.append('>');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[199]++;
        result.append(str);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[200]++;
        result.append("</");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[201]++;
        result.append(tag);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[202]++;
        result.append('>');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[203]++;
        return result.toString();
    }

    public CharSequence toCharSequence() {
        return string;
    }

    @Override
    public String toString() {
        return string instanceof String ? (String)string : string.toString();
    }

    /* Make array-style property lookup work for strings.
     * XXX is this ECMA?  A version check is probably needed. In js too.
     */
    @Override
    public Object get(int index, Scriptable start) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[204]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((index < string.length()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[123]++;
            return String.valueOf(string.charAt(index));

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[124]++;}
        return super.get(index, start);
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[205]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((index < string.length()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[125]++;
            return;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[126]++;}
        super.put(index, start, value);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[206]++;
    }

    /*
     *
     * See ECMA 15.5.4.6.  Uses Java String.indexOf()
     * OPT to add - BMH searching from jsstr.c.
     */
    private static int js_indexOf(String target, Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[207]++;
        String search = ScriptRuntime.toString(args, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[208]++;
        double begin = ScriptRuntime.toInteger(args, 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[209]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((begin > target.length()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[127]++;
            return -1;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[128]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[210]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((begin < 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[129]++;
                begin = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[211]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[130]++;}
            return target.indexOf(search, (int)begin);
        }
    }

    /*
     *
     * See ECMA 15.5.4.7
     *
     */
    private static int js_lastIndexOf(String target, Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[212]++;
        String search = ScriptRuntime.toString(args, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[213]++;
        double end = ScriptRuntime.toNumber(args, 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[214]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((end != end) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((end > target.length()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[131]++;
            end = target.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[215]++;
}
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[132]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[216]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[133]++;
            end = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[217]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[134]++;}
}

        return target.lastIndexOf(search, (int)end);
    }


    /*
     * See ECMA 15.5.4.15
     */
    private static CharSequence js_substring(Context cx, CharSequence target,
                                       Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[218]++;
        int length = target.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[219]++;
        double start = ScriptRuntime.toInteger(args, 0);
        double end;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[220]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[135]++;
            start = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[221]++;
}
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[136]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[222]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((start > length) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[137]++;
            start = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[223]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[138]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[224]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((args.length <= 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((args[1] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[139]++;
            end = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[225]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[140]++;
            end = ScriptRuntime.toInteger(args[1]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[226]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[227]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[141]++;
                end = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[228]++;
}
            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[142]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[229]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((end > length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[143]++;
                end = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[230]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[144]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[231]++;
int CodeCoverConditionCoverageHelper_C31;

            // swap if end < start
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((end < start) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[145]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[232]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() != Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[147]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[233]++;
                    double temp = start;
                    start = end;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[234]++;
                    end = temp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[235]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[148]++;
                    // Emulate old JDK1.0 java.lang.String.substring()
                    end = start;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[236]++;
                }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[146]++;}
        }
        return target.subSequence((int)start, (int)end);
    }

    int getLength() {
        return string.length();
    }

    /*
     * Non-ECMA methods.
     */
    private static CharSequence js_substr(CharSequence target, Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[237]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[149]++;
            return target;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[150]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[238]++;

        double begin = ScriptRuntime.toInteger(args[0]);
        double end;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[239]++;
        int length = target.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[240]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((begin < 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[151]++;
            begin += length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[241]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[242]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((begin < 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[153]++;
                begin = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[243]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[154]++;}

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[152]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[244]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((begin > length) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[155]++;
            begin = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[245]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[156]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[246]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[157]++;
            end = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[247]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[158]++;
            end = ScriptRuntime.toInteger(args[1]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[248]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[249]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[159]++;
                end = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[250]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[160]++;}
            end += begin;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[251]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[252]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((end > length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[161]++;
                end = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[253]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[162]++;}
        }

        return target.subSequence((int)begin, (int)end);
    }

    /*
     * Python-esque sequence operations.
     */
    private static String js_concat(String target, Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[254]++;
        int N = args.length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[255]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[163]++; return target;
 }
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[164]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[256]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((N == 1) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[165]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[257]++;
            String arg = ScriptRuntime.toString(args[0]);
            return target.concat(arg);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[166]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[258]++;

        // Find total capacity for the final string to avoid unnecessary
        // re-allocations in StringBuffer
        int size = target.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[259]++;
        String[] argsAsStrings = new String[N];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[260]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[16]++;


int CodeCoverConditionCoverageHelper_C42;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[16]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[17]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[18]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[261]++;
            String s = ScriptRuntime.toString(args[i]);
            argsAsStrings[i] = s;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[262]++;
            size += s.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[263]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[264]++;

        StringBuffer result = new StringBuffer(size);
        result.append(target);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[265]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[266]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[19]++;


int CodeCoverConditionCoverageHelper_C43;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[19]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[20]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.loops[21]++;
}
            result.append(argsAsStrings[i]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[267]++;
        }
        return result.toString();
    }

    private static CharSequence js_slice(CharSequence target, Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[268]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((args.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[167]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[269]++;
            double begin = ScriptRuntime.toInteger(args[0]);
            double end;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[270]++;
            int length = target.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[271]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((begin < 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[169]++;
                begin += length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[272]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[273]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((begin < 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[171]++;
                    begin = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[274]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[172]++;}

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[170]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[275]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((begin > length) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[173]++;
                begin = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[276]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[174]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[277]++;
int CodeCoverConditionCoverageHelper_C48;

            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[175]++;
                end = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[278]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[176]++;
                end = ScriptRuntime.toInteger(args[1]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[279]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[280]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[177]++;
                    end += length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[281]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[282]++;
int CodeCoverConditionCoverageHelper_C50;
                    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[179]++;
                        end = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[283]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[180]++;}

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[178]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[284]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((end > length) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[181]++;
                    end = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[285]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[182]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[286]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((end < begin) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[183]++;
                    end = begin;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[287]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[184]++;}
            }
            return target.subSequence((int) begin, (int) end);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[168]++;}
        return target;
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2009-07-23 07:32:39 EST
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[288]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[289]++; String X = null; int c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[290]++;
            L: switch (s.length()) {
            case 3:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[185]++; c=s.charAt(2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[291]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[292]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((c=='b') && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[186]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[293]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((s.charAt(0)=='s') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='u') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[188]++;id=Id_sub;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[294]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[295]++; break L0;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[189]++;}
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[187]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[296]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[190]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[297]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((s.charAt(0)=='b') && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='i') && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[192]++;id=Id_big;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[298]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[299]++; break L0;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[193]++;}
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[191]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[300]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[194]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[301]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((s.charAt(0)=='s') && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='u') && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[196]++;id=Id_sup;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[302]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[303]++; break L0;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[197]++;}
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[195]++;}
}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[304]++;
                break L;
            case 4:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[198]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[305]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[306]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((c=='b') && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[199]++; X="bold";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[307]++;id=Id_bold;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[308]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[200]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[309]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((c=='l') && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[201]++; X="link";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[310]++;id=Id_link;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[311]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[202]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[312]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[203]++; X="trim";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[313]++;id=Id_trim;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[314]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[204]++;}
}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[315]++;
                break L;
            case 5:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[205]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[316]++; switch (s.charAt(4)) {
                case 'd':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[206]++; X="fixed";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[317]++;id=Id_fixed;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[318]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[319]++; break L;
                case 'e':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[207]++; X="slice";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[320]++;id=Id_slice;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[321]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[322]++; break L;
                case 'h':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[208]++; X="match";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[323]++;id=Id_match;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[324]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[325]++; break L;
                case 'k':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[209]++; X="blink";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[326]++;id=Id_blink;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[327]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[328]++; break L;
                case 'l':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[210]++; X="small";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[329]++;id=Id_small;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[330]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[331]++; break L;
                case 't':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[211]++; X="split";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[332]++;id=Id_split;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[333]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[334]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[212]++;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[335]++; break L;
            case 6:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[213]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[336]++; switch (s.charAt(1)) {
                case 'e':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[214]++; X="search";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[337]++;id=Id_search;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[338]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[339]++; break L;
                case 'h':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[215]++; X="charAt";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[340]++;id=Id_charAt;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[341]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[342]++; break L;
                case 'n':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[216]++; X="anchor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[343]++;id=Id_anchor;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[344]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[345]++; break L;
                case 'o':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[217]++; X="concat";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[346]++;id=Id_concat;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[347]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[348]++; break L;
                case 'q':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[218]++; X="equals";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[349]++;id=Id_equals;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[350]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[351]++; break L;
                case 't':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[219]++; X="strike";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[352]++;id=Id_strike;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[353]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[354]++; break L;
                case 'u':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[220]++; X="substr";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[355]++;id=Id_substr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[356]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[357]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[221]++;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[358]++; break L;
            case 7:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[222]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[359]++; switch (s.charAt(1)) {
                case 'a':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[223]++; X="valueOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[360]++;id=Id_valueOf;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[361]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[362]++; break L;
                case 'e':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[224]++; X="replace";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[363]++;id=Id_replace;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[364]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[365]++; break L;
                case 'n':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[225]++; X="indexOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[366]++;id=Id_indexOf;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[367]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[368]++; break L;
                case 't':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[226]++; X="italics";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[369]++;id=Id_italics;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[370]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[371]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[227]++;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[372]++; break L;
            case 8:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[228]++; c=s.charAt(4);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[373]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[374]++;
int CodeCoverConditionCoverageHelper_C62;
                if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[229]++; X="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[375]++;id=Id_toString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[376]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[230]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[377]++;
int CodeCoverConditionCoverageHelper_C63; if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[231]++; X="fontsize";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[378]++;id=Id_fontsize;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[379]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[232]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[380]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((c=='u') && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[233]++; X="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[381]++;id=Id_toSource;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[382]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[234]++;}
}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[383]++;
                break L;
            case 9:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[235]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[384]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[385]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[236]++; X="fontcolor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[386]++;id=Id_fontcolor;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[387]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[237]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[388]++;
int CodeCoverConditionCoverageHelper_C66; if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[238]++; X="substring";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[389]++;id=Id_substring;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[390]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[239]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[391]++;
                break L;
            case 10:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[240]++; X="charCodeAt";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[392]++;id=Id_charCodeAt;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[393]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[394]++; break L;
            case 11:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[241]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[395]++; switch (s.charAt(2)) {
                case 'L':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[242]++; X="toLowerCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[396]++;id=Id_toLowerCase;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[397]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[398]++; break L;
                case 'U':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[243]++; X="toUpperCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[399]++;id=Id_toUpperCase;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[400]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[401]++; break L;
                case 'n':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[244]++; X="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[402]++;id=Id_constructor;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[403]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[404]++; break L;
                case 's':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[245]++; X="lastIndexOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[405]++;id=Id_lastIndexOf;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[406]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[407]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[246]++;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[408]++; break L;
            case 13:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[247]++; X="localeCompare";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[409]++;id=Id_localeCompare;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[410]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[411]++; break L;
            case 16:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[248]++; X="equalsIgnoreCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[412]++;id=Id_equalsIgnoreCase;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[413]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[414]++; break L;
            case 17:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[249]++; c=s.charAt(8);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[415]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[416]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((c=='L') && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[250]++; X="toLocaleLowerCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[417]++;id=Id_toLocaleLowerCase;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[418]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[251]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[419]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((c=='U') && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[252]++; X="toLocaleUpperCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[420]++;id=Id_toLocaleUpperCase;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[421]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[253]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[422]++;
                break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[254]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[423]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[255]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[424]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.branches[256]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[425]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        ConstructorId_fromCharCode   = -1,

        Id_constructor               = 1,
        Id_toString                  = 2,
        Id_toSource                  = 3,
        Id_valueOf                   = 4,
        Id_charAt                    = 5,
        Id_charCodeAt                = 6,
        Id_indexOf                   = 7,
        Id_lastIndexOf               = 8,
        Id_split                     = 9,
        Id_substring                 = 10,
        Id_toLowerCase               = 11,
        Id_toUpperCase               = 12,
        Id_substr                    = 13,
        Id_concat                    = 14,
        Id_slice                     = 15,
        Id_bold                      = 16,
        Id_italics                   = 17,
        Id_fixed                     = 18,
        Id_strike                    = 19,
        Id_small                     = 20,
        Id_big                       = 21,
        Id_blink                     = 22,
        Id_sup                       = 23,
        Id_sub                       = 24,
        Id_fontsize                  = 25,
        Id_fontcolor                 = 26,
        Id_link                      = 27,
        Id_anchor                    = 28,
        Id_equals                    = 29,
        Id_equalsIgnoreCase          = 30,
        Id_match                     = 31,
        Id_search                    = 32,
        Id_replace                   = 33,
        Id_localeCompare             = 34,
        Id_toLocaleLowerCase         = 35,
        Id_toLocaleUpperCase         = 36,
        Id_trim                      = 37,
        MAX_PROTOTYPE_ID             = Id_trim;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[426]++;
  }

// #/string_id_map#

    private static final int
        ConstructorId_charAt         = -Id_charAt,
        ConstructorId_charCodeAt     = -Id_charCodeAt,
        ConstructorId_indexOf        = -Id_indexOf,
        ConstructorId_lastIndexOf    = -Id_lastIndexOf,
        ConstructorId_split          = -Id_split,
        ConstructorId_substring      = -Id_substring,
        ConstructorId_toLowerCase    = -Id_toLowerCase,
        ConstructorId_toUpperCase    = -Id_toUpperCase,
        ConstructorId_substr         = -Id_substr,
        ConstructorId_concat         = -Id_concat,
        ConstructorId_slice          = -Id_slice,
        ConstructorId_equalsIgnoreCase = -Id_equalsIgnoreCase,
        ConstructorId_match          = -Id_match,
        ConstructorId_search         = -Id_search,
        ConstructorId_replace        = -Id_replace,
        ConstructorId_localeCompare  = -Id_localeCompare,
        ConstructorId_toLocaleLowerCase = -Id_toLocaleLowerCase;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d.statements[427]++;
  }

    private CharSequence string;
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d ());
  }
    public static long[] statements = new long[428];
    public static long[] branches = new long[257];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[70];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeString.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,0,1,1,1,1,1,2,1,1,1,1,2,2,1,1,2,2,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 69; i++) {
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

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyrij1x6upnxs019d () {
    super("org.mozilla.javascript.RHINO-SRC-NativeString.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 427; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 256; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 69; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeString.java");
      for (int i = 1; i <= 427; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 256; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 69; i++) {
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

