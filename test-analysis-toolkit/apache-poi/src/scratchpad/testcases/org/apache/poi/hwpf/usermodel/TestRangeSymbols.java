/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.hwpf.usermodel;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFTestDataSamples;

/**
 * API for processing of symbols, see Bugzilla 49908
 */
public final class TestRangeSymbols extends TestCase {

    public void test() throws IOException {
        HWPFDocument doc = HWPFTestDataSamples.openSampleFile("Bug49908.doc");

        Range range = doc.getRange();

        assertTrue(range.numCharacterRuns() >= 2);
        CharacterRun chr = range.getCharacterRun(0);
        assertEquals(false, chr.isSymbol());

        chr = range.getCharacterRun(1);
        assertEquals(true, chr.isSymbol());
        assertEquals("\u0028", chr.text());
        assertEquals("Wingdings", chr.getSymbolFont().getMainFontName());
        assertEquals(0xf028, chr.getSymbolCharacter());
    }


public TestRangeSymbols (String name) {super(name);}
}