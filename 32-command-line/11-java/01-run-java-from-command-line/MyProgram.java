package mypackage;

public class MyProgram
{
    public static void main( String[] args )
    {
        System.out.println( "Hello world" );
        for(int i = 0; i < args.length; i++){
          System.out.println("args[" + i + "] is " + args[i]);
        }
        System.out.println( "No more" );
    }
}
