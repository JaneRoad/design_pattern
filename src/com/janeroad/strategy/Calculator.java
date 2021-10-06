package com.janeroad.strategy;

public class Calculator {
    private Operation operation;

    public Calculator(Operation operation){
        this.operation = operation;
    }

    public Integer doOperation(Integer num1, Integer num2){
        return this.operation.doOperation(num1, num2);
    }
}
