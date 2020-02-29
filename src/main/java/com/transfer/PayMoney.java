package com.transfer;

import com.bank.Bank;

import java.io.IOException;

public class PayMoney implements Transaction {
    private Bank bank;
    private String surname;
    private int money;

    public PayMoney(Bank bank, String surname, int money) {
        this.bank = bank;
        this.surname = surname;
        this.money = money;
    }

    @Override
    public void execute() throws IOException {
        bank.payMoney(surname, money);
        bank.saveToDatabase();
    }
}
