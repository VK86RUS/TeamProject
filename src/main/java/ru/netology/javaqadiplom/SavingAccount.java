package ru.netology.javaqadiplom;

/**
 * Сберегательный счёт
 * Может иметь баланс только в пределах от указанного минимального до указанного максимального включительно.
 * Не может уходить в минус (минимальный баланс не может быть отрицательным).
 * Имеет ставку - количество процентов годовых на остаток.
 */
public class SavingAccount extends Account {
    protected int minBalance;
    protected int maxBalance;

    /**
     * Создаёт новый объект сберегательного счёта с заданными параметрами.
     * Если параметры некорректны (мин. баланс больше максимального и так далее), то
     * должно выкидываться исключения вида IllegalArgumentException.
     *
     * @param initialBalance - начальный баланс
     * @param minBalance     - минимальный баланс
     * @param maxBalance     - максимальный баланс
     * @param rate           - неотрицательное число, ставка в процентах годовых на остаток
     */
    public SavingAccount(int initialBalance, int minBalance, int maxBalance, int rate) {
        if (rate < 0) {
            throw new IllegalArgumentException(
                    "Накопительная ставка не может быть отрицательной, а у вас: " + rate
            );

        }
        if (minBalance > maxBalance) {
            throw new IllegalArgumentException(
                    "Минимальный баланс не может быть больше максимального"
            );
        }
        if (initialBalance < minBalance || initialBalance > maxBalance) {
            throw new IllegalArgumentException(
                    "Начальный баланс должен находиться в пределах от " + maxBalance + "до" + maxBalance
            );
        }
        this.balance = initialBalance;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.rate = rate;
    }

    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти в минус), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */
    @Override
    public boolean pay(int amount) {
        if (amount <= 0 || balance - amount < minBalance) {
            return false;
        }
        balance = balance - amount;
        if (balance >= minBalance) {
            return true;
        } else {
            return false;

        }
    }


    /**
     * Операция пополнения карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен увеличиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта, то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма пополнения
     * @param amount
     * @return true если операция прошла успешно, false иначе.
     * @return
     */
    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance + amount <= maxBalance) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция расчёта процентов на остаток счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте 200 рублей, то при ставке 15% ответ должен быть 30.
     *
     * @return
     */
    @Override
    public int yearChange() {
        int interest = (balance * rate) / 100;
        int newBalance = balance + interest;
        if (newBalance > maxBalance) {
            balance = maxBalance;
        } else {
            balance = newBalance;
        }
        return interest;
    }

    public void addInterest() {
        int interest = yearChange();
        balance += interest;
    }


    public int getMinBalance() {
        return minBalance;
    }

    public int getMaxBalance() {
        return maxBalance;
    }

}

