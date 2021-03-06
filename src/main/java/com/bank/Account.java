package com.bank;

import com.company.Client;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.bank.Account.AccountNumber.createUuid;

@Getter
@Setter
public class Account implements Serializable {
    private String number;
    private Client client;
    private int money;
    private int pin;
    private String date;

    public  Account() {}

    public Account(Client client, int money, int pin) {
        this.client = client;
        this.money = money;
        this.pin = pin;
        this.number = createUuid().toString();
        this.date = getCurrentDate();
    }

    private String getCurrentDate() {
        return new Date().toString().replaceAll(" ", "-");
    }

    public static class AccountNumber {
        public static boolean checkIfContains(List<String> uuidList, String uuid) {
            return uuidList.stream().anyMatch(x -> x.equals(uuid));
        }

        public static UUID createUuid() {
            return UUID.randomUUID();
        }
    }
}
