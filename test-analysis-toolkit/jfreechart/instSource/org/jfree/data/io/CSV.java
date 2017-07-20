/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * --------
 * CSV.java
 * --------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 24-Nov-2003 : Version 1 (DG);
 *
 */

package org.jfree.data.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * A utility class for reading {@link CategoryDataset} data from a CSV file.  
 * This initial version is very basic, and won't handle errors in the data 
 * file very gracefully.
 */
public class CSV {
  static {
    CodeCoverCoverageCounter$10uvzzmlho5ep.ping();
  }


    /** The field delimiter. */
    private char fieldDelimiter;
    
    /** The text delimiter. */
    private char textDelimiter;
     
    /** 
     * Creates a new CSV reader where the field delimiter is a comma, and the 
     * text delimiter is a double-quote.
     */
    public CSV() {
        this(',', '"');
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[1]++;    
    }
    
    /**
     * Creates a new reader with the specified field and text delimiters.
     * 
     * @param fieldDelimiter  the field delimiter (usually a comma, semi-colon,
     *                        colon, tab or space).
     * @param textDelimiter  the text delimiter (usually a single or double 
     *                       quote).
     */
    public CSV(char fieldDelimiter, char textDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[2]++;
        this.textDelimiter = textDelimiter;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[3]++;
    }
    
    /**
     * Reads a {@link CategoryDataset} from a CSV file or input source.
     * 
     * @param in  the input source.
     * 
     * @return A category dataset.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public CategoryDataset readCategoryDataset(Reader in) throws IOException {
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[4]++;
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[5]++;
        BufferedReader reader = new BufferedReader(in);
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[6]++;
        List columnKeys = null;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[7]++;
        int lineIndex = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[8]++;
        String line = reader.readLine();
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((line != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[1]--;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[2]--;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[3]++;
}
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((lineIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[1]++;  // first line contains column keys
                columnKeys = extractColumnKeys(line);
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[11]++;

            }
            else {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[2]++;  // remaining lines contain a row key and data values
                extractRowKeyAndData(line, dataset, columnKeys);
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[12]++;
            }
            line = reader.readLine();
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[13]++;
            lineIndex++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[14]++;
        }
        return dataset;     
         
    }
    
    /**
     * Extracts the column keys from a string.
     * 
     * @param line  a line from the input file.
     * 
     * @return A list of column keys.
     */
    private List extractColumnKeys(String line) {
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[15]++;
        List keys = new java.util.ArrayList();
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[16]++;
        int fieldIndex = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[17]++;
        int start = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < line.length()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[4]--;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[5]--;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[6]++;
}
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((line.charAt(i) == this.fieldDelimiter) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[3]++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((fieldIndex > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[5]++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[21]++;  // first field is ignored, since 
                                       // column 0 is for row keys
                    String key = line.substring(start, i);
                    keys.add(removeStringDelimiters(key));
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[22]++;

                } else {
  CodeCoverCoverageCounter$10uvzzmlho5ep.branches[6]++;}
                start = i + 1;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[23]++;
                fieldIndex++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[24]++;

            } else {
  CodeCoverCoverageCounter$10uvzzmlho5ep.branches[4]++;}
        }
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[25]++;
        String key = line.substring(start, line.length());
        keys.add(removeStringDelimiters(key));
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[26]++;
        return keys;        
    }
    
    /**
     * Extracts the row key and data for a single line from the input source.
     * 
     * @param line  the line from the input source.
     * @param dataset  the dataset to be populated.
     * @param columnKeys  the column keys.
     */
    private void extractRowKeyAndData(String line,
                                      DefaultCategoryDataset dataset,
                                      List columnKeys) {
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[27]++;
        Comparable rowKey = null;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[28]++;
        int fieldIndex = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[29]++;
        int start = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[30]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$10uvzzmlho5ep.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < line.length()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[7]--;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[8]--;
  CodeCoverCoverageCounter$10uvzzmlho5ep.loops[9]++;
}
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((line.charAt(i) == this.fieldDelimiter) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[7]++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((fieldIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[9]++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[33]++;  // first field contains the row key
                    String key = line.substring(start, i);
                    rowKey = removeStringDelimiters(key);
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[34]++;

                }
                else {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[10]++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[35]++;  // remaining fields contain values
                    Double value = Double.valueOf(
                        removeStringDelimiters(line.substring(start, i))
                    );
                    dataset.addValue(
                        value, rowKey, 
                        (Comparable) columnKeys.get(fieldIndex - 1)
                    );
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[36]++;
                }
                start = i + 1;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[37]++;
                fieldIndex++;
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[38]++;

            } else {
  CodeCoverCoverageCounter$10uvzzmlho5ep.branches[8]++;}
        }
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[39]++;
        Double value = Double.valueOf(
            removeStringDelimiters(line.substring(start, line.length()))
        );
        dataset.addValue(
            value, rowKey, (Comparable) columnKeys.get(fieldIndex - 1)
        );
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[40]++; 
    }
    
    /**
     * Removes the string delimiters from a key (as well as any white space 
     * outside the delimiters).
     * 
     * @param key  the key (including delimiters).
     * 
     * @return The key without delimiters.
     */
    private String removeStringDelimiters(String key) {
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[41]++;
        String k = key.trim();
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((k.charAt(0) == this.textDelimiter) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[11]++;
            k = k.substring(1);
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[43]++;

        } else {
  CodeCoverCoverageCounter$10uvzzmlho5ep.branches[12]++;}
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((k.charAt(k.length() - 1) == this.textDelimiter) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$10uvzzmlho5ep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$10uvzzmlho5ep.branches[13]++;
            k = k.substring(0, k.length() - 1);
CodeCoverCoverageCounter$10uvzzmlho5ep.statements[45]++;

        } else {
  CodeCoverCoverageCounter$10uvzzmlho5ep.branches[14]++;}
        return k;
    }
    
}

class CodeCoverCoverageCounter$10uvzzmlho5ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$10uvzzmlho5ep ());
  }
    public static long[] statements = new long[46];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.io.CSV.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$10uvzzmlho5ep () {
    super("org.jfree.data.io.CSV.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 45; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.io.CSV.java");
      for (int i = 1; i <= 45; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
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
      for (int i = 1; i <= 3; i++) {
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

