package com.company.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/** Description of MyClass
 *
 */
public class MyClass {
    /** Description of read()
     *
     * @throws FileNotFoundException   Description of exception
     */
    public void read() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("myfile.txt");
    }
}
