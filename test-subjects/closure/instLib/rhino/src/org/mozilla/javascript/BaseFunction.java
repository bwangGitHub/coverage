/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * The base class for Function objects
 * See ECMA 15.3.
 */
public class BaseFunction extends IdScriptableObject implements Function
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.ping();
  }


    static final long serialVersionUID = 5311394446546053859L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[1]++;
  }

    private static final Object FUNCTION_TAG = "Function";
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[2]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[3]++;
        BaseFunction obj = new BaseFunction();
        // Function.prototype attributes: see ECMA 15.3.3.1
        obj.prototypePropertyAttributes = DONTENUM | READONLY | PERMANENT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[4]++;
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[5]++;
    }

    public BaseFunction()
    {
    }

    public BaseFunction(Scriptable scope, Scriptable prototype)
    {
        super(scope, prototype);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[6]++;
    }

    @Override
    public String getClassName() {
        return "Function";
    }

    /**
     * Gets the value returned by calling the typeof operator on this object.
     * @see org.mozilla.javascript.ScriptableObject#getTypeOf()
     * @return "function" or "undefined" if {@link #avoidObjectDetection()} returns <code>true</code>
     */
    @Override
    public String getTypeOf()
    {
    	return avoidObjectDetection() ? "undefined" : "function";
    }

    /**
     * Implements the instanceof operator for JavaScript Function objects.
     * <p>
     * <code>
     * foo = new Foo();<br>
     * foo instanceof Foo;  // true<br>
     * </code>
     *
     * @param instance The value that appeared on the LHS of the instanceof
     *              operator
     * @return true if the "prototype" property of "this" appears in
     *              value's prototype chain
     *
     */
    @Override
    public boolean hasInstance(Scriptable instance)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[7]++;
        Object protoProp = ScriptableObject.getProperty(this, "prototype");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((protoProp instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[1]++;
            return ScriptRuntime.jsDelegatesTo(instance, (Scriptable)protoProp);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[2]++;}
        throw ScriptRuntime.typeError1("msg.instanceof.bad.prototype",
                                       getFunctionName());
    }

