package com.janeroad.templatemethod;

public class Test {
    public static void main(String[] args) {
        Cook cook = new CookTomato();
        cook.cook();
        cook = new CookPotato();
        System.out.println("-----------炒下一道菜-----------");
        cook.cook();
    }
}
