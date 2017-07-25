/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.ast.*;

import java.util.List;
import java.util.ArrayList;

/**
 * This class rewrites the parse tree into an IR suitable for codegen.
 *
 * @see Node
 */
public final class IRFactory extends Parser
{
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.ping();
  }

    private static final int LOOP_DO_WHILE = 0;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1]++;
  }
    private static final int LOOP_WHILE    = 1;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[2]++;
  }
    private static final int LOOP_FOR      = 2;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[3]++;
  }

    private static final int ALWAYS_TRUE_BOOLEAN = 1;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[4]++;
  }
    private static final int ALWAYS_FALSE_BOOLEAN = -1;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[5]++;
  }

    private Decompiler decompiler = new Decompiler();
  {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[6]++;
  }

    public IRFactory() {
        super();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[7]++;
    }

    public IRFactory(CompilerEnvirons env) {
        this(env, env.getErrorReporter());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[8]++;
    }

    public IRFactory(CompilerEnvirons env, ErrorReporter errorReporter) {
        super(env, errorReporter);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[9]++;
    }

    /**
     * Transforms the tree into a lower-level IR suitable for codegen.
     * Optionally generates the encoded source.
     */
    public ScriptNode transformTree(AstRoot root) {
        currentScriptOrFn = root;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[10]++;
        this.inUseStrictDirective = root.isInStrictMode();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[11]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[12]++;
        int sourceStartOffset = decompiler.getCurrentOffset();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[1]++;
            System.out.println("IRFactory.transformTree");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[14]++;
            System.out.println(root.debugPrint());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[15]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[2]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[16]++;
        ScriptNode script = (ScriptNode)transform(root);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[17]++;

        int sourceEndOffset = decompiler.getCurrentOffset();
        script.setEncodedSourceBounds(sourceStartOffset,
                                      sourceEndOffset);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[18]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((compilerEnv.isGeneratingSource()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[3]++;
            script.setEncodedSource(decompiler.getEncodedSource());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[20]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[4]++;}

        decompiler = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[21]++;
        return script;
    }

    // Might want to convert this to polymorphism - move transform*
    // functions into the AstNode subclasses.  OTOH that would make
    // IR transformation part of the public AST API - desirable?
    // Another possibility:  create AstTransformer interface and adapter.
    public Node transform(AstNode node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[22]++;
        switch (node.getType()) {
          case Token.ARRAYCOMP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[5]++;
              return transformArrayComp((ArrayComprehension)node);
          case Token.ARRAYLIT:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[6]++;
              return transformArrayLiteral((ArrayLiteral)node);
          case Token.BLOCK:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[7]++;
              return transformBlock(node);
          case Token.BREAK:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[8]++;
              return transformBreak((BreakStatement)node);
          case Token.CALL:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[9]++;
              return transformFunctionCall((FunctionCall)node);
          case Token.CONTINUE:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[10]++;
              return transformContinue((ContinueStatement)node);
          case Token.DO:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[11]++;
              return transformDoLoop((DoLoop)node);
          case Token.EMPTY:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[12]++;
              return node;
          case Token.FOR:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[13]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
              if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((node instanceof ForInLoop) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[14]++;
                  return transformForInLoop((ForInLoop)node);

              } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[15]++;
                  return transformForLoop((ForLoop)node);
              }
          case Token.FUNCTION:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[16]++;
              return transformFunction((FunctionNode)node);
          case Token.GENEXPR:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[17]++;
              return transformGenExpr((GeneratorExpression)node);
          case Token.GETELEM:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[18]++;
              return transformElementGet((ElementGet)node);
          case Token.GETPROP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[19]++;
              return transformPropertyGet((PropertyGet)node);
          case Token.HOOK:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[20]++;
              return transformCondExpr((ConditionalExpression)node);
          case Token.IF:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[21]++;
              return transformIf((IfStatement)node);

          case Token.TRUE:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[22]++;
          case Token.FALSE:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[23]++;
          case Token.THIS:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[24]++;
          case Token.NULL:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[25]++;
          case Token.DEBUGGER:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[26]++;
              return transformLiteral(node);

          case Token.NAME:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[27]++;
              return transformName((Name)node);
          case Token.NUMBER:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[28]++;
              return transformNumber((NumberLiteral)node);
          case Token.NEW:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[29]++;
              return transformNewExpr((NewExpression)node);
          case Token.OBJECTLIT:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[30]++;
              return transformObjectLiteral((ObjectLiteral)node);
          case Token.REGEXP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[31]++;
              return transformRegExp((RegExpLiteral)node);
          case Token.RETURN:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[32]++;
              return transformReturn((ReturnStatement)node);
          case Token.SCRIPT:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[33]++;
              return transformScript((ScriptNode)node);
          case Token.STRING:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[34]++;
              return transformString((StringLiteral)node);
          case Token.SWITCH:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[35]++;
              return transformSwitch((SwitchStatement)node);
          case Token.THROW:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[36]++;
              return transformThrow((ThrowStatement)node);
          case Token.TRY:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[37]++;
              return transformTry((TryStatement)node);
          case Token.WHILE:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[38]++;
              return transformWhileLoop((WhileLoop)node);
          case Token.WITH:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[39]++;
              return transformWith((WithStatement)node);
          case Token.YIELD:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[40]++;
              return transformYield((Yield)node);
          default:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[41]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
              if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((node instanceof ExpressionStatement) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[42]++;
                  return transformExprStmt((ExpressionStatement)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[43]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
              if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node instanceof Assignment) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[44]++;
                  return transformAssignment((Assignment)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[45]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
              if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node instanceof UnaryExpression) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[46]++;
                  return transformUnary((UnaryExpression)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[47]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
              if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node instanceof XmlMemberGet) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[48]++;
                  return transformXmlMemberGet((XmlMemberGet)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[49]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
              if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node instanceof InfixExpression) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[50]++;
                  return transformInfix((InfixExpression)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[51]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
              if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((node instanceof VariableDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[52]++;
                  return transformVariables((VariableDeclaration)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[53]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
              if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((node instanceof ParenthesizedExpression) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[54]++;
                  return transformParenExpr((ParenthesizedExpression)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[55]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
              if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((node instanceof LabeledStatement) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[56]++;
                  return transformLabeledStatement((LabeledStatement)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[57]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
              if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((node instanceof LetNode) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[58]++;
                  return transformLetNode((LetNode)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[59]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;
              if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((node instanceof XmlRef) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[60]++;
                  return transformXmlRef((XmlRef)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[61]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[34]++;
int CodeCoverConditionCoverageHelper_C14;
              if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((node instanceof XmlLiteral) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[62]++;
                  return transformXmlLiteral((XmlLiteral)node);

              } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[63]++;}
              throw new IllegalArgumentException("Can't transform: " + node);
        }
    }

    private Node transformArrayComp(ArrayComprehension node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[35]++;
        // An array comprehension expression such as
        //
        //   [expr for (x in foo) for each ([y, z] in bar) if (cond)]
        //
        // is rewritten approximately as
        //
        // new Scope(ARRAYCOMP) {
        //   new Node(BLOCK) {
        //     let tmp1 = new Array;
        //     for (let x in foo) {
        //       for each (let tmp2 in bar) {
        //         if (cond) {
        //           tmp1.push([y, z] = tmp2, expr);
        //         }
        //       }
        //     }
        //   }
        //   createName(tmp1)
        // }

        int lineno = node.getLineno();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[36]++;
        Scope scopeNode = createScopeNode(Token.ARRAYCOMP, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[37]++;
        String arrayName = currentScriptOrFn.getNextTempName();
        pushScope(scopeNode);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[38]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[39]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            defineSymbol(Token.LET, arrayName, false);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[40]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[41]++;
            Node block = new Node(Token.BLOCK, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[42]++;
            Node newArray = createCallOrNew(Token.NEW, createName("Array"));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[43]++;
            Node init = new Node(Token.EXPR_VOID,
                                 createAssignment(Token.ASSIGN,
                                                  createName(arrayName),
                                                  newArray),
                                 lineno);
            block.addChildToBack(init);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[44]++;
            block.addChildToBack(arrayCompTransformHelper(node, arrayName));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[45]++;
            scopeNode.addChildToBack(block);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[46]++;
            scopeNode.addChildToBack(createName(arrayName));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[47]++;
            return scopeNode;
        } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[64]++;
}
            popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[48]++;
        }
    }

    private Node arrayCompTransformHelper(ArrayComprehension node,
                                          String arrayName) {
        decompiler.addToken(Token.LB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[49]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[50]++;
        int lineno = node.getLineno();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[51]++;
        Node expr = transform(node.getResult());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[52]++;

        List<ArrayComprehensionLoop> loops = node.getLoops();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[53]++;
        int numLoops = loops.size();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[54]++;

        // Walk through loops, collecting and defining their iterator symbols.
        Node[] iterators = new Node[numLoops];
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[55]++;
        Node[] iteratedObjs = new Node[numLoops];
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[56]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[1]++;


int CodeCoverConditionCoverageHelper_C15;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < numLoops) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[1]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[2]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[3]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[57]++;
            ArrayComprehensionLoop acl = loops.get(i);
            decompiler.addName(" ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[58]++;
            decompiler.addToken(Token.FOR);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[59]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((acl.isForEach()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[65]++;
                decompiler.addName("each ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[61]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[66]++;}
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[62]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[63]++;

            AstNode iter = acl.getIterator();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[64]++;
            String name = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[65]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((iter.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[67]++;
                name = iter.getString();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[66]++;
                decompiler.addName(name);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[67]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[68]++;
                // destructuring assignment
                decompile(iter);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[68]++;
                name = currentScriptOrFn.getNextTempName();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[69]++;
                defineSymbol(Token.LP, name, false);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[70]++;
                expr = createBinary(Token.COMMA,
                                    createAssignment(Token.ASSIGN,
                                                     iter,
                                                     createName(name)),
                                    expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[71]++;
            }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[72]++;
            Node init = createName(name);
            // Define as a let since we want the scope of the variable to
            // be restricted to the array comprehension
            defineSymbol(Token.LET, name, false);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[73]++;
            iterators[i] = init;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[74]++;

            decompiler.addToken(Token.IN);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[75]++;
            iteratedObjs[i] = transform(acl.getIteratedObject());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[76]++;
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[77]++;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[78]++;

        // generate code for tmpArray.push(body)
        Node call = createCallOrNew(Token.CALL,
                                    createPropertyGet(createName(arrayName),
                                                      null,
                                                      "push", 0));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[79]++;

        Node body = new Node(Token.EXPR_VOID, call, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[80]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((node.getFilter() != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[69]++;
            decompiler.addName(" ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[81]++;
            decompiler.addToken(Token.IF);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[82]++;
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[83]++;
            body = createIf(transform(node.getFilter()), body, null, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[84]++;
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[85]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[70]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[86]++;

        // Now walk loops in reverse to build up the body statement.
        int pushed = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[87]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[88]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[4]++;


int CodeCoverConditionCoverageHelper_C19;
            for (int i = numLoops-1;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[4]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[5]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[6]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[89]++;
                ArrayComprehensionLoop acl = loops.get(i);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[90]++;
                Scope loop = createLoopNode(null,  // no label
                                            acl.getLineno());
                pushScope(loop);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[91]++;
                pushed++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[92]++;
                body = createForIn(Token.LET,
                                   loop,
                                   iterators[i],
                                   iteratedObjs[i],
                                   body,
                                   acl.isForEach());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[93]++;
            }
        } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[71]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[94]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[7]++;


int CodeCoverConditionCoverageHelper_C20;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < pushed) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[7]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[8]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[9]++;
}
                popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[95]++;
            }
        }

        decompiler.addToken(Token.RB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[96]++;

        // Now that we've accumulated any destructuring forms,
        // add expr to the call node; it's pushed on each iteration.
        call.addChildToBack(expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[97]++;
        return body;
    }

    private Node transformArrayLiteral(ArrayLiteral node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[98]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((node.isDestructuring()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[72]++;
            return node;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[73]++;}
        decompiler.addToken(Token.LB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[99]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[100]++;
        List<AstNode> elems = node.getElements();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[101]++;
        Node array = new Node(Token.ARRAYLIT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[102]++;
        List<Integer> skipIndexes = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[103]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[10]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < elems.size()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[10]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[11]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[12]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[104]++;
            AstNode elem = elems.get(i);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[105]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((elem.getType() != Token.EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[74]++;
                array.addChildToBack(transform(elem));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[106]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[75]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[107]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((skipIndexes == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[76]++;
                    skipIndexes = new ArrayList<Integer>();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[108]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[77]++;}
                skipIndexes.add(i);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[109]++;
            }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[110]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i < elems.size() - 1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[78]++;
                decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[111]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[79]++;}
        }
        decompiler.addToken(Token.RB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[112]++;
        array.putIntProp(Node.DESTRUCTURING_ARRAY_LENGTH,
                         node.getDestructuringLength());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[113]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[114]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((skipIndexes != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[80]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[115]++;
            int[] skips = new int[skipIndexes.size()];
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[116]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < skipIndexes.size()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[13]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[14]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[15]++;
}
                skips[i] = skipIndexes.get(i);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[117]++;
  }
            array.putProp(Node.SKIP_INDEXES_PROP, skips);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[118]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[81]++;}
        return array;
    }

    private Node transformAssignment(Assignment node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[119]++;
        AstNode left = removeParens(node.getLeft());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[120]++;
        Node target = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[121]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((isDestructuring(left)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[82]++;
            decompile(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[122]++;
            target = left;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[123]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[83]++;
            target = transform(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[124]++;
        }
        decompiler.addToken(node.getType());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[125]++;
        return createAssignment(node.getType(),
                                target,
                                transform(node.getRight()));
    }

    private Node transformBlock(AstNode node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[126]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((node instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[84]++;
            pushScope((Scope)node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[127]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[85]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[128]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[129]++;
            List<Node> kids = new ArrayList<Node>();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[130]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[16]++;


            for (Node kid : node) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[16]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[17]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[18]++;
}
                kids.add(transform((AstNode)kid));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[131]++;
            }
            node.removeChildren();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[132]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[133]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[19]++;


            for (Node kid : kids) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[19]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[20]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[21]++;
}
                node.addChildToBack(kid);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[134]++;
            }
            return node;
        } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[86]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[135]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((node instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[87]++;
                popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[136]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[88]++;}
        }
    }

    private Node transformBreak(BreakStatement node) {
        decompiler.addToken(Token.BREAK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[137]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[138]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((node.getBreakLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[89]++;
            decompiler.addName(node.getBreakLabel().getIdentifier());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[139]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[90]++;}
        decompiler.addEOL(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[140]++;
        return node;
    }

    private Node transformCondExpr(ConditionalExpression node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[141]++;
        Node test = transform(node.getTestExpression());
        decompiler.addToken(Token.HOOK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[142]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[143]++;
        Node ifTrue = transform(node.getTrueExpression());
        decompiler.addToken(Token.COLON);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[144]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[145]++;
        Node ifFalse = transform(node.getFalseExpression());
        return createCondExpr(test, ifTrue, ifFalse);
    }

    private Node transformContinue(ContinueStatement node) {
        decompiler.addToken(Token.CONTINUE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[146]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[147]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((node.getLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[91]++;
            decompiler.addName(node.getLabel().getIdentifier());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[148]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[92]++;}
        decompiler.addEOL(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[149]++;
        return node;
    }

    private Node transformDoLoop(DoLoop loop) {
        loop.setType(Token.LOOP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[150]++;
        pushScope(loop);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[151]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[152]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
            decompiler.addToken(Token.DO);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[153]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[154]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[155]++;
            Node body = transform(loop.getBody());
            decompiler.addToken(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[156]++;
            decompiler.addToken(Token.WHILE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[157]++;
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[158]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[159]++;
            Node cond = transform(loop.getCondition());
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[160]++;
            decompiler.addEOL(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[161]++;
            return createLoop(loop, LOOP_DO_WHILE,
                              body, cond, null, null);
        } finally {
if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[93]++;
}
            popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[162]++;
        }
    }

    private Node transformElementGet(ElementGet node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[163]++;
        // OPT: could optimize to createPropertyGet
        // iff elem is string that can not be number
        Node target = transform(node.getTarget());
        decompiler.addToken(Token.LB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[164]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[165]++;
        Node element = transform(node.getElement());
        decompiler.addToken(Token.RB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[166]++;
        return new Node(Token.GETELEM, target, element);
    }

    private Node transformExprStmt(ExpressionStatement node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[167]++;
        Node expr = transform(node.getExpression());
        decompiler.addEOL(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[168]++;
        return new Node(node.getType(), expr, node.getLineno());
    }

    private Node transformForInLoop(ForInLoop loop) {
        decompiler.addToken(Token.FOR);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[169]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[170]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((loop.isForEach()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[94]++;
            decompiler.addName("each ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[171]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[95]++;}
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[172]++;

        loop.setType(Token.LOOP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[173]++;
        pushScope(loop);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[174]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[175]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
        try {
CodeCoverTryBranchHelper_Try5 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[176]++;
            int declType = -1;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[177]++;
            AstNode iter = loop.getIterator();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[178]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((iter instanceof VariableDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[97]++;
                declType = ((VariableDeclaration)iter).getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[179]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[98]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[180]++;
            Node lhs = transform(iter);
            decompiler.addToken(Token.IN);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[181]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[182]++;
            Node obj = transform(loop.getIteratedObject());
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[183]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[184]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[185]++;
            Node body = transform(loop.getBody());
            decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[186]++;
            return createForIn(declType, loop, lhs, obj, body,
                               loop.isForEach());
        } finally {
if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[96]++;
}
            popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[187]++;
        }
    }

    private Node transformForLoop(ForLoop loop) {
        decompiler.addToken(Token.FOR);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[188]++;
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[189]++;
        loop.setType(Token.LOOP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[190]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[191]++;
        // XXX: Can't use pushScope/popScope here since 'createFor' may split
        // the scope
        Scope savedScope = currentScope;
        currentScope = loop;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[192]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[193]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
        try {
CodeCoverTryBranchHelper_Try6 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[194]++;
            Node init = transform(loop.getInitializer());
            decompiler.addToken(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[195]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[196]++;
            Node test = transform(loop.getCondition());
            decompiler.addToken(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[197]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[198]++;
            Node incr = transform(loop.getIncrement());
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[199]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[200]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[201]++;
            Node body = transform(loop.getBody());
            decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[202]++;
            return createFor(loop, init, test, incr, body);
        } finally {
if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[99]++;
}
            currentScope = savedScope;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[203]++;
        }
    }

    private Node transformFunction(FunctionNode fn) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[204]++;
        int functionType = fn.getFunctionType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[205]++;
        int start = decompiler.markFunctionStart(functionType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[206]++;
        Node mexpr = decompileFunctionHeader(fn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[207]++;
        int index = currentScriptOrFn.addFunction(fn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[208]++;

        PerFunctionVariables savedVars = new PerFunctionVariables(fn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[209]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
        try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[210]++;
            // If we start needing to record much more codegen metadata during
            // function parsing, we should lump it all into a helper class.
            Node destructuring = (Node)fn.getProp(Node.DESTRUCTURING_PARAMS);
            fn.removeProp(Node.DESTRUCTURING_PARAMS);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[211]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[212]++;

            int lineno = fn.getBody().getLineno();
            ++nestingOfFunction;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[213]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[214]++;  // only for body, not params
            Node body = transform(fn.getBody());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[215]++;
int CodeCoverConditionCoverageHelper_C35;

            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((fn.isExpressionClosure()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[101]++;
                decompiler.addToken(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[216]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[102]++;}
            fn.setEncodedSourceBounds(start, decompiler.markFunctionEnd(start));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[217]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[218]++;
int CodeCoverConditionCoverageHelper_C36;

            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((functionType != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((fn.isExpressionClosure()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[103]++;
                // Add EOL only if function is not part of expression
                // since it gets SEMI + EOL from Statement in that case
                decompiler.addToken(Token.EOL);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[219]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[104]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[220]++;
int CodeCoverConditionCoverageHelper_C37;

            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((destructuring != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[105]++;
                body.addChildToFront(new Node(Token.EXPR_VOID,
                                              destructuring, lineno));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[221]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[106]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[222]++;

            int syntheticType = fn.getFunctionType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[223]++;
            Node pn = initFunction(fn, index, body, syntheticType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[224]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((mexpr != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[107]++;
                pn = createAssignment(Token.ASSIGN, mexpr, pn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[225]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[226]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((syntheticType != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[109]++;
                    pn = createExprStatementNoReturn(pn, fn.getLineno());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[227]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[110]++;}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[108]++;}
            return pn;

        } finally {
if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[100]++;
}
            --nestingOfFunction;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[228]++;
            savedVars.restore();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[229]++;
        }
    }

    private Node transformFunctionCall(FunctionCall node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[230]++;
        Node call = createCallOrNew(Token.CALL, transform(node.getTarget()));
        call.setLineno(node.getLineno());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[231]++;
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[232]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[233]++;
        List<AstNode> args = node.getArguments();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[234]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[22]++;


int CodeCoverConditionCoverageHelper_C40;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i < args.size()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[22]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[23]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[24]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[235]++;
            AstNode arg = args.get(i);
            call.addChildToBack(transform(arg));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[236]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[237]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i < args.size() - 1) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[111]++;
                decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[238]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[112]++;}
        }
        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[239]++;
        return call;
    }

    private Node transformGenExpr(GeneratorExpression node) {
        Node pn;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[240]++;

        FunctionNode fn = new FunctionNode();
        fn.setSourceName(currentScriptOrFn.getNextTempName());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[241]++;
        fn.setIsGenerator();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[242]++;
        fn.setFunctionType(FunctionNode.FUNCTION_EXPRESSION);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[243]++;
        fn.setRequiresActivation();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[244]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[245]++;

        int functionType = fn.getFunctionType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[246]++;
        int start = decompiler.markFunctionStart(functionType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[247]++;
        Node mexpr = decompileFunctionHeader(fn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[248]++;
        int index = currentScriptOrFn.addFunction(fn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[249]++;

        PerFunctionVariables savedVars = new PerFunctionVariables(fn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[250]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
        try {
CodeCoverTryBranchHelper_Try8 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[251]++;
            // If we start needing to record much more codegen metadata during
            // function parsing, we should lump it all into a helper class.
            Node destructuring = (Node)fn.getProp(Node.DESTRUCTURING_PARAMS);
            fn.removeProp(Node.DESTRUCTURING_PARAMS);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[252]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[253]++;

            int lineno = node.lineno;
            ++nestingOfFunction;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[254]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[255]++;  // only for body, not params
            Node body = genExprTransformHelper(node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[256]++;
int CodeCoverConditionCoverageHelper_C42;

            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((fn.isExpressionClosure()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[114]++;
                decompiler.addToken(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[257]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[115]++;}
            fn.setEncodedSourceBounds(start, decompiler.markFunctionEnd(start));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[258]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[259]++;
int CodeCoverConditionCoverageHelper_C43;

            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((functionType != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((fn.isExpressionClosure()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[116]++;
                // Add EOL only if function is not part of expression
                // since it gets SEMI + EOL from Statement in that case
                decompiler.addToken(Token.EOL);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[260]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[117]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[261]++;
int CodeCoverConditionCoverageHelper_C44;

            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((destructuring != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[118]++;
                body.addChildToFront(new Node(Token.EXPR_VOID,
                                              destructuring, lineno));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[262]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[119]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[263]++;

            int syntheticType = fn.getFunctionType();
            pn = initFunction(fn, index, body, syntheticType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[264]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[265]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((mexpr != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[120]++;
                pn = createAssignment(Token.ASSIGN, mexpr, pn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[266]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[267]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((syntheticType != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[122]++;
                    pn = createExprStatementNoReturn(pn, fn.getLineno());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[268]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[123]++;}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[121]++;}
        } finally {
if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[113]++;
}
            --nestingOfFunction;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[269]++;
            savedVars.restore();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[270]++;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[271]++;

        Node call = createCallOrNew(Token.CALL, pn);
        call.setLineno(node.getLineno());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[272]++;
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[273]++;
        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[274]++;
        return call;
    }

    private Node genExprTransformHelper(GeneratorExpression node) {
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[275]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[276]++;
        int lineno = node.getLineno();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[277]++;
        Node expr = transform(node.getResult());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[278]++;

        List<GeneratorExpressionLoop> loops = node.getLoops();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[279]++;
        int numLoops = loops.size();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[280]++;

        // Walk through loops, collecting and defining their iterator symbols.
        Node[] iterators = new Node[numLoops];
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[281]++;
        Node[] iteratedObjs = new Node[numLoops];
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[282]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[25]++;


int CodeCoverConditionCoverageHelper_C47;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((i < numLoops) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[25]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[26]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[27]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[283]++;
            GeneratorExpressionLoop acl = loops.get(i);
            decompiler.addName(" ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[284]++;
            decompiler.addToken(Token.FOR);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[285]++;
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[286]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[287]++;

            AstNode iter = acl.getIterator();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[288]++;
            String name = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[289]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((iter.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[124]++;
                name = iter.getString();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[290]++;
                decompiler.addName(name);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[291]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[125]++;
                // destructuring assignment
                decompile(iter);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[292]++;
                name = currentScriptOrFn.getNextTempName();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[293]++;
                defineSymbol(Token.LP, name, false);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[294]++;
                expr = createBinary(Token.COMMA,
                                    createAssignment(Token.ASSIGN,
                                                     iter,
                                                     createName(name)),
                                    expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[295]++;
            }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[296]++;
            Node init = createName(name);
            // Define as a let since we want the scope of the variable to
            // be restricted to the array comprehension
            defineSymbol(Token.LET, name, false);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[297]++;
            iterators[i] = init;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[298]++;

            decompiler.addToken(Token.IN);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[299]++;
            iteratedObjs[i] = transform(acl.getIteratedObject());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[300]++;
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[301]++;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[302]++;

        // generate code for tmpArray.push(body)
        Node yield = new Node(Token.YIELD, expr, node.getLineno());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[303]++;

        Node body = new Node(Token.EXPR_VOID, yield, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[304]++;
int CodeCoverConditionCoverageHelper_C49;

        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((node.getFilter() != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[126]++;
            decompiler.addName(" ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[305]++;
            decompiler.addToken(Token.IF);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[306]++;
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[307]++;
            body = createIf(transform(node.getFilter()), body, null, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[308]++;
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[309]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[127]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[310]++;

        // Now walk loops in reverse to build up the body statement.
        int pushed = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[311]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
        try {
CodeCoverTryBranchHelper_Try9 = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[312]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[28]++;


int CodeCoverConditionCoverageHelper_C50;
            for (int i = numLoops-1;(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[28]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[29]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[30]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[313]++;
                GeneratorExpressionLoop acl = loops.get(i);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[314]++;
                Scope loop = createLoopNode(null,  // no label
                                            acl.getLineno());
                pushScope(loop);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[315]++;
                pushed++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[316]++;
                body = createForIn(Token.LET,
                                   loop,
                                   iterators[i],
                                   iteratedObjs[i],
                                   body,
                                   acl.isForEach());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[317]++;
            }
        } finally {
if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[128]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[318]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[31]++;


int CodeCoverConditionCoverageHelper_C51;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((i < pushed) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[31]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[32]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[33]++;
}
                popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[319]++;
            }
        }

        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[320]++;

        return body;
    }

    private Node transformIf(IfStatement n) {
        decompiler.addToken(Token.IF);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[321]++;
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[322]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[323]++;
        Node cond = transform(n.getCondition());
        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[324]++;
        decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[325]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[326]++;
        Node ifTrue = transform(n.getThenPart());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[327]++;
        Node ifFalse = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[328]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((n.getElsePart() != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[129]++;
            decompiler.addToken(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[329]++;
            decompiler.addToken(Token.ELSE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[330]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[331]++;
            ifFalse = transform(n.getElsePart());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[332]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[130]++;}
        decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[333]++;
        return createIf(cond, ifTrue, ifFalse, n.getLineno());
    }

    private Node transformInfix(InfixExpression node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[334]++;
        Node left = transform(node.getLeft());
        decompiler.addToken(node.getType());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[335]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[336]++;
        Node right = transform(node.getRight());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[337]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((node instanceof XmlDotQuery) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[131]++;
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[338]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[132]++;}
        return createBinary(node.getType(), left, right);
    }

    private Node transformLabeledStatement(LabeledStatement ls) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[339]++;
        Label label = ls.getFirstLabel();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[340]++;
        List<Label> labels = ls.getLabels();
        decompiler.addName(label.getName());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[341]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[342]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((labels.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[133]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[343]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[34]++;


            // more than one label
            for (Label lb : labels.subList(1, labels.size())) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[34]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[35]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[36]++;
}
                decompiler.addEOL(Token.COLON);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[344]++;
                decompiler.addName(lb.getName());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[345]++;
            }

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[134]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[346]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((ls.getStatement().getType() == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[135]++;
            // reuse OBJECTLIT for ':' workaround, cf. transformObjectLiteral()
            decompiler.addToken(Token.OBJECTLIT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[347]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[348]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[136]++;
            decompiler.addEOL(Token.COLON);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[349]++;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[350]++;
        Node statement = transform(ls.getStatement());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[351]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((ls.getStatement().getType() == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[137]++;
            decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[352]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[138]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[353]++;

        // Make a target and put it _after_ the statement node.  Add in the
        // LABEL node, so breaks get the right target.
        Node breakTarget = Node.newTarget();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[354]++;
        Node block = new Node(Token.BLOCK, label, statement, breakTarget);
        label.target = breakTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[355]++;

        return block;
    }

    private Node transformLetNode(LetNode node) {
        pushScope(node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[356]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[357]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
        try {
CodeCoverTryBranchHelper_Try10 = true;
            decompiler.addToken(Token.LET);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[358]++;
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[359]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[360]++;
            Node vars = transformVariableInitializers(node.getVariables());
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[361]++;
            node.addChildToBack(vars);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[362]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[363]++;
            boolean letExpr = node.getType() == Token.LETEXPR;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[364]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((node.getBody() != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[140]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[365]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((letExpr) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[142]++;
                    decompiler.addName(" ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[366]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[143]++;
                    decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[367]++;
                }
                node.addChildToBack(transform(node.getBody()));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[368]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[369]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((letExpr) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[144]++;
                    decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[370]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[145]++;}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[141]++;}
            return node;
        } finally {
if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[139]++;
}
            popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[371]++;
        }
    }

    private Node transformLiteral(AstNode node) {
        decompiler.addToken(node.getType());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[372]++;
        return node;
    }

    private Node transformName(Name node) {
        decompiler.addName(node.getIdentifier());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[373]++;
        return node;
    }

    private Node transformNewExpr(NewExpression node) {
        decompiler.addToken(Token.NEW);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[374]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[375]++;
        Node nx = createCallOrNew(Token.NEW, transform(node.getTarget()));
        nx.setLineno(node.getLineno());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[376]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[377]++;
        List<AstNode> args = node.getArguments();
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[378]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[379]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[37]++;


int CodeCoverConditionCoverageHelper_C60;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((i < args.size()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[37]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[38]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[39]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[380]++;
            AstNode arg = args.get(i);
            nx.addChildToBack(transform(arg));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[381]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[382]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((i < args.size() - 1) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[146]++;
                decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[383]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[147]++;}
        }
        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[384]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[385]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((node.getInitializer() != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[148]++;
            nx.addChildToBack(transformObjectLiteral(node.getInitializer()));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[386]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[149]++;}
        return nx;
    }

    private Node transformNumber(NumberLiteral node) {
        decompiler.addNumber(node.getNumber());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[387]++;
        return node;
    }

    private Node transformObjectLiteral(ObjectLiteral node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[388]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((node.isDestructuring()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[150]++;
            return node;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[151]++;}
        // createObjectLiteral rewrites its argument as object
        // creation plus object property entries, so later compiler
        // stages don't need to know about object literals.
        decompiler.addToken(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[389]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[390]++;
        List<ObjectProperty> elems = node.getElements();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[391]++;
        Node object = new Node(Token.OBJECTLIT);
        Object[] properties;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[392]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((elems.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[152]++;
            properties = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[393]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[153]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[394]++;
            int size = elems.size(), i = 0;
            properties = new Object[size];
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[395]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[396]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[40]++;


            for (ObjectProperty prop : elems) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[40]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[41]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[42]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[397]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((prop.isGetter()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[154]++;
                    decompiler.addToken(Token.GET);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[398]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[155]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[399]++;
int CodeCoverConditionCoverageHelper_C66; if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((prop.isSetter()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[156]++;
                    decompiler.addToken(Token.SET);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[400]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[157]++;}
}

                properties[i++] = getPropKey(prop.getLeft());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[401]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[402]++;
int CodeCoverConditionCoverageHelper_C67;

                // OBJECTLIT is used as ':' in object literal for
                // decompilation to solve spacing ambiguity.
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((prop.isGetter()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((prop.isSetter()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[158]++;
                    decompiler.addToken(Token.OBJECTLIT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[403]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[159]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[404]++;

                Node right = transform(prop.getRight());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[405]++;
int CodeCoverConditionCoverageHelper_C68;
                if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((prop.isGetter()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[160]++;
                    right = createUnary(Token.GET, right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[406]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[161]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[407]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((prop.isSetter()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[162]++;
                    right = createUnary(Token.SET, right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[408]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[163]++;}
}
                object.addChildToBack(right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[409]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[410]++;
int CodeCoverConditionCoverageHelper_C70;

                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[164]++;
                    decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[411]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[165]++;}
            }
        }
        decompiler.addToken(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[412]++;
        object.putProp(Node.OBJECT_IDS_PROP, properties);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[413]++;
        return object;
    }

    private Object getPropKey(Node id) {
        Object key;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[414]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((id instanceof Name) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[166]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[415]++;
            String s = ((Name)id).getIdentifier();
            decompiler.addName(s);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[416]++;
            key = ScriptRuntime.getIndexObject(s);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[417]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[167]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[418]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((id instanceof StringLiteral) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[168]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[419]++;
            String s = ((StringLiteral)id).getValue();
            decompiler.addString(s);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[420]++;
            key = ScriptRuntime.getIndexObject(s);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[421]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[169]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[422]++;
int CodeCoverConditionCoverageHelper_C73; if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((id instanceof NumberLiteral) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[170]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[423]++;
            double n = ((NumberLiteral)id).getNumber();
            decompiler.addNumber(n);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[424]++;
            key = ScriptRuntime.getIndexObject(n);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[425]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[171]++;
            throw Kit.codeBug();
        }
}
}
        return key;
    }

    private Node transformParenExpr(ParenthesizedExpression node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[426]++;
        AstNode expr = node.getExpression();
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[427]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[428]++;
        int count = 1;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[429]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[43]++;


int CodeCoverConditionCoverageHelper_C74;
        while ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((expr instanceof ParenthesizedExpression) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[43]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[44]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[45]++;
}
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[430]++;
            count++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[431]++;
            expr = ((ParenthesizedExpression)expr).getExpression();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[432]++;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[433]++;
        Node result = transform(expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[434]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[46]++;


int CodeCoverConditionCoverageHelper_C75;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[46]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[47]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[48]++;
}
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[435]++;
        }
        result.putProp(Node.PARENTHESIZED_PROP, Boolean.TRUE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[436]++;
        return result;
    }

    private Node transformPropertyGet(PropertyGet node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[437]++;
        Node target = transform(node.getTarget());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[438]++;
        String name = node.getProperty().getIdentifier();
        decompiler.addToken(Token.DOT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[439]++;
        decompiler.addName(name);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[440]++;
        return createPropertyGet(target, null, name, 0);
    }

    private Node transformRegExp(RegExpLiteral node) {
        decompiler.addRegexp(node.getValue(), node.getFlags());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[441]++;
        currentScriptOrFn.addRegExp(node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[442]++;
        return node;
    }

    private Node transformReturn(ReturnStatement node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[443]++;
        boolean expClosure = Boolean.TRUE.equals(node.getProp(Node.EXPRESSION_CLOSURE_PROP));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[444]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((expClosure) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[172]++;
            decompiler.addName(" ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[445]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[173]++;
            decompiler.addToken(Token.RETURN);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[446]++;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[447]++;
        AstNode rv = node.getReturnValue();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[448]++;
        Node value = rv == null ? null : transform(rv);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[449]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((expClosure) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[174]++; decompiler.addEOL(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[450]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[175]++;}
        return rv == null
            ? new Node(Token.RETURN, node.getLineno())
            : new Node(Token.RETURN, value, node.getLineno());
    }

    private Node transformScript(ScriptNode node) {
        decompiler.addToken(Token.SCRIPT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[451]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[452]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((currentScope != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[176]++; Kit.codeBug();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[453]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[177]++;}
        currentScope = node;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[454]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[455]++;
        Node body = new Node(Token.BLOCK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[456]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[49]++;


        for (Node kid : node) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[49]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[50]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[51]++;
}
            body.addChildToBack(transform((AstNode)kid));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[457]++;
        }
        node.removeChildren();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[458]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[459]++;
        Node children = body.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[460]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((children != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[178]++;
            node.addChildrenToBack(children);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[461]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[179]++;}
        return node;
    }

    private Node transformString(StringLiteral node) {
        decompiler.addString(node.getValue());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[462]++;
        return Node.newString(node.getValue());
    }

    private Node transformSwitch(SwitchStatement node) {
        // The switch will be rewritten from:
        //
        // switch (expr) {
        //   case test1: statements1;
        //   ...
        //   default: statementsDefault;
        //   ...
        //   case testN: statementsN;
        // }
        //
        // to:
        //
        // {
        //     switch (expr) {
        //       case test1: goto label1;
        //       ...
        //       case testN: goto labelN;
        //     }
        //     goto labelDefault;
        //   label1:
        //     statements1;
        //   ...
        //   labelDefault:
        //     statementsDefault;
        //   ...
        //   labelN:
        //     statementsN;
        //   breakLabel:
        // }
        //
        // where inside switch each "break;" without label will be replaced
        // by "goto breakLabel".
        //
        // If the original switch does not have the default label, then
        // after the switch he transformed code would contain this goto:
        //     goto breakLabel;
        // instead of:
        //     goto labelDefault;

        decompiler.addToken(Token.SWITCH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[463]++;
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[464]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[465]++;
        Node switchExpr = transform(node.getExpression());
        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[466]++;
        node.addChildToBack(switchExpr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[467]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[468]++;

        Node block = new Node(Token.BLOCK, node, node.getLineno());
        decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[469]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[470]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[52]++;



        for (SwitchCase sc : node.getCases()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[52]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[53]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[54]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[471]++;
            AstNode expr = sc.getExpression();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[472]++;
            Node caseExpr = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[473]++;
int CodeCoverConditionCoverageHelper_C80;

            if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((expr != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[180]++;
                decompiler.addToken(Token.CASE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[474]++;
                caseExpr = transform(expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[475]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[181]++;
                decompiler.addToken(Token.DEFAULT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[476]++;
            }
            decompiler.addEOL(Token.COLON);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[477]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[478]++;

            List<AstNode> stmts = sc.getStatements();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[479]++;
            Node body = new Block();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[480]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((stmts != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[182]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[481]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[55]++;


                for (AstNode kid : stmts) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[55]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[56]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[57]++;
}
                    body.addChildToBack(transform(kid));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[482]++;
                }

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[183]++;}
            addSwitchCase(block, caseExpr, body);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[483]++;
        }
        decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[484]++;
        closeSwitch(block);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[485]++;
        return block;
    }

    private Node transformThrow(ThrowStatement node) {
        decompiler.addToken(Token.THROW);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[486]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[487]++;
        Node value = transform(node.getExpression());
        decompiler.addEOL(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[488]++;
        return new Node(Token.THROW, value, node.getLineno());
    }

    private Node transformTry(TryStatement node) {
        decompiler.addToken(Token.TRY);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[489]++;
        decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[490]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[491]++;
        Node tryBlock = transform(node.getTryBlock());
        decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[492]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[493]++;

        Node catchBlocks = new Block();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[494]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[58]++;


        for (CatchClause cc : node.getCatchClauses()) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[58]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[59]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[60]++;
}
            decompiler.addToken(Token.CATCH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[495]++;
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[496]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[497]++;

            String varName = cc.getVarName().getIdentifier();
            decompiler.addName(varName);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[498]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[499]++;

            Node catchCond = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[500]++;
            AstNode ccc = cc.getCatchCondition();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[501]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((ccc != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[184]++;
                decompiler.addName(" ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[502]++;
                decompiler.addToken(Token.IF);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[503]++;
                catchCond = transform(ccc);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[504]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[185]++;
                catchCond = new EmptyExpression();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[505]++;
            }
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[506]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[507]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[508]++;

            Node body = transform(cc.getBody());
            decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[509]++;

            catchBlocks.addChildToBack(createCatch(varName, catchCond,
                                                   body, cc.getLineno()));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[510]++;
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[511]++;
        Node finallyBlock = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[512]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((node.getFinallyBlock() != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[186]++;
            decompiler.addToken(Token.FINALLY);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[513]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[514]++;
            finallyBlock = transform(node.getFinallyBlock());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[515]++;
            decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[516]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[187]++;}
        return createTryCatchFinally(tryBlock, catchBlocks,
                                     finallyBlock, node.getLineno());
    }

    private Node transformUnary(UnaryExpression node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[517]++;
        int type = node.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[518]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((type == Token.DEFAULTNAMESPACE) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[188]++;
            return transformDefaultXmlNamepace(node);

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[189]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[519]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((node.isPrefix()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[190]++;
            decompiler.addToken(type);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[520]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[191]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[521]++;
        Node child = transform(node.getOperand());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[522]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((node.isPostfix()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[192]++;
            decompiler.addToken(type);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[523]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[193]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[524]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((type == Token.INC) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((type == Token.DEC) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[194]++;
            return createIncDec(type, node.isPostfix(), child);

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[195]++;}
        return createUnary(type, child);
    }

    private Node transformVariables(VariableDeclaration node) {
        decompiler.addToken(node.getType());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[525]++;
        transformVariableInitializers(node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[526]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[527]++;

        // Might be most robust to have parser record whether it was
        // a variable declaration statement, possibly as a node property.
        AstNode parent = node.getParent();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[528]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((parent instanceof Loop) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
) && !(
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((parent instanceof LetNode) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[196]++;
            decompiler.addEOL(Token.SEMI);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[529]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[197]++;}
        return node;
    }

    private Node transformVariableInitializers(VariableDeclaration node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[530]++;
        List<VariableInitializer> vars = node.getVariables();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[531]++;
        int size = vars.size(), i = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[532]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[61]++;


        for (VariableInitializer var : vars) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[61]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[62]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[63]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[533]++;
            AstNode target = var.getTarget();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[534]++;
            AstNode init = var.getInitializer();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[535]++;

            Node left = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[536]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((var.isDestructuring()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[198]++;
                decompile(target);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[537]++;  // decompile but don't transform
                left = target;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[538]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[199]++;
                left = transform(target);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[539]++;
            }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[540]++;

            Node right = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[541]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((init != null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[200]++;
                decompiler.addToken(Token.ASSIGN);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[542]++;
                right = transform(init);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[543]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[201]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[544]++;
int CodeCoverConditionCoverageHelper_C91;

            if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((var.isDestructuring()) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[202]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[545]++;
int CodeCoverConditionCoverageHelper_C92;
                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((right == null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[204]++;  // TODO:  should this ever happen?
                    node.addChildToBack(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[546]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[205]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[547]++;
                    Node d = createDestructuringAssignment(node.getType(),
                                                           left, right);
                    node.addChildToBack(d);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[548]++;
                }

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[203]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[549]++;
int CodeCoverConditionCoverageHelper_C93;
                if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((right != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[206]++;
                    left.addChildToBack(right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[550]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[207]++;}
                node.addChildToBack(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[551]++;
            }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[552]++;
int CodeCoverConditionCoverageHelper_C94;
            if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((i++ < size-1) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[208]++;
                decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[553]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[209]++;}
        }
        return node;
    }

    private Node transformWhileLoop(WhileLoop loop) {
        decompiler.addToken(Token.WHILE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[554]++;
        loop.setType(Token.LOOP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[555]++;
        pushScope(loop);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[556]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[557]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
        try {
CodeCoverTryBranchHelper_Try11 = true;
            decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[558]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[559]++;
            Node cond = transform(loop.getCondition());
            decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[560]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[561]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[562]++;
            Node body = transform(loop.getBody());
            decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[563]++;
            return createLoop(loop, LOOP_WHILE, body, cond, null, null);
        } finally {
if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[210]++;
}
            popScope();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[564]++;
        }
    }

    private Node transformWith(WithStatement node) {
        decompiler.addToken(Token.WITH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[565]++;
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[566]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[567]++;
        Node expr = transform(node.getExpression());
        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[568]++;
        decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[569]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[570]++;
        Node stmt = transform(node.getStatement());
        decompiler.addEOL(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[571]++;
        return createWith(expr, stmt, node.getLineno());
    }

    private Node transformYield(Yield node) {
        decompiler.addToken(Token.YIELD);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[572]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[573]++;
        Node kid = node.getValue() == null ? null : transform(node.getValue());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[574]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((kid != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[211]++;
            return new Node(Token.YIELD, kid, node.getLineno());
}
        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[212]++;
            return new Node(Token.YIELD, node.getLineno());
}
    }

    private Node transformXmlLiteral(XmlLiteral node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[575]++;
        // a literal like <foo>{bar}</foo> is rewritten as
        //   new XML("<foo>" + bar + "</foo>");

        Node pnXML = new Node(Token.NEW, node.getLineno());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[576]++;
        List<XmlFragment> frags = node.getFragments();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[577]++;

        XmlString first = (XmlString)frags.get(0);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[578]++;
        boolean anon = first.getXml().trim().startsWith("<>");
        pnXML.addChildToBack(createName(anon ? "XMLList" : "XML"));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[579]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[580]++;

        Node pn = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[581]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[64]++;


        for (XmlFragment frag : frags) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[64]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[65]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[66]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[582]++;
int CodeCoverConditionCoverageHelper_C96;
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((frag instanceof XmlString) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[213]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[583]++;
                String xml = ((XmlString)frag).getXml();
                decompiler.addName(xml);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[584]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[585]++;
int CodeCoverConditionCoverageHelper_C97;
                if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((pn == null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[215]++;
                    pn = createString(xml);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[586]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[216]++;
                    pn = createBinary(Token.ADD, pn, createString(xml));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[587]++;
                }

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[214]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[588]++;
                XmlExpression xexpr = (XmlExpression)frag;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[589]++;
                boolean isXmlAttr = xexpr.isXmlAttribute();
                Node expr;
                decompiler.addToken(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[590]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[591]++;
int CodeCoverConditionCoverageHelper_C98;
                if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((xexpr.getExpression() instanceof EmptyExpression) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[217]++;
                    expr = createString("");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[592]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[218]++;
                    expr = transform(xexpr.getExpression());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[593]++;
                }
                decompiler.addToken(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[594]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[595]++;
int CodeCoverConditionCoverageHelper_C99;
                if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((isXmlAttr) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[219]++;
                    // Need to put the result in double quotes
                    expr = createUnary(Token.ESCXMLATTR, expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[596]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[597]++;
                    Node prepend = createBinary(Token.ADD,
                                                createString("\""),
                                                expr);
                    expr = createBinary(Token.ADD,
                                        prepend,
                                        createString("\""));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[598]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[220]++;
                    expr = createUnary(Token.ESCXMLTEXT, expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[599]++;
                }
                pn = createBinary(Token.ADD, pn, expr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[600]++;
            }
        }

        pnXML.addChildToBack(pn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[601]++;
        return pnXML;
    }

    private Node transformXmlMemberGet(XmlMemberGet node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[602]++;
        XmlRef ref = node.getMemberRef();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[603]++;
        Node pn = transform(node.getLeft());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[604]++;
        int flags = ref.isAttributeAccess() ? Node.ATTRIBUTE_FLAG : 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[605]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((node.getType() == Token.DOTDOT) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[221]++;
            flags |= Node.DESCENDANTS_FLAG;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[606]++;
            decompiler.addToken(Token.DOTDOT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[607]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[222]++;
            decompiler.addToken(Token.DOT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[608]++;
        }
        return transformXmlRef(pn, ref, flags);
    }

    // We get here if we weren't a child of a . or .. infix node
    private Node transformXmlRef(XmlRef node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[609]++;
        int memberTypeFlags = node.isAttributeAccess()
            ? Node.ATTRIBUTE_FLAG : 0;
        return transformXmlRef(null, node, memberTypeFlags);
    }

    private Node transformXmlRef(Node pn, XmlRef node, int memberTypeFlags) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[610]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 (((memberTypeFlags & Node.ATTRIBUTE_FLAG) != 0) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[223]++;
            decompiler.addToken(Token.XMLATTR);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[611]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[224]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[612]++;
        Name namespace = node.getNamespace();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[613]++;
        String ns = namespace != null ? namespace.getIdentifier() : null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[614]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((ns != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[225]++;
            decompiler.addName(ns);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[615]++;
            decompiler.addToken(Token.COLONCOLON);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[616]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[226]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[617]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((node instanceof XmlPropRef) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[227]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[618]++;
            String name = ((XmlPropRef)node).getPropName().getIdentifier();
            decompiler.addName(name);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[619]++;
            return createPropertyGet(pn, ns, name, memberTypeFlags);

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[228]++;
            decompiler.addToken(Token.LB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[620]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[621]++;
            Node expr = transform(((XmlElemRef)node).getExpression());
            decompiler.addToken(Token.RB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[622]++;
            return createElementGet(pn, ns, expr, memberTypeFlags);
        }
    }

    private Node transformDefaultXmlNamepace(UnaryExpression node) {
        decompiler.addToken(Token.DEFAULT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[623]++;
        decompiler.addName(" xml");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[624]++;
        decompiler.addName(" namespace");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[625]++;
        decompiler.addToken(Token.ASSIGN);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[626]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[627]++;
        Node child = transform(node.getOperand());
        return createUnary(Token.DEFAULTNAMESPACE, child);
    }

    /**
     * If caseExpression argument is null it indicates a default label.
     */
    private void addSwitchCase(Node switchBlock, Node caseExpression,
                               Node statements)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[628]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((switchBlock.getType() != Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[229]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[230]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[629]++;
        Jump switchNode = (Jump)switchBlock.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[630]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((switchNode.getType() != Token.SWITCH) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[231]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[232]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[631]++;

        Node gotoTarget = Node.newTarget();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[632]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((caseExpression != null) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[233]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[633]++;
            Jump caseNode = new Jump(Token.CASE, caseExpression);
            caseNode.target = gotoTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[634]++;
            switchNode.addChildToBack(caseNode);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[635]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[234]++;
            switchNode.setDefault(gotoTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[636]++;
        }
        switchBlock.addChildToBack(gotoTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[637]++;
        switchBlock.addChildToBack(statements);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[638]++;
    }

    private void closeSwitch(Node switchBlock)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[639]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((switchBlock.getType() != Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[235]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[236]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[640]++;
        Jump switchNode = (Jump)switchBlock.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[641]++;
int CodeCoverConditionCoverageHelper_C108;
        if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((switchNode.getType() != Token.SWITCH) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[237]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[238]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[642]++;

        Node switchBreakTarget = Node.newTarget();
        // switchNode.target is only used by NodeTransformer
        // to detect switch end
        switchNode.target = switchBreakTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[643]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[644]++;

        Node defaultTarget = switchNode.getDefault();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[645]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((defaultTarget == null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[239]++;
            defaultTarget = switchBreakTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[646]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[240]++;}

        switchBlock.addChildAfter(makeJump(Token.GOTO, defaultTarget),
                                  switchNode);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[647]++;
        switchBlock.addChildToBack(switchBreakTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[648]++;
    }

    private Node createExprStatementNoReturn(Node expr, int lineno) {
        return new Node(Token.EXPR_VOID, expr, lineno);
    }

    private Node createString(String string) {
        return Node.newString(string);
    }

    /**
     * Catch clause of try/catch/finally
     * @param varName the name of the variable to bind to the exception
     * @param catchCond the condition under which to catch the exception.
     *                  May be null if no condition is given.
     * @param stmts the statements in the catch clause
     * @param lineno the starting line number of the catch clause
     */
    private Node createCatch(String varName, Node catchCond, Node stmts,
                             int lineno) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[649]++;
int CodeCoverConditionCoverageHelper_C110;
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((catchCond == null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[241]++;
            catchCond = new Node(Token.EMPTY);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[650]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[242]++;}
        return new Node(Token.CATCH, createName(varName),
                        catchCond, stmts, lineno);
    }

    private Node initFunction(FunctionNode fnNode, int functionIndex,
                              Node statements, int functionType) {
        fnNode.setFunctionType(functionType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[651]++;
        fnNode.addChildToBack(statements);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[652]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[653]++;

        int functionCount = fnNode.getFunctionCount();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[654]++;
int CodeCoverConditionCoverageHelper_C111;
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((functionCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[243]++;
            // Functions containing other functions require activation objects
            fnNode.setRequiresActivation();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[655]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[244]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[656]++;
int CodeCoverConditionCoverageHelper_C112;

        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((functionType == FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[245]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[657]++;
            Name name = fnNode.getFunctionName();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[658]++;
int CodeCoverConditionCoverageHelper_C113;
            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (32)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C113 |= (8)) == 0 || true) &&
 ((name.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((fnNode.getSymbol(name.getIdentifier()) == null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 3) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[247]++;
                // A function expression needs to have its name as a
                // variable (if it isn't already allocated as a variable).
                // See ECMA Ch. 13.  We add code to the beginning of the
                // function to initialize a local variable of the
                // function's name to the function value, but only if the
                // function doesn't already define a formal parameter, var,
                // or nested function with the same name.
                fnNode.putSymbol(new Symbol(Token.FUNCTION, name.getIdentifier()));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[659]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[660]++;
                Node setFn = new Node(Token.EXPR_VOID,
                                 new Node(Token.SETNAME,
                                          Node.newString(Token.BINDNAME,
                                                         name.getIdentifier()),
                                     new Node(Token.THISFN)));
                statements.addChildrenToFront(setFn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[661]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[248]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[246]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[662]++;

        // Add return to end if needed.
        Node lastStmt = statements.getLastChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[663]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (8)) == 0 || true) &&
 ((lastStmt == null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((lastStmt.getType() != Token.RETURN) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[249]++;
            statements.addChildToBack(new Node(Token.RETURN));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[664]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[250]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[665]++;

        Node result = Node.newString(Token.FUNCTION, fnNode.getName());
        result.putIntProp(Node.FUNCTION_PROP, functionIndex);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[666]++;
        return result;
    }

    /**
     * Create loop node. The code generator will later call
     * createWhile|createDoWhile|createFor|createForIn
     * to finish loop generation.
     */
    private Scope createLoopNode(Node loopLabel, int lineno) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[667]++;
        Scope result = createScopeNode(Token.LOOP, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[668]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((loopLabel != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[251]++;
            ((Jump)loopLabel).setLoop(result);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[669]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[252]++;}
        return result;
    }

    private Node createFor(Scope loop, Node init,
                           Node test, Node incr, Node body) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[670]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((init.getType() == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[253]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[671]++;
            // rewrite "for (let i=s; i < N; i++)..." as
            // "let (i=s) { for (; i < N; i++)..." so that "s" is evaluated
            // outside the scope of the for.
            Scope let = Scope.splitScope(loop);
            let.setType(Token.LET);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[672]++;
            let.addChildrenToBack(init);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[673]++;
            let.addChildToBack(createLoop(loop, LOOP_FOR, body, test,
                new Node(Token.EMPTY), incr));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[674]++;
            return let;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[254]++;}
        return createLoop(loop, LOOP_FOR, body, test, init, incr);
    }

    private Node createLoop(Jump loop, int loopType, Node body,
                            Node cond, Node init, Node incr)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[675]++;
        Node bodyTarget = Node.newTarget();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[676]++;
        Node condTarget = Node.newTarget();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[677]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (8)) == 0 || true) &&
 ((loopType == LOOP_FOR) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((cond.getType() == Token.EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[255]++;
            cond = new Node(Token.TRUE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[678]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[256]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[679]++;
        Jump IFEQ = new Jump(Token.IFEQ, cond);
        IFEQ.target = bodyTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[680]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[681]++;
        Node breakTarget = Node.newTarget();

        loop.addChildToBack(bodyTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[682]++;
        loop.addChildrenToBack(body);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[683]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[684]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((loopType == LOOP_WHILE) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((loopType == LOOP_FOR) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[257]++;
            // propagate lineno to condition
            loop.addChildrenToBack(new Node(Token.EMPTY, loop.getLineno()));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[685]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[258]++;}
        loop.addChildToBack(condTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[686]++;
        loop.addChildToBack(IFEQ);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[687]++;
        loop.addChildToBack(breakTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[688]++;

        loop.target = breakTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[689]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[690]++;
        Node continueTarget = condTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[691]++;
int CodeCoverConditionCoverageHelper_C119;

        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (8)) == 0 || true) &&
 ((loopType == LOOP_WHILE) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((loopType == LOOP_FOR) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[259]++;
            // Just add a GOTO to the condition in the do..while
            loop.addChildToFront(makeJump(Token.GOTO, condTarget));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[692]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[693]++;
int CodeCoverConditionCoverageHelper_C120;

            if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((loopType == LOOP_FOR) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[261]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[694]++;
                int initType = init.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[695]++;
int CodeCoverConditionCoverageHelper_C121;
                if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((initType != Token.EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[263]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[696]++;
int CodeCoverConditionCoverageHelper_C122;
                    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (8)) == 0 || true) &&
 ((initType != Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((initType != Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[265]++;
                        init = new Node(Token.EXPR_VOID, init);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[697]++;

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[266]++;}
                    loop.addChildToFront(init);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[698]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[264]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[699]++;
                Node incrTarget = Node.newTarget();
                loop.addChildAfter(incrTarget, body);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[700]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[701]++;
int CodeCoverConditionCoverageHelper_C123;
                if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((incr.getType() != Token.EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[267]++;
                    incr = new Node(Token.EXPR_VOID, incr);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[702]++;
                    loop.addChildAfter(incr, incrTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[703]++;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[268]++;}
                continueTarget = incrTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[704]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[262]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[260]++;}

        loop.setContinue(continueTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[705]++;
        return loop;
    }

    /**
     * Generate IR for a for..in loop.
     */
    private Node createForIn(int declType, Node loop, Node lhs,
                             Node obj, Node body, boolean isForEach)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[706]++;
        int destructuring = -1;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[707]++;
        int destructuringLen = 0;
        Node lvalue;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[708]++;
        int type = lhs.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[709]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (8)) == 0 || true) &&
 ((type == Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((type == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[269]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[710]++;
            Node kid = lhs.getLastChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[711]++;
            int kidType = kid.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[712]++;
int CodeCoverConditionCoverageHelper_C125;
            if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((kidType == Token.ARRAYLIT) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((kidType == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) && false))
            {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[271]++;
                type = destructuring = kidType;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[713]++;
                lvalue = kid;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[714]++;
                destructuringLen = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[715]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[716]++;
int CodeCoverConditionCoverageHelper_C126;
                if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((kid instanceof ArrayLiteral) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[273]++;
                    destructuringLen = ((ArrayLiteral) kid).getDestructuringLength();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[717]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[274]++;}

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[272]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[718]++;
int CodeCoverConditionCoverageHelper_C127; if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((kidType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[275]++;
                lvalue = Node.newString(Token.NAME, kid.getString());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[719]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[276]++;
                reportError("msg.bad.for.in.lhs");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[720]++;
                return null;
            }
}

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[270]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[721]++;
int CodeCoverConditionCoverageHelper_C128; if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((type == Token.ARRAYLIT) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((type == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[277]++;
            destructuring = type;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[722]++;
            lvalue = lhs;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[723]++;
            destructuringLen = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[724]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[725]++;
int CodeCoverConditionCoverageHelper_C129;
            if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((lhs instanceof ArrayLiteral) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[279]++;
                destructuringLen = ((ArrayLiteral) lhs).getDestructuringLength();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[726]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[280]++;}

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[278]++;
            lvalue = makeReference(lhs);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[727]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[728]++;
int CodeCoverConditionCoverageHelper_C130;
            if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((lvalue == null) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[281]++;
                reportError("msg.bad.for.in.lhs");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[729]++;
                return null;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[282]++;}
        }
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[730]++;

        Node localBlock = new Node(Token.LOCAL_BLOCK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[731]++;
        int initType = isForEach ? Token.ENUM_INIT_VALUES
                                 : (destructuring != -1
                                    ? Token.ENUM_INIT_ARRAY
                                    : Token.ENUM_INIT_KEYS);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[732]++;
        Node init = new Node(initType, obj);
        init.putProp(Node.LOCAL_BLOCK_PROP, localBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[733]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[734]++;
        Node cond = new Node(Token.ENUM_NEXT);
        cond.putProp(Node.LOCAL_BLOCK_PROP, localBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[735]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[736]++;
        Node id = new Node(Token.ENUM_ID);
        id.putProp(Node.LOCAL_BLOCK_PROP, localBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[737]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[738]++;

        Node newBody = new Node(Token.BLOCK);
        Node assign;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[739]++;
int CodeCoverConditionCoverageHelper_C131;
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((destructuring != -1) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[283]++;
            assign = createDestructuringAssignment(declType, lvalue, id);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[740]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[741]++;
int CodeCoverConditionCoverageHelper_C132;
            if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C132 |= (32)) == 0 || true) &&
 ((isForEach) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C132 |= (8)) == 0 || true) &&
 ((destructuring == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((destructuringLen != 2) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 3) && false))
            {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[285]++;
                // destructuring assignment is only allowed in for..each or
                // with an array type of length 2 (to hold key and value)
                reportError("msg.bad.for.in.destruct");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[742]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[286]++;}

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[284]++;
            assign = simpleAssignment(lvalue, id);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[743]++;
        }
        newBody.addChildToBack(new Node(Token.EXPR_VOID, assign));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[744]++;
        newBody.addChildToBack(body);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[745]++;

        loop = createLoop((Jump)loop, LOOP_WHILE, newBody, cond, null, null);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[746]++;
        loop.addChildToFront(init);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[747]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[748]++;
int CodeCoverConditionCoverageHelper_C133;
        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (8)) == 0 || true) &&
 ((type == Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((type == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[287]++;
            loop.addChildToFront(lhs);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[749]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[288]++;}
        localBlock.addChildToBack(loop);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[750]++;

        return localBlock;
    }

    /**
     * Try/Catch/Finally
     *
     * The IRFactory tries to express as much as possible in the tree;
     * the responsibilities remaining for Codegen are to add the Java
     * handlers: (Either (but not both) of TARGET and FINALLY might not
     * be defined)
     *
     * - a catch handler for javascript exceptions that unwraps the
     * exception onto the stack and GOTOes to the catch target
     *
     * - a finally handler
     *
     * ... and a goto to GOTO around these handlers.
     */
    private Node createTryCatchFinally(Node tryBlock, Node catchBlocks,
                                       Node finallyBlock, int lineno)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[751]++;
        boolean hasFinally = (finallyBlock != null)
                             && (finallyBlock.getType() != Token.BLOCK
                                 || finallyBlock.hasChildren());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[752]++;
int CodeCoverConditionCoverageHelper_C134;

        // short circuit
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (32)) == 0 || true) &&
 ((tryBlock.getType() == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C134 |= (8)) == 0 || true) &&
 ((tryBlock.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((hasFinally) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 3) && false))
        {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[289]++;
            return tryBlock;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[290]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[753]++;

        boolean hasCatch = catchBlocks.hasChildren();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[754]++;
int CodeCoverConditionCoverageHelper_C135;

        // short circuit
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C135 |= (8)) == 0 || true) &&
 ((hasFinally) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((hasCatch) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 2) && false))  {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[291]++;
            // bc finally might be an empty block...
            return tryBlock;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[292]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[755]++;

        Node handlerBlock  = new Node(Token.LOCAL_BLOCK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[756]++;
        Jump pn = new Jump(Token.TRY, tryBlock, lineno);
        pn.putProp(Node.LOCAL_BLOCK_PROP, handlerBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[757]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[758]++;
int CodeCoverConditionCoverageHelper_C136;

        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((hasCatch) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[293]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[759]++;
            // jump around catch code
            Node endCatch = Node.newTarget();
            pn.addChildToBack(makeJump(Token.GOTO, endCatch));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[760]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[761]++;

            // make a TARGET for the catch that the tcf node knows about
            Node catchTarget = Node.newTarget();
            pn.target = catchTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[762]++;
            // mark it
            pn.addChildToBack(catchTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[763]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[764]++;

            //
            //  Given
            //
            //   try {
            //       tryBlock;
            //   } catch (e if condition1) {
            //       something1;
            //   ...
            //
            //   } catch (e if conditionN) {
            //       somethingN;
            //   } catch (e) {
            //       somethingDefault;
            //   }
            //
            //  rewrite as
            //
            //   try {
            //       tryBlock;
            //       goto after_catch:
            //   } catch (x) {
            //       with (newCatchScope(e, x)) {
            //           if (condition1) {
            //               something1;
            //               goto after_catch;
            //           }
            //       }
            //   ...
            //       with (newCatchScope(e, x)) {
            //           if (conditionN) {
            //               somethingN;
            //               goto after_catch;
            //           }
            //       }
            //       with (newCatchScope(e, x)) {
            //           somethingDefault;
            //           goto after_catch;
            //       }
            //   }
            // after_catch:
            //
            // If there is no default catch, then the last with block
            // arround  "somethingDefault;" is replaced by "rethrow;"

            // It is assumed that catch handler generation will store
            // exeception object in handlerBlock register

            // Block with local for exception scope objects
            Node catchScopeBlock = new Node(Token.LOCAL_BLOCK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[765]++;

            // expects catchblocks children to be (cond block) pairs.
            Node cb = catchBlocks.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[766]++;
            boolean hasDefault = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[767]++;
            int scopeIndex = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[768]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[67]++;


int CodeCoverConditionCoverageHelper_C137;
            while ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((cb != null) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[67]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[68]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[69]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[769]++;
                int catchLineNo = cb.getLineno();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[770]++;

                Node name = cb.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[771]++;
                Node cond = name.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[772]++;
                Node catchStatement = cond.getNext();
                cb.removeChild(name);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[773]++;
                cb.removeChild(cond);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[774]++;
                cb.removeChild(catchStatement);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[775]++;

                // Add goto to the catch statement to jump out of catch
                // but prefix it with LEAVEWITH since try..catch produces
                // "with"code in order to limit the scope of the exception
                // object.
                catchStatement.addChildToBack(new Node(Token.LEAVEWITH));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[776]++;
                catchStatement.addChildToBack(makeJump(Token.GOTO, endCatch));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[777]++;

                // Create condition "if" when present
                Node condStmt;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[778]++;
int CodeCoverConditionCoverageHelper_C138;
                if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((cond.getType() == Token.EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[295]++;
                    condStmt = catchStatement;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[779]++;
                    hasDefault = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[780]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[296]++;
                    condStmt = createIf(cond, catchStatement, null,
                                        catchLineNo);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[781]++;
                }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[782]++;

                // Generate code to create the scope object and store
                // it in catchScopeBlock register
                Node catchScope = new Node(Token.CATCH_SCOPE, name,
                                           createUseLocal(handlerBlock));
                catchScope.putProp(Node.LOCAL_BLOCK_PROP, catchScopeBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[783]++;
                catchScope.putIntProp(Node.CATCH_SCOPE_PROP, scopeIndex);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[784]++;
                catchScopeBlock.addChildToBack(catchScope);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[785]++;

                // Add with statement based on catch scope object
                catchScopeBlock.addChildToBack(
                    createWith(createUseLocal(catchScopeBlock), condStmt,
                               catchLineNo));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[786]++;

                // move to next cb
                cb = cb.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[787]++;
                ++scopeIndex;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[788]++;
            }
            pn.addChildToBack(catchScopeBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[789]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[790]++;
int CodeCoverConditionCoverageHelper_C139;
            if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((hasDefault) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[297]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[791]++;
                // Generate code to rethrow if no catch clause was executed
                Node rethrow = new Node(Token.RETHROW);
                rethrow.putProp(Node.LOCAL_BLOCK_PROP, handlerBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[792]++;
                pn.addChildToBack(rethrow);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[793]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[298]++;}

            pn.addChildToBack(endCatch);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[794]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[294]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[795]++;
int CodeCoverConditionCoverageHelper_C140;

        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((hasFinally) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[299]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[796]++;
            Node finallyTarget = Node.newTarget();
            pn.setFinally(finallyTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[797]++;

            // add jsr finally to the try block
            pn.addChildToBack(makeJump(Token.JSR, finallyTarget));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[798]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[799]++;

            // jump around finally code
            Node finallyEnd = Node.newTarget();
            pn.addChildToBack(makeJump(Token.GOTO, finallyEnd));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[800]++;

            pn.addChildToBack(finallyTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[801]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[802]++;
            Node fBlock = new Node(Token.FINALLY, finallyBlock);
            fBlock.putProp(Node.LOCAL_BLOCK_PROP, handlerBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[803]++;
            pn.addChildToBack(fBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[804]++;

            pn.addChildToBack(finallyEnd);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[805]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[300]++;}
        handlerBlock.addChildToBack(pn);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[806]++;
        return handlerBlock;
    }

    private Node createWith(Node obj, Node body, int lineno) {
        setRequiresActivation();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[807]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[808]++;
        Node result = new Node(Token.BLOCK, lineno);
        result.addChildToBack(new Node(Token.ENTERWITH, obj));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[809]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[810]++;
        Node bodyNode = new Node(Token.WITH, body, lineno);
        result.addChildrenToBack(bodyNode);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[811]++;
        result.addChildToBack(new Node(Token.LEAVEWITH));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[812]++;
        return result;
    }

    private Node createIf(Node cond, Node ifTrue, Node ifFalse, int lineno)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[813]++;
        int condStatus = isAlwaysDefinedBoolean(cond);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[814]++;
int CodeCoverConditionCoverageHelper_C141;
        if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((condStatus == ALWAYS_TRUE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[301]++;
            return ifTrue;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[302]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[815]++;
int CodeCoverConditionCoverageHelper_C142; if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((condStatus == ALWAYS_FALSE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[303]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[816]++;
int CodeCoverConditionCoverageHelper_C143;
            if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((ifFalse != null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[305]++;
                return ifFalse;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[306]++;}
            // Replace if (false) xxx by empty block
            return new Node(Token.BLOCK, lineno);

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[304]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[817]++;

        Node result = new Node(Token.BLOCK, lineno);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[818]++;
        Node ifNotTarget = Node.newTarget();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[819]++;
        Jump IFNE = new Jump(Token.IFNE, cond);
        IFNE.target = ifNotTarget;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[820]++;

        result.addChildToBack(IFNE);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[821]++;
        result.addChildrenToBack(ifTrue);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[822]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[823]++;
int CodeCoverConditionCoverageHelper_C144;

        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((ifFalse != null) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[307]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[824]++;
            Node endTarget = Node.newTarget();
            result.addChildToBack(makeJump(Token.GOTO, endTarget));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[825]++;
            result.addChildToBack(ifNotTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[826]++;
            result.addChildrenToBack(ifFalse);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[827]++;
            result.addChildToBack(endTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[828]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[308]++;
            result.addChildToBack(ifNotTarget);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[829]++;
        }

        return result;
    }

    private Node createCondExpr(Node cond, Node ifTrue, Node ifFalse) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[830]++;
        int condStatus = isAlwaysDefinedBoolean(cond);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[831]++;
int CodeCoverConditionCoverageHelper_C145;
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((condStatus == ALWAYS_TRUE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[309]++;
            return ifTrue;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[310]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[832]++;
int CodeCoverConditionCoverageHelper_C146; if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((condStatus == ALWAYS_FALSE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[311]++;
            return ifFalse;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[312]++;}
}
        return new Node(Token.HOOK, cond, ifTrue, ifFalse);
    }

    private Node createUnary(int nodeType, Node child)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[833]++;
        int childType = child.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[834]++;
        switch (nodeType) {
          case Token.DELPROP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[313]++; {
            Node n;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[835]++;
int CodeCoverConditionCoverageHelper_C147;
            if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((childType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[314]++;
                // Transform Delete(Name "a")
                //  to Delete(Bind("a"), String("a"))
                child.setType(Token.BINDNAME);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[836]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[837]++;
                Node left = child;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[838]++;
                Node right = Node.newString(child.getString());
                n = new Node(nodeType, left, right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[839]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[315]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[840]++;
int CodeCoverConditionCoverageHelper_C148; if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (8)) == 0 || true) &&
 ((childType == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((childType == Token.GETELEM) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 2) && false))
            {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[316]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[841]++;
                Node left = child.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[842]++;
                Node right = child.getLastChild();
                child.removeChild(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[843]++;
                child.removeChild(right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[844]++;
                n = new Node(nodeType, left, right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[845]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[317]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[846]++;
int CodeCoverConditionCoverageHelper_C149; if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((childType == Token.GET_REF) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[318]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[847]++;
                Node ref = child.getFirstChild();
                child.removeChild(ref);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[848]++;
                n = new Node(Token.DEL_REF, ref);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[849]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[319]++;
                // Always evaluate delete operand, see ES5 11.4.1 & bug #726121
                n = new Node(nodeType, new Node(Token.TRUE), child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[850]++;
            }
}
}
            return n;
          }
          case Token.TYPEOF:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[320]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[851]++;
int CodeCoverConditionCoverageHelper_C150;
            if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((childType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[321]++;
                child.setType(Token.TYPEOFNAME);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[852]++;
                return child;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[322]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[853]++;
            break;
          case Token.BITNOT:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[323]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[854]++;
int CodeCoverConditionCoverageHelper_C151;
            if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((childType == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[324]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[855]++;
                int value = ScriptRuntime.toInt32(child.getDouble());
                child.setDouble(~value);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[856]++;
                return child;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[325]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[857]++;
            break;
          case Token.NEG:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[326]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[858]++;
int CodeCoverConditionCoverageHelper_C152;
            if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((childType == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[327]++;
                child.setDouble(-child.getDouble());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[859]++;
                return child;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[328]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[860]++;
            break;
          case Token.NOT:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[329]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[861]++;
            int status = isAlwaysDefinedBoolean(child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[862]++;
int CodeCoverConditionCoverageHelper_C153;
            if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((status != 0) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[330]++;
                int type;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[863]++;
int CodeCoverConditionCoverageHelper_C154;
                if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((status == ALWAYS_TRUE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[332]++;
                    type = Token.FALSE;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[864]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[333]++;
                    type = Token.TRUE;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[865]++;
                }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[866]++;
int CodeCoverConditionCoverageHelper_C155;
                if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (8)) == 0 || true) &&
 ((childType == Token.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((childType == Token.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[334]++;
                    child.setType(type);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[867]++;
                    return child;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[335]++;}
                return new Node(type);

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[331]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[868]++;
            break;
          } default : CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[336]++;
        }
        return new Node(nodeType, child);
    }

    private Node createCallOrNew(int nodeType, Node child) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[869]++;
        int type = Node.NON_SPECIALCALL;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[870]++;
int CodeCoverConditionCoverageHelper_C156;
        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((child.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[337]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[871]++;
            String name = child.getString();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[872]++;
int CodeCoverConditionCoverageHelper_C157;
            if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((name.equals("eval")) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[339]++;
                type = Node.SPECIALCALL_EVAL;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[873]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[340]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[874]++;
int CodeCoverConditionCoverageHelper_C158; if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((name.equals("With")) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[341]++;
                type = Node.SPECIALCALL_WITH;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[875]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[342]++;}
}

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[338]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[876]++;
int CodeCoverConditionCoverageHelper_C159; if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((child.getType() == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[343]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[877]++;
            String name = child.getLastChild().getString();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[878]++;
int CodeCoverConditionCoverageHelper_C160;
            if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((name.equals("eval")) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[345]++;
                type = Node.SPECIALCALL_EVAL;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[879]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[346]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[344]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[880]++;
        Node node = new Node(nodeType, child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[881]++;
int CodeCoverConditionCoverageHelper_C161;
        if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((type != Node.NON_SPECIALCALL) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[347]++;
            // Calls to these functions require activation objects.
            setRequiresActivation();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[882]++;
            node.putIntProp(Node.SPECIALCALL_PROP, type);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[883]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[348]++;}
        return node;
    }

    private Node createIncDec(int nodeType, boolean post, Node child)
    {
        child = makeReference(child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[884]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[885]++;
        int childType = child.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[886]++;

        switch (childType) {
          case Token.NAME:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[349]++;
          case Token.GETPROP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[350]++;
          case Token.GETELEM:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[351]++;
          case Token.GET_REF:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[352]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[887]++;
            Node n = new Node(nodeType, child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[888]++;
            int incrDecrMask = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[889]++;
int CodeCoverConditionCoverageHelper_C162;
            if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((nodeType == Token.DEC) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[353]++;
                incrDecrMask |= Node.DECR_FLAG;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[890]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[354]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[891]++;
int CodeCoverConditionCoverageHelper_C163;
            if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[355]++;
                incrDecrMask |= Node.POST_FLAG;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[892]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[356]++;}
            n.putIntProp(Node.INCRDECR_PROP, incrDecrMask);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[893]++;
            return n;
          } default : CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[357]++;
        }
        throw Kit.codeBug();
    }

    private Node createPropertyGet(Node target, String namespace, String name,
                                   int memberTypeFlags)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[894]++;
int CodeCoverConditionCoverageHelper_C164;
        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (8)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((memberTypeFlags == 0) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[358]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[895]++;
int CodeCoverConditionCoverageHelper_C165;
            if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((target == null) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[360]++;
                return createName(name);

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[361]++;}
            checkActivationName(name, Token.GETPROP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[896]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[897]++;
int CodeCoverConditionCoverageHelper_C166;
            if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isSpecialProperty(name)) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[362]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[898]++;
                Node ref = new Node(Token.REF_SPECIAL, target);
                ref.putProp(Node.NAME_PROP, name);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[899]++;
                return new Node(Token.GET_REF, ref);

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[363]++;}
            return new Node(Token.GETPROP, target, Node.newString(name));

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[359]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[900]++;
        Node elem = Node.newString(name);
        memberTypeFlags |= Node.PROPERTY_FLAG;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[901]++;
        return createMemberRefGet(target, namespace, elem, memberTypeFlags);
    }

    /**
     * @param target the node before the LB
     * @param namespace optional namespace
     * @param elem the node in the brackets
     * @param memberTypeFlags E4X flags
     */
    private Node createElementGet(Node target, String namespace, Node elem,
                                  int memberTypeFlags)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[902]++;
int CodeCoverConditionCoverageHelper_C167;
        // OPT: could optimize to createPropertyGet
        // iff elem is string that can not be number
        if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (8)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((memberTypeFlags == 0) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[364]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[903]++;
int CodeCoverConditionCoverageHelper_C168;
            // stand-alone [aaa] as primary expression is array literal
            // declaration and should not come here!
            if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((target == null) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[366]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[367]++;}
            return new Node(Token.GETELEM, target, elem);

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[365]++;}
        return createMemberRefGet(target, namespace, elem, memberTypeFlags);
    }

    private Node createMemberRefGet(Node target, String namespace, Node elem,
                                    int memberTypeFlags)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[904]++;
        Node nsNode = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[905]++;
int CodeCoverConditionCoverageHelper_C169;
        if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((namespace != null) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[368]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[906]++;
int CodeCoverConditionCoverageHelper_C170;
            // See 11.1.2 in ECMA 357
            if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((namespace.equals("*")) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[370]++;
                nsNode = new Node(Token.NULL);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[907]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[371]++;
                nsNode = createName(namespace);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[908]++;
            }

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[369]++;}
        Node ref;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[909]++;
int CodeCoverConditionCoverageHelper_C171;
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((target == null) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[372]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[910]++;
int CodeCoverConditionCoverageHelper_C172;
            if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[374]++;
                ref = new Node(Token.REF_NAME, elem);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[911]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[375]++;
                ref = new Node(Token.REF_NS_NAME, nsNode, elem);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[912]++;
            }

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[373]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[913]++;
int CodeCoverConditionCoverageHelper_C173;
            if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((namespace == null) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[376]++;
                ref = new Node(Token.REF_MEMBER, target, elem);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[914]++;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[377]++;
                ref = new Node(Token.REF_NS_MEMBER, target, nsNode, elem);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[915]++;
            }
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[916]++;
int CodeCoverConditionCoverageHelper_C174;
        if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((memberTypeFlags != 0) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[378]++;
            ref.putIntProp(Node.MEMBER_TYPE_PROP, memberTypeFlags);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[917]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[379]++;}
        return new Node(Token.GET_REF, ref);
    }

    private Node createBinary(int nodeType, Node left, Node right) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[918]++;
        switch (nodeType) {

          case Token.ADD:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[380]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[919]++;
int CodeCoverConditionCoverageHelper_C175;
            // numerical addition and string concatenation
            if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((left.type == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[381]++;
                String s2;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[920]++;
int CodeCoverConditionCoverageHelper_C176;
                if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((right.type == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[383]++;
                    s2 = right.getString();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[921]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[384]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[922]++;
int CodeCoverConditionCoverageHelper_C177; if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((right.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[385]++;
                    s2 = ScriptRuntime.numberToString(right.getDouble(), 10);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[923]++;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[386]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[924]++;
                    break;
                }
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[925]++;
                String s1 = left.getString();
                left.setString(s1.concat(s2));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[926]++;
                return left;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[382]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[927]++;
int CodeCoverConditionCoverageHelper_C178; if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((left.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[387]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[928]++;
int CodeCoverConditionCoverageHelper_C179;
                if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((right.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[389]++;
                    left.setDouble(left.getDouble() + right.getDouble());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[929]++;
                    return left;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[390]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[930]++;
int CodeCoverConditionCoverageHelper_C180; if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((right.type == Token.STRING) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[391]++;
                    String s1, s2;
                    s1 = ScriptRuntime.numberToString(left.getDouble(), 10);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[931]++;
                    s2 = right.getString();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[932]++;
                    right.setString(s1.concat(s2));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[933]++;
                    return right;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[392]++;}
}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[388]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[934]++;
            // can't do anything if we don't know  both types - since
            // 0 + object is supposed to call toString on the object and do
            // string concantenation rather than addition
            break;

          case Token.SUB:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[393]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[935]++;
int CodeCoverConditionCoverageHelper_C181;
            // numerical subtraction
            if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((left.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[394]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[936]++;
                double ld = left.getDouble();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[937]++;
int CodeCoverConditionCoverageHelper_C182;
                if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((right.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[396]++;
                    //both numbers
                    left.setDouble(ld - right.getDouble());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[938]++;
                    return left;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[397]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[939]++;
int CodeCoverConditionCoverageHelper_C183; if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((ld == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[398]++;
                    // first 0: 0-x -> -x
                    return new Node(Token.NEG, right);

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[399]++;}
}

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[395]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[940]++;
int CodeCoverConditionCoverageHelper_C184; if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((right.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[400]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[941]++;
int CodeCoverConditionCoverageHelper_C185;
                if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((right.getDouble() == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[402]++;
                    //second 0: x - 0 -> +x
                    // can not make simply x because x - 0 must be number
                    return new Node(Token.POS, left);

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[403]++;}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[401]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[942]++;
            break;

          case Token.MUL:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[404]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[943]++;
int CodeCoverConditionCoverageHelper_C186;
            // numerical multiplication
            if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((left.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[405]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[944]++;
                double ld = left.getDouble();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[945]++;
int CodeCoverConditionCoverageHelper_C187;
                if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((right.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[407]++;
                    //both numbers
                    left.setDouble(ld * right.getDouble());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[946]++;
                    return left;

                } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[408]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[947]++;
int CodeCoverConditionCoverageHelper_C188; if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((ld == 1.0) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[409]++;
                    // first 1: 1 *  x -> +x
                    return new Node(Token.POS, right);

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[410]++;}
}

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[406]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[948]++;
int CodeCoverConditionCoverageHelper_C189; if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((right.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[411]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[949]++;
int CodeCoverConditionCoverageHelper_C190;
                if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((right.getDouble() == 1.0) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[413]++;
                    //second 1: x * 1 -> +x
                    // can not make simply x because x - 0 must be number
                    return new Node(Token.POS, left);

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[414]++;}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[412]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[950]++;
            // can't do x*0: Infinity * 0 gives NaN, not 0
            break;

          case Token.DIV:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[415]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[951]++;
int CodeCoverConditionCoverageHelper_C191;
            // number division
            if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((right.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[416]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[952]++;
                double rd = right.getDouble();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[953]++;
int CodeCoverConditionCoverageHelper_C192;
                if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((left.type == Token.NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[418]++;
                    // both constants -- just divide, trust Java to handle x/0
                    left.setDouble(left.getDouble() / rd);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[954]++;
                    return left;

               } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[419]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[955]++;
int CodeCoverConditionCoverageHelper_C193; if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((rd == 1.0) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[420]++;
                    // second 1: x/1 -> +x
                    // not simply x to force number convertion
                    return new Node(Token.POS, left);

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[421]++;}
}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[417]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[956]++;
            break;

          case Token.AND:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[422]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[957]++;
            // Since x && y gives x, not false, when Boolean(x) is false,
            // and y, not Boolean(y), when Boolean(x) is true, x && y
            // can only be simplified if x is defined. See bug 309957.

            int leftStatus = isAlwaysDefinedBoolean(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[958]++;
int CodeCoverConditionCoverageHelper_C194;
            if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((leftStatus == ALWAYS_FALSE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[423]++;
                // if the first one is false, just return it
                return left;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[424]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[959]++;
int CodeCoverConditionCoverageHelper_C195; if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((leftStatus == ALWAYS_TRUE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[425]++;
                // if first is true, set to second
                return right;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[426]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[960]++;
            break;
          }

          case Token.OR:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[427]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[961]++;
            // Since x || y gives x, not true, when Boolean(x) is true,
            // and y, not Boolean(y), when Boolean(x) is false, x || y
            // can only be simplified if x is defined. See bug 309957.

            int leftStatus = isAlwaysDefinedBoolean(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[962]++;
int CodeCoverConditionCoverageHelper_C196;
            if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((leftStatus == ALWAYS_TRUE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[428]++;
                // if the first one is true, just return it
                return left;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[429]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[963]++;
int CodeCoverConditionCoverageHelper_C197; if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((leftStatus == ALWAYS_FALSE_BOOLEAN) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[430]++;
                // if first is false, set to second
                return right;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[431]++;}
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[964]++;
            break;
          } default : CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[432]++;
        }

        return new Node(nodeType, left, right);
    }

    private Node createAssignment(int assignType, Node left, Node right)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[965]++;
        Node ref = makeReference(left);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[966]++;
int CodeCoverConditionCoverageHelper_C198;
        if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((ref == null) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[433]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[967]++;
int CodeCoverConditionCoverageHelper_C199;
            if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (8)) == 0 || true) &&
 ((left.getType() == Token.ARRAYLIT) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((left.getType() == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 2) && false))
            {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[435]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[968]++;
int CodeCoverConditionCoverageHelper_C200;
                if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((assignType != Token.ASSIGN) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[437]++;
                    reportError("msg.bad.destruct.op");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[969]++;
                    return right;

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[438]++;}
                return createDestructuringAssignment(-1, left, right);

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[436]++;}
            reportError("msg.bad.assign.left");
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[970]++;
            return right;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[434]++;}
        left = ref;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[971]++;

        int assignOp;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[972]++;
        switch (assignType) {
          case Token.ASSIGN:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[439]++;
            return simpleAssignment(left, right);
          case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[440]++;  assignOp = Token.BITOR;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[973]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[974]++;  break;
          case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[441]++; assignOp = Token.BITXOR;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[975]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[976]++; break;
          case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[442]++; assignOp = Token.BITAND;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[977]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[978]++; break;
          case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[443]++;    assignOp = Token.LSH;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[979]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[980]++;    break;
          case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[444]++;    assignOp = Token.RSH;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[981]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[982]++;    break;
          case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[445]++;   assignOp = Token.URSH;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[983]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[984]++;   break;
          case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[446]++;    assignOp = Token.ADD;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[985]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[986]++;    break;
          case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[447]++;    assignOp = Token.SUB;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[987]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[988]++;    break;
          case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[448]++;    assignOp = Token.MUL;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[989]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[990]++;    break;
          case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[449]++;    assignOp = Token.DIV;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[991]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[992]++;    break;
          case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[450]++;    assignOp = Token.MOD;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[993]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[994]++;    break;
          default:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[451]++; throw Kit.codeBug();
        }
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[995]++;

        int nodeType = left.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[996]++;
        switch (nodeType) {
          case Token.NAME:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[452]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[997]++;
            Node op = new Node(assignOp, left, right);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[998]++;
            Node lvalueLeft = Node.newString(Token.BINDNAME, left.getString());
            return new Node(Token.SETNAME, lvalueLeft, op);
          }
          case Token.GETPROP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[453]++;
          case Token.GETELEM:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[454]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[999]++;
            Node obj = left.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1000]++;
            Node id = left.getLastChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1001]++;

            int type = nodeType == Token.GETPROP
                       ? Token.SETPROP_OP
                       : Token.SETELEM_OP;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1002]++;

            Node opLeft = new Node(Token.USE_STACK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1003]++;
            Node op = new Node(assignOp, opLeft, right);
            return new Node(type, obj, id, op);
          }
          case Token.GET_REF:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[455]++; {
            ref = left.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1004]++;
            checkMutableReference(ref);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1005]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1006]++;
            Node opLeft = new Node(Token.USE_STACK);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1007]++;
            Node op = new Node(assignOp, opLeft, right);
            return new Node(Token.SET_REF_OP, ref, op);
          } default : CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[456]++;
        }

        throw Kit.codeBug();
    }

    private Node createUseLocal(Node localBlock) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1008]++;
int CodeCoverConditionCoverageHelper_C201;
        if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((Token.LOCAL_BLOCK != localBlock.getType()) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[457]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[458]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1009]++;
        Node result = new Node(Token.LOCAL_LOAD);
        result.putProp(Node.LOCAL_BLOCK_PROP, localBlock);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1010]++;
        return result;
    }

    private Jump makeJump(int type, Node target) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1011]++;
        Jump n = new Jump(type);
        n.target = target;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1012]++;
        return n;
    }

    private Node makeReference(Node node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1013]++;
        int type = node.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1014]++;
        switch (type) {
          case Token.NAME:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[459]++;
          case Token.GETPROP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[460]++;
          case Token.GETELEM:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[461]++;
          case Token.GET_REF:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[462]++;
            return node;
          case Token.CALL:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[463]++;
            node.setType(Token.REF_CALL);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1015]++;
            return new Node(Token.GET_REF, node); default : CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[464]++;
        }
        // Signal caller to report error
        return null;
    }

    // Check if Node always mean true or false in boolean context
    private static int isAlwaysDefinedBoolean(Node node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1016]++;
        switch (node.getType()) {
          case Token.FALSE:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[465]++;
          case Token.NULL:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[466]++;
            return ALWAYS_FALSE_BOOLEAN;
          case Token.TRUE:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[467]++;
            return ALWAYS_TRUE_BOOLEAN;
          case Token.NUMBER:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[468]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1017]++;
            double num = node.getDouble();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1018]++;
int CodeCoverConditionCoverageHelper_C202;
            if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (8)) == 0 || true) &&
 ((num == num) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((num != 0.0) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[469]++;
                return ALWAYS_TRUE_BOOLEAN;

            } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[470]++;
                return ALWAYS_FALSE_BOOLEAN;
            }
          } default : CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[471]++;
        }
        return 0;
    }

    // Check if node is the target of a destructuring bind.
    boolean isDestructuring(Node n) {
        return n instanceof DestructuringForm
            && ((DestructuringForm)n).isDestructuring();
    }

    Node decompileFunctionHeader(FunctionNode fn) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1019]++;
        Node mexpr = null;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1020]++;
int CodeCoverConditionCoverageHelper_C203;
        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((fn.getFunctionName() != null) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[472]++;
            decompiler.addName(fn.getName());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1021]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[473]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1022]++;
int CodeCoverConditionCoverageHelper_C204; if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((fn.getMemberExprNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[474]++;
            mexpr = transform(fn.getMemberExprNode());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1023]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[475]++;}
}
        decompiler.addToken(Token.LP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1024]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1025]++;
        List<AstNode> params = fn.getParams();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1026]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[70]++;


int CodeCoverConditionCoverageHelper_C205;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((i < params.size()) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[70]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[71]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[72]++;
}
            decompile(params.get(i));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1027]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1028]++;
int CodeCoverConditionCoverageHelper_C206;
            if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((i < params.size() - 1) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[476]++;
                decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1029]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[477]++;}
        }
        decompiler.addToken(Token.RP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1030]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1031]++;
int CodeCoverConditionCoverageHelper_C207;
        if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((fn.isExpressionClosure()) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[478]++;
            decompiler.addEOL(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1032]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[479]++;}
        return mexpr;
    }

    void decompile(AstNode node) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1033]++;
        switch (node.getType()) {
          case Token.ARRAYLIT:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[480]++;
              decompileArrayLiteral((ArrayLiteral)node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1034]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1035]++;
              break;
          case Token.OBJECTLIT:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[481]++;
              decompileObjectLiteral((ObjectLiteral)node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1036]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1037]++;
              break;
          case Token.STRING:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[482]++;
              decompiler.addString(((StringLiteral)node).getValue());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1038]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1039]++;
              break;
          case Token.NAME:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[483]++;
              decompiler.addName(((Name)node).getIdentifier());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1040]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1041]++;
              break;
          case Token.NUMBER:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[484]++;
              decompiler.addNumber(((NumberLiteral)node).getNumber());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1042]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1043]++;
              break;
          case Token.GETPROP:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[485]++;
              decompilePropertyGet((PropertyGet)node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1044]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1045]++;
              break;
          case Token.EMPTY:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[486]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1046]++;
              break;
          case Token.GETELEM:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[487]++;
              decompileElementGet((ElementGet) node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1047]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1048]++;
              break;
          case Token.THIS:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[488]++;
              decompiler.addToken(node.getType());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1049]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1050]++;
              break;
          default:
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[489]++;
              Kit.codeBug("unexpected token: "
                          + Token.typeToName(node.getType()));
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1051]++;
        }
    }

    // used for destructuring forms, since we don't transform() them
    void decompileArrayLiteral(ArrayLiteral node) {
        decompiler.addToken(Token.LB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1052]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1053]++;
        List<AstNode> elems = node.getElements();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1054]++;
        int size = elems.size();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1055]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[73]++;


int CodeCoverConditionCoverageHelper_C208;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[73]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[74]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[75]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1056]++;
            AstNode elem = elems.get(i);
            decompile(elem);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1057]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1058]++;
int CodeCoverConditionCoverageHelper_C209;
            if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((i < size - 1) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[490]++;
                decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1059]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[491]++;}
        }
        decompiler.addToken(Token.RB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1060]++;
    }

    // only used for destructuring forms
    void decompileObjectLiteral(ObjectLiteral node) {
        decompiler.addToken(Token.LC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1061]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1062]++;
        List<ObjectProperty> props = node.getElements();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1063]++;
        int size = props.size();
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1064]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[76]++;


int CodeCoverConditionCoverageHelper_C210;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[76]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[77]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.loops[78]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1065]++;
            ObjectProperty prop = props.get(i);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1066]++;
            boolean destructuringShorthand =
                    Boolean.TRUE.equals(prop.getProp(Node.DESTRUCTURING_SHORTHAND));
            decompile(prop.getLeft());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1067]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1068]++;
int CodeCoverConditionCoverageHelper_C211;
            if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((destructuringShorthand) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[492]++;
                decompiler.addToken(Token.COLON);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1069]++;
                decompile(prop.getRight());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1070]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[493]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1071]++;
int CodeCoverConditionCoverageHelper_C212;
            if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((i < size - 1) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[494]++;
                decompiler.addToken(Token.COMMA);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1072]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.branches[495]++;}
        }
        decompiler.addToken(Token.RC);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1073]++;
    }

    // only used for destructuring forms
    void decompilePropertyGet(PropertyGet node) {
        decompile(node.getTarget());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1074]++;
        decompiler.addToken(Token.DOT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1075]++;
        decompile(node.getProperty());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1076]++;
    }

    // only used for destructuring forms
    void decompileElementGet(ElementGet node) {
        decompile(node.getTarget());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1077]++;
        decompiler.addToken(Token.LB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1078]++;
        decompile(node.getElement());
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1079]++;
        decompiler.addToken(Token.RB);
CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd.statements[1080]++;
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd ());
  }
    public static long[] statements = new long[1081];
    public static long[] branches = new long[496];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[213];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-IRFactory.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,2,1,1,2,2,2,1,1,2,1,2,2,1,1,2,1,1,1,3,2,3,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 212; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[79];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzgo895sozwfi5u7p8lw2zxd () {
    super("org.mozilla.javascript.RHINO-SRC-IRFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1080; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 495; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 212; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 78; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-IRFactory.java");
      for (int i = 1; i <= 1080; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 495; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 212; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 26; i++) {
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

