/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.regexp;

import java.io.Serializable;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;

/**
 * This class implements the RegExp native object.
 *
 * Revision History:
 * Implementation in C by Brendan Eich
 * Initial port to Java by Norris Boyd from jsregexp.c version 1.36
 * Merged up to version 1.38, which included Unicode support.
 * Merged bug fixes in version 1.39.
 * Merged JSFUN13_BRANCH changes up to 1.32.2.13
 *
 */



public class NativeRegExp extends IdScriptableObject implements Function
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }

    static final long serialVersionUID = 4965263491464903264L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1]++;
  }

    private static final Object REGEXP_TAG = new Object();
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[2]++;
  }

    public static final int JSREG_GLOB = 0x1;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[3]++;
  }       // 'g' flag: global
    public static final int JSREG_FOLD = 0x2;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[4]++;
  }       // 'i' flag: fold
    public static final int JSREG_MULTILINE = 0x4;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[5]++;
  }  // 'm' flag: multiline

    //type of match to perform
    public static final int TEST = 0;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[6]++;
  }
    public static final int MATCH = 1;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[7]++;
  }
    public static final int PREFIX = 2;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[8]++;
  }

    private static final boolean debug = false;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[9]++;
  }

    private static final byte REOP_SIMPLE_START  = 1;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[10]++;
  }  /* start of 'simple opcodes' */
    private static final byte REOP_EMPTY         = 1;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[11]++;
  }  /* match rest of input against rest of r.e. */
    private static final byte REOP_BOL           = 2;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[12]++;
  }  /* beginning of input (or line if multiline) */
    private static final byte REOP_EOL           = 3;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[13]++;
  }  /* end of input (or line if multiline) */
    private static final byte REOP_WBDRY         = 4;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[14]++;
  }  /* match "" at word boundary */
    private static final byte REOP_WNONBDRY      = 5;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[15]++;
  }  /* match "" at word non-boundary */
    private static final byte REOP_DOT           = 6;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[16]++;
  }  /* stands for any character */
    private static final byte REOP_DIGIT         = 7;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[17]++;
  }  /* match a digit char: [0-9] */
    private static final byte REOP_NONDIGIT      = 8;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[18]++;
  }  /* match a non-digit char: [^0-9] */
    private static final byte REOP_ALNUM         = 9;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[19]++;
  }  /* match an alphanumeric char: [0-9a-z_A-Z] */
    private static final byte REOP_NONALNUM      = 10;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[20]++;
  } /* match a non-alphanumeric char: [^0-9a-z_A-Z] */
    private static final byte REOP_SPACE         = 11;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[21]++;
  } /* match a whitespace char */
    private static final byte REOP_NONSPACE      = 12;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[22]++;
  } /* match a non-whitespace char */
    private static final byte REOP_BACKREF       = 13;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[23]++;
  } /* back-reference (e.g., \1) to a parenthetical */
    private static final byte REOP_FLAT          = 14;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[24]++;
  } /* match a flat string */
    private static final byte REOP_FLAT1         = 15;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[25]++;
  } /* match a single char */
    private static final byte REOP_FLATi         = 16;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[26]++;
  } /* case-independent REOP_FLAT */
    private static final byte REOP_FLAT1i        = 17;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[27]++;
  } /* case-independent REOP_FLAT1 */
    private static final byte REOP_UCFLAT1       = 18;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[28]++;
  } /* single Unicode char */
    private static final byte REOP_UCFLAT1i      = 19;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[29]++;
  } /* case-independent REOP_UCFLAT1 */
//    private static final byte REOP_UCFLAT        = 20; /* flat Unicode string; len immediate counts chars */
//    private static final byte REOP_UCFLATi       = 21; /* case-independent REOP_UCFLAT */
    private static final byte REOP_CLASS         = 22;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[30]++;
  } /* character class with index */
    private static final byte REOP_NCLASS        = 23;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[31]++;
  } /* negated character class with index */
    private static final byte REOP_SIMPLE_END    = 23;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[32]++;
  } /* end of 'simple opcodes' */
    private static final byte REOP_QUANT         = 25;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[33]++;
  } /* quantified atom: atom{1,2} */
    private static final byte REOP_STAR          = 26;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[34]++;
  } /* zero or more occurrences of kid */
    private static final byte REOP_PLUS          = 27;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[35]++;
  } /* one or more occurrences of kid */
    private static final byte REOP_OPT           = 28;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[36]++;
  } /* optional subexpression in kid */
    private static final byte REOP_LPAREN        = 29;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[37]++;
  } /* left paren bytecode: kid is u.num'th sub-regexp */
    private static final byte REOP_RPAREN        = 30;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[38]++;
  } /* right paren bytecode */
    private static final byte REOP_ALT           = 31;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[39]++;
  } /* alternative subexpressions in kid and next */
    private static final byte REOP_JUMP          = 32;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[40]++;
  } /* for deoptimized closure loops */
//    private static final byte REOP_DOTSTAR       = 33; /* optimize .* to use a single opcode */
//    private static final byte REOP_ANCHOR        = 34; /* like .* but skips left context to unanchored r.e. */
//    private static final byte REOP_EOLONLY       = 35; /* $ not preceded by any pattern */
//    private static final byte REOP_BACKREFi      = 37; /* case-independent REOP_BACKREF */
//    private static final byte REOP_LPARENNON     = 40; /* non-capturing version of REOP_LPAREN */
    private static final byte REOP_ASSERT        = 41;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[41]++;
  } /* zero width positive lookahead assertion */
    private static final byte REOP_ASSERT_NOT    = 42;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[42]++;
  } /* zero width negative lookahead assertion */
    private static final byte REOP_ASSERTTEST    = 43;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[43]++;
  } /* sentinel at end of assertion child */
    private static final byte REOP_ASSERTNOTTEST = 44;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[44]++;
  } /* sentinel at end of !assertion child */
    private static final byte REOP_MINIMALSTAR   = 45;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[45]++;
  } /* non-greedy version of * */
    private static final byte REOP_MINIMALPLUS   = 46;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[46]++;
  } /* non-greedy version of + */
    private static final byte REOP_MINIMALOPT    = 47;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[47]++;
  } /* non-greedy version of ? */
    private static final byte REOP_MINIMALQUANT  = 48;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[48]++;
  } /* non-greedy version of {} */
    private static final byte REOP_ENDCHILD      = 49;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[49]++;
  } /* sentinel at end of quantifier child */
    private static final byte REOP_REPEAT        = 51;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[50]++;
  } /* directs execution of greedy quantifier */
    private static final byte REOP_MINIMALREPEAT = 52;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[51]++;
  } /* directs execution of non-greedy quantifier */
    private static final byte REOP_ALTPREREQ     = 53;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[52]++;
  } /* prerequisite for ALT, either of two chars */
    private static final byte REOP_ALTPREREQi    = 54;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[53]++;
  } /* case-independent REOP_ALTPREREQ */
    private static final byte REOP_ALTPREREQ2    = 55;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[54]++;
  } /* prerequisite for ALT, a char or a class */
