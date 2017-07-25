/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.TimeZone;
import java.util.SimpleTimeZone;

/**
 * This class implements the Date native object.
 * See ECMA 15.9.
 */
final class NativeDate extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.ping();
  }

    static final long serialVersionUID = -8307438915861678966L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[1]++;
  }

    private static final Object DATE_TAG = "Date";
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[2]++;
  }

    private static final String js_NaN_date_str = "Invalid Date";
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[3]++;
  }

    private static final DateFormat isoFormat;
    static {
      isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[4]++;
      isoFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[5]++;
      isoFormat.setLenient(false);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[6]++;
    }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[7]++;
        NativeDate obj = new NativeDate();
        // Set the value of the prototype Date to NaN ('invalid date');
        obj.date = ScriptRuntime.NaN;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[8]++;
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[9]++;
    }

    private NativeDate()
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((thisTimeZone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[1]++;
            // j.u.TimeZone is synchronized, so setting class statics from it
            // should be OK.
            thisTimeZone = TimeZone.getDefault();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[11]++;
            LocalTZA = thisTimeZone.getRawOffset();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[12]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[2]++;}
    }

    @Override
    public String getClassName()
    {
        return "Date";
    }

    @Override
    public Object getDefaultValue(Class<?> typeHint)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((typeHint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[3]++;
            typeHint = ScriptRuntime.StringClass;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[14]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[4]++;}
        return super.getDefaultValue(typeHint);
    }

    double getJSTimeValue()
    {
        return date;
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor)
    {
        addIdFunctionProperty(ctor, DATE_TAG, ConstructorId_now,
                              "now", 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[15]++;
        addIdFunctionProperty(ctor, DATE_TAG, ConstructorId_parse,
                              "parse", 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[16]++;
        addIdFunctionProperty(ctor, DATE_TAG, ConstructorId_UTC,
                              "UTC", 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[17]++;
        super.fillConstructorProperties(ctor);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[18]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[19]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[5]++;        arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[20]++; s="constructor";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[21]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[22]++;        break;
          case Id_toString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[6]++;           arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[23]++; s="toString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[24]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[25]++;           break;
          case Id_toTimeString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[7]++;       arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[26]++; s="toTimeString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[27]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[28]++;       break;
          case Id_toDateString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[8]++;       arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[29]++; s="toDateString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[30]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[31]++;       break;
          case Id_toLocaleString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[9]++;     arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[32]++; s="toLocaleString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[33]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[34]++;     break;
          case Id_toLocaleTimeString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[10]++; arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[35]++; s="toLocaleTimeString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[36]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[37]++; break;
          case Id_toLocaleDateString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[11]++; arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[38]++; s="toLocaleDateString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[39]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[40]++; break;
          case Id_toUTCString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[12]++;        arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[41]++; s="toUTCString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[42]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[43]++;        break;
          case Id_toSource:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[13]++;           arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[44]++; s="toSource";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[45]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[46]++;           break;
          case Id_valueOf:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[14]++;            arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[47]++; s="valueOf";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[48]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[49]++;            break;
          case Id_getTime:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[15]++;            arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[50]++; s="getTime";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[51]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[52]++;            break;
          case Id_getYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[16]++;            arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[53]++; s="getYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[54]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[55]++;            break;
          case Id_getFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[17]++;        arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[56]++; s="getFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[57]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[58]++;        break;
          case Id_getUTCFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[18]++;     arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[59]++; s="getUTCFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[60]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[61]++;     break;
          case Id_getMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[19]++;           arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[62]++; s="getMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[63]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[64]++;           break;
          case Id_getUTCMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[20]++;        arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[65]++; s="getUTCMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[66]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[67]++;        break;
          case Id_getDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[21]++;            arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[68]++; s="getDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[69]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[70]++;            break;
          case Id_getUTCDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[22]++;         arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[71]++; s="getUTCDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[72]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[73]++;         break;
          case Id_getDay:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[23]++;             arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[74]++; s="getDay";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[75]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[76]++;             break;
          case Id_getUTCDay:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[24]++;          arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[77]++; s="getUTCDay";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[78]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[79]++;          break;
          case Id_getHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[25]++;           arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[80]++; s="getHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[81]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[82]++;           break;
          case Id_getUTCHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[26]++;        arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[83]++; s="getUTCHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[84]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[85]++;        break;
          case Id_getMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[27]++;         arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[86]++; s="getMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[87]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[88]++;         break;
          case Id_getUTCMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[28]++;      arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[89]++; s="getUTCMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[90]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[91]++;      break;
          case Id_getSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[29]++;         arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[92]++; s="getSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[93]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[94]++;         break;
          case Id_getUTCSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[30]++;      arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[95]++; s="getUTCSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[96]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[97]++;      break;
          case Id_getMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[31]++;    arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[98]++; s="getMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[99]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[100]++;    break;
          case Id_getUTCMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[32]++; arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[101]++; s="getUTCMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[102]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[103]++; break;
          case Id_getTimezoneOffset:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[33]++;  arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[104]++; s="getTimezoneOffset";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[105]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[106]++;  break;
          case Id_setTime:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[34]++;            arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[107]++; s="setTime";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[108]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[109]++;            break;
          case Id_setMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[35]++;    arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[110]++; s="setMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[111]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[112]++;    break;
          case Id_setUTCMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[36]++; arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[113]++; s="setUTCMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[114]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[115]++; break;
          case Id_setSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[37]++;         arity=2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[116]++; s="setSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[117]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[118]++;         break;
          case Id_setUTCSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[38]++;      arity=2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[119]++; s="setUTCSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[120]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[121]++;      break;
          case Id_setMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[39]++;         arity=3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[122]++; s="setMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[123]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[124]++;         break;
          case Id_setUTCMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[40]++;      arity=3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[125]++; s="setUTCMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[126]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[127]++;      break;
          case Id_setHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[41]++;           arity=4;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[128]++; s="setHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[129]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[130]++;           break;
          case Id_setUTCHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[42]++;        arity=4;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[131]++; s="setUTCHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[132]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[133]++;        break;
          case Id_setDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[43]++;            arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[134]++; s="setDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[135]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[136]++;            break;
          case Id_setUTCDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[44]++;         arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[137]++; s="setUTCDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[138]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[139]++;         break;
          case Id_setMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[45]++;           arity=2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[140]++; s="setMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[141]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[142]++;           break;
          case Id_setUTCMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[46]++;        arity=2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[143]++; s="setUTCMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[144]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[145]++;        break;
          case Id_setFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[47]++;        arity=3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[146]++; s="setFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[147]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[148]++;        break;
          case Id_setUTCFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[48]++;     arity=3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[149]++; s="setUTCFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[150]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[151]++;     break;
          case Id_setYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[49]++;            arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[152]++; s="setYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[153]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[154]++;            break;
          case Id_toISOString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[50]++;        arity=0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[155]++; s="toISOString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[156]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[157]++;        break;
          case Id_toJSON:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[51]++;             arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[158]++; s="toJSON";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[159]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[160]++;             break;
          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[52]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(DATE_TAG, id, s, arity);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[161]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[162]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((f.hasTag(DATE_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[53]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[54]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[163]++;
        int id = f.methodId();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[164]++;
        switch (id) {
          case ConstructorId_now:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[55]++;
            return ScriptRuntime.wrapNumber(now());

          case ConstructorId_parse:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[56]++;
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[165]++;
                String dataStr = ScriptRuntime.toString(args, 0);
                return ScriptRuntime.wrapNumber(date_parseString(dataStr));
            }

          case ConstructorId_UTC:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[57]++;
            return ScriptRuntime.wrapNumber(jsStaticFunction_UTC(args));

          case Id_constructor:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[58]++;
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[166]++;
int CodeCoverConditionCoverageHelper_C4;
                // if called as a function, just return a string
                // representing the current time.
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((thisObj != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[59]++;
                    return date_format(now(), Id_toString);
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[60]++;}
                return jsConstructor(args);
            }

          case Id_toJSON:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[61]++;
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[167]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeDate) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[62]++;
                    return ((NativeDate) thisObj).toISOString();

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[63]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[168]++;

                final String toISOString = "toISOString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[169]++;

                Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[170]++;
                Object tv = ScriptRuntime.toPrimitive(o, ScriptRuntime.NumberClass);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[171]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((tv instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[64]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[172]++;
                    double d = ((Number) tv).doubleValue();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[173]++;
int CodeCoverConditionCoverageHelper_C7;
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((Double.isInfinite(d)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[66]++;
                        return null;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[67]++;}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[65]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[174]++;
                Object toISO = o.get(toISOString, o);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[175]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((toISO == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[68]++;
                    throw ScriptRuntime.typeError2("msg.function.not.found.in",
                            toISOString,
                            ScriptRuntime.toString(o));

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[69]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[176]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((toISO instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false) ) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[70]++;
                    throw ScriptRuntime.typeError3("msg.isnt.function.in",
                            toISOString,
                            ScriptRuntime.toString(o),
                            ScriptRuntime.toString(toISO));

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[71]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[177]++;
                Object result = ((Callable) toISO).call(cx, scope, o,
                            ScriptRuntime.emptyArgs);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[178]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isPrimitive(result)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false) ) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[72]++;
                    throw ScriptRuntime.typeError1("msg.toisostring.must.return.primitive",
                            ScriptRuntime.toString(result));

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[73]++;}
                return result;
            } default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[74]++;

        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[179]++;
int CodeCoverConditionCoverageHelper_C11;

        // The rest of Date.prototype methods require thisObj to be Date

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeDate) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[75]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[76]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[180]++;
        NativeDate realThis = (NativeDate)thisObj;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[181]++;
        double t = realThis.date;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[182]++;

        switch (id) {

          case Id_toString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[77]++;
          case Id_toTimeString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[78]++;
          case Id_toDateString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[79]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[183]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[80]++;
                return date_format(t, id);

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[81]++;}
            return js_NaN_date_str;

          case Id_toLocaleString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[82]++;
          case Id_toLocaleTimeString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[83]++;
          case Id_toLocaleDateString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[84]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[184]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[85]++;
                return toLocale_helper(t, id);

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[86]++;}
            return js_NaN_date_str;

          case Id_toUTCString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[87]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[185]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[88]++;
                return js_toUTCString(t);

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[89]++;}
            return js_NaN_date_str;

          case Id_toSource:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[90]++;
            return "(new Date("+ScriptRuntime.toString(t)+"))";

          case Id_valueOf:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[91]++;
          case Id_getTime:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[92]++;
            return ScriptRuntime.wrapNumber(t);

          case Id_getYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[93]++;
          case Id_getFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[94]++;
          case Id_getUTCFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[95]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[186]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[96]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[187]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((id != Id_getUTCFullYear) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[98]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[188]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[99]++;}
                t = YearFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[189]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[190]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((id == Id_getYear) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[100]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[191]++;
int CodeCoverConditionCoverageHelper_C18;
                    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_NON_ECMA_GET_YEAR)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[102]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[192]++;
int CodeCoverConditionCoverageHelper_C19;
                        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((1900 <= t) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((t < 2000) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[104]++;
                            t -= 1900;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[193]++;

                        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[105]++;}

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[103]++;
                        t -= 1900;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[194]++;
                    }

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[101]++;}

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[97]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[106]++;
          case Id_getUTCMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[107]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[195]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[108]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[196]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((id == Id_getMonth) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[110]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[197]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[111]++;}
                t = MonthFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[198]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[109]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[112]++;
          case Id_getUTCDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[113]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[199]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[114]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[200]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((id == Id_getDate) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[116]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[201]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[117]++;}
                t = DateFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[202]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[115]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getDay:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[118]++;
          case Id_getUTCDay:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[119]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[203]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[120]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[204]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((id == Id_getDay) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[122]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[205]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[123]++;}
                t = WeekDay(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[206]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[121]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[124]++;
          case Id_getUTCHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[125]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[207]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[126]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[208]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((id == Id_getHours) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[128]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[209]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[129]++;}
                t = HourFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[210]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[127]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[130]++;
          case Id_getUTCMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[131]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[211]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[132]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[212]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((id == Id_getMinutes) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[134]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[213]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[135]++;}
                t = MinFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[214]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[133]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[136]++;
          case Id_getUTCSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[137]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[215]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[138]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[216]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((id == Id_getSeconds) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[140]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[217]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[141]++;}
                t = SecFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[218]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[139]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[142]++;
          case Id_getUTCMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[143]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[219]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[144]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[220]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((id == Id_getMilliseconds) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[146]++; t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[221]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[147]++;}
                t = msFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[222]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[145]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_getTimezoneOffset:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[148]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[223]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((t == t) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[149]++;
                t = (t - LocalTime(t)) / msPerMinute;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[224]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[150]++;}
            return ScriptRuntime.wrapNumber(t);

          case Id_setTime:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[151]++;
            t = TimeClip(ScriptRuntime.toNumber(args, 0));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[225]++;
            realThis.date = t;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[226]++;
            return ScriptRuntime.wrapNumber(t);

          case Id_setMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[152]++;
          case Id_setUTCMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[153]++;
          case Id_setSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[154]++;
          case Id_setUTCSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[155]++;
          case Id_setMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[156]++;
          case Id_setUTCMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[157]++;
          case Id_setHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[158]++;
          case Id_setUTCHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[159]++;
            t = makeTime(t, args, id);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[227]++;
            realThis.date = t;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[228]++;
            return ScriptRuntime.wrapNumber(t);

          case Id_setDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[160]++;
          case Id_setUTCDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[161]++;
          case Id_setMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[162]++;
          case Id_setUTCMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[163]++;
          case Id_setFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[164]++;
          case Id_setUTCFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[165]++;
            t = makeDate(t, args, id);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[229]++;
            realThis.date = t;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[230]++;
            return ScriptRuntime.wrapNumber(t);

          case Id_setYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[166]++;
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[231]++;
                double year = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[232]++;
int CodeCoverConditionCoverageHelper_C35;

                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((year != year) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((Double.isInfinite(year)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[167]++;
                    t = ScriptRuntime.NaN;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[233]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[168]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[234]++;
int CodeCoverConditionCoverageHelper_C36;
                    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((t != t) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[169]++;
                        t = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[235]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[170]++;
                        t = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[236]++;
                    }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[237]++;
int CodeCoverConditionCoverageHelper_C37;

                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((year >= 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((year <= 99) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[171]++;
                        year += 1900;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[238]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[172]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[239]++;

                    double day = MakeDay(year, MonthFromTime(t),
                                         DateFromTime(t));
                    t = MakeDate(day, TimeWithinDay(t));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[240]++;
                    t = internalUTC(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[241]++;
                    t = TimeClip(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[242]++;
                }
            }
            realThis.date = t;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[243]++;
            return ScriptRuntime.wrapNumber(t);

          case Id_toISOString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[173]++;
            return realThis.toISOString();

          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[174]++; throw new IllegalArgumentException(String.valueOf(id));
        }

    }

    private String toISOString() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[244]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((date == date) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[175]++;
            synchronized (isoFormat) {
                return isoFormat.format(new Date((long) date));
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[176]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[245]++;
        String msg = ScriptRuntime.getMessage0("msg.invalid.date");
        throw ScriptRuntime.constructError("RangeError", msg);
    }

    /* ECMA helper functions */

    private static final double HalfTimeDomain = 8.64e15;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[246]++;
  }
    private static final double HoursPerDay    = 24.0;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[247]++;
  }
    private static final double MinutesPerHour = 60.0;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[248]++;
  }
    private static final double SecondsPerMinute = 60.0;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[249]++;
  }
    private static final double msPerSecond    = 1000.0;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[250]++;
  }
    private static final double MinutesPerDay  = (HoursPerDay * MinutesPerHour);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[251]++;
  }
    private static final double SecondsPerDay  = (MinutesPerDay * SecondsPerMinute);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[252]++;
  }
    private static final double SecondsPerHour = (MinutesPerHour * SecondsPerMinute);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[253]++;
  }
    private static final double msPerDay       = (SecondsPerDay * msPerSecond);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[254]++;
  }
    private static final double msPerHour      = (SecondsPerHour * msPerSecond);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[255]++;
  }
    private static final double msPerMinute    = (SecondsPerMinute * msPerSecond);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[256]++;
  }

    private static double Day(double t)
    {
        return Math.floor(t / msPerDay);
    }

    private static double TimeWithinDay(double t)
    {
        double result;
        result = t % msPerDay;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[257]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[258]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[177]++;
            result += msPerDay;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[259]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[178]++;}
        return result;
    }

    private static boolean IsLeapYear(int year)
    {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /* math here has to be f.p, because we need
     *  floor((1968 - 1969) / 4) == -1
     */
    private static double DayFromYear(double y)
    {
        return ((365 * ((y)-1970) + Math.floor(((y)-1969)/4.0)
                 - Math.floor(((y)-1901)/100.0) + Math.floor(((y)-1601)/400.0)));
    }

    private static double TimeFromYear(double y)
    {
        return DayFromYear(y) * msPerDay;
    }

    private static int YearFromTime(double t)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[260]++;
        int lo = (int) Math.floor((t / msPerDay) / 366) + 1970;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[261]++;
        int hi = (int) Math.floor((t / msPerDay) / 365) + 1970;
        int mid;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[262]++;
int CodeCoverConditionCoverageHelper_C40;

        /* above doesn't work for negative dates... */
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((hi < lo) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[179]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[263]++;
            int temp = lo;
            lo = hi;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[264]++;
            hi = temp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[265]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[180]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[266]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[1]++;


int CodeCoverConditionCoverageHelper_C41;

        /* Use a simple binary search algorithm to find the right
           year.  This seems like brute force... but the computation
           of hi and lo years above lands within one year of the
           correct answer for years within a thousand years of
           1970; the loop below only requires six iterations
           for year 270000. */
        while ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((hi > lo) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[3]++;
}
            mid = (hi + lo) / 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[267]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[268]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((TimeFromYear(mid) > t) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[181]++;
                hi = mid - 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[269]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[182]++;
                lo = mid + 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[270]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[271]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((TimeFromYear(lo) > t) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[183]++;
                    return mid;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[184]++;}
            }
        }
        return lo;
    }

    private static double DayFromMonth(int m, int year)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[272]++;
        int day = m * 30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[273]++;
