package com.company.app.model

class Car {
    Car(){
        this.id = 0
        this.make = null
        this.color = null
    }
    Car(int id, String make, String color){
        this.id = id
        this.make = make
        this.color = color
    }
    int id
    String make
    String color
}
