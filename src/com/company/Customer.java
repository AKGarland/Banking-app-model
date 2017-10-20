package com.company;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name, ArrayList<Double> transactions) {
        this.name = name;
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void addTransaction(double transaction) {
        transactions.add(Double.valueOf(transaction));
        System.out.println(transaction + " has been added to " + name + "'s account");
    }

    public void printTransactions() {
        System.out.println("Transactions for " + name);
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i+1) + ". " + transactions.get(i).doubleValue());
        }
    }
}
