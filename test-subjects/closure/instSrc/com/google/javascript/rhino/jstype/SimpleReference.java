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

import com.google.javascript.rhino.Node;

/**
 * A simple immutable reference.
 * @author nicksantos@google.com (Nick Santos)
 */
public class SimpleReference<T extends StaticSlot<JSType>>
    implements StaticReference<JSType> {
  static {
    CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541.ping();
  }

  private final T symbol;
  private final Node node;

  public SimpleReference(T symbol, Node node) {
    this.symbol = symbol;
CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541.statements[1]++;
    this.node = node;
CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541.statements[2]++;
  }

  @Override
  final public T getSymbol() {
    return symbol;
  }

  @Override
  final public Node getNode() {
    return node;
  }

  @Override
  final public StaticSourceFile getSourceFile() {
    return node.getStaticSourceFile();
  }

  @Override
  public String toString() {
CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541.statements[3]++;
    String sourceName = node == null ? null : node.getSourceFileName();
CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541.statements[4]++;
    int lineNo = node == null ? -1 : node.getLineno();
    return node.getQualifiedName() + "@" + sourceName + ":" + lineNo;
  }
}

class CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541 ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$9qrhjuyvf6y8xld5axxfsj631q6a541 () {
    super("com.google.javascript.rhino.jstype.SimpleReference.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.jstype.SimpleReference.java");
      for (int i = 1; i <= 4; i++) {
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

