package com.company.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

interface Provider{
    Service newService();
}
interface Service{
    // Service-specific methods go here
}
class Services{
    private Services(){
        // Prevents instantiation (Item 4)
    }
    private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();
    public static final String DEFAULT_PROVIDER_NAME = "<def>";
    // Provider registration API
    public static void registerDefaultProvider(Provider provider){
        registerProvider(DEFAULT_PROVIDER_NAME, provider);
    }
    public static void registerProvider(String name, Provider provider){
        providers.put(name, provider);
    }
    // Service access API
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    public static Service newInstance(String name){
        Provider provider = providers.get(name);
        if(provider == null){
            throw new IllegalArgumentException("No provider registered with name: " + name);
        }
        return provider.newService();
    }
}
class MyService implements Service{
    @Override
    public String toString(){
        return "My Service";
    }
}
class MyProvider implements Provider{
    public Service newService() {
        return new MyService();
    }
}
public class App
{
    private static Provider DEFAULT_PROVIDER = new Provider() {
        public Service newService() {
            return new Service() {
                @Override
                public String toString(){
                    return "Default service";
                }
            };
        }
    };
    private static Provider COMP_PROVIDER = new Provider() {
        public Service newService() {
            return new Service() {
                @Override
                public String toString(){
                    return "Complementary service";
                }
            };
        }
    };
    private static Provider ARMED_PROVIDER = new Provider() {
        public Service newService() {
            return new Service() {
                @Override
                public String toString(){
                    return "Armed service";
                }
            };
        }
    };
    public static void main( String[] args )
    {
        // Providers would execute these lines
        Services.registerDefaultProvider(DEFAULT_PROVIDER);
        Services.registerProvider("comp", COMP_PROVIDER);
        Services.registerProvider("armed", ARMED_PROVIDER);
        Services.registerProvider("my", new MyProvider());

        // Clients would execute these lines
        Service s1 = Services.newInstance();
        Service s2 = Services.newInstance("comp");
        Service s3 = Services.newInstance("armed");
        Service s4 = Services.newInstance("my");
        System.out.printf("%s, %s, %s, %s%n", s1, s2, s3, s4);
    }
}
/*
output:
Default service, Complementary service, Armed service
 */