//    private static final byte REOP_ENDALT        = 56; /* end of final alternate */
    private static final byte REOP_END           = 57;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[55]++;
  }

    private static final int ANCHOR_BOL = -2;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[56]++;
  }


    public static void init(Context cx, Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[57]++;

        NativeRegExp proto = new NativeRegExp();
        proto.re = compileRE(cx, "", null, false);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[58]++;
        proto.activatePrototypeMap(MAX_PROTOTYPE_ID);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[59]++;
        proto.setParentScope(scope);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[60]++;
        proto.setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[61]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[62]++;

        NativeRegExpCtor ctor = new NativeRegExpCtor();
        // Bug #324006: ECMA-262 15.10.6.1 says "The initial value of
        // RegExp.prototype.constructor is the builtin RegExp constructor."
        proto.defineProperty("constructor", ctor, ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[63]++;

        ScriptRuntime.setFunctionProtoAndParent(ctor, scope);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[64]++;

        ctor.setImmunePrototypeProperty(proto);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[65]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[66]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[1]++;
            proto.sealObject();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[67]++;
            ctor.sealObject();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[68]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[2]++;}

        defineProperty(scope, "RegExp", ctor, ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[69]++;
    }

    NativeRegExp(Scriptable scope, RECompiled regexpCompiled)
    {
        this.re = regexpCompiled;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[70]++;
        this.lastIndex = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[71]++;
        ScriptRuntime.setBuiltinProtoAndParent(this, scope, TopLevel.Builtins.RegExp);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[72]++;
    }

    @Override
    public String getClassName()
    {
        return "RegExp";
    }

    /**
     * Gets the value to be returned by the typeof operator called on this object.
     * @see org.mozilla.javascript.ScriptableObject#getTypeOf()
     * @return "object"
     */
    @Override
    public String getTypeOf()
    {
    	return "object";
    }

    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        return execSub(cx, scope, args, MATCH);
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
        return (Scriptable)execSub(cx, scope, args, MATCH);
    }

    Scriptable compile(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[73]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((args[0] instanceof NativeRegExp) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[3]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[74]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((args.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((args[1] != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[5]++;
                // report error
                throw ScriptRuntime.typeError0("msg.bad.regexp.compile");

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[6]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[75]++;
            NativeRegExp thatObj = (NativeRegExp) args[0];
            this.re = thatObj.re;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[76]++;
            this.lastIndex = thatObj.lastIndex;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[77]++;
            return this;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[4]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[78]++;
        String s = args.length == 0 ? "" : escapeRegExp(args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[79]++;
        String global = args.length > 1 && args[1] != Undefined.instance
            ? ScriptRuntime.toString(args[1])
            : null;
        this.re = compileRE(cx, s, global, false);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[80]++;
        this.lastIndex = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[81]++;
        return this;
    }

    @Override
    public String toString()
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[82]++;
        StringBuilder buf = new StringBuilder();
        buf.append('/');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[83]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[84]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((re.source.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[7]++;
            buf.append(re.source);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[85]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[8]++;
            // See bugzilla 226045
            buf.append("(?:)");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[86]++;
        }
        buf.append('/');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[87]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[88]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 (((re.flags & JSREG_GLOB) != 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[9]++;
            buf.append('g');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[89]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[10]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[90]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (((re.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[11]++;
            buf.append('i');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[91]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[12]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[92]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 (((re.flags & JSREG_MULTILINE) != 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[13]++;
            buf.append('m');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[93]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[14]++;}
        return buf.toString();
    }

    NativeRegExp() {  }

    private static RegExpImpl getImpl(Context cx)
    {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(cx);
    }

    private static String escapeRegExp(Object src) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[94]++;
        String s = ScriptRuntime.toString(src);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[95]++;
        // Escape any naked slashes in regexp source, see bug #510265
        StringBuilder sb = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[96]++; // instantiated only if necessary
        int start = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[97]++;
        int slash = s.indexOf('/');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[98]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((slash > -1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[3]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[99]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((slash == start) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s.charAt(slash - 1) != '\\') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[15]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[100]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[17]++;
                    sb = new StringBuilder();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[101]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[18]++;}
                sb.append(s, start, slash);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[102]++;
                sb.append("\\/");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[103]++;
                start = slash + 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[104]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[16]++;}
            slash = s.indexOf('/', slash + 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[105]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[106]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[19]++;
            sb.append(s, start, s.length());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[107]++;
            s = sb.toString();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[108]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[20]++;}
        return s;
    }

    private Object execSub(Context cx, Scriptable scopeObj,
                           Object[] args, int matchType)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[109]++;
        RegExpImpl reImpl = getImpl(cx);
        String str;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[110]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[21]++;
            str = reImpl.input;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[111]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[112]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((str == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[23]++;
                reportError("msg.no.re.input.for", toString());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[113]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[24]++;}

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[22]++;
            str = ScriptRuntime.toString(args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[114]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[115]++;
        double d = ((re.flags & JSREG_GLOB) != 0) ? lastIndex : 0;

        Object rval;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[116]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((d < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((str.length() < d) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[25]++;
            lastIndex = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[117]++;
            rval = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[118]++;

        }
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[26]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[119]++;
            int indexp[] = { (int)d };
            rval = executeRegExp(cx, scopeObj, reImpl, str, indexp, matchType);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[120]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[121]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (((re.flags & JSREG_GLOB) != 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[27]++;
                lastIndex = (rval == null || rval == Undefined.instance)
                            ? 0 : indexp[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[122]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[28]++;}
        }
        return rval;
    }

    static RECompiled compileRE(Context cx, String str, String global, boolean flat)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[123]++;
        RECompiled regexp = new RECompiled(str);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[124]++;
        int length = str.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[125]++;
        int flags = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[126]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((global != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[29]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[127]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < global.length()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[6]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[128]++;
                char c = global.charAt(i);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[129]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c == 'g') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[31]++;
                    flags |= JSREG_GLOB;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[130]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[32]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[131]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c == 'i') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[33]++;
                    flags |= JSREG_FOLD;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[132]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[34]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[133]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((c == 'm') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[35]++;
                    flags |= JSREG_MULTILINE;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[134]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[36]++;
                    reportError("msg.invalid.re.flag", String.valueOf(c));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[135]++;
                }
}
}
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[30]++;}
        regexp.flags = flags;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[136]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[137]++;

        CompilerState state = new CompilerState(cx, regexp.source, length, flags);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[138]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((flat) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((length > 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[37]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[139]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[39]++;
                System.out.println("flat = \"" + str + "\"");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[140]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[40]++;}
            state.result = new RENode(REOP_FLAT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[141]++;
            state.result.chr = state.cpbegin[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[142]++;
            state.result.length = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[143]++;
            state.result.flatIndex = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[144]++;
            state.progLength += 5;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[145]++;

        }
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[38]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[146]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((parseDisjunction(state)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[41]++;
                return null;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[42]++;}
}

        regexp.program = new byte[state.progLength + 1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[147]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[148]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((state.classCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[43]++;
            regexp.classList = new RECharSet[state.classCount];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[149]++;
            regexp.classCount = state.classCount;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[150]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[44]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[151]++;
        int endPC = emitREBytecode(state, regexp, 0, state.result);
        regexp.program[endPC++] = REOP_END;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[152]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[153]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[45]++;
            System.out.println("Prog. length = " + endPC);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[154]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[155]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[7]++;


int CodeCoverConditionCoverageHelper_C26;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < endPC) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[7]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[8]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[9]++;
}
                System.out.print(regexp.program[i]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[156]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[157]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < (endPC - 1)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[47]++; System.out.print(", ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[158]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[48]++;}
            }
            System.out.println();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[159]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[46]++;}
        regexp.parenCount = state.parenCount;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[160]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[161]++;

        // If re starts with literal, init anchorCh accordingly
        switch (regexp.program[0]) {
            case REOP_UCFLAT1:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[49]++;
            case REOP_UCFLAT1i:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[50]++;
                regexp.anchorCh = (char)getIndex(regexp.program, 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[162]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[163]++;
                break;
            case REOP_FLAT1:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[51]++;
            case REOP_FLAT1i:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[52]++;
                regexp.anchorCh = (char)(regexp.program[1] & 0xFF);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[164]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[165]++;
                break;
            case REOP_FLAT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[53]++;
            case REOP_FLATi:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[54]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[166]++;
                int k = getIndex(regexp.program, 1);
                regexp.anchorCh = regexp.source[k];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[167]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[168]++;
                break;
            case REOP_BOL:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[55]++;
                regexp.anchorCh = ANCHOR_BOL;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[169]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[170]++;
                break;
            case REOP_ALT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[56]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[171]++;
                RENode n = state.result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[172]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((n.kid.op == REOP_BOL) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.kid2.op == REOP_BOL) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[57]++;
                    regexp.anchorCh = ANCHOR_BOL;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[173]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[58]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[174]++;
                break; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[59]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[175]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((debug) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[60]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[176]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((regexp.anchorCh >= 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[62]++;
                System.out.println("Anchor ch = '" + (char)regexp.anchorCh + "'");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[177]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[63]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[61]++;}
        return regexp;
    }

    static boolean isDigit(char c)
    {
        return '0' <= c && c <= '9';
    }

    private static boolean isWord(char c)
    {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || isDigit(c) || c == '_';
    }

    private static boolean isControlLetter(char c)
    {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isLineTerm(char c)
    {
        return ScriptRuntime.isJSLineTerminator(c);
    }

    private static boolean isREWhiteSpace(int c)
    {
        return ScriptRuntime.isJSWhitespaceOrLineTerminator(c);
    }

    /*
     *
     * 1. If IgnoreCase is false, return ch.
     * 2. Let u be ch converted to upper case as if by calling
     *    String.prototype.toUpperCase on the one-character string ch.
     * 3. If u does not consist of a single character, return ch.
     * 4. Let cu be u's character.
     * 5. If ch's code point value is greater than or equal to decimal 128 and cu's
     *    code point value is less than decimal 128, then return ch.
     * 6. Return cu.
     */
    private static char upcase(char ch)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[178]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((ch < 128) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[64]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[179]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 (('a' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((ch <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[66]++;
                return (char)(ch + ('A' - 'a'));

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[67]++;}
            return ch;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[65]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[180]++;
        char cu = Character.toUpperCase(ch);
        return (cu < 128) ? ch : cu;
    }

    private static char downcase(char ch)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[181]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ch < 128) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[68]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[182]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 (('A' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((ch <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[70]++;
                return (char)(ch + ('a' - 'A'));

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[71]++;}
            return ch;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[69]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[183]++;
        char cl = Character.toLowerCase(ch);
        return (cl < 128) ? ch : cl;

    }

/*
 * Validates and converts hex ascii value.
 */
    private static int toASCIIHexDigit(int c)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[184]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[72]++;
            return -1;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[73]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[185]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[74]++;
            return c - '0';

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[75]++;}
        c |= 0x20;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[186]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[187]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c <= 'f') && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[76]++;
            return c - 'a' + 10;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[77]++;}
        return -1;
    }

/*
 * Top-down regular expression grammar, based closely on Perl4.
 *
 *  regexp:     altern                  A regular expression is one or more
 *              altern '|' regexp       alternatives separated by vertical bar.
 */
    private static boolean parseDisjunction(CompilerState state)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[188]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((parseAlternative(state)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[78]++;
            return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[79]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[189]++;
        char[] source = state.cpbegin;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[190]++;
        int index = state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[191]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((index != source.length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((source[index] == '|') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[80]++;
            RENode result;
            ++state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[192]++;
            result = new RENode(REOP_ALT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[193]++;
            result.kid = state.result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[194]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[195]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((parseDisjunction(state)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[82]++;
                return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[83]++;}
            result.kid2 = state.result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[196]++;
            state.result = result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[197]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[198]++;
int CodeCoverConditionCoverageHelper_C41;
            /*
             * Look at both alternates to see if there's a FLAT or a CLASS at
             * the start of each. If so, use a prerequisite match.
             */
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((result.kid.op == REOP_FLAT) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((result.kid2.op == REOP_FLAT) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[84]++;
                result.op = (state.flags & JSREG_FOLD) == 0 ?
                        REOP_ALTPREREQ : REOP_ALTPREREQi;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[199]++;
                result.chr = result.kid.chr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[200]++;
                result.index = result.kid2.chr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[201]++;
                /* ALTPREREQ, uch1, uch2, <next>, ...,
                                            JUMP, <end> ... JUMP, <end> */
                state.progLength += 13;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[202]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[85]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[203]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (128)) == 0 || true) &&
 ((result.kid.op == REOP_CLASS) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (32)) == 0 || true) &&
 ((result.kid.index < 256) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((result.kid2.op == REOP_FLAT) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 (((state.flags & JSREG_FOLD) == 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 4) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 4) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[86]++;
                result.op = REOP_ALTPREREQ2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[204]++;
                result.chr = result.kid2.chr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[205]++;
                result.index = result.kid.index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[206]++;
                /* ALTPREREQ2, uch1, uch2, <next>, ...,
                                            JUMP, <end> ... JUMP, <end> */
                state.progLength += 13;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[207]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[87]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[208]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (128)) == 0 || true) &&
 ((result.kid.op == REOP_FLAT) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (32)) == 0 || true) &&
 ((result.kid2.op == REOP_CLASS) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((result.kid2.index < 256) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 (((state.flags & JSREG_FOLD) == 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 4) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 4) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[88]++;
                result.op = REOP_ALTPREREQ2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[209]++;
                result.chr = result.kid.chr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[210]++;
                result.index = result.kid2.index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[211]++;
                /* ALTPREREQ2, uch1, uch2, <next>, ...,
                                            JUMP, <end> ... JUMP, <end> */
                state.progLength += 13;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[212]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[89]++;
                /* ALT, <next>, ..., JUMP, <end> ... JUMP, <end> */
                state.progLength += 9;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[213]++;
            }
}
}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[81]++;}
        return true;
    }

/*
 *  altern:     item                    An alternative is one or more items,
 *              item altern             concatenated together.
 */
    private static boolean parseAlternative(CompilerState state)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[214]++;
        RENode headTerm = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[215]++;
        RENode tailTerm = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[216]++;
        char[] source = state.cpbegin;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[217]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[10]++;


        while (true) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[10]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[11]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[12]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[218]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (128)) == 0 || true) &&
 ((state.cp == state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C45 |= (32)) == 0 || true) &&
 ((source[state.cp] == '|') && 
  ((CodeCoverConditionCoverageHelper_C45 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((state.parenNesting != 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((source[state.cp] == ')') && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 4) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 4) && false))
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[90]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[219]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((headTerm == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[92]++;
                    state.result = new RENode(REOP_EMPTY);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[220]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[93]++;
                    state.result = headTerm;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[221]++;
}
                return true;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[91]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[222]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((parseTerm(state)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[94]++;
                return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[95]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[223]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((headTerm == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[96]++;
                headTerm = state.result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[224]++;
                tailTerm = headTerm;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[225]++;

            }
            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[97]++;
                tailTerm.next = state.result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[226]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[227]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[13]++;


int CodeCoverConditionCoverageHelper_C49;
            while ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((tailTerm.next != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) { 
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[13]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[14]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[15]++;
} tailTerm = tailTerm.next;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[228]++;
  }
        }
    }

    /* calculate the total size of the bitmap required for a class expression */
    private static boolean
    calculateBitmapSize(CompilerState state, RENode target, char[] src,
                        int index, int end)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[229]++;
        char rangeStart = 0;
        char c;
        int n;
        int nDigits;
        int i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[230]++;
        int max = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[231]++;
        boolean inRange = false;

        target.bmsize = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[232]++;
        target.sense = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[233]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[234]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((index == end) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[98]++;
            return true;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[99]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[235]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((src[index] == '^') && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[100]++;
            ++index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[236]++;
            target.sense = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[237]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[101]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[238]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[16]++;


int CodeCoverConditionCoverageHelper_C52;

        while ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((index != end) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[16]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[17]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[18]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[239]++;
            int localMax = 0;
            nDigits = 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[240]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[241]++;
            switch (src[index]) {
            case '\\':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[102]++;
                ++index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[242]++;
                c = src[index++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[243]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[244]++;
                switch (c) {
                case 'b':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[103]++;
                    localMax = 0x8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[245]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[246]++;
                    break;
                case 'f':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[104]++;
                    localMax = 0xC;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[247]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[248]++;
                    break;
                case 'n':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[105]++;
                    localMax = 0xA;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[249]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[250]++;
                    break;
                case 'r':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[106]++;
                    localMax = 0xD;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[251]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[252]++;
                    break;
                case 't':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[107]++;
                    localMax = 0x9;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[253]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[254]++;
                    break;
                case 'v':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[108]++;
                    localMax = 0xB;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[255]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[256]++;
                    break;
                case 'c':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[109]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[257]++;
int CodeCoverConditionCoverageHelper_C53;
                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((index < end) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((isControlLetter(src[index])) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[110]++;
                        localMax = (char)(src[index++] & 0x1F);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[258]++;
}
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[111]++;
                        --index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[259]++;
}
                        localMax = '\\';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[260]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[261]++;
                    break;
                case 'u':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[112]++;
                    nDigits += 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[262]++;
                    // fall thru...
                case 'x':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[113]++;
                    n = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[263]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[264]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[19]++;


int CodeCoverConditionCoverageHelper_C54;
                    for (i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((i < nDigits) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((index < end) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[19]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[20]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[21]++;
}
                        c = src[index++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[265]++;
                        n = Kit.xDigitToInt(c, n);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[266]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[267]++;
int CodeCoverConditionCoverageHelper_C55;
                        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[114]++;
                            // Back off to accepting the original
                            // '\' as a literal
                            index -= (i + 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[268]++;
                            n = '\\';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[269]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[270]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[115]++;}
                    }
                    localMax = n;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[271]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[272]++;
                    break;
                case 'd':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[116]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[273]++;
int CodeCoverConditionCoverageHelper_C56;
                    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((inRange) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[117]++;
                        reportError("msg.bad.range", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[274]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[118]++;}
                    localMax = '9';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[275]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[276]++;
                    break;
                case 'D':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[119]++;
                case 's':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[120]++;
                case 'S':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[121]++;
                case 'w':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[122]++;
                case 'W':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[123]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[277]++;
int CodeCoverConditionCoverageHelper_C57;
                    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((inRange) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[124]++;
                        reportError("msg.bad.range", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[278]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[125]++;}
                    target.bmsize = 65536;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[279]++;
                    return true;
                case '0':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[126]++;
                case '1':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[127]++;
                case '2':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[128]++;
                case '3':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[129]++;
                case '4':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[130]++;
                case '5':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[131]++;
                case '6':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[132]++;
                case '7':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[133]++;
                    /*
                     *  This is a non-ECMA extension - decimal escapes (in this
                     *  case, octal!) are supposed to be an error inside class
                     *  ranges, but supported here for backwards compatibility.
                     *
                     */
                    n = (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[280]++;
                    c = src[index];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[281]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[282]++;
int CodeCoverConditionCoverageHelper_C58;
                    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((c <= '7') && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[134]++;
                        index++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[283]++;
                        n = 8 * n + (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[284]++;
                        c = src[index];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[285]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[286]++;
int CodeCoverConditionCoverageHelper_C59;
                        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((c <= '7') && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[136]++;
                            index++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[287]++;
                            i = 8 * n + (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[288]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[289]++;
int CodeCoverConditionCoverageHelper_C60;
                            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((i <= 0377) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[138]++;
                                n = i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[290]++;
}
                            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[139]++;
                                index--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[291]++;
}

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[137]++;}

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[135]++;}
                    localMax = n;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[292]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[293]++;
                    break;

                default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[140]++;
                    localMax = c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[294]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[295]++;
                    break;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[296]++;
                break;
            default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[141]++;
                localMax = src[index++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[297]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[298]++;
                break;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[299]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((inRange) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[142]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[300]++;
int CodeCoverConditionCoverageHelper_C62;
                if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((rangeStart > localMax) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[144]++;
                    reportError("msg.bad.range", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[301]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[145]++;}
                inRange = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[302]++;

            }
            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[143]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[303]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((index < (end - 1)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[146]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[304]++;
int CodeCoverConditionCoverageHelper_C64;
                    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((src[index] == '-') && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[148]++;
                        ++index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[305]++;
                        inRange = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[306]++;
                        rangeStart = (char)localMax;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[307]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[308]++;
                        continue;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[149]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[147]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[309]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 (((state.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)){
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[150]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[310]++;
                char cu = upcase((char)localMax);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[311]++;
                char cd = downcase((char)localMax);
                localMax = (cu >= cd) ? cu : cd;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[312]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[151]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[313]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((localMax > max) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[152]++;
                max = localMax;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[314]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[153]++;}
        }
        target.bmsize = max + 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[315]++;
        return true;
    }

    /*
     *  item:       assertion               An item is either an assertion or
     *              quantatom               a quantified atom.
     *
     *  assertion:  '^'                     Assertions match beginning of string
     *                                      (or line if the class static property
     *                                      RegExp.multiline is true).
     *              '$'                     End of string (or line if the class
     *                                      static property RegExp.multiline is
     *                                      true).
     *              '\b'                    Word boundary (between \w and \W).
     *              '\B'                    Word non-boundary.
     *
     *  quantatom:  atom                    An unquantified atom.
     *              quantatom '{' n ',' m '}'
     *                                      Atom must occur between n and m times.
     *              quantatom '{' n ',' '}' Atom must occur at least n times.
     *              quantatom '{' n '}'     Atom must occur exactly n times.
     *              quantatom '*'           Zero or more times (same as {0,}).
     *              quantatom '+'           One or more times (same as {1,}).
     *              quantatom '?'           Zero or one time (same as {0,1}).
     *
     *              any of which can be optionally followed by '?' for ungreedy
     *
     *  atom:       '(' regexp ')'          A parenthesized regexp (what matched
     *                                      can be addressed using a backreference,
     *                                      see '\' n below).
     *              '.'                     Matches any char except '\n'.
     *              '[' classlist ']'       A character class.
     *              '[' '^' classlist ']'   A negated character class.
     *              '\f'                    Form Feed.
     *              '\n'                    Newline (Line Feed).
     *              '\r'                    Carriage Return.
     *              '\t'                    Horizontal Tab.
     *              '\v'                    Vertical Tab.
     *              '\d'                    A digit (same as [0-9]).
     *              '\D'                    A non-digit.
     *              '\w'                    A word character, [0-9a-z_A-Z].
     *              '\W'                    A non-word character.
     *              '\s'                    A whitespace character, [ \b\f\n\r\t\v].
     *              '\S'                    A non-whitespace character.
     *              '\' n                   A backreference to the nth (n decimal
     *                                      and positive) parenthesized expression.
     *              '\' octal               An octal escape sequence (octal must be
     *                                      two or three digits long, unless it is
     *                                      0 for the null character).
     *              '\x' hex                A hex escape (hex must be two digits).
     *              '\c' ctrl               A control character, ctrl is a letter.
     *              '\' literalatomchar     Any character except one of the above
     *                                      that follow '\' in an atom.
     *              otheratomchar           Any character not first among the other
     *                                      atom right-hand sides.
     */

    private static void doFlat(CompilerState state, char c)
    {
        state.result = new RENode(REOP_FLAT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[316]++;
        state.result.chr = c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[317]++;
        state.result.length = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[318]++;
        state.result.flatIndex = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[319]++;
        state.progLength += 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[320]++;
    }

    private static int
    getDecimalValue(char c, CompilerState state, int maxValue,
                    String overflowMessageId)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[321]++;
        boolean overflow = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[322]++;
        int start = state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[323]++;
        char[] src = state.cpbegin;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[324]++;
        int value = c - '0';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[325]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[22]++;


int CodeCoverConditionCoverageHelper_C67;
        for (;(((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((state.cp != state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false); ++state.cp) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[22]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[23]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[24]++;
}
            c = src[state.cp];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[326]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[327]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((isDigit(c)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[154]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[328]++;
                break;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[155]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[329]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((overflow) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[156]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[330]++;
                int digit = c - '0';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[331]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((value < (maxValue - digit) / 10) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[158]++;
                    value = value * 10 + digit;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[332]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[159]++;
                    overflow = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[333]++;
                    value = maxValue;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[334]++;
                }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[157]++;}
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[335]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((overflow) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[160]++;
            reportError(overflowMessageId,
                        String.valueOf(src, start, state.cp - start));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[336]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[161]++;}
        return value;
    }

    private static boolean
    parseTerm(CompilerState state)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[337]++;
        char[] src = state.cpbegin;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[338]++;
        char c = src[state.cp++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[339]++;
        int nDigits = 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[340]++;
        int parenBaseCount = state.parenCount;
        int num, tmp;
        RENode term;
        int termStart;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[341]++;

        switch (c) {
        /* assertions and atoms */
        case '^':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[162]++;
            state.result = new RENode(REOP_BOL);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[342]++;
            state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[343]++;
            return true;
        case '$':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[163]++;
            state.result = new RENode(REOP_EOL);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[344]++;
            state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[345]++;
            return true;
        case '\\':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[164]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[346]++;
int CodeCoverConditionCoverageHelper_C72;
            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((state.cp < state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[165]++;
                c = src[state.cp++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[347]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[348]++;
                switch (c) {
                /* assertion escapes */
                case 'b' :
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[167]++;
                    state.result = new RENode(REOP_WBDRY);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[349]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[350]++;
                    return true;
                case 'B':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[168]++;
                    state.result = new RENode(REOP_WNONBDRY);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[351]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[352]++;
                    return true;
                /* Decimal escape */
                case '0':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[169]++;
/*
 * Under 'strict' ECMA 3, we interpret \0 as NUL and don't accept octal.
 * However, (XXX and since Rhino doesn't have a 'strict' mode) we'll just
 * behave the old way for compatibility reasons.
 * (see http://bugzilla.mozilla.org/show_bug.cgi?id=141078)
 *
 */
                    reportWarning(state.cx, "msg.bad.backref", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[353]++;
                    /* octal escape */
                    num = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[354]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[355]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[25]++;


int CodeCoverConditionCoverageHelper_C73;
                    while ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((state.cp < state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[25]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[26]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[27]++;
}
                        c = src[state.cp];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[356]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[357]++;
int CodeCoverConditionCoverageHelper_C74;
                        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((c <= '7') && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[170]++;
                            state.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[358]++;
                            tmp = 8 * num + (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[359]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[360]++;
int CodeCoverConditionCoverageHelper_C75;
                            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((tmp > 0377) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[172]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[361]++;
                                break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[173]++;}
                            num = tmp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[362]++;

                        }
                        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[171]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[363]++;
                            break;
}
                    }
                    c = (char)(num);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[364]++;
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[365]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[366]++;
                    break;
                case '1':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[174]++;
                case '2':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[175]++;
                case '3':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[176]++;
                case '4':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[177]++;
                case '5':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[178]++;
                case '6':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[179]++;
                case '7':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[180]++;
                case '8':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[181]++;
                case '9':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[182]++;
                    termStart = state.cp - 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[367]++;
                    num = getDecimalValue(c, state, 0xFFFF,
                                          "msg.overlarge.backref");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[368]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[369]++;
int CodeCoverConditionCoverageHelper_C76;
                    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((num > state.parenCount) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[183]++;
                        reportWarning(state.cx, "msg.bad.backref", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[370]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[184]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[371]++;
int CodeCoverConditionCoverageHelper_C77;
                    /*
                     * n > 9 or > count of parentheses,
                     * then treat as octal instead.
                     */
                    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((num > 9) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((num > state.parenCount) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[185]++;
                        state.cp = termStart;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[372]++;
                        num = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[373]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[374]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[28]++;


int CodeCoverConditionCoverageHelper_C78;
                        while ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((state.cp < state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[28]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[29]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[30]++;
}
                            c = src[state.cp];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[375]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[376]++;
int CodeCoverConditionCoverageHelper_C79;
                            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((c >= '0') && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((c <= '7') && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[187]++;
                                state.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[377]++;
                                tmp = 8 * num + (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[378]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[379]++;
int CodeCoverConditionCoverageHelper_C80;
                                if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((tmp > 0377) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[189]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[380]++;
                                    break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[190]++;}
                                num = tmp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[381]++;

                            }
                            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[188]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[382]++;
                                break;
}
                        }
                        c = (char)(num);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[383]++;
                        doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[384]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[385]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[186]++;}
                    /* otherwise, it's a back-reference */
                    state.result = new RENode(REOP_BACKREF);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[386]++;
                    state.result.parenIndex = num - 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[387]++;
                    state.progLength += 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[388]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[389]++;
                    break;
                /* Control escape */
                case 'f':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[191]++;
                    c = 0xC;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[390]++;
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[391]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[392]++;
                    break;
                case 'n':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[192]++;
                    c = 0xA;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[393]++;
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[394]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[395]++;
                    break;
                case 'r':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[193]++;
                    c = 0xD;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[396]++;
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[397]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[398]++;
                    break;
                case 't':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[194]++;
                    c = 0x9;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[399]++;
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[400]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[401]++;
                    break;
                case 'v':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[195]++;
                    c = 0xB;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[402]++;
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[403]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[404]++;
                    break;
                /* Control letter */
                case 'c':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[196]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[405]++;
int CodeCoverConditionCoverageHelper_C81;
                    if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((state.cp < state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((isControlLetter(src[state.cp])) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[197]++;
                        c = (char)(src[state.cp++] & 0x1F);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[406]++;
}
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[198]++;
                        /* back off to accepting the original '\' as a literal */
                        --state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[407]++;
                        c = '\\';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[408]++;
                    }
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[409]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[410]++;
                    break;
                /* UnicodeEscapeSequence */
                case 'u':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[199]++;
                    nDigits += 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[411]++;
                    // fall thru...
                /* HexEscapeSequence */
                case 'x':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[200]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[412]++;
                        int n = 0;
                        int i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[413]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[31]++;


int CodeCoverConditionCoverageHelper_C82;
                        for (i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((i < nDigits) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((state.cp < state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[31]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[32]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[33]++;
}
                            c = src[state.cp++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[414]++;
                            n = Kit.xDigitToInt(c, n);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[415]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[416]++;
int CodeCoverConditionCoverageHelper_C83;
                            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[201]++;
                                // Back off to accepting the original
                                // 'u' or 'x' as a literal
                                state.cp -= (i + 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[417]++;
                                n = src[state.cp++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[418]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[419]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[202]++;}
                        }
                        c = (char)(n);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[420]++;
                    }
                    doFlat(state, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[421]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[422]++;
                    break;
                /* Character class escapes */
                case 'd':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[203]++;
                    state.result = new RENode(REOP_DIGIT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[423]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[424]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[425]++;
                    break;
                case 'D':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[204]++;
                    state.result = new RENode(REOP_NONDIGIT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[426]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[427]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[428]++;
                    break;
                case 's':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[205]++;
                    state.result = new RENode(REOP_SPACE);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[429]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[430]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[431]++;
                    break;
                case 'S':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[206]++;
                    state.result = new RENode(REOP_NONSPACE);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[432]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[433]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[434]++;
                    break;
                case 'w':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[207]++;
                    state.result = new RENode(REOP_ALNUM);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[435]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[436]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[437]++;
                    break;
                case 'W':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[208]++;
                    state.result = new RENode(REOP_NONALNUM);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[438]++;
                    state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[439]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[440]++;
                    break;
                /* IdentityEscape */
                default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[209]++;
                    state.result = new RENode(REOP_FLAT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[441]++;
                    state.result.chr = c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[442]++;
                    state.result.length = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[443]++;
                    state.result.flatIndex = state.cp - 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[444]++;
                    state.progLength += 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[445]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[446]++;
                    break;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[447]++;
                break;

            }
            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[166]++;
                /* a trailing '\' is an error */
                reportError("msg.trail.backslash", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[448]++;
                return false;
            }
        case '(':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[210]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[449]++;
            RENode result = null;
            termStart = state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[450]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[451]++;
            if (state.cp + 1 < state.cpend && src[state.cp] == '?' && ((c = src[state.cp + 1]) == '=' || c == '!' || c == ':'))
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[211]++;
                state.cp += 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[452]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[453]++;
int CodeCoverConditionCoverageHelper_C85;
                if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((c == '=') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[213]++;
                    result = new RENode(REOP_ASSERT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[454]++;
                    /* ASSERT, <next>, ... ASSERTTEST */
                    state.progLength += 4;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[455]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[214]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[456]++;
int CodeCoverConditionCoverageHelper_C86; if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((c == '!') && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[215]++;
                    result = new RENode(REOP_ASSERT_NOT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[457]++;
                    /* ASSERTNOT, <next>, ... ASSERTNOTTEST */
                    state.progLength += 4;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[458]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[216]++;}
}

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[212]++;
                result = new RENode(REOP_LPAREN);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[459]++;
                /* LPAREN, <index>, ... RPAREN, <index> */
                state.progLength += 6;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[460]++;
                result.parenIndex = state.parenCount++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[461]++;
            }
            ++state.parenNesting;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[462]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[463]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((parseDisjunction(state)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[217]++;
                return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[218]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[464]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((state.cp == state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((src[state.cp] != ')') && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[219]++;
                reportError("msg.unterm.paren", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[465]++;
                return false;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[220]++;}
            ++state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[466]++;
            --state.parenNesting;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[467]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[468]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[221]++;
                result.kid = state.result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[469]++;
                state.result = result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[470]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[222]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[471]++;
            break;
        }
        case ')':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[223]++;
          reportError("msg.re.unmatched.right.paren", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[472]++;
          return false;
        case '[':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[224]++;
            state.result = new RENode(REOP_CLASS);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[473]++;
            termStart = state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[474]++;
            state.result.startIndex = termStart;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[475]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[476]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[34]++;


            while (true) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[34]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[35]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[36]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[477]++;
int CodeCoverConditionCoverageHelper_C91;
                if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((state.cp == state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[225]++;
                    reportError("msg.unterm.class", "");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[478]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[226]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[479]++;
int CodeCoverConditionCoverageHelper_C92;
                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((src[state.cp] == '\\') && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[227]++;
                    state.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[480]++;
}
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[228]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[481]++;
int CodeCoverConditionCoverageHelper_C93;
                    if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((src[state.cp] == ']') && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[229]++;
                        state.result.kidlen = state.cp - termStart;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[482]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[483]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[230]++;}
                }
                state.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[484]++;
            }
            state.result.index = state.classCount++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[485]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[486]++;
int CodeCoverConditionCoverageHelper_C94;
            /*
             * Call calculateBitmapSize now as we want any errors it finds
             * to be reported during the parse phase, not at execution.
             */
            if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((calculateBitmapSize(state, state.result, src, termStart, state.cp++)) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[231]++;
                return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[232]++;}
            state.progLength += 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[487]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[488]++; /* CLASS, <index> */
            break;

        case '.':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[233]++;
            state.result = new RENode(REOP_DOT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[489]++;
            state.progLength++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[490]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[491]++;
            break;
        case '*':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[234]++;
        case '+':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[235]++;
        case '?':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[236]++;
            reportError("msg.bad.quant", String.valueOf(src[state.cp - 1]));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[492]++;
            return false;
        default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[237]++;
            state.result = new RENode(REOP_FLAT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[493]++;
            state.result.chr = c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[494]++;
            state.result.length = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[495]++;
            state.result.flatIndex = state.cp - 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[496]++;
            state.progLength += 3;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[497]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[498]++;
            break;
        }

        term = state.result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[499]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[500]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((state.cp == state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[238]++;
            return true;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[239]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[501]++;
        boolean hasQ = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[502]++;
        switch (src[state.cp]) {
            case '+':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[240]++;
                state.result = new RENode(REOP_QUANT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[503]++;
                state.result.min = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[504]++;
                state.result.max = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[505]++;
                /* <PLUS>, <parencount>, <parenindex>, <next> ... <ENDCHILD> */
                state.progLength += 8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[506]++;
                hasQ = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[507]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[508]++;
                break;
            case '*':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[241]++;
                state.result = new RENode(REOP_QUANT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[509]++;
                state.result.min = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[510]++;
                state.result.max = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[511]++;
                /* <STAR>, <parencount>, <parenindex>, <next> ... <ENDCHILD> */
                state.progLength += 8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[512]++;
                hasQ = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[513]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[514]++;
                break;
            case '?':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[242]++;
                state.result = new RENode(REOP_QUANT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[515]++;
                state.result.min = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[516]++;
                state.result.max = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[517]++;
                /* <OPT>, <parencount>, <parenindex>, <next> ... <ENDCHILD> */
                state.progLength += 8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[518]++;
                hasQ = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[519]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[520]++;
                break;
            case '{':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[243]++;  /* balance '}' */
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[521]++;
                int min = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[522]++;
                int max = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[523]++;
                int leftCurl = state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[524]++;

               /* For Perl etc. compatibility, if quntifier does not match
                * \{\d+(,\d*)?\} exactly back off from it
                * being a quantifier, and chew it up as a literal
                * atom next time instead.
                */

                if (++state.cp < src.length && isDigit(c = src[state.cp])) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[244]++;
                    ++state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[525]++;
                    min = getDecimalValue(c, state, 0xFFFF,
                                          "msg.overlarge.min");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[526]++;
                    c = src[state.cp];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[527]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[528]++;
int CodeCoverConditionCoverageHelper_C97;
                    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((c == ',') && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[246]++;
                        c = src[++state.cp];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[529]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[530]++;
int CodeCoverConditionCoverageHelper_C98;
                        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((isDigit(c)) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[248]++;
                            ++state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[531]++;
                            max = getDecimalValue(c, state, 0xFFFF,
                                                  "msg.overlarge.max");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[532]++;
                            c = src[state.cp];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[533]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[534]++;
int CodeCoverConditionCoverageHelper_C99;
                            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((min > max) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[250]++;
                                reportError("msg.max.lt.min",
                                            String.valueOf(src[state.cp]));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[535]++;
                                return false;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[251]++;}

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[249]++;}

                    } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[247]++;
                        max = min;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[536]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[537]++;
int CodeCoverConditionCoverageHelper_C100;
                    /* balance '{' */
                    if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((c == '}') && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[252]++;
                        state.result = new RENode(REOP_QUANT);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[538]++;
                        state.result.min = min;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[539]++;
                        state.result.max = max;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[540]++;
                        // QUANT, <min>, <max>, <parencount>,
                        // <parenindex>, <next> ... <ENDCHILD>
                        state.progLength += 12;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[541]++;
                        hasQ = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[542]++;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[253]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[245]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[543]++;
int CodeCoverConditionCoverageHelper_C101;
                if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((hasQ) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[254]++;
                    state.cp = leftCurl;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[544]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[255]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[545]++;
                break;
            } default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[256]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[546]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((hasQ) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[257]++;
            return true;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[258]++;}

        ++state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[547]++;
        state.result.kid = term;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[548]++;
        state.result.parenIndex = parenBaseCount;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[549]++;
        state.result.parenCount = state.parenCount - parenBaseCount;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[550]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[551]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C103 |= (8)) == 0 || true) &&
 ((state.cp < state.cpend) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((src[state.cp] == '?') && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[259]++;
            ++state.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[552]++;
            state.result.greedy = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[553]++;

        }
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[260]++;
            state.result.greedy = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[554]++;
}
        return true;
    }

    private static void resolveForwardJump(byte[] array, int from, int pc)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[555]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((from > pc) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[261]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[262]++;}
        addIndex(array, from, pc - from);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[556]++;
    }

    private static int getOffset(byte[] array, int pc)
    {
        return getIndex(array, pc);
    }

    private static int addIndex(byte[] array, int pc, int index)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[557]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[263]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[264]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[558]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((index > 0xFFFF) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[265]++;
            throw Context.reportRuntimeError("Too complex regexp");
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[266]++;}
        array[pc] = (byte)(index >> 8);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[559]++;
        array[pc + 1] = (byte)(index);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[560]++;
        return pc + 2;
    }

    private static int getIndex(byte[] array, int pc)
    {
        return ((array[pc] & 0xFF) << 8) | (array[pc + 1] & 0xFF);
    }

    private static final int INDEX_LEN  = 2;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[561]++;
  }

    private static int
    emitREBytecode(CompilerState state, RECompiled re, int pc, RENode t)
    {
        RENode nextAlt;
        int nextAltFixup, nextTermFixup;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[562]++;
        byte[] program = re.program;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[563]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[37]++;


int CodeCoverConditionCoverageHelper_C107;

        while ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[37]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[38]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[39]++;
}
            program[pc++] = t.op;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[564]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[565]++;
            switch (t.op) {
            case REOP_EMPTY:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[267]++;
                --pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[566]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[567]++;
                break;
            case REOP_ALTPREREQ:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[268]++;
            case REOP_ALTPREREQi:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[269]++;
            case REOP_ALTPREREQ2:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[270]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[568]++;
                boolean ignoreCase = t.op == REOP_ALTPREREQi;
                addIndex(program, pc, ignoreCase ? upcase(t.chr) : t.chr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[569]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[570]++;
                addIndex(program, pc, ignoreCase ? upcase((char)t.index) : t.index);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[571]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[572]++;
                // fall through to REOP_ALT
            case REOP_ALT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[271]++;
                nextAlt = t.kid2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[573]++;
                nextAltFixup = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[574]++;    /* address of next alternate */
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[575]++;
                pc = emitREBytecode(state, re, pc, t.kid);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[576]++;
                program[pc++] = REOP_JUMP;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[577]++;
                nextTermFixup = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[578]++;    /* address of following term */
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[579]++;
                resolveForwardJump(program, nextAltFixup, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[580]++;
                pc = emitREBytecode(state, re, pc, nextAlt);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[581]++;

                program[pc++] = REOP_JUMP;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[582]++;
                nextAltFixup = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[583]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[584]++;

                resolveForwardJump(program, nextTermFixup, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[585]++;
                resolveForwardJump(program, nextAltFixup, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[586]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[587]++;
                break;
            case REOP_FLAT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[272]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[588]++;
int CodeCoverConditionCoverageHelper_C108;
                /*
                 * Consecutize FLAT's if possible.
                 */
                if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((t.flatIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[273]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[589]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[40]++;


int CodeCoverConditionCoverageHelper_C109;
                    while ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C109 |= (32)) == 0 || true) &&
 ((t.next != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C109 |= (8)) == 0 || true) &&
 ((t.next.op == REOP_FLAT) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 (((t.flatIndex + t.length)
                                            == t.next.flatIndex) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 3) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[40]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[41]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[42]++;
}
                        t.length += t.next.length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[590]++;
                        t.next = t.next.next;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[591]++;
                    }

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[274]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[592]++;
int CodeCoverConditionCoverageHelper_C110;
                if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 ((t.flatIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((t.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[275]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[593]++;
int CodeCoverConditionCoverageHelper_C111;
                    if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 (((state.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[277]++;
                        program[pc - 1] = REOP_FLATi;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[594]++;
}
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[278]++;
                        program[pc - 1] = REOP_FLAT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[595]++;
}
                    pc = addIndex(program, pc, t.flatIndex);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[596]++;
                    pc = addIndex(program, pc, t.length);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[597]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[276]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[598]++;
int CodeCoverConditionCoverageHelper_C112;
                    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((t.chr < 256) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[279]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[599]++;
int CodeCoverConditionCoverageHelper_C113;
                        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 (((state.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[281]++;
                            program[pc - 1] = REOP_FLAT1i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[600]++;
}
                        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[282]++;
                            program[pc - 1] = REOP_FLAT1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[601]++;
}
                        program[pc++] = (byte)(t.chr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[602]++;

                    }
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[280]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[603]++;
int CodeCoverConditionCoverageHelper_C114;
                        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 (((state.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[283]++;
                            program[pc - 1] = REOP_UCFLAT1i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[604]++;
}
                        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[284]++;
                            program[pc - 1] = REOP_UCFLAT1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[605]++;
}
                        pc = addIndex(program, pc, t.chr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[606]++;
                    }
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[607]++;
                break;
            case REOP_LPAREN:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[285]++;
                pc = addIndex(program, pc, t.parenIndex);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[608]++;
                pc = emitREBytecode(state, re, pc, t.kid);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[609]++;
                program[pc++] = REOP_RPAREN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[610]++;
                pc = addIndex(program, pc, t.parenIndex);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[611]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[612]++;
                break;
            case REOP_BACKREF:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[286]++;
                pc = addIndex(program, pc, t.parenIndex);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[613]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[614]++;
                break;
            case REOP_ASSERT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[287]++;
                nextTermFixup = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[615]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[616]++;
                pc = emitREBytecode(state, re, pc, t.kid);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[617]++;
                program[pc++] = REOP_ASSERTTEST;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[618]++;
                resolveForwardJump(program, nextTermFixup, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[619]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[620]++;
                break;
            case REOP_ASSERT_NOT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[288]++;
                nextTermFixup = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[621]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[622]++;
                pc = emitREBytecode(state, re, pc, t.kid);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[623]++;
                program[pc++] = REOP_ASSERTNOTTEST;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[624]++;
                resolveForwardJump(program, nextTermFixup, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[625]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[626]++;
                break;
            case REOP_QUANT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[289]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[627]++;
int CodeCoverConditionCoverageHelper_C115;
                if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C115 |= (8)) == 0 || true) &&
 ((t.min == 0) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((t.max == -1) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[290]++;
                    program[pc - 1] = (t.greedy) ? REOP_STAR : REOP_MINIMALSTAR;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[628]++;
}
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[291]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[629]++;
int CodeCoverConditionCoverageHelper_C116;
                if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C116 |= (8)) == 0 || true) &&
 ((t.min == 0) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((t.max == 1) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[292]++;
                    program[pc - 1] = (t.greedy) ? REOP_OPT : REOP_MINIMALOPT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[630]++;
}
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[293]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[631]++;
int CodeCoverConditionCoverageHelper_C117;
                if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C117 |= (8)) == 0 || true) &&
 ((t.min == 1) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((t.max == -1) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[294]++;
                    program[pc - 1] = (t.greedy) ? REOP_PLUS : REOP_MINIMALPLUS;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[632]++;
}
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[295]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[633]++;
int CodeCoverConditionCoverageHelper_C118;
                    if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((t.greedy) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[296]++; program[pc - 1] = REOP_MINIMALQUANT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[634]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[297]++;}
                    pc = addIndex(program, pc, t.min);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[635]++;
                    // max can be -1 which addIndex does not accept
                    pc = addIndex(program, pc, t.max + 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[636]++;
                }
}
}
                pc = addIndex(program, pc, t.parenCount);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[637]++;
                pc = addIndex(program, pc, t.parenIndex);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[638]++;
                nextTermFixup = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[639]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[640]++;
                pc = emitREBytecode(state, re, pc, t.kid);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[641]++;
                program[pc++] = REOP_ENDCHILD;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[642]++;
                resolveForwardJump(program, nextTermFixup, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[643]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[644]++;
                break;
            case REOP_CLASS:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[298]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[645]++;
int CodeCoverConditionCoverageHelper_C119;
                if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((t.sense) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[299]++;
                    program[pc - 1] = REOP_NCLASS;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[646]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[300]++;}
                pc = addIndex(program, pc, t.index);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[647]++;
                re.classList[t.index] = new RECharSet(t.bmsize, t.startIndex,
                                                      t.kidlen, t.sense);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[648]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[649]++;
                break;
            default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[301]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[650]++;
                break;
            }
            t = t.next;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[651]++;
        }
        return pc;
    }

    private static void
    pushProgState(REGlobalData gData, int min, int max, int cp,
                  REBackTrackData backTrackLastToSave,
                  int continuationOp, int continuationPc)
    {
        gData.stateStackTop = new REProgState(gData.stateStackTop, min, max,
                                              cp, backTrackLastToSave,
                                              continuationOp, continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[652]++;
    }

    private static REProgState
    popProgState(REGlobalData gData)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[653]++;
        REProgState state = gData.stateStackTop;
        gData.stateStackTop = state.previous;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[654]++;
        return state;
    }

    private static void
    pushBackTrackState(REGlobalData gData, byte op, int pc)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[655]++;
        REProgState state = gData.stateStackTop;
        gData.backTrackStackTop = new REBackTrackData(gData, op, pc,
                gData.cp, state.continuationOp, state.continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[656]++;
    }

    private static void
    pushBackTrackState(REGlobalData gData, byte op, int pc,
                       int cp, int continuationOp, int continuationPc)
    {
        gData.backTrackStackTop = new REBackTrackData(gData, op, pc,
                cp, continuationOp, continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[657]++;
    }

    /*
     *   Consecutive literal characters.
     */
    private static boolean
    flatNMatcher(REGlobalData gData, int matchChars,
                 int length, String input, int end)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[658]++;
int CodeCoverConditionCoverageHelper_C120;
        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 (((gData.cp + length) > end) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[302]++;
            return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[303]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[659]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[43]++;


int CodeCoverConditionCoverageHelper_C121;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[43]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[44]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[45]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[660]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((gData.regexp.source[matchChars + i] != input.charAt(gData.cp + i)) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[304]++;
                return false;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[305]++;}
        }
        gData.cp += length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[661]++;
        return true;
    }

    private static boolean
    flatNIMatcher(REGlobalData gData, int matchChars,
                  int length, String input, int end)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[662]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 (((gData.cp + length) > end) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[306]++;
            return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[307]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[663]++;
        char[] source = gData.regexp.source;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[664]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[46]++;


int CodeCoverConditionCoverageHelper_C124;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[46]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[47]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[48]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[665]++;
            char c1 = source[matchChars + i];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[666]++;
            char c2 = input.charAt(gData.cp + i);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[667]++;
int CodeCoverConditionCoverageHelper_C125;
            if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((c1 != c2) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((upcase(c1) != upcase(c2)) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[308]++;
                return false;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[309]++;}
        }
        gData.cp += length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[668]++;
        return true;
    }

    /*
    1. Evaluate DecimalEscape to obtain an EscapeValue E.
    2. If E is not a character then go to step 6.
    3. Let ch be E's character.
    4. Let A be a one-element RECharSet containing the character ch.
    5. Call CharacterSetMatcher(A, false) and return its Matcher result.
    6. E must be an integer. Let n be that integer.
    7. If n=0 or n>NCapturingParens then throw a SyntaxError exception.
    8. Return an internal Matcher closure that takes two arguments, a State x
       and a Continuation c, and performs the following:
        1. Let cap be x's captures internal array.
        2. Let s be cap[n].
        3. If s is undefined, then call c(x) and return its result.
        4. Let e be x's endIndex.
        5. Let len be s's length.
        6. Let f be e+len.
        7. If f>InputLength, return failure.
        8. If there exists an integer i between 0 (inclusive) and len (exclusive)
           such that Canonicalize(s[i]) is not the same character as
           Canonicalize(Input [e+i]), then return failure.
        9. Let y be the State (f, cap).
        10. Call c(y) and return its result.
    */
    private static boolean
    backrefMatcher(REGlobalData gData, int parenIndex,
                   String input, int end)
    {
        int len;
        int i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[669]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (8)) == 0 || true) &&
 ((gData.parens == null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((parenIndex >= gData.parens.length) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[310]++;
            return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[311]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[670]++;
        int parenContent = gData.parensIndex(parenIndex);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[671]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((parenContent == -1) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[312]++;
            return true;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[313]++;}

        len = gData.parensLength(parenIndex);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[672]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[673]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 (((gData.cp + len) > end) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[314]++;
            return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[315]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[674]++;
int CodeCoverConditionCoverageHelper_C129;

        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 (((gData.regexp.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[316]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[675]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[49]++;


int CodeCoverConditionCoverageHelper_C130;
            for (i = 0;(((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[49]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[50]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[51]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[676]++;
                char c1 = input.charAt(parenContent + i);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[677]++;
                char c2 = input.charAt(gData.cp + i);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[678]++;
int CodeCoverConditionCoverageHelper_C131;
                if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (8)) == 0 || true) &&
 ((c1 != c2) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((upcase(c1) != upcase(c2)) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[318]++;
                    return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[319]++;}
            }

        }
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[317]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[679]++;
int CodeCoverConditionCoverageHelper_C132; if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((input.regionMatches(parenContent, input, gData.cp, len)) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[320]++;
            return false;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[321]++;}
}
        gData.cp += len;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[680]++;
        return true;
    }


    /* Add a single character to the RECharSet */
    private static void
    addCharacterToCharSet(RECharSet cs, char c)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[681]++;
        int byteIndex = (c / 8);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[682]++;
int CodeCoverConditionCoverageHelper_C133;
        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((c >= cs.length) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[322]++;
            throw ScriptRuntime.constructError("SyntaxError",
                    "invalid range in character class");

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[323]++;}
        cs.bits[byteIndex] |= 1 << (c & 0x7);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[683]++;
    }


    /* Add a character range, c1 to c2 (inclusive) to the RECharSet */
    private static void
    addCharacterRangeToCharSet(RECharSet cs, char c1, char c2)
    {
        int i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[684]++;

        int byteIndex1 = (c1 / 8);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[685]++;
        int byteIndex2 = (c2 / 8);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[686]++;
int CodeCoverConditionCoverageHelper_C134;

        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C134 |= (8)) == 0 || true) &&
 ((c2 >= cs.length) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((c1 > c2) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[324]++;
            throw ScriptRuntime.constructError("SyntaxError",
                    "invalid range in character class");

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[325]++;}

        c1 &= 0x7;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[687]++;
        c2 &= 0x7;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[688]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[689]++;
int CodeCoverConditionCoverageHelper_C135;

        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((byteIndex1 == byteIndex2) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[326]++;
            cs.bits[byteIndex1] |= ((0xFF) >> (7 - (c2 - c1))) << c1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[690]++;

        }
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[327]++;
            cs.bits[byteIndex1] |= 0xFF << c1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[691]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[692]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[52]++;


int CodeCoverConditionCoverageHelper_C136;
            for (i = byteIndex1 + 1;(((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((i < byteIndex2) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[52]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[53]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[54]++;
}
                cs.bits[i] = (byte)0xFF;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[693]++;
  }
            cs.bits[byteIndex2] |= (0xFF) >> (7 - c2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[694]++;
        }
    }

    /* Compile the source of the class into a RECharSet */
    private static void
    processCharSet(REGlobalData gData, RECharSet charSet)
    {
        synchronized (charSet) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[695]++;
int CodeCoverConditionCoverageHelper_C137;
            if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((charSet.converted) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[328]++;
                processCharSetImpl(gData, charSet);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[696]++;
                charSet.converted = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[697]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[329]++;}
        }
    }


    private static void
    processCharSetImpl(REGlobalData gData, RECharSet charSet)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[698]++;
        int src = charSet.startIndex;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[699]++;
        int end = src + charSet.strlength;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[700]++;

        char rangeStart = 0, thisCh;
        int byteLength;
        char c;
        int n;
        int nDigits;
        int i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[701]++;
        boolean inRange = false;

        byteLength = (charSet.length + 7) / 8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[702]++;
        charSet.bits = new byte[byteLength];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[703]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[704]++;
int CodeCoverConditionCoverageHelper_C138;

        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((src == end) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[330]++;
            return;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[331]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[705]++;
int CodeCoverConditionCoverageHelper_C139;

        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((gData.regexp.source[src] == '^') && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[332]++;
            assert (!charSet.sense);
            ++src;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[706]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[333]++;
            assert (charSet.sense);
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[707]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[55]++;


int CodeCoverConditionCoverageHelper_C140;

        while ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((src != end) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[55]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[56]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[57]++;
}
            nDigits = 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[708]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[709]++;
            switch (gData.regexp.source[src]) {
            case '\\':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[334]++;
                ++src;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[710]++;
                c = gData.regexp.source[src++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[711]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[712]++;
                switch (c) {
                case 'b':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[335]++;
                    thisCh = 0x8;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[713]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[714]++;
                    break;
                case 'f':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[336]++;
                    thisCh = 0xC;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[715]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[716]++;
                    break;
                case 'n':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[337]++;
                    thisCh = 0xA;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[717]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[718]++;
                    break;
                case 'r':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[338]++;
                    thisCh = 0xD;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[719]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[720]++;
                    break;
                case 't':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[339]++;
                    thisCh = 0x9;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[721]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[722]++;
                    break;
                case 'v':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[340]++;
                    thisCh = 0xB;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[723]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[724]++;
                    break;
                case 'c':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[341]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[725]++;
int CodeCoverConditionCoverageHelper_C141;
                    if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((src < end) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((isControlLetter(gData.regexp.source[src])) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[342]++;
                        thisCh = (char)(gData.regexp.source[src++] & 0x1F);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[726]++;
}
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[343]++;
                        --src;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[727]++;
                        thisCh = '\\';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[728]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[729]++;
                    break;
                case 'u':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[344]++;
                    nDigits += 2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[730]++;
                    // fall thru
                case 'x':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[345]++;
                    n = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[731]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[732]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[58]++;


int CodeCoverConditionCoverageHelper_C142;
                    for (i = 0;(((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C142 |= (8)) == 0 || true) &&
 ((i < nDigits) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((src < end) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[58]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[59]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[60]++;
}
                        c = gData.regexp.source[src++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[733]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[734]++;
                        int digit = toASCIIHexDigit(c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[735]++;
int CodeCoverConditionCoverageHelper_C143;
                        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((digit < 0) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[346]++;
                            /* back off to accepting the original '\'
                             * as a literal
                             */
                            src -= (i + 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[736]++;
                            n = '\\';
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[737]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[738]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[347]++;}
                        n = (n << 4) | digit;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[739]++;
                    }
                    thisCh = (char)(n);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[740]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[741]++;
                    break;
                case '0':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[348]++;
                case '1':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[349]++;
                case '2':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[350]++;
                case '3':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[351]++;
                case '4':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[352]++;
                case '5':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[353]++;
                case '6':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[354]++;
                case '7':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[355]++;
                    /*
                     *  This is a non-ECMA extension - decimal escapes (in this
                     *  case, octal!) are supposed to be an error inside class
                     *  ranges, but supported here for backwards compatibility.
                     *
                     */
                    n = (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[742]++;
                    c = gData.regexp.source[src];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[743]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[744]++;
int CodeCoverConditionCoverageHelper_C144;
                    if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((c <= '7') && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[356]++;
                        src++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[745]++;
                        n = 8 * n + (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[746]++;
                        c = gData.regexp.source[src];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[747]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[748]++;
int CodeCoverConditionCoverageHelper_C145;
                        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((c <= '7') && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[358]++;
                            src++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[749]++;
                            i = 8 * n + (c - '0');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[750]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[751]++;
int CodeCoverConditionCoverageHelper_C146;
                            if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((i <= 0377) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[360]++;
                                n = i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[752]++;
}
                            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[361]++;
                                src--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[753]++;
}

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[359]++;}

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[357]++;}
                    thisCh = (char)(n);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[754]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[755]++;
                    break;

                case 'd':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[362]++;
                    addCharacterRangeToCharSet(charSet, '0', '9');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[756]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[757]++;
                    continue;   /* don't need range processing */
                case 'D':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[363]++;
                    addCharacterRangeToCharSet(charSet, (char)0, (char)('0' - 1));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[758]++;
                    addCharacterRangeToCharSet(charSet, (char)('9' + 1),
                                                (char)(charSet.length - 1));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[759]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[760]++;
                    continue;
                case 's':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[364]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[761]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[61]++;


int CodeCoverConditionCoverageHelper_C147;
                    for (i = (charSet.length - 1);(((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false); i--) { 
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[61]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[62]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[63]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[762]++;
int CodeCoverConditionCoverageHelper_C148;
                        if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((isREWhiteSpace(i)) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[365]++;
                            addCharacterToCharSet(charSet, (char)(i));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[763]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[366]++;}
  }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[764]++;
                    continue;
                case 'S':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[367]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[765]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[64]++;


int CodeCoverConditionCoverageHelper_C149;
                    for (i = (charSet.length - 1);(((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false); i--) { 
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[64]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[65]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[66]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[766]++;
int CodeCoverConditionCoverageHelper_C150;
                        if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((isREWhiteSpace(i)) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[368]++;
                            addCharacterToCharSet(charSet, (char)(i));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[767]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[369]++;}
  }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[768]++;
                    continue;
                case 'w':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[370]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[769]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[67]++;


int CodeCoverConditionCoverageHelper_C151;
                    for (i = (charSet.length - 1);(((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false); i--) { 
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[67]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[68]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[69]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[770]++;
int CodeCoverConditionCoverageHelper_C152;
                        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((isWord((char)i)) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[371]++;
                            addCharacterToCharSet(charSet, (char)(i));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[771]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[372]++;}
  }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[772]++;
                    continue;
                case 'W':
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[373]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[773]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[70]++;


int CodeCoverConditionCoverageHelper_C153;
                    for (i = (charSet.length - 1);(((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false); i--) { 
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[70]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[71]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[72]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[774]++;
int CodeCoverConditionCoverageHelper_C154;
                        if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((isWord((char)i)) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[374]++;
                            addCharacterToCharSet(charSet, (char)(i));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[775]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[375]++;}
  }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[776]++;
                    continue;
                default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[376]++;
                    thisCh = c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[777]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[778]++;
                    break;

                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[779]++;
                break;

            default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[377]++;
                thisCh = gData.regexp.source[src++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[780]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[781]++;
                break;

            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[782]++;
int CodeCoverConditionCoverageHelper_C155;
            if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((inRange) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[378]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[783]++;
int CodeCoverConditionCoverageHelper_C156;
                if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 (((gData.regexp.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[380]++;
                    assert(rangeStart <= thisCh);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[784]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[73]++;


int CodeCoverConditionCoverageHelper_C157;
                    for (c = rangeStart;(((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((c <= thisCh) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false);) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[73]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[74]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[75]++;
}
                        addCharacterToCharSet(charSet, c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[785]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[786]++;
                        char uch = upcase(c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[787]++;
                        char dch = downcase(c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[788]++;
int CodeCoverConditionCoverageHelper_C158;
                        if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((c != uch) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[382]++;
                            addCharacterToCharSet(charSet, uch);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[789]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[383]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[790]++;
int CodeCoverConditionCoverageHelper_C159;
                        if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((c != dch) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[384]++;
                            addCharacterToCharSet(charSet, dch);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[791]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[385]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[792]++;
int CodeCoverConditionCoverageHelper_C160;
                        if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((++c == 0) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[386]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[793]++;
                            break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[387]++;} // overflow
                    }

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[381]++;
                    addCharacterRangeToCharSet(charSet, rangeStart, thisCh);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[794]++;
                }
                inRange = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[795]++;

            }
            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[379]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[796]++;
int CodeCoverConditionCoverageHelper_C161;
                if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 (((gData.regexp.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[388]++;
                    addCharacterToCharSet(charSet, upcase(thisCh));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[797]++;
                    addCharacterToCharSet(charSet, downcase(thisCh));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[798]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[389]++;
                    addCharacterToCharSet(charSet, thisCh);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[799]++;
                }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[800]++;
int CodeCoverConditionCoverageHelper_C162;
                if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((src < (end - 1)) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[390]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[801]++;
int CodeCoverConditionCoverageHelper_C163;
                    if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((gData.regexp.source[src] == '-') && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[392]++;
                        ++src;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[802]++;
                        inRange = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[803]++;
                        rangeStart = thisCh;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[804]++;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[393]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[391]++;}
            }
        }
    }


    /*
     *   Initialize the character set if it this is the first call.
     *   Test the bit - if the ^ flag was specified, non-inclusion is a success
     */
    private static boolean
    classMatcher(REGlobalData gData, RECharSet charSet, char ch)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[805]++;
int CodeCoverConditionCoverageHelper_C164;
        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((charSet.converted) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[394]++;
            processCharSet(gData, charSet);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[806]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[395]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[807]++;

        int byteIndex = ch >> 3;
        return (charSet.length == 0 ||
                ch >= charSet.length ||
                (charSet.bits[byteIndex] & (1 << (ch & 0x7))) == 0) ^ charSet.sense;
    }

    private static boolean reopIsSimple(int op) {
        return op >= REOP_SIMPLE_START && op <= REOP_SIMPLE_END;
    }

    /*
    *   Apply the current op against the given input to see if
    *   it's going to match or fail. Return false if we don't
    *   get a match, true if we do and update the state of the
    *   input and pc if the update flag is true.
    */
    private static int simpleMatch(REGlobalData gData, String input, int op,
                                   byte[] program, int pc, int end, boolean updatecp)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[808]++;
        boolean result = false;
        char matchCh;
        int parenIndex;
        int offset, length, index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[809]++;
        int startcp = gData.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[810]++;

        switch (op) {
            case REOP_EMPTY:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[396]++;
                result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[811]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[812]++;
                break;
            case REOP_BOL:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[397]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[813]++;
int CodeCoverConditionCoverageHelper_C165;
                if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((gData.cp != 0) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[398]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[814]++;
int CodeCoverConditionCoverageHelper_C166;
                    if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C166 |= (8)) == 0 || true) &&
 ((gData.multiline) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((isLineTerm(input.charAt(gData.cp - 1))) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[400]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[815]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[401]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[399]++;}
                result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[816]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[817]++;
                break;
            case REOP_EOL:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[402]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[818]++;
int CodeCoverConditionCoverageHelper_C167;
                if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[403]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[819]++;
int CodeCoverConditionCoverageHelper_C168;
                    if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C168 |= (8)) == 0 || true) &&
 ((gData.multiline) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((isLineTerm(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[405]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[820]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[406]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[404]++;}
                result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[821]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[822]++;
                break;
            case REOP_WBDRY:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[407]++;
                result = ((gData.cp == 0 || !isWord(input.charAt(gData.cp - 1)))
                        ^ !((gData.cp < end) && isWord(input.charAt(gData.cp))));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[823]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[824]++;
                break;
            case REOP_WNONBDRY:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[408]++;
                result = ((gData.cp == 0 || !isWord(input.charAt(gData.cp - 1)))
                        ^ ((gData.cp < end) && isWord(input.charAt(gData.cp))));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[825]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[826]++;
                break;
            case REOP_DOT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[409]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[827]++;
int CodeCoverConditionCoverageHelper_C169;
                if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((isLineTerm(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[410]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[828]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[829]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[411]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[830]++;
                break;
            case REOP_DIGIT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[412]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[831]++;
int CodeCoverConditionCoverageHelper_C170;
                if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((isDigit(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[413]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[832]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[833]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[414]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[834]++;
                break;
            case REOP_NONDIGIT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[415]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[835]++;
int CodeCoverConditionCoverageHelper_C171;
                if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((isDigit(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[416]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[836]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[837]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[417]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[838]++;
                break;
            case REOP_ALNUM:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[418]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[839]++;
int CodeCoverConditionCoverageHelper_C172;
                if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((isWord(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[419]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[840]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[841]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[420]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[842]++;
                break;
            case REOP_NONALNUM:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[421]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[843]++;
int CodeCoverConditionCoverageHelper_C173;
                if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((isWord(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[422]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[844]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[845]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[423]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[846]++;
                break;
            case REOP_SPACE:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[424]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[847]++;
int CodeCoverConditionCoverageHelper_C174;
                if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((isREWhiteSpace(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[425]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[848]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[849]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[426]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[850]++;
                break;
            case REOP_NONSPACE:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[427]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[851]++;
int CodeCoverConditionCoverageHelper_C175;
                if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((isREWhiteSpace(input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[428]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[852]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[853]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[429]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[854]++;
                break;
            case REOP_BACKREF:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[430]++;
            {
                parenIndex = getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[855]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[856]++;
                result = backrefMatcher(gData, parenIndex, input, end);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[857]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[858]++;
            break;
            case REOP_FLAT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[431]++;
            {
                offset = getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[859]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[860]++;
                length = getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[861]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[862]++;
                result = flatNMatcher(gData, offset, length, input, end);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[863]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[864]++;
            break;
            case REOP_FLAT1:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[432]++;
            {
                matchCh = (char)(program[pc++] & 0xFF);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[865]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[866]++;
int CodeCoverConditionCoverageHelper_C176;
                if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((input.charAt(gData.cp) == matchCh) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[433]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[867]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[868]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[434]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[869]++;
            break;
            case REOP_FLATi:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[435]++;
            {
                offset = getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[870]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[871]++;
                length = getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[872]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[873]++;
                result = flatNIMatcher(gData, offset, length, input, end);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[874]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[875]++;
            break;
            case REOP_FLAT1i:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[436]++;
            {
                matchCh = (char)(program[pc++] & 0xFF);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[876]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[877]++;
int CodeCoverConditionCoverageHelper_C177;
                if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[437]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[878]++;
                    char c = input.charAt(gData.cp);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[879]++;
int CodeCoverConditionCoverageHelper_C178;
                    if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (8)) == 0 || true) &&
 ((matchCh == c) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((upcase(matchCh) == upcase(c)) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[439]++;
                        result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[880]++;
                        gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[881]++;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[440]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[438]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[882]++;
            break;
            case REOP_UCFLAT1:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[441]++;
            {
                matchCh = (char)getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[883]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[884]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[885]++;
int CodeCoverConditionCoverageHelper_C179;
                if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (8)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((input.charAt(gData.cp) == matchCh) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[442]++;
                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[886]++;
                    gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[887]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[443]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[888]++;
            break;
            case REOP_UCFLAT1i:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[444]++;
            {
                matchCh = (char)getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[889]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[890]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[891]++;
int CodeCoverConditionCoverageHelper_C180;
                if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[445]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[892]++;
                    char c = input.charAt(gData.cp);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[893]++;
int CodeCoverConditionCoverageHelper_C181;
                    if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (8)) == 0 || true) &&
 ((matchCh == c) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((upcase(matchCh) == upcase(c)) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[447]++;
                        result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[894]++;
                        gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[895]++;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[448]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[446]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[896]++;
            break;

            case REOP_CLASS:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[449]++;
            case REOP_NCLASS:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[450]++;
            {
                index = getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[897]++;
                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[898]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[899]++;
int CodeCoverConditionCoverageHelper_C182;
                if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((gData.cp != end) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[451]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[900]++;
int CodeCoverConditionCoverageHelper_C183;
                    if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((classMatcher(gData, gData.regexp.classList[index],
                            input.charAt(gData.cp))) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false))
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[453]++;
                        gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[901]++;
                        result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[902]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[903]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[454]++;}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[452]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[904]++;
            break;

            default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[455]++;
                throw Kit.codeBug();
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[905]++;
int CodeCoverConditionCoverageHelper_C184;
        if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[456]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[906]++;
int CodeCoverConditionCoverageHelper_C185;
            if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((updatecp) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[458]++;
                gData.cp = startcp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[907]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[459]++;}
            return pc;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[457]++;}
        gData.cp = startcp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[908]++;
        return -1;
    }


    private static boolean
    executeREBytecode(REGlobalData gData, String input, int end)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[909]++;
        int pc = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[910]++;
        byte program[] = gData.regexp.program;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[911]++;
        int continuationOp = REOP_END;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[912]++;
        int continuationPc = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[913]++;
        boolean result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[914]++;

        int op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[915]++;
int CodeCoverConditionCoverageHelper_C186;

        /*
         * If the first node is a simple match, step the index into the string
         * until that match is made, or fail if it can't be found at all.
         */
        if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (8)) == 0 || true) &&
 ((gData.regexp.anchorCh < 0) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((reopIsSimple(op)) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[460]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[916]++;
            boolean anchor = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[917]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[76]++;


int CodeCoverConditionCoverageHelper_C187;
            while ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((gData.cp <= end) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[76]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[77]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[78]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[918]++;
                int match = simpleMatch(gData, input, op, program, pc, end, true);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[919]++;
int CodeCoverConditionCoverageHelper_C188;
                if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((match >= 0) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[462]++;
                    anchor = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[920]++;
                    pc = match;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[921]++;    /* accept skip to next opcode */
                    op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[922]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[923]++;
                    break;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[463]++;}
                gData.skipped++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[924]++;
                gData.cp++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[925]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[926]++;
int CodeCoverConditionCoverageHelper_C189;
            if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((anchor) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[464]++;
                return false;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[465]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[461]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[927]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[79]++;



        for (;;) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[79]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[80]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[81]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[928]++;
int CodeCoverConditionCoverageHelper_C191;

            if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((reopIsSimple(op)) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[466]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[929]++;
                int match = simpleMatch(gData, input, op, program, pc, end, true);
                result = match >= 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[930]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[931]++;
int CodeCoverConditionCoverageHelper_C192;
                if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[468]++;
                    pc = match;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[932]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[469]++;}
    /* accept skip to next opcode */
            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[467]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[933]++;
                switchStatement:
                switch (op) {
                    case REOP_ALTPREREQ:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[470]++;
                    case REOP_ALTPREREQi:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[471]++;
                    case REOP_ALTPREREQ2:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[472]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[934]++;
                        char matchCh1 = (char)getIndex(program, pc);
                        pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[935]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[936]++;
                        char matchCh2 = (char)getIndex(program, pc);
                        pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[937]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[938]++;
int CodeCoverConditionCoverageHelper_C193;

                        if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((gData.cp == end) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[473]++;
                            result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[939]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[940]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[474]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[941]++;
                        char c = input.charAt(gData.cp);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[942]++;
int CodeCoverConditionCoverageHelper_C194;
                        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((op == REOP_ALTPREREQ2) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[475]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[943]++;
int CodeCoverConditionCoverageHelper_C195;
                            if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (8)) == 0 || true) &&
 ((c != matchCh1) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((classMatcher(gData, gData.regexp.classList[matchCh2], c)) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[477]++;
                                result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[944]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[945]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[478]++;}

                        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[476]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[946]++;
int CodeCoverConditionCoverageHelper_C196;
                            if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((op == REOP_ALTPREREQi) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[479]++;
                                c = upcase(c);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[947]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[480]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[948]++;
int CodeCoverConditionCoverageHelper_C197;
                            if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (8)) == 0 || true) &&
 ((c != matchCh1) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((c != matchCh2) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[481]++;
                                result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[949]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[950]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[482]++;}
                        }
                    }
                    /* else false thru... */
                    case REOP_ALT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[483]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[951]++;
                        int nextpc = pc + getOffset(program, pc);
                        pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[952]++;
                        op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[953]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[954]++;
                        int startcp = gData.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[955]++;
int CodeCoverConditionCoverageHelper_C198;
                        if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((reopIsSimple(op)) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[484]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[956]++;
                            int match = simpleMatch(gData, input, op, program, pc, end, true);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[957]++;
int CodeCoverConditionCoverageHelper_C199;
                            if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((match < 0) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[486]++;
                                op = program[nextpc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[958]++;
                                pc = nextpc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[959]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[960]++;
                                continue;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[487]++;}
                            result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[961]++;
                            pc = match;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[962]++;
                            op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[963]++;

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[485]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[964]++;
                        byte nextop = program[nextpc++];
                        pushBackTrackState(gData, nextop, nextpc, startcp,
                                continuationOp, continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[965]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[966]++;
                    continue;

                    case REOP_JUMP:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[488]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[967]++;
                        int offset = getOffset(program, pc);
                        pc += offset;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[968]++;
                        op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[969]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[970]++;
                    continue;


                    case REOP_LPAREN:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[489]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[971]++;
                        int parenIndex = getIndex(program, pc);
                        pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[972]++;
                        gData.setParens(parenIndex, gData.cp, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[973]++;
                        op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[974]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[975]++;
                    continue;
                    case REOP_RPAREN:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[490]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[976]++;
                        int parenIndex = getIndex(program, pc);
                        pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[977]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[978]++;
                        int cap_index = gData.parensIndex(parenIndex);
                        gData.setParens(parenIndex, cap_index,
                                gData.cp - cap_index);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[979]++;
                        op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[980]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[981]++;
                    continue;

                    case REOP_ASSERT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[491]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[982]++;
                        int nextpc = pc + getIndex(program, pc); /* start of term after ASSERT */
                        pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[983]++;                         /* start of ASSERT child */
                        op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[984]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[985]++;
int CodeCoverConditionCoverageHelper_C200;
                        if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (8)) == 0 || true) &&
 ((reopIsSimple(op)) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((simpleMatch(gData, input, op, program, pc, end, false) < 0) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[492]++;
                            result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[986]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[987]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[493]++;}
                        pushProgState(gData, 0, 0, gData.cp, gData.backTrackStackTop,
                                continuationOp, continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[988]++;
                        pushBackTrackState(gData, REOP_ASSERTTEST, nextpc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[989]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[990]++;
                    continue;
                    case REOP_ASSERT_NOT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[494]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[991]++;
                        int nextpc = pc + getIndex(program, pc); /* start of term after ASSERT */
                        pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[992]++;                         /* start of ASSERT child */
                        op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[993]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[994]++;
int CodeCoverConditionCoverageHelper_C201;
                        if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((reopIsSimple(op)) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[495]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[995]++;
                            int match = simpleMatch(gData, input, op, program, pc, end, false);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[996]++;
int CodeCoverConditionCoverageHelper_C202;
                            if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (8)) == 0 || true) &&
 ((match >= 0) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((program[match] == REOP_ASSERTNOTTEST) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[497]++;
                                result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[997]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[998]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[498]++;}

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[496]++;}
                        pushProgState(gData, 0, 0, gData.cp, gData.backTrackStackTop,
                                continuationOp, continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[999]++;
                        pushBackTrackState(gData, REOP_ASSERTNOTTEST, nextpc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1000]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1001]++;
                    continue;

                    case REOP_ASSERTTEST:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[499]++;
                    case REOP_ASSERTNOTTEST:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[500]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1002]++;
                        REProgState state = popProgState(gData);
                        gData.cp = state.index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1003]++;
                        gData.backTrackStackTop = state.backTrack;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1004]++;
                        continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1005]++;
                        continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1006]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1007]++;
int CodeCoverConditionCoverageHelper_C203;
                        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((op == REOP_ASSERTNOTTEST) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[501]++;
                            result = !result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1008]++;

                        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[502]++;}
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1009]++;
                    break;

                    case REOP_STAR:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[503]++;
                    case REOP_PLUS:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[504]++;
                    case REOP_OPT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[505]++;
                    case REOP_QUANT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[506]++;
                    case REOP_MINIMALSTAR:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[507]++;
                    case REOP_MINIMALPLUS:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[508]++;
                    case REOP_MINIMALOPT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[509]++;
                    case REOP_MINIMALQUANT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[510]++;
                    {
                        int min, max;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1010]++;
                        boolean greedy = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1011]++;
                        switch (op) {
                            case REOP_STAR:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[511]++;
                                greedy = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1012]++;
                                // fallthrough
                            case REOP_MINIMALSTAR:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[512]++;
                                min = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1013]++;
                                max = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1014]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1015]++;
                                break;
                            case REOP_PLUS:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[513]++;
                                greedy = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1016]++;
                                // fallthrough
                            case REOP_MINIMALPLUS:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[514]++;
                                min = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1017]++;
                                max = -1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1018]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1019]++;
                                break;
                            case REOP_OPT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[515]++;
                                greedy = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1020]++;
                                // fallthrough
                            case REOP_MINIMALOPT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[516]++;
                                min = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1021]++;
                                max = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1022]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1023]++;
                                break;
                            case REOP_QUANT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[517]++;
                                greedy = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1024]++;
                                // fallthrough
                            case REOP_MINIMALQUANT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[518]++;
                                min = getOffset(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1025]++;
                                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1026]++;
                                // See comments in emitREBytecode for " - 1" reason
                                max = getOffset(program, pc) - 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1027]++;
                                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1028]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1029]++;
                                break;
                            default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[519]++;
                                throw Kit.codeBug();
                        }
                        pushProgState(gData, min, max, gData.cp, null,
                                continuationOp, continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1030]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1031]++;
int CodeCoverConditionCoverageHelper_C204;
                        if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((greedy) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[520]++;
                            pushBackTrackState(gData, REOP_REPEAT, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1032]++;
                            continuationOp = REOP_REPEAT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1033]++;
                            continuationPc = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1034]++;
                            /* Step over <parencount>, <parenindex> & <next> */
                            pc += 3 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1035]++;
                            op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1036]++;

                        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[521]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1037]++;
int CodeCoverConditionCoverageHelper_C205;
                            if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((min != 0) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[522]++;
                                continuationOp = REOP_MINIMALREPEAT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1038]++;
                                continuationPc = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1039]++;
                                /* <parencount> <parenindex> & <next> */
                                pc += 3 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1040]++;
                                op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1041]++;

                            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[523]++;
                                pushBackTrackState(gData, REOP_MINIMALREPEAT, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1042]++;
                                popProgState(gData);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1043]++;
                                pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1044]++;  // <parencount> & <parenindex>
                                pc = pc + getOffset(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1045]++;
                                op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1046]++;
                            }
                        }
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1047]++;
                    continue;

                    case REOP_ENDCHILD:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[524]++; /* marks the end of a quantifier child */
                        // If we have not gotten a result here, it is because of an
                        // empty match.  Do the same thing REOP_EMPTY would do.
                        result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1048]++;
                        // Use the current continuation.
                        pc = continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1049]++;
                        op = continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1050]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1051]++;
                        continue;

                    case REOP_REPEAT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[525]++;
                    {
                        int nextpc, nextop;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1052]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[82]++;


int CodeCoverConditionCoverageHelper_C206;
                        do {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[82]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[83]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[84]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1053]++;
                            REProgState state = popProgState(gData);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1054]++;
int CodeCoverConditionCoverageHelper_C207;
                            if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[526]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1055]++;
int CodeCoverConditionCoverageHelper_C208;
                                // Failed, see if we have enough children.
                                if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((state.min == 0) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[528]++;
                                    result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1056]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[529]++;}
                                continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1057]++;
                                continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1058]++;
                                pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1059]++;  /* <parencount> & <parenindex> */
                                pc += getOffset(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1060]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1061]++;
                                break switchStatement;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[527]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1062]++;
int CodeCoverConditionCoverageHelper_C209;
                            if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (8)) == 0 || true) &&
 ((state.min == 0) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((gData.cp == state.index) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[530]++;
                                // matched an empty string, that'll get us nowhere
                                result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1063]++;
                                continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1064]++;
                                continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1065]++;
                                pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1066]++;
                                pc += getOffset(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1067]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1068]++;
                                break switchStatement;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[531]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1069]++;
                            int new_min = state.min, new_max = state.max;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1070]++;
int CodeCoverConditionCoverageHelper_C210;
                            if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((new_min != 0) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[532]++; new_min--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1071]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[533]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1072]++;
int CodeCoverConditionCoverageHelper_C211;
                            if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((new_max != -1) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[534]++; new_max--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1073]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[535]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1074]++;
int CodeCoverConditionCoverageHelper_C212;
                            if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((new_max == 0) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[536]++;
                                result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1075]++;
                                continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1076]++;
                                continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1077]++;
                                pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1078]++;
                                pc += getOffset(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1079]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1080]++;
                                break switchStatement;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[537]++;}
                            nextpc = pc + 3 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1081]++;
                            nextop = program[nextpc];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1082]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1083]++;
                            int startcp = gData.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1084]++;
int CodeCoverConditionCoverageHelper_C213;
                            if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((reopIsSimple(nextop)) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[538]++;
                                nextpc++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1085]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1086]++;
                                int match = simpleMatch(gData, input, nextop, program, nextpc, end, true);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1087]++;
int CodeCoverConditionCoverageHelper_C214;
                                if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((match < 0) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[540]++;
                                    result = (new_min == 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1088]++;
                                    continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1089]++;
                                    continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1090]++;
                                    pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1091]++;  /* <parencount> & <parenindex> */
                                    pc += getOffset(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1092]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1093]++;
                                    break switchStatement;

                                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[541]++;}
                                result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1094]++;
                                nextpc = match;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1095]++;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[539]++;}
                            continuationOp = REOP_REPEAT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1096]++;
                            continuationPc = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1097]++;
                            pushProgState(gData, new_min, new_max, startcp, null,
                                    state.continuationOp, state.continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1098]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1099]++;
int CodeCoverConditionCoverageHelper_C215;
                            if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((new_min == 0) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[542]++;
                                pushBackTrackState(gData, REOP_REPEAT, pc, startcp,
                                        state.continuationOp, state.continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1100]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1101]++;
                                int parenCount = getIndex(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1102]++;
                                int parenIndex = getIndex(program, pc + INDEX_LEN);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1103]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[85]++;


int CodeCoverConditionCoverageHelper_C216;
                                for (int k = 0;(((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((k < parenCount) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[85]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[86]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[87]++;
}
                                    gData.setParens(parenIndex + k, -1, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1104]++;
                                }

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[543]++;}
                        } while ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((program[nextpc] == REOP_ENDCHILD) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false));

                        pc = nextpc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1105]++;
                        op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1106]++;
                    }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1107]++;
                    continue;

                    case REOP_MINIMALREPEAT:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[544]++;
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1108]++;
                        REProgState state = popProgState(gData);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1109]++;
int CodeCoverConditionCoverageHelper_C217;
                        if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[545]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1110]++;
int CodeCoverConditionCoverageHelper_C218;
                            //
                            // Non-greedy failure - try to consume another child.
                            //
                            if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (8)) == 0 || true) &&
 ((state.max == -1) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((state.max > 0) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[547]++;
                                pushProgState(gData, state.min, state.max, gData.cp, null,
                                        state.continuationOp, state.continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1111]++;
                                continuationOp = REOP_MINIMALREPEAT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1112]++;
                                continuationPc = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1113]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1114]++;
                                int parenCount = getIndex(program, pc);
                                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1115]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1116]++;
                                int parenIndex = getIndex(program, pc);
                                pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1117]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1118]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[88]++;


int CodeCoverConditionCoverageHelper_C219;
                                for (int k = 0;(((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((k < parenCount) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[88]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[89]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[90]++;
}
                                    gData.setParens(parenIndex + k, -1, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1119]++;
                                }
                                op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1120]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1121]++;
                                continue;

                            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[548]++;
                                // Don't need to adjust pc since we're going to pop.
                                continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1122]++;
                                continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1123]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1124]++;
                                break;
                            }

                        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[546]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1125]++;
int CodeCoverConditionCoverageHelper_C220;
                            if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (8)) == 0 || true) &&
 ((state.min == 0) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((gData.cp == state.index) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[549]++;
                                // Matched an empty string, that'll get us nowhere.
                                result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1126]++;
                                continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1127]++;
                                continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1128]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1129]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[550]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1130]++;
                            int new_min = state.min, new_max = state.max;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1131]++;
int CodeCoverConditionCoverageHelper_C221;
                            if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((new_min != 0) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[551]++; new_min--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1132]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[552]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1133]++;
int CodeCoverConditionCoverageHelper_C222;
                            if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((new_max != -1) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[553]++; new_max--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1134]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[554]++;}
                            pushProgState(gData, new_min, new_max, gData.cp, null,
                                    state.continuationOp, state.continuationPc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1135]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1136]++;
int CodeCoverConditionCoverageHelper_C223;
                            if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((new_min != 0) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[555]++;
                                continuationOp = REOP_MINIMALREPEAT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1137]++;
                                continuationPc = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1138]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1139]++;
                                int parenCount = getIndex(program, pc);
                                pc += INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1140]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1141]++;
                                int parenIndex = getIndex(program, pc);
                                pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1142]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1143]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[91]++;


int CodeCoverConditionCoverageHelper_C224;
                                for (int k = 0;(((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((k < parenCount) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[91]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[92]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[93]++;
}
                                    gData.setParens(parenIndex + k, -1, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1144]++;
                                }
                                op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1145]++;

                            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[556]++;
                                continuationPc = state.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1146]++;
                                continuationOp = state.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1147]++;
                                pushBackTrackState(gData, REOP_MINIMALREPEAT, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1148]++;
                                popProgState(gData);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1149]++;
                                pc += 2 * INDEX_LEN;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1150]++;
                                pc = pc + getOffset(program, pc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1151]++;
                                op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1152]++;
                            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1153]++;
                            continue;
                        }
                    }

                    case REOP_END:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[557]++;
                        return true;

                    default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[558]++;
                        throw Kit.codeBug("invalid bytecode");

                }
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1154]++;
int CodeCoverConditionCoverageHelper_C225;
            /*
             *  If the match failed and there's a backtrack option, take it.
             *  Otherwise this is a complete and utter failure.
             */
            if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[559]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1155]++;
                REBackTrackData backTrackData = gData.backTrackStackTop;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1156]++;
int CodeCoverConditionCoverageHelper_C226;
                if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((backTrackData != null) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[561]++;
                    gData.backTrackStackTop = backTrackData.previous;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1157]++;
                    gData.parens = backTrackData.parens;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1158]++;
                    gData.cp = backTrackData.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1159]++;
                    gData.stateStackTop = backTrackData.stateStackTop;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1160]++;
                    continuationOp = backTrackData.continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1161]++;
                    continuationPc = backTrackData.continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1162]++;
                    pc = backTrackData.pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1163]++;
                    op = backTrackData.op;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1164]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1165]++;
                    continue;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[562]++;
                    return false;
}

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[560]++;}

            op = program[pc++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1166]++;
        }

    }

    private static boolean
    matchRegExp(REGlobalData gData, RECompiled re,
                String input, int start, int end, boolean multiline)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1167]++;
int CodeCoverConditionCoverageHelper_C227;
        if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((re.parenCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[563]++;
            gData.parens = new long[re.parenCount];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1168]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[564]++;
            gData.parens = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1169]++;
        }

        gData.backTrackStackTop = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1170]++;
        gData.stateStackTop = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1171]++;

        gData.multiline = multiline || (re.flags & JSREG_MULTILINE) != 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1172]++;
        gData.regexp = re;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1173]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1174]++;

        int anchorCh = gData.regexp.anchorCh;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1175]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[94]++;


int CodeCoverConditionCoverageHelper_C228;
        //
        // have to include the position beyond the last character
        //  in order to detect end-of-input/line condition
        //
        for (int i = start;(((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((i <= end) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[94]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[95]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[96]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1176]++;
int CodeCoverConditionCoverageHelper_C229;
            //
            // If the first node is a literal match, step the index into
            // the string until that match is made, or fail if it can't be
            // found at all.
            //
            if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((anchorCh >= 0) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[565]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1177]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[97]++;


                for (;;) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[97]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[98]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[99]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1178]++;
int CodeCoverConditionCoverageHelper_C231;
                    if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((i == end) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[567]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[568]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1179]++;
                    char matchCh = input.charAt(i);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1180]++;
int CodeCoverConditionCoverageHelper_C232;
                    if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (32)) == 0 || true) &&
 ((matchCh == anchorCh) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C232 |= (8)) == 0 || true) &&
 (((gData.regexp.flags & JSREG_FOLD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((upcase(matchCh) == upcase((char)anchorCh)) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 3) && false))
                    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[569]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1181]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[570]++;}
                    ++i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1182]++;
                }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[566]++;}
            gData.cp = i;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1183]++;
            gData.skipped = i - start;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1184]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1185]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[100]++;


