/*
 * Copyright 2007 The Closure Compiler Authors.
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

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Set;

/**
 * Finds all method declarations and pulls them into data structures
 * for use during cleanups such as arity checks or inlining.
 *
 */
abstract class MethodCompilerPass implements CompilerPass {
  static {
    CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.ping();
  }

  /** List of methods defined in externs */
  final Set<String> externMethods = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[1]++;
  }

  /** List of extern methods without signatures that we can't warn about */
  final Set<String> externMethodsWithoutSignatures = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[2]++;
  }

  /** List of property names that may not be methods */
  final Set<String> nonMethodProperties = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[3]++;
  }

  // Use a linked map here to keep the output deterministic.  Otherwise,
  // the choice of method bodies is random when multiple identical definitions
  // are found which causes problems in the source maps.
  final Multimap<String, Node> methodDefinitions =
      LinkedHashMultimap.create();
  {
    CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[4]++;
  }

  final AbstractCompiler compiler;

  /**
   * The signature storage is provided by the implementing class.
   */
  interface SignatureStore {
    public void reset();
    public void addSignature(
        String functionName, Node functionNode, String sourceFile);
    public void removeSignature(String functionName);
  }

  MethodCompilerPass(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[5]++;
  }

  @Override
  public void process(Node externs, Node root) {
    externMethods.clear();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[6]++;
    externMethodsWithoutSignatures.clear();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[7]++;
    getSignatureStore().reset();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[8]++;
    methodDefinitions.clear();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[9]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((externs != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[1]++;
      NodeTraversal.traverse(compiler, externs, new GetExternMethods());
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[11]++;

    } else {
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[2]++;}
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[12]++;


    List<Node> externsAndJs = Lists.newArrayList(externs, root);
    NodeTraversal.traverseRoots(
        compiler, Lists.newArrayList(externs, root), new GatherSignatures());
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[13]++;
    NodeTraversal.traverseRoots(
        compiler, externsAndJs, getActingCallback());
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[14]++;
  }

  /**
   * Subclasses should return a callback that does the actual work they
   * want to perform given the computed list of method signatures
   */
  abstract Callback getActingCallback();

  /**
   * Subclasses should return a SignatureStore for storing discovered
   * signatures.
   */
  abstract SignatureStore getSignatureStore();

  /**
   * Adds a node that may represent a function signature (if it's a function
   * itself or the name of a function).
   */
  private void addPossibleSignature(String name, Node node, NodeTraversal t) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[3]++;
      // The node we're looking at is a function, so we can add it directly
      addSignature(name, node, t.getSourceName());
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[16]++;

    } else {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[4]++;
      nonMethodProperties.add(name);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[17]++;
    }
  }

  private void addSignature(String name, Node function, String fnSourceName) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((externMethodsWithoutSignatures.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[5]++;
      return;

    } else {
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[6]++;}

    getSignatureStore().addSignature(name, function, fnSourceName);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[19]++;
    methodDefinitions.put(name, function);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[20]++;
  }

  /**
   * Gathers methods from the externs file. Methods that are listed there but
   * do not have a signature are flagged to be ignored when doing arity checks.
   * Methods that do include signatures will be checked.
   */
  private class GetExternMethods extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[21]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[7]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[8]++; {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[22]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;

          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[9]++;
            return;

          } else {
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[10]++;}
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[24]++;

          String name = dest.getString();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;

          // We have a signature. Parse tree of the form:
          // assign                       <- parent
          //      getprop                 <- n
          //          name methods
          //          string setTimeout
          //      function
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.getNext().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[11]++;
            addSignature(name, n.getNext(), t.getSourceName());
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[26]++;

          } else {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[12]++;
            getSignatureStore().removeSignature(name);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[27]++;
            externMethodsWithoutSignatures.add(name);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[28]++;
          }

          externMethods.add(name);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[29]++;
        }
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[30]++; break;

        case Token.OBJECTLIT:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[13]++; {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
          for (Node key = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); key = key.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[1]--;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[2]--;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[3]++;
}
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[32]++;
            Node value = key.getFirstChild();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[33]++;
            String name = key.getString();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((key.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[14]++;
              addSignature(name, value, t.getSourceName());
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[35]++;

            } else {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[15]++;
              getSignatureStore().removeSignature(name);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[36]++;
              externMethodsWithoutSignatures.add(name);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[37]++;
            }
            externMethods.add(name);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[38]++;
          }
        }
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[39]++; break; default : CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[16]++;
      }
    }
  }

  /**
   * Gather signatures from the source to be compiled.
   */
  private class GatherSignatures extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[40]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[17]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[18]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[41]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;

          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[19]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dest.getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[21]++;
              processPrototypeParent(t, parent);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[44]++;

            } else {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[22]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
              // Static methods of the form Foo.bar = function() {} or
              // Static methods of the form Foo.bar = baz (where baz is a
              // function name). Parse tree looks like:
              // assign                 <- parent
              //      getprop           <- n
              //          name Foo
              //          string bar
              //      function or name  <- n.getNext()
              if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[23]++;
                addPossibleSignature(dest.getString(), n.getNext(), t);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[46]++;

              } else {
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[24]++;}
            }

          } else {
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[20]++;}
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[47]++;
          break;

        case Token.OBJECTLIT:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[25]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[48]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;
          for (Node key = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); key = key.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[4]--;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[5]--;
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.loops[6]++;
}
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[49]++;
            switch(key.getType()) {
              case Token.STRING_KEY:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[26]++;
                addPossibleSignature(key.getString(), key.getFirstChild(), t);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[50]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[51]++;
                break;
              case Token.SETTER_DEF:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[27]++;
              case Token.GETTER_DEF:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[28]++;
                nonMethodProperties.add(key.getString());
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[52]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[53]++;
                break;
              default:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[29]++;
                throw new IllegalStateException(
                    "unexpect OBJECTLIT key: " + key);
            }
          }
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[54]++;
          break; default : CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[30]++;
      }
    }

    /**
     * Processes the parent of a GETPROP prototype, which can either be
     * another GETPROP (in the case of Foo.prototype.bar), or can be
     * an assignment (in the case of Foo.prototype = ...).
     */
    private void processPrototypeParent(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[55]++;
      switch (n.getType()) {
        // Foo.prototype.getBar = function() { ... } or
        // Foo.prototype.getBar = getBaz (where getBaz is a function)
        // parse tree looks like:
        // assign                          <- parent
        //     getprop                     <- n
        //         getprop
        //             name Foo
        //             string prototype
        //         string getBar
        //     function or name            <- assignee
        case Token.GETPROP:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[31]++;
        case Token.GETELEM:
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[32]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[56]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[57]++;
          Node parent = n.getParent().getParent();
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[58]++;
int CodeCoverConditionCoverageHelper_C12;

          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[33]++;
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[59]++;
            Node assignee = parent.getFirstChild().getNext();

            addPossibleSignature(dest.getString(), assignee, t);
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[60]++;

          } else {
  CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[34]++;}
CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.statements[61]++;
          break; default : CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl.branches[35]++;
      }
    }
  }
}

class CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl ());
  }
    public static long[] statements = new long[62];
    public static long[] branches = new long[36];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MethodCompilerPass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,3,1,2,1,1,2,1,2};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$2iazzu7qu6wvjlpuy5qg8eehhae1hi4qpizl () {
    super("com.google.javascript.jscomp.MethodCompilerPass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 61; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 35; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MethodCompilerPass.java");
      for (int i = 1; i <= 61; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 35; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

