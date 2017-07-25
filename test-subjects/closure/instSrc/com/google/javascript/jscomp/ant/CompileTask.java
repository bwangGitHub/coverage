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

package com.google.javascript.jscomp.ant;

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.DiagnosticGroup;
import com.google.javascript.jscomp.DiagnosticGroups;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.MessageFormatter;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.WarningLevel;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.Path;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * This class implements a simple Ant task to do almost the same as
 * CommandLineRunner.
 *
 * Most of the public methods of this class are entry points for the
 * Ant code to hook into.
 *
 */
public final class CompileTask
    extends Task {
  static {
    CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.ping();
  }

  private CompilerOptions.LanguageMode languageIn;
  private WarningLevel warningLevel;
  private boolean debugOptions;
  private String encoding = "UTF-8";
  {
    CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[1]++;
  }
  private String outputEncoding = "UTF-8";
  {
    CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[2]++;
  }
  private CompilationLevel compilationLevel;
  private boolean customExternsOnly;
  private boolean manageDependencies;
  private boolean prettyPrint;
  private boolean printInputDelimiter;
  private boolean generateExports;
  private boolean replaceProperties;
  private boolean forceRecompile;
  private String replacePropertiesPrefix;
  private File outputFile;
  private final List<Parameter> defineParams;
  private final List<FileList> externFileLists;
  private final List<FileList> sourceFileLists;
  private final List<Path> sourcePaths;
  private final List<Warning> warnings;

  public CompileTask() {
    this.languageIn = CompilerOptions.LanguageMode.ECMASCRIPT3;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[3]++;
    this.warningLevel = WarningLevel.DEFAULT;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[4]++;
    this.debugOptions = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[5]++;
    this.compilationLevel = CompilationLevel.SIMPLE_OPTIMIZATIONS;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[6]++;
    this.customExternsOnly = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[7]++;
    this.manageDependencies = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[8]++;
    this.prettyPrint = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[9]++;
    this.printInputDelimiter = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[10]++;
    this.generateExports = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[11]++;
    this.replaceProperties = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[12]++;
    this.forceRecompile = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[13]++;
    this.replacePropertiesPrefix = "closure.define.";
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[14]++;
    this.defineParams = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[15]++;
    this.externFileLists = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[16]++;
    this.sourceFileLists = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[17]++;
    this.sourcePaths = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[18]++;
    this.warnings = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[19]++;
  }

  /**
   * Set the language to which input sources conform.
   * @param value The name of the language.
   *     (ECMASCRIPT3, ECMASCRIPT5, ECMASCRIPT5_STRICT).
   */
  public void setLanguageIn(String value) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[20]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((value.equals("ECMASCRIPT5_STRICT")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((value.equals("ES5_STRICT")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[1]++;
      this.languageIn = CompilerOptions.LanguageMode.ECMASCRIPT5_STRICT;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[21]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[2]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[22]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((value.equals("ECMASCRIPT5")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value.equals("ES5")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[3]++;
      this.languageIn = CompilerOptions.LanguageMode.ECMASCRIPT5;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[23]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[4]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[24]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((value.equals("ECMASCRIPT3")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value.equals("ES3")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[5]++;
      this.languageIn = CompilerOptions.LanguageMode.ECMASCRIPT3;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[25]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[6]++;
      throw new BuildException(
          "Unrecognized 'languageIn' option value (" + value + ")");
    }
}
}
  }

  /**
   * Set the warning level.
   * @param value The warning level by string name. (default, quiet, verbose).
   */
  public void setWarning(String value) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 (("default".equalsIgnoreCase(value)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[7]++;
      this.warningLevel = WarningLevel.DEFAULT;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[27]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[8]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[28]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 (("quiet".equalsIgnoreCase(value)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[9]++;
      this.warningLevel = WarningLevel.QUIET;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[29]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[10]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[30]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (("verbose".equalsIgnoreCase(value)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[11]++;
      this.warningLevel = WarningLevel.VERBOSE;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[31]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[12]++;
      throw new BuildException(
          "Unrecognized 'warning' option value (" + value + ")");
    }
}
}
  }

  /**
   * Enable debugging options.
   * @param value True if debug mode is enabled.
   */
  public void setDebug(boolean value) {
    this.debugOptions = value;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[32]++;
  }

  /**
   * Set the compilation level.
   * @param value The optimization level by string name.
   *     (whitespace, simple, advanced).
   */
  public void setCompilationLevel(String value) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 (("simple".equalsIgnoreCase(value)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[13]++;
      this.compilationLevel = CompilationLevel.SIMPLE_OPTIMIZATIONS;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[34]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[14]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[35]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 (("advanced".equalsIgnoreCase(value)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[15]++;
      this.compilationLevel = CompilationLevel.ADVANCED_OPTIMIZATIONS;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[36]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[16]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[37]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 (("whitespace".equalsIgnoreCase(value)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[17]++;
      this.compilationLevel = CompilationLevel.WHITESPACE_ONLY;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[38]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[18]++;
      throw new BuildException(
          "Unrecognized 'compilation' option value (" + value + ")");
    }
}
}
  }

  public void setManageDependencies(boolean value) {
    this.manageDependencies = value;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[39]++;
  }

  /**
   * Use only custom externs.
   */
  public void setCustomExternsOnly(boolean value) {
    this.customExternsOnly = value;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[40]++;
  }

  /**
   * Set output file.
   */
  public void setOutput(File value) {
    this.outputFile = value;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[41]++;
  }

  /**
   * Set the replacement property prefix.
   */
  public void setReplacePropertiesPrefix(String value) {
    this.replacePropertiesPrefix = value;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[42]++;
  }

  /**
   * Whether to replace {@code @define} lines with properties
   */
  public void setReplaceProperties(boolean value) {
    this.replaceProperties = value;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[43]++;
  }

  /**
   * Set input file encoding
   */
  public void setEncoding(String encoding) {
    this.encoding = encoding;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[44]++;
  }

  /**
   * Set output file encoding
   */
  public void setOutputEncoding(String outputEncoding) {
    this.outputEncoding = outputEncoding;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[45]++;
  }

  /**
   * Set pretty print formatting option
   */
  public void setPrettyPrint(boolean pretty) {
    this.prettyPrint = pretty;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[46]++;
  }

  /**
   * Set print input delimiter formatting option
   */
  public void setPrintInputDelimiter(boolean print) {
    this.printInputDelimiter = print;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[47]++;
  }

  /**
   * Set force recompile option
   */
  public void setForceRecompile(boolean forceRecompile) {
    this.forceRecompile = forceRecompile;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[48]++;
  }

  /**
   * Set generateExports option
   */
  public void setGenerateExports(boolean generateExports) {
   this.generateExports = generateExports;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[49]++;
  }

  /**
   * Sets the externs file.
   */
  public void addExterns(FileList list) {
    this.externFileLists.add(list);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[50]++;
  }

  /**
   * Adds a <warning/> entry
   *
   * Each warning entry must have two attributes, group and level. Group must
   * contain one of the constants from DiagnosticGroups (e.g.,
   * "ACCESS_CONTROLS"), while level must contain one of the CheckLevel
   * constants ("ERROR", "WARNING" or "OFF").
   */
  public void addWarning(Warning warning) {
    this.warnings.add(warning);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[51]++;
  }

  /**
   * Sets the source files.
   */
  public void addSources(FileList list) {
    this.sourceFileLists.add(list);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[52]++;
  }

  /**
   * Adds a <path/> entry.
   */
  public void addPath(Path list) {
    this.sourcePaths.add(list);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[53]++;
  }

  @Override
  public void execute() {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.outputFile == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[19]++;
      throw new BuildException("outputFile attribute must be set");

    } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[20]++;}

    Compiler.setLoggingLevel(Level.OFF);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[55]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[56]++;

    CompilerOptions options = createCompilerOptions();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[57]++;
    Compiler compiler = createCompiler(options);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[58]++;

    List<SourceFile> externs = findExternFiles();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[59]++;
    List<SourceFile> sources = findSourceFiles();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[60]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((isStale()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((forceRecompile) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[21]++;
      log("Compiling " + sources.size() + " file(s) with " +
          externs.size() + " extern(s)");
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[61]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[62]++;

      Result result = compiler.compile(externs, sources, options);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[63]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((result.success) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[23]++;
        writeResult(compiler.toSource());
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[64]++;

      } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[24]++;
        throw new BuildException("Compilation failed.");
      }

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[22]++;
      log("None of the files changed. Compilation skipped.");
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[65]++;
    }
  }

  private CompilerOptions createCompilerOptions() {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[66]++;
    CompilerOptions options = new CompilerOptions();

    this.compilationLevel.setOptionsForCompilationLevel(options);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[67]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[68]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.debugOptions) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[25]++;
      this.compilationLevel.setDebugOptionsForCompilationLevel(options);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[69]++;

    } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[26]++;}

    options.prettyPrint = this.prettyPrint;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[70]++;
    options.printInputDelimiter = this.printInputDelimiter;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[71]++;
    options.generateExports = this.generateExports;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[72]++;

    options.setLanguageIn(this.languageIn);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[73]++;

    this.warningLevel.setOptionsForWarningLevel(options);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[74]++;
    options.setManageClosureDependencies(manageDependencies);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[75]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[76]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((replaceProperties) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[27]++;
      convertPropertiesMap(options);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[77]++;

    } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[28]++;}

    convertDefineParameters(options);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[78]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[79]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[1]++;



    for (Warning warning : warnings) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[1]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[2]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[3]++;
}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[80]++;
      CheckLevel level = warning.getLevel();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[81]++;
      String groupName = warning.getGroup();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[82]++;
      DiagnosticGroup group = new DiagnosticGroups().forName(groupName);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[83]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((group == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[29]++;
        throw new BuildException(
            "Unrecognized 'warning' option value (" + groupName + ")");

      } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[30]++;}
      options.setWarningLevel(group, level);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[84]++;
    }

    return options;
  }

  /**
   * Creates a new {@code <define/>} nested element. Supports name and value
   * attributes.
   */
  public Parameter createDefine() {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[85]++;
    Parameter param = new Parameter();
    defineParams.add(param);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[86]++;
    return param;
  }

  /**
   * Converts {@code <define/>} nested elements into Compiler {@code @define}
   * replacements. Note: unlike project properties, {@code <define/>} elements
   * do not need to be named starting with the replacement prefix.
   */
  private void convertDefineParameters(CompilerOptions options) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[87]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[4]++;


    for (Parameter p : defineParams) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[4]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[5]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[6]++;
}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[88]++;
      String key = p.getName();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[89]++;
      Object value = p.getValue();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[90]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((setDefine(options, key, value)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[31]++;
        log("Unexpected @define value for name=" + key + "; value=" + value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[91]++;

      } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[32]++;}
    }
  }

  /**
   * Converts project properties beginning with the replacement prefix
   * into Compiler {@code @define} replacements.
   *
   * @param options
   */
  private void convertPropertiesMap(CompilerOptions options) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[92]++;
    @SuppressWarnings("unchecked")
    Map<String, Object> props = getProject().getProperties();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[93]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[7]++;


    for (Map.Entry<String, Object> entry : props.entrySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[7]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[8]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[9]++;
}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[94]++;
      String key = entry.getKey();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[95]++;
      Object value = entry.getValue();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[96]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((key.startsWith(replacePropertiesPrefix)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[33]++;
        key = key.substring(replacePropertiesPrefix.length());
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[97]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[98]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((setDefine(options, key, value)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[35]++;
          log("Unexpected property value for key=" + key + "; value=" + value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[99]++;

        } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[36]++;}

      } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[34]++;}
    }
  }

  /**
   * Maps Ant-style values (e.g., from Properties) into expected
   * Closure {@code @define} literals
   *
   * @return True if the {@code @define} replacement succeeded, false if
   *         the variable's value could not be mapped properly.
   */
  private boolean setDefine(CompilerOptions options,
      String key, Object value) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[100]++;
    boolean success = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[101]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[37]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[102]++;
      final boolean isTrue = "true".equals(value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[103]++;
      final boolean isFalse = "false".equals(value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[104]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((isTrue) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isFalse) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[39]++;
        options.setDefineToBooleanLiteral(key, isTrue);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[105]++;

      } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[40]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[106]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[107]++;
          double dblTemp = Double.parseDouble((String) value);
          options.setDefineToDoubleLiteral(key, dblTemp);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[108]++;
        } catch (NumberFormatException nfe) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[42]++;
          // Not a number, assume string
          options.setDefineToStringLiteral(key, (String) value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[109]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[41]++;
}
  }
      }

      success = true;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[110]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[38]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[111]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[43]++;
      options.setDefineToBooleanLiteral(key, (Boolean) value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[112]++;
      success = true;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[113]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[44]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[114]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((value instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[45]++;
      options.setDefineToNumberLiteral(key, (Integer) value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[115]++;
      success = true;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[116]++;

    } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[46]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[117]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((value instanceof Double) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[47]++;
      options.setDefineToDoubleLiteral(key, (Double) value);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[118]++;
      success = true;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[119]++;

    } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[48]++;}
}
}
}

    return success;
  }

  private Compiler createCompiler(CompilerOptions options) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[120]++;
    Compiler compiler = new Compiler();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[121]++;
    MessageFormatter formatter =
        options.errorFormat.toFormatter(compiler, false);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[122]++;
    AntErrorManager errorManager = new AntErrorManager(formatter, this);
    compiler.setErrorManager(errorManager);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[123]++;
    return compiler;
  }

  private List<SourceFile> findExternFiles() {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[124]++;
    List<SourceFile> files = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[125]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.customExternsOnly) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[49]++;
      files.addAll(getDefaultExterns());
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[126]++;

    } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[50]++;}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[127]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[10]++;



    for (FileList list : this.externFileLists) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[10]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[11]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[12]++;
}
      files.addAll(findJavaScriptFiles(list));
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[128]++;
    }

    return files;
  }

  private List<SourceFile> findSourceFiles() {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[129]++;
    List<SourceFile> files = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[130]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[13]++;



    for (FileList list : this.sourceFileLists) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[13]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[14]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[15]++;
}
      files.addAll(findJavaScriptFiles(list));
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[131]++;
    }
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[132]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[16]++;



    for (Path list : this.sourcePaths) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[16]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[17]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[18]++;
}
      files.addAll(findJavaScriptFiles(list));
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[133]++;
    }

    return files;
  }

  /**
   * Translates an Ant file list into the file format that the compiler
   * expects.
   */
  private List<SourceFile> findJavaScriptFiles(FileList fileList) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[134]++;
    List<SourceFile> files = Lists.newLinkedList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[135]++;
    File baseDir = fileList.getDir(getProject());
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[136]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[19]++;



    for (String included : fileList.getFiles(getProject())) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[19]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[20]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[21]++;
}
      files.add(SourceFile.fromFile(new File(baseDir, included),
          Charset.forName(encoding)));
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[137]++;
    }

    return files;
  }

  /**
   * Translates an Ant Path into the file list format that the compiler
   * expects.
   */
  private List<SourceFile> findJavaScriptFiles(Path path) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[138]++;
    List<SourceFile> files = Lists.newArrayList();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[139]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[22]++;



    for (String included : path.list()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[22]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[23]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[24]++;
}
      files.add(SourceFile.fromFile(new File(included),
          Charset.forName(encoding)));
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[140]++;
    }

    return files;
  }

  /**
   * Gets the default externs set.
   *
   * Adapted from {@link CommandLineRunner}.
   */
  private List<SourceFile> getDefaultExterns() {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[141]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
      return CommandLineRunner.getDefaultExterns();
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[52]++;
      throw new BuildException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[51]++;
}
  }
  }

  private void writeResult(String source) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[142]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.outputFile.getParentFile().mkdirs()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[53]++;
      log("Created missing parent directory " +
          this.outputFile.getParentFile(), Project.MSG_DEBUG);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[143]++;

    } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[54]++;}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[144]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

    try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[145]++;
      OutputStreamWriter out = new OutputStreamWriter(
          new FileOutputStream(this.outputFile), outputEncoding);
      out.append(source);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[146]++;
      out.flush();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[147]++;
      out.close();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[148]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[56]++;
      throw new BuildException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[55]++;
}
  }

    log("Compiled JavaScript written to " + this.outputFile.getAbsolutePath(),
        Project.MSG_DEBUG);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[149]++;
  }

  /**
   * Determine if compilation must actually happen, i.e. if any input file
   * (extern or source) has changed after the outputFile was last modified.
   *
   * @return true if compilation should happen
   */
  private boolean isStale() {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[150]++;
    long lastRun = outputFile.lastModified();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[151]++;
    long sourcesLastModified = Math.max(
        getLastModifiedTime(this.sourceFileLists),
        getLastModifiedTime(this.sourcePaths));
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[152]++;
    long externsLastModified = getLastModifiedTime(this.externFileLists);

    return lastRun <= sourcesLastModified || lastRun <= externsLastModified;
  }

  /**
   * Returns the most recent modified timestamp of the file collection.
   *
   * Note: this must be combined into one method to account for both
   * Path and FileList erasure types.
   *
   * @param fileLists Collection of FileList or Path
   * @return Most recent modified timestamp
   */
  private long getLastModifiedTime(List<?> fileLists) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[153]++;
    long lastModified = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[154]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[25]++;



    for (Object entry : fileLists) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[25]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[26]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[27]++;
}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[155]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((entry instanceof FileList) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[57]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[156]++;
        FileList list = (FileList) entry;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[157]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[28]++;



        for (String fileName : list.getFiles(this.getProject())) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[28]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[29]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[30]++;
}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[158]++;
          File path = list.getDir(this.getProject());
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[159]++;
          File file = new File(path, fileName);
          lastModified = Math.max(getLastModifiedTime(file), lastModified);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[160]++;
        }

      } else {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[58]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[161]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((entry instanceof Path) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[59]++;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[162]++;
        Path path = (Path) entry;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[163]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[31]++;


        for (String src : path.list()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[31]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[32]--;
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.loops[33]++;
}
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[164]++;
          File file = new File(src);
          lastModified = Math.max(getLastModifiedTime(file), lastModified);
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[165]++;
        }

      } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[60]++;}
}
    }

    return lastModified;
  }

  /**
   * Returns the last modified timestamp of the given File.
   */
  private long getLastModifiedTime(File file) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[166]++;
    long fileLastModified = file.lastModified();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[167]++;
int CodeCoverConditionCoverageHelper_C28;
    // If the file is absent, we don't know if it changed (maybe was deleted),
    // so assume it has just changed.
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((fileLastModified == 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[61]++;
      fileLastModified = new Date().getTime();
CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.statements[168]++;

    } else {
  CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh.branches[62]++;}
    return fileLastModified;
  }
}

class CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh ());
  }
    public static long[] statements = new long[169];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ant.CompileTask.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 28; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$3zq26pu1uoyrswmvngdxcs1gh () {
    super("com.google.javascript.jscomp.ant.CompileTask.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 168; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ant.CompileTask.java");
      for (int i = 1; i <= 168; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 11; i++) {
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

