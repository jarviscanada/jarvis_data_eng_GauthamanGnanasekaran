package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepImp implements JavaGrep{

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

//  public JavaGrepImp(String regex, String rootPath, String outFile) {
//    setRegex(regex);
//    setRootPath(rootPath);
//    setOutFile(outFile);
//  }

  @Override
  /**
   * Search flow for the top level
   * @throws java.io.IOException
   */
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();

    for (File file : this.listFiles(this.getRootPath())) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }

    writeToFile(matchedLines);
  }
  /**
    * Traverse a given directory and return all files
   * @param rootDir input directory
   * @return files under the rootDir
   */
  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<>();
    File root = new File(rootDir);
    for(File eachFile: root.listFiles()){
      files.add(eachFile);
    }

    return files;

  }

  /**
   * Read a file and return all the lines
   * InputReader: Reading character files. Meant for reading streams of character
   * BufferedReader: Reads text from a character input stream.
   * Character Encoding: Using a key to unlock the character to read the data
   * @param inputFile
   * @return list of strings
   * @throws IllegalArgumentException if a given input is not a file
   */

  @Override
  public List<String> readLines(File inputFile) throws IOException, IllegalArgumentException {
    List<String> lines = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e){}
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.matches(this.getRegex(), line);
  }

  /**
   * write lines to a file
   * FileOutputStream: Output stream for writing data to a file
   * OutputStreamWriter: Characters written are encoded into bytes using a specified charset
   * BufferedWriter: Buffers characters to provide efficient writing
   * @param lines matched line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    try(    BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
    ){
      for(String line : lines){
        writer.write(line + "\n");
      }
      writer.close();
    }catch (IOException ioe){

    }

  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  public static void main(String[] args) {
    if(args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex root path outFile");
    }

    BasicConfigurator.configure();
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch(Exception ex){
      javaGrepImp.logger.error("Error: unable to process",ex);
    }

  }

}
