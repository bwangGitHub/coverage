/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.*;
import org.mozilla.javascript.xml.*;

/**
 *  This abstract class describes what all XML objects (XML, XMLList) should
 *  have in common.
 *
 * @see XML
 */
abstract class XMLObjectImpl extends XMLObject {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.ping();
  }

    private static final Object XMLOBJECT_TAG = "XMLObject";
  static {
    CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[1]++;
  }
    private XMLLibImpl lib;
    private boolean prototypeFlag;

    protected XMLObjectImpl(XMLLibImpl lib, Scriptable scope,
                            XMLObject prototype)
    {
        initialize(lib, scope, prototype);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[2]++;
    }

    final void initialize(XMLLibImpl lib, Scriptable scope,
                          XMLObject prototype)
    {
        setParentScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[3]++;
        setPrototype(prototype);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[4]++;
        prototypeFlag = (prototype == null);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[5]++;
        this.lib = lib;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[6]++;
    }

    final boolean isPrototype() {
        return prototypeFlag;
    }

    XMLLibImpl getLib() {
        return lib;
    }

    final XML newXML(XmlNode node) {
        return lib.newXML(node);
    }

    XML xmlFromNode(XmlNode node) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node.getXml() == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[1]++;
            node.setXml( newXML(node) );
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[8]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[2]++;}
        return node.getXml();
    }

    final XMLList newXMLList() {
        return lib.newXMLList();
    }

    final XMLList newXMLListFrom(Object o) {
        return lib.newXMLListFrom(o);
    }

    final XmlProcessor getProcessor() {
        return lib.getProcessor();
    }

    final QName newQName(String uri, String localName, String prefix) {
        return lib.newQName(uri, localName, prefix);
    }

    final QName newQName(XmlNode.QName name) {
        return lib.newQName(name);
    }

    final Namespace createNamespace(XmlNode.Namespace declaration) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((declaration == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[3]++; return null;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[4]++;}
        return lib.createNamespaces( new XmlNode.Namespace[] { declaration } )[0];
    }

    final Namespace[] createNamespaces(XmlNode.Namespace[] declarations) {
        return lib.createNamespaces(declarations);
    }


    @Override
    public final Scriptable getPrototype() {
        return super.getPrototype();
    }

    @Override
    public final void setPrototype(Scriptable prototype) {
        super.setPrototype(prototype);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[10]++;
    }

    @Override
    public final Scriptable getParentScope() {
        return super.getParentScope();
    }

    @Override
    public final void setParentScope(Scriptable parent) {
        super.setParentScope(parent);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[11]++;
    }

    @Override
    public final Object getDefaultValue(Class<?> hint) {
        return this.toString();
    }

    @Override
    public final boolean hasInstance(Scriptable scriptable) {
        return super.hasInstance(scriptable);
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

    abstract void addMatches(XMLList rv, XMLName name);

    private XMLList getMatches(XMLName name) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[12]++;
        XMLList rv = newXMLList();
        addMatches(rv, name);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[13]++;
        return rv;
    }

    abstract XML getXML();

    // Methods from section 12.4.4 in the spec
    abstract XMLList child(int index);
    abstract XMLList child(XMLName xmlName);
    abstract XMLList children();
    abstract XMLList comments();
    abstract boolean contains(Object xml);
    abstract XMLObjectImpl copy();
    abstract XMLList elements(XMLName xmlName);
    abstract boolean hasOwnProperty(XMLName xmlName);
    abstract boolean hasComplexContent();
    abstract boolean hasSimpleContent();
    abstract int length();
    abstract void normalize();
    abstract Object parent();
    abstract XMLList processingInstructions(XMLName xmlName);
    abstract boolean propertyIsEnumerable(Object member);
    abstract XMLList text();
    @Override
    public abstract String toString();
    abstract String toSource(int indent);
    abstract String toXMLString();
    abstract Object valueOf();

    protected abstract Object jsConstructor(Context cx, boolean inNewExpr, Object[] args);

    //
    //
    // Methods overriding ScriptableObject
    //
    //

    /**
     * XMLObject always compare with any value and equivalentValues
     * never returns {@link Scriptable#NOT_FOUND} for them but rather
     * calls equivalentXml(value) and wrap the result as Boolean.
     */
    @Override
    protected final Object equivalentValues(Object value) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[14]++;
        boolean result = equivalentXml(value);
        return result ? Boolean.TRUE : Boolean.FALSE;
    }

    //
    //
    // Methods overriding XMLObject
    //
    //

    /**
     * Implementation of ECMAScript [[Has]]
     */
    @Override
    public final boolean has(Context cx, Object id) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[5]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[16]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[6]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[17]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[7]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[19]++;
            long index = ScriptRuntime.lastUint32Result(cx);
            // XXX Fix this cast
            return has((int)index, this);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[8]++;}
        return hasXMLProperty(xmlName);
    }

    @Override
    public boolean has(String name, Scriptable start) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[20]++;
        Context cx = Context.getCurrentContext();
        return hasXMLProperty(lib.toXMLNameFromString(cx, name));
    }
    /**
     * Implementation of ECMAScript [[Get]]
     */
    @Override
    public final Object get(Context cx, Object id) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[9]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[22]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[10]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[23]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[11]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[25]++;
            long index = ScriptRuntime.lastUint32Result(cx);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[26]++;
            // XXX Fix this cast
            Object result = get((int)index, this);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[13]++;
                result = Undefined.instance;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[28]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[14]++;}
            return result;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[12]++;}
        return getXMLProperty(xmlName);
    }

    @Override
    public Object get(String name, Scriptable start) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[29]++;
        Context cx = Context.getCurrentContext();
        return getXMLProperty(lib.toXMLNameFromString(cx, name));
    }
    /**
     * Implementation of ECMAScript [[Put]]
     */
    @Override
    public final void put(Context cx, Object id, Object value) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[15]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[31]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[16]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[32]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[17]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[34]++;
            long index = ScriptRuntime.lastUint32Result(cx);
            // XXX Fix this cast
            put((int)index, this, value);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[35]++;
            return;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[18]++;}
        putXMLProperty(xmlName, value);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[36]++;
    }

   @Override
    public void put(String name, Scriptable start, Object value) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[37]++;
        Context cx = Context.getCurrentContext();
        putXMLProperty(lib.toXMLNameFromString(cx, name), value);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[38]++;
    }
    /**
     * Implementation of ECMAScript [[Delete]].
     */
    @Override
    public final boolean delete(Context cx, Object id) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[19]++; cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[40]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[20]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[41]++;
        XMLName xmlName = lib.toXMLNameOrIndex(cx, id);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[21]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[43]++;
            long index = ScriptRuntime.lastUint32Result(cx);
            // XXX Fix this
            delete((int)index);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[44]++;
            return true;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[22]++;}
        deleteXMLProperty(xmlName);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[45]++;
        return true;
    }


    @Override
    public void delete(String name) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[46]++;
        Context cx = Context.getCurrentContext();
        deleteXMLProperty(lib.toXMLNameFromString(cx, name));
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[47]++;
    }

    @Override
    public Object getFunctionProperty(Context cx, int id) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[23]++;
            return super.get(id, this);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[24]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[49]++;
            Scriptable proto = getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[50]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((proto instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[25]++;
                return ((XMLObject)proto).getFunctionProperty(cx, id);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[26]++;}
        }
        return NOT_FOUND;
    }

    @Override
    public Object getFunctionProperty(Context cx, String name) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((isPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[27]++;
            return super.get(name, this);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[28]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[52]++;
            Scriptable proto = getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((proto instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[29]++;
                return ((XMLObject)proto).getFunctionProperty(cx, name);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[30]++;}
        }
        return NOT_FOUND;
    }

    //    TODO    Can this be made more strongly typed?
    @Override
    public Ref memberRef(Context cx, Object elem, int memberTypeFlags) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[54]++;
        boolean attribute = (memberTypeFlags & Node.ATTRIBUTE_FLAG) != 0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[55]++;
        boolean descendants = (memberTypeFlags & Node.DESCENDANTS_FLAG) != 0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((attribute) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((descendants) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[31]++;
            // Code generation would use ecma(Get|Has|Delete|Set) for
            // normal name identifiers so one ATTRIBUTE_FLAG
            // or DESCENDANTS_FLAG has to be set
            throw Kit.codeBug();

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[32]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[57]++;
        XmlNode.QName qname = lib.toNodeQName(cx, elem, attribute);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[58]++;
        XMLName rv = XMLName.create(qname, attribute, descendants);
        rv.initXMLObject(this);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[59]++;
        return rv;
    }

    /**
     * Generic reference to implement x::ns, x.@ns::y, x..@ns::y etc.
     */
    @Override
    public Ref memberRef(Context cx, Object namespace, Object elem,
            int memberTypeFlags)
    {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[60]++;
        boolean attribute = (memberTypeFlags & Node.ATTRIBUTE_FLAG) != 0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[61]++;
        boolean descendants = (memberTypeFlags & Node.DESCENDANTS_FLAG) != 0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[62]++;
        XMLName rv = XMLName.create(lib.toNodeQName(cx, namespace, elem),
                attribute, descendants);
        rv.initXMLObject(this);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[63]++;
        return rv;
    }

    @Override
    public NativeWith enterWith(Scriptable scope) {
        return new XMLWithScope(lib, scope, this);
    }

    @Override
    public NativeWith enterDotQuery(Scriptable scope) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[64]++;
        XMLWithScope xws = new XMLWithScope(lib, scope, this);
        xws.initAsDotQuery();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[65]++;
        return xws;
    }

    @Override
    public final Object addValues(Context cx, boolean thisIsLeft,
        Object value) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[33]++;
            XMLObject v1, v2;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((thisIsLeft) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[35]++;
                v1 = this;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[68]++;
                v2 = (XMLObject)value;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[69]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[36]++;
                v1 = (XMLObject)value;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[70]++;
                v2 = this;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[71]++;
            }
            return lib.addXMLObjects(cx, v1, v2);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[34]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[72]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[37]++;
            // both "xml + undefined" and "undefined + xml" gives String(xml)
            return ScriptRuntime.toString(this);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[38]++;}

        return super.addValues(cx, thisIsLeft, value);
    }

    //
    //
    // IdScriptableObject machinery
    //
    //

    final void exportAsJSClass(boolean sealed) {
        prototypeFlag = true;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[73]++;
        exportAsJSClass(MAX_PROTOTYPE_ID, getParentScope(), sealed);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[74]++;
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
        Id_elements                = 13,
        Id_inScopeNamespaces       = 14,
        Id_insertChildAfter        = 15,
        Id_insertChildBefore       = 16,
        Id_hasOwnProperty          = 17,
        Id_hasComplexContent       = 18,
        Id_hasSimpleContent        = 19,
        Id_length                  = 20,
        Id_localName               = 21,
        Id_name                    = 22,
        Id_namespace               = 23,
        Id_namespaceDeclarations   = 24,
        Id_nodeKind                = 25,
        Id_normalize               = 26,
        Id_parent                  = 27,
        Id_prependChild            = 28,
        Id_processingInstructions  = 29,
        Id_propertyIsEnumerable    = 30,
        Id_removeNamespace         = 31,
        Id_replace                 = 32,
        Id_setChildren             = 33,
        Id_setLocalName            = 34,
        Id_setName                 = 35,
        Id_setNamespace            = 36,
        Id_text                    = 37,
        Id_toString                = 38,
        Id_toSource                = 39,
        Id_toXMLString             = 40,
        Id_valueOf                 = 41,

        MAX_PROTOTYPE_ID           = 41;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[75]++;
  }

    @Override
    protected int findPrototypeId(String s) {
        int id;
// #generated# Last update: 2008-10-21 12:32:31 MESZ
        L0: { id = 0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[76]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[77]++; String X = null; int c;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[78]++;
            L: switch (s.length()) {
            case 4:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[39]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[79]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[80]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[40]++; X="copy";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[81]++;id=Id_copy;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[82]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[41]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[83]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((c=='n') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[42]++; X="name";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[84]++;id=Id_name;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[85]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[43]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[86]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[44]++; X="text";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[87]++;id=Id_text;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[88]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[45]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[89]++;
                break L;
            case 5:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[46]++; X="child";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[90]++;id=Id_child;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[91]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[92]++; break L;
            case 6:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[47]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[93]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[94]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((c=='l') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[48]++; X="length";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[95]++;id=Id_length;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[96]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[49]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[97]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[50]++; X="parent";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[98]++;id=Id_parent;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[99]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[51]++;}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[100]++;
                break L;
            case 7:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[52]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[101]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[102]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[53]++; X="replace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[103]++;id=Id_replace;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[104]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[54]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[105]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[55]++; X="setName";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[106]++;id=Id_setName;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[107]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[56]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[108]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c=='v') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[57]++; X="valueOf";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[109]++;id=Id_valueOf;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[110]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[58]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[111]++;
                break L;
            case 8:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[59]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[112]++; switch (s.charAt(2)) {
                case 'S':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[60]++; c=s.charAt(7);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[113]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[114]++;
int CodeCoverConditionCoverageHelper_C28;
                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[61]++; X="toSource";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[115]++;id=Id_toSource;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[116]++;
 }
                    else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[62]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[117]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((c=='g') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[63]++; X="toString";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[118]++;id=Id_toString;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[119]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[64]++;}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[120]++;
                    break L;
                case 'd':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[65]++; X="nodeKind";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[121]++;id=Id_nodeKind;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[122]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[123]++; break L;
                case 'e':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[66]++; X="elements";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[124]++;id=Id_elements;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[125]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[126]++; break L;
                case 'i':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[67]++; X="children";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[127]++;id=Id_children;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[128]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[129]++; break L;
                case 'm':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[68]++; X="comments";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[130]++;id=Id_comments;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[131]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[132]++; break L;
                case 'n':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[69]++; X="contains";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[133]++;id=Id_contains;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[134]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[135]++; break L; default : CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[70]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[136]++; break L;
            case 9:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[71]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[137]++; switch (s.charAt(2)) {
                case 'c':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[72]++; X="localName";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[138]++;id=Id_localName;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[139]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[140]++; break L;
                case 'm':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[73]++; X="namespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[141]++;id=Id_namespace;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[142]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[143]++; break L;
                case 'r':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[74]++; X="normalize";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[144]++;id=Id_normalize;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[145]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[146]++; break L;
                case 't':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[75]++; X="attribute";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[147]++;id=Id_attribute;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[148]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[149]++; break L; default : CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[76]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[150]++; break L;
            case 10:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[77]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[151]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[152]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c=='a') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[78]++; X="attributes";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[153]++;id=Id_attributes;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[154]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[79]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[155]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[80]++; X="childIndex";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[156]++;id=Id_childIndex;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[157]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[81]++;}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[158]++;
                break L;
            case 11:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[82]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[159]++; switch (s.charAt(0)) {
                case 'a':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[83]++; X="appendChild";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[160]++;id=Id_appendChild;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[161]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[162]++; break L;
                case 'c':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[84]++; X="constructor";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[163]++;id=Id_constructor;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[164]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[165]++; break L;
                case 'd':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[85]++; X="descendants";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[166]++;id=Id_descendants;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[167]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[168]++; break L;
                case 's':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[86]++; X="setChildren";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[169]++;id=Id_setChildren;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[170]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[171]++; break L;
                case 't':
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[87]++; X="toXMLString";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[172]++;id=Id_toXMLString;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[173]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[174]++; break L; default : CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[88]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[175]++; break L;
            case 12:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[89]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[176]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[177]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((c=='a') && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[90]++; X="addNamespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[178]++;id=Id_addNamespace;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[179]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[91]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[180]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[92]++; X="prependChild";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[181]++;id=Id_prependChild;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[182]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[93]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[183]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[94]++;
                    c=s.charAt(3);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[184]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[185]++;
int CodeCoverConditionCoverageHelper_C35;
                    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((c=='L') && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[96]++; X="setLocalName";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[186]++;id=Id_setLocalName;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[187]++;
 }
                    else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[97]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[188]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((c=='N') && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[98]++; X="setNamespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[189]++;id=Id_setNamespace;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[190]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[99]++;}
}

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[95]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[191]++;
                break L;
            case 14:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[100]++; X="hasOwnProperty";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[192]++;id=Id_hasOwnProperty;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[193]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[194]++; break L;
            case 15:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[101]++; X="removeNamespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[195]++;id=Id_removeNamespace;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[196]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[197]++; break L;
            case 16:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[102]++; c=s.charAt(0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[198]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[199]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c=='h') && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[103]++; X="hasSimpleContent";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[200]++;id=Id_hasSimpleContent;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[201]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[104]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[202]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((c=='i') && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[105]++; X="insertChildAfter";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[203]++;id=Id_insertChildAfter;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[204]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[106]++;}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[205]++;
                break L;
            case 17:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[107]++; c=s.charAt(3);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[206]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[207]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((c=='C') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[108]++; X="hasComplexContent";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[208]++;id=Id_hasComplexContent;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[209]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[109]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[210]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[110]++; X="inScopeNamespaces";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[211]++;id=Id_inScopeNamespaces;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[212]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[111]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[213]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((c=='e') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[112]++; X="insertChildBefore";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[214]++;id=Id_insertChildBefore;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[215]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[113]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[216]++;
                break L;
            case 20:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[114]++; X="propertyIsEnumerable";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[217]++;id=Id_propertyIsEnumerable;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[218]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[219]++; break L;
            case 21:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[115]++; X="namespaceDeclarations";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[220]++;id=Id_namespaceDeclarations;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[221]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[222]++; break L;
            case 22:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[116]++; X="processingInstructions";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[223]++;id=Id_processingInstructions;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[224]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[225]++; break L; default : CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[117]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[226]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 3) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[118]++; id = 0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[227]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[119]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[228]++;
            break L0;
        }
// #/generated#
        return id;
    }
// #/string_id_map#

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[229]++;
        switch (id) {
            case Id_constructor:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[120]++; {
                IdFunctionObject ctor;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[230]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[121]++;
                    ctor = new XMLCtor((XML)this, XMLOBJECT_TAG, id, 1);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[231]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[122]++;
                    ctor = new IdFunctionObject(this, XMLOBJECT_TAG, id, 1);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[232]++;
                }
                initPrototypeConstructor(ctor);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[233]++;
                return;
            }

            case Id_addNamespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[123]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[234]++; s="addNamespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[235]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[236]++;      break;
            case Id_appendChild:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[124]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[237]++; s="appendChild";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[238]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[239]++;       break;
            case Id_attribute:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[125]++;         arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[240]++; s="attribute";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[241]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[242]++;         break;
            case Id_attributes:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[126]++;        arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[243]++; s="attributes";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[244]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[245]++;        break;
            case Id_child:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[127]++;             arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[246]++; s="child";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[247]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[248]++;             break;
            case Id_childIndex:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[128]++;        arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[249]++; s="childIndex";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[250]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[251]++;        break;
            case Id_children:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[129]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[252]++; s="children";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[253]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[254]++;          break;
            case Id_comments:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[130]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[255]++; s="comments";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[256]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[257]++;          break;
            case Id_contains:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[131]++;          arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[258]++; s="contains";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[259]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[260]++;          break;
            case Id_copy:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[132]++;              arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[261]++; s="copy";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[262]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[263]++;              break;
            case Id_descendants:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[133]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[264]++; s="descendants";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[265]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[266]++;       break;
            case Id_elements:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[134]++;          arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[267]++; s="elements";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[268]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[269]++;          break;
            case Id_hasComplexContent:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[135]++; arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[270]++; s="hasComplexContent";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[271]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[272]++; break;
            case Id_hasOwnProperty:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[136]++;    arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[273]++; s="hasOwnProperty";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[274]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[275]++;    break;
            case Id_hasSimpleContent:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[137]++;  arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[276]++; s="hasSimpleContent";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[277]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[278]++;  break;
            case Id_inScopeNamespaces:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[138]++; arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[279]++; s="inScopeNamespaces";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[280]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[281]++; break;
            case Id_insertChildAfter:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[139]++;  arity=2;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[282]++; s="insertChildAfter";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[283]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[284]++;  break;
            case Id_insertChildBefore:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[140]++; arity=2;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[285]++; s="insertChildBefore";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[286]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[287]++; break;
            case Id_length:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[141]++;            arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[288]++; s="length";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[289]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[290]++;            break;
            case Id_localName:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[142]++;         arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[291]++; s="localName";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[292]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[293]++;         break;
            case Id_name:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[143]++;              arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[294]++; s="name";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[295]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[296]++;              break;
            case Id_namespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[144]++;         arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[297]++; s="namespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[298]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[299]++;         break;
            case Id_namespaceDeclarations:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[145]++;
                arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[300]++; s="namespaceDeclarations";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[301]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[302]++; break;
            case Id_nodeKind:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[146]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[303]++; s="nodeKind";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[304]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[305]++;          break;
            case Id_normalize:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[147]++;         arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[306]++; s="normalize";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[307]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[308]++;         break;
            case Id_parent:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[148]++;            arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[309]++; s="parent";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[310]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[311]++;            break;
            case Id_prependChild:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[149]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[312]++; s="prependChild";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[313]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[314]++;      break;
            case Id_processingInstructions:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[150]++;
                arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[315]++; s="processingInstructions";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[316]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[317]++; break;
            case Id_propertyIsEnumerable:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[151]++;
                arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[318]++; s="propertyIsEnumerable";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[319]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[320]++; break;
            case Id_removeNamespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[152]++;   arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[321]++; s="removeNamespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[322]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[323]++;   break;
            case Id_replace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[153]++;           arity=2;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[324]++; s="replace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[325]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[326]++;           break;
            case Id_setChildren:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[154]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[327]++; s="setChildren";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[328]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[329]++;       break;
            case Id_setLocalName:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[155]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[330]++; s="setLocalName";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[331]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[332]++;      break;
            case Id_setName:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[156]++;           arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[333]++; s="setName";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[334]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[335]++;           break;
            case Id_setNamespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[157]++;      arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[336]++; s="setNamespace";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[337]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[338]++;      break;
            case Id_text:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[158]++;              arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[339]++; s="text";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[340]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[341]++;              break;
            case Id_toString:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[159]++;          arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[342]++; s="toString";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[343]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[344]++;          break;
            case Id_toSource:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[160]++;          arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[345]++; s="toSource";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[346]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[347]++;          break;
            case Id_toXMLString:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[161]++;       arity=1;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[348]++; s="toXMLString";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[349]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[350]++;       break;
            case Id_valueOf:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[162]++;           arity=0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[351]++; s="valueOf";
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[352]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[353]++;           break;

            default:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[163]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(XMLOBJECT_TAG, id, s, arity);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[354]++;
    }

    private Object[] toObjectArray(Object[] typed) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[355]++;
        Object[] rv = new Object[typed.length];
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[356]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.loops[1]++;


int CodeCoverConditionCoverageHelper_C44;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i<rv.length) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.loops[1]--;
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.loops[2]--;
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.loops[3]++;
}
            rv[i] = typed[i];
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[357]++;
        }
        return rv;
    }

    private void xmlMethodNotFound(Object object, String name) {
        throw ScriptRuntime.notFunctionError(object, name);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[358]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((f.hasTag(XMLOBJECT_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[164]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[165]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[359]++;
        int id = f.methodId();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[360]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((id == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[166]++;
            return jsConstructor(cx, thisObj == null, args);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[167]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[361]++;
int CodeCoverConditionCoverageHelper_C47;

        // All (XML|XMLList).prototype methods require thisObj to be XML
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((thisObj instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[168]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[169]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[362]++;
        XMLObjectImpl realThis = (XMLObjectImpl)thisObj;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[363]++;

        XML xml = realThis.getXML();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[364]++;
        switch (id) {
            case Id_appendChild:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[170]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[365]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[171]++; xmlMethodNotFound(realThis, "appendChild");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[366]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[172]++;}
                return xml.appendChild(arg(args, 0));
            }
            case Id_addNamespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[173]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[367]++;
int CodeCoverConditionCoverageHelper_C49;
                if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[174]++; xmlMethodNotFound(realThis, "addNamespace");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[368]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[175]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[369]++;
                Namespace ns = lib.castToNamespace(cx, arg(args, 0));
                return xml.addNamespace(ns);
            }
            case Id_childIndex:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[176]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[370]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[177]++; xmlMethodNotFound(realThis, "childIndex");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[371]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[178]++;}
                return ScriptRuntime.wrapInt(xml.childIndex());
            }
            case Id_inScopeNamespaces:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[179]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[372]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[180]++; xmlMethodNotFound(realThis, "inScopeNamespaces");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[373]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[181]++;}
                return cx.newArray(scope, toObjectArray(xml.inScopeNamespaces()));
            }
            case Id_insertChildAfter:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[182]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[374]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[183]++; xmlMethodNotFound(realThis, "insertChildAfter");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[375]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[184]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[376]++;
                Object arg0 = arg(args, 0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[377]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((arg0 == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((arg0 instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[185]++;
                    return xml.insertChildAfter((XML)arg0, arg(args, 1));

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[186]++;}
                return Undefined.instance;
            }
            case Id_insertChildBefore:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[187]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[378]++;
int CodeCoverConditionCoverageHelper_C54;
                if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[188]++; xmlMethodNotFound(realThis, "insertChildBefore");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[379]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[189]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[380]++;
                Object arg0 = arg(args, 0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[381]++;
int CodeCoverConditionCoverageHelper_C55;
                if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((arg0 == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((arg0 instanceof XML) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[190]++;
                    return xml.insertChildBefore((XML)arg0, arg(args, 1));

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[191]++;}
                return Undefined.instance;
            }
            case Id_localName:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[192]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[382]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[193]++; xmlMethodNotFound(realThis, "localName");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[383]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[194]++;}
                return xml.localName();
            }
            case Id_name:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[195]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[384]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[196]++; xmlMethodNotFound(realThis, "name");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[385]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[197]++;}
                return xml.name();
            }
            case Id_namespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[198]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[386]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[199]++; xmlMethodNotFound(realThis, "namespace");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[387]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[200]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[388]++;
                String prefix = (args.length > 0) ? ScriptRuntime.toString(args[0]) : null;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[389]++;
                Namespace rv = xml.namespace(prefix);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[390]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((rv == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[201]++;
                    return Undefined.instance;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[202]++;
                    return rv;
                }
            }
            case Id_namespaceDeclarations:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[203]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[391]++;
int CodeCoverConditionCoverageHelper_C60;
                if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[204]++; xmlMethodNotFound(realThis, "namespaceDeclarations");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[392]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[205]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[393]++;
                Namespace[] array = xml.namespaceDeclarations();
                return cx.newArray(scope, toObjectArray(array));
            }
            case Id_nodeKind:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[206]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[394]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[207]++; xmlMethodNotFound(realThis, "nodeKind");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[395]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[208]++;}
                return xml.nodeKind();
            }
            case Id_prependChild:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[209]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[396]++;
