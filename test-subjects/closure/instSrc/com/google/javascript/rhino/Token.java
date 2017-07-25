/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Roger Lawrence
 *   Mike McCabe
 *   Igor Bukanov
 *   Milen Nankov
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino;

/**
 * This class implements the JavaScript scanner.
 *
 * It is based on the C source files jsscan.c and jsscan.h
 * in the jsref package.
 *
 */

public class Token {
  static {
    CodeCoverCoverageCounter$1sxhuumkb6tcv78h.ping();
  }


    /**
     * Token types.  These values correspond to JSTokenType values in
     * jsscan.c.
     */
    public final static int
        ERROR          = -1,

        RETURN         = 4,
        BITOR          = 9,
        BITXOR         = 10,
        BITAND         = 11,
        EQ             = 12,
        NE             = 13,
        LT             = 14,
        LE             = 15,
        GT             = 16,
        GE             = 17,
        LSH            = 18,
        RSH            = 19,
        URSH           = 20,
        ADD            = 21,
        SUB            = 22,
        MUL            = 23,
        DIV            = 24,
        MOD            = 25,
        NOT            = 26,
        BITNOT         = 27,
        POS            = 28,
        NEG            = 29,
        NEW            = 30,
        DELPROP        = 31,
        TYPEOF         = 32,
        GETPROP        = 33,
        GETELEM        = 35,
        CALL           = 37,
        NAME           = 38,
        NUMBER         = 39,
        STRING         = 40,
        NULL           = 41,
        THIS           = 42,
        FALSE          = 43,
        TRUE           = 44,
        SHEQ           = 45,   // shallow equality (===)
        SHNE           = 46,   // shallow inequality (!==)
        REGEXP         = 47,
        THROW          = 49,
        IN             = 51,
        INSTANCEOF     = 52,
        ARRAYLIT       = 63, // array literal
        OBJECTLIT      = 64, // object literal

        TRY            = 77,
        PARAM_LIST     = 83,
        COMMA          = 85,  // comma operator

        ASSIGN         = 86,  // simple assignment  (=)
        ASSIGN_BITOR   = 87,  // |=
        ASSIGN_BITXOR  = 88,  // ^=
        ASSIGN_BITAND  = 89,  // &=
        ASSIGN_LSH     = 90,  // <<=
        ASSIGN_RSH     = 91,  // >>=
        ASSIGN_URSH    = 92,  // >>>=
        ASSIGN_ADD     = 93,  // +=
        ASSIGN_SUB     = 94,  // -=
        ASSIGN_MUL     = 95,  // *=
        ASSIGN_DIV     = 96,  // /=
        ASSIGN_MOD     = 97,  // %=

        HOOK           = 98,  // conditional (?:)
        OR             = 100, // logical or (||)
        AND            = 101, // logical and (&&)
        INC            = 102, // increment (++)
        DEC            = 103, // decrement (--)
        FUNCTION       = 105, // function keyword
        IF             = 108, // if keyword
        SWITCH         = 110, // switch keyword
        CASE           = 111, // case keyword
        DEFAULT_CASE   = 112, // default keyword
        WHILE          = 113, // while keyword
        DO             = 114, // do keyword
        FOR            = 115, // for keyword
        BREAK          = 116, // break keyword
        CONTINUE       = 117, // continue keyword
        VAR            = 118, // var keyword
        WITH           = 119, // with keyword
        CATCH          = 120, // catch keyword
        VOID           = 122, // void keyword

        EMPTY          = 124,

        BLOCK          = 125, // statement block
        LABEL          = 126, // label
        EXPR_RESULT    = 130, // expression statement in scripts
        SCRIPT         = 132, // top-level node for entire script

        GETTER_DEF     = 147,
        SETTER_DEF     = 148,

        CONST          = 149,  // JS 1.5 const keyword
        DEBUGGER       = 152,

        // JSCompiler introduced tokens
        LABEL_NAME     = 153,
        STRING_KEY     = 154, // object literal key
        CAST           = 155,

        // JSDoc-only tokens
        ANNOTATION     = 300,
        PIPE           = 301,
        STAR           = 302,
        EOC            = 303,
        QMARK          = 304,
        ELLIPSIS       = 305,
        BANG           = 306,
        EQUALS         = 307,
        LB             = 308,  // left brackets
        LC             = 309,  // left curly braces
        COLON          = 310;
  static {
    CodeCoverCoverageCounter$1sxhuumkb6tcv78h.statements[1]++;
  }

    // Transitional definitions
    // TODO(johnlenz): remove these
    public final static int
         DEFAULT        = DEFAULT_CASE,
         GET            = GETTER_DEF,
         LP             = PARAM_LIST,
         SET            = SETTER_DEF;
  static {
    CodeCoverCoverageCounter$1sxhuumkb6tcv78h.statements[2]++;
  }

