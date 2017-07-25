/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;

import org.mozilla.javascript.xml.XMLLib;
import static org.mozilla.javascript.ScriptableObject.DONTENUM;
import static org.mozilla.javascript.ScriptableObject.READONLY;
import static org.mozilla.javascript.ScriptableObject.PERMANENT;

/**
 * This class implements the global native object (function and value
 * properties only).
 *
 * See ECMA 15.1.[12].
 *
 */

public class NativeGlobal implements Serializable, IdFunctionCall
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.ping();
  }

    static final long serialVersionUID = 6080442165748707530L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[1]++;
  }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[2]++;
        NativeGlobal obj = new NativeGlobal();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

        for (int id = 1;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((id <= LAST_SCOPE_FUNCTION_ID) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++id) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[3]++;
}
            String name;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[4]++;
            int arity = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[5]++;
            switch (id) {
              case Id_decodeURI:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[1]++;
                name = "decodeURI";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[6]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[7]++;
                break;
              case Id_decodeURIComponent:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[2]++;
                name = "decodeURIComponent";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[8]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[9]++;
                break;
              case Id_encodeURI:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[3]++;
                name = "encodeURI";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[10]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[11]++;
                break;
              case Id_encodeURIComponent:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[4]++;
                name = "encodeURIComponent";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[12]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[13]++;
                break;
              case Id_escape:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[5]++;
                name = "escape";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[14]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[15]++;
                break;
              case Id_eval:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[6]++;
                name = "eval";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[16]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[17]++;
                break;
              case Id_isFinite:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[7]++;
                name = "isFinite";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[18]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[19]++;
                break;
              case Id_isNaN:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[8]++;
                name = "isNaN";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[20]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[21]++;
                break;
              case Id_isXMLName:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[9]++;
                name = "isXMLName";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[22]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[23]++;
                break;
              case Id_parseFloat:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[10]++;
                name = "parseFloat";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[24]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[25]++;
                break;
              case Id_parseInt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[11]++;
                name = "parseInt";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[26]++;
                arity = 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[27]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[28]++;
                break;
              case Id_unescape:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[12]++;
                name = "unescape";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[29]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[30]++;
                break;
              case Id_uneval:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[13]++;
                name = "uneval";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[31]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[32]++;
                break;
              default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[14]++;
                  throw Kit.codeBug();
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[33]++;
            IdFunctionObject f = new IdFunctionObject(obj, FTAG, id, name,
                                                      arity, scope);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[34]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[15]++;
                f.sealObject();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[35]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[16]++;}
            f.exportAsScopeProperty();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[36]++;
        }

        ScriptableObject.defineProperty(
            scope, "NaN", ScriptRuntime.NaNobj,
            READONLY|DONTENUM|PERMANENT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[37]++;
        ScriptableObject.defineProperty(
            scope, "Infinity",
            ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY),
            READONLY|DONTENUM|PERMANENT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[38]++;
        ScriptableObject.defineProperty(
            scope, "undefined", Undefined.instance,
            READONLY|DONTENUM|PERMANENT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[39]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[40]++;

        String[] errorMethods = {
                "ConversionError",
                "EvalError",
                "RangeError",
                "ReferenceError",
                "SyntaxError",
                "TypeError",
                "URIError",
                "InternalError",
                "JavaException"
        };
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[41]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;

        /*
            Each error constructor gets its own Error object as a prototype,
            with the 'name' property set to the name of the error.
        */
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < errorMethods.length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[6]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[42]++;
            String name = errorMethods[i];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[43]++;
            ScriptableObject errorProto =
              (ScriptableObject) ScriptRuntime.newObject(cx, scope, "Error",
                                                  ScriptRuntime.emptyArgs);
            errorProto.put("name", errorProto, name);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[44]++;
            errorProto.put("message", errorProto, "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[45]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[46]++;
            IdFunctionObject ctor = new IdFunctionObject(obj, FTAG,
                                                         Id_new_CommonError,
                                                         name, 1, scope);
            ctor.markAsConstructor(errorProto);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[47]++;
            errorProto.put("constructor", errorProto, ctor);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[48]++;
            errorProto.setAttributes("constructor", ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[49]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[50]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[17]++;
                errorProto.sealObject();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[51]++;
                ctor.sealObject();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[52]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[18]++;}
            ctor.exportAsScopeProperty();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[53]++;
        }
    }

    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[54]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((f.hasTag(FTAG)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[19]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[55]++;
            int methodId = f.methodId();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[56]++;
            switch (methodId) {
                case Id_decodeURI:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[21]++;
                case Id_decodeURIComponent:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[22]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[57]++;
                    String str = ScriptRuntime.toString(args, 0);
                    return decode(str, methodId == Id_decodeURI);
                }

                case Id_encodeURI:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[23]++;
                case Id_encodeURIComponent:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[24]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[58]++;
                    String str = ScriptRuntime.toString(args, 0);
                    return encode(str, methodId == Id_encodeURI);
                }

                case Id_escape:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[25]++;
                    return js_escape(args);

                case Id_eval:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[26]++;
                    return js_eval(cx, scope, args);

                case Id_isFinite:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[27]++; {
                    boolean result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[59]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[28]++;
                        result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[60]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[29]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[61]++;
                        double d = ScriptRuntime.toNumber(args[0]);
                        result = (d == d
                                  && d != Double.POSITIVE_INFINITY
                                  && d != Double.NEGATIVE_INFINITY);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[62]++;
                    }
                    return ScriptRuntime.wrapBoolean(result);
                }

                case Id_isNaN:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[30]++; {
                    // The global method isNaN, as per ECMA-262 15.1.2.6.
                    boolean result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[63]++;
int CodeCoverConditionCoverageHelper_C7;
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[31]++;
                        result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[64]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[32]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[65]++;
                        double d = ScriptRuntime.toNumber(args[0]);
                        result = (d != d);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[66]++;
                    }
                    return ScriptRuntime.wrapBoolean(result);
                }

                case Id_isXMLName:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[33]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[67]++;
                    Object name = (args.length == 0)
                                  ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[68]++;
                    XMLLib xmlLib = XMLLib.extractFromScope(scope);
                    return ScriptRuntime.wrapBoolean(
                        xmlLib.isXMLName(cx, name));
                }

                case Id_parseFloat:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[34]++;
                    return js_parseFloat(args);

                case Id_parseInt:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[35]++;
                    return js_parseInt(args);

                case Id_unescape:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[36]++;
                    return js_unescape(args);

                case Id_uneval:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[37]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[69]++;
                    Object value = (args.length != 0)
                                   ? args[0] : Undefined.instance;
                    return ScriptRuntime.uneval(cx, scope, value);
                }

                case Id_new_CommonError:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[38]++;
                    // The implementation of all the ECMA error constructors
                    // (SyntaxError, TypeError, etc.)
                    return NativeError.make(cx, scope, f, args); default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[39]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[20]++;}
        throw f.unknown();
    }

    /**
     * The global method parseInt, as per ECMA-262 15.1.2.2.
     */
    private Object js_parseInt(Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[70]++;
        String s = ScriptRuntime.toString(args, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[71]++;
        int radix = ScriptRuntime.toInt32(args, 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[72]++;

        int len = s.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[73]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((len == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[40]++;
            return ScriptRuntime.NaNobj;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[41]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[74]++;

        boolean negative = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[75]++;
        int start = 0;
        char c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[76]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;
        do {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[7]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[8]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[9]++;
}
            c = s.charAt(start);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[77]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[78]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isStrWhiteSpaceChar(c)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[42]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[79]++;
                break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[43]++;}
            start++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[80]++;
        } while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((start < len) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[81]++;

        if (c == '+' || (negative = (c == '-'))) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[44]++;
            start++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[82]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[45]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[83]++;

        final int NO_RADIX = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[84]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((radix == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[46]++;
            radix = NO_RADIX;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[85]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[47]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[86]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((radix < 2) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((radix > 36) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[48]++;
            return ScriptRuntime.NaNobj;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[49]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[87]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((radix == 16) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((len - start > 1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((s.charAt(start) == '0') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[50]++;
            c = s.charAt(start+1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[88]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[89]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((c == 'x') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((c == 'X') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[52]++;
                start += 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[90]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[53]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[51]++;}
}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[91]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((radix == NO_RADIX) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[54]++;
            radix = 10;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[92]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[93]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((len - start > 1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((s.charAt(start) == '0') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[56]++;
                c = s.charAt(start+1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[94]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[95]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((c == 'x') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c == 'X') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[58]++;
                    radix = 16;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[96]++;
                    start += 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[97]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[59]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[98]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[60]++;
                    radix = 8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[99]++;
                    start++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[100]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[61]++;}
}

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[57]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[55]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[101]++;

        double d = ScriptRuntime.stringToNumber(s, start, radix);
        return ScriptRuntime.wrapNumber(negative ? -d : d);
    }

    /**
     * The global method parseFloat, as per ECMA-262 15.1.2.3.
     *
     * @param args the arguments to parseFloat, ignoring args[>=1]
     */
    private Object js_parseFloat(Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[102]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[62]++;
            return ScriptRuntime.NaNobj;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[63]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[103]++;

        String s = ScriptRuntime.toString(args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[104]++;
        int len = s.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[105]++;
        int start = 0;
        // Scan forward to skip whitespace
        char c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[106]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[10]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[10]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[11]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[12]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[107]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((start == len) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[64]++;
                return ScriptRuntime.NaNobj;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[65]++;}
            c = s.charAt(start);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[108]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[109]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isStrWhiteSpaceChar(c)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[66]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[110]++;
                break;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[67]++;}
            ++start;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[111]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[112]++;

        int i = start;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[113]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[68]++;
            ++i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[114]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[115]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i == len) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[70]++;
                return ScriptRuntime.NaNobj;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[71]++;}
            c = s.charAt(i);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[116]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[69]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[117]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((c == 'I') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[72]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[118]++;
int CodeCoverConditionCoverageHelper_C27;
            // check for "Infinity"
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((i+8 <= len) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((s.regionMatches(i, "Infinity", 0, 8)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[74]++;
                double d;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[119]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((s.charAt(start) == '-') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[76]++;
                    d = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[120]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[77]++;
                    d = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[121]++;
                }
                return ScriptRuntime.wrapNumber(d);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[75]++;}
            return ScriptRuntime.NaNobj;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[73]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[122]++;

        // Find the end of the legal bit
        int decimal = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[123]++;
        int exponent = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[124]++;
        boolean exponentValid = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[125]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[13]++;


int CodeCoverConditionCoverageHelper_C29;
        for (;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[13]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[14]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[15]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[126]++;
            switch (s.charAt(i)) {
              case '.':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[78]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[127]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((decimal != -1) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[79]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[128]++; // Only allow a single decimal point.
                    break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[80]++;}
                decimal = i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[129]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[130]++;
                continue;

              case 'e':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[81]++;
              case 'E':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[82]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[131]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((exponent != -1) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[83]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[132]++;
                    break;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[84]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[133]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i == len - 1) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[85]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[134]++;
                    break;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[86]++;}
}
                exponent = i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[135]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[136]++;
                continue;

              case '+':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[87]++;
              case '-':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[88]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[137]++;
int CodeCoverConditionCoverageHelper_C33;
                 // Only allow '+' or '-' after 'e' or 'E'
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((exponent != i-1) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[89]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[138]++;
                    break;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[90]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[139]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i == len - 1) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[91]++;
                    --i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[140]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[141]++;
                    break;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[92]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[142]++;
                continue;

              case '0':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[93]++; case '1':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[94]++; case '2':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[95]++; case '3':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[96]++; case '4':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[97]++;
              case '5':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[98]++; case '6':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[99]++; case '7':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[100]++; case '8':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[101]++; case '9':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[102]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[143]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((exponent != -1) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[103]++;
                    exponentValid = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[144]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[104]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[145]++;
                continue;

              default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[105]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[146]++;
                break;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[147]++;
            break;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[148]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((exponent != -1) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((exponentValid) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[106]++;
            i = exponent;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[149]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[107]++;}
        s = s.substring(start, i);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[150]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[151]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return Double.valueOf(s);
        }
        catch (NumberFormatException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[109]++;
            return ScriptRuntime.NaNobj;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[108]++;
}
  }
    }

    /**
     * The global method escape, as per ECMA-262 15.1.2.4.

     * Includes code for the 'mask' argument supported by the C escape
     * method, which used to be part of the browser imbedding.  Blame
     * for the strange constant names should be directed there.
     */

    private Object js_escape(Object[] args) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[152]++;
        final int
            URL_XALPHAS = 1,
            URL_XPALPHAS = 2,
            URL_PATH = 4;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[153]++;

        String s = ScriptRuntime.toString(args, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[154]++;

        int mask = URL_XALPHAS | URL_XPALPHAS | URL_PATH;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[155]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((args.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[110]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[156]++; // the 'mask' argument.  Non-ECMA.
            double d = ScriptRuntime.toNumber(args[1]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[157]++;
            if (d != d || ((mask = (int) d) != d) || 0 != (mask & ~(URL_XALPHAS | URL_XPALPHAS | URL_PATH)))
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[112]++;
                throw Context.reportRuntimeError0("msg.bad.esc.mask");

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[113]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[111]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[158]++;

        StringBuffer sb = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[159]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[16]++;


int CodeCoverConditionCoverageHelper_C39;
        for (int k = 0, L = s.length();(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((k != L) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); ++k) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[16]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[17]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[18]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[160]++;
            int c = s.charAt(k);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[161]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (536870912)) == 0 || true) &&
 ((mask != 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (268435456)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C40 |= (134217728)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (67108864)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (33554432)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16777216)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C40 |= (8388608)) == 0 || true) &&
 ((c >= 'A') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4194304)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2097152)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1048576)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C40 |= (524288)) == 0 || true) &&
 ((c >= 'a') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (262144)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (131072)) == 0 || true) &&
 ((c <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (65536)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C40 |= (32768)) == 0 || true) &&
 ((c == '@') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16384)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (8192)) == 0 || true) &&
 ((c == '*') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4096)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2048)) == 0 || true) &&
 ((c == '_') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (512)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (128)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (64)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((0 != (mask & URL_PATH)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
))))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 15) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 15) && false))
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[114]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[162]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[116]++;
                    sb.append((char)c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[163]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[117]++;}

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[115]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[164]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[118]++;
                    sb = new StringBuffer(L + 3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[165]++;
                    sb.append(s);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[166]++;
                    sb.setLength(k);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[167]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[119]++;}

                int hexSize;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[168]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((c < 256) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[120]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[169]++;
int CodeCoverConditionCoverageHelper_C44;
                    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((c == ' ') && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((mask == URL_XPALPHAS) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[122]++;
                        sb.append('+');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[170]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[171]++;
                        continue;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[123]++;}
                    sb.append('%');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[172]++;
                    hexSize = 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[173]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[121]++;
                    sb.append('%');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[174]++;
                    sb.append('u');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[175]++;
                    hexSize = 4;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[176]++;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[177]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[19]++;


int CodeCoverConditionCoverageHelper_C45;

                // append hexadecimal form of c left-padded with 0
                for (int shift = (hexSize - 1) * 4;(((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((shift >= 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false); shift -= 4) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[19]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[20]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[21]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[178]++;
                    int digit = 0xf & (c >> shift);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[179]++;
                    int hc = (digit < 10) ? '0' + digit : 'A' - 10 + digit;
                    sb.append((char)hc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[180]++;
                }
            }
        }

        return (sb == null) ? s : sb.toString();
    }

    /**
     * The global unescape method, as per ECMA-262 15.1.2.5.
     */

    private Object js_unescape(Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[181]++;
        String s = ScriptRuntime.toString(args, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[182]++;
        int firstEscapePos = s.indexOf('%');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[183]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((firstEscapePos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[124]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[184]++;
            int L = s.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[185]++;
            char[] buf = s.toCharArray();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[186]++;
            int destination = firstEscapePos;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[187]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[22]++;


int CodeCoverConditionCoverageHelper_C47;
            for (int k = firstEscapePos;(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((k != L) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false);) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[22]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[23]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[24]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[188]++;
                char c = buf[k];
                ++k;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[189]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[190]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((c == '%') && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((k != L) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[126]++;
                    int end, start;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[191]++;
int CodeCoverConditionCoverageHelper_C49;
                    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((buf[k] == 'u') && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[128]++;
                        start = k + 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[192]++;
                        end = k + 5;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[193]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[129]++;
                        start = k;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[194]++;
                        end = k + 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[195]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[196]++;
int CodeCoverConditionCoverageHelper_C50;
                    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((end <= L) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[130]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[197]++;
                        int x = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[198]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[25]++;


int CodeCoverConditionCoverageHelper_C51;
                        for (int i = start;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[25]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[26]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[27]++;
}
                            x = Kit.xDigitToInt(buf[i], x);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[199]++;
                        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[200]++;
int CodeCoverConditionCoverageHelper_C52;
                        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((x >= 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[132]++;
                            c = (char)x;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[201]++;
                            k = end;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[202]++;

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[133]++;}

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[131]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[127]++;}
                buf[destination] = c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[203]++;
                ++destination;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[204]++;
            }
            s = new String(buf, 0, destination);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[205]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[125]++;}
        return s;
    }

    /**
     * This is an indirect call to eval, and thus uses the global environment.
     * Direct calls are executed via ScriptRuntime.callSpecial().
     */
    private Object js_eval(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[206]++;
        Scriptable global = ScriptableObject.getTopLevelScope(scope);
        return ScriptRuntime.evalSpecial(cx, global, global, args, "eval code", 1);
    }

    static boolean isEvalFunction(Object functionObj)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[207]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((functionObj instanceof IdFunctionObject) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[134]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[208]++;
            IdFunctionObject function = (IdFunctionObject)functionObj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[209]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((function.hasTag(FTAG)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((function.methodId() == Id_eval) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[136]++;
                return true;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[137]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[135]++;}
        return false;
    }

    /**
     * @deprecated Use {@link ScriptRuntime#constructError(String,String)}
     * instead.
     */
    public static EcmaError constructError(Context cx,
                                           String error,
                                           String message,
                                           Scriptable scope)
    {
        return ScriptRuntime.constructError(error, message);
    }

    /**
     * @deprecated Use
     * {@link ScriptRuntime#constructError(String,String,String,int,String,int)}
     * instead.
     */
    public static EcmaError constructError(Context cx,
                                           String error,
                                           String message,
                                           Scriptable scope,
                                           String sourceName,
                                           int lineNumber,
                                           int columnNumber,
                                           String lineSource)
    {
        return ScriptRuntime.constructError(error, message,
                                            sourceName, lineNumber,
                                            lineSource, columnNumber);
    }

    /*
    *   ECMA 3, 15.1.3 URI Handling Function Properties
    *
    *   The following are implementations of the algorithms
    *   given in the ECMA specification for the hidden functions
    *   'Encode' and 'Decode'.
    */
    private static String encode(String str, boolean fullUri) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[210]++;
        byte[] utf8buf = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[211]++;
        StringBuffer sb = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[212]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[28]++;


int CodeCoverConditionCoverageHelper_C55;

        for (int k = 0, length = str.length();(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((k != length) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); ++k) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[28]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[29]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[30]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[213]++;
            char C = str.charAt(k);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[214]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((encodeUnescaped(C, fullUri)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[138]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[215]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[140]++;
                    sb.append(C);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[216]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[141]++;}

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[139]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[217]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[142]++;
                    sb = new StringBuffer(length + 3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[218]++;
                    sb.append(str);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[219]++;
                    sb.setLength(k);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[220]++;
                    utf8buf = new byte[6];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[221]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[143]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[222]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((0xDC00 <= C) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((C <= 0xDFFF) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[144]++;
                    throw uriError();

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[145]++;}
                int V;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[223]++;
int CodeCoverConditionCoverageHelper_C60;
                if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((C < 0xD800) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((0xDBFF < C) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[146]++;
                    V = C;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[224]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[147]++;
                    k++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[225]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[226]++;
int CodeCoverConditionCoverageHelper_C61;
                    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((k == length) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[148]++;
                        throw uriError();

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[149]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[227]++;
                    char C2 = str.charAt(k);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[228]++;
int CodeCoverConditionCoverageHelper_C62;
                    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((0xDC00 <= C2) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((C2 <= 0xDFFF) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[150]++;
                        throw uriError();

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[151]++;}
                    V = ((C - 0xD800) << 10) + (C2 - 0xDC00) + 0x10000;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[229]++;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[230]++;
                int L = oneUcs4ToUtf8Char(utf8buf, V);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[231]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[31]++;


int CodeCoverConditionCoverageHelper_C63;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((j < L) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[31]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[32]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[33]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[232]++;
                    int d = 0xff & utf8buf[j];
                    sb.append('%');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[233]++;
                    sb.append(toHexChar(d >>> 4));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[234]++;
                    sb.append(toHexChar(d & 0xf));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[235]++;
                }
            }
        }
        return (sb == null) ? str : sb.toString();
    }

    private static char toHexChar(int i) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[236]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((i >> 4 != 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[152]++; Kit.codeBug();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[237]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[153]++;}
        return (char)((i < 10) ? i + '0' : i - 10 + 'A');
    }

    private static int unHex(char c) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[238]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 (('A' <= c) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((c <= 'F') && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[154]++;
            return c - 'A' + 10;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[155]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[239]++;
int CodeCoverConditionCoverageHelper_C66; if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((c <= 'f') && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[156]++;
            return c - 'a' + 10;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[157]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[240]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[158]++;
            return c - '0';

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[159]++;
            return -1;
        }
}
}
    }

    private static int unHex(char c1, char c2) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[241]++;
        int i1 = unHex(c1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[242]++;
        int i2 = unHex(c2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[243]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((i1 >= 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((i2 >= 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[160]++;
            return (i1 << 4) | i2;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[161]++;}
        return -1;
    }

    private static String decode(String str, boolean fullUri) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[244]++;
        char[] buf = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[245]++;
        int bufTop = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[246]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[34]++;


int CodeCoverConditionCoverageHelper_C69;

        for (int k = 0, length = str.length();(((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((k != length) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false);) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[34]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[35]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[36]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[247]++;
            char C = str.charAt(k);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[248]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((C != '%') && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[162]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[249]++;
int CodeCoverConditionCoverageHelper_C71;
                if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((buf != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[164]++;
                    buf[bufTop++] = C;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[250]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[165]++;}
                ++k;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[251]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[163]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[252]++;
int CodeCoverConditionCoverageHelper_C72;
                if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((buf == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[166]++;
                    // decode always compress so result can not be bigger then
                    // str.length()
                    buf = new char[length];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[253]++;
                    str.getChars(0, k, buf, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[254]++;
                    bufTop = k;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[255]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[167]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[256]++;
                int start = k;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[257]++;
int CodeCoverConditionCoverageHelper_C73;
                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((k + 3 > length) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[168]++;
                    throw uriError();
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[169]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[258]++;
                int B = unHex(str.charAt(k + 1), str.charAt(k + 2));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[259]++;
int CodeCoverConditionCoverageHelper_C74;
                if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((B < 0) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[170]++; throw uriError();
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[171]++;}
                k += 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[260]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[261]++;
int CodeCoverConditionCoverageHelper_C75;
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 (((B & 0x80) == 0) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[172]++;
                    C = (char)B;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[262]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[173]++;
                    // Decode UTF-8 sequence into ucs4Char and encode it into
                    // UTF-16
                    int utf8Tail, ucs4Char, minUcs4Char;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[263]++;
int CodeCoverConditionCoverageHelper_C76;
                    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 (((B & 0xC0) == 0x80) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[174]++;
                        // First  UTF-8 should be ouside 0x80..0xBF
                        throw uriError();

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[175]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[264]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 (((B & 0x20) == 0) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[176]++;
                        utf8Tail = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[265]++; ucs4Char = B & 0x1F;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[266]++;
                        minUcs4Char = 0x80;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[267]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[177]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[268]++;
int CodeCoverConditionCoverageHelper_C78; if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 (((B & 0x10) == 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[178]++;
                        utf8Tail = 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[269]++; ucs4Char = B & 0x0F;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[270]++;
                        minUcs4Char = 0x800;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[271]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[179]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[272]++;
int CodeCoverConditionCoverageHelper_C79; if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 (((B & 0x08) == 0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[180]++;
                        utf8Tail = 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[273]++; ucs4Char = B & 0x07;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[274]++;
                        minUcs4Char = 0x10000;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[275]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[181]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[276]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 (((B & 0x04) == 0) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[182]++;
                        utf8Tail = 4;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[277]++; ucs4Char = B & 0x03;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[278]++;
                        minUcs4Char = 0x200000;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[279]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[183]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[280]++;
int CodeCoverConditionCoverageHelper_C81; if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 (((B & 0x02) == 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[184]++;
                        utf8Tail = 5;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[281]++; ucs4Char = B & 0x01;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[282]++;
                        minUcs4Char = 0x4000000;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[283]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[185]++;
                        // First UTF-8 can not be 0xFF or 0xFE
                        throw uriError();
                    }
}
}
}
}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[284]++;
int CodeCoverConditionCoverageHelper_C82;
                    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((k + 3 * utf8Tail > length) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[186]++;
                        throw uriError();
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[187]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[285]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[37]++;


int CodeCoverConditionCoverageHelper_C83;
                    for (int j = 0;(((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((j != utf8Tail) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[37]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[38]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[39]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[286]++;
int CodeCoverConditionCoverageHelper_C84;
                        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((str.charAt(k) != '%') && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[188]++;
                            throw uriError();
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[189]++;}
                        B = unHex(str.charAt(k + 1), str.charAt(k + 2));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[287]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[288]++;
int CodeCoverConditionCoverageHelper_C85;
                        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((B < 0) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 (((B & 0xC0) != 0x80) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[190]++;
                            throw uriError();
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[191]++;}
                        ucs4Char = (ucs4Char << 6) | (B & 0x3F);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[289]++;
                        k += 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[290]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[291]++;
int CodeCoverConditionCoverageHelper_C86;
                    // Check for overlongs and other should-not-present codes
                    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (32)) == 0 || true) &&
 ((ucs4Char < minUcs4Char) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((ucs4Char >= 0xD800) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((ucs4Char <= 0xDFFF) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[192]++;
                        ucs4Char = INVALID_UTF8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[292]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[193]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[293]++;
int CodeCoverConditionCoverageHelper_C87; if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((ucs4Char == 0xFFFE) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((ucs4Char == 0xFFFF) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[194]++;
                        ucs4Char = 0xFFFD;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[294]++;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[195]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[295]++;
int CodeCoverConditionCoverageHelper_C88;
                    if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((ucs4Char >= 0x10000) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[196]++;
                        ucs4Char -= 0x10000;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[296]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[297]++;
int CodeCoverConditionCoverageHelper_C89;
                        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((ucs4Char > 0xFFFFF) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[198]++;
                            throw uriError();

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[199]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[298]++;
                        char H = (char)((ucs4Char >>> 10) + 0xD800);
                        C = (char)((ucs4Char & 0x3FF) + 0xDC00);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[299]++;
                        buf[bufTop++] = H;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[300]++;

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[197]++;
                        C = (char)ucs4Char;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[301]++;
                    }
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[302]++;
int CodeCoverConditionCoverageHelper_C90;
                if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (8)) == 0 || true) &&
 ((fullUri) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((URI_DECODE_RESERVED.indexOf(C) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[200]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[303]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[40]++;


int CodeCoverConditionCoverageHelper_C91;
                    for (int x = start;(((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((x != k) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false); x++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[40]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[41]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[42]++;
}
                        buf[bufTop++] = str.charAt(x);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[304]++;
                    }

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[201]++;
                    buf[bufTop++] = C;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[305]++;
                }
            }
        }
        return (buf == null) ? str : new String(buf, 0, bufTop);
    }

    private static boolean encodeUnescaped(char c, boolean fullUri) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[306]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C92 |= (2048)) == 0 || true) &&
 (('A' <= c) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C92 |= (512)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C92 |= (256)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C92 |= (128)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C92 |= (32)) == 0 || true) &&
 ((c <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C92 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 6) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 6) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[202]++;
            return true;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[203]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[307]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 (("-_.!~*'()".indexOf(c) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[204]++;
            return true;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[205]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[308]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((fullUri) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[206]++;
            return URI_DECODE_RESERVED.indexOf(c) >= 0;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[207]++;}
        return false;
    }

    private static EcmaError uriError() {
        return ScriptRuntime.constructError("URIError",
                ScriptRuntime.getMessage0("msg.bad.uri"));
    }

    private static final String URI_DECODE_RESERVED = ";/?:@&=+$,#";
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[309]++;
  }
    private static final int INVALID_UTF8 = Integer.MAX_VALUE;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[310]++;
  }

    /* Convert one UCS-4 char and write it into a UTF-8 buffer, which must be
    * at least 6 bytes long.  Return the number of UTF-8 bytes of data written.
    */
    private static int oneUcs4ToUtf8Char(byte[] utf8Buffer, int ucs4Char) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[311]++;
        int utf8Length = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[312]++;
int CodeCoverConditionCoverageHelper_C95;

        //JS_ASSERT(ucs4Char <= 0x7FFFFFFF);
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 (((ucs4Char & ~0x7F) == 0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[208]++;
            utf8Buffer[0] = (byte)ucs4Char;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[313]++;
}
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.branches[209]++;
            int i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[314]++;
            int a = ucs4Char >>> 11;
            utf8Length = 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[315]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[316]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[43]++;


int CodeCoverConditionCoverageHelper_C96;
            while ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((a != 0) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[43]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[44]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[45]++;
}
                a >>>= 5;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[317]++;
                utf8Length++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[318]++;
            }
            i = utf8Length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[319]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[320]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[46]++;


int CodeCoverConditionCoverageHelper_C97;
            while ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((--i > 0) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[46]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[47]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.loops[48]++;
}
                utf8Buffer[i] = (byte)((ucs4Char & 0x3F) | 0x80);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[321]++;
                ucs4Char >>>= 6;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[322]++;
            }
            utf8Buffer[0] = (byte)(0x100 - (1 << (8-utf8Length)) + ucs4Char);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[323]++;
        }
        return utf8Length;
    }

    private static final Object FTAG = "Global";
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[324]++;
  }

    private static final int
        Id_decodeURI           =  1,
        Id_decodeURIComponent  =  2,
        Id_encodeURI           =  3,
        Id_encodeURIComponent  =  4,
        Id_escape              =  5,
        Id_eval                =  6,
        Id_isFinite            =  7,
        Id_isNaN               =  8,
        Id_isXMLName           =  9,
        Id_parseFloat          = 10,
        Id_parseInt            = 11,
        Id_unescape            = 12,
        Id_uneval              = 13,

        LAST_SCOPE_FUNCTION_ID = 13,

        Id_new_CommonError     = 14;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt.statements[325]++;
  }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt ());
  }
    public static long[] statements = new long[326];
    public static long[] branches = new long[210];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[98];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeGlobal.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,0,1,2,3,2,1,2,2,2,1,0,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,0,1,3,1,1,1,2,1,1,1,2,1,1,1,1,1,2,1,1,1,1,2,2,1,2,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,2,1,1,2,1,3,1,1,1,1,1};
    for (int i = 1; i <= 97; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[49];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsa4yzt3kmxuhcj8spt () {
    super("org.mozilla.javascript.RHINO-SRC-NativeGlobal.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 325; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 209; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 97; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 48; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeGlobal.java");
      for (int i = 1; i <= 325; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 209; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 97; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 16; i++) {
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

