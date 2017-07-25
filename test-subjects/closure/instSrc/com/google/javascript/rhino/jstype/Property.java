/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Nick Santos
 *   Google Inc.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino.jstype;

import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;

import java.io.Serializable;

/**
 * A property slot of an object.
 * @author nicksantos@google.com (Nick Santos)
 */
public final class Property
    implements Serializable, StaticSlot<JSType>, StaticReference<JSType> {
  static {
    CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[1]++;
  }

  /**
   * Property's name.
   */
  private final String name;

  /**
   * Property's type.
   */
  private JSType type;

  /**
   * Whether the property's type is inferred.
   */
  private final boolean inferred;

  /**
   * The node corresponding to this property, e.g., a GETPROP node that
   * declares this property.
   */
  private Node propertyNode;

  /**  The JSDocInfo for this property. */
  private JSDocInfo docInfo = null;
  {
    CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[2]++;
  }

  Property(String name, JSType type, boolean inferred,
      Node propertyNode) {
    this.name = name;
CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[3]++;
    this.type = type;
CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[4]++;
    this.inferred = inferred;
CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[5]++;
    this.propertyNode = propertyNode;
CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[6]++;
  }

  @Override
      public String getName() {
    return name;
  }

  @Override
      public Node getNode() {
    return propertyNode;
  }

  @Override
      public StaticSourceFile getSourceFile() {
    return propertyNode == null ? null : propertyNode.getStaticSourceFile();
  }

  @Override
      public Property getSymbol() {
    return this;
  }

  @Override
      public Property getDeclaration() {
    return propertyNode == null ? null : this;
  }

  @Override
      public JSType getType() {
    return type;
  }

  @Override
      public boolean isTypeInferred() {
    return inferred;
  }

  boolean isFromExterns() {
    return propertyNode == null ? false : propertyNode.isFromExterns();
  }

  void setType(JSType type) {
    this.type = type;
CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[7]++;
  }

  @Override public JSDocInfo getJSDocInfo() {
    return this.docInfo;
  }

  void setJSDocInfo(JSDocInfo info) {
    this.docInfo = info;
CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[8]++;
  }

  public void setNode(Node n) {
    this.propertyNode = n;
CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d.statements[9]++;
  }
}

class CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$h5xqw3xrci4rzb704z1d () {
    super("com.google.javascript.rhino.jstype.Property.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.jstype.Property.java");
      for (int i = 1; i <= 9; i++) {
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