int CodeCoverConditionCoverageHelper_C233;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((j < re.parenCount) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[100]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[101]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[102]++;
}
                gData.parens[j] = -1l;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1186]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1187]++;
            boolean result = executeREBytecode(gData, input, end);

            gData.backTrackStackTop = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1188]++;
            gData.stateStackTop = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1189]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1190]++;
int CodeCoverConditionCoverageHelper_C234;
            if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[571]++;
                return true;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[572]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1191]++;
int CodeCoverConditionCoverageHelper_C235;
            if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (8)) == 0 || true) &&
 ((anchorCh == ANCHOR_BOL) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((gData.multiline) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[573]++;
                gData.skipped = end;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1192]++;
                return false;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[574]++;}
            i = start + gData.skipped;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1193]++;
        }
        return false;
    }

    /*
     * indexp is assumed to be an array of length 1
     */
    Object executeRegExp(Context cx, Scriptable scope, RegExpImpl res,
                         String str, int indexp[], int matchType)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1194]++;
        REGlobalData gData = new REGlobalData();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1195]++;

        int start = indexp[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1196]++;
        int end = str.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1197]++;
int CodeCoverConditionCoverageHelper_C236;
        if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((start > end) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[575]++;
            start = end;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1198]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[576]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1199]++;
        //
        // Call the recursive matcher to do the real work.
        //
        boolean matches = matchRegExp(gData, re, str, start, end,
                                      res.multiline);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1200]++;
