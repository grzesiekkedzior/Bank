package com.company;

import com.bank.Account;
import com.bank.Bank;
import com.table.TableDataBase;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

public class Millenium extends Bank {
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    private String bankName;

    @Override
    public void createClient(String name, String surname, int money, int pin) {
        addNewRecord(name, surname, money, pin);
    }

    @Override
    public void deleteClient(String surname, int pin) {
        Account account = getAccountData(surname, pin);
        Optional.ofNullable(account)
                .ifPresentOrElse(
                        x -> getAccountList().remove(x),
                        () -> System.out.println(RED_BOLD + "CANNOT REMOVE\nCLIENT DOES NOT EXIST!!!")
                );
    }

    @Override
    public void payMoney(String surname , int money) {
        getAccountList().stream()
                .filter(x -> x.getClient().getSurname().equals(surname))
                .forEach(x -> {
                    x.setMoney(x.getMoney() + money);
                    x.setDate(new Date());
                    System.out.println(GREEN_BOLD + "SUCCESS!!!\nYOUR ACCOUNT IS NOW " + x.getMoney());
                });
    }

    @Override
    public void transferMoney(String owner, int money, String client) throws IOException {
        getAccountList().stream()
                .filter(x -> x.getClient().getSurname().equals(owner))
                .forEach(x -> {
                    if (x.getMoney() > money) {
                        x.setMoney(x.getMoney() - money);
                        x.setDate(new Date());
                        payMoney(client, money);
                        System.out.println("SUCCESS!!!");
                    } else {
                        System.out.println("FAILD");
                    }
                });
    }

    @Override
    public void withdrawMoney(String surname, int money) {
        getAccountList().stream()
                .filter(x -> x.getClient().getSurname().equals(surname))
                .forEach(x -> {
                    if (x.getMoney() > money) {
                        x.setMoney(x.getMoney() - money);
                        x.setDate(new Date());
                        System.out.println("SUCCESS!!!");
                    } else {
                        System.out.println("FAILD");
                    }
                });
    }

    @Override
    public void accountBalance(String surname, int pin) {
        Optional.ofNullable(getAccountData(surname, pin))
                .ifPresentOrElse(
                        x -> System.out.println(GREEN_BOLD + "\nYOUR MONEY = " + x.getMoney()),
                        () -> System.out.println(RED_BOLD + "CLIENT DOES NOT EXIST!!!")
                );
    }

    private Account getAccountData(String surname, int pin) {
        return getAccountList().stream()
                .filter(x -> x.getPin()==pin)
                .filter(x -> x.getClient().getSurname().equals(surname))
                .findFirst()
                .orElse(null);
    }

    private void addNewRecord(String name, String surname, int money, int pin) {
        getAccountList().add(new Account(new Client(name, surname),money, pin));
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
