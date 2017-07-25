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
 * Bottom type, representing the subclass of any value or object.
 *
 * Although JavaScript programmers can't explicitly denote the bottom type,
 * it comes up in static analysis. For example, if we have:
 * <code>
 * var x = null;
 * if (x) {
 *   f(x);
 * }
 * </code>
 * We need to be able to assign {@code x} a type within the {@code f(x)}
 * call. Since it has no possible type, we assign {@code x} the NoType,
 * so that {@code f(x)} is legal no matter what the type of {@code f}'s
 * first argument is.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Bottom_type">Bottom types</a>
 */
public class NoType extends NoObjectType {
  static {
    CodeCoverCoverageCounter$bwwy1jl5pbhtz2w69.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$bwwy1jl5pbhtz2w69.statements[1]++;
  }

  NoType(JSTypeRegistry registry) {
    super(registry);
CodeCoverCoverageCounter$bwwy1jl5pbhtz2w69.statements[2]++;
  }

  @Override
  public boolean isNoObjectType() {
    return false;
  }

  @Override
  public boolean isNoType() {
    return true;
  }

  @Override
  public boolean isNullable() {
    return true;
  }

  @Override
  public boolean isSubtype(JSType that) {
    return true;
  }

  @Override
  public BooleanLiteralSet getPossibleToBooleanOutcomes() {
    return BooleanLiteralSet.EMPTY;
  }

  @Override
  public boolean matchesNumberContext() {
    return true;
  }

  @Override
  public boolean matchesObjectContext() {
    return true;
  }

  @Override
  public boolean matchesStringContext() {
    return true;
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseNoType();
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseNoType(that);
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return forAnnotations ? "?" : "None";
  }
}

class CodeCoverCoverageCounter$bwwy1jl5pbhtz2w69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bwwy1jl5pbhtz2w69 ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$bwwy1jl5pbhtz2w69 () {
    super("com.google.javascript.rhino.jstype.NoType.java");
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
    log.startNamedSection("com.google.javascript.rhino.jstype.NoType.java");
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

