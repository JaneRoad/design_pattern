package com.janeroad.observer;

import java.util.ArrayList;
import java.util.List;

public class NewspaperOffice {
    private List<Customer> customers= new ArrayList<>();

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void notifyAllObservers(){
        for (Customer customer : customers) {
            customer.update();
        }
    }

    public void newspaper(){
        this.notifyAllObservers();
    }
}
