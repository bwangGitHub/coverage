/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.ast.FunctionNode;

/**
 * The following class save decompilation information about the source.
 * Source information is returned from the parser as a String
 * associated with function nodes and with the toplevel script.  When
 * saved in the constant pool of a class, this string will be UTF-8
 * encoded, and token values will occupy a single byte.

 * Source is saved (mostly) as token numbers.  The tokens saved pretty
 * much correspond to the token stream of a 'canonical' representation
 * of the input program, as directed by the parser.  (There were a few
 * cases where tokens could have been left out where decompiler could
 * easily reconstruct them, but I left them in for clarity).  (I also
 * looked adding source collection to TokenStream instead, where I
 * could have limited the changes to a few lines in getToken... but
 * this wouldn't have saved any space in the resulting source
 * representation, and would have meant that I'd have to duplicate
 * parser logic in the decompiler to disambiguate situations where
 * newlines are important.)  The function decompile expands the
 * tokens back into their string representations, using simple
 * lookahead to correct spacing and indentation.
 *
 * Assignments are saved as two-token pairs (Token.ASSIGN, op). Number tokens
 * are stored inline, as a NUMBER token, a character representing the type, and
 * either 1 or 4 characters representing the bit-encoding of the number.  String
 * types NAME, STRING and OBJECT are currently stored as a token type,
 * followed by a character giving the length of the string (assumed to
 * be less than 2^16), followed by the characters of the string
 * inlined into the source string.  Changing this to some reference to
 * to the string in the compiled class' constant pool would probably
 * save a lot of space... but would require some method of deriving
 * the final constant pool entry from information available at parse
 * time.
 */
