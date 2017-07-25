/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.json.JsonParser;

import java.util.Stack;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
 * This class implements the JSON native object.
 * See ECMA 15.12.
 */
public final class NativeJSON extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.ping();
  }

    static final long serialVersionUID = -4567599697595654984L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[1]++;
  }

    private static final Object JSON_TAG = "JSON";
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[2]++;
  }

    private static final int MAX_STRINGIFY_GAP_LENGTH = 10;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[3]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[4]++;
        NativeJSON obj = new NativeJSON();
        obj.activatePrototypeMap(MAX_ID);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[5]++;
        obj.setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[6]++;
        obj.setParentScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[7]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[1]++; obj.sealObject();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[9]++;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[2]++;}
        ScriptableObject.defineProperty(scope, "JSON", obj,
                                        ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[10]++;
    }

    private NativeJSON()
    {
    }

    @Override
    public String getClassName() { return "JSON"; }

    @Override
    protected void initPrototypeId(int id)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((id <= LAST_METHOD_ID) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[3]++;
            String name;
            int arity;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[12]++;
            switch (id) {
              case Id_toSource:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[5]++;  arity = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[13]++; name = "toSource";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[14]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[15]++;  break;
              case Id_parse:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[6]++;     arity = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[16]++; name = "parse";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[17]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[18]++;     break;
              case Id_stringify:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[7]++; arity = 3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[19]++; name = "stringify";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[20]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[21]++; break;
              default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[8]++; throw new IllegalStateException(String.valueOf(id));
            }
            initPrototypeMethod(JSON_TAG, id, name, arity);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[22]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[4]++;
            throw new IllegalStateException(String.valueOf(id));
        }
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((f.hasTag(JSON_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[9]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[10]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[24]++;
        int methodId = f.methodId();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[25]++;
        switch (methodId) {
            case Id_toSource:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[11]++;
                return "JSON";

            case Id_parse:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[12]++; {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[26]++;
                String jtext = ScriptRuntime.toString(args, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[27]++;
                Object reviver = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((args.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[13]++;
                    reviver = args[1];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[29]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[14]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((reviver instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[15]++;
                  return parse(cx, scope, jtext, (Callable) reviver);

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[16]++;
                  return parse(cx, scope, jtext);
                }
            }

            case Id_stringify:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[17]++; {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[31]++;
                Object value = null, replacer = null, space = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[32]++;
                switch (args.length) {
                    default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[18]++;
                    case 3:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[19]++; space = args[2];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[33]++;
                    case 2:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[20]++; replacer = args[1];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[34]++;
                    case 1:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[21]++; value = args[0];
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[35]++;
                    case 0:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[22]++;
                }
                return stringify(cx, scope, value, replacer, space);
            }

            default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[23]++; throw new IllegalStateException(String.valueOf(methodId));
        }
    }

    private static Object parse(Context cx, Scriptable scope, String jtext) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[36]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        return new JsonParser(cx, scope).parseValue(jtext);
      } catch (JsonParser.ParseException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[25]++;
        throw ScriptRuntime.constructError("SyntaxError", ex.getMessage());
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[24]++;
}
  }
    }

    public static Object parse(Context cx, Scriptable scope, String jtext,
                               Callable reviver)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[37]++;
      Object unfiltered = parse(cx, scope, jtext);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[38]++;
      Scriptable root = cx.newObject(scope);
      root.put("", root, unfiltered);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[39]++;
      return walk(cx, scope, reviver, root, "");
    }

    private static Object walk(Context cx, Scriptable scope, Callable reviver,
                               Scriptable holder, Object name)
    {
        final Object property;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[40]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((name instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[26]++;
            property = holder.get( ((Number) name).intValue(), holder);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[41]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[27]++;
            property = holder.get( ((String) name), holder);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[42]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[43]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((property instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[28]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[44]++;
            Scriptable val = ((Scriptable) property);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((val instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[30]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[46]++;
                long len = ((NativeArray) val).getLength();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[47]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
                for (long i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
                    // indices greater than MAX_INT are represented as strings
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[32]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[49]++;
                        String id = Long.toString(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[50]++;
                        Object newElement = walk(cx, scope, reviver, val, id);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
                        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((newElement == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[34]++;
                            val.delete(id);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[52]++;

                        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[35]++;
                            val.put(id, val, newElement);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[53]++;
                        }

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[33]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[54]++;
                        int idx = (int) i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[55]++;
                        Object newElement = walk(cx, scope, reviver, val, idx);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;
                        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((newElement == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[36]++;
                            val.delete(idx);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[57]++;

                        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[37]++;
                            val.put(idx, val, newElement);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[58]++;
                        }
                    }
                }

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[31]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[59]++;
                Object[] keys = val.getIds();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[60]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[4]++;


                for (Object p : keys) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[61]++;
                    Object newElement = walk(cx, scope, reviver, val, p);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[62]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((newElement == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[38]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
                        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((p instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[40]++;
                          val.delete(((Number) p).intValue());
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[64]++;
}
                        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[41]++;
                          val.delete((String) p);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[65]++;
}

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[39]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;
                        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((p instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[42]++;
                          val.put(((Number) p).intValue(), val, newElement);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[67]++;
}
                        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[43]++;
                          val.put((String) p, val, newElement);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[68]++;
}
                    }
                }
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[29]++;}

        return reviver.call(cx, scope, holder, new Object[] { name, property });
    }

    private static String repeat(char c, int count) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[69]++;
      char chars[] = new char[count];
      Arrays.fill(chars, c);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[70]++;
      return new String(chars);
    }

    private static class StringifyState {
        StringifyState(Context cx, Scriptable scope, String indent, String gap,
                       Callable replacer, List<Object> propertyList,
                       Object space)
        {
            this.cx = cx;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[71]++;
            this.scope = scope;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[72]++;

            this.indent = indent;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[73]++;
            this.gap = gap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[74]++;
            this.replacer = replacer;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[75]++;
            this.propertyList = propertyList;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[76]++;
            this.space = space;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[77]++;
        }

        Stack<Scriptable> stack = new Stack<Scriptable>();
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[78]++;
  }
        String indent;
        String gap;
        Callable replacer;
        List<Object> propertyList;
        Object space;

        Context cx;
        Scriptable scope;
    }

    public static Object stringify(Context cx, Scriptable scope, Object value,
                                   Object replacer, Object space)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[79]++;
        String indent = "";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[80]++;
        String gap = "";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[81]++;

        List<Object> propertyList = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[82]++;
        Callable replacerFunction = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[83]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((replacer instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[44]++;
          replacerFunction = (Callable) replacer;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[84]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[45]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[85]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((replacer instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[46]++;
          propertyList = new LinkedList<Object>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[86]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[87]++;
          NativeArray replacerArray = (NativeArray) replacer;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[88]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[7]++;


          for (int i : replacerArray.getIndexIds()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[9]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[89]++;
            Object v = replacerArray.get(i, replacerArray);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[90]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((v instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((v instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[48]++;
              propertyList.add(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[91]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[49]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[92]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((v instanceof NativeString) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((v instanceof NativeNumber) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[50]++;
              propertyList.add(ScriptRuntime.toString(v));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[93]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[51]++;}
}
          }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[47]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[94]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((space instanceof NativeNumber) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[52]++;
            space = ScriptRuntime.toNumber(space);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[95]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[53]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[96]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((space instanceof NativeString) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[54]++;
            space = ScriptRuntime.toString(space);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[97]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[55]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[98]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((space instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[56]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[99]++;
            int gapLength = (int) ScriptRuntime.toInteger(space);
            gapLength = Math.min(MAX_STRINGIFY_GAP_LENGTH, gapLength);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[100]++;
            gap = (gapLength > 0) ? repeat(' ', gapLength) : "";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[101]++;
            space = gapLength;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[102]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[57]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[103]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((space instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[58]++;
            gap = (String) space;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[104]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[105]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((gap.length() > MAX_STRINGIFY_GAP_LENGTH) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[60]++;
              gap = gap.substring(0, MAX_STRINGIFY_GAP_LENGTH);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[106]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[61]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[59]++;}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[107]++;

        StringifyState state = new StringifyState(cx, scope,
            indent,
            gap,
            replacerFunction,
            propertyList,
            space);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[108]++;

        ScriptableObject wrapper = new NativeObject();
        wrapper.setParentScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[109]++;
        wrapper.setPrototype(ScriptableObject.getObjectPrototype(scope));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[110]++;
        wrapper.defineProperty("", value, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[111]++;
        return str("", wrapper, state);
    }

    private static Object str(Object key, Scriptable holder,
                              StringifyState state)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[112]++;
        Object value = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[113]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((key instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[62]++;
            value = getProperty(holder, (String) key);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[114]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[63]++;
            value = getProperty(holder, ((Number) key).intValue());
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[115]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[116]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[64]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[117]++;
            Object toJSON = getProperty((Scriptable) value, "toJSON");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[118]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((toJSON instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[66]++;
                value = callMethod(state.cx, (Scriptable) value, "toJSON",
                                   new Object[] { key });
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[119]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[67]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[65]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[120]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((state.replacer != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[68]++;
            value = state.replacer.call(state.cx, state.scope, holder,
                                        new Object[] { key, value });
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[121]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[69]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[122]++;
int CodeCoverConditionCoverageHelper_C29;


        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((value instanceof NativeNumber) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[70]++;
            value = ScriptRuntime.toNumber(value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[123]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[71]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[124]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((value instanceof NativeString) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[72]++;
            value = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[125]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[73]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[126]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((value instanceof NativeBoolean) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[74]++;
            value = ((NativeBoolean) value).getDefaultValue(ScriptRuntime.BooleanClass);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[127]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[75]++;}
}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[128]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[76]++; return "null";
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[77]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[129]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((value.equals(Boolean.TRUE)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[78]++; return "true";
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[79]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[130]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((value.equals(Boolean.FALSE)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[80]++; return "false";
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[81]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[131]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((value instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[82]++;
            return quote(value.toString());

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[83]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[132]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[84]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[133]++;
            double d = ((Number) value).doubleValue();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[134]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((d == d) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((d != Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((d != Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) && false))
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[86]++;
                return ScriptRuntime.toString(value);

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[87]++;
                return "null";
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[85]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[135]++;
int CodeCoverConditionCoverageHelper_C38;

        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((value instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[88]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[136]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((value instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[90]++;
                return ja((NativeArray) value, state);

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[91]++;}
            return jo((Scriptable) value, state);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[89]++;}

        return Undefined.instance;
    }

    private static String join(Collection<Object> objs, String delimiter) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[137]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((objs == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((objs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[92]++;
            return "";

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[93]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[138]++;
        Iterator<Object> iter = objs.iterator();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[139]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((iter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[94]++; return "";
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[95]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[140]++;
        StringBuilder builder = new StringBuilder(iter.next().toString());
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[141]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[10]++;


int CodeCoverConditionCoverageHelper_C42;
        while ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((iter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[10]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[11]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[12]++;
}
            builder.append(delimiter).append(iter.next().toString());
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[142]++;
        }
        return builder.toString();
    }

    private static String jo(Scriptable value, StringifyState state) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[143]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((state.stack.search(value) != -1) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[96]++;
            throw ScriptRuntime.typeError0("msg.cyclic.value");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[97]++;}
        state.stack.push(value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[144]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[145]++;

        String stepback = state.indent;
        state.indent = state.indent + state.gap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[146]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[147]++;
        Object[] k = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[148]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((state.propertyList != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[98]++;
            k = state.propertyList.toArray();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[149]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[99]++;
            k = value.getIds();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[150]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[151]++;

        List<Object> partial = new LinkedList<Object>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[152]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[13]++;



        for (Object p : k) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[13]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[14]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[15]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[153]++;
            Object strP = str(p, value, state);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[154]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((strP != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[100]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[155]++;
                String member = quote(p.toString()) + ":";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[156]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((state.gap.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[102]++;
                    member = member + " ";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[157]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[103]++;}
                member = member + strP;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[158]++;
                partial.add(member);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[159]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[101]++;}
        }

        final String finalValue;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[160]++;
int CodeCoverConditionCoverageHelper_C47;

        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((partial.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[104]++;
            finalValue = "{}";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[161]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[105]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[162]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((state.gap.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[106]++;
                finalValue = '{' + join(partial, ",") + '}';
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[163]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[107]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[164]++;
                String separator = ",\n" + state.indent;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[165]++;
                String properties = join(partial, separator);
                finalValue = "{\n" + state.indent + properties + '\n' +
                    stepback + '}';
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[166]++;
            }
        }

        state.stack.pop();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[167]++;
        state.indent = stepback;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[168]++;
        return finalValue;
    }

    private static String ja(NativeArray value, StringifyState state) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[169]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((state.stack.search(value) != -1) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[108]++;
            throw ScriptRuntime.typeError0("msg.cyclic.value");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[109]++;}
        state.stack.push(value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[170]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[171]++;

        String stepback = state.indent;
        state.indent = state.indent + state.gap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[172]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[173]++;
        List<Object> partial = new LinkedList<Object>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[174]++;

        long len = value.getLength();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[175]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[16]++;


int CodeCoverConditionCoverageHelper_C50;
        for (long index = 0;(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((index < len) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[16]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[17]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[18]++;
}
            Object strP;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[176]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((index > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[110]++;
                strP = str(Long.toString(index), value, state);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[177]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[111]++;
                strP = str((int) index, value, state);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[178]++;
            }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[179]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((strP == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[112]++;
                partial.add("null");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[180]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[113]++;
                partial.add(strP);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[181]++;
            }
        }

        final String finalValue;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[182]++;
int CodeCoverConditionCoverageHelper_C53;

        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((partial.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[114]++;
            finalValue = "[]";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[183]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[115]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[184]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((state.gap.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[116]++;
                finalValue = '[' + join(partial, ",") + ']';
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[185]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[117]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[186]++;
                String separator = ",\n" + state.indent;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[187]++;
                String properties = join(partial, separator);
                finalValue = "[\n" + state.indent + properties + '\n' + stepback + ']';
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[188]++;
            }
        }

        state.stack.pop();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[189]++;
        state.indent = stepback;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[190]++;
        return finalValue;
    }

    private static String quote(String string) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[191]++;
        StringBuffer product = new StringBuffer(string.length()+2); // two extra chars for " on either side
        product.append('"');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[192]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[193]++;
        int length = string.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[194]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[19]++;


int CodeCoverConditionCoverageHelper_C55;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[19]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[20]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.loops[21]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[195]++;
            char c = string.charAt(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[196]++;
            switch (c) {
                case '"':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[118]++;
                    product.append("\\\"");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[197]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[198]++;
                    break;
                case '\\':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[119]++;
                    product.append("\\\\");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[199]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[200]++;
                    break;
                case '\b':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[120]++;
                    product.append("\\b");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[201]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[202]++;
                    break;
                case '\f':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[121]++;
                    product.append("\\f");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[203]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[204]++;
                    break;
                case '\n':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[122]++;
                    product.append("\\n");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[205]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[206]++;
                    break;
                case '\r':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[123]++;
                    product.append("\\r");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[207]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[208]++;
                    break;
                case '\t':
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[124]++;
                    product.append("\\t");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[209]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[210]++;
                    break;
                default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[125]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[211]++;
int CodeCoverConditionCoverageHelper_C56;
                    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((c < ' ') && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[126]++;
                        product.append("\\u");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[212]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[213]++;
                        String hex = String.format("%04x", (int) c);
                        product.append(hex);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[214]++;

                    }
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[127]++;
                        product.append(c);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[215]++;
                    }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[216]++;
                    break;
            }
        }
        product.append('"');
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[217]++;
        return product.toString();
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2009-05-25 16:01:00 EDT
        {   id = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[218]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[219]++; String X = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[220]++;
            L: switch (s.length()) {
            case 5:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[128]++; X="parse";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[221]++;id=Id_parse;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[222]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[223]++; break L;
            case 8:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[129]++; X="toSource";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[224]++;id=Id_toSource;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[225]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[226]++; break L;
            case 9:
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[130]++; X="stringify";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[227]++;id=Id_stringify;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[228]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[229]++; break L; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[131]++;
            }
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[230]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 3) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 3) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[132]++; id = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[231]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.branches[133]++;}
        }
// #/generated#
        return id;
    }

    private static final int
        Id_toSource     = 1,
        Id_parse        = 2,
        Id_stringify    = 3,
        LAST_METHOD_ID  = 3,
        MAX_ID          = 3;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt.statements[232]++;
  }

// #/string_id_map#
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt ());
  }
    public static long[] statements = new long[233];
    public static long[] branches = new long[134];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[58];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeJSON.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 57; i++) {
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0qika1qml34a4pt () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJSON.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 232; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 133; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 57; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJSON.java");
      for (int i = 1; i <= 232; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 133; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 57; i++) {
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

