package com.bank;

import com.table.TableDataBase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Bank implements BankMenu, Serializable{
    private List<Account> accountList = new ArrayList<>();

    public void saveToDatabase() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/database.txt"));) {
            for (Account a : getAccountList()) {
                writer.write(String.format("%10d | %15s | %15s | %15s | %10s | %10d \r\n",
                        a.getPin(),
                        a.getNumber(),
                        a.getClient().getName(),
                        a.getClient().getSurname(),
                        a.getDate(),
                        a.getMoney()));
            }
        }
    }

    public void printAllData() {
        getAccountList().stream().forEach(System.out::println);
    }

    public void printDatabaseFromFile() {
        try (BufferedReader stream = new BufferedReader(new FileReader("src/main/resources/database.txt"))) {
            stream.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cardAuthorization(int pin) {
        return getAccountList().stream().allMatch(x -> x.getPin() == pin);
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    public void printTable() {
        new TableDataBase(this).printTable();
    }
}
