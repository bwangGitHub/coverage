/*
 * Copyright 2006 The Closure Compiler Authors.
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
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.GlobalNamespace.Name;
import com.google.javascript.jscomp.GlobalNamespace.Ref;
import com.google.javascript.jscomp.GlobalNamespace.Ref.Type;
import com.google.javascript.jscomp.ReferenceCollectingCallback;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceCollection;
import com.google.javascript.jscomp.Scope;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;
import com.google.javascript.rhino.jstype.JSType;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Flattens global objects/namespaces by replacing each '.' with '$' in
 * their names. This reduces the number of property lookups the browser has
 * to do and allows the {@link RenameVars} pass to shorten namespaced names.
 * For example, goog.events.handleEvent() -> goog$events$handleEvent() -> Za().
 *
 * <p>If a global object's name is assigned to more than once, or if a property
 * is added to the global object in a complex expression, then none of its
 * properties will be collapsed (for safety/correctness).
 *
 * <p>If, after a global object is declared, it is never referenced except when
 * its properties are read or set, then the object will be removed after its
 * properties have been collapsed.
 *
 * <p>Uninitialized variable stubs are created at a global object's declaration
 * site for any of its properties that are added late in a local scope.
 *
 * <p>If, after an object is declared, it is referenced directly in a way that
 * might create an alias for it, then none of its properties will be collapsed.
 * This behavior is a safeguard to prevent the values associated with the
 * flattened names from getting out of sync with the object's actual property
 * values. For example, in the following case, an alias a$b, if created, could
 * easily keep the value 0 even after a.b became 5:
 * <code> a = {b: 0}; c = a; c.b = 5; </code>.
 *
 * <p>This pass doesn't flatten property accesses of the form: a[b].
 *
 * <p>For lots of examples, see the unit test.
 *
 */
class CollapseProperties implements CompilerPass {
  static {
    CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.ping();
  }


  // Warnings
  static final DiagnosticType UNSAFE_NAMESPACE_WARNING =
      DiagnosticType.warning(
          "JSC_UNSAFE_NAMESPACE",
          "incomplete alias created for namespace {0}");
  static {
    CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[1]++;
  }

  static final DiagnosticType NAMESPACE_REDEFINED_WARNING =
      DiagnosticType.warning(
          "JSC_NAMESPACE_REDEFINED",
          "namespace {0} should not be redefined");
  static {
    CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[2]++;
  }

  static final DiagnosticType UNSAFE_THIS = DiagnosticType.warning(
      "JSC_UNSAFE_THIS",
      "dangerous use of 'this' in static method {0}");
  static {
    CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[3]++;
  }

  private AbstractCompiler compiler;

  /** Global namespace tree */
  private List<Name> globalNames;

  /** Maps names (e.g. "a.b.c") to nodes in the global namespace tree */
  private Map<String, Name> nameMap;

  private final boolean collapsePropertiesOnExternTypes;
  private final boolean inlineAliases;

  /**
   * Creates an instance.
   *
   * @param compiler The JSCompiler, for reporting code changes
   * @param collapsePropertiesOnExternTypes if true, will rename user-defined
   *     static properties on externed typed. E.g. String.foo.
   * @param inlineAliases Whether we're allowed to inline local aliases of
   *     namespaces, etc.
   */
  CollapseProperties(AbstractCompiler compiler,
      boolean collapsePropertiesOnExternTypes, boolean inlineAliases) {
    this.compiler = compiler;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[4]++;
    this.collapsePropertiesOnExternTypes = collapsePropertiesOnExternTypes;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[5]++;
    this.inlineAliases = inlineAliases;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
    GlobalNamespace namespace;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((collapsePropertiesOnExternTypes) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[1]++;
      namespace = new GlobalNamespace(compiler, externs, root);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[8]++;

    } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[2]++;
      namespace = new GlobalNamespace(compiler, root);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[9]++;
    }
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((inlineAliases) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[3]++;
      inlineAliases(namespace);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[11]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[4]++;}
    nameMap = namespace.getNameIndex();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[12]++;
    globalNames = namespace.getNameForest();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[13]++;
    checkNamespaces();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[14]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[1]++;



    for (Name n : globalNames) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[1]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[2]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[3]++;
}
      flattenReferencesToCollapsibleDescendantNames(n, n.getBaseName());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[16]++;
    }
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[4]++;



    // We collapse property definitions after collapsing property references
    // because this step can alter the parse tree above property references,
    // invalidating the node ancestry stored with each reference.
    for (Name n : globalNames) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[4]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[5]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[6]++;
}
      collapseDeclarationOfNameAndDescendants(n, n.getBaseName());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[18]++;
    }
  }

  /**
   * For each qualified name N in the global scope, we check if:
   * (a) No ancestor of N is ever aliased or assigned an unknown value type.
   *     (If N = "a.b.c", "a" and "a.b" are never aliased).
   * (b) N has exactly one write, and it lives in the global scope.
   * (c) N is aliased in a local scope.
   *
   * If (a) is true, then GlobalNamespace must know all the writes to N.
   * If (a) and (b) are true, then N cannot change during the execution of
   *    a local scope.
   * If (a) and (b) and (c) are true, then the alias can be inlined if the
   *    alias obeys the usual rules for how we decide whether a variable is
   *    inlineable.
   * @see InlineVariables
   */
  private void inlineAliases(GlobalNamespace namespace) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[19]++;
    // Invariant: All the names in the worklist meet condition (a).
    Deque<Name> workList = new ArrayDeque<Name>(namespace.getNameForest());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[20]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[7]++;


