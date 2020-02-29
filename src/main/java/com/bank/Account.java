package com.bank;

import com.company.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Account implements Serializable {
    private String number;
    private Client client;
    private int money;
    private int pin;
    private Date date;

    public  Account() {}

    public Account(Client client, int money, int pin) {
        this.client = client;
        this.money = money;
        this.pin = pin;
        number = AccountNumber.createUuid().toString();
        date = new Date();
    }

    public static class AccountNumber {
        public static boolean checkIfContains(List<String> uuidList, String uuid) {
            return uuidList.stream().anyMatch(x -> x.equals(uuid));
        }

        public static UUID createUuid() {
            return UUID.randomUUID();
        }
    }

    public String getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    public int getMoney() {
        return money;
    }

    public Date getDate() {
        return date;
    }

    public int getPin() {
        return pin;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPin(int i) {
        this.pin = pin;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "pin = '" + pin +
                "number = '" + number + '\'' +
                "date = '" + date.toString() +
                ", client = " + client.getName() + " " + client.getSurname() +
                ", money = " + money;
    }
}
