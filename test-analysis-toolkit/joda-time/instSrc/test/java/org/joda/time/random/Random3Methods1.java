package org.joda.time.random;
import junit.framework.*;
public class Random3Methods1 extends TestCase {
static TestSuite randomSuite;
public Random3Methods1 (String testname) {super(testname);}

public static void addMethods0 () {
randomSuite.addTest(new org.joda.time.TestDateTimeZone("testForTimeZone_TimeZone"));
randomSuite.addTest(new org.joda.time.TestInterval_Constructors("testConstructor_Object3"));
randomSuite.addTest(new org.joda.time.chrono.TestISOChronology("testFactory_Zone"));
}

public static Test suite(){
randomSuite = new TestSuite();
addMethods0();
return randomSuite;}}