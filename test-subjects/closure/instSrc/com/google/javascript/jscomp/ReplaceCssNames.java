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

import static com.google.javascript.rhino.jstype.JSTypeNative.STRING_TYPE;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.JSType;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * ReplaceCssNames replaces occurrences of goog.getCssName('foo') with
 * a shorter version from the passed in renaming map. There are two
 * styles of operation: for 'BY_WHOLE' we look up the whole string in the
 * renaming map. For 'BY_PART', all the class name's components,
 * separated by '-', are renamed individually and then recombined.
 *
 * Given the renaming map:
 *   {
 *     once:  'a',
 *     upon:  'b',
 *     atime: 'c',
 *     long:  'd',
 *     time:  'e',
 *     ago:   'f'
 *   }
 *
 * The following outputs are expected with the 'BY_PART' renaming style:
 *
 * goog.getCssName('once') -> 'a'
 * goog.getCssName('once-upon-atime') -> 'a-b-c'
 *
 * var baseClass = goog.getCssName('long-time');
 * el.className = goog.getCssName(baseClass, 'ago');
 * ->
 * var baseClass = 'd-e';
 * el.className = baseClass + '-f';
 *
 * However if we have the following renaming map with the 'BY_WHOLE' renaming style:
 *   {
 *     once: 'a',
 *     upon-atime: 'b',
 *     long-time: 'c',
 *     ago: 'd'
 *   }
 *
 * Then we would expect:
 *
 * goog.getCssName('once') -> 'a'
 *
 * var baseClass = goog.getCssName('long-time');
 * el.className = goog.getCssName(baseClass, 'ago');
 * ->
 * var baseClass = 'c';
 * el.className = baseClass + '-d';
 *
 * In addition, the CSS names before replacement can optionally be gathered.
 *
 */
class ReplaceCssNames implements CompilerPass {
  static {
    CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.ping();
  }


  static final String GET_CSS_NAME_FUNCTION = "goog.getCssName";
  static {
    CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[1]++;
  }

  static final DiagnosticType INVALID_NUM_ARGUMENTS_ERROR =
      DiagnosticType.error("JSC_GETCSSNAME_NUM_ARGS",
          "goog.getCssName called with \"{0}\" arguments, expected 1 or 2.");
  static {
    CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[2]++;
  }

  static final DiagnosticType STRING_LITERAL_EXPECTED_ERROR =
      DiagnosticType.error("JSC_GETCSSNAME_STRING_LITERAL_EXPECTED",
          "goog.getCssName called with invalid argument, string literal " +
          "expected.  Was \"{0}\".");
  static {
    CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[3]++;
  }

  static final DiagnosticType UNEXPECTED_STRING_LITERAL_ERROR =
    DiagnosticType.error("JSC_GETCSSNAME_UNEXPECTED_STRING_LITERAL",
        "goog.getCssName called with invalid arguments, string literal " +
        "passed as first of two arguments.  Did you mean " +
        "goog.getCssName(\"{0}-{1}\")?");
  static {
    CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[4]++;
  }

  static final DiagnosticType UNKNOWN_SYMBOL_WARNING =
      DiagnosticType.warning("JSC_GETCSSNAME_UNKNOWN_CSS_SYMBOL",
         "goog.getCssName called with unrecognized symbol \"{0}\" in class " +
         "\"{1}\".");
  static {
    CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[5]++;
  }


  private final AbstractCompiler compiler;

  private final Map<String, Integer> cssNames;

  private CssRenamingMap symbolMap;

  private final Set<String> whitelist;

  private final JSType nativeStringType;

  ReplaceCssNames(AbstractCompiler compiler,
      @Nullable Map<String, Integer> cssNames,
      @Nullable Set<String> whitelist) {
    this.compiler = compiler;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[6]++;
    this.cssNames = cssNames;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[7]++;
    this.whitelist = whitelist;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[8]++;
    this.nativeStringType =  compiler.getTypeRegistry()
        .getNativeType(STRING_TYPE);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[9]++;
  }

  @Override
  public void process(Node externs, Node root) {
    // The CssRenamingMap may not have been available from the compiler when
    // this ReplaceCssNames pass was constructed, so getCssRenamingMap() should
    // only be called before this pass is actually run.
    symbolMap = getCssRenamingMap();
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[10]++;

    NodeTraversal.traverse(compiler, root, new Traversal());
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[11]++;
  }

  @VisibleForTesting
  protected CssRenamingMap getCssRenamingMap() {
    return compiler.getCssRenamingMap();
  }

