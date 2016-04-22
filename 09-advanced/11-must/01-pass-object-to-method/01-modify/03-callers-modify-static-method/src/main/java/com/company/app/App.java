package com.company.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
checkout http://tutorials.jenkov.com/java-concurrency/java-memory-model.html
to see how method is called within a thread.
 */
class DriverInfo{
    public DriverInfo(String info){
        this.Info = info;
    }
    public String Info;
}
class Driver{
    public String Name;
}
class DriverKey{
    public DriverKey(String key){
        this.Key = key;
    }
    public String Key;
}
class DriverFactory{
    public static Map<String, Driver> providers = new ConcurrentHashMap<>();
    public static Driver newDriver(DriverKey driverKey, DriverInfo driverInfo){
        Driver driver;

        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName+" driverKey="+driverKey.Key);

        if(providers.containsKey(driverKey.Key)){
            driver = providers.get(driverKey.Key);
        }
        else{
            driver = new Driver();
            driver.Name = driverInfo.Info;
            providers.put(driverKey.Key, driver);
        }
        return driver;
    }
}
public class App
{
    public static void main( String[] args )
    {
        for(int i = 1; i <=3; i++){
            DriverFactory.newDriver(new DriverKey("Key_" + i), new DriverInfo("Name_" + i));
        }

        for(Map.Entry<String, Driver> entry : DriverFactory.providers.entrySet()){
            System.out.println("key="+entry.getKey()+", value=" + entry.getValue().Name);
        }

        System.out.println("\nMultiple Threads running");
        new Thread("Thread_1"){
            public void run(){
                Driver driver = DriverFactory.newDriver(new DriverKey("Key_1"), new DriverInfo("New_1"));
                System.out.println("Thread_1 "+driver.Name);
            }
        }.start();

        new Thread("Thread_2"){
            public void run(){
                try {
                    Thread.sleep(100);  // add sleep(1000) to make it easy to reproduce the race condition
                } catch (InterruptedException e) { }
                Driver driver = DriverFactory.newDriver(new DriverKey("Key_2"), new DriverInfo("New_2"));
                System.out.println("Thread_2 "+driver.Name);
            }
        }.start();
    }
}
/*
output:
main driverKey=Key_1
main driverKey=Key_2
main driverKey=Key_3
key=Key_1, value=Name_1
key=Key_2, value=Name_2
key=Key_3, value=Name_3

Multiple Threads running
Thread_1 driverKey=Key_1
Thread_1 Name_1
Thread_2 driverKey=Key_2
Thread_2 Name_2
 */