int CodeCoverConditionCoverageHelper_C237;
        if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((matches) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[577]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1201]++;
int CodeCoverConditionCoverageHelper_C238;
            if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((matchType != PREFIX) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[579]++; return null;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[580]++;}
            return Undefined.instance;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[578]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1202]++;
        int index = gData.cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1203]++;
        int ep = indexp[0] = index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1204]++;
        int matchlen = ep - (start + gData.skipped);
        index -= matchlen;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1205]++;
        Object result;
        Scriptable obj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1206]++;
int CodeCoverConditionCoverageHelper_C239;

        if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((matchType == TEST) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[581]++;
            /*
             * Testing for a match and updating cx.regExpImpl: don't allocate
             * an array object, do return true.
             */
            result = Boolean.TRUE;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1207]++;
            obj = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1208]++;

        }
        else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[582]++;
            /*
             * The array returned on match has element 0 bound to the matched
             * string, elements 1 through re.parenCount bound to the paren
             * matches, an index property telling the length of the left context,
             * and an input property referring to the input string.
             */
            result = cx.newArray(scope, 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1209]++;
            obj = (Scriptable) result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1210]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1211]++;

            String matchstr = str.substring(index, index + matchlen);
            obj.put(0, obj, matchstr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1212]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1213]++;
int CodeCoverConditionCoverageHelper_C240;

        if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((re.parenCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[583]++;
            res.parens = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1214]++;
            res.lastParen = SubString.emptySubString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1215]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[584]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1216]++;
            SubString parsub = null;
            int num;
            res.parens = new SubString[re.parenCount];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1217]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1218]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[103]++;


