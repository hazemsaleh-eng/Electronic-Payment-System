package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Transaction;
import model.TransactionStatus;

public class TransactionFileService {

    private static final String FILE_NAME =
            "transactions.txt";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm:ss"
            );


    // =========================================
    // SAVE ONE TRANSACTION
    // =========================================

    public void saveTransaction(
            Transaction transaction) {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        FILE_NAME,
                                        true
                                )
                        )
        ) {

            writeTransaction(
                    writer,
                    transaction
            );

        } catch (IOException e) {

            System.out.println(
                    "Error saving transaction to file."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // =========================================
    // SAVE ALL TRANSACTIONS
    // =========================================

    public void saveAllTransactions(
            List<Transaction> transactions) {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        FILE_NAME,
                                        false
                                )
                        )
        ) {

            for (Transaction transaction : transactions) {

                writeTransaction(
                        writer,
                        transaction
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error updating transactions file."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // =========================================
    // WRITE ONE TRANSACTION TO FILE
    // =========================================

    private void writeTransaction(
            BufferedWriter writer,
            Transaction transaction)
            throws IOException {

        writer.write(
                transaction.getTransactionId()
                + "|"
                + transaction.getCustomer().getCustomerId()
                + "|"
                + transaction.getCustomer().getName()
                + "|"
                + transaction.getCustomer().getEmail()
                + "|"
                + transaction.getAmount()
                + "|"
                + transaction.getPaymentMethod()
                + "|"
                + transaction.getDate().format(FORMATTER)
                + "|"
                + transaction.getStatus()
        );

        writer.newLine();
    }


    // =========================================
    // LOAD TRANSACTIONS
    // =========================================

    public ArrayList<Transaction> loadTransactions() {

        ArrayList<Transaction> transactions =
                new ArrayList<>();

        File file =
                new File(FILE_NAME);


        // File does not exist yet
        if (!file.exists()) {

            return transactions;
        }


        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(FILE_NAME)
                        )
        ) {

            String line;


            while ((line = reader.readLine()) != null) {

                // Ignore empty lines
                if (line.trim().isEmpty()) {

                    continue;
                }


                String[] data =
                        line.split("\\|");


                // Each transaction must have 8 fields
                if (data.length != 8) {

                    continue;
                }


                String transactionId =
                        data[0];


                int customerId =
                        Integer.parseInt(
                                data[1]
                        );


                String customerName =
                        data[2];


                String customerEmail =
                        data[3];


                double amount =
                        Double.parseDouble(
                                data[4]
                        );


                String paymentMethod =
                        data[5];


                LocalDateTime date =
                        LocalDateTime.parse(
                                data[6],
                                FORMATTER
                        );


                TransactionStatus status =
                        TransactionStatus.valueOf(
                                data[7]
                        );


                Customer customer =
                        new Customer(
                                customerId,
                                customerName,
                                customerEmail
                        );


                Transaction transaction =
                        new Transaction(
                                transactionId,
                                customer,
                                amount,
                                paymentMethod,
                                date,
                                status
                        );


                transactions.add(
                        transaction
                );
            }


        } catch (Exception e) {

            System.out.println(
                    "Error loading transactions from file."
            );

            System.out.println(
                    e.getMessage()
            );
        }


        return transactions;
    }
}