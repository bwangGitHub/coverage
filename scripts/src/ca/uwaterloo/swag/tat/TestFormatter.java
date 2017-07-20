package ca.uwaterloo.swag.tat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestFormatter
{
	public TestFormatter()
	{}

	public static void main(String[] args) throws IOException
	{
		TestFormatter tf = new TestFormatter();
		tf.addConstructor(args[0]);
	}

	public void addConstructor(String rootdir) throws IOException
	{
		// Examine every file in the root directory
		String[] filenames = (new File(rootdir)).list();
		for (String f : filenames)
		{
			// See if the file is a regular file or a directory
			String fname = rootdir + "/" + f;
			File file = new File(fname);

			if (file.isFile())
			{
				// The file is a regular file, so see if it's a test file
				boolean isJavaFile = false;
				boolean startsWithTest = false;
				boolean startsWithBaseTest = false;

				//boolean useFile = false;

				if (f.length() >= 4)
				{
				    //useFile = (f.substring(f.length() - 9, f.length())).equals("Test.java");

					startsWithTest = (f.substring(0, 4)).equals("Test");
					isJavaFile = (f.substring(f.length() - 4, f.length()))
							.equals("java");
				}
				if (f.length() >= 8)
				{
					startsWithBaseTest = (f.substring(0, 8)).equals("BaseTest");
				}

				if (isJavaFile && (startsWithTest || startsWithBaseTest))
				    //if (useFile)
				{
					// It is, so read it in
					BufferedReader infile = new BufferedReader(new FileReader(
							file));
					ArrayList<String> infileLines = new ArrayList<String>();
					String line;
					while ((line = infile.readLine()) != null)
					{
						infileLines.add(line);
					}
					infile.close();

					// Find the final curly brace in the file and insert the new
					// constructor right before it
					// This assumes the closing brace for the class doesn't have
					// code on the same line
					BufferedWriter outfile = new BufferedWriter(new FileWriter(
							fname + ".temp"));
					String constructor = "\npublic "
							+ f.substring(0, f.length() - 5)
							+ " (String name) {super(name);}\n";
					for (int i = infileLines.size() - 1; i >= 0; i--)
					{
						line = infileLines.get(i);
						if (line.contains("}"))
						{
							// Write out the lines from index 0 to i-1
							for (int j = 0; j < i; j++)
							{
								outfile.write(infileLines.get(j) + "\n");
							}

							// Write the constructor
							outfile.write(constructor);

							// Write the rest of the infile
							for (int j = i; j < infileLines.size(); j++)
							{
								outfile.write(infileLines.get(j) + "\n");
							}
							break;
						}
					}
					outfile.close();

					// Write over the input file with the output file
					File temp = new File(fname + ".temp");
					boolean success = temp.renameTo(file);
					if (!success)
					{
						System.err.println("Rename failed");
					}
				}
			}
			else if (file.isDirectory())
			{
				// The file is a directory so look for more files recursively
				addConstructor(fname);
			}
		}
	}
}
