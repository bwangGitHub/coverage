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
 *   Bob Jervis
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

/**
 * A set in the domain {true,false}.
 * There are four possible sets: {}, {true}, {false}, {true,false}.
 *
 */
public enum BooleanLiteralSet {
  EMPTY,
  TRUE,
  FALSE,
  BOTH;

  private BooleanLiteralSet fromOrdinal(int ordinal) {
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.statements[1]++;
    switch (ordinal) {
      case 0:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[1]++; return EMPTY;
      case 1:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[2]++; return TRUE;
      case 2:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[3]++; return FALSE;
      case 3:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[4]++; return BOTH;
      default:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[5]++; throw new IllegalArgumentException("Ordinal: " + ordinal);
    }
  }

  /**
   * Computes the intersection of this set and {@code that}.
   */
  public BooleanLiteralSet intersection(BooleanLiteralSet that) {
    return fromOrdinal(this.ordinal() & that.ordinal());
  }

  /**
   * Computes the union of this set and {@code that}.
   */
  public BooleanLiteralSet union(BooleanLiteralSet that) {
    return fromOrdinal(this.ordinal() | that.ordinal());
  }

  /**
   * Returns whether {@code this} contains the given literal value.
   */
  public boolean contains(boolean literalValue) {
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.statements[2]++;
    switch (this.ordinal()) {
      case 0:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[6]++; return false;
      case 1:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[7]++; return literalValue;
      case 2:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[8]++; return !literalValue;
      case 3:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[9]++; return true;
      default:
CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep.branches[10]++; throw new IndexOutOfBoundsException("Ordinal: " +
          this.ordinal());
    }
  }

  /**
   * Returns the singleton set {literalValue}.
   */
  public static BooleanLiteralSet get(boolean literalValue) {
    return literalValue ? TRUE : FALSE;
  }
}

class CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[11];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$awfchjmfexohgta5dzwzgstvlsfw9hv9ep () {
    super("com.google.javascript.rhino.jstype.BooleanLiteralSet.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.BooleanLiteralSet.java");
      for (int i = 1; i <= 2; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
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

