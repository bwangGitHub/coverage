/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.*;

/**
Base class for native object implementation that uses IdFunctionObject to export its methods to script via <class-name>.prototype object.

Any descendant should implement at least the following methods:
    findInstanceIdInfo
    getInstanceIdName
    execIdCall
    methodArity

To define non-function properties, the descendant should override
    getInstanceIdValue
    setInstanceIdValue
to get/set property value and provide its default attributes.


To customize initialization of constructor and prototype objects, descendant
may override scopeInit or fillConstructorProperties methods.

*/
public abstract class IdScriptableObject extends ScriptableObject
    implements IdFunctionCall
{
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.ping();
  }

    private transient PrototypeValues prototypeValues;

    private static final class PrototypeValues implements Serializable
    {
        static final long serialVersionUID = 3038645279153854371L;

        private static final int NAME_SLOT = 1;
        private static final int SLOT_SPAN = 2;

        private IdScriptableObject obj;
        private int maxId;
        private Object[] valueArray;
        private short[] attributeArray;

        // The following helps to avoid creation of valueArray during runtime
        // initialization for common case of "constructor" property
        int constructorId;
        private IdFunctionObject constructor;
        private short constructorAttrs;

        PrototypeValues(IdScriptableObject obj, int maxId)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[1]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[2]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((maxId < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[3]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[4]++;}
            this.obj = obj;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[3]++;
            this.maxId = maxId;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[4]++;
        }

        final int getMaxId()
        {
            return maxId;
        }

        final void initValue(int id, String name, Object value, int attributes)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((1 <= id) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((id <= maxId) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[5]++;
                throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[6]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[6]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[7]++;
                throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[8]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[7]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[9]++;
                throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[10]++;}
            ScriptableObject.checkValidAttributes(attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[8]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[9]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj.findPrototypeId(name) != id) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[11]++;
                throw new IllegalArgumentException(name);
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[12]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[10]++;
int CodeCoverConditionCoverageHelper_C7;

            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((id == constructorId) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[13]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[11]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value instanceof IdFunctionObject) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[15]++;
                    throw new IllegalArgumentException("consructor should be initialized with IdFunctionObject");

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[16]++;}
                constructor = (IdFunctionObject)value;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[12]++;
                constructorAttrs = (short)attributes;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[13]++;
                return;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[14]++;}

            initSlot(id, name, value, attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[14]++;
        }

        private void initSlot(int id, String name, Object value,
                              int attributes)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[15]++;
            Object[] array = valueArray;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[16]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((array == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[17]++;
                throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[18]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[17]++;
int CodeCoverConditionCoverageHelper_C10;

            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[19]++;
                value = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[18]++;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[20]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[19]++;
            int index = (id - 1) * SLOT_SPAN;
            synchronized (this) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[20]++;
                Object value2 = array[index];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[21]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value2 == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[21]++;
                    array[index] = value;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[22]++;
                    array[index + NAME_SLOT] = name;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[23]++;
                    attributeArray[id - 1] = (short)attributes;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[24]++;

                } else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[22]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[25]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((name.equals(array[index + NAME_SLOT])) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[23]++;
                         throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[24]++;}
                }
            }
        }

        final IdFunctionObject createPrecachedConstructor()
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[26]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((constructorId != 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[25]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[26]++;}
            constructorId = obj.findPrototypeId("constructor");
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[27]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[28]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((constructorId == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[27]++;
                throw new IllegalStateException(
                    "No id for constructor property");

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[28]++;}
            obj.initPrototypeId(constructorId);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[29]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[30]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((constructor == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[29]++;
                throw new IllegalStateException(
                    obj.getClass().getName()+".initPrototypeId() did not "
                    +"initialize id="+constructorId);

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[30]++;}
            constructor.initFunction(obj.getClassName(),
                                     ScriptableObject.getTopLevelScope(obj));
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[31]++;
            constructor.markAsConstructor(obj);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[32]++;
            return constructor;
        }

        final int findId(String name)
        {
            return obj.findPrototypeId(name);
        }

        final boolean has(int id)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[33]++;
            Object[] array = valueArray;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[34]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((array == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[31]++;
                // Not yet initialized, assume all exists
                return true;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[32]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[35]++;
            int valueSlot = (id  - 1) * SLOT_SPAN;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[36]++;
            Object value = array[valueSlot];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[37]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[33]++;
                // The particular entry has not been yet initialized
                return true;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[34]++;}
            return value != NOT_FOUND;
        }

        final Object get(int id)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[38]++;
            Object value = ensureId(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[39]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((value == UniqueTag.NULL_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[35]++;
                value = null;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[40]++;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[36]++;}
            return value;
        }

        final void set(int id, Scriptable start, Object value)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[41]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[37]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[38]++;}
            ensureId(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[42]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[43]++;
            int attr = attributeArray[id - 1];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[44]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 (((attr & READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[39]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[45]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((start == obj) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[41]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[46]++;
int CodeCoverConditionCoverageHelper_C22;
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[43]++;
                        value = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[47]++;

                    } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[44]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[48]++;
                    int valueSlot = (id  - 1) * SLOT_SPAN;
                    synchronized (this) {
                        valueArray[valueSlot] = value;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[49]++;
                    }

                }
                else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[42]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[50]++;
                    int nameSlot = (id  - 1) * SLOT_SPAN + NAME_SLOT;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[51]++;
                    String name = (String)valueArray[nameSlot];
                    start.put(name, start, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[52]++;
                }

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[40]++;}
        }

        final void delete(int id)
        {
            ensureId(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[53]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[54]++;
            int attr = attributeArray[id - 1];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[55]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 (((attr & PERMANENT) == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[45]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[56]++;
                int valueSlot = (id  - 1) * SLOT_SPAN;
                synchronized (this) {
                    valueArray[valueSlot] = NOT_FOUND;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[57]++;
                    attributeArray[id - 1] = EMPTY;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[58]++;
                }

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[46]++;}
        }

        final int getAttributes(int id)
        {
            ensureId(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[59]++;
            return attributeArray[id - 1];
        }

        final void setAttributes(int id, int attributes)
        {
            ScriptableObject.checkValidAttributes(attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[60]++;
            ensureId(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[61]++;
            synchronized (this) {
                attributeArray[id - 1] = (short)attributes;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[62]++;
            }
        }

        final Object[] getNames(boolean getAll, Object[] extraEntries)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[63]++;
            Object[] names = null;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[64]++;
            int count = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[65]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[1]++;


int CodeCoverConditionCoverageHelper_C24;
            for (int id = 1;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((id <= maxId) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); ++id) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[1]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[2]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[3]++;
}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[66]++;
                Object value = ensureId(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[67]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((getAll) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 (((attributeArray[id - 1] & DONTENUM) == 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[47]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[68]++;
int CodeCoverConditionCoverageHelper_C26;
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[49]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[69]++;
                        int nameSlot = (id  - 1) * SLOT_SPAN + NAME_SLOT;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[70]++;
                        String name = (String)valueArray[nameSlot];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[71]++;
int CodeCoverConditionCoverageHelper_C27;
                        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((names == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[51]++;
                            names = new Object[maxId];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[72]++;

                        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[52]++;}
                        names[count++] = name;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[73]++;

                    } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[50]++;}

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[48]++;}
            }
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[74]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[53]++;
                return extraEntries;

            } else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[54]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[75]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((extraEntries == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((extraEntries.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[55]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[76]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((count != names.length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[57]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[77]++;
                    Object[] tmp = new Object[count];
                    System.arraycopy(names, 0, tmp, 0, count);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[78]++;
                    names = tmp;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[79]++;

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[58]++;}
                return names;

            } else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[56]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[80]++;
                int extra = extraEntries.length;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[81]++;
                Object[] tmp = new Object[extra + count];
                System.arraycopy(extraEntries, 0, tmp, 0, extra);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[82]++;
                System.arraycopy(names, 0, tmp, extra, count);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[83]++;
                return tmp;
            }
}
        }

        private Object ensureId(int id)
        {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[84]++;
            Object[] array = valueArray;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[85]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((array == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[59]++;
                synchronized (this) {
                    array = valueArray;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[86]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[87]++;
int CodeCoverConditionCoverageHelper_C32;
                    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((array == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[61]++;
                        array = new Object[maxId * SLOT_SPAN];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[88]++;
                        valueArray = array;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[89]++;
                        attributeArray = new short[maxId];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[90]++;

                    } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[62]++;}
                }

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[60]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[91]++;
            int valueSlot = (id  - 1) * SLOT_SPAN;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[92]++;
            Object value = array[valueSlot];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[93]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[63]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[94]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((id == constructorId) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[65]++;
                    initSlot(constructorId, "constructor",
                             constructor, constructorAttrs);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[95]++;
                    constructor = null;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[96]++;
 // no need to refer it any longer
                } else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[66]++;
                    obj.initPrototypeId(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[97]++;
                }
                value = array[valueSlot];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[98]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[99]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[67]++;
                    throw new IllegalStateException(
                        obj.getClass().getName()+".initPrototypeId(int id) "
                        +"did not initialize id="+id);

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[68]++;}

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[64]++;}
            return value;
        }
    }

    public IdScriptableObject()
    {
    }

    public IdScriptableObject(Scriptable scope, Scriptable prototype)
    {
        super(scope, prototype);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[100]++;
    }

    protected final Object defaultGet(String name)
    {
        return super.get(name, this);
    }

    protected final void defaultPut(String name, Object value)
    {
        super.put(name, this, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[101]++;
    }

    @Override
    public boolean has(String name, Scriptable start)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[102]++;
        int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[103]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[69]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[104]++;
            int attr = (info >>> 16);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[105]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 (((attr & PERMANENT) != 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[71]++;
                return true;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[72]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[106]++;
            int id = (info & 0xFFFF);
            return NOT_FOUND != getInstanceIdValue(id);

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[70]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[107]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[73]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[108]++;
            int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[109]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[75]++;
                return prototypeValues.has(id);

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[76]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[74]++;}
        return super.has(name, start);
    }

    @Override
    public Object get(String name, Scriptable start)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[110]++;
        // Check for slot first for performance. This is a very hot code
        // path that should be further optimized.
        Object value = super.get(name, start);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[111]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[77]++;
            return value;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[78]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[112]++;
        int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[113]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[79]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[114]++;
            int id = (info & 0xFFFF);
            value = getInstanceIdValue(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[115]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[116]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[81]++; return value;
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[82]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[80]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[117]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[83]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[118]++;
            int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[119]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[85]++;
                value = prototypeValues.get(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[120]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[121]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[87]++; return value;
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[88]++;}

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[86]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[84]++;}
        return NOT_FOUND;
    }

    @Override
    public void put(String name, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[122]++;
        int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[123]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[89]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[124]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[91]++;
                throw Context.reportRuntimeError1("msg.modify.sealed",
                                                  name);

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[92]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[125]++;
            int attr = (info >>> 16);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[126]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 (((attr & READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[93]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[127]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[95]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[128]++;
                    int id = (info & 0xFFFF);
                    setInstanceIdValue(id, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[129]++;

                }
                else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[96]++;
                    start.put(name, start, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[130]++;
                }

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[94]++;}
            return;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[90]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[131]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[97]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[132]++;
            int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[133]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[99]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[134]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[101]++;
                    throw Context.reportRuntimeError1("msg.modify.sealed",
                                                      name);

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[102]++;}
                prototypeValues.set(id, start, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[135]++;
                return;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[100]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[98]++;}
        super.put(name, start, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[136]++;
    }

    @Override
    public void delete(String name)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[137]++;
        int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[138]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[103]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[139]++;
int CodeCoverConditionCoverageHelper_C54;
            // Let the super class to throw exceptions for sealed objects
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[105]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[140]++;
                int attr = (info >>> 16);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[141]++;
int CodeCoverConditionCoverageHelper_C55;
                if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 (((attr & PERMANENT) == 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[107]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[142]++;
                    int id = (info & 0xFFFF);
                    setInstanceIdValue(id, NOT_FOUND);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[143]++;

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[108]++;}
                return;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[106]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[104]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[144]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[109]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[145]++;
            int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[146]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[111]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[147]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[113]++;
                    prototypeValues.delete(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[148]++;

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[114]++;}
                return;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[112]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[110]++;}
        super.delete(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[149]++;
    }

    @Override
    public int getAttributes(String name)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[150]++;
        int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[151]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[115]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[152]++;
            int attr = (info >>> 16);
            return attr;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[116]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[153]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[117]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[154]++;
            int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[155]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[119]++;
                return prototypeValues.getAttributes(id);

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[120]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[118]++;}
        return super.getAttributes(name);
    }

    @Override
    public void setAttributes(String name, int attributes)
    {
        ScriptableObject.checkValidAttributes(attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[156]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[157]++;
        int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[158]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[121]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[159]++;
            int id = (info & 0xFFFF);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[160]++;
            int currentAttributes = (info >>> 16);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[161]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((attributes != currentAttributes) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[123]++;
                setInstanceIdAttributes(id, attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[162]++;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[124]++;}
            return;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[122]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[163]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[125]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[164]++;
            int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[165]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[127]++;
                prototypeValues.setAttributes(id, attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[166]++;
                return;

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[128]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[126]++;}
        super.setAttributes(name, attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[167]++;
    }

    @Override
    Object[] getIds(boolean getAll)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[168]++;
        Object[] result = super.getIds(getAll);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[169]++;
int CodeCoverConditionCoverageHelper_C66;

        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[129]++;
            result = prototypeValues.getNames(getAll, result);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[170]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[130]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[171]++;

        int maxInstanceId = getMaxInstanceId();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[172]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((maxInstanceId != 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[131]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[173]++;
            Object[] ids = null;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[174]++;
            int count = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[175]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[4]++;


int CodeCoverConditionCoverageHelper_C68;

            for (int id = maxInstanceId;(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); --id) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[4]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[5]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.loops[6]++;
}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[176]++;
                String name = getInstanceIdName(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[177]++;
                int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[178]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[133]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[179]++;
                    int attr = (info >>> 16);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[180]++;
int CodeCoverConditionCoverageHelper_C70;
                    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 (((attr & PERMANENT) == 0) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[135]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[181]++;
int CodeCoverConditionCoverageHelper_C71;
                        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((NOT_FOUND == getInstanceIdValue(id)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[137]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[182]++;
                            continue;

                        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[138]++;}

                    } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[136]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[183]++;
int CodeCoverConditionCoverageHelper_C72;
                    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((getAll) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 (((attr & DONTENUM) == 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[139]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[184]++;
int CodeCoverConditionCoverageHelper_C73;
                        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[141]++;
                            // Need extra room for no more then [1..id] names
                            ids = new Object[id];
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[185]++;

                        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[142]++;}
                        ids[count++] = name;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[186]++;

                    } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[140]++;}

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[134]++;}
            }
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[187]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((count != 0) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[143]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[188]++;
int CodeCoverConditionCoverageHelper_C75;
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((result.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((ids.length == count) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[145]++;
                    result = ids;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[189]++;

                }
                else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[146]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[190]++;
                    Object[] tmp = new Object[result.length + count];
                    System.arraycopy(result, 0, tmp, 0, result.length);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[191]++;
                    System.arraycopy(ids, 0, tmp, result.length, count);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[192]++;
                    result = tmp;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[193]++;
                }

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[144]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[132]++;}
        return result;
    }

    /**
     * Get maximum id findInstanceIdInfo can generate.
     */
    protected int getMaxInstanceId()
    {
        return 0;
    }

    protected static int instanceIdInfo(int attributes, int id)
    {
        return (attributes << 16) | id;
    }

    /**
     * Map name to id of instance property.
     * Should return 0 if not found or the result of
     * {@link #instanceIdInfo(int, int)}.
     */
    protected int findInstanceIdInfo(String name)
    {
        return 0;
    }

    /** Map id back to property name it defines.
     */
    protected String getInstanceIdName(int id)
    {
        throw new IllegalArgumentException(String.valueOf(id));
    }

    /** Get id value.
     ** If id value is constant, descendant can call cacheIdValue to store
     ** value in the permanent cache.
     ** Default implementation creates IdFunctionObject instance for given id
     ** and cache its value
     */
    protected Object getInstanceIdValue(int id)
    {
        throw new IllegalStateException(String.valueOf(id));
    }

    /**
     * Set or delete id value. If value == NOT_FOUND , the implementation
     * should make sure that the following getInstanceIdValue return NOT_FOUND.
     */
    protected void setInstanceIdValue(int id, Object value)
    {
        throw new IllegalStateException(String.valueOf(id));
    }

    /**
     * Update the attributes of the given instance property. Classes which
     * want to support changing property attributes via Object.defineProperty
     * must override this method. The default implementation throws
     * InternalError.
     * @param id the instance property id
     * @param attr the new attribute bitset
     */
    protected void setInstanceIdAttributes(int id, int attr) {
        throw ScriptRuntime.constructError("InternalError",
                "Changing attributes not supported for " + getClassName()
                + " " + getInstanceIdName(id) + " property");
    }

    /** 'thisObj' will be null if invoked as constructor, in which case
     ** instance of Scriptable should be returned. */
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
        throw f.unknown();
    }

    public final IdFunctionObject exportAsJSClass(int maxPrototypeId,
                                                  Scriptable scope,
                                                  boolean sealed)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[194]++;
int CodeCoverConditionCoverageHelper_C76;
        // Set scope and prototype unless this is top level scope itself
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (8)) == 0 || true) &&
 ((scope != this) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[147]++;
            setParentScope(scope);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[195]++;
            setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[196]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[148]++;}

        activatePrototypeMap(maxPrototypeId);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[197]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[198]++;
        IdFunctionObject ctor = prototypeValues.createPrecachedConstructor();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[199]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[149]++;
            sealObject();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[200]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[150]++;}
        fillConstructorProperties(ctor);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[201]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[202]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[151]++;
            ctor.sealObject();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[203]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[152]++;}
        ctor.exportAsScopeProperty();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[204]++;
        return ctor;
    }

    public final boolean hasPrototypeMap()
    {
        return prototypeValues != null;
    }

    public final void activatePrototypeMap(int maxPrototypeId)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[205]++;
        PrototypeValues values = new PrototypeValues(this, maxPrototypeId);
        synchronized (this) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[206]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[153]++;
                throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[154]++;}
            prototypeValues = values;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[207]++;
        }
    }

    public final void initPrototypeMethod(Object tag, int id, String name,
                                          int arity)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[208]++;
        Scriptable scope = ScriptableObject.getTopLevelScope(this);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[209]++;
        IdFunctionObject f = newIdFunction(tag, id, name, arity, scope);
        prototypeValues.initValue(id, name, f, DONTENUM);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[210]++;
    }

    public final void initPrototypeConstructor(IdFunctionObject f)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[211]++;
        int id = prototypeValues.constructorId;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[212]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((id == 0) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[155]++;
            throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[156]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[213]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((f.methodId() != id) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[157]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[158]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[214]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[159]++; f.sealObject();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[215]++;
 } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[160]++;}
        prototypeValues.initValue(id, "constructor", f, DONTENUM);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[216]++;
    }

    public final void initPrototypeValue(int id, String name, Object value,
                                         int attributes)
    {
        prototypeValues.initValue(id, name, value, attributes);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[217]++;
    }

    protected void initPrototypeId(int id)
    {
        throw new IllegalStateException(String.valueOf(id));
    }

    protected int findPrototypeId(String name)
    {
        throw new IllegalStateException(name);
    }

    protected void fillConstructorProperties(IdFunctionObject ctor)
    {
    }

    protected void addIdFunctionProperty(Scriptable obj, Object tag, int id,
                                         String name, int arity)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[218]++;
        Scriptable scope = ScriptableObject.getTopLevelScope(obj);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[219]++;
        IdFunctionObject f = newIdFunction(tag, id, name, arity, scope);
        f.addAsProperty(obj);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[220]++;
    }

    /**
     * Utility method to construct type error to indicate incompatible call
     * when converting script thisObj to a particular type is not possible.
     * Possible usage would be to have a private function like realThis:
     * <pre>
     *  private static NativeSomething realThis(Scriptable thisObj,
     *                                          IdFunctionObject f)
     *  {
     *      if (!(thisObj instanceof NativeSomething))
     *          throw incompatibleCallError(f);
     *      return (NativeSomething)thisObj;
     * }
     * </pre>
     * Note that although such function can be implemented universally via
     * java.lang.Class.isInstance(), it would be much more slower.
     * @param f function that is attempting to convert 'this'
     * object.
     * @return Scriptable object suitable for a check by the instanceof
     * operator.
     * @throws RuntimeException if no more instanceof target can be found
     */
    protected static EcmaError incompatibleCallError(IdFunctionObject f)
    {
        throw ScriptRuntime.typeError1("msg.incompat.call",
                                       f.getFunctionName());
    }

    private IdFunctionObject newIdFunction(Object tag, int id, String name,
                                           int arity, Scriptable scope)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[221]++;
        IdFunctionObject f = new IdFunctionObject(this, tag, id, name, arity,
                                                  scope);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[222]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[161]++; f.sealObject();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[223]++;
 } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[162]++;}
        return f;
    }

    @Override
    public void defineOwnProperty(Context cx, Object key, ScriptableObject desc) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[224]++;
