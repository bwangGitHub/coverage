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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NameReferenceGraph.Name;
import com.google.javascript.jscomp.NameReferenceGraph.Reference;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.JSType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Generate a nice HTML file describing the name reference graph.
 * For each declaration, list the sites where the declaration's name
 * is referenced, and list all the names that the declaration references.
 * For each, name exactly where use occurs in the source code.
 *
 * <p>This report should be useful both for internal compiler
 * developers and for engineers trying to understand running behavior
 * of their code or who want to understand why the compiler won't
 * move their code into a new module.
 *
 * @author bowdidge@google.com (Robert Bowdidge)
 */

final class NameReferenceGraphReport {
  static {
    CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.ping();
  }

  private NameReferenceGraph graph = null;
  {
    CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[1]++;
  }

  /**
   * Create a NameReferenceGraphReport object.
   *
   * @param g  name reference graph to describe in report.
   */
  NameReferenceGraphReport(NameReferenceGraph g) {
    this.graph = g;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[2]++;
  }

  /**
   * Generate a nice HTML file describing the name reference graph.
   * For each declaration, list the sites where the declaration's name
   * is referenced, and list all the names that the declaration references.
   * For each, name exactly where use occurs in the source code.
   *
   * <p>This report should be useful both for internal compiler
   * developers and for engineers trying to understand running
   * behavior of their code or who want to understand why
   * AbstractCompiler won't move their code into a new module.
   *
   * @return String containing the entire HTML for the report.
   */
  public String getHtmlReport() {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[3]++;
    StringBuilder builder = new StringBuilder();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[4]++;
    List<DiGraphNode<Name, Reference>> nodes = Lists.newArrayList(
        graph.getDirectedGraphNodes());

    generateHtmlReportHeader(builder);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[5]++;

    builder.append("<h1>Name Reference Graph Dump</h1>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[6]++;
    builder.append("OVERALL STATS\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[7]++;
    builder.append("<ul>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[8]++;
    builder.append("<li>Total names: " + nodes.size());
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[9]++;
    builder.append("</ul>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[10]++;

    builder.append("ALL NAMES\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[11]++;
    builder.append("<UL>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[12]++;

    // Sort declarations in alphabetical order.
    Collections.sort(nodes, new DiGraphNodeComparator());
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[13]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[1]++;



    for (DiGraphNode<Name, Reference> n : nodes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[1]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[2]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[3]++;
}
      // Generate the HTML describing the declaration itself.
      generateDeclarationReport(builder, n);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[15]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[16]++;

      // Next, list the places where this name is used (REFERS TO), and the
      // names that this declaration refers to (REFERENCED BY).
      List<DiGraphEdge<Name, Reference>> outEdges =
          graph.getOutEdges(n.getValue());
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[17]++;
      List<DiGraphEdge<Name, Reference>> inEdges =
          graph.getInEdges(n.getValue());
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[18]++;
int CodeCoverConditionCoverageHelper_C1;

      // Don't bother to create the dotted list if we don't have anything to
      // put in it.
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((outEdges.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((inEdges.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[1]++;
        builder.append("<ul>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[19]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((outEdges.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[3]++;
          builder.append("<li>REFERS TO:<br>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[21]++;
          builder.append("<ul>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[22]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[4]++;


          for (DiGraphEdge<Name, Reference> edge : outEdges) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[4]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[5]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[6]++;
}
            generateEdgeReport(builder, edge.getDestination().getValue(),
                edge);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[24]++;
          }
          builder.append("</ul>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[25]++;

        } else {
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[4]++;}
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((inEdges.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[5]++;
          builder.append("<li>REFERENCED BY:<br>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[27]++;
          builder.append("<ul>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[28]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[29]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[7]++;


          for (DiGraphEdge<Name, Reference> edge : inEdges) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[7]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[8]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[9]++;
}
            generateEdgeReport(builder, edge.getSource().getValue(), edge);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[30]++;
          }
          builder.append("</ul>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[31]++;

        } else {
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[6]++;}
        builder.append("</ul>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[32]++;

      } else {
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[2]++;}
    }
    builder.append("</ul>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[33]++;
    generateHtmlReportFooter(builder);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[34]++;
    return builder.toString();
  }

  /**
   * Given a node, find the name of the containing source file.
   *
   * @param node Parse tree node whose filename is requested
   * @return String containing name of source file, or empty string if name
   *     cannot be identified.
   */
  private String getSourceFile(Node node) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[35]++;
    String filename = node.getSourceFileName();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[7]++;
      return "";

    } else {
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[8]++;}
    return filename;
  }

  /**
   * Generate the HTML for describing a specific declaration.
   * @param builder  contents of report to be generated
   * @param declarationNode declaration to describe
   */
  private void generateDeclarationReport(StringBuilder builder,
      DiGraphNode<Name, Reference> declarationNode) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[37]++;
    // Provide the name and location of declaration,
    // with an anchor to allow navigation to this declaration.
    String declName = declarationNode.getValue().getQualifiedName();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[38]++;
    JSType declType = declarationNode.getValue().getType();

    builder.append("<LI> ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[39]++;
    builder.append("<A NAME=\"" + declName + "\">");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[40]++;
    builder.append(declName);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[41]++;
    builder.append("\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[42]++;

    // Provide the type of the declaration.
    // This is helpful for debugging.
    generateType(builder, declType);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[43]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[44]++;

    // List all the definitions of this name that were found in the code.
    // For each, list
    List<DefinitionsRemover.Definition> defs =
        declarationNode.getValue().getDeclarations();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[45]++;
int CodeCoverConditionCoverageHelper_C5;

    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((defs.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[9]++;
       builder.append("<br>No definitions found<br>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[46]++;

    } else {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[10]++;
      // Otherwise, provide a list of definitions in a dotted list.
      // For each definition, print the location where that definition is
      // found.
      builder.append("<ul>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[47]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[48]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[10]++;


      for (DefinitionsRemover.Definition def : defs) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[10]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[11]--;
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.loops[12]++;
}
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[49]++;
        Node fnDef = def.getRValue();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[50]++;
        String sourceFileName = getSourceFile(fnDef);
        builder.append("<li> Defined: ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[51]++;
        generateSourceReferenceLink(builder,
            sourceFileName, fnDef.getLineno(), fnDef.getCharno());
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[52]++;
      }
      builder.append("</ul>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[53]++;
    }
  }

  /**
   *  Generate the HTML header for the report style.
   * Borrowed straight from NameAnalyzer's report style.
   *
   * @param builder contents of the report to be generated
   */
  private void generateHtmlReportHeader(StringBuilder builder) {
    builder.append("<!DOCTYPE html>\n" +
        "<html>" +
        "<head>" +
        "<meta http-equiv=\"Content-Type\" " +
        "content=\"text/html;charset=utf-8\" >" +
        "<title>Name Reference Graph Dump</title>" +
        "<style type=\"text/css\">body, td, ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[54]++;
    builder.append("p {font-family: Arial; font-size: 83%} ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[55]++;
    builder.append("ul {margin-top:2px; margin-left:0px; padding-left:1em;}");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[56]++;
    builder.append("li {margin-top:3px; margin-left:24px;" +
        "padding-left:0px;padding-bottom: 4px}");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[57]++;
    builder.append("</style></head><body>\n");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[58]++;
  }

  /**
   * Generate the HTML footer for the report style.
   */
  private void generateHtmlReportFooter(StringBuilder builder) {
    builder.append("</body></html>");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[59]++;
  }

  /**
   * Generate a description of a specific edge between two nodes.
   * For each edge, name the element being linked, the location of the
   * reference in the source file, and the type of the reference.
   *
   * @param builder contents of the report to be generated
   * @param referencedDecl name of the declaration being referenced
   * @param edge the graph edge being described
   */
  private void generateEdgeReport(StringBuilder builder,
      Name referencedDecl, DiGraphEdge<Name, Reference> edge) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[60]++;
    String srcDeclName = referencedDecl.getQualifiedName();
    builder.append("<li><A HREF=\"#" + srcDeclName + "\">");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[61]++;
    builder.append(srcDeclName);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[62]++;
    builder.append("</a> ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[63]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[64]++;

    Node def = edge.getValue().getSite();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[65]++;
    int lineNumber = def.getLineno();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[66]++;
    int columnNumber = def.getCharno();
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[67]++;
    String sourceFile = getSourceFile(def);

    generateSourceReferenceLink(builder, sourceFile, lineNumber, columnNumber);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[68]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[69]++;

    JSType defType = edge.getValue().getSite().getJSType();
    generateType(builder, defType);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[70]++;
  }

  /**
   * Generate a link and text for a reference to a particular location
   * in a source file.  Selecting the link should take the programmer
   * to a browsable version of the file.
   *
   * @param builder  contents of the report to be generated
   * @param sourceFile  Path to the file
   * @param lineNumber  line where the object to view is located
   * @param columnNumber  column where the object to highlight is located.
   */
  private void generateSourceReferenceLink(StringBuilder builder,
    String sourceFile, int lineNumber, int columnNumber) {
    assert(sourceFile != null);

    builder.append("(");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[71]++;


    // Print out the text path so the user knows where things come from.
    builder.append(sourceFile + ":" +
        lineNumber + "," + columnNumber);
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[72]++;


    builder.append(")");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[73]++;
  }

  /**
   * Dump a type in a nice, readable way.
   *
   * @param builder contents of the report to be generated.
   * @param defType type to describe
   */
  private void generateType(StringBuilder builder, JSType defType) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[74]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((defType == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[11]++;
      builder.append(" (type: null) ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[75]++;

    } else {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[12]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[76]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((defType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[13]++;
      builder.append(" (type: unknown) ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[77]++;

    } else {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[14]++;
      builder.append(" (type: " +
          defType.toString() + ") ");
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[78]++;
    }
}
  }

  /**
   * DiGraphNodeComparator gives us a way to generate sorted lists
   * of DiGraphNodes.  It provides a compare function used by the
   * String class's sort method.
   */
  class DiGraphNodeComparator implements
      Comparator<DiGraphNode<Name, Reference>> {
    @Override
    public int compare(DiGraphNode<Name, Reference> node1,
        DiGraphNode<Name, Reference> node2) {
      Preconditions.checkNotNull(node1.getValue());
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[79]++;
      Preconditions.checkNotNull(node2.getValue());
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[80]++;
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[81]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((node1.getValue().getQualifiedName() == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node2.getValue().getQualifiedName() == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[15]++;
        return 0;

      } else {
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[16]++;}
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[82]++;
int CodeCoverConditionCoverageHelper_C9;

      // Node 1, if null, comes before node 2.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((node1.getValue().getQualifiedName() == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[17]++;
        return -1;

      } else {
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[18]++;}
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.statements[83]++;
int CodeCoverConditionCoverageHelper_C10;

      // Node 2, if null, comes before node 1.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((node2.getValue().getQualifiedName() == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[19]++;
        return 1;

      } else {
  CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh.branches[20]++;}

      return node1.getValue().getQualifiedName().compareTo(
          node2.getValue().getQualifiedName());
    }
  }

}

class CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh ());
  }
    public static long[] statements = new long[84];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NameReferenceGraphReport.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,2,1,1};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$71gs40wq1n9of41hmy5n9s6u80o2djr4pp254sv6267oh () {
    super("com.google.javascript.jscomp.NameReferenceGraphReport.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 83; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NameReferenceGraphReport.java");
      for (int i = 1; i <= 83; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

