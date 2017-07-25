/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements the JavaScript scanner.
 *
 * It is based on the C source files jsscan.c and jsscan.h
 * in the jsref package.
 *
 * @see org.mozilla.javascript.Parser
 *
 */

public class Token
{
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.ping();
  }

    public static enum CommentType {
        LINE, BLOCK_COMMENT, JSDOC, HTML
    }

    // debug flags
    public static final boolean printTrees = false;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[1]++;
  }
    static final boolean printICode = false;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[2]++;
  }
    static final boolean printNames = printTrees || printICode;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[3]++;
  }

    /**
     * Token types.  These values correspond to JSTokenType values in
     * jsscan.c.
     */

    public final static int
    // start enum
        ERROR          = -1, // well-known as the only code < EOF
        EOF            = 0,  // end of file token - (not EOF_CHAR)
        EOL            = 1,  // end of line

        // Interpreter reuses the following as bytecodes
        FIRST_BYTECODE_TOKEN    = 2,

        ENTERWITH      = 2,
        LEAVEWITH      = 3,
        RETURN         = 4,
        GOTO           = 5,
        IFEQ           = 6,
        IFNE           = 7,
        SETNAME        = 8,
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
        GETPROPNOWARN  = 34,
        SETPROP        = 35,
        GETELEM        = 36,
        SETELEM        = 37,
        CALL           = 38,
        NAME           = 39,
        NUMBER         = 40,
        STRING         = 41,
        NULL           = 42,
        THIS           = 43,
        FALSE          = 44,
        TRUE           = 45,
        SHEQ           = 46,   // shallow equality (===)
        SHNE           = 47,   // shallow inequality (!==)
        REGEXP         = 48,
        BINDNAME       = 49,
        THROW          = 50,
        RETHROW        = 51, // rethrow caught exception: catch (e if ) use it
        IN             = 52,
        INSTANCEOF     = 53,
        LOCAL_LOAD     = 54,
        GETVAR         = 55,
        SETVAR         = 56,
        CATCH_SCOPE    = 57,
        ENUM_INIT_KEYS = 58,
        ENUM_INIT_VALUES = 59,
        ENUM_INIT_ARRAY= 60,
        ENUM_NEXT      = 61,
        ENUM_ID        = 62,
        THISFN         = 63,
        RETURN_RESULT  = 64, // to return previously stored return result
        ARRAYLIT       = 65, // array literal
        OBJECTLIT      = 66, // object literal
        GET_REF        = 67, // *reference
        SET_REF        = 68, // *reference    = something
        DEL_REF        = 69, // delete reference
        REF_CALL       = 70, // f(args)    = something or f(args)++
        REF_SPECIAL    = 71, // reference for special properties like __proto
        YIELD          = 72,  // JS 1.7 yield pseudo keyword
        STRICT_SETNAME = 73,

        // For XML support:
        DEFAULTNAMESPACE = 74, // default xml namespace =
        ESCXMLATTR     = 75,
        ESCXMLTEXT     = 76,
        REF_MEMBER     = 77, // Reference for x.@y, x..y etc.
        REF_NS_MEMBER  = 78, // Reference for x.ns::y, x..ns::y etc.
        REF_NAME       = 79, // Reference for @y, @[y] etc.
        REF_NS_NAME    = 80;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[4]++;
  } // Reference for ns::y, @ns::y@[y] etc.

        // End of interpreter bytecodes
    public final static int
        LAST_BYTECODE_TOKEN    = REF_NS_NAME,

        TRY            = 81,
        SEMI           = 82,  // semicolon
        LB             = 83,  // left and right brackets
        RB             = 84,
        LC             = 85,  // left and right curlies (braces)
        RC             = 86,
        LP             = 87,  // left and right parentheses
        RP             = 88,
        COMMA          = 89,  // comma operator

        ASSIGN         = 90,  // simple assignment  (=)
        ASSIGN_BITOR   = 91,  // |=
        ASSIGN_BITXOR  = 92,  // ^=
        ASSIGN_BITAND  = 93,  // |=
        ASSIGN_LSH     = 94,  // <<=
        ASSIGN_RSH     = 95,  // >>=
        ASSIGN_URSH    = 96,  // >>>=
        ASSIGN_ADD     = 97,  // +=
        ASSIGN_SUB     = 98,  // -=
        ASSIGN_MUL     = 99,  // *=
        ASSIGN_DIV     = 100,  // /=
        ASSIGN_MOD     = 101;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[5]++;
  }  // %=

    public final static int
        FIRST_ASSIGN   = ASSIGN,
        LAST_ASSIGN    = ASSIGN_MOD,

        HOOK           = 102, // conditional (?:)
        COLON          = 103,
        OR             = 104, // logical or (||)
        AND            = 105, // logical and (&&)
        INC            = 106, // increment/decrement (++ --)
        DEC            = 107,
        DOT            = 108, // member operator (.)
        FUNCTION       = 109, // function keyword
        EXPORT         = 110, // export keyword
        IMPORT         = 111, // import keyword
        IF             = 112, // if keyword
        ELSE           = 113, // else keyword
        SWITCH         = 114, // switch keyword
        CASE           = 115, // case keyword
        DEFAULT        = 116, // default keyword
        WHILE          = 117, // while keyword
        DO             = 118, // do keyword
        FOR            = 119, // for keyword
        BREAK          = 120, // break keyword
        CONTINUE       = 121, // continue keyword
        VAR            = 122, // var keyword
        WITH           = 123, // with keyword
        CATCH          = 124, // catch keyword
        FINALLY        = 125, // finally keyword
        VOID           = 126, // void keyword
        RESERVED       = 127, // reserved keywords

        EMPTY          = 128,

        /* types used for the parse tree - these never get returned
         * by the scanner.
         */

        BLOCK          = 129, // statement block
        LABEL          = 130, // label
        TARGET         = 131,
        LOOP           = 132,
        EXPR_VOID      = 133, // expression statement in functions
        EXPR_RESULT    = 134, // expression statement in scripts
        JSR            = 135,
        SCRIPT         = 136, // top-level node for entire script
        TYPEOFNAME     = 137, // for typeof(simple-name)
        USE_STACK      = 138,
        SETPROP_OP     = 139, // x.y op= something
        SETELEM_OP     = 140, // x[y] op= something
        LOCAL_BLOCK    = 141,
        SET_REF_OP     = 142, // *reference op= something

        // For XML support:
        DOTDOT         = 143,  // member operator (..)
        COLONCOLON     = 144,  // namespace::name
        XML            = 145,  // XML type
        DOTQUERY       = 146,  // .() -- e.g., x.emps.emp.(name == "terry")
        XMLATTR        = 147,  // @
        XMLEND         = 148,

        // Optimizer-only-tokens
        TO_OBJECT      = 149,
        TO_DOUBLE      = 150,

        GET            = 151,  // JS 1.5 get pseudo keyword
        SET            = 152,  // JS 1.5 set pseudo keyword
        LET            = 153,  // JS 1.7 let pseudo keyword
        CONST          = 154,
        SETCONST       = 155,
        SETCONSTVAR    = 156,
        ARRAYCOMP      = 157,  // array comprehension
        LETEXPR        = 158,
        WITHEXPR       = 159,
        DEBUGGER       = 160,
        COMMENT        = 161,
        GENEXPR        = 162,
        LAST_TOKEN     = 163;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[6]++;
  }

    /**
     * Returns a name for the token.  If Rhino is compiled with certain
     * hardcoded debugging flags in this file, it calls {@code #typeToName};
     * otherwise it returns a string whose value is the token number.
     */
    public static String name(int token)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((printNames) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[1]++;
            return String.valueOf(token);

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[2]++;}
        return typeToName(token);
    }

    /**
     * Always returns a human-readable string for the token name.
     * For instance, {@link #FINALLY} has the name "FINALLY".
     * @param token the token code
     * @return the actual name for the token code
     */
    public static String typeToName(int token) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[8]++;
        switch (token) {
          case ERROR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[3]++;           return "ERROR";
          case EOF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[4]++;             return "EOF";
          case EOL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[5]++;             return "EOL";
          case ENTERWITH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[6]++;       return "ENTERWITH";
          case LEAVEWITH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[7]++;       return "LEAVEWITH";
          case RETURN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[8]++;          return "RETURN";
          case GOTO:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[9]++;            return "GOTO";
          case IFEQ:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[10]++;            return "IFEQ";
          case IFNE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[11]++;            return "IFNE";
          case SETNAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[12]++;         return "SETNAME";
          case BITOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[13]++;           return "BITOR";
          case BITXOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[14]++;          return "BITXOR";
          case BITAND:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[15]++;          return "BITAND";
          case EQ:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[16]++;              return "EQ";
          case NE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[17]++;              return "NE";
          case LT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[18]++;              return "LT";
          case LE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[19]++;              return "LE";
          case GT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[20]++;              return "GT";
          case GE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[21]++;              return "GE";
          case LSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[22]++;             return "LSH";
          case RSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[23]++;             return "RSH";
          case URSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[24]++;            return "URSH";
          case ADD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[25]++;             return "ADD";
          case SUB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[26]++;             return "SUB";
          case MUL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[27]++;             return "MUL";
          case DIV:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[28]++;             return "DIV";
          case MOD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[29]++;             return "MOD";
          case NOT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[30]++;             return "NOT";
          case BITNOT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[31]++;          return "BITNOT";
          case POS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[32]++;             return "POS";
          case NEG:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[33]++;             return "NEG";
          case NEW:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[34]++;             return "NEW";
          case DELPROP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[35]++;         return "DELPROP";
          case TYPEOF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[36]++;          return "TYPEOF";
          case GETPROP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[37]++;         return "GETPROP";
          case GETPROPNOWARN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[38]++;   return "GETPROPNOWARN";
          case SETPROP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[39]++;         return "SETPROP";
          case GETELEM:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[40]++;         return "GETELEM";
          case SETELEM:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[41]++;         return "SETELEM";
          case CALL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[42]++;            return "CALL";
          case NAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[43]++;            return "NAME";
          case NUMBER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[44]++;          return "NUMBER";
          case STRING:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[45]++;          return "STRING";
          case NULL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[46]++;            return "NULL";
          case THIS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[47]++;            return "THIS";
          case FALSE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[48]++;           return "FALSE";
          case TRUE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[49]++;            return "TRUE";
          case SHEQ:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[50]++;            return "SHEQ";
          case SHNE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[51]++;            return "SHNE";
          case REGEXP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[52]++;          return "REGEXP";
          case BINDNAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[53]++;        return "BINDNAME";
          case THROW:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[54]++;           return "THROW";
          case RETHROW:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[55]++;         return "RETHROW";
          case IN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[56]++;              return "IN";
          case INSTANCEOF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[57]++;      return "INSTANCEOF";
          case LOCAL_LOAD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[58]++;      return "LOCAL_LOAD";
          case GETVAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[59]++;          return "GETVAR";
          case SETVAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[60]++;          return "SETVAR";
          case CATCH_SCOPE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[61]++;     return "CATCH_SCOPE";
          case ENUM_INIT_KEYS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[62]++;  return "ENUM_INIT_KEYS";
          case ENUM_INIT_VALUES:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[63]++;return "ENUM_INIT_VALUES";
          case ENUM_INIT_ARRAY:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[64]++; return "ENUM_INIT_ARRAY";
          case ENUM_NEXT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[65]++;       return "ENUM_NEXT";
          case ENUM_ID:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[66]++;         return "ENUM_ID";
          case THISFN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[67]++;          return "THISFN";
          case RETURN_RESULT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[68]++;   return "RETURN_RESULT";
          case ARRAYLIT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[69]++;        return "ARRAYLIT";
          case OBJECTLIT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[70]++;       return "OBJECTLIT";
          case GET_REF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[71]++;         return "GET_REF";
          case SET_REF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[72]++;         return "SET_REF";
          case DEL_REF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[73]++;         return "DEL_REF";
          case REF_CALL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[74]++;        return "REF_CALL";
          case REF_SPECIAL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[75]++;     return "REF_SPECIAL";
          case DEFAULTNAMESPACE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[76]++;return "DEFAULTNAMESPACE";
          case ESCXMLTEXT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[77]++;      return "ESCXMLTEXT";
          case ESCXMLATTR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[78]++;      return "ESCXMLATTR";
          case REF_MEMBER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[79]++;      return "REF_MEMBER";
          case REF_NS_MEMBER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[80]++;   return "REF_NS_MEMBER";
          case REF_NAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[81]++;        return "REF_NAME";
          case REF_NS_NAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[82]++;     return "REF_NS_NAME";
          case TRY:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[83]++;             return "TRY";
          case SEMI:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[84]++;            return "SEMI";
          case LB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[85]++;              return "LB";
          case RB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[86]++;              return "RB";
          case LC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[87]++;              return "LC";
          case RC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[88]++;              return "RC";
          case LP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[89]++;              return "LP";
          case RP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[90]++;              return "RP";
          case COMMA:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[91]++;           return "COMMA";
          case ASSIGN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[92]++;          return "ASSIGN";
          case ASSIGN_BITOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[93]++;    return "ASSIGN_BITOR";
          case ASSIGN_BITXOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[94]++;   return "ASSIGN_BITXOR";
          case ASSIGN_BITAND:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[95]++;   return "ASSIGN_BITAND";
          case ASSIGN_LSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[96]++;      return "ASSIGN_LSH";
          case ASSIGN_RSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[97]++;      return "ASSIGN_RSH";
          case ASSIGN_URSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[98]++;     return "ASSIGN_URSH";
          case ASSIGN_ADD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[99]++;      return "ASSIGN_ADD";
          case ASSIGN_SUB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[100]++;      return "ASSIGN_SUB";
          case ASSIGN_MUL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[101]++;      return "ASSIGN_MUL";
          case ASSIGN_DIV:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[102]++;      return "ASSIGN_DIV";
          case ASSIGN_MOD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[103]++;      return "ASSIGN_MOD";
          case HOOK:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[104]++;            return "HOOK";
          case COLON:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[105]++;           return "COLON";
          case OR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[106]++;              return "OR";
          case AND:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[107]++;             return "AND";
          case INC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[108]++;             return "INC";
          case DEC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[109]++;             return "DEC";
          case DOT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[110]++;             return "DOT";
          case FUNCTION:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[111]++;        return "FUNCTION";
          case EXPORT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[112]++;          return "EXPORT";
          case IMPORT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[113]++;          return "IMPORT";
          case IF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[114]++;              return "IF";
          case ELSE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[115]++;            return "ELSE";
          case SWITCH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[116]++;          return "SWITCH";
          case CASE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[117]++;            return "CASE";
          case DEFAULT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[118]++;         return "DEFAULT";
          case WHILE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[119]++;           return "WHILE";
          case DO:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[120]++;              return "DO";
          case FOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[121]++;             return "FOR";
          case BREAK:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[122]++;           return "BREAK";
          case CONTINUE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[123]++;        return "CONTINUE";
          case VAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[124]++;             return "VAR";
          case WITH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[125]++;            return "WITH";
          case CATCH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[126]++;           return "CATCH";
          case FINALLY:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[127]++;         return "FINALLY";
          case VOID:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[128]++;            return "VOID";
          case RESERVED:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[129]++;        return "RESERVED";
          case EMPTY:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[130]++;           return "EMPTY";
          case BLOCK:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[131]++;           return "BLOCK";
          case LABEL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[132]++;           return "LABEL";
          case TARGET:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[133]++;          return "TARGET";
          case LOOP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[134]++;            return "LOOP";
          case EXPR_VOID:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[135]++;       return "EXPR_VOID";
          case EXPR_RESULT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[136]++;     return "EXPR_RESULT";
          case JSR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[137]++;             return "JSR";
          case SCRIPT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[138]++;          return "SCRIPT";
          case TYPEOFNAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[139]++;      return "TYPEOFNAME";
          case USE_STACK:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[140]++;       return "USE_STACK";
          case SETPROP_OP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[141]++;      return "SETPROP_OP";
          case SETELEM_OP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[142]++;      return "SETELEM_OP";
          case LOCAL_BLOCK:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[143]++;     return "LOCAL_BLOCK";
          case SET_REF_OP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[144]++;      return "SET_REF_OP";
          case DOTDOT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[145]++;          return "DOTDOT";
          case COLONCOLON:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[146]++;      return "COLONCOLON";
          case XML:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[147]++;             return "XML";
          case DOTQUERY:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[148]++;        return "DOTQUERY";
          case XMLATTR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[149]++;         return "XMLATTR";
          case XMLEND:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[150]++;          return "XMLEND";
          case TO_OBJECT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[151]++;       return "TO_OBJECT";
          case TO_DOUBLE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[152]++;       return "TO_DOUBLE";
          case GET:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[153]++;             return "GET";
          case SET:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[154]++;             return "SET";
          case LET:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[155]++;             return "LET";
          case YIELD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[156]++;           return "YIELD";
          case CONST:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[157]++;           return "CONST";
          case SETCONST:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[158]++;        return "SETCONST";
          case ARRAYCOMP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[159]++;       return "ARRAYCOMP";
          case WITHEXPR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[160]++;        return "WITHEXPR";
          case LETEXPR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[161]++;         return "LETEXPR";
          case DEBUGGER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[162]++;        return "DEBUGGER";
          case COMMENT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[163]++;         return "COMMENT";
          case GENEXPR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[164]++;         return "GENEXPR"; default : CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[165]++;
        }

        // Token without name
        throw new IllegalStateException(String.valueOf(token));
    }

    /**
     * Convert a keyword token to a name string for use with the
     * {@link Context.FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER} feature.
     * @param token A token
     * @return the corresponding name string
     */
    public static String keywordToName(int token) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.statements[9]++;
        switch (token) {
            case Token.BREAK:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[166]++;      return "break";
            case Token.CASE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[167]++;       return "case";
            case Token.CONTINUE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[168]++;   return "continue";
            case Token.DEFAULT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[169]++;    return "default";
            case Token.DELPROP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[170]++;    return "delete";
            case Token.DO:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[171]++;         return "do";
            case Token.ELSE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[172]++;       return "else";
            case Token.FALSE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[173]++;      return "false";
            case Token.FOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[174]++;        return "for";
            case Token.FUNCTION:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[175]++;   return "function";
            case Token.IF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[176]++;         return "if";
            case Token.IN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[177]++;         return "in";
            case Token.LET:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[178]++;        return "let";
            case Token.NEW:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[179]++;        return "new";
            case Token.NULL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[180]++;       return "null";
            case Token.RETURN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[181]++;     return "return";
            case Token.SWITCH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[182]++;     return "switch";
            case Token.THIS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[183]++;       return "this";
            case Token.TRUE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[184]++;       return "true";
            case Token.TYPEOF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[185]++;     return "typeof";
            case Token.VAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[186]++;        return "var";
            case Token.VOID:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[187]++;       return "void";
            case Token.WHILE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[188]++;      return "while";
            case Token.WITH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[189]++;       return "with";
            case Token.YIELD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[190]++;      return "yield";
            case Token.CATCH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[191]++;      return "catch";
            case Token.CONST:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[192]++;      return "const";
            case Token.DEBUGGER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[193]++;   return "debugger";
            case Token.FINALLY:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[194]++;    return "finally";
            case Token.INSTANCEOF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[195]++; return "instanceof";
            case Token.THROW:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[196]++;      return "throw";
            case Token.TRY:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[197]++;        return "try";
            default:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x.branches[198]++;               return null;
        }
    }

    /**
     * Return true if the passed code is a valid Token constant.
     * @param code a potential token code
     * @return true if it's a known token
     */
    public static boolean isValidToken(int code) {
        return code >= ERROR
                && code <= LAST_TOKEN;
    }
}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[199];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Token.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bql4qhlklauto28x () {
    super("org.mozilla.javascript.RHINO-SRC-Token.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 198; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Token.java");
      for (int i = 1; i <= 9; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 198; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

