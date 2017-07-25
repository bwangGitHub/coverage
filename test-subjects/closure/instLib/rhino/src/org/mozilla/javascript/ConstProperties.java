/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

public interface ConstProperties {
    /**
     * Sets a named const property in this object.
     * <p>
     * The property is specified by a string name
     * as defined for <code>Scriptable.get</code>.
     * <p>
     * The possible values that may be passed in are as defined for
     * <code>Scriptable.get</code>. A class that implements this method may choose
     * to ignore calls to set certain properties, in which case those
     * properties are effectively read-only.<p>
     * For properties defined in a prototype chain,
     * use <code>putProperty</code> in ScriptableObject. <p>
     * Note that if a property <i>a</i> is defined in the prototype <i>p</i>
     * of an object <i>o</i>, then evaluating <code>o.a = 23</code> will cause
     * <code>set</code> to be called on the prototype <i>p</i> with
     * <i>o</i> as the  <i>start</i> parameter.
     * To preserve JavaScript semantics, it is the Scriptable
     * object's responsibility to modify <i>o</i>. <p>
     * This design allows properties to be defined in prototypes and implemented
     * in terms of getters and setters of Java values without consuming slots
     * in each instance.<p>
     * <p>
     * The values that may be set are limited to the following:
     * <UL>
     * <LI>java.lang.Boolean objects</LI>
     * <LI>java.lang.String objects</LI>
     * <LI>java.lang.Number objects</LI>
     * <LI>org.mozilla.javascript.Scriptable objects</LI>
     * <LI>null</LI>
     * <LI>The value returned by Context.getUndefinedValue()</LI>
     * </UL><p>
     * Arbitrary Java objects may be wrapped in a Scriptable by first calling
     * <code>Context.toObject</code>. This allows the property of a JavaScript
     * object to contain an arbitrary Java object as a value.<p>
     * Note that <code>has</code> will be called by the runtime first before
     * <code>set</code> is called to determine in which object the
     * property is defined.
     * Note that this method is not expected to traverse the prototype chain,
     * which is different from the ECMA [[Put]] operation.
     * @param name the name of the property
     * @param start the object whose property is being set
     * @param value value to set the property to
     * @see org.mozilla.javascript.Scriptable#has(String, Scriptable)
     * @see org.mozilla.javascript.Scriptable#get(String, Scriptable)
     * @see org.mozilla.javascript.ScriptableObject#putProperty(Scriptable, String, Object)
     * @see org.mozilla.javascript.Context#toObject(Object, Scriptable)
     */
    public void putConst(String name, Scriptable start, Object value);

    /**
     * Reserves a definition spot for a const.  This will set up a definition
     * of the const property, but set its value to undefined.  The semantics of
     * the start parameter is the same as for putConst.
     * @param name The name of the property.
     * @param start The object whose property is being reserved.
     */
    public void defineConst(String name, Scriptable start);

    /**
     * Returns true if the named property is defined as a const on this object.
     * @param name
     * @return true if the named property is defined as a const, false
     * otherwise.
     */
    public boolean isConst(String name);
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpt5b385sydiijjx2hiqpzgk6t0tzyl41 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpt5b385sydiijjx2hiqpzgk6t0tzyl41 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpt5b385sydiijjx2hiqpzgk6t0tzyl41 () {
    super("org.mozilla.javascript.RHINO-SRC-ConstProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ConstProperties.java");
      for (int i = 1; i <= -1; i++) {
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

