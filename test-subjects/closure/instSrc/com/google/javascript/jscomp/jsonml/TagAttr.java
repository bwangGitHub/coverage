/*
 * Copyright 2010 The Closure Compiler Authors.
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

package com.google.javascript.jscomp.jsonml;

import java.util.HashMap;
import java.util.Map;

/**
 * List of attributes that a JsonML element may have.
 *
 * @author dhans@google.com (Daniel Hans)
 */
public enum TagAttr {
  BODY("body"),
  DIRECTIVE("directive"),
  END_COLUMN("endColumn"),
  END_LINE("endLine"),
  FLAGS("flags"),
  IS_PREFIX("isPrefix"),
  LABEL("label"),
  NAME("name"),
  OP("op"),
  OPAQUE_POSITION("opaque_position"),
  SOURCE("source"),
  START_COLUMN("startColumn"),
  START_LINE("startLine"),
  TYPE("type"),
  VALUE("value");

  private final String name;
  private static final Map<String, TagAttr> lookup =
      new HashMap<String, TagAttr>();
  static {
    CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.statements[1]++;
  }

  static {
CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.statements[2]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.loops[1]++;


    for (TagAttr t : TagAttr.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.loops[1]--;
  CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.loops[2]--;
  CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.loops[3]++;
}
      lookup.put(t.getName(), t);
CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.statements[3]++;
    }
  }

  private String getName() {
    return name;
  }

  private TagAttr(String name) {
    this.name = name;
CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d.statements[4]++;
  }

  public static TagAttr get(String name) {
    return lookup.get(name);
  }

  @Override
  public String toString() {
    return name;
  }
}

class CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$2j59mdwsfiojvrsqi5d () {
    super("com.google.javascript.jscomp.jsonml.TagAttr.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.TagAttr.java");
      for (int i = 1; i <= 4; i++) {
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

