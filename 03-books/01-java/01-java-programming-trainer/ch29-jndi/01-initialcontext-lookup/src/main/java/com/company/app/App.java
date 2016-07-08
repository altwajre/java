package com.company.app;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class App {
    public static void main(String[] args) throws NamingException {
        // Identify service provider to use
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, "file:/Users/whan/jndi/");

        // Create the initial context
        Context ctx = new InitialContext(env);

        Monkey monkeySteven = new Monkey("steven", "banana", true);
//        ctx.bind("monkey steven", monkeySteven);
        ctx.rebind("monkey steven", monkeySteven);
//        ctx.unbind("monkey steven");

        Object lookup = ctx.lookup("monkey steven");
        System.out.println(lookup.getClass().getName());
        System.out.println(lookup);

        // Close the context when we're done
        ctx.close();
    }
}
/*
https://www.youtube.com/watch?v=NrxaJaKpnlg
http://www.java2s.com/Code/JarDownload/fscontext/fscontext-1.2.1.jar.zip
http://www.java2s.com/Code/JarDownload/sun/sun-jndi-providerutil.jar.zip

run it
use atom to open /Users/whan/jndi/.bindings
 */
