# Electronic Payment System

A Java console-based educational project that simulates an electronic payment system using Object-Oriented Programming principles.

The system allows users to make payments using different payment methods, view transaction history, search for transactions, process refunds, and save transaction data to a local file.

> This project is created for educational and portfolio purposes only.
> It does not process real financial transactions.

---

## Features

- Make electronic payments
- Credit Card payment
- PayPal payment
- Electronic Wallet payment
- Generate unique Transaction IDs
- View transaction history
- Search transactions by Transaction ID
- Refund successful transactions
- Prevent duplicate refunds
- Input validation
- Custom exception handling
- Save transactions to a file
- Load transactions when the application starts
- Preserve transaction status after restarting
- Continue Transaction IDs after restarting

---

## Technologies Used

- Java
- Eclipse IDE
- Object-Oriented Programming
- Java Collections
- File Handling
- Exception Handling
- Java Time API

---

## OOP Concepts Used

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Abstract Classes
- Interfaces
- Constructors
- Method Overriding
- Composition
- Enums

---

## Project Structure

```text
ElectronicPaymentSystem
│
├── src
│   ├── model
│   │   ├── Customer.java
│   │   ├── Transaction.java
│   │   └── TransactionStatus.java
│   │
│   ├── payment
│   │   ├── Payment.java
│   │   ├── Refundable.java
│   │   ├── CreditCardPayment.java
│   │   ├── PayPalPayment.java
│   │   └── WalletPayment.java
│   │
│   ├── service
│   │   ├── PaymentProcessor.java
│   │   ├── TransactionService.java
│   │   └── TransactionFileService.java
│   │
│   ├── exception
│   │   ├── InvalidAmountException.java
│   │   └── TransactionNotFoundException.java
│   │
│   ├── util
│   │   └── InputValidator.java
│   │
│   └── main
│       └── Main.java
│
├── .gitignore
└── README.md
```

---

## Main Menu

```text
====================================
       ELECTRONIC PAYMENT SYSTEM
====================================

1. Make Payment
2. View Transactions
3. Search Transaction
4. Refund Payment
5. Exit

Choose option:
```

---

## Payment Methods

The system supports:

```text
1. Credit Card
2. PayPal
3. Electronic Wallet
```

---

## Example Payment

```text
Customer ID: 1001
Customer Name: Hazem
Customer Email: hazem@example.com
Amount: 150

Choose Payment Method:

1. Credit Card
2. PayPal
3. Electronic Wallet

Choose option: 1

Card Number: 1234567812345678
```

Example output:

```text
Payment Successful!

Transaction ID: TXN-10001
Customer: Hazem
Amount: $150.00
Payment Method: Credit Card
Status: SUCCESS
```

---

## Transaction Status

Transactions can have the following statuses:

```text
SUCCESS
REFUNDED
FAILED
```

These values are managed using the `TransactionStatus` enum.

---

## Validation

The application validates user input to ensure that:

- Customer ID is greater than 0
- Amount is greater than 0
- Customer name is not empty
- Email address is valid
- Credit card number contains exactly 16 digits
- Payment method is between 1 and 3
- Main menu option is between 1 and 5

---

## Exception Handling

The project uses custom exceptions:

```java
InvalidAmountException
TransactionNotFoundException
```

These exceptions help prevent invalid operations and improve error handling.

---

## File Handling

Transactions are stored locally in:

```text
transactions.txt
```

The application can save new transactions, load previous transactions, update refunded transactions, and continue Transaction IDs after restarting.

The runtime transaction file is excluded from GitHub using `.gitignore`.

---

## How to Run

1. Clone or download the project.
2. Open Eclipse IDE.
3. Import the project using `File → Import → Existing Projects into Workspace`.
4. Open `src/main/Main.java`.
5. Select `Run As → Java Application`.

---

## Learning Outcomes

Through this project I practiced:

- Java Object-Oriented Programming
- Class design
- Encapsulation
- Inheritance
- Polymorphism
- Abstract classes
- Interfaces
- Collections and ArrayList
- Custom exceptions
- Input validation
- File handling
- Enums
- Clean Code principles
- Separation of responsibilities

---

## Disclaimer

This project is an educational and portfolio project only.

It does not connect to real payment gateways, banks, PayPal APIs, credit card processors, or electronic wallet services.

No real financial transactions are performed.