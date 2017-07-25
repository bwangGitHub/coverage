/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements the Number native object.
 *
 * See ECMA 15.7.
 *
 */
final class NativeNumber extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.ping();
  }

    static final long serialVersionUID = 3504516769741512101L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[1]++;
  }

    private static final Object NUMBER_TAG = "Number";
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[2]++;
  }

    private static final int MAX_PRECISION = 100;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[3]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[4]++;
        NativeNumber obj = new NativeNumber(0.0);
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[5]++;
    }

    NativeNumber(double number)
    {
        doubleValue = number;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[6]++;
    }

    @Override
    public String getClassName()
    {
        return "Number";
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[7]++;
        final int attr = ScriptableObject.DONTENUM |
                         ScriptableObject.PERMANENT |
                         ScriptableObject.READONLY;

        ctor.defineProperty("NaN", ScriptRuntime.NaNobj, attr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[8]++;
        ctor.defineProperty("POSITIVE_INFINITY",
                            ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY),
                            attr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[9]++;
        ctor.defineProperty("NEGATIVE_INFINITY",
                            ScriptRuntime.wrapNumber(Double.NEGATIVE_INFINITY),
                            attr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[10]++;
        ctor.defineProperty("MAX_VALUE",
                            ScriptRuntime.wrapNumber(Double.MAX_VALUE),
                            attr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[11]++;
        ctor.defineProperty("MIN_VALUE",
                            ScriptRuntime.wrapNumber(Double.MIN_VALUE),
                            attr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[12]++;

        super.fillConstructorProperties(ctor);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[13]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[14]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[1]++;    arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[15]++; s="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[16]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[17]++;    break;
          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[2]++;       arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[18]++; s="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[19]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[20]++;       break;
          case Id_toLocaleString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[3]++; arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[21]++; s="toLocaleString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[22]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[23]++; break;
          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[4]++;       arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[24]++; s="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[25]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[26]++;       break;
          case Id_valueOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[5]++;        arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[27]++; s="valueOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[28]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[29]++;        break;
          case Id_toFixed:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[6]++;        arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[30]++; s="toFixed";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[31]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[32]++;        break;
          case Id_toExponential:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[7]++;  arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[33]++; s="toExponential";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[34]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[35]++;  break;
          case Id_toPrecision:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[8]++;    arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[36]++; s="toPrecision";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[37]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[38]++;    break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[9]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(NUMBER_TAG, id, s, arity);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[39]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[40]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((f.hasTag(NUMBER_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[10]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[11]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[41]++;
        int id = f.methodId();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[42]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((id == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[12]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[43]++;
            double val = (args.length >= 1)
                ? ScriptRuntime.toNumber(args[0]) : 0.0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[44]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((thisObj == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[14]++;
                // new Number(val) creates a new Number object.
                return new NativeNumber(val);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[15]++;}
            // Number(val) converts val to a number value.
            return ScriptRuntime.wrapNumber(val);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[13]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[45]++;
int CodeCoverConditionCoverageHelper_C4;

        // The rest of Number.prototype methods require thisObj to be Number

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeNumber) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[16]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[17]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[46]++;
        double value = ((NativeNumber)thisObj).doubleValue;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[47]++;

        switch (id) {

          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[18]++;
          case Id_toLocaleString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[19]++;
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[48]++;
                // toLocaleString is just an alias for toString for now
                int base = (args.length == 0 || args[0] == Undefined.instance)
                    ? 10 : ScriptRuntime.toInt32(args[0]);
                return ScriptRuntime.numberToString(value, base);
            }

          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[20]++;
            return "(new Number("+ScriptRuntime.toString(value)+"))";

          case Id_valueOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[21]++;
            return ScriptRuntime.wrapNumber(value);

          case Id_toFixed:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[22]++;
            return num_to(value, args, DToA.DTOSTR_FIXED,
                          DToA.DTOSTR_FIXED, -20, 0);

          case Id_toExponential:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[23]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[49]++;
int CodeCoverConditionCoverageHelper_C5;
              // Handle special values before range check
              if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[24]++;
                  return "NaN";

              } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[25]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[50]++;
int CodeCoverConditionCoverageHelper_C6;
              if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((Double.isInfinite(value)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[26]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[51]++;
int CodeCoverConditionCoverageHelper_C7;
                  if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[28]++;
                      return "Infinity";

                  }
                  else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[29]++;
                      return "-Infinity";
                  }

              } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[27]++;}
              // General case
              return num_to(value, args, DToA.DTOSTR_STANDARD_EXPONENTIAL,
                      DToA.DTOSTR_EXPONENTIAL, 0, 1);
          }

          case Id_toPrecision:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[30]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[52]++;
int CodeCoverConditionCoverageHelper_C8;
              // Undefined precision, fall back to ToString()
              if((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((args[0] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[31]++;
                  return ScriptRuntime.numberToString(value, 10);

              } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[32]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[53]++;
int CodeCoverConditionCoverageHelper_C9;
              // Handle special values before range check
              if((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[33]++;
                  return "NaN";

              } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[34]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
              if((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((Double.isInfinite(value)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[35]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[55]++;
int CodeCoverConditionCoverageHelper_C11;
                  if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[37]++;
                      return "Infinity";

                  }
                  else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[38]++;
                      return "-Infinity";
                  }

              } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[36]++;}
              return num_to(value, args, DToA.DTOSTR_STANDARD,
                      DToA.DTOSTR_PRECISION, 1, 0);
          }

          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[39]++; throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    @Override
    public String toString() {
        return ScriptRuntime.numberToString(doubleValue, 10);
    }

    private static String num_to(double val,
                                 Object[] args,
                                 int zeroArgMode, int oneArgMode,
                                 int precisionMin, int precisionOffset)
    {
        int precision;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[40]++;
            precision = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[57]++;
            oneArgMode = zeroArgMode;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[58]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[41]++;
            /* We allow a larger range of precision than
               ECMA requires; this is permitted by ECMA. */
            precision = ScriptRuntime.toInt32(args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[59]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[60]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((precision < precisionMin) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((precision > MAX_PRECISION) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[42]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[61]++;
                String msg = ScriptRuntime.getMessage1(
                    "msg.bad.precision", ScriptRuntime.toString(args[0]));
                throw ScriptRuntime.constructError("RangeError", msg);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[43]++;}
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[62]++;
        StringBuilder sb = new StringBuilder();
        DToA.JS_dtostr(sb, oneArgMode, precision + precisionOffset, val);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[63]++;
        return sb.toString();
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:15:50 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[64]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[65]++; String X = null; int c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[66]++;
            L: switch (s.length()) {
            case 7:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[44]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[67]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[68]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[45]++; X="toFixed";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[69]++;id=Id_toFixed;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[70]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[46]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[71]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((c=='v') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[47]++; X="valueOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[72]++;id=Id_valueOf;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[73]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[48]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[74]++;
                break L;
            case 8:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[49]++; c=s.charAt(3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[75]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[50]++; X="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[77]++;id=Id_toSource;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[78]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[51]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[79]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[52]++; X="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[80]++;id=Id_toString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[81]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[53]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[82]++;
                break L;
            case 11:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[54]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[83]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[84]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[55]++; X="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[85]++;id=Id_constructor;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[86]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[56]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[87]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[57]++; X="toPrecision";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[88]++;id=Id_toPrecision;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[89]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[58]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[90]++;
                break L;
            case 13:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[59]++; X="toExponential";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[91]++;id=Id_toExponential;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[92]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[93]++; break L;
            case 14:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[60]++; X="toLocaleString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[94]++;id=Id_toLocaleString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[95]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[96]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[61]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[97]++;
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
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[62]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[98]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.branches[63]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[99]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor           = 1,
        Id_toString              = 2,
        Id_toLocaleString        = 3,
        Id_toSource              = 4,
        Id_valueOf               = 5,
        Id_toFixed               = 6,
        Id_toExponential         = 7,
        Id_toPrecision           = 8,
        MAX_PROTOTYPE_ID         = 8;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh.statements[100]++;
  }

// #/string_id_map#

    private double doubleValue;
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh ());
  }
    public static long[] statements = new long[101];
    public static long[] branches = new long[64];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeNumber.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,3};
    for (int i = 1; i <= 20; i++) {
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

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsb7fya9jycg5x5kvoh () {
    super("org.mozilla.javascript.RHINO-SRC-NativeNumber.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 100; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 63; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeNumber.java");
      for (int i = 1; i <= 100; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 63; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
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

