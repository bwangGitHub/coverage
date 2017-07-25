/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.mozilla.javascript.debug.DebuggableObject;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;
import org.mozilla.javascript.annotations.JSStaticFunction;

/**
 * This is the default implementation of the Scriptable interface. This
 * class provides convenient default behavior that makes it easier to
 * define host objects.
 * <p>
 * Various properties and methods of JavaScript objects can be conveniently
 * defined using methods of ScriptableObject.
 * <p>
 * Classes extending ScriptableObject must define the getClassName method.
 *
 * @see org.mozilla.javascript.Scriptable
 */

public abstract class ScriptableObject implements Scriptable, Serializable,
                                                  DebuggableObject,
                                                  ConstProperties
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.ping();
  }


    static final long serialVersionUID = 2829861078851942586L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[1]++;
  }

    /**
     * The empty property attribute.
     *
     * Used by getAttributes() and setAttributes().
     *
     * @see org.mozilla.javascript.ScriptableObject#getAttributes(String)
     * @see org.mozilla.javascript.ScriptableObject#setAttributes(String, int)
     */
    public static final int EMPTY =     0x00;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[2]++;
  }

    /**
     * Property attribute indicating assignment to this property is ignored.
     *
     * @see org.mozilla.javascript.ScriptableObject
     *      #put(String, Scriptable, Object)
     * @see org.mozilla.javascript.ScriptableObject#getAttributes(String)
     * @see org.mozilla.javascript.ScriptableObject#setAttributes(String, int)
     */
    public static final int READONLY =  0x01;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[3]++;
  }

    /**
     * Property attribute indicating property is not enumerated.
     *
     * Only enumerated properties will be returned by getIds().
     *
     * @see org.mozilla.javascript.ScriptableObject#getIds()
     * @see org.mozilla.javascript.ScriptableObject#getAttributes(String)
     * @see org.mozilla.javascript.ScriptableObject#setAttributes(String, int)
     */
    public static final int DONTENUM =  0x02;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[4]++;
  }

    /**
     * Property attribute indicating property cannot be deleted.
     *
     * @see org.mozilla.javascript.ScriptableObject#delete(String)
     * @see org.mozilla.javascript.ScriptableObject#getAttributes(String)
     * @see org.mozilla.javascript.ScriptableObject#setAttributes(String, int)
     */
    public static final int PERMANENT = 0x04;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[5]++;
  }

    /**
     * Property attribute indicating that this is a const property that has not
     * been assigned yet.  The first 'const' assignment to the property will
     * clear this bit.
     */
    public static final int UNINITIALIZED_CONST = 0x08;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[6]++;
  }

    public static final int CONST = PERMANENT|READONLY|UNINITIALIZED_CONST;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[7]++;
  }
    /**
     * The prototype of this object.
     */
    private Scriptable prototypeObject;

    /**
     * The parent scope of this object.
     */
    private Scriptable parentScopeObject;

    private transient Slot[] slots;
    // If count >= 0, it gives number of keys or if count < 0,
    // it indicates sealed object where ~count gives number of keys
    private int count;

    // gateways into the definition-order linked list of slots
    private transient Slot firstAdded;
    private transient Slot lastAdded;


    private volatile Map<Object,Object> associatedValues;

    private static final int SLOT_QUERY = 1;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[8]++;
  }
    private static final int SLOT_MODIFY = 2;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[9]++;
  }
    private static final int SLOT_MODIFY_CONST = 3;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[10]++;
  }
    private static final int SLOT_MODIFY_GETTER_SETTER = 4;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[11]++;
  }
    private static final int SLOT_CONVERT_ACCESSOR_TO_DATA = 5;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[12]++;
  }

    // initial slot array size, must be a power of 2
    private static final int INITIAL_SLOT_SIZE = 4;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[13]++;
  }

    private boolean isExtensible = true;
  {
    CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[14]++;
  }

    private static class Slot implements Serializable
    {
        private static final long serialVersionUID = -6090581677123995491L;
        String name; // This can change due to caching
        int indexOrHash;
        private volatile short attributes;
        transient volatile boolean wasDeleted;
        volatile Object value;
        transient Slot next; // next in hash table bucket
        transient volatile Slot orderedNext; // next in linked list

        Slot(String name, int indexOrHash, int attributes)
        {
            this.name = name;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[15]++;
            this.indexOrHash = indexOrHash;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[16]++;
            this.attributes = (short)attributes;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[17]++;
        }

        private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException
        {
            in.defaultReadObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[18]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[1]++;
                indexOrHash = name.hashCode();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[20]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[2]++;}
        }

        boolean setValue(Object value, Scriptable owner, Scriptable start) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (((attributes & READONLY) != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[3]++;
                return true;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[4]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((owner == start) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[5]++;
                this.value = value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[23]++;
                return true;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[6]++;
                return false;
            }
        }

        Object getValue(Scriptable start) {
            return value;
        }

        int getAttributes()
        {
            return attributes;
        }

        synchronized void setAttributes(int value)
        {
            checkValidAttributes(value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[24]++;
            attributes = (short)value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[25]++;
        }

        void markDeleted() {
            wasDeleted = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[26]++;
            value = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[27]++;
            name = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[28]++;
        }

        ScriptableObject getPropertyDescriptor(Context cx, Scriptable scope) {
            return buildDataDescriptor(scope, value, attributes);
        }

    }

    protected static ScriptableObject buildDataDescriptor(Scriptable scope,
                                                          Object value,
                                                          int attributes) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[29]++;
        ScriptableObject desc = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[30]++;
        desc.defineProperty("value", value, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[31]++;
        desc.defineProperty("writable", (attributes & READONLY) == 0, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[32]++;
        desc.defineProperty("enumerable", (attributes & DONTENUM) == 0, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[33]++;
        desc.defineProperty("configurable", (attributes & PERMANENT) == 0, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[34]++;
        return desc;
    }

    private static final class GetterSlot extends Slot
    {
        static final long serialVersionUID = -4900574849788797588L;

        Object getter;
        Object setter;

        GetterSlot(String name, int indexOrHash, int attributes)
        {
            super(name, indexOrHash, attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[35]++;
        }

        @Override
        ScriptableObject getPropertyDescriptor(Context cx, Scriptable scope) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[36]++;
            int attr = getAttributes();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[37]++;
            ScriptableObject desc = new NativeObject();
            ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[38]++;
            desc.defineProperty("enumerable", (attr & DONTENUM) == 0, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[39]++;
            desc.defineProperty("configurable", (attr & PERMANENT) == 0, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[40]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[41]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getter != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[7]++; desc.defineProperty("get", getter, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[42]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[8]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[43]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((setter != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[9]++; desc.defineProperty("set", setter, EMPTY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[44]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[10]++;}
            return desc;
        }

        @Override
        boolean setValue(Object value, Scriptable owner, Scriptable start) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((setter == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[11]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[46]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getter != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[13]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[47]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Context.getContext().hasFeature(Context.FEATURE_STRICT_MODE)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[15]++;
                        // Based on TC39 ES3.1 Draft of 9-Feb-2009, 8.12.4, step 2,
                        // we should throw a TypeError in this case.
                        throw ScriptRuntime.typeError1("msg.set.prop.no.setter", name);

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[16]++;}
                    // Assignment to a property with only a getter defined. The
                    // assignment is ignored. See bug 478047.
                    return true;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[14]++;}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[12]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[48]++;
                Context cx = Context.getContext();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((setter instanceof MemberBox) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[17]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[50]++;
                    MemberBox nativeSetter = (MemberBox)setter;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[51]++;
                    Class<?> pTypes[] = nativeSetter.argTypes;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[52]++;
                    // XXX: cache tag since it is already calculated in
                    // defineProperty ?
                    Class<?> valueType = pTypes[pTypes.length - 1];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[53]++;
                    int tag = FunctionObject.getTypeTag(valueType);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[54]++;
                    Object actualArg = FunctionObject.convertArg(cx, start,
                                                                 value, tag);
                    Object setterThis;
                    Object[] args;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((nativeSetter.delegateTo == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[19]++;
                        setterThis = start;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[56]++;
                        args = new Object[] { actualArg };
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[57]++;

                    } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[20]++;
                        setterThis = nativeSetter.delegateTo;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[58]++;
                        args = new Object[] { start, actualArg };
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[59]++;
                    }
                    nativeSetter.invoke(setterThis, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[60]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[18]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[61]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((setter instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[21]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[62]++;
                    Function f = (Function)setter;
                    f.call(cx, f.getParentScope(), start,
                           new Object[] { value });
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[63]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[22]++;}
}
                return true;
            }
            return super.setValue(value, owner, start);
        }

        @Override
        Object getValue(Scriptable start) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[64]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((getter != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[23]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[65]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((getter instanceof MemberBox) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[25]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[66]++;
                    MemberBox nativeGetter = (MemberBox)getter;
                    Object getterThis;
                    Object[] args;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((nativeGetter.delegateTo == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[27]++;
                        getterThis = start;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[68]++;
                        args = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[69]++;

                    } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[28]++;
                        getterThis = nativeGetter.delegateTo;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[70]++;
                        args = new Object[] { start };
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[71]++;
                    }
                    return nativeGetter.invoke(getterThis, args);

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[26]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[72]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getter instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[29]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[73]++;
                    Function f = (Function)getter;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[74]++;
                    Context cx = Context.getContext();
                    return f.call(cx, f.getParentScope(), start,
                                  ScriptRuntime.emptyArgs);

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[30]++;}
}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[24]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[75]++;
            Object val = this.value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((val instanceof LazilyLoadedCtor) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[31]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[77]++;
                LazilyLoadedCtor initializer = (LazilyLoadedCtor)val;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[78]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    initializer.init();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[79]++;
                } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[33]++;
}
                    this.value = val = initializer.getValue();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[80]++;
                }

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[32]++;}
            return val;
        }

        @Override
        void markDeleted() {
            super.markDeleted();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[81]++;
            getter = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[82]++;
            setter = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[83]++;
        }
    }

    /**
     * A wrapper around a slot that allows the slot to be used in a new slot
     * table while keeping it functioning in its old slot table/linked list
     * context. This is used when linked slots are copied to a new slot table.
     * In a multi-threaded environment, these slots may still be accessed
     * through their old slot table. See bug 688458.
     */
    private static class RelinkedSlot extends Slot {

        final Slot slot;

        RelinkedSlot(Slot slot) {
            super(slot.name, slot.indexOrHash, slot.attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[84]++;
            // Make sure we always wrap the actual slot, not another relinked one
            this.slot = unwrapSlot(slot);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[85]++;
        }

        @Override
        boolean setValue(Object value, Scriptable owner, Scriptable start) {
            return slot.setValue(value, owner, start);
        }

        @Override
        Object getValue(Scriptable start) {
            return slot.getValue(start);
        }

        @Override
        ScriptableObject getPropertyDescriptor(Context cx, Scriptable scope) {
            return slot.getPropertyDescriptor(cx, scope);
        }

        @Override
        int getAttributes() {
            return slot.getAttributes();
        }

        @Override
        void setAttributes(int value) {
            slot.setAttributes(value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[86]++;
        }

        @Override
        void markDeleted() {
            super.markDeleted();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[87]++;
            slot.markDeleted();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[88]++;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(slot);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[89]++;  // just serialize the wrapped slot
        }

    }

    static void checkValidAttributes(int attributes)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[90]++;
        final int mask = READONLY | DONTENUM | PERMANENT | UNINITIALIZED_CONST;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[91]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 (((attributes & ~mask) != 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[34]++;
            throw new IllegalArgumentException(String.valueOf(attributes));

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[35]++;}
    }

    public ScriptableObject()
    {
    }

    public ScriptableObject(Scriptable scope, Scriptable prototype)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[92]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[36]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[37]++;}

        parentScopeObject = scope;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[93]++;
        prototypeObject = prototype;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[94]++;
    }

    /**
     * Gets the value that will be returned by calling the typeof operator on this object.
     * @return default is "object" unless {@link #avoidObjectDetection()} is <code>true</code> in which
     * case it returns "undefined"
     */
    public String getTypeOf() {
    	return avoidObjectDetection() ? "undefined" : "object";
    }

    /**
     * Return the name of the class.
     *
     * This is typically the same name as the constructor.
     * Classes extending ScriptableObject must implement this abstract
     * method.
     */
    public abstract String getClassName();

    /**
     * Returns true if the named property is defined.
     *
     * @param name the name of the property
     * @param start the object in which the lookup began
     * @return true if and only if the property was found in the object
     */
    public boolean has(String name, Scriptable start)
    {
        return null != getSlot(name, 0, SLOT_QUERY);
    }

    /**
     * Returns true if the property index is defined.
     *
     * @param index the numeric index for the property
     * @param start the object in which the lookup began
     * @return true if and only if the property was found in the object
     */
    public boolean has(int index, Scriptable start)
    {
        return null != getSlot(null, index, SLOT_QUERY);
    }

    /**
     * Returns the value of the named property or NOT_FOUND.
     *
     * If the property was created using defineProperty, the
     * appropriate getter method is called.
     *
     * @param name the name of the property
     * @param start the object in which the lookup began
     * @return the value of the property (may be null), or NOT_FOUND
     */
    public Object get(String name, Scriptable start)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[95]++;
        Slot slot = getSlot(name, 0, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[96]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[38]++;
            return Scriptable.NOT_FOUND;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[39]++;}
        return slot.getValue(start);
    }

    /**
     * Returns the value of the indexed property or NOT_FOUND.
     *
     * @param index the numeric index for the property
     * @param start the object in which the lookup began
     * @return the value of the property (may be null), or NOT_FOUND
     */
    public Object get(int index, Scriptable start)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[97]++;
        Slot slot = getSlot(null, index, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[40]++;
            return Scriptable.NOT_FOUND;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[41]++;}
        return slot.getValue(start);
    }

    /**
     * Sets the value of the named property, creating it if need be.
     *
     * If the property was created using defineProperty, the
     * appropriate setter method is called. <p>
     *
     * If the property's attributes include READONLY, no action is
     * taken.
     * This method will actually set the property in the start
     * object.
     *
     * @param name the name of the property
     * @param start the object whose property is being set
     * @param value value to set the property to
     */
    public void put(String name, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[99]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((putImpl(name, 0, start, value)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[42]++;
            return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[43]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[100]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[44]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[45]++;}
        start.put(name, start, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[101]++;
    }

    /**
     * Sets the value of the indexed property, creating it if need be.
     *
     * @param index the numeric index for the property
     * @param start the object whose property is being set
     * @param value value to set the property to
     */
    public void put(int index, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[102]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((putImpl(null, index, start, value)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[46]++;
            return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[47]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[103]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[48]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[49]++;}
        start.put(index, start, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[104]++;
    }

    /**
     * Removes a named property from the object.
     *
     * If the property is not found, or it has the PERMANENT attribute,
     * no action is taken.
     *
     * @param name the name of the property
     */
    public void delete(String name)
    {
        checkNotSealed(name, 0);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[105]++;
        removeSlot(name, 0);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[106]++;
    }

    /**
     * Removes the indexed property from the object.
     *
     * If the property is not found, or it has the PERMANENT attribute,
     * no action is taken.
     *
     * @param index the numeric index for the property
     */
    public void delete(int index)
    {
        checkNotSealed(null, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[107]++;
        removeSlot(null, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[108]++;
    }

    /**
     * Sets the value of the named const property, creating it if need be.
     *
     * If the property was created using defineProperty, the
     * appropriate setter method is called. <p>
     *
     * If the property's attributes include READONLY, no action is
     * taken.
     * This method will actually set the property in the start
     * object.
     *
     * @param name the name of the property
     * @param start the object whose property is being set
     * @param value value to set the property to
     */
    public void putConst(String name, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[109]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((putConstImpl(name, 0, start, value, READONLY)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[50]++;
            return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[51]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[110]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[52]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[53]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[111]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((start instanceof ConstProperties) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[54]++;
            ((ConstProperties)start).putConst(name, start, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[112]++;
}
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[55]++;
            start.put(name, start, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[113]++;
}
    }

    public void defineConst(String name, Scriptable start)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[114]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((putConstImpl(name, 0, start, Undefined.instance, UNINITIALIZED_CONST)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[56]++;
            return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[57]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[115]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[58]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[59]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[116]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((start instanceof ConstProperties) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[60]++;
            ((ConstProperties)start).defineConst(name, start);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[117]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[61]++;}
    }
    /**
     * Returns true if the named property is defined as a const on this object.
     * @param name
     * @return true if the named property is defined as a const, false
     * otherwise.
     */
    public boolean isConst(String name)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[118]++;
        Slot slot = getSlot(name, 0, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[119]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[62]++;
            return false;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[63]++;}
        return (slot.getAttributes() & (PERMANENT|READONLY)) ==
                                       (PERMANENT|READONLY);

    }
    /**
     * @deprecated Use {@link #getAttributes(String name)}. The engine always
     * ignored the start argument.
     */
    public final int getAttributes(String name, Scriptable start)
    {
        return getAttributes(name);
    }

    /**
     * @deprecated Use {@link #getAttributes(int index)}. The engine always
     * ignored the start argument.
     */
    public final int getAttributes(int index, Scriptable start)
    {
        return getAttributes(index);
    }

    /**
     * @deprecated Use {@link #setAttributes(String name, int attributes)}.
     * The engine always ignored the start argument.
     */
    public final void setAttributes(String name, Scriptable start,
                                    int attributes)
    {
        setAttributes(name, attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[120]++;
    }

    /**
     * @deprecated Use {@link #setAttributes(int index, int attributes)}.
     * The engine always ignored the start argument.
     */
    public void setAttributes(int index, Scriptable start,
                              int attributes)
    {
        setAttributes(index, attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[121]++;
    }

    /**
     * Get the attributes of a named property.
     *
     * The property is specified by <code>name</code>
     * as defined for <code>has</code>.<p>
     *
     * @param name the identifier for the property
     * @return the bitset of attributes
     * @exception EvaluatorException if the named property is not found
     * @see org.mozilla.javascript.ScriptableObject#has(String, Scriptable)
     * @see org.mozilla.javascript.ScriptableObject#READONLY
     * @see org.mozilla.javascript.ScriptableObject#DONTENUM
     * @see org.mozilla.javascript.ScriptableObject#PERMANENT
     * @see org.mozilla.javascript.ScriptableObject#EMPTY
     */
    public int getAttributes(String name)
    {
        return findAttributeSlot(name, 0, SLOT_QUERY).getAttributes();
    }

    /**
     * Get the attributes of an indexed property.
     *
     * @param index the numeric index for the property
     * @exception EvaluatorException if the named property is not found
     *            is not found
     * @return the bitset of attributes
     * @see org.mozilla.javascript.ScriptableObject#has(String, Scriptable)
     * @see org.mozilla.javascript.ScriptableObject#READONLY
     * @see org.mozilla.javascript.ScriptableObject#DONTENUM
     * @see org.mozilla.javascript.ScriptableObject#PERMANENT
     * @see org.mozilla.javascript.ScriptableObject#EMPTY
     */
    public int getAttributes(int index)
    {
        return findAttributeSlot(null, index, SLOT_QUERY).getAttributes();
    }

    /**
     * Set the attributes of a named property.
     *
     * The property is specified by <code>name</code>
     * as defined for <code>has</code>.<p>
     *
     * The possible attributes are READONLY, DONTENUM,
     * and PERMANENT. Combinations of attributes
     * are expressed by the bitwise OR of attributes.
     * EMPTY is the state of no attributes set. Any unused
     * bits are reserved for future use.
     *
     * @param name the name of the property
     * @param attributes the bitset of attributes
     * @exception EvaluatorException if the named property is not found
     * @see org.mozilla.javascript.Scriptable#has(String, Scriptable)
     * @see org.mozilla.javascript.ScriptableObject#READONLY
     * @see org.mozilla.javascript.ScriptableObject#DONTENUM
     * @see org.mozilla.javascript.ScriptableObject#PERMANENT
     * @see org.mozilla.javascript.ScriptableObject#EMPTY
     */
    public void setAttributes(String name, int attributes)
    {
        checkNotSealed(name, 0);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[122]++;
        findAttributeSlot(name, 0, SLOT_MODIFY).setAttributes(attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[123]++;
    }

    /**
     * Set the attributes of an indexed property.
     *
     * @param index the numeric index for the property
     * @param attributes the bitset of attributes
     * @exception EvaluatorException if the named property is not found
     * @see org.mozilla.javascript.Scriptable#has(String, Scriptable)
     * @see org.mozilla.javascript.ScriptableObject#READONLY
     * @see org.mozilla.javascript.ScriptableObject#DONTENUM
     * @see org.mozilla.javascript.ScriptableObject#PERMANENT
     * @see org.mozilla.javascript.ScriptableObject#EMPTY
     */
    public void setAttributes(int index, int attributes)
    {
        checkNotSealed(null, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[124]++;
        findAttributeSlot(null, index, SLOT_MODIFY).setAttributes(attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[125]++;
    }

    /**
     * XXX: write docs.
     */
    public void setGetterOrSetter(String name, int index,
                                  Callable getterOrSetter, boolean isSetter)
    {
        setGetterOrSetter(name, index, getterOrSetter, isSetter, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[126]++;
    }

    private void setGetterOrSetter(String name, int index, Callable getterOrSetter,
                                   boolean isSetter, boolean force)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[127]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((index != 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[64]++;
            throw new IllegalArgumentException(name);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[65]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[128]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((force) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[66]++;
            checkNotSealed(name, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[129]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[67]++;}

        final GetterSlot gslot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[130]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isExtensible()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[68]++;
            gslot = (GetterSlot)getSlot(name, index, SLOT_MODIFY_GETTER_SETTER);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[131]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[69]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[132]++;
            Slot slot = unwrapSlot(getSlot(name, index, SLOT_QUERY));
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[133]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((slot instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[70]++;
                return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[71]++;}
            gslot = (GetterSlot) slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[134]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[135]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((force) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[72]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[136]++;
            int attributes = gslot.getAttributes();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[137]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 (((attributes & READONLY) != 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[74]++;
                throw Context.reportRuntimeError1("msg.modify.readonly", name);

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[75]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[73]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[138]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((isSetter) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[76]++;
            gslot.setter = getterOrSetter;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[139]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[77]++;
            gslot.getter = getterOrSetter;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[140]++;
        }
        gslot.value = Undefined.instance;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[141]++;
    }

    /**
     * Get the getter or setter for a given property. Used by __lookupGetter__
     * and __lookupSetter__.
     *
     * @param name Name of the object. If nonnull, index must be 0.
     * @param index Index of the object. If nonzero, name must be null.
     * @param isSetter If true, return the setter, otherwise return the getter.
     * @exception IllegalArgumentException if both name and index are nonnull
     *            and nonzero respectively.
     * @return Null if the property does not exist. Otherwise returns either
     *         the getter or the setter for the property, depending on
     *         the value of isSetter (may be undefined if unset).
     */
    public Object getGetterOrSetter(String name, int index, boolean isSetter)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[142]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((index != 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[78]++;
            throw new IllegalArgumentException(name);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[79]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[143]++;
        Slot slot = unwrapSlot(getSlot(name, index, SLOT_QUERY));
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[144]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[80]++;
            return null;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[81]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[145]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((slot instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[82]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[146]++;
            GetterSlot gslot = (GetterSlot)slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[147]++;
            Object result = isSetter ? gslot.setter : gslot.getter;
            return result != null ? result : Undefined.instance;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[83]++;
            return Undefined.instance;
}
    }

    /**
     * Returns whether a property is a getter or a setter
     * @param name property name
     * @param index property index
     * @param setter true to check for a setter, false for a getter
     * @return whether the property is a getter or a setter
     */
    protected boolean isGetterOrSetter(String name, int index, boolean setter) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[148]++;
        Slot slot = unwrapSlot(getSlot(name, index, SLOT_QUERY));
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[149]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((slot instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[84]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[150]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((setter) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((((GetterSlot)slot).setter != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[86]++; return true;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[87]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[151]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((setter) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((((GetterSlot)slot).getter != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[88]++; return true;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[89]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[85]++;}
        return false;
    }

    void addLazilyInitializedValue(String name, int index,
                                   LazilyLoadedCtor init, int attributes)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[152]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((index != 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[90]++;
            throw new IllegalArgumentException(name);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[91]++;}
        checkNotSealed(name, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[153]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[154]++;
        GetterSlot gslot = (GetterSlot)getSlot(name, index,
                                               SLOT_MODIFY_GETTER_SETTER);
        gslot.setAttributes(attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[155]++;
        gslot.getter = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[156]++;
        gslot.setter = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[157]++;
        gslot.value = init;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[158]++;
    }

    /**
     * Returns the prototype of the object.
     */
    public Scriptable getPrototype()
    {
        return prototypeObject;
    }

    /**
     * Sets the prototype of the object.
     */
    public void setPrototype(Scriptable m)
    {
        prototypeObject = m;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[159]++;
    }

    /**
     * Returns the parent (enclosing) scope of the object.
     */
    public Scriptable getParentScope()
    {
        return parentScopeObject;
    }

    /**
     * Sets the parent (enclosing) scope of the object.
     */
    public void setParentScope(Scriptable m)
    {
        parentScopeObject = m;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[160]++;
    }

    /**
     * Returns an array of ids for the properties of the object.
     *
     * <p>Any properties with the attribute DONTENUM are not listed. <p>
     *
     * @return an array of java.lang.Objects with an entry for every
     * listed property. Properties accessed via an integer index will
     * have a corresponding
     * Integer entry in the returned array. Properties accessed by
     * a String will have a String entry in the returned array.
     */
    public Object[] getIds() {
        return getIds(false);
    }

    /**
     * Returns an array of ids for the properties of the object.
     *
     * <p>All properties, even those with attribute DONTENUM, are listed. <p>
     *
     * @return an array of java.lang.Objects with an entry for every
     * listed property. Properties accessed via an integer index will
     * have a corresponding
     * Integer entry in the returned array. Properties accessed by
     * a String will have a String entry in the returned array.
     */
    public Object[] getAllIds() {
        return getIds(true);
    }

    /**
     * Implements the [[DefaultValue]] internal method.
     *
     * <p>Note that the toPrimitive conversion is a no-op for
     * every type other than Object, for which [[DefaultValue]]
     * is called. See ECMA 9.1.<p>
     *
     * A <code>hint</code> of null means "no hint".
     *
     * @param typeHint the type hint
     * @return the default value for the object
     *
     * See ECMA 8.6.2.6.
     */
    public Object getDefaultValue(Class<?> typeHint)
    {
        return getDefaultValue(this, typeHint);
    }

    public static Object getDefaultValue(Scriptable object, Class<?> typeHint)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[161]++;
        Context cx = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[162]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[1]++;


int CodeCoverConditionCoverageHelper_C46;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i < 2) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[1]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[2]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[3]++;
}
            boolean tryToString;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[163]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((typeHint == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[92]++;
                tryToString = (i == 0);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[164]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[93]++;
                tryToString = (i == 1);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[165]++;
            }

            String methodName;
            Object[] args;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[166]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((tryToString) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[94]++;
                methodName = "toString";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[167]++;
                args = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[168]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[95]++;
                methodName = "valueOf";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[169]++;
                args = new Object[1];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[170]++;
                String hint;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[171]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((typeHint == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[96]++;
                    hint = "undefined";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[172]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[97]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[173]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((typeHint == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[98]++;
                    hint = "string";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[174]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[99]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[175]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((typeHint == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[100]++;
                    hint = "object";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[176]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[101]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[177]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((typeHint == ScriptRuntime.FunctionClass) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[102]++;
                    hint = "function";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[178]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[103]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[179]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((typeHint == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((typeHint == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[104]++;
                    hint = "boolean";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[180]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[105]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[181]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2097152)) == 0 || true) &&
 ((typeHint == ScriptRuntime.NumberClass) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1048576)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (524288)) == 0 || true) &&
 ((typeHint == ScriptRuntime.ByteClass) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (262144)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (131072)) == 0 || true) &&
 ((typeHint == Byte.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (65536)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (32768)) == 0 || true) &&
 ((typeHint == ScriptRuntime.ShortClass) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (16384)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (8192)) == 0 || true) &&
 ((typeHint == Short.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4096)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2048)) == 0 || true) &&
 ((typeHint == ScriptRuntime.IntegerClass) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (512)) == 0 || true) &&
 ((typeHint == Integer.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (128)) == 0 || true) &&
 ((typeHint == ScriptRuntime.FloatClass) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (32)) == 0 || true) &&
 ((typeHint == Float.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((typeHint == ScriptRuntime.DoubleClass) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((typeHint == Double.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 11) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 11) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[106]++;
                    hint = "number";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[182]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[107]++;
                    throw Context.reportRuntimeError1(
                        "msg.invalid.type", typeHint.toString());
                }
}
}
}
}
}
                args[0] = hint;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[183]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[184]++;
            Object v = getProperty(object, methodName);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[185]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((v instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[108]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[186]++;
                continue;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[109]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[187]++;
            Function fun = (Function) v;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[188]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[110]++;
                cx = Context.getContext();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[189]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[111]++;}
            v = fun.call(cx, fun.getParentScope(), object, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[190]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[191]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[112]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[192]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((v instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[114]++;
                    return v;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[115]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[193]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((typeHint == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((typeHint == ScriptRuntime.FunctionClass) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[116]++;
                    return v;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[117]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[194]++;
int CodeCoverConditionCoverageHelper_C60;
                if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((tryToString) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((v instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[118]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[195]++;
                    // Let a wrapped java.lang.String pass for a primitive
                    // string.
                    Object u = ((Wrapper)v).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[196]++;
int CodeCoverConditionCoverageHelper_C61;
                    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((u instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[120]++;
                        return u;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[121]++;}

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[119]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[113]++;}
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[197]++;
        // fall through to error
        String arg = (typeHint == null) ? "undefined" : typeHint.getName();
        throw ScriptRuntime.typeError1("msg.default.value", arg);
    }

    /**
     * Implements the instanceof operator.
     *
     * <p>This operator has been proposed to ECMA.
     *
     * @param instance The value that appeared on the LHS of the instanceof
     *              operator
     * @return true if "this" appears in value's prototype chain
     *
     */
    public boolean hasInstance(Scriptable instance) {
        // Default for JS objects (other than Function) is to do prototype
        // chasing.  This will be overridden in NativeFunction and non-JS
        // objects.

        return ScriptRuntime.jsDelegatesTo(instance, this);
    }

    /**
     * Emulate the SpiderMonkey (and Firefox) feature of allowing
     * custom objects to avoid detection by normal "object detection"
     * code patterns. This is used to implement document.all.
     * See https://bugzilla.mozilla.org/show_bug.cgi?id=412247.
     * This is an analog to JOF_DETECTING from SpiderMonkey; see
     * https://bugzilla.mozilla.org/show_bug.cgi?id=248549.
     * Other than this special case, embeddings should return false.
     * @return true if this object should avoid object detection
     * @since 1.7R1
     */
    public boolean avoidObjectDetection() {
        return false;
    }

    /**
     * Custom <tt>==</tt> operator.
     * Must return {@link Scriptable#NOT_FOUND} if this object does not
     * have custom equality operator for the given value,
     * <tt>Boolean.TRUE</tt> if this object is equivalent to <tt>value</tt>,
     * <tt>Boolean.FALSE</tt> if this object is not equivalent to
     * <tt>value</tt>.
     * <p>
     * The default implementation returns Boolean.TRUE
     * if <tt>this == value</tt> or {@link Scriptable#NOT_FOUND} otherwise.
     * It indicates that by default custom equality is available only if
     * <tt>value</tt> is <tt>this</tt> in which case true is returned.
     */
    protected Object equivalentValues(Object value)
    {
        return (this == value) ? Boolean.TRUE : Scriptable.NOT_FOUND;
    }

    /**
     * Defines JavaScript objects from a Java class that implements Scriptable.
     *
     * If the given class has a method
     * <pre>
     * static void init(Context cx, Scriptable scope, boolean sealed);</pre>
     *
     * or its compatibility form
     * <pre>
     * static void init(Scriptable scope);</pre>
     *
     * then it is invoked and no further initialization is done.<p>
     *
     * However, if no such a method is found, then the class's constructors and
     * methods are used to initialize a class in the following manner.<p>
     *
     * First, the zero-parameter constructor of the class is called to
     * create the prototype. If no such constructor exists,
     * a {@link EvaluatorException} is thrown. <p>
     *
     * Next, all methods are scanned for special prefixes that indicate that they
     * have special meaning for defining JavaScript objects.
     * These special prefixes are
     * <ul>
     * <li><code>jsFunction_</code> for a JavaScript function
     * <li><code>jsStaticFunction_</code> for a JavaScript function that
     *           is a property of the constructor
     * <li><code>jsGet_</code> for a getter of a JavaScript property
     * <li><code>jsSet_</code> for a setter of a JavaScript property
     * <li><code>jsConstructor</code> for a JavaScript function that
     *           is the constructor
     * </ul><p>
     *
     * If the method's name begins with "jsFunction_", a JavaScript function
     * is created with a name formed from the rest of the Java method name
     * following "jsFunction_". So a Java method named "jsFunction_foo" will
     * define a JavaScript method "foo". Calling this JavaScript function
     * will cause the Java method to be called. The parameters of the method
     * must be of number and types as defined by the FunctionObject class.
     * The JavaScript function is then added as a property
     * of the prototype. <p>
     *
     * If the method's name begins with "jsStaticFunction_", it is handled
     * similarly except that the resulting JavaScript function is added as a
     * property of the constructor object. The Java method must be static.
     *
     * If the method's name begins with "jsGet_" or "jsSet_", the method is
     * considered to define a property. Accesses to the defined property
     * will result in calls to these getter and setter methods. If no
     * setter is defined, the property is defined as READONLY.<p>
     *
     * If the method's name is "jsConstructor", the method is
     * considered to define the body of the constructor. Only one
     * method of this name may be defined. You may use the varargs forms
     * for constructors documented in {@link FunctionObject#FunctionObject(String, Member, Scriptable)}
     *
     * If no method is found that can serve as constructor, a Java
     * constructor will be selected to serve as the JavaScript
     * constructor in the following manner. If the class has only one
     * Java constructor, that constructor is used to define
     * the JavaScript constructor. If the the class has two constructors,
     * one must be the zero-argument constructor (otherwise an
     * {@link EvaluatorException} would have already been thrown
     * when the prototype was to be created). In this case
     * the Java constructor with one or more parameters will be used
     * to define the JavaScript constructor. If the class has three
     * or more constructors, an {@link EvaluatorException}
     * will be thrown.<p>
     *
     * Finally, if there is a method
     * <pre>
     * static void finishInit(Scriptable scope, FunctionObject constructor,
     *                        Scriptable prototype)</pre>
     *
     * it will be called to finish any initialization. The <code>scope</code>
     * argument will be passed, along with the newly created constructor and
     * the newly created prototype.<p>
     *
     * @param scope The scope in which to define the constructor.
     * @param clazz The Java class to use to define the JavaScript objects
     *              and properties.
     * @exception IllegalAccessException if access is not available
     *            to a reflected class member
     * @exception InstantiationException if unable to instantiate
     *            the named class
     * @exception InvocationTargetException if an exception is thrown
     *            during execution of methods of the named class
     * @see org.mozilla.javascript.Function
     * @see org.mozilla.javascript.FunctionObject
     * @see org.mozilla.javascript.ScriptableObject#READONLY
     * @see org.mozilla.javascript.ScriptableObject
     *      #defineProperty(String, Class, int)
     */
    public static <T extends Scriptable> void defineClass(
            Scriptable scope, Class<T> clazz)
        throws IllegalAccessException, InstantiationException,
               InvocationTargetException
    {
        defineClass(scope, clazz, false, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[198]++;
    }

    /**
     * Defines JavaScript objects from a Java class, optionally
     * allowing sealing.
     *
     * Similar to <code>defineClass(Scriptable scope, Class clazz)</code>
     * except that sealing is allowed. An object that is sealed cannot have
     * properties added or removed. Note that sealing is not allowed in
     * the current ECMA/ISO language specification, but is likely for
     * the next version.
     *
     * @param scope The scope in which to define the constructor.
     * @param clazz The Java class to use to define the JavaScript objects
     *              and properties. The class must implement Scriptable.
     * @param sealed Whether or not to create sealed standard objects that
     *               cannot be modified.
     * @exception IllegalAccessException if access is not available
     *            to a reflected class member
     * @exception InstantiationException if unable to instantiate
     *            the named class
     * @exception InvocationTargetException if an exception is thrown
     *            during execution of methods of the named class
     * @since 1.4R3
     */
    public static <T extends Scriptable> void defineClass(
            Scriptable scope, Class<T> clazz, boolean sealed)
        throws IllegalAccessException, InstantiationException,
               InvocationTargetException
    {
        defineClass(scope, clazz, sealed, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[199]++;
    }

    /**
     * Defines JavaScript objects from a Java class, optionally
     * allowing sealing and mapping of Java inheritance to JavaScript
     * prototype-based inheritance.
     *
     * Similar to <code>defineClass(Scriptable scope, Class clazz)</code>
     * except that sealing and inheritance mapping are allowed. An object
     * that is sealed cannot have properties added or removed. Note that
     * sealing is not allowed in the current ECMA/ISO language specification,
     * but is likely for the next version.
     *
     * @param scope The scope in which to define the constructor.
     * @param clazz The Java class to use to define the JavaScript objects
     *              and properties. The class must implement Scriptable.
     * @param sealed Whether or not to create sealed standard objects that
     *               cannot be modified.
     * @param mapInheritance Whether or not to map Java inheritance to
     *                       JavaScript prototype-based inheritance.
     * @return the class name for the prototype of the specified class
     * @exception IllegalAccessException if access is not available
     *            to a reflected class member
     * @exception InstantiationException if unable to instantiate
     *            the named class
     * @exception InvocationTargetException if an exception is thrown
     *            during execution of methods of the named class
     * @since 1.6R2
     */
    public static <T extends Scriptable> String defineClass(
            Scriptable scope, Class<T> clazz, boolean sealed,
            boolean mapInheritance)
        throws IllegalAccessException, InstantiationException,
               InvocationTargetException
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[200]++;
        BaseFunction ctor = buildClassCtor(scope, clazz, sealed,
                                           mapInheritance);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[201]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((ctor == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[122]++;
            return null;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[123]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[202]++;
        String name = ctor.getClassPrototype().getClassName();
        defineProperty(scope, name, ctor, ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[203]++;
        return name;
    }

    static <T extends Scriptable> BaseFunction buildClassCtor(
            Scriptable scope, Class<T> clazz,
            boolean sealed,
            boolean mapInheritance)
        throws IllegalAccessException, InstantiationException,
               InvocationTargetException
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[204]++;
        Method[] methods = FunctionObject.getMethodList(clazz);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[205]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[4]++;


int CodeCoverConditionCoverageHelper_C63;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((i < methods.length) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[4]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[5]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[6]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[206]++;
            Method method = methods[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[207]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((method.getName().equals("init")) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[124]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[208]++;
                continue;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[125]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[209]++;
            Class<?>[] parmTypes = method.getParameterTypes();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[210]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (512)) == 0 || true) &&
 ((parmTypes.length == 3) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (128)) == 0 || true) &&
 ((parmTypes[0] == ScriptRuntime.ContextClass) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (32)) == 0 || true) &&
 ((parmTypes[1] == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((parmTypes[2] == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(method.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 5) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 5) && false))
            {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[126]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[211]++;
                Object args[] = { Context.getContext(), scope,
                                  sealed ? Boolean.TRUE : Boolean.FALSE };
                method.invoke(null, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[212]++;
                return null;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[127]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[213]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (32)) == 0 || true) &&
 ((parmTypes.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((parmTypes[0] == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(method.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 3) && false))
            {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[128]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[214]++;
                Object args[] = { scope };
                method.invoke(null, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[215]++;
                return null;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[129]++;}

        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[216]++;

        // If we got here, there isn't an "init" method with the right
        // parameter types.

        Constructor<?>[] ctors = clazz.getConstructors();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[217]++;
        Constructor<?> protoCtor = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[218]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[7]++;


int CodeCoverConditionCoverageHelper_C67;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((i < ctors.length) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[7]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[8]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[9]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[219]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((ctors[i].getParameterTypes().length == 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[130]++;
                protoCtor = ctors[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[220]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[221]++;
                break;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[131]++;}
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[222]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((protoCtor == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[132]++;
            throw Context.reportRuntimeError1(
                      "msg.zero.arg.ctor", clazz.getName());

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[133]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[223]++;

        Scriptable proto = (Scriptable) protoCtor.newInstance(ScriptRuntime.emptyArgs);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[224]++;
        String className = proto.getClassName();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[225]++;

        // check for possible redefinition
        Object existing = getProperty(getTopLevelScope(scope), className);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[226]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((existing instanceof BaseFunction) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[134]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[227]++;
            Object existingProto = ((BaseFunction)existing).getPrototypeProperty();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[228]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((existingProto != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((clazz.equals(existingProto.getClass())) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[136]++;
                return (BaseFunction)existing;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[137]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[135]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[229]++;

        // Set the prototype's prototype, trying to map Java inheritance to JS
        // prototype-based inheritance if requested to do so.
        Scriptable superProto = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[230]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((mapInheritance) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[138]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[231]++;
            Class<? super T> superClass = clazz.getSuperclass();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[232]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((ScriptRuntime.ScriptableClass.isAssignableFrom(superClass)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((Modifier.isAbstract(superClass.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) && false))
            {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[140]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[233]++;
                Class<? extends Scriptable> superScriptable =
                    extendsScriptable(superClass);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[234]++;
                String name = ScriptableObject.defineClass(scope,
                        superScriptable, sealed, mapInheritance);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[235]++;
int CodeCoverConditionCoverageHelper_C74;
                if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[142]++;
                    superProto = ScriptableObject.getClassPrototype(scope, name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[236]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[143]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[141]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[139]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[237]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((superProto == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[144]++;
            superProto = ScriptableObject.getObjectPrototype(scope);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[238]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[145]++;}
        proto.setPrototype(superProto);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[239]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[240]++;

        // Find out whether there are any methods that begin with
        // "js". If so, then only methods that begin with special
        // prefixes will be defined as JavaScript entities.
        final String functionPrefix = "jsFunction_";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[241]++;
        final String staticFunctionPrefix = "jsStaticFunction_";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[242]++;
        final String getterPrefix = "jsGet_";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[243]++;
        final String setterPrefix = "jsSet_";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[244]++;
        final String ctorName = "jsConstructor";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[245]++;

        Member ctorMember = findAnnotatedMember(methods, JSConstructor.class);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[246]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((ctorMember == null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[146]++;
            ctorMember = findAnnotatedMember(ctors, JSConstructor.class);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[247]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[147]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[248]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((ctorMember == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[148]++;
            ctorMember = FunctionObject.findSingleMethod(methods, ctorName);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[249]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[149]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[250]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((ctorMember == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[150]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[251]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((ctors.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[152]++;
                ctorMember = ctors[0];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[252]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[153]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[253]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((ctors.length == 2) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[154]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[254]++;
int CodeCoverConditionCoverageHelper_C81;
                if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((ctors[0].getParameterTypes().length == 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[156]++;
                    ctorMember = ctors[1];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[255]++;
}
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[157]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[256]++;
int CodeCoverConditionCoverageHelper_C82; if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((ctors[1].getParameterTypes().length == 0) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[158]++;
                    ctorMember = ctors[0];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[257]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[159]++;}
}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[155]++;}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[258]++;
int CodeCoverConditionCoverageHelper_C83;
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((ctorMember == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[160]++;
                throw Context.reportRuntimeError1(
                          "msg.ctor.multiple.parms", clazz.getName());

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[161]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[151]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[259]++;

        FunctionObject ctor = new FunctionObject(className, ctorMember, scope);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[260]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((ctor.isVarArgsMethod()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[162]++;
            throw Context.reportRuntimeError1
                ("msg.varargs.ctor", ctorMember.getName());

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[163]++;}
        ctor.initAsConstructor(scope, proto);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[261]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[262]++;

        Method finishInit = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[263]++;
        HashSet<String> staticNames = new HashSet<String>(),
                        instanceNames = new HashSet<String>();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[264]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[10]++;


        for (Method method : methods) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[10]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[11]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[12]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[265]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((method == ctorMember) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[164]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[266]++;
                continue;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[165]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[267]++;
            String name = method.getName();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[268]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((name.equals("finishInit")) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[166]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[269]++;
                Class<?>[] parmTypes = method.getParameterTypes();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[270]++;
int CodeCoverConditionCoverageHelper_C87;
                if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (512)) == 0 || true) &&
 ((parmTypes.length == 3) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (128)) == 0 || true) &&
 ((parmTypes[0] == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (32)) == 0 || true) &&
 ((parmTypes[1] == FunctionObject.class) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((parmTypes[2] == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(method.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 5) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 5) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[168]++;
                    finishInit = method;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[271]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[272]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[169]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[167]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[273]++;
int CodeCoverConditionCoverageHelper_C88;
            // ignore any compiler generated methods.
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((name.indexOf('$') != -1) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[170]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[274]++;
                continue;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[171]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[275]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((name.equals(ctorName)) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[172]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[276]++;
                continue;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[173]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[277]++;

            Annotation annotation = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[278]++;
            String prefix = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[279]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((method.isAnnotationPresent(JSFunction.class)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[174]++;
                annotation = method.getAnnotation(JSFunction.class);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[280]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[175]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[281]++;
int CodeCoverConditionCoverageHelper_C91; if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((method.isAnnotationPresent(JSStaticFunction.class)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[176]++;
                annotation = method.getAnnotation(JSStaticFunction.class);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[282]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[177]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[283]++;
int CodeCoverConditionCoverageHelper_C92; if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((method.isAnnotationPresent(JSGetter.class)) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[178]++;
                annotation = method.getAnnotation(JSGetter.class);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[284]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[179]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[285]++;
int CodeCoverConditionCoverageHelper_C93; if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((method.isAnnotationPresent(JSSetter.class)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[180]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[286]++;
                continue;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[181]++;}
}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[287]++;
int CodeCoverConditionCoverageHelper_C94;

            if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[182]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[288]++;
int CodeCoverConditionCoverageHelper_C95;
                if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((name.startsWith(functionPrefix)) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[184]++;
                    prefix = functionPrefix;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[289]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[185]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[290]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((name.startsWith(staticFunctionPrefix)) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[186]++;
                    prefix = staticFunctionPrefix;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[291]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[187]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[292]++;
int CodeCoverConditionCoverageHelper_C97; if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((name.startsWith(getterPrefix)) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[188]++;
                    prefix = getterPrefix;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[293]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[189]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[294]++;
int CodeCoverConditionCoverageHelper_C98; if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[190]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[295]++;
                    // note that setterPrefix is among the unhandled names here -
                    // we deal with that when we see the getter
                    continue;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[191]++;}
}
}
}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[183]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[296]++;

            boolean isStatic = annotation instanceof JSStaticFunction
                    || prefix == staticFunctionPrefix;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[297]++;
            HashSet<String> names = isStatic ? staticNames : instanceNames;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[298]++;
            String propName = getPropertyName(name, prefix, annotation);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[299]++;
int CodeCoverConditionCoverageHelper_C99;
            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((names.contains(propName)) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[192]++;
                throw Context.reportRuntimeError2("duplicate.defineClass.name",
                        name, propName);

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[193]++;}
            names.add(propName);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[300]++;
            name = propName;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[301]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[302]++;
int CodeCoverConditionCoverageHelper_C100;

            if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (8)) == 0 || true) &&
 ((annotation instanceof JSGetter) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((prefix == getterPrefix) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[194]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[303]++;
int CodeCoverConditionCoverageHelper_C101;
                if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((proto instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[196]++;
                    throw Context.reportRuntimeError2(
                        "msg.extend.scriptable",
                        proto.getClass().toString(), name);

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[197]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[304]++;
                Method setter = findSetterMethod(methods, name, setterPrefix);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[305]++;
                int attr = ScriptableObject.PERMANENT |
                           ScriptableObject.DONTENUM  |
                           (setter != null ? 0
                                           : ScriptableObject.READONLY);
                ((ScriptableObject) proto).defineProperty(name, null,
                                                          method, setter,
                                                          attr);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[306]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[307]++;
                continue;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[195]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[308]++;
int CodeCoverConditionCoverageHelper_C102;

            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (8)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(method.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[198]++;
                throw Context.reportRuntimeError(
                        "jsStaticFunction must be used with static method.");

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[199]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[309]++;

            FunctionObject f = new FunctionObject(name, method, proto);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[310]++;
int CodeCoverConditionCoverageHelper_C103;
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((f.isVarArgsConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[200]++;
                throw Context.reportRuntimeError1
                    ("msg.varargs.fun", ctorMember.getName());

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[201]++;}
            defineProperty(isStatic ? ctor : proto, name, f, DONTENUM);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[311]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[312]++;
int CodeCoverConditionCoverageHelper_C104;
            if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[202]++;
                f.sealObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[313]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[203]++;}
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[314]++;
int CodeCoverConditionCoverageHelper_C105;

        // Call user code to complete initialization if necessary.
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((finishInit != null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[204]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[315]++;
            Object[] finishArgs = { scope, ctor, proto };
            finishInit.invoke(null, finishArgs);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[316]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[205]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[317]++;
int CodeCoverConditionCoverageHelper_C106;

        // Seal the object if necessary.
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[206]++;
            ctor.sealObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[318]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[319]++;
int CodeCoverConditionCoverageHelper_C107;
            if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((proto instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[208]++;
                ((ScriptableObject) proto).sealObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[320]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[209]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[207]++;}

        return ctor;
    }

    private static Member findAnnotatedMember(AccessibleObject[] members,
                                              Class<? extends Annotation> annotation) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[321]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[13]++;


        for (AccessibleObject member : members) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[13]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[14]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[15]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[322]++;
int CodeCoverConditionCoverageHelper_C108;
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((member.isAnnotationPresent(annotation)) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[210]++;
                return (Member) member;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[211]++;}
        }
        return null;
    }

    private static Method findSetterMethod(Method[] methods,
                                           String name,
                                           String prefix) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[323]++;
        String newStyleName = "set"
                + Character.toUpperCase(name.charAt(0))
                + name.substring(1);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[324]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[16]++;


        for (Method method : methods) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[16]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[17]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[18]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[325]++;
            JSSetter annotation = method.getAnnotation(JSSetter.class);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[326]++;
int CodeCoverConditionCoverageHelper_C109;
            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((annotation != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[212]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[327]++;
int CodeCoverConditionCoverageHelper_C110;
                if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (32)) == 0 || true) &&
 ((name.equals(annotation.value())) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 (("".equals(annotation.value())) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((newStyleName.equals(method.getName())) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[214]++;
                    return method;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[215]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[213]++;}
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[328]++;
        String oldStyleName = prefix + name;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[329]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[19]++;


        for (Method method : methods) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[19]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[20]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[21]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[330]++;
int CodeCoverConditionCoverageHelper_C111;
            if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((oldStyleName.equals(method.getName())) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[216]++;
                return method;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[217]++;}
        }
        return null;
    }

    private static String getPropertyName(String methodName,
                                          String prefix,
                                          Annotation annotation) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[331]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((prefix != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[218]++;
            return methodName.substring(prefix.length());

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[219]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[332]++;
        String propName = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[333]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((annotation instanceof JSGetter) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[220]++;
            propName = ((JSGetter) annotation).value();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[334]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[335]++;
int CodeCoverConditionCoverageHelper_C114;
            if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (8)) == 0 || true) &&
 ((propName == null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((propName.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[222]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[336]++;
int CodeCoverConditionCoverageHelper_C115;
                if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (8)) == 0 || true) &&
 ((methodName.length() > 3) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((methodName.startsWith("get")) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[224]++;
                    propName = methodName.substring(3);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[337]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[338]++;
int CodeCoverConditionCoverageHelper_C116;
                    if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((Character.isUpperCase(propName.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[226]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[339]++;
int CodeCoverConditionCoverageHelper_C117;
                        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((propName.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[228]++;
                            propName = propName.toLowerCase();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[340]++;

                        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[229]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[341]++;
int CodeCoverConditionCoverageHelper_C118; if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((Character.isUpperCase(propName.charAt(1))) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)){
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[230]++;
                            propName = Character.toLowerCase(propName.charAt(0))
                                    + propName.substring(1);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[342]++;

                        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[231]++;}
}

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[227]++;}

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[225]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[223]++;}

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[221]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[343]++;
int CodeCoverConditionCoverageHelper_C119; if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((annotation instanceof JSFunction) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[232]++;
            propName = ((JSFunction) annotation).value();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[344]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[233]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[345]++;
int CodeCoverConditionCoverageHelper_C120; if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((annotation instanceof JSStaticFunction) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[234]++;
            propName = ((JSStaticFunction) annotation).value();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[346]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[235]++;}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[347]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (8)) == 0 || true) &&
 ((propName == null) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((propName.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[236]++;
            propName = methodName;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[348]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[237]++;}
        return propName;
    }

    @SuppressWarnings({"unchecked"})
    private static <T extends Scriptable> Class<T> extendsScriptable(Class<?> c)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[349]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((ScriptRuntime.ScriptableClass.isAssignableFrom(c)) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[238]++;
            return (Class<T>) c;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[239]++;}
        return null;
    }

    /**
     * Define a JavaScript property.
     *
     * Creates the property with an initial value and sets its attributes.
     *
     * @param propertyName the name of the property to define.
     * @param value the initial value of the property
     * @param attributes the attributes of the JavaScript property
     * @see org.mozilla.javascript.Scriptable#put(String, Scriptable, Object)
     */
    public void defineProperty(String propertyName, Object value,
                               int attributes)
    {
        checkNotSealed(propertyName, 0);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[350]++;
        put(propertyName, this, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[351]++;
        setAttributes(propertyName, attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[352]++;
    }

    /**
     * Utility method to add properties to arbitrary Scriptable object.
     * If destination is instance of ScriptableObject, calls
     * defineProperty there, otherwise calls put in destination
     * ignoring attributes
     */
    public static void defineProperty(Scriptable destination,
                                      String propertyName, Object value,
                                      int attributes)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[353]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((destination instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[240]++;
            destination.put(propertyName, destination, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[354]++;
            return;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[241]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[355]++;
        ScriptableObject so = (ScriptableObject)destination;
        so.defineProperty(propertyName, value, attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[356]++;
    }

    /**
     * Utility method to add properties to arbitrary Scriptable object.
     * If destination is instance of ScriptableObject, calls
     * defineProperty there, otherwise calls put in destination
     * ignoring attributes
     */
    public static void defineConstProperty(Scriptable destination,
                                           String propertyName)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[357]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((destination instanceof ConstProperties) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[242]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[358]++;
            ConstProperties cp = (ConstProperties)destination;
            cp.defineConst(propertyName, destination);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[359]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[243]++;
            defineProperty(destination, propertyName, Undefined.instance, CONST);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[360]++;
}
    }

    /**
     * Define a JavaScript property with getter and setter side effects.
     *
     * If the setter is not found, the attribute READONLY is added to
     * the given attributes. <p>
     *
     * The getter must be a method with zero parameters, and the setter, if
     * found, must be a method with one parameter.<p>
     *
     * @param propertyName the name of the property to define. This name
     *                    also affects the name of the setter and getter
     *                    to search for. If the propertyId is "foo", then
     *                    <code>clazz</code> will be searched for "getFoo"
     *                    and "setFoo" methods.
     * @param clazz the Java class to search for the getter and setter
     * @param attributes the attributes of the JavaScript property
     * @see org.mozilla.javascript.Scriptable#put(String, Scriptable, Object)
     */
    public void defineProperty(String propertyName, Class<?> clazz,
                               int attributes)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[361]++;
        int length = propertyName.length();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[362]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[244]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[245]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[363]++;
        char[] buf = new char[3 + length];
        propertyName.getChars(0, length, buf, 3);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[364]++;
        buf[3] = Character.toUpperCase(buf[3]);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[365]++;
        buf[0] = 'g';
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[366]++;
        buf[1] = 'e';
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[367]++;
        buf[2] = 't';
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[368]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[369]++;
        String getterName = new String(buf);
        buf[0] = 's';
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[370]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[371]++;
        String setterName = new String(buf);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[372]++;

        Method[] methods = FunctionObject.getMethodList(clazz);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[373]++;
        Method getter = FunctionObject.findSingleMethod(methods, getterName);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[374]++;
        Method setter = FunctionObject.findSingleMethod(methods, setterName);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[375]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((setter == null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[246]++;
            attributes |= ScriptableObject.READONLY;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[376]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[247]++;}
        defineProperty(propertyName, null, getter,
                       setter == null ? null : setter, attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[377]++;
    }

    /**
     * Define a JavaScript property.
     *
     * Use this method only if you wish to define getters and setters for
     * a given property in a ScriptableObject. To create a property without
     * special getter or setter side effects, use
     * <code>defineProperty(String,int)</code>.
     *
     * If <code>setter</code> is null, the attribute READONLY is added to
     * the given attributes.<p>
     *
     * Several forms of getters or setters are allowed. In all cases the
     * type of the value parameter can be any one of the following types:
     * Object, String, boolean, Scriptable, byte, short, int, long, float,
     * or double. The runtime will perform appropriate conversions based
     * upon the type of the parameter (see description in FunctionObject).
     * The first forms are nonstatic methods of the class referred to
     * by 'this':
     * <pre>
     * Object getFoo();
     * void setFoo(SomeType value);</pre>
     * Next are static methods that may be of any class; the object whose
     * property is being accessed is passed in as an extra argument:
     * <pre>
     * static Object getFoo(Scriptable obj);
     * static void setFoo(Scriptable obj, SomeType value);</pre>
     * Finally, it is possible to delegate to another object entirely using
     * the <code>delegateTo</code> parameter. In this case the methods are
     * nonstatic methods of the class delegated to, and the object whose
     * property is being accessed is passed in as an extra argument:
     * <pre>
     * Object getFoo(Scriptable obj);
     * void setFoo(Scriptable obj, SomeType value);</pre>
     *
     * @param propertyName the name of the property to define.
     * @param delegateTo an object to call the getter and setter methods on,
     *                   or null, depending on the form used above.
     * @param getter the method to invoke to get the value of the property
     * @param setter the method to invoke to set the value of the property
     * @param attributes the attributes of the JavaScript property
     */
    public void defineProperty(String propertyName, Object delegateTo,
                               Method getter, Method setter, int attributes)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[378]++;
        MemberBox getterBox = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[379]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((getter != null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[248]++;
            getterBox = new MemberBox(getter);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[380]++;

            boolean delegatedForm;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[381]++;
int CodeCoverConditionCoverageHelper_C128;
            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(getter.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[250]++;
                delegatedForm = (delegateTo != null);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[382]++;
                getterBox.delegateTo = delegateTo;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[383]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[251]++;
                delegatedForm = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[384]++;
                // Ignore delegateTo for static getter but store
                // non-null delegateTo indicator.
                getterBox.delegateTo = Void.TYPE;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[385]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[386]++;

            String errorId = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[387]++;
            Class<?>[] parmTypes = getter.getParameterTypes();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[388]++;
int CodeCoverConditionCoverageHelper_C129;
            if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((parmTypes.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[252]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[389]++;
int CodeCoverConditionCoverageHelper_C130;
                if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((delegatedForm) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[254]++;
                    errorId = "msg.obj.getter.parms";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[390]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[255]++;}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[253]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[391]++;
int CodeCoverConditionCoverageHelper_C131; if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((parmTypes.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[256]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[392]++;
                Object argType = parmTypes[0];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[393]++;
int CodeCoverConditionCoverageHelper_C132;
                // Allow ScriptableObject for compatibility
                if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C132 |= (8)) == 0 || true) &&
 ((argType == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((argType == ScriptRuntime.ScriptableObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 2) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[258]++;
                    errorId = "msg.bad.getter.parms";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[394]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[259]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[395]++;
int CodeCoverConditionCoverageHelper_C133; if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((delegatedForm) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[260]++;
                    errorId = "msg.bad.getter.parms";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[396]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[261]++;}
}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[257]++;
                errorId = "msg.bad.getter.parms";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[397]++;
            }
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[398]++;
int CodeCoverConditionCoverageHelper_C134;
            if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((errorId != null) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[262]++;
                throw Context.reportRuntimeError1(errorId, getter.toString());

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[263]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[249]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[399]++;

        MemberBox setterBox = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[400]++;
int CodeCoverConditionCoverageHelper_C135;
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((setter != null) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[264]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[401]++;
int CodeCoverConditionCoverageHelper_C136;
            if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((setter.getReturnType() != Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[266]++;
                throw Context.reportRuntimeError1("msg.setter.return",
                                                  setter.toString());
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[267]++;}

            setterBox = new MemberBox(setter);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[402]++;

            boolean delegatedForm;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[403]++;
int CodeCoverConditionCoverageHelper_C137;
            if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(setter.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[268]++;
                delegatedForm = (delegateTo != null);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[404]++;
                setterBox.delegateTo = delegateTo;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[405]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[269]++;
                delegatedForm = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[406]++;
                // Ignore delegateTo for static setter but store
                // non-null delegateTo indicator.
                setterBox.delegateTo = Void.TYPE;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[407]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[408]++;

            String errorId = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[409]++;
            Class<?>[] parmTypes = setter.getParameterTypes();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[410]++;
int CodeCoverConditionCoverageHelper_C138;
            if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((parmTypes.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[270]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[411]++;
int CodeCoverConditionCoverageHelper_C139;
                if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((delegatedForm) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[272]++;
                    errorId = "msg.setter2.expected";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[412]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[273]++;}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[271]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[413]++;
int CodeCoverConditionCoverageHelper_C140; if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((parmTypes.length == 2) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[274]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[414]++;
                Object argType = parmTypes[0];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[415]++;
int CodeCoverConditionCoverageHelper_C141;
                // Allow ScriptableObject for compatibility
                if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((argType == ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((argType == ScriptRuntime.ScriptableObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[276]++;
                    errorId = "msg.setter2.parms";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[416]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[277]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[417]++;
int CodeCoverConditionCoverageHelper_C142; if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((delegatedForm) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[278]++;
                    errorId = "msg.setter1.parms";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[418]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[279]++;}
}

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[275]++;
                errorId = "msg.setter.parms";
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[419]++;
            }
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[420]++;
int CodeCoverConditionCoverageHelper_C143;
            if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((errorId != null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[280]++;
                throw Context.reportRuntimeError1(errorId, setter.toString());

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[281]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[265]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[421]++;

        GetterSlot gslot = (GetterSlot)getSlot(propertyName, 0,
                                               SLOT_MODIFY_GETTER_SETTER);
        gslot.setAttributes(attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[422]++;
        gslot.getter = getterBox;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[423]++;
        gslot.setter = setterBox;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[424]++;
    }

    /**
     * Defines one or more properties on this object.
     *
     * @param cx the current Context
     * @param props a map of property ids to property descriptors
     */
    public void defineOwnProperties(Context cx, ScriptableObject props) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[425]++;
        Object[] ids = props.getIds();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[426]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[22]++;


        for (Object id : ids) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[22]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[23]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[24]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[427]++;
            Object descObj = props.get(id);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[428]++;
            ScriptableObject desc = ensureScriptableObject(descObj);
            checkPropertyDefinition(desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[429]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[430]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[25]++;


        for (Object id : ids) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[25]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[26]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[27]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[431]++;
            ScriptableObject desc = (ScriptableObject)props.get(id);
            defineOwnProperty(cx, id, desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[432]++;
        }
    }

    /**
     * Defines a property on an object.
     *
     * @param cx the current Context
     * @param id the name/index of the property
     * @param desc the new property descriptor, as described in 8.6.1
     */
    public void defineOwnProperty(Context cx, Object id, ScriptableObject desc) {
        checkPropertyDefinition(desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[433]++;
        defineOwnProperty(cx, id, desc, true);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[434]++;
    }

    /**
     * Defines a property on an object.
     *
     * Based on [[DefineOwnProperty]] from 8.12.10 of the spec.
     *
     * @param cx the current Context
     * @param id the name/index of the property
     * @param desc the new property descriptor, as described in 8.6.1
     * @param checkValid whether to perform validity checks
     */
    protected void defineOwnProperty(Context cx, Object id, ScriptableObject desc,
                                     boolean checkValid) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[435]++;

        Slot slot = getSlot(cx, id, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[436]++;
        boolean isNew = slot == null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[437]++;
int CodeCoverConditionCoverageHelper_C144;

        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((checkValid) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[282]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[438]++;
            ScriptableObject current = slot == null ?
                    null : slot.getPropertyDescriptor(cx, this);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[439]++;
            String name = ScriptRuntime.toString(id);
            checkPropertyChange(name, current, desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[440]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[283]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[441]++;

        boolean isAccessor = isAccessorDescriptor(desc);
        final int attributes;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[442]++;
int CodeCoverConditionCoverageHelper_C145;

        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[284]++; // new slot
            slot = getSlot(cx, id, isAccessor ? SLOT_MODIFY_GETTER_SETTER : SLOT_MODIFY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[443]++;
            attributes = applyDescriptorToAttributeBitset(DONTENUM|READONLY|PERMANENT, desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[444]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[285]++;
            attributes = applyDescriptorToAttributeBitset(slot.getAttributes(), desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[445]++;
        }

        slot = unwrapSlot(slot);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[446]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[447]++;
int CodeCoverConditionCoverageHelper_C146;

        if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((isAccessor) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[286]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[448]++;
int CodeCoverConditionCoverageHelper_C147;
            if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((slot instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false) ) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[288]++;
                slot = getSlot(cx, id, SLOT_MODIFY_GETTER_SETTER);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[449]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[289]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[450]++;

            GetterSlot gslot = (GetterSlot) slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[451]++;

            Object getter = getProperty(desc, "get");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[452]++;
int CodeCoverConditionCoverageHelper_C148;
            if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((getter != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[290]++;
                gslot.getter = getter;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[453]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[291]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[454]++;
            Object setter = getProperty(desc, "set");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[455]++;
int CodeCoverConditionCoverageHelper_C149;
            if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((setter != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[292]++;
                gslot.setter = setter;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[456]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[293]++;}

            gslot.value = Undefined.instance;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[457]++;
            gslot.setAttributes(attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[458]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[287]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[459]++;
int CodeCoverConditionCoverageHelper_C150;
            if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (8)) == 0 || true) &&
 ((slot instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((isDataDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[294]++;
                slot = getSlot(cx, id, SLOT_CONVERT_ACCESSOR_TO_DATA);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[460]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[295]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[461]++;

            Object value = getProperty(desc, "value");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[462]++;
int CodeCoverConditionCoverageHelper_C151;
            if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((value != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[296]++;
                slot.value = value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[463]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[297]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[464]++;
int CodeCoverConditionCoverageHelper_C152; if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((isNew) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[298]++;
                slot.value = Undefined.instance;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[465]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[299]++;}
}
            slot.setAttributes(attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[466]++;
        }
    }

    protected void checkPropertyDefinition(ScriptableObject desc) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[467]++;
        Object getter = getProperty(desc, "get");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[468]++;
int CodeCoverConditionCoverageHelper_C153;
        if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (32)) == 0 || true) &&
 ((getter != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C153 |= (8)) == 0 || true) &&
 ((getter != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((getter instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[300]++;
            throw ScriptRuntime.notFunctionError(getter);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[301]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[469]++;
        Object setter = getProperty(desc, "set");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[470]++;
int CodeCoverConditionCoverageHelper_C154;
        if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (32)) == 0 || true) &&
 ((setter != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C154 |= (8)) == 0 || true) &&
 ((setter != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((setter instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[302]++;
            throw ScriptRuntime.notFunctionError(setter);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[303]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[471]++;
int CodeCoverConditionCoverageHelper_C155;
        if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (8)) == 0 || true) &&
 ((isDataDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((isAccessorDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[304]++;
            throw ScriptRuntime.typeError0("msg.both.data.and.accessor.desc");

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[305]++;}
    }

    protected void checkPropertyChange(String id, ScriptableObject current,
                                       ScriptableObject desc) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[472]++;
int CodeCoverConditionCoverageHelper_C156;
        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((current == null) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[306]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[473]++;
int CodeCoverConditionCoverageHelper_C157; // new property
            if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((isExtensible()) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[308]++; throw ScriptRuntime.typeError0("msg.not.extensible");
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[309]++;}

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[307]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[474]++;
int CodeCoverConditionCoverageHelper_C158;
            if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((isFalse(current.get("configurable", current))) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[310]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[475]++;
int CodeCoverConditionCoverageHelper_C159;
                if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((isTrue(getProperty(desc, "configurable"))) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[312]++;
                    throw ScriptRuntime.typeError1(
                        "msg.change.configurable.false.to.true", id);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[313]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[476]++;
int CodeCoverConditionCoverageHelper_C160;
                if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((isTrue(current.get("enumerable", current)) != isTrue(getProperty(desc, "enumerable"))) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[314]++;
                    throw ScriptRuntime.typeError1(
                        "msg.change.enumerable.with.configurable.false", id);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[315]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[477]++;
                boolean isData = isDataDescriptor(desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[478]++;
                boolean isAccessor = isAccessorDescriptor(desc);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[479]++;
int CodeCoverConditionCoverageHelper_C161;
                if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C161 |= (8)) == 0 || true) &&
 ((isData) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((isAccessor) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[316]++;

                    // no further validation required for generic descriptor
                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[317]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[480]++;
int CodeCoverConditionCoverageHelper_C162; if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (8)) == 0 || true) &&
 ((isData) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((isDataDescriptor(current)) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[318]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[481]++;
int CodeCoverConditionCoverageHelper_C163;
                    if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((isFalse(current.get("writable", current))) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[320]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[482]++;
int CodeCoverConditionCoverageHelper_C164;
                        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((isTrue(getProperty(desc, "writable"))) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[322]++;
                            throw ScriptRuntime.typeError1(
                                "msg.change.writable.false.to.true.with.configurable.false", id);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[323]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[483]++;
int CodeCoverConditionCoverageHelper_C165;

                        if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((sameValue(getProperty(desc, "value"), current.get("value", current))) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[324]++;
                            throw ScriptRuntime.typeError1(
                                "msg.change.value.with.writable.false", id);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[325]++;}

                    } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[321]++;}

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[319]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[484]++;
int CodeCoverConditionCoverageHelper_C166; if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (8)) == 0 || true) &&
 ((isAccessor) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((isAccessorDescriptor(current)) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[326]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[485]++;
int CodeCoverConditionCoverageHelper_C167;
                    if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((sameValue(getProperty(desc, "set"), current.get("set", current))) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[328]++;
                        throw ScriptRuntime.typeError1(
                            "msg.change.setter.with.configurable.false", id);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[329]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[486]++;
int CodeCoverConditionCoverageHelper_C168;

                    if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((sameValue(getProperty(desc, "get"), current.get("get", current))) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[330]++;
                        throw ScriptRuntime.typeError1(
                            "msg.change.getter.with.configurable.false", id);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[331]++;}

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[327]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[487]++;
int CodeCoverConditionCoverageHelper_C169;
                    if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((isDataDescriptor(current)) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[332]++;
                        throw ScriptRuntime.typeError1(
                            "msg.change.property.data.to.accessor.with.configurable.false", id);
}
                    else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[333]++;
                        throw ScriptRuntime.typeError1(
                            "msg.change.property.accessor.to.data.with.configurable.false", id);
}
                }
}
}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[311]++;}
        }
    }

    protected static boolean isTrue(Object value) {
        return (value != NOT_FOUND) && ScriptRuntime.toBoolean(value);
    }

    protected static boolean isFalse(Object value) {
        return !isTrue(value);
    }

    /**
     * Implements SameValue as described in ES5 9.12, additionally checking
     * if new value is defined.
     * @param newValue the new value
     * @param currentValue the current value
     * @return true if values are the same as defined by ES5 9.12
     */
    protected boolean sameValue(Object newValue, Object currentValue) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[488]++;
int CodeCoverConditionCoverageHelper_C170;
        if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((newValue == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[334]++;
            return true;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[335]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[489]++;
int CodeCoverConditionCoverageHelper_C171;
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((currentValue == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[336]++;
            currentValue = Undefined.instance;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[490]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[337]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[491]++;
int CodeCoverConditionCoverageHelper_C172;
        // Special rules for numbers: NaN is considered the same value,
        // while zeroes with different signs are considered different.
        if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (8)) == 0 || true) &&
 ((currentValue instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((newValue instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[338]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[492]++;
            double d1 = ((Number)currentValue).doubleValue();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[493]++;
            double d2 = ((Number)newValue).doubleValue();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[494]++;
int CodeCoverConditionCoverageHelper_C173;
            if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (8)) == 0 || true) &&
 ((Double.isNaN(d1)) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((Double.isNaN(d2)) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[340]++;
                return true;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[341]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[495]++;
int CodeCoverConditionCoverageHelper_C174;
            if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (8)) == 0 || true) &&
 ((d1 == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((Double.doubleToLongBits(d1) != Double.doubleToLongBits(d2)) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[342]++;
                return false;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[343]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[339]++;}
        return ScriptRuntime.shallowEq(currentValue, newValue);
    }

    protected int applyDescriptorToAttributeBitset(int attributes,
                                                   ScriptableObject desc)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[496]++;
        Object enumerable = getProperty(desc, "enumerable");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[497]++;
int CodeCoverConditionCoverageHelper_C175;
        if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((enumerable != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[344]++;
            attributes = ScriptRuntime.toBoolean(enumerable)
                    ? attributes & ~DONTENUM : attributes | DONTENUM;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[498]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[345]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[499]++;

        Object writable = getProperty(desc, "writable");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[500]++;
int CodeCoverConditionCoverageHelper_C176;
        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((writable != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[346]++;
            attributes = ScriptRuntime.toBoolean(writable)
                    ? attributes & ~READONLY : attributes | READONLY;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[501]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[347]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[502]++;

        Object configurable = getProperty(desc, "configurable");
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[503]++;
int CodeCoverConditionCoverageHelper_C177;
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((configurable != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[348]++;
            attributes = ScriptRuntime.toBoolean(configurable)
                    ? attributes & ~PERMANENT : attributes | PERMANENT;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[504]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[349]++;}

        return attributes;
    }

    /**
     * Implements IsDataDescriptor as described in ES5 8.10.2
     * @param desc a property descriptor
     * @return true if this is a data descriptor.
     */
    protected boolean isDataDescriptor(ScriptableObject desc) {
        return hasProperty(desc, "value") || hasProperty(desc, "writable");
    }

    /**
     * Implements IsAccessorDescriptor as described in ES5 8.10.1
     * @param desc a property descriptor
     * @return true if this is an accessor descriptor.
     */
    protected boolean isAccessorDescriptor(ScriptableObject desc) {
        return hasProperty(desc, "get") || hasProperty(desc, "set");
    }

    /**
     * Implements IsGenericDescriptor as described in ES5 8.10.3
     * @param desc a property descriptor
     * @return true if this is a generic descriptor.
     */
    protected boolean isGenericDescriptor(ScriptableObject desc) {
        return !isDataDescriptor(desc) && !isAccessorDescriptor(desc);
    }

    protected static Scriptable ensureScriptable(Object arg) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[505]++;
int CodeCoverConditionCoverageHelper_C178;
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((arg instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false) ) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[350]++;
            throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(arg));
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[351]++;}
        return (Scriptable) arg;
    }

    protected static ScriptableObject ensureScriptableObject(Object arg) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[506]++;
int CodeCoverConditionCoverageHelper_C179;
        if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((arg instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false) ) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[352]++;
            throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(arg));
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[353]++;}
        return (ScriptableObject) arg;
    }

    /**
     * Search for names in a class, adding the resulting methods
     * as properties.
     *
     * <p> Uses reflection to find the methods of the given names. Then
     * FunctionObjects are constructed from the methods found, and
     * are added to this object as properties with the given names.
     *
     * @param names the names of the Methods to add as function properties
     * @param clazz the class to search for the Methods
     * @param attributes the attributes of the new properties
     * @see org.mozilla.javascript.FunctionObject
     */
    public void defineFunctionProperties(String[] names, Class<?> clazz,
                                         int attributes)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[507]++;
        Method[] methods = FunctionObject.getMethodList(clazz);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[508]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[28]++;


int CodeCoverConditionCoverageHelper_C180;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((i < names.length) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[28]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[29]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[30]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[509]++;
            String name = names[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[510]++;
            Method m = FunctionObject.findSingleMethod(methods, name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[511]++;
int CodeCoverConditionCoverageHelper_C181;
            if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((m == null) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[354]++;
                throw Context.reportRuntimeError2(
                    "msg.method.not.found", name, clazz.getName());

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[355]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[512]++;
            FunctionObject f = new FunctionObject(name, m, this);
            defineProperty(name, f, attributes);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[513]++;
        }
    }

    /**
     * Get the Object.prototype property.
     * See ECMA 15.2.4.
     */
    public static Scriptable getObjectPrototype(Scriptable scope) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scope),
                TopLevel.Builtins.Object);
    }

    /**
     * Get the Function.prototype property.
     * See ECMA 15.3.4.
     */
    public static Scriptable getFunctionPrototype(Scriptable scope) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scope),
                TopLevel.Builtins.Function);
    }

    public static Scriptable getArrayPrototype(Scriptable scope) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scope),
                TopLevel.Builtins.Array);
    }

    /**
     * Get the prototype for the named class.
     *
     * For example, <code>getClassPrototype(s, "Date")</code> will first
     * walk up the parent chain to find the outermost scope, then will
     * search that scope for the Date constructor, and then will
     * return Date.prototype. If any of the lookups fail, or
     * the prototype is not a JavaScript object, then null will
     * be returned.
     *
     * @param scope an object in the scope chain
     * @param className the name of the constructor
     * @return the prototype for the named class, or null if it
     *         cannot be found.
     */
    public static Scriptable getClassPrototype(Scriptable scope,
                                               String className)
    {
        scope = getTopLevelScope(scope);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[514]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[515]++;
        Object ctor = getProperty(scope, className);
        Object proto;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[516]++;
int CodeCoverConditionCoverageHelper_C182;
        if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((ctor instanceof BaseFunction) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[356]++;
            proto = ((BaseFunction)ctor).getPrototypeProperty();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[517]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[357]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[518]++;
int CodeCoverConditionCoverageHelper_C183; if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((ctor instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[358]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[519]++;
            Scriptable ctorObj = (Scriptable)ctor;
            proto = ctorObj.get("prototype", ctorObj);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[520]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[359]++;
            return null;
        }
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[521]++;
int CodeCoverConditionCoverageHelper_C184;
        if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((proto instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[360]++;
            return (Scriptable)proto;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[361]++;}
        return null;
    }

    /**
     * Get the global scope.
     *
     * <p>Walks the parent scope chain to find an object with a null
     * parent scope (the global object).
     *
     * @param obj a JavaScript object
     * @return the corresponding global scope
     */
    public static Scriptable getTopLevelScope(Scriptable obj)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[522]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[31]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[31]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[32]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[33]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[523]++;
            Scriptable parent = obj.getParentScope();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[524]++;
int CodeCoverConditionCoverageHelper_C186;
            if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[362]++;
                return obj;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[363]++;}
            obj = parent;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[525]++;
        }
    }

    public boolean isExtensible() {
      return isExtensible;
    }

    public void preventExtensions() {
      isExtensible = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[526]++;
    }

    /**
     * Seal this object.
     *
     * It is an error to add properties to or delete properties from
     * a sealed object. It is possible to change the value of an
     * existing property. Once an object is sealed it may not be unsealed.
     *
     * @since 1.4R3
     */
    public synchronized void sealObject() {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[527]++;
int CodeCoverConditionCoverageHelper_C187;
        if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((count >= 0) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[364]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[528]++;
            // Make sure all LazilyLoadedCtors are initialized before sealing.
            Slot slot = firstAdded;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[529]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[34]++;


int CodeCoverConditionCoverageHelper_C188;
            while ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[34]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[35]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[36]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[530]++;
                Object value = slot.value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[531]++;
int CodeCoverConditionCoverageHelper_C189;
                if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((value instanceof LazilyLoadedCtor) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[366]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[532]++;
                    LazilyLoadedCtor initializer = (LazilyLoadedCtor) value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[533]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                    try {
CodeCoverTryBranchHelper_Try2 = true;
                        initializer.init();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[534]++;
                    } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[368]++;
}
                        slot.value = initializer.getValue();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[535]++;
                    }

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[367]++;}
                slot = slot.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[536]++;
            }
            count = ~count;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[537]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[365]++;}
    }

    /**
     * Return true if this object is sealed.
     *
     * @return true if sealed, false otherwise.
     * @since 1.4R3
     * @see #sealObject()
     */
    public final boolean isSealed() {
        return count < 0;
    }

    private void checkNotSealed(String name, int index)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[538]++;
int CodeCoverConditionCoverageHelper_C190;
        if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[369]++;
            return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[370]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[539]++;

        String str = (name != null) ? name : Integer.toString(index);
        throw Context.reportRuntimeError1("msg.modify.sealed", str);
    }

    /**
     * Gets a named property from an object or any object in its prototype chain.
     * <p>
     * Searches the prototype chain for a property named <code>name</code>.
     * <p>
     * @param obj a JavaScript object
     * @param name a property name
     * @return the value of a property with name <code>name</code> found in
     *         <code>obj</code> or any object in its prototype chain, or
     *         <code>Scriptable.NOT_FOUND</code> if not found
     * @since 1.5R2
     */
    public static Object getProperty(Scriptable obj, String name)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[540]++;
        Scriptable start = obj;
        Object result;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[541]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[37]++;


int CodeCoverConditionCoverageHelper_C191;
        do {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[37]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[38]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[39]++;
}
            result = obj.get(name, start);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[542]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[543]++;
int CodeCoverConditionCoverageHelper_C192;
            if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((result != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[371]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[544]++;
                break;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[372]++;}
            obj = obj.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[545]++;
        } while ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false));
        return result;
    }

    /**
     * Gets an indexed property from an object or any object in its prototype
     * chain and coerces it to the requested Java type.
     * <p>
     * Searches the prototype chain for a property with integral index
     * <code>index</code>. Note that if you wish to look for properties with numerical
     * but non-integral indicies, you should use getProperty(Scriptable,String) with
     * the string value of the index.
     * <p>
     * @param s a JavaScript object
     * @param index an integral index
     * @param type the required Java type of the result
     * @return the value of a property with name <code>name</code> found in
     *         <code>obj</code> or any object in its prototype chain, or
     *         null if not found. Note that it does not return
     *         {@link Scriptable#NOT_FOUND} as it can ordinarily not be
     *         converted to most of the types.
     * @since 1.7R3
     */
    public static <T> T getTypedProperty(Scriptable s, int index, Class<T> type) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[546]++;
        Object val = getProperty(s, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[547]++;
int CodeCoverConditionCoverageHelper_C193;
        if((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((val == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[373]++;
            val = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[548]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[374]++;}
        return type.cast(Context.jsToJava(val, type));
    }

    /**
     * Gets an indexed property from an object or any object in its prototype chain.
     * <p>
     * Searches the prototype chain for a property with integral index
     * <code>index</code>. Note that if you wish to look for properties with numerical
     * but non-integral indicies, you should use getProperty(Scriptable,String) with
     * the string value of the index.
     * <p>
     * @param obj a JavaScript object
     * @param index an integral index
     * @return the value of a property with index <code>index</code> found in
     *         <code>obj</code> or any object in its prototype chain, or
     *         <code>Scriptable.NOT_FOUND</code> if not found
     * @since 1.5R2
     */
    public static Object getProperty(Scriptable obj, int index)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[549]++;
        Scriptable start = obj;
        Object result;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[550]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[40]++;


int CodeCoverConditionCoverageHelper_C194;
        do {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[40]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[41]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[42]++;
}
            result = obj.get(index, start);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[551]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[552]++;
int CodeCoverConditionCoverageHelper_C195;
            if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((result != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[375]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[553]++;
                break;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[376]++;}
            obj = obj.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[554]++;
        } while ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false));
        return result;
    }

    /**
     * Gets a named property from an object or any object in its prototype chain
     * and coerces it to the requested Java type.
     * <p>
     * Searches the prototype chain for a property named <code>name</code>.
     * <p>
     * @param s a JavaScript object
     * @param name a property name
     * @param type the required Java type of the result
     * @return the value of a property with name <code>name</code> found in
     *         <code>obj</code> or any object in its prototype chain, or
     *         null if not found. Note that it does not return
     *         {@link Scriptable#NOT_FOUND} as it can ordinarily not be
     *         converted to most of the types.
     * @since 1.7R3
     */
    public static <T> T getTypedProperty(Scriptable s, String name, Class<T> type) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[555]++;
        Object val = getProperty(s, name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[556]++;
int CodeCoverConditionCoverageHelper_C196;
        if((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((val == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[377]++;
            val = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[557]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[378]++;}
        return type.cast(Context.jsToJava(val, type));
    }

    /**
     * Returns whether a named property is defined in an object or any object
     * in its prototype chain.
     * <p>
     * Searches the prototype chain for a property named <code>name</code>.
     * <p>
     * @param obj a JavaScript object
     * @param name a property name
     * @return the true if property was found
     * @since 1.5R2
     */
    public static boolean hasProperty(Scriptable obj, String name)
    {
        return null != getBase(obj, name);
    }

    /**
     * If hasProperty(obj, name) would return true, then if the property that
     * was found is compatible with the new property, this method just returns.
     * If the property is not compatible, then an exception is thrown.
     *
     * A property redefinition is incompatible if the first definition was a
     * const declaration or if this one is.  They are compatible only if neither
     * was const.
     */
    public static void redefineProperty(Scriptable obj, String name,
                                        boolean isConst)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[558]++;
        Scriptable base = getBase(obj, name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[559]++;
int CodeCoverConditionCoverageHelper_C197;
        if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[379]++;
            return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[380]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[560]++;
int CodeCoverConditionCoverageHelper_C198;
        if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((base instanceof ConstProperties) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[381]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[561]++;
            ConstProperties cp = (ConstProperties)base;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[562]++;
int CodeCoverConditionCoverageHelper_C199;

            if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((cp.isConst(name)) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[383]++;
                throw ScriptRuntime.typeError1("msg.const.redecl", name);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[384]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[382]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[563]++;
int CodeCoverConditionCoverageHelper_C200;
        if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((isConst) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[385]++;
            throw ScriptRuntime.typeError1("msg.var.redecl", name);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[386]++;}
    }
    /**
     * Returns whether an indexed property is defined in an object or any object
     * in its prototype chain.
     * <p>
     * Searches the prototype chain for a property with index <code>index</code>.
     * <p>
     * @param obj a JavaScript object
     * @param index a property index
     * @return the true if property was found
     * @since 1.5R2
     */
    public static boolean hasProperty(Scriptable obj, int index)
    {
        return null != getBase(obj, index);
    }

    /**
     * Puts a named property in an object or in an object in its prototype chain.
     * <p>
     * Searches for the named property in the prototype chain. If it is found,
     * the value of the property in <code>obj</code> is changed through a call
     * to {@link Scriptable#put(String, Scriptable, Object)} on the
     * prototype passing <code>obj</code> as the <code>start</code> argument.
     * This allows the prototype to veto the property setting in case the
     * prototype defines the property with [[ReadOnly]] attribute. If the
     * property is not found, it is added in <code>obj</code>.
     * @param obj a JavaScript object
     * @param name a property name
     * @param value any JavaScript value accepted by Scriptable.put
     * @since 1.5R2
     */
    public static void putProperty(Scriptable obj, String name, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[564]++;
        Scriptable base = getBase(obj, name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[565]++;
int CodeCoverConditionCoverageHelper_C201;
        if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[387]++;
            base = obj;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[566]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[388]++;}
        base.put(name, obj, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[567]++;
    }

    /**
     * Puts a named property in an object or in an object in its prototype chain.
     * <p>
     * Searches for the named property in the prototype chain. If it is found,
     * the value of the property in <code>obj</code> is changed through a call
     * to {@link Scriptable#put(String, Scriptable, Object)} on the
     * prototype passing <code>obj</code> as the <code>start</code> argument.
     * This allows the prototype to veto the property setting in case the
     * prototype defines the property with [[ReadOnly]] attribute. If the
     * property is not found, it is added in <code>obj</code>.
     * @param obj a JavaScript object
     * @param name a property name
     * @param value any JavaScript value accepted by Scriptable.put
     * @since 1.5R2
     */
    public static void putConstProperty(Scriptable obj, String name, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[568]++;
        Scriptable base = getBase(obj, name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[569]++;
int CodeCoverConditionCoverageHelper_C202;
        if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[389]++;
            base = obj;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[570]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[390]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[571]++;
int CodeCoverConditionCoverageHelper_C203;
        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((base instanceof ConstProperties) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[391]++;
            ((ConstProperties)base).putConst(name, obj, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[572]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[392]++;}
    }

    /**
     * Puts an indexed property in an object or in an object in its prototype chain.
     * <p>
     * Searches for the indexed property in the prototype chain. If it is found,
     * the value of the property in <code>obj</code> is changed through a call
     * to {@link Scriptable#put(int, Scriptable, Object)} on the prototype
     * passing <code>obj</code> as the <code>start</code> argument. This allows
     * the prototype to veto the property setting in case the prototype defines
     * the property with [[ReadOnly]] attribute. If the property is not found,
     * it is added in <code>obj</code>.
     * @param obj a JavaScript object
     * @param index a property index
     * @param value any JavaScript value accepted by Scriptable.put
     * @since 1.5R2
     */
    public static void putProperty(Scriptable obj, int index, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[573]++;
        Scriptable base = getBase(obj, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[574]++;
int CodeCoverConditionCoverageHelper_C204;
        if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[393]++;
            base = obj;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[575]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[394]++;}
        base.put(index, obj, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[576]++;
    }

    /**
     * Removes the property from an object or its prototype chain.
     * <p>
     * Searches for a property with <code>name</code> in obj or
     * its prototype chain. If it is found, the object's delete
     * method is called.
     * @param obj a JavaScript object
     * @param name a property name
     * @return true if the property doesn't exist or was successfully removed
     * @since 1.5R2
     */
    public static boolean deleteProperty(Scriptable obj, String name)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[577]++;
        Scriptable base = getBase(obj, name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[578]++;
int CodeCoverConditionCoverageHelper_C205;
        if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[395]++;
            return true;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[396]++;}
        base.delete(name);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[579]++;
        return !base.has(name, obj);
    }

    /**
     * Removes the property from an object or its prototype chain.
     * <p>
     * Searches for a property with <code>index</code> in obj or
     * its prototype chain. If it is found, the object's delete
     * method is called.
     * @param obj a JavaScript object
     * @param index a property index
     * @return true if the property doesn't exist or was successfully removed
     * @since 1.5R2
     */
    public static boolean deleteProperty(Scriptable obj, int index)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[580]++;
        Scriptable base = getBase(obj, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[581]++;
int CodeCoverConditionCoverageHelper_C206;
        if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[397]++;
            return true;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[398]++;}
        base.delete(index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[582]++;
        return !base.has(index, obj);
    }

    /**
     * Returns an array of all ids from an object and its prototypes.
     * <p>
     * @param obj a JavaScript object
     * @return an array of all ids from all object in the prototype chain.
     *         If a given id occurs multiple times in the prototype chain,
     *         it will occur only once in this list.
     * @since 1.5R2
     */
    public static Object[] getPropertyIds(Scriptable obj)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[583]++;
int CodeCoverConditionCoverageHelper_C207;
        if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[399]++;
            return ScriptRuntime.emptyArgs;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[400]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[584]++;
        Object[] result = obj.getIds();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[585]++;
        ObjToIntMap map = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[586]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[43]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[43]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[44]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[45]++;
}
            obj = obj.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[587]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[588]++;
int CodeCoverConditionCoverageHelper_C209;
            if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[401]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[589]++;
                break;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[402]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[590]++;
            Object[] ids = obj.getIds();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[591]++;
int CodeCoverConditionCoverageHelper_C210;
            if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((ids.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[403]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[592]++;
                continue;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[404]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[593]++;
int CodeCoverConditionCoverageHelper_C211;
            if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((map == null) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[405]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[594]++;
int CodeCoverConditionCoverageHelper_C212;
                if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((result.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[407]++;
                    result = ids;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[595]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[596]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[408]++;}
                map = new ObjToIntMap(result.length + ids.length);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[597]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[598]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[46]++;


int CodeCoverConditionCoverageHelper_C213;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((i != result.length) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[46]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[47]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[48]++;
}
                    map.intern(result[i]);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[599]++;
                }
                result = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[600]++;
 // Allow to GC the result
            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[406]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[601]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[49]++;


int CodeCoverConditionCoverageHelper_C214;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((i != ids.length) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[49]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[50]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[51]++;
}
                map.intern(ids[i]);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[602]++;
            }
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[603]++;
int CodeCoverConditionCoverageHelper_C215;
        if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((map != null) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[409]++;
            result = map.getKeys();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[604]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[410]++;}
        return result;
    }

    /**
     * Call a method of an object.
     * @param obj the JavaScript object
     * @param methodName the name of the function property
     * @param args the arguments for the call
     *
     * @see Context#getCurrentContext()
     */
    public static Object callMethod(Scriptable obj, String methodName,
                                    Object[] args)
    {
        return callMethod(null, obj, methodName, args);
    }

    /**
     * Call a method of an object.
     * @param cx the Context object associated with the current thread.
     * @param obj the JavaScript object
     * @param methodName the name of the function property
     * @param args the arguments for the call
     */
    public static Object callMethod(Context cx, Scriptable obj,
                                    String methodName,
                                    Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[605]++;
        Object funObj = getProperty(obj, methodName);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[606]++;
int CodeCoverConditionCoverageHelper_C216;
        if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((funObj instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[411]++;
            throw ScriptRuntime.notFunctionError(obj, methodName);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[412]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[607]++;
        Function fun = (Function)funObj;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[608]++;
        // XXX: What should be the scope when calling funObj?
        // The following favor scope stored in the object on the assumption
        // that is more useful especially under dynamic scope setup.
        // An alternative is to check for dynamic scope flag
        // and use ScriptableObject.getTopLevelScope(fun) if the flag is not
        // set. But that require access to Context and messy code
        // so for now it is not checked.
        Scriptable scope = ScriptableObject.getTopLevelScope(obj);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[609]++;
int CodeCoverConditionCoverageHelper_C217;
        if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[413]++;
            return fun.call(cx, scope, obj, args);

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[414]++;
            return Context.call(null, fun, scope, obj, args);
        }
    }

    private static Scriptable getBase(Scriptable obj, String name)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[610]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[52]++;


int CodeCoverConditionCoverageHelper_C218;
        do {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[52]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[53]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[54]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[611]++;
int CodeCoverConditionCoverageHelper_C219;
            if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((obj.has(name, obj)) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[415]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[612]++;
                break;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[416]++;}
            obj = obj.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[613]++;
        } while((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false));
        return obj;
    }

    private static Scriptable getBase(Scriptable obj, int index)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[614]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[55]++;


int CodeCoverConditionCoverageHelper_C220;
        do {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[55]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[56]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[57]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[615]++;
int CodeCoverConditionCoverageHelper_C221;
            if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((obj.has(index, obj)) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[417]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[616]++;
                break;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[418]++;}
            obj = obj.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[617]++;
        } while((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false));
        return obj;
    }

    /**
     * Get arbitrary application-specific value associated with this object.
     * @param key key object to select particular value.
     * @see #associateValue(Object key, Object value)
     */
    public final Object getAssociatedValue(Object key)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[618]++;
        Map<Object,Object> h = associatedValues;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[619]++;
int CodeCoverConditionCoverageHelper_C222;
        if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((h == null) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[419]++;
            return null;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[420]++;}
        return h.get(key);
    }

    /**
     * Get arbitrary application-specific value associated with the top scope
     * of the given scope.
     * The method first calls {@link #getTopLevelScope(Scriptable scope)}
     * and then searches the prototype chain of the top scope for the first
     * object containing the associated value with the given key.
     *
     * @param scope the starting scope.
     * @param key key object to select particular value.
     * @see #getAssociatedValue(Object key)
     */
    public static Object getTopScopeValue(Scriptable scope, Object key)
    {
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[620]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[621]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[58]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[58]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[59]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[60]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[622]++;
int CodeCoverConditionCoverageHelper_C224;
            if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((scope instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[421]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[623]++;
                ScriptableObject so = (ScriptableObject)scope;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[624]++;
                Object value = so.getAssociatedValue(key);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[625]++;
int CodeCoverConditionCoverageHelper_C225;
                if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[423]++;
                    return value;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[424]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[422]++;}
            scope = scope.getPrototype();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[626]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[627]++;
int CodeCoverConditionCoverageHelper_C226;
            if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[425]++;
                return null;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[426]++;}
        }
    }

    /**
     * Associate arbitrary application-specific value with this object.
     * Value can only be associated with the given object and key only once.
     * The method ignores any subsequent attempts to change the already
     * associated value.
     * <p> The associated values are not serialized.
     * @param key key object to select particular value.
     * @param value the value to associate
     * @return the passed value if the method is called first time for the
     * given key or old value for any subsequent calls.
     * @see #getAssociatedValue(Object key)
     */
    public synchronized final Object associateValue(Object key, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[628]++;
int CodeCoverConditionCoverageHelper_C227;
        if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[427]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[428]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[629]++;
        Map<Object,Object> h = associatedValues;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[630]++;
int CodeCoverConditionCoverageHelper_C228;
        if ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((h == null) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[429]++;
            h = new HashMap<Object,Object>();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[631]++;
            associatedValues = h;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[632]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[430]++;}
        return Kit.initHash(h, key, value);
    }

    /**
     *
     * @param name
     * @param index
     * @param start
     * @param value
     * @return false if this != start and no slot was found.  true if this == start
     * or this != start and a READONLY slot was found.
     */
    private boolean putImpl(String name, int index, Scriptable start,
                            Object value)
    {
        // This method is very hot (basically called on each assignment)
        // so we inline the extensible/sealed checks below.
        Slot slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[633]++;
int CodeCoverConditionCoverageHelper_C229;
        if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((this != start) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[431]++;
            slot = getSlot(name, index, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[634]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[635]++;
int CodeCoverConditionCoverageHelper_C230;
            if ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[433]++;
                return false;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[434]++;}

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[432]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[636]++;
int CodeCoverConditionCoverageHelper_C231; if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((isExtensible) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[435]++;
            slot = getSlot(name, index, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[637]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[638]++;
int CodeCoverConditionCoverageHelper_C232;
            if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[437]++;
                return true;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[438]++;}

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[436]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[639]++;
int CodeCoverConditionCoverageHelper_C233;
            if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((count < 0) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[439]++; checkNotSealed(name, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[640]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[440]++;}
            slot = getSlot(name, index, SLOT_MODIFY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[641]++;
        }
}
        return slot.setValue(value, this, start);
    }


    /**
     *
     * @param name
     * @param index
     * @param start
     * @param value
     * @param constFlag EMPTY means normal put.  UNINITIALIZED_CONST means
     * defineConstProperty.  READONLY means const initialization expression.
     * @return false if this != start and no slot was found.  true if this == start
     * or this != start and a READONLY slot was found.
     */
    private boolean putConstImpl(String name, int index, Scriptable start,
                                 Object value, int constFlag)
    {
        assert (constFlag != EMPTY);
        Slot slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[642]++;
int CodeCoverConditionCoverageHelper_C234;
        if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((this != start) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[441]++;
            slot = getSlot(name, index, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[643]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[644]++;
int CodeCoverConditionCoverageHelper_C235;
            if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[443]++;
                return false;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[444]++;}

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[442]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[645]++;
int CodeCoverConditionCoverageHelper_C236; if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((isExtensible()) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[445]++;
            slot = getSlot(name, index, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[646]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[647]++;
int CodeCoverConditionCoverageHelper_C237;
            if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[447]++;
                return true;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[448]++;}

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[446]++;
            checkNotSealed(name, index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[648]++;
            // either const hoisted declaration or initialization
            slot = unwrapSlot(getSlot(name, index, SLOT_MODIFY_CONST));
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[649]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[650]++;
            int attr = slot.getAttributes();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[651]++;
int CodeCoverConditionCoverageHelper_C238;
            if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 (((attr & READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[449]++;
                throw Context.reportRuntimeError1("msg.var.redecl", name);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[450]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[652]++;
int CodeCoverConditionCoverageHelper_C239;
            if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 (((attr & UNINITIALIZED_CONST) != 0) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[451]++;
                slot.value = value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[653]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[654]++;
int CodeCoverConditionCoverageHelper_C240;
                // clear the bit on const initialization
                if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((constFlag != UNINITIALIZED_CONST) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[453]++;
                    slot.setAttributes(attr & ~UNINITIALIZED_CONST);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[655]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[454]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[452]++;}
            return true;
        }
}
        return slot.setValue(value, this, start);
    }

    private Slot findAttributeSlot(String name, int index, int accessType)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[656]++;
        Slot slot = getSlot(name, index, accessType);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[657]++;
int CodeCoverConditionCoverageHelper_C241;
        if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[455]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[658]++;
            String str = (name != null ? name : Integer.toString(index));
            throw Context.reportRuntimeError1("msg.prop.not.found", str);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[456]++;}
        return slot;
    }

    private static Slot unwrapSlot(Slot slot) {
        return (slot instanceof RelinkedSlot) ? ((RelinkedSlot)slot).slot : slot;
    }

    /**
     * Locate the slot with given name or index. Depending on the accessType
     * parameter and the current slot status, a new slot may be allocated.
     *
     * @param name property name or null if slot holds spare array index.
     * @param index index or 0 if slot holds property name.
     */
    private Slot getSlot(String name, int index, int accessType)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[659]++;
        // Check the hashtable without using synchronization
        Slot[] slotsLocalRef = slots;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[660]++;
int CodeCoverConditionCoverageHelper_C242; // Get stable local reference
        if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (8)) == 0 || true) &&
 ((slotsLocalRef == null) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((accessType == SLOT_QUERY) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[457]++;
            return null;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[458]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[661]++;

        int indexOrHash = (name != null ? name.hashCode() : index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[662]++;
int CodeCoverConditionCoverageHelper_C243;
        if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((slotsLocalRef != null) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[459]++;
            Slot slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[663]++;
            int slotIndex = getSlotIndex(slotsLocalRef.length, indexOrHash);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[664]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[61]++;


int CodeCoverConditionCoverageHelper_C244;
            for (slot = slotsLocalRef[slotIndex];(((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false);
                 slot = slot.next) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[61]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[62]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[63]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[665]++;
                Object sname = slot.name;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[666]++;
int CodeCoverConditionCoverageHelper_C245;
                if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (128)) == 0 || true) &&
 ((indexOrHash == slot.indexOrHash) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C245 |= (32)) == 0 || true) &&
 ((sname == name) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C245 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((name.equals(sname)) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 4) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 4) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[461]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[667]++;
                    break;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[462]++;}
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[668]++;
            switch (accessType) {
                case SLOT_QUERY:
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[463]++;
                    return slot;
                case SLOT_MODIFY:
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[464]++;
                case SLOT_MODIFY_CONST:
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[465]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[669]++;
int CodeCoverConditionCoverageHelper_C246;
                    if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[466]++;
                        return slot;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[467]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[670]++;
                    break;
                case SLOT_MODIFY_GETTER_SETTER:
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[468]++;
                    slot = unwrapSlot(slot);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[671]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[672]++;
int CodeCoverConditionCoverageHelper_C247;
                    if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((slot instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[469]++;
                        return slot;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[470]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[673]++;
                    break;
                case SLOT_CONVERT_ACCESSOR_TO_DATA:
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[471]++;
                    slot = unwrapSlot(slot);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[674]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[675]++;
int CodeCoverConditionCoverageHelper_C248;
                    if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((slot instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false) ) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[472]++;
                        return slot;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[473]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[676]++;
                    break; default : CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[474]++;
            }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[460]++;}

        // A new slot has to be inserted or the old has to be replaced
        // by GetterSlot. Time to synchronize.
        return createSlot(name, indexOrHash, accessType);
    }

    private synchronized Slot createSlot(String name, int indexOrHash, int accessType) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[677]++;
        Slot[] slotsLocalRef = slots;
        int insertPos;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[678]++;
int CodeCoverConditionCoverageHelper_C249;
        if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[475]++;
            // Always throw away old slots if any on empty insert.
            slotsLocalRef = new Slot[INITIAL_SLOT_SIZE];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[679]++;
            slots = slotsLocalRef;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[680]++;
            insertPos = getSlotIndex(slotsLocalRef.length, indexOrHash);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[681]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[476]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[682]++;
            int tableSize = slotsLocalRef.length;
            insertPos = getSlotIndex(tableSize, indexOrHash);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[683]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[684]++;
            Slot prev = slotsLocalRef[insertPos];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[685]++;
            Slot slot = prev;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[686]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[64]++;


int CodeCoverConditionCoverageHelper_C250;
            while ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false)) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[64]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[65]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[66]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[687]++;
int CodeCoverConditionCoverageHelper_C251;
                if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (128)) == 0 || true) &&
 ((slot.indexOrHash == indexOrHash) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C251 |= (32)) == 0 || true) &&
 ((slot.name == name) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C251 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((name.equals(slot.name)) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 4) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 4) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[477]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[688]++;
                    break;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[478]++;}
                prev = slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[689]++;
                slot = slot.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[690]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[691]++;
int CodeCoverConditionCoverageHelper_C252;

            if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[479]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[692]++;
                // A slot with same name/index already exists. This means that
                // a slot is being redefined from a value to a getter slot or
                // vice versa, or it could be a race in application code.
                // Check if we need to replace the slot depending on the
                // accessType flag and return the appropriate slot instance.

                Slot inner = unwrapSlot(slot);
                Slot newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[693]++;
int CodeCoverConditionCoverageHelper_C253;

                if ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (8)) == 0 || true) &&
 ((accessType == SLOT_MODIFY_GETTER_SETTER) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((inner instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[481]++;
                    newSlot = new GetterSlot(name, indexOrHash, inner.getAttributes());
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[694]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[482]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[695]++;
int CodeCoverConditionCoverageHelper_C254; if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (8)) == 0 || true) &&
 ((accessType == SLOT_CONVERT_ACCESSOR_TO_DATA) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((inner instanceof GetterSlot) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[483]++;
                    newSlot = new Slot(name, indexOrHash, inner.getAttributes());
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[696]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[484]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[697]++;
int CodeCoverConditionCoverageHelper_C255; if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((accessType == SLOT_MODIFY_CONST) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[485]++;
                    return null;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[486]++;
                    return inner;
                }
}
}

                newSlot.value = inner.value;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[698]++;
                newSlot.next = slot.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[699]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[700]++;
int CodeCoverConditionCoverageHelper_C256;
                // add new slot to linked list
                if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((lastAdded != null) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[487]++;
                    lastAdded.orderedNext = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[701]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[488]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[702]++;
int CodeCoverConditionCoverageHelper_C257;
                if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((firstAdded == null) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[489]++;
                    firstAdded = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[703]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[490]++;}
                lastAdded = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[704]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[705]++;
int CodeCoverConditionCoverageHelper_C258;
                // add new slot to hash table
                if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((prev == slot) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[491]++;
                    slotsLocalRef[insertPos] = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[706]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[492]++;
                    prev.next = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[707]++;
                }
                // other housekeeping
                slot.markDeleted();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[708]++;
                return newSlot;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[480]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[709]++;
int CodeCoverConditionCoverageHelper_C259;
                // Check if the table is not too full before inserting.
                if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((4 * (count + 1) > 3 * slotsLocalRef.length) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[493]++;
                    // table size must be a power of 2, always grow by x2
                    slotsLocalRef = new Slot[slotsLocalRef.length * 2];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[710]++;
                    copyTable(slots, slotsLocalRef, count);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[711]++;
                    slots = slotsLocalRef;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[712]++;
                    insertPos = getSlotIndex(slotsLocalRef.length,
                            indexOrHash);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[713]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[494]++;}
            }
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[714]++;
        Slot newSlot = (accessType == SLOT_MODIFY_GETTER_SETTER
                ? new GetterSlot(name, indexOrHash, 0)
                : new Slot(name, indexOrHash, 0));
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[715]++;
int CodeCoverConditionCoverageHelper_C260;
        if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((accessType == SLOT_MODIFY_CONST) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[495]++;
            newSlot.setAttributes(CONST);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[716]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[496]++;}
        ++count;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[717]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[718]++;
int CodeCoverConditionCoverageHelper_C261;
        // add new slot to linked list
        if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((lastAdded != null) && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[497]++;
            lastAdded.orderedNext = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[719]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[498]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[720]++;
int CodeCoverConditionCoverageHelper_C262;
        if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((firstAdded == null) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[499]++;
            firstAdded = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[721]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[500]++;}
        lastAdded = newSlot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[722]++;
        // add new slot to hash table, return it
        addKnownAbsentSlot(slotsLocalRef, newSlot, insertPos);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[723]++;
        return newSlot;
    }

    private synchronized void removeSlot(String name, int index) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[724]++;
        int indexOrHash = (name != null ? name.hashCode() : index);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[725]++;

        Slot[] slotsLocalRef = slots;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[726]++;
int CodeCoverConditionCoverageHelper_C263;
        if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((count != 0) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[501]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[727]++;
            int tableSize = slotsLocalRef.length;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[728]++;
            int slotIndex = getSlotIndex(tableSize, indexOrHash);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[729]++;
            Slot prev = slotsLocalRef[slotIndex];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[730]++;
            Slot slot = prev;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[731]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[67]++;


int CodeCoverConditionCoverageHelper_C264;
            while ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) && false)) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[67]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[68]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[69]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[732]++;
int CodeCoverConditionCoverageHelper_C265;
                if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (128)) == 0 || true) &&
 ((slot.indexOrHash == indexOrHash) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C265 |= (32)) == 0 || true) &&
 ((slot.name == name) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C265 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((name.equals(slot.name)) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 4) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 4) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[503]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[733]++;
                    break;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[504]++;}
                prev = slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[734]++;
                slot = slot.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[735]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[736]++;
int CodeCoverConditionCoverageHelper_C266;
            if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C266 |= (8)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 (((slot.getAttributes() & PERMANENT) == 0) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[505]++;
                count--;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[737]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[738]++;
int CodeCoverConditionCoverageHelper_C267;
                // remove slot from hash table
                if ((((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((prev == slot) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[507]++;
                    slotsLocalRef[slotIndex] = slot.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[739]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[508]++;
                    prev.next = slot.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[740]++;
                }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[741]++;

                // remove from ordered list. Previously this was done lazily in
                // getIds() but delete is an infrequent operation so O(n)
                // should be ok

                // ordered list always uses the actual slot
                Slot deleted = unwrapSlot(slot);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[742]++;
int CodeCoverConditionCoverageHelper_C268;
                if ((((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((deleted == firstAdded) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[509]++;
                    prev = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[743]++;
                    firstAdded = deleted.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[744]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[510]++;
                    prev = firstAdded;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[745]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[746]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[70]++;


int CodeCoverConditionCoverageHelper_C269;
                    while ((((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((prev.orderedNext != deleted) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false)) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[70]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[71]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[72]++;
}
                        prev = prev.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[747]++;
                    }
                    prev.orderedNext = deleted.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[748]++;
                }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[749]++;
int CodeCoverConditionCoverageHelper_C270;
                if ((((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((deleted == lastAdded) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[511]++;
                    lastAdded = prev;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[750]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[512]++;}

                // Mark the slot as removed.
                slot.markDeleted();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[751]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[506]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[502]++;}
    }

    private static int getSlotIndex(int tableSize, int indexOrHash)
    {
        // tableSize is a power of 2
        return indexOrHash & (tableSize - 1);
    }

    // Must be inside synchronized (this)
    private static void copyTable(Slot[] oldSlots, Slot[] newSlots, int count)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[752]++;
int CodeCoverConditionCoverageHelper_C271;
        if ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[513]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[514]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[753]++;

        int tableSize = newSlots.length;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[754]++;
        int i = oldSlots.length;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[755]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[73]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[73]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[74]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[75]++;
}
            --i;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[756]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[757]++;
            Slot slot = oldSlots[i];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[758]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[76]++;


int CodeCoverConditionCoverageHelper_C273;
            while ((((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false)) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[76]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[77]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[78]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[759]++;
                int insertPos = getSlotIndex(tableSize, slot.indexOrHash);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[760]++;
                // If slot has next chain in old table use a new
                // RelinkedSlot wrapper to keep old table valid
                Slot insSlot = slot.next == null ? slot : new RelinkedSlot(slot);
                addKnownAbsentSlot(newSlots, insSlot, insertPos);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[761]++;
                slot = slot.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[762]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[763]++;
int CodeCoverConditionCoverageHelper_C274;
                if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((--count == 0) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[515]++;
                    return;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[516]++;}
            }
        }
    }

    /**
     * Add slot with keys that are known to absent from the table.
     * This is an optimization to use when inserting into empty table,
     * after table growth or during deserialization.
     */
    private static void addKnownAbsentSlot(Slot[] slots, Slot slot,
                                           int insertPos)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[764]++;
int CodeCoverConditionCoverageHelper_C275;
        if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((slots[insertPos] == null) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[517]++;
            slots[insertPos] = slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[765]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[518]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[766]++;
            Slot prev = slots[insertPos];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[767]++;
            Slot next = prev.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[768]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[79]++;


int CodeCoverConditionCoverageHelper_C276;
            while ((((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) && false)) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[79]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[80]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[81]++;
}
                prev = next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[769]++;
                next = prev.next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[770]++;
            }
            prev.next = slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[771]++;
        }
    }

    Object[] getIds(boolean getAll) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[772]++;
        Slot[] s = slots;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[773]++;
        Object[] a = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[774]++;
int CodeCoverConditionCoverageHelper_C277;
        if ((((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[519]++;
            return a;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[520]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[775]++;
        int c = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[776]++;
        Slot slot = firstAdded;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[777]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[82]++;


int CodeCoverConditionCoverageHelper_C278;
        while ((((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (8)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((slot.wasDeleted) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 2) && false)) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[82]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[83]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[84]++;
}
            // we used to removed deleted slots from the linked list here
            // but this is now done in removeSlot(). There may still be deleted
            // slots (e.g. from slot conversion) but we don't want to mess
            // with the list in unsynchronized code.
            slot = slot.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[778]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[779]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[85]++;


int CodeCoverConditionCoverageHelper_C279;
        while ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) && false)) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[85]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[86]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[87]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[780]++;
int CodeCoverConditionCoverageHelper_C280;
            if ((((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (8)) == 0 || true) &&
 ((getAll) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 (((slot.getAttributes() & DONTENUM) == 0) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[521]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[781]++;
int CodeCoverConditionCoverageHelper_C281;
                if ((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((c == 0) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[523]++;
                    a = new Object[s.length];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[782]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[524]++;}
                a[c++] = slot.name != null
                        ? slot.name
                        : Integer.valueOf(slot.indexOrHash);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[783]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[522]++;}
            slot = slot.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[784]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[785]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[88]++;


int CodeCoverConditionCoverageHelper_C282;
            while ((((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (8)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((slot.wasDeleted) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 2) && false)) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[88]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[89]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[90]++;
}
                // skip deleted slots, see comment above
                slot = slot.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[786]++;
            }
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[787]++;
int CodeCoverConditionCoverageHelper_C283;
        if ((((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((c == a.length) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[525]++;
            return a;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[526]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[788]++;
        Object[] result = new Object[c];
        System.arraycopy(a, 0, result, 0, c);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[789]++;
        return result;
    }

    private synchronized void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[790]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[791]++;
        int objectsCount = count;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[792]++;
int CodeCoverConditionCoverageHelper_C284;
        if ((((((CodeCoverConditionCoverageHelper_C284 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C284 |= (2)) == 0 || true) &&
 ((objectsCount < 0) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[527]++;
            // "this" was sealed
            objectsCount = ~objectsCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[793]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[528]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[794]++;
int CodeCoverConditionCoverageHelper_C285;
        if ((((((CodeCoverConditionCoverageHelper_C285 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C285 |= (2)) == 0 || true) &&
 ((objectsCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[529]++;
            out.writeInt(0);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[795]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[530]++;
            out.writeInt(slots.length);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[796]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[797]++;
            Slot slot = firstAdded;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[798]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[91]++;


int CodeCoverConditionCoverageHelper_C286;
            while ((((((CodeCoverConditionCoverageHelper_C286 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C286 |= (8)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C286 |= (2)) == 0 || true) &&
 ((slot.wasDeleted) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 2) && false)) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[91]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[92]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[93]++;
}
                // as long as we're traversing the order-added linked list,
                // remove deleted slots
                slot = slot.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[799]++;
            }
            firstAdded = slot;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[800]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[801]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[94]++;


int CodeCoverConditionCoverageHelper_C287;
            while ((((((CodeCoverConditionCoverageHelper_C287 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C287 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) && false)) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[94]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[95]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[96]++;
}
                out.writeObject(slot);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[802]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[803]++;
                Slot next = slot.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[804]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[97]++;


int CodeCoverConditionCoverageHelper_C288;
                while ((((((CodeCoverConditionCoverageHelper_C288 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C288 |= (8)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C288 |= (2)) == 0 || true) &&
 ((next.wasDeleted) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 2) && false)) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[97]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[98]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[99]++;
}
                    // remove deleted slots
                    next = next.orderedNext;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[805]++;
                }
                slot.orderedNext = next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[806]++;
                slot = next;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[807]++;
            }
        }
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[808]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[809]++;

        int tableSize = in.readInt();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[810]++;
int CodeCoverConditionCoverageHelper_C289;
        if ((((((CodeCoverConditionCoverageHelper_C289 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C289 |= (2)) == 0 || true) &&
 ((tableSize != 0) && 
  ((CodeCoverConditionCoverageHelper_C289 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[531]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[811]++;
int CodeCoverConditionCoverageHelper_C290;
            // If tableSize is not a power of 2 find the closest
            // power of 2 >= the original size.
            if ((((((CodeCoverConditionCoverageHelper_C290 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C290 |= (2)) == 0 || true) &&
 (((tableSize & (tableSize - 1)) != 0) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[533]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[812]++;
int CodeCoverConditionCoverageHelper_C291;
                if ((((((CodeCoverConditionCoverageHelper_C291 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C291 |= (2)) == 0 || true) &&
 ((tableSize > 1 << 30) && 
  ((CodeCoverConditionCoverageHelper_C291 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[535]++;
                    throw new RuntimeException("Property table overflow");
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[536]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[813]++;
                int newSize = INITIAL_SLOT_SIZE;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[814]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[100]++;


int CodeCoverConditionCoverageHelper_C292;
                while ((((((CodeCoverConditionCoverageHelper_C292 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C292 |= (2)) == 0 || true) &&
 ((newSize < tableSize) && 
  ((CodeCoverConditionCoverageHelper_C292 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) && false)) { 
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[100]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[101]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[102]++;
}
                    newSize <<= 1;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[815]++;
  }
                tableSize = newSize;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[816]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[534]++;}
            slots = new Slot[tableSize];
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[817]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[818]++;
            int objectsCount = count;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[819]++;
int CodeCoverConditionCoverageHelper_C293;
            if ((((((CodeCoverConditionCoverageHelper_C293 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C293 |= (2)) == 0 || true) &&
 ((objectsCount < 0) && 
  ((CodeCoverConditionCoverageHelper_C293 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[537]++;
                // "this" was sealed
                objectsCount = ~objectsCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[820]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[538]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[821]++;
            Slot prev = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[822]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[103]++;


int CodeCoverConditionCoverageHelper_C294;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C294 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C294 |= (2)) == 0 || true) &&
 ((i != objectsCount) && 
  ((CodeCoverConditionCoverageHelper_C294 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[103]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[104]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.loops[105]++;
}
                lastAdded = (Slot)in.readObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[823]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[824]++;
int CodeCoverConditionCoverageHelper_C295;
                if ((((((CodeCoverConditionCoverageHelper_C295 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C295 |= (2)) == 0 || true) &&
 ((i==0) && 
  ((CodeCoverConditionCoverageHelper_C295 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[539]++;
                    firstAdded = lastAdded;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[825]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[540]++;
                    prev.orderedNext = lastAdded;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[826]++;
                }
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[827]++;
                int slotIndex = getSlotIndex(tableSize, lastAdded.indexOrHash);
                addKnownAbsentSlot(slots, lastAdded, slotIndex);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[828]++;
                prev = lastAdded;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[829]++;
            }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[532]++;}
    }

    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[830]++;
        Slot slot = getSlot(cx, id, SLOT_QUERY);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[831]++;
int CodeCoverConditionCoverageHelper_C296;
        if ((((((CodeCoverConditionCoverageHelper_C296 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C296 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C296 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[541]++; return null;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[542]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[832]++;
        Scriptable scope = getParentScope();
        return slot.getPropertyDescriptor(cx, (scope == null ? this : scope));
    }

    protected Slot getSlot(Context cx, Object id, int accessType) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[833]++;
        String name = ScriptRuntime.toStringIdOrIndex(cx, id);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[834]++;
int CodeCoverConditionCoverageHelper_C297;
        if ((((((CodeCoverConditionCoverageHelper_C297 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C297 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C297 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[543]++;
            return getSlot(null, ScriptRuntime.lastIndexResult(cx), accessType);

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[544]++;
            return getSlot(name, 0, accessType);
        }
    }

    // Partial implementation of java.util.Map. See NativeObject for
    // a subclass that implements java.util.Map.

    public int size() {
        return count < 0 ? ~count : count;
    }

    public boolean isEmpty() {
        return count == 0 || count == -1;
    }


    public Object get(Object key) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[835]++;
        Object value = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[836]++;
int CodeCoverConditionCoverageHelper_C298;
        if ((((((CodeCoverConditionCoverageHelper_C298 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C298 |= (2)) == 0 || true) &&
 ((key instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C298 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[545]++;
            value = get((String) key, this);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[837]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[546]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[838]++;
int CodeCoverConditionCoverageHelper_C299; if ((((((CodeCoverConditionCoverageHelper_C299 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C299 |= (2)) == 0 || true) &&
 ((key instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C299 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[547]++;
            value = get(((Number) key).intValue(), this);
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[839]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[548]++;}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[840]++;
int CodeCoverConditionCoverageHelper_C300;
        if ((((((CodeCoverConditionCoverageHelper_C300 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C300 |= (8)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C300 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C300 |= (2)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C300 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[549]++;
            return null;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[550]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.statements[841]++;
int CodeCoverConditionCoverageHelper_C301; if ((((((CodeCoverConditionCoverageHelper_C301 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C301 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[551]++;
            return ((Wrapper) value).unwrap();

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9.branches[552]++;
            return value;
        }
}
    }

}

class CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9 ());
  }
    public static long[] statements = new long[842];
    public static long[] branches = new long[553];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[302];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ScriptableObject.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,2,2,2,1,1,1,1,1,1,1,2,3,1,1,1,1,2,2,1,1,1,1,3,3,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,3,1,1,1,2,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,3,3,2,1,1,1,1,1,2,2,1,1,1,2,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,3,1,1,1,1,1,3,1,2,2,1,1,1,1,1,1,1,1,1,1,3,2,1,1,1,1,1,0,1,1,1,1,1,2,1,2,1,2,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1};
    for (int i = 1; i <= 301; i++) {
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

  public CodeCoverCoverageCounter$adralqrs9n89mg8nwisyuefmzu2809rx8uaqvu3734qwkme9 () {
    super("org.mozilla.javascript.RHINO-SRC-ScriptableObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 841; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 552; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 301; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 105; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ScriptableObject.java");
      for (int i = 1; i <= 841; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 552; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 301; i++) {
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

