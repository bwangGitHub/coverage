/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

/**
 * Represents a symbol-table entry.
 */
public class Symbol {
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.ping();
  }


    // One of Token.FUNCTION, Token.LP (for parameters), Token.VAR,
    // Token.LET, or Token.CONST
    private int declType;
    private int index = -1;
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[1]++;
  }
    private String name;
    private Node node;
    private Scope containingTable;

    public Symbol() {
    }

    /**
     * Constructs a new Symbol with a specific name and declaration type
     * @param declType {@link Token#FUNCTION}, {@link Token#LP}
     * (for params), {@link Token#VAR}, {@link Token#LET} or {@link Token#CONST}
     */
    public Symbol(int declType, String name) {
        setName(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[2]++;
        setDeclType(declType);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[3]++;
    }

    /**
     * Returns symbol declaration type
     */
    public int getDeclType() {
        return declType;
    }

    /**
     * Sets symbol declaration type
     */
    public void setDeclType(int declType) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (512)) == 0 || true) &&
 ((declType == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((declType == Token.LP) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((declType == Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((declType == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((declType == Token.CONST) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 5) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 5) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.branches[1]++;
            throw new IllegalArgumentException("Invalid declType: " + declType);
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.branches[2]++;}
        this.declType = declType;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[5]++;
    }

    /**
     * Returns symbol name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets symbol name
     */
    public void setName(String name) {
        this.name = name;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[6]++;
    }

    /**
     * Returns the node associated with this identifier
     */
    public Node getNode() {
        return node;
    }

    /**
     * Returns symbol's index in its scope
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets symbol's index in its scope
     */
    public void setIndex(int index) {
        this.index = index;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[7]++;
    }

    /**
     * Sets the node associated with this identifier
     */
    public void setNode(Node node) {
        this.node = node;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[8]++;
    }

    /**
     * Returns the Scope in which this symbol is entered
     */
    public Scope getContainingTable() {
        return containingTable;
    }

    /**
     * Sets this symbol's Scope
     */
    public void setContainingTable(Scope containingTable) {
        this.containingTable = containingTable;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[9]++;
    }

    public String getDeclTypeName() {
        return Token.typeToName(declType);
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[10]++;
        StringBuilder result = new StringBuilder();
        result.append("Symbol (");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[11]++;
        result.append(getDeclTypeName());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[12]++;
        result.append(") name=");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[13]++;
        result.append(name);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[14]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.branches[3]++;
            result.append(" line=");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[16]++;
            result.append(node.getLineno());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.statements[17]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929.branches[4]++;}
        return result.toString();
    }
}

class CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929 ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-Symbol.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbt3njggjqbugi929 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Symbol.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Symbol.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

