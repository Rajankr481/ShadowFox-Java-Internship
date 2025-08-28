import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void deposit() {
        BankAccount acc = new BankAccount("12345", "TestUser");
        acc.deposit(100);
        assertEquals(100, acc.getBalance());
    }

    @Test
    void withdraw() {
        BankAccount acc = new BankAccount("12345", "TestUser");
        acc.deposit(100);
        acc.withdraw(50);
        assertEquals(50, acc.getBalance());
    }

    @Test
    void getBalance() {
        BankAccount acc = new BankAccount("12345", "TestUser");
        acc.deposit(200);
        assertEquals(200, acc.getBalance());
    }
}
