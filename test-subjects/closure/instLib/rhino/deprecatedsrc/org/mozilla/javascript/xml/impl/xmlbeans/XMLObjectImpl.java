/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.*;

/**
 *  This abstract class describes what all XML objects (XML, XMLList) should have in common.
 *
 * @see XML
 */
abstract class XMLObjectImpl extends XMLObject
{
  static {
    CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.ping();
  }

    private static final Object XMLOBJECT_TAG = "XMLObject";
  static {
    CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[1]++;
  }

    protected final XMLLibImpl lib;
    protected boolean prototypeFlag;

    protected XMLObjectImpl(XMLLibImpl lib, XMLObject prototype)
    {
        super(lib.globalScope(), prototype);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[2]++;
        this.lib = lib;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[3]++;
    }

    /**
     * ecmaHas(cx, id) calls this after resolving when id to XMLName
     * and checking it is not Uint32 index.
     */
    abstract boolean hasXMLProperty(XMLName name);

    /**
     * ecmaGet(cx, id) calls this after resolving when id to XMLName
     * and checking it is not Uint32 index.
     */
    abstract Object getXMLProperty(XMLName name);

    /**
     * ecmaPut(cx, id, value) calls this after resolving when id to XMLName
     * and checking it is not Uint32 index.
     */
    abstract void putXMLProperty(XMLName name, Object value);

    /**
     * ecmaDelete(cx, id) calls this after resolving when id to XMLName
     * and checking it is not Uint32 index.
     */
    abstract void deleteXMLProperty(XMLName name);

    /**
     * Test XML equality with target the target.
     */
    abstract boolean equivalentXml(Object target);

    // Methods from section 12.4.4 in the spec
    abstract XML addNamespace(Namespace ns);
    abstract XML appendChild(Object xml);
    abstract XMLList attribute(XMLName xmlName);
    abstract XMLList attributes();
    abstract XMLList child(long index);
    abstract XMLList child(XMLName xmlName);
    abstract int childIndex();
    abstract XMLList children();
    abstract XMLList comments();
    abstract boolean contains(Object xml);
    abstract Object copy();
    abstract XMLList descendants(XMLName xmlName);
    abstract Object[] inScopeNamespaces();
    abstract XML insertChildAfter(Object child, Object xml);
    abstract XML insertChildBefore(Object child, Object xml);
    abstract boolean hasOwnProperty(XMLName xmlName);
    abstract boolean hasComplexContent();
    abstract boolean hasSimpleContent();
    abstract int length();
    abstract String localName();
    abstract QName name();
    abstract Object namespace(String prefix);
    abstract Object[] namespaceDeclarations();
    abstract Object nodeKind();
    abstract void normalize();
    abstract Object parent();
    abstract XML prependChild(Object xml);
    abstract Object processingInstructions(XMLName xmlName);
    abstract boolean propertyIsEnumerable(Object member);
    abstract XML removeNamespace(Namespace ns);
    abstract XML replace(long index, Object xml);
    abstract XML replace(XMLName name, Object xml);
    abstract XML setChildren(Object xml);
    abstract void setLocalName(String name);
    abstract void setName(QName xmlName);
    abstract void setNamespace(Namespace ns);
    abstract XMLList text();
    public abstract String toString();
    abstract String toSource(int indent);
    abstract String toXMLString(int indent);
    abstract Object valueOf();

    /**
     * Extension to access native implementation from scripts
     */
    abstract org.apache.xmlbeans.XmlObject getXmlObject();

    protected abstract Object jsConstructor(Context cx, boolean inNewExpr,
                                            Object[] args);


    //
    //
    // Methods overriding ScriptableObject
    //
    //

    public final Object getDefaultValue(Class hint)
    {
        return toString();
    }


