package com.company.app;

import java.util.*;

final class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
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
    /*
    Returns the string representation of this phone number. The string consists of fourteen characters whose format is
    "(xxx) YYY-ZZZZ", where XXX is the area code, YYY is the prefix, and ZZZZ is the line number. (Each of the capital
    letters presents a single decimal digit.)
    If any of the three parts of this phone number is too small to fill up its field, the field is padded with leading
    zeros. For example, if the value of the line number is 123, the last four characters of the string representation
    will be "0123".
    Note that there is a single space separating the closing parenthesis after the area code from the first digit of
    the prefix.
     */
    @Override
    public String toString(){
        return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
    }
    @Override
    public PhoneNumber clone(){
        try{
            return (PhoneNumber) super.clone();
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError(); // Can't happen
        }
    }

    @Override
    public int compareTo(PhoneNumber pn) {
        // Compare area codes
        int areaCodeDiff = areaCode - pn.areaCode;
        if(areaCodeDiff != 0) return areaCodeDiff;

        // Area codes are equal, compare prefixes
        int prefixDiff = prefix - pn.prefix;
        if(prefixDiff != 0) return prefixDiff;

        return lineNumber - pn.lineNumber;
    }
}
public class App
{
    private static final Random rnd = new Random();
    private static PhoneNumber randomPhoneNumber(){
        return new PhoneNumber((short)rnd.nextInt(1000), (short)rnd.nextInt(1000), (short)rnd.nextInt(10000));
    }
    public static void main( String[] args )
    {
        NavigableSet<PhoneNumber> s = new TreeSet<>();
        for(int i = 0; i < 10; i++){
            s.add(randomPhoneNumber());
        }
        System.out.println(s);
    }
}
/*
output:
[(031) 966-1025, (151) 297-4257, (240) 178-8024, (305) 229-1152, (308) 953-3372, (444) 825-8791, (650) 381-3655, (658) 757-0341, (722) 848-4644, (834) 142-9462]
 */