int CodeCoverConditionCoverageHelper_C241;
            for (num = 0;(((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((num < re.parenCount) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false); num++) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[103]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[104]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.loops[105]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1219]++;
                int cap_index = gData.parensIndex(num);
                String parstr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1220]++;
int CodeCoverConditionCoverageHelper_C242;
                if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((cap_index != -1) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[585]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1221]++;
                    int cap_length = gData.parensLength(num);
                    parsub = new SubString(str, cap_index, cap_length);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1222]++;
                    res.parens[num] = parsub;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1223]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1224]++;
int CodeCoverConditionCoverageHelper_C243;
                    if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((matchType != TEST) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[587]++;
                        obj.put(num+1, obj, parsub.toString());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1225]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[588]++;}

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[586]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1226]++;
int CodeCoverConditionCoverageHelper_C244;
                    if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((matchType != TEST) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[589]++;
                        obj.put(num+1, obj, Undefined.instance);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1227]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[590]++;}
                }
            }
            res.lastParen = parsub;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1228]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1229]++;
int CodeCoverConditionCoverageHelper_C245;

        if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((matchType == TEST) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[591]++;
            /*
             * Define the index and input properties last for better for/in loop
             * order (so they come after the elements).
             */
            obj.put("index", obj, Integer.valueOf(start + gData.skipped));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1230]++;
            obj.put("input", obj, str);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1231]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[592]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1232]++;
