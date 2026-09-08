package service;

import exception.TransactionNotFoundException;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import model.TransactionStatus;

public class TransactionService {

    private ArrayList<Transaction> transactions;


    // =========================================
    // Constructor
    // =========================================

    public TransactionService() {

        transactions = new ArrayList<>();
    }


    // =========================================
    // Add Transaction
    // =========================================

    public void addTransaction(
            Transaction transaction) {

        transactions.add(transaction);
    }


    // =========================================
    // Display All Transactions
    // =========================================

    public void displayAllTransactions() {

        if (transactions.isEmpty()) {

            System.out.println(
                    "No transactions found."
            );

            return;
        }


        System.out.println(
                "================================"
        );

        System.out.println(
                "       TRANSACTION HISTORY"
        );

        System.out.println(
                "================================"
        );


        for (Transaction transaction : transactions) {

            transaction.displayTransaction();

            System.out.println(
                    "--------------------------------"
            );
        }
    }


    // =========================================
    // Search Transaction By ID
    // =========================================

    public Transaction searchTransactionById(
            String transactionId)
            throws TransactionNotFoundException {

        for (Transaction transaction : transactions) {

            if (transaction
                    .getTransactionId()
                    .equalsIgnoreCase(transactionId)) {

                return transaction;
            }
        }

        throw new TransactionNotFoundException(
                "Transaction with ID "
                + transactionId
                + " was not found."
        );
    }


    // =========================================
    // Refund Transaction
    // =========================================

    public boolean refundTransaction(
            String transactionId)
            throws TransactionNotFoundException {

        Transaction transaction =
                searchTransactionById(
                        transactionId
                );

        if (transaction.getStatus()
                == TransactionStatus.REFUNDED) {

            return false;
        }

        if (transaction.getStatus()
                != TransactionStatus.SUCCESS) {

            return false;
        }

        transaction.setStatus(
                TransactionStatus.REFUNDED
        );

        return true;
    }


    // =========================================
    // Get Transaction Count
    // =========================================

    public int getTransactionCount() {

        return transactions.size();
    }


    // =========================================
    // Get All Transactions
    // =========================================

    public List<Transaction> getAllTransactions() {

        return new ArrayList<>(transactions);
    }
}