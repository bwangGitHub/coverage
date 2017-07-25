/*
 * Copyright 2004 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.javascript.jscomp.AbstractCompiler.LifeCycleStage;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Nullable;

/**
 * RenamePrototypes renames custom properties (including methods) of custom
 * prototypes and object literals. Externed property names are never renamed.
 *
 * To ensure that a prototype property or object literal property gets renamed,
 * end it with an underscore.
 *
 * To ensure that a prototype property is not renamed, give it a leading
 * underscore.
 *
 * For custom prototype property names that lack leading and trailing
 * underscores:
 * - To always rename these, use aggressive renaming.
 * - If aggressive renaming is off, we use a heuristic to decide whether to
 *   rename (to avoid most built-in JS methods). We rename if the original name
 *   contains at least one character that is not a lowercase letter.
 *
 * When a property name is used both in a prototype definition and as an object
 * literal key, we rename it only if it satisfies both renaming policies.
 *
 */
class RenamePrototypes implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.ping();
  }


  private final AbstractCompiler compiler;
  private final boolean aggressiveRenaming;
  private final char[] reservedCharacters;

  /** Previously used prototype renaming map. */
  private final VariableMap prevUsedRenameMap;

  /**
   * The Property class encapsulates the information needed for renaming
   * a method or member.
   */
  private class Property {
    String oldName;
    String newName;
    int prototypeCount;
    int objLitCount;
    int refCount;

    Property(String name) {
      this.oldName = name;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[1]++;
      this.newName = null;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[2]++;
      this.prototypeCount = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[3]++;
      this.objLitCount = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[4]++;
      this.refCount = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[5]++;
    }

    int count() {
      return prototypeCount + objLitCount + refCount;
    }

    boolean canRename() {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((this.prototypeCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.objLitCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[1]++;
        return canRenamePrototypeProperty();

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[2]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((this.objLitCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.prototypeCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[3]++;
        return canRenameObjLitProperty();

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[4]++;}
      // We're not sure what kind of property this is, so we're conservative.
      // Note that we still want to try renaming the property even when both
      // counts are zero. It may be a property added to an object at runtime,
      // like: o.newProp = x;
      return canRenamePrototypeProperty() && canRenameObjLitProperty();
    }

    private boolean canRenamePrototypeProperty() {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(oldName)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[5]++;
        // an externally visible name should not be renamed.
        return false;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[6]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;

      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isPrivate(oldName)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[7]++;
        // private names can be safely renamed. Rename!
        return true;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[8]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((aggressiveRenaming) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[9]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[10]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;

      for (int i = 0, n = oldName.length();(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[1]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[2]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[3]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[12]++;
        char ch = oldName.charAt(i);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((Character.isUpperCase(ch)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((Character.isLetter(ch)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[11]++;
          return true;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[12]++;}
      }
      return false;
    }

    private boolean canRenameObjLitProperty() {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[14]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(oldName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[13]++;
        // an externally visible name should not be renamed.
        return false;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[14]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[15]++;
int CodeCoverConditionCoverageHelper_C9;

      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isPrivate(oldName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[15]++;
        // private names can be safely renamed. Rename!
        return true;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[16]++;}

      // NOTE(user): We should probably have more aggressive options, like
      // renaming all obj lit properties that are not quoted.
      return false;
    }
  }

  /**
   * Sorts Property objects by their count, breaking ties alphabetically to
   * ensure a deterministic total ordering.
   */
  private static final Comparator<Property> FREQUENCY_COMPARATOR =
    new Comparator<Property>() {
      @Override
      public int compare(Property a1, Property a2) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[16]++;
        int n1 = a1.count();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[17]++;
        int n2 = a2.count();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[18]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n1 != n2) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[17]++;
          return n2 - n1;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[18]++;}
        return a1.oldName.compareTo(a2.oldName);
      }
    };
  static {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[19]++;
  }


  // Set of String nodes to rename
  private final Set<Node> stringNodes = new HashSet<Node>();
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[20]++;
  }

  // Mapping of property names to Property objects
  private final Map<String, Property> properties =
      new HashMap<String, Property>();
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[21]++;
  }

  // Set of names not to rename. Externed properties/methods are added later.
  private final Set<String> reservedNames =
      new HashSet<String>(Arrays.asList(
          "indexOf", "lastIndexOf", "toString", "valueOf"));
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[22]++;
  }

  // Set of OBJLIT nodes that are assigned to prototypes
  private final Set<Node> prototypeObjLits = new HashSet<Node>();
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[23]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The JSCompiler
   * @param aggressiveRenaming Whether to rename aggressively
   * @param reservedCharacters If specified these characters won't be used in
   *   generated names
   * @param prevUsedRenameMap The rename map used in the previous compilation
   */
  RenamePrototypes(AbstractCompiler compiler, boolean aggressiveRenaming,
                   @Nullable char[] reservedCharacters,
                   @Nullable VariableMap prevUsedRenameMap) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[24]++;
    this.aggressiveRenaming = aggressiveRenaming;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[25]++;
    this.reservedCharacters = reservedCharacters;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[26]++;
    this.prevUsedRenameMap = prevUsedRenameMap;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[27]++;
  }

  /**
   * Does property/method renaming.
   *
   * @param externs The root of the externs parse tree
   * @param root The root of the main code parse tree
   */
  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[28]++;

    NodeTraversal.traverse(compiler, externs,
                           new ProcessExternedProperties());
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[29]++;
    NodeTraversal.traverse(compiler, root, new ProcessProperties());
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[30]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[31]++;

    // Gather the properties to rename, sorted by count.
    SortedSet<Property> propsByFrequency =
        new TreeSet<Property>(FREQUENCY_COMPARATOR);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[32]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;

    for (Iterator<Map.Entry<String, Property>> it =
           properties.entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); ) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[4]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[5]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[6]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[33]++;
      Property a = it.next().getValue();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[34]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((a.canRename()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((reservedNames.contains(a.oldName)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[19]++;
        propsByFrequency.add(a);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[35]++;

      } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[20]++;
        it.remove();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[36]++;

        // If we're not renaming this, make sure we don't name something
        // else to this name.
        reservedNames.add(a.oldName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[37]++;
      }
    }
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[38]++;
int CodeCoverConditionCoverageHelper_C13;

    // Try and reuse as many names from the previous compilation as possible.
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((prevUsedRenameMap != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[21]++;
      reusePrototypeNames(propsByFrequency);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[39]++;

    } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[22]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[40]++;

    // Generate new names.
    NameGenerator nameGen = new NameGenerator(reservedNames, "",
                                              reservedCharacters);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[41]++;
    StringBuilder debug = new StringBuilder();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[42]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[7]++;


    for (Property a : propsByFrequency) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[7]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[8]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[9]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[43]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((a.newName == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[23]++;
        a.newName = nameGen.generateNextName();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[44]++;
        reservedNames.add(a.newName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[45]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[24]++;}

      debug.append(a.oldName).append(" => ").append(a.newName).append('\n');
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[46]++;
    }

    compiler.addToDebugLog("JS property assignments:\n" + debug);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[47]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[48]++;

    // Update the string nodes.
    boolean changed = false;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[49]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[10]++;


    for (Node n : stringNodes) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[10]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[11]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[12]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[50]++;
      String oldName = n.getString();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[51]++;
      Property a = properties.get(oldName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((a != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((a.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[25]++;
        n.setString(a.newName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[53]++;
        changed = changed || !a.newName.equals(oldName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[54]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[26]++;}
    }
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[55]++;
int CodeCoverConditionCoverageHelper_C16;

    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[27]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[56]++;

    } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[28]++;}

    compiler.setLifeCycleStage(LifeCycleStage.NORMALIZED_OBFUSCATED);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[57]++;
  }

  /**
   * Runs through the list of properties and tries to rename as many as possible
   * with names that were used for them in the previous compilation.
   * {@code reservedNames} is updated with the set of reused names.
   * @param properties The set of properties to attempt to rename.
   */
  private void reusePrototypeNames(Set<Property> properties) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[58]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[13]++;


    for (Property prop : properties) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[13]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[14]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[15]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[59]++;
      String prevName = prevUsedRenameMap.lookupNewName(prop.oldName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[60]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((prevName != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[29]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[61]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((reservedNames.contains(prevName)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[31]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[62]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[32]++;}

        prop.newName = prevName;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[63]++;
        reservedNames.add(prevName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[64]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[30]++;}
    }
  }

  /**
   * Iterate through the nodes, collect all of the STRING nodes that are
   * children of GETPROP or GETELEM and mark them as externs.
   */
  private class ProcessExternedProperties extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[65]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[33]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[34]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[66]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[67]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[35]++;
            reservedNames.add(dest.getString());
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[68]++;

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[36]++;} default : CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[37]++;
      }
    }
  }

  /**
   * Iterate through the nodes, collect all of the STRING nodes that are
   * children of GETPROP, GETELEM, or OBJLIT, and also count the number of
   * times each STRING is referenced.
   *
   * Also collects OBJLIT assignments of prototypes as candidates for renaming.
   */
  private class ProcessProperties extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[69]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[38]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[39]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[70]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[71]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[40]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[72]++;
            String s = dest.getString();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[73]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((s.equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[42]++;
              processPrototypeParent(parent, t.getInput());
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[74]++;

            } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[43]++;
              markPropertyAccessCandidate(dest, t.getInput());
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[75]++;
            }

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[41]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[76]++;
          break;
        case Token.OBJECTLIT:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[44]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[77]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((prototypeObjLits.contains(n)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[45]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[78]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[16]++;


int CodeCoverConditionCoverageHelper_C23;
            // Object literals have their property name/value pairs as a flat
            // list as their children. We want every other node in order to get
            // only the property names.
            for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false);
                 child = child.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[16]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[17]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[18]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[79]++;
int CodeCoverConditionCoverageHelper_C24;

              if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(child.getString())) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[47]++;
                markObjLitPropertyCandidate(child, t.getInput());
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[80]++;

              } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[48]++;}
            }

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[46]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[81]++;
          break; default : CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[49]++;
      }
    }

    /**
     * Processes the parent of a GETPROP prototype, which can either be
     * another GETPROP (in the case of Foo.prototype.bar), or can be
     * an assignment (in the case of Foo.prototype = ...).
     */
    private void processPrototypeParent(Node n, CompilerInput input) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[82]++;
      switch (n.getType()) {
        // Foo.prototype.getBar = function() { ... }
        case Token.GETPROP:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[50]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[51]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[83]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[84]++;
int CodeCoverConditionCoverageHelper_C25;
          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[52]++;
            markPrototypePropertyCandidate(dest, input);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[85]++;

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[53]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[86]++;
          break;

        // Foo.prototype = { "getBar" : function() { ... } }
        case Token.ASSIGN:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[54]++;
        case Token.CALL:
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[55]++;
          Node map;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[87]++;
int CodeCoverConditionCoverageHelper_C26;
          if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[56]++;
            map = n.getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[88]++;

          } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[57]++;
            map = n.getLastChild();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[89]++;
          }
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[90]++;
int CodeCoverConditionCoverageHelper_C27;
          if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((map.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[58]++;
            // Remember this node so that we can avoid processing it again when
            // the traversal reaches it.
            prototypeObjLits.add(map);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[91]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[92]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[19]++;


int CodeCoverConditionCoverageHelper_C28;

            for (Node key = map.getFirstChild();(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); key = key.getNext()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[19]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[20]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[21]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[93]++;
int CodeCoverConditionCoverageHelper_C29;
              if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(key.getString())) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[60]++;
               // May be STRING, GET, or SET
                markPrototypePropertyCandidate(key, input);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[94]++;

              } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[61]++;}
            }

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[59]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[95]++;
          break; default : CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[62]++;
      }
    }

    /**
     * Remembers the given String node and increments the property name's
     * access count.
     *
     * @param n A STRING node
     * @param input The Input that the node came from
     */
    private void markPrototypePropertyCandidate(Node n, CompilerInput input) {
      stringNodes.add(n);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[96]++;
      getProperty(n.getString()).prototypeCount++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[97]++;
    }

    /**
     * Remembers the given String node and increments the property name's
     * access count.
     *
     * @param n A STRING node
     * @param input The Input that the node came from
     */
    private void markObjLitPropertyCandidate(Node n, CompilerInput input) {
      stringNodes.add(n);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[98]++;
      getProperty(n.getString()).objLitCount++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[99]++;
    }

    /**
     * Remembers the given String node and increments the property name's
     * access count.
     *
     * @param n A STRING node
     * @param input The Input that the node came from
     */
    private void markPropertyAccessCandidate(Node n, CompilerInput input) {
      stringNodes.add(n);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[100]++;
      getProperty(n.getString()).refCount++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[101]++;
    }

    /**
     * Gets the current property for the given name, creating a new one if
     * none exists.
     */
    private Property getProperty(String name) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[102]++;
      Property prop = properties.get(name);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[103]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((prop == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[63]++;
        prop = new Property(name);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[104]++;
        properties.put(name, prop);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[105]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[64]++;}
      return prop;
    }
  }

  /**
   * Gets the property renaming map.
   *
   * @return A mapping from original names to new names
   */
  VariableMap getPropertyMap() {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[106]++;
    ImmutableMap.Builder<String, String> map = ImmutableMap.builder();
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[107]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[22]++;


    for (Property p : properties.values()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[22]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[23]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.loops[24]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[108]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((p.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[65]++;
        map.put(p.oldName, p.newName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.statements[109]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1.branches[66]++;}
    }
    return new VariableMap(map.build());
  }
}

class CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1 ());
  }
    public static long[] statements = new long[110];
    public static long[] branches = new long[67];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RenamePrototypes.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$1wfy4ojobmluvlvzmfyulzdpf520gl3s1 () {
    super("com.google.javascript.jscomp.RenamePrototypes.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 109; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 66; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RenamePrototypes.java");
      for (int i = 1; i <= 109; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 66; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

