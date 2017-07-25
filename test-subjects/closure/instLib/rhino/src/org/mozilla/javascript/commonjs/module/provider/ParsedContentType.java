/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Breaks a "contentType; charset=encoding" MIME type into content type and
 * encoding parts.
 * @version $Id: ParsedContentType.java,v 1.3 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public final class ParsedContentType implements Serializable
{
  static {
    CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[1]++;
  }

    private final String contentType;
    private final String encoding;

    /**
     * Creates a new parsed content type.
     * @param mimeType the full MIME type; typically the value of the
     * "Content-Type" header of some MIME-compliant message. Can be null.
     */
    public ParsedContentType(String mimeType) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[2]++;
        String contentType = null;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[3]++;
        String encoding = null;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((mimeType != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[1]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[5]++;
            StringTokenizer tok = new StringTokenizer(mimeType, ";");
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((tok.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[3]++;
                contentType = tok.nextToken().trim();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[7]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
                while((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((tok.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.loops[1]--;
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.loops[2]--;
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.loops[3]++;
}
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[9]++;
                    String param = tok.nextToken().trim();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
                    if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((param.startsWith("charset=")) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[5]++;
                        encoding = param.substring(8).trim();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[11]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[12]++;
                        int l = encoding.length();
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
                        if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((l > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[7]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
                            if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((encoding.charAt(0) == '"') && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[9]++;
                                encoding = encoding.substring(1);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[15]++;

                            } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[10]++;}
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[16]++;
int CodeCoverConditionCoverageHelper_C7;
                            if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((encoding.charAt(l - 1) == '"') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[11]++;
                                encoding = encoding.substring(0, l - 1);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[17]++;

                            } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[12]++;}

                        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[8]++;}
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[18]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[6]++;}
                }

            } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.branches[2]++;}
        this.contentType = contentType;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[19]++;
        this.encoding = encoding;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d.statements[20]++;
    }

    /**
     * Returns the content type (without charset declaration) of the MIME type.
     * @return the content type (without charset declaration) of the MIME type.
     * Can be null if the MIME type was null.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Returns the character encoding of the MIME type.
     * @return the character encoding of the MIME type. Can be null when it is
     * not specified.
     */
    public String getEncoding() {
        return encoding;
    }
}

class CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ParsedContentType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$21tu3emdl0l6sfnhlhi46m0h00s4142yufozmq19e99c7x4j1d () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ParsedContentType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-ParsedContentType.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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
