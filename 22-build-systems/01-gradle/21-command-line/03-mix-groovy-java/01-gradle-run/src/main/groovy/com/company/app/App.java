package com.company.app;

import com.company.app.model.Product;

public class App {
    public static void main(String... args){
        System.out.println("# Hello");
        for(int i = 0; i < args.length; i++){
            System.out.println(args[i]);
        }

        Product product = new Product(1, "phone");
        System.out.println("Product name: "+product.getName());

    }
}
