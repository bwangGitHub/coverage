/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.mozilla.javascript.Kit;
import org.mozilla.javascript.commonjs.module.provider.ParsedContentType;

/**
 * @version $Id: SourceReader.java,v 1.2 2010/02/15 19:31:17 szegedia%freemail.hu Exp $
 */
public class SourceReader
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.ping();
  }

    public static URL toUrl(String path) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        // Assume path is URL if it contains a colon and there are at least
        // 2 characters in the protocol part. The later allows under Windows
        // to interpret paths with driver letter as file, not URL.
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((path.indexOf(':') >= 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[1]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[2]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                return new URL(path);
            } catch (MalformedURLException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[4]++;
                // not a URL
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[3]++;
}
  }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[2]++;}
        return null;
    }

    public static Object readFileOrUrl(String path, boolean convertToString,
            String defaultEncoding) throws IOException
    {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[3]++;
        URL url = toUrl(path);
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[4]++;
        InputStream is = null;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[5]++;
        int capacityHint = 0;
        String encoding;
        final String contentType;
        byte[] data;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[6]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((url == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[6]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[8]++;
                File file = new File(path);
                contentType = encoding = null;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[9]++;
                capacityHint = (int)file.length();
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[10]++;
                is = new FileInputStream(file);
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[11]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[7]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[12]++;
                URLConnection uc = url.openConnection();
                is = uc.getInputStream();
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[13]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
                if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((convertToString) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[8]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[15]++;
                    ParsedContentType pct = new ParsedContentType(uc.getContentType());
                    contentType = pct.getContentType();
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[16]++;
                    encoding = pct.getEncoding();
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[17]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[9]++;
                    contentType = encoding = null;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[18]++;
                }
                capacityHint = uc.getContentLength();
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[19]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
                // Ignore insane values for Content-Length
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((capacityHint > (1 << 20)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[10]++;
                    capacityHint = -1;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[21]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[11]++;}
            }
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((capacityHint <= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[12]++;
                capacityHint = 4096;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[23]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[13]++;}

            data = Kit.readStream(is, capacityHint);
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[24]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[5]++;
}
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
            if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((is != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[14]++;
                is.close();
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[26]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[15]++;}
        }

        Object result;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((convertToString) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[16]++;
            result = data;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[28]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[17]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
            if((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((encoding == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[18]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[30]++;
int CodeCoverConditionCoverageHelper_C9;
                // None explicitly specified in Content-type header. Use RFC-4329
                // 4.2.2 section to autodetect
                if((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (512)) == 0 || true) &&
 ((data.length > 3) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 ((data[0] == -1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((data[1] == -2) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((data[2] == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((data[3] == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 5) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 5) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[20]++;
                    encoding = "UTF-32LE";
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[31]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[21]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[32]++;
int CodeCoverConditionCoverageHelper_C10; if((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (512)) == 0 || true) &&
 ((data.length > 3) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (128)) == 0 || true) &&
 ((data[0] == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((data[1] == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((data[2] == -2) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((data[3] == -1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 5) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 5) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[22]++;
                    encoding = "UTF-32BE";
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[33]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[23]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[34]++;
int CodeCoverConditionCoverageHelper_C11; if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (128)) == 0 || true) &&
 ((data.length > 2) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((data[0] == -17) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((data[1] == -69) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((data[2] == -65) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 4) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 4) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[24]++;
                    encoding = "UTF-8";
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[35]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[25]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[36]++;
int CodeCoverConditionCoverageHelper_C12; if((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((data.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((data[0] == -1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((data[1] == -2) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[26]++;
                    encoding = "UTF-16LE";
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[37]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[27]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[38]++;
int CodeCoverConditionCoverageHelper_C13; if((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((data.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((data[0] == -2) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((data[1] == -1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[28]++;
                    encoding = "UTF-16BE";
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[39]++;

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[29]++;
                    // No autodetect. See if we have explicit value on command line
                    encoding = defaultEncoding;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[40]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[41]++;
int CodeCoverConditionCoverageHelper_C14;
                    if((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((encoding == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[30]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
                        // No explicit encoding specification
                        if((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((url == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[32]++;
                            // Local files default to system encoding
                            encoding = System.getProperty("file.encoding");
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[43]++;

                        }
                        else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[33]++;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[44]++;
int CodeCoverConditionCoverageHelper_C16; if((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((contentType != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((contentType.startsWith("application/")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[34]++;
                            // application/* types default to UTF-8
                            encoding = "UTF-8";
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[45]++;

                        }
                        else {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[35]++;
                            // text/* MIME types default to US-ASCII
                            encoding = "US-ASCII";
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[46]++;
                        }
}

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[31]++;}
                }
}
}
}
}

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[19]++;}
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[47]++;
            String strResult = new String(data, encoding);
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[48]++;
int CodeCoverConditionCoverageHelper_C17;
            // Skip BOM
            if((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((strResult.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((strResult.charAt(0) == '\uFEFF') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false))
            {
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[36]++;
                strResult = strResult.substring(1);
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[49]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.branches[37]++;}
            result = strResult;
CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x.statements[50]++;
        }
        return result;
    }
}

class CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x ());
  }
    public static long[] statements = new long[51];
    public static long[] branches = new long[38];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.RHINO-TOO-SourceReader.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,3,3,3,3,3,1,1,2,2};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$59ffmx2g7ljqr128aedpipyub9n1xe5gex5r20tu8x () {
    super("org.mozilla.javascript.tools.RHINO-TOO-SourceReader.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 50; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 37; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.RHINO-TOO-SourceReader.java");
      for (int i = 1; i <= 50; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 37; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

