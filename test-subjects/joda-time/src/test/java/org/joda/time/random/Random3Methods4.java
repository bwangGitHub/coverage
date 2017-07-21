package org.joda.time.random;
import junit.framework.*;
public class Random3Methods4 extends TestCase {
static TestSuite randomSuite;
public Random3Methods4 (String testname) {super(testname);}

public static void addMethods0 () {
randomSuite.addTest(new org.joda.time.format.TestPeriodFormat("test_wordBased_pt_parseOneField"));
randomSuite.addTest(new org.joda.time.TestYearMonthDay_Properties("testPropertySetMonth"));
randomSuite.addTest(new org.joda.time.TestHours("testPlus_int"));
}

public static Test suite(){
randomSuite = new TestSuite();
addMethods0();
return randomSuite;}}