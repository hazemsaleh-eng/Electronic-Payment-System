package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String transactionId;
    private Customer customer;
    private double amount;
    private String paymentMethod;
    private LocalDateTime date;
    private TransactionStatus status;


    // Constructor for new transactions
    public Transaction(
            String transactionId,
            Customer customer,
            double amount,
            String paymentMethod,
            TransactionStatus status) {

        this(
                transactionId,
                customer,
                amount,
                paymentMethod,
                LocalDateTime.now(),
                status
        );
    }


    // Constructor for loading old transactions from file
    public Transaction(
            String transactionId,
            Customer customer,
            double amount,
            String paymentMethod,
            LocalDateTime date,
            TransactionStatus status) {

        this.transactionId = transactionId;
        this.customer = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.status = status;
    }


    // =========================================
    // Get Transaction ID
    // =========================================

    public String getTransactionId() {

        return transactionId;
    }


    // =========================================
    // Get Customer
    // =========================================

    public Customer getCustomer() {

        return customer;
    }


    // =========================================
    // Get Amount
    // =========================================

    public double getAmount() {

        return amount;
    }


    // =========================================
    // Get Payment Method
    // =========================================

    public String getPaymentMethod() {

        return paymentMethod;
    }


    // =========================================
    // Get Date
    // =========================================

    public LocalDateTime getDate() {

        return date;
    }


    // =========================================
    // Get Status
    // =========================================

    public TransactionStatus getStatus() {

        return status;
    }


    // =========================================
    // Set Status
    // =========================================

    public void setStatus(
            TransactionStatus status) {

        this.status = status;
    }


    // =========================================
    // Display Transaction
    // =========================================

    public void displayTransaction() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"
                );


        System.out.println(
                "Transaction ID: "
                + transactionId
        );


        System.out.println(
                "Customer: "
                + customer.getName()
        );


        System.out.printf(
                "Amount: $%.2f%n",
                amount
        );


        System.out.println(
                "Payment Method: "
                + paymentMethod
        );


        System.out.println(
                "Date: "
                + date.format(formatter)
        );


        System.out.println(
                "Status: "
                + status
        );
    }
}