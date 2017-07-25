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

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * All the strings used by the webservice protocol.
 *
 */
public class Protocol {
  static {
    CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.ping();
  }


  private Protocol() {}

  /**
   * All enums that need to be shared between the Java and JS code should
   * implement this interface.
   */
  public static interface ProtocolEnum {
    /**
     * @return A string representing the key or value specified by the
     * protocol.
     */
    public String getValue();
  }

  /**
   * All the keys that can be part of the http request.
   */
  public static enum RequestKey implements ProtocolEnum {
    CODE_URL("code_url"),
    JS_CODE("js_code"),
    EXCLUDE_DEFAULT_EXTERNS("exclude_default_externs"),
    EXTERNS_URL("externs_url"),
    EXTERNS_CODE("js_externs"),
    COMPILATION_LEVEL("compilation_level"),
    OUTPUT_FORMAT("output_format"),
    OUTPUT_INFO("output_info"),
    OUTPUT_FILE_NAME("output_file_name"),
    OUTPUT_WRAPPER("output_wrapper"),
    API_KEY("api_key"),
    FORMATTING("formatting"),
    WARNING_LEVEL("warning_level"),
    USER_ID("uid"),
    USE_CLOSURE("use_closure_library"),
    BUILD_DEBUG("debug"),
    CHARSET("charset"),
    LANGUAGE("language"),
    USE_TYPES_FOR_OPTIMIZATIONS("use_types_for_optimization"),

    // Old ROBOCOMP urls.
    RAWJS("rawjs"),
    BASE("base"),
    MODE("mode"),
    SCRIPT("script"),
    NOCACHE("nocache") // Ignored.
    ;

    private static final Set<String> permittedKeys = getPermittedKeys();

    private static Set<String> getPermittedKeys() {
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[1]++;
      Set<String> keys = Sets.newHashSet();
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[2]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.loops[1]++;



      for (RequestKey key : RequestKey.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.loops[1]--;
  CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.loops[2]--;
  CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.loops[3]++;
}
        keys.add(key.asGetParameter());
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[3]++;
      }
      return keys;
    }

    private final String asGetParameter;

    private RequestKey(String asGetParameter) {
      this.asGetParameter = asGetParameter;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[4]++;
    }

    public String asGetParameter() {
      return asGetParameter;
    }

    @Override
    public String toString() {
      return asGetParameter;
    }

    public static boolean isKeyValid(String key) {
      return permittedKeys.contains(key.toLowerCase());
    }

