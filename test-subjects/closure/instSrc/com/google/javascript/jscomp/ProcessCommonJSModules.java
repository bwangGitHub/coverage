/*
 * Copyright 2011 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Rewrites a CommonJS module http://wiki.commonjs.org/wiki/Modules/1.1.1
 * into a form that can be safely concatenated.
 * Does not add a function around the module body but instead adds suffixes
 * to global variables to avoid conflicts.
 * Calls to require are changed to reference the required module directly.
 * goog.provide and goog.require are emitted for closure compiler automatic
 * ordering.
 */
public class ProcessCommonJSModules implements CompilerPass {
  static {
    CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.ping();
  }

  // According to the spec, the forward slash should be the delimite on
  // all platforms.
  private static final String MODULE_SLASH = "/";
  static {
    CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[1]++;
  }

  public static final String DEFAULT_FILENAME_PREFIX = "." + MODULE_SLASH;
  static {
    CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[2]++;
  }

  private static final String MODULE_NAME_SEPARATOR = "\\$";
  static {
    CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[3]++;
  }
  private static final String MODULE_NAME_PREFIX = "module$";
  static {
    CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[4]++;
  }

  private final AbstractCompiler compiler;
  private final String filenamePrefix;
  private final boolean reportDependencies;
  private JSModule module;

  ProcessCommonJSModules(AbstractCompiler compiler, String filenamePrefix) {
    this(compiler, filenamePrefix, true);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[5]++;
  }

  ProcessCommonJSModules(AbstractCompiler compiler, String filenamePrefix,
      boolean reportDependencies) {
    this.compiler = compiler;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[6]++;
    this.filenamePrefix = filenamePrefix.endsWith(MODULE_SLASH) ?
        filenamePrefix : filenamePrefix + MODULE_SLASH;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[7]++;
    this.reportDependencies = reportDependencies;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[8]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal
        .traverse(compiler, root, new ProcessCommonJsModulesCallback());
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[9]++;
  }

  String guessCJSModuleName(String filename) {
    return toModuleName(normalizeSourceName(filename));
  }

  /**
   * For every file that is being processed this returns the module that
   * created for it.
   */
  JSModule getModule() {
    return module;
  }

  /**
   * Turns a filename into a JS identifier that is used for moduleNames in
   * rewritten code. Removes leading ./, replaces / with $, removes trailing .js
   * and replaces - with _. All moduleNames get a "module$" prefix.
   */
  public static String toModuleName(String filename) {
    return MODULE_NAME_PREFIX +
        filename.replaceAll("^\\." + Pattern.quote(MODULE_SLASH), "")
            .replaceAll(Pattern.quote(MODULE_SLASH), MODULE_NAME_SEPARATOR)
            .replaceAll("\\.js$", "").replaceAll("-", "_");
  }

  /**
   * Turn a filename into a moduleName with support for relative addressing
   * with ./ and ../ based on currentFilename;
   */
  public static String toModuleName(String requiredFilename,
      String currentFilename) {
    requiredFilename = requiredFilename.replaceAll("\\.js$", "");
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[10]++;
    currentFilename = currentFilename.replaceAll("\\.js$", "");
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[11]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((requiredFilename.startsWith("." + MODULE_SLASH)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((requiredFilename.startsWith(".." + MODULE_SLASH)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[1]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[13]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        requiredFilename = (new URI(currentFilename)).resolve(new URI(requiredFilename))
            .toString();
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[14]++;
      } catch (URISyntaxException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[4]++;
        throw new RuntimeException(e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[3]++;
}
  }

    } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[2]++;}
    return toModuleName(requiredFilename);
  }

  private String normalizeSourceName(String filename) {
    // The DOS command shell will normalize "/" to "\", so we have to
    // wrestle it back.
    filename = filename.replace("\\", "/");
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[15]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((filename.indexOf(filenamePrefix) == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[5]++;
      filename = filename.substring(filenamePrefix.length());
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[17]++;

    } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[6]++;}

    return filename;
  }

  /**
   * Visits require, every "script" and special module.exports assignments.
   */
  private class ProcessCommonJsModulesCallback extends
      AbstractPostOrderCallback {

    private int scriptNodeCount = 0;
  {
    CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[18]++;
  }
    private Set<String> modulesWithExports = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[19]++;
  }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (128)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((n.getChildCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 (("require".equals(n.getFirstChild().getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.getChildAtIndex(1).isString()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 4) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 4) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[7]++;
        visitRequireCall(t, n, parent);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[21]++;

      } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[8]++;}
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;

      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[9]++;
        scriptNodeCount++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[23]++;
        visitScript(t, n);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[24]++;

      } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[10]++;}
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 (("module.exports".equals(n.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[11]++;
        visitModuleExports(n);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[26]++;

      } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[12]++;}
    }

