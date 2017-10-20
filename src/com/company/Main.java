package com.company;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Bank myBank;

    public static void main(String[] args) {
        // Bank class (w/ arraylist of branches), customers class (w/ arraylist of Doubles (wrapper) = transactions).
        // Bank (lots of methods...calling code from other classes!):
        // add customer to that new branch with initial transaction amount
        // show a list of customers for that particular branch
        // + option to see transactions, too.
        // Autoboxing & unboxing with transactions (as these are Doubles, then doubles, then Doubles again probably.)
        // Data validation: check that a customer doesn't already exist at that branch etc.


        System.out.println("Name your bank: ");
        String bankName = scanner.nextLine();
        myBank = new Bank(bankName);
        menu();
    }

    public static void menu () {
        System.out.println("Welcome to the top menu");
        System.out.println("Press 1 to create a new branch");
        System.out.println("Press 2 to manage an existing branch");
        System.out.println("Press 3 to quit");

        int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                   newBranch();
                    break;
                case 2:
                    System.out.println("Please input the name of the branch you would like to manage: ");
                    String branchName = scanner.next();
                    branchMenu(branchName);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Please select an option from the menu");
                    menu();
                    break;
            }

    }

    private static void newBranch() {
        System.out.println("Please input the name of the branch you would like to create: ");
        String newBranchName;
        newBranchName = scanner.next();
        myBank.addNewBranch(newBranchName);
        branchMenu(newBranchName);
    }

    public static void branchMenu (String branchName) {
        if (myBank.branchExists(branchName)) {
            System.out.println("Welcome to the branch management screen");
            System.out.println("Press 1 to add a new customer");
            System.out.println("Press 2 to add a transaction for a customer");
            System.out.println("Press 3 to display a list of customers at this branch");
            System.out.println("Press 4 to display a list of transactions for a particular customer");
            System.out.println("Press 5 to return to the top menu");

            int branchMenuChoice = scanner.nextInt();
            String customerName;
            double transaction;

            switch(branchMenuChoice) {
                case 1:
                    System.out.println("Input the name of the new customer: ");
                    customerName = scanner.next();
                    System.out.println("Input the initial transaction for the new customer: ");
                    transaction = scanner.nextDouble();
                    myBank.addCustomerToBranch(branchName,customerName,transaction);
                    System.out.println("If you would like to now add a new transaction for this customer please press 1");
                    System.out.println("Otherwise, press any other key to return to the branch management screen for " + branchName + ":");
                    int nextChoice = scanner.nextInt();
                    if (nextChoice == 1) {
                        System.out.println("Input the transaction amount you would like to add: ");
                        transaction = scanner.nextDouble();
                        myBank.addTransactionForCustomerAtBranch(branchName,customerName,transaction);
                        branchMenu(branchName);
                    }
                    branchMenu(branchName);;
                    break;
                case 2:
                    System.out.println("Input the name of the customer: ");
                    customerName = scanner.next();
                    System.out.println("Input the transaction amount: ");
                    myBank.addTransactionForCustomerAtBranch(branchName,customerName,scanner.nextDouble());
                    branchMenu(branchName);;
                    break;
                case 3:
                    myBank.displayListOfCustomersAtBranch(branchName);
                    branchMenu(branchName);
                    break;
                case 4:
                    System.out.println("Input the name of the customer: ");
                    customerName = scanner.next();
                    myBank.displayTransactionsForCustomerAtBranch(branchName,customerName);
                    branchMenu(branchName);
                    break;
                case 5:
                    menu();
                    break;
            }
        } else {
            System.out.println("Branch does not exist, please try again");
            menu();
        }
    }
}