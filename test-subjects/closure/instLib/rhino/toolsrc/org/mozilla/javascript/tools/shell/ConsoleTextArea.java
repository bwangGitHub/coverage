/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.shell;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import javax.swing.text.Segment;

class ConsoleWrite implements Runnable {
  static {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.ping();
  }

    private ConsoleTextArea textArea;
    private String str;

    public ConsoleWrite(ConsoleTextArea textArea, String str) {
        this.textArea = textArea;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[1]++;
        this.str = str;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[2]++;
    }

    public void run() {
        textArea.write(str);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[3]++;
    }
}

class ConsoleWriter extends java.io.OutputStream {
  static {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.ping();
  }


    private ConsoleTextArea textArea;
    private StringBuffer buffer;

    public ConsoleWriter(ConsoleTextArea textArea) {
        this.textArea = textArea;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[4]++;
        buffer = new StringBuffer();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[5]++;
    }

    @Override
    public synchronized void write(int ch) {
        buffer.append((char)ch);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[6]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((ch == '\n') && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[1]++;
            flushBuffer();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[8]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[2]++;}
    }

    public synchronized void write (char[] data, int off, int len) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for(int i = off;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.loops[3]++;
}
            buffer.append(data[i]);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[10]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((data[i] == '\n') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[3]++;
                flushBuffer();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[12]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[4]++;}
        }
    }

    @Override
    public synchronized void flush() {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((buffer.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[5]++;
            flushBuffer();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[14]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[6]++;}
    }

    @Override
    public void close () {
        flush();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[15]++;
    }

    private void flushBuffer() {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[16]++;
        String str = buffer.toString();
        buffer.setLength(0);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[17]++;
        SwingUtilities.invokeLater(new ConsoleWrite(textArea, str));
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[18]++;
    }
}

public class ConsoleTextArea
    extends JTextArea implements KeyListener, DocumentListener
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.ping();
  }

    static final long serialVersionUID = 8557083244830872961L;
  static {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[19]++;
  }

    private ConsoleWriter console1;
    private ConsoleWriter console2;
    private PrintStream out;
    private PrintStream err;
    private PrintWriter inPipe;
    private PipedInputStream in;
    private java.util.List<String> history;
    private int historyIndex = -1;
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[20]++;
  }
    private int outputMark = 0;
  {
    CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[21]++;
  }

    @Override
    public void select(int start, int end) {
        requestFocus();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[22]++;
        super.select(start, end);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[23]++;
    }

    public ConsoleTextArea(String[] argv) {
        super();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[24]++;
        history = new java.util.ArrayList<String>();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[25]++;
        console1 = new ConsoleWriter(this);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[26]++;
        console2 = new ConsoleWriter(this);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[27]++;
        out = new PrintStream(console1, true);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[28]++;
        err = new PrintStream(console2, true);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[29]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[30]++;
        PipedOutputStream outPipe = new PipedOutputStream();
        inPipe = new PrintWriter(outPipe);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[31]++;
        in = new PipedInputStream();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[32]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[33]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            outPipe.connect(in);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[34]++;
        } catch(IOException exc) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[8]++;
            exc.printStackTrace();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[35]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[7]++;
}
  }
        getDocument().addDocumentListener(this);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[36]++;
        addKeyListener(this);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[37]++;
        setLineWrap(true);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[38]++;
        setFont(new Font("Monospaced", 0, 12));
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[39]++;
    }


    synchronized void returnPressed() {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[40]++;
        Document doc = getDocument();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[41]++;
        int len = doc.getLength();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[42]++;
        Segment segment = new Segment();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[43]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            doc.getText(outputMark, len - outputMark, segment);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[44]++;
        } catch(javax.swing.text.BadLocationException ignored) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[10]++;
            ignored.printStackTrace();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[45]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[9]++;
}
  }
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[46]++;
int CodeCoverConditionCoverageHelper_C5;
        if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((segment.count > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[11]++;
            history.add(segment.toString());
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[47]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[12]++;}
        historyIndex = history.size();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[48]++;
        inPipe.write(segment.array, segment.offset, segment.count);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[49]++;
        append("\n");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[50]++;
        outputMark = doc.getLength();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[51]++;
        inPipe.write("\n");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[52]++;
        inPipe.flush();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[53]++;
        console1.flush();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[54]++;
    }

    public void eval(String str) {
        inPipe.write(str);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[55]++;
        inPipe.write("\n");
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[56]++;
        inPipe.flush();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[57]++;
        console1.flush();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[58]++;
    }

    public void keyPressed(KeyEvent e) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[59]++;
        int code = e.getKeyCode();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[60]++;