  private class Traversal extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((GET_CSS_NAME_FUNCTION.equals(n.getFirstChild().getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[1]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[13]++;
        int count = n.getChildCount();
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[14]++;
        Node first = n.getFirstChild().getNext();
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[15]++;
        switch (count) {
          case 2:
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[3]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
            // Replace the function call with the processed argument.
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((first.isString()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[4]++;
              processStringNode(t, first);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[17]++;
              n.removeChild(first);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[18]++;
              parent.replaceChild(n, first);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[19]++;
              compiler.reportCodeChange();
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[20]++;

            } else {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[5]++;
              compiler.report(t.makeError(n, STRING_LITERAL_EXPECTED_ERROR,
                  Token.name(first.getType())));
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[21]++;
            }
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[22]++;
            break;

          case 3:
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[6]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[23]++;
            // Replace function call with concatenation of two args.  It's
            // assumed the first arg has already been processed.

            Node second = first.getNext();
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;

            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((second.isString()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[7]++;
              compiler.report(t.makeError(n, STRING_LITERAL_EXPECTED_ERROR,
                  Token.name(second.getType())));
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[25]++;

            } else {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[8]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[26]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((first.isString()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[9]++;
              compiler.report(t.makeError(
                  n, UNEXPECTED_STRING_LITERAL_ERROR,
                  first.getString(), second.getString()));
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[27]++;

            } else {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[10]++;
              processStringNode(t, second);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[28]++;
              n.removeChild(first);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[29]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[30]++;
              Node replacement = IR.add(first,
                  IR.string("-" + second.getString())
                      .copyInformationFrom(second))
                  .copyInformationFrom(n);
              replacement.setJSType(nativeStringType);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[31]++;
              parent.replaceChild(n, replacement);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[32]++;
              compiler.reportCodeChange();
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[33]++;
            }
}
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[34]++;
            break;

          default:
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[11]++;
            compiler.report(t.makeError(
                n, INVALID_NUM_ARGUMENTS_ERROR, String.valueOf(count)));
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[35]++;
        }

      } else {
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[2]++;}
    }

    /**
     * Processes a string argument to goog.getCssName().  The string will be
     * renamed based off the symbol map.  If there is no map or any part of the
     * name can't be renamed, a warning is reported to the compiler and the node
     * is left unchanged.
     *
     * If the type is unexpected then an error is reported to the compiler.
     *
     * @param t The node traversal.
     * @param n The string node to process.
     */
    private void processStringNode(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[36]++;
      String name = n.getString();
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((whitelist != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((whitelist.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[12]++;
        // We apply the whitelist before splitting on dashes, and not after.
        // External substitution maps should do the same.
        return;

      } else {
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[13]++;}
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[38]++;
      String[] parts = name.split("-");
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((symbolMap != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[14]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[40]++;
        String replacement = null;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[41]++;
        switch (symbolMap.getStyle()) {
          case BY_WHOLE:
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[16]++;
            replacement = symbolMap.get(name);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[42]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[43]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((replacement == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[17]++;
              compiler.report(
                  t.makeError(n, UNKNOWN_SYMBOL_WARNING, name, name));
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[44]++;
              return;

            } else {
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[18]++;}
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[45]++;
            break;
          case BY_PART:
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[19]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[46]++;
            String[] replaced = new String[parts.length];
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[47]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < parts.length) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[1]--;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[2]--;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[3]++;
}
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[48]++;
              String part = symbolMap.get(parts[i]);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
              if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((part == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[20]++;
                // If we can't encode all parts, don't encode any of it.
                compiler.report(
                    t.makeError(n, UNKNOWN_SYMBOL_WARNING, parts[i], name));
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[50]++;
                return;

              } else {
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[21]++;}
              replaced[i] = part;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[51]++;
            }
            replacement = Joiner.on("-").join(replaced);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[52]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[53]++;
            break;
          default:
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[22]++;
            throw new IllegalStateException(
              "Unknown replacement style: " + symbolMap.getStyle());
        }
        n.setString(replacement);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[54]++;

      } else {
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[15]++;}
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cssNames != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[23]++;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[56]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;
        // We still want to collect statistics even if we've already
        // done the full replace. The statistics are collected on a
        // per-part basis.
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < parts.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[4]--;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[5]--;
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.loops[6]++;
}
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[57]++;
          Integer count = cssNames.get(parts[i]);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[58]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((count == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[25]++;
            count = Integer.valueOf(0);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[59]++;

          } else {
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[26]++;}
          cssNames.put(parts[i], count.intValue() + 1);
CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.statements[60]++;
        }

      } else {
  CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75.branches[24]++;}
    }
  }

}

class CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75 ());
  }
    public static long[] statements = new long[61];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ReplaceCssNames.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$9mhqpbfe2bxxzxl6y8ewinfa6hhti75 () {
    super("com.google.javascript.jscomp.ReplaceCssNames.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 60; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ReplaceCssNames.java");
      for (int i = 1; i <= 60; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

