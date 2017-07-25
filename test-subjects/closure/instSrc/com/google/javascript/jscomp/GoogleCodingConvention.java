/*
 * Copyright 2007 The Closure Compiler Authors.
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




import com.google.javascript.rhino.Node;

import java.util.regex.Pattern;

/**
 * This describes the Google-specific JavaScript coding conventions.
 * Within Google, variable names are semantically significant.
 *
 */
public class GoogleCodingConvention extends CodingConventions.Proxy {
  static {
    CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.ping();
  }


  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[1]++;
  }

  private static final String OPTIONAL_ARG_PREFIX = "opt_";
  static {
    CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[2]++;
  }

  private static final String VAR_ARGS_NAME = "var_args";
  static {
    CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[3]++;
  }

  private static final Pattern ENUM_KEY_PATTERN =
    Pattern.compile("[A-Z0-9][A-Z0-9_]*");
  static {
    CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[4]++;
  }

  /** By default, decorate the ClosureCodingConvention. */
  public GoogleCodingConvention() {
    this(new ClosureCodingConvention());
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[5]++;
  }

  /** Decorates a wrapped CodingConvention. */
  public GoogleCodingConvention(CodingConvention convention) {
    super(convention);
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[6]++;
  }

  /**
   * {@inheritDoc}
   *
   * <p>This enforces the Google const name convention, that the first character
   * after the last $ must be an upper-case letter and all subsequent letters
   * must be upper case. The name must be at least 2 characters long.
   *
   * <p>Examples:
   * <pre>
   *      aaa          Not constant - lower-case letters in the name
   *      A            Not constant - too short
   *      goog$A       Constant - letters after the $ are upper-case.
   *      AA17         Constant - digits can appear after the first letter
   *      goog$7A      Not constant - first character after the $ must be
   *                   upper case.
   *      $A           Constant - doesn't have to be anything in front of the $
   * </pre>
   */
  @Override
  public boolean isConstant(String name) {
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((name.length() <= 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[2]++;}
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[8]++;

    // In compiled code, '$' is often a namespace delimiter. To allow inlining
    // of namespaced constants, we strip off any namespaces here.
    int pos = name.lastIndexOf('$');
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((pos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[3]++;
      name = name.substring(pos + 1);
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[10]++;
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((name.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[5]++;
        return false;

      } else {
  CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[4]++;}

    return isConstantKey(name);
  }

  @Override
  public boolean isConstantKey(String name) {
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Character.isUpperCase(name.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[7]++;
      return false;

    } else {
  CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9.branches[8]++;}

    // hack way of checking that there aren't any lower-case letters
    return name.toUpperCase().equals(name);
  }

  /**
   * {@inheritDoc}
   *
   * <p>This enforces Google's convention about enum key names. They must match
   * the regular expression {@code [A-Z0-9][A-Z0-9_]*}.
   *
   * <p>Examples:
   * <ul>
   * <li>A</li>
   * <li>213</li>
   * <li>FOO_BAR</li>
   * </ul>
   */
  @Override
  public boolean isValidEnumKey(String key) {
    return ENUM_KEY_PATTERN.matcher(key).matches();
  }

  /**
   * {@inheritDoc}
   *
   * <p>In Google code, parameter names beginning with {@code opt_} are
   * treated as optional arguments.
   */
  @Override
  public boolean isOptionalParameter(Node parameter) {
    return parameter.getString().startsWith(OPTIONAL_ARG_PREFIX);
  }

  @Override
  public boolean isVarArgsParameter(Node parameter) {
    return VAR_ARGS_NAME.equals(parameter.getString());
  }

  /**
   * {@inheritDoc}
   *
   * <p>In Google code, any global name starting with an underscore is
   * considered exported.
   */
  @Override
  public boolean isExported(String name, boolean local) {
    return super.isExported(name, local) ||
        (!local && name.startsWith("_"));
  }

  /**
   * {@inheritDoc}
   *
   * <p>In Google code, private names end with an underscore, and exported
   * names are never considered private (see {@link #isExported}).
   */
  @Override
  public boolean isPrivate(String name) {
    return name.endsWith("_") && !isExported(name);
  }
}

class CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9 ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GoogleCodingConvention.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2};
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

  public CodeCoverCoverageCounter$4kgg1moklr5dlfytg3zuh53tn8v5iqamrxm0qz7fq9 () {
    super("com.google.javascript.jscomp.GoogleCodingConvention.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.GoogleCodingConvention.java");
      for (int i = 1; i <= 12; i++) {
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

