package com.transfer;

import com.bank.Bank;

import java.io.IOException;

public class TransferMoney implements Transaction {
    private Bank bank;
    private String owner;
    private String client;
    private int money;

    public TransferMoney(Bank bank, String owner, int money, String client) {
        this.bank = bank;
        this.owner = owner;
        this.money = money;
        this.client = client;
    }

    @Override
    public void execute() throws IOException {
        bank.transferMoney(owner, money, client);
        bank.saveToDatabase();
    }
}
