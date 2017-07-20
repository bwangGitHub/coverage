package org.jfree.random;
import junit.framework.*;
public class Random3Methods1 extends TestCase {
static TestSuite randomSuite;
public Random3Methods1 (String testname) {super(testname);}

public static void addMethods0 () {
randomSuite.addTest(new org.jfree.chart.renderer.category.junit.LevelRendererTests("testEquals"));
randomSuite.addTest(new org.jfree.data.junit.ComparableObjectSeriesTests("testSerialization"));
randomSuite.addTest(new org.jfree.chart.annotations.junit.CategoryLineAnnotationTests("testSerialization"));
}

public static Test suite(){
randomSuite = new TestSuite();
addMethods0();
return randomSuite;}}