    /**
     * XMLObject always compare with any value and equivalentValues
     * never returns {@link Scriptable#NOT_FOUND} for them but rather
     * calls equivalentXml(value) and wrap the result as Boolean.
     */
    protected final Object equivalentValues(Object value)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[4]++;
        boolean result = equivalentXml(value);
        return result ? Boolean.TRUE : Boolean.FALSE;
    }

    //
    //
    // Methods overriding XMLObject
    //
    //

    public final XMLLib lib()
    {
        return lib;
    }

    /**
     * Implementation of ECMAScript [[Has]]
     */
    public final boolean has(Context cx, Object id)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[1]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[6]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[2]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[7]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[3]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[9]++;
            long index = ScriptRuntime.lastUint32Result(cx);
            // XXX Fix this cast
            return has((int)index, this);

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[4]++;}
        return hasXMLProperty(xmlName);
    }

    @Override
    public boolean has(String name, Scriptable start) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[10]++;
        Context cx = Context.getCurrentContext();
        return hasXMLProperty(lib.toXMLNameFromString(cx, name));
    }
    /**
     * Implementation of ECMAScript [[Get]]
     */
    @Override
    public final Object get(Context cx, Object id)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[5]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[12]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[6]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[13]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[7]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[15]++;
            long index = ScriptRuntime.lastUint32Result(cx);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[16]++;
            // XXX Fix this cast
            Object result = get((int)index, this);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[9]++;
                result = Undefined.instance;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[18]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[10]++;}
            return result;

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[8]++;}
        return getXMLProperty(xmlName);
    }

    @Override
    public Object get(String name, Scriptable start) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[19]++;
        Context cx = Context.getCurrentContext();
        return getXMLProperty(lib.toXMLNameFromString(cx, name));
    }
    /**
     * Implementation of ECMAScript [[Put]]
     */
    @Override
    public final void put(Context cx, Object id, Object value)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[11]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[21]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[12]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[22]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[13]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[24]++;
            long index = ScriptRuntime.lastUint32Result(cx);
            // XXX Fix this cast
            put((int)index, this, value);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[25]++;
            return;

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[14]++;}
        putXMLProperty(xmlName, value);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[26]++;
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[27]++;
        Context cx = Context.getCurrentContext();
        putXMLProperty(lib.toXMLNameFromString(cx, name), value);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[28]++;
    }
    /**
     * Implementation of ECMAScript [[Delete]].
     */
    @Override
    public final boolean delete(Context cx, Object id)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[15]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[30]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[16]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[31]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[17]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[33]++;
            long index = ScriptRuntime.lastUint32Result(cx);
            // XXX Fix this
            delete((int)index);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[34]++;
            return true;

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[18]++;}
        deleteXMLProperty(xmlName);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[35]++;
        return true;
    }

    @Override
    public void delete(String name) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[36]++;
        Context cx = Context.getCurrentContext();
        deleteXMLProperty(lib.toXMLNameFromString(cx, name));
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[37]++;
    }

    @Override
    public Object getFunctionProperty(Context cx, int id) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((prototypeFlag) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[19]++;
            return super.get(id, this);

        } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[20]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[39]++;
            Scriptable proto = getPrototype();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((proto instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[21]++;
                return ((XMLObject)proto).getFunctionProperty(cx, id);

            } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[22]++;}
        }
        return NOT_FOUND;
    }

    @Override
    public Object getFunctionProperty(Context cx, String name) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((prototypeFlag) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[23]++;
            return super.get(name, this);

        } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[24]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[42]++;
            Scriptable proto = getPrototype();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((proto instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[25]++;
                return ((XMLObject)proto).getFunctionProperty(cx, name);

            } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[26]++;}
        }
        return NOT_FOUND;
    }

    public Ref memberRef(Context cx, Object elem, int memberTypeFlags)
    {
        XMLName xmlName;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.ATTRIBUTE_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[27]++;
            xmlName = lib.toAttributeName(cx, elem);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[45]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[28]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[46]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.DESCENDANTS_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[29]++;
                // Code generation would use ecma(Get|Has|Delete|Set) for
                // normal name idenrifiers so one ATTRIBUTE_FLAG
                // or DESCENDANTS_FLAG has to be set
                throw Kit.codeBug();

            } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[30]++;}
            xmlName = lib.toXMLName(cx, elem);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[47]++;
        }
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[48]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.DESCENDANTS_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[31]++;
            xmlName.setIsDescendants();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[49]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[32]++;}
        xmlName.initXMLObject(this);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[50]++;
        return xmlName;
    }

    /**
     * Generic reference to implement x::ns, x.@ns::y, x..@ns::y etc.
     */
    public Ref memberRef(Context cx, Object namespace, Object elem,
                         int memberTypeFlags)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[51]++;
        XMLName xmlName = lib.toQualifiedName(cx, namespace, elem);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[52]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.ATTRIBUTE_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[33]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[53]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((xmlName.isAttributeName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[35]++;
                xmlName.setAttributeName();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[54]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[34]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[55]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.DESCENDANTS_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[37]++;
            xmlName.setIsDescendants();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[56]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[38]++;}
        xmlName.initXMLObject(this);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[57]++;
        return xmlName;
    }

    public NativeWith enterWith(Scriptable scope)
    {
        return new XMLWithScope(lib, scope, this);
    }

    public NativeWith enterDotQuery(Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[58]++;
        XMLWithScope xws = new XMLWithScope(lib, scope, this);
        xws.initAsDotQuery();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[59]++;
        return xws;
    }

    public final Object addValues(Context cx, boolean thisIsLeft,
                                     Object value)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[60]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((value instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[39]++;
            XMLObject v1, v2;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[61]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((thisIsLeft) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[41]++;
                v1 = this;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[62]++;
                v2 = (XMLObject)value;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[63]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[42]++;
                v1 = (XMLObject)value;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[64]++;
                v2 = this;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[65]++;
            }
            return lib.addXMLObjects(cx, v1, v2);

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[40]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[66]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[43]++;
            // both "xml + undefined" and "undefined + xml" gives String(xml)
            return ScriptRuntime.toString(this);

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[44]++;}

        return super.addValues(cx, thisIsLeft, value);
    }

    //
    //
    // IdScriptableObject machinery
    //
    //

    final void exportAsJSClass(boolean sealed)
    {
        prototypeFlag = true;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[67]++;
        exportAsJSClass(MAX_PROTOTYPE_ID, lib.globalScope(), sealed);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[68]++;
    }

