package com.janeroad.decorator;

public abstract class ClothesDecorator implements Person{

    protected Person person;

    protected ClothesDecorator(Person person) {
        this.person = person;
    }
}
