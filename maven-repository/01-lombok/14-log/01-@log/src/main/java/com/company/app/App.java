package com.company.app;

import lombok.extern.java.Log;

import java.util.logging.Level;

// java.util.logging.Logger
@Log
public class App {
    public static void main(String... args){
        log.log(Level.WARNING, "Something");
    }
}
/*

output:
Apr 30, 2016 12:40:17 AM com.company.app.App main
WARNING: Something
 */