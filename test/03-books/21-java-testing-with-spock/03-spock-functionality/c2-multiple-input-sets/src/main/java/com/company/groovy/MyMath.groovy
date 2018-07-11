package com.company.groovy

class MyMath {
    boolean isEvenNumber
    int add(int x, int y){
        int result = x + y;
        isEvenNumber = result % 2 == 0
        return result
    }
}
