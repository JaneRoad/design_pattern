package com.janeroad.decorator;

public class XiaoMing implements Person{
    @Override
    public Double cost() {
        return 0.0;
    }

    @Override
    public void show() {
        System.out.println("没穿衣服");
    }
}
