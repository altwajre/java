package com.company.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class App
{
    static void watchFileChange(){
        try {
            final Path path = Paths.get(".");
            final WatchService watchService = path.getFileSystem()
                    .newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("Report any file changed within next 1 minute...");
            final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
            if(watchKey != null){
                watchKey.pollEvents()
                        .stream()
                        .forEach(event -> System.out.println(event.context()));
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void main( String[] args ) throws Exception {
        new Thread(() -> watchFileChange()).start();
        createNewFile("sample.txt");
        createNewFile("foo.txt");
    }

    private static void createNewFile(String fileName) throws IOException, InterruptedException {
        final File file = new File(fileName);
        file.createNewFile();
        Thread.sleep(1000);
        file.setLastModified(System.currentTimeMillis());
    }
}
/*
output:
Report any file changed within next 1 minute...
foo.txt
sample.txt
 */
