/*
 * Copyright 2012 The Closure Compiler Authors.
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
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.TypeValidator.TypeMismatch;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;

import java.util.Map;
import java.util.Set;


/**
 * InlineProperties attempts to find references to properties that are known to
 * be constants and inline the known value.
 *
 * This pass relies on type information to find these property references and
 * properties are assumed to be constant if either:
 *   - the property is assigned unconditionally in the instance constructor
 *   - the property is assigned unconditionally to the type's prototype
 *
 * The current implementation only inlines immutable values (as defined by
 * NodeUtil.isImmutableValue).
 *
 * @author johnlenz@google.com (John Lenz)
 */
public class InlineProperties implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.ping();
  }


  private final AbstractCompiler compiler;

  static class PropertyInfo {
    PropertyInfo(JSType type, Node value) {
      this.type = type;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[1]++;
      this.value = value;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[2]++;
    }
    final JSType type;
    final Node value;
  }

  private static final PropertyInfo INVALIDATED = new PropertyInfo(
      null, null);
  static {
    CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[3]++;
  }

  private final Map<String, PropertyInfo> props = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[4]++;
  }

  private Set<JSType> invalidatingTypes;

  InlineProperties(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[5]++;
    buildInvalidatingTypeSet();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[6]++;
  }

  // TODO(johnlenz): this is a direct copy of the invalidation code
  // from AmbiguateProperties, if in the end we don't need to modify it
  // we should move it to a common location.
  private void buildInvalidatingTypeSet() {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[7]++;
    JSTypeRegistry registry = compiler.getTypeRegistry();
    invalidatingTypes = Sets.newHashSet(
        registry.getNativeType(JSTypeNative.ALL_TYPE),
        registry.getNativeType(JSTypeNative.NO_OBJECT_TYPE),
        registry.getNativeType(JSTypeNative.NO_TYPE),
        registry.getNativeType(JSTypeNative.NULL_TYPE),
        registry.getNativeType(JSTypeNative.VOID_TYPE),
        registry.getNativeType(JSTypeNative.FUNCTION_FUNCTION_TYPE),
        registry.getNativeType(JSTypeNative.FUNCTION_INSTANCE_TYPE),
        registry.getNativeType(JSTypeNative.FUNCTION_PROTOTYPE),
        registry.getNativeType(JSTypeNative.GLOBAL_THIS),
        registry.getNativeType(JSTypeNative.OBJECT_TYPE),
        registry.getNativeType(JSTypeNative.OBJECT_PROTOTYPE),
        registry.getNativeType(JSTypeNative.OBJECT_FUNCTION_TYPE),
        registry.getNativeType(JSTypeNative.TOP_LEVEL_PROTOTYPE),
        registry.getNativeType(JSTypeNative.UNKNOWN_TYPE));
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[8]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[1]++;



    for (TypeMismatch mis : compiler.getTypeValidator().getMismatches()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[1]--;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[2]--;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[3]++;
}
      addInvalidatingType(mis.typeA);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[10]++;
      addInvalidatingType(mis.typeB);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[11]++;
    }
  }

  /**
   * Invalidates the given type, so that no properties on it will be renamed.
   */
  private void addInvalidatingType(JSType type) {
    type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[12]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[1]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[4]++;


      for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[4]--;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[5]--;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[6]++;
}
        addInvalidatingType(alt);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[15]++;
      }

    } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[2]++;}

    invalidatingTypes.add(type);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[16]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[17]++;
    ObjectType objType = ObjectType.cast(type);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((objType.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[3]++;
      invalidatingTypes.add(objType.getImplicitPrototype());
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[19]++;

    } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[4]++;}
  }

  /** Returns true if properties on this type should not be renamed. */
  private boolean isInvalidatingType(JSType type) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[5]++;
      type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[21]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[7]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[23]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[7]++;


        for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[7]--;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[8]--;
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.loops[9]++;
}
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isInvalidatingType(alt)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[9]++;
            return true;

          } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[10]++;}
        }
        return false;

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[6]++;}
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[25]++;
    ObjectType objType = ObjectType.cast(type);
    return objType == null
        || invalidatingTypes.contains(objType)
        || !objType.hasReferenceName()
        || objType.isUnknownType()
        || objType.isEmptyType() /* unresolved types */
        || objType.isEnumType()
        || objType.autoboxesTo() != null;
  }

  /**
   * This method gets the JSType from the Node argument and verifies that it is
   * present.
   */
  private JSType getJSType(Node n) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[26]++;
    JSType jsType = n.getJSType();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[11]++;
      return compiler.getTypeRegistry().getNativeType(
          JSTypeNative.UNKNOWN_TYPE);

    } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[12]++;
      return jsType;
    }
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverseRoots(
        compiler, new GatherCandidates(), externs, root);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[28]++;
    NodeTraversal.traverseRoots(
        compiler, new ReplaceCandidates(), externs, root);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[29]++;
  }

  class GatherCandidates extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[30]++;
      boolean invalidatingPropRef = false;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[31]++;
      String propName = null;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[13]++;
        propName = n.getLastChild().getString();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[33]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((t.getInput().isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[15]++;
          // Any extern reference invalidates
          invalidatingPropRef = true;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[35]++;

        } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[16]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[36]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[17]++;
          invalidatingPropRef = !maybeCandidateDefinition(t, n, parent);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[37]++;

        } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[18]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[38]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((NodeUtil.isLValue(n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[19]++;
          // Other LValue references invalidate
          invalidatingPropRef = true;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[39]++;

        } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[20]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[40]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((parent.isDelProp()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[21]++;
          // Deletes invalidate
          invalidatingPropRef = true;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[41]++;

        } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[22]++;
          // A property read doesn't invalidate
          invalidatingPropRef = false;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[42]++;
        }
}
}
}

      } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[14]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[43]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[23]++;
        propName = n.getString();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[44]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[45]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((t.getInput().isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[25]++;
          // Any extern reference invalidates
          invalidatingPropRef = true;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[46]++;

        } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[26]++;
          // For now, any object literal key invalidates
          // TODO(johnlenz): support prototype properties like:
          //   foo.prototype = { a: 1, b: 2 };
          invalidatingPropRef = true;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[47]++;
        }

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[24]++;}
}
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[48]++;
int CodeCoverConditionCoverageHelper_C14;

      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((invalidatingPropRef) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[27]++;
        Preconditions.checkNotNull(propName);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[49]++;
        invalidateProperty(propName);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[50]++;

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[28]++;}
    }

    /**
     * @return Whether this is a valid definition for a candidate property.
     */
    private boolean maybeCandidateDefinition(
        NodeTraversal t, Node n, Node parent) {
      Preconditions.checkState(n.isGetProp() && parent.isAssign());
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[51]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[52]++;
      boolean isCandidate = false;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[53]++;
      Node src = n.getFirstChild();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[54]++;
      String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[55]++;

      Node value = parent.getLastChild();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[56]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((src.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[29]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[57]++;
int CodeCoverConditionCoverageHelper_C16;
        // This is a simple assignment like:
        //    this.foo = 1;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((inContructor(t)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[31]++;
          // This maybe a valid assignment.
          isCandidate = maybeStoreCandidateValue(
              getJSType(src), propName, value);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[58]++;

        } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[32]++;}

      } else {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[30]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[59]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((src.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((src.getLastChild().getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[33]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[60]++;
        // This is a prototype assignment like:
        //    x.prototype.foo = 1;
        JSType instanceType = maybeGetInstanceTypeFromPrototypeRef(src);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[61]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((instanceType != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[35]++;
          isCandidate = maybeStoreCandidateValue(
              instanceType, propName, value);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[62]++;

        } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[36]++;}

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[34]++;}
}
      return isCandidate;
    }

    private JSType maybeGetInstanceTypeFromPrototypeRef(Node src) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[63]++;
      JSType ownerType = getJSType(src.getFirstChild());
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[64]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((ownerType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ownerType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[37]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[65]++;
        FunctionType functionType = ((FunctionType) ownerType);
        return functionType.getInstanceType();

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[38]++;}
      return null;
    }

    private void invalidateProperty(String propName) {
      props.put(propName, INVALIDATED);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[66]++;
    }

    private boolean maybeStoreCandidateValue(
        JSType type, String propName, Node value) {
      Preconditions.checkNotNull(value);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[67]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[68]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (128)) == 0 || true) &&
 ((props.containsKey(propName)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((isInvalidatingType(type)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((NodeUtil.isExecutedExactlyOnce(value)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[39]++;
        props.put(propName, new PropertyInfo(type, value));
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[69]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[40]++;}
      return false;
    }

    private boolean inContructor(NodeTraversal t) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[70]++;
      Node root = t.getScopeRoot();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[71]++;
      JSDocInfo info = NodeUtil.getBestJSDocInfo(root);
      return info != null && info.isConstructor();
    }
  }

  class ReplaceCandidates extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.isLValue(n)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[41]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[73]++;
        Node target = n.getFirstChild();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[74]++;
        String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[75]++;
        PropertyInfo info = props.get(propName);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[76]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((info != INVALIDATED) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isMatchingType(target, info.type)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[43]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[77]++;
          Node replacement = info.value.cloneTree();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[78]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(n.getFirstChild(), compiler)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[45]++;
            replacement = IR.comma(n.removeFirstChild(), replacement).srcref(n);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[79]++;

          } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[46]++;}
          parent.replaceChild(n, replacement);
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[80]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[81]++;

        } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[44]++;}

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[42]++;}
    }

    private boolean isMatchingType(Node n, JSType src) {
      src = src.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[82]++;
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[83]++;
      JSType dest = getJSType(n).restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.statements[84]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((isInvalidatingType(dest)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((dest.isSubtype(src)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[47]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp.branches[48]++;}
      return false;
    }
  }
}

class CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp ());
  }
    public static long[] statements = new long[85];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InlineProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,2,3,2,3,1,2};
    for (int i = 1; i <= 24; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$1ozv5rh7kh8dg14qd2myxxipqqri2wzmp () {
    super("com.google.javascript.jscomp.InlineProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 84; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.InlineProperties.java");
      for (int i = 1; i <= 84; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

