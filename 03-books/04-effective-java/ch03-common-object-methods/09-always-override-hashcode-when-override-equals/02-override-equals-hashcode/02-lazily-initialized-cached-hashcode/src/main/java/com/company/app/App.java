package com.company.app;

import java.util.HashMap;
import java.util.Map;

/*
If a class is immutable and the cost of computing the hash code is significant, you might consider caching the hash code
in the object rather than recalculating it each time it is requested. If you believe that most objects of this type will
be used as hash keys, then you should calculate the hash code when the instance is created. Otherwise, you might choose
to lazily initialize it the first time hashCode is invoked.
 */
class PhoneNumber{
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    private static void rangeCheck(int arg, int max, String name){
        if(arg < 0 || arg > max){
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof PhoneNumber)) return false;
        PhoneNumber pn = (PhoneNumber)o;
        return pn.lineNumber == lineNumber && pn.prefix == prefix && pn.areaCode == areaCode;
    }

    // Lazily initialized, cached hashCode
    private volatile int hashCode;
    @Override
    public int hashCode(){
        int result = hashCode;
        if(result == 0){
            result = 17;
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
            hashCode = result;
        }
        return result;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny");
        System.out.println(m.get(new PhoneNumber(707, 867, 5309)));
    }
}
/*
output:
Jenny
 */