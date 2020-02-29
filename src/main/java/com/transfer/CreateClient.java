package com.transfer;

import com.bank.Bank;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

public class CreateClient implements Transaction {
    Bank bank;
    private String name;
    private String surname;
    private int money;
    private int pin;

    public CreateClient(Bank bank, String name, String surname, int money, int pin) {
        this.bank = bank;
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.pin = pin;
    }

    @Override
    public void execute() throws IOException {
        bank.createClient(name, surname, money, pin);
        bank.saveToDatabase();
    }
}
