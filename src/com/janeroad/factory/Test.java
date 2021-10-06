package com.janeroad.factory;

public class Test {
    public static void main(String[] args) {
        ComputerFactory factory = new ComputerFactory();
        Computer macbook = factory.createComputer("Apple");
        Computer dellComputer = factory.createComputer("Dell");
    }
}