int CodeCoverConditionCoverageHelper_C246;

        if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((res.lastMatch == null) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[593]++;
            res.lastMatch = new SubString();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1233]++;
            res.leftContext = new SubString();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1234]++;
            res.rightContext = new SubString();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1235]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[594]++;}
        res.lastMatch.str = str;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1236]++;
        res.lastMatch.index = index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1237]++;
        res.lastMatch.length = matchlen;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1238]++;

        res.leftContext.str = str;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1239]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1240]++;
int CodeCoverConditionCoverageHelper_C247;
        if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[595]++;
            /*
             * JS1.2 emulated Perl4.0.1.8 (patch level 36) for global regexps used
             * in scalar contexts, and unintentionally for the string.match "list"
             * psuedo-context.  On "hi there bye", the following would result:
             *
             * Language     while(/ /g){print("$`");}   s/ /$`/g
             * perl4.036    "hi", "there"               "hihitherehi therebye"
             * perl5        "hi", "hi there"            "hihitherehi therebye"
             * js1.2        "hi", "there"               "hihitheretherebye"
             *
             * Insofar as JS1.2 always defined $` as "left context from the last
             * match" for global regexps, it was more consistent than perl4.
             */
            res.leftContext.index = start;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1241]++;
            res.leftContext.length = gData.skipped;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1242]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[596]++;
            /*
             * For JS1.3 and ECMAv2, emulate Perl5 exactly:
             *
             * js1.3        "hi", "hi there"            "hihitherehi therebye"
             */
            res.leftContext.index = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1243]++;
            res.leftContext.length = start + gData.skipped;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1244]++;
        }

        res.rightContext.str = str;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1245]++;
        res.rightContext.index = ep;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1246]++;
        res.rightContext.length = end - ep;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1247]++;

        return result;
    }

    int getFlags()
    {
        return re.flags;
    }

    private static void reportWarning(Context cx, String messageId, String arg)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1248]++;
