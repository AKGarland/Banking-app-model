package com.company;

import java.util.ArrayList;

public class Branches {
    private String name;
    private ArrayList<Customer> customers;

    public Branches(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
    }

    public String getName() {
        return name;
    }

    public boolean customerExists(String customerName) {
        for(int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(customerName)) {
                return true;
            }
        }
        return false;
    }

    private int findCustomer(String customerName) {
        if(customerExists(customerName)) {
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getName().equals(customerName)) {
                    return i;
                }
            }
        } else {
            System.out.println("This customer does not exist"); // is this necessary? findCustomer is private and I'm only using it where I've already called customerExists and it's true
        }
        return -1;
    }

    public Customer returnCustomer(String customerName) { return customers.get(findCustomer(customerName)); }

    public void addNewCustomer(String customerName, double customerTransaction) {
        if (customerExists(customerName)) {
            System.out.println("This customer already exists at branch " + name);
        } else {
            ArrayList<Double> transactionArrayList = new ArrayList<Double>();
            transactionArrayList.add(Double.valueOf(customerTransaction));
            Customer newCustomer = new Customer(customerName, transactionArrayList);
            customers.add(newCustomer);
            System.out.println(customerName + " has been added to branch " + name);
        }
    }

    public void addNewTransaction(String customerName, Double transactionAmount) {
        if (!customerExists(customerName)) {
            System.out.println("Cannot find customer named " + customerName + " at branch " + name);
        } else {
            customers.get(findCustomer(customerName)).addTransaction(transactionAmount);
        }
    }

    public void printAllCustomerNames(){
        System.out.println("Customers at branch " + name);
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i+1) + ". " + customers.get(i).getName());
        }
    }
}