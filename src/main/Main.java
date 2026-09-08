package main;

import java.util.ArrayList;
import java.util.Scanner;

import exception.InvalidAmountException;
import exception.TransactionNotFoundException;

import model.Customer;
import model.Transaction;
import model.TransactionStatus;

import payment.CreditCardPayment;
import payment.PayPalPayment;
import payment.Payment;
import payment.WalletPayment;

import service.PaymentProcessor;
import service.TransactionFileService;
import service.TransactionService;

import util.InputValidator;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PaymentProcessor processor =
                new PaymentProcessor();

        TransactionService transactionService =
                new TransactionService();

        TransactionFileService fileService =
                new TransactionFileService();


        // =========================================
        // LOAD SAVED TRANSACTIONS
        // =========================================

        ArrayList<Transaction> loadedTransactions =
                fileService.loadTransactions();

        for (Transaction transaction : loadedTransactions) {

            transactionService.addTransaction(
                    transaction
            );
        }


        // =========================================
        // INITIALIZE TRANSACTION ID COUNTER
        // =========================================

        processor.initializeTransactionCounter(
                loadedTransactions
        );


        int choice;

        do {

            System.out.println();
            System.out.println("====================================");
            System.out.println("       ELECTRONIC PAYMENT SYSTEM");
            System.out.println("====================================");
            System.out.println();
            System.out.println("1. Make Payment");
            System.out.println("2. View Transactions");
            System.out.println("3. Search Transaction");
            System.out.println("4. Refund Payment");
            System.out.println("5. Exit");
            System.out.println();

            choice =
                    InputValidator.readIntInRange(
                            scanner,
                            "Choose option: ",
                            1,
                            5
                    );


            switch (choice) {

                case 1:

                    makePayment(
                            scanner,
                            processor,
                            transactionService,
                            fileService
                    );

                    break;


                case 2:

                    System.out.println();

                    transactionService
                            .displayAllTransactions();

                    break;


                case 3:

                    searchTransaction(
                            scanner,
                            transactionService
                    );

                    break;


                case 4:

                    refundPayment(
                            scanner,
                            transactionService,
                            fileService
                    );

                    break;


                case 5:

                    System.out.println();

                    System.out.println(
                            "Thank you for using Electronic Payment System."
                    );

                    break;


                default:

                    System.out.println();

                    System.out.println(
                            "Invalid option. Please choose from 1 to 5."
                    );
            }

        } while (choice != 5);


        scanner.close();
    }


    // ==================================================
    // MAKE PAYMENT
    // ==================================================

    private static void makePayment(
            Scanner scanner,
            PaymentProcessor processor,
            TransactionService transactionService,
            TransactionFileService fileService) {

        System.out.println();
        System.out.println(
                "========== MAKE PAYMENT =========="
        );

        System.out.println();


        int customerId =
                InputValidator.readPositiveInt(
                        scanner,
                        "Customer ID: "
                );


        String customerName =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Customer Name: "
                );


        String customerEmail =
                InputValidator.readEmail(
                        scanner,
                        "Customer Email: "
                );


        double amount =
                InputValidator.readPositiveDouble(
                        scanner,
                        "Amount: "
                );


        Customer customer =
                new Customer(
                        customerId,
                        customerName,
                        customerEmail
                );


        // =========================================
        // CHOOSE PAYMENT METHOD
        // =========================================

        System.out.println();
        System.out.println("Choose Payment Method:");
        System.out.println();
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Electronic Wallet");
        System.out.println();


        int paymentChoice =
                InputValidator.readIntInRange(
                        scanner,
                        "Choose option: ",
                        1,
                        3
                );


        Payment payment;


        switch (paymentChoice) {

            case 1:

                System.out.println();

                String cardNumber =
                        InputValidator.readCardNumber(
                                scanner,
                                "Card Number: "
                        );

                payment =
                        new CreditCardPayment(
                                customer,
                                amount,
                                cardNumber
                        );

                break;


            case 2:

                System.out.println();

                String paypalEmail =
                        InputValidator.readEmail(
                                scanner,
                                "PayPal Email: "
                        );

                payment =
                        new PayPalPayment(
                                customer,
                                amount,
                                paypalEmail
                        );

                break;


            case 3:

                System.out.println();

                String walletId =
                        InputValidator.readNonEmptyString(
                                scanner,
                                "Wallet ID: "
                        );

                payment =
                        new WalletPayment(
                                customer,
                                amount,
                                walletId
                        );

                break;


            default:

                System.out.println();

                System.out.println(
                        "Invalid payment method."
                );

                return;
        }


        // =========================================
        // PROCESS PAYMENT
        // =========================================

        try {

            Transaction transaction =
                    processor.processPayment(
                            payment
                    );


            transactionService.addTransaction(
                    transaction
            );


            fileService.saveTransaction(
                    transaction
            );


            System.out.println();

            System.out.println(
                    "Payment Successful!"
            );

            System.out.println();

            transaction.displayTransaction();


        } catch (InvalidAmountException e) {

            System.out.println();

            System.out.println(
                    "Payment Failed!"
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // ==================================================
    // SEARCH TRANSACTION
    // ==================================================

    private static void searchTransaction(
            Scanner scanner,
            TransactionService transactionService) {

        System.out.println();

        System.out.println(
                "======= SEARCH TRANSACTION ======="
        );

        System.out.println();


        String transactionId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Transaction ID: "
                );


        try {

            Transaction transaction =
                    transactionService
                            .searchTransactionById(
                                    transactionId
                            );


            System.out.println();

            System.out.println(
                    "Transaction Found!"
            );

            System.out.println();

            transaction.displayTransaction();


        } catch (TransactionNotFoundException e) {

            System.out.println();

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // ==================================================
    // REFUND PAYMENT
    // ==================================================

    private static void refundPayment(
            Scanner scanner,
            TransactionService transactionService,
            TransactionFileService fileService) {

        System.out.println();

        System.out.println(
                "========= REFUND PAYMENT ========="
        );

        System.out.println();


        String transactionId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Transaction ID: "
                );


        try {

            Transaction transaction =
                    transactionService
                            .searchTransactionById(
                                    transactionId
                            );


            // Check if transaction is already refunded
            if (transaction.getStatus()
                    == TransactionStatus.REFUNDED) {

                System.out.println();

                System.out.println(
                        "This transaction has already been refunded."
                );

                return;
            }


            boolean refundResult =
                    transactionService
                            .refundTransaction(
                                    transactionId
                            );


            System.out.println();


            if (refundResult) {

                // Update transactions.txt
                fileService.saveAllTransactions(
                        transactionService
                                .getAllTransactions()
                );


                System.out.println(
                        "Refund Successful!"
                );

                System.out.println();

                transaction.displayTransaction();

            } else {

                System.out.println(
                        "Refund Failed!"
                );
            }


        } catch (TransactionNotFoundException e) {

            System.out.println();

            System.out.println(
                    e.getMessage()
            );
        }
    }
}