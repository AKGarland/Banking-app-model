package com.company;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branches> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branches>();
    }

    public boolean branchExists(String branchName) {   // can/should I make branchExists/findBranch and customerExists/findCustomer DRY or something. they do the same thing so...
        for(int i = 0; i < branches.size(); i++) {
            if (branches.get(i).getName().equals(branchName)) {
                return true;
            }
        }
        return false;
    }

    private int findBranch(String branchName) {
        if(branchExists(branchName)) {
            for (int i = 0; i < branches.size(); i++) {
                if (branches.get(i).getName().equals(branchName)) {
                    return i;
                }
            }
        } else {
            System.out.println("This branch does not exist");
        }
        return -1;
    }

    public void addNewBranch(String branchName) {
        if(!branchExists(branchName)) {
            Branches branch = new Branches(branchName);
            this.branches.add(branch);
            System.out.println("Branch named " + branchName + " has been added");
        } else {
            System.out.println("A branch named " + branchName + " already exists!");}
    }

    public void addCustomerToBranch(String branchName, String customerName, double initialTransactionAmount) {
        if (branchExists(branchName)) {
            branches.get(findBranch(branchName)).addNewCustomer(customerName, initialTransactionAmount);
            System.out.println("New customer named " + customerName + " added to branch " + branchName + " with initial transaction amount of £" + initialTransactionAmount);
        }   else {
            System.out.println("Cannot find branch of name " + branchName);
        }
    }

    public void displayListOfCustomersAtBranch(String branchName) {
        if(branchExists(branchName)){
            Branches branch = branches.get(findBranch(branchName));
            branch.printAllCustomerNames();
        }
    }

    public void displayTransactionsForCustomerAtBranch(String branchName, String customerName) {
        if(branchExists(branchName)) {
            Branches branch = branches.get(findBranch(branchName));
            if(branch.customerExists(customerName)) {
                Customer customer = branch.returnCustomer(customerName);
                customer.printTransactions();
            } else {
                System.out.println("Customer does not exist at branch " + branchName);
            }
        } else {
            System.out.println("Cannot find branch of name " + branchName);
        }
    }

    public void addTransactionForCustomerAtBranch(String branchName, String customerName, double transaction) {
        if (branchExists(branchName)) {
            branches.get(findBranch(branchName)).addNewTransaction(customerName, Double.valueOf(transaction));
            System.out.println("Customer " + customerName + " at branch " + branchName + " added transaction amount of £" + transaction);
        }   else {
            System.out.println("Cannot find branch of name " + branchName);
        }
    }
}
