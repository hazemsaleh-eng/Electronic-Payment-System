package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {

    // =========================================
    // Read Integer
    // =========================================

    public static int readInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            try {

                int value = scanner.nextInt();
                scanner.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input. Please enter a whole number."
                );

                scanner.nextLine();
            }
        }
    }


    // =========================================
    // Read Double
    // =========================================

    public static double readDouble(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            try {

                double value = scanner.nextDouble();
                scanner.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );

                scanner.nextLine();
            }
        }
    }


    // =========================================
    // Read Non-Empty String
    // =========================================

    public static String readNonEmptyString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String value =
                    scanner.nextLine().trim();

            if (!value.isEmpty()) {

                return value;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }


    // =========================================
    // Read Email
    // =========================================

    public static String readEmail(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String email =
                    scanner.nextLine().trim();

            if (email.contains("@")
                    && email.contains(".")) {

                return email;
            }

            System.out.println(
                    "Invalid email address."
            );
        }
    }


    // =========================================
    // Read Card Number
    // =========================================

    public static String readCardNumber(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String cardNumber =
                    scanner.nextLine().trim();

            if (cardNumber.matches("\\d{16}")) {

                return cardNumber;
            }

            System.out.println(
                    "Invalid card number. Enter exactly 16 digits."
            );
        }
    }
    public static int readPositiveInt(
            Scanner scanner,
            String message) {

        while (true) {

            int value =
                    readInt(
                            scanner,
                            message
                    );

            if (value > 0) {

                return value;
            }

            System.out.println(
                    "Value must be greater than 0."
            );
        }
    }
    public static double readPositiveDouble(
            Scanner scanner,
            String message) {

        while (true) {

            double value =
                    readDouble(
                            scanner,
                            message
                    );

            if (value > 0) {

                return value;
            }

            System.out.println(
                    "Amount must be greater than 0."
            );
        }
    }
    public static int readIntInRange(
            Scanner scanner,
            String message,
            int min,
            int max) {

        while (true) {

            int value =
                    readInt(
                            scanner,
                            message
                    );

            if (value >= min && value <= max) {

                return value;
            }

            System.out.println(
                    "Please enter a number from "
                    + min
                    + " to "
                    + max
                    + "."
            );
        }
    }
    
}