/*
 * Copyright 2011 The Closure Compiler Authors.
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

import com.google.javascript.jscomp.NodeTraversal.AbstractShallowCallback;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;

/**
 * A CleanupPass implementation that will remove all field declarations on
 * JSTypes contributed by the original file.
 * <p>
 * This pass is expected to clear out declarations contributed to any JSType,
 * even if the constructor declaration is not provided in the file being
 * updated.
 *
 * @author tylerg@google.com (Tyler Goodwin)
 */
public class FieldCleanupPass implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.ping();
  }


  private final AbstractCompiler compiler;

  public FieldCleanupPass(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[1]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[2]++;
    String srcName = originalRoot.getSourceFileName();
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[3]++;
    Callback cb =
        new QualifiedNameSearchTraversal(compiler.getTypeRegistry(), srcName);
    new NodeTraversal(compiler, cb).traverse(originalRoot);
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[4]++;
  }

  @Override
  public void process(Node externs, Node root) {
    // FieldCleanupPass should not do work during process.
  }

  /**
   * Search for fields to cleanup by looking for nodes in the tree which are
   * root nodes of qualified names and getting the final token of the qualified
   * name as a candidate field.
   * <p>
   * Once a candidate field is found, ask the {@code JSTypeRegistry} for all
   * JSTypes that have a field with the same name, and check if the field on
   * that type is defined in the file the compiler is cleaning up. If so, remove
   * the field, and update the {@code JSTypeRegistry} to no longer associate the
   * type with the field.
   * <p>
   * This algorithm was chosen for simplicity and is less than optimally
   * efficient in two ways:
   * <p>
   * 1) All types with a matching field name are iterated over (when only types
   * that extend or implement the JSType indicated by the containing object in
   * the found Qualified Name need to be checked).
   * <p>
   * 2) All Qualified Names are checked, even those which are not L-Values or
   * single declarations of an Type Expression. In general field should only be
   * declared as part of an assignment ('ns.Type.a = 3;') or stand alone name
   * declaration ('ns.Type.a;').
   */
  static class QualifiedNameSearchTraversal extends AbstractShallowCallback {

    private final JSTypeRegistry typeRegistry;
    private final String srcName;

    public QualifiedNameSearchTraversal(
        JSTypeRegistry typeRegistry, String srcName) {
      this.typeRegistry = typeRegistry;
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[5]++;
      this.srcName = srcName;
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[6]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node p) {
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
      // We are a root GetProp
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[1]++;
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[8]++;
        String propName = getFieldName(n);
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[9]++;
        JSType type = n.getFirstChild().getJSType();
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type.toObjectType() == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[3]++;
          // Note cases like <primitive>.field
          return;

        } else {
  CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[4]++;}
        removeProperty(type.toObjectType(), propName);
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[11]++;

      } else {
  CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[2]++;}
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[5]++;
        n.getJSDocInfo().setAssociatedNode(null);
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[13]++;

      } else {
  CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[6]++;}
    }

    /**
     * Removes a given property from a type and updates type-registry.
     *
     * @param type the object type to be updated, should not be null
     * @param propName the property to remove
     */
    private void removeProperty(ObjectType type, String propName) {
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[14]++;
      Node pNode = type.getPropertyNode(propName);
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((pNode != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((srcName.equals(pNode.getSourceFileName())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[7]++;
        typeRegistry.unregisterPropertyOnType(propName, type);
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[16]++;
        type.removeProperty(propName);
CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.statements[17]++;

      } else {
  CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl.branches[8]++;}
    }

    private String getFieldName(Node n) {
      return n.getLastChild().getString();
    }
  }
}

class CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FieldCleanupPass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,2};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$1mhkfa4bxr3ul6nqw2wtte0efozy62ezl () {
    super("com.google.javascript.jscomp.FieldCleanupPass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FieldCleanupPass.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

