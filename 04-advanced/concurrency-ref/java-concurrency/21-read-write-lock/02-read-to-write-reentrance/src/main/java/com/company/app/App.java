package com.company.app;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App
{
    static class ReadWriteLock{
        Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
        int readers = 0;
        int writers = 0;
        int writeRequests = 0;
        int writeAccesses = 0;
        Thread writingThread = null;

        public synchronized void lockWrite() throws InterruptedException {
            writeRequests++;
            Thread callingThread = Thread.currentThread();
            while(!canGrantWriteAccess(callingThread)){
                wait();
            }
            writeRequests--;
            writeAccesses++;
            writingThread = callingThread;
        }
        public synchronized void unlockWrite(){
            writeAccesses--;
            if(writeAccesses == 0){
                writingThread = null;
            }
            notifyAll();
        }
        boolean canGrantWriteAccess(Thread callingThread){
            if(isOnlyReader(callingThread)) return true;
            if(hasReaders()) return false;
            if(writingThread == null) return true;
            if(!isWriter(callingThread)) return false;
            return true;
        }
        boolean hasReaders(){
            return readingThreads.size() > 0;
        }
        boolean isWriter(Thread callingThread){
            return writingThread == callingThread;
        }
        boolean isOnlyReader(Thread callingThread){
            return readers == 1 && readingThreads.get(callingThread) != null;
        }

        public synchronized void lockRead() throws InterruptedException {
            Thread callingThread = Thread.currentThread();
            while(!canGrantReadAccess(callingThread)){
                wait();
            }
            readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);
        }
        boolean canGrantReadAccess(Thread callingThread){
            if(writers > 0) return false;
            if(isReader(callingThread)) return true;
            if(writeRequests > 0) return false;
            return true;
        }
        int getReadAccessCount(Thread callingThread){
            Integer accessCount = readingThreads.get(callingThread);
            if(accessCount == null) return 0;
            return accessCount.intValue();
        }
        boolean isReader(Thread callingThread){
            return readingThreads.get(callingThread) != null;
        }
    }
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
    }
}