  public static String name(int token) {
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.statements[3]++;
        switch (token) {
          case ERROR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[1]++;           return "ERROR";
          case RETURN:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[2]++;          return "RETURN";
          case BITOR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[3]++;           return "BITOR";
          case BITXOR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[4]++;          return "BITXOR";
          case BITAND:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[5]++;          return "BITAND";
          case EQ:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[6]++;              return "EQ";
          case NE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[7]++;              return "NE";
          case LT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[8]++;              return "LT";
          case LE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[9]++;              return "LE";
          case GT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[10]++;              return "GT";
          case GE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[11]++;              return "GE";
          case LSH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[12]++;             return "LSH";
          case RSH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[13]++;             return "RSH";
          case URSH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[14]++;            return "URSH";
          case ADD:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[15]++;             return "ADD";
          case SUB:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[16]++;             return "SUB";
          case MUL:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[17]++;             return "MUL";
          case DIV:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[18]++;             return "DIV";
          case MOD:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[19]++;             return "MOD";
          case NOT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[20]++;             return "NOT";
          case BITNOT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[21]++;          return "BITNOT";
          case POS:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[22]++;             return "POS";
          case NEG:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[23]++;             return "NEG";
          case NEW:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[24]++;             return "NEW";
          case DELPROP:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[25]++;         return "DELPROP";
          case TYPEOF:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[26]++;          return "TYPEOF";
          case GETPROP:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[27]++;         return "GETPROP";
          case GETELEM:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[28]++;         return "GETELEM";
          case CALL:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[29]++;            return "CALL";
          case NAME:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[30]++;            return "NAME";
          case LABEL_NAME:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[31]++;      return "LABEL_NAME";
          case NUMBER:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[32]++;          return "NUMBER";
          case STRING:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[33]++;          return "STRING";
          case STRING_KEY:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[34]++;      return "STRING_KEY";
          case NULL:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[35]++;            return "NULL";
          case THIS:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[36]++;            return "THIS";
          case FALSE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[37]++;           return "FALSE";
          case TRUE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[38]++;            return "TRUE";
          case SHEQ:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[39]++;            return "SHEQ";
          case SHNE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[40]++;            return "SHNE";
          case REGEXP:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[41]++;          return "REGEXP";
          case THROW:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[42]++;           return "THROW";
          case IN:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[43]++;              return "IN";
          case INSTANCEOF:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[44]++;      return "INSTANCEOF";
          case ARRAYLIT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[45]++;        return "ARRAYLIT";
          case OBJECTLIT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[46]++;       return "OBJECTLIT";
          case TRY:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[47]++;             return "TRY";
          case PARAM_LIST:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[48]++;      return "PARAM_LIST";
          case COMMA:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[49]++;           return "COMMA";
          case ASSIGN:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[50]++;          return "ASSIGN";
          case ASSIGN_BITOR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[51]++;    return "ASSIGN_BITOR";
          case ASSIGN_BITXOR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[52]++;   return "ASSIGN_BITXOR";
          case ASSIGN_BITAND:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[53]++;   return "ASSIGN_BITAND";
          case ASSIGN_LSH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[54]++;      return "ASSIGN_LSH";
          case ASSIGN_RSH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[55]++;      return "ASSIGN_RSH";
          case ASSIGN_URSH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[56]++;     return "ASSIGN_URSH";
          case ASSIGN_ADD:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[57]++;      return "ASSIGN_ADD";
          case ASSIGN_SUB:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[58]++;      return "ASSIGN_SUB";
          case ASSIGN_MUL:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[59]++;      return "ASSIGN_MUL";
          case ASSIGN_DIV:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[60]++;      return "ASSIGN_DIV";
          case ASSIGN_MOD:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[61]++;      return "ASSIGN_MOD";
          case HOOK:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[62]++;            return "HOOK";
          case OR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[63]++;              return "OR";
          case AND:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[64]++;             return "AND";
          case INC:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[65]++;             return "INC";
          case DEC:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[66]++;             return "DEC";
          case FUNCTION:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[67]++;        return "FUNCTION";
          case IF:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[68]++;              return "IF";
          case SWITCH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[69]++;          return "SWITCH";
          case CASE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[70]++;            return "CASE";
          case DEFAULT_CASE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[71]++;    return "DEFAULT_CASE";
          case WHILE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[72]++;           return "WHILE";
          case DO:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[73]++;              return "DO";
          case FOR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[74]++;             return "FOR";
          case BREAK:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[75]++;           return "BREAK";
          case CONTINUE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[76]++;        return "CONTINUE";
          case VAR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[77]++;             return "VAR";
          case WITH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[78]++;            return "WITH";
          case CATCH:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[79]++;           return "CATCH";
          case EMPTY:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[80]++;           return "EMPTY";
          case BLOCK:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[81]++;           return "BLOCK";
          case LABEL:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[82]++;           return "LABEL";
          case EXPR_RESULT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[83]++;     return "EXPR_RESULT";
          case SCRIPT:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[84]++;          return "SCRIPT";
          case GETTER_DEF:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[85]++;      return "GETTER_DEF";
          case SETTER_DEF:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[86]++;      return "SETTER_DEF";
          case CONST:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[87]++;           return "CONST";
          case DEBUGGER:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[88]++;        return "DEBUGGER";
          case CAST:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[89]++;            return "CAST";
          case ANNOTATION:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[90]++;      return "ANNOTATION";
          case PIPE:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[91]++;            return "PIPE";
          case STAR:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[92]++;            return "STAR";
          case EOC:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[93]++;             return "EOC";
          case QMARK:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[94]++;           return "QMARK";
          case ELLIPSIS:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[95]++;        return "ELLIPSIS";
          case BANG:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[96]++;            return "BANG";
          case VOID:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[97]++;            return "VOID";
          case EQUALS:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[98]++;          return "EQUALS";
          case LB:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[99]++;              return "LB";
          case LC:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[100]++;              return "LC";
          case COLON:
CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[101]++;           return "COLON"; default : CodeCoverCoverageCounter$1sxhuumkb6tcv78h.branches[102]++;
        }

        // Token without name
        throw new IllegalStateException(String.valueOf(token));
    }
}

class CodeCoverCoverageCounter$1sxhuumkb6tcv78h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1sxhuumkb6tcv78h ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[103];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1sxhuumkb6tcv78h () {
    super("com.google.javascript.rhino.Token.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 102; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.Token.java");
      for (int i = 1; i <= 3; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 102; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

