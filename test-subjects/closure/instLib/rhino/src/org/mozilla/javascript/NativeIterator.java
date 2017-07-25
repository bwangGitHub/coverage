/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.util.Iterator;

/**
 * This class implements iterator objects. See
 * http://developer.mozilla.org/en/docs/New_in_JavaScript_1.7#Iterators
 *
 */
public final class NativeIterator extends IdScriptableObject {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.ping();
  }

    private static final long serialVersionUID = -4136968203581667681L;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[1]++;
  }
    private static final Object ITERATOR_TAG = "Iterator";
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[2]++;
  }

    static void init(ScriptableObject scope, boolean sealed) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[3]++;
        // Iterator
        NativeIterator iterator = new NativeIterator();
        iterator.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[4]++;

        // Generator
        NativeGenerator.init(scope, sealed);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[5]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[6]++;

        // StopIteration
        NativeObject obj = new StopIteration();
        obj.setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[7]++;
        obj.setParentScope(scope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[8]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[1]++; obj.sealObject();
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[10]++;
 } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[2]++;}
        ScriptableObject.defineProperty(scope, STOP_ITERATION, obj,
                                        ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[11]++;
        // Use "associateValue" so that generators can continue to
        // throw StopIteration even if the property of the global
        // scope is replaced or deleted.
        scope.associateValue(ITERATOR_TAG, obj);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[12]++;
    }

    /**
     * Only for constructing the prototype object.
     */
    private NativeIterator() {
    }

    private NativeIterator(Object objectIterator) {
      this.objectIterator = objectIterator;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[13]++;
    }

    /**
     * Get the value of the "StopIteration" object. Note that this value
     * is stored in the top-level scope using "associateValue" so the
     * value can still be found even if a script overwrites or deletes
     * the global "StopIteration" property.
     * @param scope a scope whose parent chain reaches a top-level scope
     * @return the StopIteration object
     */
    public static Object getStopIterationObject(Scriptable scope) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[14]++;
        Scriptable top = ScriptableObject.getTopLevelScope(scope);
        return ScriptableObject.getTopScopeValue(top, ITERATOR_TAG);
    }

    private static final String STOP_ITERATION = "StopIteration";
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[15]++;
  }
    public static final String ITERATOR_PROPERTY_NAME = "__iterator__";
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[16]++;
  }

    static class StopIteration extends NativeObject {
        private static final long serialVersionUID = 2485151085722377663L;

        @Override
        public String getClassName() {
            return STOP_ITERATION;
        }

        /* StopIteration has custom instanceof behavior since it
         * doesn't have a constructor.
         */
        @Override
        public boolean hasInstance(Scriptable instance) {
            return instance instanceof StopIteration;
        }
    }

    @Override
    public String getClassName() {
        return "Iterator";
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[17]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[3]++;    arity=2;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[18]++; s="constructor";
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[19]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[20]++;          break;
          case Id_next:
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[4]++;           arity=0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[21]++; s="next";
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[22]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[23]++;                 break;
          case Id___iterator__:
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[5]++;   arity=1;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[24]++; s=ITERATOR_PROPERTY_NAME;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[25]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[26]++; break;
          default:
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[6]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(ITERATOR_TAG, id, s, arity);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[27]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[28]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((f.hasTag(ITERATOR_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[7]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[8]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[29]++;
        int id = f.methodId();
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((id == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[9]++;
            return jsConstructor(cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[10]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeIterator) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[11]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[12]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[32]++;

        NativeIterator iterator = (NativeIterator) thisObj;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[33]++;

        switch (id) {

          case Id_next:
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[13]++;
            return iterator.next(cx, scope);

          case Id___iterator__:
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[14]++;
            /// XXX: what about argument? SpiderMonkey apparently ignores it
            return thisObj;

          default:
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[15]++;
            throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    /* The JavaScript constructor */
    private static Object jsConstructor(Context cx, Scriptable scope,
                                        Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((args[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((args[0] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false))
        {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[16]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[35]++;
            Object argument = args.length == 0 ? Undefined.instance : args[0];
            throw ScriptRuntime.typeError1("msg.no.properties",
                                           ScriptRuntime.toString(argument));

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[17]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[36]++;
        Scriptable obj = ScriptRuntime.toObject(scope, args[0]);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[37]++;
        boolean keyOnly = args.length > 1 && ScriptRuntime.toBoolean(args[1]);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((thisObj != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[18]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[39]++;
            // Called as a function. Convert to iterator if possible.

            // For objects that implement java.lang.Iterable or
            // java.util.Iterator, have JavaScript Iterator call the underlying
            // iteration methods
            Iterator<?> iterator =
                VMBridge.instance.getJavaIterator(cx, scope, obj);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[40]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((iterator != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[20]++;
                scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[41]++;
                return cx.getWrapFactory().wrap(cx, scope,
                        new WrappedJavaIterator(iterator, scope),
                        WrappedJavaIterator.class);

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[21]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[42]++;

            // Otherwise, just call the runtime routine
            Scriptable jsIterator = ScriptRuntime.toIterator(cx, scope, obj,
                                                             keyOnly);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((jsIterator != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[22]++;
                return jsIterator;

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[23]++;}

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[19]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[44]++;

        // Otherwise, just set up to iterate over the properties of the object.
        // Do not call __iterator__ method.
        Object objectIterator = ScriptRuntime.enumInit(obj, cx,
            keyOnly ? ScriptRuntime.ENUMERATE_KEYS_NO_ITERATOR
                    : ScriptRuntime.ENUMERATE_ARRAY_NO_ITERATOR);
        ScriptRuntime.setEnumNumbers(objectIterator, true);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[45]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[46]++;
        NativeIterator result = new NativeIterator(objectIterator);
        result.setPrototype(ScriptableObject.getClassPrototype(scope,
                                result.getClassName()));
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[47]++;
        result.setParentScope(scope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[48]++;
        return result;
    }

    private Object next(Context cx, Scriptable scope) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[49]++;
        Boolean b = ScriptRuntime.enumNext(this.objectIterator);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[50]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((b.booleanValue()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[24]++;
            // Out of values. Throw StopIteration.
            throw new JavaScriptException(
                NativeIterator.getStopIterationObject(scope), null, 0);

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[25]++;}
        return ScriptRuntime.enumId(this.objectIterator, cx);
    }

    static public class WrappedJavaIterator
    {
        WrappedJavaIterator(Iterator<?> iterator, Scriptable scope) {
            this.iterator = iterator;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[51]++;
            this.scope = scope;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[52]++;
        }

        public Object next() {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[53]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[26]++;
                // Out of values. Throw StopIteration.
                throw new JavaScriptException(
                    NativeIterator.getStopIterationObject(scope), null, 0);

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[27]++;}
            return iterator.next();
        }

        public Object __iterator__(boolean b) {
            return this;
        }

        private Iterator<?> iterator;
        private Scriptable scope;
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s) {
        int id;
// #generated# Last update: 2007-06-11 09:43:19 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[54]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[55]++; String X = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[56]++;
            int s_length = s.length();
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s_length==4) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[28]++; X="next";
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[58]++;id=Id_next;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[59]++;
 }
            else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[29]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[60]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[30]++; X="constructor";
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[61]++;id=Id_constructor;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[62]++;
 }
            else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[31]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[63]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((s_length==12) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[32]++; X="__iterator__";
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[64]++;id=Id___iterator__;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[65]++;
 } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[33]++;}
}
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[66]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[34]++; id = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[67]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.branches[35]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[68]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor           = 1,
        Id_next                  = 2,
        Id___iterator__          = 3,
        MAX_PROTOTYPE_ID         = 3;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5.statements[69]++;
  }

// #/string_id_map#

    private Object objectIterator;
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5 ());
  }
    public static long[] statements = new long[70];
    public static long[] branches = new long[36];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeIterator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,3,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 14; i++) {
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh44spiw227r0m3au56tnj5 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeIterator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 69; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 35; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeIterator.java");
      for (int i = 1; i <= 69; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 35; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 14; i++) {
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

