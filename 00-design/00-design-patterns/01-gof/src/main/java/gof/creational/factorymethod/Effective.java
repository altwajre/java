package gof.creational.factorymethod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
Consider static factory methods instead of constructors

https://www.safaribooksonline.com/library/view/effective-java-2nd/9780137150021/ch02.html#ch02lev1sec1
 */
interface Service {
}

interface Provider {
  Service newService();
}

class Services {
  private Services() {
  }

  private static final Map<String, Provider> providers = new ConcurrentHashMap<>();
  public static final String DEFAULT_PROVIDER_KEY = "<default>";

  // Registration API
  public static void registerProvider(String key, Provider provider) {
    providers.put(key, provider);
  }

  public static void registerDefaultProvider(Provider provider) {
    registerProvider(DEFAULT_PROVIDER_KEY, provider);
  }

  // Service access API
  public static Service newInstance(String key) { // static factory method
    Provider provider = providers.get(key);
    if (provider == null) {
      throw new IllegalArgumentException("No provider registered with key: " + key);
    }
    return provider.newService();
  }

  public static Service newInstance() {
    return newInstance(DEFAULT_PROVIDER_KEY);
  }
}

class MyService implements Service {
  @Override
  public String toString() {
    return "My Service";
  }
}

class MyProvider implements Provider {
  @Override
  public Service newService() {
    return new MyService();
  }
}

public class Effective {
  private static Provider DEFAULT_PROVIDER = () -> new Service() {
    @Override
    public String toString() {
      return "Default service";
    }
  };
  private static Provider COMP_PROVIDER = () -> new Service() {
    @Override
    public String toString() {
      return "Complementary service";
    }
  };

  public static void main(String[] args) {
    Services.registerDefaultProvider(DEFAULT_PROVIDER);
    Services.registerProvider("comp", COMP_PROVIDER);
    Services.registerProvider("my", new MyProvider());

    final Service defaultService = Services.newInstance();// default
    System.out.println("defaultService: " + defaultService);
    final Service compService = Services.newInstance("comp");
    System.out.println("compService: " + compService);
    final Service myService = Services.newInstance("my");
    System.out.println("myService: " + myService);
  }
}
/*
defaultService: Default service
compService: Complementary service
myService: My Service
 */
