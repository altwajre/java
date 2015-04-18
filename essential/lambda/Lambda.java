public class Lambda {

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello World!");
        r.run();
    }
}
