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
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * RenameLabels renames all the labels so that they have short names, to reduce
 * code size and also to obfuscate the code.
 *
 * Label names have a unique namespace, so variable or function names clashes
 * are not a concern, but keywords clashes are.
 *
 * Additionally, labels names are only within the statements include in the
 * label and do not cross function boundaries. This means that it is possible to
 * create one label name that is used for labels at any given depth of label
 * nesting. Typically, the name "a" will be used for all top-level labels, "b"
 * for the next nested label, and so on. For example:
 *
 * <code>
 * function bar() {
 *   a: {
 *     b: {
 *       foo();
 *     }
 *   }
 *
 *   a: {
 *     b: break a;
 *   }
 * }
 * </code>
 *
 * The general processes is as follows: process() is the entry point for the
 * CompilerPass, and from there a standard "ScopedCallback" traversal is done,
 * where "shouldTraverse" is called when descending the tree, and the "visit" is
 * called in a depth first manner. The name for the label is selected during the
 * decent in "shouldTraverse", and the references to the label name are renamed
 * as they are encountered during the "visit". This means that if the label is
 * unreferenced, it is known when the label node is visited, and, if so, can be
 * safely removed.
 *
 * @author johnlenz@google.com (John Lenz)
 */
final class RenameLabels implements CompilerPass {
  static {
    CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.ping();
  }

  private final AbstractCompiler compiler;
  private final Supplier<String> nameSupplier;
  private final boolean removeUnused;

  RenameLabels(AbstractCompiler compiler) {
    this(compiler, new DefaultNameSupplier(), true);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[1]++;
  }

  RenameLabels(
      AbstractCompiler compiler,
      Supplier<String> supplier,
      boolean removeUnused) {
    this.compiler = compiler;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[2]++;
    this.nameSupplier = supplier;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[3]++;
    this.removeUnused = removeUnused;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[4]++;
  }

  static class DefaultNameSupplier implements Supplier<String> {
    // NameGenerator is used to create safe label names.
    final NameGenerator nameGenerator =
        new NameGenerator(new HashSet<String>(), "", null);
  {
    CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[5]++;
  }

    @Override
    public String get() {
      return nameGenerator.generateNextName();
    }
  }

  /**
   * Iterate through the nodes, renaming all the labels.
   */
  class ProcessLabels implements ScopedCallback {

    ProcessLabels() {
      // Create a entry for global scope.
      namespaceStack.push(new LabelNamespace());
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[6]++;
    }

    // A stack of labels namespaces. Labels in an outer scope aren't part of an
    // inner scope, so a new namespace is created each time a scope is entered.
    final Deque<LabelNamespace> namespaceStack = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[7]++;
  }

    // The list of generated names. Typically, the first name will be "a",
    // the second "b", etc.
    final ArrayList<String> names = new ArrayList<String>();
  {
    CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[8]++;
  }


    @Override
    public void enterScope(NodeTraversal nodeTraversal) {
      // Start a new namespace for label names.
      namespaceStack.push(new LabelNamespace());
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[9]++;
    }

    @Override
    public void exitScope(NodeTraversal nodeTraversal) {
      namespaceStack.pop();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[10]++;
    }

