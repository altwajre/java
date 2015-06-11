package com.company.test;

public class App extends Thread
{
    static class Context {
        private String transactionId = null;
        public void setTransactionId(String transactionId){
            this.transactionId = transactionId;
        }
        public String getTransactionId(){
            return transactionId;
        }
    }
    static class BusinessService {
        public void businessMethod(){ // get the context from thread local
            Context context = MyThreadLocal.get();
            System.out.println(context.getTransactionId());
        }
    }
    static class MyThreadLocal {
        public static final ThreadLocal<Context> userThreadLocal = new ThreadLocal<Context>();
        public static void set(Context user){
            userThreadLocal.set(user);
        }
        public static void unset(){
            userThreadLocal.remove();
        }
        public static Context get(){
            return userThreadLocal.get();
        }
    }
    public static void main( String[] args ) {
        Thread threadOne = new App();
        threadOne.start();
        Thread threadTwo = new App();
        threadTwo.start();
    }
    @Override
    public void run(){
        // simulate set transaction id
        Context context = new Context();
        context.setTransactionId(getName());

        // Set the context object in thread local to access it somewhere else
        MyThreadLocal.set(context);

        // Note that we are not explicitly passing the transaction id
        new BusinessService().businessMethod();
        MyThreadLocal.unset();
    }
}
