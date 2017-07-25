/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements the Math native object.
 * See ECMA 15.8.
 */

final class NativeMath extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.ping();
  }

    static final long serialVersionUID = -8838847185801131569L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[1]++;
  }

    private static final Object MATH_TAG = "Math";
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[2]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[3]++;
        NativeMath obj = new NativeMath();
        obj.activatePrototypeMap(MAX_ID);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[4]++;
        obj.setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[5]++;
        obj.setParentScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[6]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[1]++; obj.sealObject();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[8]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[2]++;}
        ScriptableObject.defineProperty(scope, "Math", obj,
                                        ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[9]++;
    }

    private NativeMath()
    {
    }

    @Override
    public String getClassName() { return "Math"; }

    @Override
    protected void initPrototypeId(int id)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((id <= LAST_METHOD_ID) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[3]++;
            String name;
            int arity;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[11]++;
            switch (id) {
              case Id_toSource:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[5]++; arity = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[12]++; name = "toSource";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[13]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[14]++; break;
              case Id_abs:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[6]++;      arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[15]++; name = "abs";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[16]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[17]++;      break;
              case Id_acos:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[7]++;     arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[18]++; name = "acos";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[19]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[20]++;     break;
              case Id_asin:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[8]++;     arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[21]++; name = "asin";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[22]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[23]++;     break;
              case Id_atan:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[9]++;     arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[24]++; name = "atan";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[25]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[26]++;     break;
              case Id_atan2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[10]++;    arity = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[27]++; name = "atan2";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[28]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[29]++;    break;
              case Id_ceil:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[11]++;     arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[30]++; name = "ceil";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[31]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[32]++;     break;
              case Id_cos:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[12]++;      arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[33]++; name = "cos";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[34]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[35]++;      break;
              case Id_exp:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[13]++;      arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[36]++; name = "exp";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[37]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[38]++;      break;
              case Id_floor:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[14]++;    arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[39]++; name = "floor";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[40]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[41]++;    break;
              case Id_log:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[15]++;      arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[42]++; name = "log";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[43]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[44]++;      break;
              case Id_max:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[16]++;      arity = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[45]++; name = "max";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[46]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[47]++;      break;
              case Id_min:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[17]++;      arity = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[48]++; name = "min";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[49]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[50]++;      break;
              case Id_pow:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[18]++;      arity = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[51]++; name = "pow";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[52]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[53]++;      break;
              case Id_random:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[19]++;   arity = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[54]++; name = "random";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[55]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[56]++;   break;
              case Id_round:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[20]++;    arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[57]++; name = "round";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[58]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[59]++;    break;
              case Id_sin:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[21]++;      arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[60]++; name = "sin";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[61]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[62]++;      break;
              case Id_sqrt:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[22]++;     arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[63]++; name = "sqrt";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[64]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[65]++;     break;
              case Id_tan:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[23]++;      arity = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[66]++; name = "tan";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[67]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[68]++;      break;
              default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[24]++; throw new IllegalStateException(String.valueOf(id));
            }
            initPrototypeMethod(MATH_TAG, id, name, arity);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[69]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[4]++;
            String name;
            double x;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[70]++;
            switch (id) {
              case Id_E:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[25]++;       x = Math.E;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[71]++;             name = "E";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[72]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[73]++;       break;
              case Id_PI:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[26]++;      x = Math.PI;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[74]++;            name = "PI";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[75]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[76]++;      break;
              case Id_LN10:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[27]++;    x = 2.302585092994046;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[77]++;  name = "LN10";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[78]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[79]++;    break;
              case Id_LN2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[28]++;     x = 0.6931471805599453;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[80]++; name = "LN2";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[81]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[82]++;     break;
              case Id_LOG2E:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[29]++;   x = 1.4426950408889634;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[83]++; name = "LOG2E";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[84]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[85]++;   break;
              case Id_LOG10E:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[30]++;  x = 0.4342944819032518;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[86]++; name = "LOG10E";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[87]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[88]++;  break;
              case Id_SQRT1_2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[31]++; x = 0.7071067811865476;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[89]++; name = "SQRT1_2";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[90]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[91]++; break;
              case Id_SQRT2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[32]++;   x = 1.4142135623730951;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[92]++; name = "SQRT2";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[93]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[94]++;   break;
              default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[33]++; throw new IllegalStateException(String.valueOf(id));
            }
            initPrototypeValue(id, name, ScriptRuntime.wrapNumber(x),
                               DONTENUM | READONLY | PERMANENT);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[95]++;
        }
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[96]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((f.hasTag(MATH_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[34]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[35]++;}
        double x;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[97]++;
        int methodId = f.methodId();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[98]++;
        switch (methodId) {
            case Id_toSource:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[36]++;
                return "Math";

            case Id_abs:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[37]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[99]++;
                // abs(-0.0) should be 0.0, but -0.0 < 0.0 == false
                x = (x == 0.0) ? 0.0 : (x < 0.0) ? -x : x;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[100]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[101]++;
                break;

            case Id_acos:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[38]++;
            case Id_asin:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[39]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[102]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[103]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((x == x) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((-1.0 <= x) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((x <= 1.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[40]++;
                    x = (methodId == Id_acos) ? Math.acos(x) : Math.asin(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[104]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[41]++;
                    x = Double.NaN;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[105]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[106]++;
                break;

            case Id_atan:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[42]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[107]++;
                x = Math.atan(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[108]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[109]++;
                break;

            case Id_atan2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[43]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[110]++;
                x = Math.atan2(x, ScriptRuntime.toNumber(args, 1));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[111]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[112]++;
                break;

            case Id_ceil:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[44]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[113]++;
                x = Math.ceil(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[114]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[115]++;
                break;

            case Id_cos:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[45]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[116]++;
                x = (x == Double.POSITIVE_INFINITY
                     || x == Double.NEGATIVE_INFINITY)
                    ? Double.NaN : Math.cos(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[117]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[118]++;
                break;

            case Id_exp:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[46]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[119]++;
                x = (x == Double.POSITIVE_INFINITY) ? x
                    : (x == Double.NEGATIVE_INFINITY) ? 0.0
                    : Math.exp(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[120]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[121]++;
                break;

            case Id_floor:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[47]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[122]++;
                x = Math.floor(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[123]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[124]++;
                break;

            case Id_log:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[48]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[125]++;
                // Java's log(<0) = -Infinity; we need NaN
                x = (x < 0) ? Double.NaN : Math.log(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[126]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[127]++;
                break;

            case Id_max:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[49]++;
            case Id_min:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[50]++;
                x = (methodId == Id_max)
                    ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[128]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[129]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[130]++;
                    double d = ScriptRuntime.toNumber(args[i]);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[131]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[51]++;
                        x = d;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[132]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[133]++; // NaN
                        break;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[52]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[134]++;
int CodeCoverConditionCoverageHelper_C7;
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((methodId == Id_max) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[53]++;
                        // if (x < d) x = d; does not work due to -0.0 >= +0.0
                        x = Math.max(x, d);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[135]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[54]++;
                        x = Math.min(x, d);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[136]++;
                    }
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[137]++;
                break;

            case Id_pow:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[55]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[138]++;
                x = js_pow(x, ScriptRuntime.toNumber(args, 1));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[139]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[140]++;
                break;

            case Id_random:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[56]++;
                x = Math.random();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[141]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[142]++;
                break;

            case Id_round:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[57]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[143]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[144]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((x == x) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((x != Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((x != Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false))
                {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[58]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[145]++;
                    // Round only finite x
                    long l = Math.round(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[146]++;
int CodeCoverConditionCoverageHelper_C9;
                    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((l != 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[60]++;
                        x = l;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[147]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[61]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[148]++;
int CodeCoverConditionCoverageHelper_C10;
                        // We must propagate the sign of d into the result
                        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((x < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[62]++;
                            x = ScriptRuntime.negativeZero;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[149]++;

                        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[63]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[150]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((x != 0.0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[64]++;
                            x = 0.0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[151]++;

                        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[65]++;}
}
                    }

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[59]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[152]++;
                break;

            case Id_sin:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[66]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[153]++;
                x = (x == Double.POSITIVE_INFINITY
                     || x == Double.NEGATIVE_INFINITY)
                    ? Double.NaN : Math.sin(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[154]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[155]++;
                break;

            case Id_sqrt:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[67]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[156]++;
                x = Math.sqrt(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[157]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[158]++;
                break;

            case Id_tan:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[68]++;
                x = ScriptRuntime.toNumber(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[159]++;
                x = Math.tan(x);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[160]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[161]++;
                break;

            default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[69]++; throw new IllegalStateException(String.valueOf(methodId));
        }
        return ScriptRuntime.wrapNumber(x);
    }

    // See Ecma 15.8.2.13
    private double js_pow(double x, double y) {
        double result;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[162]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((y != y) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[70]++;
            // y is NaN, result is always NaN
            result = y;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[163]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[71]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[164]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((y == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[72]++;
            // Java's pow(NaN, 0) = NaN; we need 1
            result = 1.0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[165]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[73]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[166]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((x == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[74]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[167]++;
int CodeCoverConditionCoverageHelper_C15;
            // Many differences from Java's Math.pow
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((1 / x > 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[76]++;
                result = (y > 0) ? 0 : Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[168]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[77]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[169]++;
                // x is -0, need to check if y is an odd integer
                long y_long = (long)y;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[170]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((y_long == y) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (((y_long & 0x1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[78]++;
                    result = (y > 0) ? -0.0 : Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[171]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[79]++;
                    result = (y > 0) ? 0.0 : Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[172]++;
                }
            }

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[75]++;
            result = Math.pow(x, y);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[173]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[174]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((result != result) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[80]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[175]++;
int CodeCoverConditionCoverageHelper_C18;
                // Check for broken Java implementations that gives NaN
                // when they should return something else
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((y == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[82]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[176]++;
int CodeCoverConditionCoverageHelper_C19;
                    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((x < -1.0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((1.0 < x) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[84]++;
                        result = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[177]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[85]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[178]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((-1.0 < x) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((x < 1.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[86]++;
                        result = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[179]++;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[87]++;}
}

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[83]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[180]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((y == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[88]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[181]++;
int CodeCoverConditionCoverageHelper_C22;
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((x < -1.0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((1.0 < x) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[90]++;
                        result = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[182]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[91]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[183]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((-1.0 < x) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((x < 1.0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[92]++;
                        result = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[184]++;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[93]++;}
}

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[89]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[185]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((x == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[94]++;
                    result = (y > 0) ? Double.POSITIVE_INFINITY : 0.0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[186]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[95]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[187]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((x == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[96]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[188]++;
                    long y_long = (long)y;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[189]++;
int CodeCoverConditionCoverageHelper_C26;
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((y_long == y) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 (((y_long & 0x1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[98]++;
                        // y is odd integer
                        result = (y > 0) ? Double.NEGATIVE_INFINITY : -0.0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[190]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[99]++;
                        result = (y > 0) ? Double.POSITIVE_INFINITY : 0.0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[191]++;
                    }

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[97]++;}
}
}
}

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[81]++;}
        }
}
}
        return result;
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2004-03-17 13:51:32 CET
        L0: { id = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[192]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[193]++; String X = null; int c;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[194]++;
            L: switch (s.length()) {
            case 1:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[100]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[195]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((s.charAt(0)=='E') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[101]++;id=Id_E;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[196]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[197]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[102]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[198]++; break L;
            case 2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[103]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[199]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((s.charAt(0)=='P') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='I') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[104]++;id=Id_PI;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[200]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[201]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[105]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[202]++; break L;
            case 3:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[106]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[203]++; switch (s.charAt(0)) {
                case 'L':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[107]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[204]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='2') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='N') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[108]++;id=Id_LN2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[205]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[206]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[109]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[207]++; break L;
                case 'a':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[110]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[208]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='s') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='b') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[111]++;id=Id_abs;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[209]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[210]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[112]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[211]++; break L;
                case 'c':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[113]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[212]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='s') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='o') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[114]++;id=Id_cos;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[213]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[214]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[115]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[215]++; break L;
                case 'e':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[116]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[216]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='p') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='x') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[117]++;id=Id_exp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[217]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[218]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[118]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[219]++; break L;
                case 'l':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[119]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[220]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='g') && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='o') && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[120]++;id=Id_log;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[221]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[222]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[121]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[223]++; break L;
                case 'm':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[122]++; c=s.charAt(2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[224]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[225]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[123]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[226]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='i') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[125]++;id=Id_min;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[227]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[228]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[126]++;}
 }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[124]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[229]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((c=='x') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[127]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[230]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='a') && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[129]++;id=Id_max;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[231]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[232]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[130]++;}
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[128]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[233]++;
                    break L;
                case 'p':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[131]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[234]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='w') && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='o') && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[132]++;id=Id_pow;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[235]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[236]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[133]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[237]++; break L;
                case 's':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[134]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[238]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='n') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='i') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[135]++;id=Id_sin;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[239]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[240]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[136]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[241]++; break L;
                case 't':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[137]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[242]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='n') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='a') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[138]++;id=Id_tan;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[243]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[244]++; break L0;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[139]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[245]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[140]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[246]++; break L;
            case 4:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[141]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[247]++; switch (s.charAt(1)) {
                case 'N':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[142]++; X="LN10";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[248]++;id=Id_LN10;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[249]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[250]++; break L;
                case 'c':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[143]++; X="acos";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[251]++;id=Id_acos;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[252]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[253]++; break L;
                case 'e':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[144]++; X="ceil";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[254]++;id=Id_ceil;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[255]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[256]++; break L;
                case 'q':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[145]++; X="sqrt";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[257]++;id=Id_sqrt;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[258]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[259]++; break L;
                case 's':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[146]++; X="asin";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[260]++;id=Id_asin;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[261]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[262]++; break L;
                case 't':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[147]++; X="atan";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[263]++;id=Id_atan;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[264]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[265]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[148]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[266]++; break L;
            case 5:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[149]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[267]++; switch (s.charAt(0)) {
                case 'L':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[150]++; X="LOG2E";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[268]++;id=Id_LOG2E;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[269]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[270]++; break L;
                case 'S':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[151]++; X="SQRT2";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[271]++;id=Id_SQRT2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[272]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[273]++; break L;
                case 'a':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[152]++; X="atan2";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[274]++;id=Id_atan2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[275]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[276]++; break L;
                case 'f':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[153]++; X="floor";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[277]++;id=Id_floor;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[278]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[279]++; break L;
                case 'r':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[154]++; X="round";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[280]++;id=Id_round;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[281]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[282]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[155]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[283]++; break L;
            case 6:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[156]++; c=s.charAt(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[284]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[285]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((c=='L') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[157]++; X="LOG10E";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[286]++;id=Id_LOG10E;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[287]++;
 }
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[158]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[288]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[159]++; X="random";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[289]++;id=Id_random;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[290]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[160]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[291]++;
                break L;
            case 7:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[161]++; X="SQRT1_2";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[292]++;id=Id_SQRT1_2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[293]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[294]++; break L;
            case 8:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[162]++; X="toSource";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[295]++;id=Id_toSource;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[296]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[297]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[163]++;
            }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[298]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[164]++; id = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[299]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.branches[165]++;}
        }
// #/generated#
        return id;
    }

    private static final int
        Id_toSource     =  1,
        Id_abs          =  2,
        Id_acos         =  3,
        Id_asin         =  4,
        Id_atan         =  5,
        Id_atan2        =  6,
        Id_ceil         =  7,
        Id_cos          =  8,
        Id_exp          =  9,
        Id_floor        = 10,
        Id_log          = 11,
        Id_max          = 12,
        Id_min          = 13,
        Id_pow          = 14,
        Id_random       = 15,
        Id_round        = 16,
        Id_sin          = 17,
        Id_sqrt         = 18,
        Id_tan          = 19,

        LAST_METHOD_ID  = 19;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[300]++;
  }

    private static final int
        Id_E            = LAST_METHOD_ID + 1,
        Id_PI           = LAST_METHOD_ID + 2,
        Id_LN10         = LAST_METHOD_ID + 3,
        Id_LN2          = LAST_METHOD_ID + 4,
        Id_LOG2E        = LAST_METHOD_ID + 5,
        Id_LOG10E       = LAST_METHOD_ID + 6,
        Id_SQRT1_2      = LAST_METHOD_ID + 7,
        Id_SQRT2        = LAST_METHOD_ID + 8,

        MAX_ID = LAST_METHOD_ID + 8;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1.statements[301]++;
  }

// #/string_id_map#
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1 ());
  }
    public static long[] statements = new long[302];
    public static long[] branches = new long[166];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[44];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeMath.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,1,1,1,3,1,1,1,1,1,1,1,2,1,1,2,2,1,2,2,1,1,2,1,2,2,2,2,2,2,1,1,1,1,2,2,2,1,1,3};
    for (int i = 1; i <= 43; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0quggzri1p1a6w1 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeMath.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 301; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 165; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeMath.java");
      for (int i = 1; i <= 301; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 165; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