int CodeCoverConditionCoverageHelper_C3;
    while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((workList.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[7]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[8]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[9]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[21]++;
      Name name = workList.pop();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;

      // Don't attempt to inline a getter or setter property as a variable.
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((name.type == Name.Type.GET) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name.type == Name.Type.SET) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[5]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[23]++;
        continue;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[6]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((name.globalSets == 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((name.localSets == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((name.aliasingGets > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[7]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[25]++;
        // {@code name} meets condition (b). Find all of its local aliases
        // and try to inline them.
        List<Ref> refs = Lists.newArrayList(name.getRefs());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[26]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[10]++;


        for (Ref ref : refs) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[10]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[11]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[12]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((ref.type == Type.ALIASING_GET) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ref.scope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[9]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
            // {@code name} meets condition (c). Try to inline it.
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((inlineAliasIfPossible(ref, namespace)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[11]++;
              name.removeRef(ref);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[29]++;

            } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[12]++;}

          } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[10]++;}
        }

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[8]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;

      // Check if {@code name} has any aliases left after the
      // local-alias-inlining above.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C8 |= (128)) == 0 || true) &&
 ((name.type == Name.Type.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((name.type == Name.Type.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((name.aliasingGets == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((name.props != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 4) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 4) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[13]++;
        // All of {@code name}'s children meet condition (a), so they can be
        // added to the worklist.
        workList.addAll(name.props);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[31]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[14]++;}
    }
  }

  private boolean inlineAliasIfPossible(Ref alias, GlobalNamespace namespace) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[32]++;
    // Ensure that the alias is assigned to a local variable at that
    // variable's declaration. If the alias's parent is a NAME,
    // then the NAME must be the child of a VAR node, and we must
    // be in a VAR assignment.
    Node aliasParent = alias.node.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((aliasParent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[15]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[34]++;
      // Ensure that the local variable is well defined and never reassigned.
      Scope scope = alias.scope;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[35]++;
      Var aliasVar = scope.getVar(aliasParent.getString());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[36]++;
      ReferenceCollectingCallback collector =
          new ReferenceCollectingCallback(compiler,
              ReferenceCollectingCallback.DO_NOTHING_BEHAVIOR,
              Predicates.<Var>equalTo(aliasVar));
      (new NodeTraversal(compiler, collector)).traverseAtScope(scope);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[37]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[38]++;

      ReferenceCollection aliasRefs = collector.getReferences(aliasVar);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((aliasRefs.isWellDefined()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((aliasRefs.firstReferenceIsAssigningDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((aliasRefs.isAssignedOnceInLifetime()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[17]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[40]++;
        // The alias is well-formed, so do the inlining now.
        int size = aliasRefs.references.size();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[41]++;
        Set<Node> newNodes = Sets.newHashSetWithExpectedSize(size - 1);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[42]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[13]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[13]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[14]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[15]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[43]++;
          ReferenceCollectingCallback.Reference aliasRef =
              aliasRefs.references.get(i);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[44]++;

          Node newNode = alias.node.cloneTree();
          aliasRef.getParent().replaceChild(aliasRef.getNode(), newNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[45]++;
          newNodes.add(newNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[46]++;
        }

        // just set the original alias to null.
        aliasParent.replaceChild(alias.node, IR.nullNode());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[47]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[48]++;

        // Inlining the variable may have introduced new references
        // to descendants of {@code name}. So those need to be collected now.
        namespace.scanNewNodes(alias.scope, newNodes);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[49]++;
        return true;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[18]++;}

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[16]++;}

    return false;
  }

  /**
   * Runs through all namespaces (prefixes of classes and enums), and checks if
   * any of them have been used in an unsafe way.
   */
  private void checkNamespaces() {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[50]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[16]++;


    for (Name name : nameMap.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[16]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[17]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[18]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[51]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (128)) == 0 || true) &&
 ((name.isNamespace()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((name.aliasingGets > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((name.localSets + name.globalSets > 1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((name.deleteProps > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 4) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 4) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[19]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[52]++;
        boolean initialized = name.getDeclaration() != null;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[53]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[19]++;


        for (Ref ref : name.getRefs()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[19]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[20]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[21]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((ref == name.getDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[21]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[55]++;
            continue;

          } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[22]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;

          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((ref.type == Ref.Type.DELETE_PROP) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[23]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((initialized) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[25]++;
              warnAboutNamespaceRedefinition(name, ref);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[58]++;

            } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[26]++;}

          } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[24]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[59]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((ref.type == Ref.Type.SET_FROM_GLOBAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ref.type == Ref.Type.SET_FROM_LOCAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[27]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[60]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((initialized) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[29]++;
              warnAboutNamespaceRedefinition(name, ref);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[61]++;

            } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[30]++;}

            initialized = true;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[62]++;

          } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[28]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[63]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ref.type == Ref.Type.ALIASING_GET) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[31]++;
            warnAboutNamespaceAliasing(name, ref);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[64]++;

          } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[32]++;}
}
}
        }

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[20]++;}
    }
  }

  /**
   * Reports a warning because a namespace was aliased.
   *
   * @param nameObj A namespace that is being aliased
   * @param ref The reference that forced the alias
   */
  private void warnAboutNamespaceAliasing(Name nameObj, Ref ref) {
    compiler.report(
        JSError.make(ref.getSourceName(), ref.node,
                     UNSAFE_NAMESPACE_WARNING, nameObj.getFullName()));
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[65]++;
  }

  /**
   * Reports a warning because a namespace was redefined.
   *
   * @param nameObj A namespace that is being redefined
   * @param ref The reference that set the namespace
   */
  private void warnAboutNamespaceRedefinition(Name nameObj, Ref ref) {
    compiler.report(
        JSError.make(ref.getSourceName(), ref.node,
                     NAMESPACE_REDEFINED_WARNING, nameObj.getFullName()));
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[66]++;
  }

  /**
   * Flattens all references to collapsible properties of a global name except
   * their initial definitions. Recurses on subnames.
   *
   * @param n An object representing a global name
   * @param alias The flattened name for {@code n}
   */
  private void flattenReferencesToCollapsibleDescendantNames(
      Name n, String alias) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[67]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n.props == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[33]++; return;
} else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[34]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[68]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[22]++;



    for (Name p : n.props) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[22]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[23]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[24]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[69]++;
      String propAlias = appendPropForAlias(alias, p.getBaseName());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((p.canCollapse()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[35]++;
        flattenReferencesTo(p, propAlias);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[71]++;

      } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[36]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[72]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((p.isSimpleStubDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[37]++;
        flattenSimpleStubDeclaration(p, propAlias);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[73]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[38]++;}
}

      flattenReferencesToCollapsibleDescendantNames(p, propAlias);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[74]++;
    }
  }


  /**
   * Flattens a stub declaration.
   * This is mostly a hack to support legacy users.
   */
  private void flattenSimpleStubDeclaration(Name name, String alias) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[75]++;
    Ref ref = Iterables.getOnlyElement(name.getRefs());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[76]++;
    Node nameNode = NodeUtil.newName(
        compiler.getCodingConvention(), alias, ref.node,
        name.getFullName());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[77]++;
    Node varNode = IR.var(nameNode).copyInformationFrom(nameNode);

    Preconditions.checkState(
        ref.node.getParent().isExprResult());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[78]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[79]++;
    Node parent = ref.node.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[80]++;
    Node gramps = parent.getParent();
    gramps.replaceChild(parent, varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[81]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[82]++;
  }


  /**
   * Flattens all references to a collapsible property of a global name except
   * its initial definition.
   *
   * @param n A global property name (e.g. "a.b" or "a.b.c.d")
   * @param alias The flattened name (e.g. "a$b" or "a$b$c$d")
   */
  private void flattenReferencesTo(Name n, String alias) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[83]++;
    String originalName = n.getFullName();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[84]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[25]++;


    for (Ref r : n.getRefs()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[25]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[26]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[27]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[85]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((r == n.getDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[39]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[86]++;
        // Declarations are handled separately.
        continue;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[40]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[87]++;

      Node rParent = r.node.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[88]++;
int CodeCoverConditionCoverageHelper_C23;

      // There are two cases when we shouldn't flatten a reference:
      // 1) Object literal keys, because duplicate keys show up as refs.
      // 2) References inside a complex assign. (a = x.y = 0). These are
      //    called TWIN references, because they show up twice in the
      //    reference list. Only collapse the set, not the alias.
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(r.node)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((r.getTwin() == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((r.isSet()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[41]++;
        flattenNameRef(alias, r.node, rParent, originalName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[89]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[42]++;}
    }
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[90]++;
int CodeCoverConditionCoverageHelper_C24;

    // Flatten all occurrences of a name as a prefix of its subnames. For
    // example, if {@code n} corresponds to the name "a.b", then "a.b" will be
    // replaced with "a$b" in all occurrences of "a.b.c", "a.b.c.d", etc.
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((n.props != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[43]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[91]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[28]++;


      for (Name p : n.props) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[28]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[29]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[30]++;
}
        flattenPrefixes(alias, p, 1);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[92]++;
      }

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[44]++;}
  }

  /**
   * Flattens all occurrences of a name as a prefix of subnames beginning
   * with a particular subname.
   *
   * @param n A global property name (e.g. "a.b.c.d")
   * @param alias A flattened prefix name (e.g. "a$b")
   * @param depth The difference in depth between the property name and
   *    the prefix name (e.g. 2)
   */
  private void flattenPrefixes(String alias, Name n, int depth) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[93]++;
    // Only flatten the prefix of a name declaration if the name being
    // initialized is fully qualified (i.e. not an object literal key).
    String originalName = n.getFullName();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[94]++;
    Ref decl = n.getDeclaration();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[95]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((decl != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((decl.node != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((decl.node.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[45]++;
      flattenNameRefAtDepth(alias, decl.node, depth, originalName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[96]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[46]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[97]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[31]++;



    for (Ref r : n.getRefs()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[31]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[32]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[33]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[98]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((r == decl) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[47]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[99]++;
        // Declarations are handled separately.
        continue;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[48]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[100]++;
int CodeCoverConditionCoverageHelper_C27;

      // References inside a complex assign (a = x.y = 0)
      // have twins. We should only flatten one of the twins.
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((r.getTwin() == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((r.isSet()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[49]++;
        flattenNameRefAtDepth(alias, r.node, depth, originalName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[101]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[50]++;}
    }
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[102]++;
int CodeCoverConditionCoverageHelper_C28;

    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.props != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[51]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[103]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[34]++;


      for (Name p : n.props) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[34]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[35]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[36]++;
}
        flattenPrefixes(alias, p, depth + 1);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[104]++;
      }

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[52]++;}
  }

  /**
   * Flattens a particular prefix of a single name reference.
   *
   * @param alias A flattened prefix name (e.g. "a$b")
   * @param n The node corresponding to a subproperty name (e.g. "a.b.c.d")
   * @param depth The difference in depth between the property name and
   *    the prefix name (e.g. 2)
   * @param originalName String version of the property name.
   */
  private void flattenNameRefAtDepth(String alias, Node n, int depth,
      String originalName) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[105]++;
    // This method has to work for both GETPROP chains and, in rare cases,
    // OBJLIT keys, possibly nested. That's why we check for children before
    // proceeding. In the OBJLIT case, we don't need to do anything.
    int nType = n.getType();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[106]++;
    boolean isQName = nType == Token.NAME || nType == Token.GETPROP;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[107]++;
    boolean isObjKey = NodeUtil.isObjectLitKey(n);
    Preconditions.checkState(isObjKey || isQName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[108]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[109]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isQName) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[53]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[110]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[37]++;


int CodeCoverConditionCoverageHelper_C30;
      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((i < depth) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[37]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[38]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[39]++;
}
        n = n.getFirstChild();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[111]++;
      }
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[112]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[55]++;
        flattenNameRef(alias, n.getFirstChild(), n, originalName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[113]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[56]++;}

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[54]++;}
  }

  /**
   * Replaces a GETPROP a.b.c with a NAME a$b$c.
   *
   * @param alias A flattened prefix name (e.g. "a$b")
   * @param n The GETPROP node corresponding to the original name (e.g. "a.b")
   * @param parent {@code n}'s parent
   * @param originalName String version of the property name.
   */
  private void flattenNameRef(String alias, Node n, Node parent,
      String originalName) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[114]++;
    // BEFORE:
    //   getprop
    //     getprop
    //       name a
    //       string b
    //     string c
    // AFTER:
    //   name a$b$c
    Node ref = NodeUtil.newName(
        compiler.getCodingConvention(), alias, n, originalName);
    NodeUtil.copyNameAnnotations(n.getLastChild(), ref);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[115]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[116]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((n == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[57]++;
      // The node was a call target, we are deliberately flatten these as
      // we node the "this" isn't provided by the namespace. Mark it as such:
      parent.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[117]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[58]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[118]++;

    JSType type = n.getJSType();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[119]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[59]++;
      ref.setJSType(type);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[120]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[60]++;}
    parent.replaceChild(n, ref);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[121]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[122]++;
  }

  /**
   * Collapses definitions of the collapsible properties of a global name.
   * Recurses on subnames that also represent JavaScript objects with
   * collapsible properties.
   *
   * @param n A node representing a global name
   * @param alias The flattened name for {@code n}
   */
  private void collapseDeclarationOfNameAndDescendants(Name n, String alias) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[123]++;
    boolean canCollapseChildNames = n.canCollapseUnannotatedChildNames();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[124]++;
int CodeCoverConditionCoverageHelper_C34;

    // Handle this name first so that nested object literals get unrolled.
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((n.canCollapse()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[61]++;
      updateObjLitOrFunctionDeclaration(n, alias, canCollapseChildNames);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[125]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[62]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[126]++;
int CodeCoverConditionCoverageHelper_C35;

    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((n.props != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[63]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[127]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[40]++;


      for (Name p : n.props) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[40]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[41]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[42]++;
}
        // Recurse first so that saved node ancestries are intact when needed.
        collapseDeclarationOfNameAndDescendants(
            p, appendPropForAlias(alias, p.getBaseName()));
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[128]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[129]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (8192)) == 0 || true) &&
 ((p.inExterns) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4096)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2048)) == 0 || true) &&
 ((canCollapseChildNames) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (512)) == 0 || true) &&
 ((p.getDeclaration() != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (128)) == 0 || true) &&
 ((p.canCollapse()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (32)) == 0 || true) &&
 ((p.getDeclaration().node != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((p.getDeclaration().node.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((p.getDeclaration().node.getParent().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 7) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 7) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[65]++;
          updateSimpleDeclaration(
              appendPropForAlias(alias, p.getBaseName()), p, p.getDeclaration());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[130]++;

        } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[66]++;}
      }

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[64]++;}
  }

  /**
   * Updates the initial assignment to a collapsible property at global scope
   * by changing it to a variable declaration (e.g. a.b = 1 -> var a$b = 1).
   * The property's value may either be a primitive or an object literal or
   * function whose properties aren't collapsible.
   *
   * @param alias The flattened property name (e.g. "a$b")
   * @param refName The name for the reference being updated.
   * @param ref An object containing information about the assignment getting
   *     updated
   */
  private void updateSimpleDeclaration(String alias, Name refName, Ref ref) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[131]++;
    Node rvalue = ref.node.getNext();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[132]++;
    Node parent = ref.node.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[133]++;
    Node gramps = parent.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[134]++;
    Node greatGramps = gramps.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[135]++;
int CodeCoverConditionCoverageHelper_C37;

    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((rvalue != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((rvalue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[67]++;
      checkForHosedThisReferences(rvalue, refName.docInfo, refName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[136]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[68]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[137]++;

    // Create the new alias node.
    Node nameNode = NodeUtil.newName(
        compiler.getCodingConvention(), alias, gramps.getFirstChild(),
        refName.getFullName());
    NodeUtil.copyNameAnnotations(ref.node.getLastChild(), nameNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[138]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[139]++;
int CodeCoverConditionCoverageHelper_C38;

    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((gramps.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[69]++;
      // BEFORE: a.b.c = ...;
      //   exprstmt
      //     assign
      //       getprop
      //         getprop
      //           name a
      //           string b
      //         string c
      //       NODE
      // AFTER: var a$b$c = ...;
      //   var
      //     name a$b$c
      //       NODE

      // Remove the r-value (NODE).
      parent.removeChild(rvalue);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[140]++;
      nameNode.addChildToFront(rvalue);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[141]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[142]++;

      Node varNode = IR.var(nameNode);
      greatGramps.replaceChild(gramps, varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[143]++;

    } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[70]++;
      // This must be a complex assignment.
      Preconditions.checkNotNull(ref.getTwin());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[144]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[145]++;

      // BEFORE:
      // ... (x.y = 3);
      //
      // AFTER:
      // var x$y;
      // ... (x$y = 3);

      Node current = gramps;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[146]++;
      Node currentParent = gramps.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[147]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[43]++;


int CodeCoverConditionCoverageHelper_C39;
      for (;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((currentParent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((currentParent.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false);
           current = currentParent,
           currentParent = currentParent.getParent()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[43]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[44]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[45]++;
}}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[148]++;

      // Create a stub variable declaration right
      // before the current statement.
      Node stubVar = IR.var(nameNode.cloneTree())
          .copyInformationFrom(nameNode);
      currentParent.addChildBefore(stubVar, current);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[149]++;

      parent.replaceChild(ref.node, nameNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[150]++;
    }

    compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[151]++;
  }

  /**
   * Updates the first initialization (a.k.a "declaration") of a global name.
   * This involves flattening the global name (if it's not just a global
   * variable name already), collapsing object literal keys into global
   * variables, declaring stub global variables for properties added later
   * in a local scope.
   *
   * It may seem odd that this function also takes care of declaring stubs
   * for direct children. The ultimate goal of this function is to eliminate
   * the global name entirely (when possible), so that "middlemen" namespaces
   * disappear, and to do that we need to make sure that all the direct children
   * will be collapsed as well.
   *
   * @param n An object representing a global name (e.g. "a", "a.b.c")
   * @param alias The flattened name for {@code n} (e.g. "a", "a$b$c")
   * @param canCollapseChildNames Whether it's possible to collapse children of
   *     this name. (This is mostly passed for convenience; it's equivalent to
   *     n.canCollapseChildNames()).
   */
  private void updateObjLitOrFunctionDeclaration(
      Name n, String alias, boolean canCollapseChildNames) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[152]++;
    Ref decl = n.getDeclaration();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[153]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((decl == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[71]++;
      // Some names do not have declarations, because they
      // are only defined in local scopes.
      return;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[72]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[154]++;
int CodeCoverConditionCoverageHelper_C41;

    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((decl.getTwin() != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[73]++;
      // Twin declarations will get handled when normal references
      // are handled.
      return;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[74]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[155]++;

    switch (decl.node.getParent().getType()) {
      case Token.ASSIGN:
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[75]++;
        updateObjLitOrFunctionDeclarationAtAssignNode(
            n, alias, canCollapseChildNames);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[156]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[157]++;
        break;
      case Token.VAR:
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[76]++;
        updateObjLitOrFunctionDeclarationAtVarNode(n, canCollapseChildNames);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[158]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[159]++;
        break;
      case Token.FUNCTION:
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[77]++;
        updateFunctionDeclarationAtFunctionNode(n, canCollapseChildNames);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[160]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[161]++;
        break; default : CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[78]++;
    }
  }

  /**
   * Updates the first initialization (a.k.a "declaration") of a global name
   * that occurs at an ASSIGN node. See comment for
   * {@link #updateObjLitOrFunctionDeclaration}.
   *
   * @param n An object representing a global name (e.g. "a", "a.b.c")
   * @param alias The flattened name for {@code n} (e.g. "a", "a$b$c")
   */
  private void updateObjLitOrFunctionDeclarationAtAssignNode(
      Name n, String alias, boolean canCollapseChildNames) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[162]++;
    // NOTE: It's important that we don't add additional nodes
    // (e.g. a var node before the exprstmt) because the exprstmt might be
    // the child of an if statement that's not inside a block).

    Ref ref = n.getDeclaration();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[163]++;
    Node rvalue = ref.node.getNext();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[164]++;
    Node varNode = new Node(Token.VAR);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[165]++;
    Node varParent = ref.node.getAncestor(3);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[166]++;
    Node gramps = ref.node.getAncestor(2);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[167]++;
    boolean isObjLit = rvalue.isObjectLit();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[168]++;
    boolean insertedVarNode = false;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[169]++;
int CodeCoverConditionCoverageHelper_C42;

    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((isObjLit) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((n.canEliminate()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[79]++;
      // Eliminate the object literal altogether.
      varParent.replaceChild(gramps, varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[170]++;
      ref.node = null;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[171]++;
      insertedVarNode = true;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[172]++;


    } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[80]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[173]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((n.isSimpleName()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[81]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[174]++;
int CodeCoverConditionCoverageHelper_C44;
      // Create a VAR node to declare the name.
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((rvalue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[83]++;
        checkForHosedThisReferences(rvalue, n.docInfo, n);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[175]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[84]++;}

      ref.node.getParent().removeChild(rvalue);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[176]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[177]++;

      Node nameNode = NodeUtil.newName(
          compiler.getCodingConvention(),
          alias, ref.node.getAncestor(2), n.getFullName());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[178]++;

      JSDocInfo info = ref.node.getParent().getJSDocInfo();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[179]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (32)) == 0 || true) &&
 ((ref.node.getLastChild().getBooleanProp(Node.IS_CONSTANT_NAME)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((info.isConstant()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 3) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 3) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[85]++;
        nameNode.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[180]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[86]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[181]++;
int CodeCoverConditionCoverageHelper_C46;

      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[87]++;
        varNode.setJSDocInfo(info);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[182]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[88]++;}
      varNode.addChildToBack(nameNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[183]++;
      nameNode.addChildToFront(rvalue);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[184]++;
      varParent.replaceChild(gramps, varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[185]++;

      // Update the node ancestry stored in the reference.
      ref.node = nameNode;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[186]++;
      insertedVarNode = true;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[187]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[82]++;}
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[188]++;
int CodeCoverConditionCoverageHelper_C47;

    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((canCollapseChildNames) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[89]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[189]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isObjLit) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[91]++;
        declareVarsForObjLitValues(
            n, alias, rvalue,
            varNode, varParent.getChildBefore(varNode), varParent);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[190]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[92]++;}

      addStubsForUndeclaredProperties(n, alias, varParent, varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[191]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[90]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[192]++;
int CodeCoverConditionCoverageHelper_C49;

    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((insertedVarNode) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[93]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[193]++;
int CodeCoverConditionCoverageHelper_C50;
      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((varNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[95]++;
        varParent.removeChild(varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[194]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[96]++;}
      compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[195]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[94]++;}
  }

  /**
   * Warns about any references to "this" in the given FUNCTION. The function
   * is getting collapsed, so the references will change.
   */
  private void checkForHosedThisReferences(Node function, JSDocInfo docInfo,
      final Name name) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[196]++;
int CodeCoverConditionCoverageHelper_C51;
    // A function is getting collapsed. Make sure that if it refers to
    // "this", it must be a constructor or documented with @this.
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (32)) == 0 || true) &&
 ((docInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (16)) == 0 || true)))
 || (!
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((docInfo.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((docInfo.hasThisType()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[97]++;
      NodeTraversal.traverse(compiler, function.getLastChild(),
          new NodeTraversal.AbstractShallowCallback() {
            @Override
            public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[198]++;
int CodeCoverConditionCoverageHelper_C52;
              if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((n.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[99]++;
                compiler.report(
                    JSError.make(name.getDeclaration().getSourceName(), n,
                        UNSAFE_THIS, name.getFullName()));
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[199]++;

              } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[100]++;}
            }
          });
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[197]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[98]++;}
  }

  /**
   * Updates the first initialization (a.k.a "declaration") of a global name
   * that occurs at a VAR node. See comment for
   * {@link #updateObjLitOrFunctionDeclaration}.
   *
   * @param n An object representing a global name (e.g. "a")
   */
  private void updateObjLitOrFunctionDeclarationAtVarNode(
      Name n, boolean canCollapseChildNames) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[200]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((canCollapseChildNames) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[101]++;
      return;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[102]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[201]++;

    Ref ref = n.getDeclaration();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[202]++;
    String name = ref.node.getString();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[203]++;
    Node rvalue = ref.node.getFirstChild();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[204]++;
    Node varNode = ref.node.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[205]++;
    Node gramps = varNode.getParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[206]++;

    boolean isObjLit = rvalue.isObjectLit();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[207]++;
    int numChanges = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[208]++;
int CodeCoverConditionCoverageHelper_C54;

    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((isObjLit) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[103]++;
      numChanges += declareVarsForObjLitValues(
          n, name, rvalue, varNode, gramps.getChildBefore(varNode),
          gramps);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[209]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[104]++;}

    numChanges += addStubsForUndeclaredProperties(n, name, gramps, varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[210]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[211]++;
int CodeCoverConditionCoverageHelper_C55;

    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((isObjLit) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((n.canEliminate()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[105]++;
      varNode.removeChild(ref.node);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[212]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[213]++;
int CodeCoverConditionCoverageHelper_C56;
      if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((varNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[107]++;
        gramps.removeChild(varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[214]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[108]++;}
      numChanges++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[215]++;

      // Clear out the object reference, since we've eliminated it from the
      // parse tree.
      ref.node = null;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[216]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[106]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[217]++;
int CodeCoverConditionCoverageHelper_C57;

    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((numChanges > 0) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[109]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[218]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[110]++;}
  }

  /**
   * Updates the first initialization (a.k.a "declaration") of a global name
   * that occurs at a FUNCTION node. See comment for
   * {@link #updateObjLitOrFunctionDeclaration}.
   *
   * @param n An object representing a global name (e.g. "a")
   */
  private void updateFunctionDeclarationAtFunctionNode(
      Name n, boolean canCollapseChildNames) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[219]++;
int CodeCoverConditionCoverageHelper_C58;
    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((canCollapseChildNames) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[111]++;
      return;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[112]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[220]++;

    Ref ref = n.getDeclaration();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[221]++;
    String fnName = ref.node.getString();
    addStubsForUndeclaredProperties(
        n, fnName, ref.node.getAncestor(2), ref.node.getParent());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[222]++;
  }

  /**
   * Declares global variables to serve as aliases for the values in an object
   * literal, optionally removing all of the object literal's keys and values.
   *
   * @param alias The object literal's flattened name (e.g. "a$b$c")
   * @param objlit The OBJLIT node
   * @param varNode The VAR node to which new global variables should be added
   *     as children
   * @param nameToAddAfter The child of {@code varNode} after which new
   *     variables should be added (may be null)
   * @param varParent {@code varNode}'s parent
   * @return The number of variables added
   */
  private int declareVarsForObjLitValues(
      Name objlitName, String alias, Node objlit, Node varNode,
      Node nameToAddAfter, Node varParent) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[223]++;
    int numVars = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[224]++;
    int arbitraryNameCounter = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[225]++;
    boolean discardKeys = !objlitName.shouldKeepKeys();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[226]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[46]++;


int CodeCoverConditionCoverageHelper_C59;

    for (Node key = objlit.getFirstChild(), nextKey;(((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false);
         key = nextKey) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[46]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[47]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[48]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[227]++;
      Node value = key.getFirstChild();
      nextKey = key.getNext();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[228]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[229]++;
int CodeCoverConditionCoverageHelper_C60;

      // A get or a set can not be rewritten as a VAR.
      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((key.isGetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((key.isSetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[113]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[230]++;
        continue;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[114]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[231]++;

      // We generate arbitrary names for keys that aren't valid JavaScript
      // identifiers, since those keys are never referenced. (If they were,
      // this object literal's child names wouldn't be collapsible.) The only
      // reason that we don't eliminate them entirely is the off chance that
      // their values are expressions that have side effects.
      boolean isJsIdentifier = !key.isNumber() &&
                               TokenStream.isJSIdentifier(key.getString());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[232]++;
      String propName = isJsIdentifier ?
          key.getString() : String.valueOf(++arbitraryNameCounter);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[233]++;

      // If the name cannot be collapsed, skip it.
      String qName = objlitName.getFullName() + '.' + propName;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[234]++;
      Name p = nameMap.get(qName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[235]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((p.canCollapse()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[115]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[236]++;
        continue;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[116]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[237]++;

      String propAlias = appendPropForAlias(alias, propName);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[238]++;
      Node refNode = null;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[239]++;
int CodeCoverConditionCoverageHelper_C62;
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((discardKeys) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[117]++;
        objlit.removeChild(key);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[240]++;
        value.detachFromParent();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[241]++;

      } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[118]++;
        // Substitute a reference for the value.
        refNode = IR.name(propAlias);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[242]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[243]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((key.getBooleanProp(Node.IS_CONSTANT_NAME)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[119]++;
          refNode.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[244]++;

        } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[120]++;}

        key.replaceChild(value, refNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[245]++;
      }
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[246]++;

      // Declare the collapsed name as a variable with the original value.
      Node nameNode = IR.name(propAlias);
      nameNode.addChildToFront(value);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[247]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[248]++;
int CodeCoverConditionCoverageHelper_C64;
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((key.getBooleanProp(Node.IS_CONSTANT_NAME)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[121]++;
        nameNode.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[249]++;

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[122]++;}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[250]++;
      Node newVar = IR.var(nameNode)
          .copyInformationFromForTree(key);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[251]++;
int CodeCoverConditionCoverageHelper_C65;
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((nameToAddAfter != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[123]++;
        varParent.addChildAfter(newVar, nameToAddAfter);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[252]++;

      } else {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[124]++;
        varParent.addChildBefore(newVar, varNode);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[253]++;
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[254]++;
      nameToAddAfter = newVar;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[255]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[256]++;
int CodeCoverConditionCoverageHelper_C66;

      // Update the global name's node ancestry if it hasn't already been
      // done. (Duplicate keys in an object literal can bring us here twice
      // for the same global name.)
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((isJsIdentifier) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[125]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[257]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((discardKeys) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[127]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[258]++;
          Ref newAlias =
              p.getDeclaration().cloneAndReclassify(Ref.Type.ALIASING_GET);
          newAlias.node = refNode;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[259]++;
          p.addRef(newAlias);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[260]++;

        } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[128]++;}

        p.getDeclaration().node = nameNode;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[261]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[262]++;
int CodeCoverConditionCoverageHelper_C68;

        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[129]++;
          checkForHosedThisReferences(value, value.getJSDocInfo(), p);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[263]++;

        } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[130]++;}

      } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[126]++;}

      numVars++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[264]++;
    }
    return numVars;
  }

  /**
   * Adds global variable "stubs" for any properties of a global name that are
   * only set in a local scope or read but never set.
   *
   * @param n An object representing a global name (e.g. "a", "a.b.c")
   * @param alias The flattened name of the object whose properties we are
   *     adding stubs for (e.g. "a$b$c")
   * @param parent The node to which new global variables should be added
   *     as children
   * @param addAfter The child of after which new
   *     variables should be added (may be null)
   * @return The number of variables added
   */
  private int addStubsForUndeclaredProperties(
      Name n, String alias, Node parent, Node addAfter) {
    Preconditions.checkState(n.canCollapseUnannotatedChildNames());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[265]++;
    Preconditions.checkArgument(NodeUtil.isStatementBlock(parent));
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[266]++;
    Preconditions.checkNotNull(addAfter);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[267]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[268]++;
    int numStubs = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[269]++;
int CodeCoverConditionCoverageHelper_C69;
    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((n.props != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[131]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[270]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[49]++;


      for (Name p : n.props) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[49]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[50]--;
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.loops[51]++;
}
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[271]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((p.needsToBeStubbed()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[133]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[272]++;
          String propAlias = appendPropForAlias(alias, p.getBaseName());
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[273]++;
          Node nameNode = IR.name(propAlias);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[274]++;
          Node newVar = IR.var(nameNode)
              .copyInformationFromForTree(addAfter);
          parent.addChildAfter(newVar, addAfter);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[275]++;
          addAfter = newVar;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[276]++;
          numStubs++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[277]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[278]++;
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[279]++;
int CodeCoverConditionCoverageHelper_C71;

          // Determine if this is a constant var by checking the first
          // reference to it. Don't check the declaration, as it might be null.
          if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((p.getRefs().get(0).node.getLastChild().getBooleanProp(
                  Node.IS_CONSTANT_NAME)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[135]++;
            nameNode.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[280]++;

          } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[136]++;}

        } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[134]++;}
      }

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[132]++;}
    return numStubs;
  }

  private static String appendPropForAlias(String root, String prop) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[281]++;
int CodeCoverConditionCoverageHelper_C72;
    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((prop.indexOf('$') != -1) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[137]++;
      // Encode '$' in a property as '$0'. Because '0' cannot be the
      // start of an identifier, this will never conflict with our
      // encoding from '.' -> '$'.
      prop = prop.replace("$", "$0");
CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.statements[282]++;

    } else {
  CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x.branches[138]++;}
    return root + '$' + prop;
  }
}

class CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x ());
  }
    public static long[] statements = new long[283];
    public static long[] branches = new long[139];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[73];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CollapseProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,3,2,1,3,1,3,1,3,1,1,1,2,1,1,1,1,1,1,3,1,3,1,2,1,1,2,1,2,1,1,1,3,2,1,2,1,1,2,1,1,3,1,1,1,1,1,3,1,1,1,2,1,1,1,1,2,2,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 72; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[52];

  public CodeCoverCoverageCounter$26ol8912n8s73pfp1tnt1bur6il1ph799u8x () {
    super("com.google.javascript.jscomp.CollapseProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 282; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 138; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 51; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CollapseProperties.java");
      for (int i = 1; i <= 282; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 138; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 17; i++) {
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

