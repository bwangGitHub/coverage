/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.json;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptRuntime;

import java.util.ArrayList;
import java.util.List;

/**
 * This class converts a stream of JSON tokens into a JSON value.
 *
 * See ECMA 15.12.
 */
public class JsonParser {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.ping();
  }


    private Context cx;
    private Scriptable scope;

    private int pos;
    private int length;
    private String src;

    public JsonParser(Context cx, Scriptable scope) {
        this.cx = cx;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[1]++;
        this.scope = scope;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[2]++;
    }

    public synchronized Object parseValue(String json) throws ParseException {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((json == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[1]++;
            throw new ParseException("Input string may not be null");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[2]++;}
        pos = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[4]++;
        length = json.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[5]++;
        src = json;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[6]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[7]++;
        Object value = readValue();
        consumeWhitespace();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[8]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[3]++;
            throw new ParseException("Expected end of stream at char " + pos);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[4]++;}
        return value;
    }

    private Object readValue() throws ParseException {
        consumeWhitespace();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[10]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[12]++;
            char c = src.charAt(pos++);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[13]++;
            switch (c) {
                case '{':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[5]++;
                    return readObject();
                case '[':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[6]++;
                    return readArray();
                case 't':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[7]++;
                    return readTrue();
                case 'f':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[8]++;
                    return readFalse();
                case '"':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[9]++;
                    return readString();
                case 'n':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[10]++;
                    return readNull();
                case '1':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[11]++;
                case '2':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[12]++;
                case '3':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[13]++;
                case '4':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[14]++;
                case '5':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[15]++;
                case '6':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[16]++;
                case '7':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[17]++;
                case '8':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[18]++;
                case '9':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[19]++;
                case '0':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[20]++;
                case '-':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[21]++;
                    return readNumber(c);
                default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[22]++;
                    throw new ParseException("Unexpected token: " + c);
            }
        }
        throw new ParseException("Empty JSON string");
    }

    private Object readObject() throws ParseException {
        consumeWhitespace();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[14]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[15]++;
        Scriptable object = cx.newObject(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        // handle empty object literal case early
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((src.charAt(pos) == '}') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[23]++;
            pos += 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[17]++;
            return object;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[24]++;}
        String id;
        Object value;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[18]++;
        boolean needsComma = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[20]++;
            char c = src.charAt(pos++);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[21]++;
            switch(c) {
                case '}':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[25]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((needsComma) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[26]++;
                        throw new ParseException("Unexpected comma in object literal");

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[27]++;}
                    return object;
                case ',':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[28]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((needsComma) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[29]++;
                        throw new ParseException("Unexpected comma in object literal");

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[30]++;}
                    needsComma = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[24]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[25]++;
                    break;
                case '"':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[31]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((needsComma) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[32]++;
                        throw new ParseException("Missing comma in object literal");

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[33]++;}
                    id = readString();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[27]++;
                    consume(':');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[28]++;
                    value = readValue();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[29]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[30]++;

                    long index = ScriptRuntime.indexFromString(id);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
                    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[34]++;
                      object.put(id, object, value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[32]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[35]++;
                      object.put((int)index, object, value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[33]++;
                    }

                    needsComma = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[34]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[35]++;
                    break;
                default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[36]++;
                    throw new ParseException("Unexpected token in object literal");
            }
            consumeWhitespace();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[36]++;
        }
        throw new ParseException("Unterminated object literal");
    }

    private Object readArray() throws ParseException {
        consumeWhitespace();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[37]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
        // handle empty array literal case early
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((src.charAt(pos) == ']') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[37]++;
            pos += 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[39]++;
            return cx.newArray(scope, 0);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[38]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[40]++;
        List<Object> list = new ArrayList<Object>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[41]++;
        boolean needsComma = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[42]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[7]++;


int CodeCoverConditionCoverageHelper_C11;
        while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[9]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[43]++;
            char c = src.charAt(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[44]++;
            switch(c) {
                case ']':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[39]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((needsComma) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[40]++;
                        throw new ParseException("Unexpected comma in array literal");

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[41]++;}
                    pos += 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[46]++;
                    return cx.newArray(scope, list.toArray());
                case ',':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[42]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[47]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((needsComma) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[43]++;
                        throw new ParseException("Unexpected comma in array literal");

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[44]++;}
                    needsComma = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[48]++;
                    pos += 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[49]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[50]++;
                    break;
                default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[45]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((needsComma) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[46]++;
                        throw new ParseException("Missing comma in array literal");

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[47]++;}
                    list.add(readValue());
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[52]++;
                    needsComma = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[53]++;
            }
            consumeWhitespace();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[54]++;
        }
        throw new ParseException("Unterminated array literal");
    }

    private String readString() throws ParseException {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[55]++;
        /*
         * Optimization: if the source contains no escaped characters, create the
         * string directly from the source text.
         */
        int stringStart = pos;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[56]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[10]++;


int CodeCoverConditionCoverageHelper_C15;
        while ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[10]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[11]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[12]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[57]++;
            char c = src.charAt(pos++);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[58]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c <= '\u001F') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[48]++;
                throw new ParseException("String contains control character");

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[49]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[59]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[50]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[60]++;
                break;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[51]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[61]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c == '"') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[52]++;
                return src.substring(stringStart, pos - 1);

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[53]++;}
}
}
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[62]++;

        /*
         * Slow case: string contains escaped characters.  Copy a maximal sequence
         * of unescaped characters into a temporary buffer, then an escaped
         * character, and repeat until the entire string is consumed.
         */
        StringBuilder b = new StringBuilder();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[63]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;
        while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[13]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[14]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[15]++;
}
            assert src.charAt(pos - 1) == '\\';
            b.append(src, stringStart, pos - 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[64]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[65]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((pos >= length) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[54]++;
                throw new ParseException("Unterminated string");

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[55]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[66]++;
            char c = src.charAt(pos++);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[67]++;
            switch (c) {
                case '"':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[56]++;
                    b.append('"');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[68]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[69]++;
                    break;
                case '\\':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[57]++;
                    b.append('\\');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[70]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[71]++;
                    break;
                case '/':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[58]++;
                    b.append('/');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[72]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[73]++;
                    break;
                case 'b':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[59]++;
                    b.append('\b');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[74]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[75]++;
                    break;
                case 'f':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[60]++;
                    b.append('\f');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[76]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[77]++;
                    break;
                case 'n':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[61]++;
                    b.append('\n');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[78]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[79]++;
                    break;
                case 'r':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[62]++;
                    b.append('\r');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[80]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[81]++;
                    break;
                case 't':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[63]++;
                    b.append('\t');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[82]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[83]++;
                    break;
                case 'u':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[64]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[84]++;
int CodeCoverConditionCoverageHelper_C21;
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((length - pos < 5) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[65]++;
                        throw new ParseException("Invalid character code: \\u" + src.substring(pos));

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[66]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[85]++;
                    int code = fromHex(src.charAt(pos + 0)) << 12
                             | fromHex(src.charAt(pos + 1)) << 8
                             | fromHex(src.charAt(pos + 2)) << 4
                             | fromHex(src.charAt(pos + 3));
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[86]++;
int CodeCoverConditionCoverageHelper_C22;
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((code < 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[67]++;
                        throw new ParseException("Invalid character code: " + src.substring(pos, pos + 4));

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[68]++;}
                    pos += 4;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[87]++;
                    b.append((char) code);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[88]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[89]++;
                    break;
                default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[69]++;
                    throw new ParseException("Unexpected character in string: '\\" + c + "'");
            }
            stringStart = pos;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[90]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[91]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[16]++;


int CodeCoverConditionCoverageHelper_C23;
            while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[16]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[17]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[18]++;
}
                c = src.charAt(pos++);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[92]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[93]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((c <= '\u001F') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[70]++;
                    throw new ParseException("String contains control character");

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[71]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[94]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[72]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[95]++;
                    break;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[73]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[96]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((c == '"') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[74]++;
                    b.append(src, stringStart, pos - 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[97]++;
                    return b.toString();

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[75]++;}
}
}
            }
        }
        throw new ParseException("Unterminated string literal");
    }

    private int fromHex(char c) {
        return c >= '0' && c <= '9' ? c - '0'
                : c >= 'A' && c <= 'F' ? c - 'A' + 10
                : c >= 'a' && c <= 'f' ? c - 'a' + 10
                : -1;
    }

    private Number readNumber(char c) throws ParseException {
        assert c == '-' || (c >= '0' && c <= '9');
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[98]++;
        final int numberStart = pos - 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[99]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[76]++;
            c = nextOrNumberError(numberStart);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[100]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[101]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[78]++;
                throw numberError(numberStart, pos);

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[79]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[77]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[102]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((c != '0') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[80]++;
            readDigits();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[103]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[81]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[104]++;
int CodeCoverConditionCoverageHelper_C30;
        // read optional fraction part
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[82]++;
            c = src.charAt(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[105]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[106]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[84]++;
                pos += 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[107]++;
                c = nextOrNumberError(numberStart);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[108]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[109]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[86]++;
                    throw numberError(numberStart, pos);

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[87]++;}
                readDigits();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[110]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[85]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[83]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[111]++;
int CodeCoverConditionCoverageHelper_C33;
        // read optional exponent part
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[88]++;
            c = src.charAt(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[112]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[113]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((c == 'e') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((c == 'E') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[90]++;
                pos += 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[114]++;
                c = nextOrNumberError(numberStart);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[115]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[116]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[92]++;
                    c = nextOrNumberError(numberStart);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[117]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[93]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[118]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[94]++;
                    throw numberError(numberStart, pos);

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[95]++;}
                readDigits();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[119]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[91]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[89]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[120]++;
        String num = src.substring(numberStart, pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[121]++;
        final double dval = Double.parseDouble(num);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[122]++;
        final int ival = (int)dval;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[123]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((ival == dval) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[96]++;
            return Integer.valueOf(ival);

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[97]++;
            return Double.valueOf(dval);
        }
    }

    private ParseException numberError(int start, int end) {
        return new ParseException("Unsupported number format: " + src.substring(start, end));
    }

    private char nextOrNumberError(int numberStart) throws ParseException {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[124]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((pos >= length) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[98]++;
            throw numberError(numberStart, length);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[99]++;}
        return src.charAt(pos++);
    }

    private void readDigits() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[125]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[19]++;


int CodeCoverConditionCoverageHelper_C39;
        for (;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); ++pos) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[19]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[20]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[21]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[126]++;
            char c = src.charAt(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[127]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[100]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[128]++;
                break;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[101]++;}
        }
    }

    private Boolean readTrue() throws ParseException {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[129]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (128)) == 0 || true) &&
 ((length - pos < 3) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (32)) == 0 || true) &&
 ((src.charAt(pos) != 'r') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((src.charAt(pos + 1) != 'u') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((src.charAt(pos + 2) != 'e') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[102]++;
            throw new ParseException("Unexpected token: t");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[103]++;}
        pos += 3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[130]++;
        return Boolean.TRUE;
    }

    private Boolean readFalse() throws ParseException {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[131]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (512)) == 0 || true) &&
 ((length - pos < 4) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (128)) == 0 || true) &&
 ((src.charAt(pos) != 'a') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (32)) == 0 || true) &&
 ((src.charAt(pos + 1) != 'l') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((src.charAt(pos + 2) != 's') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((src.charAt(pos + 3) != 'e') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 5) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 5) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[104]++;
            throw new ParseException("Unexpected token: f");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[105]++;}
        pos += 4;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[132]++;
        return Boolean.FALSE;
    }

    private Object readNull() throws ParseException {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[133]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (128)) == 0 || true) &&
 ((length - pos < 3) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (32)) == 0 || true) &&
 ((src.charAt(pos) != 'u') && 
  ((CodeCoverConditionCoverageHelper_C43 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((src.charAt(pos + 1) != 'l') && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((src.charAt(pos + 2) != 'l') && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 4) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 4) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[106]++;
            throw new ParseException("Unexpected token: n");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[107]++;}
        pos += 3;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[134]++;
        return null;
    }

    private void consumeWhitespace() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[135]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[22]++;


int CodeCoverConditionCoverageHelper_C44;
        while ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((pos < length) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[22]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[23]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.loops[24]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[136]++;
            char c = src.charAt(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[137]++;
            switch (c) {
                case ' ':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[108]++;
                case '\t':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[109]++;
                case '\r':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[110]++;
                case '\n':
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[111]++;
                    pos += 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[138]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[139]++;
                    break;
                default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[112]++;
                    return;
            }
        }
    }

    private void consume(char token) throws ParseException {
        consumeWhitespace();
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[140]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[141]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((pos >= length) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[113]++;
            throw new ParseException("Expected " + token + " but reached end of stream");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[114]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[142]++;
        char c = src.charAt(pos++);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[143]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((c == token) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[115]++;
            return;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.branches[116]++;
            throw new ParseException("Expected " + token + " found " + c);
        }
    }

    public static class ParseException extends Exception {

        static final long serialVersionUID = 4804542791749920772L;

        ParseException(String message) {
            super(message);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[144]++;
        }

        ParseException(Exception cause) {
            super(cause);
CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h.statements[145]++;
        }
    }

}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h ());
  }
    public static long[] statements = new long[146];
    public static long[] branches = new long[117];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[47];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.json.RHINO-SRC-JsonParser.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,2,2,2,1,1,1,2,3,3,3,1,1,1};
    for (int i = 1; i <= 46; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw6whftmyunmw30uuicpb378h () {
    super("org.mozilla.javascript.json.RHINO-SRC-JsonParser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 145; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 116; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 46; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.json.RHINO-SRC-JsonParser.java");
      for (int i = 1; i <= 145; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 116; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 46; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

