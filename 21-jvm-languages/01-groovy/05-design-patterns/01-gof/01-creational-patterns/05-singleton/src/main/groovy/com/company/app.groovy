package com.company

// http://groovy-lang.org/design-patterns.html#_singleton_pattern
class Singleton{
    private static instance
    protected Singleton(){
    }
    static getInstance(){
        if(instance == null){
            instance = new Singleton()
        }
        return instance
    }

}

class app {
    static void main(String[] args){
        Singleton s1 = Singleton.getInstance()
        Singleton s2 = Singleton.getInstance()
        if(s1 == s2){
            println('Object are the same instance')
        }
        else{
            println("Object are NOT the same instance")
        }
    }
}
/*
output:
Object are the same instance
 */
