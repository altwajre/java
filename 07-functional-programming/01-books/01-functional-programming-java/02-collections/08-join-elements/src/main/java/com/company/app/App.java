package com.company.app;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.joining;

public class App
{
    final static List<String> friends = Arrays.asList("Tom", "Dick", "Harry", "Nate", "Bill", "Will", "Neal");
    static void imperative() {
        for(int i = 0; i < friends.size() - 1; i++){
            System.out.print(friends.get(i) + ", ");
        }
        if(friends.size() > 0){
            System.out.print(friends.get(friends.size() - 1));
        }
        System.out.println("");
    }
    static void functionalStringJoin(){
        System.out.println(String.join(", ", friends));
    }
    static void functionalCollectorsJoining() {
        // use Collectors.joining() to return a collector for joining
        String result = friends.stream().map(String::toUpperCase).collect(joining(", "));
        System.out.println(result);
    }
    public static void main( String[] args )
    {
        System.out.println("#imperative");
        imperative();
        System.out.println("#functionalStringJoin");
        functionalStringJoin();
        System.out.println("#functionalCollectorsJoining");
        functionalCollectorsJoining();
    }
}
/*
output:
#imperative
Tom, Dick, Harry, Nate, Bill, Will, Neal
#functionalStringJoin
Tom, Dick, Harry, Nate, Bill, Will, Neal
#functionalCollectorsJoining
TOM, DICK, HARRY, NATE, BILL, WILL, NEAL
 */