public class Decompiler
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.ping();
  }

    /**
     * Flag to indicate that the decompilation should omit the
     * function header and trailing brace.
     */
    public static final int ONLY_BODY_FLAG = 1 << 0;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[1]++;
  }

    /**
     * Flag to indicate that the decompilation generates toSource result.
     */
    public static final int TO_SOURCE_FLAG = 1 << 1;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[2]++;
  }

    /**
     * Decompilation property to specify initial ident value.
     */
    public static final int INITIAL_INDENT_PROP = 1;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[3]++;
  }

    /**
     * Decompilation property to specify default identation offset.
     */
    public static final int INDENT_GAP_PROP = 2;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[4]++;
  }

    /**
     * Decompilation property to specify identation offset for case labels.
     */
    public static final int CASE_GAP_PROP = 3;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[5]++;
  }

    // Marker to denote the last RC of function so it can be distinguished from
    // the last RC of object literals in case of function expressions
    private static final int FUNCTION_END = Token.LAST_TOKEN + 1;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[6]++;
  }

    String getEncodedSource()
    {
        return sourceToString(0);
    }

    int getCurrentOffset()
    {
        return sourceTop;
    }

    int markFunctionStart(int functionType)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[7]++;
        int savedOffset = getCurrentOffset();
        addToken(Token.FUNCTION);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[8]++;
        append((char)functionType);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[9]++;
        return savedOffset;
    }

    int markFunctionEnd(int functionStart)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[10]++;
        int offset = getCurrentOffset();
        append((char)FUNCTION_END);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[11]++;
        return offset;
    }

    void addToken(int token)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((0 <= token) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((token <= Token.LAST_TOKEN) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[1]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[2]++;}

        append((char)token);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[13]++;
    }

    void addEOL(int token)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((0 <= token) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((token <= Token.LAST_TOKEN) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[3]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[4]++;}

        append((char)token);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[15]++;
        append((char)Token.EOL);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[16]++;
    }

    void addName(String str)
    {
        addToken(Token.NAME);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[17]++;
        appendString(str);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[18]++;
    }

    void addString(String str)
    {
        addToken(Token.STRING);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[19]++;
        appendString(str);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[20]++;
    }

    void addRegexp(String regexp, String flags)
    {
        addToken(Token.REGEXP);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[21]++;
        appendString('/' + regexp + '/' + flags);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[22]++;
    }

    void addNumber(double n)
    {
        addToken(Token.NUMBER);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[23]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[24]++;

        /* encode the number in the source stream.
         * Save as NUMBER type (char | char char char char)
         * where type is
         * 'D' - double, 'S' - short, 'J' - long.

         * We need to retain float vs. integer type info to keep the
         * behavior of liveconnect type-guessing the same after
         * decompilation.  (Liveconnect tries to present 1.0 to Java
         * as a float/double)
         * OPT: This is no longer true. We could compress the format.

         * This may not be the most space-efficient encoding;
         * the chars created below may take up to 3 bytes in
         * constant pool UTF-8 encoding, so a Double could take
         * up to 12 bytes.
         */

        long lbits = (long)n;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lbits != n) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[5]++;
            // if it's floating point, save as a Double bit pattern.
            // (12/15/97 our scanner only returns Double for f.p.)
            lbits = Double.doubleToLongBits(n);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[26]++;
            append('D');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[27]++;
            append((char)(lbits >> 48));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[28]++;
            append((char)(lbits >> 32));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[29]++;
            append((char)(lbits >> 16));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[30]++;
            append((char)lbits);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[31]++;

        }
        else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[6]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
            // we can ignore negative values, bc they're already prefixed
            // by NEG
               if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lbits < 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[7]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[33]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[8]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;

            // will it fit in a char?
            // this gives a short encoding for integer values up to 2^16.
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((lbits <= Character.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[9]++;
                append('S');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[35]++;
                append((char)lbits);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[36]++;

            }
            else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[10]++; // Integral, but won't fit in a char. Store as a long.
                append('J');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[37]++;
                append((char)(lbits >> 48));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[38]++;
                append((char)(lbits >> 32));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[39]++;
                append((char)(lbits >> 16));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[40]++;
                append((char)lbits);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[41]++;
            }
        }
    }

    private void appendString(String str)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[42]++;
        int L = str.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[43]++;
        int lengthEncodingSize = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[44]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((L >= 0x8000) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[11]++;
            lengthEncodingSize = 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[45]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[12]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[46]++;
        int nextTop = sourceTop + lengthEncodingSize + L;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nextTop > sourceBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[13]++;
            increaseSourceCapacity(nextTop);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[48]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[14]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[49]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((L >= 0x8000) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[15]++;
            // Use 2 chars to encode strings exceeding 32K, were the highest
            // bit in the first char indicates presence of the next byte
            sourceBuffer[sourceTop] = (char)(0x8000 | (L >>> 16));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[50]++;
            ++sourceTop;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[51]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[16]++;}
        sourceBuffer[sourceTop] = (char)L;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[52]++;
        ++sourceTop;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[53]++;
        str.getChars(0, L, sourceBuffer, sourceTop);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[54]++;
        sourceTop = nextTop;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[55]++;
    }

    private void append(char c)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[56]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sourceTop == sourceBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[17]++;
            increaseSourceCapacity(sourceTop + 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[57]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[18]++;}
        sourceBuffer[sourceTop] = c;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[58]++;
        ++sourceTop;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[59]++;
    }

    private void increaseSourceCapacity(int minimalCapacity)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[60]++;
