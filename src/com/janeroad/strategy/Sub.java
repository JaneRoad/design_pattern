package com.janeroad.strategy;

public class Sub implements Operation{
    @Override
    public Integer doOperation(Integer num1, Integer num2) {
        return num1-num2;
    }
}
