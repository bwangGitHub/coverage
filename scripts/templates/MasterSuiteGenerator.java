package MASTER_SUITE_PACKAGE;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.regex.*;

import java.io.*;

public class MasterSuiteGenerator
{
    private ArrayList<UnitTest> unitTests;
    private ArrayList<UnitTest> excludedTests;
    private final String testDir = "TEST_SRC_DIR"; //This is the directory the master suite will be written to
    private final int offset = OFFSET; //This is the number of characters to drop off the front of filenames
    private final String packageName = "MASTER_SUITE_PACKAGE"; //This should generally be the same as the one at the top of the file
    
    @SuppressWarnings("unchecked")
    public static void main (String [] args) throws Exception
    {
        MasterSuiteGenerator suiteGenerator = new MasterSuiteGenerator();
	suiteGenerator.buildMasterSuite();
	suiteGenerator.writeMasterSuite();
    }

    public MasterSuiteGenerator ()
    {
	unitTests = new ArrayList<UnitTest>();
	excludedTests = fillExcludedTestList();
	System.out.println(excludedTests.size() + " tests are being excluded from the test suite");
    }

    //This can be used to determine if a test is failing.  Not called in current implementation.
    public void removeTest (int index)
    {
	excludedTests.remove(index);
	System.out.println(excludedTests.size() + " tests are being excluded from the test suite");
    }

    //These tests will not be included in the master suite.  Use to exclude broken tests.
    public ArrayList<UnitTest> fillExcludedTestList ()
    {
	ArrayList<UnitTest> excludes = new ArrayList<UnitTest>();
 	//Example:
	//excludes.add(new UnitTest("org.jfree.taskdefs.AbstractCvsTaskTest", "testAbstractCvsTask"));
	return excludes;
    }

    public void buildMasterSuite ()
    {
	try
	{
	    //Get a list of all of the files in the test directory and its subdirectories
	    HashSet<String> filenames = listContainedFiles(new File(testDir));
	    System.out.println("Found " + filenames.size() + " test files");
	    
	    //For every file, add its tests to unitTests
	    for (String f : filenames)
	    {
		Class c;
		try
		{
		    c = Class.forName(f);
		}
		catch (ClassNotFoundException e)
		{
		    System.out.println("Class not found: " + f);
		    continue;
		}

	        try
		{
		    identifyTestMethods((TestSuite)((c.getMethod("suite")).invoke(null)));
		}
		catch (NoSuchMethodException e)
		{
		    identifyTestMethods(new TestSuite(c));
		    continue;
		}
		catch (ClassCastException e)
		{
		    System.out.println("Class is not a test suite/case: " + f);
		    continue;
		}
	    }
	}
	catch (Exception e)
	{
	    System.out.println("Could not generate a list of test methods");
	    e.printStackTrace();
	}
    }

    public HashSet<String> listContainedFiles (File f) throws Exception
    {
	HashSet<String> filenames = new HashSet<String>();
	File [] contents = f.listFiles();

	for (File c : contents)
	{
	    if (c.isFile())
	    {
		String name = c.getCanonicalPath();
		String extension = name.substring(name.length() - 5, name.length());

		if (extension.equals(".java"))
		{
		    name = name.substring(offset, name.length() - 5);
		    name = name.replace("/", ".");
		    //System.out.println(name);
		    filenames.add(name);
		}
	    }

	    else
	    {
		filenames.addAll(listContainedFiles(c));
	    }
	}

	return filenames;
    }
   
    public void identifyTestMethods (Test t)
    {
	if (t instanceof TestSuite)
        {       
            for (Enumeration<Test> e = ((TestSuite)t).tests(); e.hasMoreElements(); )
            {
                identifyTestMethods (e.nextElement());
            }         
        }
        
        else if (t instanceof TestCase)
        {          
            
	    System.out.println("Found a test case");
	    //We can only use this class if it has a constructor that takes one string
            Class c = t.getClass();
            Constructor[] allConstructors = c.getDeclaredConstructors();
            for (Constructor ctor : allConstructors)
            {
                Class [] types = ctor.getParameterTypes();
                if (types.length == 1 && types[0] == String.class)
                {
                    //It does, so look for test methods
		    String classname = c.getName();
		    Method[] allMethods = c.getDeclaredMethods();
                   			
		    for (Method mthd : allMethods)
		    {
			//We only want methods that 
			//1. take no parameters
			//2. start with "test" (this is required by JUnit 3, not arbitrary)
			//3. are not already in unitTests
			//4. are not in the list of excluded tests
			String methodname = mthd.getName();
			Class [] params = mthd.getParameterTypes();
			if (params.length == 0 && methodname.startsWith("test"))
			{
			    UnitTest ut = new UnitTest(classname, methodname);
			    if (!unitTests.contains(ut) && !excludedTests.contains(ut))
			    {
				unitTests.add(ut);
				System.out.println("Adding the unit test " + classname + "." + methodname);
			    }
			}
 		    }
		}
	    }
        }
            
        else
        {
            assert false : "Test suite is ill-formed";
        }   
    }

    public void writeMasterSuite () throws Exception
    {
        System.out.println("Writing out master suite...");
        BufferedWriter outfile = new BufferedWriter(new FileWriter(testDir + "MasterTestSuite.java"));
        outfile.write(buildFileHeader("MasterTestSuite"));
        
	//Each method adds at most 100 tests to the suite to avoid hitting method length limits
        int methodCounter = 0;
        for (int i = 0; i < unitTests.size(); i++)
        {
            if (i % 100 == 0)
            {
                outfile.write("\n\npublic static void addMethods" + methodCounter + " () {");
                methodCounter++;
            }
            UnitTest t = unitTests.get(i);
            outfile.write("randomSuite.addTest(new " + t.classname + "(\"" + t.methodname + "\"));" + "\n");
            if (i % 100 == 99 || i == (unitTests.size() - 1))
            {
                outfile.write("}");
            }
        }

        outfile.write("\n\npublic static Test suite(){\nrandomSuite = new TestSuite();\n");
        for (int i = 0; i < methodCounter; i++)
        {
            outfile.write("addMethods" + i + "();\n");
        }
        outfile.write("return randomSuite;}}");
        
        outfile.close();
        System.out.println("Finished writing master suite");
    }
    
    public String buildFileHeader(String classname)
    {
        return "package " + packageName + ";" +
        "\nimport junit.framework.*;" +
        "\npublic class " + classname + " extends TestCase {" + 
        "\nstatic TestSuite randomSuite;" +
        "\npublic " + classname + "(String testname) {super(testname);}";
    }
    
    private static class UnitTest
    {
        String classname;
        String methodname;
        
        public UnitTest (String c, String m)
        {
            classname = c;
            methodname = m;
        }

	public boolean equals (Object o)
	{
	    if (o instanceof UnitTest)
	    {
		UnitTest u = (UnitTest)o;
		if ( (u.classname).equals(this.classname) && (u.methodname).equals(this.methodname) )
		{
		    return true;
		}
	    }
	    return false;
	}
    }
}
