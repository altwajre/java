import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda {

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello World!");  // void run();
        r.run();

        Predicate<String> predicate = (s) -> s == "true";  // boolean test(T t);
        System.out.println(predicate.test("true"));  // true

        Function<String, Integer> function = s -> s.length();
        System.out.println(function.apply("Hello"));  // 5
    }
}
