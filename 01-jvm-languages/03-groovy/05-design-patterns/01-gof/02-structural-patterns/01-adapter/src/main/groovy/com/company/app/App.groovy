package com.company.app

// http://groovy-lang.org/design-patterns.html#_adapter_pattern
class Target{
    def request(){
        println('Target.request() is invoked')
    }
}

class Adapter extends Target {
    private Adaptee adaptee = new Adaptee()
    // override Target.request()
    def request() {
        adaptee.specificRequest()
    }
}

class Adaptee {
    def specificRequest(){
        println('Adaptee.specificRequest()')
    }
}

class App {
    static void main(String[] args){
        Target target = new Adapter()
        target.request()
    }
}
/*
output:
Adaptee.specificRequest()
 */