// #string_id_map#

    private static final int
        Id_length       = 1,
        Id_arity        = 2,
        Id_name         = 3,
        Id_prototype    = 4,
        Id_arguments    = 5,

        MAX_INSTANCE_ID = 5;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[9]++;
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
// #generated# Last update: 2007-05-09 08:15:15 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[10]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[11]++; String X = null; int c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[12]++;
            L: switch (s.length()) {
            case 4:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[3]++; X="name";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[13]++;id=Id_name;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[14]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[15]++; break L;
            case 5:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[4]++; X="arity";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[16]++;id=Id_arity;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[17]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[18]++; break L;
            case 6:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[5]++; X="length";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[19]++;id=Id_length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[20]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[21]++; break L;
            case 9:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[6]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[22]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
                if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((c=='a') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[7]++; X="arguments";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[24]++;id=Id_arguments;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[25]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[8]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[26]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[9]++; X="prototype";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[27]++;id=Id_prototype;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[28]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[10]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[29]++;
                break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[11]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[12]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[31]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[13]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[32]++;
            break L0;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
// #/generated#
// #/string_id_map#

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[14]++; return super.findInstanceIdInfo(s);
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[15]++;}

        int attr;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[34]++;
        switch (id) {
          case Id_length:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[16]++;
          case Id_arity:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[17]++;
          case Id_name:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[18]++;
            attr = DONTENUM | READONLY | PERMANENT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[35]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[36]++;
            break;
          case Id_prototype:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[19]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[37]++;
int CodeCoverConditionCoverageHelper_C6;
            // some functions such as built-ins don't have a prototype property
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((hasPrototypeProperty()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[20]++;
                return 0;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[21]++;}
            attr = prototypePropertyAttributes;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[38]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[39]++;
            break;
          case Id_arguments:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[22]++;
            attr = DONTENUM | PERMANENT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[40]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[41]++;
            break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[23]++; throw new IllegalStateException();
        }
        return instanceIdInfo(attr, id);
    }

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[42]++;
        switch (id) {
            case Id_length:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[24]++;       return "length";
            case Id_arity:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[25]++;        return "arity";
            case Id_name:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[26]++;         return "name";
            case Id_prototype:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[27]++;    return "prototype";
            case Id_arguments:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[28]++;    return "arguments"; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[29]++;
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[43]++;
        switch (id) {
          case Id_length:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[30]++;    return ScriptRuntime.wrapInt(getLength());
          case Id_arity:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[31]++;     return ScriptRuntime.wrapInt(getArity());
          case Id_name:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[32]++;      return getFunctionName();
          case Id_prototype:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[33]++; return getPrototypeProperty();
          case Id_arguments:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[34]++; return getArguments(); default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[35]++;
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[44]++;
        switch (id) {
            case Id_prototype:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[36]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[45]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 (((prototypePropertyAttributes & READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[37]++;
                    prototypeProperty = (value != null)
                                        ? value : UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[46]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[38]++;}
                return;
            case Id_arguments:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[39]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[47]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[40]++;
                    // This should not be called since "arguments" is PERMANENT
                    Kit.codeBug();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[48]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[41]++;}
                defaultPut("arguments", value);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[49]++;
                return;
            case Id_name:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[42]++;
            case Id_arity:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[43]++;
            case Id_length:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[44]++;
                return; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[45]++;
        }
        super.setInstanceIdValue(id, value);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[50]++;
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor)
    {
        // Fix up bootstrapping problem: getPrototype of the IdFunctionObject
        // can not return Function.prototype because Function object is not
        // yet defined.
        ctor.setPrototype(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[51]++;
        super.fillConstructorProperties(ctor);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[52]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[53]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[46]++; arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[54]++; s="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[55]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[56]++; break;
          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[47]++;    arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[57]++; s="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[58]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[59]++;    break;
          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[48]++;    arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[60]++; s="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[61]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[62]++;    break;
          case Id_apply:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[49]++;       arity=2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[63]++; s="apply";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[64]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[65]++;       break;
          case Id_call:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[50]++;        arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[66]++; s="call";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[67]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[68]++;        break;
          case Id_bind:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[51]++;        arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[69]++; s="bind";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[70]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[71]++;        break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[52]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(FUNCTION_TAG, id, s, arity);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[72]++;
    }

    static boolean isApply(IdFunctionObject f) {
        return f.hasTag(FUNCTION_TAG) && f.methodId() == Id_apply;
    }

    static boolean isApplyOrCall(IdFunctionObject f) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[73]++;
int CodeCoverConditionCoverageHelper_C9;
        if((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((f.hasTag(FUNCTION_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[53]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[74]++;
            switch(f.methodId()) {
                case Id_apply:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[55]++;
                case Id_call:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[56]++;
                    return true; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[57]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[54]++;}
        return false;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[75]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((f.hasTag(FUNCTION_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[58]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[59]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[76]++;
        int id = f.methodId();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[77]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[60]++;
            return jsConstructor(cx, scope, args);

          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[61]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[78]++;
            BaseFunction realf = realFunction(thisObj, f);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[79]++;
            int indent = ScriptRuntime.toInt32(args, 0);
            return realf.decompile(indent, 0);
          }

          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[62]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[80]++;
            BaseFunction realf = realFunction(thisObj, f);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[81]++;
            int indent = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[82]++;
            int flags = Decompiler.TO_SOURCE_FLAG;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[83]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((args.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[63]++;
                indent = ScriptRuntime.toInt32(args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[84]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[85]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((indent >= 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[65]++;
                    flags = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[86]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[66]++;
                    indent = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[87]++;
                }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[64]++;}
            return realf.decompile(indent, flags);
          }

          case Id_apply:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[67]++;
          case Id_call:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[68]++;
            return ScriptRuntime.applyOrCall(id == Id_apply,
                                             cx, scope, thisObj, args);

          case Id_bind:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[69]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[88]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((thisObj instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false) ) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[70]++;
              throw ScriptRuntime.notFunctionError(thisObj);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[71]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[89]++;
            Callable targetFunction = (Callable) thisObj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[90]++;
            int argc = args.length;
            final Scriptable boundThis;
            final Object[] boundArgs;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[91]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((argc > 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[72]++;
              boundThis = ScriptRuntime.toObjectOrNull(cx, args[0], scope);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[92]++;
              boundArgs = new Object[argc-1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[93]++;
              System.arraycopy(args, 1, boundArgs, 0, argc-1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[94]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[73]++;
              boundThis = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[95]++;
              boundArgs = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[96]++;
            }
            return new BoundFunction(cx, scope, targetFunction, boundThis, boundArgs); default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[74]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private BaseFunction realFunction(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[97]++;
        Object x = thisObj.getDefaultValue(ScriptRuntime.FunctionClass);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[98]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((x instanceof BaseFunction) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[75]++;
            return (BaseFunction)x;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[76]++;}
        throw ScriptRuntime.typeError1("msg.incompat.call",
                                       f.getFunctionName());
    }

    /**
     * Make value as DontEnum, DontDelete, ReadOnly
     * prototype property of this Function object
     */
    public void setImmunePrototypeProperty(Object value)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[99]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (((prototypePropertyAttributes & READONLY) != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[77]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[78]++;}
        prototypeProperty = (value != null) ? value : UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[100]++;
        prototypePropertyAttributes = DONTENUM | PERMANENT | READONLY;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[101]++;
    }

    protected Scriptable getClassPrototype()
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[102]++;
        Object protoVal = getPrototypeProperty();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[103]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((protoVal instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[79]++;
            return (Scriptable) protoVal;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[80]++;}
        return ScriptableObject.getObjectPrototype(this);
    }

    /**
     * Should be overridden.
     */
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        return Undefined.instance;
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[104]++;
        Scriptable result = createObject(cx, scope);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[105]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[81]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[106]++;
            Object val = call(cx, scope, result, args);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[107]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[83]++;
                result = (Scriptable)val;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[108]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[84]++;}

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[82]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[109]++;
            Object val = call(cx, scope, null, args);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[110]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[85]++;
                // It is program error not to return Scriptable from
                // the call method if createObject returns null.
                throw new IllegalStateException(
                    "Bad implementaion of call as constructor, name="
                    +getFunctionName()+" in "+getClass().getName());

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[86]++;}
            result = (Scriptable)val;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[111]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[112]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((result.getPrototype() == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[87]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[113]++;
                Scriptable proto = getClassPrototype();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[114]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((result != proto) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[89]++;
                    result.setPrototype(proto);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[115]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[90]++;}

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[88]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[116]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((result.getParentScope() == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[91]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[117]++;
                Scriptable parent = getParentScope();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[118]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((result != parent) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[93]++;
                    result.setParentScope(parent);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[119]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[94]++;}

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[92]++;}
        }
        return result;
    }

    /**
     * Creates new script object.
     * The default implementation of {@link #construct} uses the method to
     * to get the value for <tt>thisObj</tt> argument when invoking
     * {@link #call}.
     * The methos is allowed to return <tt>null</tt> to indicate that
     * {@link #call} will create a new object itself. In this case
     * {@link #construct} will set scope and prototype on the result
     * {@link #call} unless they are already set.
     */
    public Scriptable createObject(Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[120]++;
        Scriptable newInstance = new NativeObject();
        newInstance.setPrototype(getClassPrototype());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[121]++;
        newInstance.setParentScope(getParentScope());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[122]++;
        return newInstance;
    }

    /**
     * Decompile the source information associated with this js
     * function/script back into a string.
     *
     * @param indent How much to indent the decompiled result.
     *
     * @param flags Flags specifying format of decompilation output.
     */
    String decompile(int indent, int flags)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[123]++;
        StringBuffer sb = new StringBuffer();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[124]++;
        boolean justbody = (0 != (flags & Decompiler.ONLY_BODY_FLAG));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[125]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((justbody) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[95]++;
            sb.append("function ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[126]++;
            sb.append(getFunctionName());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[127]++;
            sb.append("() {\n\t");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[128]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[96]++;}
        sb.append("[native code, arity=");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[129]++;
        sb.append(getArity());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[130]++;
        sb.append("]\n");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[131]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[132]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((justbody) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[97]++;
            sb.append("}\n");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[133]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[98]++;}
        return sb.toString();
    }

    public int getArity() { return 0; }

    public int getLength() { return 0; }

    public String getFunctionName() {
        return "";
    }

    protected boolean hasPrototypeProperty() {
        return prototypeProperty != null || this instanceof NativeFunction;
    }

    protected Object getPrototypeProperty() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[134]++;
        Object result = prototypeProperty;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[135]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[99]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[136]++;
int CodeCoverConditionCoverageHelper_C28;
            // only create default prototype on native JavaScript functions,
            // not on built-in functions, java methods, host objects etc.
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this instanceof NativeFunction) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[101]++;
                result = setupDefaultPrototype();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[137]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[102]++;
                result = Undefined.instance;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[138]++;
            }

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[100]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[139]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((result == UniqueTag.NULL_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[103]++;
            result = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[140]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[104]++;}
}
        return result;
    }

    private synchronized Object setupDefaultPrototype() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[141]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((prototypeProperty != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[105]++;
            return prototypeProperty;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[106]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[142]++;
        NativeObject obj = new NativeObject();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[143]++;
        final int attr = ScriptableObject.DONTENUM;
        obj.defineProperty("constructor", this, attr);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[144]++;
        // put the prototype property into the object now, then in the
        // wacky case of a user defining a function Object(), we don't
        // get an infinite loop trying to find the prototype.
        prototypeProperty = obj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[145]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[146]++;
        Scriptable proto = getObjectPrototype(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[147]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((proto != obj) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[107]++;
            // not the one we just made, it must remain grounded
            obj.setPrototype(proto);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[148]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[108]++;}
        return obj;
    }

    private Object getArguments()
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[149]++;
      // <Function name>.arguments is deprecated, so we use a slow
      // way of getting it that doesn't add to the invocation cost.
      // TODO: add warning, error based on version
      Object value = defaultGet("arguments");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[150]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[109]++;
          // Should after changing <Function name>.arguments its
          // activation still be available during Function call?
          // This code assumes it should not:
          // defaultGet("arguments") != NOT_FOUND
          // means assigned arguments
          return value;

      } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[110]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[151]++;
      Context cx = Context.getContext();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[152]++;
      NativeCall activation = ScriptRuntime.findFunctionActivation(cx, this);
      return (activation == null)
             ? null
             : activation.get("arguments", activation);
    }

    private static Object jsConstructor(Context cx, Scriptable scope,
                                        Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[153]++;
        int arglen = args.length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[154]++;
        StringBuffer sourceBuf = new StringBuffer();

        sourceBuf.append("function ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[155]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[156]++;
int CodeCoverConditionCoverageHelper_C33;
        /* version != 1.2 Function constructor behavior -
         * print 'anonymous' as the function name if the
         * version (under which the function was compiled) is
         * less than 1.2... or if it's greater than 1.2, because
         * we need to be closer to ECMA.
         */
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() != Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[111]++;
            sourceBuf.append("anonymous");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[157]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[112]++;}
        sourceBuf.append('(');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[158]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[159]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.loops[1]++;


int CodeCoverConditionCoverageHelper_C34;

        // Append arguments as coma separated strings
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i < arglen - 1) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.loops[3]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[160]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[113]++;
                sourceBuf.append(',');
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[161]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[114]++;}
            sourceBuf.append(ScriptRuntime.toString(args[i]));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[162]++;
        }
        sourceBuf.append(") {");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[163]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[164]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((arglen != 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[115]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[165]++;
            // append function body
            String funBody = ScriptRuntime.toString(args[arglen - 1]);
            sourceBuf.append(funBody);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[166]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[116]++;}
        sourceBuf.append("\n}");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[167]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[168]++;
        String source = sourceBuf.toString();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[169]++;

        int[] linep = new int[1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[170]++;
        String filename = Context.getSourcePositionFromStack(linep);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[171]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[117]++;
            filename = "<eval'ed string>";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[172]++;
            linep[0] = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[173]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[118]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[174]++;

        String sourceURI = ScriptRuntime.
            makeUrlForGeneratedScript(false, filename, linep[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[175]++;

        Scriptable global = ScriptableObject.getTopLevelScope(scope);

        ErrorReporter reporter;
        reporter = DefaultErrorReporter.forEval(cx.getErrorReporter());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[176]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[177]++;

        Evaluator evaluator = Context.createInterpreter();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[178]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((evaluator == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[119]++;
            throw new JavaScriptException("Interpreter not present",
                    filename, linep[0]);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[120]++;}

        // Compile with explicit interpreter instance to force interpreter
        // mode.
        return cx.compileFunction(global, source, evaluator, reporter,
                                  sourceURI, 1, null);
    }

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #string_id_map#
// #generated# Last update: 2009-07-24 16:00:52 EST
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[179]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[180]++; String X = null; int c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[181]++;
            L: switch (s.length()) {
            case 4:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[121]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[182]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[183]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((c=='b') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[122]++; X="bind";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[184]++;id=Id_bind;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[185]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[123]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[186]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[124]++; X="call";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[187]++;id=Id_call;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[188]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[125]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[189]++;
                break L;
            case 5:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[126]++; X="apply";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[190]++;id=Id_apply;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[191]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[192]++; break L;
            case 8:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[127]++; c=s.charAt(3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[193]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[194]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[128]++; X="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[195]++;id=Id_toSource;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[196]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[129]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[197]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[130]++; X="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[198]++;id=Id_toString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[199]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[131]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[200]++;
                break L;
            case 11:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[132]++; X="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[201]++;id=Id_constructor;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[202]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[203]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[133]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[204]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[134]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[205]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.branches[135]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[206]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor    = 1,
        Id_toString       = 2,
        Id_toSource       = 3,
        Id_apply          = 4,
        Id_call           = 5,
        Id_bind           = 6,

        MAX_PROTOTYPE_ID  = Id_bind;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[207]++;
  }

// #/string_id_map#

    private Object prototypeProperty;
    // For function object instances, attributes are
    //  {configurable:false, enumerable:false};
    // see ECMA 15.3.5.2
    private int prototypePropertyAttributes = PERMANENT|DONTENUM;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh.statements[208]++;
  }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh ());
  }
    public static long[] statements = new long[209];
    public static long[] branches = new long[136];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[44];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-BaseFunction.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 43; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmggy4ogytpeeoi9ei4vksqdjhwh () {
    super("org.mozilla.javascript.RHINO-SRC-BaseFunction.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 208; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 135; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-BaseFunction.java");
      for (int i = 1; i <= 208; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 135; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

