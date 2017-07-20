package ca.uwaterloo.swag.tat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class MutantDatabase
{
    private static Logger                      logger = Logger.getLogger(MutantDatabase.class
                                                              .getName());
    private static Level                       level  = Level.INFO;
    private HashMap<UnitTest, HashSet<Mutant>> coveredMutants;
    private HashMap<UnitTest, HashSet<Mutant>> killedMutants;
    private HashSet<Mutant>                    equivalentMutants;
    private ArrayList<Mutant>                  allMutants;

    public MutantDatabase(String log)
    {
        logger.setLevel(level);
        logger.finest("Logger " + logger.getName()
                + " initialized; logging messages at level " + level.toString()
                + " or higher");
        killedMutants = new HashMap<UnitTest, HashSet<Mutant>>();
        coveredMutants = new HashMap<UnitTest, HashSet<Mutant>>();
        equivalentMutants = new HashSet<Mutant>();
        allMutants = new ArrayList<Mutant>();
        parseLog(log);
    }

    private void parseLog(String log)
    {
        try
        {
            BufferedReader logfile = new BufferedReader(new FileReader(
                    new File(log)));
            Pattern currentMutantPattern = Pattern
                    .compile("XXX: running mutation:([^,]+),([^,]+),([0-9]+),\\[(.+)\\]YYY");
            Pattern killedPattern = Pattern
                    .compile("XXX: one result is: KILLED by ([^\\(]+)\\(.+\\)YYY");
            Pattern survivedPattern = Pattern
                    .compile("XXX: one result is: SURVIVEDYYY");
            Matcher m;
            String line;
            Mutant currentMutant = new Mutant();

            while ((line = logfile.readLine()) != null)
            {
                m = currentMutantPattern.matcher(line);
                if (m.find())
                {
                    logger.finest("Found a new mutant, resetting current mutant");
                    currentMutant = new Mutant(m.group(1), m.group(2),
                            Integer.parseInt(m.group(3)));
                    allMutants.add(currentMutant);
                    ArrayList<UnitTest> testsCoveringMutant = extractTests(m
                            .group(4));
                    for (UnitTest test : testsCoveringMutant)
                    {
                        HashSet<Mutant> mutantsCoveredByTest = getMutantsCoveredByTest(test);
                        mutantsCoveredByTest.add(currentMutant);
                        coveredMutants.put(test, mutantsCoveredByTest);
                    }
                }

                m = killedPattern.matcher(line);
                if (m.find())
                {
                    logger.finest("Found a test that kills the current mutant");
                    UnitTest killingTest = new UnitTest(m.group(1));
                    HashSet<Mutant> mutantsKilledByTest = getMutantsKilledByTest(killingTest);
                    mutantsKilledByTest.add(currentMutant);
                    killedMutants.put(killingTest, mutantsKilledByTest);
                }

                // If we find a survivedPattern, the mutant is equivalent
                m = survivedPattern.matcher(line);
                if (m.find())
                {
                    logger.finest("Found a surviving mutant, adding it to list of equivalent mutants");
                    equivalentMutants.add(currentMutant);
                }
            }

            if (killedMutants.containsKey(new Mutant()))
            {
                logger.severe("Log error: found a killed mutant before the mutant itself; aborting");
                System.exit(1);
            }
            logger.info(coveredMutants.size()
                    + " tests cover one or more mutants");
            logger.info(killedMutants.size()
                    + " tests killed one or more mutants");
            logger.info(equivalentMutants.size() + " mutants are equivalent");
            logfile.close();
        }

        catch (Exception e)
        {
            logger.severe("Could not open log file; aborting");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private ArrayList<UnitTest> extractTests(String listOfTests)
    {
        Pattern testNamePattern = Pattern.compile("([^\\s\\(,]+)\\([^\\)]+\\)");
        Matcher m = testNamePattern.matcher(listOfTests);
        ArrayList<UnitTest> tests = new ArrayList<UnitTest>();

        while (m.find())
        {
            logger.finest("Identified a test with the name " + m.group(1));
            tests.add(new UnitTest(m.group(1)));
        }

        return tests;
    }

    public int getTotalNumberOfMutants()
    {
        return allMutants.size();
    }

    public int getNumberOfEquivalentMutants()
    {
        return equivalentMutants.size();
    }

    public int getNumberOfKilledMutants()
    {
        return killedMutants.size();
    }

    public int getNumberOfCoveredMutants()
    {
        return coveredMutants.size();
    }

    public HashSet<Mutant> getMutantsCoveredByTest(UnitTest t)
    {
        HashSet<Mutant> mutantsCoveredByTest;
        if (coveredMutants.containsKey(t))
        {
            mutantsCoveredByTest = coveredMutants.get(t);
        }
        else
        {
            mutantsCoveredByTest = new HashSet<Mutant>();
        }
        return mutantsCoveredByTest;
    }

    public HashSet<Mutant> getMutantsKilledByTest(UnitTest t)
    {
        HashSet<Mutant> mutantsKilledByTest;
        if (killedMutants.containsKey(t))
        {
            mutantsKilledByTest = killedMutants.get(t);
        }
        else
        {
            mutantsKilledByTest = new HashSet<Mutant>();
        }
        return mutantsKilledByTest;
    }

    public ArrayList<Mutant> getAllMutants()
    {
        return allMutants;
    }

    public Mutant getMutant(int index)
    {
        return allMutants.get(index);
    }

    public HashSet<Mutant> getEquivalentMutants()
    {
        return equivalentMutants;
    }

    public HashMap<UnitTest, HashSet<Mutant>> getCoveredMutants()
    {
        return coveredMutants;
    }

    public HashMap<UnitTest, HashSet<Mutant>> getKilledMutants()
    {
        return killedMutants;
    }
}
