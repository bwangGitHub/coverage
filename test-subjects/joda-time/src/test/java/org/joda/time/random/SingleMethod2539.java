package org.joda.time.random;
import junit.framework.*;
public class SingleMethod2539 extends TestCase {
static TestSuite randomSuite;
public SingleMethod2539 (String testname) {super(testname);}

public static void addMethods0 () {
randomSuite.addTest(new org.joda.time.TestDateTimeConstants("testHalfdaysOfDay"));
}

public static Test suite(){
randomSuite = new TestSuite();
addMethods0();
return randomSuite;}}