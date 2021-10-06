package com.janeroad.decorator;

public class Test {
    public static void main(String[] args) {
        Person xiaoMing = new XiaoMing();
        xiaoMing = new Shirt(xiaoMing);
        xiaoMing = new Trousers(xiaoMing);
        xiaoMing.show();
    }
}
