package com.company.app;

import java.io.FileWriter;
import java.io.IOException;

// this interface allows lambda expression to throw checked exception
@FunctionalInterface
interface UseInstance<T, X extends Throwable> {
    void accept(T instance) throws X;
}
class FileWriterEAM {
    private final FileWriter writer;
    private FileWriterEAM(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }
    private void close() throws IOException {
        System.out.println("close called automatically...");
        writer.close();
    }
    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }
    // execute around method pattern
    public static void use(final String fileName, final UseInstance<FileWriterEAM, IOException> block) throws IOException {
        final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
        try {
            block.accept(writerEAM);
        } finally {
            writerEAM.close();
        }
    }
}
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("#oneWriteStuff");
        oneWriteStuff();
        System.out.println("#twoWriteStuff");
        twoWriteStuff();
    }
    private static void twoWriteStuff() throws IOException {
        FileWriterEAM.use("eam2.txt", w -> {
            w.writeStuff("how");
            w.writeStuff("sweet");
        });
    }
    private static void oneWriteStuff() throws IOException {
        FileWriterEAM.use("eam.txt", w -> w.writeStuff("sweet"));
    }
}
/*
output:
#oneWriteStuff
close called automatically...
#twoWriteStuff
close called automatically...

note:
When a team had to perform operations within the bounds of transactions. Rather than creating and managing transactions
all over the code, we wrapped them into a nice runWithinTransaction method. The method’s callers received a transaction
instance, and when they returned the method took care of checking the status as well as performing actions such as
committing or rolling back the transaction and logging.
 */