int CodeCoverConditionCoverageHelper_C6;
        if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((code == KeyEvent.VK_BACK_SPACE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[13]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[61]++;
int CodeCoverConditionCoverageHelper_C7;
            if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((outputMark == getCaretPosition()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[15]++;
                e.consume();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[62]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[16]++;}

        } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[14]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[63]++;
int CodeCoverConditionCoverageHelper_C8; if((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_HOME) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[17]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[64]++;
           int caretPos = getCaretPosition();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[65]++;
int CodeCoverConditionCoverageHelper_C9;
           if((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((caretPos == outputMark) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[19]++;
               e.consume();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[66]++;

           } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[20]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[67]++;
int CodeCoverConditionCoverageHelper_C10; if((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((caretPos > outputMark) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[21]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[68]++;
int CodeCoverConditionCoverageHelper_C11;
               if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((e.isControlDown()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[23]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[69]++;
int CodeCoverConditionCoverageHelper_C12;
                   if((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((e.isShiftDown()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[25]++;
                       moveCaretPosition(outputMark);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[70]++;

                   } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[26]++;
                       setCaretPosition(outputMark);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[71]++;
                   }
                   e.consume();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[72]++;

               } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[24]++;}

           } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[22]++;}
}

        } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[18]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[73]++;
int CodeCoverConditionCoverageHelper_C13; if((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_ENTER) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[27]++;
            returnPressed();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[74]++;
            e.consume();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[75]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[28]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[76]++;
int CodeCoverConditionCoverageHelper_C14; if((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_UP) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[29]++;
            historyIndex--;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[77]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[78]++;
int CodeCoverConditionCoverageHelper_C15;
            if((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((historyIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[31]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[79]++;
int CodeCoverConditionCoverageHelper_C16;
                if((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((historyIndex >= history.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[33]++;
                    historyIndex = history.size() -1;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[80]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[34]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
                if((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((historyIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[35]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[82]++;
                    String str = history.get(historyIndex);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[83]++;
                    int len = getDocument().getLength();
                    replaceRange(str, outputMark, len);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[84]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[85]++;
                    int caretPos = outputMark + str.length();
                    select(caretPos, caretPos);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[86]++;

                } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[36]++;
                    historyIndex++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[87]++;
                }

            } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[32]++;
                historyIndex++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[88]++;
            }
            e.consume();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[89]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[30]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[90]++;
int CodeCoverConditionCoverageHelper_C18; if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((code == KeyEvent.VK_DOWN) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[37]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[91]++;
            int caretPos = outputMark;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[92]++;
int CodeCoverConditionCoverageHelper_C19;
            if((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((history.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[39]++;
                historyIndex++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[93]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[94]++;
int CodeCoverConditionCoverageHelper_C20;
                if((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((historyIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[41]++;historyIndex = 0;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[95]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[42]++;}
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[96]++;
                int len = getDocument().getLength();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[97]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((historyIndex < history.size()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[43]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[98]++;
                    String str = history.get(historyIndex);
                    replaceRange(str, outputMark, len);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[99]++;
                    caretPos = outputMark + str.length();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[100]++;

                } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[44]++;
                    historyIndex = history.size();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[101]++;
                    replaceRange("", outputMark, len);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[102]++;
                }

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[40]++;}
            select(caretPos, caretPos);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[103]++;
            e.consume();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[104]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[38]++;}
}
}
}
}
    }

    public void keyTyped(KeyEvent e) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[105]++;
        int keyChar = e.getKeyChar();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[106]++;
int CodeCoverConditionCoverageHelper_C22;
        if((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((keyChar == 0x8) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false) /* KeyEvent.VK_BACK_SPACE */) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[45]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[107]++;
int CodeCoverConditionCoverageHelper_C23;
            if((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((outputMark == getCaretPosition()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[47]++;
                e.consume();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[108]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[48]++;}

        } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[46]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[109]++;
int CodeCoverConditionCoverageHelper_C24; if((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((getCaretPosition() < outputMark) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[49]++;
            setCaretPosition(outputMark);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[110]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[50]++;}
}
    }

    public synchronized void keyReleased(KeyEvent e) {
    }

    public synchronized void write(String str) {
        insert(str, outputMark);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[111]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[112]++;
        int len = str.length();
        outputMark += len;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[113]++;
        select(outputMark, outputMark);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[114]++;
    }

    public synchronized void insertUpdate(DocumentEvent e) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[115]++;
        int len = e.getLength();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[116]++;
        int off = e.getOffset();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[117]++;
int CodeCoverConditionCoverageHelper_C25;
        if((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((outputMark > off) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[51]++;
            outputMark += len;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[118]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[52]++;}
    }

    public synchronized void removeUpdate(DocumentEvent e) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[119]++;
        int len = e.getLength();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[120]++;
        int off = e.getOffset();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[121]++;
int CodeCoverConditionCoverageHelper_C26;
        if((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((outputMark > off) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[53]++;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[122]++;
int CodeCoverConditionCoverageHelper_C27;
            if((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((outputMark >= off + len) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[55]++;
                outputMark -= len;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[123]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[56]++;
                outputMark = off;
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[124]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.branches[54]++;}
    }

    public synchronized void postUpdateUI() {
        // this attempts to cleanup the damage done by updateComponentTreeUI
        requestFocus();
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[125]++;
        setCaret(getCaret());
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[126]++;
        select(outputMark, outputMark);
CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap.statements[127]++;
    }

    public synchronized void changedUpdate(DocumentEvent e) {
    }


    public InputStream getIn() {
        return in;
    }

    public PrintStream getOut() {
        return out;
    }

    public PrintStream getErr() {
        return err;
    }

}

class CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap ());
  }
    public static long[] statements = new long[128];
    public static long[] branches = new long[57];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-ConsoleTextArea.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 27; i++) {
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

  public CodeCoverCoverageCounter$1gk5ffks5uu2sz1ddhvzno31nxyln4fyo4vx7zq9e8kq6ap () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-ConsoleTextArea.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 127; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 56; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-ConsoleTextArea.java");
      for (int i = 1; i <= 127; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 56; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
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

