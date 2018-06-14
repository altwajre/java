package com.company.app.provider

import com.company.app.model.CalculatorMessage

class CalculatorProvider {

    def calculate(CalculatorMessage calculatorMessage) {
        calculatorMessage.operation.operate(calculatorMessage.firstNumber, calculatorMessage.secondNumber)
    }
}
