package service;

import java.util.List;

import exception.InvalidAmountException;

import model.Transaction;
import model.TransactionStatus;

import payment.Payment;

public class PaymentProcessor {

    private int transactionCounter = 10001;


    // =========================================
    // PROCESS PAYMENT
    // =========================================

    public Transaction processPayment(Payment payment)
            throws InvalidAmountException {

        // Validate amount
        if (payment.getAmount() <= 0) {

            throw new InvalidAmountException(
                    "Invalid amount. Amount must be greater than 0."
            );
        }


        // Process payment
        payment.processPayment();


        // Generate Transaction ID
        String transactionId =
                "TXN-" + transactionCounter++;


        // Get payment method using Polymorphism
        String paymentMethod =
                payment.getPaymentMethod();


        // Create Transaction
        Transaction transaction =
                new Transaction(
                        transactionId,
                        payment.getCustomer(),
                        payment.getAmount(),
                        paymentMethod,
                        TransactionStatus.SUCCESS
                );


        return transaction;
    }


    // =========================================
    // INITIALIZE TRANSACTION COUNTER
    // =========================================

    public void initializeTransactionCounter(
            List<Transaction> transactions) {

        int highestNumber = 10000;


        for (Transaction transaction : transactions) {

            String transactionId =
                    transaction.getTransactionId();


            if (transactionId != null
                    && transactionId.startsWith("TXN-")) {

                try {

                    int number =
                            Integer.parseInt(
                                    transactionId.substring(4)
                            );


                    if (number > highestNumber) {

                        highestNumber = number;
                    }

                } catch (NumberFormatException e) {

                    // Ignore invalid transaction IDs
                }
            }
        }


        transactionCounter =
                highestNumber + 1;
    }
}