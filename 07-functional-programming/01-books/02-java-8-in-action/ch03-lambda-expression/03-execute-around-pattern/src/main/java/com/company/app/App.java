package com.company.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
A recurrent pattern in resource processing (for example, dealing with files or databases) is to open a resource, do some
processing on it, and then close the resource. The setup and cleanup phases are always similar and surround the
important code doing the processing. This is called the execute around pattern.
Tasks A and B are surrounded by the same redundant code responsible for preparation/cleanup.
 */
public class App
{
    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    interface BufferedReaderProcessor{
        public String process(BufferedReader bufferedReader) throws IOException;
    }
    public static String processFile(BufferedReaderProcessor processor) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return processor.process(br);
        }
    }

    public static void main( String[] args ) throws IOException {
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(twoLines);
    }
}
