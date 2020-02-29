package com.bank;

import com.company.Client;
import com.company.Millenium;
import com.transfer.CreateClient;
import com.transfer.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountTest {
    private Transaction transaction;
    private Account account;
    private List<String> numberList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        account = new Account(new Client("Jan", "Kedzior"), 100, 1111);
        numberList.add(account.getNumber());
        account = new Account(new Client("Michal", "Jaskola"), 120, 2222);
        numberList.add(account.getNumber());
        account = new Account(new Client("Bronek", "Polakowski"), 140, 3333);
        numberList.add(account.getNumber());

    }

    @Test
    public void testNumerAccount() {
        String number = numberList.get(1);
        Assert.assertEquals(true, Account.AccountNumber.checkIfContains(numberList, number));
        Assert.assertNotEquals("324agewe5tggasgf", number);
    }
}