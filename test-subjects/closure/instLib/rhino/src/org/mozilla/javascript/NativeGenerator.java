/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements generator objects. See
 * http://developer.mozilla.org/en/docs/New_in_JavaScript_1.7#Generators
 *
 */
public final class NativeGenerator extends IdScriptableObject {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.ping();
  }

    private static final long serialVersionUID = 1645892441041347273L;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[1]++;
  }

    private static final Object GENERATOR_TAG = "Generator";
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[2]++;
  }

    static NativeGenerator init(ScriptableObject scope, boolean sealed) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[3]++;
        // Generator
        // Can't use "NativeGenerator().exportAsJSClass" since we don't want
        // to define "Generator" as a constructor in the top-level scope.

        NativeGenerator prototype = new NativeGenerator();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[1]++;
            prototype.setParentScope(scope);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[5]++;
            prototype.setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[6]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[2]++;}
        prototype.activatePrototypeMap(MAX_PROTOTYPE_ID);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[7]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[3]++;
            prototype.sealObject();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[9]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[4]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;

        // Need to access Generator prototype when constructing
        // Generator instances, but don't have a generator constructor
        // to use to find the prototype. Use the "associateValue"
        // approach instead.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[5]++;
            scope.associateValue(GENERATOR_TAG, prototype);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[11]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[6]++;}

        return prototype;
    }

    /**
     * Only for constructing the prototype object.
     */
    private NativeGenerator() { }

    public NativeGenerator(Scriptable scope, NativeFunction function,
                           Object savedState)
    {
        this.function = function;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[12]++;
        this.savedState = savedState;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[13]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[14]++;
        // Set parent and prototype properties. Since we don't have a
        // "Generator" constructor in the top scope, we stash the
        // prototype in the top scope's associated value.
        Scriptable top = ScriptableObject.getTopLevelScope(scope);
        this.setParentScope(top);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[15]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[16]++;
        NativeGenerator prototype = (NativeGenerator)
            ScriptableObject.getTopScopeValue(top, GENERATOR_TAG);
        this.setPrototype(prototype);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[17]++;
    }

    public static final int GENERATOR_SEND  = 0,
                            GENERATOR_THROW = 1,
                            GENERATOR_CLOSE = 2;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[18]++;
  }

    @Override
    public String getClassName() {
        return "Generator";
    }

    private static class CloseGeneratorAction implements ContextAction {
        private NativeGenerator generator;

        CloseGeneratorAction(NativeGenerator generator) {
            this.generator = generator;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[19]++;
        }

        public Object run(Context cx) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[20]++;
            Scriptable scope = ScriptableObject.getTopLevelScope(generator);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[21]++;
            Callable closeGenerator = new Callable() {
                public Object call(Context cx, Scriptable scope,
                                   Scriptable thisObj, Object[] args) {
                     return ((NativeGenerator)thisObj).resume(cx, scope,
                             GENERATOR_CLOSE, new GeneratorClosedException());
                }
            };
            return ScriptRuntime.doTopCall(closeGenerator, cx, scope,
                                           generator, null);
        }
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[22]++;
        switch (id) {
          case Id_close:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[7]++;          arity=1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[23]++; s="close";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[24]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[25]++;          break;
          case Id_next:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[8]++;           arity=1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[26]++; s="next";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[27]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[28]++;           break;
          case Id_send:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[9]++;           arity=0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[29]++; s="send";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[30]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[31]++;           break;
          case Id_throw:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[10]++;          arity=0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[32]++; s="throw";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[33]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[34]++;          break;
          case Id___iterator__:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[11]++;   arity=1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[35]++; s="__iterator__";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[36]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[37]++;   break;
          default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[12]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(GENERATOR_TAG, id, s, arity);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[38]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[39]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((f.hasTag(GENERATOR_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[13]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[14]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[40]++;
        int id = f.methodId();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeGenerator) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[15]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[16]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[42]++;

        NativeGenerator generator = (NativeGenerator) thisObj;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[43]++;

        switch (id) {

          case Id_close:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[17]++;
            // need to run any pending finally clauses
            return generator.resume(cx, scope, GENERATOR_CLOSE,
                                    new GeneratorClosedException());

          case Id_next:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[18]++;
            // arguments to next() are ignored
            generator.firstTime = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[44]++;
            return generator.resume(cx, scope, GENERATOR_SEND,
                                    Undefined.instance);

          case Id_send:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[19]++; {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[45]++;
            Object arg = args.length > 0 ? args[0] : Undefined.instance;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[46]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((generator.firstTime) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((arg.equals(Undefined.instance)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[20]++;
                throw ScriptRuntime.typeError0("msg.send.newborn");

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[21]++;}
            return generator.resume(cx, scope, GENERATOR_SEND, arg);
          }

          case Id_throw:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[22]++;
            return generator.resume(cx, scope, GENERATOR_THROW,
                args.length > 0 ? args[0] : Undefined.instance);

          case Id___iterator__:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[23]++;
            return thisObj;

          default:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[24]++;
            throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    private Object resume(Context cx, Scriptable scope, int operation,
                          Object value)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((savedState == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[25]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((operation == GENERATOR_CLOSE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[27]++;
                return Undefined.instance;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[28]++;}
            Object thrown;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((operation == GENERATOR_THROW) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[29]++;
                thrown = value;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[50]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[30]++;
                thrown = NativeIterator.getStopIterationObject(scope);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[51]++;
            }
            throw new JavaScriptException(thrown, lineSource, lineNumber);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[26]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[52]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            synchronized (this) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[53]++;
int CodeCoverConditionCoverageHelper_C10;
              // generator execution is necessarily single-threaded and
              // non-reentrant.
              // See https://bugzilla.mozilla.org/show_bug.cgi?id=349263
              if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((locked) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[32]++;
                  throw ScriptRuntime.typeError0("msg.already.exec.gen");
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[33]++;}
              locked = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[54]++;
            }
            return function.resumeGenerator(cx, scope, operation, savedState,
                                            value);
        } catch (GeneratorClosedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[34]++;
            // On closing a generator in the compile path, the generator
            // throws a special exception. This ensures execution of all pending
            // finalizers and will not get caught by user code.
            return Undefined.instance;
        } catch (RhinoException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[35]++;
            lineNumber = e.lineNumber();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[55]++;
            lineSource = e.lineSource();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[56]++;
            savedState = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[57]++;
            throw e;
        } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[31]++;
}
            synchronized (this) {
              locked = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[58]++;
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[59]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((operation == GENERATOR_CLOSE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[36]++;
                savedState = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[60]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[37]++;}
        }
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s) {
        int id;
// #generated# Last update: 2007-06-14 13:13:03 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[61]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[62]++; String X = null; int c;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[63]++;
            int s_length = s.length();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[64]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s_length==4) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[38]++;
                c=s.charAt(0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[65]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[66]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[40]++; X="next";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[67]++;id=Id_next;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[68]++;
 }
                else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[41]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[69]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[42]++; X="send";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[70]++;id=Id_send;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[71]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[43]++;}
}

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[39]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[72]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((s_length==5) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[44]++;
                c=s.charAt(0);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[73]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[46]++; X="close";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[75]++;id=Id_close;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[76]++;
 }
                else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[47]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[77]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[48]++; X="throw";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[78]++;id=Id_throw;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[79]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[49]++;}
}

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[45]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[80]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((s_length==12) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[50]++; X="__iterator__";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[81]++;id=Id___iterator__;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[82]++;
 } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[51]++;}
}
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[83]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[52]++; id = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[84]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.branches[53]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[85]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_close                 = 1,
        Id_next                  = 2,
        Id_send                  = 3,
        Id_throw                 = 4,
        Id___iterator__          = 5,
        MAX_PROTOTYPE_ID         = 5;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[86]++;
  }

// #/string_id_map#
    private NativeFunction function;
    private Object savedState;
    private String lineSource;
    private int lineNumber;
    private boolean firstTime = true;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h.statements[87]++;
  }
    private boolean locked;

    public static class GeneratorClosedException extends RuntimeException {
        private static final long serialVersionUID = 2561315658662379681L;
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[54];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppazpqffdx4fxlm6mcvhf8h () {
    super("org.mozilla.javascript.RHINO-SRC-NativeGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 53; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeGenerator.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 53; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