int CodeCoverConditionCoverageHelper_C62;
                if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[210]++; xmlMethodNotFound(realThis, "prependChild");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[397]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[211]++;}
                return xml.prependChild(arg(args, 0));
            }
            case Id_removeNamespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[212]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[398]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[213]++; xmlMethodNotFound(realThis, "removeNamespace");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[399]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[214]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[400]++;
                Namespace ns = lib.castToNamespace(cx, arg(args, 0));
                return xml.removeNamespace(ns);
            }
            case Id_replace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[215]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[401]++;
int CodeCoverConditionCoverageHelper_C64;
                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[216]++; xmlMethodNotFound(realThis, "replace");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[402]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[217]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[403]++;
                XMLName xmlName = lib.toXMLNameOrIndex(cx, arg(args, 0));
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[404]++;
                Object arg1 = arg(args, 1);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[405]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[218]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[406]++;
                    //    I refuse to believe that this number will exceed 2^31
                    int index = (int)ScriptRuntime.lastUint32Result(cx);
                    return xml.replace(index, arg1);

                } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[219]++;
                    return xml.replace(xmlName, arg1);
                }
            }
            case Id_setChildren:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[220]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[407]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[221]++; xmlMethodNotFound(realThis, "setChildren");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[408]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[222]++;}
                return xml.setChildren(arg(args, 0));
            }
            case Id_setLocalName:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[223]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[409]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[224]++; xmlMethodNotFound(realThis, "setLocalName");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[410]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[225]++;}
                String localName;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[411]++;
                Object arg = arg(args, 0);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[412]++;
