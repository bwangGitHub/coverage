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
 *   John Lenz
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
 * A type relationship visitor.<p>
 *
 * This code will calculate a specific value of type {@code T} from
 * two types based on its structure.
 *
 * @author johnlenz@google.com (John Lenz)
 */
interface RelationshipVisitor<T> {

  /**
   * Unknown type's case.
   */
  T caseUnknownType(JSType thisType, JSType thatType);

  /**
   * Bottom type's case.
   */
  T caseNoType(JSType thatType);

  /**
   * Bottom Object type's case.
   */
  T caseNoObjectType(JSType thatType);

  /**
   * All type's case.
   */
  T caseAllType(JSType thatType);

  /**
   * Value type's case.
   */
  T caseValueType(ValueType thisType, JSType thatType);

  /**
   * Object type's case.
   */
  T caseObjectType(ObjectType thisType, JSType thatType);

  /**
   * Function type's case.
   */
  T caseFunctionType(FunctionType thisType, JSType thatType);

  /**
   * Union type's case.
   */
  T caseUnionType(UnionType thisType, JSType thatType);

  /**
   * Templatized type's case.
   */
  T caseTemplatizedType(TemplatizedType thisType, JSType thatType);

  /**
   * Template type's case.
   */
  T caseTemplateType(TemplateType thisType, JSType thatType);

  /**
   * Enum element type's case.
   */
  T caseEnumElementType(EnumElementType typeType, JSType thatType);

}

class CodeCoverCoverageCounter$iznoirywuytgcunu3uxk5b39zoxrtzs3am4ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iznoirywuytgcunu3uxk5b39zoxrtzs3am4ip ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iznoirywuytgcunu3uxk5b39zoxrtzs3am4ip () {
    super("com.google.javascript.rhino.jstype.RelationshipVisitor.java");
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
    log.startNamedSection("com.google.javascript.rhino.jstype.RelationshipVisitor.java");
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

