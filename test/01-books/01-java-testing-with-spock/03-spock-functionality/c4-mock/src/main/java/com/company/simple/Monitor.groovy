package com.company.simple

class Monitor {
    Reactor reactor
    void readSensor(boolean pass){
        if(pass){
            reactor.pass()
        }
        else{
            reactor.fail()
        }
    }
}
