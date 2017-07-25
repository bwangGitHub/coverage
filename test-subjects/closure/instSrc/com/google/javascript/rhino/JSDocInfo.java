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

package com.google.javascript.rhino;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>JSDoc information describing JavaScript code. JSDoc is represented as a
 * unified object with fields for each JSDoc annotation, even though some
 * combinations are incorrect. For instance, if a JSDoc describes an enum,
 * it cannot have information about a return type. This implementation
 * takes advantage of such incompatibilities to reuse fields for multiple
 * purposes, reducing memory consumption.</p>
 *
 * <p>Constructing {@link JSDocInfo} objects is simplified by
 * {@link JSDocInfoBuilder} which provides early incompatibility detection.</p>
 *
 */
public class JSDocInfo implements Serializable {
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[1]++;
  }

  /**
   * Visibility categories. The {@link Visibility#ordinal()} can be used as a
   * numerical indicator of privacy, where 0 is the most private. This means
   * that the {@link Visibility#compareTo} method can be used to
   * determine if a visibility is more permissive than another.
   */
  public enum Visibility {
    PRIVATE,
    PROTECTED,
    PUBLIC,

    // If visibility is not specified, we just assume that visibility
    // is inherited from the super class.
    INHERITED
  }

  private static final class LazilyInitializedInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    // Function information
    JSTypeExpression baseType = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[2]++;
  }
    List<JSTypeExpression> extendedInterfaces = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[3]++;
  }
    List<JSTypeExpression> implementedInterfaces = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[4]++;
  }
    Map<String, JSTypeExpression> parameters = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[5]++;
  }
    List<JSTypeExpression> thrownTypes = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[6]++;
  }
    ImmutableList<String> templateTypeNames = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[7]++;
  }

    // Other information
    String description = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[8]++;
  }
    String meaning = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[9]++;
  }
    String deprecated = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[10]++;
  }
    String license = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[11]++;
  }
    Set<String> suppressions = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[12]++;
  }
    Set<String> modifies = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[13]++;
  }
    String lendsName = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[14]++;
  }
    boolean ngInject = false;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[15]++;
  }
  }

  private static final class LazilyInitializedDocumentation {
    String sourceComment = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[16]++;
  }
    List<Marker> markers = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[17]++;
  }

    Map<String, String> parameters = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[18]++;
  }
    Map<JSTypeExpression, String> throwsDescriptions = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[19]++;
  }
    String blockDescription = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[20]++;
  }
    String fileOverview = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[21]++;
  }
    String returnDescription = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[22]++;
  }
    String version = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[23]++;
  }

    List<String> authors = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[24]++;
  }
    List<String> sees = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[25]++;
  }
  }

  /**
   * A piece of information (found in a marker) which contains a position
   * with a string.
   */
  public static class StringPosition extends SourcePosition<String> {
  }

  /**
   * A piece of information (found in a marker) which contains a position
   * with a string that has no leading or trailing whitespace.
   */
  static class TrimmedStringPosition extends StringPosition {
    @Override public void setItem(String item) {
      Preconditions.checkArgument(
          item.charAt(0) != ' ' &&
          item.charAt(item.length() - 1) != ' ',
          "String has leading or trailing whitespace");
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[26]++;
      super.setItem(item);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[27]++;
    }
  }

  /**
   * A piece of information (found in a marker) which contains a position
   * with a name node.
   */
  public static class NamePosition extends SourcePosition<Node> {}

  /**
   * A piece of information (found in a marker) which contains a position
   * with a type expression syntax tree.
   */
  public static class TypePosition extends SourcePosition<Node> {
    private boolean brackets = false;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[28]++;
  }

    /** Returns whether the type has curly braces around it. */
    public boolean hasBrackets() {
      return brackets;
    }

    void setHasBrackets(boolean newVal) {
      brackets = newVal;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[29]++;
    }
  }

  /**
   * Defines a class for containing the parsing information
   * for this JSDocInfo. For each annotation found in the
   * JsDoc, a marker will be created indicating the annotation
   * itself, the name of the annotation (if any; for example,
   * a @param has a name, but a @return does not), the
   * textual description found on that annotation and, if applicable,
   * the type declaration. All this information is only collected
   * if documentation collection is turned on.
   */
  public static final class Marker {
    private TrimmedStringPosition annotation = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[30]++;
  }
    private TrimmedStringPosition name = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[31]++;
  }
    private SourcePosition<Node> nameNode = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[32]++;
  }
    private StringPosition description = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[33]++;
  }
    private TypePosition type = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[34]++;
  }

    /**
     * Gets the position information for the annotation name. (e.g., "param")
     */
    public StringPosition getAnnotation() {
      return annotation;
    }

    void setAnnotation(TrimmedStringPosition p) {
      annotation = p;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[35]++;
    }

    /**
     * Gets the position information for the name found
     * in a @param tag.
     * @deprecated Use #getNameNode
     */
    @Deprecated
    public StringPosition getName() {
      return name;
    }

    void setName(TrimmedStringPosition p) {
      name = p;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[36]++;
    }

    /**
     * Gets the position information for the name found
     * in an @param tag.
     */
    public SourcePosition<Node> getNameNode() {
      return nameNode;
    }

    void setNameNode(SourcePosition<Node> p) {
      nameNode = p;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[37]++;
    }

    /**
     * Gets the position information for the description found
     * in a block tag.
     */
    public StringPosition getDescription() {
      return description;
    }

    void setDescription(StringPosition p) {
      description = p;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[38]++;
    }

    /**
     * Gets the position information for the type expression found
     * in some block tags, like "@param" and "@return".
     */
    public TypePosition getType() {
      return type;
    }

    void setType(TypePosition p) {
      type = p;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[39]++;
    }
  }

  private LazilyInitializedInfo info = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[40]++;
  }

  private LazilyInitializedDocumentation documentation = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[41]++;
  }

  // The Node this JSDoc is associated with.
  private Node associatedNode = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[42]++;
  }

  private Visibility visibility = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[43]++;
  }

  /**
   * The {@link #isConstant()}, {@link #isConstructor()}, {@link #isInterface},
   * {@link #isHidden()} and {@link #shouldPreserveTry()} flags as well as
   * whether the {@link #type} field stores a value for {@link #getType()},
   * {@link #getReturnType()} or {@link #getEnumParameterType()}.
   *
   * @see #setFlag(boolean, int)
   * @see #getFlag(int)
   * @see #setType(JSTypeExpression, int)
   * @see #getType(int)
   */
  private int bitset = 0x00;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[44]++;
  }

  /**
   * The type for {@link #getType()}, {@link #getReturnType()} or
   * {@link #getEnumParameterType()}. The knowledge of which one is recorded is
   * stored in the {@link #bitset} field.
   *
   * @see #setType(JSTypeExpression, int)
   * @see #getType(int)
   */
  private JSTypeExpression type = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[45]++;
  }

  /**
   * The type for {@link #getThisType()}.
   */
  private JSTypeExpression thisType = null;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[46]++;
  }

  /**
   * Whether to include documentation.
   *
   * @see JSDocInfo.LazilyInitializedDocumentation
   */
  private boolean includeDocumentation = false;
  {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[47]++;
  }

  // We use a bit map to represent whether or not the JSDoc contains
  // one of the "boolean" annotation types (annotations like @constructor,
  // for which the presence of the annotation alone is significant).

  // Mask all the boolean annotation types
  private static final int MASK_FLAGS       = 0x3FFFFFFF;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[48]++;
  }

  private static final int MASK_CONSTANT      = 0x00000001;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[49]++;
  } // @const
  private static final int MASK_CONSTRUCTOR   = 0x00000002;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[50]++;
  } // @constructor
  private static final int MASK_DEFINE        = 0x00000004;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[51]++;
  } // @define
  private static final int MASK_HIDDEN        = 0x00000008;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[52]++;
  } // @hidden
  private static final int MASK_PRESERVETRY   = 0x00000010;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[53]++;
  } // @preserveTry
  private static final int MASK_NOCHECK       = 0x00000020;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[54]++;
  } // @notypecheck
  private static final int MASK_OVERRIDE      = 0x00000040;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[55]++;
  } // @override
  private static final int MASK_NOALIAS       = 0x00000080;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[56]++;
  } // @noalias
  private static final int MASK_DEPRECATED    = 0x00000100;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[57]++;
  } // @deprecated
  private static final int MASK_INTERFACE     = 0x00000200;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[58]++;
  } // @interface
  private static final int MASK_EXPORT        = 0x00000400;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[59]++;
  } // @export
  private static final int MASK_NOSHADOW      = 0x00000800;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[60]++;
  } // @noshadow
  private static final int MASK_FILEOVERVIEW  = 0x00001000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[61]++;
  } // @fileoverview
  private static final int MASK_IMPLICITCAST  = 0x00002000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[62]++;
  } // @implicitCast
  private static final int MASK_NOSIDEEFFECTS = 0x00004000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[63]++;
  } // @nosideeffects
  private static final int MASK_EXTERNS       = 0x00008000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[64]++;
  } // @externs
  private static final int MASK_JAVADISPATCH  = 0x00010000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[65]++;
  } // @javadispatch
  private static final int MASK_NOCOMPILE     = 0x00020000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[66]++;
  } // @nocompile
  private static final int MASK_CONSISTIDGEN  = 0x00040000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[67]++;
  } // @consistentIdGenerator
  private static final int MASK_IDGEN         = 0x00080000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[68]++;
  } // @idGenerator
  private static final int MASK_EXPOSE        = 0x00100000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[69]++;
  } // @expose
  private static final int MASK_STRUCT        = 0x00200000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[70]++;
  } // @struct
  private static final int MASK_DICT          = 0x00400000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[71]++;
  } // @dict
  private static final int MASK_STALBEIDGEN   = 0x00800000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[72]++;
  } // @stableIdGenerator

  // 3 bit type field stored in the top 3 bits of the most significant
  // nibble.
  private static final int MASK_TYPEFIELD    = 0xE0000000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[73]++;
  } // 1110...
  private static final int TYPEFIELD_TYPE    = 0x20000000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[74]++;
  } // 0010...
  private static final int TYPEFIELD_RETURN  = 0x40000000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[75]++;
  } // 0100...
  private static final int TYPEFIELD_ENUM    = 0x60000000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[76]++;
  } // 0110...
  private static final int TYPEFIELD_TYPEDEF = 0x80000000;
  static {
    CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[77]++;
  } // 1000...

  /**
   * Creates a {@link JSDocInfo} object. This object should be created using
   * a {@link JSDocInfoBuilder}.
   */
  JSDocInfo(boolean includeDocumentation) {
    this.includeDocumentation = includeDocumentation;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[78]++;
  }

  // Visible for testing.
  public JSDocInfo() {}

  void setConsistentIdGenerator(boolean value) {
    setFlag(value, MASK_CONSISTIDGEN);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[79]++;
  }

  void setStableIdGenerator(boolean value) {
    setFlag(value, MASK_STALBEIDGEN);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[80]++;
  }

  void setConstant(boolean value) {
    setFlag(value, MASK_CONSTANT);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[81]++;
  }

  void setConstructor(boolean value) {
    setFlag(value, MASK_CONSTRUCTOR);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[82]++;
  }

  void setStruct() {
    setFlag(true, MASK_STRUCT);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[83]++;
  }

  void setDict() {
    setFlag(true, MASK_DICT);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[84]++;
  }

  void setDefine(boolean value) {
    setFlag(value, MASK_DEFINE);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[85]++;
  }

  void setHidden(boolean value) {
    setFlag(value, MASK_HIDDEN);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[86]++;
  }

  void setNoCheck(boolean value) {
    setFlag(value, MASK_NOCHECK);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[87]++;
  }

  void setShouldPreserveTry(boolean value) {
    setFlag(value, MASK_PRESERVETRY);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[88]++;
  }

  void setOverride(boolean value) {
    setFlag(value, MASK_OVERRIDE);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[89]++;
  }

  void setNoAlias(boolean value) {
    setFlag(value, MASK_NOALIAS);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[90]++;
  }

  // Visible for testing.
  public void setDeprecated(boolean value) {
    setFlag(value, MASK_DEPRECATED);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[91]++;
  }

  void setInterface(boolean value) {
    setFlag(value, MASK_INTERFACE);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[92]++;
  }

  void setExport(boolean value) {
    setFlag(value, MASK_EXPORT);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[93]++;
  }

  void setExpose(boolean value) {
    setFlag(value, MASK_EXPOSE);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[94]++;
  }

  void setNoShadow(boolean value) {
    setFlag(value, MASK_NOSHADOW);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[95]++;
  }

  void setIdGenerator(boolean value) {
    setFlag(value, MASK_IDGEN);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[96]++;
  }

  void setImplicitCast(boolean value) {
    setFlag(value, MASK_IMPLICITCAST);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[97]++;
  }

  void setNoSideEffects(boolean value) {
    setFlag(value, MASK_NOSIDEEFFECTS);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[98]++;
  }

  void setExterns(boolean value) {
    setFlag(value, MASK_EXTERNS);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[99]++;
  }

  void setJavaDispatch(boolean value) {
    setFlag(value, MASK_JAVADISPATCH);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[100]++;
  }

  void setNoCompile(boolean value) {
    setFlag(value, MASK_NOCOMPILE);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[101]++;
  }

  private void setFlag(boolean value, int mask) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[102]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((value) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[1]++;
      bitset |= mask;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[103]++;

    } else {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[2]++;
      bitset &= ~mask;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[104]++;
    }
  }

  /**
   * @return whether the {@code @consistentIdGenerator} is present on
   * this {@link JSDocInfo}
   */
  public boolean isConsistentIdGenerator() {
    return getFlag(MASK_CONSISTIDGEN);
  }

  /**
   * @return whether the {@code @stableIdGenerator} is present on this {@link JSDocInfo}.
   */
  public boolean isStableIdGenerator() {
    return getFlag(MASK_STALBEIDGEN);
  }

  /**
   * Returns whether the {@code @const} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isConstant() {
    return getFlag(MASK_CONSTANT) || isDefine();
  }

  /**
   * Returns whether the {@code @constructor} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isConstructor() {
    return getFlag(MASK_CONSTRUCTOR);
  }

  /**
   * Returns whether the {@code @struct} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean makesStructs() {
    return getFlag(MASK_STRUCT);
  }

  /**
   * Returns whether the {@code @dict} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean makesDicts() {
    return getFlag(MASK_DICT);
  }

  /**
   * Returns whether the {@code @define} annotation is present on this
   * {@link JSDocInfo}. If this annotation is present, then the
   * {@link #getType()} method will retrieve the define type.
   */
  public boolean isDefine() {
    return getFlag(MASK_DEFINE);
  }

  /**
   * Returns whether the {@code @hidden} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isHidden() {
    return getFlag(MASK_HIDDEN);
  }

  /**
   * Returns whether the {@code @nocheck} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isNoTypeCheck() {
    return getFlag(MASK_NOCHECK);
  }

  /**
   * Returns whether the {@code @preserveTry} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean shouldPreserveTry() {
    return getFlag(MASK_PRESERVETRY);
  }

  /**
   * Returns whether the {@code @override} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isOverride() {
    return getFlag(MASK_OVERRIDE);
  }

  /**
   * Returns whether the {@code @noalias} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isNoAlias() {
    return getFlag(MASK_NOALIAS);
  }

  /**
   * Returns whether the {@code @deprecated} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isDeprecated() {
    return getFlag(MASK_DEPRECATED);
  }

  /**
   * Returns whether the {@code @interface} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isInterface() {
    return getFlag(MASK_INTERFACE);
  }

  /**
   * Returns whether the {@code @export} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isExport() {
    return getFlag(MASK_EXPORT);
  }

  /**
   * Returns whether the {@code @expose} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isExpose() {
    return getFlag(MASK_EXPOSE);
  }

  /**
   * Returns whether the {@code @noshadow} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isNoShadow() {
    return getFlag(MASK_NOSHADOW);
  }

  /**
   * @return whether the {@code @idGenerator} is present on
   * this {@link JSDocInfo}
   */
  public boolean isIdGenerator() {
    return getFlag(MASK_IDGEN);
  }

  /**
   * Returns whether the {@code @implicitCast} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isImplicitCast() {
    return getFlag(MASK_IMPLICITCAST);
  }

  /**
   * Returns whether the {@code @nosideeffects} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isNoSideEffects() {
    return getFlag(MASK_NOSIDEEFFECTS);
  }

  /**
   * Returns whether the {@code @externs} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isExterns() {
    return getFlag(MASK_EXTERNS);
  }

  /**
   * Returns whether the {@code @javadispatch} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isJavaDispatch() {
    return getFlag(MASK_JAVADISPATCH);
  }

  /**
   * Returns whether the {@code @nocompile} annotation is present on this
   * {@link JSDocInfo}.
   */
  public boolean isNoCompile() {
    return getFlag(MASK_NOCOMPILE);
  }

  /**
   * @return Whether there is declaration present on this {@link JSDocInfo}.
   */
  public boolean containsDeclaration() {
    return (hasType()
        || hasReturnType()
        || hasEnumParameterType()
        || hasTypedefType()
        || hasThisType()
        || getParameterCount() > 0
        || getFlag(MASK_CONSTANT
            | MASK_CONSTRUCTOR
            | MASK_DEFINE
            | MASK_OVERRIDE
            | MASK_NOALIAS
            | MASK_DEPRECATED
            | MASK_INTERFACE
            | MASK_NOSHADOW
            | MASK_IMPLICITCAST
            | MASK_NOSIDEEFFECTS));
  }

  private boolean getFlag(int mask) {
    return (bitset & mask) != 0x00;
  }

  // Visible for testing.
  public void setVisibility(Visibility visibility) {
    this.visibility = visibility;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[105]++;
  }

  private void lazyInitInfo() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[106]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[3]++;
      info = new LazilyInitializedInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[107]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[4]++;}
  }

  /**
   * Lazily initializes the documentation information object, but only
   * if the JSDocInfo was told to keep such information around.
   */
  private boolean lazyInitDocumentation() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[108]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((includeDocumentation) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[5]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[6]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[109]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((documentation == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[7]++;
      documentation = new LazilyInitializedDocumentation();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[110]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[8]++;}

    return true;
  }

  /**
   * Adds a marker to the documentation (if it exists) and
   * returns the marker. Returns null otherwise.
   */
  Marker addMarker() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[111]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[9]++;
      return null;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[10]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[112]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((documentation.markers == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[11]++;
      documentation.markers = Lists.newArrayList();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[113]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[12]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[114]++;

    Marker marker = new Marker();
    documentation.markers.add(marker);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[115]++;
    return marker;
  }

  /**
   * Sets the deprecation reason.
   *
   * @param reason The deprecation reason
   */
  boolean setDeprecationReason(String reason) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[116]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[117]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((info.deprecated != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[13]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[14]++;}

    info.deprecated = reason;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[118]++;
    return true;
  }

  /**
   * Add a suppressed warning.
   */
  public void addSuppression(String suppression) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[119]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[120]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((info.suppressions == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[15]++;
      info.suppressions = Sets.newHashSet();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[121]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[16]++;}
    info.suppressions.add(suppression);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[122]++;
  }

  /**
   * Sets suppressed warnings.
   * @param suppressions A list of suppressed warning types.
   */
  boolean setSuppressions(Set<String> suppressions) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[123]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[124]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((info.suppressions != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[17]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[18]++;}

    info.suppressions = suppressions;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[125]++;
    return true;
  }

  /**
   * Add modifies values.
   */
  void addModifies(String modifies) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[126]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[127]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((info.modifies == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[19]++;
      info.modifies = Sets.newHashSet();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[128]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[20]++;}
    info.modifies.add(modifies);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[129]++;
  }

  /**
   * Sets modifies values.
   * @param modifies A list of modifies types.
   */
  boolean setModifies(Set<String> modifies) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[130]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[131]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((info.modifies != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[21]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[22]++;}

    info.modifies = modifies;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[132]++;
    return true;
  }

  /**
   * Documents the version.
   */
  boolean documentVersion(String version) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[133]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[23]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[24]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[134]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((documentation.version != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[25]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[26]++;}

    documentation.version = version;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[135]++;
    return true;
  }

  /**
   * Documents a reference (i.e. adds a "see" reference to the list).
   */
  boolean documentReference(String reference) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[136]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[27]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[28]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[137]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((documentation.sees == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[29]++;
      documentation.sees = Lists.newArrayList();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[138]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[30]++;}

    documentation.sees.add(reference);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[139]++;
    return true;
  }

  /**
   * Documents the author (i.e. adds it to the author list).
   */
  boolean documentAuthor(String author) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[140]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[31]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[32]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[141]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((documentation.authors == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[33]++;
      documentation.authors = Lists.newArrayList();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[142]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[34]++;}

    documentation.authors.add(author);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[143]++;
    return true;
  }

  /**
   * Documents the throws (i.e. adds it to the throws list).
   */
  boolean documentThrows(JSTypeExpression type, String throwsDescription) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[144]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[35]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[36]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[145]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((documentation.throwsDescriptions == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[37]++;
      documentation.throwsDescriptions =
          new LinkedHashMap<JSTypeExpression, String>();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[146]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[38]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[147]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((documentation.throwsDescriptions.containsKey(type)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[39]++;
      documentation.throwsDescriptions.put(type, throwsDescription);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[148]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[40]++;}

    return false;
  }


  /**
   * Documents a parameter. Parameters are described using the {@code @param}
   * annotation.
   *
   * @param parameter the parameter's name
   * @param description the parameter's description
   */
  boolean documentParam(String parameter, String description) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[149]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[41]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[42]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[150]++;
int CodeCoverConditionCoverageHelper_C22;

    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((documentation.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[43]++;
      documentation.parameters = new LinkedHashMap<String, String>();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[151]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[44]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[152]++;
int CodeCoverConditionCoverageHelper_C23;

    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((documentation.parameters.containsKey(parameter)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[45]++;
      documentation.parameters.put(parameter, description);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[153]++;
      return true;

    } else {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[46]++;
      return false;
    }
  }

  /**
   * Documents the block-level comment/description.
   *
   * @param description the description
   */
  boolean documentBlock(String description) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[154]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[47]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[48]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[155]++;
int CodeCoverConditionCoverageHelper_C25;

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((documentation.blockDescription != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[49]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[50]++;}

    documentation.blockDescription = description;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[156]++;
    return true;
  }

  /**
   * Documents the fileoverview comment/description.
   *
   * @param description the description
   */
  boolean documentFileOverview(String description) {
    setFlag(true, MASK_FILEOVERVIEW);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[157]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[158]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[51]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[52]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[159]++;
int CodeCoverConditionCoverageHelper_C27;

    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((documentation.fileOverview != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[53]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[54]++;}

    documentation.fileOverview = description;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[160]++;
    return true;
  }

  /**
   * Documents the return value. Return value is described using the
   * {@code @return} annotation.
   *
   * @param description the return value's description
   */
  boolean documentReturn(String description) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[161]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[55]++;
      return true;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[56]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[162]++;
int CodeCoverConditionCoverageHelper_C29;

    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((documentation.returnDescription != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[57]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[58]++;}

    documentation.returnDescription = description;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[163]++;
    return true;
  }

  /**
   * Declares a parameter. Parameters are described using the {@code @param}
   * annotation.
   *
   * @param jsType the parameter's type, it may be {@code null} when the
   *     {@code @param} annotation did not specify a type.
   * @param parameter the parameter's name
   */
  boolean declareParam(JSTypeExpression jsType, String parameter) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[164]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[165]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((info.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[59]++;
      info.parameters = new LinkedHashMap<String, JSTypeExpression>();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[166]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[60]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[167]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((info.parameters.containsKey(parameter)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[61]++;
      info.parameters.put(parameter, jsType);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[168]++;
      return true;

    } else {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[62]++;
      return false;
    }
  }

  /**
   * Declares a template type name. Template type names are described using the
   * {@code @template} annotation.
   *
   * @param templateTypeNames the template type name.
   */
  boolean declareTemplateTypeNames(List<String> templateTypeNames) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[169]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[170]++;
int CodeCoverConditionCoverageHelper_C32;

    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((info.templateTypeNames != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[63]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[64]++;}

    info.templateTypeNames = ImmutableList.copyOf(templateTypeNames);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[171]++;
    return true;
  }

  /**
   * Declares that the method throws a given type.
   *
   * @param jsType The type that can be thrown by the method.
   */
  boolean declareThrows(JSTypeExpression jsType) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[172]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[173]++;
int CodeCoverConditionCoverageHelper_C33;

    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((info.thrownTypes == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[65]++;
      info.thrownTypes = Lists.newArrayList();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[174]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[66]++;}

    info.thrownTypes.add(jsType);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[175]++;
    return true;
  }

  /**
   * Gets the visibility specified by {@code @private}, {@code @protected} or
   * {@code @public} annotation. If no visibility is specified, visibility
   * is inherited from the base class.
   */
  public Visibility getVisibility() {
    return visibility;
  }

  /**
   * Gets the parameter type.
   * @param parameter the parameter's name
   * @return the parameter's type or {@code null} if this parameter is not
   *     defined or has a {@code null} type
   */
  public JSTypeExpression getParameterType(String parameter) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[176]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((info.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[67]++;
      return null;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[68]++;}
    return info.parameters.get(parameter);
  }

  /**
   * Returns whether the parameter is defined.
   */
  public boolean hasParameter(String parameter) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[177]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((info.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[69]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[70]++;}
    return info.parameters.containsKey(parameter);
  }

  /**
   * Returns whether the parameter has an attached type.
   *
   * @return {@code true} if the parameter has an attached type, {@code false}
   *     if the parameter has no attached type or does not exist.
   */
  public boolean hasParameterType(String parameter) {
    return getParameterType(parameter) != null;
  }

  /**
   * Returns the set of names of the defined parameters. The iteration order
   * of the returned set is not the order in which parameters are defined.
   *
   * @return the set of names of the defined parameters. The returned set is
   *     immutable.
   */
  public Set<String> getParameterNames() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[178]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((info.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[71]++;
      return ImmutableSet.of();

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[72]++;}
    return ImmutableSet.copyOf(info.parameters.keySet());
  }

  /**
   * Gets the number of parameters defined.
   */
  public int getParameterCount() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[179]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((info.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[73]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[74]++;}
    return info.parameters.size();
  }

  void setType(JSTypeExpression type) {
    setType(type, TYPEFIELD_TYPE);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[180]++;
  }

  void setReturnType(JSTypeExpression type) {
    setType(type, TYPEFIELD_RETURN);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[181]++;
  }

  void setEnumParameterType(JSTypeExpression type) {
    setType(type, TYPEFIELD_ENUM);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[182]++;
  }

  void setTypedefType(JSTypeExpression type) {
    setType(type, TYPEFIELD_TYPEDEF);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[183]++;
  }

  private void setType(JSTypeExpression type, int mask) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[184]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 (((bitset & MASK_TYPEFIELD) != 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[75]++;
      throw new IllegalStateException(
          "API tried to add two incompatible type tags. " +
          "This should have been blocked and emitted a warning.");

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[76]++;}
    this.bitset = (bitset & MASK_FLAGS) | mask;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[185]++;
    this.type = type;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[186]++;
  }

  /**
   * Returns the list of thrown types.
   */
  public List<JSTypeExpression> getThrownTypes() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[187]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((info.thrownTypes == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[77]++;
      return ImmutableList.of();

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[78]++;}
    return Collections.unmodifiableList(info.thrownTypes);
  }

  /**
   * Returns whether a type, specified using the {@code @type} annotation, is
   * present on this JSDoc.
   */
  public boolean hasType() {
    return hasType(TYPEFIELD_TYPE);
  }

  /**
   * Returns whether an enum parameter type, specified using the {@code @enum}
   * annotation, is present on this JSDoc.
   */
  public boolean hasEnumParameterType() {
    return hasType(TYPEFIELD_ENUM);
  }

  /**
   * Returns whether a typedef parameter type, specified using the
   * {@code @typedef} annotation, is present on this JSDoc.
   */
  public boolean hasTypedefType() {
    return hasType(TYPEFIELD_TYPEDEF);
  }

  /**
   * Returns whether this {@link JSDocInfo} contains a type for {@code @return}
   * annotation.
   */
  public boolean hasReturnType() {
    return hasType(TYPEFIELD_RETURN);
  }

  private boolean hasType(int mask) {
    return (bitset & MASK_TYPEFIELD) == mask;
  }

  /**
   * Gets the type specified by the {@code @type} annotation.
   */
  public JSTypeExpression getType() {
    return getType(TYPEFIELD_TYPE);
  }

  /**
   * Gets the return type specified by the {@code @return} annotation.
   */
  public JSTypeExpression getReturnType() {
    return getType(TYPEFIELD_RETURN);
  }

  /**
   * Gets the enum parameter type specified by the {@code @enum} annotation.
   */
  public JSTypeExpression getEnumParameterType() {
    return getType(TYPEFIELD_ENUM);
  }

  /**
   * Gets the typedef type specified by the {@code @type} annotation.
   */
  public JSTypeExpression getTypedefType() {
    return getType(TYPEFIELD_TYPEDEF);
  }

  private JSTypeExpression getType(int typefield) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[188]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 (((MASK_TYPEFIELD & bitset) == typefield) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[79]++;
      return type;

    } else {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[80]++;
      return null;
    }
  }

  /**
   * Gets the type specified by the {@code @this} annotation.
   */
  public JSTypeExpression getThisType() {
    return thisType;
  }

  /**
   * Sets the type specified by the {@code @this} annotation.
   */
  void setThisType(JSTypeExpression type) {
    this.thisType = type;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[189]++;
  }

  /**
   * Returns whether this {@link JSDocInfo} contains a type for {@code @this}
   * annotation.
   */
  public boolean hasThisType() {
    return thisType != null;
  }

  void setBaseType(JSTypeExpression type) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[190]++;
    info.baseType = type;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[191]++;
  }

  /**
   * Gets the base type specified by the {@code @extends} annotation.
   */
  public JSTypeExpression getBaseType() {
    return (info == null) ? null : info.baseType;
  }

  /**
   * Gets the description specified by the {@code @desc} annotation.
   */
  public String getDescription() {
    return (info == null) ? null : info.description;
  }

  void setDescription(String desc) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[192]++;
    info.description = desc;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[193]++;
  }

  /**
   * Gets the meaning specified by the {@code @meaning} annotation.
   *
   * In localization systems, two messages with the same content but
   * different "meanings" may be translated differently. By default, we
   * use the name of the variable that the message is initialized to as
   * the "meaning" of the message.
   *
   * But some code generators (like Closure Templates) inject their own
   * meaning with the jsdoc {@code @meaning} annotation.
   */
  public String getMeaning() {
    return (info == null) ? null : info.meaning;
  }

  void setMeaning(String meaning) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[194]++;
    info.meaning = meaning;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[195]++;
  }

  /**
   * Gets the name we're lending to in a {@code @lends} annotation.
   *
   * In many reflection APIs, you pass an anonymous object to a function,
   * and that function mixes the anonymous object into another object.
   * The {@code @lends} annotation allows the type system to track
   * those property assignments.
   */
  public String getLendsName() {
    return (info == null) ? null : info.lendsName;
  }

  void setLendsName(String name) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[196]++;
    info.lendsName = name;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[197]++;
  }

  /**
   * Returns whether JSDoc is annotated with {@code @ngInject} annotation.
   */
  public boolean isNgInject() {
    return (info == null) ? false : info.ngInject;
  }

  void setNgInject(boolean ngInject) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[198]++;
    info.ngInject = ngInject;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[199]++;
  }

  /**
   * Gets the description specified by the {@code @license} annotation.
   */
  public String getLicense() {
    return (info == null) ? null : info.license;
  }

  /** License directives can appear in multiple comments, and always
   * apply to the entire file.  Break protection and allow outsiders to
   * update the license string so that we can attach the license text even
   * when the JSDocInfo has been created and tagged with other information.
   * @param license String containing new license text.
   */

  public void setLicense(String license) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[200]++;
    info.license = license;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[201]++;
  }

  @Override
  public String toString() {
    return "JSDocInfo";
  }

  /**
   * Returns whether this {@link JSDocInfo} contains a type for {@code @extends}
   * annotation.
   */
  public boolean hasBaseType() {
    return getBaseType() != null;
  }

  /**
   * Adds an implemented interface. Returns whether the interface was added. If
   * the interface was already present in the list, it won't get added again.
   */
  boolean addImplementedInterface(JSTypeExpression interfaceName) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[202]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[203]++;
int CodeCoverConditionCoverageHelper_C41;
    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((info.implementedInterfaces == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[81]++;
      info.implementedInterfaces = Lists.newArrayListWithCapacity(2);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[204]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[82]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[205]++;
int CodeCoverConditionCoverageHelper_C42;
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((info.implementedInterfaces.contains(interfaceName)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[83]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[84]++;}
    info.implementedInterfaces.add(interfaceName);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[206]++;
    return true;
  }

  /**
   * Returns the types specified by the {@code @implements} annotation.
   *
   * @return An immutable list of JSTypeExpression objects that can
   *    be resolved to types.
   */
  public List<JSTypeExpression> getImplementedInterfaces() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[207]++;
int CodeCoverConditionCoverageHelper_C43;
    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((info.implementedInterfaces == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[85]++;
      return ImmutableList.of();

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[86]++;}
    return Collections.unmodifiableList(info.implementedInterfaces);
  }

  /**
   * Gets the number of interfaces specified by the {@code @implements}
   * annotation.
   */
  public int getImplementedInterfaceCount() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[208]++;
int CodeCoverConditionCoverageHelper_C44;
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((info.implementedInterfaces == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[87]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[88]++;}
    return info.implementedInterfaces.size();
  }

  /**
   * Adds an extended interface (for interface only).
   * Returns whether the type was added.
   * if the type was already present in the list, it won't get added again.
   */
  boolean addExtendedInterface(JSTypeExpression type) {
    lazyInitInfo();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[209]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[210]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((info.extendedInterfaces == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[89]++;
      info.extendedInterfaces = Lists.newArrayListWithCapacity(2);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[211]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[90]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[212]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((info.extendedInterfaces.contains(type)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[91]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[92]++;}
    info.extendedInterfaces.add(type);
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[213]++;
    return true;
  }

  /**
   * Returns the interfaces extended by an interface
   *
   * @return An immutable list of JSTypeExpression objects that can
   *    be resolved to types.
   */
  public List<JSTypeExpression> getExtendedInterfaces() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[214]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((info.extendedInterfaces == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[93]++;
      return ImmutableList.of();

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[94]++;}
    return Collections.unmodifiableList(info.extendedInterfaces);
  }

  /**
   * Gets the number of extended interfaces specified
   */
  public int getExtendedInterfacesCount() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[215]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((info.extendedInterfaces == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[95]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[96]++;}
    return info.extendedInterfaces.size();
  }

  /**
   * Returns the deprecation reason or null if none specified.
   */
  public String getDeprecationReason() {
    return info == null ? null : info.deprecated;
  }

  /**
   * Returns the set of suppressed warnings.
   */
  public Set<String> getSuppressions() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[216]++;
    Set<String> suppressions = info == null ? null : info.suppressions;
    return suppressions == null ? Collections.<String>emptySet() : suppressions;
  }

  /**
   * Returns the set of sideeffect notations.
   */
  public Set<String> getModifies() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[217]++;
    Set<String> modifies = info == null ? null : info.modifies;
    return modifies == null ? Collections.<String>emptySet() : modifies;
  }

  /**
   * Returns whether a description exists for the parameter with the specified
   * name.
   */
  public boolean hasDescriptionForParameter(String name) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[218]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((documentation == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((documentation.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[97]++;
      return false;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[98]++;}

    return documentation.parameters.containsKey(name);
  }

  /**
   * Returns the description for the parameter with the given name, if its
   * exists.
   */
  public String getDescriptionForParameter(String name) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[219]++;
int CodeCoverConditionCoverageHelper_C50;
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((documentation == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((documentation.parameters == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[99]++;
      return null;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[100]++;}

    return documentation.parameters.get(name);
  }

  /**
   * Returns the list of authors or null if none.
   */
  public Collection<String> getAuthors() {
    return documentation == null ? null : documentation.authors;
  }

  /**
   * Returns the list of references or null if none.
   */
  public Collection<String> getReferences() {
    return documentation == null ? null : documentation.sees;
  }

  /**
   * Returns the version or null if none.
   */
  public String getVersion() {
    return documentation == null ? null : documentation.version;
  }

  /**
   * Returns the description of the returned object or null if none specified.
   */
  public String getReturnDescription() {
    return documentation == null ? null : documentation.returnDescription;
  }

  /**
   * Returns the block-level description or null if none specified.
   */
  public String getBlockDescription() {
    return documentation == null ? null : documentation.blockDescription;
  }

  /**
   * Returns whether this has a fileoverview flag.
   */
  public boolean hasFileOverview() {
    return getFlag(MASK_FILEOVERVIEW);
  }

  /**
   * Returns the file overview or null if none specified.
   */
  public String getFileOverview() {
    return documentation == null ? null : documentation.fileOverview;
  }

  public Node getAssociatedNode() {
    return this.associatedNode;
  }

  /**
   * Sets the node associated with this JSDoc.
   * Notice that many nodes may have pointer to the same JSDocInfo
   * object (because we propagate it across the type graph). But there
   * is only one canonical "owner" node of the JSDocInfo, which corresponds
   * to its original place in the syntax tree.
   */
  public void setAssociatedNode(Node node) {
    this.associatedNode = node;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[220]++;
  }

  /** Gets the name of the source file that contains this JSDoc. */
  public String getSourceName() {
    return this.associatedNode != null
        ? this.associatedNode.getSourceFileName() : null;
  }

  /** Gets the list of all markers for the documentation in this JSDoc. */
  public Collection<Marker> getMarkers() {
    return (documentation == null || documentation.markers == null)
        ? ImmutableList.<Marker>of() : documentation.markers;
  }

  /** Gets the template type name. */
  public ImmutableList<String> getTemplateTypeNames() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[221]++;
int CodeCoverConditionCoverageHelper_C51;
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((info.templateTypeNames == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[101]++;
      return ImmutableList.of();

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[102]++;}
    return info.templateTypeNames;
  }

  /**
   * Returns a collection of all type nodes that are a part of this JSDocInfo.
   * This includes @type, @this, @extends, @implements, @param, @throws,
   * and @return.  Any future type specific JSDoc should make sure to add the
   * appropriate nodes here.
   * @return collection of all type nodes
   */
  public Collection<Node> getTypeNodes() {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[222]++;
    List<Node> nodes = Lists.newArrayList();
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[223]++;
int CodeCoverConditionCoverageHelper_C52;

    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[103]++;
      nodes.add(type.getRoot());
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[224]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[104]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[225]++;
int CodeCoverConditionCoverageHelper_C53;

    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((thisType != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[105]++;
      nodes.add(thisType.getRoot());
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[226]++;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[106]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[227]++;
int CodeCoverConditionCoverageHelper_C54;

    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[107]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[228]++;
int CodeCoverConditionCoverageHelper_C55;
      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((info.baseType != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[109]++;
        nodes.add(info.baseType.getRoot());
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[229]++;

      } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[110]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[230]++;
int CodeCoverConditionCoverageHelper_C56;

      if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((info.extendedInterfaces != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[111]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[231]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[1]++;


        for (JSTypeExpression interfaceType : info.extendedInterfaces) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[1]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[2]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[3]++;
}
          nodes.add(interfaceType.getRoot());
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[232]++;
        }

      } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[112]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[233]++;
int CodeCoverConditionCoverageHelper_C57;

      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((info.implementedInterfaces != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[113]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[234]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[4]++;


        for (JSTypeExpression interfaceType : info.implementedInterfaces) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[4]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[5]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[6]++;
}
          nodes.add(interfaceType.getRoot());
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[235]++;
        }

      } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[114]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[236]++;
int CodeCoverConditionCoverageHelper_C58;

      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((info.parameters != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[115]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[237]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[7]++;


        for (JSTypeExpression parameterType : info.parameters.values()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[7]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[8]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[9]++;
}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[238]++;
int CodeCoverConditionCoverageHelper_C59;
          if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((parameterType != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[117]++;
            nodes.add(parameterType.getRoot());
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[239]++;

          } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[118]++;}
        }

      } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[116]++;}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[240]++;
int CodeCoverConditionCoverageHelper_C60;

      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((info.thrownTypes != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[119]++;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[241]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[10]++;


        for (JSTypeExpression thrownType : info.thrownTypes) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[10]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[11]--;
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.loops[12]++;
}
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[242]++;
int CodeCoverConditionCoverageHelper_C61;
          if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((thrownType != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[121]++;
            nodes.add(thrownType.getRoot());
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[243]++;

          } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[122]++;}
        }

      } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[120]++;}

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[108]++;}

    return nodes;
  }

  public boolean hasModifies() {
    return info != null && info.modifies != null;
  }

  /**
   * Returns the original JSDoc comment string. Returns null unless
   * parseJsDocDocumentation is enabled via the ParserConfig.
   */
  public String getOriginalCommentString() {
    return documentation == null ? null : documentation.sourceComment;
  }

  void setOriginalCommentString(String sourceComment) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[244]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((lazyInitDocumentation()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[123]++;
      return;

    } else {
  CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.branches[124]++;}
    documentation.sourceComment = sourceComment;
CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5.statements[245]++;
  }
}

class CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5 ());
  }
    public static long[] statements = new long[246];
    public static long[] branches = new long[125];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[63];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.JSDocInfo.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,2,1,1,1,2,2,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 62; i++) {
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

  public CodeCoverCoverageCounter$34ru3fv57ou2kvh3uthnj5 () {
    super("com.google.javascript.rhino.JSDocInfo.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 245; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 124; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 62; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.JSDocInfo.java");
      for (int i = 1; i <= 245; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 124; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 62; i++) {
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

