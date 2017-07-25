/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml;

import org.mozilla.javascript.*;

/**
 *  This Interface describes what all XML objects (XML, XMLList) should have in common.
 *
 */
public abstract class XMLObject extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzhayj43gmtjk4w2gg1gukbl.ping();
  }

    
    static final long serialVersionUID = 8455156490438576500L;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzhayj43gmtjk4w2gg1gukbl.statements[1]++;
  }
    
    public XMLObject()
    {
    }

    public XMLObject(Scriptable scope, Scriptable prototype)
    {
        super(scope, prototype);
CodeCoverCoverageCounter$iypomt0ag7yuozzhayj43gmtjk4w2gg1gukbl.statements[2]++;
    }

    /**
     * Implementation of ECMAScript [[Has]].
     */
    public abstract boolean has(Context cx, Object id);

    /**
     * Implementation of ECMAScript [[Get]].
     */
    public abstract Object get(Context cx, Object id);

    /**
     * Implementation of ECMAScript [[Put]].
     */
    public abstract void put(Context cx, Object id, Object value);

    /**
     * Implementation of ECMAScript [[Delete]].
     */
    public abstract boolean delete(Context cx, Object id);


    public abstract Object getFunctionProperty(Context cx, String name);

    public abstract Object getFunctionProperty(Context cx, int id);

    /**
     * Return an additional object to look for methods that runtime should
     * consider during method search. Return null if no such object available.
     */
    public abstract Scriptable getExtraMethodSource(Context cx);

    /**
     * Generic reference to implement x.@y, x..y etc.
     */
    public abstract Ref memberRef(Context cx, Object elem,
                                  int memberTypeFlags);

    /**
     * Generic reference to implement x::ns, x.@ns::y, x..@ns::y etc.
     */
    public abstract Ref memberRef(Context cx, Object namespace, Object elem,
                                  int memberTypeFlags);

    /**
     * Wrap this object into NativeWith to implement the with statement.
     */
    public abstract NativeWith enterWith(Scriptable scope);

    /**
     * Wrap this object into NativeWith to implement the .() query.
     */
    public abstract NativeWith enterDotQuery(Scriptable scope);

    /**
     * Custom <tt>+</tt> operator.
     * Should return {@link Scriptable#NOT_FOUND} if this object does not have
     * custom addition operator for the given value,
     * or the result of the addition operation.
     * <p>
     * The default implementation returns {@link Scriptable#NOT_FOUND}
     * to indicate no custom addition operation.
     *
     * @param cx the Context object associated with the current thread.
     * @param thisIsLeft if true, the object should calculate this + value
     *                   if false, the object should calculate value + this.
     * @param value the second argument for addition operation.
     */
    public Object addValues(Context cx, boolean thisIsLeft, Object value)
    {
        return Scriptable.NOT_FOUND;
    }

    /**
     * Gets the value returned by calling the typeof operator on this object.
     * @see org.mozilla.javascript.ScriptableObject#getTypeOf()
     * @return "xml" or "undefined" if {@link #avoidObjectDetection()} returns <code>true</code>
     */
    @Override
    public String getTypeOf()
    {
    	return avoidObjectDetection() ? "undefined" : "xml";
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzhayj43gmtjk4w2gg1gukbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzhayj43gmtjk4w2gg1gukbl ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzhayj43gmtjk4w2gg1gukbl () {
    super("org.mozilla.javascript.xml.RHINO-SRC-XMLObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.RHINO-SRC-XMLObject.java");
      for (int i = 1; i <= 2; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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
