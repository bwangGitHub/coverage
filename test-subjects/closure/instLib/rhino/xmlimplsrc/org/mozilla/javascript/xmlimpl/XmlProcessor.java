/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xmlimpl;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import org.mozilla.javascript.*;

//    Disambiguate from org.mozilla.javascript.Node
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

class XmlProcessor implements Serializable {
  static {
    CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.ping();
  }


    private static final long serialVersionUID = 6903514433204808713L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[1]++;
  }

    private boolean ignoreComments;
    private boolean ignoreProcessingInstructions;
    private boolean ignoreWhitespace;
    private boolean prettyPrint;
    private int prettyIndent;

    private transient javax.xml.parsers.DocumentBuilderFactory dom;
    private transient javax.xml.transform.TransformerFactory xform;
    private transient LinkedBlockingDeque<DocumentBuilder> documentBuilderPool;
    private RhinoSAXErrorHandler errorHandler = new RhinoSAXErrorHandler();
  {
    CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[2]++;
  }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[3]++;
        this.dom = javax.xml.parsers.DocumentBuilderFactory.newInstance();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[4]++;
        this.dom.setNamespaceAware(true);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[5]++;
        this.dom.setIgnoringComments(false);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[6]++;
        this.xform = javax.xml.transform.TransformerFactory.newInstance();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[7]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[8]++;
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        this.documentBuilderPool = new LinkedBlockingDeque<DocumentBuilder>(poolSize);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[9]++;
    }

    private static class RhinoSAXErrorHandler implements ErrorHandler, Serializable {

        private static final long serialVersionUID = 6918417235413084055L;

        private void throwError(SAXParseException e) {
            throw ScriptRuntime.constructError("TypeError", e.getMessage(),
                e.getLineNumber() - 1);
        }

        public void error(SAXParseException e) {
            throwError(e);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[10]++;
        }

        public void fatalError(SAXParseException e) {
            throwError(e);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[11]++;
        }

        public void warning(SAXParseException e) {
            Context.reportWarning(e.getMessage());
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[12]++;
        }
    }

    XmlProcessor() {
        setDefault();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[13]++;
        this.dom = javax.xml.parsers.DocumentBuilderFactory.newInstance();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[14]++;
        this.dom.setNamespaceAware(true);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[15]++;
        this.dom.setIgnoringComments(false);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[16]++;
        this.xform = javax.xml.transform.TransformerFactory.newInstance();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[17]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[18]++;
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        this.documentBuilderPool = new LinkedBlockingDeque<DocumentBuilder>(poolSize);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[19]++;
    }

    final void setDefault() {
        this.setIgnoreComments(true);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[20]++;
        this.setIgnoreProcessingInstructions(true);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[21]++;
        this.setIgnoreWhitespace(true);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[22]++;
        this.setPrettyPrinting(true);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[23]++;
        this.setPrettyIndent(2);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[24]++;
    }

    final void setIgnoreComments(boolean b) {
        this.ignoreComments = b;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[25]++;
    }

    final void setIgnoreWhitespace(boolean b) {
        this.ignoreWhitespace = b;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[26]++;
    }

    final void setIgnoreProcessingInstructions(boolean b) {
        this.ignoreProcessingInstructions = b;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[27]++;
    }

    final void setPrettyPrinting(boolean b) {
        this.prettyPrint = b;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[28]++;
    }

    final void setPrettyIndent(int i) {
        this.prettyIndent = i;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[29]++;
    }

    final boolean isIgnoreComments() {
        return ignoreComments;
    }

    final boolean isIgnoreProcessingInstructions() {
        return ignoreProcessingInstructions;
    }

    final boolean isIgnoreWhitespace() {
        return ignoreWhitespace;
    }

    final boolean isPrettyPrinting() {
        return prettyPrint;
    }

    final int getPrettyIndent() {
        return prettyIndent;
    }

    private String toXmlNewlines(String rv) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[30]++;
        StringBuffer nl = new StringBuffer();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i<rv.length()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[3]++;
}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[32]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((rv.charAt(i) == '\r') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[1]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[33]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((rv.charAt(i+1) == '\n') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[3]++;

                    //    DOS, do nothing and skip the \r
                } else {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[4]++;
                    //    Macintosh, substitute \n
                    nl.append('\n');
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[34]++;
                }

            } else {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[2]++;
                nl.append(rv.charAt(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[35]++;
            }
        }
        return nl.toString();
    }

    private javax.xml.parsers.DocumentBuilderFactory getDomFactory() {
        return dom;
    }

    // Get from pool, or create one without locking, if needed.
    private DocumentBuilder getDocumentBuilderFromPool()
            throws ParserConfigurationException {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[36]++;
        DocumentBuilder builder = documentBuilderPool.pollFirst();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[37]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((builder == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)){
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[5]++;
            builder = getDomFactory().newDocumentBuilder();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[38]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[6]++;}
        builder.setErrorHandler(errorHandler);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[39]++;
        return builder;
    }

    // Insert into pool, if resettable. Pool capacity is limited to
    // number of processors * 2.
    private void returnDocumentBuilderToPool(DocumentBuilder db) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[40]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            db.reset();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[41]++;
            documentBuilderPool.offerFirst(db);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[42]++;
        } catch (UnsupportedOperationException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[8]++;
            // document builders that don't support reset() can't be pooled
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[7]++;
}
  }
    }

    private void addProcessingInstructionsTo(List<Node> list, Node node) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[43]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node instanceof ProcessingInstruction) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[9]++;
            list.add(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[44]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[10]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node.getChildNodes() != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[11]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[46]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i<node.getChildNodes().getLength()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[6]++;
}
                addProcessingInstructionsTo(list, node.getChildNodes().item(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[47]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[12]++;}
    }

    private void addCommentsTo(List<Node> list, Node node) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node instanceof Comment) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[13]++;
            list.add(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[49]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[14]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[50]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((node.getChildNodes() != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[15]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[51]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i<node.getChildNodes().getLength()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[7]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[8]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[9]++;
}
                addProcessingInstructionsTo(list, node.getChildNodes().item(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[52]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[16]++;}
    }

    private void addTextNodesToRemoveAndTrim(List<Node> toRemove, Node node) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((node instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[17]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[54]++;
            Text text = (Text)node;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[55]++;
            boolean BUG_369394_IS_VALID = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((BUG_369394_IS_VALID) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[19]++;
                text.setData(text.getData().trim());
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[57]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[20]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[58]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((text.getData().trim().length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[21]++;
                    text.setData("");
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[59]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[22]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[60]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((text.getData().length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[23]++;
                toRemove.add(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[61]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[18]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((node.getChildNodes() != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[25]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[63]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[10]++;


int CodeCoverConditionCoverageHelper_C16;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i<node.getChildNodes().getLength()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[10]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[11]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[12]++;
}
                addTextNodesToRemoveAndTrim(toRemove, node.getChildNodes().item(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[64]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[26]++;}
    }

    final Node toXml(String defaultNamespaceUri, String xml) throws org.xml.sax.SAXException {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[65]++;
        //    See ECMA357 10.3.1
        DocumentBuilder builder = null;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[66]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[67]++;
            String syntheticXml = "<parent xmlns=\"" + defaultNamespaceUri +
                "\">" + xml + "</parent>";
            builder = getDocumentBuilderFromPool();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[68]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[69]++;
            Document document = builder.parse( new org.xml.sax.InputSource(new java.io.StringReader(syntheticXml)) );
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ignoreProcessingInstructions) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[28]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[71]++;
                List<Node> list = new java.util.ArrayList<Node>();
                addProcessingInstructionsTo(list, document);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[72]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[73]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[13]++;


                for (Node node: list) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[13]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[14]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[15]++;
}
                    node.getParentNode().removeChild(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[74]++;
                }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[29]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[75]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ignoreComments) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[30]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[76]++;
                List<Node> list = new java.util.ArrayList<Node>();
                addCommentsTo(list, document);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[77]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[78]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[16]++;


                for (Node node: list) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[16]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[17]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[18]++;
}
                    node.getParentNode().removeChild(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[79]++;
                }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[31]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[80]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ignoreWhitespace) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[32]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[81]++;
                //    Apparently JAXP setIgnoringElementContentWhitespace() has a different meaning, it appears from the Javadoc
                //    Refers to element-only content models, which means we would need to have a validating parser and DTD or schema
                //    so that it would know which whitespace to ignore.

                //    Instead we will try to delete it ourselves.
                List<Node> list = new java.util.ArrayList<Node>();
                addTextNodesToRemoveAndTrim(list, document);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[82]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[83]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[19]++;


                for (Node node: list) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[19]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[20]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[21]++;
}
                    node.getParentNode().removeChild(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[84]++;
                }

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[33]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[85]++;
            NodeList rv = document.getDocumentElement().getChildNodes();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[86]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((rv.getLength() > 1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[34]++;
                throw ScriptRuntime.constructError("SyntaxError", "XML objects may contain at most one node.");

            } else {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[35]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[87]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((rv.getLength() == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[36]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[88]++;
                Node node = document.createTextNode("");
                return node;

            } else {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[37]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[89]++;
                Node node = rv.item(0);
                document.getDocumentElement().removeChild(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[90]++;
                return node;
            }
}
        } catch (java.io.IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[38]++;
            throw new RuntimeException("Unreachable.");
        } catch (javax.xml.parsers.ParserConfigurationException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[39]++;
            throw new RuntimeException(e);
        } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[27]++;
}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[91]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((builder != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[40]++;
                returnDocumentBuilderToPool(builder);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[92]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[41]++;}
        }
    }

    Document newDocument() {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[93]++;
        DocumentBuilder builder = null;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[94]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            //    TODO    Should this use XML settings?
            builder = getDocumentBuilderFromPool();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[95]++;
            return builder.newDocument();
        } catch (javax.xml.parsers.ParserConfigurationException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[43]++;
            //    TODO    How to handle these runtime errors?
            throw new RuntimeException(ex);
        } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[42]++;
}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[96]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((builder != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[44]++;
                returnDocumentBuilderToPool(builder);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[97]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[45]++;}
        }
    }

    //    TODO    Cannot remember what this is for, so whether it should use settings or not
    private String toString(Node node) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[98]++;
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(node);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[99]++;
        java.io.StringWriter writer = new java.io.StringWriter();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[100]++;
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(writer);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[101]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[102]++;
            javax.xml.transform.Transformer transformer = xform.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[103]++;
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "no");
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[104]++;
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.METHOD, "xml");
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[105]++;
            transformer.transform(source, result);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[106]++;
        } catch (javax.xml.transform.TransformerConfigurationException ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[47]++;
            //    TODO    How to handle these runtime errors?
            throw new RuntimeException(ex);
        } catch (javax.xml.transform.TransformerException ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[48]++;
            //    TODO    How to handle these runtime errors?
            throw new RuntimeException(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[46]++;
}
  }
        return toXmlNewlines(writer.toString());
    }

    String escapeAttributeValue(Object value) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[107]++;
        String text = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[108]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((text.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[49]++; return "";
} else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[50]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[109]++;

        Document dom = newDocument();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[110]++;
        Element e = dom.createElement("a");
        e.setAttribute("b", text);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[111]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[112]++;
        String elementText = toString(e);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[113]++;
        int begin = elementText.indexOf('"');
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[114]++;
        int end = elementText.lastIndexOf('"');
        return elementText.substring(begin+1,end);
    }

    String escapeTextValue(Object value) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[115]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((value instanceof XMLObjectImpl) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[51]++;
            return ((XMLObjectImpl)value).toXMLString();

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[52]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[116]++;

        String text = ScriptRuntime.toString(value);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[117]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((text.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[53]++; return text;
} else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[54]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[118]++;

        Document dom = newDocument();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[119]++;
        Element e = dom.createElement("a");
        e.setTextContent(text);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[120]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[121]++;
        String elementText = toString(e);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[122]++;

        int begin = elementText.indexOf('>') + 1;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[123]++;
        int end = elementText.lastIndexOf('<');
        return (begin < end) ? elementText.substring(begin, end) : "";
    }

    private String escapeElementValue(String s) {
        //    TODO    Check this
        return escapeTextValue(s);
    }

    private String elementToXmlString(Element element) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[124]++;
        //    TODO    My goodness ECMA is complicated (see 10.2.1).  We'll try this first.
        Element copy = (Element)element.cloneNode(true);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[125]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((prettyPrint) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[55]++;
            beautifyElement(copy, 0);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[126]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[56]++;}
        return toString(copy);
    }

    final String ecmaToXmlString(Node node) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[127]++;
        //    See ECMA 357 Section 10.2.1
        StringBuffer s = new StringBuffer();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[128]++;
        int indentLevel = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[129]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((prettyPrint) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[57]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[130]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[22]++;


int CodeCoverConditionCoverageHelper_C29;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i<indentLevel) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[22]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[23]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[24]++;
}
                s.append(' ');
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[131]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[58]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[132]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((node instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[59]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[133]++;
            String data = ((Text)node).getData();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[134]++;
            //    TODO Does Java trim() work same as XMLWhitespace?
            String v = (prettyPrint) ? data.trim() : data;
            s.append(escapeElementValue(v));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[135]++;
            return s.toString();

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[60]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[136]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((node instanceof Attr) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[61]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[137]++;
            String value = ((Attr)node).getValue();
            s.append(escapeAttributeValue(value));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[138]++;
            return s.toString();

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[62]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[139]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((node instanceof Comment) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[63]++;
            s.append("<!--" + ((Comment)node).getNodeValue() + "-->");
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[140]++;
            return s.toString();

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[64]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[141]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((node instanceof ProcessingInstruction) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[65]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[142]++;
            ProcessingInstruction pi = (ProcessingInstruction)node;
            s.append("<?" + pi.getTarget() + " " + pi.getData() + "?>");
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[143]++;
            return s.toString();

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[66]++;}
        s.append(elementToXmlString((Element)node));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[144]++;
        return s.toString();
    }

    private void beautifyElement(Element e, int indent) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[145]++;
        StringBuffer s = new StringBuffer();
        s.append('\n');
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[146]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[147]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[25]++;


int CodeCoverConditionCoverageHelper_C34;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i<indent) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[25]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[26]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[27]++;
}
            s.append(' ');
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[148]++;
        }
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[149]++;
        String afterContent = s.toString();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[150]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[28]++;


