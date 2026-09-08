package payment;

import model.Customer;

public class CreditCardPayment extends Payment {

    private String cardNumber;

    public CreditCardPayment(
            Customer customer,
            double amount,
            String cardNumber) {

        super(customer, amount);

        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment() {

        System.out.println(
                "Processing Credit Card payment..."
        );

        System.out.println(
                "Customer: " + getCustomer().getName()
        );

        System.out.printf(
                "Amount: $%.2f%n",
                getAmount()
        );

        System.out.println(
                "Card Number: " + cardNumber
        );
    }

    @Override
    public String getPaymentMethod() {

        return "Credit Card";
    }

    public String getCardNumber() {

        return cardNumber;
    }
}