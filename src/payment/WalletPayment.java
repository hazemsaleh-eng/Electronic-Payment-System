package payment;

import model.Customer;

public class WalletPayment extends Payment {

    private String walletId;

    public WalletPayment(
            Customer customer,
            double amount,
            String walletId) {

        super(customer, amount);

        this.walletId = walletId;
    }

    @Override
    public void processPayment() {

        System.out.println(
                "Processing Electronic Wallet payment..."
        );

        System.out.println(
                "Customer: " + getCustomer().getName()
        );

        System.out.printf(
                "Amount: $%.2f%n",
                getAmount()
        );

        System.out.println(
                "Wallet ID: " + walletId
        );
    }

    @Override
    public String getPaymentMethod() {

        return "Electronic Wallet";
    }

    public String getWalletId() {

        return walletId;
    }
}