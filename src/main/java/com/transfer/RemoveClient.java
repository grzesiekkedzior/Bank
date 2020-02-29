package com.transfer;

import com.bank.Bank;

import java.io.IOException;

public class RemoveClient implements Transaction{
    private Bank bank;
    private  String surame;
    private int pin;

    public RemoveClient(Bank bank, String surame, int pin) {
        this.bank = bank;
        this.surame = surame;
        this.pin = pin;
    }

    @Override
    public void execute() throws IOException {
        bank.deleteClient(surame, pin);
        bank.saveToDatabase();
    }
}
