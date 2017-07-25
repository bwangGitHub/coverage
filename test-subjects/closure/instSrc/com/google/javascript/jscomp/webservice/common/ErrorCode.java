/*
 * Copyright 2009 The Closure Compiler Authors.
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

package com.google.javascript.jscomp.webservice.common;

/**
 * Enum of all the possible error described in the Web Service protocol.
 *
 */
public enum ErrorCode {
  UNKNOWN_OUTPUT_MODE(2),
  UNKNOWN_API_KEY(3),
  UNKNOWN_COMPILATION_LEVEL(4),
  UNKNOWN_CHARSET(5),
  POST_DATA_TOO_LARGE(8),
  FILE_TOO_LARGE(9),
  UNREACHABLE_URL(10),
  MALFORMED_URL(12),
  NO_OUTPUT_INFO(13),
  UNKNOWN_OUTPUT_INFO(14),
  MISSING_API_KEY(15),
  UNKNOWN_WARNING_LEVEL(16),
  UNKNOWN_FORMATTING_OPTION(17),
  UNKNOWN_PARAMETER(18),
  ILLEGAL_OUTPUT_FILE_NAME(19),
  HASH_MISMATCH(20),
  NO_CODE_FOUND_IN_CACHE(21),
  ACCOUNT_OVER_QUOTA(22),
  COMPILER_EXCEPTION(23),
  UNSUPPORTED_INPUT_RESOURCE_TYPE(24),
  DOWNLOAD_OVER_QUOTA(25),
  ;

  private final int code;
  ErrorCode(int code) {
    this.code = code;
CodeCoverCoverageCounter$2xddja4g097phzbgulr3a9.statements[1]++;
  }

  public int getCode() {
    return code;
  }
}

class CodeCoverCoverageCounter$2xddja4g097phzbgulr3a9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2xddja4g097phzbgulr3a9 ());
  }
    public static long[] statements = new long[2];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2xddja4g097phzbgulr3a9 () {
    super("com.google.javascript.jscomp.webservice.common.ErrorCode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.webservice.common.ErrorCode.java");
      for (int i = 1; i <= 1; i++) {
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

