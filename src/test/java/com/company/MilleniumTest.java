package com.company;

import com.bank.Bank;
import com.transfer.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MilleniumTest {
    Bank millenium;

    @Before
    public void setUp() throws Exception {
        millenium = new Millenium();
        millenium.createClient("Jan", "Kowalski", 100, 1111);
    }

    @Test
    public void cardAuthorization() {
        int pin = millenium.getAccountList().get(0).getPin();
        Assert.assertEquals(1111, pin);
        Assert.assertNotEquals(1234, pin);
    }

    @Test
    public void createClient() {
        Bank millenium = new Millenium();
        millenium.createClient("Michal", "Kaczorowski", 100, 12);
        Assert.assertNotNull(millenium);
    }

    @Test
    public void deleteClient() {
        millenium.createClient("Michal", "Kaczorowski", 100, 12);
        millenium.deleteClient("Kowalski",1111);
        Assert.assertEquals("Kaczorowski", millenium.getAccountList().get(0).getClient().getSurname());
        Assert.assertNotEquals("Kowalski", millenium.getAccountList().get(0).getClient().getSurname());
    }

    @Test
    public void payMoney() {
        millenium.payMoney("Kowalski", 100);
        Assert.assertEquals(200, millenium.getAccountList().get(0).getMoney());
        Assert.assertNotEquals(201, millenium.getAccountList().get(0).getMoney());
    }

    @Test
    public void withdrawMoney() {
        millenium.withdrawMoney("Kowalski" ,50);
        Assert.assertEquals(50, millenium.getAccountList().get(0).getMoney());
        Assert.assertNotEquals(51, millenium.getAccountList().get(0).getMoney());
    }
}