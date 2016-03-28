package com.company.app;

import java.io.FileWriter;
import java.io.IOException;

class FileWriterExample{
    private final FileWriter writer;
    public FileWriterExample(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }
    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }
    public void finalize() throws IOException {
        writer.close();
    }
    public void close() throws IOException {
        writer.close();
    }
}
class FileWriterARM implements AutoCloseable{
    private final FileWriter writer;
    public FileWriterARM(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }
    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }
    public void close() throws Exception {
        System.out.println("close called automcatically...");
        writer.close();
    }
}
public class App
{
    public static void main( String[] args ) throws Exception {
        System.out.println("#writeWithoutClose");
        writeWithoutClose();
        System.out.println("#writeThenClose");
        writeThenClose();
        System.out.println("#tryFinallyBlocks");
        tryFinallyBlocks();
        System.out.println("#tryWithResources");
        tryWithResources();
    }

    /*
    1, implement the AutoCloseable
    2, when leave the try block scope, the close method is automatically called
     */
    private static void tryWithResources() throws Exception {
        try(final FileWriterARM fileWriterARM = new FileWriterARM("peekaboo.txt")){
            fileWriterARM.writeStuff("peek-a-boo");
            System.out.println("done wirh the resource...");
        }
    }

    // use try and finally blocks to ensure resource cleanup, but code is smelly with lots of effort
    private static void tryFinallyBlocks() throws IOException {
        FileWriterExample writerExample = new FileWriterExample("peekaboo.txt");
        try{
            writerExample.writeStuff("peek-a-boo");
        }
        finally {
            writerExample.close();
        }
    }

    // close() is not reached if there is an exception before it
    private static void writeThenClose() throws IOException {
        FileWriterExample writerExample = new FileWriterExample("peekaboo.txt");
        writerExample.writeStuff("peek-a-boo");
        writerExample.close();
    }

    // peekaboo.txt is empty because the file was never closed, and the content was not flushed from memory
    private static void writeWithoutClose() throws IOException {
        FileWriterExample writerExample = new FileWriterExample("peekaboo.txt");
        writerExample.writeStuff("peek-a-boo");
    }
}
/*
output:
#writeWithoutClose
#writeThenClose
#tryFinallyBlocks
#tryWithResources
done wirh the resource...
close called automcatically...
 */
