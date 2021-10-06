package com.janeroad.decorator;

public class Shirt extends ClothesDecorator{
    protected Shirt(Person person) {
        super(person);
    }

    @Override
    public Double cost() {
        return this.person.cost()+1000;
    }

    @Override
    public void show() {
        this.person.show();
        System.out.println("买了一件衬衫，消费"+this.cost());
    }
}