int CodeCoverConditionCoverageHelper_C10;
        // Call this only when capacity increase is must
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((minimalCapacity <= sourceBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[19]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[61]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[20]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[62]++;
        int newCapacity = sourceBuffer.length * 2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((newCapacity < minimalCapacity) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[21]++;
            newCapacity = minimalCapacity;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[64]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[22]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[65]++;
        char[] tmp = new char[newCapacity];
        System.arraycopy(sourceBuffer, 0, tmp, 0, sourceTop);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[66]++;
        sourceBuffer = tmp;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[67]++;
    }

    private String sourceToString(int offset)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((offset < 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((sourceTop < offset) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[23]++; Kit.codeBug();
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[69]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[24]++;}
        return new String(sourceBuffer, offset, sourceTop - offset);
    }

    /**
     * Decompile the source information associated with this js
     * function/script back into a string.  For the most part, this
     * just means translating tokens back to their string
     * representations; there's a little bit of lookahead logic to
     * decide the proper spacing/indentation.  Most of the work in
     * mapping the original source to the prettyprinted decompiled
     * version is done by the parser.
     *
     * @param source encoded source tree presentation
     *
     * @param flags flags to select output format
     *
     * @param properties indentation properties
     *
     */
    public static String decompile(String source, int flags,
                                   UintMap properties)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[70]++;
        int length = source.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[71]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[25]++; return "";
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[26]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[72]++;

        int indent = properties.getInt(INITIAL_INDENT_PROP, 0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[73]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((indent < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[27]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[28]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[74]++;
        int indentGap = properties.getInt(INDENT_GAP_PROP, 4);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[75]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((indentGap < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[29]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[30]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[76]++;
        int caseGap = properties.getInt(CASE_GAP_PROP, 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((caseGap < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[31]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[32]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[78]++;

        StringBuffer result = new StringBuffer();
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[79]++;
        boolean justFunctionBody = (0 != (flags & Decompiler.ONLY_BODY_FLAG));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[80]++;
        boolean toSource = (0 != (flags & Decompiler.TO_SOURCE_FLAG));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;

        // Spew tokens in source, for debugging.
        // as TYPE number char
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((printSource) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[33]++;
            System.err.println("length:" + length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[82]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[83]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[1]++;


int CodeCoverConditionCoverageHelper_C18;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[84]++;
                // Note that tokenToName will fail unless Context.printTrees
                // is true.
                String tokenname = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[85]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((Token.printNames) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[35]++;
                    tokenname = Token.name(source.charAt(i));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[86]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[36]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[87]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((tokenname == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[37]++;
                    tokenname = "---";
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[88]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[38]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[89]++;
                String pad = tokenname.length() > 7
                    ? "\t"
                    : "\t\t";
                System.err.println
                    (tokenname
                     + pad + (int)source.charAt(i)
                     + "\t'" + ScriptRuntime.escapeString
                     (source.substring(i, i+1))
                     + "'");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[90]++;
            }
            System.err.println();
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[91]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[34]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[92]++;

        int braceNesting = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[93]++;
        boolean afterFirstEOL = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[94]++;
        int i = 0;
        int topFunctionType;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[95]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((source.charAt(i) == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[39]++;
            ++i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[96]++;
            topFunctionType = -1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[97]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[40]++;
            topFunctionType = source.charAt(i + 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[98]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[99]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((toSource) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[41]++;
            // add an initial newline to exactly match js.
            result.append('\n');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[100]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[101]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[4]++;


int CodeCoverConditionCoverageHelper_C23;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((j < indent) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); j++) { 
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[6]++;
}
                result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[102]++;
  }

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[42]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[103]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((topFunctionType == FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[43]++;
                result.append('(');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[104]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[44]++;}
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[105]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[7]++;


int CodeCoverConditionCoverageHelper_C25;

        while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[9]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[106]++;
            switch(source.charAt(i)) {
            case Token.GET:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[45]++;
            case Token.SET:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[46]++;
                result.append(source.charAt(i) == Token.GET ? "get " : "set ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[107]++;
                ++i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[108]++;
                i = printSourceString(source, i + 1, false, result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[109]++;
                // Now increment one more to get past the FUNCTION token
                ++i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[110]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[111]++;
                break;

            case Token.NAME:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[47]++;
            case Token.REGEXP:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[48]++;  // re-wrapped in '/'s in parser...
                i = printSourceString(source, i + 1, false, result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[112]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[113]++;
                continue;

            case Token.STRING:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[49]++;
                i = printSourceString(source, i + 1, true, result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[114]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[115]++;
                continue;

            case Token.NUMBER:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[50]++;
                i = printSourceNumber(source, i + 1, result);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[116]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[117]++;
                continue;

            case Token.TRUE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[51]++;
                result.append("true");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[118]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[119]++;
                break;

            case Token.FALSE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[52]++;
                result.append("false");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[120]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[121]++;
                break;

            case Token.NULL:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[53]++;
                result.append("null");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[122]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[123]++;
                break;

            case Token.THIS:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[54]++;
                result.append("this");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[124]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[125]++;
                break;

            case Token.FUNCTION:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[55]++;
                ++i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[126]++; // skip function type
                result.append("function ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[127]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[128]++;
                break;

            case FUNCTION_END:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[56]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[129]++;
                // Do nothing
                break;

            case Token.COMMA:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[57]++;
                result.append(", ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[130]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[131]++;
                break;

            case Token.LC:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[58]++;
                ++braceNesting;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[132]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[133]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((Token.EOL == getNext(source, length, i)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[59]++;
                    indent += indentGap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[134]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[60]++;}
                result.append('{');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[135]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[136]++;
                break;

            case Token.RC:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[61]++; {
                --braceNesting;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[137]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[138]++;
int CodeCoverConditionCoverageHelper_C27;
                /* don't print the closing RC if it closes the
                 * toplevel function and we're called from
                 * decompileFunctionBody.
                 */
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((justFunctionBody) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((braceNesting == 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[62]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[139]++;
                    break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[63]++;}

                result.append('}');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[140]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[141]++;
                switch (getNext(source, length, i)) {
                    case Token.EOL:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[64]++;
                    case FUNCTION_END:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[65]++;
                        indent -= indentGap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[142]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[143]++;
                        break;
                    case Token.WHILE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[66]++;
                    case Token.ELSE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[67]++;
                        indent -= indentGap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[144]++;
                        result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[145]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[146]++;
                        break; default : CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[68]++;
                }
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[147]++;
                break;
            }
            case Token.LP:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[69]++;
                result.append('(');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[148]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[149]++;
                break;

            case Token.RP:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[70]++;
                result.append(')');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[150]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[151]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((Token.LC == getNext(source, length, i)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[71]++;
                    result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[152]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[72]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[153]++;
                break;

            case Token.LB:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[73]++;
                result.append('[');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[154]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[155]++;
                break;

            case Token.RB:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[74]++;
                result.append(']');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[156]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[157]++;
                break;

            case Token.EOL:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[75]++; {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[158]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((toSource) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[76]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[159]++; break;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[77]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[160]++;
                boolean newLine = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[161]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((afterFirstEOL) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[78]++;
                    afterFirstEOL = true;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[162]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[163]++;
int CodeCoverConditionCoverageHelper_C31;
                    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((justFunctionBody) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[80]++;
                        /* throw away just added 'function name(...) {'
                         * and restore the original indent
                         */
                        result.setLength(0);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[164]++;
                        indent -= indentGap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[165]++;
                        newLine = false;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[166]++;

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[81]++;}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[79]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[167]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((newLine) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[82]++;
                    result.append('\n');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[168]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[83]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[169]++;
int CodeCoverConditionCoverageHelper_C33;

                /* add indent if any tokens remain,
                 * less setback if next token is
                 * a label, case or default.
                 */
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((i + 1 < length) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[84]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[170]++;
                    int less = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[171]++;
                    int nextToken = source.charAt(i + 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[172]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((nextToken == Token.CASE) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((nextToken == Token.DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false))
                    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[86]++;
                        less = indentGap - caseGap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[173]++;

                    } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[87]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[174]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((nextToken == Token.RC) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[88]++;
                        less = indentGap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[175]++;

                    }

                    /* elaborate check against label... skip past a
                     * following inlined NAME and look for a COLON.
                     */
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[89]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[176]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((nextToken == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[90]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[177]++;
                        int afterName = getSourceStringEnd(source, i + 2);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[178]++;
int CodeCoverConditionCoverageHelper_C37;
                        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((source.charAt(afterName) == Token.COLON) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[92]++;
                            less = indentGap;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[179]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[93]++;}

                    } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[91]++;}
}
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[180]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[10]++;


int CodeCoverConditionCoverageHelper_C38;

                    for (;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((less < indent) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); less++) { 
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[10]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[11]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.loops[12]++;
}
                        result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[181]++;
  }

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[85]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[182]++;
                break;
            }
            case Token.DOT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[94]++;
                result.append('.');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[183]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[184]++;
                break;

            case Token.NEW:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[95]++;
                result.append("new ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[185]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[186]++;
                break;

            case Token.DELPROP:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[96]++;
                result.append("delete ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[187]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[188]++;
                break;

            case Token.IF:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[97]++;
                result.append("if ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[189]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[190]++;
                break;

            case Token.ELSE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[98]++;
                result.append("else ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[191]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[192]++;
                break;

            case Token.FOR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[99]++;
                result.append("for ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[193]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[194]++;
                break;

            case Token.IN:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[100]++;
                result.append(" in ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[195]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[196]++;
                break;

            case Token.WITH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[101]++;
                result.append("with ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[197]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[198]++;
                break;

            case Token.WHILE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[102]++;
                result.append("while ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[199]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[200]++;
                break;

            case Token.DO:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[103]++;
                result.append("do ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[201]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[202]++;
                break;

            case Token.TRY:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[104]++;
                result.append("try ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[203]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[204]++;
                break;

            case Token.CATCH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[105]++;
                result.append("catch ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[205]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[206]++;
                break;

            case Token.FINALLY:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[106]++;
                result.append("finally ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[207]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[208]++;
                break;

            case Token.THROW:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[107]++;
                result.append("throw ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[209]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[210]++;
                break;

            case Token.SWITCH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[108]++;
                result.append("switch ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[211]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[212]++;
                break;

            case Token.BREAK:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[109]++;
                result.append("break");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[213]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[214]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((Token.NAME == getNext(source, length, i)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[110]++;
                    result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[215]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[111]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[216]++;
                break;

            case Token.CONTINUE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[112]++;
                result.append("continue");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[217]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[218]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((Token.NAME == getNext(source, length, i)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[113]++;
                    result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[219]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[114]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[220]++;
                break;

            case Token.CASE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[115]++;
                result.append("case ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[221]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[222]++;
                break;

            case Token.DEFAULT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[116]++;
                result.append("default");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[223]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[224]++;
                break;

            case Token.RETURN:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[117]++;
                result.append("return");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[225]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[226]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((Token.SEMI != getNext(source, length, i)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[118]++;
                    result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[227]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[119]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[228]++;
                break;

            case Token.VAR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[120]++;
                result.append("var ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[229]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[230]++;
                break;

            case Token.LET:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[121]++;
              result.append("let ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[231]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[232]++;
              break;

            case Token.SEMI:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[122]++;
                result.append(';');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[233]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[234]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((Token.EOL != getNext(source, length, i)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[123]++;
                    // separators in FOR
                    result.append(' ');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[235]++;

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[124]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[236]++;
                break;

            case Token.ASSIGN:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[125]++;
                result.append(" = ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[237]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[238]++;
                break;

            case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[126]++;
                result.append(" += ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[239]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[240]++;
                break;

            case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[127]++;
                result.append(" -= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[241]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[242]++;
                break;

            case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[128]++;
                result.append(" *= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[243]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[244]++;
                break;

            case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[129]++;
                result.append(" /= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[245]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[246]++;
                break;

            case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[130]++;
                result.append(" %= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[247]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[248]++;
                break;

            case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[131]++;
                result.append(" |= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[249]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[250]++;
                break;

            case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[132]++;
                result.append(" ^= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[251]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[252]++;
                break;

            case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[133]++;
                result.append(" &= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[253]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[254]++;
                break;

            case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[134]++;
                result.append(" <<= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[255]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[256]++;
                break;

            case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[135]++;
                result.append(" >>= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[257]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[258]++;
                break;

            case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[136]++;
                result.append(" >>>= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[259]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[260]++;
                break;

            case Token.HOOK:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[137]++;
                result.append(" ? ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[261]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[262]++;
                break;

            case Token.OBJECTLIT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[138]++;
                // pun OBJECTLIT to mean colon in objlit property
                // initialization.
                // This needs to be distinct from COLON in the general case
                // to distinguish from the colon in a ternary... which needs
                // different spacing.
                result.append(": ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[263]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[264]++;
                break;

            case Token.COLON:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[139]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[265]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((Token.EOL == getNext(source, length, i)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[140]++;
                    // it's the end of a label
                    result.append(':');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[266]++;
}
                else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[141]++;
                    // it's the middle part of a ternary
                    result.append(" : ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[267]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[268]++;
                break;

            case Token.OR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[142]++;
                result.append(" || ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[269]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[270]++;
                break;

            case Token.AND:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[143]++;
                result.append(" && ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[271]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[272]++;
                break;

            case Token.BITOR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[144]++;
                result.append(" | ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[273]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[274]++;
                break;

            case Token.BITXOR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[145]++;
                result.append(" ^ ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[275]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[276]++;
                break;

            case Token.BITAND:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[146]++;
                result.append(" & ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[277]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[278]++;
                break;

            case Token.SHEQ:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[147]++;
                result.append(" === ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[279]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[280]++;
                break;

            case Token.SHNE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[148]++;
                result.append(" !== ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[281]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[282]++;
                break;

            case Token.EQ:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[149]++;
                result.append(" == ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[283]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[284]++;
                break;

            case Token.NE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[150]++;
                result.append(" != ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[285]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[286]++;
                break;

            case Token.LE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[151]++;
                result.append(" <= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[287]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[288]++;
                break;

            case Token.LT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[152]++;
                result.append(" < ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[289]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[290]++;
                break;

            case Token.GE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[153]++;
                result.append(" >= ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[291]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[292]++;
                break;

            case Token.GT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[154]++;
                result.append(" > ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[293]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[294]++;
                break;

            case Token.INSTANCEOF:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[155]++;
                result.append(" instanceof ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[295]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[296]++;
                break;

            case Token.LSH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[156]++;
                result.append(" << ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[297]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[298]++;
                break;

            case Token.RSH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[157]++;
                result.append(" >> ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[299]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[300]++;
                break;

            case Token.URSH:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[158]++;
                result.append(" >>> ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[301]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[302]++;
                break;

            case Token.TYPEOF:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[159]++;
                result.append("typeof ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[303]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[304]++;
                break;

            case Token.VOID:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[160]++;
                result.append("void ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[305]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[306]++;
                break;

            case Token.CONST:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[161]++;
                result.append("const ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[307]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[308]++;
                break;

            case Token.YIELD:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[162]++;
                result.append("yield ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[309]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[310]++;
                break;

            case Token.NOT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[163]++;
                result.append('!');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[311]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[312]++;
                break;

            case Token.BITNOT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[164]++;
                result.append('~');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[313]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[314]++;
                break;

            case Token.POS:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[165]++;
                result.append('+');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[315]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[316]++;
                break;

            case Token.NEG:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[166]++;
                result.append('-');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[317]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[318]++;
                break;

            case Token.INC:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[167]++;
                result.append("++");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[319]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[320]++;
                break;

            case Token.DEC:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[168]++;
                result.append("--");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[321]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[322]++;
                break;

            case Token.ADD:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[169]++;
                result.append(" + ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[323]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[324]++;
                break;

            case Token.SUB:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[170]++;
                result.append(" - ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[325]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[326]++;
                break;

            case Token.MUL:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[171]++;
                result.append(" * ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[327]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[328]++;
                break;

            case Token.DIV:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[172]++;
                result.append(" / ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[329]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[330]++;
                break;

            case Token.MOD:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[173]++;
                result.append(" % ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[331]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[332]++;
                break;

            case Token.COLONCOLON:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[174]++;
                result.append("::");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[333]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[334]++;
                break;

            case Token.DOTDOT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[175]++;
                result.append("..");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[335]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[336]++;
                break;

            case Token.DOTQUERY:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[176]++;
                result.append(".(");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[337]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[338]++;
                break;

            case Token.XMLATTR:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[177]++;
                result.append('@');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[339]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[340]++;
                break;

            case Token.DEBUGGER:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[178]++;
                result.append("debugger;\n");
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[341]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[342]++;
                break;

            default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[179]++;
                // If we don't know how to decompile it, raise an exception.
                throw new RuntimeException("Token: " +
                                               Token.name(source.charAt(i)));
            }
            ++i;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[343]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[344]++;
int CodeCoverConditionCoverageHelper_C44;

        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((toSource) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[180]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[345]++;
int CodeCoverConditionCoverageHelper_C45;
            // add that trailing newline if it's an outermost function.
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((justFunctionBody) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[182]++;
                result.append('\n');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[346]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[183]++;}

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[181]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[347]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((topFunctionType == FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[184]++;
                result.append(')');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[348]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[185]++;}
        }

        return result.toString();
    }

    private static int getNext(String source, int length, int i)
    {
        return (i + 1 < length) ? source.charAt(i + 1) : Token.EOF;
    }

    private static int getSourceStringEnd(String source, int offset)
    {
        return printSourceString(source, offset, false, null);
    }

    private static int printSourceString(String source, int offset,
                                         boolean asQuotedString,
                                         StringBuffer sb)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[349]++;
        int length = source.charAt(offset);
        ++offset;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[350]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[351]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 (((0x8000 & length) != 0) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[186]++;
            length = ((0x7FFF & length) << 16) | source.charAt(offset);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[352]++;
            ++offset;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[353]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[187]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[354]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[188]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[355]++;
            String str = source.substring(offset, offset + length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[356]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((asQuotedString) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[190]++;
                sb.append(str);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[357]++;

            } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[191]++;
                sb.append('"');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[358]++;
                sb.append(ScriptRuntime.escapeString(str));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[359]++;
                sb.append('"');
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[360]++;
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[189]++;}
        return offset + length;
    }

    private static int printSourceNumber(String source, int offset,
                                         StringBuffer sb)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[361]++;
        double number = 0.0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[362]++;
        char type = source.charAt(offset);
        ++offset;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[363]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[364]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((type == 'S') && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[192]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[365]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[194]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[366]++;
                int ival = source.charAt(offset);
                number = ival;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[367]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[195]++;}
            ++offset;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[368]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[193]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[369]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((type == 'J') && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((type == 'D') && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[196]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[370]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[198]++;
                long lbits;
                lbits = (long)source.charAt(offset) << 48;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[371]++;
                lbits |= (long)source.charAt(offset + 1) << 32;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[372]++;
                lbits |= (long)source.charAt(offset + 2) << 16;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[373]++;
                lbits |= source.charAt(offset + 3);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[374]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[375]++;
int CodeCoverConditionCoverageHelper_C54;
                if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((type == 'J') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[200]++;
                    number = lbits;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[376]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[201]++;
                    number = Double.longBitsToDouble(lbits);
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[377]++;
                }

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[199]++;}
            offset += 4;
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[378]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[197]++;
            // Bad source
            throw new RuntimeException();
        }
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[379]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[202]++;
            sb.append(ScriptRuntime.numberToString(number, 10));
CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[380]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.branches[203]++;}
        return offset;
    }

    private char[] sourceBuffer = new char[128];
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[381]++;
  }

// Per script/function source buffer top: parent source does not include a
// nested functions source and uses function index as a reference instead.
    private int sourceTop;

// whether to do a debug print of the source information, when decompiling.
    private static final boolean printSource = false;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h.statements[382]++;
  }

}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h ());
  }
    public static long[] statements = new long[383];
    public static long[] branches = new long[204];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[56];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Decompiler.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 55; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw535karceuanr0x2hxiw6b8h () {
    super("org.mozilla.javascript.RHINO-SRC-Decompiler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 382; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 203; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Decompiler.java");
      for (int i = 1; i <= 382; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 203; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

