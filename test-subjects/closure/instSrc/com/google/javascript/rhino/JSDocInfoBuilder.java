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

import com.google.javascript.rhino.JSDocInfo.Visibility;
import com.google.javascript.rhino.jstype.StaticSourceFile;

import java.util.List;
import java.util.Set;

/**
 * A builder for {@link JSDocInfo} objects. This builder abstracts the
 * construction process of {@link JSDocInfo} objects whilst minimizing the
 * number of instances of {@link JSDocInfo} objects. It provides early
 * incompatibility detection among properties stored on the {@code JSDocInfo}
 * object being created.
 *
 */
final public class JSDocInfoBuilder {
  static {
    CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.ping();
  }

  // the current JSDoc which is being populated
  private JSDocInfo currentInfo;

  // whether the current JSDocInfo has valuable information
  private boolean populated = false;
  {
    CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[1]++;
  }

  // whether to include the documentation itself when parsing the JsDoc
  private boolean parseDocumentation = false;
  {
    CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[2]++;
  }

  // the current marker, if any.
  private JSDocInfo.Marker currentMarker = null;
  {
    CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[3]++;
  }

  public JSDocInfoBuilder(boolean parseDocumentation) {
    this.currentInfo = new JSDocInfo(parseDocumentation);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[4]++;
    this.parseDocumentation = parseDocumentation;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[5]++;
  }

