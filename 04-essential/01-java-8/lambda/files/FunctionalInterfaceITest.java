public class FunctionalInterfaceITest {
    interface ITest{
        void run();
    }
    static class Test1 implements ITest{

        @Override
        public void run() {
            System.out.println("Running Test1");
        }
    }
    static void runner(ITest test){
        test.run();
    }
    public static void main(String[] args) {
        runner(new Test1());
        runner(() -> System.out.println("Running lambda"));
    }
}
