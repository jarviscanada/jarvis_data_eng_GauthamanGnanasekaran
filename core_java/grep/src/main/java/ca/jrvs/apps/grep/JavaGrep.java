package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public interface JavaGrep {
  /**
   * Search flow for the top level
   * @throws java.io.IOException
   */
  void process() throws IOException;

  /**
   * Traverse a given directory and return all files
   * @param rootDir input directory
   * @return files under the rootDir
   */
  List<File> listFiles(String rootDir);

  /**
   * Read a file and return all the lines
   * InputReader: Reading character files. Meant for reading streams of character
   * BufferedReader: Reads text from a character input stream.
   * Character Encoding: Using a key to unlock the character to read the data
   * @param inputFile
   * @return list of strings
   * @throws IllegalArgumentException if a given input is not a file
   */

  List<String> readLines(File inputFile) throws IOException, IllegalArgumentException;


  /**
   * Check if a line contains the regex pattern (passed by user)
   * @param line input string
   * @return true if there is a match
   */

  boolean containsPattern(String line);

  /**
   * write lines to a file
   * FileOutputStream: Output stream for writing data to a file
   * OutputStreamWriter: Characters written are encoded into bytes using a specified charset
   * BufferedWriter: Buffers characters to provide efficient writing
   * @param lines matched line
   * @throws IOException if write failed
   */
  void writeToFile(List<String> lines) throws IOException;

  /**
   * Get the root path
   * @return
   */
  String getRootPath();

  /**
   * set the rootPath to the string that is entered
   * @param rootPath
   */
  void setRootPath(String rootPath);

  /**
   * get the expression from the instance
   * @return
   */
  String getRegex();

  /**
   * set the regex of this instance
   * @param regex
   */
  void setRegex(String regex);

  /**
   * Retrieve the output file from this instance
   * @return
   */
  String getOutFile();

  /**
   * set the output file of this instance
   * @param outFile
   */
  void setOutFile(String outFile);

}