    /**
     * Visit require calls. Emit corresponding goog.require and rewrite require
     * to be a direct reference to name of require module.
     */
    private void visitRequireCall(NodeTraversal t, Node require, Node parent) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[27]++;
      String moduleName = toModuleName(require.getChildAtIndex(1).getString(),
          normalizeSourceName(t.getSourceName()));
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[28]++;
      Node moduleRef = IR.name(moduleName).srcref(require);
      parent.replaceChild(require, moduleRef);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[29]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[30]++;
      Node script = getCurrentScriptNode(parent);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((reportDependencies) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[13]++;
        t.getInput().addRequire(moduleName);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[32]++;

      } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[14]++;}
      // Rewrite require("name").
      script.addChildToFront(IR.exprResult(
          IR.call(IR.getprop(IR.name("goog"), IR.string("require")),
              IR.string(moduleName))).copyInformationFromForTree(require));
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[33]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[34]++;
    }

    /**
     * Emit goog.provide and add suffix to all global vars to avoid conflicts
     * with other modules.
     */
    private void visitScript(NodeTraversal t, Node script) {
      Preconditions.checkArgument(scriptNodeCount == 1,
          "ProcessCommonJSModules supports only one invocation per " +
          "CompilerInput / script node");
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[35]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[36]++;
      String moduleName = guessCJSModuleName(script.getSourceFileName());
      script.addChildToFront(IR.var(IR.name(moduleName), IR.objectlit())
          .copyInformationFromForTree(script));
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[37]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((reportDependencies) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[15]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[39]++;
        CompilerInput ci = t.getInput();
        ci.addProvide(moduleName);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[40]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[41]++;
        JSModule m = new JSModule(moduleName);
        m.addAndOverrideModule(ci);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[42]++;
        module = m;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[43]++;

      } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[16]++;}
      script.addChildToFront(IR.exprResult(
          IR.call(IR.getprop(IR.name("goog"), IR.string("provide")),
              IR.string(moduleName))).copyInformationFromForTree(script));
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[44]++;

      emitOptionalModuleExportsOverride(script, moduleName);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[45]++;

      // Rename vars to not conflict in global scope.
      NodeTraversal.traverse(compiler, script, new SuffixVarsCallback(
          moduleName));
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[46]++;

      compiler.reportCodeChange();
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[47]++;
    }

    /**
     * Emit <code>if (moduleName.module$exports) {
     *    moduleName = moduleName.module$export;
     * }</code> at end of file.
     */
    private void emitOptionalModuleExportsOverride(Node script,
        String moduleName) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((modulesWithExports.contains(moduleName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[17]++;
        return;

      } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[18]++;}
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[49]++;

      Node moduleExportsProp = IR.getprop(IR.name(moduleName),
          IR.string("module$exports"));
      script.addChildToBack(IR.ifNode(
          moduleExportsProp,
          IR.block(IR.exprResult(IR.assign(IR.name(moduleName),
              moduleExportsProp.cloneTree())))).copyInformationFromForTree(
          script));
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[50]++;
    }

    /**
     * Rewrite module.exports to moduleName.module$exports.
     */
    private void visitModuleExports(Node prop) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[51]++;
      String moduleName = guessCJSModuleName(prop.getSourceFileName());
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[52]++;
      Node module = prop.getChildAtIndex(0);
      module.putProp(Node.ORIGINALNAME_PROP, "module");
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[53]++;
      module.setString(moduleName);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[54]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[55]++;
      Node exports = prop.getChildAtIndex(1);
      exports.putProp(Node.ORIGINALNAME_PROP, "exports");
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[56]++;
      exports.setString("module$exports");
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[57]++;
      modulesWithExports.add(moduleName);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[58]++;
    }

    /**
     * Returns next script node in parents.
     */
    private Node getCurrentScriptNode(Node n) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[59]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.loops[1]++;


      while (true) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.loops[1]--;
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.loops[2]--;
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.loops[3]++;
}
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[60]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[19]++;
          return n;

        } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[20]++;}
        n = n.getParent();
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[61]++;
      }
    }
  }

  /**
   * Traverses a node tree and appends a suffix to all global variable names.
   */
  private class SuffixVarsCallback extends AbstractPostOrderCallback {

    private static final String EXPORTS = "exports";

    private final String suffix;

    SuffixVarsCallback(String suffix) {
      this.suffix = suffix;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[62]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[21]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[64]++;
        String name = n.getString();
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[65]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((suffix.equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[23]++;
          return;

        } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[24]++;}
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[66]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((EXPORTS.equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[25]++;
          n.setString(suffix);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[67]++;
          n.putProp(Node.ORIGINALNAME_PROP, EXPORTS);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[68]++;

        } else {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[26]++;
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[69]++;
          Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[70]++;
int CodeCoverConditionCoverageHelper_C14;
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((var.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[27]++;
            n.setString(name + "$$" + suffix);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[71]++;
            n.putProp(Node.ORIGINALNAME_PROP, name);
CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.statements[72]++;

          } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[28]++;}
        }

      } else {
  CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735.branches[22]++;}
    }
  }
}

class CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735 ());
  }
    public static long[] statements = new long[73];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ProcessCommonJSModules.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,3,1,2,1,1,1,0,1,1,1,1,2};
    for (int i = 1; i <= 14; i++) {
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

  public CodeCoverCoverageCounter$557bmg1bzayxpd321e8p8oaov8er80i9r1kg09m735 () {
    super("com.google.javascript.jscomp.ProcessCommonJSModules.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 72; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ProcessCommonJSModules.java");
      for (int i = 1; i <= 72; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 14; i++) {
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