int CodeCoverConditionCoverageHelper_C35;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i<prettyIndent) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[28]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[29]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[30]++;
}
            s.append(' ');
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[151]++;
        }
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[152]++;
        String beforeContent = s.toString();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[153]++;

        //    We "mark" all the nodes first; if we tried to do this loop otherwise, it would behave unexpectedly (the inserted nodes
        //    would contribute to the length and it might never terminate).
        ArrayList<Node> toIndent = new ArrayList<Node>();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[154]++;
        boolean indentChildren = false;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[155]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[31]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i<e.getChildNodes().getLength()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[31]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[32]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[33]++;
}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[156]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((i == 1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[67]++; indentChildren = true;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[157]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[68]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[158]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((e.getChildNodes().item(i) instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[69]++;
                toIndent.add(e.getChildNodes().item(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[159]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[70]++;
                indentChildren = true;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[160]++;
                toIndent.add(e.getChildNodes().item(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[161]++;
            }
        }
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[162]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((indentChildren) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[71]++;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[163]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[34]++;


int CodeCoverConditionCoverageHelper_C40;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i<toIndent.size()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[34]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[35]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[36]++;
}
                e.insertBefore(e.getOwnerDocument().createTextNode(beforeContent),
                        toIndent.get(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[164]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[72]++;}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[165]++;
        NodeList nodes = e.getChildNodes();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[166]++;
        ArrayList<Element> list = new ArrayList<Element>();
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[167]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[37]++;


int CodeCoverConditionCoverageHelper_C41;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i < nodes.getLength()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[37]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[38]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[39]++;
}
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[168]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((nodes.item(i) instanceof Element) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[73]++;
                list.add((Element)nodes.item(i));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[169]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[74]++;}
        }
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[170]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[40]++;


        for (Element elem: list) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[40]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[41]--;
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.loops[42]++;
}
            beautifyElement(elem, indent + prettyIndent);
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[171]++;
        }
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[172]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((indentChildren) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[75]++;
            e.appendChild(e.getOwnerDocument().createTextNode(afterContent));
CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.statements[173]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt.branches[76]++;}
    }
}

class CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt ());
  }
    public static long[] statements = new long[174];
    public static long[] branches = new long[77];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[44];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xmlimpl.RHINO-XML-XmlProcessor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 43; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$59ffmx2g7ln24p2bfbkep91b4odu88k8za7m9eukpt () {
    super("org.mozilla.javascript.xmlimpl.RHINO-XML-XmlProcessor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 173; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 76; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xmlimpl.RHINO-XML-XmlProcessor.java");
      for (int i = 1; i <= 173; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 76; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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

