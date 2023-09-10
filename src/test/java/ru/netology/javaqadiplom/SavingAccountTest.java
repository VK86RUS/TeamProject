package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void testConstructorWithValidRate() {
        SavingAccount account = new SavingAccount(1000, 500, 5000, 5);
        Assertions.assertEquals(1000, account.getBalance());
        Assertions.assertEquals(500, account.getMinBalance());
        Assertions.assertEquals(5000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());

    }

    @Test
    public void PayWithValidAmount() {
        SavingAccount account = new SavingAccount(2000, 500, 5000, 5);
        Assertions.assertTrue(account.pay(200));
        Assertions.assertEquals(1800, account.getBalance());
    }

    @Test
    public void PayWithInvalidAmount() {
        SavingAccount account = new SavingAccount(1000, 500, 1000, 5);
        Assertions.assertFalse(account.pay(1500));
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void addingWithAnIncorrectAmount() {
        SavingAccount account = new SavingAccount(2000, 300, 10_000, 5);
        Assertions.assertFalse(account.add(-100));
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void replenishmentToTheMaxBalance() {
        SavingAccount account = new SavingAccount(1000, 100, 5000, 5);
        account.add(5100);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void balanceChangeForTheYear() {
        SavingAccount account = new SavingAccount(10_000, 1000, 50_000, 5);
        int expected = (10_000 * 5) / 100;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void negativeRateTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, 500, 5000, -5);
        });
    }

    @Test
    public void balanceChangeForTheYear0() {
        SavingAccount account = new SavingAccount(10_000, 1000, 50_000, 0);
        int expected = 0;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void balanceChangeForTheYearWithTheMax() {
        SavingAccount account = new SavingAccount(10_000, 1000, 10_000, 10);
        account.add((10_000 * 10) / 100);
        int expected = 1000;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void yearChangeWithMaxBalance() {
        SavingAccount account = new SavingAccount(3800, 3800, 3800, 50); // Максимальный баланс равен начальному
        account.yearChange();

        Assertions.assertTrue(account.getBalance() <= account.getMaxBalance());
    }


    @Test
    public void initialBalanceEqualsMinBalance() {

        SavingAccount account = new SavingAccount(1000, 1000, 5000, 5);

        Assertions.assertEquals(1000, account.getBalance());
        Assertions.assertFalse(account.pay(200));


    }

    @Test
    public void afterPaymentBalanceEqualMinBalance() {

        SavingAccount account = new SavingAccount(1000, 500, 5000, 5);

        Assertions.assertEquals(1000, account.getBalance());
        Assertions.assertTrue(account.pay(500));
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void maximumInterestRate() {
        SavingAccount account = new SavingAccount(1000, 200, 10_000, 100);
        account.yearChange();
        Assertions.assertEquals(2000, account.getBalance());


    }

    @Test
    public void InvalidMinMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2500, 5000, 2000, 10); // Минимальный баланс больше максимального
        });
    }

    @Test
    public void limitFromMaxBalanceToMinBalance() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(100, 1000, 2000, 10); // тут баланс уходит за пределы минимального
        });
    }
}



