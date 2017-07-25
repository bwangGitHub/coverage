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

package com.google.javascript.jscomp;


import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * An abstract representation of a JavaScript source file, as input to
 * JSCompiler.
 *
 * @author nicksantos@google.com (Nick Santos)
 * @author moedinger@google.com (Andrew Moedinger)
 * @deprecated JSSourceFile is an empty wrapper around SourceFile. Just
 *     use SourceFile directly.
 */
// TODO(nicksantos): Delete this file.
@Deprecated
public class JSSourceFile extends SourceFile implements Serializable {
  static {
    CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd.statements[1]++;
  }

  public static JSSourceFile fromFile(String fileName, Charset charSet) {
    return new JSSourceFile(SourceFile.fromFile(fileName, charSet));
  }

  public static JSSourceFile fromFile(String fileName) {
    return new JSSourceFile(SourceFile.fromFile(fileName, Charsets.UTF_8));
  }

  public static JSSourceFile fromFile(File file, Charset charSet) {
    return new JSSourceFile(SourceFile.fromFile(file, charSet));
  }

  public static JSSourceFile fromFile(File file) {
    return new JSSourceFile(SourceFile.fromFile(file, Charsets.UTF_8));
  }

  public static JSSourceFile fromCode(String fileName, String code) {
    return new JSSourceFile(SourceFile.fromCode(fileName, code));
  }

  public static JSSourceFile fromInputStream(String fileName, InputStream s)
      throws IOException {
    return new JSSourceFile(SourceFile.fromInputStream(fileName, s));
  }

  public static JSSourceFile fromGenerator(String fileName,
      Generator generator) {
    return new JSSourceFile(SourceFile.fromGenerator(fileName, generator));
  }


  private SourceFile referenced;

  private JSSourceFile(SourceFile referenced) {
    super(referenced.getName());
CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd.statements[2]++;
    this.referenced = referenced;
CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd.statements[3]++;
  }

  @Override
  public String getCode() throws IOException {
    return referenced.getCode();
  }

  @Override
  public void clearCachedSource() {
    referenced.clearCachedSource();
CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd.statements[4]++;
  }

  @Override
  @VisibleForTesting
  String getCodeNoCache() {
    return referenced.getCodeNoCache();
  }
}

class CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$vagp0h8zh2fv57cvbrv0r865pd () {
    super("com.google.javascript.jscomp.JSSourceFile.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.JSSourceFile.java");
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

