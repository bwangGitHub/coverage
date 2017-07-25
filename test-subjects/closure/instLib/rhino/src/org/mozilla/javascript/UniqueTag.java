/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;

/**
 * Class instances represent serializable tags to mark special Object values.
 * <p>
 * Compatibility note: under jdk 1.1 use
 * org.mozilla.javascript.serialize.ScriptableInputStream to read serialized
 * instances of UniqueTag as under this JDK version the default
 * ObjectInputStream would not restore them correctly as it lacks support
 * for readResolve method
 */
public final class UniqueTag implements Serializable
{
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.ping();
  }

    static final long serialVersionUID = -4320556826714577259L;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[1]++;
  }

    private static final int ID_NOT_FOUND    = 1;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[2]++;
  }
    private static final int ID_NULL_VALUE   = 2;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[3]++;
  }
    private static final int ID_DOUBLE_MARK  = 3;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[4]++;
  }

    /**
     * Tag to mark non-existing values.
     */
    public static final UniqueTag
        NOT_FOUND = new UniqueTag(ID_NOT_FOUND);
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[5]++;
  }

    /**
     * Tag to distinguish between uninitialized and null values.
     */
    public static final UniqueTag
        NULL_VALUE = new UniqueTag(ID_NULL_VALUE);
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[6]++;
  }

    /**
     * Tag to indicate that a object represents "double" with the real value
     * stored somewhere else.
     */
    public static final UniqueTag
        DOUBLE_MARK = new UniqueTag(ID_DOUBLE_MARK);
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[7]++;
  }

    private final int tagId;

    private UniqueTag(int tagId)
    {
        this.tagId = tagId;
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[8]++;
    }

    public Object readResolve()
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[9]++;
        switch (tagId) {
          case ID_NOT_FOUND:
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[1]++;
            return NOT_FOUND;
          case ID_NULL_VALUE:
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[2]++;
            return NULL_VALUE;
          case ID_DOUBLE_MARK:
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[3]++;
            return DOUBLE_MARK; default : CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[4]++;
        }
        throw new IllegalStateException(String.valueOf(tagId));
    }

// Overridden for better debug printouts
    @Override
    public String toString()
    {
        String name;
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[10]++;
        switch (tagId) {
          case ID_NOT_FOUND:
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[5]++;
            name = "NOT_FOUND";
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[11]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[12]++;
            break;
          case ID_NULL_VALUE:
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[6]++;
            name = "NULL_VALUE";
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[13]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[14]++;
            break;
          case ID_DOUBLE_MARK:
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[7]++;
            name = "DOUBLE_MARK";
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[15]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.statements[16]++;
            break;
          default:
CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x.branches[8]++;
            throw Kit.codeBug();
        }
        return super.toString()+": "+name;
    }

}

class CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[9];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzh6lq8fvbiz4rhbr92lez4x () {
    super("org.mozilla.javascript.RHINO-SRC-UniqueTag.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-UniqueTag.java");
      for (int i = 1; i <= 16; i++) {
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


