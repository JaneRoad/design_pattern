package com.janeroad.decorator;

public class Trousers extends ClothesDecorator{
    protected Trousers(Person person) {
        super(person);
    }

    @Override
    public Double cost() {
        return this.person.cost()+800;
    }

    @Override
    public void show() {
        this.person.show();
        System.out.println("买了一条裤子，消费"+this.cost());
    }
}
