/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

/**
 * Encapsulates information for a JavaScript parse error or warning.
 */
public class ParseProblem {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.ping();
  }


    public static enum Type {Error, Warning}

    private Type type;
    private String message;
    private String sourceName;
    private int offset;
    private int length;

    /**
     * Constructs a new ParseProblem.
     */
    public ParseProblem(ParseProblem.Type type, String message,
                        String sourceName, int offset, int length) {
        setType(type);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[1]++;
        setMessage(message);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[2]++;
        setSourceName(sourceName);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[3]++;
        setFileOffset(offset);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[4]++;
        setLength(length);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[5]++;
    }

    public ParseProblem.Type getType() {
        return type;
    }

    public void setType(ParseProblem.Type type) {
        this.type = type;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[6]++;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[7]++;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String name) {
        this.sourceName = name;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[8]++;
    }

    public int getFileOffset() {
        return offset;
    }

    public void setFileOffset(int offset) {
        this.offset = offset;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[9]++;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[10]++;
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[11]++;
        StringBuilder sb = new StringBuilder(200);
        sb.append(sourceName).append(":");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[12]++;
        sb.append("offset=").append(offset).append(",");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[13]++;
        sb.append("length=").append(length).append(",");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[14]++;
        sb.append(type == Type.Error ? "error: " : "warning: ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[15]++;
        sb.append(message);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp.statements[16]++;
        return sb.toString();
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmmd4nl4qqnw1ch6oclj63ot1mqp () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ParseProblem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ParseProblem.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

