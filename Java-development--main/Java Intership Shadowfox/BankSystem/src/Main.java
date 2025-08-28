public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("98765", "John Doe");
        acc.deposit(5000);
        acc.withdraw(1200);

        System.out.println("Balance: ₹" + acc.getBalance());
        System.out.println("Transaction History:");
        for (Transaction t : acc.getTransactionHistory()) {
            System.out.println(t);
        }
    }
}
