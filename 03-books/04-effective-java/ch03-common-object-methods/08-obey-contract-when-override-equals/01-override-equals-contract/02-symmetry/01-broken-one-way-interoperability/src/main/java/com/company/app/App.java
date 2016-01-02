package com.company.app;

/*
Symmetric: For any non-null reference values x and y, x.equals(y) must return true if and only if y.equals(x) returns true
Once you're violated the equals contract, you simply don't know how other objects will behave wwhen confronted with your object
 */
final class CaseInsensitiveString{
    private final String s;

    public CaseInsensitiveString(String s) {
        if(s == null) throw new NullPointerException();
        this.s = s;
    }
    // Broken - violates symmetry
    @Override
    public boolean equals(Object o){
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        if (o instanceof String) // One-way interoperability: cis.equals(s) != s.equals(cis)
            return s.equalsIgnoreCase((String) o);
        return false;
    }
}
public class App
{
    public static void main( String[] args )
    {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s) + "  " + s.equals(cis));
    }
}
/*
output:
true  false
 */