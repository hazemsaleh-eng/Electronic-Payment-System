package payment;

import model.Customer;

public abstract class Payment implements Refundable {

    private Customer customer;
    private double amount;

    public Payment(
            Customer customer,
            double amount) {

        this.customer = customer;
        this.amount = amount;
    }

    public Customer getCustomer() {

        return customer;
    }

    public double getAmount() {

        return amount;
    }

    public abstract void processPayment();

    public abstract String getPaymentMethod();

    @Override
    public void refund() {

        System.out.println(
                "Refunding "
                + getPaymentMethod()
                + " payment..."
        );
    }
}