    /**
     * shouldTraverse is call when descending into the Node tree, so it is used
     * here to build the context for label renames.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean shouldTraverse(NodeTraversal nodeTraversal, Node node,
        Node parent) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[1]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[12]++;
        // Determine the new name for this label.
        LabelNamespace current = namespaceStack.peek();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[13]++;
        int currentDepth = current.renameMap.size() + 1;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[14]++;
        String name = node.getFirstChild().getString();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[15]++;

        // Store the context for this label name.
        LabelInfo li = new LabelInfo(currentDepth);
        Preconditions.checkState(!current.renameMap.containsKey(name));
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[16]++;
        current.renameMap.put(name, li);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[17]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;

        // Create a new name, if needed, for this depth.
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((names.size() < currentDepth) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[3]++;
          names.add(nameSupplier.get());
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[19]++;

        } else {
  CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[4]++;}
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[20]++;

        String newName = getNameForId(currentDepth);
        compiler.addToDebugLog("label renamed: " + name + " => " + newName);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[21]++;

      } else {
  CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[2]++;}

      return true;
    }

    /**
     * Delegate the actual processing of the node to visitLabel and
     * visitBreakOrContinue.
     *
     * {@inheritDoc}
     */
    @Override
    public void visit(NodeTraversal nodeTraversal, Node node, Node parent) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[22]++;
      switch (node.getType()) {
        case Token.LABEL:
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[5]++;
          visitLabel(node, parent);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[23]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[24]++;
          break;

        case Token.BREAK:
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[6]++;
        case Token.CONTINUE:
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[7]++;
          visitBreakOrContinue(node);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[25]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[26]++;
          break; default : CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[8]++;
      }
    }

    /**
     * Rename label references in breaks and continues.
     * @param node The break or continue node.
     */
    private void visitBreakOrContinue(Node node) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[27]++;
      Node nameNode = node.getFirstChild();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nameNode != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[9]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[29]++;
        // This is a named break or continue;
        String name = nameNode.getString();
        Preconditions.checkState(name.length() != 0);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[30]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[31]++;
        LabelInfo li = getLabelInfo(name);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((li != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[11]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[33]++;
          String newName = getNameForId(li.id);
          // Mark the label as referenced so it isn't removed.
          li.referenced = true;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[34]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((name.equals(newName)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[13]++;
            // Give it the short name.
            nameNode.setString(newName);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[36]++;
            compiler.reportCodeChange();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[37]++;

          } else {
  CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[12]++;}

      } else {
  CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[10]++;}
    }

    /**
     * Rename or remove labels.
     * @param node  The label node.
     * @param parent The parent of the label node.
     */
    private void visitLabel(Node node, Node parent) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[38]++;
      Node nameNode = node.getFirstChild();
      Preconditions.checkState(nameNode != null);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[39]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[40]++;
      String name = nameNode.getString();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[41]++;
      LabelInfo li = getLabelInfo(name);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
      // This is a label...
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((li.referenced) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((removeUnused) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[15]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[43]++;
        String newName = getNameForId(li.id);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((name.equals(newName)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[17]++;
          // ... and it is used, give it the short name.
          nameNode.setString(newName);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[45]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[46]++;

        } else {
  CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[18]++;}

      } else {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[16]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[47]++;
        // ... and it is not referenced, just remove it.
        Node newChild = node.getLastChild();
        node.removeChild(newChild);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[48]++;
        parent.replaceChild(node, newChild);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[49]++;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[50]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((newChild.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[19]++;
          NodeUtil.tryMergeBlock(newChild);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[51]++;

        } else {
  CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.branches[20]++;}
        compiler.reportCodeChange();
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[52]++;
      }

      // Remove the label from the current stack of labels.
      namespaceStack.peek().renameMap.remove(name);
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[53]++;
    }

    /**
     * @param id The id, which is the depth of the label in the current context,
     *        for which to get a short name.
     * @return The short name of the identified label.
     */
    String getNameForId(int id) {
      return names.get(id - 1);
    }

    /**
     * @param name The name to retrieve information about.
     * @return The structure representing the name in the current context.
     */
    LabelInfo getLabelInfo(String name) {
      return namespaceStack.peek().renameMap.get(name);
    }
  }

  @Override
  public void process(Node externs, Node root) {
    // Do variable reference counting.
    NodeTraversal.traverse(compiler, root, new ProcessLabels());
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[54]++;
  }


  private static class LabelInfo {
    boolean referenced = false;
  {
    CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[55]++;
  }
    final int id;

    LabelInfo(int id) {
      this.id = id;
CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[56]++;
    }
  }


  private static class LabelNamespace {
    final Map<String, LabelInfo> renameMap = new HashMap<String, LabelInfo>();
  {
    CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69.statements[57]++;
  }
  }

}

class CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69 ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RenameLabels.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$yos5p7wypn5ds71c561tfzrs69 () {
    super("com.google.javascript.jscomp.RenameLabels.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RenameLabels.java");
      for (int i = 1; i <= 57; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

