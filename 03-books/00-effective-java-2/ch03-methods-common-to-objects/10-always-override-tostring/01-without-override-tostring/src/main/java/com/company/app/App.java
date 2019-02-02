package com.company.app;

import java.util.HashMap;
import java.util.Map;

/*
By default, it consists of the class named followed by an "at" sign (@) and the unsigned hexadecimal representation of
the hash code, for example, "PhoneNumber@163b91".
 */
final class PhoneNumber{
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    PhoneNumber(int areaCode, int prefix, int lineNumber) {
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
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNumber == lineNumber && pn.prefix == prefix && pn.areaCode == areaCode;
    }
    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNumber;
        return result;
    }
//    @Override
//    public String toString(){
//        return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
//    }
}
public class App
{
    public static void main( String[] args )
    {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny");
        System.out.println(m);
    }
}
/*
output:
{com.company.app.PhoneNumber@12960c=Jenny}
 */
