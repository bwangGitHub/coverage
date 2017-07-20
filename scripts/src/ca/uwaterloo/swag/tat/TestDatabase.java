package ca.uwaterloo.swag.tat;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestDatabase
{
    private static Logger       logger = Logger.getLogger(TestDatabase.class
                                               .getName());
    private static Level        level  = Level.INFO;
    private ArrayList<UnitTest> allTests;

    public TestDatabase(TestSuite masterSuite)
    {
        logger.setLevel(level);
        logger.finest("Logger " + logger.getName()
                + " initialized; logging messages at level " + level.toString()
                + " or higher");
        allTests = new ArrayList<UnitTest>();
	identifyTestMethods(masterSuite);
        logger.info("Found " + allTests.size() + " test methods");
    }

    @SuppressWarnings("rawtypes")
    private void identifyTestMethods(Test t)
    {
        if (t instanceof TestSuite)
        {
            logger.finest("Expanding TestSuite " + t.toString());
            for (Enumeration<Test> e = ((TestSuite) t).tests(); e
                    .hasMoreElements();)
            {
                identifyTestMethods(e.nextElement());
            }
        }

        else if (t instanceof TestCase)
        {
            logger.finest("Expanding TestCase " + t.toString());
            // We can only use this class if it has a constructor that takes one
            // string
            // (necessary to add a single test method to a new suite)
            Class c = t.getClass();
            Constructor[] allConstructors = c.getDeclaredConstructors();
            for (Constructor ctor : allConstructors)
            {
                Class[] types = ctor.getParameterTypes();
                if (types.length == 1 && types[0] == String.class)
                {
                    logger.finest("The test case has a one arg constructor");
                    Method[] allMethods = c.getDeclaredMethods();
                    String classname = c.getName();
                    for (Method mthd : allMethods)
                    {
                        // We only want methods that
                        // 1. take no parameters
                        // 2. start with "test" (required by JUnit 3)
                        // 3. are not already in testMethods
                        String methodname = mthd.getName();
                        Class[] params = mthd.getParameterTypes();
                        if (params.length == 0 && methodname.startsWith("test"))
                        {
                            UnitTest ut = new UnitTest(classname, methodname);
                            if (!allTests.contains(ut))
                            {
                                logger.finest("Found a method that meets requirements:"
                                        + methodname);
                                allTests.add(ut);
                            }
                        }
                    }
                }
            }
        }

        else
        {
            logger.severe("Test suite is ill-formed; aborting");
            System.exit(1);
        }
    }

    public ArrayList<UnitTest> getAllTests()
    {
        return allTests;
    }

    public UnitTest getTest(int index)
    {
        return allTests.get(index);
    }

    public int getTotalNumberOfTests()
    {
        return allTests.size();
    }
}
