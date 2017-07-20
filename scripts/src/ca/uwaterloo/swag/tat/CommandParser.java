package ca.uwaterloo.swag.tat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CommandParser
{
    private static Logger      rootLogger = Logger.getLogger("ca.uwaterloo.swag.tat");
    private static Logger      logger     = Logger.getLogger(CommandParser.class
                                                  .getName());
    private static Level       level      = Level.FINEST;
    private Controller         controller;
    private BufferedReader     in         = null;
    private OutputStreamWriter out        = null;

    /**
     * @param args[0]: the name of the master suite
     * @param args[1]: the full path of the mutation log
     * @param args[2]: the full path of the output log
     */
    public static void main(String args[])
    {
        new CommandParser(args);
    }

    public CommandParser(String[] args)
    {
        configureRootLogger(args[2]);
        logger.setLevel(level);
        logger.finest("Logger " + logger.getName()
                + " initialized; logging messages at level " + level.toString()
                + " or higher");
        start(args);
    }

    private void configureRootLogger(String logfilename)
    {
        // Create a file handler for the root logger
        try
        {
            FileHandler fh = new FileHandler(logfilename, true);
            fh.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fh);
        }
        catch (Exception e)
        {
            System.err.println("Root logger initialization failed; aborting");
            e.printStackTrace();
            System.exit(1);
        }

        // Set the log level for all of the root logger's handlers
        // Make all of them "finest" so any message that makes it past logging
        // is handled
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler h : handlers)
        {
            h.setLevel(Level.FINEST);
        }

        // Set the root logger's level to FINEST -- this will be the default for
        // children
        rootLogger.setLevel(Level.FINEST);
        rootLogger.finest("Root logger initialized");
    }

    private void start(String[] args)
    {
        String input;

        try
        {
            logger.finest("Attempting to open stdin and stdout");
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new OutputStreamWriter(System.out);
        }
        catch (Exception e)
        {
            logger.severe("Could not open fifos; aborting");
            e.printStackTrace();
            System.exit(1);
        }

        controller = new Controller(args);
        ack();
        
        while (true)
        {
            logger.finest("Looping");
            try
            {
                logger.finest("Reading a command from stdin");
                input = in.readLine();
                if (input == null)
                {
                    break;
                }

                if (input.startsWith("gts"))
                {
                    logger.finest("Received a generate test suite command");
                    parseGenerateTestSuiteCommand(input.split("\\s+"));
		    ack();
                }
                else if (input.startsWith("cks"))
                {
                    logger.finest("Received a compute kill score command");
                    parseComputeKillScoreCommand(input.split("\\s+"));
                }
                else if (input.startsWith("gms"))
                {
                    logger.finest("Received a generate mutant set command");
                    parseGenerateMutantSetCommand(input.split("\\s+"));
		    ack();
                }
                else if (input.startsWith("wts"))
                {
                    logger.finest("Received a write test suite command");
                    parseWriteTestSuiteCommand(input);
		    ack();
                }
                else if (input.startsWith("pcl"))
                {
                    logger.finest("Received a parse coverage log command");
                    parseParseCoverageLogCommand(input.split("\\s+"));
                }
		else if (input.startsWith("gnt"))
		{
		    logger.finest("Received a get number of tests command");
		    out.write(controller.getNumberOfTests() + "\n");
		    out.flush();
		}
		else if (input.startsWith("gtn"))
		{
		    logger.finest("Received a get test name command");
		    out.write(controller.getTestName(0) + "\n");
		    out.flush();
		}
                else if (input.startsWith("exit"))
                {
                    logger.finest("Received an exit command");
                    break;
                }
                else
                {
                    logger.warning("Unrecognized command");
                }
            }
            catch (IOException e)
            {
                logger.severe("Could not read stdin, aborting");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private void ack()
    {
	try
        {
            out.write("OK\n");
            out.flush();
        }
        catch (IOException e)
        {
            logger.severe("Could not write to stdout, aborting");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void parseGenerateTestSuiteCommand(String[] command)
    {
        boolean singleMethodSuite;
        int sizeOrIndex;

        if (command[1].equals("method"))
        {
            singleMethodSuite = true;
        }
        else if (command[1].equals("suite"))
        {
            singleMethodSuite = false;
        }
        else
        {
            logger.warning("Unrecognized argument: use \"method\" or \"suite\"");
            return;
        }

        try
        {
            sizeOrIndex = Integer.parseInt(command[2]);
        }
        catch (NumberFormatException e)
        {
            logger.warning("Invalid suite size or index");
            return;
        }

        logger.finest("Executing a generate test suite command");
        controller.generateTestSuite(singleMethodSuite, sizeOrIndex);
    }

    private void parseComputeKillScoreCommand(String[] command)
    {
        boolean removeEquivs;

        if (command[1].equals("inc"))
        {
            removeEquivs = false;
        }
        else if (command[1].equals("exc"))
        {
            removeEquivs = true;
        }
        else
        {
            logger.warning("Illegal option, specify \"inc\" or \"exc\"");
            return;
        }

        logger.finest("Executing a compute kill score command");
        try
        {
            out.write(controller.computeKillScore(removeEquivs) + "\n");
            out.flush();
        }
        catch (Exception e)
        {
            logger.severe("Failed to write a kill score to stdout");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void parseGenerateMutantSetCommand(String[] command)
    {
        MutantSelectionStrategy ms;

        if (command[1].equals("all"))
        {
            ms = MutantSelectionStrategy.ALL;
            logger.finest("Executing a generate mutant set command");
            controller.generateMutantSet(ms);
        }
        else if (command[1].equals("random"))
        {
            ms = MutantSelectionStrategy.REDUCE_RANDOM;
            int size;
            try
            {
                size = Integer.parseInt(command[2]);
            }
            catch (NumberFormatException e)
            {
                logger.warning("Invalid mutant set size");
                return;
            }

            logger.finest("Executing a generate mutant set command");
            logger.finest("Set will include " + size + " mutants");
            controller.generateMutantSet(ms, size);
        }
        else if (command[1].equals("faulty"))
        {
            ms = MutantSelectionStrategy.REDUCE_FAULTY;
            logger.finest("Executing a generate mutant set command");
            controller.generateMutantSet(ms);
        }
        else
        {
            logger.warning("Unrecognized method: " + command[1]
                    + "; use \"all\", \"random\" or \"faulty\"");
            return;
        }
    }

    public void parseWriteTestSuiteCommand(String input)
    {
        //Input format:
        //wts path/to/test "file header with spaces"
        
        int spaceBeforeHeader = input.indexOf(" ", 4);
        String path = input.substring(4, spaceBeforeHeader);
        String header = input.substring(spaceBeforeHeader+1);
        header = header.replace("\\n", "\n");
        
        logger.finest("Executing a write test suite command");
        controller.writeTestSuite(path, header);
    }

    public void parseParseCoverageLogCommand(String[] command)
    {
        logger.finest("Executing a parse coverage log command");
        logger.finest(command.toString());

        try
        {
            out.write(controller.parseCoverageLog(command[1]) + "\n");
            out.flush();
        }
        catch (Exception e)
        {
            logger.severe("Failed to write a coverage score to stdout");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
