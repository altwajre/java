import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/*
https://www.youtube.com/watch?v=pRwvx4QpUfo
*/
public class ClassMetadataLeakSimulator {
  private static Map<String, ClassA> classLeakingMap = new HashMap<>();
  private final static int NB_ITERATIONS_DEFAULT = 50000;

  public static void main(String[] args) {
    System.out.println("Class metadata leak simulator");

    int nbIterations = (args != null && args.length == 1) ? Integer.parseInt(args[0]) : NB_ITERATIONS_DEFAULT;

    try {

      for (int i = 0; i < nbIterations; i++) {

        String fictiousClassloaderJAR = "file:" + i + ".jar";

        URL[] fictiousClassloaderURL = new URL[] { new URL(fictiousClassloaderJAR) };

        // Create a new classloader instance
        URLClassLoader newClassLoader = new URLClassLoader(fictiousClassloaderURL);

        // Create a new Proxy instance
        ClassA t = (ClassA) Proxy.newProxyInstance(newClassLoader,
            new Class<?>[] { ClassA.class },
            new ClassAInvocationHandler(new ClassAImpl()));

        // Add the new Proxy instance to the leaking HashMap
        classLeakingMap.put(fictiousClassloaderJAR, t);
      }
    }
    catch (Throwable any) {
      System.out.println("ERROR: " + any);
    }

    System.out.println("Done!");
  }
}
