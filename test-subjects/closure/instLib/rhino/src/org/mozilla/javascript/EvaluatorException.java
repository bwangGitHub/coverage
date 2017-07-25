/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


package org.mozilla.javascript;

/**
 * The class of exceptions thrown by the JavaScript engine.
 */
public class EvaluatorException extends RhinoException
{
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl.ping();
  }

    static final long serialVersionUID = -8743165779676009808L;
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl.statements[1]++;
  }

    public EvaluatorException(String detail)
    {
        super(detail);
CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl.statements[2]++;
    }

    /**
     * Create an exception with the specified detail message.
     *
     * Errors internal to the JavaScript engine will simply throw a
     * RuntimeException.
     *
     * @param detail the error message
     * @param sourceName the name of the source reponsible for the error
     * @param lineNumber the line number of the source
     */
    public EvaluatorException(String detail, String sourceName,
                              int lineNumber)
    {
        this(detail, sourceName, lineNumber, null, 0);
CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl.statements[3]++;
    }

    /**
     * Create an exception with the specified detail message.
     *
     * Errors internal to the JavaScript engine will simply throw a
     * RuntimeException.
     *
     * @param detail the error message
     * @param sourceName the name of the source responsible for the error
     * @param lineNumber the line number of the source
     * @param columnNumber the columnNumber of the source (may be zero if
     *                     unknown)
     * @param lineSource the source of the line containing the error (may be
     *                   null if unknown)
     */
    public EvaluatorException(String detail, String sourceName, int lineNumber,
                              String lineSource, int columnNumber)
    {
        super(detail);
CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl.statements[4]++;
        recordErrorOrigin(sourceName, lineNumber, lineSource, columnNumber);
CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl.statements[5]++;
    }

    /**
     * @deprecated Use {@link RhinoException#sourceName()} from the super class.
     */
    public String getSourceName()
    {
        return sourceName();
    }

    /**
     * @deprecated Use {@link RhinoException#lineNumber()} from the super class.
     */
    public int getLineNumber()
    {
        return lineNumber();
    }

    /**
     * @deprecated Use {@link RhinoException#columnNumber()} from the super class.
     */
    public int getColumnNumber()
    {
        return columnNumber();
    }

    /**
     * @deprecated Use {@link RhinoException#lineSource()} from the super class.
     */
    public String getLineSource()
    {
        return lineSource();
    }

}

class CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$el0607z4lg6oa7b0s1unu54cginq2ep66s0j5h4qjbuts4ucorl () {
    super("org.mozilla.javascript.RHINO-SRC-EvaluatorException.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-EvaluatorException.java");
      for (int i = 1; i <= 5; i++) {
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

