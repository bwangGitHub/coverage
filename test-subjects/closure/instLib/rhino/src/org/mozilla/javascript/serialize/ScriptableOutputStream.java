/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.serialize;

import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.*;

import org.mozilla.javascript.*;

/**
 * Class ScriptableOutputStream is an ObjectOutputStream used
 * to serialize JavaScript objects and functions. Note that
 * compiled functions currently cannot be serialized, only
 * interpreted functions. The top-level scope containing the
 * object is not written out, but is instead replaced with
 * another top-level object when the ScriptableInputStream
 * reads in this object. Also, object corresponding to names
 * added to the exclude list are not written out but instead
 * are looked up during deserialization. This approach avoids
 * the creation of duplicate copies of standard objects
 * during deserialization.
 *
 */

// API class

public class ScriptableOutputStream extends ObjectOutputStream {
  static {
    CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.ping();
  }


    /**
     * ScriptableOutputStream constructor.
     * Creates a ScriptableOutputStream for use in serializing
     * JavaScript objects. Calls excludeStandardObjectNames.
     *
     * @param out the OutputStream to write to.
     * @param scope the scope containing the object.
     */
    public ScriptableOutputStream(OutputStream out, Scriptable scope)
        throws IOException
    {
        super(out);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[1]++;
        this.scope = scope;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[2]++;
        table = new HashMap<Object,String>();
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[3]++;
        table.put(scope, "");
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[4]++;
        enableReplaceObject(true);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[5]++;
        excludeStandardObjectNames();
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[6]++; // XXX
    }

    public void excludeAllIds(Object[] ids) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[1]++;


        for (Object id: ids) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[1]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[2]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[3]++;
}
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scope.get((String) id, scope) instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))
            {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[1]++;
                this.addExcludedName((String)id);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[9]++;

            } else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[2]++;}
        }
    }

    /**
     * Adds a qualified name to the list of object to be excluded from
     * serialization. Names excluded from serialization are looked up
     * in the new scope and replaced upon deserialization.
     * @param name a fully qualified name (of the form "a.b.c", where
     *             "a" must be a property of the top-level object). The object
     *             need not exist, in which case the name is ignored.
     * @throws IllegalArgumentException if the object is not a
     *         {@link Scriptable}.
     */
    public void addOptionalExcludedName(String name) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[10]++;
        Object obj = lookupQualifiedName(scope, name);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj != UniqueTag.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[3]++;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[5]++;
                throw new IllegalArgumentException(
                        "Object for excluded name " + name +
                        " is not a Scriptable, it is " +
                        obj.getClass().getName());

            } else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[6]++;}
            table.put(obj, name);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[13]++;

        } else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[4]++;}
    }

    /**
     * Adds a qualified name to the list of objects to be excluded from
     * serialization. Names excluded from serialization are looked up
     * in the new scope and replaced upon deserialization.
     * @param name a fully qualified name (of the form "a.b.c", where
     *             "a" must be a property of the top-level object)
     * @throws IllegalArgumentException if the object is not found or is not
     *         a {@link Scriptable}.
     */
    public void addExcludedName(String name) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[14]++;
        Object obj = lookupQualifiedName(scope, name);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[7]++;
            throw new IllegalArgumentException("Object for excluded name " +
                                               name + " not found.");

        } else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[8]++;}
        table.put(obj, name);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[16]++;
    }

    /**
     * Returns true if the name is excluded from serialization.
     */
    public boolean hasExcludedName(String name) {
        return table.get(name) != null;
    }

    /**
     * Removes a name from the list of names to exclude.
     */
    public void removeExcludedName(String name) {
        table.remove(name);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[17]++;
    }

    /**
     * Adds the names of the standard objects and their
     * prototypes to the list of excluded names.
     */
    public void excludeStandardObjectNames() {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[18]++;
        String[] names = { "Object", "Object.prototype",
                           "Function", "Function.prototype",
                           "String", "String.prototype",
                           "Math",  // no Math.prototype
                           "Array", "Array.prototype",
                           "Error", "Error.prototype",
                           "Number", "Number.prototype",
                           "Date", "Date.prototype",
                           "RegExp", "RegExp.prototype",
                           "Script", "Script.prototype",
                           "Continuation", "Continuation.prototype",
                         };
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < names.length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[4]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[5]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[6]++;
}
            addExcludedName(names[i]);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[20]++;
        }
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[21]++;

        String[] optionalNames = {
                "XML", "XML.prototype",
                "XMLList", "XMLList.prototype",
        };
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[22]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < optionalNames.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[7]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[8]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[9]++;
}
            addOptionalExcludedName(optionalNames[i]);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[23]++;
        }
    }

    static Object lookupQualifiedName(Scriptable scope,
                                      String qualifiedName)
    {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[24]++;
        StringTokenizer st = new StringTokenizer(qualifiedName, ".");
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[25]++;
        Object result = scope;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[26]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[10]++;


int CodeCoverConditionCoverageHelper_C7;
        while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[10]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[11]--;
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.loops[12]++;
}
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[27]++;
            String s = st.nextToken();
            result = ScriptableObject.getProperty((Scriptable)result, s);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[28]++;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((result instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[9]++;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[30]++;
                break;
} else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[10]++;}
        }
        return result;
    }

    static class PendingLookup implements Serializable
    {
        static final long serialVersionUID = -2692990309789917727L;

        PendingLookup(String name) { this.name = name;
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[31]++; }

        String getName() { return name; }

        private String name;
    }

    @Override
    protected Object replaceObject(Object obj) throws IOException
    {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[32]++;
        if (false) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[11]++; throw new IOException();
} else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[12]++;}
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[33]++; // suppress warning
        String name = table.get(obj);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.statements[34]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[13]++;
            return obj;
} else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox.branches[14]++;}
        return new PendingLookup(name);
    }

    private Scriptable scope;
    private Map<Object,String> table;
}

class CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.serialize.RHINO-SRC-ScriptableOutputStream.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,2,0,1};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$srvjzfg6sfs5ucp26g897y0kvi3443rljnycbunhnz11jyp4ntv7npqox () {
    super("org.mozilla.javascript.serialize.RHINO-SRC-ScriptableOutputStream.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.serialize.RHINO-SRC-ScriptableOutputStream.java");
      for (int i = 1; i <= 34; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

