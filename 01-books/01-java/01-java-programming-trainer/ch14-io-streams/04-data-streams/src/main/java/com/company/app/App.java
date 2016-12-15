package com.company.app;

import java.io.*;

public class App {
    public static void main(String[] args) {
        try (FileOutputStream myFile = new FileOutputStream("data.dat");
             BufferedOutputStream buff = new BufferedOutputStream(myFile);
             DataOutputStream data = new DataOutputStream(buff)) {
            data.writeInt(8);
            data.writeInt(18);
            double d1 = 28.0;
            data.writeDouble(d1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream myFile = new FileInputStream("data.dat");
             BufferedInputStream buff = new BufferedInputStream(myFile);
             DataInputStream data = new DataInputStream(buff)) {
            int num1 = data.readInt();
            System.out.println(num1);
            int num2 = data.readInt();
            System.out.println(num2);
            double num3 = data.readDouble();
            System.out.println(num3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
8
18
28.0
 */