import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM machine");
        System.out.println("Enter your account details:");

        System.out.print("Account holder's name: ");
        String accountHolderName = scanner.nextLine();

        System.out.print("Account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character left in buffer

        Account acc = new Account(accountHolderName, accountNumber, initialBalance);

        while (true) {
            ATM atm = new ATM(scanner, acc);

            System.out.println("Do you want the service again? [YES = Y]/[NO = N]");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("N")) {
                break;
            }
        }

        scanner.close();
    }
}

class ATM {
    private double balance;
    private Scanner scanner;
    private Account account;

    public ATM(Scanner scanner, Account account) {
        this.scanner = scanner;
        this.account = account;
        this.balance = account.getBalance();

        System.out.println("*** Welcome to the ATM machine ***");
        System.out.println("Enter the option you want to select. Withdraw[W] Deposit[D] Check balance[B].");

        String op = scanner.nextLine();

        switch (op.toUpperCase()) {
            case "W":
                withdraw();
                break;
            case "D":
                deposit();
                break;
            case "B":
                checkBalance();
                break;
            default:
                System.out.println("Enter a valid choice.");
        }
    }

    private void withdraw() {
        System.out.println("Enter the amount that you want to Withdraw:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character left in buffer
        if (balance - amount >= 100) {
            balance -= amount;
            account.setBalance(balance);
            System.out.println("Transaction successful. Remaining balance: " + balance);
        } else {
            System.out.println("Transaction failure: At least Rs.100 should be available in the bank account.");
        }
    }

    private void deposit() {
        System.out.println("Enter the amount that you want to Deposit:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character left in buffer
        balance += amount;
        account.setBalance(balance);
        System.out.println("Deposit successful. Updated balance: " + balance);
    }

    private void checkBalance() {
        System.out.println("Your account balance is: " + balance);
    }
}

class Account {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    public Account(String accountHolderName, String accountNumber, double balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
