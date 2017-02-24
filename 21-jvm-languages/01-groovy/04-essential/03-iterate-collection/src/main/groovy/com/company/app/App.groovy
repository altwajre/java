package com.company.app

class App {
    static void main(String... args){
        map_test()

        list_test()

        set_test()

    }

    private static void set_test() {
        print '#set_test'
        def nums = [3, 1, 4, 1, 5, 2, 5] as Set
        nums.each { println it }
    }

    private static void list_test() {
        println '#list_test'
        List nums = [3, 1, 4, 1]
        nums.each {
            if (it == 3) {
                println 'wow'
            } else {
                println it
            }
        }
    }
/*
output:
#list_test
wow
1
4
1
 */

    private static void map_test() {
        println '#map_test'
        def map = ['a': false, 'b': true, 'c': false]

        println '* .each { e -> ...}'
        map.each { e -> println "map[${e.key}]=${e.value}" }

        println '* .each { k, v -> ...}'
        map.each { k, v -> println "map[${k}]=${v}"}
    }
/*
output:
#map_test
* .each { e -> ...}
map[a]=false
map[b]=true
map[c]=false
* .each { k, v -> ...}
map[a]=false
map[b]=true
map[c]=false
 */
}
