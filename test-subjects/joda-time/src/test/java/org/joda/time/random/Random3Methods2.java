package org.joda.time.random;
import junit.framework.*;
public class Random3Methods2 extends TestCase {
static TestSuite randomSuite;
public Random3Methods2 (String testname) {super(testname);}

public static void addMethods0 () {
randomSuite.addTest(new org.joda.time.TestLocalTime_Basics("testSize"));
randomSuite.addTest(new org.joda.time.TestDateTime_Basics("testToLocalTime"));
randomSuite.addTest(new org.joda.time.convert.TestConverterManager("testGetIntervalConverter"));
}

public static Test suite(){
randomSuite = new TestSuite();
addMethods0();
return randomSuite;}}