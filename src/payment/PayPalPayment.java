package payment;

import model.Customer;

public class PayPalPayment extends Payment {

    private String email;

    public PayPalPayment(
            Customer customer,
            double amount,
            String email) {

        super(customer, amount);

        this.email = email;
    }

    @Override
    public void processPayment() {

        System.out.println(
                "Processing PayPal payment..."
        );

        System.out.println(
                "Customer: " + getCustomer().getName()
        );

        System.out.printf(
                "Amount: $%.2f%n",
                getAmount()
        );

        System.out.println(
                "PayPal Email: " + email
        );
    }

    @Override
    public String getPaymentMethod() {

        return "PayPal";
    }

    public String getEmail() {

        return email;
    }
}