  /**
   * Sets the original JSDoc comment string. This is a no-op if the builder
   * isn't configured to record documentation.
   */
  public void recordOriginalCommentString(String sourceComment) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parseDocumentation) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[1]++;
      currentInfo.setOriginalCommentString(sourceComment);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[7]++;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[2]++;}
  }

  public boolean shouldParseDocumentation() {
    return parseDocumentation;
  }

  /**
   * Returns whether this builder is populated with information that can be
   * used to {@link #build} a {@link JSDocInfo} object.
   */
  public boolean isPopulated() {
    return populated;
  }

  /**
   * Returns whether this builder is populated with information that can be
   * used to {@link #build} a {@link JSDocInfo} object that has a
   * fileoverview tag.
   */
  public boolean isPopulatedWithFileOverview() {
    return isPopulated() &&
        (currentInfo.hasFileOverview() || currentInfo.isExterns() ||
         currentInfo.isNoCompile());
  }

  /**
   * Returns whether this builder recorded a description.
   */
  public boolean isDescriptionRecorded() {
    return currentInfo.getDescription() != null;
  }

  /**
   * Builds a {@link JSDocInfo} object based on the populated information and
   * returns it. Once this method is called, the builder can be reused to build
   * another {@link JSDocInfo} object.
   *
   * @param associatedNode The source node containing the JSDoc.
   * @return a {@link JSDocInfo} object populated with the values given to this
   *     builder. If no value was populated, this method simply returns
   *     {@code null}
   */
  public JSDocInfo build(Node associatedNode) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((populated) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[3]++;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[9]++;
      JSDocInfo built = currentInfo;
      built.setAssociatedNode(associatedNode);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[10]++;
      populateDefaults(built);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[11]++;
      populated = false;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[12]++;
      currentInfo = new JSDocInfo(this.parseDocumentation);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[13]++;
      return built;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[4]++;
      return null;
    }
  }

  /** Generate defaults when certain parameters are not specified. */
  private static void populateDefaults(JSDocInfo info) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((info.getVisibility() == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[5]++;
      info.setVisibility(Visibility.INHERITED);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[15]++;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[6]++;}
  }

  /**
   * Adds a marker to the current JSDocInfo and populates the marker with the
   * annotation information.
   */
  public void markAnnotation(String annotation, int lineno, int charno) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[16]++;
    JSDocInfo.Marker marker = currentInfo.addMarker();
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((marker != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[7]++;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[18]++;
      JSDocInfo.TrimmedStringPosition position =
          new JSDocInfo.TrimmedStringPosition();
      position.setItem(annotation);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[19]++;
      position.setPositionInformation(lineno, charno, lineno,
          charno + annotation.length());
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[20]++;
      marker.setAnnotation(position);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[21]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[22]++;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[8]++;}

    currentMarker = marker;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[23]++;
  }

  /**
   * Adds a textual block to the current marker.
   */
  public void markText(String text, int startLineno, int startCharno,
      int endLineno, int endCharno) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((currentMarker != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[9]++;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[25]++;
      JSDocInfo.StringPosition position = new JSDocInfo.StringPosition();
      position.setItem(text);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[26]++;
      position.setPositionInformation(startLineno, startCharno,
          endLineno, endCharno);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[27]++;
      currentMarker.setDescription(position);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[28]++;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[10]++;}
  }

  /**
   * Adds a type declaration to the current marker.
   */
  public void markTypeNode(Node typeNode, int lineno, int startCharno,
      int endLineno, int endCharno, boolean hasLC) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((currentMarker != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[11]++;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[30]++;
      JSDocInfo.TypePosition position = new JSDocInfo.TypePosition();
      position.setItem(typeNode);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[31]++;
      position.setHasBrackets(hasLC);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[32]++;
      position.setPositionInformation(lineno, startCharno,
          endLineno, endCharno);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[33]++;
      currentMarker.setType(position);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[34]++;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[12]++;}
  }

  /**
   * Adds a name declaration to the current marker.
   * @deprecated Use #markName(String, StaticSourceFile, int, int)
   */
  @Deprecated
  public void markName(String name,  int lineno, int charno) {
    markName(name, null, lineno, charno);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[35]++;
  }

  /**
   * Adds a name declaration to the current marker.
   */
  public void markName(String name, StaticSourceFile file,
      int lineno, int charno) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((currentMarker != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[13]++;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[37]++;
      // Record the name as both a SourcePosition<String> and a
      // SourcePosition<Node>. The <String> form is deprecated,
      // because <Node> is more consistent with how other name
      // references are handled (see #markTypeNode)
      //
      // TODO(nicksantos): Remove all uses of the Name position
      // and replace them with the NameNode position.
      JSDocInfo.TrimmedStringPosition position =
          new JSDocInfo.TrimmedStringPosition();
      position.setItem(name);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[38]++;
      position.setPositionInformation(lineno, charno,
          lineno, charno + name.length());
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[39]++;
      currentMarker.setName(position);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[40]++;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[41]++;

      SourcePosition<Node> nodePos =
          new JSDocInfo.NamePosition();
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[42]++;
      Node node = Node.newString(Token.NAME, name, lineno, charno);
      node.setLength(name.length());
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[43]++;
      node.setStaticSourceFile(file);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[44]++;
      nodePos.setItem(node);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[45]++;
      nodePos.setPositionInformation(lineno, charno,
          lineno, charno + name.length());
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[46]++;
      currentMarker.setNameNode(nodePos);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[47]++;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[14]++;}
  }

  /**
   * Records a block-level description.
   *
   * @return {@code true} if the description was recorded.
   */
  public boolean recordBlockDescription(String description) {
    populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[48]++;
    return currentInfo.documentBlock(description);
  }

  /**
   * Records a visibility.
   *
   * @return {@code true} if the visibility was recorded and {@code false}
   *     if it was already defined
   */
  public boolean recordVisibility(Visibility visibility) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[49]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((currentInfo.getVisibility() == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[15]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[50]++;
      currentInfo.setVisibility(visibility);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[51]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[16]++;
      return false;
    }
  }

  /**
   * Records a typed parameter.
   *
   * @return {@code true} if the typed parameter was recorded and
   *     {@code false} if a parameter with the same name was already defined
   */
  public boolean recordParameter(String parameterName, JSTypeExpression type) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[52]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((currentInfo.declareParam(type, parameterName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[17]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[53]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[18]++;
      return false;
    }
  }

  /**
   * Records a parameter's description.
   *
   * @return {@code true} if the parameter's description was recorded and
   *     {@code false} if a parameter with the same name was already defined
   */
  public boolean recordParameterDescription(
      String parameterName, String description) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((currentInfo.documentParam(parameterName, description)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[19]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[55]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[20]++;
      return false;
    }
  }

  /**
   * Records a template type name.
   *
   * @return {@code true} if the template type name was recorded and
   *     {@code false} if a template type name was already defined.
   */
  public boolean recordTemplateTypeNames(List<String> names) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((currentInfo.declareTemplateTypeNames(names)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[21]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[57]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[22]++;
      return false;
    }
  }

  /**
   * Records a thrown type.
   */
  public boolean recordThrowType(JSTypeExpression type) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[58]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[23]++;
      currentInfo.declareThrows(type);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[59]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[60]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[24]++;}
    return false;
  }

  /**
   * Records a throw type's description.
   *
   * @return {@code true} if the type's description was recorded and
   *     {@code false} if a description with the same type was already defined
   */
  public boolean recordThrowDescription(
      JSTypeExpression type, String description) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((currentInfo.documentThrows(type, description)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[25]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[62]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[26]++;
      return false;
    }
  }


  /**
   * Adds an author to the current information.
   */
  public boolean addAuthor(String author) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((currentInfo.documentAuthor(author)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[27]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[64]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[28]++;
      return false;
    }
  }


  /**
   * Adds a reference ("@see") to the current information.
   */
  public boolean addReference(String reference) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[65]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((currentInfo.documentReference(reference)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[29]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[66]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[30]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isConsistentIdGenerator()} flag set to
   * {@code true}.
   *
   * @return {@code true} if the consistentIdGenerator flag was recorded and
   *     {@code false} if it was already recorded
   */
  public boolean recordConsistentIdGenerator() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((currentInfo.isConsistentIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[31]++;
      currentInfo.setConsistentIdGenerator(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[68]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[69]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[32]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its {@link
   * JSDocInfo#isStableIdGenerator()} flag set to {@code true}.
   *
   * @return {@code true} if the stableIdGenerator flag was recorded and {@code false} if it was
   *     already recorded.
   */
  public boolean recordStableIdGenerator() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((currentInfo.isStableIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[33]++;
      currentInfo.setStableIdGenerator(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[71]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[72]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[34]++;
      return false;
    }
  }

  /**
   * Records the version.
   */
  public boolean recordVersion(String version) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((currentInfo.documentVersion(version)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[35]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[74]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[36]++;
      return false;
    }
  }

  /**
   * Records the deprecation reason.
   */
  public boolean recordDeprecationReason(String reason) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[75]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((currentInfo.setDeprecationReason(reason)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[37]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[76]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[38]++;
      return false;
    }
  }

  /**
   * Records the list of suppressed warnings.
   */
  public boolean recordSuppressions(Set<String> suppressions) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[77]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((currentInfo.setSuppressions(suppressions)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[39]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[78]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[40]++;
      return false;
    }
  }

  /**
   * Records the list of modifies warnings.
   */
  public boolean recordModifies(Set<String> modifies) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((hasAnySingletonSideEffectTags()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((currentInfo.setModifies(modifies)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[41]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[80]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[42]++;
      return false;
    }
  }

  /**
   * Records a type.
   *
   * @return {@code true} if the type was recorded and {@code false} if
   *     it is invalid or was already defined
   */
  public boolean recordType(JSTypeExpression type) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[81]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((hasAnyTypeRelatedTags()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[43]++;
      currentInfo.setType(type);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[82]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[83]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[44]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should be populated
   * with a {@code typedef}'d type.
   */
  public boolean recordTypedef(JSTypeExpression type) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[84]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((hasAnyTypeRelatedTags()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[45]++;
      currentInfo.setTypedefType(type);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[85]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[86]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[46]++;}
    return false;
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isIdGenerator()} flag set to
   * {@code true}.
   *
   * @return {@code true} if the idGenerator flag was recorded and {@code false}
   *     if it was already recorded
   */
  public boolean recordIdGenerator() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[87]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((currentInfo.isIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[47]++;
      currentInfo.setIdGenerator(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[88]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[89]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[48]++;
      return false;
    }
  }

  /**
   * Records a return type.
   *
   * @return {@code true} if the return type was recorded and {@code false} if
   *     it is invalid or was already defined
   */
  public boolean recordReturnType(JSTypeExpression jsType) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[90]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((jsType != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((currentInfo.getReturnType() == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[49]++;
      currentInfo.setReturnType(jsType);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[91]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[92]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[50]++;
      return false;
    }
  }

  /**
   * Records a return description
   *
   * @return {@code true} if the return description was recorded and
   *     {@code false} if it is invalid or was already defined
   */
  public boolean recordReturnDescription(String description) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[93]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((currentInfo.documentReturn(description)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[51]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[94]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[52]++;
      return false;
    }
  }

  /**
   * Records the type of a define.
   *
   * 'Define' values are special constants that may be manipulated by
   * the compiler. They are designed to mimic the #define command in
   * the C preprocessor.
   */
  public boolean recordDefineType(JSTypeExpression type) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[95]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (128)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C27 |= (32)) == 0 || true) &&
 ((currentInfo.isConstant()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((currentInfo.isDefine()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((recordType(type)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[53]++;
      currentInfo.setDefine(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[96]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[97]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[54]++;
      return false;
    }
  }

  /**
   * Records a parameter type to an enum.
   *
   * @return {@code true} if the enum's parameter type was recorded and
   *     {@code false} if it was invalid or already defined
   */
  public boolean recordEnumParameterType(JSTypeExpression type) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[98]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((hasAnyTypeRelatedTags()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[55]++;
      currentInfo.setEnumParameterType(type);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[99]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[100]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[56]++;
      return false;
    }
  }

  /**
   * Records a type for {@code @this} annotation.
   *
   * @return {@code true} if the type was recorded and
   *     {@code false} if it is invalid or if it collided with {@code @enum} or
   *     {@code @type} annotations
   */
  public boolean recordThisType(JSTypeExpression type) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[101]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((currentInfo.hasThisType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[57]++;
      currentInfo.setThisType(type);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[102]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[103]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[58]++;
      return false;
    }
  }

  /**
   * Records a base type.
   *
   * @return {@code true} if the base type was recorded and {@code false}
   *     if it was already defined
   */
  public boolean recordBaseType(JSTypeExpression jsType) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[104]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (32)) == 0 || true) &&
 ((jsType != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((currentInfo.hasBaseType()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[59]++;
      currentInfo.setBaseType(jsType);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[105]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[106]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[60]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isConstant()} flag set to {@code true}.
   *
   * @return {@code true} if the constancy was recorded and {@code false}
   *     if it was already defined
   */
  public boolean recordConstancy() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[107]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((currentInfo.isConstant()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[61]++;
      currentInfo.setConstant(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[108]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[109]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[62]++;
      return false;
    }
  }

  /**
   * Records a description giving context for translation (i18n).
   *
   * @return {@code true} if the description was recorded and {@code false}
   *     if the description was invalid or was already defined
   */
  public boolean recordDescription(String description) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[110]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((description != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((currentInfo.getDescription() == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[63]++;
      currentInfo.setDescription(description);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[111]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[112]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[64]++;
      return false;
    }
  }

  /**
   * Records a meaning giving context for translation (i18n). Different
   * meanings will result in different translations.
   *
   * @return {@code true} If the meaning was successfully updated.
   */
  public boolean recordMeaning(String meaning) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[113]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((meaning != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((currentInfo.getMeaning() == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[65]++;
      currentInfo.setMeaning(meaning);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[114]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[115]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[66]++;
      return false;
    }
  }

  /**
   * Records a fileoverview description.
   *
   * @return {@code true} if the description was recorded and {@code false}
   *     if the description was invalid or was already defined.
   */
  public boolean recordFileOverview(String description) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[116]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((currentInfo.documentFileOverview(description)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[67]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[117]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[68]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isHidden()} flag set to {@code true}.
   *
   * @return {@code true} if the hiddenness was recorded and {@code false}
   *     if it was already defined
   */
  public boolean recordHiddenness() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[118]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((currentInfo.isHidden()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[69]++;
      currentInfo.setHidden(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[119]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[120]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[70]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isNoCompile()} flag set to {@code true}.
   *
   * @return {@code true} if the no compile flag was recorded and {@code false}
   *     if it was already recorded
   */
  public boolean recordNoCompile() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[121]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((currentInfo.isNoCompile()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[71]++;
      currentInfo.setNoCompile(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[122]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[123]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[72]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isNoTypeCheck()} flag set to {@code true}.
   *
   * @return {@code true} if the no check flag was recorded and {@code false}
   *     if it was already recorded
   */
  public boolean recordNoTypeCheck() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[124]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((currentInfo.isNoTypeCheck()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[73]++;
      currentInfo.setNoCheck(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[125]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[126]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[74]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isConstructor()} flag set to {@code true}.
   *
   * @return {@code true} if the constructor was recorded and {@code false}
   *     if it was already defined or it was incompatible with the existing
   *     flags
   */
  public boolean recordConstructor() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[127]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (32)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((currentInfo.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((currentInfo.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 3) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 3) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[75]++;
      currentInfo.setConstructor(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[128]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[129]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[76]++;
      return false;
    }
  }

  /**
   * Whether the {@link JSDocInfo} being built will have its
   * {@link JSDocInfo#isConstructor()} flag set to {@code true}.
   */
  public boolean isConstructorRecorded() {
    return currentInfo.isConstructor();
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#makesStructs()} flag set to {@code true}.
   *
   * @return {@code true} if the struct was recorded and {@code false}
   * if it was already defined or it was incompatible with the existing flags
   */
  public boolean recordStruct() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[130]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (128)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((currentInfo.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((currentInfo.makesDicts()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((currentInfo.makesStructs()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 4) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 4) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[77]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[78]++;}
    currentInfo.setStruct();
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[131]++;
    populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[132]++;
    return true;
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#makesDicts()} flag set to {@code true}.
   *
   * @return {@code true} if the dict was recorded and {@code false}
   * if it was already defined or it was incompatible with the existing flags
   */
  public boolean recordDict() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[133]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (128)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((currentInfo.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((currentInfo.makesDicts()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((currentInfo.makesStructs()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 4) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 4) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[79]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[80]++;}
    currentInfo.setDict();
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[134]++;
    populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[135]++;
    return true;
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isJavaDispatch()} flag set to {@code true}.
   *
   * @return {@code true} if the javadispatch was recorded and {@code false}
   *     if it was already defined or it was incompatible with the existing
   *     flags
   */
  public boolean recordJavaDispatch() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[136]++;
int CodeCoverConditionCoverageHelper_C41;
    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((currentInfo.isJavaDispatch()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[81]++;
      currentInfo.setJavaDispatch(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[137]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[138]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[82]++;
      return false;
    }
  }

  /**
   * Whether the {@link JSDocInfo} being built will have its
   * {@link JSDocInfo#isJavaDispatch()} flag set to {@code true}.
   */
  public boolean isJavaDispatch() {
    return currentInfo.isJavaDispatch();
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#shouldPreserveTry()} flag set to {@code true}.
   */
  public boolean recordPreserveTry() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[139]++;
int CodeCoverConditionCoverageHelper_C42;
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((currentInfo.shouldPreserveTry()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[83]++;
      currentInfo.setShouldPreserveTry(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[140]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[141]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[84]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isOverride()} flag set to {@code true}.
   */
  public boolean recordOverride() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[142]++;
int CodeCoverConditionCoverageHelper_C43;
    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((currentInfo.isOverride()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[85]++;
      currentInfo.setOverride(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[143]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[144]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[86]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isNoAlias()} flag set to {@code true}.
   */
  public boolean recordNoAlias() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[145]++;
int CodeCoverConditionCoverageHelper_C44;
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((currentInfo.isNoAlias()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[87]++;
      currentInfo.setNoAlias(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[146]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[147]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[88]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isDeprecated()} flag set to {@code true}.
   */
  public boolean recordDeprecated() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[148]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((currentInfo.isDeprecated()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[89]++;
      currentInfo.setDeprecated(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[149]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[150]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[90]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isInterface()} flag set to {@code true}.
   *
   * @return {@code true} if the flag was recorded and {@code false}
   * if it was already defined or it was incompatible with the existing flags
   */
  public boolean recordInterface() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[151]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (512)) == 0 || true) &&
 ((hasAnySingletonTypeTags()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (128)) == 0 || true) &&
 ((currentInfo.makesStructs()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (32)) == 0 || true) &&
 ((currentInfo.makesDicts()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((currentInfo.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((currentInfo.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 5) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 5) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[91]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[92]++;}
    currentInfo.setInterface(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[152]++;
    populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[153]++;
    return true;
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isExport()} flag set to {@code true}.
   */
  public boolean recordExport() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[154]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((currentInfo.isExport()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[93]++;
      currentInfo.setExport(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[155]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[156]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[94]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isExpose()} flag set to {@code true}.
   */
  public boolean recordExpose() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[157]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((currentInfo.isExpose()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[95]++;
      currentInfo.setExpose(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[158]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[159]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[96]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isNoShadow()} flag set to {@code true}.
   */
  public boolean recordNoShadow() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[160]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((currentInfo.isNoShadow()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[97]++;
      currentInfo.setNoShadow(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[161]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[162]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[98]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isImplicitCast()} flag set to {@code true}.
   */
  public boolean recordImplicitCast() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[163]++;
int CodeCoverConditionCoverageHelper_C50;
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((currentInfo.isImplicitCast()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[99]++;
      currentInfo.setImplicitCast(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[164]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[165]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[100]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isNoSideEffects()} flag set to {@code true}.
   */
  public boolean recordNoSideEffects() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[166]++;
int CodeCoverConditionCoverageHelper_C51;
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((hasAnySingletonSideEffectTags()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((currentInfo.isNoSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[101]++;
      currentInfo.setNoSideEffects(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[167]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[168]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[102]++;
      return false;
    }
  }

  /**
   * Records that the {@link JSDocInfo} being built should have its
   * {@link JSDocInfo#isExterns()} flag set to {@code true}.
   */
  public boolean recordExterns() {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[169]++;
int CodeCoverConditionCoverageHelper_C52;
    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((currentInfo.isExterns()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[103]++;
      currentInfo.setExterns(true);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[170]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[171]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[104]++;
      return false;
    }
  }

  /**
   * Whether the {@link JSDocInfo} being built will have its
   * {@link JSDocInfo#isInterface()} flag set to {@code true}.
   */
  public boolean isInterfaceRecorded() {
    return currentInfo.isInterface();
  }

  /**
   * @return Whether a parameter of the given name has already been recorded.
   */
  public boolean hasParameter(String name) {
    return currentInfo.hasParameter(name);
  }

  /**
   * Records an implemented interface.
   */
  public boolean recordImplementedInterface(JSTypeExpression interfaceName) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[172]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((currentInfo.addImplementedInterface(interfaceName)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[105]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[173]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[106]++;
      return false;
    }
  }

  /**
   * Records an extended interface type.
   */
  public boolean recordExtendedInterface(JSTypeExpression interfaceType) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[174]++;
int CodeCoverConditionCoverageHelper_C54;
    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((currentInfo.addExtendedInterface(interfaceType)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[107]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[175]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[108]++;
      return false;
    }
  }

  /**
   * Records that we're lending to another name.
   */
  public boolean recordLends(String name) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[176]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((hasAnyTypeRelatedTags()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[109]++;
      currentInfo.setLendsName(name);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[177]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[178]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[110]++;
      return false;
    }
  }

  /**
   * Returns whether current JSDoc is annotated with {@code @ngInject}.
   */
  public boolean isNgInjectRecorded() {
    return currentInfo.isNgInject();
  }

  /**
   * Records that we'd like to add {@code $inject} property inferred from
   * parameters.
   */
  public boolean recordNgInject(boolean ngInject) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[179]++;
int CodeCoverConditionCoverageHelper_C56;
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((isNgInjectRecorded()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[111]++;
      currentInfo.setNgInject(ngInject);
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[180]++;
      populated = true;
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.statements[181]++;
      return true;

    } else {
CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h.branches[112]++;
      return false;
    }
  }

  /**
   * Whether the current doc info has other type tags, like
   * {@code @param} or {@code @return} or {@code @type} or etc.
   */
  private boolean hasAnyTypeRelatedTags() {
    return currentInfo.isConstructor() ||
        currentInfo.isInterface() ||
        currentInfo.getParameterCount() > 0 ||
        currentInfo.hasReturnType() ||
        currentInfo.hasBaseType() ||
        currentInfo.getExtendedInterfacesCount() > 0 ||
        currentInfo.getLendsName() != null ||
        currentInfo.hasThisType() ||
        hasAnySingletonTypeTags();
  }

  /**
   * Whether the current doc info has any of the singleton type
   * tags that may not appear with other type tags, like
   * {@code @type} or {@code @typedef}.
   */
  private boolean hasAnySingletonTypeTags() {
    return currentInfo.hasType() ||
        currentInfo.hasTypedefType() ||
        currentInfo.hasEnumParameterType();
  }

  /**
   * Whether the current doc info has any of the singleton type
   * tags that may not appear with other type tags, like
   * {@code @type} or {@code @typedef}.
   */
  private boolean hasAnySingletonSideEffectTags() {
    return currentInfo.isNoSideEffects() ||
        currentInfo.hasModifies();
  }

}

class CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h ());
  }
    public static long[] statements = new long[182];
    public static long[] branches = new long[113];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[57];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.JSDocInfoBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,3,1,3,2,3,3,1,2,2,1,1,1,1,3,3,3,1,1,1,1,1,3,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 56; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1pqlhsyqs7imhbpzdzdj64i70vn9rj04h () {
    super("com.google.javascript.rhino.JSDocInfoBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 181; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 112; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 56; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.JSDocInfoBuilder.java");
      for (int i = 1; i <= 181; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 112; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 56; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

