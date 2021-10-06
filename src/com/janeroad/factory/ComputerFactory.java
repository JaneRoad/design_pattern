package com.janeroad.factory;

public class ComputerFactory {
    public Computer createComputer(String name){
        Computer computer = null;
        if ("Apple".equals(name)){
            computer = new Apple();
        }
        if ("Dell".equals(name)){
            computer = new Dell();
        }
        return computer;
    }
}
