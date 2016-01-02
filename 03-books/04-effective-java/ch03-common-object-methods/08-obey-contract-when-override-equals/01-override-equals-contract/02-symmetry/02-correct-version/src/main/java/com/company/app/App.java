package com.company.app;

/*
Symmetric: For any non-null reference values x and y, x.equals(y) must return true if and only if y.equals(x) returns true
 */
final class CaseInsensitiveString{
    private final String s;

    public CaseInsensitiveString(String s) {
        if(s == null) throw new NullPointerException();
        this.s = s;
    }
    // This version is correct
    @Override
    public boolean equals(Object o){
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        return false;
    }
}
public class App
{
    public static void main( String[] args )
    {
        trueTest();

        falseTest();
    }

    private static void trueTest() {
        System.out.println("#trueTest");
        CaseInsensitiveString cis1 = new CaseInsensitiveString("Polish");
        CaseInsensitiveString cis2 = new CaseInsensitiveString("polish");
        System.out.println(cis1.equals(cis2) + "  " + cis2.equals(cis1));
    }

    private static void falseTest() {
        System.out.println("#falseTest");
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s) + "  " + s.equals(cis));
    }
}
/*
output:
#trueTest
true  true
#falseTest
false  false
 */