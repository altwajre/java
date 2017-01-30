package com.company.app;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class App {
    public static void main(String... args){
        Pair<Integer, Boolean> pair = Pair.of(1, true);
        System.out.println(pair);

        Triple<String, Integer, Boolean> triple = Triple.of("Tom", 10, false);
        System.out.println(triple);
    }
}
/*
http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/tuple/package-summary.html

output:
(1,true)
(Tom,10,false)
 */
