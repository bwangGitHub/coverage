package ca.uwaterloo.swag.tat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestSuite;

public class Controller
{
    private static Logger  logger = Logger.getLogger(Controller.class.getName());
    private static Level   level  = Level.FINEST;
    private CurrentSuite   currentSuite;
    private TestDatabase   testdb;
    private MutantDatabase mutantdb;

    public Controller(String[] args)
    {
        logger.setLevel(level);
        logger.finest("Logger " + logger.getName()
                + " initialized; logging messages at level " + level.toString()
                + " or higher");
        currentSuite = new CurrentSuite();
        try
        {
            testdb = new TestDatabase(
                    (TestSuite) (((Class.forName(args[0])).getMethod("suite"))
                            .invoke(null)));
        }
        catch (Exception e)
        {
            logger.severe("Could not initialize test database, aborting");
            e.printStackTrace();
            System.exit(1);
        }
        mutantdb = new MutantDatabase(args[1]);
    }

    public void generateTestSuite(boolean singleMethodSuite, int sizeOrIndex)
    {
        logger.finest("Generating test suite");

        if (singleMethodSuite)
        {
            ArrayList<UnitTest> suite = new ArrayList<UnitTest>();
            suite.add(testdb.getTest(sizeOrIndex));
            currentSuite.setTests(suite);
        }

        else
        {
            ArrayList<UnitTest> randomSuite = new ArrayList<UnitTest>();
            HashSet<Integer> testIndices = generateRandomNumbers(sizeOrIndex,
                    testdb.getTotalNumberOfTests());

            for (Integer i : testIndices)
            {
                randomSuite.add(testdb.getTest(i));
            }

            currentSuite.setTests(randomSuite);
        }
    }

    private HashSet<Integer> generateRandomNumbers(int count, int max)
    {
        HashSet<Integer> randomNums = new HashSet<Integer>();
        Random generator = new Random();

        while (randomNums.size() < count)
        {
            randomNums.add(generator.nextInt(max));
        }

        return randomNums;
    }

    public int getNumberOfTests()
    {
	logger.finest("Returning total number of tests");
	return testdb.getTotalNumberOfTests();
    }

    public String getTestName(int index)
    {
	String name = currentSuite.getTests().get(index).getClassname() + "." +  currentSuite.getTests().get(index).getMethodname();
	return name;
    }

    public String computeKillScore(boolean removeEquivs)
    {
        ArrayList<UnitTest> tests = currentSuite.getTests();
        ArrayList<Mutant> mutants = currentSuite.getMutants();
        HashSet<Mutant> mutantsCoveredBySuite = new HashSet<Mutant>();
        HashSet<Mutant> mutantsCoveredByTest = new HashSet<Mutant>();
        HashSet<Mutant> mutantsKilledBySuite = new HashSet<Mutant>();
        HashSet<Mutant> mutantsKilledByTest = new HashSet<Mutant>();

        for (UnitTest t : tests)
        {
            mutantsCoveredByTest = mutantdb.getMutantsCoveredByTest(t);
            mutantsKilledByTest = mutantdb.getMutantsKilledByTest(t);

            if (mutantsCoveredByTest != null)
            {
                for (Mutant m : mutantsCoveredByTest)
                {
                    if (mutants.contains(m))
                    {
                        mutantsCoveredBySuite.add(m);
                    }
                }
            }

            if (mutantsKilledByTest != null)
            {
                for (Mutant m : mutantsKilledByTest)
                {
                    if (mutants.contains(m))
                    {
                        mutantsKilledBySuite.add(m);
                    }
                }
            }
        }

        if (mutantsCoveredBySuite.size() == 0)
        {
            return "0,0";
        }

        if (removeEquivs)
        {
            HashSet<Mutant> equivMutants = mutantdb.getEquivalentMutants();
            Iterator<Mutant> mutantIter = mutantsCoveredBySuite.iterator();
            while (mutantIter.hasNext())
            {
                Mutant m = mutantIter.next();
                if (equivMutants.contains(m))
                {
                    mutantIter.remove();
                }
            }
        }

        return mutantsKilledBySuite.size() + "," + mutantsCoveredBySuite.size();
    }

    public void generateMutantSet(MutantSelectionStrategy ms, int size)
    {
        if (ms == MutantSelectionStrategy.REDUCE_RANDOM)
        {
            ArrayList<Mutant> randomMutants = new ArrayList<Mutant>();
            HashSet<Integer> mutantIndices = generateRandomNumbers(size,
                    mutantdb.getTotalNumberOfMutants());

            for (Integer i : mutantIndices)
            {
                randomMutants.add(mutantdb.getMutant(i));
            }

            currentSuite.setMutants(randomMutants);
        }
        else
        {
            logger.severe("Illegal mutation strategy passed to controller, aborting");
        }
    }

    public void generateMutantSet(MutantSelectionStrategy ms)
    {
        if (ms == MutantSelectionStrategy.ALL)
        {
            currentSuite.setMutants(mutantdb.getAllMutants());
        }
        else if (ms == MutantSelectionStrategy.REDUCE_FAULTY)
        {
            // TODO: hardcoded for New Ideas, should probably read from a
            // file or something
            ArrayList<String> faultyClasses = new ArrayList<String>();
            faultyClasses.add("org.apache.poi.hssf.usermodel.HSSFSheet");
            faultyClasses.add("org.apache.poi.hssf.usermodel.HSSFWorkbook");
            faultyClasses.add("org.apache.poi.xssf.usermodel.XSSFSheet");
            faultyClasses.add("org.apache.poi.xssf.usermodel.XSSFWorkbook");

            ArrayList<Mutant> reducedMutants = new ArrayList<Mutant>();
            ArrayList<Mutant> allMutants = mutantdb.getAllMutants();

            for (Mutant m : allMutants)
            {
                if (faultyClasses.contains(m.getClassname()))
                {
                    reducedMutants.add(m);
                }
            }

            logger.finest("Using " + reducedMutants.size()
                    + " mutants in the set of reduced mutants");
            currentSuite.setMutants(reducedMutants);
        }
        else
        {
            logger.severe("Illegal mutation strategy passed to controller, aborting");
        }
    }

    public void writeTestSuite(String filename, String header)
    {
        logger.finest("Writing test suite: " + filename + " with header: " + header);
        currentSuite.writeTestSuiteToFile(filename, header);
    }

    public String parseCoverageLog(String logfile)
    {
        LogParser lp = new LogParser();
        return lp.readCoverageResultFile(logfile);
    }
}