int CodeCoverConditionCoverageHelper_C248;
        if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_STRICT_MODE)) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[597]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1249]++;
            String msg = ScriptRuntime.getMessage1(messageId, arg);
            Context.reportWarning(msg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1250]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[598]++;}
    }

    private static void reportError(String messageId, String arg)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1251]++;
        String msg = ScriptRuntime.getMessage1(messageId, arg);
        throw ScriptRuntime.constructError("SyntaxError", msg);
    }

// #string_id_map#

    private static final int
        Id_lastIndex    = 1,
        Id_source       = 2,
        Id_global       = 3,
        Id_ignoreCase   = 4,
        Id_multiline    = 5,

        MAX_INSTANCE_ID = 5;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1252]++;
  }

    @Override
    protected int getMaxInstanceId()
    {
        return MAX_INSTANCE_ID;
    }

    @Override
    protected int findInstanceIdInfo(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:16:24 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1253]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1254]++; String X = null; int c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1255]++;
            int s_length = s.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1256]++;
int CodeCoverConditionCoverageHelper_C249;
            if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((s_length==6) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[599]++;
                c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1257]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1258]++;
int CodeCoverConditionCoverageHelper_C250;
                if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[601]++; X="global";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1259]++;id=Id_global;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1260]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[602]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1261]++;
