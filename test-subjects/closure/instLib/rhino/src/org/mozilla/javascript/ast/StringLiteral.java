/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;
import org.mozilla.javascript.ScriptRuntime;

/**
 * AST node for a single- or double-quoted string literal.
 * Node type is {@link Token#STRING}.<p>
 */
public class StringLiteral extends AstNode {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.ping();
  }


    private String value;
    private char quoteChar;

    {
        type = Token.STRING;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[1]++;
    }

    public StringLiteral() {
    }

    public StringLiteral(int pos) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[2]++;
    }

    /**
     * Creates a string literal node at the specified position.
     * @param len the length <em>including</em> the enclosing quotes
     */
    public StringLiteral(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[3]++;
    }

    /**
     * Returns the node's value:  the parsed string without the enclosing quotes
     * @return the node's value, a {@link String} of unescaped characters
     * that includes the delimiter quotes.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the string value, optionally including the enclosing quotes.
     */
    public String getValue(boolean includeQuotes) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((includeQuotes) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.branches[1]++;
            return value;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.branches[2]++;}
        return quoteChar + value + quoteChar;
    }

    /**
     * Sets the node's value.  Do not include the enclosing quotes.
     * @param value the node's value
     * @throws IllegalArgumentException} if value is {@code null}
     */
    public void setValue(String value) {
        assertNotNull(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[5]++;
        this.value = value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[6]++;
    }

    /**
     * Returns the character used as the delimiter for this string.
     */
    public char getQuoteCharacter() {
        return quoteChar;
    }

    public void setQuoteCharacter(char c) {
        quoteChar = c;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[7]++;
    }

    @Override
    public String toSource(int depth) {
        return new StringBuilder(makeIndent(depth))
                .append(quoteChar)
                .append(ScriptRuntime.escapeString(value, quoteChar))
                .append(quoteChar)
                .toString();
    }

    /**
     * Visits this node.  There are no children to visit.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p.statements[8]++;
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-StringLiteral.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$11f1r6z5fa12k16ld48oqbrudts8smaye5bvxmztcw2p () {
    super("org.mozilla.javascript.ast.RHINO-SRC-StringLiteral.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
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
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-StringLiteral.java");
      for (int i = 1; i <= 8; i++) {
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

