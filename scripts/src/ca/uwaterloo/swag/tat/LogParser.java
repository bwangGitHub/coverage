package ca.uwaterloo.swag.tat;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class LogParser
{
    private static Logger logger = Logger.getLogger(LogParser.class.getName());
    private static Level  level  = Level.FINEST;

    public LogParser()
    {
        logger.setLevel(level);
        logger.finest("Logger " + logger.getName()
                + " initialized; logging messages at level " + level.toString()
                + " or higher");
    }

    public String readCoverageResultFile(String filename)
    {
        double stmtCoverage = -1.0;
        double branchCoverage = -1.0;
        double mcCoverage = -1.0;

        logger.finest("Reading coverage results from " + filename);
        try
        {
            BufferedReader infile = new BufferedReader(new FileReader(new File(
                    filename)));

            // There's five sorttable_customkeys and we want the second (stmt),
            // third (branch) and fifth (MCC)
            Pattern sorttablePatt = Pattern.compile("sorttable_customkey");
            Pattern coveragePatt = Pattern
                    .compile("([0-9]+)&nbsp;/&nbsp;([0-9]+)");
            Matcher sorttableMatcher;
            Matcher coverageMatcher;
            String line;
            int i = 0;

            while ((line = infile.readLine()) != null)
            {
                sorttableMatcher = sorttablePatt.matcher(line);
                if (sorttableMatcher.find())
                {
                    logger.finest("Found a match for the coverage pattern in the coverage log");
                    line = infile.readLine();
                    coverageMatcher = coveragePatt.matcher(line);
                    if (coverageMatcher.find())
                    {
                        if (i == 1) //(i == 8) //hack for closure (i == 1)
                        {
                            if (Double.parseDouble(coverageMatcher.group(2)) == 0)
         			{
				    stmtCoverage = 0;
				}
			    else
				{
				    stmtCoverage = Double.parseDouble(coverageMatcher
                                    .group(1))
                                    / Double.parseDouble(coverageMatcher
                                            .group(2));
				}
                        }
                        else if (i == 2) //(i == 9) //hack (i == 2)
                        {
			    if (Double.parseDouble(coverageMatcher.group(2)) == 0)
				{
				    branchCoverage = 0;
				}
			    else
				{
			    branchCoverage = Double.parseDouble(coverageMatcher
                                    .group(1))
                                    / Double.parseDouble(coverageMatcher
                                            .group(2));
				}
                        }
                        else if (i == 4) //(i == 11) //hack (i == 4)
                        {
			    if (Double.parseDouble(coverageMatcher.group(2)) == 0)
				{
				    mcCoverage = 0;
				}
			    else
				{
mcCoverage = Double.parseDouble(coverageMatcher
                                    .group(1))
                                    / Double.parseDouble(coverageMatcher
                                            .group(2));
				}
                        }
                    }
                    i++;
                }
            }
            infile.close();
        }

        catch (FileNotFoundException e)
        {
            // CodeCover did not collect any metadata, so coverage is 0
            logger.warning("File not found: assuming no metadata was collected");
            stmtCoverage = 0.0;
            branchCoverage = 0.0;
            mcCoverage = 0.0;
        }

        catch (Exception e)
        {
            logger.severe("Could not parse coverage file; aborting");
            e.printStackTrace();
            System.exit(1);
        }

        return stmtCoverage + "," + branchCoverage + "," + mcCoverage;
    }

}