int CodeCoverConditionCoverageHelper_C68;
                if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((arg instanceof QName) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[226]++;
                    localName = ((QName)arg).localName();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[413]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[227]++;
                    localName = ScriptRuntime.toString(arg);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[414]++;
                }
                xml.setLocalName(localName);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[415]++;
                return Undefined.instance;
            }
            case Id_setName:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[228]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[416]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[229]++; xmlMethodNotFound(realThis, "setName");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[417]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[230]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[418]++;
                Object arg = (args.length != 0) ? args[0] : Undefined.instance;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[419]++;
                QName qname = lib.constructQName(cx, arg);
                xml.setName(qname);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[420]++;
                return Undefined.instance;
            }
            case Id_setNamespace:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[231]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[421]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((xml == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[232]++; xmlMethodNotFound(realThis, "setNamespace");
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[422]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[233]++;}
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[423]++;
                Namespace ns = lib.castToNamespace(cx, arg(args, 0));
                xml.setNamespace(ns);
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[424]++;
                return Undefined.instance;
            }

            case Id_attribute:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[234]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[425]++;
                XMLName xmlName = XMLName.create( lib.toNodeQName(cx, arg(args, 0), true), true, false );
                return realThis.getMatches(xmlName);
            }
            case Id_attributes:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[235]++;
                return realThis.getMatches(XMLName.create(XmlNode.QName.create(null, null), true, false));
            case Id_child:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[236]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[426]++;
                XMLName xmlName = lib.toXMLNameOrIndex(cx, arg(args, 0));
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[427]++;
int CodeCoverConditionCoverageHelper_C71;
                if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((xmlName == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[237]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[428]++;
                    //    Two billion or so is a fine upper limit, so we cast to int
                    int index = (int)ScriptRuntime.lastUint32Result(cx);
                    return realThis.child(index);

                } else {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[238]++;
                    return realThis.child(xmlName);
                }
            }
            case Id_children:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[239]++;
                return realThis.children();
            case Id_comments:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[240]++;
                return realThis.comments();
            case Id_contains:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[241]++;
                return ScriptRuntime.wrapBoolean(
                    realThis.contains(arg(args, 0)));
            case Id_copy:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[242]++;
                return realThis.copy();
            case Id_descendants:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[243]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[429]++;
                XmlNode.QName qname = (args.length == 0) ? XmlNode.QName.create(null, null) : lib.toNodeQName(cx, args[0], false);
                return realThis.getMatches( XMLName.create(qname, false, true) );
            }
            case Id_elements:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[244]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[430]++;
                XMLName xmlName = (args.length == 0)
                ? XMLName.formStar()
                : lib.toXMLName(cx, args[0]);
                return realThis.elements(xmlName);
            }
            case Id_hasOwnProperty:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[245]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[431]++;
                XMLName xmlName = lib.toXMLName(cx, arg(args, 0));
                return ScriptRuntime.wrapBoolean(
                    realThis.hasOwnProperty(xmlName));
            }
            case Id_hasComplexContent:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[246]++;
                return ScriptRuntime.wrapBoolean(realThis.hasComplexContent());
            case Id_hasSimpleContent:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[247]++;
                return ScriptRuntime.wrapBoolean(realThis.hasSimpleContent());
            case Id_length:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[248]++;
                return ScriptRuntime.wrapInt(realThis.length());
            case Id_normalize:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[249]++;
                realThis.normalize();
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[432]++;
                return Undefined.instance;
            case Id_parent:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[250]++;
                return realThis.parent();
            case Id_processingInstructions:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[251]++; {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[433]++;
                XMLName xmlName = (args.length > 0)
                ? lib.toXMLName(cx, args[0])
                : XMLName.formStar();
                return realThis.processingInstructions(xmlName);
            }
            case Id_propertyIsEnumerable:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[252]++; {
                return ScriptRuntime.wrapBoolean(
                    realThis.propertyIsEnumerable(arg(args, 0)));
            }
            case Id_text:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[253]++;
                return realThis.text();
            case Id_toString:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[254]++;
                return realThis.toString();
            case Id_toSource:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[255]++;
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[434]++;
                int indent = ScriptRuntime.toInt32(args, 0);
                return realThis.toSource(indent);
            case Id_toXMLString:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[256]++; {
                return realThis.toXMLString();
            }
            case Id_valueOf:
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[257]++;
                return realThis.valueOf(); default : CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.branches[258]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static Object arg(Object[] args, int i) {
        return (i < args.length) ? args[i] : Undefined.instance;
    }

    final XML newTextElementXML(XmlNode reference, XmlNode.QName qname, String value) {
        return lib.newTextElementXML(reference, qname, value);
    }

    /* TODO: Hopefully this can be replaced with ecmaToXml below. */
    final XML newXMLFromJs(Object inputObject) {
        return lib.newXMLFromJs(inputObject);
    }

    final XML ecmaToXml(Object object) {
        return lib.ecmaToXml(object);
    }

    final String ecmaEscapeAttributeValue(String s) {
CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29.statements[435]++;
        //    TODO    Check this
        String quoted = lib.escapeAttributeValue(s);
        return quoted.substring(1, quoted.length() - 1);
    }

    final XML createEmptyXML() {
        return newXML(XmlNode.createEmpty(getProcessor()));
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29 ());
  }
    public static long[] statements = new long[436];
    public static long[] branches = new long[259];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[72];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XMLObjectImpl.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 71; i++) {
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

  public CodeCoverCoverageCounter$11f1r6z5fa1vz5e8h8kq8tuo60ks4f5527h45ez11t29 () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLObjectImpl.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 435; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 258; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 71; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XMLObjectImpl.java");
      for (int i = 1; i <= 435; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 258; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 71; i++) {
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

