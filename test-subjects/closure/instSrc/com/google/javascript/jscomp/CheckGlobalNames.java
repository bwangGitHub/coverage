/*
 * Copyright 2008 The Closure Compiler Authors.
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
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.GlobalNamespace.Name;
import com.google.javascript.jscomp.GlobalNamespace.Ref;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;

import java.util.Set;

/**
 * Checks references to undefined properties of global variables.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class CheckGlobalNames implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.ping();
  }


  private final AbstractCompiler compiler;
  private final CodingConvention convention;
  private final CheckLevel level;

  private GlobalNamespace namespace = null;
  {
    CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[1]++;
  }
  private final Set<String> objectPrototypeProps = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[2]++;
  }
  private final Set<String> functionPrototypeProps = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[3]++;
  }

  // Warnings
  static final DiagnosticType UNDEFINED_NAME_WARNING = DiagnosticType.warning(
      "JSC_UNDEFINED_NAME",
      "{0} is never defined");
  static {
    CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[4]++;
  }

  static final DiagnosticType NAME_DEFINED_LATE_WARNING =
      DiagnosticType.warning(
          "JSC_NAME_DEFINED_LATE",
          "{0} defined before its owner. {1} is defined at {2}:{3}");
  static {
    CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[5]++;
  }

  static final DiagnosticType STRICT_MODULE_DEP_QNAME =
      DiagnosticType.disabled(
          "JSC_STRICT_MODULE_DEP_QNAME",
          "module {0} cannot reference {2}, defined in " +
          "module {1}");
  static {
    CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[6]++;
  }

  /**
   * Creates a pass to check global name references at the given warning level.
   */
  CheckGlobalNames(AbstractCompiler compiler, CheckLevel level) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[7]++;
    this.convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[8]++;
    this.level = level;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[9]++;
  }

  /**
   * Injects a pre-computed global namespace, so that the same namespace
   * can be re-used for multiple check passes. Returns this for easy chaining.
   */
  CheckGlobalNames injectNamespace(GlobalNamespace namespace) {
    Preconditions.checkArgument(namespace.hasExternsRoot());
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[10]++;
    this.namespace = namespace;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[11]++;
    return this;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[1]++;
      namespace = new GlobalNamespace(compiler, externs, root);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[13]++;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[2]++;}

    // Find prototype properties that will affect our analysis.
    Preconditions.checkState(namespace.hasExternsRoot());
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[14]++;
    findPrototypeProps("Object", objectPrototypeProps);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[15]++;
    findPrototypeProps("Function", functionPrototypeProps);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[16]++;
    objectPrototypeProps.addAll(
        convention.getIndirectlyDeclaredProperties());
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[17]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[1]++;



    for (Name name : namespace.getNameForest()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[1]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[2]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[3]++;
}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
      // Skip extern names. Externs are often not runnable as real code,
      // and will do things like:
      // var x;
      // x.method;
      // which this check forbids.
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((name.inExterns) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[3]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[20]++;
        continue;

      } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[4]++;}

      checkDescendantNames(name, name.globalSets + name.localSets > 0);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[21]++;
    }
  }

  private void findPrototypeProps(String type, Set<String> props) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[22]++;
    Name slot = namespace.getSlot(type);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[5]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[24]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[4]++;


      for (Ref ref : slot.getRefs()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[4]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[5]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[6]++;
}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ref.type == Ref.Type.PROTOTYPE_GET) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[7]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[26]++;
          Node fullName = ref.getNode().getParent().getParent();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((fullName.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[9]++;
            props.add(fullName.getLastChild().getString());
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[28]++;

          } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[8]++;}
      }

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[6]++;}
  }

  /**
   * Checks to make sure all the descendants of a name are defined if they
   * are referenced.
   *
   * @param name A global name.
   * @param nameIsDefined If true, {@code name} is defined. Otherwise, it's
   *    undefined, and any references to descendant names should emit warnings.
   */
  private void checkDescendantNames(Name name, boolean nameIsDefined) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((name.props != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[11]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[30]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[7]++;


      for (Name prop : name.props) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[7]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[8]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[9]++;
}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[31]++;
        // if the ancestor of a property is not defined, then we should emit
        // warnings for all references to the property.
        boolean propIsDefined = false;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nameIsDefined) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[13]++;
          // if the ancestor of a property is defined, then let's check that
          // the property is also explicitly defined if it needs to be.
          propIsDefined = (!propertyMustBeInitializedByFullName(prop) ||
              prop.globalSets + prop.localSets > 0);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[33]++;

        } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[14]++;}

        validateName(prop, propIsDefined);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[34]++;
        checkDescendantNames(prop, propIsDefined);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[35]++;
      }

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[12]++;}
  }

  private void validateName(Name name, boolean isDefined) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[36]++;
    // If the name is not defined, emit warnings for each reference. While
    // we're looking through each reference, check all the module dependencies.
    Ref declaration = name.getDeclaration();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[37]++;
    Name parent = name.parent;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[38]++;

    JSModuleGraph moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[39]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[10]++;


    for (Ref ref : name.getRefs()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[10]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[11]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[12]++;
}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[40]++;
      // Don't worry about global exprs.
      boolean isGlobalExpr = ref.getNode().getParent().isExprResult();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((isDefined) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isTypedef(ref)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[15]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isGlobalExpr) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[17]++;
          reportRefToUndefinedName(name, ref);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[43]++;

        } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[18]++;}

      } else {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[16]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[44]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((declaration != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((ref.getModule() != declaration.getModule()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(
              ref.getModule(), declaration.getModule())) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[19]++;
        reportBadModuleReference(name, ref);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[45]++;

      } else {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[20]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
        // Check for late references.
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ref.scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[21]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[47]++;
          // Prototype references are special, because in our reference graph,
          // A.prototype counts as a reference to A.
          boolean isPrototypeGet = (ref.type == Ref.Type.PROTOTYPE_GET);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[48]++;
          Name owner = isPrototypeGet ? name : parent;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[49]++;
          boolean singleGlobalParentDecl =
              owner != null &&
              owner.getDeclaration() != null &&
              owner.localSets == 0;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;

          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((singleGlobalParentDecl) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((owner.getDeclaration().preOrderIndex > ref.preOrderIndex) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[23]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[51]++;
            String refName = isPrototypeGet
                ? name.getFullName() + ".prototype"
                : name.getFullName();
            compiler.report(
                JSError.make(ref.source.getName(), ref.node,
                    NAME_DEFINED_LATE_WARNING,
                    refName,
                    owner.getFullName(),
                    owner.getDeclaration().source.getName(),
                    String.valueOf(owner.getDeclaration().node.getLineno())));
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[52]++;

          } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[22]++;}
      }
}
    }
  }

  private boolean isTypedef(Ref ref) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[53]++;
    // If this is an annotated EXPR-GET, don't do anything.
    Node parent = ref.node.getParent();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[25]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[55]++;
      JSDocInfo info = ref.node.getJSDocInfo();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((info.hasTypedefType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[27]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[28]++;}

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[26]++;}
    return false;
  }

  private void reportBadModuleReference(Name name, Ref ref) {
    compiler.report(
        JSError.make(ref.source.getName(), ref.node, STRICT_MODULE_DEP_QNAME,
                     ref.getModule().getName(),
                     name.getDeclaration().getModule().getName(),
                     name.getFullName()));
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[57]++;
  }

  private void reportRefToUndefinedName(Name name, Ref ref) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[58]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
    // grab the highest undefined ancestor to output in the warning message.
    while ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((name.parent != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((name.parent.globalSets + name.parent.localSets == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[13]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[14]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[15]++;
}
      name = name.parent;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[59]++;
    }

    compiler.report(
        JSError.make(ref.getSourceName(), ref.node, level,
            UNDEFINED_NAME_WARNING, name.getFullName()));
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[60]++;
  }

  /**
   * Checks whether the given name is a property, and whether that property
   * must be initialized with its full qualified name.
   */
  private boolean propertyMustBeInitializedByFullName(Name name) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
    // If an object or function literal in the global namespace is never
    // aliased, then its properties can only come from one of 2 places:
    // 1) From its prototype chain, or
    // 2) From an assignment to its fully qualified name.
    // If we assume #1 is not the case, then #2 implies that its
    // properties must all be modeled in the GlobalNamespace as well.
    //
    // We assume that for global object literals and types (constructors and
    // interfaces), we can find all the properties inherited from the prototype
    // chain of functions and objects.
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((name.parent == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[29]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[30]++;}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[62]++;

    boolean parentIsAliased = false;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((name.parent.aliasingGets > 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[31]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[64]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[16]++;


      for (Ref ref : name.parent.getRefs()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[16]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[17]--;
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.loops[18]++;
}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[65]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ref.type == Ref.Type.ALIASING_GET) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[33]++;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[66]++;
          Node aliaser = ref.getNode().getParent();
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[67]++;

          // We don't need to worry about known aliased, because
          // they're already covered by the getIndirectlyDeclaredProperties
          // call at the top.
          boolean isKnownAlias =
              aliaser.isCall() &&
              (convention.getClassesDefinedByCall(aliaser) != null ||
               convention.getSingletonGetterClassName(aliaser) != null);
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isKnownAlias) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[35]++;
            parentIsAliased = true;
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[69]++;

          } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[34]++;}
      }

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[32]++;}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((parentIsAliased) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[37]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[38]++;}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[71]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((objectPrototypeProps.contains(name.getBaseName())) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[39]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[40]++;}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[72]++;
int CodeCoverConditionCoverageHelper_C22;

    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((name.parent.type == Name.Type.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[41]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[42]++;}
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.statements[73]++;
int CodeCoverConditionCoverageHelper_C23;

    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((name.parent.type == Name.Type.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((name.parent.isDeclaredType()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((functionPrototypeProps.contains(name.getBaseName())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) || true)) || (CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) && false)) {
CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[43]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip.branches[44]++;}

    return false;
  }
}

class CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip ());
  }
    public static long[] statements = new long[74];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckGlobalNames.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,3,1,2,1,2,2,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 23; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$1jzqmgk1xmdqldeop10uk1mazfsuy64ip () {
    super("com.google.javascript.jscomp.CheckGlobalNames.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 73; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckGlobalNames.java");
      for (int i = 1; i <= 73; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