// #string_id_map#
    private final static int
        Id_constructor             = 1,

        Id_addNamespace            = 2,
        Id_appendChild             = 3,
        Id_attribute               = 4,
        Id_attributes              = 5,
        Id_child                   = 6,
        Id_childIndex              = 7,
        Id_children                = 8,
        Id_comments                = 9,
        Id_contains                = 10,
        Id_copy                    = 11,
        Id_descendants             = 12,
        Id_inScopeNamespaces       = 13,
        Id_insertChildAfter        = 14,
        Id_insertChildBefore       = 15,
        Id_hasOwnProperty          = 16,
        Id_hasComplexContent       = 17,
        Id_hasSimpleContent        = 18,
        Id_length                  = 19,
        Id_localName               = 20,
        Id_name                    = 21,
        Id_namespace               = 22,
        Id_namespaceDeclarations   = 23,
        Id_nodeKind                = 24,
        Id_normalize               = 25,
        Id_parent                  = 26,
        Id_prependChild            = 27,
        Id_processingInstructions  = 28,
        Id_propertyIsEnumerable    = 29,
        Id_removeNamespace         = 30,
        Id_replace                 = 31,
        Id_setChildren             = 32,
        Id_setLocalName            = 33,
        Id_setName                 = 34,
        Id_setNamespace            = 35,
        Id_text                    = 36,
        Id_toString                = 37,
        Id_toSource                = 38,
        Id_toXMLString             = 39,
        Id_valueOf                 = 40,

        Id_getXmlObject            = 41,

        MAX_PROTOTYPE_ID           = 41;
  static {
    CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[69]++;
  }

    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2004-11-10 15:38:11 CET
        L0: { id = 0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[70]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[71]++; String X = null; int c;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[72]++;
            L: switch (s.length()) {
            case 4:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[45]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[73]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[74]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[46]++; X="copy";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[75]++;id=Id_copy;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[76]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[47]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[77]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[48]++; X="name";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[78]++;id=Id_name;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[79]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[49]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[80]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[50]++; X="text";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[81]++;id=Id_text;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[82]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[51]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[83]++;
                break L;
            case 5:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[52]++; X="child";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[84]++;id=Id_child;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[85]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[86]++; break L;
            case 6:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[53]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[87]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[88]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((c=='l') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[54]++; X="length";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[89]++;id=Id_length;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[90]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[55]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[91]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[56]++; X="parent";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[92]++;id=Id_parent;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[93]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[57]++;}
}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[94]++;
                break L;
            case 7:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[58]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[95]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[96]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[59]++; X="replace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[97]++;id=Id_replace;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[98]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[60]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[99]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[61]++; X="setName";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[100]++;id=Id_setName;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[101]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[62]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[102]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c=='v') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[63]++; X="valueOf";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[103]++;id=Id_valueOf;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[104]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[64]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[105]++;
                break L;
            case 8:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[65]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[106]++; switch (s.charAt(4)) {
                case 'K':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[66]++; X="nodeKind";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[107]++;id=Id_nodeKind;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[108]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[109]++; break L;
                case 'a':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[67]++; X="contains";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[110]++;id=Id_contains;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[111]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[112]++; break L;
                case 'd':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[68]++; X="children";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[113]++;id=Id_children;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[114]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[115]++; break L;
                case 'e':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[69]++; X="comments";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[116]++;id=Id_comments;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[117]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[118]++; break L;
                case 'r':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[70]++; X="toString";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[119]++;id=Id_toString;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[120]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[121]++; break L;
                case 'u':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[71]++; X="toSource";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[122]++;id=Id_toSource;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[123]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[124]++; break L; default : CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[72]++;
                }
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[125]++; break L;
            case 9:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[73]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[126]++; switch (s.charAt(2)) {
                case 'c':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[74]++; X="localName";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[127]++;id=Id_localName;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[128]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[129]++; break L;
                case 'm':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[75]++; X="namespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[130]++;id=Id_namespace;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[131]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[132]++; break L;
                case 'r':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[76]++; X="normalize";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[133]++;id=Id_normalize;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[134]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[135]++; break L;
                case 't':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[77]++; X="attribute";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[136]++;id=Id_attribute;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[137]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[138]++; break L; default : CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[78]++;
                }
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[139]++; break L;
            case 10:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[79]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[140]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[141]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((c=='a') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[80]++; X="attributes";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[142]++;id=Id_attributes;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[143]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[81]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[144]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[82]++; X="childIndex";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[145]++;id=Id_childIndex;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[146]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[83]++;}
}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[147]++;
                break L;
            case 11:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[84]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[148]++; switch (s.charAt(0)) {
                case 'a':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[85]++; X="appendChild";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[149]++;id=Id_appendChild;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[150]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[151]++; break L;
                case 'c':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[86]++; X="constructor";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[152]++;id=Id_constructor;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[153]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[154]++; break L;
                case 'd':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[87]++; X="descendants";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[155]++;id=Id_descendants;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[156]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[157]++; break L;
                case 's':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[88]++; X="setChildren";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[158]++;id=Id_setChildren;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[159]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[160]++; break L;
                case 't':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[89]++; X="toXMLString";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[161]++;id=Id_toXMLString;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[162]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[163]++; break L; default : CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[90]++;
                }
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[164]++; break L;
            case 12:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[91]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[165]++; switch (s.charAt(0)) {
                case 'a':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[92]++; X="addNamespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[166]++;id=Id_addNamespace;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[167]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[168]++; break L;
                case 'g':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[93]++; X="getXmlObject";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[169]++;id=Id_getXmlObject;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[170]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[171]++; break L;
                case 'p':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[94]++; X="prependChild";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[172]++;id=Id_prependChild;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[173]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[174]++; break L;
                case 's':
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[95]++; c=s.charAt(3);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[175]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[176]++;
int CodeCoverConditionCoverageHelper_C33;
                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((c=='L') && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[96]++; X="setLocalName";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[177]++;id=Id_setLocalName;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[178]++;
 }
                    else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[97]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[179]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((c=='N') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[98]++; X="setNamespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[180]++;id=Id_setNamespace;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[181]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[99]++;}
}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[182]++;
                    break L; default : CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[100]++;
                }
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[183]++; break L;
            case 14:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[101]++; X="hasOwnProperty";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[184]++;id=Id_hasOwnProperty;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[185]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[186]++; break L;
            case 15:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[102]++; X="removeNamespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[187]++;id=Id_removeNamespace;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[188]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[189]++; break L;
            case 16:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[103]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[190]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[191]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((c=='h') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[104]++; X="hasSimpleContent";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[192]++;id=Id_hasSimpleContent;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[193]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[105]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[194]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[106]++; X="insertChildAfter";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[195]++;id=Id_insertChildAfter;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[196]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[107]++;}
}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[197]++;
                break L;
            case 17:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[108]++; c=s.charAt(3);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[198]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[199]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c=='C') && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[109]++; X="hasComplexContent";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[200]++;id=Id_hasComplexContent;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[201]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[110]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[202]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[111]++; X="inScopeNamespaces";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[203]++;id=Id_inScopeNamespaces;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[204]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[112]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[205]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[113]++; X="insertChildBefore";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[206]++;id=Id_insertChildBefore;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[207]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[114]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[208]++;
                break L;
            case 20:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[115]++; X="propertyIsEnumerable";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[209]++;id=Id_propertyIsEnumerable;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[210]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[211]++; break L;
            case 21:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[116]++; X="namespaceDeclarations";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[212]++;id=Id_namespaceDeclarations;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[213]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[214]++; break L;
            case 22:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[117]++; X="processingInstructions";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[215]++;id=Id_processingInstructions;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[216]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[217]++; break L; default : CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[118]++;
            }
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[218]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[119]++; id = 0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[219]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[120]++;}
        }
