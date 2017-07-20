package ca.uwaterloo.swag.tat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrentSuite
{
    private static Logger       logger = Logger.getLogger(CurrentSuite.class
                                               .getName());
    private static Level        level  = Level.INFO;
    private ArrayList<UnitTest> tests;
    private ArrayList<Mutant>   mutants;

    public CurrentSuite()
    {
        logger.setLevel(level);
        logger.finest("Logger " + logger.getName()
                + " initialized; logging messages at level " + level.toString()
                + " or higher");
        tests = new ArrayList<UnitTest>();
        mutants = new ArrayList<Mutant>();
    }

    public void writeTestSuiteToFile(String filename, String header)
    {
        try
        {
            logger.finest("Writing the current suite to the file " + filename);
            BufferedWriter outfile = new BufferedWriter(
                    new FileWriter(filename));
            outfile.write(header);

            // Cap each method at 100 tests so the method length limit is not
            // exceeded
            int testCounter = 0;
            int methodCounter = 0;
            for (UnitTest test : tests)
            {
                if (testCounter % 100 == 0)
                {
                    outfile.write("\n\npublic static void addMethods"
                            + methodCounter + " () {\n");
                    methodCounter++;
                }
                outfile.write("randomSuite.addTest(new " + test.getClassname()
                        + "(\"" + test.getMethodname() + "\"));" + "\n");
                if (testCounter % 100 == 99 || testCounter == tests.size() - 1)
                {
                    outfile.write("}");
                }
                testCounter++;
            }

            outfile.write("\n\npublic static Test suite(){\nrandomSuite = new TestSuite();\n");
            for (int j = 0; j < methodCounter; j++)
            {
                outfile.write("addMethods" + j + "();\n");
            }
            outfile.write("return randomSuite;}}");
            outfile.close();
        }

        catch (Exception e)
        {
            logger.severe("Failed to write random suite " + filename
                    + "; aborting");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public ArrayList<UnitTest> getTests()
    {
        return tests;
    }

    public void setTests(ArrayList<UnitTest> tests)
    {
        this.tests = tests;
    }

    public ArrayList<Mutant> getMutants()
    {
        return mutants;
    }

    public void setMutants(ArrayList<Mutant> mutants)
    {
        this.mutants = mutants;
    }
}
