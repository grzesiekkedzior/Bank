package com.bank;

import java.io.IOException;

public interface BankMenu {
    void createClient(String name, String surname,int money, int pin);
    void deleteClient(String surname, int pin);
    void payMoney(String surname, int money);
    void withdrawMoney(String surname, int money);
    void accountBalance(String surname, int pin);
    void transferMoney(String owner, int money, String client) throws IOException;
}