int CodeCoverConditionCoverageHelper_C84;
      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((key instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[163]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[225]++;
        String name = (String) key;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[226]++;
        int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[227]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[165]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[228]++;
            int id = (info & 0xFFFF);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[229]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((isAccessorDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[167]++;
              delete(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[230]++;
 // it will be replaced with a slot
            } else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[168]++;
              checkPropertyDefinition(desc);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[231]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[232]++;
              ScriptableObject current = getOwnPropertyDescriptor(cx, key);
              checkPropertyChange(name, current, desc);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[233]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[234]++;
              int attr = (info >>> 16);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[235]++;
              Object value = getProperty(desc, "value");
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[236]++;
int CodeCoverConditionCoverageHelper_C87;
              if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 (((attr & READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[169]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[237]++;
                Object currentValue = getInstanceIdValue(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[238]++;
int CodeCoverConditionCoverageHelper_C88;
                if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((sameValue(value, currentValue)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[171]++;
                  setInstanceIdValue(id, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[239]++;

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[172]++;}

              } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[170]++;}
              setAttributes(name, applyDescriptorToAttributeBitset(attr, desc));
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[240]++;
              return;
            }

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[166]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[241]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[173]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[242]++;
            int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[243]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[175]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[244]++;
int CodeCoverConditionCoverageHelper_C91;
              if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((isAccessorDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[177]++;
                prototypeValues.delete(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[245]++;
 // it will be replaced with a slot
              } else {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[178]++;
                checkPropertyDefinition(desc);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[246]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[247]++;
                ScriptableObject current = getOwnPropertyDescriptor(cx, key);
                checkPropertyChange(name, current, desc);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[248]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[249]++;
                int attr = prototypeValues.getAttributes(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[250]++;
                Object value = getProperty(desc, "value");
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[251]++;
int CodeCoverConditionCoverageHelper_C92;
                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 (((attr & READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[179]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[252]++;
                  Object currentValue = prototypeValues.get(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[253]++;
int CodeCoverConditionCoverageHelper_C93;
                  if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((sameValue(value, currentValue)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[181]++;
                    prototypeValues.set(id, this, value);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[254]++;

                  } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[182]++;}

                } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[180]++;}
                prototypeValues.setAttributes(id, applyDescriptorToAttributeBitset(attr, desc));
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[255]++;
                return;
              }

            } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[176]++;}

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[174]++;}

      } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[164]++;}
      super.defineOwnProperty(cx, key, desc);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[256]++;
    }


    @Override
    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[257]++;
      ScriptableObject desc = super.getOwnPropertyDescriptor(cx, id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[258]++;
int CodeCoverConditionCoverageHelper_C94;
      if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((desc == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[183]++;
        desc = getBuiltInDescriptor((String) id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[259]++;

      } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[184]++;}
      return desc;
    }

    private ScriptableObject getBuiltInDescriptor(String name) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[260]++;
      Object value = null;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[261]++;
      int attr = EMPTY;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[262]++;

      Scriptable scope = getParentScope();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[263]++;
int CodeCoverConditionCoverageHelper_C95;
      if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[185]++;
        scope = this;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[264]++;

      } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[186]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[265]++;

      int info = findInstanceIdInfo(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[266]++;
int CodeCoverConditionCoverageHelper_C96;
      if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((info != 0) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[187]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[267]++;
        int id = (info & 0xFFFF);
        value = getInstanceIdValue(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[268]++;
        attr = (info >>> 16);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[269]++;
        return buildDataDescriptor(scope, value, attr);

      } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[188]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[270]++;
int CodeCoverConditionCoverageHelper_C97;
      if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[189]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[271]++;
        int id = prototypeValues.findId(name);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[272]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((id != 0) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[191]++;
          value = prototypeValues.get(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[273]++;
          attr = prototypeValues.getAttributes(id);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[274]++;
          return buildDataDescriptor(scope, value, attr);

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[192]++;}

      } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[190]++;}
      return null;
    }

    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[275]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[276]++;
        int maxPrototypeId = stream.readInt();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[277]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((maxPrototypeId != 0) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[193]++;
            activatePrototypeMap(maxPrototypeId);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[278]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[194]++;}
    }

    private void writeObject(ObjectOutputStream stream)
        throws IOException
    {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[279]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[280]++;
        int maxPrototypeId = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[281]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((prototypeValues != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[195]++;
            maxPrototypeId = prototypeValues.getMaxId();
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[282]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.branches[196]++;}
        stream.writeInt(maxPrototypeId);
CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt.statements[283]++;
    }

}

class CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt ());
  }
    public static long[] statements = new long[284];
    public static long[] branches = new long[197];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[101];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-IdScriptableObject.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 100; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$el0607z4lg6oa7b0wmwoaax2nrfazqk20ujg9a0wusg93ghv0pt () {
    super("org.mozilla.javascript.RHINO-SRC-IdScriptableObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 283; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 196; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 100; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-IdScriptableObject.java");
      for (int i = 1; i <= 283; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 196; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 100; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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


