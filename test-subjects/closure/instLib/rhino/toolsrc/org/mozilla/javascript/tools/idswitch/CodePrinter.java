/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.idswitch;

class CodePrinter {
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.ping();
  }


// length of u-type escape like \u12AB
    private static final int LITERAL_CHAR_MAX_SIZE = 6;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[1]++;
  }

    private String lineTerminator = "\n";
  {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[2]++;
  }

    private int indentStep = 4;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[3]++;
  }
    private int indentTabSize = 8;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[4]++;
  }

    private char[] buffer = new char[1 << 12];
  {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[5]++;
  } // 4K
    private int offset;

    public String getLineTerminator() { return lineTerminator; }
    public void setLineTerminator(String value) { lineTerminator = value;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[6]++; }

    public int getIndentStep() { return indentStep; }
    public void setIndentStep(int char_count) { indentStep = char_count;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[7]++; }

    public int getIndentTabSize() {    return indentTabSize; }
    public void setIndentTabSize(int tab_size) { indentTabSize = tab_size;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[8]++; }

    public void clear() {
        offset = 0;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[9]++;
    }

    private int ensure_area(int area_size) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[10]++;
        int begin = offset;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[11]++;
        int end = begin + area_size;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end > buffer.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[1]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[13]++;
            int new_capacity = buffer.length * 2;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((end > new_capacity) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[3]++; new_capacity = end;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[15]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[4]++;}
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[16]++;
            char[] tmp = new char[new_capacity];
            System.arraycopy(buffer, 0, tmp, 0, begin);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[17]++;
            buffer = tmp;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[18]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[2]++;}
        return begin;
    }

    private int add_area(int area_size) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[19]++;
        int pos = ensure_area(area_size);
        offset = pos + area_size;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[20]++;
        return pos;
    }

    public int getOffset() {
        return offset;
    }

    public int getLastChar() {
        return offset == 0 ? -1 : buffer[offset - 1];
    }

    public void p(char c) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[21]++;
        int pos = add_area(1);
        buffer[pos] = c;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[22]++;
    }

    public void p(String s) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[23]++;
        int l = s.length();
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[24]++;
        int pos = add_area(l);
        s.getChars(0, l, buffer, pos);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[25]++;
    }

    public final void p(char[] array) {
        p(array, 0, array.length);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[26]++;
    }

    public void p(char[] array, int begin, int end) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[27]++;
        int l = end - begin;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[28]++;
        int pos = add_area(l);
        System.arraycopy(array, begin, buffer, pos, l);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[29]++;
    }

    public void p(int i) {
        p(Integer.toString(i));
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[30]++;
    }

    public void qchar(int c) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[31]++;
        int pos = ensure_area(2 + LITERAL_CHAR_MAX_SIZE);
        buffer[pos] = '\'';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[32]++;
        pos = put_string_literal_char(pos + 1, c, false);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[33]++;
        buffer[pos] = '\'';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[34]++;
        offset = pos + 1;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[35]++;
    }

    public void qstring(String s) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[36]++;
        int l = s.length();
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[37]++;
        int pos = ensure_area(2 + LITERAL_CHAR_MAX_SIZE * l);
        buffer[pos] = '"';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[38]++;
        ++pos;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[39]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[40]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i != l) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[1]--;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[2]--;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[3]++;
}
            pos = put_string_literal_char(pos, s.charAt(i), true);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[41]++;
        }
        buffer[pos] = '"';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[42]++;
        offset = pos + 1;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[43]++;
    }

    private int put_string_literal_char(int pos, int c, boolean in_string) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[44]++;
        boolean backslash_symbol = true;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[45]++;
        switch (c) {
            case '\b':
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[5]++; c = 'b';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[46]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[47]++; break;
            case '\t':
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[6]++; c = 't';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[48]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[49]++; break;
            case '\n':
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[7]++; c = 'n';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[50]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[51]++; break;
            case '\f':
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[8]++; c = 'f';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[52]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[53]++; break;
            case '\r':
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[9]++; c = 'r';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[54]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[55]++; break;
            case '\'':
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[10]++; backslash_symbol = !in_string;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[56]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[57]++; break;
            case '"':
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[11]++; backslash_symbol = in_string;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[58]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[59]++; break;
            default:
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[12]++; backslash_symbol = false;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[60]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[61]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((backslash_symbol) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[13]++;
            buffer[pos] = '\\';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[62]++;
            buffer[pos + 1] = (char)c;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[63]++;
            pos += 2;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[64]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[14]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[65]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((' ' <= c) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c <= 126) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[15]++;
            buffer[pos] = (char)c;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[66]++;
            ++pos;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[67]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[16]++;
            buffer[pos] = '\\';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[68]++;
            buffer[pos + 1] = 'u';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[69]++;
            buffer[pos + 2] = digit_to_hex_letter(0xF & (c >> 12));
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[70]++;
            buffer[pos + 3] = digit_to_hex_letter(0xF & (c >> 8));
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[71]++;
            buffer[pos + 4] = digit_to_hex_letter(0xF & (c >> 4));
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[72]++;
            buffer[pos + 5] = digit_to_hex_letter(0xF & c);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[73]++;
            pos += 6;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[74]++;
        }
}
        return pos;
    }

    private static char digit_to_hex_letter(int d) {
        return (char)((d < 10) ? '0' + d : 'A' - 10 + d);
    }

    public void indent(int level) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[75]++;
        int visible_size = indentStep * level;
        int indent_size, tab_count;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[76]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((indentTabSize <= 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[17]++;
            tab_count = 0;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[77]++; indent_size = visible_size;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[78]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.branches[18]++;
            tab_count = visible_size / indentTabSize;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[79]++;
            indent_size = tab_count + visible_size % indentTabSize;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[80]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[81]++;
        int pos = add_area(indent_size);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[82]++;
        int tab_end = pos + tab_count;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[83]++;
        int indent_end = pos + indent_size;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[84]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
        for (;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((pos != tab_end) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++pos) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[4]--;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[5]--;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[6]++;
}    buffer[pos] = '\t';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[85]++; }
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[86]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
        for (;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((pos != indent_end) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); ++pos) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[7]--;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[8]--;
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.loops[9]++;
}    buffer[pos] = ' ';
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[87]++; }
    }

    public void nl() {
        p('\n');
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[88]++;
    }

    public void line(int indent_level, String s) {
        indent(indent_level);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[89]++; p(s);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[90]++; nl();
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[91]++;
    }

    public void erase(int begin, int end) {
        System.arraycopy(buffer, end, buffer, begin, offset - end);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[92]++;
        offset -= end - begin;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1.statements[93]++;
    }

    @Override
    public String toString() {
        return new String(buffer, 0, offset);
    }



}

class CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1 ());
  }
    public static long[] statements = new long[94];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.idswitch.RHINO-TOO-CodePrinter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 8; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$qmzdwrwnkgu76c4iijnt9a16dny2pb1lk1jxsrc1 () {
    super("org.mozilla.javascript.tools.idswitch.RHINO-TOO-CodePrinter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 93; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.idswitch.RHINO-TOO-CodePrinter.java");
      for (int i = 1; i <= 93; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

