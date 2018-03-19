package com.company.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
https://www.journaldev.com/878/java-write-to-file
 */
public class App {
  public static void main(String[] args) {
    String data = "I will write this String to File in Java";
    int noOfLines = 3;
    writeUsingOutputStream(data);
    writeUsingFiles(data);
    writeUsingBufferedWriter(data, noOfLines);
    writeUsingFileWriter(data);
    System.out.println("done");
  }

  /**
   * Use Streams when you are dealing with raw data
   * @param data
   */
  private static void writeUsingOutputStream(String data) {
    OutputStream os = null;
    try {
      os = new FileOutputStream(new File("/Users/whan/test_os.txt"));
      os.write(data.getBytes(), 0, data.length());
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
      try {
        os.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Use Files class from Java 1.7 to write files, internally uses OutputStream
   * @param data
   */
  private static void writeUsingFiles(String data) {
    try {
      Files.write(Paths.get("/Users/whan/test_files.txt"), data.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Use BufferedWriter when number of write operations are more
   * It uses internal buffer to reduce real IO operations and saves time
   * @param data
   * @param noOfLines
   */
  private static void writeUsingBufferedWriter(String data, int noOfLines) {
    File file = new File("/Users/whan/test_BufferedWriter.txt");
    FileWriter fr = null;
    BufferedWriter br = null;
    String dataWithNewLine=data+System.getProperty("line.separator");
    try{
      fr = new FileWriter(file);
      br = new BufferedWriter(fr);
      for(int i = noOfLines; i>0; i--){
        br.write(dataWithNewLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
      try {
        br.close();
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Use FileWriter when number of write operations are less
   * @param data
   */
  private static void writeUsingFileWriter(String data) {
    File file = new File("/Users/whan/test_FileWriter.txt");
    FileWriter fr = null;
    try {
      fr = new FileWriter(file);
      fr.write(data);
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
      //close resources
      try {
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
/*
cat test_*
I will write this String to File in Java
I will write this String to File in Java
I will write this String to File in Java
I will write this String to File in JavaI will write this String to File in JavaI will write this String to File in Java
 */