package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void testSuccessfulPayment() { // оплата при нулевом балансе
        CreditAccount account = new CreditAccount(0, 2500, 15);
        boolean result = account.pay(2000);
        Assertions.assertTrue(result);
        Assertions.assertEquals(-2000, account.getBalance());
    }

    @Test
    public void paymentWithinTheBalance() { // оплата в пределах баланса
        CreditAccount account = new CreditAccount(2000, 2500, 15);
        boolean result = account.pay(1000);
        Assertions.assertTrue(result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void paymentOutsideBalance() { // выход за пределы баланса
        CreditAccount account = new CreditAccount(1000, 3000, 15);
        boolean result = account.pay(2000);
        Assertions.assertTrue(result);
        Assertions.assertEquals(-1000, account.getBalance());
    }

    @Test
    public void exceedingTheCreditLimit() { // превышение кредитного лимита
        CreditAccount account = new CreditAccount(200, 3000, 15);
        boolean result = account.pay(4000);
        Assertions.assertFalse(result);
        Assertions.assertEquals(200, account.getBalance());

    }

    @Test
    void replenishmentNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -3000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    void interestOnNegativeBalance() {
        CreditAccount account = new CreditAccount(-200, 2500, 15);
        int expected = -200 / 100 * 15;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void interestThePositiveBalance() {
        CreditAccount account = new CreditAccount(200, 2500, 15);
        int expected = 0;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void negativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(0, 500, -15);
        });
    }

}