int CodeCoverConditionCoverageHelper_C251; if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[603]++; X="source";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1262]++;id=Id_source;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1263]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[604]++;}
}

            }
            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[600]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1264]++;
int CodeCoverConditionCoverageHelper_C252; if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((s_length==9) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[605]++;
                c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1265]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1266]++;
int CodeCoverConditionCoverageHelper_C253;
                if ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((c=='l') && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[607]++; X="lastIndex";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1267]++;id=Id_lastIndex;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1268]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[608]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1269]++;
int CodeCoverConditionCoverageHelper_C254; if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((c=='m') && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[609]++; X="multiline";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1270]++;id=Id_multiline;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1271]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[610]++;}
}

            }
            else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[606]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1272]++;
int CodeCoverConditionCoverageHelper_C255; if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((s_length==10) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[611]++; X="ignoreCase";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1273]++;id=Id_ignoreCase;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1274]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[612]++;}
}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1275]++;
int CodeCoverConditionCoverageHelper_C256;
            if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C256 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[613]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1276]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[614]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1277]++;
            break L0;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1278]++;
int CodeCoverConditionCoverageHelper_C257;
// #/generated#
// #/string_id_map#

        if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[615]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[616]++;}

        int attr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1279]++;
        switch (id) {
          case Id_lastIndex:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[617]++;
            attr = PERMANENT | DONTENUM;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1280]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1281]++;
            break;
          case Id_source:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[618]++;
          case Id_global:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[619]++;
          case Id_ignoreCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[620]++;
          case Id_multiline:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[621]++;
            attr = PERMANENT | READONLY | DONTENUM;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1282]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1283]++;
            break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[622]++;
            throw new IllegalStateException();
        }
        return instanceIdInfo(attr, id);
    }

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1284]++;
        switch (id) {
            case Id_lastIndex:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[623]++;  return "lastIndex";
            case Id_source:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[624]++;     return "source";
            case Id_global:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[625]++;     return "global";
            case Id_ignoreCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[626]++; return "ignoreCase";
            case Id_multiline:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[627]++;  return "multiline"; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[628]++;
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1285]++;
        switch (id) {
          case Id_lastIndex:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[629]++;
            return ScriptRuntime.wrapNumber(lastIndex);
          case Id_source:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[630]++;
            return new String(re.source);
          case Id_global:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[631]++;
            return ScriptRuntime.wrapBoolean((re.flags & JSREG_GLOB) != 0);
          case Id_ignoreCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[632]++;
            return ScriptRuntime.wrapBoolean((re.flags & JSREG_FOLD) != 0);
          case Id_multiline:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[633]++;
            return ScriptRuntime.wrapBoolean((re.flags & JSREG_MULTILINE) != 0); default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[634]++;
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1286]++;
        switch (id) {
          case Id_lastIndex:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[635]++;
            lastIndex = ScriptRuntime.toNumber(value);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1287]++;
            return;
          case Id_source:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[636]++;
          case Id_global:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[637]++;
          case Id_ignoreCase:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[638]++;
          case Id_multiline:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[639]++;
            return; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[640]++;
        }
        super.setInstanceIdValue(id, value);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1288]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1289]++;
        switch (id) {
          case Id_compile:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[641]++;  arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1290]++; s="compile";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1291]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1292]++;  break;
          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[642]++; arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1293]++; s="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1294]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1295]++; break;
          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[643]++; arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1296]++; s="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1297]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1298]++; break;
          case Id_exec:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[644]++;     arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1299]++; s="exec";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1300]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1301]++;     break;
          case Id_test:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[645]++;     arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1302]++; s="test";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1303]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1304]++;     break;
          case Id_prefix:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[646]++;   arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1305]++; s="prefix";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1306]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1307]++;   break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[647]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(REGEXP_TAG, id, s, arity);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1308]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1309]++;
int CodeCoverConditionCoverageHelper_C258;
        if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((f.hasTag(REGEXP_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[648]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[649]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1310]++;
        int id = f.methodId();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1311]++;
        switch (id) {
          case Id_compile:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[650]++;
            return realThis(thisObj, f).compile(cx, scope, args);

          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[651]++;
          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[652]++;
            return realThis(thisObj, f).toString();

          case Id_exec:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[653]++;
            return realThis(thisObj, f).execSub(cx, scope, args, MATCH);

          case Id_test:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[654]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1312]++;
            Object x = realThis(thisObj, f).execSub(cx, scope, args, TEST);
            return Boolean.TRUE.equals(x) ? Boolean.TRUE : Boolean.FALSE;
          }

          case Id_prefix:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[655]++;
            return realThis(thisObj, f).execSub(cx, scope, args, PREFIX); default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[656]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static NativeRegExp realThis(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1313]++;
int CodeCoverConditionCoverageHelper_C259;
        if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeRegExp) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[657]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[658]++;}
        return (NativeRegExp)thisObj;
    }

// #string_id_map#
    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:16:24 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1314]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1315]++; String X = null; int c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1316]++;
            L: switch (s.length()) {
            case 4:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[659]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1317]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1318]++;
int CodeCoverConditionCoverageHelper_C260;
                if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[660]++; X="exec";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1319]++;id=Id_exec;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1320]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[661]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1321]++;
int CodeCoverConditionCoverageHelper_C261; if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[662]++; X="test";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1322]++;id=Id_test;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1323]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[663]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1324]++;
                break L;
            case 6:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[664]++; X="prefix";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1325]++;id=Id_prefix;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1326]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1327]++; break L;
            case 7:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[665]++; X="compile";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1328]++;id=Id_compile;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1329]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1330]++; break L;
            case 8:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[666]++; c=s.charAt(3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1331]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1332]++;
int CodeCoverConditionCoverageHelper_C262;
                if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[667]++; X="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1333]++;id=Id_toSource;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1334]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[668]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1335]++;
int CodeCoverConditionCoverageHelper_C263; if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[669]++; X="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1336]++;id=Id_toString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1337]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[670]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1338]++;
                break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[671]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1339]++;
int CodeCoverConditionCoverageHelper_C264;
            if ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C264 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C264 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[672]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1340]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[673]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1341]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_compile       = 1,
        Id_toString      = 2,
        Id_toSource      = 3,
        Id_exec          = 4,
        Id_test          = 5,
        Id_prefix        = 6,

        MAX_PROTOTYPE_ID = 6;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1342]++;
  }

// #/string_id_map#

    private RECompiled re;
    double lastIndex;          /* index after last match, for //g iterator */

}       // class NativeRegExp

class RECompiled implements Serializable
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }

    static final long serialVersionUID = -6144956577595844213L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1343]++;
  }

    final char[] source;    /* locked source string, sans // */
    int parenCount;         /* number of parenthesized submatches */
    int flags;              /* flags  */
    byte[] program;         /* regular expression bytecode */
    int classCount;         /* count [...] bitmaps */
    RECharSet[] classList;  /* list of [...] bitmaps */
    int anchorCh = -1;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1344]++;
  }      /* if >= 0, then re starts with this literal char */

    RECompiled(String str) {
        this.source = str.toCharArray();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1345]++;
    }
}

class RENode {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }


    RENode(byte op)
    {
        this.op = op;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1346]++;
    }

    byte            op;         /* r.e. op bytecode */
    RENode          next;       /* next in concatenation order */
    RENode          kid;        /* first operand */

    RENode          kid2;       /* second operand */
    int             parenIndex; /* or a parenthesis index */

                                /* or a range */
    int             min;
    int             max;
    int             parenCount;
    boolean         greedy;

                                /* or a character class */
    int             startIndex;
    int             kidlen;     /* length of string at kid, in chars */
    int             bmsize;     /* bitmap size, based on max char code */
    int             index;      /* index into class list */
    boolean         sense;

                                /* or a literal sequence */
    char            chr;        /* of one character */
    int             length;     /* or many (via the index) */
    int             flatIndex;  /* which is -1 if not sourced */

}

class CompilerState {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }


    CompilerState(Context cx, char[] source, int length, int flags)
    {
        this.cx = cx;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1347]++;
        this.cpbegin = source;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1348]++;
        this.cp = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1349]++;
        this.cpend = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1350]++;
        this.flags = flags;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1351]++;
        this.parenCount = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1352]++;
        this.classCount = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1353]++;
        this.progLength = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1354]++;
    }

    Context     cx;
    char        cpbegin[];
    int         cpend;
    int         cp;
    int         flags;
    int         parenCount;
    int         parenNesting;
    int         classCount;   /* number of [] encountered */
    int         progLength;   /* estimated bytecode length */
    RENode      result;
}

class REProgState
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }

    REProgState(REProgState previous, int min, int max, int index,
                REBackTrackData backTrack,
                int continuationOp, int continuationPc)
    {
        this.previous = previous;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1355]++;
        this.min = min;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1356]++;
        this.max = max;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1357]++;
        this.index = index;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1358]++;
        this.continuationOp = continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1359]++;
        this.continuationPc = continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1360]++;
        this.backTrack = backTrack;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1361]++;
    }

    final REProgState previous; // previous state in stack

    final int min;                      /* current quantifier min */
    final int max;                      /* current quantifier max */
    final int index;                    /* progress in text */
    final int continuationOp;
    final int continuationPc;
    final REBackTrackData backTrack; // used by ASSERT_  to recover state
}

class REBackTrackData {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }


    REBackTrackData(REGlobalData gData, int op, int pc, int cp,
                    int continuationOp, int continuationPc)
    {
        previous = gData.backTrackStackTop;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1362]++;
        this.op = op;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1363]++;
        this.pc = pc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1364]++;
        this.cp = cp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1365]++;
        this.continuationOp = continuationOp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1366]++;
        this.continuationPc = continuationPc;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1367]++;
        parens = gData.parens;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1368]++;
        stateStackTop = gData.stateStackTop;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1369]++;
    }

    final REBackTrackData previous;

    final int op;                             /* operator */
    final int pc;                             /* bytecode pointer */
    final int cp;                             /* char buffer index */
    final int continuationOp;                 /* continuation op */
    final int continuationPc;                 /* continuation pc */
    final long[] parens;                      /* parenthesis captures */
    final REProgState stateStackTop;          /* state of op that backtracked */
}

class REGlobalData {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }

    boolean multiline;
    RECompiled regexp;              /* the RE in execution */
    int skipped;                    /* chars skipped anchoring this r.e. */

    int cp;                         /* char buffer index */
    long[] parens;                  /* parens captures */

    REProgState stateStackTop;       /* stack of state of current ancestors */

    REBackTrackData backTrackStackTop;  /* last matched-so-far position */


    /**
     * Get start of parenthesis capture contents, -1 for empty.
     */
    int parensIndex(int i)
    {
        return (int)(parens[i]);
    }

    /**
     * Get length of parenthesis capture contents.
     */
    int parensLength(int i)
    {
        return (int)(parens[i] >>> 32);
    }

    void setParens(int i, int index, int length)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1370]++;
int CodeCoverConditionCoverageHelper_C265;
        // clone parens array if it is shared with backtrack state
        if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (8)) == 0 || true) &&
 ((backTrackStackTop != null) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((backTrackStackTop.parens == parens) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[674]++;
            parens = parens.clone();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1371]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.branches[675]++;}
        parens[i] = (index & 0xffffffffL) | ((long)length << 32);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1372]++;
    }

}

/*
 * This struct holds a bitmap representation of a class from a regexp.
 * There's a list of these referenced by the classList field in the NativeRegExp
 * struct below. The initial state has startIndex set to the offset in the
 * original regexp source of the beginning of the class contents. The first
 * use of the class converts the source representation into a bitmap.
 *
 */
final class RECharSet implements Serializable
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.ping();
  }

    static final long serialVersionUID = 7931787979395898394L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1373]++;
  }

    RECharSet(int length, int startIndex, int strlength, boolean sense)
    {
        this.length = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1374]++;
        this.startIndex = startIndex;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1375]++;
        this.strlength = strlength;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1376]++;
        this.sense = sense;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h.statements[1377]++;
    }

    final int length;
    final int startIndex;
    final int strlength;
    final boolean sense;

    volatile transient boolean converted;
    volatile transient byte[] bits;
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h ());
  }
    public static long[] statements = new long[1378];
    public static long[] branches = new long[676];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[266];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.regexp.RHINO-SRC-NativeRegExp.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,2,1,2,1,1,2,1,2,1,2,3,3,0,3,1,1,1,1,1,1,1,2,2,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,2,1,2,2,1,0,1,1,1,2,1,0,1,1,1,1,1,0,1,1,1,1,1,1,2,1,1,1,1,1,3,2,1,1,1,1,2,2,2,1,1,1,1,1,1,1,2,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,2,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,2,2,2,2,2,2,2,2,1,2,2,1,2,1,1,1,1,2,1,1,1,0,1,1,1,1,2,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,0,1,3,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,3,2};
    for (int i = 1; i <= 265; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[106];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbsz2zm2i72vhx0j8h () {
    super("org.mozilla.javascript.regexp.RHINO-SRC-NativeRegExp.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1377; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 675; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 265; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 105; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.regexp.RHINO-SRC-NativeRegExp.java");
      for (int i = 1; i <= 1377; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 675; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 265; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 35; i++) {
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

