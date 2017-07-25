package com.google;
import junit.framework.*;
public class MasterTestSuite extends TestCase {
static TestSuite randomSuite;
public MasterTestSuite(String testname) {super(testname);}

public static void addMethods0 () {randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV2Test("testEmptyMap"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV2Test("testGetMappingForLine"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV2Test("testGetMappingForLineWithNameIndex"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV2Test("testInvalidJSONFailure"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV2Test("testUnknownVersion"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV2Test("testMissingFile"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testGetMappingForLine"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testInvalidJSONFailure"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testLineEdges"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testColumnEdges"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testNegativeOneInLineMap"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testSimpleParse"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testBlankLine"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testCountFailure"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testInvalidCountFailure"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testInvalidHeaderFailure"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testInvalidPostHeaderToken"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testInvalidMappingArrayFailure"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testMultipleLineFragments"));
randomSuite.addTest(new com.google.debugging.sourcemap.SourceMapConsumerV1Test("testMultipleMappingFragments"));
}

public static Test suite(){
randomSuite = new TestSuite();
addMethods0();
return randomSuite;}}