    @Override
    public String getValue() {
      return asGetParameter;
    }
  }

  /**
   * All the possible values for the OUTPUT_INFO key.
   */
  public static enum OutputInfoKey implements ProtocolEnum {
    VARIABLE_MAP("variable_map"),
    COMPILED_CODE("compiled_code"),
    WARNINGS("warnings"),
    ERRORS("errors"),
    STATISTICS("statistics"),
    ;

    private final String value;

    private OutputInfoKey(String value) {
      this.value = value;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[5]++;
    }

    @Override
    public String getValue() {
      return value;
    }
  }

  /**
   * All the possible values for the FORMATTING key.
   */
  public static enum FormattingKey implements ProtocolEnum {
    PRETTY_PRINT("pretty_print"),
    PRINT_INPUT_DELIMITER("print_input_delimiter"),
    ;

    private final String value;

    private FormattingKey(String value) {
      this.value = value;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[6]++;
    }

    @Override
    public String getValue() {
      return value;
    }
  }

  public static enum CompilationLevelKey implements ProtocolEnum {
    WHITESPACE_ONLY("whitespace_only"),
    SIMPLE_OPTIMIZATIONS("simple_optimizations"),
    ADVANCED_OPTIMIZATIONS("advanced_optimizations"),
    ;

    private final String value;

    CompilationLevelKey(String value) {
      this.value = value;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[7]++;
    }

    @Override
    public String getValue() {
      return value;
    }
  }

  public static enum WarningLevelKey implements ProtocolEnum {
    QUIET("quiet"),
    DEFAULT("default"),
    VERBOSE("verbose"),
    ;

    private final String value;

    private WarningLevelKey(String value) {
      this.value = value;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[8]++;
    }

    @Override
    public String getValue() {
      return value;
    }
  }

  public static enum OutputFormatKey implements ProtocolEnum {
    TEXT("text"),
    XML("xml"),
    JSON("json"),
    ;

    private final String value;

    private OutputFormatKey(String value) {
      this.value = value;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[9]++;
    }

    @Override
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return getValue();
    }
  }

  /**
   * Fields in the JSON response from the ApiKeyGenerationServlet.
   */
  public static enum ApiKeyResponse implements ProtocolEnum {
    API_KEY("api_key"),
    ;

    private final String responseParam;

    ApiKeyResponse(String responseParam) {
      this.responseParam = responseParam;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[10]++;
    }

    /**
     * Name of the key as it appears in the JSON.
     */
    public String getResponseParam() {
      return responseParam;
    }

    @Override
    public String toString() {
      return getResponseParam();
    }

    @Override
    public String getValue() {
      return getResponseParam();
    }
  }

  /**
   * All the xml/json tags that can be returned by the backend if xml or json is
   * selected as the output mode.
   */
  public static enum ResponseTag implements ProtocolEnum {
    ROOT_TAG("compilationResult"),
    COMPILED_CODE_TAG("compiledCode"),
    WARNINGS_TAG("warnings"),
    WARNING_TAG("warning"),
    ERRORS_TAG("errors"),
    ERROR_TAG("error"),
    ERROR_LINE_NO_ATTR("lineno"),
    ERROR_LINE_ATTR("line"),
    // Charno is negative if error occurred outside range of columns that
    // JSCompiler records.  Change the sign of the value to find the
    // maximum column represented.
    // Note that JSCompiler uses -1 as an "I don't know" state, and it can
    // also turn up occasionally.
    ERROR_CHAR_ATTR("charno"),
    ERROR_FILE_ATTR("file"),
    ERROR_TYPE_ATTR("type"),
    STATS_TAG("statistics"),
    ORIGINAL_SIZE_TAG("originalSize"),
    ORIGINAL_GZIP_SIZE_TAG("originalGzipSize"),
    COMPRESSED_SIZE_TAG("compressedSize"),
    COMPRESSED_GZIP_SIZE_TAG("compressedGzipSize"),
    COMPILE_TIME_TAG("compileTime"),
    SERVER_ERRORS_TAG("serverErrors"),
    SERVER_ERROR_TAG("error"),
    SERVER_ERROR_CODE_ATTR("code"),
    VARIABLE_MAP("variableMap"),
    VARIABLE_MAP_ENTRY("variableMapEntry"),
    ORIGINAL_NAME_ATTR("originalName"),
    NEW_NAME_ATTR("newName"),
    OUTPUT_FILE_PATH("outputFilePath"),
    ;

    private final String responseTag;

    private ResponseTag(String responseTag) {
      this.responseTag = responseTag;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[11]++;
    }

    public String getResponseTag() {
      return responseTag;
    }

    @Override
    public String toString() {
      return getResponseTag();
    }

    @Override
    public String getValue() {
      return getResponseTag();
    }
  }

  /**
   * Properties key for getting the maximum input file size that may be
   * compiled by the service.  This is parameterized so we can have different
   * values for inside and outside Google.
   * The value should be a string representation of an integer representing
   * the maximum input size in bytes.
   */
  public static final String MAX_INPUT_SIZE_KEY =
      "com.google.javascript.jscomp.webservice.maximumInputSize";
  static {
    CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[12]++;
  }

  /**
   * Fallback value in case no setting is provided.
   */
  public static final int FALLBACK_MAX_INPUT_SIZE =
      500 * 1024;
  static {
    CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[13]++;
  }

  /**
   * Hard limit on input size set at execution time from the MAX_INPUT_SIZE_KEY
   * property.
   */
  private static int maxInputSize;

  /**
   * Initialize maxInputSize to the value from the MAX_INPUT_SIZE_KEY property
   * at startup.
   */
  static {
    resetMaximumInputSize();
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[14]++;
  }

  /**
   * Find the maximum input size that this configuration of the web service
   * allows.
   * @return maximum input size permitted (in bytes)
   */
  public static final int maximumInputSize() {
    // Limit the number of files downloaded if they are too big to compile.
    return maxInputSize;
  }

  /**
   * Reset the maximum input size so that the property key is rechecked.
   * This is needed for testing code because we are caching the maximum
   * input size value.
   */
  public static final void resetMaximumInputSize() {
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[15]++;
    String maxInputSizeStr = System.getProperty(Protocol.MAX_INPUT_SIZE_KEY);
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((maxInputSizeStr == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.branches[1]++;
      maxInputSize = Protocol.FALLBACK_MAX_INPUT_SIZE;
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[17]++;

    } else {
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.branches[2]++;
      maxInputSize = Integer.parseInt(maxInputSizeStr);
CodeCoverCoverageCounter$h5xqw71kj31q7s610orl.statements[18]++;
    }
  }
}

class CodeCoverCoverageCounter$h5xqw71kj31q7s610orl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$h5xqw71kj31q7s610orl ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.webservice.common.Protocol.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$h5xqw71kj31q7s610orl () {
    super("com.google.javascript.jscomp.webservice.common.Protocol.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.webservice.common.Protocol.java");
      for (int i = 1; i <= 18; i++) {
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