// #/generated#
        return id;
    }
// #/string_id_map#

    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[220]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[121]++; {
            IdFunctionObject ctor;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[221]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[122]++;
                ctor = new XMLCtor((XML)this, XMLOBJECT_TAG, id, 1);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[222]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[123]++;
                ctor = new IdFunctionObject(this, XMLOBJECT_TAG, id, 1);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[223]++;
            }
            initPrototypeConstructor(ctor);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[224]++;
            return;
          }

          case Id_addNamespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[124]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[225]++; s="addNamespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[226]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[227]++;      break;
          case Id_appendChild:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[125]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[228]++; s="appendChild";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[229]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[230]++;       break;
          case Id_attribute:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[126]++;         arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[231]++; s="attribute";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[232]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[233]++;         break;
          case Id_attributes:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[127]++;        arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[234]++; s="attributes";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[235]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[236]++;        break;
          case Id_child:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[128]++;             arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[237]++; s="child";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[238]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[239]++;             break;
          case Id_childIndex:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[129]++;        arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[240]++; s="childIndex";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[241]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[242]++;        break;
          case Id_children:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[130]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[243]++; s="children";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[244]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[245]++;          break;
          case Id_comments:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[131]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[246]++; s="comments";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[247]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[248]++;          break;
          case Id_contains:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[132]++;          arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[249]++; s="contains";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[250]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[251]++;          break;
          case Id_copy:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[133]++;              arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[252]++; s="copy";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[253]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[254]++;              break;
          case Id_descendants:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[134]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[255]++; s="descendants";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[256]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[257]++;       break;
          case Id_hasComplexContent:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[135]++; arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[258]++; s="hasComplexContent";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[259]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[260]++; break;
          case Id_hasOwnProperty:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[136]++;    arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[261]++; s="hasOwnProperty";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[262]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[263]++;    break;
          case Id_hasSimpleContent:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[137]++;  arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[264]++; s="hasSimpleContent";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[265]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[266]++;  break;
          case Id_inScopeNamespaces:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[138]++; arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[267]++; s="inScopeNamespaces";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[268]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[269]++; break;
          case Id_insertChildAfter:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[139]++;  arity=2;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[270]++; s="insertChildAfter";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[271]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[272]++;  break;
          case Id_insertChildBefore:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[140]++; arity=2;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[273]++; s="insertChildBefore";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[274]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[275]++; break;
          case Id_length:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[141]++;            arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[276]++; s="length";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[277]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[278]++;            break;
          case Id_localName:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[142]++;         arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[279]++; s="localName";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[280]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[281]++;         break;
          case Id_name:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[143]++;              arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[282]++; s="name";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[283]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[284]++;              break;
          case Id_namespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[144]++;         arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[285]++; s="namespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[286]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[287]++;         break;
          case Id_namespaceDeclarations:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[145]++;
            arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[288]++; s="namespaceDeclarations";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[289]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[290]++; break;
          case Id_nodeKind:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[146]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[291]++; s="nodeKind";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[292]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[293]++;          break;
          case Id_normalize:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[147]++;         arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[294]++; s="normalize";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[295]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[296]++;         break;
          case Id_parent:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[148]++;            arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[297]++; s="parent";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[298]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[299]++;            break;
          case Id_prependChild:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[149]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[300]++; s="prependChild";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[301]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[302]++;      break;
          case Id_processingInstructions:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[150]++;
            arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[303]++; s="processingInstructions";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[304]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[305]++; break;
          case Id_propertyIsEnumerable:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[151]++;
            arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[306]++; s="propertyIsEnumerable";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[307]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[308]++; break;
          case Id_removeNamespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[152]++;   arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[309]++; s="removeNamespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[310]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[311]++;   break;
          case Id_replace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[153]++;           arity=2;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[312]++; s="replace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[313]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[314]++;           break;
          case Id_setChildren:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[154]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[315]++; s="setChildren";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[316]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[317]++;       break;
          case Id_setLocalName:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[155]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[318]++; s="setLocalName";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[319]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[320]++;      break;
          case Id_setName:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[156]++;           arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[321]++; s="setName";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[322]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[323]++;           break;
          case Id_setNamespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[157]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[324]++; s="setNamespace";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[325]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[326]++;      break;
          case Id_text:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[158]++;              arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[327]++; s="text";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[328]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[329]++;              break;
          case Id_toString:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[159]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[330]++; s="toString";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[331]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[332]++;          break;
          case Id_toSource:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[160]++;          arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[333]++; s="toSource";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[334]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[335]++;          break;
          case Id_toXMLString:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[161]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[336]++; s="toXMLString";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[337]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[338]++;       break;
          case Id_valueOf:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[162]++;           arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[339]++; s="valueOf";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[340]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[341]++;           break;

          case Id_getXmlObject:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[163]++;      arity=0;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[342]++; s="getXmlObject";
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[343]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[344]++;      break;
          default:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[164]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(XMLOBJECT_TAG, id, s, arity);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[345]++;
    }

    /**
     *
     * @param f
     * @param cx
     * @param scope
     * @param thisObj
     * @param args
     * @return
     */
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[346]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((f.hasTag(XMLOBJECT_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[165]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[166]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[347]++;
        int id = f.methodId();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[348]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((id == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[167]++;
            return jsConstructor(cx, thisObj == null, args);

        } else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[168]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[349]++;
int CodeCoverConditionCoverageHelper_C44;

        // All (XML|XMLList).prototype methods require thisObj to be XML
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((thisObj instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[169]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[170]++;}
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[350]++;
        XMLObjectImpl realThis = (XMLObjectImpl)thisObj;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[351]++;

        switch (id) {
          case Id_addNamespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[171]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[352]++;
            Namespace ns = lib.castToNamespace(cx, arg(args, 0));
            return realThis.addNamespace(ns);
          }
          case Id_appendChild:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[172]++;
            return realThis.appendChild(arg(args, 0));
          case Id_attribute:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[173]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[353]++;
            XMLName xmlName = lib.toAttributeName(cx, arg(args, 0));
            return realThis.attribute(xmlName);
          }
          case Id_attributes:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[174]++;
            return realThis.attributes();
          case Id_child:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[175]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[354]++;
            XMLName xmlName = lib.toXMLNameOrIndex(cx, arg(args, 0));
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[355]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[176]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[356]++;
                long index = ScriptRuntime.lastUint32Result(cx);
                return realThis.child(index);

            } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[177]++;
                return realThis.child(xmlName);
            }
          }
          case Id_childIndex:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[178]++;
            return ScriptRuntime.wrapInt(realThis.childIndex());
          case Id_children:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[179]++;
            return realThis.children();
          case Id_comments:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[180]++;
            return realThis.comments();
          case Id_contains:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[181]++;
            return ScriptRuntime.wrapBoolean(
                       realThis.contains(arg(args, 0)));
          case Id_copy:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[182]++;
            return realThis.copy();
          case Id_descendants:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[183]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[357]++;
            XMLName xmlName = (args.length == 0)
                              ? XMLName.formStar()
                              : lib.toXMLName(cx, args[0]);
            return realThis.descendants(xmlName);
          }
          case Id_inScopeNamespaces:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[184]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[358]++;
            Object[] array = realThis.inScopeNamespaces();
            return cx.newArray(scope, array);
          }
          case Id_insertChildAfter:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[185]++;
            return realThis.insertChildAfter(arg(args, 0), arg(args, 1));
          case Id_insertChildBefore:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[186]++;
            return realThis.insertChildBefore(arg(args, 0), arg(args, 1));
          case Id_hasOwnProperty:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[187]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[359]++;
            XMLName xmlName = lib.toXMLName(cx, arg(args, 0));
            return ScriptRuntime.wrapBoolean(
                       realThis.hasOwnProperty(xmlName));
          }
          case Id_hasComplexContent:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[188]++;
            return ScriptRuntime.wrapBoolean(realThis.hasComplexContent());
          case Id_hasSimpleContent:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[189]++;
            return ScriptRuntime.wrapBoolean(realThis.hasSimpleContent());
          case Id_length:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[190]++;
            return ScriptRuntime.wrapInt(realThis.length());
          case Id_localName:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[191]++;
            return realThis.localName();
          case Id_name:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[192]++;
            return realThis.name();
          case Id_namespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[193]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[360]++;
            String prefix = (args.length > 0)
                            ? ScriptRuntime.toString(args[0]) : null;
            return realThis.namespace(prefix);
          }
          case Id_namespaceDeclarations:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[194]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[361]++;
            Object[] array = realThis.namespaceDeclarations();
            return cx.newArray(scope, array);
          }
          case Id_nodeKind:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[195]++;
            return realThis.nodeKind();
          case Id_normalize:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[196]++;
            realThis.normalize();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[362]++;
            return Undefined.instance;
          case Id_parent:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[197]++;
            return realThis.parent();
          case Id_prependChild:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[198]++;
            return realThis.prependChild(arg(args, 0));
          case Id_processingInstructions:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[199]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[363]++;
            XMLName xmlName = (args.length > 0)
                              ? lib.toXMLName(cx, args[0])
                              : XMLName.formStar();
            return realThis.processingInstructions(xmlName);
          }
          case Id_propertyIsEnumerable:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[200]++; {
            return ScriptRuntime.wrapBoolean(
                       realThis.propertyIsEnumerable(arg(args, 0)));
          }
          case Id_removeNamespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[201]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[364]++;
            Namespace ns = lib.castToNamespace(cx, arg(args, 0));
            return realThis.removeNamespace(ns);
          }
          case Id_replace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[202]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[365]++;
            XMLName xmlName = lib.toXMLNameOrIndex(cx, arg(args, 0));
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[366]++;
            Object arg1 = arg(args, 1);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[367]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[203]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[368]++;
                long index = ScriptRuntime.lastUint32Result(cx);
                return realThis.replace(index, arg1);

            } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[204]++;
                return realThis.replace(xmlName, arg1);
            }
          }
          case Id_setChildren:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[205]++;
            return realThis.setChildren(arg(args, 0));
          case Id_setLocalName:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[206]++; {
            String localName;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[369]++;
            Object arg = arg(args, 0);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[370]++;
int CodeCoverConditionCoverageHelper_C47;
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((arg instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[207]++;
                localName = ((QName)arg).localName();
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[371]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[208]++;
                localName = ScriptRuntime.toString(arg);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[372]++;
            }
            realThis.setLocalName(localName);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[373]++;
            return Undefined.instance;
          }
          case Id_setName:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[209]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[374]++;
            Object arg = (args.length != 0) ? args[0] : Undefined.instance;
            QName qname;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[375]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((arg instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[210]++;
                qname = (QName)arg;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[376]++;
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[377]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((qname.uri() == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[212]++;
                    qname = lib.constructQNameFromString(cx, qname.localName());
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[378]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[213]++;
                    // E4X 13.4.4.35 requires to always construct QName
                    qname = lib.constructQName(cx, qname);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[379]++;
                }

            } else {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[211]++;
                qname = lib.constructQName(cx, arg);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[380]++;
            }
            realThis.setName(qname);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[381]++;
            return Undefined.instance;
          }
          case Id_setNamespace:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[214]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[382]++;
            Namespace ns = lib.castToNamespace(cx, arg(args, 0));
            realThis.setNamespace(ns);
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[383]++;
            return Undefined.instance;
          }
          case Id_text:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[215]++;
            return realThis.text();
          case Id_toString:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[216]++;
            return realThis.toString();
          case Id_toSource:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[217]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[384]++;
            int indent = ScriptRuntime.toInt32(args, 0);
            return realThis.toSource(indent);
          }
          case Id_toXMLString:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[218]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[385]++;
            int indent = ScriptRuntime.toInt32(args, 0);
            return realThis.toXMLString(indent);
          }
          case Id_valueOf:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[219]++;
            return realThis.valueOf();

          case Id_getXmlObject:
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[220]++; {
CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.statements[386]++;
            org.apache.xmlbeans.XmlObject xmlObject = realThis.getXmlObject();
            return Context.javaToJS(xmlObject, scope);
          } default : CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl.branches[221]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static Object arg(Object[] args, int i)
    {
        return (i < args.length) ? args[i] : Undefined.instance;
    }

}

class CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl ());
  }
    public static long[] statements = new long[387];
    public static long[] branches = new long[222];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[50];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLObjectImpl.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 49; i++) {
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

  public CodeCoverCoverageCounter$11f1r6z5f9ylnjwzp6dorx7v8x5cjic149qcl076jlnl () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLObjectImpl.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 386; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 221; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 49; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-XMLObjectImpl.java");
      for (int i = 1; i <= 386; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 221; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 49; i++) {
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
