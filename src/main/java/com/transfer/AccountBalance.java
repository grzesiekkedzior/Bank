package com.transfer;

import com.bank.Account;
import com.bank.Bank;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public class AccountBalance implements Transaction {
    private Bank bank;
    private String surname;
    private int pin;

    public AccountBalance(Bank bank, String surname, int pin) {
        this.bank = bank;
        this.surname = surname;
        this.pin = pin;
    }

    @Override
    public void execute() throws IOException {
        bank.accountBalance(surname, pin);
    }
}
