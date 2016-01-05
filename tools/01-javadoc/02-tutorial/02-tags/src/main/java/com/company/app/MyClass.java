package com.company.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/** Description of MyClass
 *
 * @author John Doe
 * @author Jane Doe
 * @version 6.0z Build 9000 Jan 3, 1970.
 */
public class MyClass {
    /** Description of myIntField */
    public int myIntField;

    /** Description of MyClass()
     *
     * @throws FileNotFoundException   Description of exception
     */
    public MyClass() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("myfile.txt");
    }

    /** Description of myMethod(int a, String b)
     *
     * @param a   Description of a
     * @param b   Description of b
     * @return    Description of c
     */
    public Object myMethod(int a, String b){
        Object c = null;
        return c;
    }
}
