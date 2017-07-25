/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * AST node for an E4X (Ecma-357) embedded XML literal.  Node type is
 * {@link Token#XML}.  The parser generates a simple list of strings and
 * expressions.  In the future we may parse the XML and produce a richer set of
 * nodes, but for now it's just a set of expressions evaluated to produce a
 * string to pass to the {@code XML} constructor function.<p>
 */
public class XmlLiteral extends AstNode {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.ping();
  }


    private List<XmlFragment> fragments = new ArrayList<XmlFragment>();
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[1]++;
  }

    {
        type = Token.XML;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[2]++;
    }

    public XmlLiteral() {
    }

    public XmlLiteral(int pos) {
        super(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[3]++;
    }

    public XmlLiteral(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[4]++;
    }

    /**
     * Returns fragment list - a list of expression nodes.
     */
    public List<XmlFragment> getFragments() {
        return fragments;
    }

    /**
     * Sets fragment list, removing any existing fragments first.
     * Sets the parent pointer for each fragment in the list to this node.
     * @param fragments fragment list.  Replaces any existing fragments.
     * @throws IllegalArgumentException} if {@code fragments} is {@code null}
     */
    public void setFragments(List<XmlFragment> fragments) {
        assertNotNull(fragments);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[5]++;
        this.fragments.clear();
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[6]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[1]++;


        for (XmlFragment fragment : fragments) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[3]++;
}
            addFragment(fragment);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[8]++;
  }
    }

    /**
     * Adds a fragment to the fragment list.  Sets its parent to this node.
     * @throws IllegalArgumentException} if {@code fragment} is {@code null}
     */
    public void addFragment(XmlFragment fragment) {
        assertNotNull(fragment);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[9]++;
        fragments.add(fragment);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[10]++;
        fragment.setParent(this);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[11]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[12]++;
        StringBuilder sb = new StringBuilder(250);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[4]++;


        for (XmlFragment frag : fragments) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[6]++;
}
            sb.append(frag.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[14]++;
        }
        return sb.toString();
    }

    /**
     * Visits this node, then visits each child fragment in lexical order.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.branches[1]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[16]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[7]++;


            for (XmlFragment frag : fragments) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.loops[9]++;
}
                frag.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.statements[17]++;
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-XmlLiteral.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao0zka5gz99wnk2zpish () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlLiteral.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlLiteral.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

