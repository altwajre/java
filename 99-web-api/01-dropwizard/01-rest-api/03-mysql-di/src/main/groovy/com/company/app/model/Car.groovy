package com.company.app.model

class Car {
    Car(){
        this.id = 0
        this.make = null
    }

    Car(int id, String make){
        this.id = id
        this.make = make
    }
    int id
    String make
}
