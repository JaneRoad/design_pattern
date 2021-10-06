package com.janeroad.proxy;

public class HouseProxy implements House{

    private House house;

    public HouseProxy(House house){
        this.house = house;
    }

    @Override
    public void findHouse() {
        System.out.println("日志：找了一个代理");
        this.house.findHouse();
    }
}
