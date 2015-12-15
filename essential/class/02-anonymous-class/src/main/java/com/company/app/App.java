package com.company.app;

interface Provider{
    Service createService();
}
interface Service{
    String run();
}
interface Provider2{
    Service2 createService();
}
interface Service2{}
public class App
{
    private static Service MyService = new Service() {
        @Override
        public String run() {
            return "HisService";
        }
    };
    private static Service HisService = () -> {
        return "MyService";
    };
    private static Provider MyProvider = () -> {
      return () -> { return "MyProvider.Service"; };
    };
    private static Service2 MyService2 = new Service2() {
        @Override
        public String toString() {
            return "MyService2 Override toString()";
        }
    };
    private static Provider2 MyProvider2 = new Provider2() {
        @Override
        public Service2 createService() {
            return new Service2() {
                @Override
                public String toString() {
                    return "MyProvider2.Service2.toString()";
                }
            };
        }
    };
    public static void main( String[] args )
    {
        testProviderService();
        testProviderService2();
    }

    private static void testProviderService2() {
        System.out.println("***testProviderService2***");
        System.out.println(MyService2);
        System.out.println(MyProvider2.createService());
    }

    private static void testProviderService() {
        System.out.println("***testProviderService***");
        System.out.println(MyService.run());
        System.out.println(HisService.run());
        System.out.println(MyProvider.createService().run());
    }
}
/*
output:
***testProviderService***
HisService
MyService
MyProvider.Service
***testProviderService2***
MyService2 Override toString()
MyProvider2.Service2.toString()
 */
