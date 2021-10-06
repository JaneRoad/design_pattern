package com.janeroad.strategy;

public class Test {
    public static void main(String[] args) {
        Add add = new Add();
        Calculator calculator = new Calculator(add);
        System.out.println(calculator.doOperation(1, 2));
    }
}
