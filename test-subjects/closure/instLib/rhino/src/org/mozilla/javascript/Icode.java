/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * Additional interpreter-specific codes
 */
abstract class Icode {
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.ping();
  }


    static final int

    // delete operator used on a name
        Icode_DELNAME                    = 0,

    // Stack: ... value1 -> ... value1 value1
        Icode_DUP                       = -1,

    // Stack: ... value2 value1 -> ... value2 value1 value2 value1
        Icode_DUP2                      = -2,

    // Stack: ... value2 value1 -> ... value1 value2
        Icode_SWAP                      = -3,

    // Stack: ... value1 -> ...
        Icode_POP                       = -4,

    // Store stack top into return register and then pop it
        Icode_POP_RESULT                = -5,

    // To jump conditionally and pop additional stack value
        Icode_IFEQ_POP                  = -6,

    // various types of ++/--
        Icode_VAR_INC_DEC               = -7,
        Icode_NAME_INC_DEC              = -8,
        Icode_PROP_INC_DEC              = -9,
        Icode_ELEM_INC_DEC              = -10,
        Icode_REF_INC_DEC               = -11,

    // load/save scope from/to local
        Icode_SCOPE_LOAD                = -12,
        Icode_SCOPE_SAVE                = -13,

        Icode_TYPEOFNAME                = -14,

    // helper for function calls
        Icode_NAME_AND_THIS             = -15,
        Icode_PROP_AND_THIS             = -16,
        Icode_ELEM_AND_THIS             = -17,
        Icode_VALUE_AND_THIS            = -18,

    // Create closure object for nested functions
        Icode_CLOSURE_EXPR              = -19,
        Icode_CLOSURE_STMT              = -20,

    // Special calls
        Icode_CALLSPECIAL               = -21,

    // To return undefined value
        Icode_RETUNDEF                  = -22,

    // Exception handling implementation
        Icode_GOSUB                     = -23,
        Icode_STARTSUB                  = -24,
        Icode_RETSUB                    = -25,

    // To indicating a line number change in icodes.
        Icode_LINE                      = -26,

    // To store shorts and ints inline
        Icode_SHORTNUMBER               = -27,
        Icode_INTNUMBER                 = -28,

    // To create and populate array to hold values for [] and {} literals
        Icode_LITERAL_NEW               = -29,
        Icode_LITERAL_SET               = -30,

    // Array literal with skipped index like [1,,2]
        Icode_SPARE_ARRAYLIT            = -31,

    // Load index register to prepare for the following index operation
        Icode_REG_IND_C0                = -32,
        Icode_REG_IND_C1                = -33,
        Icode_REG_IND_C2                = -34,
        Icode_REG_IND_C3                = -35,
        Icode_REG_IND_C4                = -36,
        Icode_REG_IND_C5                = -37,
        Icode_REG_IND1                  = -38,
        Icode_REG_IND2                  = -39,
        Icode_REG_IND4                  = -40,

    // Load string register to prepare for the following string operation
        Icode_REG_STR_C0                = -41,
        Icode_REG_STR_C1                = -42,
        Icode_REG_STR_C2                = -43,
        Icode_REG_STR_C3                = -44,
        Icode_REG_STR1                  = -45,
        Icode_REG_STR2                  = -46,
        Icode_REG_STR4                  = -47,

    // Version of getvar/setvar that read var index directly from bytecode
        Icode_GETVAR1                   = -48,
        Icode_SETVAR1                   = -49,

    // Load undefined
        Icode_UNDEF                     = -50,
        Icode_ZERO                      = -51,
        Icode_ONE                       = -52,

    // entrance and exit from .()
       Icode_ENTERDQ                    = -53,
       Icode_LEAVEDQ                    = -54,

       Icode_TAIL_CALL                  = -55,

    // Clear local to allow GC its context
       Icode_LOCAL_CLEAR                = -56,

    // Literal get/set
       Icode_LITERAL_GETTER             = -57,
       Icode_LITERAL_SETTER             = -58,

    // const
       Icode_SETCONST                   = -59,
       Icode_SETCONSTVAR                = -60,
       Icode_SETCONSTVAR1               = -61,

    // Generator opcodes (along with Token.YIELD)
       Icode_GENERATOR                  = -62,
       Icode_GENERATOR_END              = -63,

       Icode_DEBUGGER                   = -64,

       // Last icode
        MIN_ICODE                       = -64;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.statements[1]++;
  }

    static String bytecodeName(int bytecode)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((validBytecode(bytecode)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[1]++;
            throw new IllegalArgumentException(String.valueOf(bytecode));

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[2]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Token.printICode) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[3]++;
            return String.valueOf(bytecode);

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[4]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.statements[4]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((validTokenCode(bytecode)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[5]++;
            return Token.name(bytecode);

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[6]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.statements[5]++;

        switch (bytecode) {
          case Icode_DUP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[7]++;              return "DUP";
          case Icode_DUP2:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[8]++;             return "DUP2";
          case Icode_SWAP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[9]++;             return "SWAP";
          case Icode_POP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[10]++;              return "POP";
          case Icode_POP_RESULT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[11]++;       return "POP_RESULT";
          case Icode_IFEQ_POP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[12]++;         return "IFEQ_POP";
          case Icode_VAR_INC_DEC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[13]++;      return "VAR_INC_DEC";
          case Icode_NAME_INC_DEC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[14]++;     return "NAME_INC_DEC";
          case Icode_PROP_INC_DEC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[15]++;     return "PROP_INC_DEC";
          case Icode_ELEM_INC_DEC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[16]++;     return "ELEM_INC_DEC";
          case Icode_REF_INC_DEC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[17]++;      return "REF_INC_DEC";
          case Icode_SCOPE_LOAD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[18]++;       return "SCOPE_LOAD";
          case Icode_SCOPE_SAVE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[19]++;       return "SCOPE_SAVE";
          case Icode_TYPEOFNAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[20]++;       return "TYPEOFNAME";
          case Icode_NAME_AND_THIS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[21]++;    return "NAME_AND_THIS";
          case Icode_PROP_AND_THIS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[22]++;    return "PROP_AND_THIS";
          case Icode_ELEM_AND_THIS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[23]++;    return "ELEM_AND_THIS";
          case Icode_VALUE_AND_THIS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[24]++;   return "VALUE_AND_THIS";
          case Icode_CLOSURE_EXPR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[25]++;     return "CLOSURE_EXPR";
          case Icode_CLOSURE_STMT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[26]++;     return "CLOSURE_STMT";
          case Icode_CALLSPECIAL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[27]++;      return "CALLSPECIAL";
          case Icode_RETUNDEF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[28]++;         return "RETUNDEF";
          case Icode_GOSUB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[29]++;            return "GOSUB";
          case Icode_STARTSUB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[30]++;         return "STARTSUB";
          case Icode_RETSUB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[31]++;           return "RETSUB";
          case Icode_LINE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[32]++;             return "LINE";
          case Icode_SHORTNUMBER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[33]++;      return "SHORTNUMBER";
          case Icode_INTNUMBER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[34]++;        return "INTNUMBER";
          case Icode_LITERAL_NEW:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[35]++;      return "LITERAL_NEW";
          case Icode_LITERAL_SET:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[36]++;      return "LITERAL_SET";
          case Icode_SPARE_ARRAYLIT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[37]++;   return "SPARE_ARRAYLIT";
          case Icode_REG_IND_C0:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[38]++;       return "REG_IND_C0";
          case Icode_REG_IND_C1:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[39]++;       return "REG_IND_C1";
          case Icode_REG_IND_C2:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[40]++;       return "REG_IND_C2";
          case Icode_REG_IND_C3:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[41]++;       return "REG_IND_C3";
          case Icode_REG_IND_C4:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[42]++;       return "REG_IND_C4";
          case Icode_REG_IND_C5:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[43]++;       return "REG_IND_C5";
          case Icode_REG_IND1:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[44]++;         return "LOAD_IND1";
          case Icode_REG_IND2:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[45]++;         return "LOAD_IND2";
          case Icode_REG_IND4:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[46]++;         return "LOAD_IND4";
          case Icode_REG_STR_C0:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[47]++;       return "REG_STR_C0";
          case Icode_REG_STR_C1:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[48]++;       return "REG_STR_C1";
          case Icode_REG_STR_C2:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[49]++;       return "REG_STR_C2";
          case Icode_REG_STR_C3:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[50]++;       return "REG_STR_C3";
          case Icode_REG_STR1:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[51]++;         return "LOAD_STR1";
          case Icode_REG_STR2:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[52]++;         return "LOAD_STR2";
          case Icode_REG_STR4:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[53]++;         return "LOAD_STR4";
          case Icode_GETVAR1:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[54]++;          return "GETVAR1";
          case Icode_SETVAR1:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[55]++;          return "SETVAR1";
          case Icode_UNDEF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[56]++;            return "UNDEF";
          case Icode_ZERO:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[57]++;             return "ZERO";
          case Icode_ONE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[58]++;              return "ONE";
          case Icode_ENTERDQ:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[59]++;          return "ENTERDQ";
          case Icode_LEAVEDQ:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[60]++;          return "LEAVEDQ";
          case Icode_TAIL_CALL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[61]++;        return "TAIL_CALL";
          case Icode_LOCAL_CLEAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[62]++;      return "LOCAL_CLEAR";
          case Icode_LITERAL_GETTER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[63]++;   return "LITERAL_GETTER";
          case Icode_LITERAL_SETTER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[64]++;   return "LITERAL_SETTER";
          case Icode_SETCONST:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[65]++;         return "SETCONST";
          case Icode_SETCONSTVAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[66]++;      return "SETCONSTVAR";
          case Icode_SETCONSTVAR1:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[67]++;     return "SETCONSTVAR1";
          case Icode_GENERATOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[68]++;        return "GENERATOR";
          case Icode_GENERATOR_END:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[69]++;    return "GENERATOR_END";
          case Icode_DEBUGGER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[70]++;         return "DEBUGGER"; default : CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup.branches[71]++;
        }

        // icode without name
        throw new IllegalStateException(String.valueOf(bytecode));
    }

    static boolean validIcode(int icode)
    {
        return MIN_ICODE <= icode && icode <= 0;
    }

    static boolean validTokenCode(int token)
    {
        return Token.FIRST_BYTECODE_TOKEN <= token
               && token <= Token.LAST_BYTECODE_TOKEN;
    }

    static boolean validBytecode(int bytecode)
    {
        return validIcode(bytecode) || validTokenCode(bytecode);
    }
}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[72];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Icode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bi3b9abv5q498xup () {
    super("org.mozilla.javascript.RHINO-SRC-Icode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 71; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Icode.java");
      for (int i = 1; i <= 5; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 71; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