int CodeCoverConditionCoverageHelper_C44;

        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((m >= 7) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[185]++; day += m / 2 - 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[274]++;
 }
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[186]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[275]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((m >= 2) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[187]++; day += (m - 1) / 2 - 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[276]++;
 }
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[188]++; day += m;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[277]++; }
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[278]++;
int CodeCoverConditionCoverageHelper_C46;

        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((m >= 2) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((IsLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[189]++; ++day;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[279]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[190]++;}

        return day;
    }

    private static int MonthFromTime(double t)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[280]++;
        int year = YearFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[281]++;
        int d = (int)(Day(t) - DayFromYear(year));

        d -= 31 + 28;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[282]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[283]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((d < 0) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[191]++;
            return (d < -28) ? 0 : 1;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[192]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[284]++;
int CodeCoverConditionCoverageHelper_C48;

        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((IsLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[193]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[285]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((d == 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[195]++;
                return 1;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[196]++;} // 29 February
            --d;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[286]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[194]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[287]++;

        // d: date count from 1 March
        int estimate = d / 30; // approx number of month since March
        int mstart;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[288]++;
        switch (estimate) {
            case 0:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[197]++; return 2;
            case 1:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[198]++; mstart = 31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[289]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[290]++; break;
            case 2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[199]++; mstart = 31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[291]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[292]++; break;
            case 3:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[200]++; mstart = 31+30+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[293]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[294]++; break;
            case 4:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[201]++; mstart = 31+30+31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[295]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[296]++; break;
            case 5:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[202]++; mstart = 31+30+31+30+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[297]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[298]++; break;
            case 6:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[203]++; mstart = 31+30+31+30+31+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[299]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[300]++; break;
            case 7:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[204]++; mstart = 31+30+31+30+31+31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[301]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[302]++; break;
            case 8:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[205]++; mstart = 31+30+31+30+31+31+30+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[303]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[304]++; break;
            case 9:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[206]++; mstart = 31+30+31+30+31+31+30+31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[305]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[306]++; break;
            case 10:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[207]++; return 11; //Late december
            default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[208]++; throw Kit.codeBug();
        }
        // if d < mstart then real month since March == estimate - 1
        return (d >= mstart) ? estimate + 2 : estimate + 1;
    }

    private static int DateFromTime(double t)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[307]++;
        int year = YearFromTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[308]++;
        int d = (int)(Day(t) - DayFromYear(year));

        d -= 31 + 28;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[309]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[310]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((d < 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[209]++;
            return (d < -28) ? d + 31 + 28 + 1 : d + 28 + 1;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[210]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[311]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((IsLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[211]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[312]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((d == 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[213]++;
                return 29;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[214]++;} // 29 February
            --d;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[313]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[212]++;}

        // d: date count from 1 March
        int mdays, mstart;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[314]++;
        switch (d / 30) { // approx number of month since March
            case 0:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[215]++; return d + 1;
            case 1:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[216]++; mdays = 31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[315]++; mstart = 31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[316]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[317]++; break;
            case 2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[217]++; mdays = 30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[318]++; mstart = 31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[319]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[320]++; break;
            case 3:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[218]++; mdays = 31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[321]++; mstart = 31+30+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[322]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[323]++; break;
            case 4:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[219]++; mdays = 30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[324]++; mstart = 31+30+31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[325]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[326]++; break;
            case 5:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[220]++; mdays = 31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[327]++; mstart = 31+30+31+30+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[328]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[329]++; break;
            case 6:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[221]++; mdays = 31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[330]++; mstart = 31+30+31+30+31+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[331]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[332]++; break;
            case 7:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[222]++; mdays = 30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[333]++; mstart = 31+30+31+30+31+31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[334]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[335]++; break;
            case 8:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[223]++; mdays = 31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[336]++; mstart = 31+30+31+30+31+31+30+31;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[337]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[338]++; break;
            case 9:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[224]++; mdays = 30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[339]++; mstart = 31+30+31+30+31+31+30+31+30;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[340]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[341]++; break;
            case 10:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[225]++;
                return d - (31+30+31+30+31+31+30+31+30) + 1; //Late december
            default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[226]++; throw Kit.codeBug();
        }
        d -= mstart;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[342]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[343]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((d < 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[227]++;
            // wrong estimate: sfhift to previous month
            d += mdays;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[344]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[228]++;}
        return d + 1;
     }

    private static int WeekDay(double t)
    {
        double result;
        result = Day(t) + 4;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[345]++;
        result = result % 7;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[346]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[347]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[229]++;
            result += 7;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[348]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[230]++;}
        return (int) result;
    }

    private static double now()
    {
        return System.currentTimeMillis();
    }

    private static double DaylightSavingTA(double t)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[349]++;
int CodeCoverConditionCoverageHelper_C55;
        // Another workaround!  The JRE doesn't seem to know about DST
        // before year 1 AD, so we map to equivalent dates for the
        // purposes of finding DST. To be safe, we do this for years
        // before 1970.
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((t < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[231]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[350]++;
            int year = EquivalentYear(YearFromTime(t));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[351]++;
            double day = MakeDay(year, MonthFromTime(t), DateFromTime(t));
            t = MakeDate(day, TimeWithinDay(t));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[352]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[232]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[353]++;
        Date date = new Date((long) t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[354]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((thisTimeZone.inDaylightTime(date)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[233]++;
            return msPerHour;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[234]++;
            return 0;
}
    }

    /*
     * Find a year for which any given date will fall on the same weekday.
     *
     * This function should be used with caution when used other than
     * for determining DST; it hasn't been proven not to produce an
     * incorrect year for times near year boundaries.
     */
    private static int EquivalentYear(int year)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[355]++;
        int day = (int) DayFromYear(year) + 4;
        day = day % 7;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[356]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[357]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((day < 0) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[235]++;
            day += 7;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[358]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[236]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[359]++;
int CodeCoverConditionCoverageHelper_C58;
        // Years and leap years on which Jan 1 is a Sunday, Monday, etc.
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((IsLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[237]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[360]++;
            switch (day) {
                case 0:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[239]++; return 1984;
                case 1:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[240]++; return 1996;
                case 2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[241]++; return 1980;
                case 3:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[242]++; return 1992;
                case 4:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[243]++; return 1976;
                case 5:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[244]++; return 1988;
                case 6:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[245]++; return 1972; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[246]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[238]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[361]++;
            switch (day) {
                case 0:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[247]++; return 1978;
                case 1:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[248]++; return 1973;
                case 2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[249]++; return 1985;
                case 3:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[250]++; return 1986;
                case 4:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[251]++; return 1981;
                case 5:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[252]++; return 1971;
                case 6:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[253]++; return 1977; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[254]++;
            }
        }
        // Unreachable
        throw Kit.codeBug();
    }

    private static double LocalTime(double t)
    {
        return t + LocalTZA + DaylightSavingTA(t);
    }

    private static double internalUTC(double t)
    {
        return t - LocalTZA - DaylightSavingTA(t - LocalTZA);
    }

    private static int HourFromTime(double t)
    {
        double result;
        result = Math.floor(t / msPerHour) % HoursPerDay;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[362]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[363]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[255]++;
            result += HoursPerDay;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[364]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[256]++;}
        return (int) result;
    }

    private static int MinFromTime(double t)
    {
        double result;
        result = Math.floor(t / msPerMinute) % MinutesPerHour;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[365]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[366]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[257]++;
            result += MinutesPerHour;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[367]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[258]++;}
        return (int) result;
    }

    private static int SecFromTime(double t)
    {
        double result;
        result = Math.floor(t / msPerSecond) % SecondsPerMinute;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[368]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[369]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[259]++;
            result += SecondsPerMinute;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[370]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[260]++;}
        return (int) result;
    }

    private static int msFromTime(double t)
    {
        double result;
        result =  t % msPerSecond;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[371]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[372]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[261]++;
            result += msPerSecond;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[373]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[262]++;}
        return (int) result;
    }

    private static double MakeTime(double hour, double min,
                                   double sec, double ms)
    {
        return ((hour * MinutesPerHour + min) * SecondsPerMinute + sec)
            * msPerSecond + ms;
    }

    private static double MakeDay(double year, double month, double date)
    {
        year += Math.floor(month / 12);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[374]++;

        month = month % 12;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[375]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[376]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((month < 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[263]++;
            month += 12;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[377]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[264]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[378]++;

        double yearday = Math.floor(TimeFromYear(year) / msPerDay);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[379]++;
        double monthday = DayFromMonth((int)month, (int)year);

        return yearday + monthday + date - 1;
    }

    private static double MakeDate(double day, double time)
    {
        return day * msPerDay + time;
    }

    private static double TimeClip(double d)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[380]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (128)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C64 |= (32)) == 0 || true) &&
 ((d == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((d == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((Math.abs(d) > HalfTimeDomain) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 4) && false))
        {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[265]++;
            return ScriptRuntime.NaN;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[266]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[381]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((d > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[267]++;
            return Math.floor(d + 0.);
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[268]++;
            return Math.ceil(d + 0.);
}
    }

    /* end of ECMA helper functions */

    /* find UTC time from given date... no 1900 correction! */
    private static double date_msecFromDate(double year, double mon,
                                            double mday, double hour,
                                            double min, double sec,
                                            double msec)
    {
        double day;
        double time;
        double result;

        day = MakeDay(year, mon, mday);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[382]++;
        time = MakeTime(hour, min, sec, msec);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[383]++;
        result = MakeDate(day, time);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[384]++;
        return result;
    }

    /* compute the time in msec (unclipped) from the given args */
    private static final int MAXARGS = 7;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[385]++;
  }
    private static double date_msecFromArgs(Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[386]++;
        double array[] = new double[MAXARGS];
        int loop;
        double d;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[387]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[4]++;


int CodeCoverConditionCoverageHelper_C66;

        for (loop = 0;(((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((loop < MAXARGS) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false); loop++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[388]++;
int CodeCoverConditionCoverageHelper_C67;
            if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((loop < args.length) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[269]++;
                d = ScriptRuntime.toNumber(args[loop]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[389]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[390]++;
int CodeCoverConditionCoverageHelper_C68;
                if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((Double.isInfinite(d)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[271]++;
                    return ScriptRuntime.NaN;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[272]++;}
                array[loop] = ScriptRuntime.toInteger(args[loop]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[391]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[270]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[392]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((loop == 2) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[273]++;
                    array[loop] = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[393]++;
 /* Default the date argument to 1. */
                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[274]++;
                    array[loop] = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[394]++;
                }
            }
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[395]++;
int CodeCoverConditionCoverageHelper_C70;

        /* adjust 2-digit years into the 20th century */
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((array[0] >= 0) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((array[0] <= 99) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[275]++;
            array[0] += 1900;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[396]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[276]++;}

        return date_msecFromDate(array[0], array[1], array[2],
                                 array[3], array[4], array[5], array[6]);
    }

    private static double jsStaticFunction_UTC(Object[] args)
    {
        return TimeClip(date_msecFromArgs(args));
    }

    private static double date_parseString(String s)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[397]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[398]++;
int CodeCoverConditionCoverageHelper_C71;
          if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((s.length() == 24) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[278]++;
              final Date d;
              synchronized(isoFormat) {
                  d = isoFormat.parse(s);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[399]++;
              }
              return d.getTime();

          } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[279]++;}
        } catch (java.text.ParseException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[280]++;} finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[277]++;
}
  }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[400]++;

        int year = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[401]++;
        int mon = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[402]++;
        int mday = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[403]++;
        int hour = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[404]++;
        int min = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[405]++;
        int sec = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[406]++;
        char c = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[407]++;
        char si = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[408]++;
        int i = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[409]++;
        int n = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[410]++;
        double tzoffset = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[411]++;
        char prevc = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[412]++;
        int limit = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[413]++;
        boolean seenplusminus = false;

        limit = s.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[414]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[415]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[7]++;


int CodeCoverConditionCoverageHelper_C72;
        while ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((i < limit) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[9]++;
}
            c = s.charAt(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[416]++;
            i++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[417]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[418]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (32)) == 0 || true) &&
 ((c <= ' ') && 
  ((CodeCoverConditionCoverageHelper_C73 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((c == ',') && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[281]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[419]++;
int CodeCoverConditionCoverageHelper_C74;
                if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((i < limit) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[283]++;
                    si = s.charAt(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[420]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[421]++;
int CodeCoverConditionCoverageHelper_C75;
                    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (32)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C75 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 (('0' <= si) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((si <= '9') && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[285]++;
                        prevc = c;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[422]++;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[286]++;}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[284]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[423]++;
                continue;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[282]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[424]++;
int CodeCoverConditionCoverageHelper_C76;
            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((c == '(') && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[287]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[425]++; /* comments) */
                int depth = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[426]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[10]++;


int CodeCoverConditionCoverageHelper_C77;
                while ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((i < limit) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[10]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[11]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[12]++;
}
                    c = s.charAt(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[427]++;
                    i++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[428]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[429]++;
int CodeCoverConditionCoverageHelper_C78;
                    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((c == '(') && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[289]++;
                        depth++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[430]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[290]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[431]++;
int CodeCoverConditionCoverageHelper_C79; if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((c == ')') && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[291]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[432]++;
int CodeCoverConditionCoverageHelper_C80;
                        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((--depth <= 0) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[293]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[433]++;
                            break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[294]++;}
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[292]++;}
}
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[434]++;
                continue;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[288]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[435]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[295]++;
                n = c - '0';
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[436]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[437]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[13]++;


                while (i < limit && '0' <= (c = s.charAt(i)) && c <= '9') {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[13]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[14]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[15]++;
}
                    n = n * 10 + c - '0';
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[438]++;
                    i++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[439]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[440]++;
int CodeCoverConditionCoverageHelper_C83;

                /* allow TZA before the year, so
                 * 'Wed Nov 05 21:49:11 GMT-0800 1997'
                 * works */

                /* uses of seenplusminus allow : in TZA, so Java
                 * no-timezone style of GMT+4:30 works
                 */
                if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C83 |= (8)) == 0 || true) &&
 ((prevc == '+') && 
  ((CodeCoverConditionCoverageHelper_C83 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((prevc == '-') && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) && false)/*  && year>=0 */) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[297]++;
                    /* make ':' case below change tzoffset */
                    seenplusminus = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[441]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[442]++;
int CodeCoverConditionCoverageHelper_C84;

                    /* offset */
                    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((n < 24) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[299]++;
                        n = n * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[443]++;
} /* EG. "GMT-3" */
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[300]++;
                        n = n % 100 + n / 100 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[444]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[445]++;
int CodeCoverConditionCoverageHelper_C85; /* eg "GMT-0430" */
                    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((prevc == '+') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[301]++;       /* plus means east of GMT */
                        n = -n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[446]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[302]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[447]++;
int CodeCoverConditionCoverageHelper_C86;
                    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((tzoffset != 0) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((tzoffset != -1) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[303]++;
                        return ScriptRuntime.NaN;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[304]++;}
                    tzoffset = n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[448]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[298]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[449]++;
int CodeCoverConditionCoverageHelper_C87; if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (512)) == 0 || true) &&
 ((n >= 70) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (256)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C87 |= (128)) == 0 || true) &&
 ((prevc == '/') && 
  ((CodeCoverConditionCoverageHelper_C87 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (32)) == 0 || true) &&
 ((mon >= 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((mday >= 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 5) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 5) && false))
                {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[305]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[450]++;
int CodeCoverConditionCoverageHelper_C88;
                    if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((year >= 0) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[307]++;
                        return ScriptRuntime.NaN;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[308]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[451]++;
int CodeCoverConditionCoverageHelper_C89; if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (128)) == 0 || true) &&
 ((c <= ' ') && 
  ((CodeCoverConditionCoverageHelper_C89 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C89 |= (32)) == 0 || true) &&
 ((c == ',') && 
  ((CodeCoverConditionCoverageHelper_C89 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C89 |= (8)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C89 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((i >= limit) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[309]++;
                        year = n < 100 ? n + 1900 : n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[452]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[310]++;
                        return ScriptRuntime.NaN;
}
}

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[306]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[453]++;
int CodeCoverConditionCoverageHelper_C90; if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((c == ':') && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[311]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[454]++;
int CodeCoverConditionCoverageHelper_C91;
                    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((hour < 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[313]++;
                        hour = /*byte*/ n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[455]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[314]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[456]++;
int CodeCoverConditionCoverageHelper_C92; if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((min < 0) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[315]++;
                        min = /*byte*/ n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[457]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[316]++;
                        return ScriptRuntime.NaN;
}
}

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[312]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[458]++;
int CodeCoverConditionCoverageHelper_C93; if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[317]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[459]++;
int CodeCoverConditionCoverageHelper_C94;
                    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((mon < 0) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[319]++;
                        mon = /*byte*/ n-1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[460]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[320]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[461]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((mday < 0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[321]++;
                        mday = /*byte*/ n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[462]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[322]++;
                        return ScriptRuntime.NaN;
}
}

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[318]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[463]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (128)) == 0 || true) &&
 ((i < limit) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C96 |= (32)) == 0 || true) &&
 ((c != ',') && 
  ((CodeCoverConditionCoverageHelper_C96 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C96 |= (8)) == 0 || true) &&
 ((c > ' ') && 
  ((CodeCoverConditionCoverageHelper_C96 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((c != '-') && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[323]++;
                    return ScriptRuntime.NaN;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[324]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[464]++;
int CodeCoverConditionCoverageHelper_C97; if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (8)) == 0 || true) &&
 ((seenplusminus) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((n < 60) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[325]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[465]++;
int CodeCoverConditionCoverageHelper_C98;  /* handle GMT-3:30 */
                    if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((tzoffset < 0) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[327]++;
                        tzoffset -= n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[466]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[328]++;
                        tzoffset += n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[467]++;
}

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[326]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[468]++;
int CodeCoverConditionCoverageHelper_C99; if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 ((hour >= 0) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((min < 0) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[329]++;
                    min = /*byte*/ n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[469]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[330]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[470]++;
int CodeCoverConditionCoverageHelper_C100; if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (8)) == 0 || true) &&
 ((min >= 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((sec < 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[331]++;
                    sec = /*byte*/ n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[471]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[332]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[472]++;
int CodeCoverConditionCoverageHelper_C101; if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((mday < 0) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[333]++;
                    mday = /*byte*/ n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[473]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[334]++;
                    return ScriptRuntime.NaN;
                }
}
}
}
}
}
}
}
}
                prevc = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[474]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[296]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[475]++;
int CodeCoverConditionCoverageHelper_C102; if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (128)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C102 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C102 |= (32)) == 0 || true) &&
 ((c == ':') && 
  ((CodeCoverConditionCoverageHelper_C102 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C102 |= (8)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C102 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[335]++;
                prevc = c;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[476]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[336]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[477]++;
                int st = i - 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[478]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[16]++;


int CodeCoverConditionCoverageHelper_C103;
                while ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((i < limit) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[16]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[17]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[18]++;
}
                    c = s.charAt(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[479]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[480]++;
int CodeCoverConditionCoverageHelper_C104;
                    if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (!((
(((CodeCoverConditionCoverageHelper_C104 |= (128)) == 0 || true) &&
 (('A' <= c) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C104 |= (32)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C104 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C104 |= (8)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((c <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[337]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[481]++;
                        break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[338]++;}
                    i++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[482]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[483]++;
                int letterCount = i - st;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[484]++;
int CodeCoverConditionCoverageHelper_C105;
                if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((letterCount < 2) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[339]++;
                    return ScriptRuntime.NaN;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[340]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[485]++;
               /*
                * Use ported code from jsdate.c rather than the locale-specific
                * date-parsing code from Java, to keep js and rhino consistent.
                * Is this the right strategy?
                */
                String wtb = "am;pm;"
                            +"monday;tuesday;wednesday;thursday;friday;"
                            +"saturday;sunday;"
                            +"january;february;march;april;may;june;"
                            +"july;august;september;october;november;december;"
                            +"gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[486]++;
                int index = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[487]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[19]++;


                for (int wtbOffset = 0; ;) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[19]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[20]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[21]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[488]++;
                    int wtbNext = wtb.indexOf(';', wtbOffset);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[489]++;
int CodeCoverConditionCoverageHelper_C107;
                    if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((wtbNext < 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[341]++;
                        return ScriptRuntime.NaN;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[342]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[490]++;
int CodeCoverConditionCoverageHelper_C108;
                    if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((wtb.regionMatches(true, wtbOffset, s, st, letterCount)) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[343]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[491]++;
                        break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[344]++;}
                    wtbOffset = wtbNext + 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[492]++;
                    ++index;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[493]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[494]++;
int CodeCoverConditionCoverageHelper_C109;
                if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((index < 2) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[345]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[495]++;
int CodeCoverConditionCoverageHelper_C110;
                    /*
                     * AM/PM. Count 12:30 AM as 00:30, 12:30 PM as
                     * 12:30, instead of blindly adding 12 if PM.
                     */
                    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 ((hour > 12) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((hour < 0) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[347]++;
                        return ScriptRuntime.NaN;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[348]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[496]++;
int CodeCoverConditionCoverageHelper_C111; if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[349]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[497]++;
int CodeCoverConditionCoverageHelper_C112;
                        // AM
                        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((hour == 12) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[351]++;
                            hour = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[498]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[352]++;}

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[350]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[499]++;
int CodeCoverConditionCoverageHelper_C113;
                        // PM
                        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((hour != 12) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[353]++;
                            hour += 12;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[500]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[354]++;}
                    }
}

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[346]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[501]++;
int CodeCoverConditionCoverageHelper_C114; if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 (((index -= 2) < 7) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[355]++;

                    // ignore week days
                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[356]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[502]++;
int CodeCoverConditionCoverageHelper_C115; if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 (((index -= 7) < 12) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[357]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[503]++;
int CodeCoverConditionCoverageHelper_C116;
                    // month
                    if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((mon < 0) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[359]++;
                        mon = index;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[504]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[360]++;
                        return ScriptRuntime.NaN;
                    }

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[358]++;
                    index -= 12;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[505]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[506]++;
                    // timezones
                    switch (index) {
                      case 0 /* gmt */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[361]++; tzoffset = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[507]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[508]++; break;
                      case 1 /* ut */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[362]++;  tzoffset = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[509]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[510]++; break;
                      case 2 /* utc */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[363]++; tzoffset = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[511]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[512]++; break;
                      case 3 /* est */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[364]++; tzoffset = 5 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[513]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[514]++; break;
                      case 4 /* edt */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[365]++; tzoffset = 4 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[515]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[516]++; break;
                      case 5 /* cst */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[366]++; tzoffset = 6 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[517]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[518]++; break;
                      case 6 /* cdt */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[367]++; tzoffset = 5 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[519]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[520]++; break;
                      case 7 /* mst */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[368]++; tzoffset = 7 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[521]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[522]++; break;
                      case 8 /* mdt */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[369]++; tzoffset = 6 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[523]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[524]++; break;
                      case 9 /* pst */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[370]++; tzoffset = 8 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[525]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[526]++; break;
                      case 10 /* pdt */:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[371]++;tzoffset = 7 * 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[527]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[528]++; break;
                      default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[372]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[529]++;
                    }
                }
}
}
            }
}
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[530]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (32)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C117 |= (8)) == 0 || true) &&
 ((mon < 0) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((mday < 0) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[373]++;
            return ScriptRuntime.NaN;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[374]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[531]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((sec < 0) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[375]++;
            sec = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[532]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[376]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[533]++;
int CodeCoverConditionCoverageHelper_C119;
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((min < 0) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[377]++;
            min = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[534]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[378]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[535]++;
int CodeCoverConditionCoverageHelper_C120;
        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((hour < 0) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[379]++;
            hour = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[536]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[380]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[537]++;

        double msec = date_msecFromDate(year, mon, mday, hour, min, sec, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[538]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((tzoffset == -1) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[381]++; /* no time zone specified, have to use local */
            return internalUTC(msec);

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[382]++;
            return msec + tzoffset * msPerMinute;
        }
    }

    private static String date_format(double t, int methodId)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[539]++;
        StringBuffer result = new StringBuffer(60);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[540]++;
        double local = LocalTime(t);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[541]++;
int CodeCoverConditionCoverageHelper_C122;

        /* Tue Oct 31 09:41:40 GMT-0800 (PST) 2000 */
        /* Tue Oct 31 2000 */
        /* 09:41:40 GMT-0800 (PST) */

        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((methodId != Id_toTimeString) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[383]++;
            appendWeekDayName(result, WeekDay(local));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[542]++;
            result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[543]++;
            appendMonthName(result, MonthFromTime(local));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[544]++;
            result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[545]++;
            append0PaddedUint(result, DateFromTime(local), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[546]++;
            result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[547]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[548]++;
            int year = YearFromTime(local);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[549]++;
int CodeCoverConditionCoverageHelper_C123;
            if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[385]++;
                result.append('-');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[550]++;
                year = -year;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[551]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[386]++;}
            append0PaddedUint(result, year, 4);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[552]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[553]++;
int CodeCoverConditionCoverageHelper_C124;
            if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((methodId != Id_toDateString) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[387]++;
                result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[554]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[388]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[384]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[555]++;
int CodeCoverConditionCoverageHelper_C125;

        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((methodId != Id_toDateString) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[389]++;
            append0PaddedUint(result, HourFromTime(local), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[556]++;
            result.append(':');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[557]++;
            append0PaddedUint(result, MinFromTime(local), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[558]++;
            result.append(':');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[559]++;
            append0PaddedUint(result, SecFromTime(local), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[560]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[561]++;

            // offset from GMT in minutes.  The offset includes daylight
            // savings, if it applies.
            int minutes = (int) Math.floor((LocalTZA + DaylightSavingTA(t))
                                           / msPerMinute);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[562]++;
            // map 510 minutes to 0830 hours
            int offset = (minutes / 60) * 100 + minutes % 60;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[563]++;
int CodeCoverConditionCoverageHelper_C126;
            if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((offset > 0) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[391]++;
                result.append(" GMT+");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[564]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[392]++;
                result.append(" GMT-");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[565]++;
                offset = -offset;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[566]++;
            }
            append0PaddedUint(result, offset, 4);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[567]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[568]++;
int CodeCoverConditionCoverageHelper_C127;

            if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((timeZoneFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[393]++;
                timeZoneFormatter = new SimpleDateFormat("zzz");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[569]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[394]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[570]++;
int CodeCoverConditionCoverageHelper_C128;

            // Find an equivalent year before getting the timezone
            // comment.  See DaylightSavingTA.
            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((t < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[395]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[571]++;
                int equiv = EquivalentYear(YearFromTime(local));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[572]++;
                double day = MakeDay(equiv, MonthFromTime(t), DateFromTime(t));
                t = MakeDate(day, TimeWithinDay(t));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[573]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[396]++;}
            result.append(" (");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[574]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[575]++;
            Date date = new Date((long) t);
            synchronized (timeZoneFormatter) {
                result.append(timeZoneFormatter.format(date));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[576]++;
            }
            result.append(')');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[577]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[390]++;}
        return result.toString();
    }

    /* the javascript constructor */
    private static Object jsConstructor(Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[578]++;
        NativeDate obj = new NativeDate();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[579]++;
int CodeCoverConditionCoverageHelper_C129;

        // if called as a constructor with no args,
        // return a new Date with the current time.
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[397]++;
            obj.date = now();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[580]++;
            return obj;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[398]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[581]++;
int CodeCoverConditionCoverageHelper_C130;

        // if called with just one arg -
        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[399]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[582]++;
            Object arg0 = args[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[583]++;
int CodeCoverConditionCoverageHelper_C131;
            if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((arg0 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[401]++;
                arg0 = ((Scriptable) arg0).getDefaultValue(null);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[584]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[402]++;}
            double date;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[585]++;
int CodeCoverConditionCoverageHelper_C132;
            if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((arg0 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[403]++;
                // it's a string; parse it.
                date = date_parseString(arg0.toString());
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[586]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[404]++;
                // if it's not a string, use it as a millisecond date
                date = ScriptRuntime.toNumber(arg0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[587]++;
            }
            obj.date = TimeClip(date);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[588]++;
            return obj;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[400]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[589]++;

        double time = date_msecFromArgs(args);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[590]++;
int CodeCoverConditionCoverageHelper_C133;

        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C133 |= (8)) == 0 || true) &&
 ((Double.isNaN(time)) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((Double.isInfinite(time)) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[405]++;
            time = TimeClip(internalUTC(time));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[591]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[406]++;}

        obj.date = time;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[592]++;

        return obj;
    }

    private static String toLocale_helper(double t, int methodId)
    {
        DateFormat formatter;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[593]++;
        switch (methodId) {
          case Id_toLocaleString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[407]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[594]++;
int CodeCoverConditionCoverageHelper_C134;
            if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((localeDateTimeFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[408]++;
                localeDateTimeFormatter
                    = DateFormat.getDateTimeInstance(DateFormat.LONG,
                                                     DateFormat.LONG);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[595]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[409]++;}
            formatter = localeDateTimeFormatter;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[596]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[597]++;
            break;
          case Id_toLocaleTimeString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[410]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[598]++;
int CodeCoverConditionCoverageHelper_C135;
            if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((localeTimeFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[411]++;
                localeTimeFormatter
                    = DateFormat.getTimeInstance(DateFormat.LONG);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[599]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[412]++;}
            formatter = localeTimeFormatter;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[600]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[601]++;
            break;
          case Id_toLocaleDateString:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[413]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[602]++;
int CodeCoverConditionCoverageHelper_C136;
            if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((localeDateFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[414]++;
                localeDateFormatter
                    = DateFormat.getDateInstance(DateFormat.LONG);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[603]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[415]++;}
            formatter = localeDateFormatter;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[604]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[605]++;
            break;
          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[416]++; throw new AssertionError(); // unreachable
        }

        synchronized (formatter) {
            return formatter.format(new Date((long) t));
        }
    }

    private static String js_toUTCString(double date)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[606]++;
        StringBuffer result = new StringBuffer(60);

        appendWeekDayName(result, WeekDay(date));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[607]++;
        result.append(", ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[608]++;
        append0PaddedUint(result, DateFromTime(date), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[609]++;
        result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[610]++;
        appendMonthName(result, MonthFromTime(date));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[611]++;
        result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[612]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[613]++;
        int year = YearFromTime(date);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[614]++;
int CodeCoverConditionCoverageHelper_C137;
        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[417]++;
            result.append('-');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[615]++; year = -year;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[616]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[418]++;}
        append0PaddedUint(result, year, 4);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[617]++;
        result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[618]++;
        append0PaddedUint(result, HourFromTime(date), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[619]++;
        result.append(':');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[620]++;
        append0PaddedUint(result, MinFromTime(date), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[621]++;
        result.append(':');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[622]++;
        append0PaddedUint(result, SecFromTime(date), 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[623]++;
        result.append(" GMT");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[624]++;
        return result.toString();
    }

    private static void append0PaddedUint(StringBuffer sb, int i, int minWidth)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[625]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((i < 0) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[419]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[626]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[420]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[627]++;
        int scale = 1;
        --minWidth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[628]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[629]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((i >= 10) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[421]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[630]++;
int CodeCoverConditionCoverageHelper_C140;
            if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((i < 1000 * 1000 * 1000) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[423]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[631]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[22]++;


                for (;;) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[22]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[23]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[24]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[632]++;
                    int newScale = scale * 10;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[633]++;
int CodeCoverConditionCoverageHelper_C142;
                    if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((i < newScale) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[425]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[634]++; break;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[426]++;}
                    --minWidth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[635]++;
                    scale = newScale;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[636]++;
                }

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[424]++;
                // Separated case not to check against 10 * 10^9 overflow
                minWidth -= 9;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[637]++;
                scale = 1000 * 1000 * 1000;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[638]++;
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[422]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[639]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[25]++;


int CodeCoverConditionCoverageHelper_C143;
        while ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((minWidth > 0) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[25]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[26]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[27]++;
}
            sb.append('0');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[640]++;
            --minWidth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[641]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[642]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[28]++;


int CodeCoverConditionCoverageHelper_C144;
        while ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((scale != 1) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[28]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[29]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[30]++;
}
            sb.append((char)('0' + (i / scale)));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[643]++;
            i %= scale;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[644]++;
            scale /= 10;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[645]++;
        }
        sb.append((char)('0' + i));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[646]++;
    }

    private static void appendMonthName(StringBuffer sb, int index)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[647]++;
        // Take advantage of the fact that all month abbreviations
        // have the same length to minimize amount of strings runtime has
        // to keep in memory
        String months = "Jan"+"Feb"+"Mar"+"Apr"+"May"+"Jun"
                       +"Jul"+"Aug"+"Sep"+"Oct"+"Nov"+"Dec";
        index *= 3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[648]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[649]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[31]++;


int CodeCoverConditionCoverageHelper_C145;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((i != 3) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[31]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[32]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[33]++;
}
            sb.append(months.charAt(index + i));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[650]++;
        }
    }

    private static void appendWeekDayName(StringBuffer sb, int index)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[651]++;
        String days = "Sun"+"Mon"+"Tue"+"Wed"+"Thu"+"Fri"+"Sat";
        index *= 3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[652]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[653]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[34]++;


int CodeCoverConditionCoverageHelper_C146;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((i != 3) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[34]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[35]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[36]++;
}
            sb.append(days.charAt(index + i));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[654]++;
        }
    }

    private static double makeTime(double date, Object[] args, int methodId)
    {
        int maxargs;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[655]++;
        boolean local = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[656]++;
        switch (methodId) {
          case Id_setUTCMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[427]++;
              local = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[657]++;
            // fallthrough
          case Id_setMilliseconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[428]++;
            maxargs = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[658]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[659]++;
            break;

          case Id_setUTCSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[429]++;
              local = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[660]++;
            // fallthrough
          case Id_setSeconds:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[430]++;
            maxargs = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[661]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[662]++;
            break;

          case Id_setUTCMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[431]++;
              local = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[663]++;
            // fallthrough
          case Id_setMinutes:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[432]++;
            maxargs = 3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[664]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[665]++;
            break;

          case Id_setUTCHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[433]++;
              local = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[666]++;
            // fallthrough
          case Id_setHours:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[434]++;
            maxargs = 4;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[667]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[668]++;
            break;

          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[435]++;
              Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[669]++;
            maxargs = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[670]++;
        }

        int i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[671]++;
        double conv[] = new double[4];
        double hour, min, sec, msec;
        double lorutime; /* Local or UTC version of date */

        double time;
        double result;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[672]++;
int CodeCoverConditionCoverageHelper_C147;

        /* just return NaN if the date is already NaN */
        if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((date != date) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[436]++;
            return date;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[437]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[673]++;
int CodeCoverConditionCoverageHelper_C148;

        /* Satisfy the ECMA rule that if a function is called with
         * fewer arguments than the specified formal arguments, the
         * remaining arguments are set to undefined.  Seems like all
         * the Date.setWhatever functions in ECMA are only varargs
         * beyond the first argument; this should be set to undefined
         * if it's not given.  This means that "d = new Date();
         * d.setMilliseconds()" returns NaN.  Blech.
         */
        if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[438]++;
            args = ScriptRuntime.padArguments(args, 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[674]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[439]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[675]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[37]++;


int CodeCoverConditionCoverageHelper_C149;

        for (i = 0;(((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (8)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((i < maxargs) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[37]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[38]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[39]++;
}
            conv[i] = ScriptRuntime.toNumber(args[i]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[676]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[677]++;
int CodeCoverConditionCoverageHelper_C150;

            // limit checks that happen in MakeTime in ECMA.
            if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (8)) == 0 || true) &&
 ((conv[i] != conv[i]) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((Double.isInfinite(conv[i])) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[440]++;
                return ScriptRuntime.NaN;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[441]++;}
            conv[i] = ScriptRuntime.toInteger(conv[i]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[678]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[679]++;
int CodeCoverConditionCoverageHelper_C151;

        if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[442]++;
            lorutime = LocalTime(date);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[680]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[443]++;
            lorutime = date;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[681]++;
}

        i = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[682]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[683]++;
        int stop = args.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[684]++;
int CodeCoverConditionCoverageHelper_C152;

        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (8)) == 0 || true) &&
 ((maxargs >= 4) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((i < stop) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[444]++;
            hour = conv[i++];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[685]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[445]++;
            hour = HourFromTime(lorutime);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[686]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[687]++;
int CodeCoverConditionCoverageHelper_C153;

        if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (8)) == 0 || true) &&
 ((maxargs >= 3) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((i < stop) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[446]++;
            min = conv[i++];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[688]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[447]++;
            min = MinFromTime(lorutime);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[689]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[690]++;
int CodeCoverConditionCoverageHelper_C154;

        if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (8)) == 0 || true) &&
 ((maxargs >= 2) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((i < stop) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[448]++;
            sec = conv[i++];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[691]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[449]++;
            sec = SecFromTime(lorutime);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[692]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[693]++;
int CodeCoverConditionCoverageHelper_C155;

        if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (8)) == 0 || true) &&
 ((maxargs >= 1) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((i < stop) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[450]++;
            msec = conv[i++];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[694]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[451]++;
            msec = msFromTime(lorutime);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[695]++;
}

        time = MakeTime(hour, min, sec, msec);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[696]++;
        result = MakeDate(Day(lorutime), time);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[697]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[698]++;
int CodeCoverConditionCoverageHelper_C156;

        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[452]++;
            result = internalUTC(result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[699]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[453]++;}
        date = TimeClip(result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[700]++;

        return date;
    }

    private static double makeDate(double date, Object[] args, int methodId)
    {
        int maxargs;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[701]++;
        boolean local = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[702]++;
        switch (methodId) {
          case Id_setUTCDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[454]++;
              local = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[703]++;
            // fallthrough
          case Id_setDate:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[455]++;
              maxargs = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[704]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[705]++;
            break;

          case Id_setUTCMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[456]++;
              local = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[706]++;
            // fallthrough
          case Id_setMonth:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[457]++;
              maxargs = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[707]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[708]++;
            break;

          case Id_setUTCFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[458]++;
              local = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[709]++;
            // fallthrough
          case Id_setFullYear:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[459]++;
              maxargs = 3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[710]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[711]++;
            break;

          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[460]++;
              Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[712]++;
            maxargs = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[713]++;
        }

        int i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[714]++;
        double conv[] = new double[3];
        double year, month, day;
        double lorutime; /* local or UTC version of date */
        double result;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[715]++;
int CodeCoverConditionCoverageHelper_C157;

        /* See arg padding comment in makeTime.*/
        if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[461]++;
            args = ScriptRuntime.padArguments(args, 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[716]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[462]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[717]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[40]++;


int CodeCoverConditionCoverageHelper_C158;

        for (i = 0;(((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (8)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((i < maxargs) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[40]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[41]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.loops[42]++;
}
            conv[i] = ScriptRuntime.toNumber(args[i]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[718]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[719]++;
int CodeCoverConditionCoverageHelper_C159;

            // limit checks that happen in MakeDate in ECMA.
            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (8)) == 0 || true) &&
 ((conv[i] != conv[i]) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((Double.isInfinite(conv[i])) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[463]++;
                return ScriptRuntime.NaN;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[464]++;}
            conv[i] = ScriptRuntime.toInteger(conv[i]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[720]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[721]++;
int CodeCoverConditionCoverageHelper_C160;

        /* return NaN if date is NaN and we're not setting the year,
         * If we are, use 0 as the time. */
        if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((date != date) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[465]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[722]++;
int CodeCoverConditionCoverageHelper_C161;
            if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((args.length < 3) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[467]++;
                return ScriptRuntime.NaN;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[468]++;
                lorutime = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[723]++;
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[466]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[724]++;
int CodeCoverConditionCoverageHelper_C162;
            if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[469]++;
                lorutime = LocalTime(date);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[725]++;
}
            else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[470]++;
                lorutime = date;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[726]++;
}
        }

        i = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[727]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[728]++;
        int stop = args.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[729]++;
int CodeCoverConditionCoverageHelper_C163;

        if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (8)) == 0 || true) &&
 ((maxargs >= 3) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((i < stop) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[471]++;
            year = conv[i++];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[730]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[472]++;
            year = YearFromTime(lorutime);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[731]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[732]++;
int CodeCoverConditionCoverageHelper_C164;

        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (8)) == 0 || true) &&
 ((maxargs >= 2) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((i < stop) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[473]++;
            month = conv[i++];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[733]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[474]++;
            month = MonthFromTime(lorutime);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[734]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[735]++;
int CodeCoverConditionCoverageHelper_C165;

        if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (8)) == 0 || true) &&
 ((maxargs >= 1) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((i < stop) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[475]++;
            day = conv[i++];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[736]++;
}
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[476]++;
            day = DateFromTime(lorutime);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[737]++;
}

        day = MakeDay(year, month, day);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[738]++; /* day within year */
        result = MakeDate(day, TimeWithinDay(lorutime));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[739]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[740]++;
int CodeCoverConditionCoverageHelper_C166;

        if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[477]++;
            result = internalUTC(result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[741]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[478]++;}

        date = TimeClip(result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[742]++;

        return date;
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2009-07-22 05:44:02 EST
        L0: { id = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[743]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[744]++; String X = null; int c;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[745]++;
            L: switch (s.length()) {
            case 6:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[479]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[746]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[747]++;
int CodeCoverConditionCoverageHelper_C167;
                if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[480]++; X="getDay";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[748]++;id=Id_getDay;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[749]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[481]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[750]++;
int CodeCoverConditionCoverageHelper_C168; if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[482]++; X="toJSON";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[751]++;id=Id_toJSON;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[752]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[483]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[753]++;
                break L;
            case 7:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[484]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[754]++; switch (s.charAt(3)) {
                case 'D':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[485]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[755]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[756]++;
int CodeCoverConditionCoverageHelper_C169;
                    if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[486]++; X="getDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[757]++;id=Id_getDate;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[758]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[487]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[759]++;
int CodeCoverConditionCoverageHelper_C170; if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[488]++; X="setDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[760]++;id=Id_setDate;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[761]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[489]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[762]++;
                    break L;
                case 'T':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[490]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[763]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[764]++;
int CodeCoverConditionCoverageHelper_C171;
                    if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[491]++; X="getTime";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[765]++;id=Id_getTime;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[766]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[492]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[767]++;
int CodeCoverConditionCoverageHelper_C172; if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[493]++; X="setTime";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[768]++;id=Id_setTime;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[769]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[494]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[770]++;
                    break L;
                case 'Y':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[495]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[771]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[772]++;
int CodeCoverConditionCoverageHelper_C173;
                    if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[496]++; X="getYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[773]++;id=Id_getYear;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[774]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[497]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[775]++;
int CodeCoverConditionCoverageHelper_C174; if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[498]++; X="setYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[776]++;id=Id_setYear;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[777]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[499]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[778]++;
                    break L;
                case 'u':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[500]++; X="valueOf";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[779]++;id=Id_valueOf;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[780]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[781]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[501]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[782]++; break L;
            case 8:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[502]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[783]++; switch (s.charAt(3)) {
                case 'H':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[503]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[784]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[785]++;
int CodeCoverConditionCoverageHelper_C175;
                    if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[504]++; X="getHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[786]++;id=Id_getHours;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[787]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[505]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[788]++;
int CodeCoverConditionCoverageHelper_C176; if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[506]++; X="setHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[789]++;id=Id_setHours;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[790]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[507]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[791]++;
                    break L;
                case 'M':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[508]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[792]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[793]++;
int CodeCoverConditionCoverageHelper_C177;
                    if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[509]++; X="getMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[794]++;id=Id_getMonth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[795]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[510]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[796]++;
int CodeCoverConditionCoverageHelper_C178; if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[511]++; X="setMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[797]++;id=Id_setMonth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[798]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[512]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[799]++;
                    break L;
                case 'o':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[513]++; X="toSource";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[800]++;id=Id_toSource;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[801]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[802]++; break L;
                case 't':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[514]++; X="toString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[803]++;id=Id_toString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[804]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[805]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[515]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[806]++; break L;
            case 9:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[516]++; X="getUTCDay";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[807]++;id=Id_getUTCDay;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[808]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[809]++; break L;
            case 10:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[517]++; c=s.charAt(3);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[810]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[811]++;
int CodeCoverConditionCoverageHelper_C179;
                if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((c=='M') && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[518]++;
                    c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[812]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[813]++;
int CodeCoverConditionCoverageHelper_C180;
                    if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[520]++; X="getMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[814]++;id=Id_getMinutes;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[815]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[521]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[816]++;
int CodeCoverConditionCoverageHelper_C181; if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[522]++; X="setMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[817]++;id=Id_setMinutes;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[818]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[523]++;}
}

                }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[519]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[819]++;
int CodeCoverConditionCoverageHelper_C182; if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((c=='S') && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[524]++;
                    c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[820]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[821]++;
int CodeCoverConditionCoverageHelper_C183;
                    if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[526]++; X="getSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[822]++;id=Id_getSeconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[823]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[527]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[824]++;
int CodeCoverConditionCoverageHelper_C184; if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[528]++; X="setSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[825]++;id=Id_setSeconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[826]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[529]++;}
}

                }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[525]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[827]++;
int CodeCoverConditionCoverageHelper_C185; if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((c=='U') && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[530]++;
                    c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[828]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[829]++;
int CodeCoverConditionCoverageHelper_C186;
                    if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[532]++; X="getUTCDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[830]++;id=Id_getUTCDate;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[831]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[533]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[832]++;
int CodeCoverConditionCoverageHelper_C187; if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[534]++; X="setUTCDate";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[833]++;id=Id_setUTCDate;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[834]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[535]++;}
}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[531]++;}
}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[835]++;
                break L;
            case 11:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[536]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[836]++; switch (s.charAt(3)) {
                case 'F':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[537]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[837]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[838]++;
int CodeCoverConditionCoverageHelper_C188;
                    if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[538]++; X="getFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[839]++;id=Id_getFullYear;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[840]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[539]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[841]++;
int CodeCoverConditionCoverageHelper_C189; if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[540]++; X="setFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[842]++;id=Id_setFullYear;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[843]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[541]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[844]++;
                    break L;
                case 'M':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[542]++; X="toGMTString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[845]++;id=Id_toGMTString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[846]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[847]++; break L;
                case 'S':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[543]++; X="toISOString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[848]++;id=Id_toISOString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[849]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[850]++; break L;
                case 'T':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[544]++; X="toUTCString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[851]++;id=Id_toUTCString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[852]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[853]++; break L;
                case 'U':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[545]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[854]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[855]++;
int CodeCoverConditionCoverageHelper_C190;
                    if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[546]++;
                        c=s.charAt(9);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[856]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[857]++;
int CodeCoverConditionCoverageHelper_C191;
                        if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[548]++; X="getUTCHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[858]++;id=Id_getUTCHours;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[859]++;
 }
                        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[549]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[860]++;
int CodeCoverConditionCoverageHelper_C192; if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[550]++; X="getUTCMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[861]++;id=Id_getUTCMonth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[862]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[551]++;}
}

                    }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[547]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[863]++;
int CodeCoverConditionCoverageHelper_C193; if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[552]++;
                        c=s.charAt(9);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[864]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[865]++;
int CodeCoverConditionCoverageHelper_C194;
                        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[554]++; X="setUTCHours";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[866]++;id=Id_setUTCHours;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[867]++;
 }
                        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[555]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[868]++;
int CodeCoverConditionCoverageHelper_C195; if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[556]++; X="setUTCMonth";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[869]++;id=Id_setUTCMonth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[870]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[557]++;}
}

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[553]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[871]++;
                    break L;
                case 's':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[558]++; X="constructor";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[872]++;id=Id_constructor;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[873]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[874]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[559]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[875]++; break L;
            case 12:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[560]++; c=s.charAt(2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[876]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[877]++;
int CodeCoverConditionCoverageHelper_C196;
                if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((c=='D') && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[561]++; X="toDateString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[878]++;id=Id_toDateString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[879]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[562]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[880]++;
int CodeCoverConditionCoverageHelper_C197; if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((c=='T') && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[563]++; X="toTimeString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[881]++;id=Id_toTimeString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[882]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[564]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[883]++;
                break L;
            case 13:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[565]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[884]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[885]++;
int CodeCoverConditionCoverageHelper_C198;
                if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[566]++;
                    c=s.charAt(6);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[886]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[887]++;
int CodeCoverConditionCoverageHelper_C199;
                    if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((c=='M') && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[568]++; X="getUTCMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[888]++;id=Id_getUTCMinutes;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[889]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[569]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[890]++;
int CodeCoverConditionCoverageHelper_C200; if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((c=='S') && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[570]++; X="getUTCSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[891]++;id=Id_getUTCSeconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[892]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[571]++;}
}

                }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[567]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[893]++;
int CodeCoverConditionCoverageHelper_C201; if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[572]++;
                    c=s.charAt(6);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[894]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[895]++;
int CodeCoverConditionCoverageHelper_C202;
                    if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((c=='M') && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[574]++; X="setUTCMinutes";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[896]++;id=Id_setUTCMinutes;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[897]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[575]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[898]++;
int CodeCoverConditionCoverageHelper_C203; if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((c=='S') && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[576]++; X="setUTCSeconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[899]++;id=Id_setUTCSeconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[900]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[577]++;}
}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[573]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[901]++;
                break L;
            case 14:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[578]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[902]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[903]++;
int CodeCoverConditionCoverageHelper_C204;
                if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[579]++; X="getUTCFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[904]++;id=Id_getUTCFullYear;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[905]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[580]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[906]++;
int CodeCoverConditionCoverageHelper_C205; if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[581]++; X="setUTCFullYear";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[907]++;id=Id_setUTCFullYear;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[908]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[582]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[909]++;
int CodeCoverConditionCoverageHelper_C206; if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[583]++; X="toLocaleString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[910]++;id=Id_toLocaleString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[911]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[584]++;}
}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[912]++;
                break L;
            case 15:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[585]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[913]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[914]++;
int CodeCoverConditionCoverageHelper_C207;
                if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[586]++; X="getMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[915]++;id=Id_getMilliseconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[916]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[587]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[917]++;
int CodeCoverConditionCoverageHelper_C208; if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[588]++; X="setMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[918]++;id=Id_setMilliseconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[919]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[589]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[920]++;
                break L;
            case 17:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[590]++; X="getTimezoneOffset";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[921]++;id=Id_getTimezoneOffset;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[922]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[923]++; break L;
            case 18:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[591]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[924]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[925]++;
int CodeCoverConditionCoverageHelper_C209;
                if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[592]++; X="getUTCMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[926]++;id=Id_getUTCMilliseconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[927]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[593]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[928]++;
int CodeCoverConditionCoverageHelper_C210; if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[594]++; X="setUTCMilliseconds";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[929]++;id=Id_setUTCMilliseconds;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[930]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[595]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[931]++;
int CodeCoverConditionCoverageHelper_C211; if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[596]++;
                    c=s.charAt(8);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[932]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[933]++;
int CodeCoverConditionCoverageHelper_C212;
                    if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((c=='D') && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[598]++; X="toLocaleDateString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[934]++;id=Id_toLocaleDateString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[935]++;
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[599]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[936]++;
int CodeCoverConditionCoverageHelper_C213; if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((c=='T') && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[600]++; X="toLocaleTimeString";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[937]++;id=Id_toLocaleTimeString;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[938]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[601]++;}
}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[597]++;}
}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[939]++;
                break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[602]++;
            }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[940]++;
int CodeCoverConditionCoverageHelper_C214;
            if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C214 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[603]++; id = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[941]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.branches[604]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[942]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        ConstructorId_now       = -3,
        ConstructorId_parse     = -2,
        ConstructorId_UTC       = -1,

        Id_constructor          =  1,
        Id_toString             =  2,
        Id_toTimeString         =  3,
        Id_toDateString         =  4,
        Id_toLocaleString       =  5,
        Id_toLocaleTimeString   =  6,
        Id_toLocaleDateString   =  7,
        Id_toUTCString          =  8,
        Id_toSource             =  9,
        Id_valueOf              = 10,
        Id_getTime              = 11,
        Id_getYear              = 12,
        Id_getFullYear          = 13,
        Id_getUTCFullYear       = 14,
        Id_getMonth             = 15,
        Id_getUTCMonth          = 16,
        Id_getDate              = 17,
        Id_getUTCDate           = 18,
        Id_getDay               = 19,
        Id_getUTCDay            = 20,
        Id_getHours             = 21,
        Id_getUTCHours          = 22,
        Id_getMinutes           = 23,
        Id_getUTCMinutes        = 24,
        Id_getSeconds           = 25,
        Id_getUTCSeconds        = 26,
        Id_getMilliseconds      = 27,
        Id_getUTCMilliseconds   = 28,
        Id_getTimezoneOffset    = 29,
        Id_setTime              = 30,
        Id_setMilliseconds      = 31,
        Id_setUTCMilliseconds   = 32,
        Id_setSeconds           = 33,
        Id_setUTCSeconds        = 34,
        Id_setMinutes           = 35,
        Id_setUTCMinutes        = 36,
        Id_setHours             = 37,
        Id_setUTCHours          = 38,
        Id_setDate              = 39,
        Id_setUTCDate           = 40,
        Id_setMonth             = 41,
        Id_setUTCMonth          = 42,
        Id_setFullYear          = 43,
        Id_setUTCFullYear       = 44,
        Id_setYear              = 45,
        Id_toISOString          = 46,
        Id_toJSON               = 47,

        MAX_PROTOTYPE_ID        = Id_toJSON;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[943]++;
  }

    private static final int
        Id_toGMTString  =  Id_toUTCString;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp.statements[944]++;
  } // Alias, see Ecma B.2.6
// #/string_id_map#

    /* cached values */
    private static TimeZone thisTimeZone;
    private static double LocalTZA;
    private static DateFormat timeZoneFormatter;
    private static DateFormat localeDateTimeFormatter;
    private static DateFormat localeDateFormatter;
    private static DateFormat localeTimeFormatter;

    private double date;
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp ());
  }
    public static long[] statements = new long[945];
    public static long[] branches = new long[605];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[215];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeDate.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,2,1,2,1,1,3,1,3,1,1,1,1,1,2,0,2,1,1,2,3,1,3,1,1,1,1,1,1,3,2,1,2,2,1,3,1,3,1,0,1,1,1,2,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,2,2,1,2,2,2,2,1,1,2,2,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 214; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0pvf4hh2eu3nuqp () {
    super("org.mozilla.javascript.RHINO-SRC-NativeDate.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 944; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 604; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 214; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeDate.java");
      for (int i = 1; i <= 944; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 604; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 214; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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

