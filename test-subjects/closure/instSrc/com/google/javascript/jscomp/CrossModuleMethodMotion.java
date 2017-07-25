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

import com.google.javascript.jscomp.AnalyzePrototypeProperties.NameInfo;
import com.google.javascript.jscomp.AnalyzePrototypeProperties.Property;
import com.google.javascript.jscomp.AnalyzePrototypeProperties.Symbol;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Move prototype methods into later modules.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class CrossModuleMethodMotion implements CompilerPass {
  static {
    CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.ping();
  }


  // Internal errors
  static final DiagnosticType NULL_COMMON_MODULE_ERROR = DiagnosticType.error(
      "JSC_INTERNAL_ERROR_MODULE_DEPEND",
      "null deepest common module");
  static {
    CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final IdGenerator idGenerator;
  private final AnalyzePrototypeProperties analyzer;
  private final JSModuleGraph moduleGraph;

  static final String STUB_METHOD_NAME = "JSCompiler_stubMethod";
  static {
    CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[2]++;
  }
  static final String UNSTUB_METHOD_NAME = "JSCompiler_unstubMethod";
  static {
    CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[3]++;
  }

  // Visible for testing
  static final String STUB_DECLARATIONS =
      "var JSCompiler_stubMap = [];" +
      "function JSCompiler_stubMethod(JSCompiler_stubMethod_id) {" +
      "  return function() {" +
      "    return JSCompiler_stubMap[JSCompiler_stubMethod_id].apply(" +
      "        this, arguments);" +
      "  };" +
      "}" +
      "function JSCompiler_unstubMethod(" +
      "    JSCompiler_unstubMethod_id, JSCompiler_unstubMethod_body) {" +
      "  return JSCompiler_stubMap[JSCompiler_unstubMethod_id] = " +
      "      JSCompiler_unstubMethod_body;" +
      "}";
  static {
    CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[4]++;
  }

  /**
   * Creates a new pass for moving prototype properties.
   * @param compiler The compiler.
   * @param idGenerator An id generator for method stubs.
   * @param canModifyExterns If true, then we can move prototype
   *     properties that are declared in the externs file.
   */
  CrossModuleMethodMotion(AbstractCompiler compiler, IdGenerator idGenerator,
      boolean canModifyExterns) {
    this.compiler = compiler;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[5]++;
    this.idGenerator = idGenerator;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[6]++;
    this.moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[7]++;
    this.analyzer = new AnalyzePrototypeProperties(compiler, moduleGraph,
        canModifyExterns, false);
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[8]++;
  }

  @Override
  public void process(Node externRoot, Node root) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    // If there are < 2 modules, then we will never move anything,
    // so we're done.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((moduleGraph.getModuleCount() > 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[1]++;
      analyzer.process(externRoot, root);
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[10]++;
      moveMethods(analyzer.getAllNameInfo());
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[11]++;

    } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[2]++;}
  }

  /**
   * Move methods deeper in the module graph when possible.
   */
  private void moveMethods(Collection<NameInfo> allNameInfo) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[12]++;
    boolean hasStubDeclaration = idGenerator.hasGeneratedAnyIds();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[1]++;


    for (NameInfo nameInfo : allNameInfo) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[1]--;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[2]--;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[3]++;
}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((nameInfo.isReferenced()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[3]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[15]++;
        // The code below can't do anything with unreferenced name
        // infos.  They should be skipped to avoid NPE since their
        // deepestCommonModuleRef is null.
        continue;

      } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[4]++;}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;

      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nameInfo.readsClosureVariables()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[5]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[17]++;
        continue;

      } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[6]++;}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[18]++;

      JSModule deepestCommonModuleRef = nameInfo.getDeepestCommonModuleRef();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((deepestCommonModuleRef == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[7]++;
        compiler.report(JSError.make(NULL_COMMON_MODULE_ERROR));
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[20]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[21]++;
        continue;

      } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[8]++;}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[22]++;

      Iterator<Symbol> declarations =
          nameInfo.getDeclarations().descendingIterator();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
      while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((declarations.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[4]--;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[5]--;
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.loops[6]++;
}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[24]++;
        Symbol symbol = declarations.next();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((symbol instanceof Property) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[9]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[26]++;
          continue;

        } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[10]++;}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[27]++;
        Property prop = (Property) symbol;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;

        // We should only move a property across modules if:
        // 1) We can move it deeper in the module graph, and
        // 2) it's a function, and
        // 3) it is not a GETTER_DEF or a SETTER_DEF, and
        // 4) the class is available in the global scope.
        //
        // #1 should be obvious. #2 is more subtle. It's possible
        // to copy off of a prototype, as in the code:
        // for (var k in Foo.prototype) {
        //   doSomethingWith(Foo.prototype[k]);
        // }
        // This is a common way to implement pseudo-multiple inheritance in JS.
        //
        // So if we move a prototype method into a deeper module, we must
        // replace it with a stub function so that it preserves its original
        // behavior.
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((prop.getRootVar() == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((prop.getRootVar().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[11]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[29]++;
          continue;

        } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[12]++;}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[30]++;

        Node value = prop.getValue();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((moduleGraph.dependsOn(deepestCommonModuleRef, prop.getModule())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[13]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[32]++;
          Node valueParent = value.getParent();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((valueParent.isGetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((valueParent.isSetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[15]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[34]++;
            // TODO(johnlenz): a GET or SET can't be deferred like a normal
            // FUNCTION property definition as a mix-in would get the result
            // of a GET instead of the function itself.
            continue;

          } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[16]++;}
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[35]++;
          Node proto = prop.getPrototype();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[36]++;
          int stubId = idGenerator.newId();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[37]++;

          // example: JSCompiler_stubMethod(id);
          Node stubCall = IR.call(
              IR.name(STUB_METHOD_NAME),
              IR.number(stubId))
              .copyInformationFromForTree(value);
          stubCall.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[38]++;

          // stub out the method in the original module
          // A.prototype.b = JSCompiler_stubMethod(id);
          valueParent.replaceChild(value, stubCall);
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[39]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[40]++;

          // unstub the function body in the deeper module
          Node unstubParent = compiler.getNodeForCodeInsertion(
              deepestCommonModuleRef);
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[41]++;
          Node unstubCall = IR.call(
              IR.name(UNSTUB_METHOD_NAME),
              IR.number(stubId),
              value);
          unstubCall.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[42]++;
          unstubParent.addChildToFront(
              // A.prototype.b = JSCompiler_unstubMethod(id, body);
              IR.exprResult(
                  IR.assign(
                      IR.getprop(
                          proto.cloneTree(),
                          IR.string(nameInfo.name)),
                      unstubCall))
                  .copyInformationFromForTree(value));
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[43]++;

          compiler.reportCodeChange();
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[44]++;

        } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[14]++;}
      }
    }
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((hasStubDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((idGenerator.hasGeneratedAnyIds()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[17]++;
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[46]++;
      // Declare stub functions in the top-most module.
      Node declarations = compiler.parseSyntheticCode(STUB_DECLARATIONS);
      compiler.getNodeForCodeInsertion(null).addChildrenToFront(
          declarations.removeChildren());
CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[47]++;

    } else {
  CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.branches[18]++;}
  }

  static class IdGenerator implements Serializable {
    private static final long serialVersionUID = 0L;

    /**
     * Ids for cross-module method stubbing, so that each method has
     * a unique id.
     */
    private int currentId = 0;
  {
    CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip.statements[48]++;
  }

    /**
     * Returns whether we've generated any new ids.
     */
    boolean hasGeneratedAnyIds() {
      return currentId != 0;
    }

    /**
     * Creates a new id for stubbing a method.
     */
    int newId() {
      return currentId++;
    }
  }
}

class CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip ());
  }
    public static long[] statements = new long[49];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CrossModuleMethodMotion.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,2,2,2,2};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$uo6f872d528w215k1ooar6l4ysf39v3lrq46wzfcsip () {
    super("com.google.javascript.jscomp.CrossModuleMethodMotion.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 48; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CrossModuleMethodMotion.java");
      for (int i = 1; i <= 48; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

